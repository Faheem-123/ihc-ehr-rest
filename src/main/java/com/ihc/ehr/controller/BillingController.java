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
import com.ihc.ehr.model.ERAOperationResult;
import com.ihc.ehr.model.ERAPaymentPostingResponse;
import com.ihc.ehr.model.ORMBatchDetail;
import com.ihc.ehr.model.ORMClaim_Batch_Errors;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMDenialMessagesSave;
import com.ihc.ehr.model.ORMEOBSave;
import com.ihc.ehr.model.ORMEobAttachmentsSave;
import com.ihc.ehr.model.ORMEobDetailGet;
import com.ihc.ehr.model.ORMEraClaimDetailGet;
import com.ihc.ehr.model.ORMEraClaimListGet;
import com.ihc.ehr.model.ORMEraClaimServicesGet;
import com.ihc.ehr.model.ORMEraGet;
import com.ihc.ehr.model.ORMEraListGet;
import com.ihc.ehr.model.ORMEraProviderAdjustmentGet;
import com.ihc.ehr.model.ORMFourColumGeneric;
import com.ihc.ehr.model.ORMGetBatchClaimCount;
import com.ihc.ehr.model.ORMGetBillingSummaryClaims;
import com.ihc.ehr.model.ORMGetEOB;
import com.ihc.ehr.model.ORMEobAttachmentGet;
import com.ihc.ehr.model.ORMIdCodeDescriptionType;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMSaveBatch;
import com.ihc.ehr.model.ORMSaveClaimBatchDetail;
import com.ihc.ehr.model.ORMScalarValue;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORM_277ClaimStatusDetail;
import com.ihc.ehr.model.ORM_277_ResponseGet;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SubmissionProccessedClaimInfo;
import com.ihc.ehr.service.BillingService;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/billing")
public class BillingController {
	
	@Autowired
	BillingService billingService;

	@Autowired
	GeneralService generalService;

