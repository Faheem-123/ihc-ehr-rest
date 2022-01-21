package com.ihc.ehr.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihc.ehr.model.ORMGetAttorneyInfo;
import com.ihc.ehr.model.ORMGetChartDiagnosis;
import com.ihc.ehr.model.ORMGetDiagSearch;
import com.ihc.ehr.model.ORMGetProcedureSearch;
import com.ihc.ehr.model.ORMGuarantorSearch;
import com.ihc.ehr.model.ORMInsuranceSearch;
import com.ihc.ehr.model.ORMLabMappingCode;
import com.ihc.ehr.model.ORMLabResultLoincCode;
import com.ihc.ehr.model.ORMPayerSearch;
import com.ihc.ehr.model.ORMProcedureSearch;
import com.ihc.ehr.model.ORMRaceEthnicitySearch;
import com.ihc.ehr.model.ORMReferralPhysicianSearch;
import com.ihc.ehr.model.ORMSpecialitySearch;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchPHRPatient;
import com.ihc.ehr.service.SearchService;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	SearchService searchServcie;
	
	@RequestMapping("/patient")
	public @ResponseBody <T> ResponseEntity<List<T>> searchPatient(
			@RequestBody SearchCriteria searchCriteria,
			HttpSession session )
	{	
		String sessionId = session.getId();
		
	
		
		GeneralOperations.logMsg("Inside searchPatient: SearchCriteria="+searchCriteria.toString());
		System.out.println("[session-id]: " + sessionId);
		
		List<T> lstSearchPatient=searchServcie.searchPatient(searchCriteria);
		
		return new ResponseEntity<List<T>>(lstSearchPatient , HttpStatus.OK);
	}
	
	@RequestMapping("/searchLabTest")
	public @ResponseBody ResponseEntity<List<ORMProcedureSearch>> searchLabTest(
			@RequestBody SearchCriteria searchCriteria)
	{
		List<ORMProcedureSearch> lstSearchPatient=searchServcie.searchLabTest(searchCriteria);
		return new ResponseEntity<List<ORMProcedureSearch>>(lstSearchPatient , HttpStatus.OK);
	}
	@RequestMapping("/searchLabMappingTest")
	public @ResponseBody ResponseEntity<List<ORMLabMappingCode>> searchLabMappingTest(
			@RequestBody SearchCriteria searchCriteria)
	{
		List<ORMLabMappingCode> lstSearchPatient=searchServcie.searchLabMappingTest(searchCriteria);
		return new ResponseEntity<List<ORMLabMappingCode>>(lstSearchPatient , HttpStatus.OK);
	}
	
	@RequestMapping("/diagnosis")
	public @ResponseBody ResponseEntity<List<ORMGetDiagSearch>> searchDiagnosis(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside searchDiagnosis");
		
		List<ORMGetDiagSearch> lst=searchServcie.searchDiagnosis(searchCriteria);
		
		return new ResponseEntity<List<ORMGetDiagSearch>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/procedure")
	public @ResponseBody ResponseEntity<List<ORMGetProcedureSearch>> searchProcedures(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside searchProcedures");
		
		List<ORMGetProcedureSearch> lst=searchServcie.searchProcedures(searchCriteria);
		
		return new ResponseEntity<List<ORMGetProcedureSearch>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/race")
	public @ResponseBody ResponseEntity<List<ORMRaceEthnicitySearch>> searchRace(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside searchRace");
		
		List<ORMRaceEthnicitySearch> lst=searchServcie.searchRace(searchCriteria);
		
		return new ResponseEntity<List<ORMRaceEthnicitySearch>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/ethnicity")
	public @ResponseBody ResponseEntity<List<ORMRaceEthnicitySearch>> searchEthnicity(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside searchEthnicity");
		
		List<ORMRaceEthnicitySearch> lst=searchServcie.searchEthnicity(searchCriteria);
		
		return new ResponseEntity<List<ORMRaceEthnicitySearch>>(lst , HttpStatus.OK);
	}
	
	
	@RequestMapping("/insurance")
	public @ResponseBody ResponseEntity<List<ORMInsuranceSearch>> searchInsurance(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside searchInsurance");
		
		List<ORMInsuranceSearch> lst=searchServcie.searchInsurance(searchCriteria);
		
		return new ResponseEntity<List<ORMInsuranceSearch>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/guarantor")
	public @ResponseBody ResponseEntity<List<ORMGuarantorSearch>> searchGuarantor(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside searchGuarantor");
		
		List<ORMGuarantorSearch> lst=searchServcie.searchGuarantor(searchCriteria);
		
		return new ResponseEntity<List<ORMGuarantorSearch>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/refphysician")
	public @ResponseBody ResponseEntity<List<ORMReferralPhysicianSearch>> searchRefphysician(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside searchRefphysician");
		
		List<ORMReferralPhysicianSearch> lst=searchServcie.searchRefphysician(searchCriteria);
		
		return new ResponseEntity<List<ORMReferralPhysicianSearch>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/speciality")
	public @ResponseBody ResponseEntity<List<ORMSpecialitySearch>> searchSpeciality(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside searchSpeciality");
		
		List<ORMSpecialitySearch> lst=searchServcie.searchSpeciality(searchCriteria);
		
		return new ResponseEntity<List<ORMSpecialitySearch>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/payer")
	public @ResponseBody ResponseEntity<List<ORMPayerSearch>> searchPayer(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside searchPayer: SearchCriteria="+searchCriteria.toString());
		
		List<ORMPayerSearch> lstSearchPayer=searchServcie.searchPayer(searchCriteria);
		
		return new ResponseEntity<List<ORMPayerSearch>>(lstSearchPayer , HttpStatus.OK);
	}
	@RequestMapping("/getChartDiagnosis/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMGetChartDiagnosis>> getChartDiag(
			@PathVariable(value="patient_id") String patient_id,
			@PathVariable(value="chart_id") String chart_id){
		List<ORMGetChartDiagnosis> obj=searchServcie.getChartDiagnosis(patient_id,chart_id);
		return new ResponseEntity<List<ORMGetChartDiagnosis>>(obj , HttpStatus.OK);
	}
	@RequestMapping("/getLoinCode")
	public @ResponseBody ResponseEntity<List<ORMLabResultLoincCode>> getLoinCode(
			@RequestBody SearchCriteria searchCriteria)
	{			
		List<ORMLabResultLoincCode> lstSearchLoinc=searchServcie.getLoinCode(searchCriteria);
		return new ResponseEntity<List<ORMLabResultLoincCode>>(lstSearchLoinc , HttpStatus.OK);
	}
	@RequestMapping("/searchFirm")
	public @ResponseBody ResponseEntity<List<ORMGetAttorneyInfo>> searchFirm(
			@RequestBody SearchCriteria searchCriteria){		
		GeneralOperations.logMsg("Inside searchFirm: SearchCriteria="+searchCriteria.toString());
		List<ORMGetAttorneyInfo> lstSearchPatient=searchServcie.searchFirm(searchCriteria);
		return new ResponseEntity<List<ORMGetAttorneyInfo>>(lstSearchPatient , HttpStatus.OK);
	}
	@RequestMapping("/getAllChartPlan")
	public @ResponseBody ResponseEntity<List<ORMThreeColum>> getAllChartPlan(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside getAllChartPlan");
		
		List<ORMThreeColum> lst=searchServcie.getAllChartPlan(searchCriteria);
		
		return new ResponseEntity<List<ORMThreeColum>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/PHRsearchPatient")
	public @ResponseBody ResponseEntity<List<SearchPHRPatient>> PHRsearchPatient(
			@RequestBody SearchCriteria searchCriteria, HttpSession session )
	{	
		//String sessionId = session.getId();
		GeneralOperations.logMsg("Inside PHR searchPatient: SearchCriteria="+searchCriteria.toString());
		List<SearchPHRPatient> lstSearchPatient=searchServcie.PHRsearchPatient(searchCriteria);
		return new ResponseEntity<List<SearchPHRPatient>>(lstSearchPatient , HttpStatus.OK);
	}
}