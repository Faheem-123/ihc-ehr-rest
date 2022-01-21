package com.ihc.ehr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihc.ehr.model.PatientEligibility;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.service.EligibilitiyService;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

	@Autowired
	private EligibilitiyService eligibilitiyService;
	
	@RequestMapping("/getpatientelibility")
	public @ResponseBody ResponseEntity<ServiceResponse<PatientEligibility>> getPatientEligibility(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside getPatientEligibility");
		
		ServiceResponse<PatientEligibility> response =eligibilitiyService.getPatientEligibility(searchCriteria); 
		
		return new ResponseEntity<ServiceResponse<PatientEligibility>>(response , HttpStatus.OK);
	}
	
	@RequestMapping("/getClaimElibility")
	public @ResponseBody ResponseEntity<ServiceResponse<PatientEligibility>> getClaimElibility(
			@RequestBody SearchCriteria searchCriteria)
	{	
		ServiceResponse<PatientEligibility> response =eligibilitiyService.getClaimElibility(searchCriteria); 
		return new ResponseEntity<ServiceResponse<PatientEligibility>>(response , HttpStatus.OK);
	}
}
