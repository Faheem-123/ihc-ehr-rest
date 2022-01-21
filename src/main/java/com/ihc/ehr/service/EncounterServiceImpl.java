package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.EncounterDAO;
import com.ihc.ehr.model.*;

@Service
public class EncounterServiceImpl implements EncounterService {

	@Autowired
	private EncounterDAO encounterDao;

	@Override
	public List<ORMGetChartSummary> getChartSummary(String patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartSummary(patient_id);
	}

	@Override
	public List<ORMGetAppointmentDate> getAppointmentDates(String patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getAppointmentDates(patient_id);
	}

	// RFV/HPI-start
	@Override
	public ORMGetReasonForVisitHPI getChartReasonForVisit_HPI(Long chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartReasonForVisit_HPI(chart_id);
	}

	@Override
	public Long saveupdateChartRFV_HPI(ORMChartReasonForVisit_HPI obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveupdateChartRFV_HPI(obj);
	}

	// RFV/HPI-start
	// vitals-start

	@Override
	public ORMChartVital getChartVital(Long chart_id) {
		return encounterDao.getChartVital(chart_id);
	}

	@Override
	public List<ORMGetVitalGraphData> getVitalGraphData(Long patientID) {
		return encounterDao.getVitalGraphData(patientID);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveupdateChartVital(ORMChartVital obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveupdateChartVital(obj);
	}

	@Override
	public List<ORMGetCummulativeVitals> getCummulativeVitals(Long patientId,String unitType) {
		return encounterDao.getCummulativeVitals(patientId,unitType);
	}

	// vitals-end
	// officetest-start
	@Override
	public List<ORMChartOfficeTest> getOfficeTest(Long chartID) {
		return encounterDao.getOfficeTest(chartID);
	}

	/*
	 * @Override public Long addUpdateTest(ORMChartOfficeTest obj) { // TODO
	 * Auto-generated method stub return encounterDao.addUpdateTest(obj); }
	 */
	//@Override
	//public Long saveOfficeTest(ORMChartOfficeTest obj) {
		//return encounterDao.saveOfficeTest(obj);
	//}
	@Override
	public ServiceResponse<ORMKeyValue> saveOfficeTest(WrapperOfficeTestSave WrapperOfficeTestSave) {
		return encounterDao.saveOfficeTest(WrapperOfficeTestSave);
	}

	// officetest-end
	// Asthma Control Test - start
	@Override
	public List<ORMGetPatientHealthCheckSummary> getPatientHealthCheckSummary(String practice_id, String patient_id,
			String form_type) {
		return encounterDao.getPatientHealthCheckSummary(practice_id, patient_id, form_type);
	}

	@Override
	public List<ORMGetPatientHealthCheckForm> getPatientHealthCheckForm(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.getPatientHealthCheckForm(searchCriteria);
	}

	// Asthma Control Test - end
	// annotation-start
	@Override
	public List<ORMChartAnnotation> getChartAnnotation(String chartID) {
		return encounterDao.getChartAnnotation(chartID);
	}

	@Override
	public Long saveupdateChartAnnotation(ORMChartAnnotation obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveupdateChartAnnotation(obj);
	}

	// annotation-end
	// health maintenanceList-start
	@Override
	public List<ORMGetPatientHealthMaintenanceList> getPatientHealthMaintenanceList(String patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatientHealthMaintenanceList(patient_id);
	}

	@Override
	public List<ORMGetPatientHealthMaintenance> getPatientHealthMaintenance(String patient_id, String phm_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatientHealthMaintenance(patient_id, phm_id);
	}

	// health maintenanceList-end
	@Override
	public ServiceResponse<ORMKeyValue> createNewChart(ORMPatientChart obj) {
		// TODO Auto-generated method stub
		return encounterDao.createNewChart(obj);
	}

	@Override
	public List<ORMChartReportDetails> getChartReportDetails(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartReportDetails(chart_id);
	}

	@Override
	public List<ORMChartProcedure> getChartSurgery(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartSurgery(chart_id);
	}

	@Override
	public List<ORMChartProcedure> getChartProcedures(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartProcedures(chart_id);
	}

	// @Override
	// public List<ORMThreeColumGeneric> getSexualOrientDDL() {
	// // TODO Auto-generated method stub
	// return encounterDao.getSexualOrientDDL();
	// }
	// @Override
	// public List<ORMThreeColumGeneric> getGenderIdentityDDL() {
	// // TODO Auto-generated method stub
	// return encounterDao.getGenderIdentityDDL();
	// }
	@Override
	public List<ORMGetChartFamilyHis> getChartFamilyHis(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.getChartFamilyHis(searchCriteria);
	}

	@Override
	public List<ORMGetChartPrescription> getPatPrescription(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.getPatPrescription(searchCriteria);
	}

	@Override
	public List<ORMChartAllergies> getPatAllergies(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.getPatAllergies(searchCriteria);
	}

	@Override
	public List<ORMGetPatientRos> getPatROS(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatROS(chart_id);
	}

	@Override
	public List<ORMChartProgressNotes> getPatProgressNotes(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatProgressNotes(chart_id);
	}

	@Override
	public List<ORMCarePlan> getPatCarePlan(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatCarePlan(chart_id);
	}

	@Override
	public List<ORMCognitiveFunctional> getChartCognitiveFunctional(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartCognitiveFunctional(chart_id);
	}

	@Override
	public List<ORMChartPhysicalExam> getPatPhysicalExam(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatPhysicalExam(chart_id);
	}

	@Override
	public List<ORMPatientInjuryTreatmentNotes> getPatInjuryNotes(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatInjuryNotes(patient_id, chart_id);
	}

	@Override
	public List<ORMGetChartFollowUpPlan> getChartFollowUpProblem(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartFollowUpProblem(chart_id);
	}

	@Override
	public List<ORMThreeColum> getNQFPlan() {
		return encounterDao.getNQFPlan();
	}

	@Override
	public List<ORMNQFPlanDetail> getNQFPlanDetail() {
		return encounterDao.getNQFPlanDetail();
	}

	@Override
	public List<ORMPastMedicalHistory> getPastMedHistory(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPastMedHistory(chart_id);
	}

	@Override
	public List<ORMAssessment> getAssessments(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getAssessments(chart_id);
	}

	@Override
	public List<ORMPHRAuditLog> getPatientAWVPrint(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatientAWVPrint(patient_id, chart_id);
	}

	@Override
	public List<ORMHealthMentPrint> getHealthMaintPrint(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getHealthMaintPrint(patient_id, chart_id);
	}

	@Override
	public List<ORMTwoColumnGeneric> getLabOrderTestPrint(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getLabOrderTestPrint(chart_id);
	}

	@Override
	public List<ORMFourColumGeneric> getAmendmentsPrint(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getAmendmentsPrint(chart_id);
	}

	// cognative history-start
	@Override
	public List<ORMThreeColumGeneric> getCognitiveValues(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getCognitiveValues(chart_id);
	}

	@Override
	public Long CognitiveAddUpdate(ORMCognitive_Functional obj) {
		return encounterDao.CognitiveAddUpdate(obj);
	}

	// cognative history-end
	// physicians-care start
	@Override
	public List<ORMPhysicians_Care> GetPhyCare(String practice_id, String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.GetPhyCare(practice_id, patient_id, chart_id);
	}

	@Override
	public Long saveEditPhyCare(ORMPhysicians_Care obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveEditPhyCare(obj);
	}

	// physicians-care end
	// Chart Module History--start
	@Override
	public List<?> LoadChartModuleHistory(Long practice_id, String moduleName, SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.LoadChartModuleHistory(practice_id, moduleName, searchCriteria);
	}

	@Override
	public List<ORMTwoColumnGeneric> LoadChartModuleHistoryHeader(String moduleName) {
		// TODO Auto-generated method stub
		return encounterDao.LoadChartModuleHistoryHeader(moduleName);
	}

	// Chart Module History--end
	@Override
	public List<ORMChartModuleSummary> getPatientVisitSnapShot(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.getPatientVisitSnapShot(searchCriteria);
	}

	@Override
	public List<ORMGetFutureAppointments> getFutureAppointments(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getFutureAppointments(patient_id, chart_id);
	}

	@Override
	public Long SavePatientHealthExamToFile(ORMPatientHealthCheck objORMHealthCheck, String hTML, String docPath) {
		// TODO Auto-generated method stub
		return encounterDao.SavePatientHealthExamToFile(objORMHealthCheck, hTML, docPath);
	}

	@Override
	public List<ORMGetHealthCheckForms> getHealthCheckFormsList(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.getHealthCheckFormsList(searchCriteria);
	}

	@Override
	public int SignHealthCheckForm(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.SignHealthCheckForm(searchCriteria);
	}

	@Override
	public Long saveProcedure(ORMChartProcedure obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveProcedure(obj);
	}

	/***** IU ****/
	// PROBLEM LIST
	@Override
	public List<ORMGetEncounterProblemsSummary> getChartProblem(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.getChartProblem(searchCriteria);
	}

	@Override
	public ORMGetEncounterProblemDetail getChartProblemDetail(Long problem_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartProblemDetail(problem_id);
	}

	@Override
	public Long saveProblem(ORMSaveEncounterProblem obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveProblem(obj);
	}

	// END PROBLEM LIST

	@Override
	public List<ORMChartAssessmentView> getChartAssessmentView(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartAssessmentView(chart_id);
	}

	@Override
	public List<ORMChartPrescriptionView> getChartPrescriptionView(String patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartPrescriptionView(patient_id);
	}

	@Override
	public List<ORMChartAllergyView> getChartAllergyView(String patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartAllergyView(patient_id);
	}

	@Override
	public List<ORMChartProgressNotesListView> getChartProgressNoteListView(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartProgressNoteListView(patient_id, chart_id);
	}

	@Override
	public List<ORMChartProgressNotesTextView> getChartProgressNoteTextView(String note_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartProgressNoteTextView(note_id);
	}

	@Override
	public List<ORMSurgeryView> getChartSurgeryView(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartSurgeryView(chart_id);
	}

	@Override
	public List<ORMFamilyHxView> getChartFamilyHxView(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartFamilyHxView(chart_id);
	}

	@Override
	public Long savePatientAssessments(ORMSaveAssessments obj) {
		// TODO Auto-generated method stub
		return encounterDao.savePatientAssessments(obj);
	}

	@Override
	public ORMChartAssessmentDetail getChartAssessmentDetail(String id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartAssessmentDetail(id);
	}

	@Override
	public Long saveChartProgressNotes(ORMSavePlanOfCare obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveChartProgressNotes(obj);
	}

	@Override
	public List<ORMGetPatientHealthCheckForm> getHealthCheckFormFromId(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.getHealthCheckFormFromId(searchCriteria);
	}

	@Override
	public List<ORMGetPatientPhysicalExamView> getPatPhysicalExamView(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatPhysicalExamView(chart_id);
	}

	@Override
	public ORMChartPhysicalExam getPatPhysicalExamDetail(String id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatPhysicalExamDetail(id);
	}

	@Override
	public Long savePatientPhysicalExam(ORMChartPhysicalExam obj) {
		// TODO Auto-generated method stub
		return encounterDao.savePatientPhysicalExam(obj);
	}

	@Override
	public ORMChartSurgery getChartSurgeryDetail(String id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartSurgeryDetail(id);
	}

	@Override
	public Long saveChartSurgery(ORMChartSurgery obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveChartSurgery(obj);
	}

	@Override
	public Long saveFamilyHx(ORMFamilyHx obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveFamilyHx(obj);
	}

	@Override
	public ORMFamilyHx getChartFamilyHxDetail(String id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartFamilyHxDetail(id);
	}

	@Override
	public ORMChartROS getChartROS(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartROS(chart_id);
	}

	@Override
	public Long saveChartRos(ORMChartROS obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveChartRos(obj);
	}

	@Override
	public List<ORMGetChartPMHView> getChartPMHView(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartPMHView(patient_id, chart_id);
	}

	@Override
	public ORMChartPMH getChartPMHDetail(String id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartPMHDetail(id);
	}

	@Override
	public Long savePMH(ORMChartPMH obj) {
		// TODO Auto-generated method stub
		return encounterDao.savePMH(obj);
	}

	@Override
	public List<ORMGetHealthMaintList> getChartHealthMainList(String patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartHealthMainList(patient_id);
	}

	@Override
	public List<ORMGetHealthMaintDetail_View> getChartHealthMainDetail_View(String patient_id, String maint_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartHealthMainDetail_View(patient_id, maint_id);
	}

	@Override
	public Long saveHealthMaintDetail(List<ORM_HealthMaintenanceDetail> objDetail) {
		// TODO Auto-generated method stub
		return encounterDao.saveHealthMaintDetail(objDetail);
	}

	@Override
	public Long saveHealthMaint(ORM_HealthMaintenance objMain) {
		// TODO Auto-generated method stub
		return encounterDao.saveHealthMaint(objMain);
	}

	@Override
	public ServiceResponse<ORMKeyValue> getPrescriptionXml(ORMPrescriptionXml PrescriptionXml) {
		// TODO Auto-generated method stub
		return encounterDao.getPrescriptionXml(PrescriptionXml);
	}

	@Override
	public List<ORMChartAllergyView> GetPrescriptionAllergies(ORMgetPrescriptionAllergies getPresAllerg) {
		// TODO Auto-generated method stub
		return encounterDao.GetPrescriptionAllergies(getPresAllerg);
	}

	@Override
	public List<ORMChartPrescriptionView> getPatPrescriptionServer(ORMgetPrescriptionAllergies getPresAllerg) {
		// TODO Auto-generated method stub
		return encounterDao.getPatPrescriptionServer(getPresAllerg);
	}

	// @Override
	// public List<ORMGetImplantableDevicesByApi> getDeviceDetail(String UDI) {
	// public Object getDeviceDetail(String UDI) {
	// return encounterDao.getDeviceDetail(UDI);
	// }

	@Override
	public int signEncounter(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.signEncounter(searchCriteria);
	}

	@Override
	public List<ORMChartAmendments_View> getChartAmendmentsView(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartAmendmentsView(chart_id);
	}

	@Override
	public ORMChartAmendmentsSave getChartAmendmentsDetail(String id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartAmendmentsDetail(id);
	}

	@Override
	public Long saveChartAmendments(ORMChartAmendmentsSave obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveChartAmendments(obj);
	}

	// IMMUNIZATION
	@Override
	public List<ORMChartImmunizationSummaryGet> getChartImmunizationSummary(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.getChartImmunizationSummary(searchCriteria);
	}

	@Override
	public List<ORMChartImunizationVISGet> getChartImmunizationVIS(Long chart_immunization_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartImmunizationVIS(chart_immunization_id);
	}

	@Override
	public ORMChartImmunizationGet getChartImmunizationById(Long chart_immunization_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartImmunizationById(chart_immunization_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveChartImmunization(
			Wrapper_ChartImmunizationSave wrapperChartImmunizationSave) {
		// TODO Auto-generated method stub
		return encounterDao.saveChartImmunization(wrapperChartImmunizationSave);
	}
	// END IMMUNIZATION

	@Override
	public List<ORMGetGrowthChartInfo> getGrowthChartData(String practice_id, String patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getGrowthChartData(practice_id, patient_id);
	}

	@Override
	public ORMChartSocialHisDisplayGet getChartSocialHistDisplay(Long chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.ORMChartSocialHisDisplayGet(chart_id);
	}

	@Override
	public ORMChartSocialHistoryGet getChartSocialHistDetailById(Long socialhistoryId) {
		// TODO Auto-generated method stub
		return encounterDao.getChartSocialHistDetailById(socialhistoryId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSocialHistory(ORMChartSocialHistorySave obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveSocialHistory(obj);
	}

	// ***** IMPLANTABLE DEVICE **** //

	@Override
	public Object getDeviceDetailFromGlobalUDIDB(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.getDeviceDetailFromGlobalUDIDB(searchCriteria);
	}

	@Override
	public List<ORMPatientImplantableDeviceGetSummary> getPatImplantableDevicesSummary(Long patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatImplantableDevicesSummary(patient_id);
	}

	@Override
	public ORMPatientImplantableDeviceGetDetail getImplantableDevicesDetailById(Long implantable_device_id) {
		// TODO Auto-generated method stub
		return encounterDao.getImplantableDevicesDetailById(implantable_device_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePatImplantDevice(ORMPatientImplantableDeviceSave obj) {
		// TODO Auto-generated method stub
		return encounterDao.savePatImplantDevice(obj);
	}

	// ***** END IMPLANTABLE DEVICE **** //
	
	@Override
	public ORMPatientDischargeGetSummary getPatientDischargeDispositionSummary(Long chartId) {
		// TODO Auto-generated method stub
		return encounterDao.getPatientDischargeDispositionSummary(chartId);
	}

	@Override
	public ORMPatientDischargeDisposition getPatientDischargeDispositionDetail(Long dischargeId) {
		// TODO Auto-generated method stub
		return encounterDao.getPatientDischargeDispositionDetail(dischargeId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePatientDischargeDisposition(ORMPatientDischargeDisposition obj) {
		// TODO Auto-generated method stub
		return encounterDao.savePatientDischargeDisposition(obj);
	}
	

	@Override
	public Long saveCarePlan(ORMCarePlan obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveCarePlan(obj);
	}

	@Override
	public List<ORMHealthConcernView> getChartHealthConcernView(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartHealthConcernView(chart_id);
	}

	@Override
	public List<ORMHealthConcernViewDetail> getChartHealthConcernViewDetail(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartHealthConcernViewDetail(chart_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> SaveHealthConcern(HealthConcernSaveWrapper wrapper) {
		// TODO Auto-generated method stub
		return encounterDao.SaveHealthConcern(wrapper);
	}

//  ASSESSMENT & PLAN OF TREATMENT
	@Override
	public List<ORMAssessPlanGet> getPatientAssessmentPlan(Long patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatientAssessmentPlan(patient_id);
	}

	@Override
	public List<ORMAssessPlanAssessmentGet> getAssessPlanAssessment(Long patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getAssessPlanAssessment(patient_id);
	}

	@Override
	public List<ORMAssessPlanOfTreatementGet> getAssessPlanPOT(Long patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getAssessPlanPOT(patient_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveAssessPlan(WrapperAssessPlanSave wrapper) {
		// TODO Auto-generated method stub
		return encounterDao.saveAssessPlan(wrapper);
	}

	@Override
	public List<ORMGetTemplateText> getTemlplateText(String practice_id, String provider_id, String type) {
		// TODO Auto-generated method stub
		return encounterDao.getTemlplateText(practice_id,provider_id,type);
	}

	@Override
	public ServiceResponse<ORMKeyValue> ApplyChartTemplate(ORMChartTemplateApply obj) {
		// TODO Auto-generated method stub
		return encounterDao.ApplyChartTemplate(obj);
	}

	@Override
	public List<ORMGetCurrentEncounterTemplate> getCurrentEncounterTemplate(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getCurrentEncounterTemplate(chart_id);
	}

	@Override
	public List<ORMGetTemplateEncounterSummary> getTemplateEncSummary(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getTemplateEncSummary(patient_id,chart_id);
	}

	@Override
	public List<ORMGetPatientVisit_CCD> getPatientVisit_CCD(String patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatientVisit_CCD(patient_id);
	}

	@Override
	public List<ORMCCDSetting> getCCDSetting(String practice_id) {
		// TODO Auto-generated method stub
		return encounterDao.getCCDSetting(practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveCCD_Setting(List<ORMCCDSetting> obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveCCD_Setting(obj);
	}

	@Override
	public long resolveProblem(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.resolveProblem(searchCriteria);
	}

	@Override
	public List<ORMChartDiagnosis> getChartDiagnosis(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartDiagnosis(patient_id,chart_id);
	}
	 

//  END ASSESSMENT & PLAN OF TREATMENT
	
	@Override
	public Long saveSessionInfo(ORMSessionInfo obj) {
		return encounterDao.saveSessionInfo(obj);
	}
	@Override
	public List<ORMSessionInfo> getSessionInformation(Long chartID, Long patientID) {
		return encounterDao.getSessionInformation(chartID,patientID);
	}
	@Override
	public List<ORMClosingSummary> getClosingSummary(String chartID, String patientID, String session_id) {
		return encounterDao.getClosingSummary(chartID, patientID, session_id);
	}
	@Override
	public Long saveClosingSummary(ORMClosingSummary obj) {
		return encounterDao.saveClosingSummary(obj);
	}
	@Override
	public Long SaveDepressionScreeningToFile(ORMGYNForm objGYNForm, String hTML, String docPath) {
		return encounterDao.SaveDepressionScreeningToFile(objGYNForm, hTML, docPath);
	}
	@Override
	public List<ORMGetPatientHealthCheckSummary> getDepressionScreening(SearchCriteria searchCriteria) {
		return encounterDao.getDepressionScreening(searchCriteria);
	}

	@Override
	public List<ORMFourColumGeneric> getChartPreviousData(String type,String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartPreviousData(type,patient_id,chart_id);
	}

	@Override
	public List<ORMPatient_Followup> getPatientFollowup(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatientFollowup(chart_id);
	}

	@Override
	public Long savePatientFollowupTask(ORMPatient_Followup obj) {
		// TODO Auto-generated method stub
		return encounterDao.savePatientFollowupTask(obj);
	}
	@Override
	public List<ORMChartOfficetestCpt> getOfficeCpts(String practiceId) {
		return encounterDao.getOfficeCpts(practiceId);
	}

	@Override
	public List<ORMTwoColumnGeneric> spGetNoKnown(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.spGetNoKnown(patient_id,chart_id);
	}

	@Override
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateNoKnownData(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.updateNoKnownData(searchCriteria);
	}

	@Override
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateEncounterEducationProvided(
			SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.updateEncounterEducationProvided(searchCriteria);
	}

	@Override
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateEncounterMedicationReviewed(
			SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.updateEncounterMedicationReviewed(searchCriteria);
	}

	@Override
	public List<ORMFourColumGeneric> GetChartOfficeTestCodeAmt(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.GetChartOfficeTestCodeAmt(chart_id);
	}

	@Override
	public List<ORMChartSocialHistoryGet> getChartSocialHistory(Long chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartSocialHistory(chart_id);
	}
	@Override
	public ServiceResponse<ORMKeyValue> MoveProblemToCurrent(SearchCriteria searchCriteria) {
		return encounterDao.MoveProblemToCurrent(searchCriteria);
	}
	@Override
	public int signSelectedEncounter(SearchCriteria searchCriteria) {
		return encounterDao.signSelectedEncounter(searchCriteria);
	}

	@Override
	public List<ORMSurgeryView> getChartProcedureView(String chart_id) {
		// TODO Auto-generated method stub
		return encounterDao.getChartProcedureView(chart_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveChartProcedure(List<ORMChartSurgery> lstSave) {
		// TODO Auto-generated method stub
		return encounterDao.saveChartProcedure(lstSave);
	}

	@Override
	public long MarkEncounterAsClaimCreated(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return encounterDao.MarkEncounterAsClaimCreated(searchCriteria);
	}

	@Override
	public List<ORMProblemBasedTemplate> getProblemBasedChartTemplate(String practice_id) {
		// TODO Auto-generated method stub
		return encounterDao.getProblemBasedChartTemplate(practice_id);
	}

	@Override
	public List<ORMGetProvider_template> getProviderOfProblemBasedTemplate(String practice_id, String template_id) {
		// TODO Auto-generated method stub
		return encounterDao.getProviderOfProblemBasedTemplate(practice_id,template_id);
		
	}

	@Override
	public Long saveProblemBasedTemplateSetup(ORMProblemBasedTemplate obj) {
		// TODO Auto-generated method stub
		return encounterDao.saveProblemBasedTemplateSetup(obj);
	}

	@Override
	public List<ORMProblemBasedTemplate> getProblemBasedTemplateEncounter(String practice_id, String provider_id) {
		// TODO Auto-generated method stub
		return encounterDao.getProblemBasedTemplateEncounter(practice_id,provider_id);
	}
	
	@Override
	public ORMGetLocalPrescriptions getLocalPrescriptionById(Long prescriptionId) {
		// TODO Auto-generated method stub
		return encounterDao.getLocalPrescriptionById(prescriptionId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveLocalPrescription(ORMSaveLocalPrescription ormPrescription) {
		// TODO Auto-generated method stub
		return encounterDao.saveLocalPrescription(ormPrescription);
	}

	@Override
	public ServiceResponse<ORMKeyValue> markPrescriptionAsInactive(UpdateRecord updateRecord) {
		// TODO Auto-generated method stub
		return encounterDao.markPrescriptionAsInactive(updateRecord);
	}
	
	@Override
	public ORMGetLocalAllergy getLocalAllergyById(Long chartAllergyId) {
		// TODO Auto-generated method stub
		return encounterDao.getLocalAllergyById(chartAllergyId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveLocalAllergy(ORMSaveLocalAllergy ormSave) {
		// TODO Auto-generated method stub
		return encounterDao.saveLocalAllergy(ormSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> markAllergyAsInactive(UpdateRecord updateRecord) {
		// TODO Auto-generated method stub
		return encounterDao.markAllergyAsInactive(updateRecord);
	}

	@Override
	public List<ORMPatient_Followup> getPatientFullFollowup(String patient_id) {
		// TODO Auto-generated method stub
		return encounterDao.getPatientFullFollowup(patient_id);
	}

	@Override
	public List<ORMgetAllChartPrintModule> getAllChartPrintModule(String practice_id) {
		// TODO Auto-generated method stub
		return encounterDao.getAllChartPrintModule(practice_id);
	}
	@Override
	public long deleteProgressNotes(SearchCriteria searchCriteria) {
		return encounterDao.deleteProgressNotes(searchCriteria); 
	}
	@Override
	public List<ORMGetPatientHealthCheckSummary> getDrugAbuse(SearchCriteria searchCriteria) {
		return encounterDao.getDrugAbuse(searchCriteria);
	}
	@Override
	public List<ORMThreeColumGeneric> getPreviewDrigAbuse(SearchCriteria searchCriteria) {
		return encounterDao.getPreviewDrigAbuse(searchCriteria);
	}
	@Override
	public List<ORMGetPatientDrugAbuseSummary> getPatientDrugAbuseSummary(String practice_id, String patient_id, String form_type) {
		return encounterDao.getPatientDrugAbuseSummary(practice_id, patient_id, form_type);
	}
}