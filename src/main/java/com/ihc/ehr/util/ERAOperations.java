package com.ihc.ehr.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.ParameterMode;

import org.apache.log4j.Logger;

import com.ihc.ehr.dao.BillingDAOImpl;
import com.ihc.ehr.dao.ClaimDAOImpl;
import com.ihc.ehr.dao.GeneralDAOImpl;
import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ERAOperationResult;
import com.ihc.ehr.model.ERAPaymentPostingResponse;
import com.ihc.ehr.model.EraClaimServiceToMark;
import com.ihc.ehr.model.ORMAdjustmentReasonCodes;
import com.ihc.ehr.model.ORMClaimPaymentAdjustmentSave;
import com.ihc.ehr.model.ORMClaimPaymentSave;
import com.ihc.ehr.model.ORMDenialMessagesSave;
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMEdiFtpInfo;
import com.ihc.ehr.model.ORMEraAdjustmentSave;
import com.ihc.ehr.model.ORMEraAdjustmentsToPostGet;
import com.ihc.ehr.model.ORMEraClaimDetailGet;
import com.ihc.ehr.model.ORMEraClaimListGet;
import com.ihc.ehr.model.ORMEraClaimSave;
import com.ihc.ehr.model.ORMEraClaimServiceSave;
import com.ihc.ehr.model.ORMEraClaimServicesToPostGet;
import com.ihc.ehr.model.ORMEraProviderAdjustmentSave;
import com.ihc.ehr.model.ORMEraSave;
import com.ihc.ehr.model.ORMIdCode;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.Wrapper_ClaimPaymentSave;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class ERAOperations {

	private DBOperations db;
	private BillingGeneral billingGeneral;
	private GeneralDAOImpl generalDaoImpl;
	private ClaimDAOImpl claimDAOImpl;
	private BillingDAOImpl billingDAOImpl;

	String SFTPHOST = "";// sftp.gatewayedi.com";
	int SFTPPORT = 22;// 22;
	String SFTPUSER = "";
	String SFTPPASS = "";
	String remoteDirectory = "";// "remits for era";
	String website_host = "";
	int TotalERAsProcessed = 0;
	int TotalERAFiles = 0;
	int FilesDownloaded = 0;
	int TotalErrors = 0;
	int TotalEmptyFile = 0;
	int TotalInvalidTagFiles = 0;
	String FileProcessDetails = "";
	List<ORMEraAdjustmentSave> listAdjustments = new ArrayList<>();
	List<?> listAdjustcodes;
	ORMEraAdjustmentSave objAdjustments;
	String serverDateTime = "";
	String rootUrl = "";
	Long practiceId;
	String logedInUser = "";
	String clientDateTime = "";
	String clientIp = "";

	String eraSavePath = "";

	final Logger logger;

	ERAOperationResult eraAOperationResult;

	public ERAOperations(DBOperations dbOpt, BillingGeneral billingGeneral, GeneralDAOImpl generalDaoImpl,
			ClaimDAOImpl claimDAOImpl, BillingDAOImpl billingDAOImpl, Long practiceId, String logedInUser,
			String clientDateTime, String clientIp) {

		serverDateTime = DateTimeUtil.getCurrentDateTime();
		this.db = dbOpt;
		this.billingGeneral = billingGeneral;
		this.generalDaoImpl = generalDaoImpl;
		this.claimDAOImpl = claimDAOImpl;
		this.billingDAOImpl = billingDAOImpl;
		this.practiceId = practiceId;
		this.logedInUser = logedInUser;
		this.clientDateTime = clientDateTime;
		this.clientIp = clientIp;

		logger = Logger.getLogger(ERAOperations.class);
	}

	public ERAOperationResult DownlaodERAs(String rootUrl) {
		String result = "";
		try {

			serverDateTime = DateTimeUtil.getCurrentDateTime();

			this.rootUrl = rootUrl;

			TotalERAsProcessed = 0;
			TotalERAFiles = 0;
			FilesDownloaded = 0;
			TotalErrors = 0;
			TotalEmptyFile = 0;
			TotalInvalidTagFiles = 0;

			// List<ORMEdiFtpInfo> lstSFTPInfo =
			// this.billingGeneral.getEdiFtpInfo(practiceId.toString(), "ERA_DOWNLOAD");
			List<ORMEdiFtpInfo> lstSFTPInfo = this.billingGeneral.getEdiFtpInfo(practiceId.toString());

			if (lstSFTPInfo != null && lstSFTPInfo.size() > 0) {

				SFTPHOST = lstSFTPInfo.get(0).getFtp_host();
				SFTPPORT = Integer.parseInt(lstSFTPInfo.get(0).getFtp_port());
				SFTPUSER = lstSFTPInfo.get(0).getFtp_user();
				SFTPPASS = lstSFTPInfo.get(0).getFtp_password();
				remoteDirectory = lstSFTPInfo.get(0).getEra_download_directory();
				website_host = lstSFTPInfo.get(0).getApp_hosting_website();
			}

			if (GeneralOperations.isNullorEmpty(SFTPHOST) || GeneralOperations.isNullorEmpty(SFTPPORT)
					|| GeneralOperations.isNullorEmpty(SFTPUSER) || GeneralOperations.isNullorEmpty(SFTPPASS)
					|| GeneralOperations.isNullorEmpty(remoteDirectory)) {

				eraAOperationResult = new ERAOperationResult();
				eraAOperationResult.setMessage("SFTP is not configured for ERA.");
				eraAOperationResult.setMessage_type("ERROR");
				return eraAOperationResult;
			}

			if (!website_host.equalsIgnoreCase(this.rootUrl)) {
				eraAOperationResult = new ERAOperationResult();
				eraAOperationResult.setMessage("Operation cannot be performed from the host '" + this.rootUrl + "'");
				eraAOperationResult.setMessage_type("ERROR");
				return eraAOperationResult;
			}

			/*
			 * List<ORMEdiFtpInfo> lstSFTPInfo =
			 * this.billingGeneral.getEdiFtpInfo(practiceId.toString());
			 * 
			 * if (lstSFTPInfo != null && lstSFTPInfo.size() > 0) {
			 * 
			 * // SFTPHOST = "204.138.99.81";//sftp.gatewayedi.com"; // SFTPPORT = 22;//22;
			 * // SFTPUSER = ""; // SFTPPASS = ""; // remoteDirectory="remits"; //
			 * website_host="";
			 * 
			 * if (lstSFTPInfo.get(0).isEra_download()) {
			 * 
			 * SFTPHOST = lstSFTPInfo.get(0).getFtp_host(); SFTPPORT =
			 * Integer.parseInt(lstSFTPInfo.get(0).getFtp_port()); SFTPUSER =
			 * lstSFTPInfo.get(0).getFtp_user(); SFTPPASS =
			 * lstSFTPInfo.get(0).getFtp_password(); // remoteDirectory =
			 * lstSFTPInfo.get(0).getFtp_directory(); website_host =
			 * lstSFTPInfo.get(0).getApp_hosting_website();
			 * 
			 * } else {
			 * 
			 * eraAOperationResult = new ERAOperationResult();
			 * eraAOperationResult.setMessage("ERA Download is not enabled.");
			 * eraAOperationResult.setMessage_type("ERROR"); return eraAOperationResult;
			 * 
			 * } }
			 * 
			 * if (SFTPHOST.equalsIgnoreCase("") || SFTPHOST == null ||
			 * SFTPUSER.equalsIgnoreCase("") || SFTPUSER == null ||
			 * SFTPPASS.equalsIgnoreCase("") || SFTPPASS == null) {
			 * 
			 * eraAOperationResult = new ERAOperationResult();
			 * eraAOperationResult.setMessage("SFTP information missing.");
			 * eraAOperationResult.setMessage_type("ERROR"); return eraAOperationResult; }
			 * 
			 * if (!website_host.equalsIgnoreCase(this.rootUrl)) { eraAOperationResult = new
			 * ERAOperationResult();
			 * eraAOperationResult.setMessage("ERA's cannot be  downloaded from the host '"
			 * + this.rootUrl + "'"); eraAOperationResult.setMessage_type("ERROR"); return
			 * eraAOperationResult; }
			 */

			List<ORMDocumentPath> lst = generalDaoImpl.getDocPath(practiceId.toString(), "ERA");
			for (ORMDocumentPath orm : (List<ORMDocumentPath>) lst) {
				this.eraSavePath = orm.getUpload_path();
			}
			if (GeneralOperations.isNullorEmpty(eraSavePath)) {
				eraAOperationResult = new ERAOperationResult();
				eraAOperationResult.setMessage("EAR Download Path missing.");
				eraAOperationResult.setMessage_type("ERROR");
				return eraAOperationResult;
			}

			BufferedReader br;

			String ERAIds = "";
			try {

				JSch jsch = new JSch();
				Session session;
				Channel channel;
				ChannelSftp channelSftp;

				session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
				session.setConfig("StrictHostKeyChecking", "no");
				session.setPassword(SFTPPASS);
				System.out.println("\nConnecting....... SFTP");
				session.connect();

				channel = session.openChannel("sftp");
				System.out.println("\nConnecting....... Channel");
				channel.connect();

				// Check to see if we are connected
				if (!channel.isConnected()) {
					System.out.println("\nUnable to connect to SFTP.");

					eraAOperationResult = new ERAOperationResult();
					eraAOperationResult.setMessage("Unable to connect to SFTP.");
					eraAOperationResult.setMessage_type("ERROR");
					return eraAOperationResult;

				}
				System.out.println("\nConnected to SFTP successfully");

				channelSftp = (ChannelSftp) channel;

				// String sss=channelSftp.pwd();
				// Change directory to the remote folder
				if (remoteDirectory != null && remoteDirectory.equalsIgnoreCase("") == false) {
					System.out.println("\nBrowsing Directory: " + remoteDirectory);
					channelSftp.cd(remoteDirectory);
				}

				@SuppressWarnings({ "unchecked" })
				Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.RMT");

				if (list.isEmpty()) {
					// result = "ERROR~No ERA File found at directory '" + remoteDirectory + "'";
					// System.out.println(result);
					System.out.println("\nClosing SFTP Connectiong: ");

					channelSftp.exit();
					session.disconnect();
					// return result;

					eraAOperationResult = new ERAOperationResult();
					eraAOperationResult.setMessage("No ERA File found at directory '" + remoteDirectory + "'");
					eraAOperationResult.setMessage_type("INFO");
					return eraAOperationResult;

				}

				// int i = 0;
				System.out.println(
						"*************************************  DOWNLOADING ERA FILES **************************** ");
				ORMEraSave objERA;
				Long era_id;
				Boolean is_file_generated = false;
				for (ChannelSftp.LsEntry file : list) {

					TotalERAFiles++;
					is_file_generated = false;
					int mid = file.getFilename().lastIndexOf(".");
					String ext = file.getFilename().substring(mid + 1, file.getFilename().length());

					if (!ext.toUpperCase().equals("RMT")) {
						continue;
					}

					objERA = new ORMEraSave();
					objERA.setFile_name(file.getFilename());
					objERA.setPractice_id(practiceId);
					objERA.setCreated_user(logedInUser);
					objERA.setDate_created(serverDateTime);
					objERA.setSystem_ip(clientIp);
					objERA.setClient_date_created(clientDateTime);
					era_id = saveERA(objERA);
					if (era_id > 0) {

						String era_output_file_path = FileUtil.GetYearMonthDayWisePath(this.eraSavePath,
								practiceId.toString(), "ERA") + "\\" + era_id + ".RMT";

						channelSftp.get(file.getFilename(), era_output_file_path);

						String[] splitedPath = era_output_file_path.split("\\\\");
						String file_path = splitedPath[splitedPath.length - 4] + "\\"
								+ splitedPath[splitedPath.length - 3] + "\\" + splitedPath[splitedPath.length - 2]
								+ "\\" + era_id + ".RMT";

						String strQuery = "update era set file_path='" + file_path + "' where era_id='" + era_id + "'";

						if (this.db.ExecuteUpdateQuery(strQuery) > 0) {
							is_file_generated = true;
						}

						if (is_file_generated) {

							if (GeneralOperations.isNotNullorEmpty(ERAIds)) {
								ERAIds += "," + era_id.toString();
							} else {
								ERAIds = era_id.toString();
							}

							System.out.println("\nDeleting File: " + file.getFilename());

							// Uncoment in case of LIVE.
							channelSftp.rm(file.getFilename());
						}

						FilesDownloaded++;
					}

				}

				System.out.println("\nClosing SFTP Connectiong: ");
				channelSftp.exit();
				session.disconnect();
				System.out.println(
						"*************************************  DOWNLOADING ERA COMPLETED **************************** ");
			} catch (JSchException e) {
				// result = e.toString();

				eraAOperationResult = new ERAOperationResult();
				eraAOperationResult.setMessage(e.toString());
				eraAOperationResult.setMessage_type("ERROR");
				// return eraAOperationResult;

				System.out.println("\nError Occurrerd : \n");
				e.printStackTrace();
			} catch (SftpException e) {
				// result = e.toString();

				eraAOperationResult = new ERAOperationResult();
				eraAOperationResult.setMessage(e.toString());
				eraAOperationResult.setMessage_type("ERROR");

				System.out.println("\nError Occurrerd : \n");
				e.printStackTrace();

			}

			if (GeneralOperations.isNotNullorEmpty(ERAIds)) {
				System.out.println(
						"*************************************  PROCESSING ERA FILES **************************** ");
				List<ORMEraSave> lstERAFiles = getUnProcessedERAs(ERAIds);
				// ORMEra objORMERA;
				if (lstERAFiles != null && lstERAFiles.size() > 0) {
					for (ORMEraSave orm : lstERAFiles) {

						String FileName = orm.getFile_name();
						String file_path = eraSavePath + "\\" + practiceId + "\\ERA\\" + orm.getFile_path();

						String MainString;
						StringBuilder SBulder = new StringBuilder();
						br = new BufferedReader(new FileReader(file_path));
						try {

							String line;
							while ((line = br.readLine()) != null) {
								SBulder.append(line);
							}

						} catch (IOException exp) {
							logger.error("Logger Error function: ERA FILE PROCESSING: era_id:" + orm.getEra_id(), exp);
						} finally {
							br.close();
						}

						MainString = SBulder.toString();
						if (GeneralOperations.isNotNullorEmpty(MainString)) {
							String proceesingStatus = ProcessERAString(orm, MainString, FileName);
							if (proceesingStatus.equalsIgnoreCase("true")) {
								TotalERAsProcessed++;
							}
						}
					}
				}
				System.out.println(
						"*************************************  PROCESSING COMPLETED **************************** ");
			}

			eraAOperationResult = new ERAOperationResult();
			eraAOperationResult.setTotal_files(TotalERAFiles);
			eraAOperationResult.setDownloaded(FilesDownloaded);
			eraAOperationResult.setProcessed(TotalERAsProcessed);
			eraAOperationResult.setMessage("ERA Files Downlaoded Successfully :");
			eraAOperationResult.setMessage_type("SUCCESS");

			result = "ERA Files Downlaoded : " + Integer.toString(FilesDownloaded) + "/"
					+ Integer.toString(TotalERAFiles) + "\nFiles Processed Successfully: "
					+ Integer.toString(TotalERAsProcessed) + "\nTotal Errors : " + Integer.toString(TotalErrors);
			if (TotalInvalidTagFiles > 0) {
				eraAOperationResult.setInvalid_files(TotalInvalidTagFiles);
				// result += "\nInvalid Tags ERAs : " + Integer.toString(TotalInvalidTagFiles);
			}
			if (TotalEmptyFile > 0) {
				// result += "\nEmpty Files : " + Integer.toString(TotalEmptyFile);
				eraAOperationResult.setEmpty_files(TotalEmptyFile);
			}

		} catch (Exception e) {
			// result = e.toString();
			System.out.println("\nError Occurrerd : \n");
			e.printStackTrace();

			eraAOperationResult = new ERAOperationResult();
			eraAOperationResult.setMessage(e.toString());
			eraAOperationResult.setMessage_type("ERROR");

		}

		System.out.println(result);
		return eraAOperationResult;
	}

	public ERAOperationResult ProcessEraText(String eraString) {
		String result = "";
		try {

			serverDateTime = DateTimeUtil.getCurrentDateTime();

			TotalERAsProcessed = 0;
			TotalERAFiles = 0;
			FilesDownloaded = 0;
			TotalErrors = 0;
			TotalEmptyFile = 0;
			TotalInvalidTagFiles = 0;

			List<ORMDocumentPath> lst = generalDaoImpl.getDocPath(practiceId.toString(), "ERA");
			for (ORMDocumentPath orm : (List<ORMDocumentPath>) lst) {
				this.eraSavePath = orm.getUpload_path();
			}
			if (GeneralOperations.isNullorEmpty(eraSavePath)) {
				eraAOperationResult = new ERAOperationResult();
				eraAOperationResult.setMessage("EAR Download Path missing.");
				eraAOperationResult.setMessage_type("ERROR");
				return eraAOperationResult;
			}

			String proceesingStatus = ProcessERAString(null, eraString, "TEXT_STRING");
			if (proceesingStatus.equalsIgnoreCase("true")) {
				TotalERAsProcessed++;
			} else {
				eraAOperationResult = new ERAOperationResult();
				eraAOperationResult.setMessage(proceesingStatus);
				eraAOperationResult.setMessage_type("ERROR");
				return eraAOperationResult;
			}

			eraAOperationResult = new ERAOperationResult();
			eraAOperationResult.setTotal_files(TotalERAFiles);
			eraAOperationResult.setDownloaded(FilesDownloaded);
			eraAOperationResult.setProcessed(TotalERAsProcessed);
			eraAOperationResult.setMessage("ERA Files Downlaoded Successfully :");
			eraAOperationResult.setMessage_type("SUCCESS");

			result = "ERA Files Downlaoded : " + Integer.toString(FilesDownloaded) + "/"
					+ Integer.toString(TotalERAFiles) + "\nFiles Processed Successfully: "
					+ Integer.toString(TotalERAsProcessed) + "\nTotal Errors : " + Integer.toString(TotalErrors);
			if (TotalInvalidTagFiles > 0) {
				eraAOperationResult.setInvalid_files(TotalInvalidTagFiles);
				// result += "\nInvalid Tags ERAs : " + Integer.toString(TotalInvalidTagFiles);
			}
			if (TotalEmptyFile > 0) {
				// result += "\nEmpty Files : " + Integer.toString(TotalEmptyFile);
				eraAOperationResult.setEmpty_files(TotalEmptyFile);
			}

		} catch (Exception e) {
			// result = e.toString();
			System.out.println("\nError Occurrerd : \n");
			e.printStackTrace();

			eraAOperationResult = new ERAOperationResult();
			eraAOperationResult.setMessage(e.toString());
			eraAOperationResult.setMessage_type("ERROR");

			return eraAOperationResult;

		}

		System.out.println(result);
		return eraAOperationResult;
	}

	/*
	 * public Integer ProcessSingleERA(String era_id) {
	 * 
	 * serverDateTime = DateTimeUtil.getCurrentDateTime();
	 * 
	 * Integer result = 0; try { BufferedReader br; System.out.println(
	 * "*************************************  PROCESSING ERA FILES **************************** "
	 * ); List<ORMEraSave> lstERAFiles = getUnProcessedERAs(era_id); // ORMEra
	 * objORMERA; if (lstERAFiles != null && lstERAFiles.size() > 0) {
	 * 
	 * for (ORMEraSave orm : lstERAFiles) {
	 * 
	 * String FileName = orm.getFile_name(); String file_path = this.eraSavePath +
	 * "\\" + orm.getPractice_id() + "\\ERA\\" + orm.getFile_path();
	 * 
	 * String MainString; StringBuilder SBulder = new StringBuilder(); br = new
	 * BufferedReader(new FileReader(file_path)); try {
	 * 
	 * String line; while ((line = br.readLine()) != null) { SBulder.append(line); }
	 * 
	 * } finally { br.close(); }
	 * 
	 * MainString = SBulder.toString(); if
	 * (GeneralOperations.isNotNullorEmpty(MainString)) { String proceesingStatus =
	 * ProcessERAString(orm, MainString, false); if
	 * (proceesingStatus.equalsIgnoreCase("true")) { result++; } } } }
	 * System.out.println(
	 * "*************************************  PROCESSING COMPLETED **************************** "
	 * );
	 * 
	 * } catch (Exception e) { result = 0;
	 * System.out.println("\nError Occurrerd : \n"); e.printStackTrace(); } return
	 * result; }
	 */

	public String ProcessERAString(ORMEraSave objERA, String MainString, String fname) {

		serverDateTime = DateTimeUtil.getCurrentDateTime();

		String isProcess = "false";
		Long era_id = (long) 0;
		String error_message;
		// ORMEra objERA;

		if (objERA == null) {
			objERA = new ORMEraSave();
			objERA.setFile_name(fname);
			objERA.setPractice_id(practiceId);
			objERA.setCreated_user(logedInUser);
			objERA.setDate_created(serverDateTime);
			objERA.setSystem_ip(clientIp);
			objERA.setClient_date_created(clientDateTime);
			objERA.setIs_processed(true);
		} else {
			objERA.setIs_processed(true);
			era_id = objERA.getEra_id();
		}

		try {

			if (MainString.length() == 0) {
				TotalEmptyFile++;
				objERA.setIs_error(true);
				objERA.setCheck_date("01/01/1900");
				objERA.setPayer_name("<ERROR!>");
				objERA.setError_detail("File is Empty.");
				era_id = saveERA(objERA);
				FileProcessDetails += "\nFile is Empty.";
				return isProcess;

			} else {
				String[] ERASegments = MainString.split("~");
				String checkDate = "00000000";
				String checkNumber = "";
				String checkAmount = "0";

				// BPR : Beginning Segment for Payment Order/Remittance Advice
				if (ERASegments.length > 0) {
					int index = 0;
					String[] ERASub;
					String segmentTag = "";

					do {
						ERASub = ERASegments[index].split("\\*");
						segmentTag = ERASub[0];
						index++;

					} while (!segmentTag.equalsIgnoreCase("BPR") && index != ERASegments.length);

					index--;

					if (segmentTag.equals("") || !segmentTag.equals("BPR")) {
						TotalInvalidTagFiles++;
						objERA.setCheck_date("01/01/1900");
						objERA.setPayer_name("<ERROR!>");
						objERA.setIs_error(true);
						objERA.setError_detail("BPR Segment is missing.");
						era_id = saveERA(objERA);
						FileProcessDetails += "\nBPR Segment is missing.";

						// if (era_id.equalsIgnoreCase("")) {
						// DeleteERAFile = false;
						// } else {
						// DeleteERAFile = true;
						// }
						// return isProcess;
						// DeleteERAFile = GenerateERAFile(era_id, MainString, save_path);
						return isProcess;

					} else {

						// Beginning Segment for Payment Order/Remittance Advice
						if (segmentTag.equalsIgnoreCase("BPR")) {

							objERA.setTransaction_handling_code(ERASub[1]);
							checkAmount = ERASub[2];
							objERA.setCheck_amount(checkAmount);
							objERA.setPayment_method_code(ERASub[4]);
							checkDate = ERASub[16].substring(4, 6) + "/" + ERASub[16].substring(6, 8) + "/"
									+ ERASub[16].substring(0, 4);
							objERA.setCheck_date(checkDate);

							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// REASSOCIATION TRACE NUMBER
						if (segmentTag.equalsIgnoreCase("TRN")) {
							checkNumber = ERASub[2];
							objERA.setCheck_number(ERASub[2]);

							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// FOREIGN CURRENCY INFORMATION
						if (segmentTag.equalsIgnoreCase("CUR")) {
							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// Check if Already Exist in case of Manual Proccessing.....
						if (fname.contains("TEXT_STRING")) {
							if (IsERAExist(practiceId, checkNumber, checkDate, checkAmount)) {
								return "ERA Already Exist with the Same Details:\n" + "Check Number:" + checkNumber
										+ "\n" + "Check Date:" + checkDate + "\n" + "Check Amount:$" + checkAmount;
							}
						}

						//
						// RECEIVER IDENTIFICATION
						// Use this segment only when the receiver of the transaction is other
						// than the payee (e.g., Clearing House or billing service ID).
						if (segmentTag.equalsIgnoreCase("REF")) {
							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// VERSION IDENTIFICATION
						if (segmentTag.equalsIgnoreCase("REF")) {
							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// PRODUCTION DATE
						if (segmentTag.equalsIgnoreCase("DTM")) {
							String productionDate = ERASub[2].substring(4, 6) + "/" + ERASub[2].substring(6, 8) + "/"
									+ ERASub[2].substring(0, 4);

							objERA.setProduction_date(productionDate);

							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// PAYER IDENTIFICATION
						if (segmentTag.equalsIgnoreCase("N1")) {
							// if(ERASub[1].toString().equalsIgnoreCase("PR"))
							// {
							// objERA.setInsurance_type("PRIMARY");
							// }
							// else if(ERASub[1].toString().equalsIgnoreCase("SR"))
							// {
							// objERA.setInsurance_type("SECONDARY");
							// }

							if (ERASub.length > 2) {
								objERA.setPayer_name(ERASub[2]);
							}
							if (ERASub.length > 3) {
								objERA.setPayer_identifier_qualifier(ERASub[3]);
							}
							if (ERASub.length > 4) {
								objERA.setPayer_identifier(ERASub[4]);
							}

							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						if (segmentTag.equalsIgnoreCase("N3")) {

							if (ERASub.length > 2) // two line address
							{
								objERA.setPayer_address(ERASub[1] + "<BR>" + ERASub[2]);
							} else if (ERASub.length > 1) // single line address
							{
								objERA.setPayer_address(ERASub[1]);
							}

							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						if (segmentTag.equalsIgnoreCase("N4")) {
							if (ERASub.length > 1) {
								objERA.setPayer_city(ERASub[1]);
							}
							if (ERASub.length > 2) {
								objERA.setPayer_state(ERASub[2]);
							}
							if (ERASub.length > 3) {
								objERA.setPayer_zip(ERASub[3]);
							}
							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// ADDITIONAL PAYER IDENTIFICATION
						while (segmentTag.equalsIgnoreCase("REF")) // skip
						{
							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// PAYER CONTACT INFORMATION
						while (segmentTag.equalsIgnoreCase("PER")) {
							ERASub = ERASegments[index].split("\\*");
							if (ERASub.length > 1) {
								switch (ERASub[1]) {
								case "CX":// PER*CX*FORWARDHEALTH*TE*8009479627
									if (ERASub.length > 2) {
										objERA.setPayer_business_contact_information(ERASub[2]);
									}
									if (ERASub.length > 4) {
										objERA.setPayer_business_contact_information(
												objERA.getPayer_business_contact_information() + " " + ERASub[3] + ":"
														+ ERASub[4]);
									}
									if (ERASub.length > 6) {
										objERA.setPayer_business_contact_information(
												objERA.getPayer_business_contact_information() + " " + ERASub[5] + ":"
														+ ERASub[6]);
									}
									break;
								case "BL":// PER*BL*EDI SOLUTIONS*TE*8004709630*EM*ANTHEM.EDI@ANTHEM.COM
									if (ERASub.length > 2) {
										objERA.setPayer_technical_contact_information(ERASub[2]);
									}
									if (ERASub.length > 4) {
										objERA.setPayer_technical_contact_information(
												objERA.getPayer_technical_contact_information() + " " + ERASub[3] + ":"
														+ ERASub[4]);
									}
									if (ERASub.length > 6) {
										objERA.setPayer_technical_contact_information(
												objERA.getPayer_technical_contact_information() + " " + ERASub[5] + ":"
														+ ERASub[6]);
									}
									break;
								case "IC": // PER*IC**UR*WWW.ANTHEM.COM/PROVIDER/ROUTER
									if (ERASub.length > 4) {
										objERA.setPayer_website(ERASub[4]);
									}
									break;
								}
							}
							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}
						// if (segmentTag.equalsIgnoreCase("PER")) {
						// objERA.setPayer_contact_information(ERASegments[index]);
						//
						// index++;
						// ERASub = ERASegments[index].split("\\*");
						// segmentTag = ERASub[0];
						// }

						// PAYEE IDENTIFICATION
						if (segmentTag.equalsIgnoreCase("N1")) {
							if (ERASub.length > 2) {
								objERA.setPayee_name(ERASub[2]);
							}
							if (ERASub.length > 3) {
								objERA.setPayee_identifier_code_qualifier(ERASub[3]);
							}
							if (ERASub.length > 4) {
								objERA.setPayee_identifier_code(ERASub[4]);
							}

							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// PAYEE ADDRESS
						if (segmentTag.equalsIgnoreCase("N3")) {
							// objERA.setPayee_address(ERASub[1]);

							if (ERASub.length > 2) // two line address
							{
								objERA.setPayee_address(ERASub[1] + "<BR>" + ERASub[2]);
							} else if (ERASub.length > 1) // single line address
							{
								objERA.setPayee_address(ERASub[1]);
							}

							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// PAYEE CITY, STATE, ZIP CODE
						if (segmentTag.equalsIgnoreCase("N4")) {
							if (ERASub.length > 1) {
								objERA.setPayee_city(ERASub[1]);
							}
							if (ERASub.length > 2) {
								objERA.setPayee_state(ERASub[2]);
							}
							if (ERASub.length > 3) {
								objERA.setPayee_zip(ERASub[3]);
							}

							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// PAYEE ADDITIONAL IDENTIFICATION
						while (segmentTag.equalsIgnoreCase("REF")) // skip repeat >1
						{
							if (objERA.getAdditional_payee_identifier_code_qualifier() != null
									&& !objERA.getAdditional_payee_identifier_code_qualifier().equalsIgnoreCase("")) {
								objERA.setAdditional_payee_identifier_code_qualifier(
										objERA.getAdditional_payee_identifier_code_qualifier() + ", " + ERASub[1]);
								objERA.setAdditional_payee_identifier_code(
										objERA.getAdditional_payee_identifier_code() + ", " + ERASub[2]);
							} else {
								objERA.setAdditional_payee_identifier_code_qualifier(ERASub[1]);
								objERA.setAdditional_payee_identifier_code(ERASub[2]);

							}
							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// HEADER NUMBER
						if (segmentTag.equalsIgnoreCase("LX")) {
							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// PROVIDER SUMMARY INFORMATION
						if (segmentTag.equalsIgnoreCase("TS3")) {
							objERA.setProvider_summary(ERASegments[index]);

							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// PROVIDER SUPPLEMENTAL SUMMARY INFORMATION
						if (segmentTag.equalsIgnoreCase("TS2")) {
							objERA.setProvider_supplemental_summary(ERASegments[index]);

							index++;
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
						}

						// save ERA
						era_id = saveERA(objERA);
						objERA.setEra_id(era_id);

						if (era_id == null || era_id <= 0) {
							// FileProcessDetails+="\nERA could not be saved.";
							TotalErrors++;

							objERA.setIs_error(true);
							objERA.setError_detail("Error occured while saving ERA.");
							FileProcessDetails += "\nError occured while saving ERA.";
							era_id = saveERA(objERA);

							// if(GeneralOperations.isNotNullorEmpty(era_id))
							// {
							// DeleteERAFile = GenerateERAFile(era_id, MainString, save_path);
							// }
							// else
							// {
							// DeleteERAFile=false;
							// }
							//
							return isProcess;

						}
						FileProcessDetails += "\nERA ID : " + era_id;
						objERA.setEra_id(era_id);
						int w = 0;

						// CLAIM PAYMENT INFORMATION
						while (segmentTag.toString().equalsIgnoreCase("CLP")) {

							w++;
							// System.out.println("\nERA Claim # : "+w);
							FileProcessDetails += "\nERA Claim S.No : " + w;

							Long eraclaim_id = (long) 0;
							ORMEraClaimSave objClaims = new ORMEraClaimSave();
							objClaims.setEra_id(era_id);
							objClaims.setPractice_id(practiceId);
							objClaims.setCreated_user(logedInUser);
							objClaims.setClient_date_created(clientDateTime);
							objClaims.setSystem_ip(clientIp);
							objClaims.setDate_created(GeneralOperations.CurrentDateTime());

							String[] subClaim = ERASegments[index].split("\\*");

							if (subClaim.length > 0) {
								// Claim Submitter’s Identifier
								String claim_id = "";
								if (subClaim.length > 1 && !subClaim[1].equalsIgnoreCase("")) {
									// claim_id=subClaim[1].replace("-", "").replace(" ", "");
									claim_id = GeneralOperations.getNumericOnly(subClaim[1]);

									objClaims.setClaim_id(claim_id);
									FileProcessDetails += "\nEncounter ID # : " + claim_id;

									if (claim_id.equalsIgnoreCase("5241013691")) {
										System.out.println(claim_id);
									}

									try {

										String PatientID = getPatientID(claim_id);
										if (!PatientID.equalsIgnoreCase("")) {
											objClaims.setPatient_id(PatientID);
										}
									} catch (Exception ex) {
									}
								}
								// Claim Status Code
								if (subClaim.length > 2 && !subClaim[2].equalsIgnoreCase("")) {
									objClaims.setClaim_status_code(subClaim[2]);
								}
								// Total Claim Charge Amount
								if (subClaim.length > 3 && !subClaim[3].equalsIgnoreCase("")) {
									objClaims.setClaim_billed_amount(subClaim[3]);
								}
								// Claim Payment Amount
								if (subClaim.length > 4 && !subClaim[4].equalsIgnoreCase("")) {
									objClaims.setClaim_paid_amount(subClaim[4]);
								}
								// Patient Responsibility Amount
								if (subClaim.length > 5 && !subClaim[5].equalsIgnoreCase("")) {
									objClaims.setPatient_responsibility(subClaim[5]);
								}
								// Claim Filing Indicator Code
								if (subClaim.length > 6 && !subClaim[6].equalsIgnoreCase("")) {
									objClaims.setClaim_filing_indicator_code(subClaim[6]);
								}
								// Payer Claim Control Number
								if (subClaim.length > 7 && !subClaim[7].equalsIgnoreCase("")) {
									objClaims.setPayer_claim_control_number(subClaim[7]);
								}
							}

							index++;
							subClaim = ERASegments[index].split("\\*");

							// CLAIM ADJUSTMENT
							while (subClaim[0].toString().equalsIgnoreCase("CAS")) // repeat 99
							{
								if (objClaims.getClaim_adj_codes() != null
										&& !objClaims.getClaim_adj_codes().equalsIgnoreCase("")) {
									if (subClaim.length > 2) {
										objClaims.setClaim_adj_codes(objClaims.getClaim_adj_codes() + ", " + subClaim[1]
												+ "-" + subClaim[2]);
									}
									if (subClaim.length > 3) {
										double total_adjustment = Double.parseDouble(objClaims.getClaim_adj_amount())
												+ Double.parseDouble(subClaim[3]);
										objClaims.setClaim_adj_amount(Double.toString(total_adjustment));
									}
								} else {
									if (subClaim.length > 2) {
										objClaims.setClaim_adj_codes(subClaim[1] + "-" + subClaim[2]);
									}
									if (subClaim.length > 3) {
										objClaims.setClaim_adj_amount(subClaim[3]);
									}
								}

								if (subClaim[1].equalsIgnoreCase("PR")) {
									if (subClaim.length > 2) {
										if (objClaims.getPatient_responsibility_reason_code() == null || objClaims
												.getPatient_responsibility_reason_code().equalsIgnoreCase("")) {
											objClaims.setPatient_responsibility_reason_code(
													subClaim[1] + "-" + subClaim[2]);
										} else {
											objClaims.setPatient_responsibility_reason_code(
													objClaims.getPatient_responsibility_reason_code() + ", "
															+ subClaim[1] + "-" + subClaim[2]);
										}
									}
								}

								index++;
								subClaim = ERASegments[index].split("\\*");

							}

							if (subClaim[0].toString().equalsIgnoreCase("AMT")) {
								if (subClaim.length > 0) {
									if (subClaim[1].equalsIgnoreCase("I")) {
										objClaims.setClaim_interest(subClaim[2]);
									}
								}

								index++;
								subClaim = ERASegments[index].split("\\*");
							}

							// PATIENT NAME
							while (subClaim[0].toString().equalsIgnoreCase("NM1")) {
								switch (subClaim[1].toUpperCase()) {
								case "QC": // PATIENT NAME
									// if(subClaim.length>2)
									// {
									// objClaims.setEntity_type(subClaim[2]);
									// }
									if (subClaim.length > 3) {
										objClaims.setPatient_lname(subClaim[3]);
									}
									if (subClaim.length > 4) {
										objClaims.setPatient_fname(subClaim[4]);
									}
									if (subClaim.length > 5) {
										objClaims.setPatient_mname(subClaim[5]);
									}
									if (subClaim.length > 8) {
										objClaims.setPatient_identifier_qualfier(subClaim[8]);
									}
									if (subClaim.length > 9 && !subClaim[8].equalsIgnoreCase("")) {
										objClaims.setPatient_identifier(subClaim[9]);
									}

									break;
								case "IL":// INSURED NAME
									if (subClaim.length > 0) {
										if (subClaim[1].equalsIgnoreCase("IL")) {
											if (subClaim.length > 2) {
												objClaims.setSubscriber_entity_type(subClaim[2]);
											}
											if (subClaim.length > 3) {
												objClaims.setSubscriber_lname(subClaim[3]);
											}
											if (subClaim.length > 4) {
												objClaims.setSubscriber_fname(subClaim[4]);
											}
											if (subClaim.length > 5) {
												objClaims.setSubscriber_mname(subClaim[5]);
											}
											if (subClaim.length > 8 && !subClaim[8].equalsIgnoreCase("")) {
												objClaims.setSubscriber_identifier_qualfier(subClaim[8]);
											}
											if (subClaim.length > 9 && !subClaim[9].equalsIgnoreCase("")) {
												objClaims.setSubscriber_identifier(subClaim[9]);
											}
										}
									}

									break;
								case "82": // SERVICE PROVIDER NAME
									if (subClaim.length > 0) {
										if (subClaim.length > 3) {
											objClaims.setRendering_provider_lname(subClaim[3]);
										}
										if (subClaim.length > 4) {
											objClaims.setRendering_provider_fname(subClaim[4]);
										}
										if (subClaim.length > 8 && !subClaim[8].equalsIgnoreCase("")) {
											objClaims.setRendering_provider_identifier_qualifier(subClaim[8]);
										}
										if (subClaim.length > 9 && !subClaim[9].equalsIgnoreCase("")) {
											objClaims.setRendering_provider_identifier(subClaim[9]);
										}
									}
									break;

								case "74": // CORRECTED PATIENT/INSURED NAME //
											// NM1*74*1*SHEPARD*SAMUEL*O***C*666666666A
									if (subClaim.length > 0) {
										if (subClaim[1].equalsIgnoreCase("74")) {
											if (subClaim.length > 2) {
												objClaims.setCorrected_insured_entity_type(subClaim[2]);
											}
											if (subClaim.length > 3) {
												objClaims.setCorrected_insured_lname(subClaim[3]);
											}
											if (subClaim.length > 4) {
												objClaims.setCorrected_insured_fname(subClaim[4]);
											}
											if (subClaim.length > 5) {
												objClaims.setCorrected_insured_mname(subClaim[5]);
											}
											if (subClaim.length > 8 && !subClaim[8].equalsIgnoreCase("")) {
												objClaims.setCorrected_insured_identifier_qualifier(subClaim[8]);
											}
											if (subClaim.length > 9 && !subClaim[9].equalsIgnoreCase("")) {
												objClaims.setCorrected_insured_identifier(subClaim[9]);
											}
										}
									}
									break;
								case "TT": // NM1*TT*2*WISCONSIN MEDICAID*****PI*70028
									if (subClaim.length > 3) {
										objClaims.setCrossover_carrier_name(subClaim[3]);
									}
									break;
								}
								index++;
								subClaim = ERASegments[index].split("\\*");
							}

							// INPATIENT ADJUDICATION INFORMATION
							if (subClaim[0].equalsIgnoreCase("MIA")) // Medicare Inpatient Adjudication
							{
								index++;
								subClaim = ERASegments[index].split("\\*");
							}

							// OUTPATIENT ADJUDICATION INFORMATION
							if (subClaim[0].equalsIgnoreCase("MOA")) // Medicare outpatient Adjudication
							{
								if (subClaim.length > 3) {
									objClaims.setClaim_remark_codes(subClaim[3]);
								}
								if (subClaim.length > 4 && !subClaim[4].equalsIgnoreCase("")) {
									objClaims.setClaim_remark_codes(
											objClaims.getClaim_remark_codes() + "," + subClaim[4]);
								}
								index++;
								subClaim = ERASegments[index].split("\\*");
							}

							// OTHER CLAIM RELATED IDENTIFICATION
							while (subClaim[0].equalsIgnoreCase("REF")) // SKIP
							{

								// it gives wrong policy no...
								// switch (subClaim[1].toUpperCase()) {
								// case "1L": // Group or Policy Number e.g REF*1L*76030091
								// case "1W": // Member Identification Number e.g REF*1W*VZW902M72381
								// if (subClaim.length > 2) {
								// objClaims.setPatient_identifier_qualfier(subClaim[1]); //It will always b MI
								// for IW
								// objClaims.setPatient_identifier(subClaim[2]);
								// }
								// break;
								// }
								index++;
								subClaim = ERASegments[index].split("\\*");
							}

							// CLAIM DATE
							String claim_begin_service_date = "";
							String claim_end_service_date = "";
							while (subClaim[0].equalsIgnoreCase("DTM")) // repeat 4
							{
								if (subClaim.length > 2) {
									switch (subClaim[1].toUpperCase()) {
									case "232":
										claim_begin_service_date = subClaim[2].substring(4, 6) + "/"
												+ subClaim[2].substring(6, 8) + "/" + subClaim[2].substring(0, 4);
										objClaims.setClaim_statement_period_start(claim_begin_service_date);
										break;
									case "233":
										claim_end_service_date = subClaim[2].substring(4, 6) + "/"
												+ subClaim[2].substring(6, 8) + "/" + subClaim[2].substring(0, 4);
										objClaims.setClaim_statemnent_period_end(claim_end_service_date);
										break;
									// case "036": Expiration -- Use this code to convey the expiration date of
									// coverage.//
									// break;
									// case "050": Received -- Use this code to convey the date that the claim was
									// received by the payer.//
									// break;
									}
								}

								index++;
								subClaim = ERASegments[index].split("\\*");
							}

							while (subClaim[0].equalsIgnoreCase("PER")) // skip repeat 3
							{
								index++;
								subClaim = ERASegments[index].split("\\*");
							}

							// CLAIM SUPPLEMENTAL INFORMATION
							while (subClaim[0].equalsIgnoreCase("AMT")) // repeat 14
							{
								if (subClaim.length > 1) {
									switch (subClaim[1].toUpperCase()) {
									case "AU": // Coverage Amount
										if (subClaim.length > 2) {
											objClaims.setCoverage_amount(subClaim[2]);
										}
										break;
									case "F5": // Patient Amount Paid
										if (subClaim.length > 2) {
											objClaims.setPatient_responsibility(subClaim[2]);
										}
										break;
									case "I": // Interest
										if (subClaim.length > 2) {
											objClaims.setClaim_interest(subClaim[2]);
										}
										break;
									}

								}
								index++;
								subClaim = ERASegments[index].split("\\*");
							}

							// CLAIM SUPPLEMENTAL INFORMATION QUANTITY
							while (subClaim[0].equalsIgnoreCase("QTY")) // repeat 15
							{
								if (subClaim.length > 2) {
									if (objClaims.getClaim_supplemental_information_quantity_qualifier() == null
											|| objClaims.getClaim_supplemental_information_quantity_qualifier()
													.equalsIgnoreCase("")) {
										objClaims.setClaim_supplemental_information_quantity_qualifier(subClaim[1]);
										objClaims.setClaim_supplemental_information_quantity(subClaim[2]);
									} else {
										objClaims.setClaim_supplemental_information_quantity_qualifier(
												objClaims.getClaim_supplemental_information_quantity_qualifier() + ", "
														+ subClaim[1]);
										objClaims.setClaim_supplemental_information_quantity(
												objClaims.getClaim_supplemental_information_quantity() + ", "
														+ subClaim[2]);
									}
								}
								index++;
								subClaim = ERASegments[index].split("\\*");
							}

							// save ERAclaim
							eraclaim_id = saveERAClaim(objClaims);
							objClaims.setEra_claim_id(eraclaim_id);

							FileProcessDetails += "\nERA Claim ID : " + eraclaim_id;

							if (eraclaim_id == null || eraclaim_id <= 0) {
								FileProcessDetails += "\nError occured while saving  ERA Claim.";
								throw new Exception("Error occured while saving  ERA Claim.");
							}

							// listORMEraClaim.add(objClaims);
							// -----------------------------------------
							// SERVICE PAYMENT INFORMATION
							ERASub = ERASegments[index].split("\\*");
							segmentTag = ERASub[0];
							String[] subProcedures;
							// int id = 1;

							if (segmentTag.equalsIgnoreCase("SVC")) {
								while (segmentTag.equalsIgnoreCase("SVC")) {
									ORMEraClaimServiceSave objProcedures = new ORMEraClaimServiceSave();
									objProcedures.setEra_claim_id(eraclaim_id);
									objProcedures.setEra_id(era_id);

									objProcedures.setSystem_ip(clientIp);
									objProcedures.setClient_date_created(clientDateTime);
									objProcedures.setCreated_user(logedInUser);
									objProcedures.setDate_created(GeneralOperations.CurrentDateTime());

									// id++;
									subProcedures = ERASegments[index].split("\\*");

									if (subProcedures.length > 0) {
										if (subProcedures.length > 1) {
											String[] codes = subProcedures[1].split(":");
											if (codes.length > 1) {
												if (codes[0].toString().equalsIgnoreCase("HC")) {
													objProcedures.setPaid_procedure(codes[1].toString());
													objProcedures.setCharged_procedure(codes[1].toString());

													if (codes.length > 2) {
														objProcedures.setPaid_procedure_modifier(codes[2]);
														objProcedures
																.setCharged_procedure_modifier(codes[2].toString());
													}
												}
											}

										}
										if (subProcedures.length > 2) {
											objProcedures.setBilled_amount(subProcedures[2]);
										}
										if (subProcedures.length > 3) {
											objProcedures.setPaid_amount(subProcedures[3]);
										}
										if (subProcedures.length > 5) {

											objProcedures.setPaid_units(subProcedures[5]);
										} else // default value is 1
										{
											objProcedures.setPaid_units("1");
										}
										// if charged is different then paid cpt
										if (subProcedures.length > 6 && !subProcedures[6].equalsIgnoreCase("")) {

											String[] codes = subProcedures[6].split(":");
											if (codes.length > 1) {
												if (codes[0].toString().equalsIgnoreCase("HC")) {
													objProcedures.setPaid_procedure(codes[1].toString());
													objProcedures.setCharged_procedure(codes[1].toString());

													if (codes.length > 2) {
														objProcedures.setPaid_procedure_modifier(codes[2]);
														objProcedures
																.setCharged_procedure_modifier(codes[2].toString());
													}
												}
											}

											// String[] codes = subProcedures[6].split(":");
											// if (codes[0].startsWith("N1") == false
											// && codes[0].startsWith("N2") == false
											// && codes[0].startsWith("N3") == false
											// && codes[0].startsWith("N4") == false) {
											// if (codes.length > 1) {
											// objProcedures.setCharged_procedure(codes[1]);
											//
											// }
											// if (codes.length > 2) {
											// objProcedures.setCharged_procedure_modifier(codes[2]);
											//
											// }
											// }
										}

										index++;
										subProcedures = ERASegments[index].split("\\*");

										String begin_service_date = "";
										String end_service_date = "";

										// SERVICE DATE
										while (subProcedures[0].equalsIgnoreCase("DTM")) // repeate 3
										{
											if (subProcedures.length > 2) {
												switch (subProcedures[1].toUpperCase()) {
												case "150": // Service Period -- Use this code only for reporting
															// the
															// beginning of multi-day services.
													begin_service_date = subProcedures[2].substring(4, 6) + "/"
															+ subProcedures[2].substring(6, 8) + "/"
															+ subProcedures[2].substring(0, 4);
													break;
												case "151": // Service Period End -- Use this code only for
															// reporting
															// the end of multi-day services.
													end_service_date = subProcedures[2].substring(4, 6) + "/"
															+ subProcedures[2].substring(6, 8) + "/"
															+ subProcedures[2].substring(0, 4);
													break;
												case "472": // Service ADVISED Use this code to indicate a single
															// day
															// service.
													begin_service_date = subProcedures[2].substring(4, 6) + "/"
															+ subProcedures[2].substring(6, 8) + "/"
															+ subProcedures[2].substring(0, 4);
													end_service_date = subProcedures[2].substring(4, 6) + "/"
															+ subProcedures[2].substring(6, 8) + "/"
															+ subProcedures[2].substring(0, 4);
													break;
												}
											}

											index++;
											subProcedures = ERASegments[index].split("\\*");
										}
										if (begin_service_date == null || begin_service_date.equalsIgnoreCase("")) {
											begin_service_date = claim_begin_service_date;
											end_service_date = claim_end_service_date;
										}
										objProcedures.setEnd_service_date(begin_service_date);
										objProcedures.setBegin_service_date(end_service_date);

										// SERVICE ADJUSTMENT
										if (subProcedures[0].equalsIgnoreCase("CAS")) {
											while (subProcedures[0].equalsIgnoreCase("CAS")) {
												if (subProcedures.length > 1) {
													// Contractual Obligations
													if (subProcedures[1].equalsIgnoreCase("CO")) {
														for (int co = 2; co < subProcedures.length; co += 3) {
															switch (subProcedures[co].toLowerCase()) {
															case "104": // RISK AMOUNT
																if (objProcedures.getRisk_amount() == null) {
																	objProcedures.setRisk_amount(subProcedures[co + 1]);
																} else {
																	double risk_amount = Double
																			.parseDouble(objProcedures.getRisk_amount())
																			+ Double.parseDouble(subProcedures[co + 1]);
																	objProcedures.setRisk_amount(
																			Double.toString(risk_amount));
																}
																break;
															case "2": // Co-Ins Amount
																if (objProcedures.getCoins_amount() == null) {
																	objProcedures
																			.setCoins_amount(subProcedures[co + 1]);
																} else {
																	double CoIns_amount = Double.parseDouble(
																			objProcedures.getCoins_amount())
																			+ Double.parseDouble(subProcedures[co + 1]);
																	objProcedures.setCoins_amount(
																			Double.toString(CoIns_amount));
																}
																break;
															// case "223": // Federal Adjustment
															// if(objProcedures.getAdjust_codes()==null)
															// {
															// objProcedures.setAdjust_codes(subProcedures[1]+"-"+subProcedures[co]);
															// }
															// else
															// {
															// objProcedures.setAdjust_codes(objProcedures.getAdjust_codes()+","+
															// subProcedures[1]+"-"+subProcedures[co]);
															// }
															// if(objProcedures.getOther_adjusts()==null)
															// {
															// objProcedures.setOther_adjusts(subProcedures[co+1]);
															// }
															// else
															// {
															// double other_adjust=Double.parseDouble(
															// objProcedures.getOther_adjusts())+Double.parseDouble(
															// subProcedures[co+1]);
															// objProcedures.setOther_adjusts(Double.toString(
															// other_adjust));
															//
															// }
															//
															// addAdjustCode(subProcedures[1],subProcedures[co],
															// subProcedures[co+1]);
															//
															// break;
															default:
																if (objProcedures.getAdjust_codes() == null) {
																	objProcedures.setAdjust_codes(
																			subProcedures[1] + "-" + subProcedures[co]);
																} else {

																	if (!objProcedures.getAdjust_codes()
																			.contains((subProcedures[1] + "-"
																					+ subProcedures[co]))) {
																		objProcedures.setAdjust_codes(
																				objProcedures.getAdjust_codes() + ","
																						+ subProcedures[1] + "-"
																						+ subProcedures[co]);
																	}
																}

																if (objProcedures.getOther_adjusts() == null) {
																	objProcedures
																			.setOther_adjusts(subProcedures[co + 1]);
																} else {
																	double other_adjust = Double.parseDouble(
																			objProcedures.getOther_adjusts())
																			+ Double.parseDouble(subProcedures[co + 1]);
																	objProcedures.setOther_adjusts(
																			Double.toString(other_adjust));
																}

																addAdjustCodeToList(subProcedures[1], subProcedures[co],
																		subProcedures[co + 1]);

																break;

															}
														}
													} else if (subProcedures[1].equalsIgnoreCase("PR")) // Patient
																										// Responsibility
													{
														for (int pr = 2; pr < subProcedures.length; pr += 3) {
															switch (subProcedures[pr].toLowerCase()) {
															case "1":
																if (subProcedures.length > pr) {
																	if (objProcedures.getAllowed_amount() != null) {
																		double allowed_amount = Double.parseDouble(
																				objProcedures.getAllowed_amount())
																				+ Double.parseDouble(
																						subProcedures[pr + 1]);
																		objProcedures.setAllowed_amount(
																				Double.toString(allowed_amount));
																	} else {
																		objProcedures.setAllowed_amount(
																				subProcedures[pr + 1]);
																	}

																	objProcedures
																			.setDeduct_amount(subProcedures[pr + 1]);
																}

																break;
															case "2": // Co-Ins Amount
															case "02":// Co-Ins Amount
																if (subProcedures.length > pr) {
																	if (objProcedures.getAllowed_amount() != null) {
																		double allowed_amount = Double.parseDouble(
																				objProcedures.getAllowed_amount())
																				+ Double.parseDouble(
																						subProcedures[pr + 1]);
																		objProcedures.setAllowed_amount(
																				Double.toString(allowed_amount));
																	} else {
																		objProcedures.setAllowed_amount(
																				subProcedures[pr + 1]);
																	}

																	objProcedures
																			.setCoins_amount(subProcedures[pr + 1]);
																}
																break;
															case "3":
																if (subProcedures.length > pr) {
																	if (objProcedures.getAllowed_amount() != null) {
																		double allowed_amount = Double.parseDouble(
																				objProcedures.getAllowed_amount())
																				+ Double.parseDouble(
																						subProcedures[pr + 1]);
																		objProcedures.setAllowed_amount(
																				Double.toString(allowed_amount));
																	} else {
																		objProcedures.setAllowed_amount(
																				subProcedures[pr + 1]);
																	}
																	objProcedures
																			.setCopay_amount(subProcedures[pr + 1]);
																}

																break;
															default:
																if (subProcedures.length > pr) {
																	if (objProcedures.getOther_adjusts() == null) {
																		objProcedures.setOther_adjusts(
																				subProcedures[pr + 1]);
																	} else {
																		double other_adjust = Double.parseDouble(
																				objProcedures.getOther_adjusts())
																				+ Double.parseDouble(
																						subProcedures[pr + 1]);
																		objProcedures.setOther_adjusts(
																				Double.toString(other_adjust));
																	}
																	if (objProcedures.getAdjust_codes() == null) {
																		objProcedures.setAdjust_codes(subProcedures[1]
																				+ "-" + subProcedures[pr]);
																	} else {

																		if (!objProcedures.getAdjust_codes()
																				.contains((subProcedures[1] + "-"
																						+ subProcedures[pr]))) {
																			objProcedures.setAdjust_codes(
																					objProcedures.getAdjust_codes()
																							+ "," + subProcedures[1]
																							+ "-" + subProcedures[pr]);
																		}

																		// objProcedures.setAdjust_codes(
																		// objProcedures.getAdjust_codes() + ","
																		// + subProcedures[1] + "-"
																		// + subProcedures[pr]);
																	}

																	addAdjustCodeToList(subProcedures[1],
																			subProcedures[pr], subProcedures[pr + 1]);

																}
																break;

															}

															if (subProcedures.length > pr) {
																if (objProcedures.getPatient_responsibility() != null) {
																	double patient_responsibility = Double.parseDouble(
																			objProcedures.getPatient_responsibility())
																			+ Double.parseDouble(subProcedures[pr + 1]);
																	objProcedures.setPatient_responsibility(
																			Double.toString(patient_responsibility));

																} else {
																	objProcedures.setPatient_responsibility(
																			subProcedures[pr + 1]);
																}

															}
														}
													} else if (subProcedures[1].equalsIgnoreCase("CR")) // Correction
																										// and
																										// Reversals
													{
														objProcedures.setCorrection_reversals(subProcedures[3]);
													} else if (subProcedures[1].equalsIgnoreCase("OA")) // Other
																										// adjustments
													{
														for (int qa = 2; qa < subProcedures.length; qa += 3) {
															if (objProcedures.getOther_adjusts() == null) {
																objProcedures.setOther_adjusts(subProcedures[qa + 1]);
															} else {
																double other_adjust = Double
																		.parseDouble(objProcedures.getOther_adjusts())
																		+ Double.parseDouble(subProcedures[qa + 1]);
																objProcedures.setOther_adjusts(
																		Double.toString(other_adjust));
															}
															if (objProcedures.getAdjust_codes() == null) {
																objProcedures.setAdjust_codes(
																		subProcedures[1] + "-" + subProcedures[qa]);
															} else {

																if (!objProcedures.getAdjust_codes().contains(
																		(subProcedures[1] + "-" + subProcedures[qa]))) {
																	objProcedures.setAdjust_codes(
																			objProcedures.getAdjust_codes() + ","
																					+ subProcedures[1] + "-"
																					+ subProcedures[qa]);
																}

																// objProcedures
																// .setAdjust_codes(objProcedures.getAdjust_codes()
																// + "," + subProcedures[1] + "-"
																// + subProcedures[qa]);
															}

															addAdjustCodeToList(subProcedures[1], subProcedures[qa],
																	subProcedures[qa + 1]);
														}
													} else if (subProcedures[1].equalsIgnoreCase("PI")) // Payor
																										// Initiated
																										// Reductions
													{
														if (objProcedures.getOther_adjusts() == null) {
															objProcedures.setOther_adjusts(subProcedures[3]);
														} else {
															double other_adjust = Double
																	.parseDouble(objProcedures.getOther_adjusts())
																	+ Double.parseDouble(subProcedures[3]);
															objProcedures
																	.setOther_adjusts(Double.toString(other_adjust));
														}
														if (objProcedures.getAdjust_codes() == null) {
															objProcedures.setAdjust_codes(
																	subProcedures[1] + "-" + subProcedures[2]);
														} else {
															if (!objProcedures.getAdjust_codes().contains(
																	(subProcedures[1] + "-" + subProcedures[2]))) {
																objProcedures
																		.setAdjust_codes(objProcedures.getAdjust_codes()
																				+ "," + subProcedures[1] + "-"
																				+ subProcedures[2]);
															}

															// objProcedures
															// .setAdjust_codes(objProcedures.getAdjust_codes()
															// + "," + subProcedures[1] + "-"
															// + subProcedures[2]);
														}

														addAdjustCodeToList(subProcedures[1], subProcedures[2],
																subProcedures[3]);
													}

												}
												index++;
												subProcedures = ERASegments[index].split("\\*");
											} // CAS while
										}
									}

									ERASub = ERASegments[index].split("\\*");
									segmentTag = ERASub[0];
									while (segmentTag.equalsIgnoreCase("REF")) // SERVICE IDENTIFICATION
									{
										if (ERASub.length > 1) {
											objProcedures.setProvider_control_number(ERASub[1]);
											switch (ERASub[1].toUpperCase()) {
											case "6R": // Claim Procedure ID.
												if (ERASub.length > 2) {
													try {
														// check if numberic...?
														Long cpid = Long.parseLong(ERASub[2].toString().trim());
														objProcedures.setClaim_procedures_id(cpid.toString());
													} catch (Exception ex) {
														objProcedures.setClaim_procedures_id("0");
													}
												}
												break;
											}
										}
										index++;
										ERASub = ERASegments[index].split("\\*");
										segmentTag = ERASub[0];
									}

									while (segmentTag.equalsIgnoreCase("AMT")) {
										if (ERASub[1].equalsIgnoreCase("B6") || ERASub[1].equalsIgnoreCase("AU")) {
											objProcedures.setAllowed_amount(ERASub[2]);
										}
										if (ERASub[1].equalsIgnoreCase("KS")) {
											objProcedures.setLate_filing_reduction(ERASub[2]);
										}
										if (ERASub[1].equalsIgnoreCase("DY")) {
											objProcedures.setPer_day_limit(ERASub[2]);
										}
										if (ERASub[1].equalsIgnoreCase("NE")) {
											objProcedures.setNet_billed(ERASub[2]);
										}
										if (ERASub[1].equalsIgnoreCase("T")) {
											objProcedures.setTax(ERASub[2]);
										}

										index++;
										ERASub = ERASegments[index].split("\\*");
										segmentTag = ERASub[0];
									}

									// RENDERING PROVIDER INFORMATION
									if (segmentTag.equalsIgnoreCase("REF")) {
										objProcedures.setRendering_provider_identifier_qualifier(ERASub[1]);
										objProcedures.setRendering_provider_identifier(ERASub[2]);

										index++;
										ERASub = ERASegments[index].split("\\*");
										segmentTag = ERASub[0];
									}

									// SERVICE SUPPLEMENTAL QUANTITY
									while (segmentTag.equalsIgnoreCase("QTY")) // skip repeat 6
									{
										index++;
										ERASub = ERASegments[index].split("\\*");
										segmentTag = ERASub[0];
									}
									// HEALTH CARE REMARK CODES
									while (segmentTag.equalsIgnoreCase("LQ")) // repeat 6
									{
										subProcedures = ERASegments[index].split("\\*");

										if (subProcedures.length > 0 && subProcedures[1].equalsIgnoreCase("HE")) {

											if (objProcedures.getRemark_codes() != null) {

												if (!objProcedures.getRemark_codes().contains(subProcedures[2])) {
													objProcedures.setRemark_codes(
															objProcedures.getRemark_codes() + ", " + subProcedures[2]);
												}
												
											} else {
												objProcedures.setRemark_codes(subProcedures[2]);
											}
										}

										index++;
										ERASub = ERASegments[index].split("\\*");
										segmentTag = ERASub[0];
									}

									// Provider Level Adjustment
									// while (segmentTag.equalsIgnoreCase("PLB")) // skip repeat >1
									// {
									// index++;
									// ERASub = ERASegments[index].split("\\*");
									// segmentTag = ERASub[0];
									// }

									objProcedures.setPractice_id(practiceId);

									// save ERA_claim_servie
									Long era_claim_service_id = saveERAClaimService(objProcedures);
									if (era_claim_service_id == null || era_claim_service_id == 0) {
										FileProcessDetails += "\nError occured while saving  ERA Claim Service for ERA Claim ID:"
												+ eraclaim_id;
										throw new Exception(
												"Error occured while saving  ERA Claim Service for ERA Claim ID:"
														+ eraclaim_id);
									} else // save adjustments
									{
										try {
											objProcedures.setEra_claim_service_id(era_claim_service_id);
											SaveCliamServicesAdjustments(listAdjustments, objProcedures,
													objClaims.getClaim_id().trim(), logedInUser);
										} catch (Exception exp) {
											FileProcessDetails += "\nError occured while saving  Adjustment Details for ERA Claim ID:"
													+ eraclaim_id;
											throw new Exception(
													"Error occured while saving  Adjustment Details for ERA Claim ID::"
															+ eraclaim_id);
										}

									}

								} // SVC While
							}

							if (segmentTag.equalsIgnoreCase("LX")) {
								index++;
								ERASub = ERASegments[index].split("\\*");
								segmentTag = ERASub[0];

							}
							if (segmentTag.equalsIgnoreCase("TS3")) {
								if (ERASub[1].equalsIgnoreCase("TS3")) {
									objERA.setProvider_summary(ERASegments[index]);
								}

								index++;
								ERASub = ERASegments[index].split("\\*");
								segmentTag = ERASub[0];
							}

							if (segmentTag.equalsIgnoreCase("TS2")) {
								index++;
								ERASub = ERASegments[index].split("\\*");
								segmentTag = ERASub[0];
							}

							// Provider Adjustments Segment....
							while (segmentTag.equalsIgnoreCase("PLB")) {
								String[] plb = ERASegments[index].split("\\*");
								int i = 3;
								while (i < plb.length) {

									ORMEraProviderAdjustmentSave objPLB = new ORMEraProviderAdjustmentSave();
									objPLB.setEra_id(era_id);
									objPLB.setPractice_id(practiceId);
									objPLB.setDeleted(false);
									objPLB.setProvider_identifier(plb[1]);
									objPLB.setFiscal_year_end_date(plb[2].substring(4, 6) + "/" + plb[2].substring(6, 8)
											+ "/" + plb[2].substring(0, 4));
									objPLB.setReason_code(plb[i].split(":")[0]);
									if (plb[i].split(":").length > 1) {
										objPLB.setAdjustment_identifier(plb[i].split(":")[1]);
									}
									i++;
									if (plb.length > i) {
										objPLB.setAmount(plb[i]);
									}
									i++;

									saveERAProviderAdjustment(objPLB);

								}

								index++;
								ERASub = ERASegments[index].split("\\*");
								segmentTag = ERASub[0];
							}
							// TRANSACTION SET TRAILER
							if (segmentTag.equalsIgnoreCase("SE")) {
								break;
							}

						} // CLP While
					}
					isProcess = "true";
					// DeleteERAFile = true;
				}
			}

		} catch (

		Exception e) {

			TotalErrors++;
			isProcess = "false";

			error_message = "Error Occured: " + e.getMessage();

			if (era_id > 0) {
				String query = "update era set error_detail='" + error_message + "',is_error=1 where era_id='" + era_id
						+ "'";

				this.db.ExecuteUpdateQuery(query);

			} else {
				objERA.setError_detail(error_message);
				objERA.setIs_error(true);

				era_id = saveERA(objERA);
				// if (era_id > 0) {
				// DeleteERAFile = true;
				// }
			}
			FileProcessDetails += "\nError Occurrerd : \n" + e.getStackTrace().toString();
		}

		finally // will always be executed event if it retruns from try catch or exception //
				// occurs.
		{
			if (era_id > 0 && fname.contains("TEXT_STRING")) {
				GenerateERAFile(era_id, MainString);
			}
		}

		return isProcess;

	}

	private Boolean GenerateERAFile(Long era_id, String ERAMainString) {

		Boolean is_file_generated = false;

		try {
			System.out.println("Saving ERA File...");

			String full_file_path = FileUtil.GetYearMonthDayWisePath(this.eraSavePath, practiceId.toString(), "ERA")
					+ "\\" + era_id + ".RMT";

			String[] splitedPath = full_file_path.split("\\\\");

			String file_path = splitedPath[splitedPath.length - 4] + "\\" + splitedPath[splitedPath.length - 3] + "\\"
					+ splitedPath[splitedPath.length - 2] + "\\" + era_id + ".RMT";

			FileUtil.UploadStringDataToFile(ERAMainString, full_file_path);

			String strQuery = "update era set file_path='" + file_path + "' where era_id='" + era_id + "'";

			if (this.db.ExecuteUpdateQuery(strQuery) > 0) {
				is_file_generated = true;
			} else {
				is_file_generated = false;
			}

		} catch (IOException ex) {

			is_file_generated = false;

			String error_message = "Error Occured: " + ex.toString();

			if (era_id > 0) {
				String strQuery = "update era set error_detail='" + error_message + "',is_error=1 where era_id='"
						+ era_id + "'";
				this.db.ExecuteUpdateQuery(strQuery);
			}
		}
		return is_file_generated;
	}

	private List<ORMEraSave> getUnProcessedERAs(String EraIDs) {
		try {

			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("era_ids", EraIDs, String.class, ParameterMode.IN));

			return this.db.getStoreProcedureData("spGetUnProcessedERA", ORMEraSave.class, lstParam);

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

	}

	private Long saveERA(ORMEraSave objera) {
		Long ERAID = (long) 0;
		try {

			System.out.println("EAR Object:\n" + objera);
			if (GeneralOperations.isNullorEmpty(objera.getEra_id())) {
				ORMEraSave objResult = (ORMEraSave) this.db.AddEntityWithIdentity(objera);

				if (objResult != null) {
					ERAID = objResult.getEra_id();
				}
			} else {
				if (this.db.SaveEntity(objera, Operation.EDIT) > 0) {
					ERAID = objera.getEra_id();
				}
			}
		} catch (Exception e) {
			System.out.println("\nError Occurrerd : \n");
			e.printStackTrace();
		}
		return ERAID;

	}

	private Long saveERAClaim(ORMEraClaimSave objClaim) {
		Long ERAClaimID = (long) 0;
		try {
			if (GeneralOperations.isNullorEmpty(objClaim.getEra_claim_id())) {
				ORMEraClaimSave objResult = (ORMEraClaimSave) this.db.AddEntityWithIdentity(objClaim);

				if (objResult != null) {
					ERAClaimID = objResult.getEra_claim_id();
				}
			} else {
				if (this.db.SaveEntity(objClaim, Operation.EDIT) > 0) {
					ERAClaimID = objClaim.getEra_claim_id();
				}
			}
		} catch (Exception e) {
			System.out.println("\nError Occurrerd : \n");
			e.printStackTrace();
		}
		return ERAClaimID;

	}

	private Long saveERAClaimService(ORMEraClaimServiceSave objClaimServices) {
		Long ERAClaimServiceID = (long) 0;
		try {

			if (GeneralOperations.isNullorEmpty(objClaimServices.getEra_claim_service_id())) {

				ORMEraClaimServiceSave objResult = (ORMEraClaimServiceSave) this.db
						.AddEntityWithIdentity(objClaimServices);

				if (objResult != null) {
					ERAClaimServiceID = objResult.getEra_claim_service_id();
				}
			} else {
				if (this.db.SaveEntity(objClaimServices, Operation.EDIT) > 0) {
					ERAClaimServiceID = objClaimServices.getEra_claim_service_id();
				}
			}
		} catch (Exception e) {
			System.out.println("\nError Occurrerd : \n");
			e.printStackTrace();
		}
		return ERAClaimServiceID;
	}

	private void SaveCliamServicesAdjustments(List<ORMEraAdjustmentSave> objlistAdjustment,
			ORMEraClaimServiceSave objClaimServices, String ClaimID, String CreatedBy) {

		for (ORMEraAdjustmentSave orm : objlistAdjustment) {
			orm.setCreated_user(CreatedBy);
			orm.setClaim_procedures_id(objClaimServices.getClaim_procedures_id());
			orm.setPractice_id(objClaimServices.getPractice_id());
			orm.setEra_claim_service_id(objClaimServices.getEra_claim_service_id());
			orm.setEra_id(objClaimServices.getEra_id());
			orm.setEra_claim_id(objClaimServices.getEra_claim_id());
			orm.setClaim_id(ClaimID);

			this.db.AddEntityWithIdentity(orm);
		}

		listAdjustments.clear();

	}

	private Long saveERAProviderAdjustment(ORMEraProviderAdjustmentSave objORM) {
		Long restult = (long) 0;
		try {
			ORMEraProviderAdjustmentSave objResult = (ORMEraProviderAdjustmentSave) this.db
					.AddEntityWithIdentity(objORM);

			if (objResult != null) {
				restult = objResult.getProvider_adjustment_id();
			}

		} catch (Exception e) {
			System.out.println("\nError Occurrerd : \n");
			e.printStackTrace();
		}
		return restult;

	}

	public Boolean IsERAExist(Long practice_id, String check_number, String check_date, String check_amount) {

		String strQuery = " select COUNT(*) as scalar_value from era " + " where practice_id='" + practice_id.toString()
				+ "' and check_number='" + check_number + "' and convert(varchar,check_date,101)='" + check_date
				+ "' and isnull(check_amount,0)=" + check_amount + " and isnull(deleted,0)<>1 ";

		if (Integer.parseInt(this.db.getQuerySingleResult(strQuery)) > 0) {

			return true;
		} else {
			return false;
		}

	}

	private String getPatientID(String CalimID) {

		String id = "";
		try {

			String strQuery = "select top 1 patient_id as scalar_value from claim where claim_id=replace('" + CalimID
					+ "','-','')";

			id = this.db.getQuerySingleResult(strQuery);

		} catch (Exception e) {
			System.out.println("\nError Occurrerd getting Paitent ID for Claim ID : " + CalimID);
		}
		return id;
	}

	private void addAdjustCodeToList(String AdjustCodeGroup, String AdjustCode, String AdjustAmount) {

		if (listAdjustments == null) {
			listAdjustments = new ArrayList<>();
		}

		objAdjustments = new ORMEraAdjustmentSave();
		objAdjustments.setAdjustment_group_code(AdjustCodeGroup);
		objAdjustments.setAdjustment_reason_code(AdjustCode);
		objAdjustments.setAdjustment_amount(AdjustAmount);
		objAdjustments.setDate_created(serverDateTime);
		objAdjustments.setDeleted(false);

		listAdjustments.add(objAdjustments);
		// }
	}

	public ERAPaymentPostingResponse postERAPayment(String postingOption, Integer postDuplicate, Integer postInterest,
			Long eraId, Long eraClaimId, String eraClaimServiceIds, String checkDate, String checkNo) {
		// TODO Auto-generated method stub

		serverDateTime = DateTimeUtil.getCurrentDateTime();

		int paymentsPosted = 0;
		int denialsPosted = 0;

		String CPTsFullyPaidByPrimary = "";

		if (practiceId == null || practiceId == 0 || eraId == null || eraId == 0
				|| GeneralOperations.isNullorEmpty(postingOption) || GeneralOperations.isNullorEmpty(checkDate)
				|| GeneralOperations.isNullorEmpty(checkNo) || GeneralOperations.isNullorEmpty(logedInUser)
				|| GeneralOperations.isNullorEmpty(clientDateTime) || GeneralOperations.isNullorEmpty(clientIp)) {

			return new ERAPaymentPostingResponse("ERROR", "One of the required parameter is missing.", "ALERT", 0, 0);

		} else {

			try {

				Long claimId;
				Long patientId;
				Long payerId;
				Long insuranceId;
				String insuranceType;
				String policyNo;

				String primaryStatus;
				String secondaryStatus;
				String otherStatus;

				String claimStatusCode;
				String claimControlNo;

				String ehrProcCode;
				String chargedProcCode;
				String paidProcCode;

				ORMClaimPaymentSave objPayment;

				List<ORMIdCode> lstReasonCodes = claimDAOImpl.getClaimAdjustmentReasonCodesList();
				ORMEraClaimDetailGet objEraClimDetails;
				// List<ORMEraClaimServicesToPostGet> lstEraClimServicesToPost;
				Long temp_payment_id = (long) -1;
				int s_no = 1;

				SearchCriteria eraClaimSearchCriteria = new SearchCriteria();

				eraClaimSearchCriteria.setPractice_id(practiceId);

				List<SearchCriteriaParamList> param_list = new ArrayList<SearchCriteriaParamList>();
				param_list.add(new SearchCriteriaParamList("status", "pending", ""));
				param_list.add(new SearchCriteriaParamList("era_id", eraId.toString(), ""));
				if (eraClaimId != null && eraClaimId > 0) {
					param_list.add(new SearchCriteriaParamList("era_claim_id", eraClaimId.toString(), ""));
				}
				eraClaimSearchCriteria.setParam_list(param_list);

				List<ORMEraClaimListGet> lstEraClaims = this.billingDAOImpl.getEraClaimList(eraClaimSearchCriteria);

				// int sNo = 1;
				if (lstEraClaims != null && lstEraClaims.size() > 0) {

					for (int i = 0; i < lstEraClaims.size(); i++) {

						System.out.println("********** Claim S.No: " + s_no);
						// sNo++;

						// <editor-fold desc="Claim Detail">

						ORMEraClaimListGet ormEraClaim = lstEraClaims.get(i);

						eraClaimId = ormEraClaim.getEra_claim_id();

						objEraClimDetails = this.billingDAOImpl.getEraClaimDetail(practiceId, eraClaimId);

						if (objEraClimDetails != null) {

							claimId = objEraClimDetails.getEhr_claim_id();
							patientId = objEraClimDetails.getEhr_patient_id();
							insuranceId = objEraClimDetails.getEhr_insurance_id();
							payerId = objEraClimDetails.getEhr_payer_id();
							insuranceType = objEraClimDetails.getEhr_insurace_type();
							policyNo = objEraClimDetails.getPolicy_number();
							primaryStatus = objEraClimDetails.getEhr_pri_status();
							secondaryStatus = objEraClimDetails.getEhr_sec_status();
							otherStatus = objEraClimDetails.getEhr_oth_status();
							claimStatusCode = objEraClimDetails.getClaim_status_code();
							claimControlNo = objEraClimDetails.getPayer_claim_control_number();

							if (claimId == null || claimId == 0 || patientId == null || patientId == 0) {
								if (postingOption.equalsIgnoreCase("cpt_wise")) {
									return new ERAPaymentPostingResponse("ERROR", "Claim Not Found.", "ALERT", 0, 0);
								} else {
									continue;
								}
							}

							if (insuranceId == null || insuranceId == 0 || GeneralOperations.isNullorEmpty(payerId)
									|| GeneralOperations.isNullorEmpty(policyNo)) {

								if (postingOption.equalsIgnoreCase("cpt_wise")) {
									return new ERAPaymentPostingResponse("ERROR",
											"Claim Insurance is missing and/or does not match with ERA.", "ALERT", 0,
											0);
								} else {
									continue;
								}

							}

							if (insuranceType.equalsIgnoreCase("PRIMARY") && primaryStatus.equalsIgnoreCase("P")) {

								if (postingOption.equalsIgnoreCase("cpt_wise")) {
									return new ERAPaymentPostingResponse("ERROR",
											"Selected Claim is already paid. Please check or enter it manually.",
											"ALERT", 0, 0);
								} else {
									continue;
								}

							}

							if (insuranceType.equalsIgnoreCase("SECONDARY") && secondaryStatus.equalsIgnoreCase("P")) {
								if (postingOption.equalsIgnoreCase("cpt_wise")) {
									return new ERAPaymentPostingResponse("ERROR",
											"Selected Claim is already paid. Please check or enter it manually.",
											"ALERT", 0, 0);
								} else {
									continue;
								}
							}
							if (insuranceType.equalsIgnoreCase("OTHER") && otherStatus.equalsIgnoreCase("P")) {
								if (postingOption.equalsIgnoreCase("cpt_wise")) {
									return new ERAPaymentPostingResponse("ERROR",
											"Selected Claim is already paid. Please check or enter it manually.",
											"ALERT", 0, 0);
								} else {
									continue;
								}
							}

							if (GeneralOperations.isNotNullorEmpty(objEraClimDetails.getClaim_interest())
									&& Double.parseDouble(objEraClimDetails.getClaim_interest()) != 0) {
								if (postingOption.equalsIgnoreCase("cpt_wise")) {
									if (postInterest == -1) {
										return new ERAPaymentPostingResponse("INTEREST_CONFIRMATION_REQUIRED",
												"Payment has an interest amount. Do you want to continue?",
												"CONFIRMATION", 0, 0);
									} else if (postInterest == 0) {
										continue;
									}
								} else {
									continue;
								}

							}

							List<EraClaimServiceToMark> lstClaimServicesPosted = new ArrayList<EraClaimServiceToMark>();
							List<EraClaimServiceToMark> lstClaimServicesDenied = new ArrayList<EraClaimServiceToMark>();

							// String idsToMarkPosted = "";
							// String idsTMarkDenied = "";
							String strDenialMsg = "";
							String claimNotes;
							Long claimProceduresId;
							float cptPrimaryBalance;
							int snoDenail = 1;

							ArrayList<ORMClaimPaymentAdjustmentSave> lstAdjustmentSave = new ArrayList<ORMClaimPaymentAdjustmentSave>();
							ArrayList<ORMClaimPaymentSave> lstPaymentSave = new ArrayList<ORMClaimPaymentSave>();
							ArrayList<ORMAdjustmentReasonCodes> lstReasonCodeSave = new ArrayList<ORMAdjustmentReasonCodes>();

							SearchCriteria serviceToPostCriteria = new SearchCriteria();
							param_list = new ArrayList<SearchCriteriaParamList>();
							param_list.add(new SearchCriteriaParamList("era_claim_id", eraClaimId.toString(), ""));
							param_list.add(new SearchCriteriaParamList("era_claim_service_ids",
									eraClaimServiceIds.toString(), ""));
							param_list.add(new SearchCriteriaParamList("claim_id", claimId.toString(), ""));
							param_list.add(new SearchCriteriaParamList("insurance_type", insuranceType.toString(), ""));
							serviceToPostCriteria.setParam_list(param_list);
							List<ORMEraClaimServicesToPostGet> lstEraClimServicesToPost = getERAClaimServicesToPost(
									serviceToPostCriteria);

							SearchCriteria adjToPostCriteria = new SearchCriteria();
							//adjToPostCriteria.setPractice_id(practiceId);
							param_list = new ArrayList<SearchCriteriaParamList>();
							param_list.add(new SearchCriteriaParamList("era_claim_id", eraClaimId.toString(), ""));
							param_list.add(new SearchCriteriaParamList("era_claim_service_ids",
									eraClaimServiceIds.toString(), ""));
							adjToPostCriteria.setParam_list(param_list);
							List<ORMEraAdjustmentsToPostGet> lstAdjustmentToPost = getERAClaimAdjustmentsToPost(
									adjToPostCriteria);

							ORMDenialMessagesSave objDenialSave = null;

							// <editor-fold desc="Full Claim Denied">
							if (claimStatusCode.equalsIgnoreCase("4")) {

								// Whole Claim has been denied.
								strDenialMsg = "Denail Information:\nICN No:" + claimControlNo
										+ "\nWhole claim has been denied by insurance.\nPatient Name:"
										+ objEraClimDetails.getPatient_lname() + ", "
										+ objEraClimDetails.getPatient_fname() + "\nDOS:" + objEraClimDetails.getDos();
								String adjustCodes = "";
								String remarks_codes = "";

								// adjust codes
								if (lstAdjustmentToPost != null && lstAdjustmentToPost.size() > 0) {

									for (ORMEraAdjustmentsToPostGet ormAdjustment : lstAdjustmentToPost) {

										if (adjustCodes.isEmpty()) {
											adjustCodes = ormAdjustment.getAdjustment_group_code() + "-"
													+ ormAdjustment.getAdjustment_reason_code();
										} else {
											adjustCodes += ", " + ormAdjustment.getAdjustment_group_code() + "-"
													+ ormAdjustment.getAdjustment_reason_code();
										}
									}
								}

								// remarks codes
								boolean isHavingPayment = false;
								if (lstEraClimServicesToPost != null && lstEraClimServicesToPost.size() > 0) {

									for (ORMEraClaimServicesToPostGet ormClaimService : lstEraClimServicesToPost) {

										// check if any cpt payment is received.
										// if paymen tof any cpt is received then don't post denial/payment.
										if (GeneralOperations.isNotNullorEmpty(ormClaimService.getPaid_amount())
												&& Double.parseDouble(ormClaimService.getPaid_amount()) != 0) {
											isHavingPayment = true;
											break;
										}
										if (GeneralOperations.isNotNullorEmpty(ormClaimService.getRemark_codes())) {
											if (remarks_codes.isEmpty()) {
												remarks_codes = ormClaimService.getRemark_codes();
											} else {
												remarks_codes += ", " + ormClaimService.getRemark_codes();
											}
										}
									}
								}
								if (isHavingPayment) {
									if (postingOption.equalsIgnoreCase("cpt_wise")) {
										return new ERAPaymentPostingResponse("ERROR",
												"Claim Status is Denied.<br>Please confirm and post manually.", "ALERT",
												0, 0);
									} else {
										continue;
									}
								}

								if (!remarks_codes.isEmpty()) {
									strDenialMsg += "\nRemark Codes Details:\n" + remarks_codes;
								}

								if (!adjustCodes.isEmpty()) {
									strDenialMsg += "\nAdjust Codes Details:\n" + adjustCodes;
								}

								objDenialSave = new ORMDenialMessagesSave();
								objDenialSave.setClaim_id(claimId);
								objDenialSave.setPractice_id(practiceId);
								objDenialSave.setPatient_id(patientId);
								objDenialSave.setEob_era_id(eraId);
								objDenialSave.setEob_era_id_type("ERA");
								objDenialSave.setEra_claim_id(eraClaimId);
								objDenialSave.setIs_era_auto_denial(true);
								objDenialSave.setStatus("ACTIVE");
								objDenialSave.setInsurance_id(insuranceId);
								objDenialSave.setPolicy_number(policyNo);
								objDenialSave.setCreated_user(logedInUser + " [AUTO POSTING*]");
								objDenialSave.setModified_user(logedInUser + " [AUTO POSTING*]");
								objDenialSave.setDate_created(serverDateTime);
								objDenialSave.setDate_modified(serverDateTime);
								objDenialSave.setClient_date_created(clientDateTime);
								objDenialSave.setClient_date_modified(clientDateTime);
								objDenialSave.setSystem_ip(clientIp);
								objDenialSave.setMessage(strDenialMsg);
								objDenialSave.setDenial_id(db.IDGenerator("denial_messages", practiceId));

								PostDenial(objDenialSave, null, true);
								denialsPosted++;
								continue;
								// break;
							}
							// </editor-fold>

							claimNotes = getClaimStatusName(claimStatusCode);

							if (GeneralOperations.isNotNullorEmpty(objEraClimDetails.getCrossover_carrier_name())) {
								claimNotes += "\n" + objEraClimDetails.getCrossover_carrier_name();
							}

							// only those entries which are to be posted.
							// duplicate entries are excluded.
							if (lstEraClimServicesToPost != null && lstEraClimServicesToPost.size() > 0) {

								for (ORMEraClaimServicesToPostGet ormClaimService : lstEraClimServicesToPost) {

									ehrProcCode = ormClaimService.getEhr_proc_code();
									chargedProcCode = ormClaimService.getCharged_procedure();
									paidProcCode = ormClaimService.getPaid_procedure();
									claimProceduresId = ormClaimService.getClaim_procedures_id();
									cptPrimaryBalance = Float.parseFloat(ormClaimService.getCpt_primary_balance());

									Long eraClaimServiceId = ormClaimService.getEra_claim_service_id();

									if (claimProceduresId == null || claimProceduresId == 0) {

										if (postingOption.equalsIgnoreCase("cpt_wise")) {
											return new ERAPaymentPostingResponse("ERROR",
													"CPT Charged, CPT Paid and/or EHR CPT Code(s) does not match.<br>Please verify.",
													"ALERT", 0, 0);
										} else {
											continue;
										}
									}
									// charged,paid, ehr procedures are not identical... do not post automatically
									if (!ehrProcCode.equalsIgnoreCase(chargedProcCode)
											|| !ehrProcCode.equalsIgnoreCase(paidProcCode)) {

										if (postingOption.equalsIgnoreCase("cpt_wise")) {
											return new ERAPaymentPostingResponse("ERROR",
													"CPT Charged, CPT Paid and/or EHR CPT Code(s) does not match.<br>Please verify.",
													"ALERT", 0, 0);
										} else {
											continue;
										}
									}

									float allowed_amount = Float.parseFloat(ormClaimService.getAllowed_amount());
									float paid_amount = Float.parseFloat(ormClaimService.getPaid_amount());
									float copay_amount = Float.parseFloat(ormClaimService.getCopay_amount());
									float deducat_amount = Float.parseFloat(ormClaimService.getDeduct_amount());
									float coins_amount = Float.parseFloat(ormClaimService.getCoins_amount());
									float risk_amount = Float.parseFloat(ormClaimService.getRisk_amount());
									int units_paid = GeneralOperations.isNullorEmpty(ormClaimService.getPaid_units())
											? 1
											: Integer.parseInt(ormClaimService.getPaid_units());

									if (paid_amount != 0 || copay_amount != 0 || deducat_amount != 0
											|| coins_amount != 0 || risk_amount != 0) {

										// if payment is already posted. do not post duplicte.
										if (ormClaimService.getPayment_already_exist() == true) {

											if (postingOption.equalsIgnoreCase("cpt_wise")) {
												if (postDuplicate == -1) {
													return new ERAPaymentPostingResponse(
															"DUPLICATE_POSTING_CONFIRMATION_REQUIRED",
															"Duplicate Payment(s) will be posted for CPTs. Do You Want to Continue?",
															"CONFIRMATION", 0, 0);
												} else if (postDuplicate == 0) {
													continue;
												}
											} else {
												continue;
											}
										}

										// <editor-fold desc="Adjustment in case of PRIMARY INSURANCE">
										float adjustAmount = 0;
										if (insuranceType.equalsIgnoreCase("PRIMARY")) {

											if (lstAdjustmentToPost != null && lstAdjustmentToPost.size() > 0) {

												for (ORMEraAdjustmentsToPostGet ormAdjustment : lstAdjustmentToPost) {

													if (ormAdjustment.getEra_claim_service_id()
															.equals(eraClaimServiceId)) {

														if (ormAdjustment.getPost_adjustment() == true) {

															adjustAmount = GeneralOperations.addFloatNumbers(
																	adjustAmount, Float.parseFloat(ormAdjustment
																			.getAdjustment_amount().toString()));

															// adjustAmount += Float.parseFloat(
															// ormAdjustment.getAdjustment_amount().toString());

															// float adjAmt = Float.parseFloat(
															// ormAdjustment.getAdjustment_amount().toString());

															// adjustAmount=Float.sum(adjustAmount, y1);//
															// adjustAmount+adjAmt;

															ORMClaimPaymentAdjustmentSave objORMAdj = new ORMClaimPaymentAdjustmentSave();
															objORMAdj.setAdjust_code(ormAdjustment
																	.getAdjustment_group_code() + "-"
																	+ ormAdjustment.getAdjustment_reason_code());
															objORMAdj.setAdjust_amount(
																	ormAdjustment.getAdjustment_amount());
															objORMAdj.setClaim_id(claimId);
															objORMAdj.setClaim_procedures_id(claimProceduresId);
															objORMAdj.setPractice_id(practiceId);
															objORMAdj.setCreated_user(logedInUser + " [AUTO POSTING*]");
															objORMAdj
																	.setModified_user(logedInUser + " [AUTO POSTING*]");
															objORMAdj.setDate_created(serverDateTime);
															objORMAdj.setDate_modified(serverDateTime);
															objORMAdj.setClient_date_created(clientDateTime);
															objORMAdj.setClient_date_modified(clientDateTime);
															objORMAdj.setSystem_ip(clientIp);
															objORMAdj.setClaim_payments_id(temp_payment_id);// temperariliy
															// assign
															// payment ids.
															// will be
															// matched when
															// payment
															// saving
															lstAdjustmentSave.add(objORMAdj);

															boolean description_found = false;
															if (lstReasonCodes != null && lstReasonCodes.size() > 0) {
																for (ORMIdCode ormDescription : lstReasonCodes) {
																	if (ormDescription.getCode()
																			.equalsIgnoreCase(ormAdjustment
																					.getAdjustment_reason_code())) {
																		description_found = true;
																		break;
																	}
																}
															}

															if (description_found == false) {

																ORMAdjustmentReasonCodes objORMReasonCode = new ORMAdjustmentReasonCodes();
																objORMReasonCode.setCode(
																		ormAdjustment.getAdjustment_reason_code());
																objORMReasonCode.setClient_date_created(clientDateTime);
																objORMReasonCode
																		.setClient_date_modified(clientDateTime);
																objORMReasonCode.setDate_created(serverDateTime);
																objORMReasonCode.setDate_modified(serverDateTime);
																objORMReasonCode.setCreated_user(
																		logedInUser + " [AUTO POSTING*]");
																objORMReasonCode.setModified_user(
																		logedInUser + " [AUTO POSTING*]");

																lstReasonCodeSave.add(objORMReasonCode);

																// add reason code to list for next time use if new
																// reaons code is added.
																ORMIdCode ormIdCode = new ORMIdCode();
																ormIdCode.setId((long) -1 * lstReasonCodes.size());
																ormIdCode.setCode(
																		ormAdjustment.getAdjustment_reason_code());
																lstReasonCodes.add(ormIdCode);
																// lstReasonCodes =
																// getAdjustmentReasonCodesDescription();

															}
														}
													}
												}
											}
										}
										// </editor-fold>

										// <editor-fold desc="Posting">
										objPayment = new ORMClaimPaymentSave();

										objPayment.setCreated_user(logedInUser + " [AUTO POSTING*]");
										objPayment.setModified_user(logedInUser + " [AUTO POSTING*]");
										objPayment.setDate_created(serverDateTime);
										objPayment.setDate_modified(serverDateTime);
										objPayment.setClient_date_created(clientDateTime);
										objPayment.setClient_date_modified(clientDateTime);
										objPayment.setSystem_ip(clientIp);

										objPayment.setPatient_id(patientId);
										objPayment.setClaim_id(claimId);
										objPayment.setClaim_procedures_id(claimProceduresId);
										objPayment.setInsurance_id(insuranceId);
										objPayment.setPolicy_number(policyNo);
										objPayment.setPayer_id(payerId);
										objPayment.setAutoposted_era(true);

										if (claimStatusCode.equalsIgnoreCase("22")) {
											objPayment.setEntry_type("Reversal");
										} else {
											objPayment.setEntry_type("Payment");
										}

										objPayment.setEob_era_id(eraId);
										objPayment.setCheck_date(checkDate);
										objPayment.setCheck_number(checkNo);
										objPayment.setEob_era_id_type("ERA");

										objPayment.setCharged_procedure(chargedProcCode);
										objPayment.setPaid_procedure(paidProcCode);
										objPayment.setUnits(units_paid);

										objPayment.setPractice_id(practiceId);
										objPayment.setPayment_source(insuranceType);

										objPayment.setPaid_amount(new BigDecimal(paid_amount));
										objPayment.setDeductable_amount(new BigDecimal(deducat_amount));
										objPayment.setRisk_amount(new BigDecimal(risk_amount));
										objPayment.setCoinsurance_amount(new BigDecimal(coins_amount));
										objPayment.setCopay_amount(new BigDecimal(copay_amount));
										objPayment.setAllowed_amount(new BigDecimal(allowed_amount));
										objPayment.setAllowed_amount(new BigDecimal(allowed_amount));
										objPayment.setAdjusted_amount(new BigDecimal(adjustAmount));
										objPayment.setClaim_payments_id((long) temp_payment_id); // temperariliy
																									// assign
																									// payment
																									// ids. will
																									// be
																									// matched
																									// when
																									// payment
																									// saving
										lstPaymentSave.add(objPayment);

										temp_payment_id--; // set temp payment id.

										// </editor-fold>

										// if (!idsToMarkPosted.isEmpty()) {
										// idsToMarkPosted = ",";
										// }

										lstClaimServicesPosted.add(
												new EraClaimServiceToMark(ormClaimService.getEra_claim_service_id(),
														ehrProcCode, new BigDecimal(paid_amount)));
										// idsToMarkPosted = ormClaimService.getEra_claim_service_id() + ":" +
										// paid_amount
										// + ":" + ehrProcCode;
									} else {

										if (cptPrimaryBalance == 0) {

											if (postingOption.equalsIgnoreCase("cpt_wise")) {

												if (!CPTsFullyPaidByPrimary.isEmpty()) {
													CPTsFullyPaidByPrimary = ",";
												}
												CPTsFullyPaidByPrimary += ehrProcCode;
												continue;
											}
										}

										// <editor-fold desc="POST DENIAL">
										if (lstAdjustmentToPost != null && lstAdjustmentToPost.size() > 0) {

											for (ORMEraAdjustmentsToPostGet ormAdjustment : lstAdjustmentToPost) {

												if (ormAdjustment.getEra_claim_service_id().equals(eraClaimServiceId)) {

													if (!strDenialMsg.isEmpty()) {
														strDenialMsg += "\n";
													}
													strDenialMsg += "(" + snoDenail + ") " + ehrProcCode + "\n";
													strDenialMsg += "Adjustment Codes Details:";
													strDenialMsg += "\n" + ormAdjustment.getAdjustment_group_code()
															+ "-" + ormAdjustment.getAdjustment_reason_code();

													boolean description_found = false;
													if (lstReasonCodes != null && lstReasonCodes.size() > 0) {
														for (ORMIdCode ormDescription : lstReasonCodes) {
															if (ormDescription.getCode().equalsIgnoreCase(
																	ormAdjustment.getAdjustment_reason_code())) {
																description_found = true;
																break;
															}
														}
													}

													if (description_found == false) {

														ORMAdjustmentReasonCodes objORMReasonCode = new ORMAdjustmentReasonCodes();
														objORMReasonCode
																.setCode(ormAdjustment.getAdjustment_reason_code());
														objORMReasonCode.setClient_date_created(clientDateTime);
														objORMReasonCode.setClient_date_modified(clientDateTime);
														objORMReasonCode.setDate_created(serverDateTime);
														objORMReasonCode.setDate_modified(serverDateTime);
														objORMReasonCode
																.setCreated_user(logedInUser + " [AUTO POSTING*]");
														objORMReasonCode
																.setModified_user(logedInUser + " [AUTO POSTING*]");

														lstReasonCodeSave.add(objORMReasonCode);

														// add reason code to list for next time use if new
														// reaons code is added.
														ORMIdCode ormIdCode = new ORMIdCode();
														ormIdCode.setId((long) -1 * lstReasonCodes.size());
														ormIdCode.setCode(ormAdjustment.getAdjustment_reason_code());
														lstReasonCodes.add(ormIdCode);
														// lstReasonCodes =
														// getAdjustmentReasonCodesDescription();

													}

													if (GeneralOperations
															.isNotNullorEmpty(ormClaimService.getRemark_codes())) {
														strDenialMsg += "\nRemark Codes Details: "
																+ ormClaimService.getRemark_codes();
													}

													// if (!idsToMarkPosted.isEmpty()) {
													// idsToMarkPosted = ",";
													// }
													// idsToMarkPosted = ormClaimService.getEra_claim_service_id() + ":"
													// + paid_amount + ":" + ehrProcCode;

													lstClaimServicesDenied.add(new EraClaimServiceToMark(
															ormClaimService.getEra_claim_service_id(), ehrProcCode,
															new BigDecimal(paid_amount)));

													snoDenail++;

												}
											}
										}
										// </editor-fold>
									}
								}

							} else {
								// Return
								// Codes are missing in Claim. Please check
							}

							// <editor-fold desc="Saving Record">

							paymentsPosted += lstPaymentSave.size();

							if (!strDenialMsg.isEmpty()) {
								strDenialMsg = "Denail Information:\nICN No:" + claimControlNo + "\n" + strDenialMsg;
								objDenialSave = new ORMDenialMessagesSave();
								objDenialSave.setClaim_id(claimId);
								objDenialSave.setPractice_id(practiceId);
								objDenialSave.setPatient_id(patientId);
								objDenialSave.setEob_era_id(eraId);
								objDenialSave.setEob_era_id_type("ERA");
								objDenialSave.setEra_claim_id(eraClaimId);
								objDenialSave.setIs_era_auto_denial(true);
								objDenialSave.setStatus("ACTIVE");
								objDenialSave.setInsurance_id(insuranceId);
								objDenialSave.setPolicy_number(policyNo);
								objDenialSave.setCreated_user(logedInUser + " [AUTO POSTING*]");
								objDenialSave.setModified_user(logedInUser + " [AUTO POSTING*]");
								objDenialSave.setDate_created(serverDateTime);
								objDenialSave.setDate_modified(serverDateTime);
								objDenialSave.setClient_date_created(clientDateTime);
								objDenialSave.setClient_date_modified(clientDateTime);
								objDenialSave.setSystem_ip(clientIp);
								objDenialSave.setMessage(strDenialMsg);
								objDenialSave.setDenial_id(this.db.IDGenerator("denial_messages", practiceId));
								denialsPosted++;
							}

							Wrapper_ClaimPaymentSave wrapperClaimPaymentSave = new Wrapper_ClaimPaymentSave();
							wrapperClaimPaymentSave.setLstClaimPaymentSave(lstPaymentSave);
							wrapperClaimPaymentSave.setLstClaimPaymentAdjustmentSave(lstAdjustmentSave);

							List<ORMKeyValue> lstKV = new ArrayList<ORMKeyValue>();
							lstKV.add(new ORMKeyValue("CLAIM_NOTE", claimNotes));
							lstKV.add(new ORMKeyValue("IS_AUTO_CLAIM_NOTE", "true"));

							wrapperClaimPaymentSave.setLstKeyValue(lstKV);

							PostPayment(wrapperClaimPaymentSave, lstReasonCodeSave, objDenialSave, eraClaimId,
									lstClaimServicesPosted, lstClaimServicesDenied);

							// </editor-fold>

						}
						// </editor-fold>
					}
				}

			} catch (Exception e) {
				e.printStackTrace();

				String strErro = "ERA POSTING ERROR: era_id:" + eraId + " era_claim_id:" + eraClaimId + " "
						+ e.getMessage().replace("'", "`");

				db.ExecuteUpdateQuery("insert into errorlog (error,error_date) values ('" + strErro + "',getdate())");

				// return "ERROR~" + posted_payments + "~" + posted_denials + "~" +
				// cpts_fullay_paid_by_primary;

				return new ERAPaymentPostingResponse("ERROR", e.getMessage(), "ALERT", 0, 0);

			}

		}

		return new ERAPaymentPostingResponse("SUCCESS", "ERA has been posted.", "ALERT", paymentsPosted, denialsPosted);
	}

	private String PostPayment(Wrapper_ClaimPaymentSave wrapperClaimPaymentSave,
			List<ORMAdjustmentReasonCodes> lstORMAdjustmentReasonCodes, ORMDenialMessagesSave objORMDenialMessagesSave,
			Long eraClaimId, List<EraClaimServiceToMark> lstEraClaimServiceToMarkPosted,
			List<EraClaimServiceToMark> lstEraClaimServiceToMarkDenied) {

		String Msg = "Posting Summary:\n---------------------------------------------------\n";
		String ErrorMsg = "";
		int s_no = 1;
		try {

			// Saving Claim Payment and Claim Adjustments
			try {
				String cptMarked = "";
				if ((wrapperClaimPaymentSave.getLstClaimPaymentSave() != null
						&& wrapperClaimPaymentSave.getLstClaimPaymentSave().size() > 0)
						|| (wrapperClaimPaymentSave.getLstClaimPaymentAdjustmentSave() != null
								&& wrapperClaimPaymentSave.getLstClaimPaymentAdjustmentSave().size() > 0)) {

					ServiceResponse<ORMKeyValue> resp = this.claimDAOImpl.saveClaimPayment(wrapperClaimPaymentSave);
					if (resp.getStatus().equals(ServiceResponseStatus.SUCCESS)) {

						if (lstEraClaimServiceToMarkPosted != null && lstEraClaimServiceToMarkPosted.size() > 0) {
							cptMarked = MarkEraClaimServicesAutoStatus(lstEraClaimServiceToMarkPosted, "PAID");
						}

						Msg += s_no + ") Payment has been Posted.\n" + cptMarked + "\n";
						s_no++;

					} else {
						Msg += s_no + ") Unable to Post Payment.\n";
						s_no++;
					}
				} else if (lstEraClaimServiceToMarkPosted != null && lstEraClaimServiceToMarkPosted.size() > 0) {

					cptMarked = MarkEraClaimServicesAutoStatus(lstEraClaimServiceToMarkPosted, "PAID");

					Msg += s_no + ") Payment has been Marked as Posted.\n" + cptMarked + "\n";
					s_no++;

				}

			} catch (Exception e) {
				// e.printStackTrace();
				Msg += s_no + ") ERROR : Unable to Post Payment.\n";
				ErrorMsg += " ERROR : Unable to Post Payment." + e.getMessage();
				s_no++;
			}

			// saving denail
			try {
				if (objORMDenialMessagesSave != null) {

					String result = PostDenial(objORMDenialMessagesSave, lstEraClaimServiceToMarkDenied, false);

					if (Double.parseDouble(result.split("~")[0].toString()) > 0) {
						Msg += s_no + ") Denial Posted.\n" + result.split("~")[1].toString() + "\n";
						s_no++;
					} else {
						Msg += s_no + ") Unable to Post Denial";
						// s_no++;
					}
				} else if (lstEraClaimServiceToMarkDenied != null && lstEraClaimServiceToMarkDenied.size() > 0) {

					String result = MarkEraClaimServicesAutoStatus(lstEraClaimServiceToMarkDenied, "DENIED");

					Msg += s_no + ") ERA Entry has been Marked as Denied.\n" + result + "\n";
					s_no++;

				}

			} catch (Exception e) {
				// e.printStackTrace();
				Msg += s_no + ") ERROR : Unable to Post Denial.\n";
				ErrorMsg += " ERROR : Unable to Post Denial." + e.getMessage();
				s_no++;
			}

			// updating ERA Claim Status
			if (((lstEraClaimServiceToMarkPosted != null && lstEraClaimServiceToMarkPosted.size() > 0)
					|| (lstEraClaimServiceToMarkDenied != null && lstEraClaimServiceToMarkDenied.size() > 0))
					&& eraClaimId != null && eraClaimId > 0

			) {
				UpdateEraClaimStatusFromClaimService(eraClaimId);
			}

			// saving new adjust codes.
			try {
				int newCodes = 0;
				if (lstORMAdjustmentReasonCodes != null && lstORMAdjustmentReasonCodes.size() > 0) {

					List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

					Long id = this.db.IDGenerator("adjustment_reason_codes", practiceId,
							lstORMAdjustmentReasonCodes.size());

					for (ORMAdjustmentReasonCodes ormAdjustmentReasonCodes : lstORMAdjustmentReasonCodes) {

						ormAdjustmentReasonCodes.setId(id.toString());
						ormAdjustmentReasonCodes.setDate_created(serverDateTime);
						ormAdjustmentReasonCodes.setDate_modified(serverDateTime);

						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormAdjustmentReasonCodes));

						id++;
					}
					this.db.AddUpdateEntityWrapper(lstEntityWrapper);

				}
				if (newCodes > 0) {
					Msg += s_no + ") " + newCodes + "New Adjust Code(s) Added.\n";
					s_no++;
				}
			} catch (Exception e) {

				// e.printStackTrace();
				Msg += s_no + ") ERROR : Unable to Add New Adjust Code(s).\n";
				ErrorMsg += " ERROR : Unable to Add New Adjust Code(s)." + e.getMessage();
				s_no++;
			}

			Msg = "1~" + Msg;

		} catch (Exception e) {
			e.printStackTrace();
			Msg += "0~" + s_no + ") ERROR : An Error Occured While Posting ERA.\n";
			ErrorMsg += " ERROR : An Error Occured While Posting ERA." + e.getMessage();
		}

		if (ErrorMsg.contains("ERROR :")) {
			ErrorMsg = "ERA POSTING ERROR: era_claim_id:" + eraClaimId + " " + ErrorMsg;
			db.ExecuteUpdateQuery("insert into errorlog (error,error_date) values ('" + ErrorMsg + "',getdate())");
			// hibernate.CustomUpdate("insert into errorlog (error,error_date) values ('" +
			// ErrorMsg + "',getdate())");
		}

		return Msg;

	}

	public List<ORMEraClaimServicesToPostGet> getERAClaimServicesToPost(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spGetERAClaimServiceToPost", ORMEraClaimServicesToPostGet.class, lstParam);
	}

	public List<ORMEraAdjustmentsToPostGet> getERAClaimAdjustmentsToPost(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();
		
		//lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
		//		ParameterMode.IN));
		
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spgetEraAdjustmentsToPost", ORMEraAdjustmentsToPostGet.class, lstParam);
	}

	public String PostDenial(ORMDenialMessagesSave objDenial, List<EraClaimServiceToMark> lstEraClaimServiceToMark,
			Boolean wholeClaimDenied) {

		String result = "";

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objDenial));

		String strQuery = "";
		if (wholeClaimDenied) {

			strQuery = " update era_claim_service set denial_posted=1,posted_by='" + logedInUser
					+ "',date_posted=getdate(),date_modified=GETDATE(),client_date_modified='" + clientDateTime
					+ "',modified_user='" + logedInUser + "',system_ip='" + clientIp + "' " + " where era_claim_id='"
					+ objDenial.getEra_claim_id() + "'";

			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

			strQuery = " update era_claim set posted=1,posted_by='" + logedInUser + "',date_posted='" + clientDateTime
					+ "',date_modified=GETDATE(),client_date_modified='" + clientDateTime + "',modified_user='"
					+ logedInUser + "',system_ip='" + clientIp + "' " + " where era_claim_id='"
					+ objDenial.getEra_claim_id() + "' ";

			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

		}
		if (db.AddUpdateEntityWrapper(lstEntityWrapper) > 0) {
			result += "1~";

			if (!wholeClaimDenied && lstEraClaimServiceToMark != null && lstEraClaimServiceToMark.size() > 0) {
				result += MarkEraClaimServicesAutoStatus(lstEraClaimServiceToMark, "DENIED");
			}
		}

		return result;
	}

	public String MarkEraClaimServicesAutoStatus(List<EraClaimServiceToMark> lstEraClaimServiceToMark, String option) {

		String entries_marked = "";
		try {

			if (lstEraClaimServiceToMark != null && lstEraClaimServiceToMark.size() > 0) {

				List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();
				for (EraClaimServiceToMark eraClaimServiceToMark : lstEraClaimServiceToMark) {

					String strQuery;
					Long id = eraClaimServiceToMark.getEra_claim_service_id();
					String amt = "0.00";
					String cpt = "";

					if (eraClaimServiceToMark.getPaid_amount() != null) {
						amt = eraClaimServiceToMark.getPaid_amount().toString();
					}

					if (GeneralOperations.isNotNullorEmpty(eraClaimServiceToMark.getProc_code())) {
						cpt = eraClaimServiceToMark.getProc_code();
					} else {
						cpt = "(" + eraClaimServiceToMark.getEra_claim_service_id() + ")";
					}

					strQuery = " update era_claim_service set posted_by='" + logedInUser + "',date_posted='"
							+ clientDateTime + "',date_modified=GETDATE(),client_date_modified='" + clientDateTime
							+ "',modified_user='" + logedInUser + "',system_ip='" + clientIp + "' ";

					strQuery += " ,auto_posted=1 ";

					if (option.equals("PAID")) {
						strQuery += ", payment_posted=1,posted_amount='" + amt + "'";
					} else if (option.equals("DENIED")) {
						strQuery += ", denial_posted=1  ";
					}

					strQuery += " where era_claim_service_id='" + id + "'";

					entries_marked += cpt;

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));
				}

				if (lstEntityWrapper != null && lstEntityWrapper.size() > 0) {
					if (db.AddUpdateEntityWrapper(lstEntityWrapper) == 0) {
						entries_marked = "";
					}
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			// Msg += s_no + ") Unable to Mark ERA Claim Service(s) as Posted/Denied.\n";
			// s_no++;
		}

		return entries_marked;

	}

	private String getClaimStatusName(String status_code) {
		String strStatus = "";

		switch (status_code.toUpperCase()) {
		case "1":
			strStatus = "Processed as Primary";
			break;
		case "2":
			strStatus = "Processed as Secondary";
			break;
		case "3":
			strStatus = "Processed as Tertiary";
			break;
		case "4":
			strStatus = "Denied";
			break;
		case "19":
			strStatus = "Processed as Primary, <BR>Forwarded to Additional Payer(s)";
			break;
		case "20":
			strStatus = "Processed as Secondary,<BR>Forwarded to Additional Payer(s)";
			break;
		case "21":
			strStatus = "Processed as Tertiary,<BR>Forwarded to Additional Payer(s)";
			break;
		case "22":
			strStatus = "Reversal of Previous Payment";
			break;
		case "23":
			strStatus = "Not Our Claim, <BR>Forwarded to Additional Payer(s)";
			break;
		case "24":
			strStatus = "Predetermination Pricing Only - No Payment";
			break;
		default:
			strStatus = status_code.toUpperCase();
			break;
		}

		return strStatus;
	}

	public int UpdateEraClaimStatusFromClaimService(Long era_claim_id) {
		String strERAClaimStatusQuery = "  Declare @posted bit; "
				+ "  select @posted=case  when COUNT(era_claim_id) =0 then 1 else 0 end "
				+ "         from era_claim_service " + "         where era_claim_id= '" + era_claim_id + "'"
				+ "	    and ISNULL(deleted,0)<>1 " + "         and ISNULL(payment_posted,0)=0 "
				+ "         and ISNULL(denial_posted,0)=0 ;" + " update era_claim "
				+ " set posted = @posted, posted_by=case when @posted=1 then '" + logedInUser
				+ "' else NULL end, date_posted=case when @posted=1 then '" + clientDateTime + "' else NULL end,"
				+ " date_modified=GETDATE(),client_date_modified='" + clientDateTime + "',system_ip='" + clientIp
				+ "',modified_user='" + logedInUser + "'" + " where era_claim_id=" + era_claim_id.toString();

		return this.db.ExecuteUpdateQuery(strERAClaimStatusQuery);
	}
}
