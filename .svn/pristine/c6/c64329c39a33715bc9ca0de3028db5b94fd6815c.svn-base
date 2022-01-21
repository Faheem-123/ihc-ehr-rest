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

import com.ihc.ehr.model.ORMCDSRulesGetSave;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMIdNameType;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientCDSMessage;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.service.CDSService;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@RestController
@RequestMapping("/cds")
public class CDSController {
	@Autowired
	CDSService cdsService;
	
	@Autowired
	GeneralService generalService;

	@RequestMapping("/RunCDSRules")
	public @ResponseBody ResponseEntity<List<ORMPatientCDSMessage>> RunCDSRules(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMPatientCDSMessage> lst = cdsService.RunCDSRules(searchCriteria);
		return new ResponseEntity<List<ORMPatientCDSMessage>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/getCDCRulesList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdNameType>> getCDCRulesList(
			@PathVariable(value = "practice_id") Long practiceId) {
		
		List<ORMIdNameType> lst = cdsService.getCDCRulesList(practiceId);
		return new ResponseEntity<List<ORMIdNameType>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getCDSRuleById/{rule_id}")
	public @ResponseBody ResponseEntity<ORMCDSRulesGetSave> getCDSRuleById(
			@PathVariable(value = "rule_id") Long ruleId) {
		
		ORMCDSRulesGetSave obj = cdsService.getCDSRuleById(ruleId);
		return new ResponseEntity<ORMCDSRulesGetSave>(obj, HttpStatus.OK);
	}
	@RequestMapping("/saveCDSRule")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveCDSRule(
			@RequestBody ORMCDSRulesGetSave ormCDSRulesGetSave) {
		
		GeneralOperations.logMsg("Inside saveCDSRule");

		ServiceResponse<ORMKeyValue> resp = cdsService.saveCDSRule(ormCDSRulesGetSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	
	@RequestMapping("/deleteCDSRule")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteCDSRule(
			@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteCDSRule: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("cds_rules");
		ormDeleteRecord.setColumn_name("rule_id");

		int result = generalService.deleteRecord(ormDeleteRecord);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Record has been deleted successfully.");
			resp.setResult(ormDeleteRecord.getColumn_id().toString());
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	
}
