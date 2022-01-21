package com.ihc.ehr.dao;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ORMAmemdmentRequest;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMGetMessageCount;
import com.ihc.ehr.model.ORMGetMessageList;
import com.ihc.ehr.model.ORMGetPHRLabOrder;
import com.ihc.ehr.model.ORMGetPHRLabOrderAttachment;
import com.ihc.ehr.model.ORMGetPHRLoginUserInfo;
import com.ihc.ehr.model.ORMGetPHRLoginUserPatient;
import com.ihc.ehr.model.ORMGetphrLabOrderDirector;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMLabOrder;
import com.ihc.ehr.model.ORMLabOrderAttachment;
import com.ihc.ehr.model.ORMLabResult;
import com.ihc.ehr.model.ORMLabResultReportHeader;
import com.ihc.ehr.model.ORMLabResultTest;
import com.ihc.ehr.model.ORMLogin_log;
import com.ihc.ehr.model.ORMMessageAttachment;
import com.ihc.ehr.model.ORMMessageAttachmentThreeColumn;
import com.ihc.ehr.model.ORMMessageAttachmentsendfromPhr;
import com.ihc.ehr.model.ORMPHRAuditLog;
import com.ihc.ehr.model.ORMPHRLoginuser;
import com.ihc.ehr.model.ORMPHRPatMessages;
import com.ihc.ehr.model.ORMPatientMessage;
import com.ihc.ehr.model.ORMPatientMessageDetail;
import com.ihc.ehr.model.ORMProviderList;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORM_obgyn_main_info;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.ValidatedUser;
import com.ihc.ehr.model.WrapperPhrMsg;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.FileUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@Repository
public class PhrDOAImpl implements PhrDOA{

	@Autowired
	DBOperations db;
	

