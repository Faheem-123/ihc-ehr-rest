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
import com.ihc.ehr.service.MessagesService;

@RestController
@RequestMapping("/messages")
public class MessagesController {

	@Autowired
	MessagesService msgService;
	
	@RequestMapping("/getMessagesCount/{user_id}")
	public @ResponseBody ResponseEntity<List<ORMGetMessageCount>> getMessagesCount(
			@PathVariable(value = "user_id") String user_id) {
		List<ORMGetMessageCount> lstHM = msgService.getMessagesCount(user_id);

		return new ResponseEntity<List<ORMGetMessageCount>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getPatientMessagesCount/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetMessageCount>> getPatientMessagesCount(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMGetMessageCount> lstHM = msgService.getPatientMessagesCount(patient_id);

		return new ResponseEntity<List<ORMGetMessageCount>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getMessagesListt/{user_id}/{mes_type}")
	public @ResponseBody ResponseEntity<List<ORMGetMessageList>> getMessagesListt(
			@PathVariable(value = "user_id") String user_id,
			@PathVariable(value = "mes_type") String mes_type) {
		List<ORMGetMessageList> lstHM = msgService.getMessagesListt(user_id,mes_type);

		return new ResponseEntity<List<ORMGetMessageList>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getPatientMessagesListt/{patient_id}/{mes_type}")
	public @ResponseBody ResponseEntity<List<ORMGetMessageList>> getPatientMessagesListt(
			@PathVariable(value = "patient_id") String patient_id,
			@PathVariable(value = "mes_type") String mes_type) {
		List<ORMGetMessageList> lstHM = msgService.getPatientMessagesListt(patient_id,mes_type);

		return new ResponseEntity<List<ORMGetMessageList>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getMessageDetail/{message_id}/{user_id}/{mes_type}")
	public @ResponseBody ResponseEntity<ORMGetMessageDetail> getMessageDetail(
			@PathVariable(value = "message_id") String message_id,
			@PathVariable(value = "user_id") String user_id,
			@PathVariable(value = "mes_type") String mes_type) {
		ORMGetMessageDetail lstHM = msgService.getMessageDetail(message_id,user_id,mes_type);

		return new ResponseEntity<ORMGetMessageDetail>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getPatientMessageDetail/{message_id}/{patient_id}/{mes_type}")
	public @ResponseBody ResponseEntity<ORMGetMessageDetail> getPatientMessageDetail(
			@PathVariable(value = "message_id") String message_id,
			@PathVariable(value = "patient_id") String patient_id,
			@PathVariable(value = "mes_type") String mes_type) {
		ORMGetMessageDetail lstHM = msgService.getPatientMessageDetail(message_id,patient_id,mes_type);

		return new ResponseEntity<ORMGetMessageDetail>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/saveMessage")
	public @ResponseBody ResponseEntity<ServiceResponse> saveMessage(
			@RequestBody Wrapper_MessageSave Wrapper_MessageSave) {
		ServiceResponse resp = msgService.saveMessage(Wrapper_MessageSave);
		return new ResponseEntity<ServiceResponse>(resp, HttpStatus.OK);

	}
	@RequestMapping("/deleteSelectedMessage")
	public long deleteSelectedMessage(@RequestBody SearchCriteria searchCriteria) {
		return msgService.deleteSelectedMessage(searchCriteria);
	}
	@RequestMapping("/getToCcData/{message_id}")
	public @ResponseBody ResponseEntity<List<ormToCcData>> getToCcData(
			@PathVariable(value = "message_id") String message_id) {
		List<ormToCcData> lstHM = msgService.getToCcData(message_id);
		return new ResponseEntity<List<ormToCcData>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/searchPatientMSG")
	public @ResponseBody ResponseEntity<List<ORMPatientMessageGet>> searchPatientMSG(
			@RequestBody SearchCriteria searchCriteria)
	{	
		List<ORMPatientMessageGet> lst=msgService.searchPatientMSG(searchCriteria);
		return new ResponseEntity<List<ORMPatientMessageGet>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/onGetPatientMessage/{user_id}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMPatientMessageGet>> onGetPatientMessage(
			@PathVariable(value = "user_id") String user_id,
			@PathVariable(value = "practice_id") String practice_id) 
	{	
		List<ORMPatientMessageGet> lst=msgService.onGetPatientMessage(user_id, practice_id);
		return new ResponseEntity<List<ORMPatientMessageGet>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/updateToReaded")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateToReaded(
			@RequestBody List<ORMKeyValue> patientMessageStatus) {
		msgService.updateToReaded(patientMessageStatus);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
	}
	@RequestMapping("/getMessagesAttachments/{message_id}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMMessageAttachment>> getMessagesAttachments(
			@PathVariable(value = "message_id") String message_id,
			@PathVariable(value = "practice_id") String practice_id){	
		List<ORMMessageAttachment> lst = msgService.getMessagesAttachments(message_id, practice_id);
		return new ResponseEntity<List<ORMMessageAttachment>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getMessagesLinks/{message_id}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMMessageAttachment>> getMessagesLinks(
			@PathVariable(value = "message_id") String message_id,
			@PathVariable(value = "practice_id") String practice_id) 
	{	
		List<ORMMessageAttachment> lst=msgService.getMessagesLinks(message_id, practice_id);
		return new ResponseEntity<List<ORMMessageAttachment>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/SaveCorrespondence")
	public @ResponseBody ResponseEntity<ServiceResponse> SaveCorrespondence(
			@RequestBody Wrapper_AddToCorrespondence Wrapper_AddToCorrespondence) {
		ServiceResponse resp = msgService.SaveCorrespondence(Wrapper_AddToCorrespondence);
		return new ResponseEntity<ServiceResponse>(resp, HttpStatus.OK);
	}
	@RequestMapping("/onGetDirectMessages/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetDirectMessages>> onGetDirectMessages(
			@PathVariable(value = "practice_id") String practice_id){	
		List<ORMGetDirectMessages> lst=msgService.onGetDirectMessages(practice_id);
		return new ResponseEntity<List<ORMGetDirectMessages>>(lst , HttpStatus.OK);
	}
	//searchPatientMSG
	@RequestMapping("/importCCDA")
	public @ResponseBody String importCCDA(@RequestBody SearchCriteria searchCriteria){	
		return msgService.importCCDA(searchCriteria);
	}
	@RequestMapping("/importCCR")
	public String importCCR(@RequestBody SearchCriteria searchCriteria){	
		return msgService.importCCR(searchCriteria);
	}
	@RequestMapping(value = "/saveupdatePatientMessages", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveupdatePatientMessages(
			@RequestParam(required = false, value = "attachFile") MultipartFile attachFile,// inputFile,
			@RequestParam("patMsgWrapperData") String objPatientMesgWrapper) throws JsonParseException, JsonMappingException, IOException 
	{
		System.out.println("inputFile"+attachFile);
		ObjectMapper mapper = new ObjectMapper();
		WrapperPatientMessage objPatientMessageWrapper = mapper.readValue(objPatientMesgWrapper, WrapperPatientMessage.class);
		ServiceResponse<ORMKeyValue> resp  = msgService.saveupdatePatientMessages(objPatientMessageWrapper,attachFile);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deletePatMsg")
	public long deletePatMsg(@RequestBody SearchCriteria searchCriteria) {
		return msgService.deletePatMsg(searchCriteria);
	}
	@RequestMapping("/getPatMsgUsersRecipient/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMgetpatmsgusersrecipient>> getPatMsgUsersRecipient(
			@PathVariable(value = "patient_id") String patient_id){	
		List<ORMgetpatmsgusersrecipient> lst=msgService.getPatMsgUsersRecipient(patient_id);
		return new ResponseEntity<List<ORMgetpatmsgusersrecipient>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/archiveSelectedMessage")
	public long archiveSelectedMessage(@RequestBody SearchCriteria searchCriteria) {
		return msgService.archiveSelectedMessage(searchCriteria);
	}
}