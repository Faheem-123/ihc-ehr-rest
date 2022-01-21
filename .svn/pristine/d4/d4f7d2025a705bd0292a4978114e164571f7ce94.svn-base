package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.LogDAO;
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

@Service
public class LogServiceImp implements LogService {

	@Autowired
	private LogDAO logDAO;

	@Override
	public List<ORMAppointmentCallsLogGet> getAppointmentCallsLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return logDAO.getAppointmentCallsLog(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveAppointmentCallsLog(ORMAppointmentCallsLogSave obj) {
		// TODO Auto-generated method stub
		return logDAO.saveAppointmentCallsLog(obj);
	}

	@Override
	public List<ORMACUCallsLogGet> getACUCallsLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return logDAO.getACUCallsLog(searchCriteria);
	}

	@Override
	public List<ORMGetAccessLogPatient> getPatientAccessLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return logDAO.getPatientAccessLog(searchCriteria);
	}

	@Override
	public List<ORMGetAccessLogEncounter> getEncounterAccessLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return logDAO.getEncounterAccessLog(searchCriteria);
	}

	
	@Override
	public WrapperLog getAuditLog(String moduleName, SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return logDAO.getAuditLog(moduleName,searchCriteria);
	}

	@Override
	public List<ORMGetLogList> getLogList(String log_category) {
		// TODO Auto-generated method stub
		return logDAO.getLogList(log_category);
	}


}