	@Override
	public ORMGetPHRLoginUserInfo getPHRLogedInUserDetail(Long user_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));
		List<ORMGetPHRLoginUserInfo> lst= db.getStoreProcedureData("spGetPHRLogedInUserInfo", ORMGetPHRLoginUserInfo.class, lstParam);
		
		if(lst!=null && lst.size()>0) {
			return lst.get(0);
		}
		else {
			return null;
		}
	}
	@Override
	public List<ORMGetPHRLoginUserPatient> getPHRLogedInUserPatients(Long user_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPHRLogedInUserPatient", ORMGetPHRLoginUserPatient.class, lstParam);
	}

	
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
	public List<ORMGetMessageList> getMessagesList(String user_id, String mes_type) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("msg_type", mes_type.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetMessagesList", ORMGetMessageList.class, lstParam);
	}
	@Override
	public List<ORMProviderList> getProviderList(Long practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("proGetProvider", ORMProviderList.class, lstParam);
	}
	
	public int SaveAmendmentRequest(ORMAmemdmentRequest obj) {
		int result = 0;
		Long GeneratedID;
        try {
        	obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
            if (obj.getRequest_id()== null || obj.getRequest_id().equals("")) {
            	GeneratedID = db.IDGenerator("amendment_request", Long.parseLong(obj.getPractice_id()));
                obj.setRequest_id(GeneratedID.toString());
                obj.setDate_created(DateTimeUtil.getCurrentDateTime());
                result = db.SaveEntity(obj, Operation.ADD);
            } else {
                GeneratedID = Long.parseLong(obj.getRequest_id());
                result = db.SaveEntity(obj, Operation.EDIT);
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
        return result;
	}
	@Override
	public ServiceResponse<ORMKeyValue> savePatiehtPhrMsg(WrapperPhrMsg objPhrMsgWrapper, MultipartFile[] attachfile) 
	{
		System.out.println("*************************************PHR MSG FUNCTION******************************************************");
		int result = 0;
		result = 1; 
		ORMPatientMessage patientMessage = objPhrMsgWrapper.getObjPatientMessage();
		List<ORMPatientMessageDetail> lstpatientmessagedetail= objPhrMsgWrapper.getObjPatientMessageDetails();
		List<ORMMessageAttachmentsendfromPhr> lstpatientmessageAttachments= objPhrMsgWrapper.getObjMessageAttachmentPhr();
		ORMAmemdmentRequest patmsgAmemdment = objPhrMsgWrapper.getObjAmemdmentReq();
		
		String path = objPhrMsgWrapper.getPath();

		ServiceResponse resp = new ServiceResponse();
		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		Long generatedID;
		String URL = "";
		String directory = "";
		String originalFilename = "";

		ORMMessageAttachmentsendfromPhr objAttachSave;

		if ("".equals(patientMessage.getMessage_id())) {
			generatedID = db.IDGenerator("patient_message", Long.parseLong(patientMessage.getPractice_id()));
			patientMessage.setMessage_id(generatedID.toString());
			patientMessage.setDate_created(DateTimeUtil.getCurrentDateTime());
			patientMessage.setDate_modified(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(patientMessage, Operation.ADD);
			
			//**************************************************************** amendment
			
			if(patmsgAmemdment!=null)                
            {
				patmsgAmemdment.setMessage_id(patientMessage.getMessage_id());
				patmsgAmemdment.setDate_created(DateTimeUtil.getCurrentDateTime());
				patmsgAmemdment.setDate_modified(DateTimeUtil.getCurrentDateTime());
                SaveAmendmentRequest(patmsgAmemdment);
            }
			
			// ***************************************************************lstpatientmessagedetail
			if(lstpatientmessagedetail != null) {
				BigDecimal generatedDetailId = new BigDecimal(BigInteger.ZERO);
				for (ORMPatientMessageDetail objMsgDetail : lstpatientmessagedetail) {
					generatedDetailId = new BigDecimal(db.IDGenerator("patient_message_detail",Long.parseLong(patientMessage.getPractice_id())));
					objMsgDetail.setMessage_detail_id(generatedDetailId.toString());
					objMsgDetail.setMessages_id(patientMessage.getMessage_id());
					objMsgDetail.setDate_created(DateTimeUtil.getCurrentDateTime());
					objMsgDetail.setDate_modified(DateTimeUtil.getCurrentDateTime());
					result = db.SaveEntity(objMsgDetail, Operation.ADD);
				}
			}
			
			//************************************************************
			if (lstpatientmessageAttachments != null) {

				BigDecimal newId = new BigDecimal(BigInteger.ZERO);

				for (ORMMessageAttachmentsendfromPhr objAttach : lstpatientmessageAttachments) {
					newId = new BigDecimal(db.IDGenerator("message_attachment",Long.parseLong(patientMessage.getPractice_id())));
					if ("file".equals(objAttach.getAttach_type().toLowerCase())) {
						objAttachSave = new ORMMessageAttachmentsendfromPhr();
						// HibernateTransactionUtil hibernate1 = HibernateTransactionUtil.getInstance();

						if("".equals(objAttach.getMessage_attachment_id())) {
							objAttachSave.setMessage_attachment_id(newId.toString());
							
							
							String fileName = "";
							for (int i = 0; i < attachfile.length; i++) {
									// if(attachfile[i].getOriginalFilename() == objAttach.getOriginal_file_name())
									// {
									if (attachfile[i].getOriginalFilename().equals(objAttach.getOriginal_file_name())) {

										ORMDocumentPath objPath = generalDAOImpl.getDocumentPathByCategory(
												Long.parseLong(objAttach.getPractice_id()), "Messages");
										if (objPath != null) {
											directory = objPath.getUpload_path();
										}
										// directory = directory + "\\" + objAttach.getPractice_id() + "\\Messages\\" ;

										directory = FileUtil.GetYearMonthDayWisePath(directory, objAttach.getPractice_id(),
												"Messages");

										originalFilename = attachfile[i].getOriginalFilename();
										try {
											fileName = newId + GeneralOperations.GetDatetimeFileName() + "."
													+ FilenameUtils.getExtension(originalFilename);
											File destinationFile = new File(GeneralOperations.CheckPath(directory), fileName);
											attachfile[i].transferTo(destinationFile);
											objAttachSave.setLink(
													DateTimeUtil.getCurrentYear() + "\\" + DateTimeUtil.getCurrentMonth() + "\\"
															+ DateTimeUtil.getCurrentDay() + "\\" + fileName);

										} catch (Exception e) {
											System.out.println("Picture Upload Error:" + e.getMessage());
										}
										// lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD,
										// objAttachSave));

										//result = db.SaveEntity(objAttachSave, Operation.ADD);
									}
								}
						}else {
							objAttachSave.setMessage_attachment_id(objAttach.getMessage_attachment_id());
							objAttachSave.setLink(objAttach.getLink());
						}
						
						
						
						objAttachSave.setPractice_id(objAttach.getPractice_id());
						objAttachSave.setPatient_id(objAttach.getPatient_id());
						objAttachSave.setDocument_date(DateTimeUtil.getCurrentDateTime());
						objAttachSave.setOriginal_file_name(objAttach.getOriginal_file_name());
						objAttachSave.setName(objAttach.getOriginal_file_name());
						objAttachSave.setAttach_type(objAttach.getAttach_type());

						objAttachSave.setMessage_id(generatedID.toString());
						/*objAttachSave.setDeleted(objAttach.getDeleted());
						objAttachSave.setCreated_user(objAttach.getCreated_user());
						objAttachSave.setModified_user(objAttach.getModified_user());
						objAttachSave.setClient_date_created(objAttach.getClient_date_created());
						objAttachSave.setClient_modified_date(objAttach.getClient_modified_date());*/

						//objAttachSave.setDate_created(DateTimeUtil.getCurrentDateTime());
						//objAttachSave.setModified_date(DateTimeUtil.getCurrentDateTime());

					
						result = db.SaveEntity(objAttachSave, Operation.ADD);
						
					} // if file end
					else if ("link".equals(objAttach.getAttach_type().toLowerCase())) {
						objAttachSave = new ORMMessageAttachmentsendfromPhr();
						// HibernateTransactionUtil hibernate1 = HibernateTransactionUtil.getInstance();
						objAttachSave.setMessage_attachment_id(newId.toString());
						objAttachSave.setPractice_id(objAttach.getPractice_id());
						objAttachSave.setPatient_id(objAttach.getPatient_id());
						objAttachSave.setDocument_date(DateTimeUtil.getCurrentDateTime());

						objAttachSave.setOriginal_file_name(objAttach.getOriginal_file_name());
						objAttachSave.setName(objAttach.getOriginal_file_name());
						objAttachSave.setLink(objAttach.getLink());

						objAttachSave.setMessage_id(generatedID.toString());
						//objAttachSave.setDeleted(objAttach.dele .getDeleted());
						//objAttachSave.setCreated_user(objAttach.getCreated_user());
						//objAttachSave.setModified_user(objAttach.getModified_user());
						//objAttachSave.setClient_date_created(objAttach.getClient_date_created());
						//objAttachSave.setClient_modified_date(objAttach.getClient_modified_date());

						objAttachSave.setAttach_type(objAttach.getAttach_type());

						//objAttachSave.setDate_created(DateTimeUtil.getCurrentDateTime());
						//objAttachSave.setModified_date(DateTimeUtil.getCurrentDateTime());

						newId = newId.add(BigDecimal.ONE);

						// lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD,
						// objAttachSave));
						result = db.SaveEntity(objAttachSave, Operation.ADD);
					}
				}

			}
			
			
			
			
		} // if null

 
		return resp;
	}
	@Override
	public List<ORMPHRPatMessages> getPatientMessages(String patient_id, String user_id, String mail_status) {
		System.out.println("****************patient_id "+ patient_id + "****************user_id "+ user_id+ "****************mail_status "+ mail_status);
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("mail_status", mail_status.toString(), String.class, ParameterMode.IN));
		//lstParam.add(new SpParameters("reciever_id", reciever_id.toString(), String.class, ParameterMode.IN));
		//return db.getStoreProcedureData("spGetPHR_PatientMessages", ORMPHRPatMessages.class, lstParam);
		return db.getStoreProcedureData("spGetPHR_PatientMessages_new", ORMPHRPatMessages.class, lstParam);
		
	}
	/*@Override
	public ORMGetMessageDetail getMessageDetail(String message_id, String user_id, String mes_type) {
		System.out.println("****************message_id "+ message_id + "****************user_id "+ user_id + "****************mes_type "+ mes_type);
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("message_id", message_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("msg_type", mes_type.toString(), String.class, ParameterMode.IN));

		List<ORMGetMessageDetail> lstData= db.getStoreProcedureData("spGetMessageDetail", ORMGetMessageDetail.class, lstParam);
		if (!lstData.isEmpty())
			return lstData.get(0);
		else
			return null;
	}*/
	@Override
	public long updateMessage(SearchCriteria searchCriteria) {
		String column_id = "";
		String modified_user = "";
		String client_date_modified = "";
		String client_ip = "";
		String CallingFrom = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("column_id")) {
					column_id = pram.getValue();
				} 
				else if (pram.getName().equals("modified_user")) {
					modified_user = pram.getValue();
				} 
				else if (pram.getName().equals("client_date_modified")) {
					client_date_modified = pram.getValue();
				} 
				else if (pram.getName().equals("client_ip")) {
					client_ip = pram.getValue();
				}
				else if (pram.getName().equals("callingFrom")) {
					CallingFrom = pram.getValue();
				}
			}
		}
		String Query="";
		if(CallingFrom.equals("archive")) {
			Query = "update patient_message_detail set mail_status = 'archive',client_date_modified = '" + client_date_modified + "',date_modified= getdate() ,modified_user = '" + modified_user + "' where message_detail_id = '" + column_id + "'";	
		}else if(CallingFrom.equals("readed")) {
			Query = "update patient_message_detail set readed = 1,client_date_modified = '" + client_date_modified + "',date_modified= getdate() ,modified_user = '" + modified_user + "' where message_detail_id = '" + column_id + "'";
		}
		else if(CallingFrom.equals("delete")) {
			Query = "update patient_message_detail set deleted = 1 ,client_date_modified = '" + client_date_modified + "',date_modified= getdate() ,modified_user = '" + modified_user + "' where message_detail_id = '" + column_id + "'";
		}
		
		return db.ExecuteUpdateQuery(Query);
	}
	@Override
	public List<ORMMessageAttachmentThreeColumn> getPatientMsgAttachments(String message_id, String practice_id) {
		System.out.println("****************message_id "+ message_id + "****************practice_id "+ practice_id);
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("message_id", message_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPHRMessageAttachments", ORMMessageAttachmentThreeColumn.class, lstParam);
	}
	@Override
	public int deleteSelectedAttachment(ORMDeleteRecord objDelete) {
		return db.deleteRecord(objDelete);
	}
	
		
	public ValidatedUser ValidateUser(String user_name, String password) {

		List<SpParameters> lstParam=new ArrayList<>();		
		
		lstParam.add(new SpParameters("username",user_name, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("password",password,String.class, ParameterMode.IN));
		
		System.out.println("spValidateUser '"+user_name+"','"+password+"'");
		
		List<ValidatedUser> lstValidateUser=db.getStoreProcedureData("spValidateUser", ValidatedUser.class,lstParam);
	
		
		
		if(lstValidateUser!=null && lstValidateUser.size()>0)
		{
			return (ValidatedUser) lstValidateUser.get(0);
		}
		else {
			return null;
		}
	}
	@Override
	public List<ORMTwoColumnGeneric> getPHRActivityLog(String practiceId, String patient_id) {
		System.out.println("*****getPHRActivityLog***********practiceId "+ practiceId + "****************patient_id "+ patient_id);
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practiceId", practiceId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPHR_activity", ORMTwoColumnGeneric.class, lstParam);
	}
	
	@Override
	public ServiceResponse<ORMKeyValue> updatePHRLog(ORMPHRAuditLog obj) {
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		obj.setLog_id(db.IDGenerator("phr_audit_log", Long.parseLong(obj.getPractice_id())).toString());
		result = db.SaveEntity(obj, Operation.ADD);
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(obj.getLog_id().toString());
		}
		return resp;
	}
	
	@Override
	public ServiceResponse<ORMKeyValue> updateLoginInformation(ORMPHRLoginuser obj) {
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		obj.setLogid(db.IDGenerator("phr_login_log", Long.parseLong(obj.getPractice_id())).toString());
		obj.setLogintime_server(GeneralOperations.CurrentDateTime());
		result = db.SaveEntity(obj, Operation.ADD);
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(obj.getLogid().toString());
		}
		return resp;
	}
	//long updatePHRLogout(SearchCriteria searchCriteria);
	@Override
	public long updatePHRLogout(SearchCriteria searchCriteria) {
		String logOutTime = "";
		String logid = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("logOutTime")) {
					logOutTime = pram.getValue();
				} 
				else if (pram.getName().equals("logid")) {
					logid = pram.getValue();
				}
			}
		}
		String Query="";
		Query= "update phr_login_log set logouttime ='" + logOutTime + "' ,logouttime_server= getdate() where logid = '" + logid + "'";
		System.out.println("*** query : "+Query);
		return db.ExecuteUpdateQuery(Query);
	}
	
	@Override
	public List<ORMLogin_log> getPHRLoginLog(String practiceId, String patient_id) {
		System.out.println("*****getPHRLoginLog***********practiceId "+ practiceId + "****************patient_id "+ patient_id);
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practiceId", practiceId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGet_Login_log_PHR", ORMLogin_log.class, lstParam);
	}
	@Override
	public List<ORMGetPHRLabOrder> getPatientPHRLabOrderSummary(String patient_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("Patient_ID", patient_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPHROrderSummary", ORMGetPHRLabOrder.class, lstParam);
	}
	@Override
	public List<ORMLabResult> getSelectedPHRLabOrderResult(String orderID) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("OrderID", orderID.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientOrderResult", ORMLabResult.class, lstParam);
	}
	@Override
	public List<ORMGetPHRLabOrderAttachment> getLabAttachments(String orderID) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("orderID", orderID, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLabOrderAttachment", ORMGetPHRLabOrderAttachment.class, lstParam);
	}
	@Override
	public List<ORMLabResultReportHeader> getLabResultRptHeader(String Order_ID) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("Order_ID", Order_ID, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetRptResultHeader", ORMLabResultReportHeader.class, lstParam);
	}
	@Override
	public List<ORMLabResultTest> getLabRptOrderTest(String Order_ID) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("OrderID", Order_ID, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientOrderResultTest", ORMLabResultTest.class, lstParam);
	}
	@Override
	public List<ORMGetphrLabOrderDirector> getLabRptOrderDir(String Order_ID) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("OrderID", Order_ID, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetorder_Director_phr", ORMGetphrLabOrderDirector.class, lstParam);
	}
	@Override
	public List<ORMTwoColumnGeneric> getLabRptOrderSourceVolume(String Order_ID) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("Order_ID", Order_ID, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("getOrderSourceVolume", ORMTwoColumnGeneric.class, lstParam);
	}
	@Override
	public List<ORM_obgyn_main_info> getGynMain(String patient_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", "", String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetMainOBGYN_phr", ORM_obgyn_main_info.class, lstParam);
	}
	@Override
	public int resetPassword(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String newPwd = "";
		String user_name = "";
		String user_id = "";
		String client_Date = "";
		int result = 0;
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("newPwd")) {
					newPwd = pram.getValue().toString();
				} else if (pram.getName().equals("user_name")) {
					user_name = pram.getValue().toString();
				} else if (pram.getName().equals("user_id")) {
					user_id = pram.getValue().toString();
				} else if (pram.getName().equals("client_Date")) {
					client_Date = pram.getValue().toString();
				}
			}
			String strQuery = "update api_users set password ='" + newPwd + "', modified_user = '" + user_name
					+ "', date_modified =getdate() ,client_date_modified = '" + client_Date + "' where user_id = '"
					+ user_id + "' ";
			result = db.ExecuteUpdateQuery(strQuery);
		}
		return result;
	}
}
