package com.ihc.ehr.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMTeleHealthSurveySave;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@Repository
public class PublicDAOImpl implements PublicDAO {

	@Autowired
	DBOperations db;

	@Override
	public ServiceResponse<ORMKeyValue> saveTeleHealthSurvey(ORMTeleHealthSurveySave ormSurvey) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		

		ormSurvey.setDate_created(DateTimeUtil.getCurrentDateTime());
		ORMTeleHealthSurveySave resultObj = (ORMTeleHealthSurveySave) db.AddEntityWithIdentity(ormSurvey);

		if (GeneralOperations.isNullorEmpty(resultObj.getId())) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(resultObj.getId().toString());
		}

		return resp;
	}

}
