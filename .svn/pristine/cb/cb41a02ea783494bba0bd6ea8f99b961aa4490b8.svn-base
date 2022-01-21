package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.ClaimDAO;
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

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private ClaimDAO claimDao;
	@Override
	public List<ORMClaimSummary> getClaimSummary(Long patient_id, Boolean due_only, Boolean showdeleted) {
		// TODO Auto-generated method stub
		return claimDao.getClaimSummary(patient_id, due_only, showdeleted);
	}
	@Override
	public List<ORMFacilityList> getFacilityList(Long practice_id) {
		// TODO Auto-generated method stub
		return claimDao.getFacilityList(practice_id);
	}
		
	@Override
	public ORMClaimGet_Pro getClaim(Long claim_id) {
		// TODO Auto-generated method stub
		return claimDao.getClaim(claim_id);
	}
	@Override
	public List<ORMClaimDiagnosisGet_Pro> getProClaimDiagnosis(Long claim_id,Boolean showdeleted) {
		// TODO Auto-generated method stub
		return claimDao.getProClaimDiagnosis(claim_id,showdeleted);
	}
	@Override
	public List<ORMClaimProceduresGet_Pro> getProClaimProcedures(Long claim_id,Boolean showdeleted) {
		// TODO Auto-generated method stub
		return claimDao.getProClaimProcedures(claim_id,showdeleted);
	}
	
	@Override
	public List<ORMIdCodeDescription> getPracticePOSList(Long practice_id) {
		// TODO Auto-generated method stub
		return claimDao.getPracticePOSList(practice_id);
	}
	@Override
	public List<ORMIdCodeDescription> getModifierList() {
		// TODO Auto-generated method stub
		return claimDao.getModifierList();
	}
	@Override
	public List<ORMClaimInsuranceGet> getClaimInsurance(Long claim_id,Boolean showdeleted) {
		// TODO Auto-generated method stub
		return claimDao.getClaimInsurance(claim_id,showdeleted);
	}
	@Override
	public ORMGetClaimRulePatientInfo getClaimRulePatientInfo(Long patient_id) {
		// TODO Auto-generated method stub
		return claimDao.getClaimRulePatientInfo(patient_id);
	}
	@Override
	public List<ORMGetClaimImportProcedures> getClaimImportProc(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getClaimImportProc(searchCriteria);
	}
	@Override
	public List<ORMGetClaimImportDiagnosis> getClaimImportDiag(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getClaimImportDiag(searchCriteria);
	}
	@Override
	public List<ORMGetClaimImportLabOrder> getClaimImportLabOrders(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getClaimImportLabOrders(searchCriteria);
	}
	@Override
	public List<ORMIdName> getSuperBillList(Long practice_id) {
		// TODO Auto-generated method stub
		return claimDao.getSuperBillList(practice_id);
	}
	@Override
	public List<ORMGetSuperBillCategoryList> getSuperBillCategoriesList(Long bill_id) {
		// TODO Auto-generated method stub
		return claimDao.getSuperBillCategoriesList(bill_id);
	}
	@Override
	public List<ORMGetClaimSuperBillDetail> getSuperBillDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getSuperBillDetails(searchCriteria);
	}
	@Override
	public List<ORMClaimNotesGet> getClaimNotes(Long claim_id, String user, String editableOnly) {
		// TODO Auto-generated method stub
		return claimDao.getClaimNotes(claim_id,user,editableOnly);
	}
	@Override
	public List<ORMIdCodeDescriptionType> getChartFollowUpDiagnosis(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getChartFollowUpDiagnosis(searchCriteria);
	}	
	
	@Override
	public List<ORMGetProcedureSearch> getChartFollowUpProcedures(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getChartFollowUpProcedures(searchCriteria);
	}
	
	@Override
	public ServiceResponse<ORMKeyValue> saveClaimNotes(ORMClaimNotesSave ormClaimNotesSave,Boolean add_to_patient_notes) {
		// TODO Auto-generated method stub
		return claimDao.saveClaimNotes(ormClaimNotesSave,add_to_patient_notes);
	}
	@Override
	public ServiceResponse<ORMKeyValue> saveClaimPro(Wrapper_ClaimSave_Pro wrapper_ClaimSave_Pro) {
		// TODO Auto-generated method stub
		return claimDao.saveClaimPro(wrapper_ClaimSave_Pro);
	}
	@Override
	public List<ORMIdCodeDescription> getClaimAdjustmentGroupCodesList(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getClaimAdjustmentGroupCodesList(searchCriteria);
	}
	@Override
	public List<ORMIdCode> getClaimAdjustmentReasonCodesList() {
		// TODO Auto-generated method stub
		return claimDao.getClaimAdjustmentReasonCodesList();
	}	
	@Override
	public List<ORMGetPaymentInsurances> getPaymentInsurances(Long claim_id) {
		// TODO Auto-generated method stub
		return claimDao.getPaymentInsurances(claim_id);
	}
	@Override
	public List<ORMClaimPaymentGet> getClaimPayment(Long claim_id,Boolean showDeleted) {
		// TODO Auto-generated method stub
		return claimDao.getClaimPayment(claim_id,showDeleted);
	}
	@Override
	public List<ORMGetProcesuresForPosting> getProceduresForPosting(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getProceduresForPosting(searchCriteria);
	}
	@Override
	public ORMEobEraCheckDetails getEobEraCheckDetailsById(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getEobEraCheckDetailsById(searchCriteria);
	}
	@Override
	public List<ORMClaimPaymentAdjustmentGet> getClaimPaymentAdjustments(Long claim_id) {
		// TODO Auto-generated method stub
		return claimDao.getClaimPaymentAdjustments(claim_id);
	}
	@Override
	public ServiceResponse<ORMKeyValue> saveClaimPayment(Wrapper_ClaimPaymentSave wapperClaimPaymentSave) {
		// TODO Auto-generated method stub
		return claimDao.saveClaimPayment(wapperClaimPaymentSave);
	}
	@Override
	public ServiceResponse<ORMKeyValue> rectifyPayment(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		return claimDao.rectifyPayment(lstKeyValue);
	}
	
	@Override
	public List<ORMGetClaimPostedPayment> getClaimPostedPayment(Long eob_era_id, String eob_era_id_type) {
		// TODO Auto-generated method stub
		return claimDao.getClaimPostedPayment(eob_era_id,eob_era_id_type);
	}
	@Override
	public List<ORMEobGet> getEOBInfoByClaimId(Long claim_id) {
		// TODO Auto-generated method stub
		return claimDao.getEOBInfoByClaimId(claim_id);
	}
	@Override
	public List<ORMEraPaymentInfoGet> getERAPaymentInfoByClaimId(Long claim_id) {
		// TODO Auto-generated method stub
		return claimDao.getERAPaymentInfoByClaimId(claim_id);
	}
	@Override
	public ServiceResponse<ORMKeyValue> deleteClaim(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.deleteClaim(searchCriteria);
	}
	@Override
	public ServiceResponse<ORMKeyValue> updateClaimStatus(ClaimStatusUpdateModel claimStatusUpdateModel) {
		// TODO Auto-generated method stub
		return claimDao.updateClaimStatus(claimStatusUpdateModel);
	}
	@Override
	public List<ORMIdName> getSuperBillEncounterList(Long practice_id) {
		// TODO Auto-generated method stub
		return claimDao.getSuperBillEncounterList(practice_id);
	}
	@Override
	public List<ORMGetPatientStatement> getPatientStatement(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getPatientStatement(searchCriteria);
	}
	@Override
	public List<ORMGetStatementDetail> getStatementDetail(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getStatementDetail(searchCriteria);
	}
	@Override
	public ServiceResponse<ORMKeyValue> saveStatementLog(List<ORMSavePatientStatement_log> lstSave) {
		// TODO Auto-generated method stub
		return claimDao.saveStatementLog(lstSave);
	}
	@Override
	public List<ORMGetAttorneyDetail> getPrintAttorneyDetail(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getPrintAttorneyDetail(searchCriteria);
	}
	@Override
	public ServiceResponse<ORMKeyValue> saveAttorneyLog(List<ORMSavePatientAttorney_log> lstSave) {
		// TODO Auto-generated method stub
		return claimDao.saveAttorneyLog(lstSave);
	}
	@Override
	public List<ORMTwoColumnGeneric> getPatientSelfPay(Long patient_id) {
		// TODO Auto-generated method stub
		return claimDao.getPatientSelfPay(patient_id);
	}
	@Override
	public List<ORMGetCPTWisePatientPaymentForRefund> getProceduresForPatientRefund(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getProceduresForPatientRefund(searchCriteria);
	}
	@Override
	public Long saveStatementNotes(List<ORMPatientStatementNotes> objDetail) {
		// TODO Auto-generated method stub
		return claimDao.saveStatementNotes(objDetail);
	}
	@Override
	public List<ORMPatientStatementNotes> getPatientStatementSavedNotes(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getPatientStatementSavedNotes(searchCriteria);
	}
	@Override
	public List<ORMGetStatementPdf> getStatementPDF(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getStatementPDF(searchCriteria);
	}
	@Override
	public String generatePDFStatement(List<ORMStatementPDF> objDetail) {
		// TODO Auto-generated method stub
		return claimDao.generatePDFStatement(objDetail);
	}
	@Override
	public String generateStatement(List<ORMspGetPatientClaims> objDetail) {
		// TODO Auto-generated method stub
		return claimDao.generateStatement(objDetail);
	}
	@Override
	public List<ORMGetPatientStatementLog> getPatientStatementLog(Long patient_id) {
		// TODO Auto-generated method stub
		return claimDao.getPatientStatementLog(patient_id);
	}
	@Override
	public List<ORMGetCPTsWithBalance> getCPTsWithBalance(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getCPTsWithBalance(searchCriteria);
	}
	@Override
	public List<ORMGetPreviousCalimImport> getPreviousCalimImport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.getPreviousCalimImport(searchCriteria);
	}
	@Override
	public List<ORMBillingProviderTaxomonyListGet> GetBillingProviderTaxonomyList(Long practice_id,
			Long billing_provider_id) {
		// TODO Auto-generated method stub
		return claimDao.GetBillingProviderTaxonomyList(practice_id,billing_provider_id);
	}
	@Override
	public List<ORMClaimReminderGet> getClaimRemdinder(Long practiceId, Long claimId) {
		// TODO Auto-generated method stub
		return claimDao.getClaimRemdinder(practiceId,claimId);
	}
	@Override
	public ServiceResponse<ORMKeyValue> saveClaimReminder(ORMClaimReminderSave ormClaimReminderSave) {
		// TODO Auto-generated method stub
		return claimDao.saveClaimReminder(ormClaimReminderSave);
	}
	@Override
	public ServiceResponse<ORMKeyValue> resolveClaimReminder(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return claimDao.resolveClaimReminder(searchCriteria);
	}

}
