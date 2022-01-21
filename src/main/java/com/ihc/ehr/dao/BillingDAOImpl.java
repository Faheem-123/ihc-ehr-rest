package com.ihc.ehr.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.persistence.ParameterMode;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ERAOperationResult;
import com.ihc.ehr.model.ERAPaymentPostingResponse;
import com.ihc.ehr.model.HcfaFileGenerationResponse;
import com.ihc.ehr.model.ORMBatchDetail;
import com.ihc.ehr.model.ORMClaimNotesSave;
import com.ihc.ehr.model.ORMClaim_Batch_Errors;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMDenialMessagesSave;
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMEOBSave;
import com.ihc.ehr.model.ORMEdiFtpInfo;
import com.ihc.ehr.model.ORMEobAttachmentGet;
import com.ihc.ehr.model.ORMEobAttachmentsSave;
import com.ihc.ehr.model.ORMEobDetailGet;
import com.ihc.ehr.model.ORMEraClaimDetailGet;
import com.ihc.ehr.model.ORMEraClaimListGet;
import com.ihc.ehr.model.ORMEraClaimServicesGet;
import com.ihc.ehr.model.ORMEraGet;
import com.ihc.ehr.model.ORMEraListGet;
import com.ihc.ehr.model.ORMEraProviderAdjustmentGet;
import com.ihc.ehr.model.ORMFourColumGeneric;
import com.ihc.ehr.model.ORMGetBatchClaimCount;
import com.ihc.ehr.model.ORMGetBillingSummaryClaims;
import com.ihc.ehr.model.ORMGetEOB;
import com.ihc.ehr.model.ORMIdCodeDescriptionType;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMSaveBatch;
import com.ihc.ehr.model.ORMSaveClaimBatchDetail;
import com.ihc.ehr.model.ORMScalarValue;
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORM_277ClaimStatusDetail;
import com.ihc.ehr.model.ORM_277_ResponseGet;
import com.ihc.ehr.model.ORM_277_ResponseSave;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.SubmissionProccessedClaimInfo;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.util.BillingGeneral;
import com.ihc.ehr.util.ClaimBatchGeneration;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;
import com.ihc.ehr.util.ERAOperations;
import com.ihc.ehr.util.EdiParser;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.FileUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.Hcfa;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Repository
public class BillingDAOImpl implements BillingDAO {

	@Autowired
	DBOperations db;
	@Autowired
	ClaimDAOImpl claimDao;
	@Autowired
	private GeneralDAOImpl generalDao;

	@Autowired
	private BillingGeneral billingGeneral;

	@Override
	public List<ORMGetBillingSummaryClaims> getClaimBillingSummary(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String strquery = "";
		String strConditionalJoin = " and isnull(c.is_resubmitted,0)<>1 ";
		strquery += " and c.practice_id=" + searchCriteria.getPractice_id();
		List<SpParameters> lstParam = new ArrayList<>();

		String date_option = "";
		String patient_id = "";
		String provider_id = "";
		String location_id = "";
		String status = "";
		String type = "";
		String insurance_id = "";
		String from_date = "";
		String to_date = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				System.out.println(
						"param:" + pram.getName() + "    Value:" + pram.getValue() + "    Option:" + pram.getOption());

				if (pram.getName().toLowerCase().equals("date_option")) {
					date_option = pram.getValue();
				} else if (pram.getName().toLowerCase().equals("from_date")) {
					from_date = pram.getValue();
				} else if (pram.getName().toLowerCase().equals("to_date")) {
					to_date = pram.getValue();
				} else if (pram.getName().toLowerCase().equals("patient_id")) {
					patient_id = pram.getValue();
				} else if (pram.getName().toLowerCase().equals("provider_id")) {
					provider_id = pram.getValue();
				} else if (pram.getName().toLowerCase().equals("location_id")) {
					location_id = pram.getValue();
				} else if (pram.getName().toLowerCase().equals("status")) {
					status = pram.getValue();
				} else if (pram.getName().toLowerCase().equals("type")) {
					type = pram.getValue();
				} else if (pram.getName().toLowerCase().equals("insurance_id")) {
					insurance_id = pram.getValue();
				}
			}

			if (date_option.toLowerCase().equals("dos")) {
				strquery += " and convert(datetime,convert(varchar,c.dos,101)) between convert(datetime,'" + from_date
						+ "') and convert(datetime,'" + to_date + "') ";
			}
			if (date_option.toLowerCase().equals("date_created")) {
				strquery += " and convert(datetime,convert(varchar,c.date_created,101)) between convert(datetime,'"
						+ from_date + "') and convert(datetime,'" + to_date + "') ";
			}
			if (!patient_id.equals("")) {
				strquery += " and c.patient_id='" + patient_id + "'";
			}
			if (!status.equals("")) {
				if (status.toLowerCase().equals("unprocessed")) {
					strquery += " and ISNULL(submission_status,'') = ''  and ISNULL(c.self_pay,0)<>1 and  isnull(c.draft,0)<>1 ";
				} else if (status.toLowerCase().equals("processed")) {
					strConditionalJoin = " inner join claim_batch_detail cb on cb.claim_id=c.claim_id and ISNULL(cb.deleted,0)<>1 "
							+ " inner join claim_batch b on b.batch_id=cb.batch_id and ISNULL(b.deleted,0)<>1 ";

					strquery += " and isnull(c.deleted,0)<>1  and cb.claim_id is not null  and ISNULL(c.self_pay,0)<>1 and  isnull(c.draft,0)<>1 ";
				} else if (status.toLowerCase().equals("self")) {
					strquery += " and isnull(c.self_pay,0)=1  and  isnull(c.draft,0)<>1  ";
				} else if (status.toLowerCase().equals("draft")) {
					strquery += "  and isnull(c.draft,0)=1 and ISNULL(submission_status,'') = ''  ";
				} else if (status.toLowerCase().equals("follow Up")) {
					strquery += "  and isnull(c.draft,0)=1 and ISNULL(submission_status,'') = ''  ";
				}
			}
			if (!provider_id.equals("")) {
				strquery += "  and c.billing_physician='" + provider_id + "'";
			}
			if (!location_id.equals("")) {
				strquery += "  and c.location_id='" + location_id + "'";
			}
			if (!type.equals("")) {
				if (type.equals("Institutional")) {
					strquery += "  and isnull(c.claim_type,'')='I' ";
				} else {
					strquery += "  and isnull(c.claim_type,'')='P' ";
				}
			}
			if (!insurance_id.equals("")) {
				strConditionalJoin += " inner join claim_insurance cinsPri on cinsPri.claim_id=c.claim_id and ISNULL(cinsPri.deleted,0)<>1 and cinsPri.insurace_type ='PRIMARY' ";

				strquery += "  and cinsPri.insurance_id='" + insurance_id + "' ";
			}

