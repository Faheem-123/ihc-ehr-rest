package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.MAPIDGenerator;
import com.ihc.ehr.model.ORMAdjustmentReasonCodes;
import com.ihc.ehr.model.ORMAppSettingSave;
import com.ihc.ehr.model.ORMChartModuleAll;
import com.ihc.ehr.model.ORMChartModuleTempSettingSave;
import com.ihc.ehr.model.ORMDashBoardModule;
import com.ihc.ehr.model.ORMDashBoardSetting;
import com.ihc.ehr.model.ORMDiagnosisSetup;
import com.ihc.ehr.model.ORMGetPracticeServices;
import com.ihc.ehr.model.ORMGetProvider_template;
import com.ihc.ehr.model.ORMGetReferringProvider;
import com.ihc.ehr.model.ORMGetRoleCDS;
import com.ihc.ehr.model.ORMGetSetupChartModuleSettingDetail;
import com.ihc.ehr.model.ORMGetSetupInsuranceSearch;
import com.ihc.ehr.model.ORMGetTemplate;
import com.ihc.ehr.model.ORMGetTemplateForProviderSettings;
import com.ihc.ehr.model.ORMGetUserAdministrationModules;
import com.ihc.ehr.model.ORMInsurance_PayerTypes;
import com.ihc.ehr.model.ORMInsurance_setup;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMMyListCPT;
import com.ihc.ehr.model.ORMMyListICD;
import com.ihc.ehr.model.ORMPatientNotSeen;
import com.ihc.ehr.model.ORMPractice;
import com.ihc.ehr.model.ORMPracticeServices;
import com.ihc.ehr.model.ORMProcedureSetup;
import com.ihc.ehr.model.ORMRSaveoleAdministrationModule;
import com.ihc.ehr.model.ORMRestricted_code;
import com.ihc.ehr.model.ORMRoleCDSRules;
import com.ihc.ehr.model.ORMSaveGuarantor;
import com.ihc.ehr.model.ORMSaveReferral;
import com.ihc.ehr.model.ORMSaveSuperBill;
import com.ihc.ehr.model.ORMSaveSuperBillCategory;
import com.ihc.ehr.model.ORMSaveSuperBillCategoryDetail;
import com.ihc.ehr.model.ORMSetupAttorney;
import com.ihc.ehr.model.ORMSetupBillingProvider;
import com.ihc.ehr.model.ORMSetupChartModuleSetting;
import com.ihc.ehr.model.ORMSetupFacility;
import com.ihc.ehr.model.ORMSetupGuarantor;
import com.ihc.ehr.model.ORMSetupLabCategory;
import com.ihc.ehr.model.ORMSetupLabCategoryDetail;
import com.ihc.ehr.model.ORMSetupLocationSave;
import com.ihc.ehr.model.ORMSetupLocationGet;
import com.ihc.ehr.model.ORMSetupProvider;
import com.ihc.ehr.model.ORMSetupSubLabCategory;
import com.ihc.ehr.model.ORMSuperBillCategoryDetail;
import com.ihc.ehr.model.ORMSuperBillSetup;
import com.ihc.ehr.model.ORMSuperBillSetupCategory;
import com.ihc.ehr.model.ORMUserConfigurableAppSettingGet;
import com.ihc.ehr.model.ORMWriteOffCodes;
import com.ihc.ehr.model.ORM_template_provider;
import com.ihc.ehr.model.ORMgetAllDashBoardModule;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@Repository
public class SetupDAOImpl implements SetupDAO {

	@Autowired
	DBOperations db;

