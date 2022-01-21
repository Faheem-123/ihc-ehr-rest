package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.dao.PhrDOA;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMGetMessageCount;
import com.ihc.ehr.model.ORMGetMessageList;
import com.ihc.ehr.model.ORMGetPHRLabOrder;
import com.ihc.ehr.model.ORMGetPHRLabOrderAttachment;
import com.ihc.ehr.model.ORMGetPHRLoginUserInfo;
import com.ihc.ehr.model.ORMGetPHRLoginUserPatient;
import com.ihc.ehr.model.ORMGetphrLabOrderDirector;
import com.ihc.ehr.model.ORMKeyValue;
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

@Service
public class PhrServiceImpl implements PhrService{

	@Autowired
	private PhrDOA phrDao;
	
	@Override
	public ORMGetPHRLoginUserInfo getPHRLogedInUserDetail(Long user_id) {
		// TODO Auto-generated method stub
		return phrDao.getPHRLogedInUserDetail(user_id);
	}
	@Override
	public List<ORMGetPHRLoginUserPatient> getPHRLogedInUserPatients(Long user_id) {
		// TODO Auto-generated method stub
		return phrDao.getPHRLogedInUserPatients(user_id);
	}
	
	
	@Override
	public List<ORMGetMessageCount> getMessagesCount(String user_id) {
		return phrDao.getMessagesCount(user_id);
	}
	@Override
	public List<ORMGetMessageList> getMessagesList(String user_id, String mes_type) {
		return phrDao.getMessagesList(user_id,mes_type);
	}
	@Override
	public List<ORMProviderList> getProviderList(Long practice_id) {
		return phrDao.getProviderList(practice_id);
	}
	@Override
	public ServiceResponse<ORMKeyValue> savePatiehtPhrMsg(WrapperPhrMsg objPhrMsgWrapper, MultipartFile[] attachfile) {
		return phrDao.savePatiehtPhrMsg(objPhrMsgWrapper,attachfile);
	}
	//@Override
	//public List<ORMPHRPatMessages> getPatientMessages(String patient_id, String user_id, String mail_status, String reciever_id) {
	//	return phrDao.getPatientMessages(patient_id, user_id, mail_status, reciever_id);
	//}
	@Override
	public List<ORMPHRPatMessages> getPatientMessages(String patient_id, String user_id, String mail_status) {
		return phrDao.getPatientMessages(patient_id, user_id, mail_status);
	}
	/*@Override
	public ORMGetMessageDetail getMessageDetail(String message_id, String user_id, String mes_type) {
		return phrDao.getMessageDetail(message_id,user_id,mes_type);
	}*/
	@Override
	public long updateMessage(SearchCriteria searchCriteria) {
		return phrDao.updateMessage(searchCriteria);
	}
	@Override
	public List<ORMMessageAttachmentThreeColumn> getPatientMsgAttachments(String message_id, String practice_id) {
		return phrDao.getPatientMsgAttachments(message_id, practice_id);
	}
	@Override
	public int deleteSelectedAttachment(ORMDeleteRecord obj) {
		// TODO Auto-generated method stub
		return phrDao.deleteSelectedAttachment(obj);
	}
	@Override
	public List<ORMTwoColumnGeneric> getPHRActivityLog(String practiceId, String patient_id) {
		return phrDao.getPHRActivityLog(practiceId, patient_id);
	}
	@Override
	public ServiceResponse<ORMKeyValue> updatePHRLog(ORMPHRAuditLog obj) {
		return phrDao.updatePHRLog(obj);
	}
	@Override
	public ServiceResponse<ORMKeyValue> updateLoginInformation(ORMPHRLoginuser obj) {
		return phrDao.updateLoginInformation(obj);
	}
	@Override
	public long updatePHRLogout(SearchCriteria searchCriteria) {
		return phrDao.updatePHRLogout(searchCriteria);
	}
	@Override
	public List<ORMLogin_log> getPHRLoginLog(String practiceId, String patient_id) {
		return phrDao.getPHRLoginLog(practiceId, patient_id);
	}
	@Override
	public List<ORMGetPHRLabOrder> getPatientPHRLabOrderSummary(String patient_id) {
		return phrDao.getPatientPHRLabOrderSummary(patient_id);
	}
	@Override
	public List<ORMLabResult> getSelectedPHRLabOrderResult(String orderID) {
		return phrDao.getSelectedPHRLabOrderResult(orderID);
	}
	@Override
	public List<ORMGetPHRLabOrderAttachment> getLabAttachments(String orderID) {
		return phrDao.getLabAttachments(orderID);
	}
	@Override
	public List<ORMLabResultReportHeader> getLabResultRptHeader(String Order_ID) {
		return phrDao.getLabResultRptHeader(Order_ID);
	}
	@Override
	public List<ORMLabResultTest> getLabRptOrderTest(String Order_ID) {
		return phrDao.getLabRptOrderTest(Order_ID);
	}
	@Override
	public List<ORMGetphrLabOrderDirector> getLabRptOrderDir(String Order_ID) {
		return phrDao.getLabRptOrderDir(Order_ID);
	}
	@Override
	public List<ORMTwoColumnGeneric> getLabRptOrderSourceVolume(String Order_ID) {
		return phrDao.getLabRptOrderSourceVolume(Order_ID);
	}
	@Override
	public List<ORM_obgyn_main_info> getGynMain(String patient_id) {
		return phrDao.getGynMain(patient_id);
	}
	@Override
	public int resetPassword(SearchCriteria searchCriteria) {
		return phrDao.resetPassword(searchCriteria);
	}
}
