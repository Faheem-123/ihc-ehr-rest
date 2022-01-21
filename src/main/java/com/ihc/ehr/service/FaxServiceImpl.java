package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.dao.FaxDOA;
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

@Service
public class FaxServiceImpl implements FaxService {
	
	@Autowired
	FaxDOA faxDOA;

	@Override
	public List<ORMFaxContactListGet> getFaxContactList(Long practiceId) {
		// TODO Auto-generated method stub
		return faxDOA.getFaxContactList(practiceId);
	}

	@Override
	public ORMFaxContactDetailGetSave getFaxContactDetailById(Long contactId) {
		// TODO Auto-generated method stub
		return faxDOA.getFaxContactDetailById(contactId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveFaxContact(ORMFaxContactDetailGetSave ormFaxContactDetailGetSave) {
		// TODO Auto-generated method stub
		return faxDOA.saveFaxContact(ormFaxContactDetailGetSave);
	}

	@Override
	public List<ORMIdDescription> getFaxConfigFaxNumbersList(Long practiceId, String faxServer) {
		// TODO Auto-generated method stub
		return faxDOA.getFaxConfigFaxNumbersList(practiceId,faxServer);
	}

	@Override
	public ServiceResponse<ORMKeyValue> sendFax(WrapperSendFax wrapperSendFax, MultipartFile[] attachments) {
		// TODO Auto-generated method stub
		return faxDOA.sendFax(wrapperSendFax,attachments);
	}

	@Override
	public ServiceResponse<ORMKeyValue> resendFax(FaxResendModel faxResendModel) {
		// TODO Auto-generated method stub
		return faxDOA.resendFax(faxResendModel);
	}

	@Override
	public ServiceResponse<ORMKeyValue> updateFaxSendingStatus(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return faxDOA.updateFaxSendingStatus(searchCriteria);
	}

	@Override
	public List<ORMFaxSentGet> getSentFaxes(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return faxDOA.getSentFaxes(searchCriteria);
	}

	@Override
	public List<ORMFaxSentAttachment> getSentFaxAttachments(Long faxSentId) {
		// TODO Auto-generated method stub
		return faxDOA.getSentFaxAttachments(faxSentId);
	}

	@Override
	public List<ORMFaxSentAttemptsGet> getFaxSendingAttempts(Long faxSentId) {
		// TODO Auto-generated method stub
		return faxDOA.getFaxSendingAttempts(faxSentId);
	}

	@Override
	public List<ORMFaxReceivedGet> getReceivedFaxes(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return faxDOA.getReceivedFaxes(searchCriteria);
	}

	@Override
	public List<ORMFaxReceivedUserAssignedGet> getUserAssignedReceivedFaxes(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return faxDOA.getUserAssignedReceivedFaxes(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> downloadFaxesFromServer(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return faxDOA.downloadFaxesFromServer(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> addFaxReceivedToPatientDocument(
			Wrapper_ObjectSave<ORMSaveDocument> wrapperObjectSave) {
		// TODO Auto-generated method stub
		return faxDOA.addFaxReceivedToPatientDocument(wrapperObjectSave);
	}

	@Override
	public List<ORMGetAssignedToUsersList> getAssignedToUsersList(Long practiceId) {
		// TODO Auto-generated method stub
		return faxDOA.getAssignedToUsersList(practiceId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> assignFaxToUsers(WrapperListWithOneObjectSave<ORMIdName, AssignFaxToUserParmList>  wrapperListWithOneObjectSave) {
		// TODO Auto-generated method stub
		return faxDOA.assignFaxToUsers(wrapperListWithOneObjectSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> updateReceivedUserFaxStatus(List<ORMKeyValue> lstKV) {
		// TODO Auto-generated method stub
		return faxDOA.updateReceivedUserFaxStatus(lstKV);
	}

}
