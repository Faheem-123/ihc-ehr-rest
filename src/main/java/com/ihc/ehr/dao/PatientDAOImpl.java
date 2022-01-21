package com.ihc.ehr.dao;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.MAPIDGenerator;
import com.ihc.ehr.model.ORMAPIUserPatientGet;
import com.ihc.ehr.model.ORMAPIUserPatientSave;
import com.ihc.ehr.model.ORMAPIUserSave;
import com.ihc.ehr.model.ORMAPIUsersGet;
import com.ihc.ehr.model.ORMDocCategories;
import com.ihc.ehr.model.ORMDocumentPath;
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
import com.ihc.ehr.model.ORMHealthInformationCapture;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatInjury;
import com.ihc.ehr.model.ORMPatientCommunication;
import com.ihc.ehr.model.ORMPatientConfirmDel;
import com.ihc.ehr.model.ORMPatientConsultant;
import com.ihc.ehr.model.ORMPatientInsurancesForMergeGet;
import com.ihc.ehr.model.ORMPatientNotesSave;
import com.ihc.ehr.model.ORMPatient_Followup;
import com.ihc.ehr.model.ORMSaveDocument;
import com.ihc.ehr.model.ORMSavePatient;
import com.ihc.ehr.model.ORMSavePatientCareTeam;
import com.ihc.ehr.model.ORMSavePatientCareTeamMember;
import com.ihc.ehr.model.ORMSavePatientInsurance;
import com.ihc.ehr.model.ORMSavePatientNextOfKin;
import com.ihc.ehr.model.ORMSavePatientRaceEthnicity;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORM_HealthInformationCaptureAttachments;
import com.ihc.ehr.model.ORM_getHealthInfoCapture;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.WrapperPatientInfoCapture;
import com.ihc.ehr.model.Wrapper_APIUserSave;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.model.Wrapper_PatientSave;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;
import com.ihc.ehr.util.EnumUtil;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.FileUtil;
import com.ihc.ehr.util.GeneralOperations;

@Repository
public class PatientDAOImpl implements PatientDAO {

	@Autowired
	private DBOperations db;

	@Autowired
	private GeneralDAOImpl generalDAOImpl;

	@Autowired
	private UserDAOImpl userDAOImpl;
	@Autowired
	GeneralService generalService;

