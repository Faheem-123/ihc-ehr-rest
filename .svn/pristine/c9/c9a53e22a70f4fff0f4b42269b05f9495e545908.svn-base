package com.ihc.ehr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihc.ehr.model.HL7FileGenerationResult;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.service.PHSService;

@RestController
@RequestMapping("/phs")
public class PHSController {
	
	@Autowired
	PHSService phsService;
	
	@RequestMapping("/GenerateSyndromicSurveillanceMessage")
	public @ResponseBody ResponseEntity<ServiceResponse<HL7FileGenerationResult>>  GenerateSyndromicSurveillanceMessage(
			@RequestBody SearchCriteria searchCriteria) {

		ServiceResponse<HL7FileGenerationResult> resp  = phsService.GenerateSyndromicSurveillanceMessage(searchCriteria);

		return new ResponseEntity<ServiceResponse<HL7FileGenerationResult>>(resp, HttpStatus.OK);

	}

}
