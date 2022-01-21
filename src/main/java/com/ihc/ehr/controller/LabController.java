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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihc.ehr.model.LabOrderSaveWrapper;
import com.ihc.ehr.model.ORMCumulativeRpt;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMGetPatientResultPhysicianNotes;
import com.ihc.ehr.model.ORMGetPatientTestStaffNotes;
import com.ihc.ehr.model.ORMGetPracticeLabs;
import com.ihc.ehr.model.ORMIdName;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMLabFollowUpPatientList;
import com.ihc.ehr.model.ORMLabOrderAttachment;
import com.ihc.ehr.model.ORMLabOrderAttachmentComments;
import com.ihc.ehr.model.ORMLabOrderAttachmentSave;
import com.ihc.ehr.model.ORMLabOrderCategoryTest;
import com.ihc.ehr.model.ORMLabOrderComment;
import com.ihc.ehr.model.ORMLabOrderDetail;
import com.ihc.ehr.model.ORMLabOrderIcd;
import com.ihc.ehr.model.ORMLabOrderSearch;
import com.ihc.ehr.model.ORMLabOrderSpecimen;
import com.ihc.ehr.model.ORMLabOrderTest;
import com.ihc.ehr.model.ORMLabRequisition;
import com.ihc.ehr.model.ORMLabRequisitionInsurance;
import com.ihc.ehr.model.ORMLabRequisitionProc;
import com.ihc.ehr.model.ORMLabResult;
import com.ihc.ehr.model.ORMLabResultAbnormalFlags;
import com.ihc.ehr.model.ORMLabResultNotes;
import com.ihc.ehr.model.ORMLabResultSave;
import com.ihc.ehr.model.ORMLabResultStaffNotes;
import com.ihc.ehr.model.ORMLabResultStatus;
import com.ihc.ehr.model.ORMLabResultTest;
import com.ihc.ehr.model.ORMLabResultUnits;
import com.ihc.ehr.model.ORMLabSearchTest;
import com.ihc.ehr.model.ORMLabSpecimenSave;
import com.ihc.ehr.model.ORMPatientOrderSummaryView;
import com.ihc.ehr.model.ORMPendingAttachments;
import com.ihc.ehr.model.ORMPendingResults;
import com.ihc.ehr.model.ORMSignedAttachments;
import com.ihc.ehr.model.ORMSignedResults;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.ORMgetPatientTestDetails;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.WrapperLabResultSave;
import com.ihc.ehr.model.Wrapper_ObjectSave;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.service.LabService;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@RestController
@RequestMapping("/lab")
public class LabController {
	
	@Autowired
	LabService labservice;
	@Autowired
	GeneralService generalService;
	
