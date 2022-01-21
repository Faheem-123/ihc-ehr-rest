package com.ihc.ehr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihc.ehr.model.Bundle;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMMobClaimDetailGet;
import com.ihc.ehr.model.ORMMobClaimProceduresGet;
import com.ihc.ehr.model.ORMMobClamDiagnosisGet;
import com.ihc.ehr.model.ORMMobGetLocation;
import com.ihc.ehr.model.ORMMobGetLoginUserInfo;
import com.ihc.ehr.model.ORMMobGetPatient;
import com.ihc.ehr.model.ORMMobPracticeInfo;
import com.ihc.ehr.model.ORMMobPracticeUsers;
import com.ihc.ehr.model.ORMMobProviderList;
import com.ihc.ehr.model.ORMMobSavePatient;
import com.ihc.ehr.model.ORMMobileGetClaimSummary;
import com.ihc.ehr.model.ORMMobileGetPatientHeader;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchPatient;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.Wrapper_MobClaimSave;
import com.ihc.ehr.service.MobileAppService;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/m")
public class MobileAppController {

	@Autowired
	MobileAppService mobileAppService;
	
	@RequestMapping("/patSearch")
	public @ResponseBody <T> ResponseEntity<List<T>> searchPatient(
			@RequestBody SearchCriteria searchCriteria, HttpSession session ){	
		String sessionId = session.getId();
		GeneralOperations.logMsg("Inside searchPatient: SearchCriteria="+searchCriteria.toString());
		System.out.println("[session-id]: " + sessionId);
		List<T> lstSearchPatient = mobileAppService.patientSearch(searchCriteria);
		return new ResponseEntity<List<T>>(lstSearchPatient , HttpStatus.OK);
	}
	
	@RequestMapping("/getPatient/{patient_id}")
	public @ResponseBody ResponseEntity<ORMMobGetPatient> getPatient(
			@PathVariable(value = "patient_id") Long patientId) {
		ORMMobGetPatient pat = mobileAppService.getPatient(patientId);
		return new ResponseEntity<ORMMobGetPatient>(pat, HttpStatus.OK);
	}
	
