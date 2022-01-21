package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.InsurancesDAO;
import com.ihc.ehr.model.ORMGetClient_Insurances;
import com.ihc.ehr.model.ORMGetProviderModifier;
import com.ihc.ehr.model.ORMGetProviderPayers;
import com.ihc.ehr.model.ORMInsurance_PayerTypes;
import com.ihc.ehr.model.ORMInsurance_Payers;
import com.ihc.ehr.model.ORMInsurance_setup;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPracticeInsurance;
import com.ihc.ehr.model.ORMSaveProvider_Payers;
import com.ihc.ehr.model.ORMgetMappingPracticeInsurances;
import com.ihc.ehr.model.ServiceResponse;

@Service
public class InsurancesServiceImpl implements InsurancesService {

	@Autowired
	InsurancesDAO insDAO;
	
	@Override
	public List<ORMInsurance_PayerTypes> getInsurancePayerTypes() {
		// TODO Auto-generated method stub
		return insDAO.getInsurancePayerTypes();
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveInsurancePayerTypes(ORMInsurance_PayerTypes obj) {
		// TODO Auto-generated method stub
		return insDAO.saveInsurancePayerTypes(obj);
	}

	@Override
	public List<ORMInsurance_Payers> getPayerTypePayer(String payerType_id) {
		// TODO Auto-generated method stub
		return insDAO.getPayerTypePayer(payerType_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveInsurancePayer(ORMInsurance_Payers obj) {
		// TODO Auto-generated method stub
		return insDAO.saveInsurancePayer(obj);
	}

	@Override
	public List<ORMInsurance_setup> getPayerInsurances(String payer_id) {
		// TODO Auto-generated method stub
		return insDAO.getPayerInsurances(payer_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveInsurances(ORMInsurance_setup obj) {
		// TODO Auto-generated method stub
		return insDAO.saveInsurances(obj);
	}

	@Override
	public List<ORMGetProviderPayers> getProviderPayer(String provider_id) {
		// TODO Auto-generated method stub
		return insDAO.getProviderPayer(provider_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveProviderPayer(ORMSaveProvider_Payers obj) {
		// TODO Auto-generated method stub
		return insDAO.saveProviderPayer(obj);
	}

	@Override
	public List<ORMGetProviderModifier> getProviderModifier() {
		// TODO Auto-generated method stub
		return insDAO.getProviderModifier();
	}

	@Override
	public List<ORMGetClient_Insurances> getClient_Insurances(String criteria) {
		// TODO Auto-generated method stub
		return insDAO.getClient_Insurances(criteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveInsuranceslist(List<ORMInsurance_setup> lstSave) {
		// TODO Auto-generated method stub
		return insDAO.saveInsuranceslist(lstSave);
	}

	@Override
	public List<ORMgetMappingPracticeInsurances> getUnMappedPracticeInsurances(String practice_id, String is_mapped,
			String payerid) {
		// TODO Auto-generated method stub
		return insDAO.getUnMappedPracticeInsurances(practice_id,is_mapped,payerid);
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePracticeInsurance( List<ORMPracticeInsurance> obj) {
		// TODO Auto-generated method stub
		return insDAO.savePracticeInsurance(obj);
	}

	

}