			strquery += "  and isnull(c.icd_10_claim,0)=1 ";

		}
		lstParam.add(new SpParameters("criteria", strquery, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("unprocessed_join", strConditionalJoin, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetBillingClaims", ORMGetBillingSummaryClaims.class, lstParam);

	}

	public String getBatchName(String name_part) // batch name can't be duplicated as now we are uploading to ftp and if
													// batch name are same then batch is over-written.
	{
		String batch_name = "";

		String strQuery = " SELECT top 1 batch_name as scalar_value FROM claim_batch where batch_name like '"
				+ name_part + "%' order by batch_name desc";
		batch_name = db.getQuerySingleResult(strQuery);

		if (GeneralOperations.isNullorEmpty(batch_name)) {
			batch_name = name_part + "01";
		} else {
			String[] str = batch_name.split("_");
			if (str.length >= 4) {
				int no = Integer.parseInt(str[3].toString());
				no = no + 1;

				if (no <= 9) {
					batch_name = name_part + "0" + no;
				} else {
					batch_name = name_part + no;
				}
			} else {
				batch_name = name_part + "01";
			}
		}
		return batch_name;
	}

	@Override
	public ServiceResponse<ORMKeyValue> addUpdateBatch(ORMSaveBatch obj) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		int result = 0;
		String serverDateTime = DateTimeUtil.getCurrentDateTime();
		obj.setDate_modified(serverDateTime);
		if (obj.getBatch_id() != null) {
			result = db.SaveEntity(obj, Operation.EDIT);
		} else {
			obj.setDate_created(serverDateTime);
			obj.setBatch_id(db.IDGenerator("claim_batch", Long.parseLong(obj.getPractice_id())).toString());
			obj.setBatch_name(getBatchName(obj.getBatch_name()));
			result = db.SaveEntity(obj, Operation.ADD);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(obj.getBatch_id().toString());
		}

		return resp;
	}

	@Override
	public List<ORMFourColumGeneric> getUnlockBatch(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetUnlockBatch", ORMFourColumGeneric.class, lstParam);
	}

	@Override
	public List<ORMGetBatchClaimCount> getClaimBatchCount(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String date_From = "";
		String date_To = "";
		String batch_Type = "";
		List<SpParameters> lstParam = new ArrayList<>();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				System.out.println(
						"param:" + pram.getName() + "    Value:" + pram.getValue() + "    Option:" + pram.getOption());

				if (pram.getName().equals("date_From")) {
					date_From = pram.getValue();
				}
				if (pram.getName().equals("date_To")) {
					date_To = pram.getValue();
				}
				if (pram.getName().equals("batch_Type")) {
					batch_Type = pram.getValue();
				}
			}
		}
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("date_from", date_From, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("date_To", date_To, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("batch_Type", batch_Type, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetClaimBatchCount", ORMGetBatchClaimCount.class, lstParam);

	}

	@Override
	public List<ORMScalarValue> getBatchClaimList(String batch_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("batch_id", batch_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetbatchClaimList", ORMScalarValue.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveClaimBatchDetail(List<ORMSaveClaimBatchDetail> lstSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		for (ORMSaveClaimBatchDetail ormSave : lstSave) {
			ormSave.setDate_modified(serverDateTime);
			if (GeneralOperations.isNullorEmpty(ormSave.getDetail_id())) {
				ormSave.setDetail_id(
						db.IDGenerator("claim_batch_detail", Long.parseLong(ormSave.getPractice_id())).toString());
				ormSave.setDate_created(serverDateTime);
				result = db.SaveEntity(ormSave, Operation.ADD);
			} else {
				result = db.SaveEntity(ormSave, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public List<ORMBatchDetail> getBatchDetail(String practice_id, String batch_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("batch_id", batch_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetbatchDetail", ORMBatchDetail.class, lstParam);
	}

	@Override
	public int lockBatch(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String User = "";
		String batch_id = "";
		String batch_name = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				System.out.println(
						"param:" + pram.getName() + "    Value:" + pram.getValue() + "    Option:" + pram.getOption());

				if (pram.getName().toLowerCase().equals("user")) {
					User = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("batch_id")) {
					batch_id = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("batch_name")) {
					batch_name = pram.getValue();
				}
				// if(pram.getName().toLowerCase().equals("pri_ins"))
				// {
				// pri_ins=pram.getValue();
				// }
			}
		}
		String Qry = "update claim_batch set batch_lock=1 ,modified_user='" + User
				+ "',date_modified=getdate(),client_date_modified=getdate() where batch_id='" + batch_id + "'";
		db.ExecuteUpdateQuery(Qry);

		List<ORMTwoColumnGeneric> lst = db.getQueryData("select cb.claim_id as col1,"
				+ " (select top 1  i.name as name from claim_insurance ci join insurance i on i.insurance_id=ci.insurance_id and ci.insurace_type='primary' where isnull(ci.deleted,0)<>1 and claim_id=cb.claim_id ) as col2 "
				+ " from claim_batch_detail cb " + " where cb.batch_id='" + batch_id + "' and isnull(cb.deleted,0)<>1",
				ORMTwoColumnGeneric.class);

		for (ORMTwoColumnGeneric objclaim : lst) {
			ORMClaimNotesSave obj = new ORMClaimNotesSave();
			obj.setClaim_id(Long.parseLong(objclaim.getCol1()));
			obj.setPatient_id(null);
			obj.setNotes("Claim Added in Batch " + batch_name + ". Pri. Insurance: " + objclaim.getCol2() + ".");
			obj.setPractice_id(searchCriteria.getPractice_id());
			obj.setCreated_user("Billing_Admin");
			obj.setModified_user("Billing_Admin");
			obj.setIs_auto(true);
			claimDao.saveClaimNotes(obj, false);
		}

		return 1;
	}

	@Override
	public List<ORMClaim_Batch_Errors> generateBatch_5010_P(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String batch_id = "";
		String batch_name = "";
		String user_name = "";
		String clientDateTime = "";
		Boolean IgnoreRefPhyErrors = false;

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				System.out.println(
						"param:" + pram.getName() + "    Value:" + pram.getValue() + "    Option:" + pram.getOption());

				if (pram.getName().toLowerCase().equals("batch_id")) {
					batch_id = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("batch_name")) {
					batch_name = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("user_name")) {
					user_name = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("IgnoreRefPhyErrors")) {
					if (pram.getValue() == "true")
						IgnoreRefPhyErrors = true;
				}
				if (pram.getName().toLowerCase().equals("client_date_time")) {
					clientDateTime = pram.getValue();
				}

			}
		}

		ClaimBatchGeneration objClaimBatch = new ClaimBatchGeneration(db, generalDao, billingGeneral);
		return objClaimBatch.GenerateBatch_5010_P(batch_id, batch_name, searchCriteria.getPractice_id().toString(),
				user_name, clientDateTime, IgnoreRefPhyErrors);

	}

	@Override
	public List<ORMTwoColumnGeneric> getBatchPath(String batch_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("batch_id", batch_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetClaimBatch_Path", ORMTwoColumnGeneric.class, lstParam);
	}

	@Override
	public int IgnoreError(String error_id, String user_id) {
		// TODO Auto-generated method stub
		String strQuery = " update claim_batch_error set ignore_error=1,ignore_by='" + user_id
				+ "',ignore_date= getdate() where id='" + error_id + "'";
		return db.ExecuteUpdateQuery(strQuery);
	}

	@Override
	public int deleteClaimFromBatch(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String claim_batch_detail_id = "";
		String user = "";
		String client_date = "";
		String client_ip = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				System.out.println(
						"param:" + pram.getName() + "    Value:" + pram.getValue() + "    Option:" + pram.getOption());

				if (pram.getName().toLowerCase().equals("claim_batch_detail_id")) {
					claim_batch_detail_id = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("user")) {
					user = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("client_date")) {
					client_date = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("client_ip")) {
					client_ip = pram.getValue();
				}
			}
		}
		String Query = " update claim_batch_detail set deleted=1 ,modified_user='" + user
				+ "',date_modified=getdate(),client_date_modified='" + client_date + "',system_ip='" + client_ip
				+ "' where detail_id='" + claim_batch_detail_id + "'";
		db.ExecuteUpdateQuery(Query);

		Query = " update claim_batch_error set deleted=1"
				+ " where batch_id in (select batch_id from claim_batch_detail where detail_id='"
				+ claim_batch_detail_id
				+ "') and claim_id in (select claim_id from claim_batch_detail where detail_id='"
				+ claim_batch_detail_id + "')";
		db.ExecuteUpdateQuery(Query);

		Query = " update claim set pri_status='',sec_status='',oth_status='',submission_status='',submission_date=null ,modified_user='"
				+ user + "_BatchDel',date_modified=getdate(),client_date_modified='" + client_date + "',system_ip='"
				+ client_ip + "' where claim_id in (select claim_id from claim_batch_detail where detail_id='"
				+ claim_batch_detail_id + "')";
		return db.ExecuteUpdateQuery(Query);

	}

	/*
	 * public List<ORMFTPInfo> getFTPInfo(String PracticeID, String category) {
	 * 
	 * try {
	 * 
	 * List<SpParameters> lstParam = new ArrayList<>(); lstParam.add(new
	 * SpParameters("practice_id", PracticeID, String.class, ParameterMode.IN));
	 * lstParam.add(new SpParameters("category", category, String.class,
	 * ParameterMode.IN));
	 * 
	 * return db.getStoreProcedureData("spGetFTPInfo", ORMFTPInfo.class, lstParam);
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * return null; } }
	 */

	@Override
	public String uploadBatchToGatewayEDI(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String host = "";
		String user = "";
		String batch_ids = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				System.out.println(
						"param:" + pram.getName() + "    Value:" + pram.getValue() + "    Option:" + pram.getOption());

				if (pram.getName().toLowerCase().equals("host")) {
					host = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("user")) {
					user = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("batch_id")) {
					batch_ids = pram.getValue();
				}
			}
		}
		String strQuery = "select batch_id as col1,file_path as col2,upper(batch_name) as col3 from claim_batch where batch_id in ("
				+ batch_ids + ") and ISNULL(deleted,0)<>1 and practice_id='" + searchCriteria.getPractice_id() + "'";
		List<ORMThreeColumGeneric> lstBatchDetails = db.getQueryData(strQuery, ORMThreeColumGeneric.class);

		if (lstBatchDetails == null || lstBatchDetails.isEmpty()) {
			return "Batch Not Found.";
		}
		String DocumentPath = "";
		List<ORMDocumentPath> lst = (List<ORMDocumentPath>) generalDao
				.getDocPath(searchCriteria.getPractice_id().toString(), "BatchFiles");
		for (ORMDocumentPath orm : (List<ORMDocumentPath>) lst) {
			DocumentPath = orm.getUpload_path();
		}

		if (!DocumentPath.equals("")) {

		}
		String SFTPHOST = "";
		int SFTPPORT = 22;
		String SFTPUSER = "";
		String SFTPPASS = "";
		String remoteDirectory = ""; // For Batch Upload
		String website_host = "";

		System.out.println("FTP Info : Practice:" + searchCriteria.getPractice_id() + "     Type:BATCH_UPLOAD");
		// List<ORMEdiFtpInfo> list_sftpInfo =
		// this.billingGeneral.getEdiFtpInfo(searchCriteria.getPractice_id().toString(),
		// "BATCH_UPLOAD");
		List<ORMEdiFtpInfo> list_sftpInfo = this.billingGeneral
				.getEdiFtpInfo(searchCriteria.getPractice_id().toString());

		if (list_sftpInfo != null && list_sftpInfo.size() > 0) {

			SFTPHOST = list_sftpInfo.get(0).getFtp_host();
			SFTPPORT = Integer.parseInt(list_sftpInfo.get(0).getFtp_port());
			SFTPUSER = list_sftpInfo.get(0).getFtp_user();
			SFTPPASS = list_sftpInfo.get(0).getFtp_password();
			remoteDirectory = list_sftpInfo.get(0).getBatch_upload_directory();
			website_host = list_sftpInfo.get(0).getApp_hosting_website();
		}

		if (GeneralOperations.isNullorEmpty(SFTPHOST) || GeneralOperations.isNullorEmpty(SFTPPORT)
				|| GeneralOperations.isNullorEmpty(SFTPUSER) || GeneralOperations.isNullorEmpty(SFTPPASS)
				|| GeneralOperations.isNullorEmpty(remoteDirectory)) {
			return "SFTP is not configured for claim batch uploading..";
		}

		if (!website_host.equalsIgnoreCase(host)) {
			return "Operation cannot be performed from the host '" + host + "'";
		}

		JSch jsch;
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		String source_batch_file_path = "";
		String batch_file_name = "";
		String batch_id;

		String files_uploaded = "";
		String files_Not_Found = "";
		String ReturnString = "";
		String ErrorString = "";

		try {
			jsch = new JSch();
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SFTPPASS);
			System.out.println("\nConnecting. SFTP");
			session.connect();

			channel = session.openChannel("sftp");
			System.out.println("\nConnecting. Channel");
			channel.connect();

			// Check to see if we are connected
			if (!channel.isConnected()) {
				System.out.println("\nUnable to connect to SFTP.");
				return "Unable to connect to SFTP.";
			}
			System.out.println("\nConnected to SFTP successfully");

			channelSftp = (ChannelSftp) channel;

			// String sss=channelSftp.pwd();
			// Change directory to the remote folder
			if (remoteDirectory != null && remoteDirectory.equalsIgnoreCase("") == false) {
				System.out.println("\nBrowsing Directory: " + remoteDirectory);
				channelSftp.cd(remoteDirectory);
			}

			for (ORMThreeColumGeneric orm : (List<ORMThreeColumGeneric>) lstBatchDetails) {

				batch_id = orm.getCol1();
				source_batch_file_path = DocumentPath + searchCriteria.getPractice_id().toString() + "\\BatchFiles\\"
						+ orm.getCol2();
				batch_file_name = orm.getCol3() + ".txt";

				if (GeneralOperations.isNullorEmpty(source_batch_file_path)) {
					if (!"".equals(files_Not_Found)) {
						files_Not_Found += "\n";
					}
					files_Not_Found += batch_file_name;

					continue;

				} else {
					File f = new File(source_batch_file_path);
					if (!f.exists()) {
						if (!"".equals(files_Not_Found)) {
							files_Not_Found += "\n";
						}
						files_Not_Found += batch_file_name;

						continue;
					}
				}

				// FileInputStream file=new FileInputStream
				System.out.println("\nSource File: " + source_batch_file_path);
				System.out.println("\nDestincation File: " + batch_file_name);

				channelSftp.put(source_batch_file_path, batch_file_name);
				// channelSftp.put(source_batch_file_path, "test.RMT");

				if (!"".equals(files_uploaded)) {
					files_uploaded += "\n";
				}
				files_uploaded += batch_file_name;

				strQuery = " update claim_batch set date_uploaded=getdate(),uploaded_user='" + user
						+ "',batch_status='Sent To GatewayEdi',batch_status_detail='Sent To GatewayEdi' where batch_id='"
						+ batch_id + "'";
				db.ExecuteUpdateQuery(strQuery);

			}

			channelSftp.exit();
			session.disconnect();

		} catch (JSchException | SftpException e) {
			System.out.println("\nError Occurrerd : \n");
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
			ErrorString = "Error Occur while uploading file:\n" + e.getMessage();
		} catch (Exception e) {
			channelSftp.exit();
			session.disconnect();
			ErrorString = "Error Occur while uploading file:\n" + e.getMessage();
		}

		if (!"".equals(files_uploaded)) {
			ReturnString = "File(s) Uploaded :\n" + files_uploaded;
		}
		if (!"".equals(files_Not_Found)) {
			if (!"".equals(ReturnString)) {
				ReturnString += "\n";
			}

			ReturnString += "File(s) Not Found :\n" + files_Not_Found;
		}
		if (!"".equals(ErrorString)) {
			if (!"".equals(ReturnString)) {
				ReturnString += "\n";
			}

			ReturnString += "Error Occured:\n" + ErrorString;
		}
		return ReturnString;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String downloadBatchResponse(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String result = "";
		String host = "";
		// String user = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				System.out.println(
						"param:" + pram.getName() + "    Value:" + pram.getValue() + "    Option:" + pram.getOption());

				if (pram.getName().toLowerCase().equals("host")) {
					host = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("user")) {
					// user = pram.getValue();
				}

			}
		}
		String SFTPHOST = "";
		int SFTPPORT = 22;
		String SFTPUSER = "";
		String SFTPPASS = "";
		String remoteDirectory = ""; // 997 for batch response
		String website_host = "";
		// List<ORMEdiFtpInfo> list_sftpInfo = this.billingGeneral
		// .getEdiFtpInfo(searchCriteria.getPractice_id().toString(), "BATCH_RESPONSE");

		List<ORMEdiFtpInfo> list_sftpInfo = this.billingGeneral
				.getEdiFtpInfo(searchCriteria.getPractice_id().toString());

		if (list_sftpInfo != null && list_sftpInfo.size() > 0) {

			SFTPHOST = list_sftpInfo.get(0).getFtp_host();
			SFTPPORT = Integer.parseInt(list_sftpInfo.get(0).getFtp_port());
			SFTPUSER = list_sftpInfo.get(0).getFtp_user();
			SFTPPASS = list_sftpInfo.get(0).getFtp_password();
			remoteDirectory = list_sftpInfo.get(0).getBatch_response_directory();
			website_host = list_sftpInfo.get(0).getApp_hosting_website();
		}

		if (GeneralOperations.isNullorEmpty(SFTPHOST) || GeneralOperations.isNullorEmpty(SFTPPORT)
				|| GeneralOperations.isNullorEmpty(SFTPUSER) || GeneralOperations.isNullorEmpty(SFTPPASS)
				|| GeneralOperations.isNullorEmpty(remoteDirectory)) {
			return "SFTP is not configured for claim batch response.";
		}

		if (!website_host.equalsIgnoreCase(host)) {
			return "Operation cannot be performed from the host '" + host + "'";
		}

		ChannelSftp channelSftp = null;
		JSch jsch = null;
		Session session = null;
		Channel channel = null;
		try {
			jsch = new JSch();

			BufferedReader br;
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SFTPPASS);
			System.out.println("\nConnecting. SFTP");
			session.connect();

			channel = session.openChannel("sftp");
			System.out.println("\nConnecting. Channel");
			channel.connect();

			// Check to see if we are connected
			if (!channel.isConnected()) {
				System.out.println("\nUnable to connect to SFTP.");
				return "Unable to connect to SFTP.";
			}
			System.out.println("\nConnected to SFTP successfully");

			channelSftp = (ChannelSftp) channel;

			// String sss=channelSftp.pwd();
			// Change directory to the remote folder
			if (remoteDirectory != null && remoteDirectory.equalsIgnoreCase("") == false) {
				System.out.println("\nBrowsing Directory: " + remoteDirectory);
				channelSftp.cd(remoteDirectory);
			}

			Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.997");
			list.addAll(channelSftp.ls("*.999"));

			if (list.isEmpty()) {
				result = "No Response File found at directory '" + remoteDirectory + "'";
				System.out.println(result);
				System.out.println("\nClosing SFTP Connectiong: ");

				channelSftp.exit();
				session.disconnect();
				return result;
			}

			int total_files = 0;

			for (ChannelSftp.LsEntry file : list) {
				int mid = file.getFilename().lastIndexOf(".");
				// String FileName= file.getFilename().substring(0,mid);
				String ext = file.getFilename().substring(mid + 1, file.getFilename().length());

				if (!ext.toUpperCase().equals("997") && !ext.toUpperCase().equals("999")) {
					continue;
				}

				total_files++;
				BufferedInputStream bis = new BufferedInputStream(channelSftp.get(file.getFilename()));
				br = new BufferedReader(new InputStreamReader(bis, "UTF-8"));

				String MainString;
				StringBuilder SBulder = new StringBuilder();
				String temp;

				while ((temp = br.readLine()) != null) {
					SBulder.append(temp);
				}

				MainString = SBulder.toString();

				bis.close();
				br.close();

				String date_of_interchange = "";
				String batch_process_id = "";
				// String batch_status_ak5="";
				String batch_status = "";
				String batch_status_detail = "";
				// interpret response string.
				String[] ResponseSegments = MainString.split("~");
				String[] SubSegments;
				if (ResponseSegments.length > 0) {
					for (String Segment : ResponseSegments) {

						SubSegments = Segment.split("\\*");
						if (SubSegments[0].equalsIgnoreCase("ISA")) {
							if (SubSegments.length > 8) {
								date_of_interchange = SubSegments[9];
							}
						} else if (SubSegments[0].equalsIgnoreCase("AK1")) {
							if (SubSegments.length > 2) {
								batch_process_id = SubSegments[2];
							}
						} else if (GeneralOperations.isNotNullorEmpty(batch_process_id)) {
							if (SubSegments[0].equalsIgnoreCase("AK9")) {
								batch_status = SubSegments[1];
								if (SubSegments[1].equalsIgnoreCase("A")) {
									batch_status_detail = "Accepted";
								} else if (SubSegments[1].equalsIgnoreCase("E")) {
									batch_status_detail = "Accepted, but errors were noted";
								} else if (SubSegments[1].equalsIgnoreCase("M")) {
									batch_status_detail = "Rejected, message authentication code (MAC) failed";
								} else if (SubSegments[1].equalsIgnoreCase("P")) {
									batch_status_detail = "Partially accepted, at least one transaction set was rejected";
								} else if (SubSegments[1].equalsIgnoreCase("R")) {
									batch_status_detail = "Rejected";
								} else if (SubSegments[1].equalsIgnoreCase("W")) {
									batch_status_detail = "Rejected, assurance failed validity tests";
								} else if (SubSegments[1].equalsIgnoreCase("X")) {
									batch_status_detail = "Rejected, content after decryption could not be analyzed";
								}
							}
						}
					}
				}
				// -------------------------------------------------------------------
				String save_path = "";
				List<ORMDocumentPath> lst = (List<ORMDocumentPath>) generalDao
						.getDocPath(searchCriteria.getPractice_id().toString(), "BatchResponseFiles");
				for (ORMDocumentPath orm : (List<ORMDocumentPath>) lst) {
					save_path = orm.getUpload_path();
				}
				String response_file_path = GenerateResponseFile(searchCriteria.getPractice_id().toString(),
						batch_process_id, MainString, save_path, ext);
				String strQuery = " update claim_batch set date_modified=getdate(), date_processed='"
						+ date_of_interchange + "',batch_status='" + batch_status + "',batch_status_detail='"
						+ batch_status_detail + "',response_file_path='" + response_file_path + "' where batch_id='"
						+ batch_process_id + "'";
				if (db.ExecuteUpdateQuery(strQuery) > 0) {
					System.out.println("\nDeleting File: " + file.getFilename());
					channelSftp.rm(file.getFilename());
				}
			}

			channelSftp.exit();
			session.disconnect();

			return total_files + " Batch Response file(s) have been download successfully.";

		} catch (JSchException | SftpException e) {
			result = e.toString();
			System.out.println("\nError Occurrerd : \n");
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
			return "Error Occur while downloading batch response file:\n" + e.getMessage();
		} catch (Exception ex) {
			channelSftp.exit();
			session.disconnect();

			return "Error Occur while downloading batch response file:\n" + ex.getMessage();
		}
	}

	private String GenerateResponseFile(String practiceID, String batch_id, String ResponsMainString, String save_path,
			String ext) {

		String file_path = "";
		// file_path = GeneralOperations.GetYearMonthDayWisePath(practiceID, save_path,
		// "BatchResponseFiles");
		try {

			String full_file_path = FileUtil.GetYearMonthDayWisePath(save_path, practiceID.toString(),
					"BatchResponseFiles") + "\\" + batch_id + "." + ext;

			System.out.println("Saving Response File...");

			String[] splitedPath = full_file_path.split("\\\\");

			file_path = splitedPath[splitedPath.length - 4] + "\\" + splitedPath[splitedPath.length - 3] + "\\"
					+ splitedPath[splitedPath.length - 2] + "\\" + batch_id + "." + ext;

			FileUtil.UploadStringDataToFile(ResponsMainString, full_file_path);

			/*
			 * 
			 * System.out.println("Saving Response File..."); file_path +=
			 * "\\" + batch_id + "." + ext; String full_file_path = save_path +
			 * "\\" + practiceID + "\\BatchResponseFiles\\" + file_path; // String
			 * full_file_path = GeneralOperations.checkPath(save_path) + "\\" + // batch_id
			 * + "." + ext; String[] splitedPath = full_file_path.split("\\\\"); file_path =
			 * splitedPath[splitedPath.length - 2] + "\\" + batch_id + "." + ext;
			 * Files.write(Paths.get(full_file_path), ResponsMainString.getBytes(),
			 * StandardOpenOption.CREATE);
			 */

		} catch (IOException ex) {
			file_path = "";
		}
		return file_path;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveDenialMessage(ORMDenialMessagesSave ormDenialMessagesSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormDenialMessagesSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormDenialMessagesSave.getDenial_id())) {

			ormDenialMessagesSave
					.setDenial_id(db.IDGenerator("denial_messages", ormDenialMessagesSave.getPractice_id()));
			ormDenialMessagesSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormDenialMessagesSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormDenialMessagesSave, Operation.EDIT);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> resolveDenialMessage(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		String denialIds = "";
		String clientUser = "";
		String clientDateTime = "";
		String clientIp = "";
		String resolveMessage = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "denial_ids":
					denialIds = pram.getValue();
					break;
				case "loged_in_user":
					clientUser = pram.getValue();
					break;
				case "client_datetime":
					clientDateTime = pram.getValue();
					break;
				case "client_ip":
					clientIp = pram.getValue();
					break;
				case "resolve_message":
					resolveMessage = pram.getValue();
					break;

				default:
					break;
				}
			}

			String query = "update denial_messages set resolved_message='" + resolveMessage.replace('\'', '`')
					+ "' , status='RESOLVED', modified_user='" + clientUser
					+ "', date_modified=getdate(),resolved_user='" + clientUser + "', date_resolved='" + clientDateTime
					+ "', client_date_modified='" + clientDateTime + "', system_ip='" + clientIp
					+ "' where denial_id in (" + denialIds + ")";

			result = db.ExecuteUpdateQuery(query);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public List<ORMEraListGet> getEraList(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String criteria = " and e.practice_id='" + searchCriteria.getPractice_id() + "' ";

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "era_id":
					criteria += " and e.era_id='" + param.getValue() + "' ";
					break;
				case "claim_id":
					criteria += " and ec.claim_id='" + param.getValue() + "' ";
					break;
				case "patient_id":
					criteria += " and ec.patient_id='" + param.getValue() + "' ";
					break;
				case "payer_number":
					criteria += " and  isnull(e.payer_identifier,'')='" + param.getValue() + "' ";
					break;
				case "icn_number":
					criteria += " and  isnull(ec.payer_claim_control_number,'')='" + param.getValue() + "' ";
					break;
				case "status":
					if (param.getValue().equalsIgnoreCase("pending")) {
						criteria += " and isnull(e.posted,0)<>1 ";
					} else if (param.getValue().equalsIgnoreCase("posted")) {
						criteria += " and isnull(e.posted,0) = 1 ";
					}
					break;

				case "check_number":
					criteria += " and e.check_number='" + param.getValue() + "' ";
					break;
				case "check_amount":
					criteria += " and isnull(e.check_amount,0) " + param.getOption() + " '" + param.getValue() + "' ";
					break;
				case "check_date_from":
					criteria += " and convert(date,e.check_date) >= convert(date,'" + param.getValue() + "') ";
					break;
				case "check_date_to":
					criteria += " and convert(date,e.check_date) <= convert(date,'" + param.getValue() + "') ";
					break;

				case "dos_from":
					criteria += " and convert(date,ec.claim_statement_period_start) >= convert(date,'"
							+ param.getValue() + "') ";
					break;
				case "dos_to":
					criteria += " and convert(date,ec.claim_statement_period_start) <= convert(date,'"
							+ param.getValue() + "') ";
					break;

				case "date_created_from":
					criteria += " and convert(date,e.date_created) >= convert(date,'" + param.getValue() + "') ";
					break;
				case "date_created_to":
					criteria += " and convert(date,e.date_created) <= convert(date,'" + param.getValue() + "') ";
					break;

				case "file_with_error":
					if (param.getValue().equalsIgnoreCase("true")) {
						criteria += " and isnull(e.is_error,0) = 1 ";
					}
					break;
				case "file_not_processed":
					if (param.getValue().equalsIgnoreCase("true")) {
						criteria += " and isnull(e.is_processed,0) <> 1";
					}
					break;
				case "notification_only":
					if (param.getValue().equalsIgnoreCase("true")) {
						criteria += " and isnull(e.transaction_handling_code,'') = 'H' ";
					}
					break;

				default:
					break;
				}

			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetERAList", ORMEraListGet.class, lstParam);

	}

	@Override
	public List<ORMEraGet> getEraDetails(Long eraId) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("era_id", eraId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetERADetail", ORMEraGet.class, lstParam);

	}

	@Override
	public List<ORMEraClaimListGet> getEraClaimList(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String criteria = " and ec.practice_id='" + searchCriteria.getPractice_id() + "' ";

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "era_id":
					criteria += " and e.era_id='" + param.getValue() + "' ";
					break;
				case "era_claim_id":
					criteria += " and ec.era_claim_id='" + param.getValue() + "' ";
					break;
				case "claim_id":
					criteria += " and ec.claim_id='" + param.getValue() + "' ";
					break;
				case "patient_id":
					criteria += " and ec.patient_id='" + param.getValue() + "' ";
					break;
				case "icn_number":
					criteria += " and  isnull(ec.payer_claim_control_number,'')='" + param.getValue() + "' ";
					break;
				case "status":
					if (param.getValue().equalsIgnoreCase("pending")) {
						criteria += " and isnull(ec.posted,0)<>1 ";
					} else if (param.getValue().equalsIgnoreCase("posted")) {
						criteria += " and isnull(ec.posted,0) = 1 ";
					}
					break;

				case "dos_from":
					criteria += " and convert(date,ec.claim_statement_period_start) >= convert(date,'"
							+ param.getValue() + "') ";
					break;
				case "dos_to":
					criteria += " and convert(date,ec.claim_statement_period_start) <= convert(date,'"
							+ param.getValue() + "') ";
					break;

				default:
					break;
				}

			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetERAClaimByCriteria", ORMEraClaimListGet.class, lstParam);
	}

	@Override
	public ORMEraClaimDetailGet getEraClaimDetail(Long practiceId, Long eraClaimId) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("era_claim_id", eraClaimId.toString(), String.class, ParameterMode.IN));
		List<ORMEraClaimDetailGet> lst = db.getStoreProcedureData("spGetERAClaimDetailNew", ORMEraClaimDetailGet.class,
				lstParam);

		if (lst != null && lst.size() > 0) {
			return (ORMEraClaimDetailGet) lst.get(0);
		} else {
			return null;
		}

	}

	@Override
	public List<ORMEraClaimServicesGet> getEraClaimServices(Long eraClaimId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("era_claim_id", eraClaimId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetERAClaimServiceNew", ORMEraClaimServicesGet.class, lstParam);
	}

	@Override
	public List<ORMIdCodeDescriptionType> getAdjustCodesGlossary(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<ORMIdCodeDescriptionType> lst = null;

		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));
			}
		}

		lst = db.getStoreProcedureData("spgetAdjustCodesGlossary", ORMIdCodeDescriptionType.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMGetEOB> getEOB(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String check_number = "";
		String eob_id = "";
		String date_type = "";
		String date_from = "";
		String date_to = "";
		String payer_id = "";
		String patient_id = "";
		Boolean chkPending = false;
		Boolean chkPosted = false;
		Boolean chkDeleted = false;
		Boolean chkAttorny = false;
		String payment_type = "";

		String strCriteria = "";

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "date_type":
					date_type = param.getValue();
					break;
				case "check_number":
					check_number = param.getValue();
					break;
				case "eob_id":
					eob_id = param.getValue();
					break;
				case "payer_id":
					payer_id = param.getValue();
					break;
				case "patient_id":
					patient_id = param.getValue();
					break;

				case "chk_pending":
					if (param.getValue().equals("true"))
						chkPending = true;
					break;
				case "chk_posted":
					if (param.getValue().equals("true"))
						chkPosted = true;
					break;

				case "chk_attorney":
					if (param.getValue().equals("true")) {
						strCriteria += " and eob.attorney_id is not null ";
					}
					break;
				case "chk_deleted":
					if (param.getValue().equals("true"))
						chkDeleted = true;
					break;
				case "payment_type":
					payment_type = param.getValue();
					break;
				case "date_from":
					date_from = param.getValue();
					break;
				case "date_to":
					date_to = param.getValue();
					break;
				default:
					break;
				}

			}
		}

		if (!check_number.equals("")) {
			strCriteria += " and eob.check_number='" + check_number + "' ";
		}
		if (!eob_id.equals("")) {
			strCriteria += " and eob.eob_id='" + eob_id.trim() + "' ";
		}
		if (date_type.equals("check_date")) {
			strCriteria += " and eob.check_date between convert(datetime,'" + date_from + "') and convert(datetime,'"
					+ date_to + "') ";
		}
		if (date_type.equals("filing_date")) {
			strCriteria += " and eob.eob_date between convert(datetime,'" + date_from + "') and convert(datetime,'"
					+ date_to + "') ";
		}
		if (!payer_id.equals("")) {
			strCriteria += " and eob.payer_id in (" + payer_id + ") ";
		}

		String subCriteria = "";

		if (chkPending) {
			if (subCriteria != "") {
				subCriteria += " OR ";
			}
			subCriteria += " isnull(eob.is_posted,0)<>1 ";
		}

		if (chkPosted) {
			if (subCriteria != "") {
				subCriteria += " OR ";
			}
			subCriteria += " isnull(eob.is_posted,0)=1 ";
		}

		if (chkDeleted) {
			if (subCriteria != "") {
				subCriteria += " OR ";
			}
			subCriteria += " isnull(eob.deleted,0)=1 ";

		} else {
			strCriteria += " and isnull(eob.deleted,0)<>1 ";
		}

		if (subCriteria != "") {
			strCriteria += " and ( " + subCriteria + " ) ";
		}

		if (GeneralOperations.isNotNullorEmpty(payment_type)) {
			strCriteria += " and eob.payment_type='" + payment_type + "' ";
		}

		if (!patient_id.equals("")) {
			strCriteria += " and eob.patient_id='" + patient_id + "' ";
		}

		if (chkAttorny) {
			strCriteria += " and eob.attorney_id is not null ";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", strCriteria, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetEOB", ORMGetEOB.class, lstParam);
	}

	@Override
	public List<ORMEobAttachmentGet> getEOBAttachment(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));

			}
		}

		return db.getStoreProcedureData("spGetEOBAttachments", ORMEobAttachmentGet.class, lstParam);

	}

	public Boolean IsEobEraExist(String practice_id, String check_number, String check_date, String check_amount) {

		String checkAmount = check_amount.replaceAll(",", "");

		String strQuery = " select sum(total) as scalar_value " + " from " + "  ( "
				+ "          select COUNT(*) as total  " + "          from eob "
				+ "          where  isnull(deleted,0)<>1 " + "          and convert(date,check_date)=convert(date,'"
				+ check_date + "') " + "          and isnull(check_number,'')='" + check_number + "'  "
				+ "          and isnull(check_amount,0)=" + checkAmount + "          and practice_id='" + practice_id
				+ "' " + "          union all " + "          select COUNT(*) as total  " + "          from era "
				+ "          where  isnull(deleted,0)<>1 " + "          and convert(date,check_date)=convert(date,'"
				+ check_date + "') " + "          and isnull(check_number,'')='" + check_number + "'  "
				+ "          and isnull(check_amount,0)=" + checkAmount + "          and practice_id='" + practice_id
				+ "'" + "  ) as a ";
		return Integer.parseInt(db.getQuerySingleResult(strQuery)) > 0;

	}

	@Override
	public ServiceResponse<ORMKeyValue> uploadEOB(ORMEOBSave eob, ORMEobAttachmentsSave eobAtt, MultipartFile docFile) {
		// TODO Auto-generated method stub
		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (GeneralOperations.isNullorEmpty(eob.getEob_id()))// New
		{
			if (IsEobEraExist(eob.getPractice_id(), eob.getCheck_number(), eob.getCheck_date(),
					eob.getCheck_amount())) {
				String res_str = "-1~EOB/ERA Already Exist with the Same Details:\n" + "Check Number:"
						+ eob.getCheck_number() + "\n" + "Check Date:" + eob.getCheck_date() + "\n" + "Check Amount:$"
						+ eob.getCheck_amount();
				resp.setResponse(res_str);
				resp.setStatus(ServiceResponseStatus.ERROR);
				return resp;
			}
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		Date date = new Date();
		String directory = "";
		String category_path = "";
		String originalFilename = "";
		Boolean isDocUploaded = false;

		Long generatedId;

		if (docFile != null && !docFile.isEmpty()) {
			try {

				List<ORMDocumentPath> lstPath = generalDao.getDocPath(eob.getPractice_id().toString(), "EOB");
				System.out.println("Get doc" + lstPath.size());

				if (!lstPath.isEmpty()) {
					category_path = lstPath.get(0).getUpload_path();
					category_path = category_path + "\\" + eob.getPractice_id() + "\\" + "EOB";

					System.out.println("Directory" + category_path);
				}
				directory = GeneralOperations.checkPathYearMonthWise(eob.getPractice_id().toString(),
						lstPath.get(0).getUpload_path().toString(), "EOB");

				System.out.println("directory " + directory);
				originalFilename = docFile.getOriginalFilename();

			} catch (Exception e) {
				System.out.println("Picture Upload Error:" + e.getMessage());
			}
		}

		if (eob.getEob_id() != null && eob.getEob_id() != "") {
			if (docFile != null && !docFile.isEmpty()) {
				try {

					// directory=directory+"\\"+patDoc.getEmp_id()+"\\";
					String fileName = GeneralOperations.GetDatetimeFileName() + "."
							+ FilenameUtils.getExtension(originalFilename);
					System.out.println("fileName" + fileName);
					File destinationFile = new File(GeneralOperations.CheckPath(category_path + "\\" + directory),
							fileName);
					docFile.transferTo(destinationFile);
					System.out.println("destinationFile" + directory);
					System.out.println("fileName" + fileName);

					eobAtt.setFile_link(directory + "\\" + fileName);
					isDocUploaded = true;

				} catch (Exception e) {
					System.out.println("Document Upload Error:" + e.getMessage());
				}
			} else {
				isDocUploaded = true;
				//
			}

			if (isDocUploaded) {
				// System.out.println(""+e.getMessage());
				eobAtt.setDate_modified(dateFormat.format(date));
				eob.setDate_modified(dateFormat.format(date));
				// db.SaveEntity(eob, Operation.EDIT);

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, eob));
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, eobAtt));

				System.out.println(eob.toString());
				System.out.println(eobAtt.toString());
				if (db.AddUpdateEntityWrapper(lstEntityWrapper) > 0) {
					generatedId = Long.parseLong(eob.getEob_id());
				} else {
					generatedId = (long) 0;
				}
			} else {
				generatedId = (long) 0;
			}
		} else {

			eob.setEob_id(db.IDGenerator("eob", Long.parseLong(eob.getPractice_id())).toString());
			eob.setDate_created(dateFormat.format(date));
			eob.setDate_modified(dateFormat.format(date));

			eobAtt.setAttachment_id(db.IDGenerator("eob_attachments", Long.parseLong(eob.getPractice_id())).toString());
			eobAtt.setEob_id(eob.getEob_id());
			eobAtt.setDate_created(dateFormat.format(date));
			eobAtt.setDate_modified(dateFormat.format(date));

			if (docFile != null && !docFile.isEmpty()) {
				try {
					// directory=directory+"\\"+empDoc.getEmp_id()+"\\";
					String fileName = GeneralOperations.GetDatetimeFileName() + "."
							+ FilenameUtils.getExtension(originalFilename);
					System.out.println("Final path" + category_path + "\\" + directory);
					File destinationFile = new File(GeneralOperations.CheckPath(category_path + "\\" + directory),
							fileName);
					System.out.println("destinationFile path" + destinationFile.toString());
					docFile.transferTo(destinationFile);

					eobAtt.setFile_link(directory + "\\" + fileName);
					isDocUploaded = true;

				} catch (Exception e) {
					System.out.println("Document Upload Error:" + e.getMessage());
				}
			}

			if (isDocUploaded) {
				// db.SaveEntity(eob, Operation.ADD);
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, eob));
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, eobAtt));

				if (db.AddUpdateEntityWrapper(lstEntityWrapper) > 0) {
					generatedId = Long.parseLong(eob.getEob_id());
				} else {
					generatedId = (long) 0;
				}
			} else {
				generatedId = (long) 0;
			}
		}
		System.out.println("Return " + Long.toString(generatedId));

		if (generatedId > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(generatedId.toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("0~An error occured while saving record.");
		}
		return resp;
	}

	@Override
	public List<ORMEraProviderAdjustmentGet> getERAProviderAdjustment(Long eraId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("era_id", eraId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetERAProviderAdjustmentNew", ORMEraProviderAdjustmentGet.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> mapEraClaimServicesIDs(SearchCriteria searchCriteria) {

		String earClaimServiceIds = "";
		String claimId = "";
		String logedInUser = "";
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "era_claim_service_ids":
					earClaimServiceIds = param.getValue();
					break;
				case "claim_id":
					claimId = param.getValue();
					break;
				case "loged_in_user":
					logedInUser = param.getValue();
					break;
				default:
					break;
				}

			}
		}

		if (GeneralOperations.isNullorEmpty(earClaimServiceIds) || GeneralOperations.isNullorEmpty(claimId)
				|| GeneralOperations.isNullorEmpty(logedInUser)) {

			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("One of the required parameter is missing.");

		} else {

			List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

			String query = "  update ecs set ecs.claim_procedures_id=cp.claim_procedures_id,date_mapped=getdate(),mapped_by='"
					+ logedInUser + "' " + " from era_claim_service ecs "
					+ " inner join era_claim ec on ecs.era_claim_id= ec.era_claim_id "
					+ " inner join claim_procedure cp on ec.claim_id=cp.claim_id and ecs.paid_procedure=cp.proc_code  "
					+ " and isnull(ecs.paid_procedure_modifier,'')=isnull(cp.mod1,'') and isnull(cp.deleted,0)<>1"
					+ " where cp.claim_id='" + claimId + "' and ecs.era_claim_service_id in  (" + earClaimServiceIds
					+ ") ";

			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

			query = " update adj set  adj.claim_procedures_id=ecs.claim_procedures_id ,date_modified=getdate(),modified_user='"
					+ logedInUser + "' " + " from era_adjustment adj "
					+ " inner join era_claim_service ecs on ecs.era_claim_service_id=adj.era_claim_service_id "
					+ " where  ecs.era_claim_service_id in  (" + earClaimServiceIds + ") ";

			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

			result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
				// resp.setResult(obj.getBatch_id().toString());
			}
		}

		return resp;
	}

	@Override
	public ERAOperationResult importERAFromGatewayEdi(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		Long practiceId = searchCriteria.getPractice_id();
		String rootUrl = "";
		String LogedInUser = "";
		String clientDateTime = "";
		String clientIp = "";

		ERAOperationResult eraOperationResult;

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "root_url":
					rootUrl = param.getValue();
					break;
				case "client_date_time":
					clientDateTime = param.getValue();
					break;
				case "loged_in_user":
					LogedInUser = param.getValue();
					break;
				case "client_ip":
					clientIp = param.getValue();
					break;
				default:
					break;
				}
			}
		}

		if (GeneralOperations.isNullorEmpty(rootUrl) || GeneralOperations.isNullorEmpty(clientDateTime)
				|| GeneralOperations.isNullorEmpty(LogedInUser) || GeneralOperations.isNullorEmpty(clientIp)
				|| GeneralOperations.isNullorEmpty(practiceId)) {

			// resp.setStatus(ServiceResponseStatus.ERROR);
			// resp.setResponse("One of the required parameter is missing.");

			eraOperationResult = new ERAOperationResult();
			eraOperationResult.setMessage("One of the required parameter is missing.");
			eraOperationResult.setMessage_type("ERROR");

		} else {
			ERAOperations eraOperations = new ERAOperations(this.db, this.billingGeneral, this.generalDao,
					this.claimDao, this, practiceId, LogedInUser, clientDateTime, clientIp);

			eraOperationResult = eraOperations.DownlaodERAs(rootUrl);

		}

		System.out.println(eraOperationResult.toString());

		return eraOperationResult;
	}

	@Override
	public ServiceResponse<ORMKeyValue> deleteERA(ORMDeleteRecord ormDeleteRecord) {
		// TODO Auto-generated method stub

		int result = 0;

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		String strQuery = "select top 1 claim_payments_id  as scalar_value from claim_payment where isnull(deleted,0)<>1 and eob_era_id='"
				+ ormDeleteRecord.getColumn_id() + "' and eob_era_id_type='ERA' ";

		String record = db.getQuerySingleResult(strQuery);

		// return GeneralOperations.isNotNullorEmpty(result) ;

		if (GeneralOperations.isNotNullorEmpty(record)) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("Selected ERA Can't be deleted.<br>There is/are posted payment(s) against selected ERA.");
		} else {

			List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

			strQuery = " update era set deleted=1,modified_user='" + ormDeleteRecord.getModified_user()
					+ "',date_modified=getdate(),client_date_modified='" + ormDeleteRecord.getClient_date_time()
					+ "',system_ip='" + ormDeleteRecord.getClient_ip() + "' where era_id='"
					+ ormDeleteRecord.getColumn_id() + "' ";
			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

			strQuery = " update era_claim set deleted=1,modified_user='" + ormDeleteRecord.getModified_user()
					+ "',date_modified=getdate(),client_date_modified='" + ormDeleteRecord.getClient_date_time()
					+ "',system_ip='" + ormDeleteRecord.getClient_ip() + "' where era_id='"
					+ ormDeleteRecord.getColumn_id() + "' ";
			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

			strQuery = " update era_claim_service set deleted=1,modified_user='" + ormDeleteRecord.getModified_user()
					+ "',date_modified=getdate(),client_date_modified='" + ormDeleteRecord.getClient_date_time()
					+ "',system_ip='" + ormDeleteRecord.getClient_ip() + "' where era_id='"
					+ ormDeleteRecord.getColumn_id() + "' ";
			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

			strQuery = " update era_adjustment set deleted=1,modified_user='" + ormDeleteRecord.getModified_user()
					+ "',date_modified=getdate(),client_date_modified='" + ormDeleteRecord.getClient_date_time()
					+ "',system_ip='" + ormDeleteRecord.getClient_ip() + "' where era_id='"
					+ ormDeleteRecord.getColumn_id() + "' ";
			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

			strQuery = " update era_provider_adjustment set deleted=1 where era_id='" + ormDeleteRecord.getColumn_id()
					+ "' ";
			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

			result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
				// resp.setResult(obj.getBatch_id().toString());
			}

		}

		return resp;

	}

	@Override
	public ServiceResponse<ORMKeyValue> mapEraClaimPaymentInsurance(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		Long eraClaimId = (long) 0;
		Long insuranceId = (long) 0;
		String LogedInUser = "";
		String clientDateTime = "";
		String clientIp = "";

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "era_claim_id":
					eraClaimId = Long.parseLong(param.getValue());
					break;
				case "insurance_id":
					insuranceId = Long.parseLong(param.getValue());
					break;
				case "client_date_time":
					clientDateTime = param.getValue();
					break;
				case "loged_in_user":
					LogedInUser = param.getValue();
					break;
				case "client_ip":
					clientIp = param.getValue();
					break;
				default:
					break;
				}
			}
		}

		if (eraClaimId == 0 || insuranceId == 0 || GeneralOperations.isNullorEmpty(clientDateTime)
				|| GeneralOperations.isNullorEmpty(LogedInUser) || GeneralOperations.isNullorEmpty(clientIp)) {

			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("One of the required parameter is missing.");

		} else {

			String strQuery = " update era_claim set mapped_insurance_id='" + insuranceId
					+ "',date_modified=GETDATE(),client_date_modified='" + clientDateTime + "',modified_user='"
					+ LogedInUser + "',system_ip='" + clientIp + "' " + " where era_claim_id='" + eraClaimId + "'  ";

			if (db.ExecuteUpdateQuery(strQuery) > 0) {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
			} else {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			}
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> markAsPosted(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		Long Id = (long) 0;

		String dataOption = "";
		String LogedInUser = "";
		String clientDateTime = "";
		String clientIp = "";

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "id":
					Id = Long.parseLong(param.getValue());
					break;
				case "data_option":
					dataOption = param.getValue();
					break;
				case "client_date_time":
					clientDateTime = param.getValue();
					break;
				case "loged_in_user":
					LogedInUser = param.getValue();
					break;
				case "client_ip":
					clientIp = param.getValue();
					break;
				default:
					break;
				}
			}
		}

		if (Id == 0 || GeneralOperations.isNullorEmpty(dataOption) || GeneralOperations.isNullorEmpty(clientDateTime)
				|| GeneralOperations.isNullorEmpty(LogedInUser) || GeneralOperations.isNullorEmpty(clientIp)) {

			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("One of the required parameter is missing.");

		} else {

			List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

			String strQuery = "";

			switch (dataOption) {
			case "ERA":

				strQuery = " update era set posted=1,posted_by='" + LogedInUser + "-[Manual]',date_posted='"
						+ clientDateTime + "',date_modified=GETDATE(),client_date_modified='" + clientDateTime
						+ "',modified_user='" + LogedInUser + "',system_ip='" + clientIp + "' " + " where era_id='" + Id
						+ "' and isnull(posted,0)<>1  and isnull(deleted,0)<>1 ";
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				strQuery = " update era_claim set posted=1,posted_by='" + LogedInUser + "-[Manual]',date_posted='"
						+ clientDateTime + "',date_modified=GETDATE(),client_date_modified='" + clientDateTime
						+ "',modified_user='" + LogedInUser + "',system_ip='" + clientIp + "' " + " where era_id='" + Id
						+ "' and isnull(posted,0)<>1  and isnull(deleted,0)<>1 ";
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				strQuery = " update era_claim_service set payment_posted=1,posted_by='" + LogedInUser
						+ "-[Manual]',date_posted='" + clientDateTime
						+ "',date_modified=GETDATE(),client_date_modified='" + clientDateTime + "',modified_user='"
						+ LogedInUser + "',system_ip='" + clientIp + "' " + " where era_id='" + Id
						+ "' and isnull(payment_posted,0)<>1 and isnull(denial_posted,0)<>1 and isnull(deleted,0)<>1 ";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				break;
			case "ERA_CLAIM":
				strQuery = " update era_claim set posted=1,posted_by='" + LogedInUser + "-[Manual]',date_posted='"
						+ clientDateTime + "',date_modified=GETDATE(),client_date_modified='" + clientDateTime
						+ "',modified_user='" + LogedInUser + "',system_ip='" + clientIp + "' "
						+ " where era_claim_id='" + Id + "' and isnull(posted,0)<>1 and isnull(deleted,0)<>1 ";
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				strQuery = " update era_claim_service set payment_posted=1,posted_by='" + LogedInUser
						+ "-[Manual]',date_posted='" + clientDateTime
						+ "',date_modified=GETDATE(),client_date_modified='" + clientDateTime + "',modified_user='"
						+ LogedInUser + "',system_ip='" + clientIp + "' " + " where era_claim_id='" + Id
						+ "' and isnull(payment_posted,0)<>1 and isnull(denial_posted,0)<>1 and isnull(deleted,0)<>1 ";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				break;
			case "ERA_CLAIM_SERVICE":

				strQuery = " update era_claim_service set payment_posted=1,posted_by='" + LogedInUser
						+ "-[Manual]',date_posted='" + clientDateTime
						+ "',date_modified=GETDATE(),client_date_modified='" + clientDateTime + "',modified_user='"
						+ LogedInUser + "',system_ip='" + clientIp + "' " + " where era_claim_service_id='" + Id
						+ "' and isnull(payment_posted,0)<>1 and isnull(denial_posted,0)<>1 and isnull(deleted,0)<>1 ";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				break;

			case "EOB":
				strQuery = "update eob set is_posted=1 ,modified_user='" + LogedInUser
						+ "',date_modified=getdate(),client_date_modified=getdate() where eob_id='" + Id + "'";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				break;

			default:
				break;
			}

			if (db.AddUpdateEntityWrapper(lstEntityWrapper) > 0) {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
			} else {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			}
		}

		return resp;
	}

	@Override
	public ERAPaymentPostingResponse postERAPayment(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String postingOption = "";
		String logedInUser = "";
		String clientDateTime = "";
		String clientIp = "";
		String checkDate = "";
		String checkNo = "";
		Integer postDuplicate = -1; // 0|1|-1
		Integer postInterest = -1; // 0|1|-1
		String eraClaimServiceIds = "";
		Long eraId = (long) 0;
		Long eraClaimId = (long) 0;

		Long practiceId = searchCriteria.getPractice_id();

		ERAPaymentPostingResponse eraPaymentPostingResponse;
		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "posting_option":
					postingOption = param.getValue();
					break;
				case "post_duplicate":
					postDuplicate = Integer.parseInt(param.getValue());
					break;
				case "post_interest":
					postInterest = Integer.parseInt(param.getValue());
					break;
				case "era_id":
					eraId = Long.parseLong(param.getValue());
					break;
				case "era_claim_id":
					eraClaimId = Long.parseLong(param.getValue());
					break;
				case "era_claim_service_ids":
					eraClaimServiceIds = param.getValue();
					break;
				case "check_date":
					checkDate = param.getValue();
					break;
				case "check_no":
					checkNo = param.getValue();
					break;
				case "loged_in_user":
					logedInUser = param.getValue();
					break;
				case "client_date_time":
					clientDateTime = param.getValue();
					break;
				case "client_ip":
					clientIp = param.getValue();
					break;
				default:
					break;
				}
			}
		}

		if (practiceId == null || practiceId == 0 || eraId == null || eraId == 0
				|| GeneralOperations.isNullorEmpty(postingOption) || GeneralOperations.isNullorEmpty(checkDate)
				|| GeneralOperations.isNullorEmpty(checkNo) || GeneralOperations.isNullorEmpty(logedInUser)
				|| GeneralOperations.isNullorEmpty(clientDateTime) || GeneralOperations.isNullorEmpty(clientIp)) {

			eraPaymentPostingResponse = new ERAPaymentPostingResponse("ERROR",
					"One of the required parameter is missing.", "ALERT", 0, 0);

		} else {

			ERAOperations eraOperations = new ERAOperations(this.db, this.billingGeneral, this.generalDao,
					this.claimDao, this, practiceId, logedInUser, clientDateTime, clientIp);

			eraPaymentPostingResponse = eraOperations.postERAPayment(postingOption, postDuplicate, postInterest, eraId,
					eraClaimId, eraClaimServiceIds, checkDate, checkNo);
		}

		return eraPaymentPostingResponse;
	}

	@Override
	public ERAOperationResult importERAFromTextString(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		Long practiceId = searchCriteria.getPractice_id();
		String LogedInUser = "";
		String clientDateTime = "";
		String clientIp = "";
		String eraString = "";

		ERAOperationResult eraOperationResult;

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "client_date_time":
					clientDateTime = param.getValue();
					break;
				case "loged_in_user":
					LogedInUser = param.getValue();
					break;
				case "client_ip":
					clientIp = param.getValue();
					break;
				case "era_text":
					eraString = param.getValue();
					break;
				default:
					break;
				}
			}
		}

		if (GeneralOperations.isNullorEmpty(eraString) || GeneralOperations.isNullorEmpty(clientDateTime)
				|| GeneralOperations.isNullorEmpty(LogedInUser) || GeneralOperations.isNullorEmpty(clientIp)
				|| GeneralOperations.isNullorEmpty(practiceId)) {

			// resp.setStatus(ServiceResponseStatus.ERROR);
			// resp.setResponse("One of the required parameter is missing.");

			eraOperationResult = new ERAOperationResult();
			eraOperationResult.setMessage("One of the required parameter is missing.");
			eraOperationResult.setMessage_type("ERROR");

		} else {
			ERAOperations eraOperations = new ERAOperations(this.db, this.billingGeneral, this.generalDao,
					this.claimDao, this, practiceId, LogedInUser, clientDateTime, clientIp);

			eraOperationResult = eraOperations.ProcessEraText(eraString);

		}

		System.out.println(eraOperationResult.toString());

		return eraOperationResult;
	}

	@Override
	public ServiceResponse<ORMKeyValue> moveEraToOtherPractice(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		Long practiceIdToMove = (long) 0;
		Long eraId = (long) 0;
		String LogedInUser = "";
		String clientDateTime = "";
		String clientIp = "";

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "client_date_time":
					clientDateTime = param.getValue();
					break;
				case "loged_in_user":
					LogedInUser = param.getValue();
					break;
				case "client_ip":
					clientIp = param.getValue();
					break;
				case "era_id":
					eraId = Long.parseLong(param.getValue());
					break;
				case "practice_id_to_move":
					practiceIdToMove = Long.parseLong(param.getValue());
					break;
				default:
					break;
				}
			}
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		String strQuery = "";
		if (practiceIdToMove <= 0 || eraId <= 0 || GeneralOperations.isNullorEmpty(clientDateTime)
				|| GeneralOperations.isNullorEmpty(LogedInUser) || GeneralOperations.isNullorEmpty(clientIp)) {

			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("One of the required parameter is missing.");

		} else {

			strQuery = "select count(*) as scalar_value from claim_payment where isnull(deleted,0)<>1 and eob_era_id='"
					+ eraId + "' and eob_era_id_type='ERA' ";
			if (Integer.parseInt(db.getQuerySingleResult(strQuery)) > 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse(
						"Selected ERA Can't be Moved.<br>There is/are posted payment(s) against selected ERA.");
			} else {

				int eraResult = 0;
				int eraClaimResult = 0;
				int eraClaimServiceResult = 0;
				int eraAdjustmentResult = 0;
				int eraProviderAdjustmentResult = 0;
				String result = "";

				strQuery = " update era set practice_id='" + practiceIdToMove + "',modified_user='" + LogedInUser
						+ "',date_modified=getdate(),client_date_modified='" + clientDateTime + "',system_ip='"
						+ clientIp + "' where era_id='" + eraId + "' ";
				eraResult = this.db.ExecuteUpdateQuery(strQuery);

				strQuery = " update era_claim set practice_id='" + practiceIdToMove + "',modified_user='" + LogedInUser
						+ "',date_modified=getdate(),client_date_modified='" + clientDateTime + "',system_ip='"
						+ clientIp + "' where era_id='" + eraId + "' ";
				eraClaimResult = this.db.ExecuteUpdateQuery(strQuery);

				strQuery = " update era_claim_service set practice_id='" + practiceIdToMove + "',modified_user='"
						+ LogedInUser + "',date_modified=getdate(),client_date_modified='" + clientDateTime
						+ "',system_ip='" + clientIp + "' where era_id='" + eraId + "' ";
				eraClaimServiceResult = this.db.ExecuteUpdateQuery(strQuery);

				strQuery = " update era_adjustment set practice_id='" + practiceIdToMove + "',modified_user='"
						+ LogedInUser + "',date_modified=getdate(),client_date_modified='" + clientDateTime
						+ "',system_ip='" + clientIp + "' where era_id='" + eraId + "' ";
				eraAdjustmentResult = this.db.ExecuteUpdateQuery(strQuery);

				strQuery = " update era_provider_adjustment set practice_id='" + practiceIdToMove + "' where era_id='"
						+ eraId + "' ";
				eraProviderAdjustmentResult = this.db.ExecuteUpdateQuery(strQuery);

				result = eraResult + "~" + eraClaimResult + "~" + eraClaimServiceResult + "~" + eraAdjustmentResult
						+ "~" + eraProviderAdjustmentResult;

				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse(result);
				resp.setResult(eraId.toString());
			}
		}
		return resp;
	}

	@Override
	public ServiceResponse<SubmissionProccessedClaimInfo> generateHCFA(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		Long practiceId = searchCriteria.getPractice_id();
		String claimIds = "";
		String LogedInUser = "";
		String clientDateTime = "";
		String clientIp = "";
		String billToInsuranceType = "Primary";
		Boolean hcfaWithBG = false;

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "client_date_time":
					clientDateTime = param.getValue();
					break;
				case "loged_in_user":
					LogedInUser = param.getValue();
					break;
				case "client_ip":
					clientIp = param.getValue();
					break;
				case "claim_ids":// comma separated.
					claimIds = param.getValue();
					break;
				case "bill_to_insurance_type":
					billToInsuranceType = param.getValue();
					break;

				case "with_bg":
					if (param.getValue().equalsIgnoreCase("TRUE") || param.getValue().equalsIgnoreCase("1")) {
						hcfaWithBG = true;
					} else {
						hcfaWithBG = false;
					}
					break;
				default:
					break;
				}
			}
		}

		ServiceResponse<SubmissionProccessedClaimInfo> resp = new ServiceResponse<SubmissionProccessedClaimInfo>();

		if (GeneralOperations.isNullorEmpty(claimIds) || GeneralOperations.isNullorEmpty(clientDateTime)
				|| GeneralOperations.isNullorEmpty(LogedInUser) || GeneralOperations.isNullorEmpty(clientIp)) {

			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("One of the required parameter is missing.");

		} else {

			try {
				String DocumentPath = "";
				List<ORMDocumentPath> lst = (List<ORMDocumentPath>) generalDao
						.getDocPath(searchCriteria.getPractice_id().toString(), "HCFA");
				for (ORMDocumentPath orm : (List<ORMDocumentPath>) lst) {
					DocumentPath = orm.getUpload_path();
				}

				Hcfa hcfa = new Hcfa(billingGeneral);
				HcfaFileGenerationResponse hcfaFileGenerationResponse = hcfa.generateHcfa(claimIds, billToInsuranceType,
						practiceId, LogedInUser, clientDateTime, clientIp, DocumentPath, hcfaWithBG);

				if (GeneralOperations.isNullorEmpty(hcfaFileGenerationResponse.getError())) {
					resp.setStatus(ServiceResponseStatus.SUCCESS);
					resp.setResponse(hcfaFileGenerationResponse.getHcfa_link());
					resp.setResponse_list(hcfaFileGenerationResponse.getProcessed_claim());
				} else {
					resp.setStatus(ServiceResponseStatus.ERROR);
					resp.setResponse(hcfaFileGenerationResponse.getError());
				}

			} catch (Exception e) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse(e.getMessage());
			}
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> updateProcessedClaims(List<SubmissionProccessedClaimInfo> objProcessedClaim) {
		// TODO Auto-generated method stub

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (billingGeneral.updateProcessedClaims(objProcessedClaim) > 0) {

			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Claim status has been updated.");
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("Unable to Update claims, Please contact Administrator.");
		}
		return resp;
	}

	@Override
	public Boolean checkEOBPostedRecords(Long eob_id) {
		// TODO Auto-generated method stub
		String query = " select top 1 cp.claim_payments_id as scalar_value " + " from claim_payment cp "
				+ " inner join claim c on c.claim_id=cp.claim_id "
				+ " where ISNULL(c.deleted,0)<>1 and ISNULL(cp.deleted,0)<>1 " + " and cp.eob_era_id='" + eob_id
				+ "' and eob_era_id_type='EOB' ";

		String result = db.getQuerySingleResult(query);

		return GeneralOperations.isNotNullorEmpty(result); // Integer.parseInt(db.getQuerySingleResult(query)) > 0;
	}

	@Override
	public ORMEobDetailGet getEobById(Long eobId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("eob_id", eobId.toString(), String.class, ParameterMode.IN));
		List<ORMEobDetailGet> lst = db.getStoreProcedureData("spGetEobById", ORMEobDetailGet.class, lstParam);

		if (lst.isEmpty()) {
			return null;
		} else {
			return lst.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceResponse<ORMKeyValue> downloadClaimResponse(SearchCriteria searchCriteria) {

		String result = "";
		String rootUrl = "";
		String LogedInUser = "";
		String clientDateTime = "";
		String clientIp = "";

		String SFTPHOST = "";
		int SFTPPORT = 22;
		String SFTPUSER = "";
		String SFTPPASS = "";
		String remoteDirectory = ""; // for claim status
		String website_host = "";
		String fileSavePath = "";

		ChannelSftp channelSftp = null;
		JSch jsch = null;
		Session session = null;
		Channel channel = null;

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				System.out.println(
						"param:" + pram.getName() + "    Value:" + pram.getValue() + "    Option:" + pram.getOption());

				switch (pram.getName()) {
				case "root_url":
					rootUrl = pram.getValue();
					break;
				case "loged_in_user":
					LogedInUser = pram.getValue();
					break;
				case "client_date_time":
					clientDateTime = pram.getValue();
					break;
				case "client_ip":
					clientIp = pram.getValue();
					break;
				default:
					break;
				}

			}
		}

		List<ORMDocumentPath> lst = generalDao.getDocPath(searchCriteria.getPractice_id().toString(),
				"Claim_Status_Response_277");
		if (lst != null && lst.size() > 0) {
			fileSavePath = lst.get(0).getUpload_path();
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("File Download Path is not configured.");

			return resp;
		}

		List<ORMEdiFtpInfo> list_sftpInfo = this.billingGeneral
				.getEdiFtpInfo(searchCriteria.getPractice_id().toString());

		if (list_sftpInfo != null && list_sftpInfo.size() > 0) {

			SFTPHOST = list_sftpInfo.get(0).getFtp_host();
			SFTPPORT = Integer.parseInt(list_sftpInfo.get(0).getFtp_port());
			SFTPUSER = list_sftpInfo.get(0).getFtp_user();
			SFTPPASS = list_sftpInfo.get(0).getFtp_password();
			remoteDirectory = list_sftpInfo.get(0).getClaim_status_directory();
			website_host = list_sftpInfo.get(0).getApp_hosting_website();
		}

		if (GeneralOperations.isNullorEmpty(SFTPHOST) || GeneralOperations.isNullorEmpty(SFTPPORT)
				|| GeneralOperations.isNullorEmpty(SFTPUSER) || GeneralOperations.isNullorEmpty(SFTPPASS)
				|| GeneralOperations.isNullorEmpty(remoteDirectory)) {

			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("SFTP is not configured for claim status.");

			return resp;
		}

		if (!website_host.equalsIgnoreCase(rootUrl)) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("Operation cannot be performed from the host '" + rootUrl + "'");

			return resp;
		}

		try {

			jsch = new JSch();

			BufferedReader br;
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SFTPPASS);
			System.out.println("Connecting. SFTP");
			session.connect();

			channel = session.openChannel("sftp");
			System.out.println("Connecting. Channel");
			channel.connect();

			// Check to see if we are connected
			if (!channel.isConnected()) {
				System.out.println("Unable to connect to SFTP."); // return "Unable to connect to SFTP.";

				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("Unable to connect to SFTP.");

				return resp;

			}

			System.out.println("Connected to SFTP successfully");

			channelSftp = (ChannelSftp) channel;

			// String sss=channelSftp.pwd(); // Change directory to the remote folder
			if (remoteDirectory != null && remoteDirectory.equalsIgnoreCase("") == false) {
				System.out.println("Browsing Directory: " + remoteDirectory);
				channelSftp.cd(remoteDirectory);
			}

			Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.277");
			list.addAll(channelSftp.ls("*.277"));
			if (list.isEmpty()) {
				result = "No Response File found at directory '" + remoteDirectory + "'";
				System.out.println(result);
				System.out.println("Closing SFTP Connectiong: ");

				channelSftp.exit();
				session.disconnect(); // return result;

				resp.setStatus(ServiceResponseStatus.INFO);
				resp.setResponse("No Response File found at directory '" + remoteDirectory + "'");

				return resp;
			}

			int total_files = 0;

			for (ChannelSftp.LsEntry file : list) {

				// Process maximum of 20 files at a time. if (total_files == 20) { break; }

				int mid = file.getFilename().lastIndexOf(".");

				String ext = file.getFilename().substring(mid + 1, file.getFilename().length());

				if (!ext.toUpperCase().equals("277")) {
					continue;
				}

				total_files++;
				if (total_files > 50) {
					break;
				}

				BufferedInputStream bis = new BufferedInputStream(channelSftp.get(file.getFilename()));
				br = new BufferedReader(new InputStreamReader(bis, "UTF-8"));

				String MainString;
				StringBuilder SBulder = new StringBuilder();
				String temp;

				while ((temp = br.readLine()) != null) {
					SBulder.append(temp);
				}

				MainString = SBulder.toString();

				bis.close();
				br.close();

				System.out.println("Parsing 277 Response File: [" + total_files + "] " + file.getFilename());
				EdiParser ediParser = new EdiParser();
				List<ORM_277_ResponseSave> lstORMResponse = ediParser.parse277Response(MainString);

				String full_file_path = FileUtil.GetYearMonthDayWisePath(fileSavePath,
						searchCriteria.getPractice_id().toString(), "Claim_Status_Response_277") + "\\"
						+ file.getFilename();

				String[] splitedPath = full_file_path.split("\\\\");

				String file_path = splitedPath[splitedPath.length - 4] + "\\" + splitedPath[splitedPath.length - 3]
						+ "\\" + splitedPath[splitedPath.length - 2] + "\\" + file.getFilename();

				FileUtil.UploadStringDataToFile(MainString, full_file_path);

				if (lstORMResponse != null && lstORMResponse.size() > 0) {

					List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

					// Iterate in reverse order to maintain actual order of notes.
					for (int i = lstORMResponse.size() - 1; i >= 0; i--) {

						String serverDateTime = DateTimeUtil.getCurrentDateTime();

						lstORMResponse.get(i).setFile_path(file_path);
						lstORMResponse.get(i).setDate_created(serverDateTime);
						lstORMResponse.get(i).setDate_modified(serverDateTime);
						lstORMResponse.get(i).setClient_date_created(clientDateTime);
						lstORMResponse.get(i).setClient_date_modified(clientDateTime);
						lstORMResponse.get(i).setModified_user(LogedInUser);
						lstORMResponse.get(i).setCreated_user(LogedInUser);
						lstORMResponse.get(i).setSystem_ip(clientIp);
						lstORMResponse.get(i).setPractice_id(searchCriteria.getPractice_id().toString());
						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, lstORMResponse.get(i)));
					}

					if (db.AddUpdateEntityWrapper(lstEntityWrapper) > 0) {
						System.out.println("\nDeleting File From Gateway EDI: " + file.getFilename());
						channelSftp.rm(file.getFilename());
					} else {
						resp.setStatus(ServiceResponseStatus.ERROR);
						resp.setResponse("An Error Occured while saving record.");
						return resp;
					}
				}

			}

			channelSftp.exit();
			session.disconnect();

			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse(total_files + " claim Response File(s) downloaded and processed successfully.");

		} catch (JSchException | SftpException e) {
			result = e.toString();
			System.out.println("Error Occurrerd : \n");
			e.printStackTrace();
			// To change body of catch statement use File | Settings | File Templates.

			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("Error Occur while downloading batch response file:\n" + e.getMessage());
		} catch (Exception ex) {
			channelSftp.exit();
			session.disconnect();

			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("Error Occur while downloading batch response file:\n" + ex.getMessage());

		}

		// update patient_id
		String patQuery = " update ecs set ecs.patient_id=c.patient_id from edi_claim_status ecs "
				+ " inner join claim c on c.claim_id=ecs.claim_id " + " where ecs.practice_id='"
				+ searchCriteria.getPractice_id().toString() + "' and ecs.patient_id is null ";

		this.db.ExecuteUpdateQuery(patQuery);

		return resp;
	}

	// for Testing Purpose Only
	public ServiceResponse<ORMKeyValue> ProcessClaimResponseTestFiles(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		// clearAllData();

		int count = 0;
		// int success = 0;
		// int errors = 0;
		// int invalid = 0;
		// int unmapped = 0;

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		try {
			// String responseMsgType = "";

			// String response_message = "";
			String response_path = "D:\\Taha_277_files\\test";

			List<File> filesInFolder = Files.list(Paths.get(response_path))
					.filter(path -> path.toString().endsWith(".277")).map(Path::toFile).collect(Collectors.toList());

			if (filesInFolder.size() > 0) {

				for (File file : filesInFolder) {

					count++;
					try {

						System.out.println(
								"Processing File : [" + count + " of " + filesInFolder.size() + "] " + file.getName());
						FileInputStream fis = new FileInputStream(file.getPath());
						String data = IOUtils.toString(fis, "UTF-8");
						fis.close();

						EdiParser ediParser = new EdiParser();
						List<ORM_277_ResponseSave> lstORMResponse = ediParser.parse277Response(data);

						if (lstORMResponse != null && lstORMResponse.size() > 0) {
							List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

							for (int i = lstORMResponse.size() - 1; i >= 0; i--) {

								String serverDateTime = DateTimeUtil
										.getFormatedCurrentDate(DateFormatEnum.yyyy_MM_dd_HH_mm_ss_SSS);

								lstORMResponse.get(i).setFile_path(file.getName());
								lstORMResponse.get(i).setDate_created(serverDateTime);
								lstORMResponse.get(i).setDate_modified(serverDateTime);
								lstORMResponse.get(i).setClient_date_created(serverDateTime);
								lstORMResponse.get(i).setClient_date_modified(serverDateTime);
								lstORMResponse.get(i).setModified_user("bill@ihc");
								lstORMResponse.get(i).setCreated_user("bill@ihc");
								lstORMResponse.get(i).setSystem_ip("1.1.1.1");
								lstORMResponse.get(i).setPractice_id(searchCriteria.getPractice_id().toString());

								lstEntityWrapper.add(
										new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, lstORMResponse.get(i)));
							}

							if (db.AddUpdateEntityWrapper(lstEntityWrapper) > 0) {

								// update patient_id
								String patQuery = " update ecs set ecs.patient_id=c.patient_id from edi_claim_status ecs "
										+ " inner join claim c on c.claim_id=ecs.claim_id "
										+ " where ecs.practice_id='512' and ecs.patient_id is null ";

								this.db.ExecuteUpdateQuery(patQuery);

							} else {
								resp.setStatus(ServiceResponseStatus.ERROR);
								resp.setResponse("An Error Occured while saving record.");

								return resp;
							}

						}

					} catch (IOException ex) {
						ex.printStackTrace();

						resp.setStatus(ServiceResponseStatus.ERROR);
						resp.setResponse(ex.getMessage());
						return resp;
					}

				}

				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse(count + " Files Processed out of " + filesInFolder.size());

			} else

			{
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("There is no file in the response directory.");
			}

		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse(e.getMessage());
		}
		// System.out.println("Message Type:" + responseMsgType);
		return resp;
	}

	@Override
	public List<ORM_277_ResponseGet> getEdiClaimStatus(SearchCriteria searchCriteria) {

		// TODO Auto-generated method stub
		String criteria = "";

		// String strConditionalJoin = " and isnull(c.is_resubmitted,0)<>1 ";
		criteria += " and ecs.practice_id=" + searchCriteria.getPractice_id();

		List<SpParameters> lstParam = new ArrayList<>();

		String date_option = "";
		String patient_id = "";
		String claim_id = "";
		String provider_id = "";
		String location_id = "";
		String status = "";
		String from_date = "";
		String to_date = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName().toLowerCase()) {

				case "date_range_type":
					date_option = pram.getValue();
					break;
				case "date_from":
					from_date = pram.getValue();
					break;
				case "date_to":
					to_date = pram.getValue();
					break;
				case "patient_id":
					patient_id = pram.getValue();
					break;
				case "claim_id":
					claim_id = pram.getValue();
					break;
				case "provider_id":
					provider_id = pram.getValue();
					break;
				case "location_id":
					location_id = pram.getValue();
					break;
				case "status":
					status = pram.getValue();
					break;

				default:
					break;
				}
			}

			if (date_option.toLowerCase().equals("dos")) {
				criteria += " and convert(datetime,convert(varchar,c.dos,101)) between convert(datetime,'" + from_date
						+ "') and convert(datetime,'" + to_date + "') ";
			} else if (date_option.toLowerCase().equals("date_created")) {
				criteria += " and convert(datetime,convert(varchar,ecs.date_created,101)) between convert(datetime,'"
						+ from_date + "') and convert(datetime,'" + to_date + "') ";
			} else if (date_option.toLowerCase().equals("status_date")) {
				criteria += " and convert(datetime,convert(varchar,ecs.status_effective_date,101)) between convert(datetime,'"
						+ from_date + "') and convert(datetime,'" + to_date + "') ";
			}

			if (GeneralOperations.isNotNullorEmpty(patient_id)) {
				criteria += " and ecs.patient_id='" + patient_id + "'";
			}

			if (GeneralOperations.isNotNullorEmpty(claim_id)) {
				criteria += " and ecs.claim_id='" + claim_id + "'";
			}

			if (GeneralOperations.isNotNullorEmpty(provider_id)) {
				criteria += "  and c.billing_physician='" + provider_id + "'";
			}
			if (GeneralOperations.isNotNullorEmpty(location_id)) {
				criteria += "  and c.location_id='" + location_id + "'";
			}

			if (status.equalsIgnoreCase("accepted")) {
				criteria += " and isnull(ecs.status_category_code,'') in ('A0','A1','A2')";
			} else if (status.equalsIgnoreCase("rejected_all")) {
				criteria += " and isnull(ecs.status_category_code,'') not in ('A0','A1','A2')";
			} else if (status.equalsIgnoreCase("rejected_worked")) {
				criteria += " and isnull(ecs.status_category_code,'') not in ('A0','A1','A2') and isnull(ecs.worked,0)=1 ";
			} else if (status.equalsIgnoreCase("rejected_pending")) {
				criteria += " and isnull(ecs.status_category_code,'') not in ('A0','A1','A2') and isnull(ecs.worked,0)<>1 ";
			}
		}

		System.out.println(criteria);
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetEdiClaimStatus", ORM_277_ResponseGet.class, lstParam);
	}

	@Override
	public List<ORM_277ClaimStatusDetail> getEdiClaimStatusDetailByClaimId(Long claimId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claimId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetEdiClaimStatusDetail", ORM_277ClaimStatusDetail.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> markEdiClaimStatusAsWorked(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		Long Id = (long) 0;
		String LogedInUser = "";
		String clientDateTime = "";
		String clientIp = "";

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "id":
					Id = Long.parseLong(param.getValue());
					break;
				case "client_date_time":
					clientDateTime = param.getValue();
					break;
				case "loged_in_user":
					LogedInUser = param.getValue();
					break;
				case "client_ip":
					clientIp = param.getValue();
					break;
				default:
					break;
				}
			}
		}

		if (Id == 0 || GeneralOperations.isNullorEmpty(clientDateTime) || GeneralOperations.isNullorEmpty(LogedInUser)
				|| GeneralOperations.isNullorEmpty(clientIp)) {

			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("One of the required parameter is missing.");

		} else {

			String strQuery = " update edi_claim_status set worked=1,worked_by='" + LogedInUser
					+ "',date_worked=GETDATE(),date_modified=GETDATE(),modified_user='" + LogedInUser
					+ "',client_date_modified='" + clientDateTime + "',system_ip='" + clientIp + "' " + " where id='"
					+ Id + "'";

			if (this.db.ExecuteUpdateQuery(strQuery) > 0) {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
			} else {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			}
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> getEobIdByCriteria(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		// String checkDate = "";
		// String checkNumber = "";
		// String checkAmount = "";
		// String practiceId = "";
		String criteria = "";

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		criteria = " and practice_id='" + searchCriteria.getPractice_id().toString() + "' ";

		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "check_date":
					criteria += " and convert(date,check_date)=convert(date,'" + param.getValue() + "') ";
					break;
				case "check_number":
					criteria += " and isnull(check_number,'')='" + param.getValue() + "' ";
					break;
				case "check_amount":
					criteria += " and isnull(check_amount,0)=convert(money,'" + param.getValue() + "') ";
					break;
				default:
					break;
				}
			}
		}

		if (GeneralOperations.isNullorEmpty(criteria)) {

			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("One of the required parameter is missing.");

		} else {

			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
			String eobId = this.db.getStoreProcedureSingleResult("spGetEobIdByCriteria", lstParam);

			if (GeneralOperations.isNotNullorEmpty(eobId)) {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("EOB Found.");
				resp.setResult(eobId);
			} else {
				resp.setStatus(ServiceResponseStatus.NOT_FOUND);
				resp.setResponse("EOB Not Found.");
			}
		}

		return resp;
	}

}
