package com.ihc.ehr.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;
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
import com.ihc.ehr.model.ORMAPIUserPatientGet;
import com.ihc.ehr.model.ORMAPIUsersGet;
import com.ihc.ehr.model.ORMCCDRequest;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMDocCategories;
import com.ihc.ehr.model.ORMGetAttorneyInfo;
import com.ihc.ehr.model.ORMGetCommunication;
import com.ihc.ehr.model.ORMGetPatient;
import com.ihc.ehr.model.ORMGetPatientAllergiesSummary;
import com.ihc.ehr.model.ORMGetPatientAssessmentsSummary;
import com.ihc.ehr.model.ORMGetPatientCareTeamMember;
import com.ihc.ehr.model.ORMGetPatientCareTeamSummary;
import com.ihc.ehr.model.ORMGetPatientCareTeams;
import com.ihc.ehr.model.ORMGetPatientCheckInInfo;
import com.ihc.ehr.model.ORMGetPatientConceptionOutcomesSummary;
import com.ihc.ehr.model.ORMGetPatientConsultSummary;
import com.ihc.ehr.model.ORMGetPatientDocument;
import com.ihc.ehr.model.ORMGetPatientHeaderInfo;
import com.ihc.ehr.model.ORMGetPatientHealthMaintenanceSummary;
import com.ihc.ehr.model.ORMGetPatientInjuery;
import com.ihc.ehr.model.ORMGetPatientInsurance;
import com.ihc.ehr.model.ORMGetPatientInsuranceView;
import com.ihc.ehr.model.ORMGetPatientMedicationSummary;
import com.ihc.ehr.model.ORMGetPatientNextOfKin;
import com.ihc.ehr.model.ORMGetPatientNote;
import com.ihc.ehr.model.ORMGetPatientProblemsSummary;
import com.ihc.ehr.model.ORMGetPatientRaceEthnicity;
import com.ihc.ehr.model.ORMGetPatientReferralSummary;
import com.ihc.ehr.model.ORMGetPatientScannedCards;
import com.ihc.ehr.model.ORMGetPatientSuregeriesProceduresSummary;
import com.ihc.ehr.model.ORMGetPatientVitalGraphData;
import com.ihc.ehr.model.ORMGetPatient_consultant;
import com.ihc.ehr.model.ORMGetPhrUsersNames;
import com.ihc.ehr.model.ORMHeaderVitals;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatInjury;
import com.ihc.ehr.model.ORMPatientCommunication;
import com.ihc.ehr.model.ORMPatientConfirmDel;
import com.ihc.ehr.model.ORMPatientConsultant;
import com.ihc.ehr.model.ORMPatientInsurancesForMergeGet;
import com.ihc.ehr.model.ORMPatientNotesSave;
import com.ihc.ehr.model.ORMSaveDocument;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORM_HealthInformationCaptureAttachments;
import com.ihc.ehr.model.ORM_getHealthInfoCapture;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.WrapperPatientInfoCapture;
import com.ihc.ehr.model.Wrapper_APIUserSave;
import com.ihc.ehr.model.Wrapper_ObjectSave;
import com.ihc.ehr.model.Wrapper_PatientSave;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.service.PatientService;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientService patientServcie;

	@Autowired
	GeneralService generalService;

	// @Autowired
	// EncounterService encounterService;

	@RequestMapping("/getbyid/{patient_id}")
	public @ResponseBody ResponseEntity<ORMGetPatient> getPatientByID(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientByID:  patientId=" + patientId);

		ORMGetPatient patient = patientServcie.getPatientByID(patientId);

		return new ResponseEntity<ORMGetPatient>(patient, HttpStatus.OK);
	}

	@RequestMapping("/getheaderinfo/{patient_id}")
	public @ResponseBody ResponseEntity<ORMGetPatientHeaderInfo> getPatientHeaderInfo(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientHeaderInfo:  patientId=" + patientId);

		ORMGetPatientHeaderInfo patient = patientServcie.getPatientHeaderInfo(patientId);

		return new ResponseEntity<ORMGetPatientHeaderInfo>(patient, HttpStatus.OK);
	}

	@RequestMapping("/getheadervitals/{patient_id}")
	public @ResponseBody ResponseEntity<ORMHeaderVitals> getPatientHeaderVitals(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientHeaderVitals:  patientId=" + patientId);

		ORMHeaderVitals patVital = patientServcie.getPatientHeaderVitals(patientId);

		return new ResponseEntity<ORMHeaderVitals>(patVital, HttpStatus.OK);
	}

	@RequestMapping("/getvitalgraph/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientVitalGraphData>> getVitalGraphData(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientVitalSummary:  patientId=" + patientId);

		List<ORMGetPatientVitalGraphData> lstGraphData = patientServcie.getVitalGraphData(patientId);

		return new ResponseEntity<List<ORMGetPatientVitalGraphData>>(lstGraphData, HttpStatus.OK);
	}

	@RequestMapping("/getmedicationsummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientMedicationSummary>> getMedicationSummary(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getSummaryMedication:  patientId=" + patientId);

		List<ORMGetPatientMedicationSummary> lstMed = patientServcie.getMedicationSummary(patientId);

		return new ResponseEntity<List<ORMGetPatientMedicationSummary>>(lstMed, HttpStatus.OK);
	}

	@RequestMapping("/getallergiesummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientAllergiesSummary>> getAllergiesSummary(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getAllergiesSummary:  patientId=" + patientId);

		List<ORMGetPatientAllergiesSummary> lstAllergies = patientServcie.getAllergiesSummary(patientId);

		return new ResponseEntity<List<ORMGetPatientAllergiesSummary>>(lstAllergies, HttpStatus.OK);
	}

	@RequestMapping("/getproblemsummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientProblemsSummary>> getProblemsSummary(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getProblemsSummary:  patientId=" + patientId);

		List<ORMGetPatientProblemsSummary> lstProblems = patientServcie.getProblemsSummary(patientId);

		return new ResponseEntity<List<ORMGetPatientProblemsSummary>>(lstProblems, HttpStatus.OK);
	}

	@RequestMapping("/getassessmentsummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientAssessmentsSummary>> getAssessmentsSummary(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getAssessmentsSummary:  patientId=" + patientId);

		List<ORMGetPatientAssessmentsSummary> lstAssessments = patientServcie.getAssessmentsSummary(patientId);

		return new ResponseEntity<List<ORMGetPatientAssessmentsSummary>>(lstAssessments, HttpStatus.OK);
	}

	/*
	 * @RequestMapping("/getPatientImmunizationSummary") public @ResponseBody
	 * ResponseEntity<List<ORMChartImmunizationSummaryGet>>
	 * getPatientImmunizationSummary(
	 * 
	 * @RequestBody SearchCriteria searchCriteria) {
	 * 
	 * GeneralOperations.logMsg("Inside getPatientImmunizationSummary");
	 * 
	 * List<ORMChartImmunizationSummaryGet> lst =
	 * encounterService.getPatientImmunizationSummary(searchCriteria);
	 * 
	 * return new ResponseEntity<List<ORMChartImmunizationSummaryGet>>(lst,
	 * HttpStatus.OK); }
	 */

	@RequestMapping("/getsuregeriesproceduressummary/{patient_id}/{type}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientSuregeriesProceduresSummary>> getSurgeriesProceduresSummary(
			@PathVariable(value = "patient_id") Long patientId, @PathVariable(value = "type") String type) {
		GeneralOperations.logMsg("Inside getSurgeriesProceduresSummary:  patientId=" + patientId + " type=" + type);
		String entry_type = "ALL";

		switch (type.toUpperCase()) {
		case "SURGERIES":
			entry_type = "CHART_SURGERY";
			break;
		case "PROCEDURES":
			entry_type = "CHART_PROCEDURE";
			break;
		default:
			entry_type = "ALL";
			break;
		}

		List<ORMGetPatientSuregeriesProceduresSummary> lstSurgereis = patientServcie
				.getSurgeriesProceduresSummary(patientId, entry_type);

		return new ResponseEntity<List<ORMGetPatientSuregeriesProceduresSummary>>(lstSurgereis, HttpStatus.OK);
	}

	@RequestMapping("/gethealthmaintenancesummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientHealthMaintenanceSummary>> getHealthMaintenanceSummary(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getHealthMaintenanceSummary:  patientId=" + patientId);

		List<ORMGetPatientHealthMaintenanceSummary> lstHM = patientServcie.getHealthMaintenanceSummary(patientId);

		return new ResponseEntity<List<ORMGetPatientHealthMaintenanceSummary>>(lstHM, HttpStatus.OK);
	}

	@RequestMapping("/getSpeciality/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMThreeColum>> getSpeciality(
			@PathVariable(value = "practice_id") String practice_id) {
		GeneralOperations.logMsg("Inside getSpeciality:  Practice_ID=" + practice_id);

		List<ORMThreeColum> lstHM = patientServcie.getSpeciality(practice_id);

		return new ResponseEntity<List<ORMThreeColum>>(lstHM, HttpStatus.OK);
	}

	@RequestMapping("/getPatientConsultant/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatient_consultant>> getPatientConsultant(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMGetPatient_consultant> lstHM = patientServcie.getPatientConsultant(patient_id);

		return new ResponseEntity<List<ORMGetPatient_consultant>>(lstHM, HttpStatus.OK);
	}

	/*
	 * @RequestMapping("/savePatientConsultant") public
	 * ResponseEntity<ORMPatientConsultant> savePatientConsultant(
	 * 
	 * @RequestBody ORMPatientConsultant obj) {
	 * System.out.println("savePatientConsultant");
	 * patientServcie.savePatientConsultant(obj);
	 * 
	 * return new ResponseEntity<ORMPatientConsultant>(obj, HttpStatus.OK);
	 * 
	 * }
	 */
	@RequestMapping("/deletePatientConsultant")
	public int deletePatientConsultant(@RequestBody ORMDeleteRecord obj) {
		System.out.println("deletePatientConsultant");
		obj.setTable_name("patient_consultant");
		obj.setColumn_name("consultation_id");

		return generalService.deleteRecord(obj);
	}

	@RequestMapping("/getconeptionoutcomesummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientConceptionOutcomesSummary>> getConceptionOutcomeSummary(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getConceptionOutcomeSummary:  patientId=" + patientId);

		List<ORMGetPatientConceptionOutcomesSummary> lst = patientServcie.getConceptionOutcomeSummary(patientId);

		return new ResponseEntity<List<ORMGetPatientConceptionOutcomesSummary>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getedd/{patient_id}")
	public @ResponseBody ResponseEntity<String> getEDD(@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getEDD:  patientId=" + patientId);

		String edd = "{ \"edd\" : \"" + patientServcie.getEDD(patientId) + "\"}";

		return new ResponseEntity<String>(edd, HttpStatus.OK);
	}

	@RequestMapping("/getconsultsummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientConsultSummary>> getConsultSummary(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getConsultSummary:  patientId=" + patientId);

		List<ORMGetPatientConsultSummary> lst = patientServcie.getConsultSummary(patientId);

		return new ResponseEntity<List<ORMGetPatientConsultSummary>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getreferralsummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientReferralSummary>> getReferralSummary(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getReferralSummary:  patientId=" + patientId);

		List<ORMGetPatientReferralSummary> lst = patientServcie.getReferralSummary(patientId);

		return new ResponseEntity<List<ORMGetPatientReferralSummary>>(lst, HttpStatus.OK);
	}

	// Patient Correspondence
	@RequestMapping("/getCommunications/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetCommunication>> getCommunications(
			@PathVariable(value = "patient_id") Long patient_id) {
		List<ORMGetCommunication> lst = patientServcie.getCommunications(patient_id);
		return new ResponseEntity<List<ORMGetCommunication>>(lst, HttpStatus.OK);
	}

	// patient injury
	@RequestMapping("/getPatInjury/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientInjuery>> getPatInjury(
			@PathVariable(value = "patient_id") Long patient_id) {
		List<ORMGetPatientInjuery> lst = patientServcie.getPatInjury(patient_id);
		return new ResponseEntity<List<ORMGetPatientInjuery>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/deletePatientInjury")
	public int deletePatientInjury(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deletePatientInjury: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("patient_injury");
		ormDeleteRecord.setColumn_name("injury_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	// getFirmList
	@RequestMapping("/getFirmList/{value}")
	public @ResponseBody ResponseEntity<List<ORMGetAttorneyInfo>> getFirmList(
			@PathVariable(value = "value") String value) {
		List<ORMGetAttorneyInfo> lstSearchPatient = patientServcie.getFirmList(value);
		return new ResponseEntity<List<ORMGetAttorneyInfo>>(lstSearchPatient, HttpStatus.OK);
	}

	// getInsList
	// @RequestMapping("/getInsList/{value}")
	// public @ResponseBody ResponseEntity<List<ORMInsuranceSearch>> getInsList(
	// @PathVariable(value = "value") String value)
	// {
	// List<ORMInsuranceSearch> lstSearchPatient=patientServcie.getInsList(value);
	// return new ResponseEntity<List<ORMInsuranceSearch>>(lstSearchPatient ,
	// HttpStatus.OK);
	// }
	@RequestMapping("/saveEditPatInjury")
	public ResponseEntity<ORMPatInjury> saveEditPatInjury(@RequestBody ORMPatInjury obj) {
		patientServcie.saveEditPatInjury(obj);
		return new ResponseEntity<ORMPatInjury>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getPatientDocuments/{patient_id}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientDocument>> getPatientDocuments(
			@PathVariable(value = "patient_id") String patient_id,
			@PathVariable(value = "practice_id") String practice_id) {
		List<ORMGetPatientDocument> lst = patientServcie.getPatientDocuments(patient_id, practice_id);
		return new ResponseEntity<List<ORMGetPatientDocument>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/deletePatientDocument")
	public int deletePatientDocument(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("patient_document");
		ormDeleteRecord.setColumn_name("patient_document_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/confirmPatientDel/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMPatientConfirmDel>> confirmPatientDel(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMPatientConfirmDel> lst = patientServcie.confirmPatientDel(patient_id);
		return new ResponseEntity<List<ORMPatientConfirmDel>>(lst, HttpStatus.OK);
	}

	// @RequestMapping("/deletePatient")
	// public int deletePatient(
	// @RequestBody ORMDeleteRecord ormDeleteRecord)
	// {
	// ormDeleteRecord.setTable_name("patient");
	// ormDeleteRecord.setColumn_name("patient_id");
	//
	// return generalService.deleteRecord(ormDeleteRecord);
	// }
	@RequestMapping("/deletePatient")
	public int deletePatient(@RequestBody Wrapper_ObjectSave<ORMDeleteRecord> objData) {

		List<ORMKeyValue> objOtherData = objData.getLstKeyValue();
		String notes = "";
		for (ORMKeyValue item : objOtherData) {

			if (item.getKey().equalsIgnoreCase("notes")) {
				notes = item.getValue();
			}
		}

		System.out.println("notes:" + notes);
		ORMDeleteRecord objormDelete = objData.getOrmSave();
		objormDelete.setTable_name("patient");
		objormDelete.setColumn_name("patient_id");
		generalService.deleteRecord(objormDelete);

		return generalService.updateQuery(
				"update patient set deleted_notes='" + notes + "' where patient_id=" + objormDelete.getColumn_id());
	}

	@RequestMapping("/getPatientInsurance/{patient_id}/{status}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientInsurance>> getPatientInsurance(
			@PathVariable(value = "patient_id") Long patientId, @PathVariable(value = "status") String status) {
		GeneralOperations.logMsg("Inside getPatientInsurance:  patientId=" + patientId + " Status=" + status);

		List<ORMGetPatientInsurance> lstPatientInsurance = patientServcie.getPatientInsurance(patientId, status);

		return new ResponseEntity<List<ORMGetPatientInsurance>>(lstPatientInsurance, HttpStatus.OK);
	}

	@RequestMapping("/getPatientInsuranceView/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientInsuranceView>> getPatientInsuranceView(
			@PathVariable(value = "patient_id") Long patientId) {
		List<ORMGetPatientInsuranceView> lstPatientInsurance = patientServcie.getPatientInsuranceView(patientId);

		return new ResponseEntity<List<ORMGetPatientInsuranceView>>(lstPatientInsurance, HttpStatus.OK);
	}

	@RequestMapping("/getPatientRaceEthnicty/{category}/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientRaceEthnicity>> getPatientRaceEthnicty(
			@PathVariable(value = "category") String category, @PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientRaceEthnicty:  patientId=" + patientId);

		List<ORMGetPatientRaceEthnicity> lst = patientServcie.getPatientRaceEthnicty(category, patientId);

		return new ResponseEntity<List<ORMGetPatientRaceEthnicity>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatientNextOfKin/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientNextOfKin>> ORMGetPatientNextOfKin(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientNextOfKin:  patientId=" + patientId);

		List<ORMGetPatientNextOfKin> lst = patientServcie.getPatientNextOfKin(patientId);

		return new ResponseEntity<List<ORMGetPatientNextOfKin>>(lst, HttpStatus.OK);
	}

	/*
	 * @RequestMapping("/getPatientImmRegInfo/{patient_id}") public @ResponseBody
	 * ResponseEntity<ORMGetPatImmRegInfoDisplay> getPatientImmRegInfo(
	 * 
	 * @PathVariable(value = "patient_id") Long patientId) {
	 * GeneralOperations.logMsg("Inside getPatientImmRegInfo:  patientId=" +
	 * patientId);
	 * 
	 * ORMGetPatImmRegInfoDisplay obj =
	 * patientServcie.getPatientImmRegInfo(patientId);
	 * 
	 * return new ResponseEntity<ORMGetPatImmRegInfoDisplay>(obj, HttpStatus.OK); }
	 */

	@RequestMapping("/getPatientCareTeamMemberSummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientCareTeamSummary>> getPatientCareTeamSummary(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientCareTeamDisplay:  patientId=" + patientId);

		List<ORMGetPatientCareTeamSummary> obj = patientServcie.getPatientCareTeamSummary(patientId);

		return new ResponseEntity<List<ORMGetPatientCareTeamSummary>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getPatientCareTeams/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientCareTeams>> getPatientCareTeams(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientCareTeam:  patientId=" + patientId);

		List<ORMGetPatientCareTeams> obj = patientServcie.getPatientCareTeams(patientId);

		return new ResponseEntity<List<ORMGetPatientCareTeams>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getPatientCareTeamMembers/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientCareTeamMember>> getPatientCareTeamMembers(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientCareTeamMembers:  memberId=" + patientId);

		List<ORMGetPatientCareTeamMember> obj = patientServcie.getPatientCareTeamMembers(patientId);

		return new ResponseEntity<List<ORMGetPatientCareTeamMember>>(obj, HttpStatus.OK);
	}

	// PatientSaveObjectWrapper

	@RequestMapping(value = "/savePatient", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> SavePatient(
			@RequestParam(required = false, value = "pic") MultipartFile picData, // inputFile,
			@RequestParam("patient_data") String patientData)
			throws JsonParseException, JsonMappingException, IOException {

		System.out.println("savePatient");

		System.out.println("inputFile" + picData);

		ObjectMapper mapper = new ObjectMapper();
		Wrapper_PatientSave objPatientWrapper = mapper.readValue(patientData, Wrapper_PatientSave.class);

		ServiceResponse<ORMKeyValue> resp = patientServcie.SavePatient(objPatientWrapper, picData);

		// ServiceResponse<ORMKeyValue> resp = new ServiceResponse();

		// if (result == 0) {
		// resp.setStatus(ServiceResponseStatus.ERROR);
		// resp.setResponse("An Error Occured while saving record.");
		// } else {
		// resp.setStatus(ServiceResponseStatus.SUCCESS);
		// resp.setResponse("Data has been saved successfully.");
		// resp.setResult(objPatientWrapper.getPatient().getPatient_id().toString());
		// }

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

		// Employee_Detail emp= JSON.parse(empData);
		/*
		 * 
		 * Employee_Save objEMPSave=new Employee_Save();
		 * 
		 * if(emp.getEmp_id()==null) { objEMPSave.setDate_created(new Date()); }
		 * 
		 * objEMPSave.setEmp_id(emp.getEmp_id());
		 * objEMPSave.setLast_name(emp.getLast_name());
		 * objEMPSave.setFirst_name(emp.getFirst_name());
		 * objEMPSave.setDept_id(emp.getDept_id());
		 * objEMPSave.setDesignation_id(emp.getDesignation_id());
		 * objEMPSave.setShift_id(emp.getShift_id());
		 * objEMPSave.setCell_number(emp.getCell_number());
		 * objEMPSave.setHome_number(emp.getHome_number());
		 * objEMPSave.setAddress(emp.getAddress());
		 * objEMPSave.setEmail_personal(emp.getEmail_personal());
		 * objEMPSave.setEmail_official(emp.getEmail_official());
		 * objEMPSave.setBank_account(emp.getBank_account());
		 * objEMPSave.setNic(emp.getNic());
		 * objEMPSave.setEmergency_contact_info(emp.getEmergency_contact_info());
		 * objEMPSave.setEmp_status(emp.getEmp_status());
		 * objEMPSave.setDate_joined(emp.getDate_joined());
		 * objEMPSave.setDate_left(emp.getDate_left()); objEMPSave.setPic(emp.getPic());
		 * objEMPSave.setInactive(emp.getEmp_status().toString().toLowerCase().equals(
		 * "active") ? false : true); objEMPSave.setDeleted(false);
		 * objEMPSave.setCreated_user(emp.getCreated_user());
		 * objEMPSave.setModified_user(emp.getModified_user());
		 * objEMPSave.setDate_created(emp.getDate_created());
		 * objEMPSave.setDate_modified(new Date());
		 * 
		 * //emp.setDate_modified(ClockUtil.getCurrentDateString(ClockUtil.
		 * DATEFORMAT_YYYY_MM_DD_HH_mm_ss_SSS));
		 * 
		 * Long generatedId=attendanceService.SaveEmployee(objEMPSave,inputFile);
		 * 
		 * if(generatedId>0) { emp.setEmp_id(generatedId);
		 * emp.setDate_created(objEMPSave.getDate_created());
		 * emp.setCreated_user(objEMPSave.getCreated_user());
		 * 
		 * return new ResponseEntity<Employee_Detail>(emp, HttpStatus.OK); } else {
		 * return new ResponseEntity<Employee_Detail>(new Employee_Detail(),
		 * HttpStatus.NOT_MODIFIED); }
		 */
	}

	@RequestMapping("/savePatientNotes")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> savePatientNotes(
			@RequestBody ORMPatientNotesSave ormPatientNotesSave) {
		GeneralOperations.logMsg("Inside Patient Notes: ");
		ServiceResponse<ORMKeyValue> resp = patientServcie.savePatientNotes(ormPatientNotesSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveeditCorrespondence", method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveeditCorrespondence(
			@RequestParam(required = false, value = "docFile") MultipartFile docFile,
			@RequestParam("docData") String docData, @RequestParam("docCategory") String docCategory,
			@RequestParam("communication") String communication)
			throws JsonParseException, JsonMappingException, IOException, JsonParseException {

		ObjectMapper mapper = new ObjectMapper();
		ORMSaveDocument patDoc;
		if (docData.equals("null") || docData.equals(null)) {
			patDoc = null;
		} else {
			patDoc = mapper.readValue(docData, ORMSaveDocument.class);
		}

		ORMPatientCommunication comData = mapper.readValue(communication, ORMPatientCommunication.class);

		// String doc_ca=(mapper.readValue(docCategory,String.class));
		// System.out.println("Document Category:"+doc_ca);
		System.out.println("Document Category:" + docCategory);
		docCategory = "PatientDocuments";

		Long result = patientServcie.saveeditCorrespondence(patDoc, docFile, docCategory, comData);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			// resp.setError_message("");
			resp.setResult(result.toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			// resp.setError_message("An Error Occured while uploading document.");
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/savePatientConsultant", method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> savePatientConsultant(
			@RequestParam(required = false, value = "docFile") MultipartFile docFile,
			@RequestParam("docData") String docData, @RequestParam("docCategory") String docCategory,
			@RequestParam("consult") String consult)
			throws JsonParseException, JsonMappingException, IOException, JsonParseException {

		ObjectMapper mapper = new ObjectMapper();
		ORMSaveDocument patDoc;
		if (docData.equals("null") || docData.equals(null)) {
			patDoc = null;
		} else {
			patDoc = mapper.readValue(docData, ORMSaveDocument.class);
		}

		ORMPatientConsultant comData = mapper.readValue(consult, ORMPatientConsultant.class);

		// String doc_ca=(mapper.readValue(docCategory,String.class));
		// System.out.println("Document Category:"+doc_ca);
		// System.out.println("Document Category:" + docCategory);
		docCategory = "PatientDocuments";

		Long result = patientServcie.savePatientConsultant(patDoc, docFile, docCategory, comData);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			// resp.setError_message("");
			resp.setResult(result.toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			// resp.setError_message("An Error Occured while uploading document.");
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/saveOpenedPatient")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveOpenedPatient(
			@RequestBody List<ORMKeyValue> lstKeyValue) {
		GeneralOperations.logMsg("Inside saveOpenedPatient: ");

		ServiceResponse<ORMKeyValue> resp = patientServcie.saveOpenedPatient(lstKeyValue);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getHealthInfoCapture/{patient_id}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORM_getHealthInfoCapture>> getHealthInfoCapture(
			@PathVariable(value = "patient_id") Long patient_id,
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORM_getHealthInfoCapture> lst = patientServcie.getHealthInfoCapture(patient_id, practice_id);
		return new ResponseEntity<List<ORM_getHealthInfoCapture>>(lst, HttpStatus.OK);
	}

	/*
	 * @RequestMapping("/getHealthInfoCaptureAttach/{health_info_id}/{recType}")
	 * public @ResponseBody
	 * ResponseEntity<List<ORM_HealthInformationCaptureAttachments>>
	 * getHealthInfoCaptureAttach(
	 * 
	 * @PathVariable(value = "health_info_id") String health_info_id,
	 * 
	 * @PathVariable(value="attach_type") String recType) {
	 * List<ORM_HealthInformationCaptureAttachments> lst =
	 * patientServcie.getHealthInfoCaptureAttach(health_info_id,recType); return new
	 * ResponseEntity<List<ORM_HealthInformationCaptureAttachments>>(lst ,
	 * HttpStatus.OK); }
	 * 
	 * @RequestMapping("/getHealthInfoCaptureLinks/{health_info_id}/{recType}")
	 * public @ResponseBody
	 * ResponseEntity<List<ORM_HealthInformationCaptureAttachments>>
	 * getHealthInfoCaptureLinks(
	 * 
	 * @PathVariable(value = "health_info_id") String health_info_id,
	 * 
	 * @PathVariable(value="attach_type") String recType) {
	 * List<ORM_HealthInformationCaptureAttachments> lst =
	 * patientServcie.getHealthInfoCaptureLinks(health_info_id,recType); return new
	 * ResponseEntity<List<ORM_HealthInformationCaptureAttachments>>(lst ,
	 * HttpStatus.OK); }
	 */
	// List<ORM_HealthInformationCaptureAttachments>
	// getHealthInfoCaptureAttach(SearchCriteria searchCriteria);
	// List<ORM_HealthInformationCaptureAttachments>
	// getHealthInfoCaptureLinks(SearchCriteria searchCriteria);
	@RequestMapping("/getHealthInfoCaptureAttach")
	public @ResponseBody ResponseEntity<List<ORM_HealthInformationCaptureAttachments>> getHealthInfoCaptureAttach(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORM_HealthInformationCaptureAttachments> lst = patientServcie.getHealthInfoCaptureAttach(searchCriteria);
		return new ResponseEntity<List<ORM_HealthInformationCaptureAttachments>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getHealthInfoCaptureLinks")
	public @ResponseBody ResponseEntity<List<ORM_HealthInformationCaptureAttachments>> getHealthInfoCaptureLinks(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORM_HealthInformationCaptureAttachments> lst = patientServcie.getHealthInfoCaptureLinks(searchCriteria);
		return new ResponseEntity<List<ORM_HealthInformationCaptureAttachments>>(lst, HttpStatus.OK);
	}
	/*
	 * @RequestMapping("/AddEditNewHealthInformation") public @ResponseBody
	 * ResponseEntity<ServiceResponse> AddEditNewHealthInformation(
	 * 
	 * @RequestBody WrapperPatientInfoCapture WrapperPatientInfoCapture) {
	 * ServiceResponse resp =
	 * patientServcie.AddEditNewHealthInformation(WrapperPatientInfoCapture); return
	 * new ResponseEntity<ServiceResponse>(resp, HttpStatus.OK); }
	 */

	/*
	 * @RequestMapping(value = "/AddEditNewHealthInformation", headers =
	 * ("content-type=multipart/*"), method = RequestMethod.POST) public
	 * ResponseEntity<?> AddEditNewHealthInformation(@RequestParam("attachFile")
	 * MultipartFile[] attachfile,
	 * 
	 * @RequestParam("infoAttachLinks") String infoattachlinks,
	 * 
	 * @RequestParam("infoCapture") String infocapture,
	 * 
	 * @RequestParam("attachpath") String attachpath) throws JsonParseException,
	 * JsonMappingException, IOException {
	 * 
	 * 
	 * 
	 * System.out.println("AddEditNewHealthInformation");
	 * System.out.println("inputFile"+attachfile);
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); ORMHealthInformationCapture
	 * infoCapture = mapper.readValue(infocapture.toString(),
	 * ORMHealthInformationCapture.class);
	 * List<ORM_HealthInformationCaptureAttachments> infoAttachLinks =
	 * mapper.readValue(infoattachlinks.toString(),
	 * ORM_HealthInformationCaptureAttachments.class);
	 * 
	 * ServiceResponse<ORMKeyValue> resp =
	 * patientServcie.AddEditNewHealthInformation(attachfile, infoAttachLinks,
	 * infoCapture, attachpath);
	 * 
	 * 
	 * return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	 * 
	 * }
	 */

	@RequestMapping(value = "/AddEditNewHealthInformation", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> AddEditNewHealthInformation(
			@RequestParam(required = false, value = "attachFile") MultipartFile[] attachfile, // inputFile,
			@RequestParam("infoWrapperData") String objInfoCaptureWrapper)
			throws JsonParseException, JsonMappingException, IOException {
		System.out.println("inputFile" + attachfile);
		ObjectMapper mapper = new ObjectMapper();
		WrapperPatientInfoCapture objInformationCapturetWrapper = mapper.readValue(objInfoCaptureWrapper,
				WrapperPatientInfoCapture.class);
		ServiceResponse<ORMKeyValue> resp = patientServcie.AddEditNewHealthInformation(objInformationCapturetWrapper,
				attachfile);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getPatientCheckedInInfo/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientCheckInInfo>> getPatientCheckedInInfo(
			@PathVariable(value = "patient_id") Long patientId) {
		List<ORMGetPatientCheckInInfo> obj = patientServcie.getPatientCheckedInInfo(patientId);

		return new ResponseEntity<List<ORMGetPatientCheckInInfo>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getPatientNotes/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientNote>> getPatientNotes(
			@PathVariable(value = "patient_id") Long patientId) {
		List<ORMGetPatientNote> obj = patientServcie.getPatientNotes(patientId);

		return new ResponseEntity<List<ORMGetPatientNote>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getStaffNoteAlert/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientNote>> getStaffNoteAlert(
			@PathVariable(value = "patient_id") Long patientId) {
		List<ORMGetPatientNote> obj = patientServcie.getStaffNoteAlert(patientId);

		return new ResponseEntity<List<ORMGetPatientNote>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/deleteNotes")
	public int deleteNotes(@RequestBody ORMDeleteRecord obj) {
		System.out.println("deletePatientNotes");
		obj.setTable_name("patient_note");
		obj.setColumn_name("patient_note_id");

		return generalService.deleteRecord(obj);
	}

	@RequestMapping("/deleteResultStaffNotes")
	public int deleteResultStaffNotes(@RequestBody ORMDeleteRecord obj) {
		System.out.println("deleteResultStaffNotes");
		obj.setTable_name("patient_order_result_staff_note");
		obj.setColumn_name("notes_id");

		return generalService.deleteRecord(obj);
	}

	@RequestMapping("/deleteAttabhmentStaffNotes")
	public int deleteAttabhmentStaffNotes(@RequestBody ORMDeleteRecord obj) {
		System.out.println("deleteAttabhmentStaffNotes");
		obj.setTable_name("patient_order_attachment_comment");
		obj.setColumn_name("comment_id");

		return generalService.deleteRecord(obj);
	}

	// //QRDA Import
	// @RequestMapping(value = "/qrdaImport", headers =
	// ("content-type=multipart/*"), method = RequestMethod.POST)
	// public ResponseEntity<ServiceResponse<ORMKeyValue>> qrdaImport(
	// @RequestParam(required = false, value = "docFile") MultipartFile docFile,
	// @RequestParam("provider_id") String provider_id, @RequestParam("practice_id")
	// String practice_id)
	// throws JsonParseException, JsonMappingException, IOException {
	//
	// Long result = patientServcie.qrdaImport(provider_id, practice_id,docFile);
	//
	// ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
	//
	// if (result > 0) {
	// resp.setStatus(ServiceResponseStatus.SUCCESS);
	// // resp.setError_message("");
	// resp.setResult(result.toString());
	// } else {
	// resp.setStatus(ServiceResponseStatus.ERROR);
	// // resp.setError_message("An Error Occured while uploading document.");
	// }
	//
	// return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	// }

	@RequestMapping("/getPatientScannedCards/{patient_id}")
	public @ResponseBody ResponseEntity<ORMGetPatientScannedCards> getPatientScannedCards(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientScannedCards:  patientId=" + patientId);

		ORMGetPatientScannedCards obj = patientServcie.getPatientScannedCards(patientId);

		return new ResponseEntity<ORMGetPatientScannedCards>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getAPIUsers")
	public @ResponseBody ResponseEntity<List<ORMAPIUsersGet>> getAPIUsers(@RequestBody SearchCriteria searchCriteria) {

		GeneralOperations.logMsg("Inside getAPIUsers: SearchCriteria=" + searchCriteria.toString());

		List<ORMAPIUsersGet> lst = patientServcie.getAPIUsers(searchCriteria);

		return new ResponseEntity<List<ORMAPIUsersGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getAPIUserPatients")
	public @ResponseBody ResponseEntity<List<ORMAPIUserPatientGet>> getAPIUserPatients(
			@RequestBody SearchCriteria searchCriteria) {

		GeneralOperations.logMsg("Inside getAPIUserPatients: SearchCriteria=" + searchCriteria.toString());

		List<ORMAPIUserPatientGet> lst = patientServcie.getAPIUserPatients(searchCriteria);

		return new ResponseEntity<List<ORMAPIUserPatientGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveAPIUser")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveAPIUser(
			@RequestBody Wrapper_APIUserSave Wrapper_APIUserSave) {

		GeneralOperations.logMsg("Inside saveAPIUser");

		ServiceResponse<ORMKeyValue> resp = patientServcie.saveAPIUser(Wrapper_APIUserSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deleteAPIUser")
	public int deleteAPIUser(@RequestBody ORMDeleteRecord obj) {
		int result = 0;

		obj.setTable_name("api_users");
		obj.setColumn_name("user_id");
		result = generalService.deleteRecord(obj);

		obj.setTable_name("api_user_patients");
		obj.setColumn_name("user_id");
		result += generalService.deleteRecord(obj);

		return result;

	}

	@RequestMapping("/GenerateCCDA")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> GenerateCCDA(@RequestBody ORMCCDRequest ormCCD) {
		String resultPath = "";
		int responseStatus;

		InputStreamReader in = null;
		StringBuilder sb = new StringBuilder();
		String DiUrl = "http://localhost:8001/ihc-common-api/ccd/GenerateCCDA/" + ormCCD.getPatient_id() + "/"
				+ ormCCD.getChart_id() + "/" + ormCCD.getProvider_id() + "/" + ormCCD.getPractice_id() + "/"
				+ ormCCD.getIsReferal() + "/" + ormCCD.getCcd_Version() + "/" + ormCCD.getCcd_type();
		System.out.println("Requeted URL:" + DiUrl);

		try {
			URL url = new URL(DiUrl);
			// URLConnection conn = url.openConnection();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(40000); // 40 seconds time out
			// String line = "";
			String user_pass = "ihc" + ":" + "ihc@5133";
			String encoded = "";
			try {
				encoded = DatatypeConverter.printBase64Binary(user_pass.getBytes());
			} catch (Exception e) {
				throw new RuntimeException("Exception while encoding URL:--" + e);
			}
			conn.setRequestProperty("Authorization", "Basic " + encoded);
			responseStatus = conn.getResponseCode();
			if (responseStatus == 200) {
				if (conn != null && conn.getInputStream() != null) {
					in = new InputStreamReader(conn.getInputStream(), Charset.defaultCharset());
					BufferedReader bufferedReader = new BufferedReader(in);
					if (bufferedReader != null) {
						int cp;
						while ((cp = bufferedReader.read()) != -1) {
							sb.append((char) cp);
						}
						bufferedReader.close();
					}
				}
				System.out.println("String Builder:" + sb.toString());
				JSONObject objJson = new JSONObject(sb.toString());

				resultPath = objJson.get("ccd_xml_show_path").toString() + "~"
						+ objJson.get("ccd_xml_download_path").toString() + "~";
				resultPath += objJson.get("ccd_html_show_path").toString() + "~"
						+ objJson.get("ccd_html_download_path").toString();

			} else {
				in = new InputStreamReader(conn.getErrorStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
				System.out.println("Error in CCD Get Service:" + sb.toString());
			}
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + DiUrl + "--" + e);
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		resp.setResult(resultPath);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getPatientInsuranceToMerge/{patientIds}")
	public @ResponseBody ResponseEntity<List<ORMPatientInsurancesForMergeGet>> getPatientInsuranceToMerge(
			@PathVariable(value = "patientIds") String patientIds) {
		GeneralOperations.logMsg("Inside getPatientInsuranceToMerge: ");
		List<ORMPatientInsurancesForMergeGet> lst = patientServcie.getPatientInsuranceToMerge(patientIds);

		return new ResponseEntity<List<ORMPatientInsurancesForMergeGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/mergePatients")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> mergePatients(
			@RequestBody List<ORMKeyValue> lstKeyValue) {
		GeneralOperations.logMsg("Inside mergePatients: ");

		ServiceResponse<ORMKeyValue> resp = patientServcie.mergePatients(lstKeyValue);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	// @RequestMapping("/deleteDocumentCategory")
	// public int deleteDocumentCategory(@RequestBody ORMDeleteRecord
	// ormDeleteRecord) {
	// ormDeleteRecord.setTable_name("document_categories");
	// ormDeleteRecord.setColumn_name("document_categories_id");
	// return generalService.deleteRecord(ormDeleteRecord);
	// }
	@RequestMapping("/addEditDocCategory")
	public ResponseEntity<ORMDocCategories> addEditDocCategory(@RequestBody ORMDocCategories obj) {
		patientServcie.addEditDocCategory(obj);
		return new ResponseEntity<ORMDocCategories>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getPatientTaskData")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> getPatientTaskData(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMTwoColumnGeneric> lst = patientServcie.getPatientTaskData(searchCriteria);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/deleteDocumentCategory")
	public int deleteDocumentCategory(@RequestBody ORMDocCategories obj) {
		return patientServcie.deleteDocumentCategory(obj);
	}

	@RequestMapping("/deleteInfoCapture")
	public int deleteInfoCapture(@RequestBody ORMDeleteRecord obj) {
		System.out.println("deleteInfoCapture");
		obj.setTable_name("health_information_capture");
		obj.setColumn_name("health_info_id");
		return generalService.deleteRecord(obj);
	}

	@RequestMapping("/checkIfPatientExists")
	public @ResponseBody ResponseEntity<Long> checkIfPatientExists(@RequestBody SearchCriteria searchCriteria) {
		Long patientId = patientServcie.checkIfPatientExists(searchCriteria);
		return new ResponseEntity<Long>(patientId, HttpStatus.OK);
	}

	@RequestMapping(value = "/savePatientPic", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> savePatientPic(
			@RequestParam(required = false, value = "pic") MultipartFile picData, // inputFile,
			@RequestParam("patient_data") String patientData)
			throws JsonParseException, JsonMappingException, IOException {

		System.out.println("savePatientPic");

		// System.out.println("inputFile" + picData);

		ObjectMapper mapper = new ObjectMapper();
		SearchCriteria searchCriteria = mapper.readValue(patientData, SearchCriteria.class);

		ServiceResponse<ORMKeyValue> resp = patientServcie.savePatientPic(searchCriteria, picData);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/getPatientPhrUsersNames/{practiceId}/{patientId}")
	public @ResponseBody ResponseEntity<List<ORMGetPhrUsersNames>> getPatientPhrUsersNames(
			@PathVariable(value = "practiceId") String practiceId,
			@PathVariable(value = "patientId") String patientId) {
		GeneralOperations.logMsg("Inside getPatientPhrUsersNames: ");
		List<ORMGetPhrUsersNames> lst = patientServcie.getPatientPhrUsersNames(practiceId,patientId);

		return new ResponseEntity<List<ORMGetPhrUsersNames>>(lst, HttpStatus.OK);
	}

}