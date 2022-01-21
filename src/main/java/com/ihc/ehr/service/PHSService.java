package com.ihc.ehr.service;

import com.ihc.ehr.model.HL7FileGenerationResult;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;

public interface PHSService {

	ServiceResponse<HL7FileGenerationResult> GenerateSyndromicSurveillanceMessage(SearchCriteria searchCriteria);

}
