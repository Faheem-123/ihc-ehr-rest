package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.ParameterMode;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.MAPIDGenerator;
import com.ihc.ehr.model.ORMClaimDiagnosisSave_Pro;
import com.ihc.ehr.model.ORMClaimInsuranceSave;
import com.ihc.ehr.model.ORMClaimProceduresSave_Pro;
import com.ihc.ehr.model.ORMClaimSave_Pro;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMGetDirectMessages;
import com.ihc.ehr.model.ORMGetMessageCount;
import com.ihc.ehr.model.ORMGetMessageDetail;
import com.ihc.ehr.model.ORMGetMessageList;
import com.ihc.ehr.model.ORMHealthInformationCapture;
import com.ihc.ehr.model.ORMHealthInformationCaptureAttachments;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMLabOrderAttachmentComments;
import com.ihc.ehr.model.ORMLabOrderAttachmentSave;
import com.ihc.ehr.model.ORMMessageAttachment;
import com.ihc.ehr.model.ORMMessageDetailInsert;
import com.ihc.ehr.model.ORMMessageInsert;
import com.ihc.ehr.model.ORMPatientMessage;
import com.ihc.ehr.model.ORMPatientMessageDetail;
import com.ihc.ehr.model.ORMPatientMessageGet;
import com.ihc.ehr.model.ORM_HealthInformationCaptureAttachments;
import com.ihc.ehr.model.ORMgetpatmsgusersrecipient;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.WrapperPatientInfoCapture;
import com.ihc.ehr.model.WrapperPatientMessage;
import com.ihc.ehr.model.Wrapper_ClaimSave_Pro;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.model.Wrapper_MessageSave;
import com.ihc.ehr.model.ormToCcData;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.FileUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@Repository
public class MessagesDOAImpl implements MessagesDOA {

	@Autowired
	DBOperations db;

	@Autowired
	private GeneralDAOImpl generalDAOImpl;

	@Override
	public List<ORMGetMessageCount> getMessagesCount(String user_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetMessagesCount", ORMGetMessageCount.class, lstParam);
	}

	@Override
	public List<ORMGetMessageCount> getPatientMessagesCount(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPatientMessagesCount", ORMGetMessageCount.class, lstParam);
	}

	@Override
	public List<ORMGetMessageList> getMessagesListt(String user_id, String mes_type) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("msg_type", mes_type.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetMessagesList", ORMGetMessageList.class, lstParam);
	}

	@Override
	public List<ORMGetMessageList> getPatientMessagesListt(String patient_id, String mes_type) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("msg_type", mes_type.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPatientMessagesList", ORMGetMessageList.class, lstParam);
	}

	@Override
	public ORMGetMessageDetail getMessageDetail(String message_id, String user_id, String mes_type) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("message_id", message_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("msg_type", mes_type.toString(), String.class, ParameterMode.IN));

