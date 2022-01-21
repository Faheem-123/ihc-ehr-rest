package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.dao.PatientDAO;
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

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDAO patientDAO;



	@Override
	public ORMGetPatient getPatientByID(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientByID(patientId);
	}

	@Override
	public ORMGetPatientHeaderInfo getPatientHeaderInfo(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientHeaderInfo(patientId);
	}

	@Override
	public List<ORMGetPatientVitalGraphData> getVitalGraphData(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getVitalGraphData(patientId);
	}

	@Override
	public List<ORMGetPatientMedicationSummary> getMedicationSummary(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getMedicationSummary(patientId);
	}

	@Override
	public List<ORMGetPatientAllergiesSummary> getAllergiesSummary(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getAllergiesSummary(patientId);
	}

	@Override
	public List<ORMGetPatientProblemsSummary> getProblemsSummary(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getProblemsSummary(patientId);
	}

	@Override
	public List<ORMGetPatientAssessmentsSummary> getAssessmentsSummary(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getAssessmentsSummary(patientId);
	}	
	
	@Override
	public List<ORMGetPatientSuregeriesProceduresSummary> getSurgeriesProceduresSummary(Long patientId, String entry_type) {
		// TODO Auto-generated method stub
		return patientDAO.getSurgeriesProceduresSummary(patientId,entry_type);
	}

	@Override
	public List<ORMGetPatientHealthMaintenanceSummary> getHealthMaintenanceSummary(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getHealthMaintenanceSummary(patientId);
	}

	@Override
	public List<ORMThreeColum> getSpeciality(String practice_id) {
		// TODO Auto-generated method stub
		return patientDAO.getSpeciality(practice_id);
	}

	@Override
	public List<ORMGetPatient_consultant> getPatientConsultant(String patient_id) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientConsultant(patient_id);
	}

	/*@Override
	public Long savePatientConsultant(ORMPatientConsultant obj) {
		// TODO Auto-generated method stub
		return patientDAO.savePatientConsultant(obj);
	}*/

	@Override
	public List<ORMGetPatientConceptionOutcomesSummary> getConceptionOutcomeSummary(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getConceptionOutcomeSummary(patientId);
	}

	@Override
	public String getEDD(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getEDD(patientId);
	}

	@Override
	public List<ORMGetPatientConsultSummary> getConsultSummary(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getConsultSummary(patientId);
	}

	@Override
	public List<ORMGetPatientReferralSummary> getReferralSummary(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getReferralSummary(patientId);
	}
	
	@Override
	public List<ORMGetCommunication> getCommunications(Long patient_id) {
		// TODO Auto-generated method stub
		return patientDAO.getCommunications(patient_id);
	}
	
		
	@Override
	public List<ORMGetPatientInjuery> getPatInjury(Long patient_id) {
		// TODO Auto-generated method stub
		return patientDAO.getPatInjury(patient_id);
	}
	
	@Override
	public List<ORMGetAttorneyInfo> getFirmList(String value){
		return patientDAO.getFirmList(value);
	}
		
