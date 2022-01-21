package com.ihc.ehr.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ihc.ehr.model.*;

public interface EncounterService {

	List<ORMGetChartSummary> getChartSummary(String patient_id);

	List<ORMGetAppointmentDate> getAppointmentDates(String patient_id);

	// RFV/HPI-start
	ORMGetReasonForVisitHPI getChartReasonForVisit_HPI(Long chart_id);

	Long saveupdateChartRFV_HPI(ORMChartReasonForVisit_HPI obj);

	// RFV/HPI-end
	// vitals-start

	ORMChartVital getChartVital(Long chart_id);

	List<ORMGetVitalGraphData> getVitalGraphData(Long patientID);

	ServiceResponse<ORMKeyValue> saveupdateChartVital(ORMChartVital obj);

	List<ORMGetCummulativeVitals> getCummulativeVitals(Long patientId, String unitType);

	// vitals-end
	// officetest-start
	List<ORMChartOfficeTest> getOfficeTest(Long chartID);

	// public Long addUpdateTest(ORMChartOfficeTest obj);
	//Long saveOfficeTest(ORMChartOfficeTest obj);
	ServiceResponse<ORMKeyValue> saveOfficeTest(WrapperOfficeTestSave WrapperOfficeTestSave);
	// officetest-end
	// Asthma Control Test - start
	List<ORMGetPatientHealthCheckSummary> getPatientHealthCheckSummary(String practice_id, String patient_id,
			String form_type);

	List<ORMGetPatientHealthCheckForm> getPatientHealthCheckForm(SearchCriteria searchCriteria);

	// Asthma Control Test - end
	// annotation-start
	List<ORMChartAnnotation> getChartAnnotation(String chartID);

	Long saveupdateChartAnnotation(ORMChartAnnotation obj);

	// annotation-end
	// health maintenanceList-start
	List<ORMGetPatientHealthMaintenanceList> getPatientHealthMaintenanceList(String patient_id);

	List<ORMGetPatientHealthMaintenance> getPatientHealthMaintenance(String patient_id, String phm_id);

	// health maintenanceList-end
	ServiceResponse<ORMKeyValue> createNewChart(ORMPatientChart obj);

	List<ORMChartReportDetails> getChartReportDetails(String chart_id);

	List<ORMChartProcedure> getChartSurgery(String chart_id);

	List<ORMChartProcedure> getChartProcedures(String chart_id);

	// public List<ORMThreeColumGeneric> getSexualOrientDDL();
	// public List<ORMThreeColumGeneric> getGenderIdentityDDL();
	List<ORMGetChartFamilyHis> getChartFamilyHis(SearchCriteria searchCriteria);

	List<ORMGetChartPrescription> getPatPrescription(SearchCriteria searchCriteria);

	List<ORMChartAllergies> getPatAllergies(SearchCriteria searchCriteria);

	List<ORMGetPatientRos> getPatROS(String chart_id);

	List<ORMChartProgressNotes> getPatProgressNotes(String chart_id);

	List<ORMCarePlan> getPatCarePlan(String chart_id);

	List<ORMCognitiveFunctional> getChartCognitiveFunctional(String chart_id);

	List<ORMChartPhysicalExam> getPatPhysicalExam(String chart_id);

	List<ORMPatientInjuryTreatmentNotes> getPatInjuryNotes(String patient_id, String chart_id);

	List<ORMGetChartFollowUpPlan> getChartFollowUpProblem(String chart_id);

	List<ORMThreeColum> getNQFPlan();

	List<ORMNQFPlanDetail> getNQFPlanDetail();

	List<ORMPastMedicalHistory> getPastMedHistory(String chart_id);

	List<ORMAssessment> getAssessments(String chart_id);

	List<ORMPHRAuditLog> getPatientAWVPrint(String patient_id, String chart_id);

	List<ORMHealthMentPrint> getHealthMaintPrint(String patient_id, String chart_id);

	List<ORMTwoColumnGeneric> getLabOrderTestPrint(String chart_id);