		//List<ORMGetMessageDetail> lstData = db.getStoreProcedureData("spGetMessageDetail", ORMGetMessageDetail.class,lstParam);
		//List<ORMGetMessageDetail> lstData = db.getStoreProcedureData("spGetMessageDetail_new", ORMGetMessageDetail.class,lstParam);
		List<ORMGetMessageDetail> lstData = db.getStoreProcedureData("spGetMessageDetail_new_two", ORMGetMessageDetail.class,lstParam);
		if (!lstData.isEmpty())
			return lstData.get(0);
		else
			return null;
	}

	@Override
	public ORMGetMessageDetail getPatientMessageDetail(String message_id, String patient_id, String mes_type) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("message_id", message_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("msg_type", mes_type.toString(), String.class, ParameterMode.IN));

		List<ORMGetMessageDetail> lstData = db.getStoreProcedureData("spGetPatientMessageDetail",
				ORMGetMessageDetail.class, lstParam);
		if (!lstData.isEmpty())
			return lstData.get(0);
		else
			return null;
	}

	@Override
	public ServiceResponse saveMessage(Wrapper_MessageSave Wrapper_MessageSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ORMMessageInsert messageSave_Pro = Wrapper_MessageSave.getMessageSave_Pro();
		List<ORMMessageDetailInsert> lstMessageDetailInsert = Wrapper_MessageSave.getLstMessageDetails();

		ServiceResponse resp = new ServiceResponse();
		if (messageSave_Pro != null) {
			messageSave_Pro.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (messageSave_Pro.getMessage_id() == null || messageSave_Pro.getMessage_id() == "") {
				messageSave_Pro.setMessage_id(
						db.IDGenerator("message", Long.parseLong(messageSave_Pro.getPractice_id())).toString());
				messageSave_Pro.setDate_created(DateTimeUtil.getCurrentDateTime());
				messageSave_Pro.setDate_modified(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(messageSave_Pro, Operation.ADD);
			} else {
				messageSave_Pro.setDate_modified(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(messageSave_Pro, Operation.EDIT);
			}
		}
		if (!messageSave_Pro.getIs_draft()) {
			for (ORMMessageDetailInsert ormobjDetails : lstMessageDetailInsert) {
				ormobjDetails.setMessage_detail_id(
						db.IDGenerator("message_detail", Long.parseLong(messageSave_Pro.getPractice_id())).toString());
				ormobjDetails.setMessages_id(messageSave_Pro.getMessage_id());
				ormobjDetails.setDate_created(DateTimeUtil.getCurrentDateTime());
				ormobjDetails.setDate_modified(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(ormobjDetails, Operation.ADD);
			}
		} else {

		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(messageSave_Pro.getMessage_id().toString());
		}
		return resp;
	}

	// @Override
	// public int deleteSelectedMessage(ORMDeleteRecord objDelete) {
	// return db.deleteRecord(objDelete);
	// }
	@Override
	public long deleteSelectedMessage(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String column_id = "";
		String modified_user = "";
		String client_date_modified = "";
		String client_ip = "";
		String msg_Type = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("column_id")) {
					column_id = pram.getValue();
				} else if (pram.getName().equals("modified_user")) {
					modified_user = pram.getValue();
				} else if (pram.getName().equals("client_date_modified")) {
					client_date_modified = pram.getValue();
				} else if (pram.getName().equals("client_ip")) {
					client_ip = pram.getValue();
				} else if (pram.getName().equals("msg_Type")) {
					msg_Type = pram.getValue();
				}
			}
		}
		String Query = "";
		if (msg_Type.equals("deleted")) {
			Query = " update message_detail set date_modified = '" + client_date_modified
					+ "', deleted= '1', mail_status = 'deleted',modified_user='" + modified_user
					+ "',client_date_modified = '" + client_date_modified + "' where message_detail_id in (" + column_id
					+ ")";
		} else if (msg_Type.equals("inbox")) {
			Query = " update message_detail set mail_status = 'deleted',modified_user='" + modified_user
					+ "',client_date_modified = '" + client_date_modified + "' where message_detail_id in (" + column_id
					+ ")";
		}
		// else if (msg_Type.equals("archive")) {
		// Query = " update message_detail set date_modified =
		// '"+client_date_modified+"', deleted= '1', mail_status =
		// 'deleted',modified_user='"+modified_user+"',client_date_modified =
		// '"+client_date_modified+"' where message_detail_id in ("+column_id+")";
		// }

		return db.ExecuteUpdateQuery(Query);
	}

	@Override
	public List<ormToCcData> getToCcData(String message_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("message_id", message_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetToCcData", ormToCcData.class, lstParam);
	}

	@Override
	public List<ORMPatientMessageGet> searchPatientMSG(SearchCriteria searchCriteria) {

		String criteria = " and m.practice_id = '" + searchCriteria.getPractice_id().toString() + "' ";
		String criteria2 = " and m.practice_id = '" + searchCriteria.getPractice_id().toString() + "' ";

		// and isnull(m.sender_id,0) <> '"+GeneralOptions.LoginUserID+"' and up.user_id
		// = '"+GeneralOptions.LoginUserID+"' ";"
		// and isnull(m.sender_id,0) = '"+GeneralOptions.LoginUserID+"' and up.user_id =
		// '"+GeneralOptions.LoginUserID+"' ";

		String dtFrom = "";
		String dtTo = "";
		String datetype = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "sender_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria = criteria + " and isnull(m.sender_id,0) <> '" + pram.getValue()
								+ "' and up.user_id = '" + pram.getValue() + "' ";
						criteria2 = criteria2 + " and isnull(m.sender_id,0) = '" + pram.getValue()
								+ "' and up.user_id = '" + pram.getValue() + "' ";
						break;
					}
				case "PatientID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria = criteria + " and isnull(from_id,'') ='" + pram.getValue() + "' ";
						criteria2 = criteria2 + " and isnull(to_id,'') ='" + pram.getValue() + "' ";
						break;
					}
				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria = criteria + " and isnull(to_id,'') ='" + pram.getValue() + "' ";
						criteria2 = criteria2 + " and isnull(from_id,'') ='" + pram.getValue() + "' ";
						break;
					}
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtFrom = pram.getValue();
						break;
					}
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtTo = pram.getValue();
						break;
					}
				default:
					break;
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria = criteria + " and md.recieve_date between CONVERT(datetime,'" + dtFrom
					+ "') and CONVERT(datetime,'" + dtTo + "') ";
			criteria2 = criteria2 + " and md.recieve_date between CONVERT(datetime,'" + dtFrom
					+ "') and CONVERT(datetime,'" + dtTo + "') ";

		} else if (!dtFrom.isEmpty()) {
			criteria = criteria + " and md.recieve_date >= CONVERT(datetime,'" + dtFrom + "')";
			criteria2 = criteria2 + " and md.recieve_date >= CONVERT(datetime,'" + dtFrom + "')";
		} else if (!dtTo.isEmpty()) {
			criteria = criteria + " and md.recieve_date <= CONVERT(datetime,'" + dtTo + "')";
			criteria2 = criteria2 + " and md.recieve_date <= CONVERT(datetime,'" + dtTo + "')";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("criteria2", criteria2, String.class, ParameterMode.IN));

		List<ORMPatientMessageGet> lst = db.getStoreProcedureData("spGetMessageSearch", ORMPatientMessageGet.class,
				lstParam);

		return lst;
	}

	// List<ORMPatientMessageGet> onGetPatientMessage(String user_id, String
	// practice_id);
	@Override
	public List<ORMPatientMessageGet> onGetPatientMessage(String user_id, String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPatientMessages", ORMPatientMessageGet.class, lstParam);
	}

	/*
	 * @Override public ServiceResponse<ORMKeyValue>
	 * updateToReaded(List<ORMKeyValue> patientMessageStatus) { try { String
	 * client_date_modified =""; String modified_user =""; String message_detail_id
	 * =""; String strQuery = ""; String result = ""; if (patientMessageStatus !=
	 * null && !patientMessageStatus.isEmpty()) { for (ORMKeyValue pram :
	 * patientMessageStatus) { if (pram.getKey().equals("client_date_modified")) {
	 * client_date_modified = pram.getValue(); } else if
	 * (pram.getKey().equals("modified_user")) { modified_user = pram.getValue(); }
	 * else if (pram.getKey().equals("message_detail_id")) { message_detail_id =
	 * pram.getValue(); } } } strQuery =
	 * " update patient_message_detail set readed = 1,client_date_modified= '"+
	 * client_date_modified +"' ,date_modified= getdate() ,modified_user = '"+
	 * modified_user +"'  where message_detail_id = '"+ message_detail_id +"' ";
	 * return db.ExecuteUpdateQuery(strQuery); } catch (Exception e) { return 0; } }
	 */
	@Override
	public int updateToReaded(List<ORMKeyValue> patientMessageStatus) {
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		List<SpParameters> lstParam = new ArrayList<>();
		String client_date_modified = "";
		String modified_user = "";
		String message_detail_id = "";
		String callingFrom = "";
		String strQuery = "";
		if (patientMessageStatus != null && !patientMessageStatus.isEmpty()) {
			for (ORMKeyValue pram : patientMessageStatus) {
				if (pram.getKey().equals("client_date_modified")) {
					client_date_modified = pram.getValue();
				} else if (pram.getKey().equals("modified_user")) {
					modified_user = pram.getValue();
				} else if (pram.getKey().equals("message_detail_id")) {
					message_detail_id = pram.getValue();
				} else if (pram.getKey().equals("callingFrom")) {
					callingFrom = pram.getValue();
				}
			}
		}

		if (callingFrom.equals("Patientmessage")) {// for patient messages
			strQuery = " update patient_message_detail set readed = 1,client_date_modified= '" + client_date_modified
					+ "' ,date_modified= getdate() ,modified_user = '" + modified_user
					+ "'  where message_detail_id = '" + message_detail_id + "' ";
		} else if (callingFrom.equals("message")) { // for messages
			strQuery = " update message_detail set readed = 1,client_date_modified= '" + client_date_modified
					+ "' ,date_modified= getdate() ,modified_user = '" + modified_user
					+ "'  where message_detail_id = '" + message_detail_id + "' ";
		}
		return db.ExecuteUpdateQuery(strQuery);
	}

	@Override
	public List<ORMMessageAttachment> getMessagesAttachments(String message_id, String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("message_id", message_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetMessageAttachments", ORMMessageAttachment.class, lstParam);
	}

	@Override
	public List<ORMMessageAttachment> getMessagesLinks(String message_id, String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("message_id", message_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetMessageLinks", ORMMessageAttachment.class, lstParam);
	}

	// ServiceResponse SaveCorrespondence(Wrapper_AddToCorrespondence
	// Wrapper_AddToCorrespondence);
	@Override
	public ServiceResponse SaveCorrespondence(
			com.ihc.ehr.model.Wrapper_AddToCorrespondence Wrapper_AddToCorrespondence) {
		int result = 0;
		ORMHealthInformationCapture healthInfoCapture = Wrapper_AddToCorrespondence.getLstHealthInfoCapture();
		List<ORMHealthInformationCaptureAttachments> lstHealthInfoCaptureAttachments = Wrapper_AddToCorrespondence
				.getLstHealthInfoAttachments();
		List<ORMKeyValue> lstOther = Wrapper_AddToCorrespondence.getLstOther();
		ServiceResponse resp = new ServiceResponse();
		if (healthInfoCapture != null) {
			healthInfoCapture.setDate_modified(DateTimeUtil.getCurrentDateTime());

			if (healthInfoCapture.getHealth_info_id() == null || healthInfoCapture.getHealth_info_id() == "") {
				healthInfoCapture.setHealth_info_id(
						db.IDGenerator("health_information_capture", Long.parseLong(healthInfoCapture.getPractice_id()))
								.toString());
				healthInfoCapture.setDate_created(DateTimeUtil.getCurrentDateTime());
				healthInfoCapture.setDate_modified(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(healthInfoCapture, Operation.ADD);
			} else {
				healthInfoCapture.setDate_modified(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(healthInfoCapture, Operation.EDIT);
			}
		}
		for (ORMHealthInformationCaptureAttachments ormobjAttach : lstHealthInfoCaptureAttachments) {
			ormobjAttach.setAttachment_id(
					db.IDGenerator("health_info_capture_attachment", Long.parseLong(healthInfoCapture.getPractice_id()))
							.toString());
			ormobjAttach.setHealth_info_id(healthInfoCapture.getHealth_info_id());
			ormobjAttach.setDate_created(DateTimeUtil.getCurrentDateTime());
			ormobjAttach.setModified_date(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(ormobjAttach, Operation.ADD);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(healthInfoCapture.getHealth_info_id().toString());
		}
		return resp;
	}

	@Override
	public List<ORMGetDirectMessages> onGetDirectMessages(String practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetDirectMessages", ORMGetDirectMessages.class, lstParam);
	}

	@Override
	public String importCCDA(SearchCriteria searchCriteria) {
		String strPath = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("strPath")) {
					strPath = pram.getValue();
				}
			}
		}
		StringBuilder MainStr = new StringBuilder();
		if (GeneralOperations.isNullorEmpty(strPath)) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(strPath));
				String line = null;
				while ((line = reader.readLine()) != null) {
					MainStr.append(line);
				}
				reader.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return "";
			}
		}
		return MainStr.toString();
	}

	@Override
	public String importCCR(SearchCriteria searchCriteria) {
		String strPath = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("strPath")) {
					strPath = pram.getValue();
				}
			}
		}
		StringBuilder MainStr = new StringBuilder();
		if (GeneralOperations.isNullorEmpty(strPath)) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(strPath));
				String line = null;
				while ((line = reader.readLine()) != null) {
					MainStr.append(line);
				}
				reader.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return "";
			}
		}
		return MainStr.toString();
	}

	//////////

	// ServiceResponse<ORMKeyValue> saveupdatePatientMessages(WrapperPatientMessage
	// objPatientMessageWrapper, MultipartFile attachfile);

	// public ServiceResponse<ORMKeyValue>
	// AddEditNewHealthInformation(WrapperPatientInfoCapture
	// objInfoCaptureWrapper,MultipartFile[] attachfile) {

	@Override
	public ServiceResponse<ORMKeyValue> saveupdatePatientMessages(WrapperPatientMessage objPatientMessageWrapper,
			MultipartFile attachfile) {
		int result = 0;
		ORMPatientMessage objPatMsgs = objPatientMessageWrapper.getObjPatMsg();
		List<ORMPatientMessageDetail> lstPatMsgDetails = objPatientMessageWrapper.getObjPatMsgDetail();
		ORMMessageAttachment objMessageAttach = objPatientMessageWrapper.getObjMsgAttach();
		ServiceResponse resp = new ServiceResponse();
		Long generatedID;
		String URL = "";
		String generatedAttachedID = "";
		String msgAttachedID = "";
		String fileName = "";
		String directory = "";
		String originalFilename = "";

		if (objPatMsgs != null) {
			objPatMsgs.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (objPatMsgs.getMessage_id() == null || objPatMsgs.getMessage_id() == "") {
				objPatMsgs.setMessage_id(
						db.IDGenerator("patient_message", Long.parseLong(objPatMsgs.getPractice_id())).toString());
				objPatMsgs.setDate_created(DateTimeUtil.getCurrentDateTime());
				objPatMsgs.setDate_modified(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(objPatMsgs, Operation.ADD);
			} else {
				objPatMsgs.setDate_modified(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(objPatMsgs, Operation.EDIT);
			}
		}
		if (lstPatMsgDetails != null) {
			for (ORMPatientMessageDetail objAttach : lstPatMsgDetails) {
				// objAttachSave = new ORMPatientMessageDetail();

				generatedID = db.IDGenerator("patient_message_detail", Long.parseLong(objPatMsgs.getPractice_id()));

				objAttach.setMessage_detail_id(generatedID.toString());
				objAttach.setMessages_id(objPatMsgs.getMessage_id());
				objAttach.setDate_created(DateTimeUtil.getCurrentDateTime());
				objAttach.setDate_modified(DateTimeUtil.getCurrentDateTime());
				objAttach.setRecieve_date(DateTimeUtil.getCurrentDateTime());

				result = db.SaveEntity(objAttach, Operation.ADD);

			}
		}

		// ORMMessageAttachment objSaveAttach;
		if (attachfile != null) {
			generatedID = db.IDGenerator("message_attachment", Long.parseLong(objPatMsgs.getPractice_id()));
			// objSaveAttach = new ORMMessageAttachment();
			objMessageAttach.setMessage_attachment_id(generatedID.toString());
			// objMessageAttach.setMessage_attachment_id(generatedAttachedID);
			objMessageAttach.setMessage_id(msgAttachedID);
			objMessageAttach.setAttach_type("file");// only 1 attachment for patient.

			ORMDocumentPath objPath = generalDAOImpl
					.getDocumentPathByCategory(Long.parseLong(objPatMsgs.getPractice_id()), "Messages");
			if (objPath != null) {
				directory = objPath.getUpload_path();
			}
			// directory = directory + "\\" + objAttach.getPractice_id() + "\\Messages\\" ;

			directory = FileUtil.GetYearMonthDayWisePath(directory, objPatMsgs.getPractice_id(), "Messages");
			originalFilename = objMessageAttach.getOriginal_file_name();
			try {
				fileName = generatedID + GeneralOperations.GetDatetimeFileName() + "."
						+ FilenameUtils.getExtension(originalFilename);
				File destinationFile = new File(GeneralOperations.CheckPath(directory), fileName);
				attachfile.transferTo(destinationFile);
				objMessageAttach.setLink(DateTimeUtil.getCurrentYear() + "\\" + DateTimeUtil.getCurrentMonth() + "\\"
						+ DateTimeUtil.getCurrentDay() + "\\" + fileName);

				result = db.SaveEntity(objMessageAttach, Operation.ADD);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(objPatMsgs.getMessage_id().toString());
		}

		/*
		 * try { if (!"".equals(objPatMsgs.getMessage_id())) { generatedID =
		 * Long.parseLong(objPatMsgs.getMessage_id());
		 * objPatMsgs.setDate_modified(DateTimeUtil.getCurrentDateTime());
		 * //hibernate.update(objPatMsgs); result = db.SaveEntity(objPatMsgs,
		 * Operation.EDIT); } else { generatedID = db.IDGenerator("patient_message",
		 * Long.parseLong(objPatMsgs.getPractice_id()));
		 * objPatMsgs.setMessage_id(generatedID.toString()); msgAttachedID =
		 * generatedID.toString();
		 * objPatMsgs.setDate_created(DateTimeUtil.getCurrentDateTime());
		 * objPatMsgs.setDate_modified(DateTimeUtil.getCurrentDateTime());
		 * //hibernate.add(objPatMsgs); result = db.SaveEntity(objPatMsgs,
		 * Operation.ADD); }
		 * 
		 * //ORMPatientMessageDetail objAttachSave;
		 * 
		 * if(lstPatMsgDetails != null) { for (ORMPatientMessageDetail objAttach :
		 * lstPatMsgDetails) { //objAttachSave = new ORMPatientMessageDetail();
		 * 
		 * if (!"".equals(objAttach.getMessage_detail_id())) { generatedID =
		 * Long.parseLong(objPatMsgs.getMessage_id());
		 * objAttach.setDate_modified(DateTimeUtil.getCurrentDateTime());
		 * objAttach.setMessages_id(objPatMsgs.getMessage_id()); result =
		 * db.SaveEntity(objAttach, Operation.EDIT); }else { generatedID =
		 * db.IDGenerator("patient_message_detail",
		 * Long.parseLong(objPatMsgs.getPractice_id()));
		 * 
		 * objAttach.setMessage_detail_id(generatedID.toString());
		 * objAttach.setMessages_id(objPatMsgs.getMessage_id());
		 * objAttach.setDate_created(DateTimeUtil.getCurrentDateTime());
		 * objAttach.setDate_modified(DateTimeUtil.getCurrentDateTime());
		 * objAttach.setRecieve_date(DateTimeUtil.getCurrentDateTime());
		 * 
		 * result = db.SaveEntity(objAttach, Operation.ADD); }
		 * 
		 * 
		 * 
		 * } }
		 * 
		 * //ORMMessageAttachment objSaveAttach; if(attachfile != null) { generatedID =
		 * db.IDGenerator("message_attachment",
		 * Long.parseLong(objPatMsgs.getPractice_id())); //objSaveAttach = new
		 * ORMMessageAttachment();
		 * objMessageAttach.setMessage_attachment_id(generatedID.toString());
		 * //objMessageAttach.setMessage_attachment_id(generatedAttachedID);
		 * objMessageAttach.setMessage_id(msgAttachedID);
		 * objMessageAttach.setAttach_type("file");//only 1 attachment for patient.
		 * 
		 * ORMDocumentPath objPath =
		 * generalDAOImpl.getDocumentPathByCategory(Long.parseLong(objPatMsgs.
		 * getPractice_id()), "Messages"); if (objPath != null) { directory =
		 * objPath.getUpload_path(); } // directory = directory +
		 * "\\" + objAttach.getPractice_id() + "\\Messages\\" ;
		 * 
		 * directory = FileUtil.GetYearMonthDayWisePath(directory,
		 * objPatMsgs.getPractice_id(),"Messages"); originalFilename =
		 * objMessageAttach.getOriginal_file_name(); try { fileName = generatedID +
		 * GeneralOperations.GetDatetimeFileName() + "."+
		 * FilenameUtils.getExtension(originalFilename); File destinationFile = new
		 * File(GeneralOperations.CheckPath(directory), fileName);
		 * attachfile.transferTo(destinationFile);
		 * objMessageAttach.setLink(DateTimeUtil.getCurrentYear() +
		 * "\\" + DateTimeUtil.getCurrentMonth() + "\\" + DateTimeUtil.getCurrentDay() +
		 * "\\" + fileName);
		 * 
		 * result = db.SaveEntity(objMessageAttach, Operation.ADD); } catch (Exception
		 * e) { // TODO: handle exception }
		 * 
		 * }
		 * 
		 * } catch (Exception e) { // TODO: handle exception }
		 */

		return resp;
	}

	/*
	 * @Override public ServiceResponse<ORMKeyValue>
	 * saveupdatePatientMessages(WrapperPatientMessage objPatientMessageWrapper,
	 * MultipartFile attachfile) { int result = 0; ORMPatientMessage objPatientMsg =
	 * objPatientMessageWrapper.getObjPatMsg(); ORMPatientMessageDetail
	 * objPatientMsgDetail = objPatientMessageWrapper.getObjPatMsgDetail();
	 * 
	 * ServiceResponse resp = new ServiceResponse(); List<Wrapper_Entity>
	 * lstEntityWrapper = new ArrayList<>();
	 * 
	 * Long generatedID; String msgAttachedID = ""; String directory = ""; String
	 * originalFilename = ""; String attachID = "";
	 * 
	 * if (!"".equals(objPatientMsg.getMessage_id())) { generatedID =
	 * Long.parseLong(objPatientMsg.getMessage_id());
	 * objPatientMsg.setDate_modified(DateTimeUtil.getCurrentDateTime()); result =
	 * db.SaveEntity(objPatientMsg, Operation.EDIT); } else{ generatedID =
	 * db.IDGenerator("patient_message",
	 * Long.parseLong(objPatientMsg.getMessage_id()));
	 * objPatientMsg.setMessage_id(generatedID.toString()); msgAttachedID =
	 * generatedID.toString();
	 * objPatientMsg.setDate_created(DateTimeUtil.getCurrentDateTime());
	 * objPatientMsg.setDate_modified(DateTimeUtil.getCurrentDateTime()); result =
	 * db.SaveEntity(objPatientMsg, Operation.ADD); }
	 * 
	 * 
	 * if (!"".equals(objPatientMsgDetail.getMessage_detail_id())) { generatedID =
	 * Long.parseLong(objPatientMsgDetail.getMessage_detail_id());
	 * objPatientMsgDetail.setDate_modified(DateTimeUtil.getCurrentDateTime());
	 * result = db.SaveEntity(objPatientMsgDetail, Operation.EDIT); }else {
	 * generatedID = db.IDGenerator("patient_message_detail",
	 * Long.parseLong(objPatientMsgDetail.getMessage_detail_id()));
	 * objPatientMsgDetail.setMessage_detail_id(generatedID.toString()); attachID =
	 * generatedID.toString(); objPatientMsgDetail.setMessages_id(msgAttachedID);
	 * objPatientMsgDetail.setDate_created(DateTimeUtil.getCurrentDateTime());
	 * objPatientMsgDetail.setDate_modified(DateTimeUtil.getCurrentDateTime());
	 * result = db.SaveEntity(objPatientMsgDetail, Operation.ADD); }
	 * 
	 * String fileName = ""; if(attachfile!=null) {
	 * if(!"".equals(attachfile.getOriginalFilename())) { ORMDocumentPath objPath =
	 * generalDAOImpl.getDocumentPathByCategory(Long.parseLong(objPatientMsg.
	 * getPractice_id()), "Messages"); if (objPath != null) { directory =
	 * objPath.getUpload_path(); } directory =
	 * FileUtil.GetYearMonthDayWisePath(directory, objPatientMsg.getPractice_id(),
	 * "Messages"); originalFilename = attachfile.getOriginalFilename(); try {
	 * fileName = attachID + GeneralOperations.GetDatetimeFileName() + "." +
	 * FilenameUtils.getExtension(originalFilename); File destinationFile = new
	 * File(GeneralOperations.CheckPath(directory), fileName);
	 * attachfile.transferTo(destinationFile);
	 * //attachfile.setLink(DateTimeUtil.getCurrentYear() +
	 * "\\" + DateTimeUtil.getCurrentMonth() + "
	 * \\"+ DateTimeUtil.getCurrentDay() + "\\" + fileName); } catch (Exception e) {
	 * System.out.println("Picture Upload Error:" + e.getMessage()); } result =
	 * db.SaveEntity(attachfile, Operation.EDIT); //result =
	 * db.SaveEntity(attachfile, Operation.ADD); }else { ORMDocumentPath objPath =
	 * generalDAOImpl.getDocumentPathByCategory(Long.parseLong(objPatientMsg.
	 * getPractice_id()), "Messages"); if (objPath != null) { directory =
	 * objPath.getUpload_path(); } directory =
	 * FileUtil.GetYearMonthDayWisePath(directory, objPatientMsg.getPractice_id(),
	 * "Messages");
	 * 
	 * originalFilename = attachfile.getOriginalFilename(); try { fileName =
	 * attachID + GeneralOperations.GetDatetimeFileName() + "." +
	 * FilenameUtils.getExtension(originalFilename); File destinationFile = new
	 * File(GeneralOperations.CheckPath(directory), fileName);
	 * attachfile.transferTo(destinationFile);
	 * //objAttachSave.setLink(DateTimeUtil.getCurrentYear() +
	 * "\\" + DateTimeUtil.getCurrentMonth() + "
	 * \\"+ DateTimeUtil.getCurrentDay() + "\\" + fileName);
	 * 
	 * } catch (Exception e) { System.out.println("Picture Upload Error:" +
	 * e.getMessage()); }
	 * 
	 * result = db.SaveEntity(attachfile, Operation.ADD); } } return resp; }
	 */
	@Override
	public long deletePatMsg(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String column_id = "";
		String modified_user = "";
		String client_date_modified = "";
		String client_ip = "";
		String msg_Type = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("column_id")) {
					column_id = pram.getValue();
				} else if (pram.getName().equals("modified_user")) {
					modified_user = pram.getValue();
				} else if (pram.getName().equals("client_date_modified")) {
					client_date_modified = pram.getValue();
				} else if (pram.getName().equals("client_ip")) {
					client_ip = pram.getValue();
				} else if (pram.getName().equals("msg_Type")) {
					msg_Type = pram.getValue();
				}
			}
		}
		String Query = "";
		if (msg_Type.equals("delete")) {
			Query = "update patient_message set deleted = 1,client_date_modified = '" + client_date_modified
					+ "',date_modified= getdate() ,modified_user = '" + modified_user + "'  where message_id = '"
					+ column_id + "' ";
		} else if (msg_Type.equals("archive")) {
			Query = "update patient_message_detail set mail_status = 'archive',client_date_modified = '"
					+ client_date_modified + "',date_modified= getdate() ,modified_user = '" + modified_user
					+ "'  where message_detail_id = '" + column_id + "' ";
		}

		return db.ExecuteUpdateQuery(Query);
	}

	@Override
	public List<ORMgetpatmsgusersrecipient> getPatMsgUsersRecipient(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatmsgUsersRecipient", ORMgetpatmsgusersrecipient.class, lstParam);
	}

	@Override
	public long archiveSelectedMessage(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String column_id = "";
		String modified_user = "";
		String client_date_modified = "";
		String client_ip = "";
		String msg_Type = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("column_id")) {
					column_id = pram.getValue();
				} else if (pram.getName().equals("modified_user")) {
					modified_user = pram.getValue();
				} else if (pram.getName().equals("client_date_modified")) {
					client_date_modified = pram.getValue();
				} else if (pram.getName().equals("client_ip")) {
					client_ip = pram.getValue();
				} else if (pram.getName().equals("msg_Type")) {
					msg_Type = pram.getValue();
				}
			}
		}
		String Query = "";
		Query = " update message_detail set mail_status = 'archive',modified_user='" + modified_user
				+ "',client_date_modified = '" + client_date_modified + "' where message_detail_id in (" + column_id
				+ ")";

		return db.ExecuteUpdateQuery(Query);
	}

}