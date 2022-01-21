package com.ihc.ehr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihc.ehr.model.ClaimStatusUpdateModel;
import com.ihc.ehr.model.ORMBillingProviderTaxomonyListGet;
import com.ihc.ehr.model.ORMClaimDiagnosisGet_Pro;
import com.ihc.ehr.model.ORMClaimGet_Pro;
import com.ihc.ehr.model.ORMClaimInsuranceGet;
import com.ihc.ehr.model.ORMClaimNotesGet;
import com.ihc.ehr.model.ORMClaimNotesSave;
import com.ihc.ehr.model.ORMClaimPaymentAdjustmentGet;
import com.ihc.ehr.model.ORMClaimPaymentGet;
import com.ihc.ehr.model.ORMClaimProceduresGet_Pro;
import com.ihc.ehr.model.ORMClaimReminderGet;
import com.ihc.ehr.model.ORMClaimReminderSave;
import com.ihc.ehr.model.ORMClaimSummary;
import com.ihc.ehr.model.ORMEobEraCheckDetails;
import com.ihc.ehr.model.ORMEobGet;
import com.ihc.ehr.model.ORMEraPaymentInfoGet;
import com.ihc.ehr.model.ORMFacilityList;
import com.ihc.ehr.model.ORMGetAttorneyDetail;
import com.ihc.ehr.model.ORMGetCPTWisePatientPaymentForRefund;
import com.ihc.ehr.model.ORMGetCPTsWithBalance;
import com.ihc.ehr.model.ORMGetClaimImportDiagnosis;
import com.ihc.ehr.model.ORMGetClaimImportLabOrder;
import com.ihc.ehr.model.ORMGetClaimImportProcedures;
import com.ihc.ehr.model.ORMGetClaimPostedPayment;
import com.ihc.ehr.model.ORMGetClaimRulePatientInfo;
import com.ihc.ehr.model.ORMGetClaimSuperBillDetail;
import com.ihc.ehr.model.ORMGetPatientStatement;
import com.ihc.ehr.model.ORMGetPatientStatementLog;
import com.ihc.ehr.model.ORMGetPaymentInsurances;
import com.ihc.ehr.model.ORMGetPreviousCalimImport;
import com.ihc.ehr.model.ORMGetProcedureSearch;
import com.ihc.ehr.model.ORMGetProcesuresForPosting;
import com.ihc.ehr.model.ORMGetStatementDetail;
import com.ihc.ehr.model.ORMGetStatementPdf;
import com.ihc.ehr.model.ORMGetSuperBillCategoryList;
import com.ihc.ehr.model.ORMIdCode;
import com.ihc.ehr.model.ORMIdCodeDescription;
import com.ihc.ehr.model.ORMIdCodeDescriptionType;
import com.ihc.ehr.model.ORMIdName;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientStatementNotes;
import com.ihc.ehr.model.ORMSavePatientAttorney_log;
import com.ihc.ehr.model.ORMSavePatientStatement_log;
import com.ihc.ehr.model.ORMStatementPDF;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORMspGetPatientClaims;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.Wrapper_ClaimPaymentSave;
import com.ihc.ehr.model.Wrapper_ClaimSave_Pro;
import com.ihc.ehr.service.ClaimService;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/claim")
public class ClaimController {

	@Autowired
	ClaimService claimService;

