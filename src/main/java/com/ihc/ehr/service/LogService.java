package com.ihc.ehr.service;

import java.util.List;

import com.ihc.ehr.model.ORMACUCallsLogGet;
import com.ihc.ehr.model.ORMAppointmentCallsLogGet;
import com.ihc.ehr.model.ORMAppointmentCallsLogSave;
import com.ihc.ehr.model.ORMGetAccessLogEncounter;
import com.ihc.ehr.model.ORMGetLogList;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMGetAccessLogPatient;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.WrapperLog;

public interface LogService {

	List<ORMAppointmentCallsLogGet> getAppointmentCallsLog(SearchCriteria searchCriteria);
	ServiceResponse<ORMKeyValue> saveAppointmentCallsLog(ORMAppointmentCallsLogSave obj);
	List<ORMACUCallsLogGet> getACUCallsLog(SearchCriteria searchCriteria);
	List<ORMGetAccessLogPatient> getPatientAccessLog(SearchCriteria searchCriteria);
	List<ORMGetAccessLogEncounter> getEncounterAccessLog(SearchCriteria searchCriteria);
	WrapperLog getAuditLog(String moduleName, SearchCriteria searchCriteria);
	List<ORMGetLogList> getLogList(String log_category);
	
}
