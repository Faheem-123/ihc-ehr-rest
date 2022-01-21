package com.ihc.ehr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.PublicDAO;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMTeleHealthSurveySave;
import com.ihc.ehr.model.ServiceResponse;

@Service
public class PublicServiceImpl implements PublicService {
	
	@Autowired
	PublicDAO publicDAO;

	@Override
	public ServiceResponse<ORMKeyValue> saveTeleHealthSurvey(ORMTeleHealthSurveySave ormSurvey) {
		// TODO Auto-generated method stub
		return publicDAO.saveTeleHealthSurvey(ormSurvey);
	}

}
