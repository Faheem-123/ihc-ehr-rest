package com.ihc.ehr.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.model.ORMDeleteRecord;
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
import com.ihc.ehr.model.ORMMessageAttachmentThreeColumn;
import com.ihc.ehr.model.ORMPHRAuditLog;
import com.ihc.ehr.model.ORMPHRLoginuser;
import com.ihc.ehr.model.ORMPHRPatMessages;
import com.ihc.ehr.model.ORMProviderList;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORM_obgyn_main_info;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.WrapperPhrMsg;

public interface PhrDOA {

	ORMGetPHRLoginUserInfo getPHRLogedInUserDetail(Long user_id);
	List<ORMGetPHRLoginUserPatient> getPHRLogedInUserPatients(Long user_id);
	
	List<ORMGetMessageCount> getMessagesCount(String user_id);
	List<ORMGetMessageList> getMessagesList(String user_id, String mes_type);
	List<ORMProviderList> getProviderList(Long practice_id);
	ServiceResponse<ORMKeyValue> savePatiehtPhrMsg(WrapperPhrMsg objPhrMsgWrapper, MultipartFile[] attachfile);
	//List<ORMPHRPatMessages> getPatientMessages(String patient_id, String user_id, String mail_status, String reciever_id);
	List<ORMPHRPatMessages> getPatientMessages(String patient_id, String user_id, String mail_status);
	//ORMGetMessageDetail getMessageDetail(String message_id, String user_id, String mes_type);
	long updateMessage(SearchCriteria searchCriteria);
	List<ORMMessageAttachmentThreeColumn> getPatientMsgAttachments(String message_id, String practice_id);
	int deleteSelectedAttachment(ORMDeleteRecord obj);
	List<ORMTwoColumnGeneric> getPHRActivityLog(String practiceId, String patient_id);
	ServiceResponse<ORMKeyValue> updatePHRLog(ORMPHRAuditLog obj);
	ServiceResponse<ORMKeyValue> updateLoginInformation(ORMPHRLoginuser obj);
	long updatePHRLogout(SearchCriteria searchCriteria);
	List<ORMLogin_log> getPHRLoginLog(String practiceId, String patient_id);
	List<ORMGetPHRLabOrder> getPatientPHRLabOrderSummary(String patient_id);
	List<ORMLabResult> getSelectedPHRLabOrderResult(String orderID);
	List<ORMGetPHRLabOrderAttachment> getLabAttachments(String orderID);
	List<ORMLabResultReportHeader> getLabResultRptHeader(String Order_ID);
	List<ORMLabResultTest> getLabRptOrderTest(String Order_ID);
	List<ORMGetphrLabOrderDirector> getLabRptOrderDir(String Order_ID);
	List<ORMTwoColumnGeneric> getLabRptOrderSourceVolume(String Order_ID);
	List<ORM_obgyn_main_info> getGynMain(String patient_id);
	public int resetPassword(SearchCriteria searchCriteria);
}
