package com.ihc.ehr.service;

import com.ihc.ehr.model.PatientEligibility;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;

public interface EligibilitiyService {

//	PatientEligibility getPatientEligibility(WrapperEligiblityCriteria wrapperEligiblityCriteria);

	ServiceResponse<PatientEligibility> getPatientEligibility(SearchCriteria searchCriteria);

	ServiceResponse<PatientEligibility> getClaimElibility(SearchCriteria searchCriteria);
}
