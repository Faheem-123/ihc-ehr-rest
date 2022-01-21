package com.ihc.ehr.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.model.AssignFaxToUserParmList;
import com.ihc.ehr.model.FaxResendModel;
import com.ihc.ehr.model.ORMFaxContactDetailGetSave;
import com.ihc.ehr.model.ORMFaxContactListGet;
import com.ihc.ehr.model.ORMFaxReceivedGet;
import com.ihc.ehr.model.ORMFaxReceivedUserAssignedGet;
import com.ihc.ehr.model.ORMFaxSentAttachment;
import com.ihc.ehr.model.ORMFaxSentAttemptsGet;
import com.ihc.ehr.model.ORMFaxSentGet;
import com.ihc.ehr.model.ORMGetAssignedToUsersList;
import com.ihc.ehr.model.ORMIdDescription;
import com.ihc.ehr.model.ORMIdName;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMSaveDocument;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.WrapperListWithOneObjectSave;
import com.ihc.ehr.model.WrapperSendFax;
import com.ihc.ehr.model.Wrapper_ObjectSave;

public interface FaxService {

	List<ORMFaxContactListGet> getFaxContactList(Long practiceId);

	ORMFaxContactDetailGetSave getFaxContactDetailById(Long contactId);

	ServiceResponse<ORMKeyValue> saveFaxContact(ORMFaxContactDetailGetSave ormFaxContactDetailGetSave);

	List<ORMIdDescription> getFaxConfigFaxNumbersList(Long practiceId, String faxServer);

	ServiceResponse<ORMKeyValue> sendFax(WrapperSendFax wrapperSendFax, MultipartFile[] attachments);

	ServiceResponse<ORMKeyValue> resendFax(FaxResendModel faxResendModel);

	ServiceResponse<ORMKeyValue> updateFaxSendingStatus(SearchCriteria searchCriteria);

	List<ORMFaxSentGet> getSentFaxes(SearchCriteria searchCriteria);

	List<ORMFaxSentAttachment> getSentFaxAttachments(Long faxSentId);

	List<ORMFaxSentAttemptsGet> getFaxSendingAttempts(Long faxSentId);

	List<ORMFaxReceivedGet> getReceivedFaxes(SearchCriteria searchCriteria);

	List<ORMFaxReceivedUserAssignedGet> getUserAssignedReceivedFaxes(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> downloadFaxesFromServer(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> addFaxReceivedToPatientDocument(Wrapper_ObjectSave<ORMSaveDocument> wrapperObjectSave);

	List<ORMGetAssignedToUsersList> getAssignedToUsersList(Long practiceId);

	ServiceResponse<ORMKeyValue> assignFaxToUsers(
			WrapperListWithOneObjectSave<ORMIdName, AssignFaxToUserParmList> wrapperListWithOneObjectSave);

	ServiceResponse<ORMKeyValue> updateReceivedUserFaxStatus(List<ORMKeyValue> lstKV);

}
