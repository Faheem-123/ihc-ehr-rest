package com.ihc.ehr.dao;

import java.util.List;

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

public interface InsurancesDAO {
	
	List<ORMInsurance_PayerTypes> getInsurancePayerTypes();

	ServiceResponse<ORMKeyValue> saveInsurancePayerTypes(ORMInsurance_PayerTypes obj);

	List<ORMInsurance_Payers> getPayerTypePayer(String payerType_id);

	ServiceResponse<ORMKeyValue> saveInsurancePayer(ORMInsurance_Payers obj);

	List<ORMInsurance_setup> getPayerInsurances(String payer_id);

	ServiceResponse<ORMKeyValue> saveInsurances(ORMInsurance_setup obj);

	ServiceResponse<ORMKeyValue> saveProviderPayer(ORMSaveProvider_Payers obj);

	List<ORMGetProviderPayers> getProviderPayer(String provider_id);

	List<ORMGetProviderModifier> getProviderModifier();

	List<ORMGetClient_Insurances> getClient_Insurances(String criteria);

	ServiceResponse<ORMKeyValue> saveInsuranceslist(List<ORMInsurance_setup> lstSave);

	List<ORMgetMappingPracticeInsurances> getUnMappedPracticeInsurances(String practice_id, String is_mapped,
			String payerid);

	ServiceResponse<ORMKeyValue> savePracticeInsurance( List<ORMPracticeInsurance> obj);

	

}
