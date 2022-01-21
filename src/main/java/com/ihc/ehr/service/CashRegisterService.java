package com.ihc.ehr.service;

import java.util.List;

import com.ihc.ehr.model.ORMCashRegisterAdd;
import com.ihc.ehr.model.ORMCashRegisterGet;
import com.ihc.ehr.model.ORMCashRegisterModify;
import com.ihc.ehr.model.ORMCashRegisterPatInfo;
import com.ihc.ehr.model.ORMCashRegisterUnResolvedPaymentGet;
import com.ihc.ehr.model.ORMFourColumGeneric;
import com.ihc.ehr.model.ORMIdCodeDescription;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientRefundGet;
import com.ihc.ehr.model.ORMSavePatientNotPaidReason;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.Wrapper_PatientRefundSave;

public interface CashRegisterService {

	List<ORMIdCodeDescription> getWriteOffCodes(Long practice_id);

	List<ORMFourColumGeneric> getPaymentPlan(Long patient_id);

	String getPatientBalance(Long patient_id);

	ORMCashRegisterPatInfo getCahRegisterInfo(SearchCriteria searchCriteria);

	List<ORMCashRegisterGet> getCashRegister(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> addCashRegisterPayment(ORMCashRegisterAdd objCashRegister, ORMSavePatientNotPaidReason objNotPaidReason);

	ServiceResponse<ORMKeyValue> modifyCashRegisterPayment(ORMCashRegisterModify objCashRegisterModify);
	
	List<ORMFourColumGeneric> getNotPaidReason(Long patient_id);

	List<ORMPatientRefundGet> getPatientRefund(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> savePatientRefund(Wrapper_PatientRefundSave wrapperPatientRefundSave);

	ServiceResponse<ORMKeyValue> voidCashRegisterEntry(List<ORMKeyValue> lstKeyValue);

	ServiceResponse<ORMKeyValue> checkBounceCashRegisterEntry(List<ORMKeyValue> lstKeyValue);

	ServiceResponse<ORMKeyValue> markAsResolvedCashRegisterEntry(List<ORMKeyValue> lstKeyValue);

	List<ORMCashRegisterUnResolvedPaymentGet> getUnResolvedCashRegisterPayments(List<ORMKeyValue> lstKeyValue);

	ServiceResponse<ORMKeyValue> savePaymentPlan(List<ORMKeyValue> lstKeyValue);

}