	List<ORMFourColumGeneric> getAmendmentsPrint(String chart_id);

	// cognitive history-start
	List<ORMThreeColumGeneric> getCognitiveValues(String chart_id);

	Long CognitiveAddUpdate(ORMCognitive_Functional obj);

	// cognitive history-end
	// physicians-care start
	List<ORMPhysicians_Care> GetPhyCare(String practice_id, String patient_id, String chart_id);

	Long saveEditPhyCare(ORMPhysicians_Care obj);

	// physicians-care end
	// Chart Module History--start
	List<?> LoadChartModuleHistory(Long practice_id, String moduleName, SearchCriteria searchCriteria);

	List<ORMTwoColumnGeneric> LoadChartModuleHistoryHeader(String moduleName);

	List<ORMChartModuleSummary> getPatientVisitSnapShot(SearchCriteria searchCriteria);

	List<ORMGetFutureAppointments> getFutureAppointments(String patient_id, String chart_id);

	Long SavePatientHealthExamToFile(ORMPatientHealthCheck objORMHealthCheck, String hTML, String docPath);

	//
	List<ORMGetHealthCheckForms> getHealthCheckFormsList(SearchCriteria searchCriteria);

	Long saveProcedure(ORMChartProcedure obj);

	int SignHealthCheckForm(SearchCriteria searchCriteria);

	// PROBLEM LIST
	List<ORMGetEncounterProblemsSummary> getChartProblem(SearchCriteria searchCriteria);

	ORMGetEncounterProblemDetail getChartProblemDetail(Long problem_id);

	Long saveProblem(ORMSaveEncounterProblem obj);
	// END PROBLEM LIST
	// IMMUNIZATION
	// List<ORMChartImmunizationSummaryGet>
	// getPatientImmunizationSummary(SearchCriteria searchCriteria);
	// END IMMUNIZATION

	List<ORMChartAssessmentView> getChartAssessmentView(String chart_id);

	List<ORMChartPrescriptionView> getChartPrescriptionView(String patient_id);

	List<ORMChartAllergyView> getChartAllergyView(String patient_id);

	List<ORMChartProgressNotesListView> getChartProgressNoteListView(String patient_id, String chart_id);

	List<ORMChartProgressNotesTextView> getChartProgressNoteTextView(String note_id);

	List<ORMSurgeryView> getChartSurgeryView(String chart_id);

	List<ORMFamilyHxView> getChartFamilyHxView(String chart_id);

	Long savePatientAssessments(ORMSaveAssessments obj);

	ORMChartAssessmentDetail getChartAssessmentDetail(String id);

	Long saveChartProgressNotes(ORMSavePlanOfCare obj);

	List<ORMGetPatientHealthCheckForm> getHealthCheckFormFromId(SearchCriteria searchCriteria);

	List<ORMGetPatientPhysicalExamView> getPatPhysicalExamView(String chart_id);

	ORMChartPhysicalExam getPatPhysicalExamDetail(String id);

	Long savePatientPhysicalExam(ORMChartPhysicalExam obj);

	ORMChartSurgery getChartSurgeryDetail(String id);

	Long saveChartSurgery(ORMChartSurgery obj);

	Long saveFamilyHx(ORMFamilyHx obj);

	ORMFamilyHx getChartFamilyHxDetail(String id);

	ORMChartROS getChartROS(String chart_id);

	Long saveChartRos(ORMChartROS obj);

	List<ORMGetChartPMHView> getChartPMHView(String patient_id, String chart_id);

	ORMChartPMH getChartPMHDetail(String id);

	Long savePMH(ORMChartPMH obj);

	List<ORMGetHealthMaintList> getChartHealthMainList(String patient_id);

	List<ORMGetHealthMaintDetail_View> getChartHealthMainDetail_View(String patient_id, String maint_id);

	Long saveHealthMaintDetail(List<ORM_HealthMaintenanceDetail> objDetail);

	Long saveHealthMaint(ORM_HealthMaintenance objMain);