	@Override
	public ORMGetPatient getPatientByID(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatient> lstPatients = db.getStoreProcedureData("getPatientInfo", ORMGetPatient.class, lstParam);

		if (lstPatients != null && lstPatients.size() > 0) {
			return (ORMGetPatient) lstPatients.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ORMGetPatientHeaderInfo getPatientHeaderInfo(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientHeaderInfo> lstPatients = db.getStoreProcedureData("spGetPatientHeaderInfo",
				ORMGetPatientHeaderInfo.class, lstParam);

		if (lstPatients != null && lstPatients.size() > 0) {
			return (ORMGetPatientHeaderInfo) lstPatients.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMGetPatientVitalGraphData> getVitalGraphData(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientVitalGraphData> lstGraphData = db.getStoreProcedureData("spgetVitalGraphData",
				ORMGetPatientVitalGraphData.class, lstParam);

		return lstGraphData;
	}

	@Override
	public List<ORMGetPatientMedicationSummary> getMedicationSummary(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientMedicationSummary> lstMedication = db.getStoreProcedureData("spGetPatientPrescriptionSummary",
				ORMGetPatientMedicationSummary.class, lstParam);

		return lstMedication;
	}

	@Override
	public List<ORMGetPatientAllergiesSummary> getAllergiesSummary(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientAllergiesSummary> lstAllergies = db.getStoreProcedureData("spGetPatientAllergiesSummary",
				ORMGetPatientAllergiesSummary.class, lstParam);

		return lstAllergies;
	}

	@Override
	public List<ORMGetPatientProblemsSummary> getProblemsSummary(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientProblemsSummary> lstProblems = db.getStoreProcedureData("spGetPaientProblemSummary",
				ORMGetPatientProblemsSummary.class, lstParam);

		return lstProblems;
	}

	@Override
	public List<ORMGetPatientAssessmentsSummary> getAssessmentsSummary(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientAssessmentsSummary> lstProblems = db.getStoreProcedureData("spGetPaientAssessmentSummary",
				ORMGetPatientAssessmentsSummary.class, lstParam);

		return lstProblems;
	}

	@Override
	public List<ORMGetPatientSuregeriesProceduresSummary> getSurgeriesProceduresSummary(Long patientId,
			String entry_type) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("type", entry_type.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientSuregeriesProceduresSummary> lstSurgeriesProcedures = db.getStoreProcedureData(
				"spGetSuregeriesProceduresSummary", ORMGetPatientSuregeriesProceduresSummary.class, lstParam);

		return lstSurgeriesProcedures;
	}

	@Override
	public List<ORMGetPatientHealthMaintenanceSummary> getHealthMaintenanceSummary(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientHealthMaintenanceSummary> lstHM = db.getStoreProcedureData("spGetHealthMaintenanceSummary",
				ORMGetPatientHealthMaintenanceSummary.class, lstParam);

		return lstHM;
	}

	@Override
	public List<ORMGetPatientConceptionOutcomesSummary> getConceptionOutcomeSummary(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientConceptionOutcomesSummary> lstHM = db.getStoreProcedureData("spGetConceptionOutcomesSummary",
				ORMGetPatientConceptionOutcomesSummary.class, lstParam);

		return lstHM;
	}

	@Override
	public String getEDD(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<MAPIDGenerator> lst = db.getStoreProcedureData("spGetGYNPatientEDD", MAPIDGenerator.class, lstParam);

		String edd = "";

		if (!lst.isEmpty()) {
			edd = lst.get(0).getId();
		}

		return edd;
	}

	@Override
	public List<ORMGetPatientConsultSummary> getConsultSummary(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientConsultSummary> lst = db.getStoreProcedureData("spGetPatinetConsults",
				ORMGetPatientConsultSummary.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMGetPatientReferralSummary> getReferralSummary(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientReferralSummary> lst = db.getStoreProcedureData("spGetPatientSummaryReferrals",
				ORMGetPatientReferralSummary.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMThreeColum> getSpeciality(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("Practice_ID", practice_id.toString(), String.class, ParameterMode.IN));

		List<ORMThreeColum> lstHM = db.getStoreProcedureData("spGetSpecialty", ORMThreeColum.class, lstParam);

		return lstHM;
	}

	@Override
	public List<ORMGetPatient_consultant> getPatientConsultant(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("PatientID", patient_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatient_consultant> lstHM = db.getStoreProcedureData("spGetPatientConsultant",
				ORMGetPatient_consultant.class, lstParam);

		return lstHM;
	}

	/*
	 * @Override public Long savePatientConsultant(ORMPatientConsultant obj) { //
	 * TODO Auto-generated method stub if (obj.getConsultation_id() != null &&
	 * obj.getConsultation_id() != 0) { if (db.SaveEntity(obj, Operation.EDIT) > 0)
	 * return obj.getConsultation_id(); else return (long) 0; } else {
	 * obj.setConsultation_id(db.IDGenerator("patient_consultant",
	 * obj.getPractice_id()));
	 * 
	 * if (db.SaveEntity(obj, Operation.ADD) > 0) return obj.getConsultation_id();
	 * else return (long) 0; } }
	 */

	//
	@Override
	public List<ORMGetCommunication> getCommunications(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("PatientID", patient_id.toString(), String.class, ParameterMode.IN));
		List<ORMGetCommunication> obj = db.getStoreProcedureData("spGetCommunication", ORMGetCommunication.class,
				lstParam);
		return obj;
	}

	//
	@Override
	public List<ORMGetPatientInjuery> getPatInjury(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		List<ORMGetPatientInjuery> obj = db.getStoreProcedureData("spGetPatientInjuery", ORMGetPatientInjuery.class,
				lstParam);
		return obj;
	}

	// public List<SearchCriteria> getFirmList(String value); List<SearchCriteria>
	// getFirmList(SearchCriteria searchCriteria);
	@Override
	public List<ORMGetAttorneyInfo> getFirmList(String value) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("value", value.toString(), String.class, ParameterMode.IN));
		List<ORMGetAttorneyInfo> obj = db.getStoreProcedureData("spGetAttorney_new", ORMGetAttorneyInfo.class,
				lstParam);
		return obj;
	}

	//
	// @Override
	// public List<ORMInsuranceSearch> getInsList(String value) {
	// List<SpParameters> lstParam = new ArrayList<>();
	// lstParam.add(new SpParameters("value", value.toString(), String.class,
	// ParameterMode.IN));
	// List<ORMInsuranceSearch> obj =
	// db.getStoreProcedureData("spGetInsuranceSearch_new",
	// ORMInsuranceSearch.class,
	// lstParam);
	// return obj;
	// }

	// saveEditPatInjury(ORMPatInjury
	@Override
	public Long saveEditPatInjury(ORMPatInjury obj) {
		try {
			// TODO Auto-generated method stub
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			obj.setDate_modified(dateFormat.format(date));
			if (obj.getInjury_id() != null && obj.getInjury_id() != 0) {
				if (db.SaveEntity(obj, Operation.EDIT) > 0)
					return obj.getInjury_id();
				else
					return (long) 0;
			} else {
				obj.setInjury_id(db.IDGenerator("patient_injury", obj.getPractice_id()));
				obj.setDate_created(dateFormat.format(date));
				if (db.SaveEntity(obj, Operation.ADD) > 0)
					return obj.getInjury_id();
				else
					return (long) 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return (long) 0;
		}
	}

	@Override
	public List<ORMGetPatientDocument> getPatientDocuments(String patient_id, String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		List<ORMGetPatientDocument> obj = db.getStoreProcedureData("spGetPatientDocuments", ORMGetPatientDocument.class,
				lstParam);
		return obj;
	}

	@Override
	public ORMHeaderVitals getPatientHeaderVitals(Long patientId) {

		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMHeaderVitals> lstPatients = db.getStoreProcedureData("spGetPatientHeaderVitals", ORMHeaderVitals.class,
				lstParam);

		if (lstPatients != null && lstPatients.size() > 0) {
			return (ORMHeaderVitals) lstPatients.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMPatientConfirmDel> confirmPatientDel(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		List<ORMPatientConfirmDel> lstPatients = db.getStoreProcedureData("spPatientDel", ORMPatientConfirmDel.class,
				lstParam);

		return lstPatients;
	}

	@Override
	public List<ORMGetPatientInsurance> getPatientInsurance(Long patientId, String status) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("status", status.toString(), String.class, ParameterMode.IN));

		// return db.getStoreProcedureData("spGetPatientInsurance",
		// ORMGetPatientInsurance.class, lstParam);
		return db.getStoreProcedureData("spGetPatientInsuranceNew", ORMGetPatientInsurance.class, lstParam);

	}

	@Override
	public List<ORMGetPatientRaceEthnicity> getPatientRaceEthnicty(String category, Long patientId) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		if (category.equalsIgnoreCase("ALL")) {
			lstParam.add(new SpParameters("entry_type", "", String.class, ParameterMode.IN));
			return db.getStoreProcedureData("spGetPatientRaceEthnicity", ORMGetPatientRaceEthnicity.class, lstParam);
		}
		if (category.equalsIgnoreCase("RACE") || category.equalsIgnoreCase("ETHNICITY")) {
			lstParam.add(new SpParameters("entry_type", category.toString(), String.class, ParameterMode.IN));
			return db.getStoreProcedureData("spGetPatientRaceEthnicity", ORMGetPatientRaceEthnicity.class, lstParam);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMGetPatientNextOfKin> getPatientNextOfKin(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("getPatientNextOfKin", ORMGetPatientNextOfKin.class, lstParam);
	}

	/*
	 * @Override public ORMGetPatImmRegInfoDisplay getPatientImmRegInfo(Long
	 * patientId) { // TODO Auto-generated method stub List<SpParameters> lstParam =
	 * new ArrayList<>(); lstParam.add(new SpParameters("patient_id",
	 * patientId.toString(), String.class, ParameterMode.IN));
	 * 
	 * List<ORMGetPatImmRegInfoDisplay> lst =
	 * db.getStoreProcedureData("getPatImmunizationRegInfoDisplay",
	 * ORMGetPatImmRegInfoDisplay.class, lstParam);
	 * 
	 * if (lst != null && lst.size() > 0) { return (ORMGetPatImmRegInfoDisplay)
	 * lst.get(0); } else { return null; }
	 * 
	 * }
	 */

	@Override
	public List<ORMGetPatientCareTeamSummary> getPatientCareTeamSummary(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPatientCareTeamSummary", ORMGetPatientCareTeamSummary.class, lstParam);
	}

	@Override
	public List<ORMGetPatientCareTeams> getPatientCareTeams(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPatientCareTeams", ORMGetPatientCareTeams.class, lstParam);

	}

	@Override
	public List<ORMGetPatientCareTeamMember> getPatientCareTeamMembers(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPatientCareTeamMembers", ORMGetPatientCareTeamMember.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> SavePatient(Wrapper_PatientSave objPatientWrapper, MultipartFile picData) {
		// TODO Auto-generated method stub

		// check if patient already exists in case of new patient
		Long alreadyEixtPatientId = (long) 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (objPatientWrapper.getPatient() != null && (objPatientWrapper.getPatient().getPatient_id() == null
				|| objPatientWrapper.getPatient().getPatient_id() <= 0)) {

			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setPractice_id(objPatientWrapper.getPatient().getPractice_id());

			List<SearchCriteriaParamList> param_list = new ArrayList<>();
			param_list
					.add(new SearchCriteriaParamList("last_name", objPatientWrapper.getPatient().getLast_name(), null));
			param_list.add(
					new SearchCriteriaParamList("first_name", objPatientWrapper.getPatient().getFirst_name(), null));
			param_list.add(new SearchCriteriaParamList("dob", objPatientWrapper.getPatient().getDob(), null));
			param_list.add(new SearchCriteriaParamList("ssn", objPatientWrapper.getPatient().getSsn(), null));

			searchCriteria.setParam_list(param_list);

			alreadyEixtPatientId = checkIfPatientExists(searchCriteria);

			if (alreadyEixtPatientId > 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("Patient Already Exist.");
				resp.setResult(alreadyEixtPatientId.toString());
				return resp;
			}

			// return resp;
		}

		Long patientId = objPatientWrapper.getPatient_id();
		String alternatePID = "";
		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		// PATIENT
		if (objPatientWrapper.getPatient() != null || (picData != null && !picData.isEmpty())) {
			// Patient Picture
			String directory = "";
			String originalFilename = "";
			alternatePID = objPatientWrapper.getPatient().getAlternate_account();

			if (picData != null && !picData.isEmpty()) {
				try {

					ORMDocumentPath objPath = generalDAOImpl
							.getDocumentPathByCategory(objPatientWrapper.getPractice_id(), "PatientImages");

					if (objPath != null) {
						directory = objPath.getUpload_path();
					}

					directory = directory + "\\" + objPatientWrapper.getPractice_id() + "\\PatientImages\\";
					originalFilename = picData.getOriginalFilename();

				} catch (Exception e) {
					System.out.println("Picture Upload Error:" + e.getMessage());
				}
			}
			// end Patient Picture

			ORMSavePatient ormSavePatient = objPatientWrapper.getPatient();
			ormSavePatient.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (ormSavePatient.getPatient_id() == null || ormSavePatient.getPatient_id() <= 0) {

				patientId = db.IDGenerator("patient", objPatientWrapper.getPractice_id());
				alternatePID = db.PIDGenerator(objPatientWrapper.getPractice_id());

				ormSavePatient.setPatient_id(patientId);
				ormSavePatient.setAlternate_account(alternatePID);
				ormSavePatient.setDate_created(DateTimeUtil.getCurrentDateTime());

				if (picData != null && !picData.isEmpty()) {
					try {

						String fileName = patientId + "." + FilenameUtils.getExtension(originalFilename);
						File destinationFile = new File(GeneralOperations.CheckPath(directory), fileName);
						picData.transferTo(destinationFile);
						ormSavePatient.setPic(fileName);

					} catch (Exception e) {
						System.out.println("Picture Upload Error:" + e.getMessage());
					}
				}

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormSavePatient));

			} else {
				patientId = ormSavePatient.getPatient_id();

				if (picData != null && !picData.isEmpty()) {
					try {

						String fileName = patientId + "." + FilenameUtils.getExtension(originalFilename);
						File destinationFile = new File(GeneralOperations.CheckPath(directory), fileName);
						picData.transferTo(destinationFile);
						ormSavePatient.setPic(fileName);

					} catch (Exception e) {
						System.out.println("Picture Upload Error:" + e.getMessage());
					}
				}

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormSavePatient));
			}

		}
		// end PATIENT

		// PATIENT INSURANCE
		if (objPatientWrapper.getLst_patient_insurance() != null) {
			for (ORMSavePatientInsurance ormSavePatientInsurance : objPatientWrapper.getLst_patient_insurance()) {
				ormSavePatientInsurance.setDate_modified(DateTimeUtil.getCurrentDateTime());
				if (ormSavePatientInsurance.getPatientinsurance_id() == null
						|| ormSavePatientInsurance.getPatientinsurance_id() <= 0) {

					ormSavePatientInsurance.setPatientinsurance_id(
							db.IDGenerator("patient_insurance", objPatientWrapper.getPractice_id()));
					ormSavePatientInsurance.setDate_created(DateTimeUtil.getCurrentDateTime());

					if (ormSavePatientInsurance.getPatient_id() == null
							|| ormSavePatientInsurance.getPatient_id() <= 0) {
						ormSavePatientInsurance.setPatient_id(patientId);
					}
					lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormSavePatientInsurance));
				} else {

					if (ormSavePatientInsurance.getPatient_id() == null
							|| ormSavePatientInsurance.getPatient_id() <= 0) {
						ormSavePatientInsurance.setPatient_id(patientId);
					}
					lstEntityWrapper
							.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormSavePatientInsurance));
				}
			}

		}
		// end PATIENT INSURANCE

