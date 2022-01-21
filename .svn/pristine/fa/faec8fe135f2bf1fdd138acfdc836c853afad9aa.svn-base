package com.ihc.ehr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.EligibilityDOA;
import com.ihc.ehr.model.PatientEligibility;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;

@Service
public class EligibilityServiceImpl implements EligibilitiyService {
	
	@Autowired
	private EligibilityDOA eligibilityDOA;

	@Override
	public ServiceResponse<PatientEligibility> getPatientEligibility(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return eligibilityDOA.getPatientEligibility(searchCriteria);
	}

	@Override
	public ServiceResponse<PatientEligibility> getClaimElibility(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return eligibilityDOA.getClaimElibility(searchCriteria);
	}

	/*
	@Override
	public 	<T> ServiceResponse<T> PatientEligibility getPatientEligibility(WrapperEligiblityCriteria wrapperEligiblityCriteria) {
		// TODO Auto-generated method stub
		return eligibilityDOA.getPatientEligibility(wrapperEligiblityCriteria);
	}
	*/

}
