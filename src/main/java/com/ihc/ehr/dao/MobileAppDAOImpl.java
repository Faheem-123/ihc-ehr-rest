package com.ihc.ehr.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.Bundle;
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMMobClaimDetailGet;
import com.ihc.ehr.model.ORMMobClaimDiagnosisSave;
import com.ihc.ehr.model.ORMMobClaimProcedureSave;
import com.ihc.ehr.model.ORMMobClaimProceduresGet;
import com.ihc.ehr.model.ORMMobClaimSave;
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
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.SearchPatient;
import com.ihc.ehr.model.SearchPatientClaim;
import com.ihc.ehr.model.SearchPatientScheduled;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.model.Wrapper_MobClaimSave;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@Repository
public class MobileAppDAOImpl implements MobileAppDAO {

	@Autowired
	private DBOperations db;

	@Autowired
	private PatientDAOImpl patientDAOImpl;

	@Autowired
	private GeneralDAOImpl generalDAOImpl;

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> patientSearch(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<T> lstPatients = null;

		String searchCriteriaString = "";
		Long practice_id = searchCriteria.getPractice_id();
		String searchValue = "";// searchCriteria.getCriteria();
		String searchValueByFeild = "";
		String searchType = searchCriteria.getOption() == null ? "DEFAULT" : searchCriteria.getOption();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			// searchType = searchCriteria.getOption() == null ? "DEFAULT" :
			// searchCriteria.getOption();

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				searchValue = pram.getValue().replace("'", "");
				searchValueByFeild = pram.getName();
				// searchValueByFeild = pram.getName();
				System.out.println("param:" + pram.getName() + "    Value:" + pram.getValue());
				break;
			}

		}

		if (searchType.equalsIgnoreCase("TODAY_SCHEDULED")) {

			searchCriteriaString = " and p.practice_id='" + searchCriteria.getPractice_id().toString() + "' ";

			if (searchCriteria.getParam_list() != null) {
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

					switch (pram.getName()) {
					case "location_id":
						if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
							searchCriteriaString += " and app.location_id='" + pram.getValue().toString() + "' ";
						}
						break;
					case "provider_id":
						if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
							searchCriteriaString += " and app.provider_id='" + pram.getValue().toString() + "' ";
						}
						break;
					default:
						break;
					}
				}
			}

			System.out.println("spGetTodayScheduledPatientsList '" + searchCriteriaString + "'");
			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("search_criteria", searchCriteriaString.toString(), String.class,
					ParameterMode.IN));
			lstPatients = (List<T>) db.getStoreProcedureData("spGetTodayScheduledPatientsList",
					SearchPatientScheduled.class, lstParam);

		} else {

			if (searchValue.isEmpty())
				return null;

			if (searchType.equalsIgnoreCase("LATEST_OPENED")) {

				// String innerSQL = "select top 20 patient_id as scalar_value " + " from
				// user_searched_patients "
				// + " where practice_id='" + practice_id.toString() + "' and user_name='" +
				// searchValue + "'"
				// + " order by date_searched desc ";

				// searchCriteriaString += " and p.patient_id in (" + innerSQL + ")";

				System.out.println("spGetLatestOpenedPatientsList '" + practice_id + "','" + searchValue + "'");

				List<SpParameters> lstParam = new ArrayList<>();
				lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
				lstParam.add(new SpParameters("user_name", searchValue.toString(), String.class, ParameterMode.IN));

				lstPatients = (List<T>) db.getStoreProcedureData("spGetLatestOpenedPatientsList", SearchPatient.class,
						lstParam);

			} else {

				if (searchType.equalsIgnoreCase("DEFAULT")) {

					String strWithoutSpecialCharacters = searchValue.replaceAll("-", "").replaceAll("\\/", "")
							.replaceAll("\\)", "").replaceAll("\\(", "");

					if (GeneralOperations.isWholeNumber(strWithoutSpecialCharacters)) {

						// DOB
						if (strWithoutSpecialCharacters.length() == 6 || strWithoutSpecialCharacters.length() == 8) {

							String dob = strWithoutSpecialCharacters.substring(0, 2) + "/"
									+ strWithoutSpecialCharacters.substring(2, 4) + "/"
									+ strWithoutSpecialCharacters.substring(4, strWithoutSpecialCharacters.length());

							if (dob.length() == 8 && DateTimeUtil.isDateValid(dob, DateFormatEnum.MM_dd_yy)) {
								if (searchCriteriaString.isEmpty()) {
									searchCriteriaString = " and (";
								} else {
									searchCriteriaString += " OR ";
								}
								searchCriteriaString += "( (left(convert(varchar,p.dob,101),6)+convert(varchar,right(year(p.dob),2))) = '"
										+ dob + "' )";

							} else if (dob.length() == 10 && DateTimeUtil.isDateValid(dob, DateFormatEnum.MM_dd_yyyy)) {
								if (searchCriteriaString.isEmpty()) {
									searchCriteriaString = " and (";
								} else {
									searchCriteriaString += " OR";
								}
								searchCriteriaString += "( convert(varchar,p.dob,101) = '" + dob + "' )";

							}
						}

						// SSN
						if (strWithoutSpecialCharacters.length() == 9) {
							if (searchCriteriaString.isEmpty()) {
								searchCriteriaString = " and (";
							} else {
								searchCriteriaString += " OR ";
							}
							searchCriteriaString += "(( ltrim( rtrim ( replace(p.ssn,'-','')))) = '"
									+ strWithoutSpecialCharacters + "')";
						}

						// PHONE
						if (strWithoutSpecialCharacters.length() == 10) {
							if (searchCriteriaString.isEmpty()) {
								searchCriteriaString = " and (";
							} else {
								searchCriteriaString += " OR ";
							}

							searchCriteriaString += "  ( " + " isnull(p.home_phone,'') like '%"
									+ strWithoutSpecialCharacters + "%' " + " OR " + " isnull(p.work_phone,'') like '%"
									+ strWithoutSpecialCharacters + "%' " + " OR " + " isnull(p.cell_phone,'') like '%"
									+ strWithoutSpecialCharacters + "%' " + ")";
						}

						// CLAIM
						if (strWithoutSpecialCharacters.length() > 6 && strWithoutSpecialCharacters.length() <= 20) {
							if (searchCriteriaString.isEmpty()) {
								searchCriteriaString = " and (";
							} else {
								searchCriteriaString += " OR ";
							}
							searchCriteriaString += " ( p.patient_id in  (select patient_id from claim where isnull(deleted,0)<>1 and claim_id='"
									+ strWithoutSpecialCharacters.replace("'", "") + "'))";
						}

						// PATIENT ID
						if (searchCriteriaString.isEmpty()) {
							searchCriteriaString = " and (";
						} else {
							searchCriteriaString += " OR ";
						}
						searchCriteriaString += " ( p.patient_id = '" + strWithoutSpecialCharacters.replace("'", "")
								+ "')";
					}

					if (searchCriteriaString.isEmpty()) {
						searchCriteriaString = " and (";
					} else {
						searchCriteriaString += " OR ";
					}

					// Patient Name
					String[] strPatientName = searchValue.replace("'", "").split(" ");
					searchCriteriaString += " ( p.last_name like '" + searchValue.replace("'", "").split(" ")[0] + "%'";
					if (strPatientName.length > 1) {
						searchCriteriaString += " and p.first_name like '" + searchValue.replace("'", "").split(" ")[1]
								+ "%'";
					}
					searchCriteriaString += " ) ";

					// PID
					searchCriteriaString += " OR ( isnull(p.alternate_account,0)='" + searchValue + "' )";

					searchCriteriaString += " ) ";

					searchCriteriaString += " and p.practice_id='" + practice_id.toString() + "'";
					System.out.println("searchCriteriaString:" + searchCriteriaString);

					List<SpParameters> lstParam = new ArrayList<>();
					lstParam.add(new SpParameters("criteria", searchCriteriaString, String.class, ParameterMode.IN));

					lstPatients = (List<T>) db.getStoreProcedureData("SearchPatient", SearchPatient.class, lstParam);

				} else if (searchType.equalsIgnoreCase("ADVANCE")) {

					if (searchValueByFeild.equalsIgnoreCase("CLAIM_ID")) {

						System.out.println("spGetPatientClaimSearch '" + searchValue + "'");

						searchCriteriaString = " and p.practice_id='" + practice_id.toString() + "'";
						searchCriteriaString += " AND cl.claim_id='" + searchValue + "' ";

						List<SpParameters> lstParam = new ArrayList<>();
						lstParam.add(new SpParameters("search_criteria", searchCriteriaString.toString(), String.class,
								ParameterMode.IN));
						lstPatients = (List<T>) db.getStoreProcedureData("spGetPatientClaimSearch",
								SearchPatientClaim.class, lstParam);

					} else {

						switch (searchValueByFeild == null ? "" : searchValueByFeild) {
						case "PID":
						case "PATIENT_ID":
							searchCriteriaString = " and ( isnull(p.alternate_account,0)='"
									+ searchCriteria.getCriteria() + "' ";

							if (GeneralOperations.isWholeNumber(searchValue)) {
								searchCriteriaString += " OR isnull(p.patient_id,0)= '" + searchValue + "' ";
							}

							searchCriteriaString += ")";

							break;
						case "POLICY_NO":
							searchCriteriaString = " and p.patient_id in (select patient_id from patient_insurance where isnull(deleted,0)<>1 and isnull(policy_number,'') like '%"
									+ searchValue + "%')";
						case "SSN":
							searchCriteriaString = " and ltrim(rtrim(replace(p.ssn,'-',''))) = '"
									+ searchValue.replaceAll("-", "") + "'";
							break;
						case "PHONE_NO":
							String ph = GeneralOperations.getNumericFromMaskedString(searchValue);

							searchCriteriaString = " and ( " + " isnull(p.home_phone,'') like '%" + ph + "%' " + " OR "
									+ " isnull(p.work_phone,'') like '%" + ph + "%' " + " OR "
									+ " isnull(p.cell_phone,'') like '%" + ph + "%' " + ")";
							break;

						/*
						 * case "CLAIM_ID":
						 * 
						 * if (GeneralOperations.isWholeNumber(searchValue)) { searchCriteriaString =
						 * " and p.patient_id = (select patient_id from claim where isnull(deleted,0)<>1 and claim_id='"
						 * + searchValue.replace("'", "") + "')"; } else { return null; }
						 * 
						 * break;
						 */
						default:
							break;
						}

						searchCriteriaString += " and p.practice_id='" + practice_id.toString() + "'";
						System.out.println("searchCriteriaString:" + searchCriteriaString);

						List<SpParameters> lstParam = new ArrayList<>();
						lstParam.add(
								new SpParameters("criteria", searchCriteriaString, String.class, ParameterMode.IN));

						lstPatients = (List<T>) db.getStoreProcedureData("spMobSearchPatient", SearchPatient.class,
								lstParam);

					}
				}

			}
		}

		return lstPatients;
	}

	@Override
	public ORMMobGetPatient getPatient(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		List<ORMMobGetPatient> lst = db.getStoreProcedureData("getPatientInfoMob", ORMMobGetPatient.class, lstParam);
		if (!lst.isEmpty())
			return lst.get(0);
		else
			return null;
	}

	@Override
	public ORMMobileGetPatientHeader getPatientHeader(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		List<ORMMobileGetPatientHeader> lst = db.getStoreProcedureData("spGetMobilePatientHeaderInfo",
				ORMMobileGetPatientHeader.class, lstParam);
		if (!lst.isEmpty())
			return lst.get(0);
		else
			return null;
	}

	@Override
	public ServiceResponse<ORMKeyValue> SavePatient(ORMMobSavePatient ormMobSavePatient, MultipartFile picData) {
		// TODO Auto-generated method stub
		Long alreadyEixtPatientId = (long) 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (ormMobSavePatient != null
				&& (ormMobSavePatient.getPatient_id() == null || ormMobSavePatient.getPatient_id() <= 0)) {

			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setPractice_id(ormMobSavePatient.getPractice_id());

			List<SearchCriteriaParamList> param_list = new ArrayList<>();
			param_list.add(new SearchCriteriaParamList("last_name", ormMobSavePatient.getLast_name(), null));
			param_list.add(new SearchCriteriaParamList("first_name", ormMobSavePatient.getFirst_name(), null));
			param_list.add(new SearchCriteriaParamList("dob", ormMobSavePatient.getDob(), null));
			param_list.add(new SearchCriteriaParamList("ssn", ormMobSavePatient.getSsn(), null));

			searchCriteria.setParam_list(param_list);

			alreadyEixtPatientId = patientDAOImpl.checkIfPatientExists(searchCriteria);

			if (alreadyEixtPatientId > 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("Patient Already Exist.");
				resp.setResult(alreadyEixtPatientId.toString());
				return resp;
			}

			// return resp;
		}

		Long patientId = ormMobSavePatient.getPatient_id();
		String alternatePID = "";
		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		// PATIENT
		if (ormMobSavePatient != null || (picData != null && !picData.isEmpty())) {
			// Patient Picture
			String directory = "";
			String originalFilename = "";
			alternatePID = ormMobSavePatient.getAlternate_account();

			if (picData != null && !picData.isEmpty()) {
				try {

					ORMDocumentPath objPath = generalDAOImpl
							.getDocumentPathByCategory(ormMobSavePatient.getPractice_id(), "PatientImages");

					if (objPath != null) {
						directory = objPath.getUpload_path();
					}

					directory = directory + "\\" + ormMobSavePatient.getPractice_id() + "\\PatientImages\\";
					originalFilename = picData.getOriginalFilename();

				} catch (Exception e) {
					System.out.println("Picture Upload Error:" + e.getMessage());
				}
			}
			// end Patient Picture

			// ORMSavePatient ormSavePatient = objPatientWrapper.getPatient();
			ormMobSavePatient.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (ormMobSavePatient.getPatient_id() == null || ormMobSavePatient.getPatient_id() <= 0) {

				patientId = db.IDGenerator("patient", ormMobSavePatient.getPractice_id());
				alternatePID = db.PIDGenerator(ormMobSavePatient.getPractice_id());

				ormMobSavePatient.setPatient_id(patientId);
				ormMobSavePatient.setAlternate_account(alternatePID);
				ormMobSavePatient.setDate_created(DateTimeUtil.getCurrentDateTime());

				if (picData != null && !picData.isEmpty()) {
					try {

						String fileName = patientId + "." + FilenameUtils.getExtension(originalFilename);
						File destinationFile = new File(GeneralOperations.CheckPath(directory), fileName);
						picData.transferTo(destinationFile);
						ormMobSavePatient.setPic(fileName);

					} catch (Exception e) {
						System.out.println("Picture Upload Error:" + e.getMessage());
					}
				}

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormMobSavePatient));

			} else {
				patientId = ormMobSavePatient.getPatient_id();

				if (picData != null && !picData.isEmpty()) {
					try {

						String fileName = patientId + "." + FilenameUtils.getExtension(originalFilename);
						File destinationFile = new File(GeneralOperations.CheckPath(directory), fileName);
						picData.transferTo(destinationFile);
						ormMobSavePatient.setPic(fileName);

					} catch (Exception e) {
						System.out.println("Picture Upload Error:" + e.getMessage());
					}
				}

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormMobSavePatient));
			}

		}
		// end PATIENT

		int result = db.AddUpdateEntityWrapper(lstEntityWrapper);

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(patientId.toString());
		}

		return resp;
	}

	@Override
	public List<ORMMobileGetClaimSummary> getDraftClaimSummary(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		List<ORMMobileGetClaimSummary> lst = db.getStoreProcedureData("spGetMobileDraftClaimSummary",
				ORMMobileGetClaimSummary.class, lstParam);

		return lst;
	}

	@Override
	public ORMMobClaimDetailGet getClaimDetail(Long claimId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claimId.toString(), String.class, ParameterMode.IN));
		List<ORMMobClaimDetailGet> lst = db.getStoreProcedureData("spGetMobClaimDetail", ORMMobClaimDetailGet.class,
				lstParam);

		if (!lst.isEmpty()) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMMobClamDiagnosisGet> getClaimDiagnosis(Long claim_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));
		List<ORMMobClamDiagnosisGet> lst = db.getStoreProcedureData("spGetMobClaimDiagnosis",
				ORMMobClamDiagnosisGet.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMMobClaimProceduresGet> getClaimProcedures(Long claim_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));
		List<ORMMobClaimProceduresGet> lst = db.getStoreProcedureData("spGetMobClaimProcedures",
				ORMMobClaimProceduresGet.class, lstParam);

		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveClaim(Wrapper_MobClaimSave wrapper_MobClaimSave) {
		// TODO Auto-generated method stub
		int result = 0;

		ORMMobClaimSave claimSave = wrapper_MobClaimSave.getOrmMobClaimSave();

		List<ORMMobClaimDiagnosisSave> lstClaimDiagnosisSave = wrapper_MobClaimSave.getLstORMMobClaimDiagnosisSave();

		List<ORMMobClaimProcedureSave> lstClaimProceduresSave = wrapper_MobClaimSave.getLstORMMobClaimProcedureSave();

		List<Long> lstDiagIdsDeleted = wrapper_MobClaimSave.getLstDiagIdsDeleted();
		List<Long> lstProcIdsDeleted = wrapper_MobClaimSave.getLstProcIdsDeleted();

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (claimSave != null) {

			// *** Claim
			claimSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (claimSave.getClaim_id() == null) {

				claimSave.setClaim_id(db.IDGenerator("claim", claimSave.getPractice_id()));
				claimSave.setDate_created(DateTimeUtil.getCurrentDateTime());

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, claimSave));
			} else {
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, claimSave));
			}
			// *** END Claim

			// *** Claim Diagnosis
			if (lstClaimDiagnosisSave != null && lstClaimDiagnosisSave.size() > 0) {

				for (ORMMobClaimDiagnosisSave ormClaimDiagnosisSave : lstClaimDiagnosisSave) {

					ormClaimDiagnosisSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
					ormClaimDiagnosisSave.setClaim_id(claimSave.getClaim_id());
					if (ormClaimDiagnosisSave.getClaim_diagnosis_id() == null) {

						ormClaimDiagnosisSave
								.setClaim_diagnosis_id(db.IDGenerator("claim_diagnos", claimSave.getPractice_id()));
						ormClaimDiagnosisSave.setDate_created(DateTimeUtil.getCurrentDateTime());

						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimDiagnosisSave));
					} else {
						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormClaimDiagnosisSave));
					}
				}

			}

			if (lstDiagIdsDeleted != null && lstDiagIdsDeleted.size() > 0) {

				String strDelIds = "";
				for (Long delId : lstDiagIdsDeleted) {
					if (strDelIds != "")
						strDelIds += ",";
					strDelIds += "'" + delId.toString() + "'";
				}

				String strDiagDelQuery = "update claim_diagnos set deleted=1,date_modified=getdate(),client_date_modified='"
						+ claimSave.getClient_date_modified() + "',system_ip='" + claimSave.getSystem_ip()
						+ "' where claim_diagnosis_id in (" + strDelIds + ")";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strDiagDelQuery));
			}

			// ** END Claim Diagnosis

			// *** Claim Procedures
			if (lstClaimProceduresSave != null && lstClaimProceduresSave.size() > 0) {

				for (ORMMobClaimProcedureSave ormClaimProceduresSave : lstClaimProceduresSave) {

					ormClaimProceduresSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
					ormClaimProceduresSave.setClaim_id(claimSave.getClaim_id());
					if (ormClaimProceduresSave.getClaim_procedures_id() == null) {

						ormClaimProceduresSave
								.setClaim_procedures_id(db.IDGenerator("claim_procedure", claimSave.getPractice_id()));
						ormClaimProceduresSave.setDate_created(DateTimeUtil.getCurrentDateTime());

						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimProceduresSave));
					} else {
						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormClaimProceduresSave));
					}
				}

			}

			if (lstProcIdsDeleted != null && lstProcIdsDeleted.size() > 0) {

				String strDelIds = "";
				for (Long delId : lstProcIdsDeleted) {
					if (strDelIds != "")
						strDelIds += ",";
					strDelIds += "'" + delId.toString() + "'";
				}

				String strDiagDelQuery = "update claim_procedure set deleted=1,date_modified=getdate(),client_date_modified='"
						+ claimSave.getClient_date_modified() + "',system_ip='" + claimSave.getSystem_ip()
						+ "' where claim_procedures_id in (" + strDelIds + ")";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strDiagDelQuery));
			}

			// ** END Claim Procedures

			result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
				resp.setResult(claimSave.getClaim_id().toString());
			}

		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("No claim data is provided");
		}

		return resp;
	}

	@Override
	public Bundle<SearchPatient> searchPatient(Long practiceId, List<SpParameters> lstCriteriaParam) {
		// TODO Auto-generated method stub

		String Criteria = " and p.practice_id='" + practiceId.toString() + "'";

		for (SpParameters criteriaParam : lstCriteriaParam) {

			if (GeneralOperations.isNullorEmpty(criteriaParam.getParamValue())) {
				continue;
			}

			switch (criteriaParam.getParamName()) {
			case "name":
				Criteria += " and ( isnull(p.last_name,'') like '" + criteriaParam.getParamValue()
						+ "%'  OR isnull(p.first_name,'') like '" + criteriaParam.getParamValue() + "%' ) ";
				break;
			case "family":
				Criteria += " and isnull(p.last_name,'') like '" + criteriaParam.getParamValue() + "%' ";
				break;
			case "given":
				Criteria += " and isnull(p.first_name,'') like '" + criteriaParam.getParamValue() + "%' ";
				break;
			case "birthdate":
				Criteria += " and CONVERT(char(10), p.dob,126)='" + criteriaParam.getParamValue() + "' ";
				break;
			case "gender":

				if (criteriaParam.getParamValue().equals("male")) {
					Criteria += " and isnull(p.gender_code,'') = 'M' ";
				} else if (criteriaParam.getParamValue().equals("female")) {
					Criteria += " and isnull(p.gender_code,'') = 'F' ";
				} else if (criteriaParam.getParamValue().equals("unknown")) {
					Criteria += " and isnull(p.gender_code,'') in ('UNK','') ";
				} else if (criteriaParam.getParamValue().equals("other")) {
					Criteria += " and isnull(gender_code,'') = 'OTH' ";
				}
				break;
			case "identifier":

				String[] idSplit = criteriaParam.getParamValue().split("\\|");

				if (idSplit[0].equalsIgnoreCase("PID")) {
					Criteria += " and isnull(p.alternate_account,'') = '" + idSplit[1] + "'";
				} else if (idSplit[0].equalsIgnoreCase("PATIENT_ID")) {
					Criteria += " and isnull(p.patient_id,'') = '" + idSplit[1] + "'";
				} else if (idSplit[0].equalsIgnoreCase("SSN")) {
					Criteria += " and isnull(replace(p.ssn,'-',''),'') = '" + idSplit[1].replaceAll("-", "") + "'";
				}
				break;

			default:
				break;
			}
		}

		System.out.println(
				"SearchPatient '" + practiceId.toString() + "','" + Criteria.toString().replaceAll("'", "''") + "'");

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", Criteria.toString(), String.class, ParameterMode.IN));

		List<SearchPatient> lstPatient = db.getStoreProcedureData("SearchPatient", SearchPatient.class, lstParam);

		Bundle<SearchPatient> bundle = new Bundle<>();

		bundle.setTotal(lstPatient.size());
		bundle.setLst(lstPatient);

		return bundle;
	}

	@Override
	public ORMMobGetLoginUserInfo getMobLogedInUserDetail(Long user_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));
		List<ORMMobGetLoginUserInfo> lst = db.getStoreProcedureData("spMOBGetLogedInuserDetail",
				ORMMobGetLoginUserInfo.class, lstParam);
		if (!lst.isEmpty())
			return lst.get(0);
		else
			return null;
	}

	@Override
	public ORMMobPracticeInfo getMobPracticeInfo(Long practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		List<ORMMobPracticeInfo> lst = db.getStoreProcedureData("spMobGetPracticeInfo", ORMMobPracticeInfo.class,
				lstParam);

		if (lst != null && lst.size() > 0)
			return lst.get(0);
		else {
			return null;
		}
	}

	@Override
	public List<ORMMobGetLocation> getMoblocation(Long practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spMobGetLocation", ORMMobGetLocation.class, lstParam);
	}

	@Override
	public List<ORMMobPracticeUsers> getMobPracticeUserName(Long practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("PracticeId", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spMobGetALLUserName", ORMMobPracticeUsers.class, lstParam);
	}

	@Override
	public List<ORMMobProviderList> getMobProviderList(Long practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spMobGetAttendingProviderList", ORMMobProviderList.class, lstParam);
	}

	@Override
	public List<ORMMobProviderList> getMobBillingProviderList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spMobGetBillingProviderList", ORMMobProviderList.class, lstParam);
	}

}