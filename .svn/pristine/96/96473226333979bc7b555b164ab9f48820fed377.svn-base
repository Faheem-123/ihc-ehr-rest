package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.dao.MessagesDOA;
import com.ihc.ehr.model.ORMGetDirectMessages;
import com.ihc.ehr.model.ORMGetMessageCount;
import com.ihc.ehr.model.ORMGetMessageDetail;
import com.ihc.ehr.model.ORMGetMessageList;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMMessageAttachment;
import com.ihc.ehr.model.ORMPatientMessageGet;
import com.ihc.ehr.model.ORMgetpatmsgusersrecipient;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.WrapperPatientMessage;
import com.ihc.ehr.model.Wrapper_AddToCorrespondence;
import com.ihc.ehr.model.Wrapper_MessageSave;
import com.ihc.ehr.model.ormToCcData;

@Service
public class MessagesServiceImpl implements MessagesService{


	@Autowired
	private MessagesDOA msgdDao;
	
	@Override
	public List<ORMGetMessageCount> getMessagesCount(String user_id) {
		// TODO Auto-generated method stub
		return msgdDao.getMessagesCount(user_id);
	}

	@Override
	public List<ORMGetMessageList> getMessagesListt(String user_id, String mes_type) {
		// TODO Auto-generated method stub
		return msgdDao.getMessagesListt(user_id,mes_type);
	}

	@Override
	public ORMGetMessageDetail getMessageDetail(String message_id, String user_id, String mes_type) {
		// TODO Auto-generated method stub
		return msgdDao.getMessageDetail(message_id,user_id,mes_type);
	}
	@Override
	public ServiceResponse saveMessage(Wrapper_MessageSave Wrapper_MessageSave) {
		return msgdDao.saveMessage(Wrapper_MessageSave);
	}
	@Override
	public long deleteSelectedMessage(SearchCriteria searchCriteria) {
		return msgdDao.deleteSelectedMessage(searchCriteria);
	}
	@Override
	public List<ormToCcData> getToCcData(String message_id) {
		return msgdDao.getToCcData(message_id);
	}
	@Override
	public List<ORMPatientMessageGet> searchPatientMSG(SearchCriteria searchCriteria) {
		return msgdDao.searchPatientMSG(searchCriteria);
	}
	@Override
	public List<ORMPatientMessageGet> onGetPatientMessage(String user_id, String practice_id) {
		return msgdDao.onGetPatientMessage(user_id, practice_id);
	}
	@Override
	public int updateToReaded(List<ORMKeyValue> patientMessageStatus) {
		return msgdDao.updateToReaded(patientMessageStatus);
	}
	@Override
	public List<ORMMessageAttachment> getMessagesAttachments(String message_id, String practice_id) {
		return msgdDao.getMessagesAttachments(message_id, practice_id);
	}
	@Override
	public List<ORMMessageAttachment> getMessagesLinks(String message_id, String practice_id) {
		return msgdDao.getMessagesLinks(message_id, practice_id);
	}
	@Override
	public ServiceResponse SaveCorrespondence(Wrapper_AddToCorrespondence Wrapper_AddToCorrespondence) {
		return msgdDao.SaveCorrespondence(Wrapper_AddToCorrespondence);
	}
	@Override
	public List<ORMGetDirectMessages> onGetDirectMessages(String practice_id) {
		return msgdDao.onGetDirectMessages(practice_id);
	}
	@Override
	public String importCCDA(SearchCriteria searchCriteria) {
		return msgdDao.importCCDA(searchCriteria);
	}
	@Override
	public String importCCR(SearchCriteria searchCriteria) {
		return msgdDao.importCCR(searchCriteria);
	}
	@Override
	public ServiceResponse<ORMKeyValue> saveupdatePatientMessages(WrapperPatientMessage objPatientMessageWrapper, MultipartFile attachfile) {
		return msgdDao.saveupdatePatientMessages(objPatientMessageWrapper,attachfile);
	}
	@Override
	public long deletePatMsg(SearchCriteria searchCriteria) {
		return msgdDao.deletePatMsg(searchCriteria);
	}
	@Override
	public List<ORMgetpatmsgusersrecipient> getPatMsgUsersRecipient(String patient_id) {
		return msgdDao.getPatMsgUsersRecipient(patient_id);
	}
	@Override
	public long archiveSelectedMessage(SearchCriteria searchCriteria) {
		return msgdDao.archiveSelectedMessage(searchCriteria);
	}

	@Override
	public List<ORMGetMessageCount> getPatientMessagesCount(String patient_id) {
		// TODO Auto-generated method stub
		return msgdDao.getPatientMessagesCount(patient_id);
	}

	@Override
	public List<ORMGetMessageList> getPatientMessagesListt(String patient_id, String mes_type) {
		// TODO Auto-generated method stub
		return msgdDao.getPatientMessagesListt(patient_id,mes_type);
	}

	@Override
	public ORMGetMessageDetail getPatientMessageDetail(String message_id, String patient_id, String mes_type) {
		// TODO Auto-generated method stub
		return msgdDao.getPatientMessageDetail(message_id,patient_id,mes_type);
	}
}
