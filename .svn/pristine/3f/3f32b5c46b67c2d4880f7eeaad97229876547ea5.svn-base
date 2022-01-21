package com.ihc.ehr.service;

import java.util.List;

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



public interface ClaimService {

	List<ORMClaimSummary> getClaimSummary(Long patient_id, Boolean due_only, Boolean showdeleted);

	List<ORMFacilityList> getFacilityList(Long practice_id);
	
	ORMClaimGet_Pro getClaim(Long claim_id);

	List<ORMClaimDiagnosisGet_Pro> getProClaimDiagnosis(Long claim_id,Boolean showdeleted);
	
	List<ORMClaimProceduresGet_Pro> getProClaimProcedures(Long claim_id,Boolean showdeleted);

	List<ORMIdCodeDescription> getPracticePOSList(Long practice_id);

	List<ORMIdCodeDescription> getModifierList();

	List<ORMClaimInsuranceGet> getClaimInsurance(Long claim_id,Boolean showdeleted);

	ORMGetClaimRulePatientInfo getClaimRulePatientInfo(Long patient_id);

	List<ORMGetClaimImportProcedures> getClaimImportProc(SearchCriteria searchCriteria);

	List<ORMGetClaimImportDiagnosis> getClaimImportDiag(SearchCriteria searchCriteria);

	List<ORMGetClaimImportLabOrder> getClaimImportLabOrders(SearchCriteria searchCriteria);

	List<ORMIdName> getSuperBillList(Long practice_id);

	List<ORMGetSuperBillCategoryList> getSuperBillCategoriesList(Long bill_id);

	List<ORMGetClaimSuperBillDetail> getSuperBillDetails(SearchCriteria searchCriteria);

	List<ORMClaimNotesGet> getClaimNotes(Long claim_id, String user, String editableOnly);

	List<ORMIdCodeDescriptionType> getChartFollowUpDiagnosis(SearchCriteria searchCriteria);
	
	List<ORMGetProcedureSearch> getChartFollowUpProcedures(SearchCriteria searchCriteria);
	
	ServiceResponse<ORMKeyValue> saveClaimNotes(ORMClaimNotesSave ormClaimNotesSave, Boolean add_to_patient_notes);

	ServiceResponse<ORMKeyValue> saveClaimPro(Wrapper_ClaimSave_Pro wrapper_ClaimSave_Pro);

	List<ORMIdCodeDescription> getClaimAdjustmentGroupCodesList(SearchCriteria searchCriteria);

	List<ORMIdCode> getClaimAdjustmentReasonCodesList();

	List<ORMGetPaymentInsurances> getPaymentInsurances(Long claim_id);

	List<ORMClaimPaymentGet> getClaimPayment(Long claim_id,Boolean showDeleted);

	List<ORMGetProcesuresForPosting> getProceduresForPosting(SearchCriteria searchCriteria);

	ORMEobEraCheckDetails getEobEraCheckDetailsById(SearchCriteria searchCriteria);

	List<ORMClaimPaymentAdjustmentGet> getClaimPaymentAdjustments(Long claim_id);
	
	ServiceResponse<ORMKeyValue> saveClaimPayment(Wrapper_ClaimPaymentSave wapperClaimPaymentSave);

	ServiceResponse<ORMKeyValue> rectifyPayment(List<ORMKeyValue> lstKeyValue);


	List<ORMGetClaimPostedPayment> getClaimPostedPayment(Long eob_era_id, String eob_era_id_type);

	List<ORMEobGet> getEOBInfoByClaimId(Long claim_id);

	List<ORMEraPaymentInfoGet> getERAPaymentInfoByClaimId(Long claim_id);

	ServiceResponse<ORMKeyValue> deleteClaim(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> updateClaimStatus(ClaimStatusUpdateModel claimStatusUpdateModel);

	List<ORMIdName> getSuperBillEncounterList(Long practice_id);

	List<ORMGetPatientStatement> getPatientStatement(SearchCriteria searchCriteria);

	List<ORMGetStatementDetail> getStatementDetail(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> saveStatementLog(List<ORMSavePatientStatement_log> lstSave);

	List<ORMGetAttorneyDetail> getPrintAttorneyDetail(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> saveAttorneyLog(List<ORMSavePatientAttorney_log> lstSave);

	List<ORMTwoColumnGeneric> getPatientSelfPay(Long patient_id);

	List<ORMGetCPTWisePatientPaymentForRefund> getProceduresForPatientRefund(SearchCriteria searchCriteria);

	Long saveStatementNotes(List<ORMPatientStatementNotes> objDetail);

	List<ORMPatientStatementNotes> getPatientStatementSavedNotes(SearchCriteria searchCriteria);

	List<ORMGetStatementPdf> getStatementPDF(SearchCriteria searchCriteria);

	String generatePDFStatement(List<ORMStatementPDF> objDetail);

	String generateStatement(List<ORMspGetPatientClaims> objDetail);

	List<ORMGetPatientStatementLog> getPatientStatementLog(Long patient_id);

	List<ORMGetCPTsWithBalance> getCPTsWithBalance(SearchCriteria searchCriteria);

	List<ORMGetPreviousCalimImport> getPreviousCalimImport(SearchCriteria searchCriteria);

	List<ORMBillingProviderTaxomonyListGet> GetBillingProviderTaxonomyList(Long practice_id, Long billing_provider_id);

	List<ORMClaimReminderGet> getClaimRemdinder(Long practiceId, Long claimId);

	ServiceResponse<ORMKeyValue> saveClaimReminder(ORMClaimReminderSave ormClaimReminderSave);

	ServiceResponse<ORMKeyValue> resolveClaimReminder(SearchCriteria searchCriteria);

}
