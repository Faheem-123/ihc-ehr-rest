package com.ihc.ehr.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihc.ehr.model.AssignFaxToUserParmList;
import com.ihc.ehr.model.FaxResendModel;
import com.ihc.ehr.model.ORMDeleteRecord;
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
import com.ihc.ehr.service.FaxService;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/fax")
public class FaxController {

	@Autowired
	FaxService faxService;

	@Autowired
	GeneralService generalService;

	@RequestMapping("getFaxContactList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMFaxContactListGet>> getFaxContactList(
			@PathVariable(value = "practice_id") Long practiceId) {

		List<ORMFaxContactListGet> lst = faxService.getFaxContactList(practiceId);
		return new ResponseEntity<List<ORMFaxContactListGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getFaxContactDetailById/{contact_id}")
	public @ResponseBody ResponseEntity<ORMFaxContactDetailGetSave> getFaxContactDetailById(
			@PathVariable(value = "contact_id") Long contactId) {

		ORMFaxContactDetailGetSave obj = faxService.getFaxContactDetailById(contactId);
		return new ResponseEntity<ORMFaxContactDetailGetSave>(obj, HttpStatus.OK);
	}

	@RequestMapping("saveFaxContact")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveFaxContact(
			@RequestBody ORMFaxContactDetailGetSave ormFaxContactDetailGetSave) {

		ServiceResponse<ORMKeyValue> resp = faxService.saveFaxContact(ormFaxContactDetailGetSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("deleteFaxContact")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteFaxContact(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteFaxContact: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("faxContactList");
		ormDeleteRecord.setColumn_name("contactlist_id");

		int result = generalService.deleteRecord(ormDeleteRecord);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormDeleteRecord.getColumn_id().toString());
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("getFaxConfigFaxNumbersList/{practice_id}/{fax_server}")
	public @ResponseBody ResponseEntity<List<ORMIdDescription>> getFaxConfigFaxNumbersList(
			@PathVariable(value = "practice_id") Long practiceId,
			@PathVariable(value = "fax_server") String faxServer) {

		List<ORMIdDescription> lst = faxService.getFaxConfigFaxNumbersList(practiceId, faxServer);
		return new ResponseEntity<List<ORMIdDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping(value = "sendFax", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> sendFax(
			@RequestParam(required = false, value = "attachments") MultipartFile[] attachments, // inputFile,
			@RequestParam("wrapperSendFax") String objWrapperSendFax)
			throws JsonParseException, JsonMappingException, IOException {

		// System.out.println("inputFile" + attachments);
		ObjectMapper mapper = new ObjectMapper();
		WrapperSendFax wrapperSendFax = mapper.readValue(objWrapperSendFax, WrapperSendFax.class);
		

		ServiceResponse<ORMKeyValue> resp = faxService.sendFax(wrapperSendFax, attachments);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("resendFax")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> resendFax(@RequestBody FaxResendModel faxResendModel) {
		GeneralOperations.logMsg("Inside resendFax: FaxResendModel=" + faxResendModel.toString());

		ServiceResponse<ORMKeyValue> resp = faxService.resendFax(faxResendModel);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("updateFaxSendingStatus")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateFaxSendingStatus(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside updateFaxSendingStatus=" + searchCriteria.toString());

		ServiceResponse<ORMKeyValue> resp = faxService.updateFaxSendingStatus(searchCriteria);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("getSentFaxes")
	public ResponseEntity<List<ORMFaxSentGet>> getSentFaxes(@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getSentFaxes=" + searchCriteria.toString());

		List<ORMFaxSentGet> lst = faxService.getSentFaxes(searchCriteria);

		return new ResponseEntity<List<ORMFaxSentGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getSentFaxAttachments/{fax_sent_id}")
	public @ResponseBody ResponseEntity<List<ORMFaxSentAttachment>> getSentFaxAttachments(
			@PathVariable(value = "fax_sent_id") Long faxSentId) {

		GeneralOperations.logMsg("Inside getSentFaxAttachments  faxSentId=" + faxSentId.toString());

		List<ORMFaxSentAttachment> lst = faxService.getSentFaxAttachments(faxSentId);

		return new ResponseEntity<List<ORMFaxSentAttachment>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getFaxSendingAttempts/{fax_sent_id}")
	public @ResponseBody ResponseEntity<List<ORMFaxSentAttemptsGet>> getFaxSendingAttempts(
			@PathVariable(value = "fax_sent_id") Long faxSentId) {
		GeneralOperations.logMsg("Inside getFaxSendingAttempts  faxSentId=" + faxSentId.toString());

		List<ORMFaxSentAttemptsGet> lst = faxService.getFaxSendingAttempts(faxSentId);

		return new ResponseEntity<List<ORMFaxSentAttemptsGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("deleteFaxSent")
	public int deletePatientInjury(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteFaxSent: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("fax_sent");
		ormDeleteRecord.setColumn_name("fax_sent_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("getReceivedFaxes")
	public @ResponseBody ResponseEntity<List<ORMFaxReceivedGet>> getReceivedFaxes(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getReceivedFaxes=" + searchCriteria.toString());

		List<ORMFaxReceivedGet> lst = faxService.getReceivedFaxes(searchCriteria);

		return new ResponseEntity<List<ORMFaxReceivedGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getUserAssignedReceivedFaxes")
	public @ResponseBody ResponseEntity<List<ORMFaxReceivedUserAssignedGet>> getUserAssignedReceivedFaxes(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getUserAssignedReceivedFaxes=" + searchCriteria.toString());

		List<ORMFaxReceivedUserAssignedGet> lst = faxService.getUserAssignedReceivedFaxes(searchCriteria);

		return new ResponseEntity<List<ORMFaxReceivedUserAssignedGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("downloadFaxesFromServer")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> downloadFaxesFromServer(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside downloadFaxesFromServer=" + searchCriteria.toString());

		ServiceResponse<ORMKeyValue> resp = faxService.downloadFaxesFromServer(searchCriteria);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("addFaxReceivedToPatientDocument")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> addFaxReceivedToPatientDocument(
			@RequestBody Wrapper_ObjectSave<ORMSaveDocument> wrapperObjectSave) {
		GeneralOperations.logMsg("Inside AddFaxReceivedToPatientDocument");

		ServiceResponse<ORMKeyValue> resp = faxService.addFaxReceivedToPatientDocument(wrapperObjectSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("getAssignedToUsersList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetAssignedToUsersList>> getAssignedToUsersList(
			@PathVariable(value = "practice_id") Long practiceId) {

		List<ORMGetAssignedToUsersList> lst = faxService.getAssignedToUsersList(practiceId);
		return new ResponseEntity<List<ORMGetAssignedToUsersList>>(lst, HttpStatus.OK);
	}

	@RequestMapping("assignFaxToUsers")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> assignFaxToUsers(
			@RequestBody WrapperListWithOneObjectSave<ORMIdName, AssignFaxToUserParmList> wrapperListWithOneObjectSave) {
		GeneralOperations.logMsg("Inside assignFaxToUsers");

		ServiceResponse<ORMKeyValue> resp = faxService.assignFaxToUsers(wrapperListWithOneObjectSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("deleteReceivedFax")
	public int deleteReceivedFax(@RequestBody Wrapper_ObjectSave<ORMDeleteRecord> wrapperObjectSave) {

		ORMDeleteRecord ormDeleteRecord = wrapperObjectSave.getOrmSave();
		String option = "all";

		if (wrapperObjectSave.getLstKeyValue() != null && wrapperObjectSave.getLstKeyValue().size() > 0) {
			for (ORMKeyValue kv : wrapperObjectSave.getLstKeyValue()) {
				switch (kv.getKey()) {
				case "option":
					option = kv.getValue();
					break;
				default:
					break;
				}
			}
		}

		if (option.equalsIgnoreCase("myfaxes")) {
			System.out.println("Delete User Faxes: " + ormDeleteRecord.getColumn_id());
			ormDeleteRecord.setTable_name("fax_users");
			ormDeleteRecord.setColumn_name("fax_users_id");
		} else {
			System.out.println("Delete Received Faxes: " + ormDeleteRecord.getColumn_id());
			ormDeleteRecord.setTable_name("fax_recived");
			ormDeleteRecord.setColumn_name("fax_recived_id");
		}

		return generalService.deleteRecord(ormDeleteRecord);

	}
	
	@RequestMapping("updateReceivedUserFaxStatus")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateReceivedUserFaxStatus(@RequestBody List<ORMKeyValue> lstKV) {		

		
		ServiceResponse<ORMKeyValue> resp = faxService.updateReceivedUserFaxStatus(lstKV);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}
}
