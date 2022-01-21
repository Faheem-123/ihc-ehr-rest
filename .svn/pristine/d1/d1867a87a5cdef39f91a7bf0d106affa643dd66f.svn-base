package com.ihc.ehr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import com.ihc.ehr.service.LogService;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/log")
public class LogController {
	
	
	@Autowired
	LogService logService;
	
	@RequestMapping("/getAppointmentCallsLog")
	public @ResponseBody ResponseEntity<List<ORMAppointmentCallsLogGet>> getAppointmentCallsLog(
			@RequestBody SearchCriteria searchCriteria)
	{	
		List<ORMAppointmentCallsLogGet> lst=logService.getAppointmentCallsLog(searchCriteria);
		return new ResponseEntity<List<ORMAppointmentCallsLogGet>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/saveAppointmentCallsLog")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveAppointmentCallsLog(
			@RequestBody ORMAppointmentCallsLogSave obj) {
		ServiceResponse<ORMKeyValue> resp=logService.saveAppointmentCallsLog(obj);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping("/getACUCallsLog")
	public ResponseEntity<List<ORMACUCallsLogGet>> getACUCallsLog(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMACUCallsLogGet> lst=logService.getACUCallsLog(searchCriteria);
		return new ResponseEntity<List<ORMACUCallsLogGet>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/getPatientAccessLog")
	public ResponseEntity<List<ORMGetAccessLogPatient>> getPatientAccessLog(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetAccessLogPatient> lst=logService.getPatientAccessLog(searchCriteria);
		return new ResponseEntity<List<ORMGetAccessLogPatient>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/getEncounterAccessLog")
	public ResponseEntity<List<ORMGetAccessLogEncounter>> getEncounterAccessLog(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetAccessLogEncounter> lst=logService.getEncounterAccessLog(searchCriteria);
		return new ResponseEntity<List<ORMGetAccessLogEncounter>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/getAuditLog/{module_name}")
	public @ResponseBody ResponseEntity<WrapperLog> getAuditLog(
			@PathVariable(value = "module_name") String moduleName, 
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside getAuditLog");
		
		WrapperLog wrapperLog=logService.getAuditLog(moduleName,searchCriteria);
		return new ResponseEntity<WrapperLog>(wrapperLog, HttpStatus.OK);
	}
	
	@RequestMapping("/getLogList/{log_category}")
	public @ResponseBody ResponseEntity<List<ORMGetLogList>> getLogList( 
			@PathVariable(value = "log_category") String log_category)
	{	
		GeneralOperations.logMsg("Inside getLogList");
		
		List<ORMGetLogList> lst=logService.getLogList(log_category);
		return new ResponseEntity<List<ORMGetLogList>>(lst, HttpStatus.OK);
	}
	
}