	@RequestMapping("/getClaimSummary/{patient_id}/{dueOnly}/{showDeleted}")
	public @ResponseBody ResponseEntity<List<ORMClaimSummary>> getClaimSummary(
			@PathVariable(value = "patient_id") Long patient_id, @PathVariable(value = "dueOnly") Boolean dueOnly,
			@PathVariable(value = "showDeleted") Boolean showDeleted) {
		List<ORMClaimSummary> lst = claimService.getClaimSummary(patient_id, dueOnly, showDeleted);
		return new ResponseEntity<List<ORMClaimSummary>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getFacilityList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMFacilityList>> getFacilityList(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMFacilityList> lst = claimService.getFacilityList(practice_id);
		return new ResponseEntity<List<ORMFacilityList>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPracticePOSList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getPracticePOSList(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMIdCodeDescription> lst = claimService.getPracticePOSList(practice_id);
		return new ResponseEntity<List<ORMIdCodeDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getModifierList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getModifierList() {
		List<ORMIdCodeDescription> lst = claimService.getModifierList();
		return new ResponseEntity<List<ORMIdCodeDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getClaim/{claim_id}")
	public @ResponseBody ResponseEntity<ORMClaimGet_Pro> getClaim(@PathVariable(value = "claim_id") Long claim_id) {

		ORMClaimGet_Pro obj = claimService.getClaim(claim_id);
		return new ResponseEntity<ORMClaimGet_Pro>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getProClaimDiagnosis/{claim_id}/{showDeleted}")
	public @ResponseBody ResponseEntity<List<ORMClaimDiagnosisGet_Pro>> getProClaimDiagnosis(
			@PathVariable(value = "claim_id") Long claim_id,
			@PathVariable(value = "showDeleted") Boolean showDeleted) {
		List<ORMClaimDiagnosisGet_Pro> lst = claimService.getProClaimDiagnosis(claim_id,showDeleted);
		return new ResponseEntity<List<ORMClaimDiagnosisGet_Pro>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getProClaimProcedures/{claim_id}/{showDeleted}")
	public @ResponseBody ResponseEntity<List<ORMClaimProceduresGet_Pro>> getProClaimProcedures(
			@PathVariable(value = "claim_id") Long claim_id,
			@PathVariable(value = "showDeleted") Boolean showDeleted) {
		List<ORMClaimProceduresGet_Pro> lst = claimService.getProClaimProcedures(claim_id,showDeleted);
		return new ResponseEntity<List<ORMClaimProceduresGet_Pro>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getClaimInsurance/{claim_id}/{showDeleted}")
	public @ResponseBody ResponseEntity<List<ORMClaimInsuranceGet>> getClaimInsurance(
			@PathVariable(value = "claim_id") Long claim_id,
			@PathVariable(value = "showDeleted") Boolean showDeleted) {
		List<ORMClaimInsuranceGet> lst = claimService.getClaimInsurance(claim_id,showDeleted);
		return new ResponseEntity<List<ORMClaimInsuranceGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getClaimRulePatientInfo/{patient_id}")
	public @ResponseBody ResponseEntity<ORMGetClaimRulePatientInfo> getClaimRulePatientInfo(
			@PathVariable(value = "patient_id") Long patient_id) {
		ORMGetClaimRulePatientInfo obj = claimService.getClaimRulePatientInfo(patient_id);
		return new ResponseEntity<ORMGetClaimRulePatientInfo>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getClaimImportProc")
	public @ResponseBody ResponseEntity<List<ORMGetClaimImportProcedures>> getClaimImportProc(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getClaimImportProc: ");

		List<ORMGetClaimImportProcedures> lst = claimService.getClaimImportProc(searchCriteria);
		return new ResponseEntity<List<ORMGetClaimImportProcedures>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getClaimImportDiag")
	public @ResponseBody ResponseEntity<List<ORMGetClaimImportDiagnosis>> getClaimImportDiag(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getClaimImportDiag: ");

		List<ORMGetClaimImportDiagnosis> lst = claimService.getClaimImportDiag(searchCriteria);
		return new ResponseEntity<List<ORMGetClaimImportDiagnosis>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getClaimImportLabOrders")
	public @ResponseBody ResponseEntity<List<ORMGetClaimImportLabOrder>> getClaimImportLabOrders(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetClaimImportLabOrder> lst = claimService.getClaimImportLabOrders(searchCriteria);
		return new ResponseEntity<List<ORMGetClaimImportLabOrder>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getSuperBillList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdName>> getSuperBillList(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMIdName> lst = claimService.getSuperBillList(practice_id);
		return new ResponseEntity<List<ORMIdName>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/getSuperBillEncounterList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdName>> getSuperBillEncounterList(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMIdName> lst = claimService.getSuperBillEncounterList(practice_id);
		return new ResponseEntity<List<ORMIdName>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getSuperBillCatList/{bill_id}")
	public @ResponseBody ResponseEntity<List<ORMGetSuperBillCategoryList>> getSuperBillCategoriesList(
			@PathVariable(value = "bill_id") Long bill_id) {
		List<ORMGetSuperBillCategoryList> lst = claimService.getSuperBillCategoriesList(bill_id);
		return new ResponseEntity<List<ORMGetSuperBillCategoryList>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getSuperBillDetails")
	public @ResponseBody ResponseEntity<List<ORMGetClaimSuperBillDetail>> getSuperBillDetails(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetClaimSuperBillDetail> lst = claimService.getSuperBillDetails(searchCriteria);
		return new ResponseEntity<List<ORMGetClaimSuperBillDetail>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getChartFollowUpDiagnosis")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescriptionType>> getChartFollowUpDiagnosis(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMIdCodeDescriptionType> lst = claimService.getChartFollowUpDiagnosis(searchCriteria);
		return new ResponseEntity<List<ORMIdCodeDescriptionType>>(lst, HttpStatus.OK);

	}

	@RequestMapping("/getChartFollowUpProcedures")
	public @ResponseBody ResponseEntity<List<ORMGetProcedureSearch>> getChartFollowUpProcedures(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getChartFollowUpProcedures: ");

		List<ORMGetProcedureSearch> lst = claimService.getChartFollowUpProcedures(searchCriteria);
		return new ResponseEntity<List<ORMGetProcedureSearch>>(lst, HttpStatus.OK);

	}

	@RequestMapping("/getClaimNotes/{claim_id}/{user}/{editable_only}")
	public @ResponseBody ResponseEntity<List<ORMClaimNotesGet>> getClaimNotes(
			@PathVariable(value = "claim_id") Long claim_id, @PathVariable(value = "user") String user,
			@PathVariable(value = "editable_only") String editableOnly) {
		GeneralOperations.logMsg("Inside getClaimNotes: ");

		List<ORMClaimNotesGet> lst = claimService.getClaimNotes(claim_id, user, editableOnly);
		return new ResponseEntity<List<ORMClaimNotesGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveClaimNotes/{add_to_patient_notes}")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveClaimNotes(
			@PathVariable(value = "add_to_patient_notes") Boolean add_to_patient_notes,
			@RequestBody ORMClaimNotesSave ormClaimNotesSave) {
		GeneralOperations.logMsg("Inside saveClaimNotes: ");

		ServiceResponse<ORMKeyValue> resp = claimService.saveClaimNotes(ormClaimNotesSave, add_to_patient_notes);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/saveClaimPro")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveClaimPro(
			@RequestBody Wrapper_ClaimSave_Pro wrapper_ClaimSave_Pro) {
		GeneralOperations.logMsg("Inside saveClaimPro: ");

		ServiceResponse<ORMKeyValue> resp = claimService.saveClaimPro(wrapper_ClaimSave_Pro);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/getClaimAdjustmentGroupCodesList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getClaimAdjustmentGroupCodesList(@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getClaimAdjustmentGroupCodesList: ");

		List<ORMIdCodeDescription> lst = claimService.getClaimAdjustmentGroupCodesList(searchCriteria);
		return new ResponseEntity<List<ORMIdCodeDescription>>(lst, HttpStatus.OK);

	}

	@RequestMapping("/getClaimAdjustmentReasonCodesList")
	public @ResponseBody ResponseEntity<List<ORMIdCode>> getClaimAdjustmentReasonCodesList() {
		GeneralOperations.logMsg("Inside getClaimAdjustmentReasonCodesList: ");

		List<ORMIdCode> lst = claimService.getClaimAdjustmentReasonCodesList();
		return new ResponseEntity<List<ORMIdCode>>(lst, HttpStatus.OK);

	}

	@RequestMapping("/getProceduresForPosting")
	public @ResponseBody ResponseEntity<List<ORMGetProcesuresForPosting>> getProceduresForPosting(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getPaymentProcedures: ");

		List<ORMGetProcesuresForPosting> lst = claimService.getProceduresForPosting(searchCriteria);
		return new ResponseEntity<List<ORMGetProcesuresForPosting>>(lst, HttpStatus.OK);

	}

	@RequestMapping("/getPaymentInsurances/{claim_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPaymentInsurances>> getPaymentInsurances(
			@PathVariable(value = "claim_id") Long claim_id) {
		GeneralOperations.logMsg("Inside getPaymentInsurances: ");

		List<ORMGetPaymentInsurances> lst = claimService.getPaymentInsurances(claim_id);
		return new ResponseEntity<List<ORMGetPaymentInsurances>>(lst, HttpStatus.OK);

	}

	@RequestMapping("/getEobEraCheckDetailsById")
	public @ResponseBody ResponseEntity<ORMEobEraCheckDetails> getEobEraCheckDetailsById(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getEobEraCheckDetailsById: ");

		ORMEobEraCheckDetails obj = claimService.getEobEraCheckDetailsById(searchCriteria);
		return new ResponseEntity<ORMEobEraCheckDetails>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getClaimPayment/{claim_id}/{showDeleted}")
	public @ResponseBody ResponseEntity<List<ORMClaimPaymentGet>> getClaimPayment(
			@PathVariable(value = "claim_id") Long claim_id,
			@PathVariable(value = "showDeleted") Boolean showDeleted) {
		GeneralOperations.logMsg("Inside getClaimPayment: ");

		List<ORMClaimPaymentGet> lst = claimService.getClaimPayment(claim_id,showDeleted);
		return new ResponseEntity<List<ORMClaimPaymentGet>>(lst, HttpStatus.OK);

	}

	@RequestMapping("/getClaimPaymentAdjustments/{claim_id}")
	public @ResponseBody ResponseEntity<List<ORMClaimPaymentAdjustmentGet>> getClaimPaymentAdjustments(
			@PathVariable(value = "claim_id") Long claim_id) {
		GeneralOperations.logMsg("Inside getClaimPaymentAdjustments: ");

		List<ORMClaimPaymentAdjustmentGet> lst = claimService.getClaimPaymentAdjustments(claim_id);
		return new ResponseEntity<List<ORMClaimPaymentAdjustmentGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveClaimPayment")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveClaimPayment(
			@RequestBody Wrapper_ClaimPaymentSave wrapperClaimPaymentSave) {
		GeneralOperations.logMsg("Inside saveClaimPayment: ");

		ServiceResponse<ORMKeyValue> resp = claimService.saveClaimPayment(wrapperClaimPaymentSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/rectifyPayment")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> rectifyPayment(@RequestBody List<ORMKeyValue> lstKeyValue) {
		GeneralOperations.logMsg("Inside rectifyPayment: ");

		ServiceResponse<ORMKeyValue> resp = claimService.rectifyPayment(lstKeyValue);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	
	@RequestMapping("/getClaimPostedPayment/{eob_era_id}/{eob_era_id_type}")
	public @ResponseBody ResponseEntity<List<ORMGetClaimPostedPayment>> getClaimPostedPayment(
			@PathVariable(value = "eob_era_id") Long eob_era_id,
			@PathVariable(value = "eob_era_id_type") String eob_era_id_type) {
		
		GeneralOperations.logMsg("Inside getClaimPostedPayment");

		List<ORMGetClaimPostedPayment> lst = claimService.getClaimPostedPayment(eob_era_id,eob_era_id_type);
		return new ResponseEntity<List<ORMGetClaimPostedPayment>>(lst, HttpStatus.OK);

	}
	
	@RequestMapping("/getEOBInfoByClaimId/{claim_id}")
	public @ResponseBody ResponseEntity<List<ORMEobGet>> getEOBInfoByClaimId(
			@PathVariable(value = "claim_id") Long claim_id) {
		
		GeneralOperations.logMsg("Inside getEOBInfoByClaimId");

		List<ORMEobGet> lst = claimService.getEOBInfoByClaimId(claim_id);
		return new ResponseEntity<List<ORMEobGet>>(lst, HttpStatus.OK);

	}
	
	@RequestMapping("/getERAPaymentInfoByClaimId/{claim_id}")
	public @ResponseBody ResponseEntity<List<ORMEraPaymentInfoGet>> getERAPaymentInfoByClaimId(
			@PathVariable(value = "claim_id") Long claim_id) {
		
		GeneralOperations.logMsg("Inside getERAPaymentInfoByClaimId");

		List<ORMEraPaymentInfoGet> lst = claimService.getERAPaymentInfoByClaimId(claim_id);
		return new ResponseEntity<List<ORMEraPaymentInfoGet>>(lst, HttpStatus.OK);

	}
	
	@RequestMapping("/deleteClaim")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue> > deleteClaim(
			@RequestBody SearchCriteria searchCriteria
	) {
		
		GeneralOperations.logMsg("Inside deleteClaim: ");
		ServiceResponse<ORMKeyValue> resp = claimService.deleteClaim(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);		
	}
	
	@RequestMapping("/updateClaimStatus")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue> > updateClaimStatus(
			@RequestBody ClaimStatusUpdateModel claimStatusUpdateModel
	) {
		
		GeneralOperations.logMsg("Inside updateClaimStatus: ");
		ServiceResponse<ORMKeyValue> resp = claimService.updateClaimStatus(claimStatusUpdateModel);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);		
	}
	@RequestMapping("/getPatientStatement")
	public @ResponseBody ResponseEntity<List<ORMGetPatientStatement>> getPatientStatement(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPatientStatement> lst = claimService.getPatientStatement(searchCriteria);
		return new ResponseEntity<List<ORMGetPatientStatement>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/getStatementDetail")
	public @ResponseBody ResponseEntity<List<ORMGetStatementDetail>> getStatementDetail(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetStatementDetail> lst = claimService.getStatementDetail(searchCriteria);
		return new ResponseEntity<List<ORMGetStatementDetail>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/getStatementPDF")
	public @ResponseBody ResponseEntity<List<ORMGetStatementPdf>> getStatementPDF(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetStatementPdf> lst = claimService.getStatementPDF(searchCriteria);
		return new ResponseEntity<List<ORMGetStatementPdf>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/saveStatementLog")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveStatementLog(
			@RequestBody List<ORMSavePatientStatement_log> lstSave) {
		
		ServiceResponse<ORMKeyValue> resp = claimService.saveStatementLog(lstSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/getPrintAttorneyDetail")
	public @ResponseBody ResponseEntity<List<ORMGetAttorneyDetail>> getPrintAttorneyDetail(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetAttorneyDetail> lst = claimService.getPrintAttorneyDetail(searchCriteria);
		return new ResponseEntity<List<ORMGetAttorneyDetail>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/saveAttorneyLog")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveAttorneyLog(
			@RequestBody List<ORMSavePatientAttorney_log> lstSave) {
		
		ServiceResponse<ORMKeyValue> resp = claimService.saveAttorneyLog(lstSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/getPatientSelfPay/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> getPatientSelfPay(
			@PathVariable(value = "patient_id") Long patient_id) {
		
		List<ORMTwoColumnGeneric> lst = claimService.getPatientSelfPay(patient_id);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst, HttpStatus.OK);

	}
	
	@RequestMapping("/getProceduresForPatientRefund")
	public @ResponseBody ResponseEntity<List<ORMGetCPTWisePatientPaymentForRefund>> getProceduresForPatientRefund(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getProceduresForPatientRefund: ");

		List<ORMGetCPTWisePatientPaymentForRefund> lst = claimService.getProceduresForPatientRefund(searchCriteria);
		return new ResponseEntity<List<ORMGetCPTWisePatientPaymentForRefund>>(lst, HttpStatus.OK);

	}
	@RequestMapping("/saveStatementNotes")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveHealthMaintDetail(
			@RequestBody List<ORMPatientStatementNotes> objDetail) {
		Long result = claimService.saveStatementNotes(objDetail);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving saveStatementNotes");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/getPatientStatementSavedNotes")
	public @ResponseBody ResponseEntity<List<ORMPatientStatementNotes>> getPatientStatementSavedNotes(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMPatientStatementNotes> lst = claimService.getPatientStatementSavedNotes(searchCriteria);
		return new ResponseEntity<List<ORMPatientStatementNotes>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/generatePDFStatement")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> generatePDFStatement(
			@RequestBody List<ORMStatementPDF> objDetail) {
		String result = claimService.generatePDFStatement(objDetail);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result.equals("")) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while generatePDFStatement");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(result);
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/generateStatement")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> generateStatement(
			@RequestBody List<ORMspGetPatientClaims> objDetail) {
		String result = claimService.generateStatement(objDetail);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result.equals("")) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while generateStatement");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(result);
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/getPatientStatementLog/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientStatementLog>> getPatientStatementLog(
			@PathVariable(value = "patient_id") Long patient_id) {
		List<ORMGetPatientStatementLog> lst = claimService.getPatientStatementLog(patient_id);
		return new ResponseEntity<List<ORMGetPatientStatementLog>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/getCPTsWithBalance")
	public @ResponseBody ResponseEntity<List<ORMGetCPTsWithBalance>> getCPTsWithBalance(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetCPTsWithBalance> lst = claimService.getCPTsWithBalance(searchCriteria);
		return new ResponseEntity<List<ORMGetCPTsWithBalance>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/getPreviousCalimImport")
	public @ResponseBody ResponseEntity<List<ORMGetPreviousCalimImport>> getPreviousCalimImport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPreviousCalimImport> lst = claimService.getPreviousCalimImport(searchCriteria);
		return new ResponseEntity<List<ORMGetPreviousCalimImport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/GetBillingProviderTaxonomyList/{practice_id}/{billing_provider_id}")
	public @ResponseBody ResponseEntity<List<ORMBillingProviderTaxomonyListGet>> GetBillingProviderTaxonomyList(
			@PathVariable(value = "practice_id") Long practice_id,
			@PathVariable(value = "billing_provider_id") Long billing_provider_id) {
		List<ORMBillingProviderTaxomonyListGet> lst = claimService.GetBillingProviderTaxonomyList(practice_id,billing_provider_id);
		return new ResponseEntity<List<ORMBillingProviderTaxomonyListGet>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/getClaimRemdinder/{practice_id}/{claim_id}")
	public @ResponseBody ResponseEntity<List<ORMClaimReminderGet>> getClaimRemdinder(
			@PathVariable(value = "practice_id") Long practiceId,
			@PathVariable(value = "claim_id") Long claimId) {
		List<ORMClaimReminderGet> lst = claimService.getClaimRemdinder(practiceId,claimId);
		return new ResponseEntity<List<ORMClaimReminderGet>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/saveClaimReminder")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveClaimReminder(
			@RequestBody ORMClaimReminderSave ormClaimReminderSave) {
		GeneralOperations.logMsg("Inside saveClaimReminder: ");

		ServiceResponse<ORMKeyValue> resp = claimService.saveClaimReminder(ormClaimReminderSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}
	
	
	@RequestMapping("/resolveClaimReminder")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>>  resolveClaimReminder(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside resolveClaimReminder: ");

		ServiceResponse<ORMKeyValue> resp = claimService.resolveClaimReminder(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	
	/*
	 public String SaveClaimReminder(ORMClaimsReminder obj) {
	        String result = "";
	        try {
	            HibernateTransactionUtil hibernate = HibernateTransactionUtil.getInstance();
	            
	            if (GenericOperations.isNullorEmpty(obj.getReminder_id()) ) {
	                obj.setReminder_id(GenericOperations.IDGenerator("claims_reminder", obj.getPractice_id()));
	                obj.setDate_created(GenericOperations.CurrentDateTime());
	                hibernate.add(obj);               
	            } else {
	                
	                if(obj.getResolved()==true)
	                {
	                    obj.setDate_resolved(GenericOperations.CurrentDateTime());
	                }
	                obj.setDate_modified(GenericOperations.CurrentDateTime());
	                hibernate.update(obj);
	            }
	            
	            result = obj.getReminder_id();

	        } catch (Exception e) {
	            System.out.println("\nError Occurrerd : \n");
	            e.printStackTrace();
	            result="";
	        }
	        return result;
	    }
	     
	    public List<?> getClaimsReminder(String claim_id) {
	        
	        String strProceString = "spGetClaimsReminders '"+claim_id+"'";
	        HibernateTransactionUtil hibernate = HibernateConfigurations.HibernateTransactionUtil.getInstance();
	        return hibernate.SPExecute(strProceString,ORMClaimsReminder.class);

	    }
	    
	    public int ResolveClaimReminder(String id,String resolved_notes,String user) {
	        
	        String str=" update claims_reminder set resolved=1,resolved_notes='"+resolved_notes.replaceAll("'", "`")+"',modified_user='"+user+"',date_modified=getdate(),date_resolved=getdate(),resolved_user='"+user+"' where reminder_id='"+id+"'" ;
	        
	        HibernateTransactionUtil hibernate = HibernateTransactionUtil.getInstance();
	        return hibernate.CustomUpdate(str);
	    }
	    */
}