	ServiceResponse<ORMKeyValue> getPrescriptionXml(ORMPrescriptionXml prescriptionXml);

	List<ORMChartAllergyView> GetPrescriptionAllergies(ORMgetPrescriptionAllergies getPresAllerg);

	List<ORMChartPrescriptionView> getPatPrescriptionServer(ORMgetPrescriptionAllergies getPresAllerg);

	int signEncounter(SearchCriteria searchCriteria);

	List<ORMChartAmendments_View> getChartAmendmentsView(String chart_id);

	ORMChartAmendmentsSave getChartAmendmentsDetail(String id);

	Long saveChartAmendments(ORMChartAmendmentsSave obj);

	// IMMUNIZATION
	List<ORMChartImmunizationSummaryGet> getChartImmunizationSummary(SearchCriteria searchCriteria);

	List<ORMChartImunizationVISGet> getChartImmunizationVIS(Long chart_immunization_id);

	ORMChartImmunizationGet getChartImmunizationById(Long chart_immunization_id);

	ServiceResponse<ORMKeyValue> saveChartImmunization(Wrapper_ChartImmunizationSave wrapperChartImmunizationSave);
	// END IMMUNIZATION

	List<ORMGetGrowthChartInfo> getGrowthChartData(String practice_id, String patient_id);

	ORMChartSocialHisDisplayGet getChartSocialHistDisplay(Long chart_id);

	ORMChartSocialHistoryGet getChartSocialHistDetailById(Long socialhistoryId);

	ServiceResponse<ORMKeyValue> saveSocialHistory(ORMChartSocialHistorySave obj);

	// ***** IMPLANTABLE DEVICE **** //
	Object getDeviceDetailFromGlobalUDIDB(SearchCriteria searchCriteria);

	List<ORMPatientImplantableDeviceGetSummary> getPatImplantableDevicesSummary(Long patient_id);

	ORMPatientImplantableDeviceGetDetail getImplantableDevicesDetailById(Long implantable_device_id);

	ServiceResponse<ORMKeyValue> savePatImplantDevice(ORMPatientImplantableDeviceSave obj);
	// ***** END IMPLANTABLE DEVICE **** //

	ORMPatientDischargeGetSummary getPatientDischargeDispositionSummary(Long chartId);

	ORMPatientDischargeDisposition getPatientDischargeDispositionDetail(Long dischargeId);

	ServiceResponse<ORMKeyValue> savePatientDischargeDisposition(ORMPatientDischargeDisposition obj);

	Long saveCarePlan(ORMCarePlan obj);

	List<ORMHealthConcernView> getChartHealthConcernView(String chart_id);

	List<ORMHealthConcernViewDetail> getChartHealthConcernViewDetail(String chart_id);

	ServiceResponse<ORMKeyValue> SaveHealthConcern(HealthConcernSaveWrapper wrapper);

	// ASSESSMENT & PLAN OF TREATMENT
	List<ORMAssessPlanGet> getPatientAssessmentPlan(Long patient_id);

	List<ORMAssessPlanAssessmentGet> getAssessPlanAssessment(Long patient_id);

	List<ORMAssessPlanOfTreatementGet> getAssessPlanPOT(Long patient_id);

	ServiceResponse<ORMKeyValue> saveAssessPlan(WrapperAssessPlanSave wrapper);

	List<ORMGetTemplateText> getTemlplateText(String practice_id, String provider_id, String type);

	ServiceResponse<ORMKeyValue> ApplyChartTemplate(ORMChartTemplateApply obj);

	List<ORMGetCurrentEncounterTemplate> getCurrentEncounterTemplate(String chart_id);

	List<ORMGetTemplateEncounterSummary> getTemplateEncSummary(String patient_id, String chart_id);

	List<ORMGetPatientVisit_CCD> getPatientVisit_CCD(String patient_id);

	List<ORMCCDSetting> getCCDSetting(String practice_id);