	@RequestMapping("/getPatientHeader/{patient_id}")
	public @ResponseBody ResponseEntity<ORMMobileGetPatientHeader> getPatientHeader(
			@PathVariable(value = "patient_id") Long patientId) {
		ORMMobileGetPatientHeader pat = mobileAppService.getPatientHeader(patientId);
		return new ResponseEntity<ORMMobileGetPatientHeader>(pat, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/savePatient", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> SavePatient(
			@RequestParam(required = false, value = "pic") MultipartFile picData, // inputFile,
			@RequestParam("patient_data") String patientData)
			throws JsonParseException, JsonMappingException, IOException {

		System.out.println("savePatient");

		System.out.println("inputFile" + picData);

		ObjectMapper mapper = new ObjectMapper();
		ORMMobSavePatient ormMobSavePatient = mapper.readValue(patientData, ORMMobSavePatient.class);

		ServiceResponse<ORMKeyValue> resp = mobileAppService.SavePatient(ormMobSavePatient, picData);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/getDraftClaimSummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMMobileGetClaimSummary>> getDraftClaimSummary(
			@PathVariable(value = "patient_id") Long patientId) {
		List<ORMMobileGetClaimSummary> lst = mobileAppService.getDraftClaimSummary(patientId);
		return new ResponseEntity<List<ORMMobileGetClaimSummary>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/getClaimDetail/{claim_id}")
	public @ResponseBody ResponseEntity<ORMMobClaimDetailGet> getClaimDetail(
			@PathVariable(value = "claim_id") Long claimId) {
		ORMMobClaimDetailGet obj = mobileAppService.getClaimDetail(claimId);
		return new ResponseEntity<ORMMobClaimDetailGet>(obj, HttpStatus.OK);
	}
	
	
	
	@RequestMapping("/getClaimDiagnosis/{claim_id}")
	public @ResponseBody ResponseEntity<List<ORMMobClamDiagnosisGet>> getClaimDiagnosis(
			@PathVariable(value = "claim_id") Long claim_id) {
		List<ORMMobClamDiagnosisGet> lst = mobileAppService.getClaimDiagnosis(claim_id);
		return new ResponseEntity<List<ORMMobClamDiagnosisGet>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/getClaimProcedures/{claim_id}")
	public @ResponseBody ResponseEntity<List<ORMMobClaimProceduresGet>> getClaimProcedures(
			@PathVariable(value = "claim_id") Long claim_id) {
		List<ORMMobClaimProceduresGet> lst = mobileAppService.getClaimProcedures(claim_id);
		return new ResponseEntity<List<ORMMobClaimProceduresGet>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/saveClaim")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveClaim(
			@RequestBody Wrapper_MobClaimSave wrapper_MobClaimSave) {
		GeneralOperations.logMsg("Inside saveClaim: ");

		ServiceResponse<ORMKeyValue> resp = mobileAppService.saveClaim(wrapper_MobClaimSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}
	
	
	
	
	@RequestMapping("/patient/{practice_id}")
	public @ResponseBody ResponseEntity<Object> SearchPatient(@PathVariable(value = "practice_id") Long practiceId,
			@RequestParam(value = "identifier", defaultValue = "") String Identifier, // MRN or SSN identifier=MRN45454
			// OR SSN123321132
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "family", defaultValue = "") String family,
			@RequestParam(value = "given", defaultValue = "") String given,
			@RequestParam(value = "gender", defaultValue = "") String gender, // code ==> male/female/unknown
			// //http://hl7.org/fhir/ValueSet-administrative-gender.html
			@RequestParam(value = "birthdate", defaultValue = "") String birthdate) {

		System.out.println("Inside SearchPatient");
		// System.out.println("Authorization"+Authorization_Header);

		/*
		 * ***************COMBINATION SCENARIOS ******************************
		 * 
		 * 1. identifier i-e MRN or SSN identifier=MRN|45454 OR SSN|123321132
		 * ----------------------------------------------------------------------- 2. i.
		 * name : Either given or Family Name ii. birthdate iii. gender
		 * ----------------------------------------------------------------------- 3. i.
		 * given : Given Name ii. birthdate
		 * ----------------------------------------------------------------------- 4. i.
		 * family : Family Name ii. birthdate
		 * -----------------------------------------------------------------------
		 */

		if (GeneralOperations.isNotNullorEmpty(Identifier)) {

			String[] idSplit = Identifier.split("\\|");

			if (idSplit[0].equalsIgnoreCase("PID") || idSplit[0].equalsIgnoreCase("PATIENT_ID")) {

				if (idSplit.length == 1 || GeneralOperations.isNullorEmpty(idSplit[1])) {
					return new ResponseEntity<Object>("{'error':'Invalid Content.'}", HttpStatus.BAD_REQUEST);
				}

			} else if (idSplit[0].equalsIgnoreCase("SSN")) {

				if (idSplit.length == 1 || GeneralOperations.isNullorEmpty(idSplit[1])) {
					return new ResponseEntity<Object>("{'error':'Invalid Content.'}", HttpStatus.BAD_REQUEST);
				} else if ((idSplit[1].replaceAll("-", "")).length() != 9) {
					return new ResponseEntity<Object>("{'error':'Invalid Content.'}", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<Object>("{'error':'Invalid Content.'}", HttpStatus.BAD_REQUEST);
			}

		} else {
			
		
			if (GeneralOperations.isNullorEmpty(name)
				&& GeneralOperations.isNullorEmpty(birthdate)
				&& GeneralOperations.isNullorEmpty(gender)
				&& GeneralOperations.isNullorEmpty(given)
				&& GeneralOperations.isNullorEmpty(family)
					){
				return new ResponseEntity<Object>("{'error':'Required element is missing.'}", HttpStatus.BAD_REQUEST);
			}

			/*
			if (GeneralOperations.isNotNullorEmpty(name)
					&& (GeneralOperations.isNullorEmpty(birthdate) || GeneralOperations.isNullorEmpty(gender))) {
				return new ResponseEntity<Object>("{'error':'Required element is missing.'}", HttpStatus.BAD_REQUEST);
			} else if (GeneralOperations.isNotNullorEmpty(given) && GeneralOperations.isNullorEmpty(birthdate)) {
				return new ResponseEntity<Object>("{'error':'Required element is missing.'}", HttpStatus.BAD_REQUEST);
			} else if (GeneralOperations.isNotNullorEmpty(family) && GeneralOperations.isNullorEmpty(birthdate)) {
				return new ResponseEntity<Object>("{'error':'Required element is missing.'}", HttpStatus.BAD_REQUEST);
			} else if (GeneralOperations.isNullorEmpty(name) && GeneralOperations.isNullorEmpty(given)
					&& GeneralOperations.isNullorEmpty(family)) {
				return new ResponseEntity<Object>("{'error':'Required element is missing.'}", HttpStatus.BAD_REQUEST);
			}
			*/
		}

		if (GeneralOperations.isNotNullorEmpty(birthdate)
				&& !DateTimeUtil.isDateValid(birthdate, DateFormatEnum.yyyy_MM_dd)) {
			return new ResponseEntity<Object>("{'error':'Date Value is not in Corrent Formate (yyyy-MM-dd)'}",
					HttpStatus.BAD_REQUEST);
		}

		if (GeneralOperations.isNotNullorEmpty(gender) && !GeneralOperations.isValidGenderCode(gender)) {
			return new ResponseEntity<Object>(
					"{'error':'The code \"" + gender + "\" is not known and not legal in this context'}",
					HttpStatus.BAD_REQUEST);
		}

		List<SpParameters> lstCriteriaParam = new ArrayList<>();
		lstCriteriaParam.add(new SpParameters("family", family.toString(), String.class, ParameterMode.IN));
		lstCriteriaParam.add(new SpParameters("given", given.toString(), String.class, ParameterMode.IN));
		lstCriteriaParam.add(new SpParameters("gender", gender.toString(), String.class, ParameterMode.IN));
		lstCriteriaParam.add(new SpParameters("birthdate", birthdate.toString(), String.class, ParameterMode.IN));
		lstCriteriaParam.add(new SpParameters("identifier", Identifier.toString(), String.class, ParameterMode.IN));

		Bundle<SearchPatient> bundle = mobileAppService.searchPatient(practiceId, lstCriteriaParam);

		return new ResponseEntity<Object>(bundle, HttpStatus.OK);
	}

	
	@RequestMapping("/getMobLogedInUserDetail/{user_id}")
	public @ResponseBody ResponseEntity<ORMMobGetLoginUserInfo> getMobLogedInUserDetail(
			@PathVariable(value = "user_id") Long user_id) {
		ORMMobGetLoginUserInfo user = mobileAppService.getMobLogedInUserDetail(user_id);
		return new ResponseEntity<ORMMobGetLoginUserInfo>(user, HttpStatus.OK);
	}
	
	@RequestMapping("/getMobPracticeInfo/{practice_id}")
	public @ResponseBody ResponseEntity<ORMMobPracticeInfo> getMobPracticeInfo(
			@PathVariable(value = "practice_id") Long practice_id) {
		ORMMobPracticeInfo obj = mobileAppService.getMobPracticeInfo(practice_id);
		return new ResponseEntity<ORMMobPracticeInfo>(obj, HttpStatus.OK);
	}
	
	@RequestMapping("/getMoblocation/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMMobGetLocation>> getMoblocation(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMMobGetLocation> obj = mobileAppService.getMoblocation(practice_id);
		return new ResponseEntity<List<ORMMobGetLocation>>(obj, HttpStatus.OK);

	}
	
	@RequestMapping("/getMobPracticeUserName/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMMobPracticeUsers>> getMobPracticeUserName(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMMobPracticeUsers> obj = mobileAppService.getMobPracticeUserName(practice_id);
		return new ResponseEntity<List<ORMMobPracticeUsers>>(obj, HttpStatus.OK);

	}

	
	@RequestMapping("/getMobProviderList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMMobProviderList>> getMobProviderList(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMMobProviderList> obj = mobileAppService.getMobProviderList(practice_id);
		return new ResponseEntity<List<ORMMobProviderList>>(obj, HttpStatus.OK);
	}
	
	@RequestMapping("/getMobBillingProviderList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMMobProviderList>> getMobBillingProviderList(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMMobProviderList> obj = mobileAppService.getMobBillingProviderList(practice_id);
		return new ResponseEntity<List<ORMMobProviderList>>(obj, HttpStatus.OK);
	}
	
	
}
