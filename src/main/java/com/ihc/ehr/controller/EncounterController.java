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

import com.ihc.ehr.model.*;
import com.ihc.ehr.service.EncounterService;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/encounter")
public class EncounterController {

	@Autowired
	EncounterService encounterdService;
	@Autowired
	GeneralService generalService;

	@RequestMapping("/getChartSummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetChartSummary>> getChartSummary(
			@PathVariable(value = "patient_id") String patient_id) {

		List<ORMGetChartSummary> obj = encounterdService.getChartSummary(patient_id);

		return new ResponseEntity<List<ORMGetChartSummary>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/getAllChartPrintModule/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMgetAllChartPrintModule>> getAllChartPrintModule(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMgetAllChartPrintModule> obj = encounterdService.getAllChartPrintModule(practice_id);

		return new ResponseEntity<List<ORMgetAllChartPrintModule>>(obj, HttpStatus.OK);

	}
	
	@RequestMapping("/getAppointmentDates/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetAppointmentDate>> getAppointmentDates(
			@PathVariable(value = "patient_id") String patient_id) {

		List<ORMGetAppointmentDate> obj = encounterdService.getAppointmentDates(patient_id);

		return new ResponseEntity<List<ORMGetAppointmentDate>>(obj, HttpStatus.OK);

	}

	// New chart
	@RequestMapping("/createNewChart")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> createNewChart(@RequestBody ORMPatientChart obj) {
				
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(encounterdService.createNewChart(obj), HttpStatus.OK);
		
	}

	@RequestMapping("/signEncounter")
	public long signEncounter(@RequestBody SearchCriteria searchCriteria) {
		return encounterdService.signEncounter(searchCriteria);
	}
	@RequestMapping("/resolveProblem")
	public long resolveProblem(@RequestBody SearchCriteria searchCriteria) {
		return encounterdService.resolveProblem(searchCriteria);
	}
	// RFV/HPI-start
	@RequestMapping("/getChartReasonForVisit_HPI/{chart_id}")
	public @ResponseBody ResponseEntity<ORMGetReasonForVisitHPI> getChartReasonForVisit_HPI(
			@PathVariable(value = "chart_id") Long chart_id) {
		ORMGetReasonForVisitHPI rfv = encounterdService.getChartReasonForVisit_HPI(chart_id);
		return new ResponseEntity<ORMGetReasonForVisitHPI>(rfv, HttpStatus.OK);
	}

	@RequestMapping("/saveupdateChartRFV_HPI")
	public ResponseEntity<ORMChartReasonForVisit_HPI> saveupdateChartRFV_HPI(
			@RequestBody ORMChartReasonForVisit_HPI obj) {
		encounterdService.saveupdateChartRFV_HPI(obj);
		return new ResponseEntity<ORMChartReasonForVisit_HPI>(obj, HttpStatus.OK);

	}

	// RFV/HPI-end
	// Vitals-start

	@RequestMapping("/getChartVital/{chart_id}")
	public @ResponseBody ResponseEntity<ORMChartVital> getChartVital(@PathVariable(value = "chart_id") Long chart_id) {
		ORMChartVital obj = encounterdService.getChartVital(chart_id);
		return new ResponseEntity<ORMChartVital>(obj, HttpStatus.OK);
	}

	//
	@RequestMapping("/getVitalGraphData/{patientID}")
	public @ResponseBody ResponseEntity<List<ORMGetVitalGraphData>> getVitalGraphData(
			@PathVariable(value = "patientID") Long patientID) {
		List<ORMGetVitalGraphData> lst = encounterdService.getVitalGraphData(patientID);
		return new ResponseEntity<List<ORMGetVitalGraphData>>(lst, HttpStatus.OK);
	}

	//
	@RequestMapping("/saveupdateChartVital")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveupdateChartVital(@RequestBody ORMChartVital obj) {
		ServiceResponse<ORMKeyValue> resp = encounterdService.saveupdateChartVital(obj);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/getCummulativeVitals/{patient_id}/{unit_type}")
	public @ResponseBody ResponseEntity<List<ORMGetCummulativeVitals>> getCummulativeVitals(
			@PathVariable(value = "patient_id") Long patientId, @PathVariable(value = "unit_type") String unitType) {
		List<ORMGetCummulativeVitals> lst = encounterdService.getCummulativeVitals(patientId, unitType);
		return new ResponseEntity<List<ORMGetCummulativeVitals>>(lst, HttpStatus.OK);
	}

	// Vital-end
	// officetest-start -- ORMChartOfficeTest
	// Vital-end
	// officetest-start -- ORMChartOfficeTest
	@RequestMapping("/getOfficeTest/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMChartOfficeTest>> getOfficeTest(
			@PathVariable(value = "chart_id") Long chart_id) {
		List<ORMChartOfficeTest> lst = encounterdService.getOfficeTest(chart_id);
		return new ResponseEntity<List<ORMChartOfficeTest>>(lst, HttpStatus.OK);
	}

	//
	// @RequestMapping("/saveOfficeTest")
	// public ResponseEntity<ORMChartOfficeTest> addUpdateTest(@RequestBody
	// ORMChartOfficeTest obj) {
	// encounterdService.addUpdateTest(obj);
	// return new ResponseEntity<ORMChartOfficeTest>(obj, HttpStatus.OK);
	//
	// }
	/*@RequestMapping("/saveOfficeTest")
	public ResponseEntity<ORMChartOfficeTest> saveOfficeTest(@RequestBody ORMChartOfficeTest obj) {
		encounterdService.saveOfficeTest(obj);
		return new ResponseEntity<ORMChartOfficeTest>(obj, HttpStatus.OK);
	}*/
	@RequestMapping("/saveOfficeTest")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveOfficeTest(
			@RequestBody WrapperOfficeTestSave WrapperOfficeTestSave) {
		ServiceResponse<ORMKeyValue> resp = encounterdService.saveOfficeTest(WrapperOfficeTestSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}
	/*
	 * @RequestMapping("/saveOfficeTest") public
	 * ResponseEntity<ServiceResponse<ORMKeyValue>> addUpdateTest(
	 * 
	 * @RequestBody ORMChartOfficeTest obj) { Long
	 * result=encounterdService.addUpdateTest(obj); ServiceResponse<ORMKeyValue>
	 * resp = new ServiceResponse<ORMKeyValue>(); if (result == 0) {
	 * resp.setStatus(ServiceResponseStatus.ERROR);
	 * resp.setResponse("An Error Occured while saving encounter SaveOffice Test.");
	 * } else { resp.setStatus(ServiceResponseStatus.SUCCESS);
	 * resp.setResult(Long.toString(result)); } return new
	 * ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK); }
	 */

	// officetest-end
	// Asthma Control Test - start [generic functions]
	// @RequestMapping("/getPatientHealthCheckSummary/{practice_id}/{patient_id}/{form_type}")
	// public @ResponseBody ResponseEntity<List<ORMGetPatientHealthCheckSummary>>
	// getPatientHealthCheckSummary(
	// @PathVariable(value = "practice_id") String practice_id,
	// @PathVariable(value = "patient_id") String patient_id,
	// @PathVariable(value = "form_type") String form_type) {
	// List<ORMGetPatientHealthCheckSummary> lst =
	// encounterdService.getPatientHealthCheckSummary(practice_id,patient_id,form_type);
	// return new ResponseEntity<List<ORMGetPatientHealthCheckSummary>>(lst,
	// HttpStatus.OK);
	// }
	//
	@RequestMapping("/getPatientHealthCheckForm")
	public @ResponseBody ResponseEntity<List<ORMGetPatientHealthCheckForm>> getPatientHealthCheckForm(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPatientHealthCheckForm> lst = encounterdService.getPatientHealthCheckForm(searchCriteria);
		return new ResponseEntity<List<ORMGetPatientHealthCheckForm>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getHealthCheckFormFromId")
	public @ResponseBody ResponseEntity<List<ORMGetPatientHealthCheckForm>> getHealthCheckFormFromId(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPatientHealthCheckForm> lst = encounterdService.getHealthCheckFormFromId(searchCriteria);
		return new ResponseEntity<List<ORMGetPatientHealthCheckForm>>(lst, HttpStatus.OK);
	}

	// Asthma Control Test - end
	// annotation-start
	@RequestMapping("/getChartAnnotation/{chartID}")
	public @ResponseBody ResponseEntity<List<ORMChartAnnotation>> getChartAnnotation(
			@PathVariable(value = "chartID") String chartID) {
		List<ORMChartAnnotation> lst = encounterdService.getChartAnnotation(chartID);
		return new ResponseEntity<List<ORMChartAnnotation>>(lst, HttpStatus.OK);
	}

	//
	@RequestMapping("/saveupdateChartAnnotation")
	public ResponseEntity<ORMChartAnnotation> saveupdateChartAnnotation(@RequestBody ORMChartAnnotation obj) {
		encounterdService.saveupdateChartAnnotation(obj);
		return new ResponseEntity<ORMChartAnnotation>(obj, HttpStatus.OK);

	}

	@RequestMapping("/deletePatientAnnotation")
	public int deletePatientInjury(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deletePatientAnnotation: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_annotation");
		ormDeleteRecord.setColumn_name("chart_annotation_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	// annotation-end
	// health maintenanceList-start
	@RequestMapping("getPatientHealthMaintenanceList/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientHealthMaintenanceList>> getPatientHealthMaintenanceList(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMGetPatientHealthMaintenanceList> lst = encounterdService.getPatientHealthMaintenanceList(patient_id);
		return new ResponseEntity<List<ORMGetPatientHealthMaintenanceList>>(lst, HttpStatus.OK);
	}

	//
	@RequestMapping("getPatientHealthMaintenance/{patient_id}/{phm_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientHealthMaintenance>> getPatientHealthMaintenance(
			@PathVariable(value = "patient_id") String patient_id, @PathVariable(value = "phm_id") String phm_id) {
		List<ORMGetPatientHealthMaintenance> lst = encounterdService.getPatientHealthMaintenance(patient_id, phm_id);
		return new ResponseEntity<List<ORMGetPatientHealthMaintenance>>(lst, HttpStatus.OK);
	}
	// health maintenanceList-end
	//

	// @RequestMapping("/getSexualOrientDDL")
	// public @ResponseBody ResponseEntity<List<ORMThreeColumGeneric>>
	// getSexualOrientDDL()
	// {
	// List<ORMThreeColumGeneric> lst=encounterdService.getSexualOrientDDL();
	// return new ResponseEntity<List<ORMThreeColumGeneric>>(lst , HttpStatus.OK);
	// }
	//
	// @RequestMapping("/getGenderIdentityDDL")
	// public @ResponseBody ResponseEntity<List<ORMThreeColumGeneric>>
	// getGenderIdentityDDL()
	// {
	// List<ORMThreeColumGeneric> lst=encounterdService.getGenderIdentityDDL();
	// return new ResponseEntity<List<ORMThreeColumGeneric>>(lst , HttpStatus.OK);
	// }
	//
	// Print
	@RequestMapping("getChartReportDetails/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMChartReportDetails>> getChartReportDetails(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMChartReportDetails> lst = encounterdService.getChartReportDetails(chart_id);
		return new ResponseEntity<List<ORMChartReportDetails>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getChartSurgery/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMChartProcedure>> getChartSurgery(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMChartProcedure> lst = encounterdService.getChartSurgery(chart_id);
		return new ResponseEntity<List<ORMChartProcedure>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getChartProcedures/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMChartProcedure>> getChartProcedures(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMChartProcedure> lst = encounterdService.getChartProcedures(chart_id);
		return new ResponseEntity<List<ORMChartProcedure>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getChartFamilyHis")
	public @ResponseBody ResponseEntity<List<ORMGetChartFamilyHis>> getChartFamilyHis(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetChartFamilyHis> lst = encounterdService.getChartFamilyHis(searchCriteria);
		return new ResponseEntity<List<ORMGetChartFamilyHis>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatPrescription")
	public @ResponseBody ResponseEntity<List<ORMGetChartPrescription>> getPatPrescription(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetChartPrescription> lst = encounterdService.getPatPrescription(searchCriteria);
		return new ResponseEntity<List<ORMGetChartPrescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatAllergies")
	public @ResponseBody ResponseEntity<List<ORMChartAllergies>> getPatAllergies(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMChartAllergies> lst = encounterdService.getPatAllergies(searchCriteria);
		return new ResponseEntity<List<ORMChartAllergies>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getPatROS/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientRos>> getPatROS(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMGetPatientRos> lst = encounterdService.getPatROS(chart_id);
		return new ResponseEntity<List<ORMGetPatientRos>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getChartROS/{chart_id}")
	public @ResponseBody ResponseEntity<ORMChartROS> getChartROS(@PathVariable(value = "chart_id") String chart_id) {
		ORMChartROS lst = encounterdService.getChartROS(chart_id);
		return new ResponseEntity<ORMChartROS>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveChartRos")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveChartRos(@RequestBody ORMChartROS obj) {
		Long result = encounterdService.saveChartRos(obj);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter ROS.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("getChartProgressNotes/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMChartProgressNotes>> getChartProgressNotes(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMChartProgressNotes> lst = encounterdService.getPatProgressNotes(chart_id);
		return new ResponseEntity<List<ORMChartProgressNotes>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getPatCarePlan/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMCarePlan>> getPatCarePlan(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMCarePlan> lst = encounterdService.getPatCarePlan(chart_id);
		return new ResponseEntity<List<ORMCarePlan>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getChartCognitiveFunctional/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMCognitiveFunctional>> getChartCognitiveFunctional(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMCognitiveFunctional> lst = encounterdService.getChartCognitiveFunctional(chart_id);
		return new ResponseEntity<List<ORMCognitiveFunctional>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getPatPhysicalExam/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMChartPhysicalExam>> getPatPhysicalExam(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMChartPhysicalExam> lst = encounterdService.getPatPhysicalExam(chart_id);
		return new ResponseEntity<List<ORMChartPhysicalExam>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getPatInjuryNotes/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMPatientInjuryTreatmentNotes>> getPatInjuryNotes(
			@PathVariable(value = "patient_id") String patient_id, @PathVariable(value = "chart_id") String chart_id) {
		List<ORMPatientInjuryTreatmentNotes> lst = encounterdService.getPatInjuryNotes(patient_id, chart_id);
		return new ResponseEntity<List<ORMPatientInjuryTreatmentNotes>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getChartFollowUpProblem/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMGetChartFollowUpPlan>> getChartFollowUpProblem(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMGetChartFollowUpPlan> lst = encounterdService.getChartFollowUpProblem(chart_id);
		return new ResponseEntity<List<ORMGetChartFollowUpPlan>>(lst, HttpStatus.OK);
	}

	//
	@RequestMapping("getNQFPlan")
	public @ResponseBody ResponseEntity<List<ORMThreeColum>> getNQFPlan() {
		List<ORMThreeColum> lst = encounterdService.getNQFPlan();
		return new ResponseEntity<List<ORMThreeColum>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getNQFPlanDetail")
	public @ResponseBody ResponseEntity<List<ORMNQFPlanDetail>> getNQFPlanDetail() {
		List<ORMNQFPlanDetail> lst = encounterdService.getNQFPlanDetail();
		return new ResponseEntity<List<ORMNQFPlanDetail>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/deleteProblem")
	public int deleteProblem(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("del prob: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_problem");
		ormDeleteRecord.setColumn_name("problem_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/deleteSurgery")
	public int deleteSurgery(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("del surgery: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_procedures");
		ormDeleteRecord.setColumn_name("chart_procedures_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	// save

	@RequestMapping("/saveProcedure")
	public ResponseEntity<ORMChartProcedure> saveProcedure(@RequestBody ORMChartProcedure obj) {
		encounterdService.saveProcedure(obj);
		return new ResponseEntity<ORMChartProcedure>(obj, HttpStatus.OK);

	}

	//////
	@RequestMapping("getPastMedHistory/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMPastMedicalHistory>> getPastMedHistory(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMPastMedicalHistory> lst = encounterdService.getPastMedHistory(chart_id);
		return new ResponseEntity<List<ORMPastMedicalHistory>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getAssessments/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMAssessment>> getAssessments(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMAssessment> lst = encounterdService.getAssessments(chart_id);
		return new ResponseEntity<List<ORMAssessment>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getPatientAWVPrint/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMPHRAuditLog>> getPatientAWVPrint(
			@PathVariable(value = "patient_id") String patient_id, @PathVariable(value = "chart_id") String chart_id) {
		List<ORMPHRAuditLog> lst = encounterdService.getPatientAWVPrint(patient_id, chart_id);
		return new ResponseEntity<List<ORMPHRAuditLog>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getHealthMaintPrint/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMHealthMentPrint>> getHealthMaintPrint(
			@PathVariable(value = "patient_id") String patient_id, @PathVariable(value = "chart_id") String chart_id) {
		List<ORMHealthMentPrint> lst = encounterdService.getHealthMaintPrint(patient_id, chart_id);
		return new ResponseEntity<List<ORMHealthMentPrint>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getLabOrderTestPrint/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> getLabOrderTestPrint(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMTwoColumnGeneric> lst = encounterdService.getLabOrderTestPrint(chart_id);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getAmendmentsPrint/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMFourColumGeneric>> getAmendmentsPrint(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMFourColumGeneric> lst = encounterdService.getAmendmentsPrint(chart_id);
		return new ResponseEntity<List<ORMFourColumGeneric>>(lst, HttpStatus.OK);
	}

	// cognative history-start
	@RequestMapping("getCognitiveValues/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMThreeColumGeneric>> getCognitiveValues(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMThreeColumGeneric> lst = encounterdService.getCognitiveValues(chart_id);
		return new ResponseEntity<List<ORMThreeColumGeneric>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/deleteCognitivefunct")
	public int deleteCognitivefunct(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("del cognitive: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_cognitive_functional");
		ormDeleteRecord.setColumn_name("cognitive_functional_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	//
	@RequestMapping("/CognitiveAddUpdate")
	public ResponseEntity<ORMCognitive_Functional> CognitiveAddUpdate(@RequestBody ORMCognitive_Functional obj) {
		encounterdService.CognitiveAddUpdate(obj);
		return new ResponseEntity<ORMCognitive_Functional>(obj, HttpStatus.OK);

	}

	// cognative history-end
	// physicians-care start
	@RequestMapping("GetPhyCare/{practice_id}/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMPhysicians_Care>> GetPhyCare(
			@PathVariable(value = "practice_id") String practice_id,
			@PathVariable(value = "patient_id") String patient_id, @PathVariable(value = "chart_id") String chart_id) {
		List<ORMPhysicians_Care> lst = encounterdService.GetPhyCare(practice_id, patient_id, chart_id);
		return new ResponseEntity<List<ORMPhysicians_Care>>(lst, HttpStatus.OK);
	}

	//
	@RequestMapping("/saveEditPhyCare")
	public ResponseEntity<ORMPhysicians_Care> saveEditPhyCare(@RequestBody ORMPhysicians_Care obj) {
		encounterdService.saveEditPhyCare(obj);
		return new ResponseEntity<ORMPhysicians_Care>(obj, HttpStatus.OK);

	}

	@RequestMapping("/deletePhysCare")
	public int deletePhysCare(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("delete provider care: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_physicians_care");
		ormDeleteRecord.setColumn_name("physicians_care_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	// physicians-care end
	// Chart Module History--start
	@RequestMapping("/LoadChartModuleHistory/{practice_id}/{moduleName}")
	public @ResponseBody ResponseEntity<List<?>> LoadChartModuleHistory(
			@PathVariable(value = "practice_id") Long practice_id,
			@PathVariable(value = "moduleName") String moduleName, 
			@RequestBody SearchCriteria searchCriteria) {
		List<?> obj = encounterdService.LoadChartModuleHistory(practice_id, moduleName, searchCriteria);
		return new ResponseEntity<List<?>>(obj, HttpStatus.OK);
	}

	@RequestMapping("LoadChartModuleHistoryHeader/{moduleName}")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> LoadChartModuleHistoryHeader(
			@PathVariable(value = "moduleName") String moduleName) {
		List<ORMTwoColumnGeneric> lst = encounterdService.LoadChartModuleHistoryHeader(moduleName);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatientVisitSnapShot")
	public @ResponseBody ResponseEntity<List<ORMChartModuleSummary>> getPatientVisitSnapShot(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMChartModuleSummary> lst = encounterdService.getPatientVisitSnapShot(searchCriteria);
		return new ResponseEntity<List<ORMChartModuleSummary>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getHealthCheckFormsList")
	public @ResponseBody ResponseEntity<List<ORMGetHealthCheckForms>> getHealthCheckFormsList(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetHealthCheckForms> lst = encounterdService.getHealthCheckFormsList(searchCriteria);
		return new ResponseEntity<List<ORMGetHealthCheckForms>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatientHealthCheckSummary")
	public @ResponseBody ResponseEntity<List<ORMGetPatientHealthCheckSummary>> getPatientHealthCheckSummary(
			@RequestBody SearchCriteria searchCriteria) {
		String patient_id = "";
		String form_type = "";
		for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

			if (pram.getName().equals("patient_id"))
				patient_id = pram.getValue();
			else if (pram.getName().equals("form_type"))
				form_type = pram.getValue();
		}

		List<ORMGetPatientHealthCheckSummary> lst = encounterdService
				.getPatientHealthCheckSummary(searchCriteria.getPractice_id().toString(), patient_id, form_type);
		return new ResponseEntity<List<ORMGetPatientHealthCheckSummary>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getFutureAppointments/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMGetFutureAppointments>> getFutureAppointments(
			@PathVariable(value = "patient_id") String patient_id, @PathVariable(value = "chart_id") String chart_id) {

		List<ORMGetFutureAppointments> lst = encounterdService.getFutureAppointments(patient_id, chart_id);
		return new ResponseEntity<List<ORMGetFutureAppointments>>(lst, HttpStatus.OK);
	}
	// @RequestMapping("/savPatientHealthCheck")
	// public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>>
	// savPatientHealthCheck(
	// @RequestBody SaveObjectWrapper<ORMPatientHealthCheck> objHealthCheckData)
	// {
	// GeneralOperations.logMsg("Inside SavePatientHealthExamToFile");
	//
	//
	// //ORMSaveAppointment obj=objAppSaveWrapper.getOrmSave();
	//
	// //ServiceResponse
	// resp=schedulerService.saveAppointments(obj,objHealthCheckData.getKeyVlaueList());
	// ServiceResponse resp=new ServiceResponse();
	//
	// return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	//
	// }

	@RequestMapping("/SavePatientHealthExamToFile")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> SavePatientHealthExamToFile(
			@RequestBody Wrapper_ObjectSave<ORMPatientHealthCheck> objHealthCheckData) {

		List<ORMKeyValue> objOtherData = objHealthCheckData.getLstKeyValue();
		String docPath = "";
		String HTML = "";
		for (ORMKeyValue item : objOtherData) {

			if (item.getKey().equalsIgnoreCase("HTML")) {
				HTML = item.getValue();
			}
			if (item.getKey().equalsIgnoreCase("docCategory")) {
				docPath = item.getValue();
			}
		}

		// ObjectMapper mapper = new ObjectMapper();
//		System.out.println("Document Category:" + docPath);
//		System.out.println("HTML:" + HTML);

		ORMPatientHealthCheck objORMHealthCheck = objHealthCheckData.getOrmSave();
		// ORMPatientHealthCheck objORMHealthCheck = mapper.readValue(health_data,
		// ORMPatientHealthCheck.class);

		Long result = encounterdService.SavePatientHealthExamToFile(objORMHealthCheck, HTML, docPath);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(result.toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/SignHealthCheckForm")
	public long SignHealthCheckForm(@RequestBody SearchCriteria searchCriteria) {
		return encounterdService.SignHealthCheckForm(searchCriteria);

	}

	@RequestMapping("/DeletePatientHealthCheck")
	public int DeletePatientHealthCheck(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("patient_health_check");
		ormDeleteRecord.setColumn_name("health_check_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/DeleteChart")
	public @ResponseBody int DeleteChart(@RequestBody SearchCriteria searchCriteria) {
		// ClinicalDecisionSupportOperation objCDS=new
		// ClinicalDecisionSupportOperation();
		// objCDS.RemovePatientFreezedAlerts("is_diagnosis",patient_id);
		// objCDS.RemovePatientFreezedAlerts("is_vitals",patient_id);
		// objCDS.RemovePatientFreezedAlerts("is_allergy",patient_id);
		// objCDS.RemovePatientFreezedAlerts("is_medication",patient_id);
		// objCDS.RemovePatientFreezedAlerts("is_procedures",patient_id);
		// objCDS.RemovePatientFreezedAlerts("is_lifestyle",patient_id);
		// objCDS.RemovePatientFreezedAlerts("is_healthmaintenance",patient_id);
		// objCDS.RemovePatientFreezedAlerts("is_immunization",patient_id);
		//
		String chart_id = "";
		// String patient_id="";
		String user = "";
		String date = "";
		for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

			if (pram.getName().equals("chart_id"))
				chart_id = pram.getValue();
			// else if(pram.getName().equals("patient_id"))
			// patient_id=pram.getValue();
			else if (pram.getName().equals("loginUser"))
				user = pram.getValue();
			else if (pram.getName().equals("Datetime"))
				date = pram.getValue();
		}
		ORMDeleteRecord obj = new ORMDeleteRecord();
		obj.setTable_name("patient_chart");
		obj.setColumn_name("chart_id");
		obj.setColumn_id(chart_id);
		obj.setModified_user(user);
		obj.setClient_date_time(date);
		obj.setClient_ip("");
		if (generalService.deleteRecord(obj) > 0) {
			// chart_reasonforvisit_hpi
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_reasonforvisit_hpi");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_vital
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_vital");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_problem
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_problem");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_surgery
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_surgery");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_immunization
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_immunization");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_socialhistory
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_socialhistory");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_familyhistory
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_familyhistory");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_ros
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_ros");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_planofcare_note
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_planofcare_note");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_annotation
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_annotation");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// patient_officetest
			obj = new ORMDeleteRecord();
			obj.setTable_name("patient_officetest");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_procedures
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_procedures");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// patient_health_check
			obj = new ORMDeleteRecord();
			obj.setTable_name("patient_health_check");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_cognitive_functional
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_cognitive_functional");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// chart_care_plan
			obj = new ORMDeleteRecord();
			obj.setTable_name("chart_care_plan");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

			// patient_injury_treatment_notes
			obj = new ORMDeleteRecord();
			obj.setTable_name("patient_injury_treatment_notes");
			obj.setColumn_name("chart_id");
			obj.setColumn_id(chart_id);
			obj.setModified_user(user);
			obj.setClient_date_time(date);
			obj.setClient_ip("");
			generalService.deleteRecord(obj);

		}

		return 1;
	}

	// PROBLEM LIST
	@RequestMapping("getChartProblem")
	public @ResponseBody ResponseEntity<List<ORMGetEncounterProblemsSummary>> getChartProblem(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetEncounterProblemsSummary> lst = encounterdService.getChartProblem(searchCriteria);
		return new ResponseEntity<List<ORMGetEncounterProblemsSummary>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getchartproblemdetail/{problem_id}")
	public @ResponseBody ResponseEntity<ORMGetEncounterProblemDetail> getChartProblemDetail(
			@PathVariable(value = "problem_id") Long problem_id) {
		ORMGetEncounterProblemDetail record = encounterdService.getChartProblemDetail(problem_id);
		return new ResponseEntity<ORMGetEncounterProblemDetail>(record, HttpStatus.OK);
	}

	@RequestMapping("/saveproblem")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveProblem(@RequestBody ORMSaveEncounterProblem obj) {

		Long result = encounterdService.saveProblem(obj);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter problem.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}
	// END PROBLEM LIST

	@RequestMapping("/getChartAssessmentView/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMChartAssessmentView>> getChartAssessmentView(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMChartAssessmentView> obj = encounterdService.getChartAssessmentView(chart_id);
		return new ResponseEntity<List<ORMChartAssessmentView>>(obj, HttpStatus.OK);
	}

	@RequestMapping("getChartAssessmentDetail/{id}")
	public @ResponseBody ResponseEntity<ORMChartAssessmentDetail> getChartAssessmentDetail(
			@PathVariable(value = "id") String id) {
		ORMChartAssessmentDetail record = encounterdService.getChartAssessmentDetail(id);
		return new ResponseEntity<ORMChartAssessmentDetail>(record, HttpStatus.OK);
	}

	@RequestMapping("/savePatientAssessments")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> savePatientAssessments(@RequestBody ORMSaveAssessments obj) {

		Long result = encounterdService.savePatientAssessments(obj);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter Assessments.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/deleteAssessment")
	public int deleteAssessment(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("del prob: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_assessment");
		ormDeleteRecord.setColumn_name("id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/getChartPMHView/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMGetChartPMHView>> getChartPMHView(
			@PathVariable(value = "patient_id") String patient_id, @PathVariable(value = "chart_id") String chart_id) {
		List<ORMGetChartPMHView> obj = encounterdService.getChartPMHView(patient_id, chart_id);
		return new ResponseEntity<List<ORMGetChartPMHView>>(obj, HttpStatus.OK);
	}

	@RequestMapping("getChartPMHDetail/{id}")
	public @ResponseBody ResponseEntity<ORMChartPMH> getChartPMHDetail(@PathVariable(value = "id") String id) {
		ORMChartPMH record = encounterdService.getChartPMHDetail(id);
		return new ResponseEntity<ORMChartPMH>(record, HttpStatus.OK);
	}

	@RequestMapping("/savePMH")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> savePMH(@RequestBody ORMChartPMH obj) {

		Long result = encounterdService.savePMH(obj);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter Assessments.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/deletePMH")
	public int deletePMH(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("del prob: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_past_medical_history");
		ormDeleteRecord.setColumn_name("id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/getChartPrescriptionView/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMChartPrescriptionView>> getChartPrescriptionView(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMChartPrescriptionView> obj = encounterdService.getChartPrescriptionView(patient_id);
		return new ResponseEntity<List<ORMChartPrescriptionView>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getChartAllergyView/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMChartAllergyView>> getChartAllergyView(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMChartAllergyView> obj = encounterdService.getChartAllergyView(patient_id);
		return new ResponseEntity<List<ORMChartAllergyView>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getChartProgressNoteListView/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMChartProgressNotesListView>> getChartProgressNoteListView(
			@PathVariable(value = "patient_id") String patient_id, @PathVariable(value = "chart_id") String chart_id) {
		List<ORMChartProgressNotesListView> obj = encounterdService.getChartProgressNoteListView(patient_id, chart_id);
		return new ResponseEntity<List<ORMChartProgressNotesListView>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getChartProgressNoteTextView/{note_id}")
	public @ResponseBody ResponseEntity<List<ORMChartProgressNotesTextView>> getChartProgressNoteTextView(
			@PathVariable(value = "note_id") String note_id) {
		List<ORMChartProgressNotesTextView> obj = encounterdService.getChartProgressNoteTextView(note_id);
		return new ResponseEntity<List<ORMChartProgressNotesTextView>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/saveChartProgressNotes")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveChartProgressNotes(@RequestBody ORMSavePlanOfCare obj) {
		Long result = encounterdService.saveChartProgressNotes(obj);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter Progress Notes.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	/*@RequestMapping("/deleteProgressNotes")
	public int deleteProgressNotes(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("del prob: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_progress_note");
		ormDeleteRecord.setColumn_name("note_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}*/ 
	//change delete for trigner
	@RequestMapping("/deleteProgressNotes")
	public long deleteProgressNotes(@RequestBody SearchCriteria searchCriteria) {
		return encounterdService.deleteProgressNotes(searchCriteria);
	}

	@RequestMapping("/getChartSurgeryView/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMSurgeryView>> getChartSurgeryView(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMSurgeryView> obj = encounterdService.getChartSurgeryView(chart_id);
		return new ResponseEntity<List<ORMSurgeryView>>(obj, HttpStatus.OK);
	} 
	@RequestMapping("/getChartProcedureView/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMSurgeryView>> getChartProcedureView(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMSurgeryView> obj = encounterdService.getChartProcedureView(chart_id);
		return new ResponseEntity<List<ORMSurgeryView>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/saveChartProcedure")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveChartProcedure(
			@RequestBody List<ORMChartSurgery> lstSave) {		
		ServiceResponse<ORMKeyValue> resp = encounterdService.saveChartProcedure(lstSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("getChartSurgeryDetail/{id}")
	public @ResponseBody ResponseEntity<ORMChartSurgery> getChartSurgeryDetail(@PathVariable(value = "id") String id) {
		ORMChartSurgery lst = encounterdService.getChartSurgeryDetail(id);
		return new ResponseEntity<ORMChartSurgery>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveChartSurgery")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveChartSurgery(@RequestBody ORMChartSurgery obj) {
		Long result = encounterdService.saveChartSurgery(obj);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter Assessments.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getChartFamilyHxView/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMFamilyHxView>> getChartFamilyHxView(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMFamilyHxView> obj = encounterdService.getChartFamilyHxView(chart_id);
		return new ResponseEntity<List<ORMFamilyHxView>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getChartFamilyHxDetail/{id}")
	public @ResponseBody ResponseEntity<ORMFamilyHx> getChartFamilyHxDetail(@PathVariable(value = "id") String id) {
		ORMFamilyHx obj = encounterdService.getChartFamilyHxDetail(id);
		return new ResponseEntity<ORMFamilyHx>(obj, HttpStatus.OK);
	}

	@RequestMapping("/saveFamilyHx")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveFamilyHx(@RequestBody ORMFamilyHx obj) {
		Long result = encounterdService.saveFamilyHx(obj);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter familyHX.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deleteFamilyHx")
	public int deleteFamilyHx(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("del prob: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_familyhistory");
		ormDeleteRecord.setColumn_name("familyhistory_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("getPatPhysicalExamView/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientPhysicalExamView>> getPatPhysicalExamView(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMGetPatientPhysicalExamView> lst = encounterdService.getPatPhysicalExamView(chart_id);
		return new ResponseEntity<List<ORMGetPatientPhysicalExamView>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getPatPhysicalExamDetail/{chart_id}")
	public @ResponseBody ResponseEntity<ORMChartPhysicalExam> getPatPhysicalExamDetail(
			@PathVariable(value = "chart_id") String chart_id) {
		ORMChartPhysicalExam lst = encounterdService.getPatPhysicalExamDetail(chart_id);
		return new ResponseEntity<ORMChartPhysicalExam>(lst, HttpStatus.OK);
	}

	@RequestMapping("/savePatientPhysicalExam")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> savePatientPhysicalExam(@RequestBody ORMChartPhysicalExam obj) {
		Long result = encounterdService.savePatientPhysicalExam(obj);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter Assessments.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deletePhysicalExam")
	public int deletePhysicalExam(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("chart_physical_exam");
		ormDeleteRecord.setColumn_name("patient_organ_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("getChartHealthMainList/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetHealthMaintList>> getChartHealthMainList(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMGetHealthMaintList> lst = encounterdService.getChartHealthMainList(patient_id);
		return new ResponseEntity<List<ORMGetHealthMaintList>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getChartHealthMainDetail_View/{patient_id}/{maint_id}")
	public @ResponseBody ResponseEntity<List<ORMGetHealthMaintDetail_View>> getChartHealthMainDetail_View(
			@PathVariable(value = "patient_id") String patient_id, @PathVariable(value = "maint_id") String maint_id) {
		List<ORMGetHealthMaintDetail_View> lst = encounterdService.getChartHealthMainDetail_View(patient_id, maint_id);
		return new ResponseEntity<List<ORMGetHealthMaintDetail_View>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveHealthMaint")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveHealthMaint(@RequestBody ORM_HealthMaintenance objMain) {
		Long result = encounterdService.saveHealthMaint(objMain);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter Health Maint Main.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/saveHealthMaintDetail")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveHealthMaintDetail(
			@RequestBody List<ORM_HealthMaintenanceDetail> objDetail) {
		Long result = encounterdService.saveHealthMaintDetail(objDetail);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter Health Maint detail.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deleteHealthMaint")
	public int deleteHealthMaint(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteHealthMaint: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("patient_health_maintenance");
		ormDeleteRecord.setColumn_name("phm_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/deleteHealthMaintDetail")
	public int deleteHealthMaintDetail(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteHealthMaintDetail: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("patient_health_maintenance_detail");
		ormDeleteRecord.setColumn_name("detail_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/getPrescriptionXml")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> getPrescriptionXml(
			@RequestBody ORMPrescriptionXml prescriptionXml) {
		ServiceResponse<ORMKeyValue> lst = encounterdService.getPrescriptionXml(prescriptionXml);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/GetPrescriptionAllergies")
	public @ResponseBody ResponseEntity<List<ORMChartAllergyView>> GetPrescriptionAllergies(
			@RequestBody ORMgetPrescriptionAllergies getPresAllerg) {
		List<ORMChartAllergyView> lst = encounterdService.GetPrescriptionAllergies(getPresAllerg);
		return new ResponseEntity<List<ORMChartAllergyView>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatPrescriptionServer")
	public @ResponseBody ResponseEntity<List<ORMChartPrescriptionView>> getPatPrescriptionServer(
			@RequestBody ORMgetPrescriptionAllergies getPresAllerg) {
		List<ORMChartPrescriptionView> lst = encounterdService.getPatPrescriptionServer(getPresAllerg);
		return new ResponseEntity<List<ORMChartPrescriptionView>>(lst, HttpStatus.OK);
	}

	/*
	 * @RequestMapping("/getDeviceDetail/{UDI}") public @ResponseBody
	 * ResponseEntity<List<ORMGetImplantableDevicesByApi>> getDeviceDetail(
	 * 
	 * @PathVariable(value="UDI") String UDI){ List<ORMGetImplantableDevicesByApi>
	 * obj=encounterdService.getDeviceDetail(UDI); return new
	 * ResponseEntity<List<ORMGetImplantableDevicesByApi>>(obj , HttpStatus.OK); }
	 */
	/*
	 * @RequestMapping("/getDeviceDetail/{UDI}") public @ResponseBody
	 * ResponseEntity<Object> getDeviceDetail(
	 * 
	 * @PathVariable(value="UDI") String UDI){ Object
	 * obj=encounterdService.getDeviceDetail(UDI); return new
	 * ResponseEntity<Object>(obj , HttpStatus.OK); //return new Object(); }
	 */

	@RequestMapping("getChartAmendmentsView/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMChartAmendments_View>> getChartAmendmentsView(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMChartAmendments_View> lst = encounterdService.getChartAmendmentsView(chart_id);
		return new ResponseEntity<List<ORMChartAmendments_View>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getChartAmendmentsDetail/{id}")
	public @ResponseBody ResponseEntity<ORMChartAmendmentsSave> getChartAmendmentsDetail(
			@PathVariable(value = "id") String id) {
		ORMChartAmendmentsSave lst = encounterdService.getChartAmendmentsDetail(id);
		return new ResponseEntity<ORMChartAmendmentsSave>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveChartAmendments")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveChartAmendments(@RequestBody ORMChartAmendmentsSave obj) {
		Long result = encounterdService.saveChartAmendments(obj);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter saveChartAmendments.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deleteAmendments")
	public int deleteAmendments(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("chart_amendments");
		ormDeleteRecord.setColumn_name("amendment_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	// IMMUNIZATION
	@RequestMapping("getChartImmunizationSummary")
	public @ResponseBody ResponseEntity<List<ORMChartImmunizationSummaryGet>> getChartImmunizationSummary(
			@RequestBody SearchCriteria searchCriteria) {

		List<ORMChartImmunizationSummaryGet> lst = encounterdService.getChartImmunizationSummary(searchCriteria);
		return new ResponseEntity<List<ORMChartImmunizationSummaryGet>>(lst, HttpStatus.OK);

	}

	@RequestMapping("/getChartImmunizationVIS/{chart_immunization_id}")
	public @ResponseBody ResponseEntity<List<ORMChartImunizationVISGet>> getChartImmunizationVIS(
			@PathVariable(value = "chart_immunization_id") Long chart_immunization_id) {
		List<ORMChartImunizationVISGet> lst = encounterdService.getChartImmunizationVIS(chart_immunization_id);
		return new ResponseEntity<List<ORMChartImunizationVISGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getChartImmunizationById/{chart_immunization_id}")
	public @ResponseBody ResponseEntity<ORMChartImmunizationGet> getChartImmunizationById(
			@PathVariable(value = "chart_immunization_id") Long chart_immunization_id) {
		ORMChartImmunizationGet obj = encounterdService.getChartImmunizationById(chart_immunization_id);

		return new ResponseEntity<ORMChartImmunizationGet>(obj, HttpStatus.OK);
	}

	@RequestMapping("/saveChartImmunization")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveChartImmunization(
			@RequestBody Wrapper_ChartImmunizationSave wrapperChartImmunizationSave) {

		ServiceResponse<ORMKeyValue> resp = encounterdService.saveChartImmunization(wrapperChartImmunizationSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deleteChartImmunization")
	public int deleteChartImmunization(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteChartImmunization: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_immunization");
		ormDeleteRecord.setColumn_name("chart_immunization_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	// END IMMUNIZATION

	@RequestMapping("/getGrowthChartData/{practice_id}/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetGrowthChartInfo>> getGrowthChartData(
			@PathVariable(value = "practice_id") String practice_id,
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMGetGrowthChartInfo> obj = encounterdService.getGrowthChartData(practice_id, patient_id);
		return new ResponseEntity<List<ORMGetGrowthChartInfo>>(obj, HttpStatus.OK);
	}

	@RequestMapping("getChartSocialHistDisplay/{chart_id}")
	public @ResponseBody ResponseEntity<ORMChartSocialHisDisplayGet> getChartSocialHistDisplay(
			@PathVariable(value = "chart_id") Long chart_id) {
		ORMChartSocialHisDisplayGet obj = encounterdService.getChartSocialHistDisplay(chart_id);
		return new ResponseEntity<ORMChartSocialHisDisplayGet>(obj, HttpStatus.OK);
	}
	@RequestMapping("getChartSocialHistory/{chart_id}")
	public @ResponseBody  ResponseEntity<List<ORMChartSocialHistoryGet>> getChartSocialHistory(
			@PathVariable(value = "chart_id") Long chart_id) {
		
		List<ORMChartSocialHistoryGet> obj = encounterdService.getChartSocialHistory(chart_id);
		return new ResponseEntity<List<ORMChartSocialHistoryGet>>(obj, HttpStatus.OK);
		
		 
	}
	

	@RequestMapping("getChartSocialHistDetailById/{socialhistory_id}")
	public @ResponseBody ResponseEntity<ORMChartSocialHistoryGet> getChartSocialHistDetailById(
			@PathVariable(value = "socialhistory_id") Long SocialhistoryId) {
		ORMChartSocialHistoryGet obj = encounterdService.getChartSocialHistDetailById(SocialhistoryId);
		return new ResponseEntity<ORMChartSocialHistoryGet>(obj, HttpStatus.OK);
	}

	@RequestMapping("/saveSocialHistory")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveSocialHistory(@RequestBody ORMChartSocialHistorySave obj) {
		ServiceResponse<ORMKeyValue> resp = encounterdService.saveSocialHistory(obj);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	// ***** IMPLANTABLE DEVICE **** //
	@RequestMapping("/getDeviceDetailFromGlobalUDIDB")
	public @ResponseBody ResponseEntity<Object> getDeviceDetailFromGlobalUDIDB(
			@RequestBody SearchCriteria searchCriteria) {
		Object lst = encounterdService.getDeviceDetailFromGlobalUDIDB(searchCriteria);
		return new ResponseEntity<Object>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatImplantableDevicesSummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMPatientImplantableDeviceGetSummary>> getPatImplantableDevicesSummary(
			@PathVariable(value = "patient_id") Long patient_id) {
		List<ORMPatientImplantableDeviceGetSummary> lst = encounterdService.getPatImplantableDevicesSummary(patient_id);
		return new ResponseEntity<List<ORMPatientImplantableDeviceGetSummary>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatImplantableDeviceDetailById/{implantable_device_id}")
	public @ResponseBody ResponseEntity<ORMPatientImplantableDeviceGetDetail> getPatImplantableDeviceDetailById(
			@PathVariable(value = "implantable_device_id") Long implantable_device_id) {
		ORMPatientImplantableDeviceGetDetail obj = encounterdService
				.getImplantableDevicesDetailById(implantable_device_id);
		return new ResponseEntity<ORMPatientImplantableDeviceGetDetail>(obj, HttpStatus.OK);
	}

	@RequestMapping("/savePatImplantDevice")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> savePatImplantDevice(
			@RequestBody ORMPatientImplantableDeviceSave obj) {

		ServiceResponse<ORMKeyValue> resp = encounterdService.savePatImplantDevice(obj);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deletePatImplantableDevice")
	public int deleteImplantableDevice(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("del Impl Device: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("patient_implantable_device");
		ormDeleteRecord.setColumn_name("implantable_device_id");
		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/saveCarePlan")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveCarePlan(@RequestBody ORMCarePlan obj) {

		Long result = encounterdService.saveCarePlan(obj);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter saveCarePlan.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/deleteCarePlan")
	public int deleteCarePlan(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("Delete Chart CarePLan: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_care_plan");
		ormDeleteRecord.setColumn_name("chart_careplanid");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/getPatientDischargeDispositionSummary/{chart_id}")
	public @ResponseBody ResponseEntity<ORMPatientDischargeGetSummary> getPatientDischargeDispositionSummary(
			@PathVariable(value = "chart_id") Long chartId) {
		ORMPatientDischargeGetSummary obj = encounterdService.getPatientDischargeDispositionSummary(chartId);
		return new ResponseEntity<ORMPatientDischargeGetSummary>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getPatientDischargeDispositionDetail/{discharge_id}")
	public @ResponseBody ResponseEntity<ORMPatientDischargeDisposition> getPatientDischargeDispositionDetail(
			@PathVariable(value = "discharge_id") Long dischargeId) {
		ORMPatientDischargeDisposition obj = encounterdService.getPatientDischargeDispositionDetail(dischargeId);
		return new ResponseEntity<ORMPatientDischargeDisposition>(obj, HttpStatus.OK);
	}

	@RequestMapping("/savePatientDischargeDisposition")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> savePatientDischargeDisposition(
			@RequestBody ORMPatientDischargeDisposition obj) {

		ServiceResponse<ORMKeyValue> resp = encounterdService.savePatientDischargeDisposition(obj);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deleteDischargeDisposition")
	public int deleteDischargeDisposition(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteDischargeDisposition: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("patient_discharge_disposition");
		ormDeleteRecord.setColumn_name("discharge_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/getChartHealthConcernView/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMHealthConcernView>> getChartHealthConcernView(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMHealthConcernView> obj = encounterdService.getChartHealthConcernView(chart_id);
		return new ResponseEntity<List<ORMHealthConcernView>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getChartHealthConcernViewDetail/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMHealthConcernViewDetail>> getChartHealthConcernViewDetail(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMHealthConcernViewDetail> obj = encounterdService.getChartHealthConcernViewDetail(chart_id);
		return new ResponseEntity<List<ORMHealthConcernViewDetail>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/SaveHealthConcern")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> SaveHealthConcern(
			@RequestBody HealthConcernSaveWrapper wrapper) {
		GeneralOperations.logMsg("Inside SaveHealthConcern: ");

		ServiceResponse<ORMKeyValue> resp = encounterdService.SaveHealthConcern(wrapper);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/deleteHealthConcern")
	public int deleteHealthConcern(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("health_concern");
		ormDeleteRecord.setColumn_name("health_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/deleteHealthConcernDetail")
	public int deleteHealthConcernDetail(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("health_concern_details");
		ormDeleteRecord.setColumn_name("id");

		return generalService.deleteRecord(ormDeleteRecord);
	}

	// ASSESSMENT & PLAN OF TREATMENT
	@RequestMapping("/getPatientAssessmentPlan/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMAssessPlanGet>> getPatientAssessmentPlan(
			@PathVariable(value = "patient_id") Long patient_id) {

		List<ORMAssessPlanGet> obj = encounterdService.getPatientAssessmentPlan(patient_id);

		return new ResponseEntity<List<ORMAssessPlanGet>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getAssessPlanAssessment/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMAssessPlanAssessmentGet>> getAssessPlanAssessment(
			@PathVariable(value = "patient_id") Long patient_id) {

		List<ORMAssessPlanAssessmentGet> obj = encounterdService.getAssessPlanAssessment(patient_id);

		return new ResponseEntity<List<ORMAssessPlanAssessmentGet>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getAssessPlanPOT/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMAssessPlanOfTreatementGet>> getAssessPlanPOT(
			@PathVariable(value = "patient_id") Long patient_id) {

		List<ORMAssessPlanOfTreatementGet> obj = encounterdService.getAssessPlanPOT(patient_id);

		return new ResponseEntity<List<ORMAssessPlanOfTreatementGet>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/saveAssessPlan")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveAssessPlan(
			@RequestBody WrapperAssessPlanSave wrapper) {
		GeneralOperations.logMsg("Inside saveAssessPlan: ");

		ServiceResponse<ORMKeyValue> resp = encounterdService.saveAssessPlan(wrapper);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/deleteAssessPlan")
	public int deleteAssessPlan(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("assess_plan");
		ormDeleteRecord.setColumn_name("assess_plan_id");
		int result1 = generalService.deleteRecord(ormDeleteRecord);

		ormDeleteRecord.setTable_name("assess_plan_assessment");
		ormDeleteRecord.setColumn_name("assess_plan_id");
		int result2 = generalService.deleteRecord(ormDeleteRecord);

		ormDeleteRecord.setTable_name("assess_plan_planoftreatment");
		ormDeleteRecord.setColumn_name("assess_plan_id");
		int result3 = generalService.deleteRecord(ormDeleteRecord);

		return (result1 + result2 + result3);

	}
	
	@RequestMapping("/getTemlplateText/{practice_id}/{provider_id}/{type}")
	public @ResponseBody ResponseEntity<List<ORMGetTemplateText>> getTemlplateText(
			@PathVariable(value = "practice_id") String practice_id,@PathVariable(value = "provider_id") String provider_id
			,@PathVariable(value = "type") String type) {

		List<ORMGetTemplateText> obj = encounterdService.getTemlplateText(practice_id,provider_id,type);

		return new ResponseEntity<List<ORMGetTemplateText>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/getCurrentEncounterTemplate/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMGetCurrentEncounterTemplate>> getCurrentEncounterTemplate(
			@PathVariable(value = "chart_id") String chart_id) {

		List<ORMGetCurrentEncounterTemplate> obj = encounterdService.getCurrentEncounterTemplate(chart_id);

		return new ResponseEntity<List<ORMGetCurrentEncounterTemplate>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/ApplyChartTemplate")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> ApplyChartTemplate(
			@RequestBody ORMChartTemplateApply obj) {

		ServiceResponse<ORMKeyValue> resp = encounterdService.ApplyChartTemplate(obj);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}
	@RequestMapping("/getTemplateEncSummary/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMGetTemplateEncounterSummary>> getTemplateEncSummary(
			@PathVariable(value = "patient_id") String patient_id,@PathVariable(value = "chart_id") String chart_id) {

		List<ORMGetTemplateEncounterSummary> obj = encounterdService.getTemplateEncSummary(patient_id,chart_id);

		return new ResponseEntity<List<ORMGetTemplateEncounterSummary>>(obj, HttpStatus.OK);

	}
	
	@RequestMapping("/getPatientVisit_CCD/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientVisit_CCD>> getPatientVisit_CCD(
			@PathVariable(value = "patient_id") String patient_id) {

		List<ORMGetPatientVisit_CCD> obj = encounterdService.getPatientVisit_CCD(patient_id);

		return new ResponseEntity<List<ORMGetPatientVisit_CCD>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/getCCDSetting/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMCCDSetting>> getCCDSetting(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMCCDSetting> obj = encounterdService.getCCDSetting(practice_id);

		return new ResponseEntity<List<ORMCCDSetting>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/saveCCD_Setting")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveCCD_Setting(@RequestBody List<ORMCCDSetting> obj) 
	{
		ServiceResponse<ORMKeyValue> resp = encounterdService.saveCCD_Setting(obj);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}	
	@RequestMapping("/saveSessionInfo")
	public ResponseEntity<ORMSessionInfo> saveSessionInfo(@RequestBody ORMSessionInfo obj) {
		encounterdService.saveSessionInfo(obj);
		return new ResponseEntity<ORMSessionInfo>(obj, HttpStatus.OK);
	}
	@RequestMapping("/getSessionInformation/{chartID}/{patientID}")
	public @ResponseBody ResponseEntity<List<ORMSessionInfo>> getSessionInformation(
			@PathVariable(value = "chartID") Long chartID,@PathVariable(value = "patientID") Long patientID) {
		List<ORMSessionInfo> obj = encounterdService.getSessionInformation(chartID,patientID);
		return new ResponseEntity<List<ORMSessionInfo>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/getClosingSummary/{chartID}/{patientID}/{session_id}")
	public @ResponseBody ResponseEntity<List<ORMClosingSummary>> getClosingSummary(
			@PathVariable(value = "chartID") String chartID,@PathVariable(value = "patientID") String patientID
			,@PathVariable(value = "session_id") String session_id) {
		List<ORMClosingSummary> obj = encounterdService.getClosingSummary(chartID, patientID, session_id);
		return new ResponseEntity<List<ORMClosingSummary>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/saveClosingSummary")
	public ResponseEntity<ORMClosingSummary> saveClosingSummary(@RequestBody ORMClosingSummary obj) {
		encounterdService.saveClosingSummary(obj);
		return new ResponseEntity<ORMClosingSummary>(obj, HttpStatus.OK);
	}
	@RequestMapping("/getChartDiagnosis/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMChartDiagnosis>> getChartDiagnosis(
			@PathVariable(value = "patient_id") String patient_id,
			@PathVariable(value = "chart_id") String chart_id) {

		List<ORMChartDiagnosis> obj = encounterdService.getChartDiagnosis(patient_id,chart_id);

		return new ResponseEntity<List<ORMChartDiagnosis>>(obj, HttpStatus.OK);

	}
	
	@RequestMapping("/SaveDepressionScreeningToFile")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> SaveDepressionScreeningToFile(
			@RequestBody Wrapper_ObjectSave<ORMGYNForm> objGYNForm) {

		List<ORMKeyValue> objOtherData = objGYNForm.getLstKeyValue();
		String docPath = "";
		String HTML = "";
		for (ORMKeyValue item : objOtherData) {

			if (item.getKey().equalsIgnoreCase("HTML")) {
				HTML = item.getValue();
			}
			if (item.getKey().equalsIgnoreCase("docCategory")) {
				docPath = item.getValue();
			}
		}

		// ObjectMapper mapper = new ObjectMapper();
		System.out.println("Document Category:" + docPath);
		//System.out.println("HTML:" + HTML);

		ORMGYNForm objORMHealthCheck = objGYNForm.getOrmSave();
		// ORMPatientHealthCheck objORMHealthCheck = mapper.readValue(health_data,
		// ORMPatientHealthCheck.class);

		Long result = encounterdService.SaveDepressionScreeningToFile(objORMHealthCheck, HTML, docPath);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(result.toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/getDepressionScreening")
	public @ResponseBody ResponseEntity<List<ORMGetPatientHealthCheckSummary>> getDepressionScreening(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPatientHealthCheckSummary> lst = encounterdService.getDepressionScreening(searchCriteria);
		return new ResponseEntity<List<ORMGetPatientHealthCheckSummary>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/deleteDepressionScreening")
	public int deleteDepressionScreening(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("delete Depression Screening: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("gyn_form");
		ormDeleteRecord.setColumn_name("id");
		return generalService.deleteRecord(ormDeleteRecord);
	}
	@RequestMapping("/getChartPreviousData/{type}/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMFourColumGeneric>> getChartPreviousData(
			@PathVariable(value = "type") String type,
			@PathVariable(value = "patient_id") String patient_id,
			@PathVariable(value = "chart_id") String chart_id) {

		List<ORMFourColumGeneric> obj = encounterdService.getChartPreviousData(type,patient_id,chart_id);

		return new ResponseEntity<List<ORMFourColumGeneric>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/getPatientFollowup/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMPatient_Followup>> getPatientFollowup(
			@PathVariable(value = "chart_id") String chart_id) {

		List<ORMPatient_Followup> obj = encounterdService.getPatientFollowup(chart_id);

		return new ResponseEntity<List<ORMPatient_Followup>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/getPatientFullFollowup/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMPatient_Followup>> getPatientFullFollowup(
			@PathVariable(value = "patient_id") String patient_id) {

		List<ORMPatient_Followup> obj = encounterdService.getPatientFullFollowup(patient_id);

		return new ResponseEntity<List<ORMPatient_Followup>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/savePatientFollowupTask")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> savePatientFollowupTask(@RequestBody ORMPatient_Followup obj) {
		Long result = encounterdService.savePatientFollowupTask(obj);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter savePatientFollowupTask.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/getOfficeCpts/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMChartOfficetestCpt>> getOfficeCpts(
			@PathVariable(value = "practiceId") String practiceId) {
		List<ORMChartOfficetestCpt> obj = encounterdService.getOfficeCpts(practiceId);
		return new ResponseEntity<List<ORMChartOfficetestCpt>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/spGetNoKnown/{patient_id}/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> spGetNoKnown(
			@PathVariable(value = "patient_id") String patient_id,
			@PathVariable(value = "chart_id") String chart_id) {

		List<ORMTwoColumnGeneric> obj = encounterdService.spGetNoKnown(patient_id,chart_id);

		return new ResponseEntity<List<ORMTwoColumnGeneric>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/updateNoKnownData")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateNoKnownData(@RequestBody SearchCriteria searchCriteria) {
		return encounterdService.updateNoKnownData(searchCriteria);
	}	
	@RequestMapping("/updateEncounterEducationProvided")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateEncounterEducationProvided(@RequestBody SearchCriteria searchCriteria) {
		return encounterdService.updateEncounterEducationProvided(searchCriteria);
	}
	@RequestMapping("/updateEncounterMedicationReviewed")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateEncounterMedicationReviewed(@RequestBody SearchCriteria searchCriteria) {
		return encounterdService.updateEncounterMedicationReviewed(searchCriteria);
	}
	@RequestMapping("/GetChartOfficeTestCodeAmt/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMFourColumGeneric>> GetChartOfficeTestCodeAmt(
			@PathVariable(value = "chart_id") String chart_id) {

		List<ORMFourColumGeneric> obj = encounterdService.GetChartOfficeTestCodeAmt(chart_id);

		return new ResponseEntity<List<ORMFourColumGeneric>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/MoveProblemToCurrent")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> MoveProblemToCurrent(
		@RequestBody SearchCriteria searchCriteria){
		ServiceResponse<ORMKeyValue> resp = encounterdService.MoveProblemToCurrent(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
	}
	@RequestMapping("/signSelectedEncounter")
	public long signSelectedEncounter(@RequestBody SearchCriteria searchCriteria) {
		return encounterdService.signSelectedEncounter(searchCriteria);
	}
	@RequestMapping("/MarkEncounterAsClaimCreated")
	public long MarkEncounterAsClaimCreated(@RequestBody SearchCriteria searchCriteria) {
		return encounterdService.MarkEncounterAsClaimCreated(searchCriteria);
	}
	@RequestMapping("/getProblemBasedChartTemplate/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMProblemBasedTemplate>> getProblemBasedChartTemplate(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMProblemBasedTemplate> obj = encounterdService.getProblemBasedChartTemplate(practice_id);

		return new ResponseEntity<List<ORMProblemBasedTemplate>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/getProviderOfProblemBasedTemplate/{practice_id}/{template_id}")
	public @ResponseBody ResponseEntity<List<ORMGetProvider_template>> getProviderOfProblemBasedTemplate(
			@PathVariable(value = "practice_id") String practice_id,
			@PathVariable(value = "template_id") String template_id) {
		
		List<ORMGetProvider_template> obj = encounterdService.getProviderOfProblemBasedTemplate(practice_id,template_id);

		return new ResponseEntity<List<ORMGetProvider_template>>(obj, HttpStatus.OK);

	}
	@RequestMapping("/deleteProblemBasedTemplate")
	public int deleteProblemBasedTemplate(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("chart_template");
		ormDeleteRecord.setColumn_name("template_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}
	@RequestMapping("/saveProblemBasedTemplateSetup")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveProblemBasedTemplateSetup(@RequestBody ORMProblemBasedTemplate obj) {
		Long result = encounterdService.saveProblemBasedTemplateSetup(obj);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving encounter savePatientFollowupTask.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/getProblemBasedTemplateEncounter/{practice_id}/{provider_id}")
	public @ResponseBody ResponseEntity<List<ORMProblemBasedTemplate>> getProblemBasedTemplateEncoounter(
			@PathVariable(value = "practice_id") String practice_id,
			@PathVariable(value = "provider_id") String provider_id) {
		
		List<ORMProblemBasedTemplate> obj = encounterdService.getProblemBasedTemplateEncounter(practice_id,provider_id);

		return new ResponseEntity<List<ORMProblemBasedTemplate>>(obj, HttpStatus.OK);

	}
	
	@RequestMapping("/getLocalPrescriptionById/{prescription_id}")
	public @ResponseBody ResponseEntity<ORMGetLocalPrescriptions> getLocalPrescriptionById(
			@PathVariable(value = "prescription_id") Long prescriptionId) {
		ORMGetLocalPrescriptions obj = encounterdService.getLocalPrescriptionById(prescriptionId);
		return new ResponseEntity<ORMGetLocalPrescriptions>(obj, HttpStatus.OK);
	}

	@RequestMapping("saveLocalPrescription")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveLocalPrescription(@RequestBody ORMSaveLocalPrescription ormPrescription) {

		GeneralOperations.logMsg("Inside saveLocalPrescription: ");

		ServiceResponse<ORMKeyValue> serviceResponse = encounterdService.saveLocalPrescription(ormPrescription);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.OK);

	}
	
	@RequestMapping("/deletePrescription")
	public ResponseEntity<ServiceResponse<ORMKeyValue>>  deletePrescription(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		
		System.out.println("deletePrescription: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_prescription");
		ormDeleteRecord.setColumn_name("chart_prescription_id");

		int result = generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting prescription.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
		
	}
	
	@RequestMapping("markPrescriptionAsInactive")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> markPrescriptionAsInactive(
			@RequestBody UpdateRecord updateRecord) {

		GeneralOperations.logMsg("Inside markPrescriptionAsInactive: ");

		ServiceResponse<ORMKeyValue> serviceResponse = encounterdService.markPrescriptionAsInactive(updateRecord);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.OK);

	}
	
	@RequestMapping("/getLocalAllergyById/{chart_allergies_id}")
	public @ResponseBody ResponseEntity<ORMGetLocalAllergy> getLocalAllergyById(
			@PathVariable(value = "chart_allergies_id") Long chartAllergyId) {
		ORMGetLocalAllergy obj = encounterdService.getLocalAllergyById(chartAllergyId);
		return new ResponseEntity<ORMGetLocalAllergy>(obj, HttpStatus.OK);
	}
	
	@RequestMapping("saveLocalAllergy")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveLocalAllergy(@RequestBody ORMSaveLocalAllergy ormSave) {

		GeneralOperations.logMsg("Inside saveLocalAllergy: ");

		ServiceResponse<ORMKeyValue> serviceResponse = encounterdService.saveLocalAllergy(ormSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.OK);

	}
	
	@RequestMapping("/deleteAllergy")
	public ResponseEntity<ServiceResponse<ORMKeyValue>>  deleteAllergy(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		
		System.out.println("deleteAllergy: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("chart_allergies");
		ormDeleteRecord.setColumn_name("chart_allergies_id");

		int result = generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting allergy.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
		
	}
	
	@RequestMapping("markAllergyAsInactive")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> markAllergyAsInactive(
			@RequestBody UpdateRecord updateRecord) {

		GeneralOperations.logMsg("Inside markAllergyAsInactive: ");

		ServiceResponse<ORMKeyValue> serviceResponse = encounterdService.markAllergyAsInactive(updateRecord);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.OK);

	}
	@RequestMapping("/getDrugAbuse")
	public @ResponseBody ResponseEntity<List<ORMGetPatientHealthCheckSummary>> getDrugAbuse(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPatientHealthCheckSummary> lst = encounterdService.getDrugAbuse(searchCriteria);
		return new ResponseEntity<List<ORMGetPatientHealthCheckSummary>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/deleteDrugAbuse")
	public int deleteDrugAbuse(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("delete Drug Abuse: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("patient_health_check");
		ormDeleteRecord.setColumn_name("health_check_id");
		return generalService.deleteRecord(ormDeleteRecord);
	}
	@RequestMapping("/getPreviewDrigAbuse")
	public List<ORMThreeColumGeneric> getPreviewDrigAbuse(@RequestBody SearchCriteria searchCriteria) {
		return encounterdService.getPreviewDrigAbuse(searchCriteria);
	}
	
	@RequestMapping("/getPatientDrugAbuseSummary")
	public @ResponseBody ResponseEntity<List<ORMGetPatientDrugAbuseSummary>> getPatientDrugAbuseSummary(
			@RequestBody SearchCriteria searchCriteria) {
		String patient_id = "";
		String form_type = "";
		for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
			if (pram.getName().equals("patient_id"))
				patient_id = pram.getValue();
			else if (pram.getName().equals("form_type"))
				form_type = pram.getValue();
		}
		List<ORMGetPatientDrugAbuseSummary> lst = encounterdService.getPatientDrugAbuseSummary(searchCriteria.getPractice_id().toString(), patient_id, form_type);
		return new ResponseEntity<List<ORMGetPatientDrugAbuseSummary>>(lst, HttpStatus.OK);
	}	
}
