package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ORMCDSAlerts;
import com.ihc.ehr.model.ORMCDSRulesGetSave;
import com.ihc.ehr.model.ORMGetRoleUserCDSRules;
import com.ihc.ehr.model.ORMIdNameType;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientCDSMessage;
import com.ihc.ehr.model.ORMPatientGenderAgeLanguageGet;
import com.ihc.ehr.model.ORMScalarValue;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@Repository
public class CDSDAOImpl implements CDSDAO {

	@Autowired
	private DBOperations db;

	@Override
	public List<ORMPatientCDSMessage> RunCDSRules(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		// List<SpParameters> lstParam=new ArrayList<>();
		Long patient_id = null;
		Long practice_id = null;
		Long role_id = null;
		Long usre_id = null;
		String user_name = "";
		String clientDate = "";
		String system_ip = "";
		Boolean is_refresh = false;

		ORMPatientCDSMessage objCDSNotifications;
		List<ORMPatientCDSMessage> objListCDSNotifications = new ArrayList<>();
		List<ORMCDSAlerts> listCDSAlerts = new ArrayList<>();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("patient_id")) {
					patient_id = Long.parseLong(pram.getValue());
				} else if (pram.getName().equals("practice_id")) {
					practice_id = Long.parseLong(pram.getValue());
				} else if (pram.getName().equals("role_id")) {
					role_id = Long.parseLong(pram.getValue());
				} else if (pram.getName().equals("user_id")) {
					usre_id =Long.parseLong(pram.getValue());
				} else if (pram.getName().equals("user_name")) {
					user_name = pram.getValue();
				} else if (pram.getName().equals("clientDate")) {
					clientDate = pram.getValue();
				} else if (pram.getName().equals("system_ip")) {
					system_ip = pram.getValue();
				} else if (pram.getName().equals("is_refresh")) {
					is_refresh = Boolean.parseBoolean(pram.getValue());
				}
			}
		}
		List<ORMGetRoleUserCDSRules> listRules = getPracticeUserCDSRules(practice_id, role_id, usre_id);
		if (is_refresh) {
			String strQuery = " delete from cds_alerts where patient_id=" + patient_id;
			db.ExecuteUpdateQuery(strQuery);

			// ORMAuditLog objORMLog=new ORMAuditLog();
			// objORMLog.setAccess("Fetch Patient Alert");
			// objORMLog.setAccess_date(GenericOperations.CurrentDateTime());
			// objORMLog.setClient_access_date(clientDate);
			// objORMLog.setModule_name("Patient Alerts");
			// objORMLog.setPractice_id(practice_id);
			// objORMLog.setUser_name(user_name);
			// objORMLog.setSystem_ip(system_ip);
			// objORMLog.setPatient_id(patient_id);
			// GenericOperations objGeneral=new GenericOperations();
			// objGeneral.moduleAccess(objORMLog);
		} else {
			listCDSAlerts = getPatientCDSAlerts(practice_id, patient_id, clientDate);
			if (listCDSAlerts.isEmpty() && (listRules != null && listRules.size() > 0)) {
				// Appointment check
				String appPatId = this.db.getQuerySingleResult(
						" select top 1 patient_id from appointment where patient_id='" + patient_id
						+ "' and isnull(deleted,0)<>1 and convert(date,appointment_date)=convert(date,getdate())");
				// List<?> list = db.getQueryData("select top 1 patient_id as col1 from
				// appointment where patient_id='"+patient_id+"' and isnull(deleted,0)<>1 and
				// convert(date,appointment_date)=convert(date,getdate())",ORMOneColumnGeneric.class
				// );
				if (GeneralOperations.isNullorEmpty(appPatId)) {
					objCDSNotifications = new ORMPatientCDSMessage();
	                   objCDSNotifications.setPatient_id(patient_id);
	                   objCDSNotifications.setRule_id((long)-1);
	                   objCDSNotifications.setRule_notification("TODAY_APPOINTMENT_NOT_FOUND");
	                   objCDSNotifications.setRule_description_html("TODAY_APPOINTMENT_NOT_FOUND");
	                   objCDSNotifications.setDescription("TODAY_APPOINTMENT_NOT_FOUND");
	                   objCDSNotifications.setReference_link("");
	                   objCDSNotifications.setDiagnostic_therapeutic_link("");
	                   objCDSNotifications.setShow_reference_link(false);
	                   objCDSNotifications.setDescription("");
	                   objListCDSNotifications.add(objCDSNotifications); 
					return objListCDSNotifications;
				}
			}
		}
		
		// List<?> listCDSAlerts =
		// getPatientCDSAlerts(practice_id,patient_id,clientDate);
		// Boolean is_vital;
		// Boolean is_immunization;
		// Boolean is_problem;
		// Boolean is_procedure;
		// Boolean is_lab;
		// Boolean is_allergy;
		// Boolean is_medication;
		// String strImmunization_cvx_codes;
		// String strRuleCriteria;

		String patientGender = "";
		String patientAge = "";
		String patientLanguageCode = "";

		if (listRules != null && listRules.size() > 0) {

			ORMPatientGenderAgeLanguageGet patientInfo = getPatientGenderAgeLanguage(patient_id);
			if (patientInfo != null) {
				patientGender = patientInfo.getGender_code();
				if (patientInfo.getAge_year() > 0) {
					patientAge = "a:" + patientInfo.getAge_year();
				} else if (patientInfo.getAge_month() > 0) {
					patientAge = "mo:" + patientInfo.getAge_month();
				}

				if (GeneralOperations.isNotNullorEmpty(patientInfo.getLanguage_code())) {
					patientLanguageCode = patientInfo.getLanguage_code();
				} else {
					patientLanguageCode = "";
				}

			}

			for (ORMGetRoleUserCDSRules ormRules : (List<ORMGetRoleUserCDSRules>) listRules) {

				if (ormRules.getIs_active_role() != true || ormRules.getIs_active_user() != true) {

					System.out.println("Rule: " + ormRules.getRule_name() + " Role:" + ormRules.getIs_active_role()
					+ "  Usre:" + ormRules.getIs_active_user());
					continue;
				}

				Boolean AlertFound = false;
				if (listCDSAlerts != null && listCDSAlerts.size() > 0) {

					for (ORMCDSAlerts ormAlerts : listCDSAlerts) {
						System.out.println("Alert Rule _id: " +ormAlerts.getRule_id() + " Rule_id:" + ormRules.getRule_id());
						
						if (ormAlerts.getRule_id().equals(ormRules.getRule_id())) {

							if (ormAlerts.getShow()) {

								objCDSNotifications = new ORMPatientCDSMessage();
								objCDSNotifications.setPatient_id(patient_id);
								objCDSNotifications.setAge(patientAge);
								objCDSNotifications.setGender(patientGender);
								objCDSNotifications.setLanguage_code(patientLanguageCode);
								objCDSNotifications.setRule_id(ormRules.getRule_id());
								objCDSNotifications.setRule_notification(ormRules.getNotification());
								objCDSNotifications.setRule_description_html(ormRules.getDescription_html());
								objCDSNotifications.setDescription(ormRules.getDescription());
								objCDSNotifications
								.setDiagnostic_therapeutic_link(ormRules.getDiagnostic_therapeutic_link());
								objCDSNotifications.setReference_link(ormRules.getReference_link());
								objCDSNotifications.setShow_reference_link(ormRules.getShow_reference_link());
								objCDSNotifications.setDescription(ormRules.getDescription());

								objCDSNotifications.setIntervention_developer(ormRules.getIntervention_developer());
								objCDSNotifications.setFunding_source(ormRules.getFunding_source());
								objCDSNotifications.setRelease_version(ormRules.getRelease_version());
								objCDSNotifications.setCitation(ormRules.getCitation());

								objListCDSNotifications.add(objCDSNotifications);

								System.out.println(
										"Rule: " + ormRules.getRule_name() + " Found in Patient Alerts. *** SHOW:YES");

							} else {
								System.out.println(
										"Rule: " + ormRules.getRule_name() + " Found in Patient Alerts. *** SHOW:NO");
							}
							AlertFound = true;
							break;
						}
					}
				}
				if (AlertFound) {
					continue;
				} else {

					System.out.println("Rule: " + ormRules.getRule_name() + " Not Found in Patient Alerts.");

					if (GeneralOperations.isNotNullorEmpty(ormRules.getSp_name())) {
						String strProce = ormRules.getSp_name();

						List<SpParameters> lstParam = new ArrayList<>();
						lstParam.add(
								new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

						List<ORMScalarValue> lstPatient = db.getStoreProcedureData(strProce, ORMScalarValue.class,
								lstParam);

						if (lstPatient != null && lstPatient.size() > 0) {
							// Rules Criteria is fulfilled.....

							objCDSNotifications = new ORMPatientCDSMessage();
							objCDSNotifications.setPatient_id(patient_id);
							objCDSNotifications.setAge(patientAge);
							objCDSNotifications.setGender(patientGender);
							objCDSNotifications.setLanguage_code(patientLanguageCode);
							objCDSNotifications.setRule_id(ormRules.getRule_id());
							objCDSNotifications.setRule_notification(ormRules.getNotification());
							objCDSNotifications.setRule_description_html(ormRules.getDescription_html());
							objCDSNotifications.setDescription(ormRules.getDescription());
							objCDSNotifications.setReference_link(ormRules.getReference_link());
							objCDSNotifications
							.setDiagnostic_therapeutic_link(ormRules.getDiagnostic_therapeutic_link());
							objCDSNotifications.setShow_reference_link(ormRules.getShow_reference_link());
							objCDSNotifications.setDescription(ormRules.getDescription());

							objCDSNotifications.setIntervention_developer(ormRules.getIntervention_developer());
							objCDSNotifications.setFunding_source(ormRules.getFunding_source());
							objCDSNotifications.setRelease_version(ormRules.getRelease_version());
							objCDSNotifications.setCitation(ormRules.getCitation());

							objListCDSNotifications.add(objCDSNotifications);

							// add alert to freez table
							AddCDSAlerts(patient_id, practice_id, ormRules.getRule_id(), true, system_ip, user_name);
						} else {
							// add alert to freez table
							AddCDSAlerts(patient_id, practice_id, ormRules.getRule_id(), false, system_ip, user_name);
						}
					}
				}

			}
		}
		else
		{
			objCDSNotifications = new ORMPatientCDSMessage();
            objCDSNotifications.setPatient_id(patient_id);
            objCDSNotifications.setRule_id((long)-1);
            objCDSNotifications.setRule_notification("NO_ASSIGNED_CDS_FOUND");
            objCDSNotifications.setRule_description_html("NO_ASSIGNED_CDS_FOUND");
            objCDSNotifications.setDescription("NO_ASSIGNED_CDS_FOUND");
            objCDSNotifications.setReference_link("");
            objCDSNotifications.setDiagnostic_therapeutic_link("");
            objCDSNotifications.setShow_reference_link(false);
            objCDSNotifications.setDescription("");
            objListCDSNotifications.add(objCDSNotifications); 
		}
		return objListCDSNotifications;
	}

	@Override
	public List<ORMIdNameType> getCDCRulesList(Long practiceId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetCDSRulesList", ORMIdNameType.class, lstParam);
	}

	@Override
	public ORMCDSRulesGetSave getCDSRuleById(Long ruleId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("rule_id", ruleId.toString(), String.class, ParameterMode.IN));

		List<ORMCDSRulesGetSave> lst = db.getStoreProcedureData("spGetCDSRuleById", ORMCDSRulesGetSave.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveCDSRule(ORMCDSRulesGetSave ormCDSRulesGetSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		ormCDSRulesGetSave.setDate_modified(serverDateTime);

		if (GeneralOperations.isNullorEmpty(ormCDSRulesGetSave.getRule_id())) {

			ormCDSRulesGetSave.setRule_id(db.IDGenerator("cds_rules", ormCDSRulesGetSave.getPractice_id()));
			ormCDSRulesGetSave.setDate_created(serverDateTime);

			ormCDSRulesGetSave.setPractice_id((long) -1); // default is -1 for all practices
			result = db.SaveEntity(ormCDSRulesGetSave, Operation.ADD);
		} else {

			ormCDSRulesGetSave.setPractice_id((long) -1); // // default is -1 for all practices
			result = db.SaveEntity(ormCDSRulesGetSave, Operation.EDIT);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormCDSRulesGetSave.getRule_id().toString());
		}

		return resp;
	}

	private List<ORMCDSAlerts> getPatientCDSAlerts(Long practice_id, Long patient_id, String date) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("date", date, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetCDSRulesAlerts", ORMCDSAlerts.class, lstParam);
	}

	private List<ORMGetRoleUserCDSRules> getPracticeUserCDSRules(Long practice_id, Long role_id, Long user_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("role_id", role_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPracticeUserCDSRules", ORMGetRoleUserCDSRules.class, lstParam);

	}

	public int AddCDSAlerts(Long patient_id, Long practice_id, Long rule_id, Boolean show, String system_ip,
			String user) {

		try {
			String strQuery = " insert into cds_alerts (patient_id,rule_id,show,practice_id,system_ip,created_user,date_created) "
					+ " values ('" + patient_id + "','" + rule_id + "','" + show + "','" + practice_id + "','"
					+ system_ip + "','" + user + "',getdate() )";

			return db.ExecuteUpdateQuery(strQuery);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private ORMPatientGenderAgeLanguageGet getPatientGenderAgeLanguage(Long patientId) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		List<ORMPatientGenderAgeLanguageGet> lst = db.getStoreProcedureData("spGetPatientGenderAgeLanguage",
				ORMPatientGenderAgeLanguageGet.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}
}
