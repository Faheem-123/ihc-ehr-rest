package com.ihc.ehr.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

public interface MessagesService {

	List<ORMGetMessageCount> getMessagesCount(String user_id);
	List<ORMGetMessageList> getMessagesListt(String user_id, String mes_type);
	ORMGetMessageDetail getMessageDetail(String message_id, String user_id, String mes_type);
	ServiceResponse<ORMKeyValue> saveMessage(Wrapper_MessageSave Wrapper_MessageSave);
	long deleteSelectedMessage(SearchCriteria searchCriteria);
	List<ormToCcData> getToCcData(String message_id);
	List<ORMPatientMessageGet> searchPatientMSG(SearchCriteria searchCriteria);
	List<ORMPatientMessageGet> onGetPatientMessage(String user_id, String practice_id);
	int updateToReaded(List<ORMKeyValue> patientMessageStatus);
	List<ORMMessageAttachment> getMessagesAttachments(String message_id, String practice_id);
	List<ORMMessageAttachment> getMessagesLinks(String message_id, String practice_id);
	ServiceResponse<ORMKeyValue> SaveCorrespondence(Wrapper_AddToCorrespondence Wrapper_AddToCorrespondence);
	List<ORMGetDirectMessages> onGetDirectMessages(String practice_id);
	String importCCDA(SearchCriteria searchCriteria);
	String importCCR(SearchCriteria searchCriteria);
	ServiceResponse<ORMKeyValue> saveupdatePatientMessages(WrapperPatientMessage objPatientMessageWrapper, MultipartFile attachfile);
	long deletePatMsg(SearchCriteria searchCriteria);
	List<ORMgetpatmsgusersrecipient> getPatMsgUsersRecipient(String patient_id);
	long archiveSelectedMessage(SearchCriteria searchCriteria);
	List<ORMGetMessageCount> getPatientMessagesCount(String patient_id);
	List<ORMGetMessageList> getPatientMessagesListt(String patient_id, String mes_type);
	ORMGetMessageDetail getPatientMessageDetail(String message_id, String patient_id, String mes_type);
}
