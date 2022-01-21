package com.ihc.ehr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.PHSDAO;
import com.ihc.ehr.model.HL7FileGenerationResult;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;

@Service
public class PHSServiceImpl implements PHSService {

	@Autowired
	PHSDAO phsDAO;
	
	@Override
	public ServiceResponse<HL7FileGenerationResult> GenerateSyndromicSurveillanceMessage(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return phsDAO.GenerateSyndromicSurveillanceMessage(searchCriteria);
	}

}
