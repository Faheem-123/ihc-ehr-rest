package com.ihc.ehr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.SetupDAO;
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
import com.ihc.ehr.model.ServiceResponse;

@Service
public class SetupServiceImpl implements SetupService {

	@Autowired
	SetupDAO setupDOA;

	@Override
	public List<ORMgetAllDashBoardModule> getAllDashBoardModule(String user_id, String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getAllDashBoardModule(user_id,practice_id);
	}

	@Override
	public List<ORMDashBoardModule> getUserDashBoardModule(String user_id, String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getUserDashBoardModule(user_id,practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveDashboardSetting(List<ORMDashBoardSetting> ormdashSetting) {
		// TODO Auto-generated method stub
		return setupDOA.saveDashboardSetting(ormdashSetting);
	}

	@Override
	public List<ORMSetupAttorney> getSetupAttorney(String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupAttorney(practice_id);
	}

	@Override
	public List<ORMGetReferringProvider> getSetupRefferingPhysician(String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupRefferingPhysician(practice_id);
	}

	@Override
	public List<ORMSetupGuarantor> getSetupGuarantor(String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupGuarantor(practice_id);
	}

	@Override
	public List<ORMSuperBillSetup> getSuperBillSetup(String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSuperBillSetup(practice_id);
	}

	@Override
	public List<ORMSuperBillSetupCategory> getSuperBillCategorySetup(String bill_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSuperBillCategorySetup(bill_id);
	}

	@Override
	public List<ORMSuperBillCategoryDetail> getSuperBillCategoryDetailSetup(String category_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSuperBillCategoryDetailSetup(category_id);
	}

	@Override
	public List<ORMSetupChartModuleSetting> getSetupChartModuleSetting(String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupChartModuleSetting(practice_id);
	}

	@Override
	public List<ORMGetSetupChartModuleSettingDetail> getSetupChartModuleSettingsDetail(String setting_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupChartModuleSettingsDetail(setting_id);
	}

	@Override
	public List<ORMChartModuleAll> getSetupChartModuleAll() {
		// TODO Auto-generated method stub
		return setupDOA.getSetupChartModuleAll();
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveEncounterSetting(ORMSetupChartModuleSetting objsave) {
		// TODO Auto-generated method stub
		return setupDOA.saveEncounterSetting(objsave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveEncounterSettingDetail(List<ORMChartModuleTempSettingSave> lstmodule) {
		// TODO Auto-generated method stub
		return  setupDOA.saveEncounterSettingDetail(lstmodule);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveReferral(ORMSaveReferral ormSave) {
		// TODO Auto-generated method stub
		return  setupDOA.saveReferral(ormSave);
	}

	@Override
	public List<ORMGetUserAdministrationModules> getRoleAdministrationModules(String practice_id, String role_id) {
		// TODO Auto-generated method stub
		return setupDOA.getRoleAdministrationModules(practice_id,role_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> SaveRoleAdministrationModules(List<ORMRSaveoleAdministrationModule> lstmodule) {
		// TODO Auto-generated method stub
		return  setupDOA.SaveRoleAdministrationModules(lstmodule);
	}

	@Override
	public List<ORMGetRoleCDS> getPracticeRoleCDSRules(String practice_id, String role_id) {
		// TODO Auto-generated method stub
		return setupDOA.getPracticeRoleCDSRules(practice_id,role_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> SaveRoleCDS(List<ORMRoleCDSRules> lstmodule) {
		// TODO Auto-generated method stub
		return  setupDOA.SaveRoleCDS(lstmodule);
	}
	@Override
	public List<MAPIDGenerator> getClientPayerId(String practice_id) {
		return setupDOA.getClientPayerId(practice_id);
	}
	@Override
	public List<ORMInsurance_setup> getInsurances(SearchCriteria searchCriteria) {
		return setupDOA.getInsurances(searchCriteria);
	}
	@Override
	public ServiceResponse<ORMKeyValue> saveInsurance(ORMInsurance_setup ormSave) {
		return setupDOA.saveInsurance(ormSave);
	}
	@Override
	public List<ORMInsurance_PayerTypes> getPayerType(String practice_id) {
		return setupDOA.getPayerType(practice_id);
	}
	@Override
	public List<ORMGetSetupInsuranceSearch> searchInsuranceSetup(SearchCriteria searchCriteria) {
		return setupDOA.searchInsuranceSetup(searchCriteria);
	}
	@Override
	public long editWebsite(SearchCriteria searchCriteria) {
		return setupDOA.editWebsite(searchCriteria);
	}
	@Override
	public ServiceResponse<ORMKeyValue> saveGuarantor(ORMSaveGuarantor ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveGuarantor(ormSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSuperBill(ORMSaveSuperBill ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSuperBill(ormSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSuperBillCategory(ORMSaveSuperBillCategory ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSuperBillCategory(ormSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSuperBillCategoryDetail(List<ORMSaveSuperBillCategoryDetail> ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSuperBillCategoryDetail(ormSave);
	}

	@Override
	public List<ORMSetupLabCategory> getSuperLabCategory(String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSuperLabCategory(practice_id);
	}

	@Override
	public List<ORMSetupSubLabCategory> getSetupSubLabCategory(String category_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupSubLabCategory(category_id);
	}

	@Override
	public List<ORMSetupLabCategoryDetail> getSetupLabCategoryDetail(String category_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupLabCategoryDetail(category_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupLabCategory(ORMSetupLabCategory ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSetupLabCategory(ormSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupSubLabCategory(ORMSetupSubLabCategory ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSetupSubLabCategory(ormSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupLabCategoryDetail(List<ORMSetupLabCategoryDetail> ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSetupLabCategoryDetail(ormSave);
	}

	@Override
	public List<ORMSetupProvider> getSetupProvider(String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupProvider(practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupProvider(ORMSetupProvider ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSetupProvider(ormSave);
	}

	@Override
	public List<ORMSetupBillingProvider> getSetupBillingProvider(String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupBillingProvider(practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupBillingProvider(ORMSetupBillingProvider ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSetupBillingProvider(ormSave);
	}

	@Override
	public List<ORMSetupLocationGet> getSetupLocation(String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupLocation(practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupLocation(ORMSetupLocationSave ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSetupLocation(ormSave);
	}

	@Override
	public List<ORMSetupFacility> getSetupFacility(String practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupFacility(practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupFacility(ORMSetupFacility ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSetupFacility(ormSave);
	}
	@Override
	public List<ORMGetTemplate> getTemplate(String practice_id, String type) {
		return setupDOA.getTemplate(practice_id, type);
	}
	@Override
	public ServiceResponse<ORMKeyValue> saveTemplateSetup(ORMGetTemplate ormSave) {
		return setupDOA.saveTemplateSetup(ormSave);
	}
	@Override
	public long DeleteTemplateProvider(SearchCriteria searchCriteria) {
		return setupDOA.DeleteTemplateProvider(searchCriteria);
	}
	@Override
	public List<ORMMyListICD> getSetupMyListICD(String practice_id, String provider_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupMyListICD(practice_id,provider_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupMyListICD(List<ORMMyListICD> ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSetupMyListICD(ormSave);
	}

	@Override
	public List<ORMMyListCPT> getSetupMyListCPT(String practice_id, String provider_id) {
		// TODO Auto-generated method stub
		return setupDOA.getSetupMyListCPT(practice_id,provider_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSetupMyListCPT(List<ORMMyListCPT> ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveSetupMyListCPT(ormSave);
	}
	@Override
	public ServiceResponse<ORMKeyValue> saveSelectedProvider(ORM_template_provider ormSave) {
		return setupDOA.saveSelectedProvider(ormSave);
	}
	@Override
	public List<ORMGetProvider_template> getTemplateDetails(String practice_id, String template_id) {
		return setupDOA.getTemplateDetails(practice_id,template_id);
	}

	@Override
	public List<ORMUserConfigurableAppSettingGet> getUserConfigurableAppSettings(Long practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getUserConfigurableAppSettings(practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> savetConfigurableAppSettings(List<ORMAppSettingSave> lstOrmSave) {
		// TODO Auto-generated method stub
		return setupDOA.savetConfigurableAppSettings(lstOrmSave);
	}
	@Override
	public long deleteSeletedTemplate(SearchCriteria searchCriteria) {
		return setupDOA.deleteSeletedTemplate(searchCriteria);
	}

	@Override
	public long updateCategoryCodeType(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return setupDOA.updateCategoryCodeType(searchCriteria);
	}

	@Override
	public List<ORMRestricted_code> getRestrictedcode(Long practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getRestrictedcode(practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveRestrictedCode(ORMRestricted_code OrmSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveRestrictedCode(OrmSave);
	}

	@Override
	public List<ORMAdjustmentReasonCodes> getAdjustcode(Long practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getAdjustcode(practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveAdjustCode(ORMAdjustmentReasonCodes OrmSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveAdjustCode(OrmSave);
	}

	@Override
	public List<ORMProcedureSetup> getprocedures(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return setupDOA.getprocedures(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> addProcedure(ORMProcedureSetup ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.addProcedure(ormSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> updateProcedure(ORMProcedureSetup ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.updateProcedure(ormSave);
	}

	@Override
	public List<ORMDiagnosisSetup> getDiagnosis(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return setupDOA.getDiagnosis(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> addDiagnosis(ORMDiagnosisSetup ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.addDiagnosis(ormSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> updateDiagnosis(ORMDiagnosisSetup ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.updateDiagnosis(ormSave);
	}

	@Override
	public List<ORMWriteOffCodes> getWriteOffcode(Long practice_id) {
		// TODO Auto-generated method stub
		return setupDOA.getWriteOffcode(practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveWriteOffcode(ORMWriteOffCodes ormSave) {
		// TODO Auto-generated method stub
		return setupDOA.saveWriteOffcode(ormSave);
	}
	@Override
	public List<ORMPractice> getAllPractices() {
		return setupDOA.getAllPractices();
	}
	@Override
	public List<ORMGetPracticeServices> GetPracticeServices(String practice_id) {
		return setupDOA.GetPracticeServices(practice_id);
	}
	@Override
	public Long saveupdatePractices(ORMPractice obj) {
		return setupDOA.saveupdatePractices(obj);
	}
	@Override
	public Long saveupdatePracticesServices(List<ORMPracticeServices> obj) {
		return setupDOA.saveupdatePracticesServices(obj);
	}
	@Override
	public List<ORMGetTemplateForProviderSettings> getTemplateProvider(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return setupDOA.getTemplateProvider(searchCriteria);
	}
	@Override
	public ServiceResponse<ORMGetTemplate> saveProvTemplateSetup(ORMGetTemplate ormSave) {
		return setupDOA.saveProvTemplateSetup(ormSave);
	}
	@Override
	public ServiceResponse<ORM_template_provider> addEditProvTemplateSetup(ORM_template_provider ormSave) {
		return setupDOA.addEditProvTemplateSetup(ormSave);
	}
	@Override
	public long deleteSeletedProvTemplate(SearchCriteria searchCriteria) {
		return setupDOA.deleteSeletedProvTemplate(searchCriteria);
	}
}