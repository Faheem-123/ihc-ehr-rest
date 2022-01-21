package com.ihc.ehr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMTeleHealthSurveySave;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.service.PublicService;

@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired
	PublicService publicService;

	@RequestMapping("/hello")
	public String sayHello() {

		System.out.println("hello");	
		
		return "Hello";
	}

	@RequestMapping("/saveTeleHealthSurvey")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveTeleHealthSurvey(
			@RequestBody ORMTeleHealthSurveySave ormSurvey) {
		ServiceResponse<ORMKeyValue> resp = publicService.saveTeleHealthSurvey(ormSurvey);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}	
}