	@Override
	public List<ORMgetAllDashBoardModule> getAllDashBoardModule(String user_id, String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("UserId", user_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetAllDashBoarModules", ORMgetAllDashBoardModule.class, lstParam);
	}

	@Override
	public List<ORMDashBoardModule> getUserDashBoardModule(String user_id, String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("UserId", user_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetUserDashBoarModules", ORMDashBoardModule.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveDashboardSetting(List<ORMDashBoardSetting> lstdashSetting) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		for (ORMDashBoardSetting ormdashSetting : lstdashSetting) {
			db.ExecuteUpdateQuery("update dashboar_settings set deleted=1 where user_id='" + ormdashSetting.getUser_id()
					+ "' and practice_id='" + ormdashSetting.getPractice_id() + "'");
			break;
		}
		for (ORMDashBoardSetting ormdashSetting : lstdashSetting) {
			ormdashSetting.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormdashSetting.getSetting_id())) {

				ormdashSetting.setSetting_id(db
						.IDGenerator("dashboar_settings", Long.parseLong(ormdashSetting.getPractice_id())).toString());
				ormdashSetting.setDate_created(DateTimeUtil.getCurrentDateTime());

				result = db.SaveEntity(ormdashSetting, Operation.ADD);
			} else {
				result = db.SaveEntity(ormdashSetting, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public List<ORMSetupAttorney> getSetupAttorney(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetAttorney_detail", ORMSetupAttorney.class, lstParam);
	}

	@Override
	public List<ORMGetReferringProvider> getSetupRefferingPhysician(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetSetupReferringProvider", ORMGetReferringProvider.class, lstParam);
	}

	@Override
	public List<ORMSetupGuarantor> getSetupGuarantor(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetSetupGuarantor", ORMSetupGuarantor.class, lstParam);
	}

	@Override
	public List<ORMSuperBillSetup> getSuperBillSetup(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetSuperBillSetup", ORMSuperBillSetup.class, lstParam);
	}

	@Override
	public List<ORMSuperBillSetupCategory> getSuperBillCategorySetup(String bill_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("bill_id", bill_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetSuperBillCat", ORMSuperBillSetupCategory.class, lstParam);
	}

	@Override
	public List<ORMSuperBillCategoryDetail> getSuperBillCategoryDetailSetup(String category_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("category_id", category_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetSuperBillCatDetail", ORMSuperBillCategoryDetail.class, lstParam);
	}

	@Override
	public List<ORMSetupChartModuleSetting> getSetupChartModuleSetting(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetSetupChartModuleSettings", ORMSetupChartModuleSetting.class, lstParam);
	}

	@Override
	public List<ORMGetSetupChartModuleSettingDetail> getSetupChartModuleSettingsDetail(String setting_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("setting_id", setting_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetSetupChartModuleSettingsDetail",
				ORMGetSetupChartModuleSettingDetail.class, lstParam);
	}

	@Override
	public List<ORMChartModuleAll> getSetupChartModuleAll() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetChartModules", ORMChartModuleAll.class, null);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveEncounterSetting(ORMSetupChartModuleSetting objsave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		objsave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(objsave.getSetting_id())) {

			objsave.setSetting_id(
					db.IDGenerator("chart_module_setting", Long.parseLong(objsave.getPractice_id())).toString());
			objsave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(objsave, Operation.ADD);
		} else {
			result = db.SaveEntity(objsave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(objsave.getSetting_id());
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveEncounterSettingDetail(List<ORMChartModuleTempSettingSave> lstmodule) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		for (ORMChartModuleTempSettingSave ormModule : lstmodule) {
			ormModule.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormModule.getChartmodule_setting_id())) {

				ormModule.setChartmodule_setting_id(
						db.IDGenerator("user_chartmodule_setting", Long.parseLong(ormModule.getPractice_id()))
								.toString());
				ormModule.setDate_created(DateTimeUtil.getCurrentDateTime());

				result = db.SaveEntity(ormModule, Operation.ADD);
			} else {
				result = db.SaveEntity(ormModule, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveReferral(ORMSaveReferral ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getReferral_id())) {

			ormSave.setReferral_id(
					db.IDGenerator("referring_provider", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getReferral_id());
		}

		return resp;
	}

	@Override
	public List<ORMGetUserAdministrationModules> getRoleAdministrationModules(String practice_id, String role_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("role_id", role_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetRoleAdministrationModules", ORMGetUserAdministrationModules.class,
				lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> SaveRoleAdministrationModules(List<ORMRSaveoleAdministrationModule> lstmodule) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		for (ORMRSaveoleAdministrationModule ormModule : lstmodule) {
			ormModule.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormModule.getId())) {

				ormModule.setId(db.IDGenerator("role_administration_module", Long.parseLong(ormModule.getPractice_id()))
						.toString());
				ormModule.setDate_created(DateTimeUtil.getCurrentDateTime());

				result = db.SaveEntity(ormModule, Operation.ADD);
			} else {
				result = db.SaveEntity(ormModule, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public List<ORMGetRoleCDS> getPracticeRoleCDSRules(String practice_id, String role_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("role_id", role_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetRoleCDSRules", ORMGetRoleCDS.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> SaveRoleCDS(List<ORMRoleCDSRules> lstmodule) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		for (ORMRoleCDSRules ormModule : lstmodule) {
			ormModule.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormModule.getId())) {

				ormModule
						.setId(db.IDGenerator("role_cds_rules", Long.parseLong(ormModule.getPractice_id())).toString());
				ormModule.setDate_modified(DateTimeUtil.getCurrentDateTime());

				result = db.SaveEntity(ormModule, Operation.ADD);
			} else {
				result = db.SaveEntity(ormModule, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public List<MAPIDGenerator> getClientPayerId(String practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetIhcClientPayerId", MAPIDGenerator.class, lstParam);
	}

	@Override
	public List<ORMInsurance_setup> getInsurances(SearchCriteria searchCriteria) {
		String criteria = " and practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "payerid":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += "and payerid ='" + pram.getValue() + "'";
						break;
					}
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		List<ORMInsurance_setup> lst = db.getStoreProcedureData("spGetInsurances", ORMInsurance_setup.class, lstParam);
		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveInsurance(ORMInsurance_setup ormSave) {
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getInsurance_id())) {
			ormSave.setInsurance_id(db.IDGeneratorInsurance("insurance", ormSave.getPractice_id()).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getInsurance_id());
		}

		return resp;
	}

	@Override
	public List<ORMInsurance_PayerTypes> getPayerType(String practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spPayerTypes", ORMInsurance_PayerTypes.class, lstParam);
	}

	@Override
	public List<ORMGetSetupInsuranceSearch> searchInsuranceSetup(SearchCriteria searchCriteria) {
		String criteria = "";
		//remove practice id coz -1 in everytable.
		//criteria = " and ins.practice_id = '" + searchCriteria.getPractice_id().toString() + "' ";
		String typeid="";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "name":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and ins.name like '%" + pram.getValue() + "%' ";
						break;
					}
				case "payer":
					if (pram.getValue() != "" && pram.getValue() != null) {
						//criteria += "and payer.payer_id = '" + pram.getValue() + "'";
						criteria += "and payer.payer_number = '" + pram.getValue() + "'";
						break;
					}
				case "payertype":
					if (pram.getValue() != "" && pram.getValue() != null) {
						typeid = pram.getValue();
						//criteria += " and pt.payertype_id = '" + pram.getValue() + "'";
						if(typeid.toString().equals("-1")) {
						}else {
							criteria += " and pt.payertype_id = '" + typeid + "'";
						}
						break;	
					}
				default:
					break;
				}
			}
		}
		
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		List<ORMGetSetupInsuranceSearch> lst = db.getStoreProcedureData("spgetSetupInsuranceSearch",
				ORMGetSetupInsuranceSearch.class, lstParam);
		return lst;
	}

	@Override
	public long editWebsite(SearchCriteria searchCriteria) {
		String website = "";
		String modified_user = "";
		String insurance_id = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("website")) {
					website = pram.getValue();
				} else if (pram.getName().equals("modified_user")) {
					modified_user = pram.getValue();
				} else if (pram.getName().equals("insurance_id")) {
					insurance_id = pram.getValue();
				}
			}

		}
		String Query = "update insurance set website='" + website + "',modified_user='" + modified_user
				+ "',date_modified=getdate() where insurance_id='" + insurance_id + "'";
		// String Query = "update patient_order set followup_detail = 'By "+
		// User.toUpperCase() +" ("+ DateTimeUtil.getCurrentDateTime() +")',
		// modified_user ='"+ User +"', date_modified = GETDATE(),
		// follow_up_notes='Letter Sent', follow_up_action='' where
		// CONVERT(varchar,order_date,101) = CONVERT(varchar,'"+ DOS +"',101) and
		// patient_id= '"+ patient_id +"' and follow_up_notes ='Follow Up Required' and
		// isnull(deleted,0)<>1 ";
		return db.ExecuteUpdateQuery(Query);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveGuarantor(ORMSaveGuarantor ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getGuarantor_id())) {

			ormSave.setGuarantor_id(db.IDGenerator("guarantor", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getGuarantor_id());
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSuperBill(ORMSaveSuperBill ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getBill_id())) {

			ormSave.setBill_id(db.IDGenerator("SuperBill", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getBill_id());
		}
		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSuperBillCategory(ORMSaveSuperBillCategory ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getCategory_id())) {

			ormSave.setCategory_id(
					db.IDGenerator("SuperBill_Category", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getCategory_id());
		}
		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSuperBillCategoryDetail(List<ORMSaveSuperBillCategoryDetail> lstSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		for (ORMSaveSuperBillCategoryDetail ormSave : lstSave) {
			ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormSave.getDetail_id())) {
				ormSave.setDetail_id(db
						.IDGenerator("SuperBill_CategoryDetail", Long.parseLong(ormSave.getPractice_id())).toString());
				ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(ormSave, Operation.ADD);
			} else {
				result = db.SaveEntity(ormSave, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
		}
		return resp;
	}

	@Override
	public List<ORMSetupLabCategory> getSuperLabCategory(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("PracticeId", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLabCategory", ORMSetupLabCategory.class, lstParam);
	}

	@Override
	public List<ORMSetupSubLabCategory> getSetupSubLabCategory(String category_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("category_id", category_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetSetupLabSubCategory", ORMSetupSubLabCategory.class, lstParam);
	}

	@Override
	public List<ORMSetupLabCategoryDetail> getSetupLabCategoryDetail(String category_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("sub_category_id", category_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLabSetupCategoryDetail", ORMSetupLabCategoryDetail.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupLabCategory(ORMSetupLabCategory ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getCategory_id())) {

			ormSave.setCategory_id(
					db.IDGenerator("lab_categories", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getCategory_id());
		}
		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupSubLabCategory(ORMSetupSubLabCategory ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getSub_category_id())) {

			ormSave.setSub_category_id(
					db.IDGenerator("lab_category_sub", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getSub_category_id());
		}
		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupLabCategoryDetail(List<ORMSetupLabCategoryDetail> lstSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		for (ORMSetupLabCategoryDetail ormSave : lstSave) {
			ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormSave.getDetail_id())) {
				ormSave.setDetail_id(
						db.IDGenerator("lab_category_detail", Long.parseLong(ormSave.getPractice_id())).toString());
				ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(ormSave, Operation.ADD);
			} else {
				result = db.SaveEntity(ormSave, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
		}
		return resp;
	}

	@Override
	public List<ORMSetupProvider> getSetupProvider(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetSetupProvider", ORMSetupProvider.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupProvider(ORMSetupProvider ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getProvider_id())) {

			ormSave.setProvider_id(db.IDGenerator("provider", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getProvider_id());
		}
		return resp;
	}

	@Override
	public List<ORMSetupBillingProvider> getSetupBillingProvider(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetSetupBillingProvider", ORMSetupBillingProvider.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupBillingProvider(ORMSetupBillingProvider ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getBilling_provider_id())) {

			ormSave.setBilling_provider_id(
					db.IDGenerator("billing_provider", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getBilling_provider_id());
		}
		return resp;
	}

	@Override
	public List<ORMSetupLocationGet> getSetupLocation(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetSetupLocation", ORMSetupLocationGet.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupLocation(ORMSetupLocationSave ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getLocation_id())) {

			ormSave.setLocation_id(db.IDGenerator("location", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getLocation_id());
		}
		return resp;
	}

	@Override
	public List<ORMSetupFacility> getSetupFacility(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetSetupFacility", ORMSetupFacility.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupFacility(ORMSetupFacility ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getFacility_id())) {

			ormSave.setFacility_id(db.IDGenerator("facilities", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getFacility_id());
		}
		return resp;
	}

	@Override
	public List<ORMGetTemplate> getTemplate(String practice_id, String type) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("type", type.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetTemplate", ORMGetTemplate.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveTemplateSetup(ORMGetTemplate ormSave) {
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getId())) {
			ormSave.setId(db.IDGenerator("template_text", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getId());
		}
		return resp;
	}

	@Override
	public long DeleteTemplateProvider(SearchCriteria searchCriteria) {
		String modified_user = "";
		String template_id = "";
		String id = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("id")) {
					id = pram.getValue();
				} else if (pram.getName().equals("template_id")) {
					template_id = pram.getValue();
				} else if (pram.getName().equals("modified_user")) {
					modified_user = pram.getValue();
				}
			}

		}
		// String Query = "update insurance set website='"+ website
		// +"',modified_user='"+ modified_user +"',date_modified=getdate() where
		// insurance_id='"+ insurance_id +"'";
		String Query = "update template_provider set deleted=1,date_modified=getdate(),modified_user='" + modified_user
				+ "' where practice_id='" + searchCriteria.getPractice_id().toString() + "' and template_id='"
				+ template_id + "' and id in (" + id + ")";
		// String Query = "update patient_order set followup_detail = 'By "+
		// User.toUpperCase() +" ("+ DateTimeUtil.getCurrentDateTime() +")',
		// modified_user ='"+ User +"', date_modified = GETDATE(),
		// follow_up_notes='Letter Sent', follow_up_action='' where
		// CONVERT(varchar,order_date,101) = CONVERT(varchar,'"+ DOS +"',101) and
		// patient_id= '"+ patient_id +"' and follow_up_notes ='Follow Up Required' and
		// isnull(deleted,0)<>1 ";
		return db.ExecuteUpdateQuery(Query);
	}

	@Override
	public List<ORMMyListICD> getSetupMyListICD(String practice_id, String provider_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("provider_id", provider_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetMylistIcd_10", ORMMyListICD.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupMyListICD(List<ORMMyListICD> lstSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		for (ORMMyListICD ormSave : lstSave) {
			ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormSave.getId())) {
				ormSave.setId(db.IDGenerator("mylist_icd_10", Long.parseLong(ormSave.getPractice_id())).toString());
				ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(ormSave, Operation.ADD);
			} else {
				result = db.SaveEntity(ormSave, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
		}
		return resp;
	}

	@Override
	public List<ORMMyListCPT> getSetupMyListCPT(String practice_id, String provider_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("provider_id", provider_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetMylistCpt", ORMMyListCPT.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupMyListCPT(List<ORMMyListCPT> lstSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		for (ORMMyListCPT ormSave : lstSave) {
			ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormSave.getId())) {
				ormSave.setId(db.IDGenerator("mylist_cpt", Long.parseLong(ormSave.getPractice_id())).toString());
				ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(ormSave, Operation.ADD);
			} else {
				result = db.SaveEntity(ormSave, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
		}
		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSelectedProvider(ORM_template_provider ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getId())) {

			ormSave.setId(db.IDGenerator("template_provider", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getId());
		}
		return resp;
	}

	@Override
	public List<ORMGetProvider_template> getTemplateDetails(String practice_id, String template_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("template_id", template_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("sp_GetProvider_Template", ORMGetProvider_template.class, lstParam);
	}

	@Override
	public List<ORMUserConfigurableAppSettingGet> getUserConfigurableAppSettings(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetUserConfigurableAppSetting", ORMUserConfigurableAppSettingGet.class,
				lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> savetConfigurableAppSettings(List<ORMAppSettingSave> lstOrmSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		for (ORMAppSettingSave ormSave : lstOrmSave) {
			ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormSave.getDetail_id())) {
				ormSave.setDetail_id(db.IDGenerator("app_setting_detail", ormSave.getPractice_id()));
				ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(ormSave, Operation.ADD);
			} else {
				result = db.SaveEntity(ormSave, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
		}
		return resp;
	}
	//@Override
	//public int deleteSeletedTemplate(ORMDeleteRecord objDelete) {
	//	String Query = "update template_text set deleted=1,modified_user= '" + objDelete.client_date_modified
	//			+ "',date_modified=getdate() where id= '"
	//			+ column_id + "' ";
	//	db.ExecuteUpdateQuery(Query);
	//	
	//	//return db.deleteRecord(objDelete);
	//}
	@Override
	public long deleteSeletedTemplate(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String column_id = "";
		String modified_user = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("column_id")) {
					column_id = pram.getValue();
				} else if (pram.getName().equals("modified_user")) {
					modified_user = pram.getValue();
				}
			}
		}
		String Query = "";
		Query = "update template_text set deleted=1,modified_user = '"
				+ modified_user + "',date_modified=getdate() where id = '" + column_id + "' ";
		return db.ExecuteUpdateQuery(Query);
	}

	@Override
	public long updateCategoryCodeType(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String category_id="";
		String code1Type="";
		String code2Type="";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) 
			{
				if (pram.getName().equals("category_id")) 
				{
					category_id = pram.getValue();
				} 
				else if (pram.getName().equals("code1Type")) 
				{					 
					if(GeneralOperations.isNotNullorEmpty(pram.getValue()))
					{
						code1Type = " code1_type='"+pram.getValue()+"' ";
					}
				}
				else if (pram.getName().equals("code2Type")) 
				{
					if(GeneralOperations.isNotNullorEmpty(pram.getValue()))
					{
						if(!code1Type.equals(""))
							code2Type=" , ";
						code2Type += " code2_type='"+pram.getValue()+"' ";
					}
				}
			}
		}
		String query = "";
		query = "update SuperBill_Category set "+ code1Type  + code2Type+" where category_id = '" + category_id + "' ";
		return db.ExecuteUpdateQuery(query);
	}

	@Override
	public List<ORMRestricted_code> getRestrictedcode(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetRestrictedCode", ORMRestricted_code.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveRestrictedCode(ORMRestricted_code ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getRestproc_id())) {

			ormSave.setRestproc_id(db.IDGenerator("restricted_procedure", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getRestproc_id());
		}
		return resp;
	}

	@Override
	public List<ORMAdjustmentReasonCodes> getAdjustcode(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetAdjustmentReasonCodesDetail", ORMAdjustmentReasonCodes.class,  null);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveAdjustCode(ORMAdjustmentReasonCodes ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getId())) {

			ormSave.setId(db.GenerateTableMaxID("adjustment_reason_codes","id"));
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getId());
		}
		return resp;
	}

	@Override
	public List<ORMProcedureSetup> getprocedures(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String search_type="";
		String search_value="";
		String criteria="";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) 
			{
				if (pram.getName().equals("search_option")) 
				{
					search_type = pram.getValue();
				} 
				else if (pram.getName().equals("search_value")) 
				{					 
					search_value = pram.getValue(); 
				}
			}
		}
		if(search_type.equals("check_code"))
		{
			criteria="and proc_code like '%"+search_value+"%'";
		}
		else if(search_type.equals("check_description"))
		{
			criteria="and description like '%"+search_value+"%'";
		}
		else if(search_type.equals("check_pos"))
		{
			criteria="and pos like '%"+search_value+"%'";
		}
		else if(search_type.equals("check_date"))
		{
			criteria=" and CONVERT(date,expiry_date)<CONVERT(date,GETDATE()) and CONVERT(date,expiry_date)<>'1900-01-01'";
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetProcedures", ORMProcedureSetup.class,  lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> addProcedure(ORMProcedureSetup ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

		result = db.SaveEntity(ormSave, Operation.ADD);
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getProc_code());
		}
		return resp;
	}
	@Override
	public ServiceResponse<ORMKeyValue> updateProcedure(ORMProcedureSetup ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
	
		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		result = db.SaveEntity(ormSave, Operation.EDIT);
	
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getProc_code());
		}
		return resp;
	}

	@Override
	public List<ORMDiagnosisSetup> getDiagnosis(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String search_type="";
		String search_value="";
		String criteria="";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) 
			{
				if (pram.getName().equals("search_option")) 
				{
					search_type = pram.getValue();
				} 
				else if (pram.getName().equals("search_value")) 
				{					 
					search_value = pram.getValue(); 
				}
			}
		}
		if(search_type.equals("check_code"))
		{
			criteria="and code like '%"+search_value+"%'";
		}
		else if(search_type.equals("check_description"))
		{
			criteria="and description like '%"+search_value+"%'";
		}
		else if(search_type.equals("check_date"))
		{
			criteria=" and CONVERT(date,expiry_date)<CONVERT(date,GETDATE())";
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetDiagnosisSetup", ORMDiagnosisSetup.class,  lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> addDiagnosis(ORMDiagnosisSetup ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

		result = db.SaveEntity(ormSave, Operation.ADD);
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getCode());
		}
		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> updateDiagnosis(ORMDiagnosisSetup ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
	
		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		result = db.SaveEntity(ormSave, Operation.EDIT);
	
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getCode());
		}
		return resp;
	}

	@Override
	public List<ORMWriteOffCodes> getWriteOffcode(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetWriteOffCodes", ORMWriteOffCodes.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveWriteOffcode(ORMWriteOffCodes ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getId())) {
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getId());
		}
		return resp;
	}
	@Override
	public List<ORMPractice> getAllPractices() {
		return db.getStoreProcedureData("spGetAllPractices", ORMPractice.class, null);
	}
	@Override
	public List<ORMGetPracticeServices> GetPracticeServices(String practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		practice_id = "";
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPracticeServices_new", ORMGetPracticeServices.class,  lstParam);
	}
	@Override
	public Long saveupdatePractices(ORMPractice obj) {
		System.out.println("**** saveupdatePractices: "+obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		obj.setDate_created(DateTimeUtil.getCurrentDateTime());
		
		if (obj.getPractice_id() != null && obj.getPractice_id() != "") {
			System.out.println("EDIT...");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return (Long.parseLong(obj.getPractice_id()));
			else
				return (long) 0;
		} else {
			obj.setPractice_id(db.GenerateTableMaxID("practice","practice_id"));
			System.out.println("SAVE...");
			if (db.SaveEntity(obj, Operation.ADD) > 0) {
				 
				List<SpParameters> lstParam = new ArrayList<>();
				lstParam.add(new SpParameters("newPracticeID", obj.getPractice_id(), String.class, ParameterMode.IN));
				lstParam.add(new SpParameters("user", obj.getCreated_user(), String.class, ParameterMode.IN));
				lstParam.add(new SpParameters("date", obj.getClient_date_created().split(" ")[0], String.class, ParameterMode.IN));
				db.ExecuteUpdateStoreProcedure("spNewPracticeConfiguration", lstParam);
				System.out.println("*** Insert new practice configuration settings Practice ID:"+ obj.getPractice_id() +" User: "+ obj.getCreated_user() +" Date: "+ obj.getClient_date_created() +"***");
				
				return (Long.parseLong(obj.getPractice_id()));
			}
			else
				return (long) 0;
		}
	}
	@Override
	public Long saveupdatePracticesServices(List<ORMPracticeServices> obj) {
			int result = 0;
			//Boolean isDel = false;
			if (obj != null && obj.size() > 0) {
				for (ORMPracticeServices orm : obj) {
					if (orm.getPractice_service_id() == null || orm.getPractice_service_id() == "") // New
					{
						orm.setPractice_service_id(db.GenerateTableMaxID("practice_services","practice_service_id"));
						orm.setDate_created(DateTimeUtil.getCurrentDateTime());
						result += db.SaveEntity(orm, Operation.ADD);
					} else// modification
					{
						result += db.SaveEntity(orm, Operation.EDIT);
					}
				}
			}
			//isDel = false;
			return (long) result;
	}
	
	@Override
	public List<ORMGetTemplateForProviderSettings> getTemplateProvider(SearchCriteria searchCriteria){
		String type="";
		String provider_id="";
		
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "type":
					if (pram.getValue() != "" && pram.getValue() != null) {
						type = pram.getValue();
					}
					break;
				case "provider_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						provider_id = pram.getValue();
					}
					break;
				default:
					break;
				}
			}
		}
		
		
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("type", type, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("provider_id", provider_id, String.class, ParameterMode.IN));
		List<ORMGetTemplateForProviderSettings> lst = db.getStoreProcedureData("spGetTemplateForProvider_Setting", ORMGetTemplateForProviderSettings.class, lstParam);
		return lst;
	}
	@Override
	public ServiceResponse<ORMGetTemplate> saveProvTemplateSetup(ORMGetTemplate ormSave) {
		int result = 0;
		ServiceResponse<ORMGetTemplate> resp = new ServiceResponse<ORMGetTemplate>();
		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getId())) {
			ormSave.setId(db.IDGenerator("template_text", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getId());
		}
		return resp;
	}
	@Override
	public ServiceResponse<ORM_template_provider> addEditProvTemplateSetup(ORM_template_provider ormSave) {
		int result = 0;
		ServiceResponse<ORM_template_provider> resp = new ServiceResponse<ORM_template_provider>();
		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormSave.getId())) {
			ormSave.setId(db.IDGenerator("template_provider", Long.parseLong(ormSave.getPractice_id())).toString());
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(ormSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormSave, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getId());
		}
		return resp;
	}
	@Override
	public long deleteSeletedProvTemplate(SearchCriteria searchCriteria){
		String template_id = "";
		String provider_id = "";
		String modified_user = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("template_id")) {
					template_id = pram.getValue();
				} else if (pram.getName().equals("provider_id")) {
					provider_id = pram.getValue();
				} else if (pram.getName().equals("modified_user")) {
					modified_user = pram.getValue();
				}
			}
		}
		String Query = "";
		Query = "update template_provider set deleted=1,modified_user = '"
				+ modified_user + "',date_modified=getdate() where template_id = '" + template_id + "' and provider_id = '" + provider_id + "' ";
		return db.ExecuteUpdateQuery(Query);
	}
}
