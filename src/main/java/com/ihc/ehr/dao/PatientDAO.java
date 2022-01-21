package com.ihc.ehr.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.model.ORMAPIUserPatientGet;
import com.ihc.ehr.model.ORMAPIUsersGet;
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
import com.ihc.ehr.model.Wrapper_PatientSave;

public interface PatientDAO {

	ORMGetPatient getPatientByID(Long patientId);

	ORMGetPatientHeaderInfo getPatientHeaderInfo(Long patientId);

	List<ORMGetPatientVitalGraphData> getVitalGraphData(Long patientId);

	List<ORMGetPatientMedicationSummary> getMedicationSummary(Long patientId);

	List<ORMGetPatientAllergiesSummary> getAllergiesSummary(Long patientId);

	List<ORMGetPatientProblemsSummary> getProblemsSummary(Long patientId);

	List<ORMGetPatientAssessmentsSummary> getAssessmentsSummary(Long patientId);

	List<ORMGetPatientSuregeriesProceduresSummary> getSurgeriesProceduresSummary(Long patientId, String entry_type);

	List<ORMGetPatientHealthMaintenanceSummary> getHealthMaintenanceSummary(Long patientId);

	List<ORMGetPatientConceptionOutcomesSummary> getConceptionOutcomeSummary(Long patientId);

	String getEDD(Long patientId);

	List<ORMGetPatientConsultSummary> getConsultSummary(Long patientId);

	List<ORMGetPatientReferralSummary> getReferralSummary(Long patientId);
	List<ORMThreeColum>getSpeciality(String practice_id);
	List<ORMGetPatient_consultant>getPatientConsultant(String patient_id);
	
	List<ORMGetCommunication> getCommunications(Long patient_id);

	//Long saveeditCorrespondence(ORMPatientCommunication obj);
	//
	List<ORMGetPatientInjuery>getPatInjury(Long patient_id);
	
	List<ORMGetAttorneyInfo> getFirmList(String value);
	
	//List<ORMInsuranceSearch> getInsList(String value);
	
	Long saveEditPatInjury(ORMPatInjury obj);

	List<ORMGetPatientDocument> getPatientDocuments(String patient_id,String practice_id);

	ORMHeaderVitals getPatientHeaderVitals(Long patientId);

	List<ORMPatientConfirmDel> confirmPatientDel(String patient_id);
	
	List<ORMGetPatientInsurance> getPatientInsurance(Long patientId,String status);

	List<ORMGetPatientRaceEthnicity> getPatientRaceEthnicty(String category, Long patientId);

	List<ORMGetPatientNextOfKin> getPatientNextOfKin(Long patientId);

	//ORMGetPatImmRegInfoDisplay getPatientImmRegInfo(Long patientId);

	List<ORMGetPatientCareTeamSummary> getPatientCareTeamSummary(Long teamId);
	
	List<ORMGetPatientCareTeams> getPatientCareTeams(Long patientId);

	List<ORMGetPatientCareTeamMember> getPatientCareTeamMembers(Long patientId);

	ServiceResponse<ORMKeyValue> SavePatient(Wrapper_PatientSave objPatientWrapper, MultipartFile picData);

	ServiceResponse<ORMKeyValue> savePatientNotes(ORMPatientNotesSave ormPatientNotesSave);

	List<ORMGetPatientInsuranceView> getPatientInsuranceView(Long patientId);
	Long saveeditCorrespondence(ORMSaveDocument patDoc, MultipartFile docFile, String docCategory, ORMPatientCommunication communication);
	Long savePatientConsultant(ORMSaveDocument patDoc, MultipartFile docFile, String docCategory, ORMPatientConsultant consult);
	ServiceResponse<ORMKeyValue> saveOpenedPatient(List<ORMKeyValue> lstKeyValue);
	List<ORM_getHealthInfoCapture> getHealthInfoCapture(Long patient_id,Long practice_id);
	//List<ORM_HealthInformationCaptureAttachments> getHealthInfoCaptureAttach(String health_info_id,String recType);
	//List<ORM_HealthInformationCaptureAttachments> getHealthInfoCaptureLinks(String health_info_id,String recType);
	List<ORM_HealthInformationCaptureAttachments> getHealthInfoCaptureAttach(SearchCriteria searchCriteria);
	List<ORM_HealthInformationCaptureAttachments> getHealthInfoCaptureLinks(SearchCriteria searchCriteria);
	//ServiceResponse AddEditNewHealthInformation(WrapperPatientInfoCapture WrapperPatientInfoCapture);
	//ServiceResponse<ORMKeyValue> AddEditNewHealthInformation(MultipartFile[] attachfile, ORM_HealthInformationCaptureAttachments infoattachlinks, ORMHealthInformationCapture infocapture, String attachpath);
	ServiceResponse<ORMKeyValue> AddEditNewHealthInformation(WrapperPatientInfoCapture objInfoCaptureWrapper, MultipartFile[] attachfile);
	List<ORMGetPatientCheckInInfo> getPatientCheckedInInfo(Long patientId);
	List<ORMGetPatientNote> getPatientNotes(Long patientId);
	//Long qrdaImport(String provider_id, String practice_id, MultipartFile docFile);
	ORMGetPatientScannedCards getPatientScannedCards(Long patientId);

	List<ORMAPIUsersGet> getAPIUsers(SearchCriteria searchCriteria);

	List<ORMAPIUserPatientGet> getAPIUserPatients(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> saveAPIUser(Wrapper_APIUserSave wrapper_APIUserSave);

	List<ORMPatientInsurancesForMergeGet> getPatientInsuranceToMerge(String patientIds);

	ServiceResponse<ORMKeyValue> mergePatients(List<ORMKeyValue> lstKeyValue);
	Long addEditDocCategory(ORMDocCategories obj);
	List<ORMGetPatientNote> getStaffNoteAlert(Long patientId);
	List<ORMTwoColumnGeneric> getPatientTaskData(SearchCriteria searchCriteria);
	int deleteDocumentCategory(ORMDocCategories obj);

	Long checkIfPatientExists(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> savePatientPic(SearchCriteria searchCriteria, MultipartFile picData);

	List<ORMGetPhrUsersNames> getPatientPhrUsersNames(String practiceId, String patientId);
}
