package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
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
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.SearchPHRPatient;
import com.ihc.ehr.model.SearchPatient;
import com.ihc.ehr.model.SearchPatientClaim;
import com.ihc.ehr.model.SearchPatientScheduled;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;
import com.ihc.ehr.util.GeneralOperations;

@Repository
public class SearchDAOImpl implements SearchDAO {

	@Autowired
	private DBOperations db;

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchPatient(SearchCriteria searchCriteria) {
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

						lstPatients = (List<T>) db.getStoreProcedureData("SearchPatient", SearchPatient.class,
								lstParam);

					}
				}

			}
		}

		return lstPatients;
	}

	@Override
	public List<ORMGetDiagSearch> searchDiagnosis(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spGetSearchDiagnosis", ORMGetDiagSearch.class, lstParam);
	}

	@Override
	public List<ORMProcedureSearch> searchLabTest(SearchCriteria searchCriteria) {

		// String criteria = "";
		String searchOption = searchCriteria.getOption().toString();
		List<SpParameters> lstParam = new ArrayList<>();
		List<ORMProcedureSearch> lst = null;
		switch (searchOption) {
		case "LOINC":
			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
					lstParam.add(new SpParameters(pram.getName(),
							pram.getValue() == null ? "" : pram.getValue().toString(), String.class, ParameterMode.IN));
				}
			}
			lst = db.getStoreProcedureData("spGetSearchLoinc", ORMProcedureSearch.class, lstParam);
			break;
		case "SNOMED":
			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
					lstParam.add(new SpParameters(pram.getName(),
							pram.getValue() == null ? "" : pram.getValue().toString(), String.class, ParameterMode.IN));
				}
			}
			lst = db.getStoreProcedureData("spGetSearchSnomed", ORMProcedureSearch.class, lstParam);
			break;
		case "CPT":
			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
					lstParam.add(new SpParameters(pram.getName(),
							pram.getValue() == null ? "" : pram.getValue().toString(), String.class, ParameterMode.IN));
				}
			}
			// lstParam.add(new SpParameters("search_criteria", criteria.toString(),
			// String.class, ParameterMode.IN));
			lst = db.getStoreProcedureData("spGetSearchProcedure", ORMProcedureSearch.class, lstParam);
			break;

		default:
			break;
		}
		return lst;

	}

	@Override
	public List<ORMRaceEthnicitySearch> searchRace(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("searchRace", ORMRaceEthnicitySearch.class, lstParam);
	}

	@Override
	public List<ORMRaceEthnicitySearch> searchEthnicity(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("searchEthnicity", ORMRaceEthnicitySearch.class, lstParam);
	}

	@Override
	public List<ORMInsuranceSearch> searchInsurance(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spGetInsuranceSearch", ORMInsuranceSearch.class, lstParam);
	}

	@Override
	public List<ORMGuarantorSearch> searchGuarantor(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spGetGuarantorSearch", ORMGuarantorSearch.class, lstParam);
	}

	@Override
	public List<ORMReferralPhysicianSearch> searchRefphysician(SearchCriteria searchCriteria) {

		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spGetReferalSearch", ORMReferralPhysicianSearch.class, lstParam);

	}

	@Override
	public List<ORMSpecialitySearch> searchSpeciality(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		// lstParam.add(new SpParameters("practice_id",
		// searchCriteria.getPractice_id().toString(), String.class, ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spGetTaxonomySearch", ORMSpecialitySearch.class, lstParam);
	}

	@Override
	public List<ORMGetProcedureSearch> searchProcedures(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<ORMGetProcedureSearch> lst = null;

		String searchOption = searchCriteria.getOption().toString();
		List<SpParameters> lstParam = new ArrayList<>();
		switch (searchOption) {
		case "all":
			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
					lstParam.add(new SpParameters(pram.getName(),
							pram.getValue() == null ? "" : pram.getValue().toString(), String.class, ParameterMode.IN));
				}
			}

			lst = db.getStoreProcedureData("spGetSearchProcedure", ORMGetProcedureSearch.class, lstParam);
			break;

		case "super-bill":

			lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
					ParameterMode.IN));
			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

					switch (pram.getName()) {
					case "search_criteria":
					case "dos":
						lstParam.add(new SpParameters(pram.getName(),
								pram.getValue() == null ? "" : pram.getValue().toString(), String.class,
								ParameterMode.IN));
						break;

					default:
						break;
					}

				}
			}

			lst = db.getStoreProcedureData("spGetProcSearchSuperBill", ORMGetProcedureSearch.class, lstParam);
			break;
		case "snomedct":
			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
					lstParam.add(new SpParameters(pram.getName(),
							pram.getValue() == null ? "" : pram.getValue().toString(), String.class, ParameterMode.IN));
				}
			}

			lst = db.getStoreProcedureData("spGetSearchSnomed", ORMGetProcedureSearch.class, lstParam);
			break;

		default:
			break;
		}

		return lst;
	}

	@Override
	public List<ORMPayerSearch> searchPayer(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));
			}
		}
		return db.getStoreProcedureData("spGetInsurancePayerSearch", ORMPayerSearch.class, lstParam);
	}

	@Override
	public List<ORMGetChartDiagnosis> getChartDiagnosis(String patient_id, String chart_id) {
		// TODO Auto-generated method stub

		if (chart_id.equals("0")) {
			String strQuery = " select top 1 chart_id as scalar_value from patient_chart where patient_id='"
					+ patient_id + "' and isnull(deleted,0)<>1 order by visit_date desc ";
			chart_id = db.getQuerySingleResult(strQuery);

		}
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPreviousDiag", ORMGetChartDiagnosis.class, lstParam);

	}

	@Override
	public List<ORMLabMappingCode> searchLabMappingTest(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<ORMLabMappingCode> lst = null;

		String searchOption = searchCriteria.getOption().toString();
		String searchLab = searchCriteria.getCriteria().toString();
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (searchOption.equals("CPT")) {
					String crt = " and (p.proc_code like '" + pram.getValue() + "%' or p.description like '%"
							+ pram.getValue() + "%' )";
					crt += " AND lb.Lab_Code='" + searchLab + "'";
					lstParam.add(new SpParameters("criteria", crt, String.class, ParameterMode.IN));
				} else if (searchOption.equals("LAB")) {
					String crt = " and (lb.labordecode like '" + pram.getValue() + "%' or lb.labcodedescription like '%"
							+ pram.getValue() + "%' )";
					crt += " AND lb.Lab_Code='" + searchLab + "'";
					lstParam.add(new SpParameters("criteria", crt, String.class, ParameterMode.IN));
				}
			}
		}

		lst = db.getStoreProcedureData("spGetLabMappingCode", ORMLabMappingCode.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMLabResultLoincCode> getLoinCode(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		String crit = " where loinc_code like '" + searchCriteria.getCriteria() + "%' or component like '%"
				+ searchCriteria.getCriteria() + "%'";
		lstParam.add(new SpParameters("criteria", crit, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLoincCode", ORMLabResultLoincCode.class, lstParam);
	}

	@Override
	public List<ORMGetAttorneyInfo> searchFirm(SearchCriteria searchCriteria) {
		String value = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "criteria":
					if (pram.getValue() != "" && pram.getValue() != null) {
						value = pram.getValue();
					}
					break;
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("value", value, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetAttorney_new", ORMGetAttorneyInfo.class, lstParam);

	}

	@Override
	public List<ORMThreeColum> getAllChartPlan(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			String code_type = "";
			String criteria = "";
			String chart_id = "";
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("type")) {
					code_type = pram.getValue();
				} else if (pram.getName().equals("criteria")) {
					criteria = pram.getValue();
				} else if (pram.getName().equals("chart_id")) {
					chart_id = pram.getValue();
				}

			}
			if (code_type.equals("ICD-10")) {
				criteria = " and (ltrim(rtrim(code)) like '" + criteria + "%' or ltrim(rtrim(description)) like '%"
						+ criteria + "%' )";
			} else if (code_type.equals("CPT")) {
				criteria = " and (ltrim(rtrim(proc_code)) like '" + criteria + "%' or ltrim(rtrim(description)) like '%"
						+ criteria + "%' )";
			} else if (code_type.equals("Medicine")) {
				criteria = " and (ltrim(rtrim(drug_info)) like '" + criteria + "%' or ltrim(rtrim(drug_info)) like '%"
						+ criteria + "%' )";
			} else if (code_type.equals("SnomedCT")) {
				criteria = " and (ltrim(rtrim(code)) like '" + criteria + "%' or ltrim(rtrim(description)) like '%"
						+ criteria + "%' )";
			} else if (code_type.equals("Loinc")) {
				criteria = " and (ltrim(rtrim(loinc_code)) like '" + criteria + "%' or ltrim(rtrim(shortname)) like '%"
						+ criteria + "%' )";
			}
			lstParam.add(new SpParameters("type", code_type, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("criteria2", "", String.class, ParameterMode.IN));
		}

		return db.getStoreProcedureData("spGetPatientCarePlan", ORMThreeColum.class, lstParam);
	}

	@Override
	public List<SearchPHRPatient> PHRsearchPatient(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<SearchPHRPatient> lstPatients = null;
		String searchCriteriaString = "";
		Long practice_id = searchCriteria.getPractice_id();
		String searchValue = "";// searchCriteria.getCriteria();
		// String searchValueByFeild;
		// String searchType;

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			// searchType = searchCriteria.getOption() == null ? "DEFAULT" :
			// searchCriteria.getOption();

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				searchValue = pram.getValue().replace("'", "");
				// searchValueByFeild = pram.getName();
				System.out.println("param:" + pram.getName() + "    Value:" + pram.getValue());
				break;
			}

		}

		if (searchValue.isEmpty())
			return null;

		if (GeneralOperations.isWholeNumber(searchValue)) {

			// DOB
			if (searchValue.length() == 6 || searchValue.length() == 8) {

				String dob = searchValue.substring(0, 2) + "/" + searchValue.substring(2, 4) + "/"
						+ searchValue.substring(4, searchValue.length());

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
			if (searchValue.length() == 9) {
				if (searchCriteriaString.isEmpty()) {
					searchCriteriaString = " and (";
				} else {
					searchCriteriaString += " OR ";
				}
				searchCriteriaString += "(ltrim(rtrim(replace(p.ssn,'-','')) = '" + searchValue + "')";
			}

			// PHONE
			if (searchValue.length() == 10) {
				if (searchCriteriaString.isEmpty()) {
					searchCriteriaString = " and (";
				} else {
					searchCriteriaString += " OR ";
				}

				searchCriteriaString = "  ( " + " isnull(p.home_phone,'') like '%" + searchValue + "%' " + " OR "
						+ " isnull(p.work_phone,'') like '%" + searchValue + "%' " + " OR "
						+ " isnull(p.cell_phone,'') like '%" + searchValue + "%' " + ")";
			}

			// CLAIM
			if (searchValue.length() > 6) {
				if (searchCriteriaString.isEmpty()) {
					searchCriteriaString = " and (";
				} else {
					searchCriteriaString += " OR ";
				}
				searchCriteriaString += " ( p.patient_id in  (select patient_id from claim where isnull(deleted,0)<>1 and claim_id='"
						+ searchValue.replace("'", "") + "'))";
			}

			// PATIENT ID
			if (searchCriteriaString.isEmpty()) {
				searchCriteriaString = " and (";
			} else {
				searchCriteriaString += " OR ";
			}
			searchCriteriaString += " ( p.patient_id = '" + searchValue.replace("'", "") + "')";
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
			searchCriteriaString += " and p.first_name like '" + searchValue.replace("'", "").split(" ")[1] + "%'";
		}
		searchCriteriaString += " ) ";

		// PID
		searchCriteriaString += " OR ( isnull(p.alternate_account,0)='" + searchValue + "' )";

		searchCriteriaString += " ) ";

		searchCriteriaString += " and p.practice_id='" + practice_id.toString() + "'";
		System.out.println("searchCriteriaString:" + searchCriteriaString);

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", searchCriteriaString, String.class, ParameterMode.IN));

		lstPatients = db.getStoreProcedureData("SearchPHRPatient_new", SearchPHRPatient.class, lstParam);

		return lstPatients;
	}
}