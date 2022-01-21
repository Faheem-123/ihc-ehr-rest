package com.ihc.ehr.dao;

import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMTeleHealthSurveySave;
import com.ihc.ehr.model.ServiceResponse;

public interface PublicDAO {

	ServiceResponse<ORMKeyValue> saveTeleHealthSurvey(ORMTeleHealthSurveySave ormSurvey);

}