	@RequestMapping("/getClaimBillingSummary")
	public @ResponseBody ResponseEntity<List<ORMGetBillingSummaryClaims>> getClaimBillingSummary(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetBillingSummaryClaims> lst = billingService.getClaimBillingSummary(searchCriteria);
		return new ResponseEntity<List<ORMGetBillingSummaryClaims>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/addUpdateBatch")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> addUpdateBatch(@RequestBody ORMSaveBatch obj) {
		ServiceResponse<ORMKeyValue> resp = billingService.addUpdateBatch(obj);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("getUnlockBatch/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMFourColumGeneric>> getUnlockBatch(
			@PathVariable(value = "practice_id") String practice_id) {
		List<ORMFourColumGeneric> lst = billingService.getUnlockBatch(practice_id);
		return new ResponseEntity<List<ORMFourColumGeneric>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getClaimBatchCount")
	public @ResponseBody ResponseEntity<List<ORMGetBatchClaimCount>> getClaimBatchCount(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetBatchClaimCount> lst = billingService.getClaimBatchCount(searchCriteria);
		return new ResponseEntity<List<ORMGetBatchClaimCount>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getBatchClaimList/{batch_id}")
	public @ResponseBody ResponseEntity<List<ORMScalarValue>> getBatchClaimList(
			@PathVariable(value = "batch_id") String batch_id) {
		List<ORMScalarValue> lst = billingService.getBatchClaimList(batch_id);
		return new ResponseEntity<List<ORMScalarValue>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveClaimBatchDetail")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveClaimBatchDetail(
			@RequestBody List<ORMSaveClaimBatchDetail> lstSave) {
		ServiceResponse<ORMKeyValue> resp = billingService.saveClaimBatchDetail(lstSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("getBatchDetail/{practice_id}/{batch_id}")
	public @ResponseBody ResponseEntity<List<ORMBatchDetail>> getBatchDetail(
			@PathVariable(value = "practice_id") String practice_id,
			@PathVariable(value = "batch_id") String batch_id) {
		List<ORMBatchDetail> lst = billingService.getBatchDetail(practice_id, batch_id);
		return new ResponseEntity<List<ORMBatchDetail>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/deleteClaimBatch")
	public int deleteClaimBatch(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("claim_batch");
		ormDeleteRecord.setColumn_name("batch_id");
		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/lockBatch")
	public int lockBatch(@RequestBody SearchCriteria searchCriteria) {
		return billingService.lockBatch(searchCriteria);
	}

	@RequestMapping("/generateBatch_5010_P")
	public @ResponseBody ResponseEntity<List<ORMClaim_Batch_Errors>> generateBatch_5010_P(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMClaim_Batch_Errors> lst = billingService.generateBatch_5010_P(searchCriteria);
		return new ResponseEntity<List<ORMClaim_Batch_Errors>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getBatchPath/{batch_id}")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> getBatchPath(
			@PathVariable(value = "batch_id") String batch_id) {

		List<ORMTwoColumnGeneric> lst = billingService.getBatchPath(batch_id);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst, HttpStatus.OK);
	}

	@RequestMapping("IgnoreError/{error_id}/{user_id}")
	public int IgnoreError(@PathVariable(value = "error_id") String error_id,
			@PathVariable(value = "user_id") String user_id) {

		return billingService.IgnoreError(error_id, user_id);
	}

	@RequestMapping("/deleteClaimFromBatch")
	public int deleteClaimFromBatch(@RequestBody SearchCriteria searchCriteria) {
		return billingService.deleteClaimFromBatch(searchCriteria);
	}

	@RequestMapping("/uploadBatchToGatewayEDI")
	public String uploadBatchToGatewayEDI(@RequestBody SearchCriteria searchCriteria) {
		return billingService.uploadBatchToGatewayEDI(searchCriteria);
	}

	@RequestMapping("/downloadBatchResponse")
	public String downloadBatchResponse(@RequestBody SearchCriteria searchCriteria) {

		return billingService.downloadBatchResponse(searchCriteria);
	}

	@RequestMapping("/deleteDenialMessage")
	public int deleteDenialMessage(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("denial_messages");
		ormDeleteRecord.setColumn_name("denial_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/saveDenialMessage")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveDenialMessage(
			@RequestBody ORMDenialMessagesSave ormDenialMessagesSave) {

		ServiceResponse<ORMKeyValue> resp = billingService.saveDenialMessage(ormDenialMessagesSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/resolveDenialMessage")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> resolveDenialMessage(
			@RequestBody SearchCriteria searchCriteria) {

		ServiceResponse<ORMKeyValue> resp = billingService.resolveDenialMessage(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getEraList")
	public @ResponseBody ResponseEntity<List<ORMEraListGet>> getEraList(@RequestBody SearchCriteria searchCriteria) {
		List<ORMEraListGet> lst = billingService.getEraList(searchCriteria);
		return new ResponseEntity<List<ORMEraListGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getEraDetails/{era_id}")
	public @ResponseBody ResponseEntity<List<ORMEraGet>> getEraDetails(@PathVariable(value = "era_id") Long eraId) {
		List<ORMEraGet> lst = billingService.getEraDetails(eraId);
		return new ResponseEntity<List<ORMEraGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getEraClaimList")
	public @ResponseBody ResponseEntity<List<ORMEraClaimListGet>> getEraClaimList(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMEraClaimListGet> lst = billingService.getEraClaimList(searchCriteria);
		return new ResponseEntity<List<ORMEraClaimListGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getEraClaimDetail/{practice_id}/{era_claim_id}")
	public @ResponseBody ResponseEntity<ORMEraClaimDetailGet> getEraClaimDetail(
			@PathVariable(value = "practice_id") Long practiceId,
			@PathVariable(value = "era_claim_id") Long eraClaimId) {
		ORMEraClaimDetailGet obj = billingService.getEraClaimDetail(practiceId, eraClaimId);
		return new ResponseEntity<ORMEraClaimDetailGet>(obj, HttpStatus.OK);
	}

	@RequestMapping("getEraClaimServices/{era_claim_id}")
	public @ResponseBody ResponseEntity<List<ORMEraClaimServicesGet>> getEraClaimServices(
			@PathVariable(value = "era_claim_id") Long eraClaimId) {
		List<ORMEraClaimServicesGet> lst = billingService.getEraClaimServices(eraClaimId);
		return new ResponseEntity<List<ORMEraClaimServicesGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getAdjustCodesGlossary")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescriptionType>> getAdjustCodesGlossary(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getAdjustCodesGlossary: ");
		List<ORMIdCodeDescriptionType> lst = billingService.getAdjustCodesGlossary(searchCriteria);
		return new ResponseEntity<List<ORMIdCodeDescriptionType>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getEOB")
	public @ResponseBody ResponseEntity<List<ORMGetEOB>> getEOB(@RequestBody SearchCriteria searchCriteria) {

		List<ORMGetEOB> lst = billingService.getEOB(searchCriteria);
		return new ResponseEntity<List<ORMGetEOB>>(lst, HttpStatus.OK);

	}

	@RequestMapping("/getEOBAttachment")
	public @ResponseBody ResponseEntity<List<ORMEobAttachmentGet>> getEOBAttachment(
			@RequestBody SearchCriteria searchCriteria) {

		List<ORMEobAttachmentGet> lst = billingService.getEOBAttachment(searchCriteria);
		return new ResponseEntity<List<ORMEobAttachmentGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping(value = "/uploadEOB", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> uploadEOB(
			@RequestParam(required = false, value = "docFile") MultipartFile docFile,
			@RequestParam("EobData") String EobData, @RequestParam("EobAttach") String EobAttachmentData)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		ORMEOBSave Eob = mapper.readValue(EobData, ORMEOBSave.class);
		ORMEobAttachmentsSave EobAtt = mapper.readValue(EobAttachmentData, ORMEobAttachmentsSave.class);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		resp = billingService.uploadEOB(Eob, EobAtt, docFile);

		// if (result > 0) {
		// resp.setStatus(ServiceResponseStatus.SUCCESS);
		// // resp.setError_message("");
		// resp.setResult(result.toString());
		// } else {
		// resp.setStatus(ServiceResponseStatus.ERROR);
		// // resp.setError_message("An Error Occured while uploading document.");
		// }

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deleteEOB")
	public int deleteEOB(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		if (billingService.checkEOBPostedRecords(Long.parseLong(ormDeleteRecord.getColumn_id()))) {
			return -1;
		}
		ormDeleteRecord.setTable_name("eob");
		ormDeleteRecord.setColumn_name("eob_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("checkEOBPostedRecords/{eob_id}")
	public @ResponseBody Boolean checkEOBPostedRecords(@PathVariable(value = "eob_id") Long eob_id) {

		return billingService.checkEOBPostedRecords(eob_id);
	}

	@RequestMapping("getERAProviderAdjustment/{era_id}")
	public @ResponseBody ResponseEntity<List<ORMEraProviderAdjustmentGet>> getERAProviderAdjustment(
			@PathVariable(value = "era_id") Long eraId) {
		List<ORMEraProviderAdjustmentGet> lst = billingService.getERAProviderAdjustment(eraId);
		return new ResponseEntity<List<ORMEraProviderAdjustmentGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("mapEraClaimServicesIds")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> mapEraClaimServicesIDs(
			@RequestBody SearchCriteria searchCriteria) {

		ServiceResponse<ORMKeyValue> resp = billingService.mapEraClaimServicesIDs(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("importERAFromGatewayEdi")
	public ResponseEntity<ERAOperationResult> importERAFromGatewayEdi(@RequestBody SearchCriteria searchCriteria) {

		ERAOperationResult resp = billingService.importERAFromGatewayEdi(searchCriteria);
		return new ResponseEntity<ERAOperationResult>(resp, HttpStatus.OK);
	}

	@RequestMapping("importERAFromTextString")
	public ResponseEntity<ERAOperationResult> importERAFromTextString(@RequestBody SearchCriteria searchCriteria) {

		ERAOperationResult resp = billingService.importERAFromTextString(searchCriteria);
		return new ResponseEntity<ERAOperationResult>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deleteERA")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteERA(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ServiceResponse<ORMKeyValue> resp = billingService.deleteERA(ormDeleteRecord);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("mapEraClaimPaymentInsurance")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> mapEraClaimPaymentInsurance(
			@RequestBody SearchCriteria searchCriteria) {

		ServiceResponse<ORMKeyValue> resp = billingService.mapEraClaimPaymentInsurance(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("markAsPosted")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> markAsPosted(@RequestBody SearchCriteria searchCriteria) {

		ServiceResponse<ORMKeyValue> resp = billingService.markAsPosted(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("postERAPayment")
	public ResponseEntity<ERAPaymentPostingResponse> postERAPayment(@RequestBody SearchCriteria searchCriteria) {

		ERAPaymentPostingResponse resp = billingService.postERAPayment(searchCriteria);
		return new ResponseEntity<ERAPaymentPostingResponse>(resp, HttpStatus.OK);
	}

	@RequestMapping("moveEraToOtherPractice")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> moveEraToOtherPractice(
			@RequestBody SearchCriteria searchCriteria) {

		ServiceResponse<ORMKeyValue> resp = billingService.moveEraToOtherPractice(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/generateHCFA")
	public @ResponseBody ResponseEntity<ServiceResponse<SubmissionProccessedClaimInfo>> generateHCFA(
			@RequestBody SearchCriteria searchCriteria) {
		ServiceResponse<SubmissionProccessedClaimInfo> resp = billingService.generateHCFA(searchCriteria);
		return new ResponseEntity<ServiceResponse<SubmissionProccessedClaimInfo>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/updateProcessedClaims")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> updateProcessedClaims(
			@RequestBody List<SubmissionProccessedClaimInfo> objProcessedClaim) {
		ServiceResponse<ORMKeyValue> resp = billingService.updateProcessedClaims(objProcessedClaim);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getEobById/{eob_id}")
	public @ResponseBody ResponseEntity<ORMEobDetailGet> getEobById(
			@PathVariable(value = "eob_id") Long eobId) {

		ORMEobDetailGet obj = billingService.getEobById(eobId);
		return new ResponseEntity<ORMEobDetailGet>(obj, HttpStatus.OK);

	}
	
	
	@RequestMapping("/downloadClaimResponse")// 277
	public ResponseEntity<ServiceResponse<ORMKeyValue>> downloadClaimResponse(@RequestBody SearchCriteria searchCriteria) {

		ServiceResponse<ORMKeyValue> resp = billingService.downloadClaimResponse(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getEdiClaimStatus")
	public @ResponseBody ResponseEntity<List<ORM_277_ResponseGet>> getEdiClaimStatus(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORM_277_ResponseGet> lst = billingService.getEdiClaimStatus(searchCriteria);
		return new ResponseEntity<List<ORM_277_ResponseGet>>(lst, HttpStatus.OK);
	}
	
	
	@RequestMapping("/getEdiClaimStatusDetailByClaimId/{claim_id}")
	public @ResponseBody ResponseEntity<List<ORM_277ClaimStatusDetail>>  getEdiClaimStatusDetailByClaimId(
			@PathVariable(value = "claim_id") Long claimId) {

		List<ORM_277ClaimStatusDetail> lst= billingService.getEdiClaimStatusDetailByClaimId(claimId);
		return new ResponseEntity<List<ORM_277ClaimStatusDetail>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("markEdiClaimStatusAsWorked")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> markEdiClaimStatusAsWorked(@RequestBody SearchCriteria searchCriteria) {

		ServiceResponse<ORMKeyValue> resp = billingService.markEdiClaimStatusAsWorked(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping("getEobIdByCriteria")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> getEobIdByCriteria(@RequestBody SearchCriteria searchCriteria) {

		ServiceResponse<ORMKeyValue> resp = billingService.getEobIdByCriteria(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	
}
