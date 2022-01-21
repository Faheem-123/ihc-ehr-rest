package com.ihc.ehr.service;

import java.util.List;

import com.ihc.ehr.model.ORMCDSRulesGetSave;
import com.ihc.ehr.model.ORMIdNameType;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientCDSMessage;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;

public interface CDSService {

	List<ORMPatientCDSMessage> RunCDSRules(SearchCriteria searchCriteria);	

	List<ORMIdNameType> getCDCRulesList(Long practiceId);

	ORMCDSRulesGetSave getCDSRuleById(Long ruleId);

	ServiceResponse<ORMKeyValue> saveCDSRule(ORMCDSRulesGetSave ormCDSRulesGetSave);
}