//	@Override
//	public List<ORMInsuranceSearch> getInsList(String value){
//		return patientDAO.getInsList(value);
//	}
	
	@Override
	public Long saveEditPatInjury(ORMPatInjury obj) {
		// TODO Auto-generated method stub
		return patientDAO.saveEditPatInjury(obj);
	}

	@Override
	public List<ORMGetPatientDocument> getPatientDocuments(String patient_id,String practice_id) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientDocuments(patient_id,practice_id);
	}

	@Override
	public ORMHeaderVitals getPatientHeaderVitals(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientHeaderVitals(patientId);
	}

	@Override
	public List<ORMPatientConfirmDel> confirmPatientDel(String patient_id) {
		// TODO Auto-generated method stub
		return patientDAO.confirmPatientDel(patient_id);
	}

	@Override
	public List<ORMGetPatientInsurance> getPatientInsurance(Long patientId,String status) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientInsurance(patientId,status);
	}

	@Override
	public List<ORMGetPatientRaceEthnicity> getPatientRaceEthnicty(String category,
			Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientRaceEthnicty(category,patientId);
	}

	@Override
	public List<ORMGetPatientNextOfKin> getPatientNextOfKin(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientNextOfKin(patientId);
	}

	/*
	 * @Override public ORMGetPatImmRegInfoDisplay getPatientImmRegInfo(Long
	 * patientId) { // TODO Auto-generated method stub return
	 * patientDAO.getPatientImmRegInfo(patientId); }
	 */

	@Override
	public List<ORMGetPatientCareTeamSummary> getPatientCareTeamSummary(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientCareTeamSummary(patientId);
	}	

	@Override
	public List<ORMGetPatientCareTeams> getPatientCareTeams(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientCareTeams(patientId);
	}

	@Override
	public List<ORMGetPatientCareTeamMember> getPatientCareTeamMembers(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientCareTeamMembers(patientId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> SavePatient(Wrapper_PatientSave objPatientWrapper, MultipartFile picData) {
		// TODO Auto-generated method stub
		return patientDAO.SavePatient(objPatientWrapper,picData);
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePatientNotes(ORMPatientNotesSave ormPatientNotesSave) {
		// TODO Auto-generated method stub
		return patientDAO.savePatientNotes(ormPatientNotesSave);
	}

	@Override
	public List<ORMGetPatientInsuranceView> getPatientInsuranceView(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientInsuranceView(patientId);
	}
	@Override
	public Long saveeditCorrespondence(ORMSaveDocument patDoc, MultipartFile docFile,String docCategory,ORMPatientCommunication communication) {
		// TODO Auto-generated method stub
		return patientDAO.saveeditCorrespondence(patDoc,docFile,docCategory, communication);
	}
	@Override
	public Long savePatientConsultant(ORMSaveDocument patDoc, MultipartFile docFile,String docCategory,ORMPatientConsultant consult) {
		// TODO Auto-generated method stub
		return patientDAO.savePatientConsultant(patDoc,docFile,docCategory, consult);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveOpenedPatient(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		return patientDAO.saveOpenedPatient(lstKeyValue);
	}
	@Override
	public List<ORM_getHealthInfoCapture> getHealthInfoCapture(Long patient_id,Long practice_id) {
		// TODO Auto-generated method stub
		return patientDAO.getHealthInfoCapture(patient_id,practice_id);
	}
	/*@Override
	public List<ORM_HealthInformationCaptureAttachments> getHealthInfoCaptureAttach(String health_info_id,String recType) {
		// TODO Auto-generated method stub
		return patientDAO.getHealthInfoCaptureAttach(health_info_id,recType);
	}
	@Override
	public List<ORM_HealthInformationCaptureAttachments> getHealthInfoCaptureLinks(String health_info_id,String recType) {
		// TODO Auto-generated method stub
		return patientDAO.getHealthInfoCaptureLinks(health_info_id,recType);
	}*/
	@Override
	public List<ORM_HealthInformationCaptureAttachments> getHealthInfoCaptureAttach(SearchCriteria searchCriteria) {
		return patientDAO.getHealthInfoCaptureAttach(searchCriteria);
	}
	@Override
	public List<ORM_HealthInformationCaptureAttachments> getHealthInfoCaptureLinks(SearchCriteria searchCriteria) {
		return patientDAO.getHealthInfoCaptureLinks(searchCriteria);
	}
	/*@Override
	public ServiceResponse AddEditNewHealthInformation(WrapperPatientInfoCapture WrapperPatientInfoCapture) {
		return patientDAO.AddEditNewHealthInformation(WrapperPatientInfoCapture);
	}*/
/*	@Override
	public ServiceResponse<ORMKeyValue> AddEditNewHealthInformation(MultipartFile[] attachfile, ORM_HealthInformationCaptureAttachments infoattachlinks, ORMHealthInformationCapture infocapture, String attachpath) {
		// TODO Auto-generated method stub
		return patientDAO.AddEditNewHealthInformation(attachfile, infoattachlinks, infocapture, attachpath);
	}*/
	@Override
	public ServiceResponse<ORMKeyValue> AddEditNewHealthInformation(WrapperPatientInfoCapture objInfoCaptureWrapper, MultipartFile[] attachfile) {
		return patientDAO.AddEditNewHealthInformation(objInfoCaptureWrapper,attachfile);
	}

	@Override
	public List<ORMGetPatientCheckInInfo> getPatientCheckedInInfo(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientCheckedInInfo(patientId);
	}
	@Override
	public List<ORMGetPatientNote> getPatientNotes(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientNotes(patientId);
	}

//	@Override
//	public Long qrdaImport(String provider_id, String practice_id, MultipartFile docFile) {
//		// TODO Auto-generated method stub
//		return patientDAO.qrdaImport(provider_id,practice_id,docFile);
//	}

	@Override
	public ORMGetPatientScannedCards getPatientScannedCards(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientScannedCards(patientId);
	}

	@Override
	public List<ORMAPIUsersGet> getAPIUsers(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return patientDAO.getAPIUsers(searchCriteria);
	}

	@Override
	public List<ORMAPIUserPatientGet> getAPIUserPatients(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return patientDAO.getAPIUserPatients(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveAPIUser(Wrapper_APIUserSave wrapper_APIUserSave) {
		// TODO Auto-generated method stub
		return patientDAO.saveAPIUser(wrapper_APIUserSave);
	}

	@Override
	public List<ORMPatientInsurancesForMergeGet> getPatientInsuranceToMerge(String patientIds) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientInsuranceToMerge(patientIds);
	}

	@Override
	public ServiceResponse<ORMKeyValue> mergePatients(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		return patientDAO.mergePatients(lstKeyValue);
	}
	@Override
	public Long addEditDocCategory(ORMDocCategories obj) {
		return patientDAO.addEditDocCategory(obj);
	}

	@Override
	public List<ORMGetPatientNote> getStaffNoteAlert(Long patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getStaffNoteAlert(patientId);
	}

	@Override
	public List<ORMTwoColumnGeneric> getPatientTaskData(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientTaskData(searchCriteria);
	}
	@Override
	public int deleteDocumentCategory(ORMDocCategories obj) {
		return patientDAO.deleteDocumentCategory(obj);
	}

	@Override
	public Long checkIfPatientExists(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return patientDAO.checkIfPatientExists(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePatientPic(SearchCriteria searchCriteria, MultipartFile picData) {
		// TODO Auto-generated method stub
		return patientDAO.savePatientPic(searchCriteria,picData);
	}

	@Override
	public List<ORMGetPhrUsersNames> getPatientPhrUsersNames(String practiceId, String patientId) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientPhrUsersNames(practiceId,patientId);
	}
	
}