	@RequestMapping("/getSearchLabOrder")
	public @ResponseBody ResponseEntity<List<ORMLabOrderSearch>> getSearchLabOrder(
			@RequestBody SearchCriteria searchCriteria)
	{	
		List<ORMLabOrderSearch> lst=labservice.getSearchLabOrder(searchCriteria);
		return new ResponseEntity<List<ORMLabOrderSearch>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/getSearchTest/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMLabSearchTest>> getSearchTest(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMLabSearchTest> lst=labservice.getSearchTest(order_id);
		return new ResponseEntity<List<ORMLabSearchTest>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/getPendingResults")
	public @ResponseBody ResponseEntity<List<ORMPendingResults>> getPendingResults(
			@RequestBody SearchCriteria searchCriteria)
	{	
		List<ORMPendingResults> lst=labservice.getPendingResults(searchCriteria);
		return new ResponseEntity<List<ORMPendingResults>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getPendingAttachments")
	public @ResponseBody ResponseEntity<List<ORMPendingAttachments>> getPendingAttachments(
			@RequestBody SearchCriteria searchCriteria)
	{	
		List<ORMPendingAttachments> lst=labservice.spGetPendingAttachments(searchCriteria);
		return new ResponseEntity<List<ORMPendingAttachments>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getLabCategory/{practiceID}")
	public @ResponseBody ResponseEntity<List<ORMThreeColum>> getLabCategory(
			@PathVariable(value="practiceID")  String practiceID)
	{
		List<ORMThreeColum> obj=labservice.getLabCategory(practiceID);
		return new ResponseEntity<List<ORMThreeColum>>(obj , HttpStatus.OK);
	}
	@RequestMapping("/getSignedResults")
	public @ResponseBody ResponseEntity<List<ORMSignedResults>> getSignedResults(
			@RequestBody SearchCriteria searchCriteria)
	{	
		List<ORMSignedResults> lst=labservice.getSignedResults(searchCriteria);
		return new ResponseEntity<List<ORMSignedResults>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getSignedAttachments")
	public @ResponseBody ResponseEntity<List<ORMSignedAttachments>> getSignedAttachments(
			@RequestBody SearchCriteria searchCriteria)
	{	
		List<ORMSignedAttachments> lst=labservice.spGetSignedAttachments(searchCriteria);
		return new ResponseEntity<List<ORMSignedAttachments>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getCumulativeRpt")
	public @ResponseBody ResponseEntity<List<ORMCumulativeRpt>> getCumulativeRpt(
			@RequestBody SearchCriteria searchCriteria)
	{	
		List<ORMCumulativeRpt> lst=labservice.getCumulativeRpt(searchCriteria);
		
		//Logic
		 
		return new ResponseEntity<List<ORMCumulativeRpt>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getpatientOrderSummaryView/{patientId}")
	public @ResponseBody ResponseEntity<List<ORMPatientOrderSummaryView>> getpatientOrderSummaryView(
			@PathVariable(value="patientId")  String patientId)
	{
		List<ORMPatientOrderSummaryView> obj=labservice.getpatientOrderSummaryView(patientId);
		return new ResponseEntity<List<ORMPatientOrderSummaryView>>(obj , HttpStatus.OK);
	}
	@RequestMapping("/getPatientOrderDetail/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMLabOrderDetail>> getPatientOrderDetail(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMLabOrderDetail> obj=labservice.getPatientOrderDetail(order_id);
		return new ResponseEntity<List<ORMLabOrderDetail>>(obj , HttpStatus.OK);
	}
	@RequestMapping("/getPracticeLab/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPracticeLabs>> GetPracticeLab(
			@PathVariable(value="practice_id")  String practice_id)
	{
		List<ORMGetPracticeLabs> lst=labservice.GetPracticeLab(practice_id);
		return new ResponseEntity<List<ORMGetPracticeLabs>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getLabGroupTest/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMLabOrderCategoryTest>> getLabGroupTest(
			@PathVariable(value="practice_id")  String practice_id)
	{
		List<ORMLabOrderCategoryTest> lst=labservice.getLabGroupTest(practice_id);
		return new ResponseEntity<List<ORMLabOrderCategoryTest>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getLabFacility/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdName>> getLabFacility(
			@PathVariable(value="practice_id")  String practice_id)
	{
		List<ORMIdName> lst=labservice.getLabFacility(practice_id);
		return new ResponseEntity<List<ORMIdName>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/deleteLabOrder")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteLabOrder(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteLabOrder: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("patient_order");
		ormDeleteRecord.setColumn_name("order_id");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteLabOrder.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("/saveLabOrder")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveLabOrder(
			@RequestBody LabOrderSaveWrapper wrapper_order) {
		GeneralOperations.logMsg("Inside saveLabOrder: ");		

		ServiceResponse<ORMKeyValue> resp=labservice.saveLabOrder(wrapper_order); 		
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
		
	}	

	@RequestMapping("/getOrderTest/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMLabOrderTest>> getOrderTest(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMLabOrderTest> lst=labservice.getOrderTest(order_id);
		return new ResponseEntity<List<ORMLabOrderTest>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getOrderICD/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMLabOrderIcd>> getOrderICD(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMLabOrderIcd> lst=labservice.getOrderICD(order_id);
		return new ResponseEntity<List<ORMLabOrderIcd>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getOrderComments/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMLabOrderComment>> getOrderComments(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMLabOrderComment> lst=labservice.getOrderComments(order_id);
		return new ResponseEntity<List<ORMLabOrderComment>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getSpecimenInfo/{test_id}")
	public @ResponseBody ResponseEntity<List<ORMLabOrderSpecimen>> getSpecimenInfo(
			@PathVariable(value="test_id")  String test_id)
	{
		List<ORMLabOrderSpecimen> lst=labservice.getSpecimenInfo(test_id);
		return new ResponseEntity<List<ORMLabOrderSpecimen>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/saveSpecimen")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveSpecimen(@RequestBody ORMLabSpecimenSave obj) {
		Long result=labservice.saveSpecimen(obj);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter Health Maint detail.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		

	}
	@RequestMapping("/deleteSpecimen")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSpecimen(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteLabOrder: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("patient_order_specimen");
		ormDeleteRecord.setColumn_name("patient_specimen_id");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSpecimen");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
	}
	@RequestMapping("/getTestResult/{test_id}")
	public @ResponseBody ResponseEntity<List<ORMLabResult>> getTestResult(
			@PathVariable(value="test_id")  String test_id)
	{
		List<ORMLabResult> lst=labservice.getTestResult(test_id);
		return new ResponseEntity<List<ORMLabResult>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getTestAttachments/{test_id}/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMLabOrderAttachment>> getTestAttachments(
			@PathVariable(value="test_id")  String test_id,@PathVariable(value="order_id")  String order_id)
	{
		System.out.println("getTestAttachments: order_id " + order_id);
		List<ORMLabOrderAttachment> lst=labservice.getTestAttachments(test_id,order_id);
		return new ResponseEntity<List<ORMLabOrderAttachment>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getResultTest/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMLabResultTest>> getResultTest(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMLabResultTest> lst=labservice.getResultTest(order_id);
		return new ResponseEntity<List<ORMLabResultTest>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/saveResults")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveResults(@RequestBody ORMLabResultSave obj) {
		Long result=labservice.saveResults(obj);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving saveResultsl.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveResultData", method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveResultData(
			@RequestBody WrapperLabResultSave wrapperLabResultSave){
		
		Long result = labservice.saveResultData(wrapperLabResultSave);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);		
			resp.setResult(result.toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteResults")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteResults(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteResults: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("patient_order_results");
		ormDeleteRecord.setColumn_name("result_id");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteResults");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
	}
	@RequestMapping(value = "/saveLabAttachment", method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveLabAttachment(
			@RequestParam("attData") String attData,
			@RequestParam("comData") String comData)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		//ORMSaveDocument patDoc = mapper.readValue(docData, ORMSaveDocument.class);
		ORMLabOrderAttachmentSave labAttachment = mapper.readValue(attData, ORMLabOrderAttachmentSave.class);
		ORMLabOrderAttachmentComments labAttachmentcmnt = mapper.readValue(comData, ORMLabOrderAttachmentComments.class);
		
		//String doc_id=generalService.SaveDocument(patDoc, docFile, "PatientDocuments").toString();

		//labAttachment.setPatient_document_id(doc_id);
		Long result = labservice.saveLabAttachment(labAttachment, labAttachmentcmnt);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			// resp.setError_message("");
			resp.setResult(result.toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			// resp.setError_message("An Error Occured while uploading document.");
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
//	@RequestMapping("/saveLabAttachment")
//	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveLabAttachment(@RequestBody ORMLabOrderAttachmentSave obj,
//			@RequestBody  ORMSaveDocument objDoc,
//			@RequestBody ORMLabOrderAttachmentComments obj) 
//	{
//		Long result=labservice.saveLabAttachment(obj);
//		
//		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
//		if (result == 0) {
//			resp.setStatus(ServiceResponseStatus.ERROR);
//			resp.setResponse("An Error Occured while saving saveResultsl.");
//		} else {
//			resp.setStatus(ServiceResponseStatus.SUCCESS);
//			resp.setResult(Long.toString(result));
//		}
//		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
//	}
	@RequestMapping("/deleteAttachment")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteAttachment(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteResults: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("patient_order_attachment");
		ormDeleteRecord.setColumn_name("patient_order_attachment_id");

		int result= generalService.deleteRecord(ormDeleteRecord);
		System.out.println("deleteResults Return: " + result);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteAttachment");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
	}
	
	
	@RequestMapping("/getResultUnits")
	public @ResponseBody ResponseEntity<List<ORMLabResultUnits>> getResultUnits()
	{
		List<ORMLabResultUnits> lst=labservice.getResultUnits();
		return new ResponseEntity<List<ORMLabResultUnits>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getResultStatus")
	public @ResponseBody ResponseEntity<List<ORMLabResultStatus>> getResultStatus()
	{
		List<ORMLabResultStatus> lst=labservice.getResultStatus();
		return new ResponseEntity<List<ORMLabResultStatus>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/getResultAbnormalRange")
	public @ResponseBody ResponseEntity<List<ORMLabResultAbnormalFlags>> getResultAbnormalRange()
	{
		List<ORMLabResultAbnormalFlags> lst=labservice.getResultAbnormalRange();
		return new ResponseEntity<List<ORMLabResultAbnormalFlags>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getResultStafNotes/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMLabResultStaffNotes>> getResultStafNotes(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMLabResultStaffNotes> lst=labservice.getResultStafNotes(order_id);
		return new ResponseEntity<List<ORMLabResultStaffNotes>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/signLabOrder")
	public long signEncounter(@RequestBody SearchCriteria searchCriteria) {
		return labservice.signLabOrder(searchCriteria);

	}
	@RequestMapping("/updateOrderFollowup")
	public long updateOrderFollowup(@RequestBody SearchCriteria searchCriteria) {
		return labservice.updateOrderFollowup(searchCriteria);

	}
	@RequestMapping("/updateCommentStatus")
	public long updateCommentStatus(@RequestBody Wrapper_ObjectSave<ORMLabOrderComment> obj) {
		return labservice.updateCommentStatus(obj);
	}
	@RequestMapping("/saveAttachmentComments")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveAttachmentComments(@RequestBody ORMLabOrderAttachmentComments obj) {
		Long result=labservice.saveAttachmentComments(obj);
		
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving saveAttachmentComments.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
	}
	@RequestMapping("/saveResultComments")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveResultComments(@RequestBody ORMLabResultNotes obj) {
		Long result=labservice.saveResultComments(obj);
		
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving saveResultComments.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
	}
	@RequestMapping("/getReqReport/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMLabRequisition>> getReqReport(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMLabRequisition> lst=labservice.getReqReport(order_id);
		return new ResponseEntity<List<ORMLabRequisition>>(lst , HttpStatus.OK);
	}	 
	@RequestMapping("/getReqReportIns/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMLabRequisitionInsurance>> getReqReportIns(
			@PathVariable(value="patient_id")  String patient_id)
	{
		List<ORMLabRequisitionInsurance> lst=labservice.getReqReportIns(patient_id);
		return new ResponseEntity<List<ORMLabRequisitionInsurance>>(lst , HttpStatus.OK);
	}	
	@RequestMapping("/getReqReportLabCode/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMLabRequisitionProc>> getReqReportLabCode(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMLabRequisitionProc> lst=labservice.getReqReportLabCode(order_id);
		return new ResponseEntity<List<ORMLabRequisitionProc>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/GetOrderFollowUpNotes/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMThreeColumGeneric>> GetOrderFollowUpNotes(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMThreeColumGeneric> lst=labservice.GetOrderFollowUpNotes(order_id);
		return new ResponseEntity<List<ORMThreeColumGeneric>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/getLabFollowupPatients")
	public @ResponseBody ResponseEntity<List<ORMLabFollowUpPatientList>> getLabFollowupPatients(
			@RequestBody SearchCriteria searchCriteria)
	{	
		List<ORMLabFollowUpPatientList> lst=labservice.getLabFollowupPatients(searchCriteria);
		return new ResponseEntity<List<ORMLabFollowUpPatientList>>(lst , HttpStatus.OK);
	}	
	@RequestMapping("/getLabFollowupPatientTestDetail")
	public @ResponseBody ResponseEntity<List<ORMgetPatientTestDetails>> getLabFollowupPatientTestDetail(
			@RequestBody SearchCriteria searchCriteria)
	{	
		List<ORMgetPatientTestDetails> lst=labservice.getLabFollowupPatientTestDetail(searchCriteria);
		return new ResponseEntity<List<ORMgetPatientTestDetails>>(lst , HttpStatus.OK);
	}	
	@RequestMapping("/getPatientLabsStaffNotes/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientTestStaffNotes>> getPatientLabsStaffNotes(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMGetPatientTestStaffNotes> lst=labservice.getPatientLabsStaffNotes(order_id);
		return new ResponseEntity<List<ORMGetPatientTestStaffNotes>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/getPatientLabsPhysicianNotes/{order_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientResultPhysicianNotes>> getPatientLabsPhysicianNotes(
			@PathVariable(value="order_id")  String order_id)
	{
		List<ORMGetPatientResultPhysicianNotes> lst=labservice.getPatientLabsPhysicianNotes(order_id);
		return new ResponseEntity<List<ORMGetPatientResultPhysicianNotes>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/sendDrugAbuse")
	public long sendDrugAbuse(@RequestBody SearchCriteria searchCriteria){
		return labservice.sendDrugAbuse(searchCriteria);
	}
}
