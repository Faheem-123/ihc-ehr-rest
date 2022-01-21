package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.CDSDAO;
import com.ihc.ehr.model.ORMCDSRulesGetSave;
import com.ihc.ehr.model.ORMIdNameType;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientCDSMessage;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;

@Service
public class CDSServiceImpl implements CDSService{

	@Autowired
	private CDSDAO cdsDao;

	@Override
	public List<ORMPatientCDSMessage> RunCDSRules(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return cdsDao.RunCDSRules(searchCriteria);
		
	}

	@Override
	public List<ORMIdNameType> getCDCRulesList(Long practiceId) {
		// TODO Auto-generated method stub
		return cdsDao.getCDCRulesList(practiceId);
	}

	@Override
	public ORMCDSRulesGetSave getCDSRuleById(Long ruleId) {
		// TODO Auto-generated method stub
		return cdsDao.getCDSRuleById(ruleId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveCDSRule(ORMCDSRulesGetSave ormCDSRulesGetSave) {
		// TODO Auto-generated method stub
		return cdsDao.saveCDSRule(ormCDSRulesGetSave);
	}
}
