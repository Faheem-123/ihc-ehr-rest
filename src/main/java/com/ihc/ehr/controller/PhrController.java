package com.ihc.ehr.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.ihc.ehr.service.PhrService;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@RestController
@RequestMapping("/phr")
public class PhrController {
	@Autowired
	PhrService phrService;
	
	@RequestMapping("/getPHRLogedInUserDetail/{user_id}")
	public @ResponseBody ResponseEntity<ORMGetPHRLoginUserInfo> getPHRLogedInUserDetail(
			@PathVariable(value="user_id")  Long user_id)
	{			
		ORMGetPHRLoginUserInfo user=phrService.getPHRLogedInUserDetail(user_id);		
		return new ResponseEntity<ORMGetPHRLoginUserInfo>(user , HttpStatus.OK);
	}
	
	@RequestMapping("/getPHRLogedInUserPatients/{user_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPHRLoginUserPatient>> getPHRLogedInUserPatients(
			@PathVariable(value="user_id")  Long user_id)
	{			
		List<ORMGetPHRLoginUserPatient> lst=phrService.getPHRLogedInUserPatients(user_id);		
		return new ResponseEntity<List<ORMGetPHRLoginUserPatient>>(lst , HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping("/getMessagesCount/{user_id}")
	public @ResponseBody ResponseEntity<List<ORMGetMessageCount>> getMessagesCount(
			@PathVariable(value = "user_id") String user_id) {
		List<ORMGetMessageCount> lstHM = phrService.getMessagesCount(user_id);
		return new ResponseEntity<List<ORMGetMessageCount>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getMessagesList/{user_id}/{mes_type}")
	public @ResponseBody ResponseEntity<List<ORMGetMessageList>> getMessagesList(
			@PathVariable(value = "user_id") String user_id,
			@PathVariable(value = "mes_type") String mes_type) {
		List<ORMGetMessageList> lstHM = phrService.getMessagesList(user_id,mes_type);

		return new ResponseEntity<List<ORMGetMessageList>>(lstHM, HttpStatus.OK);
	}
	
	@RequestMapping("/getProviderList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMProviderList>> getProviderList(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMProviderList> obj = phrService.getProviderList(practice_id);
		return new ResponseEntity<List<ORMProviderList>>(obj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/savePatiehtPhrMsg", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> savePatiehtPhrMsg(
			@RequestParam(required = false, value = "attachFile") MultipartFile[] attachfile,// inputFile,
			@RequestParam("phrWrapperData") String objPhrMsgWrapper) throws JsonParseException, JsonMappingException, IOException 
	{
		System.out.println("inputFile"+attachfile);
		ObjectMapper mapper = new ObjectMapper();
		WrapperPhrMsg objPhrMessageWrapper = mapper.readValue(objPhrMsgWrapper, WrapperPhrMsg.class);
		ServiceResponse<ORMKeyValue> resp  = phrService.savePatiehtPhrMsg(objPhrMessageWrapper,attachfile);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	/*@RequestMapping("/getPatientMessages/{patient_id}/{user_id}/{mail_status}/{reciever_id}")
	public @ResponseBody ResponseEntity<List<ORMPHRPatMessages>> getPatientMessages(
			@PathVariable(value = "patient_id") String patient_id,
			@PathVariable(value = "user_id") String user_id,
			@PathVariable(value = "mail_status") String mail_status,
			@PathVariable(value = "reciever_id") String reciever_id) {
		List<ORMPHRPatMessages> lstHM = phrService.getPatientMessages(patient_id,user_id,mail_status, reciever_id);
		return new ResponseEntity<List<ORMPHRPatMessages>>(lstHM, HttpStatus.OK);
	}*/
	@RequestMapping("/getPatientMessages/{patient_id}/{user_id}/{mail_status}")
	public @ResponseBody ResponseEntity<List<ORMPHRPatMessages>> getPatientMessages(
			@PathVariable(value = "patient_id") String patient_id,
			@PathVariable(value = "user_id") String user_id,
			@PathVariable(value = "mail_status") String mail_status) {
		List<ORMPHRPatMessages> lstHM = phrService.getPatientMessages(patient_id,user_id,mail_status);
		return new ResponseEntity<List<ORMPHRPatMessages>>(lstHM, HttpStatus.OK);
	}
	/*@RequestMapping("/getMessageDetail/{message_id}/{user_id}/{mes_type}")
	public @ResponseBody ResponseEntity<ORMGetMessageDetail> getMessageDetail(
			@PathVariable(value = "message_id") String message_id,
			@PathVariable(value = "user_id") String user_id,
			@PathVariable(value = "mes_type") String mes_type) {
		ORMGetMessageDetail lstHM = phrService.getMessageDetail(message_id,user_id,mes_type);
		return new ResponseEntity<ORMGetMessageDetail>(lstHM, HttpStatus.OK);
	}*/
	@RequestMapping("/updateMessage")
	public long updateMessage(@RequestBody SearchCriteria searchCriteria) {
		return phrService.updateMessage(searchCriteria);
	}
	@RequestMapping("/getPatientMsgAttachments/{message_id}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMMessageAttachmentThreeColumn>> getPatientMsgAttachments(
			@PathVariable(value = "message_id") String message_id,
			@PathVariable(value = "practice_id") String practice_id) {
		List<ORMMessageAttachmentThreeColumn> lstHM = phrService.getPatientMsgAttachments(message_id,practice_id);
		return new ResponseEntity<List<ORMMessageAttachmentThreeColumn>>(lstHM, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/downloadB", method = RequestMethod.POST)
	public @ResponseBody byte[] downloadB(@RequestBody SearchCriteria searchCriteria) throws IOException {
		String file_name = searchCriteria.getCriteria();
		System.out.println("File Name:" + file_name);
		File file = new File(file_name);
		
		if (file.exists()) {
			byte[] document = org.springframework.util.FileCopyUtils.copyToByteArray(file);

			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "pdf"));
			header.set("Content-Disposition", "inline; filename=" + file.getName());
			header.setContentLength(document.length);
			return document;
		} else
			return null;
	}
	@RequestMapping("/deleteSelectedAttachment")
	public int deleteSelectedAttachment(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("message_attachment");
		ormDeleteRecord.setColumn_name("message_id");
		return phrService.deleteSelectedAttachment(ormDeleteRecord);
	}
	@RequestMapping("/getPHRActivityLog/{practiceId}/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> getPHRActivityLog(
			@PathVariable(value = "practiceId") String practiceId,
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMTwoColumnGeneric> lstHM = phrService.getPHRActivityLog(practiceId,patient_id);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/updatePHRLog")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updatePHRLog(
			@RequestBody ORMPHRAuditLog obj) {
		ServiceResponse<ORMKeyValue> resp = phrService.updatePHRLog(obj);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/updateLoginInformation")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateLoginInformation(
			@RequestBody ORMPHRLoginuser obj) {
		ServiceResponse<ORMKeyValue> resp = phrService.updateLoginInformation(obj);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/updatePHRLogout")
	public long updatePHRLogout(@RequestBody SearchCriteria searchCriteria) {
		return phrService.updatePHRLogout(searchCriteria);
	}
	@RequestMapping("/getPHRLoginLog/{practiceId}/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMLogin_log>> getPHRLoginLog(
			@PathVariable(value = "practiceId") String practiceId,
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMLogin_log> lstHM = phrService.getPHRLoginLog(practiceId,patient_id);
		return new ResponseEntity<List<ORMLogin_log>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getPatientPHRLabOrderSummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPHRLabOrder>> getPatientPHRLabOrderSummary(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMGetPHRLabOrder> lstHM = phrService.getPatientPHRLabOrderSummary(patient_id);
		return new ResponseEntity<List<ORMGetPHRLabOrder>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getSelectedPHRLabOrderResult/{orderID}")
	public @ResponseBody ResponseEntity<List<ORMLabResult>> getSelectedPHRLabOrderResult(
			@PathVariable(value = "orderID") String orderID) {
		List<ORMLabResult> lstHM = phrService.getSelectedPHRLabOrderResult(orderID);
		return new ResponseEntity<List<ORMLabResult>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getLabOrderResult/{orderID}")
	public @ResponseBody ResponseEntity<List<ORMLabResult>> getLabOrderResult(
			@PathVariable(value = "orderID") String orderID) {
		List<ORMLabResult> lstHM = phrService.getSelectedPHRLabOrderResult(orderID);
		return new ResponseEntity<List<ORMLabResult>>(lstHM, HttpStatus.OK);
	}
	
	@RequestMapping("/getLabAttachments/{orderID}")
	public @ResponseBody ResponseEntity<List<ORMGetPHRLabOrderAttachment>> getLabAttachments(
			@PathVariable(value = "orderID") String orderID){
		List<ORMGetPHRLabOrderAttachment> lst = phrService.getLabAttachments(orderID);
		return new ResponseEntity<List<ORMGetPHRLabOrderAttachment>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getLabResultRptHeader/{Order_ID}")
	public @ResponseBody ResponseEntity<List<ORMLabResultReportHeader>> getLabResultRptHeader(
			@PathVariable(value = "Order_ID") String Order_ID){
		List<ORMLabResultReportHeader> lst = phrService.getLabResultRptHeader(Order_ID);
		return new ResponseEntity<List<ORMLabResultReportHeader>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getLabRptOrderTest/{Order_ID}")
	public @ResponseBody ResponseEntity<List<ORMLabResultTest>> getLabRptOrderTest(
			@PathVariable(value = "Order_ID") String Order_ID){
		List<ORMLabResultTest> lst = phrService.getLabRptOrderTest(Order_ID);
		return new ResponseEntity<List<ORMLabResultTest>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getLabRptOrderDir/{Order_ID}")
	public @ResponseBody ResponseEntity<List<ORMGetphrLabOrderDirector>> getLabRptOrderDir(
			@PathVariable(value = "Order_ID") String Order_ID){
		List<ORMGetphrLabOrderDirector> lst = phrService.getLabRptOrderDir(Order_ID);
		return new ResponseEntity<List<ORMGetphrLabOrderDirector>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getLabRptOrderSourceVolume/{Order_ID}")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> getLabRptOrderSourceVolume(
			@PathVariable(value = "Order_ID") String Order_ID){
		List<ORMTwoColumnGeneric> lst = phrService.getLabRptOrderSourceVolume(Order_ID);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getGynMain/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORM_obgyn_main_info>> getGynMain(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORM_obgyn_main_info> lstHM = phrService.getGynMain(patient_id);
		return new ResponseEntity<List<ORM_obgyn_main_info>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/resetPassword")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> resetPassword(@RequestBody SearchCriteria searchCriteria) {
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		int result = phrService.resetPassword(searchCriteria);
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteRole.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
}