	ServiceResponse<ORMKeyValue> saveCCD_Setting(List<ORMCCDSetting> obj);

	long resolveProblem(SearchCriteria searchCriteria);

	List<ORMChartDiagnosis> getChartDiagnosis(String patient_id, String chart_id);

	// END ASSESSMENT & PLAN OF TREATMENT
	Long saveSessionInfo(ORMSessionInfo obj);
	List<ORMSessionInfo> getSessionInformation(Long chartID, Long patientID);
	List<ORMClosingSummary> getClosingSummary(String chartID, String patientID, String session_id);
	Long saveClosingSummary(ORMClosingSummary obj);
	Long SaveDepressionScreeningToFile(ORMGYNForm objGYNForm, String hTML, String docPath);
	List<ORMGetPatientHealthCheckSummary> getDepressionScreening(SearchCriteria searchCriteria);

	List<ORMFourColumGeneric> getChartPreviousData(String type, String patient_id, String chart_id);

	List<ORMPatient_Followup> getPatientFollowup(String chart_id);

	Long savePatientFollowupTask(ORMPatient_Followup obj);
	List<ORMChartOfficetestCpt> getOfficeCpts(String practiceId);

	List<ORMTwoColumnGeneric> spGetNoKnown(String patient_id, String chart_id);

	ResponseEntity<ServiceResponse<ORMKeyValue>> updateNoKnownData(SearchCriteria searchCriteria);

	ResponseEntity<ServiceResponse<ORMKeyValue>> updateEncounterEducationProvided(SearchCriteria searchCriteria);

	ResponseEntity<ServiceResponse<ORMKeyValue>> updateEncounterMedicationReviewed(SearchCriteria searchCriteria);

	List<ORMFourColumGeneric> GetChartOfficeTestCodeAmt(String chart_id);

	List<ORMChartSocialHistoryGet> getChartSocialHistory(Long chart_id);
	ServiceResponse<ORMKeyValue> MoveProblemToCurrent(SearchCriteria searchCriteria);
	int signSelectedEncounter(SearchCriteria searchCriteria);

	List<ORMSurgeryView> getChartProcedureView(String chart_id);

	ServiceResponse<ORMKeyValue> saveChartProcedure(List<ORMChartSurgery> lstSave);

	long MarkEncounterAsClaimCreated(SearchCriteria searchCriteria);

	List<ORMProblemBasedTemplate> getProblemBasedChartTemplate(String practice_id);

	List<ORMGetProvider_template> getProviderOfProblemBasedTemplate(String practice_id, String template_id);

	Long saveProblemBasedTemplateSetup(ORMProblemBasedTemplate obj);

	List<ORMProblemBasedTemplate> getProblemBasedTemplateEncounter(String practice_id, String provider_id);

	ORMGetLocalPrescriptions getLocalPrescriptionById(Long prescriptionId);

	ServiceResponse<ORMKeyValue> saveLocalPrescription(ORMSaveLocalPrescription ormPrescription);

	ServiceResponse<ORMKeyValue> markPrescriptionAsInactive(UpdateRecord updateRecord);
	
	ORMGetLocalAllergy getLocalAllergyById(Long chartAllergyId);

	ServiceResponse<ORMKeyValue> saveLocalAllergy(ORMSaveLocalAllergy ormSave);

	ServiceResponse<ORMKeyValue> markAllergyAsInactive(UpdateRecord updateRecord);

	List<ORMPatient_Followup> getPatientFullFollowup(String patient_id);

	List<ORMgetAllChartPrintModule> getAllChartPrintModule(String practice_id);
	long deleteProgressNotes(SearchCriteria searchCriteria);
	List<ORMGetPatientHealthCheckSummary> getDrugAbuse(SearchCriteria searchCriteria);
	List<ORMThreeColumGeneric> getPreviewDrigAbuse(SearchCriteria searchCriteria);
	List<ORMGetPatientDrugAbuseSummary> getPatientDrugAbuseSummary(String practice_id, String patient_id, String form_type);
} 