		// PATIENT RACE ETHNICITY
		if (objPatientWrapper.getLst_race_ethnicity() != null) {
			for (ORMSavePatientRaceEthnicity ormSavePatientRaceEthnicity : objPatientWrapper.getLst_race_ethnicity()) {
				ormSavePatientRaceEthnicity.setDate_modified(DateTimeUtil.getCurrentDateTime());
				if (ormSavePatientRaceEthnicity.getId() == null || ormSavePatientRaceEthnicity.getId() <= 0) {

					ormSavePatientRaceEthnicity
							.setId(db.IDGenerator("patient_race_ethnicity", objPatientWrapper.getPractice_id()));
					ormSavePatientRaceEthnicity.setDate_created(DateTimeUtil.getCurrentDateTime());

					if (ormSavePatientRaceEthnicity.getPatient_id() == null
							|| ormSavePatientRaceEthnicity.getPatient_id() <= 0) {
						ormSavePatientRaceEthnicity.setPatient_id(patientId);
					}
					lstEntityWrapper
							.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormSavePatientRaceEthnicity));
				} else {

					if (ormSavePatientRaceEthnicity.getPatient_id() == null
							|| ormSavePatientRaceEthnicity.getPatient_id() <= 0) {
						ormSavePatientRaceEthnicity.setPatient_id(patientId);
					}
					lstEntityWrapper
							.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormSavePatientRaceEthnicity));
				}
			}

		}
		// END PATIENT RACE ETHNICITY

		// PATIENT NEXT OF KIN
		if (objPatientWrapper.getLst_next_of_kin() != null) {
			for (ORMSavePatientNextOfKin ormSavePatientNextOfKin : objPatientWrapper.getLst_next_of_kin()) {
				ormSavePatientNextOfKin.setDate_modified(DateTimeUtil.getCurrentDateTime());
				if (ormSavePatientNextOfKin.getNext_of_kin_id() == null
						|| ormSavePatientNextOfKin.getNext_of_kin_id() <= 0) {

					ormSavePatientNextOfKin.setNext_of_kin_id(
							db.IDGenerator("patient_next_of_kin", objPatientWrapper.getPractice_id()));
					ormSavePatientNextOfKin.setDate_created(DateTimeUtil.getCurrentDateTime());

					if (ormSavePatientNextOfKin.getPatient_id() == null
							|| ormSavePatientNextOfKin.getPatient_id() <= 0) {
						ormSavePatientNextOfKin.setPatient_id(patientId);
					}
					lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormSavePatientNextOfKin));
				} else {

					if (ormSavePatientNextOfKin.getPatient_id() == null
							|| ormSavePatientNextOfKin.getPatient_id() <= 0) {
						ormSavePatientNextOfKin.setPatient_id(patientId);
					}
					lstEntityWrapper
							.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormSavePatientNextOfKin));
				}
			}
		}

		// END PATIENT NEXT OF KIN

		// PATIENT CARRE TEAM
		if (objPatientWrapper.getLst_care_team() != null) {

			// New Team
			for (ORMSavePatientCareTeam ormSavePatientCareTeam : objPatientWrapper.getLst_care_team()) {
				Long team_id;

				Long team_id_temp = ormSavePatientCareTeam.getTeam_id();

				ormSavePatientCareTeam.setDate_modified(DateTimeUtil.getCurrentDateTime());
				if (ormSavePatientCareTeam.getPatient_id() == null || ormSavePatientCareTeam.getPatient_id() <= 0) {
					ormSavePatientCareTeam.setPatient_id(patientId);
				}

				if (ormSavePatientCareTeam.getTeam_id() == null || ormSavePatientCareTeam.getTeam_id() <= 0) {
					team_id = db.IDGenerator("patient_care_team", objPatientWrapper.getPractice_id());
					ormSavePatientCareTeam.setTeam_id(team_id);
					ormSavePatientCareTeam.setDate_created(DateTimeUtil.getCurrentDateTime());
					lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormSavePatientCareTeam));

					// PATIENT CARRE TEAM MEMBER FOR NEW TEAM
					for (ORMSavePatientCareTeamMember ormSavePatientCareTeamMember : objPatientWrapper
							.getLst_care_team_member()) {

						if (ormSavePatientCareTeamMember.getTeam_id().equals(team_id_temp)) {

							ormSavePatientCareTeamMember.setTeam_id(team_id);
							ormSavePatientCareTeamMember.setDate_modified(DateTimeUtil.getCurrentDateTime());

							ormSavePatientCareTeamMember
									.setId(db.IDGenerator("care_team_member", objPatientWrapper.getPractice_id()));
							ormSavePatientCareTeamMember.setDate_created(DateTimeUtil.getCurrentDateTime());

							if (ormSavePatientCareTeamMember.getPatient_id() == null
									|| ormSavePatientCareTeamMember.getPatient_id() <= 0) {
								ormSavePatientCareTeamMember.setPatient_id(patientId);
							}

							lstEntityWrapper.add(
									new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormSavePatientCareTeamMember));
						}
					}
					// END PATIENT CARRE TEAM MEMBER FOR NEW TEAM

				} else {
					team_id = ormSavePatientCareTeam.getTeam_id();
					lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormSavePatientCareTeam));
				}
			}
			// end New Team

		}
		// END PATIENT CARRE TEAM

		// PATIENT CARRE TEAM MEMBER FOR EXISTING TEAM
		if (objPatientWrapper.getLst_care_team_member() != null) {
			for (ORMSavePatientCareTeamMember ormSavePatientCareTeamMember : objPatientWrapper
					.getLst_care_team_member()) {

				if (ormSavePatientCareTeamMember.getTeam_id() != null
						&& ormSavePatientCareTeamMember.getTeam_id() > 0) {

					if (ormSavePatientCareTeamMember.getPatient_id() == null
							|| ormSavePatientCareTeamMember.getPatient_id() <= 0) {
						ormSavePatientCareTeamMember.setPatient_id(patientId);
					}

					ormSavePatientCareTeamMember.setDate_modified(DateTimeUtil.getCurrentDateTime());

					if (ormSavePatientCareTeamMember.getId() == null || ormSavePatientCareTeamMember.getId() <= 0) {

						ormSavePatientCareTeamMember
								.setId(db.IDGenerator("care_team_member", objPatientWrapper.getPractice_id()));
						ormSavePatientCareTeamMember.setDate_created(DateTimeUtil.getCurrentDateTime());

						lstEntityWrapper.add(
								new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormSavePatientCareTeamMember));
					} else {
						lstEntityWrapper.add(
								new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormSavePatientCareTeamMember));
					}

				}
			}
		}
		// END PATIENT CARRE TEAM MEMBER FOR EXISTING TEAM

		// PATIENT IMMUNIZATION REGISTRY INFO
		/*
		 * if (objPatientWrapper.getImm_reg_info() != null) { ORMSavePatientImmRegInfo
		 * ormSavePatientImmRegInfo = objPatientWrapper.getImm_reg_info(); if
		 * (ormSavePatientImmRegInfo != null) {
		 * ormSavePatientImmRegInfo.setDate_modified(DateTimeUtil.getCurrentDateTime());
		 * if (ormSavePatientImmRegInfo.getPatient_id() == null ||
		 * ormSavePatientImmRegInfo.getPatient_id() <= 0) {
		 * ormSavePatientImmRegInfo.setPatient_id(patientId); } if
		 * (ormSavePatientImmRegInfo.getRegistry_info_id() == null ||
		 * ormSavePatientImmRegInfo.getRegistry_info_id() <= 0) {
		 * ormSavePatientImmRegInfo.setRegistry_info_id(
		 * db.IDGenerator("patient_immunization_registry_info",
		 * objPatientWrapper.getPractice_id()));
		 * ormSavePatientImmRegInfo.setDate_created(DateTimeUtil.getCurrentDateTime());
		 * 
		 * lstEntityWrapper .add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD,
		 * ormSavePatientImmRegInfo)); } else { lstEntityWrapper .add(new
		 * Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormSavePatientImmRegInfo));
		 * } } }
		 */
		// end IMMUNIZATION REGISTRY INFO

		String query = "";
		if (objPatientWrapper.getLst_deleted_ids() != null) {

			for (ORMKeyValue ormKV : objPatientWrapper.getLst_deleted_ids()) {

				switch (ormKV.getKey()) {
				case "patient_race_ethnicity":
					query = " update patient_race_ethnicity set deleted=1,date_modified=getdate(),modified_user='"
							+ objPatientWrapper.getLoged_in_user() + "',client_date_modified='"
							+ objPatientWrapper.getClient_date() + "',system_ip='" + objPatientWrapper.getSystem_ip()
							+ "' where id in (" + ormKV.getValue() + ") ";

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

					break;
				case "patient_insurance":
					query = " update patient_insurance set deleted=1,date_modified=getdate(),modified_user='"
							+ objPatientWrapper.getLoged_in_user() + "',client_date_modified='"
							+ objPatientWrapper.getClient_date() + "',system_ip='" + objPatientWrapper.getSystem_ip()
							+ "' where patientinsurance_id in (" + ormKV.getValue() + ") ";

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));
					break;
				case "patient_next_of_kin":
					query = " update patient_next_of_kin set deleted=1,date_modified=getdate(),modified_user='"
							+ objPatientWrapper.getLoged_in_user() + "',client_date_modified='"
							+ objPatientWrapper.getClient_date() + "',system_ip='" + objPatientWrapper.getSystem_ip()
							+ "' where next_of_kin_id in (" + ormKV.getValue() + ") ";

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

					break;
				case "patient_care_team":
					query = " update patient_care_team set deleted=1,date_modified=getdate(),modified_user='"
							+ objPatientWrapper.getLoged_in_user() + "',client_date_modified='"
							+ objPatientWrapper.getClient_date() + "',system_ip='" + objPatientWrapper.getSystem_ip()
							+ "' where team_id in (" + ormKV.getValue() + ") ";

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

				case "care_team_member":
					query = " update care_team_member set deleted=1,date_modified=getdate(),modified_user='"
							+ objPatientWrapper.getLoged_in_user() + "',client_date_modified='"
							+ objPatientWrapper.getClient_date() + "',system_ip='" + objPatientWrapper.getSystem_ip()
							+ "' where id in (" + ormKV.getValue() + ") ";

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

					break;

				default:
					break;
				}
			}
		}

		int result = db.AddUpdateEntityWrapper(lstEntityWrapper);

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(patientId.toString());
		}

		/*
		 * ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		 * 
		 * if (result == 0) { resp.setStatus(ServiceResponseStatus.ERROR);
		 * resp.setResponse("An Error Occured while saving record."); } else {
		 * resp.setStatus(ServiceResponseStatus.SUCCESS);
		 * resp.setResponse("Data has been saved successfully.");
		 * resp.setResult(patientId.toString()); }
		 */

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePatientNotes(ORMPatientNotesSave ormPatientNotesSave) {
		// TODO Auto-generated method stub
		int result = 0;

		ormPatientNotesSave.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (ormPatientNotesSave.getPatient_note_id() == null) {

			Long notesId = db.IDGenerator("patient_note", ormPatientNotesSave.getPractice_id());
			ormPatientNotesSave.setPatient_note_id(notesId);
			ormPatientNotesSave.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(ormPatientNotesSave, EnumUtil.Operation.ADD);

		} else {

			result = db.SaveEntity(ormPatientNotesSave, EnumUtil.Operation.EDIT);
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormPatientNotesSave.getPatient_note_id().toString());
		}

		return resp;
	}

	@Override
	public List<ORMGetPatientInsuranceView> getPatientInsuranceView(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPatientInsurance_View", ORMGetPatientInsuranceView.class, lstParam);

	}

	@Override
	public Long saveeditCorrespondence(ORMSaveDocument patDoc, MultipartFile docFile, String docCategory,
			ORMPatientCommunication communication) {
		// TODO Auto-generated method stub
		// System.out.println("Data Received: " + patDoc.toString());

		Long generatedId = null;
		Long doc_id = null;
		Long communication_id = null;

		if (docFile != null && !docFile.isEmpty()) {
			generatedId = generalDAOImpl.SaveDocument(patDoc, docFile, docCategory);
		}

		if (communication.getCommunication_id() != null && communication.getCommunication_id() != 0) {

			if (docFile != null) {
				doc_id = patDoc.getPatient_document_id();
				if (doc_id != null) {
					communication.setPatient_document_id(doc_id);
				}
			}

			communication.setModified_date(DateTimeUtil.getCurrentDateTime());

			if (db.SaveEntity(communication, Operation.EDIT) > 0)
				communication_id = communication.getCommunication_id();
			else
				communication_id = (long) 0;
		} else {
			communication.setCommunication_id(db.IDGenerator("patient_communication", communication.getPractice_id()));
			if (docFile != null) {
				doc_id = patDoc.getPatient_document_id();
				if (doc_id != null) {
					communication.setPatient_document_id(doc_id);
				}
			}
			communication.setDate_created(DateTimeUtil.getCurrentDateTime());

			if (db.SaveEntity(communication, Operation.ADD) > 0)
				communication_id = communication.getCommunication_id();
			else
				communication_id = (long) 0;
		}
		// }
		if (generatedId == null) {
			generatedId = Long.parseLong("0");
		}
		if (doc_id == null) {
			doc_id = Long.parseLong("0");
		}
		if (communication_id == null) {
			communication_id = Long.parseLong("0");
		}
		System.out.println("Return " + Long.toString(generatedId + '~' + doc_id + '~' + communication_id));
		return generatedId + '~' + doc_id;
	}

	@Override
	public Long savePatientConsultant(ORMSaveDocument patDoc, MultipartFile docFile, String docCategory,
			ORMPatientConsultant consult) {
		// TODO Auto-generated method stub
		// System.out.println("Data Received: " + patDoc.toString());

		Long generatedId = null;
		Long doc_id = null;
		Long consult_id = null;

		if (docFile != null && !docFile.isEmpty()) {
			generatedId = generalDAOImpl.SaveDocument(patDoc, docFile, docCategory);
		}

		if (consult.getConsultation_id() != null && consult.getConsultation_id() != 0) {

			if (docFile != null) {
				doc_id = patDoc.getPatient_document_id();
				if (doc_id != null) {
					consult.setPatient_document_id(doc_id);
				}
			}
			consult.setDate_modified(DateTimeUtil.getCurrentDateTime());
			System.out.println(patDoc.toString());
			if (db.SaveEntity(consult, Operation.EDIT) > 0)
				consult_id = consult.getPatient_document_id();
			else
				consult_id = (long) 0;
		} else {
			consult.setConsultation_id(db.IDGenerator("patient_consultant", consult.getPractice_id()));
			if (docFile != null) {
				doc_id = patDoc.getPatient_document_id();
				if (doc_id != null) {
					consult.setPatient_document_id(doc_id);
				}
			}
			consult.setDate_created(DateTimeUtil.getCurrentDateTime());

			if (db.SaveEntity(consult, Operation.ADD) > 0)
				consult_id = consult.getPatient_document_id();
			else
				consult_id = (long) 0;
		}
		// }
		if (generatedId == null) {
			generatedId = Long.parseLong("0");
		}
		if (doc_id == null) {
			doc_id = Long.parseLong("0");
		}
		if (consult_id == null) {
			consult_id = Long.parseLong("0");
		}
		System.out.println("Return " + Long.toString(generatedId + '~' + doc_id + '~' + consult_id));
		return generatedId + '~' + doc_id;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveOpenedPatient(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub

		// int result=0;
		// String practiceId;
		String patientId = "";
		// String userName;

		if (lstKeyValue != null) {
			List<SpParameters> lstParam = new ArrayList<>();
			for (ORMKeyValue ormKV : lstKeyValue) {

				switch (ormKV.getKey()) {
				case "practice_id":
					lstParam.add(new SpParameters("practice_id", ormKV.getValue().toString(), String.class,
							ParameterMode.IN));
					break;
				case "patient_id":
					lstParam.add(new SpParameters("patient_id", ormKV.getValue().toString(), String.class,
							ParameterMode.IN));
					break;
				case "user_name":
					lstParam.add(
							new SpParameters("user_name", ormKV.getValue().toString(), String.class, ParameterMode.IN));
					break;

				default:
					break;
				}
			}

			db.ExecuteUpdateStoreProcedure("spAddUserSearchedPatient", lstParam);

		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		// if (result == 0) {
		// resp.setStatus(ServiceResponseStatus.ERROR);
		// resp.setResponse("An Error Occured while saving record.");
		// } else {
		resp.setStatus(ServiceResponseStatus.SUCCESS);
		resp.setResponse("Data has been saved successfully.");
		resp.setResult(patientId);
		// }

		return resp;

	}

	@Override
	public List<ORM_getHealthInfoCapture> getHealthInfoCapture(Long patient_id, Long practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		List<ORM_getHealthInfoCapture> obj = db.getStoreProcedureData("spgetHealthInfoCapture",
				ORM_getHealthInfoCapture.class, lstParam);
		return obj;
	}

	@Override
	public List<ORM_HealthInformationCaptureAttachments> getHealthInfoCaptureLinks(SearchCriteria searchCriteria) {
		String health_info_id = "";
		String recType = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "health_info_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						health_info_id = pram.getValue();
						break;
					}
				case "recType":
					if (pram.getValue() != "" && pram.getValue() != null) {
						recType = pram.getValue();
						break;
					}
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("health_info_id", health_info_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("attach_type", recType, String.class, ParameterMode.IN));
		List<ORM_HealthInformationCaptureAttachments> lst = db.getStoreProcedureData(
				"spgetAttachLinksHealthInfoCapture", ORM_HealthInformationCaptureAttachments.class, lstParam);
		return lst;
	}

	@Override
	public List<ORM_HealthInformationCaptureAttachments> getHealthInfoCaptureAttach(SearchCriteria searchCriteria) {
		String health_info_id = "";
		String recType = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "health_info_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						health_info_id = pram.getValue();
						break;
					}
				case "recType":
					if (pram.getValue() != "" && pram.getValue() != null) {
						recType = pram.getValue();
						break;
					}
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("health_info_id", health_info_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("attach_type", recType, String.class, ParameterMode.IN));
		List<ORM_HealthInformationCaptureAttachments> lst = db.getStoreProcedureData(
				"spgetAttachLinksHealthInfoCapture", ORM_HealthInformationCaptureAttachments.class, lstParam);
		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> AddEditNewHealthInformation(WrapperPatientInfoCapture objInfoCaptureWrapper,
			MultipartFile[] attachfile) {
		// int result = 0;
		ORMHealthInformationCapture healthInfoCapture = objInfoCaptureWrapper.getObjHealthInfoCapture();
		List<ORM_HealthInformationCaptureAttachments> lstHealthInfoCaptureAttachments = objInfoCaptureWrapper
				.getArrHealthInfoAttachmentsLinks();
		// String path = objInfoCaptureWrapper.getPath();

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		// List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		Long generatedID;
		// String URL = "";
		String directory = "";
		String originalFilename = "";

		ORM_HealthInformationCaptureAttachments objAttachSave;

		if ("".equals(healthInfoCapture.getHealth_info_id())) {
			generatedID = db.IDGenerator("health_information_capture",
					Long.parseLong(healthInfoCapture.getPractice_id()));
			healthInfoCapture.setHealth_info_id(generatedID.toString());
			healthInfoCapture.setDate_created(DateTimeUtil.getCurrentDateTime());
			healthInfoCapture.setDate_modified(DateTimeUtil.getCurrentDateTime());

			db.SaveEntity(healthInfoCapture, Operation.ADD);
			// ***************************************************************
			if (lstHealthInfoCaptureAttachments != null) {

				BigDecimal newId = new BigDecimal(BigInteger.ZERO);

				for (ORM_HealthInformationCaptureAttachments objAttach : lstHealthInfoCaptureAttachments) {
					newId = new BigDecimal(db.IDGenerator("health_info_capture_attachment",
							Long.parseLong(healthInfoCapture.getPractice_id())));
					if ("file".equals(objAttach.getAttach_type().toLowerCase())) {
						objAttachSave = new ORM_HealthInformationCaptureAttachments();
						// HibernateTransactionUtil hibernate1 = HibernateTransactionUtil.getInstance();

						objAttachSave.setAttachment_id(newId.toString());
						objAttachSave.setPractice_id(objAttach.getPractice_id());
						objAttachSave.setPatient_id(objAttach.getPatient_id());
						objAttachSave.setDocument_date(DateTimeUtil.getCurrentDateTime());
						objAttachSave.setOriginal_file_name(objAttach.getOriginal_file_name());
						objAttachSave.setName(objAttach.getOriginal_file_name());
						objAttachSave.setAttach_type(objAttach.getAttach_type());

						objAttachSave.setHealth_info_id(generatedID.toString());
						objAttachSave.setDeleted(objAttach.getDeleted());
						objAttachSave.setCreated_user(objAttach.getCreated_user());
						objAttachSave.setModified_user(objAttach.getModified_user());
						objAttachSave.setClient_date_created(objAttach.getClient_date_created());
						objAttachSave.setClient_modified_date(objAttach.getClient_modified_date());

						objAttachSave.setDate_created(DateTimeUtil.getCurrentDateTime());
						objAttachSave.setModified_date(DateTimeUtil.getCurrentDateTime());

						String fileName = "";
						for (int i = 0; i < attachfile.length; i++) {
							// if(attachfile[i].getOriginalFilename() == objAttach.getOriginal_file_name())
							// {
							if (attachfile[i].getOriginalFilename().equals(objAttach.getOriginal_file_name())) {

								ORMDocumentPath objPath = generalDAOImpl.getDocumentPathByCategory(
										Long.parseLong(objAttach.getPractice_id()), "Messages");
								if (objPath != null) {
									directory = objPath.getUpload_path();
								}
								// directory = directory + "\\" + objAttach.getPractice_id() + "\\Messages\\" ;

								directory = FileUtil.GetYearMonthDayWisePath(directory, objAttach.getPractice_id(),
										"Messages");

								originalFilename = attachfile[i].getOriginalFilename();
								try {
									fileName = newId + GeneralOperations.GetDatetimeFileName() + "."
											+ FilenameUtils.getExtension(originalFilename);
									File destinationFile = new File(GeneralOperations.CheckPath(directory), fileName);
									attachfile[i].transferTo(destinationFile);
									objAttachSave.setLink(
											DateTimeUtil.getCurrentYear() + "\\" + DateTimeUtil.getCurrentMonth() + "\\"
													+ DateTimeUtil.getCurrentDay() + "\\" + fileName);

								} catch (Exception e) {
									System.out.println("Picture Upload Error:" + e.getMessage());
								}
								// lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD,
								// objAttachSave));

								db.SaveEntity(objAttachSave, Operation.ADD);
							}
						}

					} // if file end
					else if ("link".equals(objAttach.getAttach_type().toLowerCase())) {
						objAttachSave = new ORM_HealthInformationCaptureAttachments();
						// HibernateTransactionUtil hibernate1 = HibernateTransactionUtil.getInstance();
						objAttachSave.setAttachment_id(newId.toString());
						objAttachSave.setPractice_id(objAttach.getPractice_id());
						objAttachSave.setPatient_id(objAttach.getPatient_id());
						objAttachSave.setDocument_date(DateTimeUtil.getCurrentDateTime());

						objAttachSave.setOriginal_file_name(objAttach.getOriginal_file_name());
						objAttachSave.setName(objAttach.getOriginal_file_name());
						objAttachSave.setLink(objAttach.getLink());

						objAttachSave.setHealth_info_id(generatedID.toString());
						objAttachSave.setDeleted(objAttach.getDeleted());
						objAttachSave.setCreated_user(objAttach.getCreated_user());
						objAttachSave.setModified_user(objAttach.getModified_user());
						objAttachSave.setClient_date_created(objAttach.getClient_date_created());
						objAttachSave.setClient_modified_date(objAttach.getClient_modified_date());

						objAttachSave.setAttach_type(objAttach.getAttach_type());

						objAttachSave.setDate_created(DateTimeUtil.getCurrentDateTime());
						objAttachSave.setModified_date(DateTimeUtil.getCurrentDateTime());

						newId = newId.add(BigDecimal.ONE);

						// lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD,
						// objAttachSave));
						db.SaveEntity(objAttachSave, Operation.ADD);
					}
				}

			}
		} // if null
		else {

			healthInfoCapture.setDate_modified(DateTimeUtil.getCurrentDateTime());
			db.SaveEntity(healthInfoCapture, Operation.EDIT);
			// ***************************************************************
			if (lstHealthInfoCaptureAttachments != null) {

				BigDecimal newId = new BigDecimal(BigInteger.ZERO);
				for (ORM_HealthInformationCaptureAttachments objAttach : lstHealthInfoCaptureAttachments) {
					newId = new BigDecimal(db.IDGenerator("health_info_capture_attachment",
							Long.parseLong(healthInfoCapture.getPractice_id())));
					if ("file".equals(objAttach.getAttach_type().toLowerCase())) {
						objAttachSave = new ORM_HealthInformationCaptureAttachments();
						// HibernateTransactionUtil hibernate1 = HibernateTransactionUtil.getInstance();

						objAttachSave.setAttachment_id(newId.toString());
						objAttachSave.setPractice_id(objAttach.getPractice_id());
						objAttachSave.setPatient_id(objAttach.getPatient_id());
						objAttachSave.setDocument_date(DateTimeUtil.getCurrentDateTime());
						objAttachSave.setOriginal_file_name(objAttach.getOriginal_file_name());
						objAttachSave.setName(objAttach.getOriginal_file_name());
						objAttachSave.setAttach_type(objAttach.getAttach_type());

						objAttachSave.setHealth_info_id(objAttach.getHealth_info_id());
						objAttachSave.setDeleted(objAttach.getDeleted());
						objAttachSave.setCreated_user(objAttach.getCreated_user());
						objAttachSave.setModified_user(objAttach.getModified_user());
						objAttachSave.setClient_date_created(objAttach.getClient_date_created());
						objAttachSave.setClient_modified_date(objAttach.getClient_modified_date());

						objAttachSave.setDate_created(DateTimeUtil.getCurrentDateTime());
						objAttachSave.setModified_date(DateTimeUtil.getCurrentDateTime());

						String fileName = "";
						for (int i = 0; i < attachfile.length; i++) {
							if (attachfile[i].getOriginalFilename().equals(objAttach.getOriginal_file_name())) {

								ORMDocumentPath objPath = generalDAOImpl.getDocumentPathByCategory(
										Long.parseLong(objAttach.getPractice_id()), "Messages");
								if (objPath != null) {
									directory = objPath.getUpload_path();
								}

								directory = FileUtil.GetYearMonthDayWisePath(directory, objAttach.getPractice_id(),
										"Messages");

								originalFilename = attachfile[i].getOriginalFilename();
								try {
									fileName = newId + GeneralOperations.GetDatetimeFileName() + "."
											+ FilenameUtils.getExtension(originalFilename);
									File destinationFile = new File(GeneralOperations.CheckPath(directory), fileName);
									attachfile[i].transferTo(destinationFile);
									objAttachSave.setLink(
											DateTimeUtil.getCurrentYear() + "\\" + DateTimeUtil.getCurrentMonth() + "\\"
													+ DateTimeUtil.getCurrentDay() + "\\" + fileName);

								} catch (Exception e) {
									System.out.println("Picture Upload Error:" + e.getMessage());
								}

								db.SaveEntity(objAttachSave, Operation.EDIT);
							}
						}

					} // if file end
					else if ("link".equals(objAttach.getAttach_type().toLowerCase())) {
						objAttachSave = new ORM_HealthInformationCaptureAttachments();
						// HibernateTransactionUtil hibernate1 = HibernateTransactionUtil.getInstance();
						objAttachSave.setAttachment_id(newId.toString());
						objAttachSave.setPractice_id(objAttach.getPractice_id());
						objAttachSave.setPatient_id(objAttach.getPatient_id());
						objAttachSave.setDocument_date(DateTimeUtil.getCurrentDateTime());

						objAttachSave.setOriginal_file_name(objAttach.getOriginal_file_name());
						objAttachSave.setName(objAttach.getOriginal_file_name());
						objAttachSave.setLink(objAttach.getLink());

						objAttachSave.setHealth_info_id(objAttach.getHealth_info_id());
						objAttachSave.setDeleted(objAttach.getDeleted());
						objAttachSave.setCreated_user(objAttach.getCreated_user());
						objAttachSave.setModified_user(objAttach.getModified_user());
						objAttachSave.setClient_date_created(objAttach.getClient_date_created());
						objAttachSave.setClient_modified_date(objAttach.getClient_modified_date());

						objAttachSave.setAttach_type(objAttach.getAttach_type());

						objAttachSave.setDate_created(DateTimeUtil.getCurrentDateTime());
						objAttachSave.setModified_date(DateTimeUtil.getCurrentDateTime());

						newId = newId.add(BigDecimal.ONE);
						db.SaveEntity(objAttachSave, Operation.EDIT);
					}
				}
			}
		}

		return resp;
	}

	@Override
	public List<ORMGetPatientCheckInInfo> getPatientCheckedInInfo(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("appointmentDate",
				DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.MM_dd_yyyy).toString(), String.class,
				ParameterMode.IN));
		return db.getStoreProcedureData("Patient_CheckInInfo", ORMGetPatientCheckInInfo.class, lstParam);
	}

	@Override
	public List<ORMGetPatientNote> getPatientNotes(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("getPatientNote", ORMGetPatientNote.class, lstParam);
	}

	// @Override
	// public Long qrdaImport(String provider_id, String practice_id, MultipartFile
	// docFile) {
	// // TODO Auto-generated method stub
	//
	// QRDA_Import qrd = new QRDA_Import(db,practice_id,provider_id,docFile);
	//
	// return (long)0;
	// }

	@Override
	public ORMGetPatientScannedCards getPatientScannedCards(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientScannedCards> lstPatients = db.getStoreProcedureData("spGetPatientScannedCards",
				ORMGetPatientScannedCards.class, lstParam);

		if (lstPatients != null && lstPatients.size() > 0) {
			return lstPatients.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMAPIUsersGet> getAPIUsers(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		String criteria = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					criteria += " and ( apiUser.user_name in (select user_name from api_user_patients where patient_id = '"
							+ pram.getValue() + "') )  ";

					break;
				case "user_name":
					criteria += " and ( apiUser.user_name = '" + pram.getValue() + "'  )";

					/*
					 * criteria += " and (" +
					 * "	  (apiUser.user_type='API_USER' and apiUser.user_name = '" +
					 * pram.getValue() + "'  )" + "	  OR" +
					 * "	  (apiUser.user_type='PRACTICE_USER' and pracUser.user_name = '" +
					 * pram.getValue() + "' )" + "  ) ";
					 */
					break;
				case "last_name":
					criteria += " and ( apiUser.last_name = '" + pram.getValue() + "'  )";

					/*
					 * criteria += " and (" +
					 * "	  (apiUser.user_type='API_USER' and apiUser.last_name = '" +
					 * pram.getValue() + "'  )" + "	  OR" +
					 * "	  (apiUser.user_type='PRACTICE_USER' and pracUser.last_name = '" +
					 * pram.getValue() + "' )" + "  ) ";
					 */
					break;
				case "first_name":
					criteria += " and (apiUser.first_name = '" + pram.getValue() + "'  )";
					/*
					 * criteria += " and (" +
					 * "	  (apiUser.user_type='API_USER' and apiUser.first_name = '" +
					 * pram.getValue() + "'  )" + "	  OR" +
					 * "	  (apiUser.user_type='PRACTICE_USER' and pracUser.first_name = '" +
					 * pram.getValue() + "' )" + "  ) ";
					 */
					break;

				default:
					break;
				}

			}
		}
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));

		//return db.getStoreProcedureData("spGetAPIUsers", ORMAPIUsersGet.class, lstParam);
		return db.getStoreProcedureData("spGetAPIUsers_new", ORMAPIUsersGet.class, lstParam);
	}

	@Override
	public List<ORMAPIUserPatientGet> getAPIUserPatients(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue(),
						String.class, ParameterMode.IN));
			}
		}

		List<ORMAPIUserPatientGet> lst = db.getStoreProcedureData("spGetAPIUsersPatient", ORMAPIUserPatientGet.class,
				lstParam);

		return lst;
	}

	/*
	 * private boolean checkIfUserAlreadyExist(String userName) {
	 * 
	 * Boolean isUserAlreadyExist = false; List<SpParameters> lstCheck = new
	 * ArrayList<>(); lstCheck.add(new SpParameters("user_name",
	 * userName.toString(), String.class, ParameterMode.IN)); List<ORMScalarValue>
	 * lstResult = db.getStoreProcedureData("spCheckIfUserAlreadyExists",
	 * ORMScalarValue.class, lstCheck); if (lstResult != null && lstResult.size() >
	 * 0) { ORMScalarValue obj = (ORMScalarValue) lstResult.get(0);
	 * 
	 * if (Integer.parseInt(obj.getScalar_value()) > 0) { isUserAlreadyExist = true;
	 * } }
	 * 
	 * return isUserAlreadyExist; }
	 */

	@Override
	public ServiceResponse<ORMKeyValue> saveAPIUser(Wrapper_APIUserSave wrapper_APIUserSave) {
		// TODO Auto-generated method stub

		Boolean isUserAlreadyExist = false;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ORMAPIUserSave apiUser = wrapper_APIUserSave.getApi_user_save();
		List<ORMAPIUserPatientSave> lstAPIUserPatients = wrapper_APIUserSave.getLst_api_user_patients();
		List<Long> lstUserPatientIdsDeleted = wrapper_APIUserSave.getLst_user_patient_ids_deleted();

		// in case of new user add
		if (apiUser != null) {
			if (apiUser.getUser_id() == null || apiUser.getUser_id() <= 0) {

				SearchCriteria searchCriteria = new SearchCriteria();

				List<SearchCriteriaParamList> lst = new ArrayList<SearchCriteriaParamList>();
				lst.add(new SearchCriteriaParamList("user_name", apiUser.getUser_name(), ""));
				lst.add(new SearchCriteriaParamList("purpose", "new_record", ""));

				searchCriteria.setParam_list(lst);

				isUserAlreadyExist = userDAOImpl.checkIfUserExsist(searchCriteria);

				// isUserAlreadyExist = checkIfUserAlreadyExist(apiUser.getUser_name());
			}
		}

		if (!isUserAlreadyExist) {
			List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

			Long apUserId = (long) 0;

			if (apiUser != null) {

				apiUser.setDate_modified(DateTimeUtil.getCurrentDateTime());
				if (apiUser.getUser_id() == null || apiUser.getUser_id() <= 0) {

					apUserId = db.IDGenerator("api_users", apiUser.getPractice_id());

					apiUser.setUser_id(apUserId);
					apiUser.setDate_created(DateTimeUtil.getCurrentDateTime());

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, apiUser));
				} else {
					apUserId = apiUser.getUser_id();

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, apiUser));
				}

				if (lstAPIUserPatients != null && !lstAPIUserPatients.isEmpty()) {
					for (ORMAPIUserPatientSave apiUserPatient : lstAPIUserPatients) {
						apiUserPatient.setDate_modified(DateTimeUtil.getCurrentDateTime());
						if (apiUserPatient.getId() == null || apiUserPatient.getId() <= 0) {

							apiUserPatient.setDate_created(DateTimeUtil.getCurrentDateTime());

							if (apiUserPatient.getUser_id() == null || apiUserPatient.getUser_id() <= 0) {
								apiUserPatient.setUser_id(apUserId);
							}
							lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, apiUserPatient));

						} else {

							if (apiUserPatient.getUser_id() == null || apiUserPatient.getUser_id() <= 0) {
								apiUserPatient.setUser_id(apUserId);
							}
							lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, apiUserPatient));
						}
					}

				}

				if (lstUserPatientIdsDeleted != null && !lstUserPatientIdsDeleted.isEmpty()) {

					String strIds = "";

					for (Long id : lstUserPatientIdsDeleted) {
						if (strIds != "") {
							strIds += ",";
						}
						strIds += id;
					}

					if (strIds != "") {
						String query = " update api_user_patients set deleted=1,date_modified=getdate(),modified_user='"
								+ apiUser.getModified_user() + "',client_date_modified='"
								+ apiUser.getClient_date_created() + "',system_ip='" + apiUser.getSystem_ip()
								+ "' where id in (" + strIds + ") ";

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));
					}
				}

			}

			int result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
				resp.setResult(apiUser.getUser_id().toString());
			}
		} else {
			resp.setStatus(ServiceResponseStatus.NOT_ALLOWED);
			resp.setResponse("Another user already exist with the same user name.");
		}
		return resp;
	}

	@Override
	public List<ORMPatientInsurancesForMergeGet> getPatientInsuranceToMerge(String patientIds) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_ids", patientIds.toString(), String.class, ParameterMode.IN));

		List<ORMPatientInsurancesForMergeGet> lstIns = db.getStoreProcedureData("spGetPatientInsurancesForMerge",
				ORMPatientInsurancesForMergeGet.class, lstParam);

		return lstIns;
	}

	@Override
	public ServiceResponse<ORMKeyValue> mergePatients(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub

		String pat_id_to_keep = "";
		String patient_ids_to_delete = "";
		String insurance_ids_to_keep = "";
		String modified_user = "";
		String client_date_time = "";

		if (lstKeyValue != null && !lstKeyValue.isEmpty()) {

			for (ORMKeyValue ormKV : lstKeyValue) {

				switch (ormKV.getKey()) {
				case "pat_id_to_keep":
					pat_id_to_keep = ormKV.getValue();
					break;
				case "patient_ids_to_delete":
					patient_ids_to_delete = ormKV.getValue();
					break;
				case "insurance_ids_to_keep":
					insurance_ids_to_keep = ormKV.getValue();
					break;
				case "modified_user":
					modified_user = ormKV.getValue();
					break;
				case "client_date_time":
					client_date_time = ormKV.getValue();
				default:
					break;
				}

			}
		}

		String errorMsg = "";
		int result = 0;

		if (pat_id_to_keep == "" || patient_ids_to_delete == "" || modified_user == "" || client_date_time == "") {
			errorMsg = "One of the reqruied parameter is missing.";
		} else {
			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("pat_id_to_keep", pat_id_to_keep, String.class, ParameterMode.IN));
			lstParam.add(
					new SpParameters("patient_ids_to_delete", patient_ids_to_delete, String.class, ParameterMode.IN));
			lstParam.add(
					new SpParameters("insurance_ids_to_keep", insurance_ids_to_keep, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("modified_user", modified_user, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("client_date_time", client_date_time, String.class, ParameterMode.IN));

			result = Integer.parseInt(db.ExecuteUpdateStoreProcedureWithOutputResult("spMergePatient", lstParam));

			if (result == 0) {
				errorMsg = "An Error Occured while saving record.";
			}
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (errorMsg != "") {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse(errorMsg);
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(pat_id_to_keep.toString());
		}

		return resp;
	}

	@Override
	public Long addEditDocCategory(ORMDocCategories obj) {
		try {
			obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
			obj.setDeleted("false");
			if (obj.getDocument_categories_id() != null && obj.getDocument_categories_id() != "0"
					&& obj.getDocument_categories_id() != "") {
				System.out.println("EDIT...");
				System.out.println(obj.toString());
				if (db.SaveEntity(obj, Operation.EDIT) > 0)
					return Long.parseLong(obj.getDocument_categories_id());
				else
					return (long) 0;
			} else {
				System.out.println("ADD...");
				obj.setDocument_categories_id(
						db.IDGenerator("document_categories", Long.parseLong(obj.getPractice_id())).toString());
				obj.setDate_created(DateTimeUtil.getCurrentDateTime());
				System.out.println(obj.toString());
				if (db.SaveEntity(obj, Operation.ADD) > 0)
					return Long.parseLong(obj.getDocument_categories_id());
				else
					return (long) 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return (long) 0;
		}
	}

	@Override
	public List<ORMGetPatientNote> getStaffNoteAlert(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("getStaffNoteAlert", ORMGetPatientNote.class, lstParam);
	}

	public List<ORMPatient_Followup> getPatientFollowupApp(String appointment_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("appointment_id", appointment_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientAppointmentFollowUp", ORMPatient_Followup.class, lstParam);
	}

	@Override
	public List<ORMTwoColumnGeneric> getPatientTaskData(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<ORMTwoColumnGeneric> listResult = new ArrayList<>();
		ORMTwoColumnGeneric objNew = new ORMTwoColumnGeneric();
		String patient_id = "";
		String visit_date = "";
		String appointment_id = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						patient_id = pram.getValue();
						break;
					}
				case "visit_date":
					if (pram.getValue() != "" && pram.getValue() != null) {
						visit_date = pram.getValue();
						break;
					}
				case "appointment_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						appointment_id = pram.getValue();
						break;
					}
				}

			}
		}
		// logic
		List<MAPIDGenerator> listTest = db.getQueryData("select "
				+ "(SELECT '' + ('('+upper(ltrim(rtrim(proc_code))+') '+proc_description)) "
				+ "from patient_order_test pot  where pot.order_id=po.order_id and isnull(pot.deleted,0)<>1 FOR XML PATH(''))  as id "
				+ "from patient_order po " + "where  po.patient_id=" + patient_id + " "
				+ "and convert(date,po.order_date)=convert(date,'" + visit_date + "')", MAPIDGenerator.class);

		if (listTest != null && listTest.size() > 0) {
			for (MAPIDGenerator obj : listTest) {
				objNew = new ORMTwoColumnGeneric();
				objNew.setCol1(Integer.toString(listResult.size() + 1));
				objNew.setCol2("Patient Order is created for " + obj.getId());
				listResult.add(objNew);
			}
		}

		// Referral
		List<ORMThreeColum> listreferral = db
				.getQueryData("select distinct " + "upper(isnull(pr.referral_provider_name,'')) as id "
						+ ",upper(isnull(p.last_name,'')+', '+isnull(p.first_name,'')) as name ,  "
						+ "isnull(pr.referral_request,0) as address " + "from patient_referral pr "
						+ "left outer join provider p on p.provider_id=pr.provider_id  and ISNULL(p.deleted,0)<>1 "
						+ "left outer join location l on l.location_id=pr.location_id and ISNULL(l.deleted,0)<>1 "
						+ "left outer join consult_type ct on ct.consult_type_id=pr.consult_type_id "
						+ "where ISNULL(pr.deleted,0)<>1 " + "and pr.patient_id=" + patient_id
						+ " and convert(date,pr.date_created)=convert(date,'" + visit_date + "')", ORMThreeColum.class);
		if (listreferral != null && listreferral.size() > 0) {
			for (ORMThreeColum obj : listreferral) {
				objNew = new ORMTwoColumnGeneric();
				objNew.setCol1(Integer.toString(listResult.size() + 1));
				objNew.setCol2("Patient is Referred to " + obj.getId());

				listResult.add(objNew);
			}
		}
		List<ORMPatient_Followup> listFollowup = getPatientFollowupApp(appointment_id);
		if (listFollowup != null && listFollowup.size() > 0) {
			String day = "";
			String week = "";
			String month = "";
			for (ORMPatient_Followup obj : listFollowup) {
				day = obj.getDay();
				week = obj.getWeek();
				month = obj.getMonth();

				if (!day.equals("") || !week.equals("") || !month.equals("")) {
					String message = "";
					if (!month.equals("")) {
						message = month + " Month(s)";
					}
					if (!week.equals("")) {
						if (!message.equals("")) {
							message += ", " + week + " Week(s)";
						} else {
							message += week + " Week(s)";
						}

					}
					if (!day.equals("")) {
						if (!message.equals("")) {
							message += ", " + day + " Days(s)";
						} else {
							message += day + " Days(s)";
						}
					}
					objNew = new ORMTwoColumnGeneric();
					objNew.setCol1(Integer.toString(listResult.size() + 1));
					objNew.setCol2("FollowUp Required after " + message + ". Please take action accordingly.");
					listResult.add(objNew);
				}
			}
		}
		return listResult;
	}

	@Override
	public int deleteDocumentCategory(ORMDocCategories obj) {
		try {

			boolean docExsist = false;
			int retunValue = 0;
			String qry = "select count(*) as id from patient_document where doc_categories_id="
					+ obj.getDocument_categories_id() + " and isnull(deleted,0)<>1 and practice_id="
					+ obj.getPractice_id();

			int count = Integer.parseInt(db.getQuerySingleResult(qry));

			// return count > 0;
			if (count > 0) {
				docExsist = true;
			}
			if (docExsist == false) {
				generalService
						.updateQuery(" update document_categories set deleted = 1 where document_categories_id = '"
								+ obj.getDocument_categories_id() + "' ");
				retunValue = 1;
			} else {

				retunValue = 0;
			}
			return retunValue;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Long checkIfPatientExists(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "last_name":
				case "first_name":
				case "dob":
				case "ssn":
					lstParam.add(new SpParameters(pram.getName(),
							pram.getValue() != null ? pram.getValue().toString() : "", String.class, ParameterMode.IN));
				default:
					break;
				}
			}
		}

		String patientId = db.getStoreProcedureSingleResult("spCheckIfPatientEixists", lstParam);

		if (patientId != "") {
			return Long.parseLong(patientId);
		} else {
			return (long) 0; // Long.parseLong("0");
		}

	}

	@Override
	public ServiceResponse<ORMKeyValue> savePatientPic(SearchCriteria searchCriteria, MultipartFile picData) {
		// TODO Auto-generated method stub

		String patientId = "";
		String clientDateTime = "";
		String logedInUser = "";
		String clientIP = "";
		int result = 0;
		String errorMsg = "";

		Long practiceId = searchCriteria.getPractice_id();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {

				case "patient_id":
					patientId = pram.getValue();
					break;
				case "client_date_time":
					clientDateTime = pram.getValue();
					break;
				case "loged_in_user":
					logedInUser = pram.getValue();
					break;
				case "client_ip":
					clientIP = pram.getValue();
					break;
				default:
					break;
				}
			}
		}

		if (GeneralOperations.isNullorEmpty(patientId) || GeneralOperations.isNullorEmpty(clientDateTime)
				|| GeneralOperations.isNullorEmpty(logedInUser) || GeneralOperations.isNullorEmpty(clientIP)) {

			errorMsg = "One of the required parameter is missing.";

		}

		if (GeneralOperations.isNullorEmpty(errorMsg)) {
			// Patient Picture
			String directory = "";
			String originalFilename = "";
			String fileName = "";

			if (picData != null && !picData.isEmpty()) {
				try {

					ORMDocumentPath objPath = generalDAOImpl.getDocumentPathByCategory(practiceId, "PatientImages");

					if (objPath != null) {
						directory = objPath.getUpload_path();
					}

					directory = directory + "\\" + practiceId + "\\PatientImages\\";
					originalFilename = picData.getOriginalFilename();

					fileName = patientId + "." + FilenameUtils.getExtension(originalFilename);
					File destinationFile = new File(GeneralOperations.CheckPath(directory), fileName);
					picData.transferTo(destinationFile);

				} catch (Exception e) {
					errorMsg = "An Error Occured While Uploading Picture.";
					System.out.println("Picture Upload Error:" + e.getMessage());
				}
			}

			if (GeneralOperations.isNullorEmpty(errorMsg)) {

				String query = "update patient set pic='" + fileName + "',client_date_modified='" + clientDateTime
						+ "',modified_user='" + logedInUser + "',date_modified=getdate(),system_ip='" + clientIP + "'"
						+ "where patient_id='" + patientId + "' ";

				result = this.db.ExecuteUpdateQuery(query);

			}
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0 || errorMsg != "") {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse(errorMsg == "" ? "An Error Occured while saving record." : errorMsg);
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(patientId.toString());
		}

		return resp;

	}

	@Override
	public List<ORMGetPhrUsersNames> getPatientPhrUsersNames(String practiceId, String patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetPatientPhrUsersNames", ORMGetPhrUsersNames.class, lstParam);
	}
}