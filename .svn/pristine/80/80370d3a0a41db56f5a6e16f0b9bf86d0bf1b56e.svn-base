package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.CashRegisterDOA;
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

@Service
public class CashRegisterServiceImpl implements CashRegisterService {

	@Autowired
	CashRegisterDOA cashRegisterDOA;

	@Override
	public List<ORMIdCodeDescription> getWriteOffCodes(Long practice_id) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.getWriteOffCodes(practice_id);
	}

	@Override
	public List<ORMFourColumGeneric> getPaymentPlan(Long patient_id) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.getPaymentPlan(patient_id);
	}

	@Override
	public String getPatientBalance(Long patient_id) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.getPatientBalance(patient_id);
	}

	@Override
	public ORMCashRegisterPatInfo getCahRegisterInfo(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.getCahRegisterInfo(searchCriteria);
	}

	@Override
	public List<ORMCashRegisterGet> getCashRegister(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.getCashRegister(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> addCashRegisterPayment(ORMCashRegisterAdd objCashRegister,
			ORMSavePatientNotPaidReason objNotPaidReason) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.addCashRegisterPayment(objCashRegister,objNotPaidReason);
	}
	
	@Override
	public ServiceResponse<ORMKeyValue> modifyCashRegisterPayment(ORMCashRegisterModify objCashRegisterModify) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.modifyCashRegisterPayment(objCashRegisterModify);
	}
	

	@Override
	public List<ORMFourColumGeneric> getNotPaidReason(Long patient_id) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.getNotPaidReason(patient_id);
	}

	@Override
	public List<ORMPatientRefundGet> getPatientRefund(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.getPatientRefund(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePatientRefund(Wrapper_PatientRefundSave wrapperPatientRefundSave) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.savePatientRefund(wrapperPatientRefundSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> voidCashRegisterEntry(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.voidCashRegisterEntry(lstKeyValue);
	}

	@Override
	public ServiceResponse<ORMKeyValue> checkBounceCashRegisterEntry(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.checkBounceCashRegisterEntry(lstKeyValue);
	}

	@Override
	public ServiceResponse<ORMKeyValue> markAsResolvedCashRegisterEntry(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.markAsResolvedCashRegisterEntry(lstKeyValue);
	}

	@Override
	public List<ORMCashRegisterUnResolvedPaymentGet> getUnResolvedCashRegisterPayments(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.getUnResolvedCashRegisterPayments(lstKeyValue);
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePaymentPlan(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		return cashRegisterDOA.savePaymentPlan(lstKeyValue);
	}
}
