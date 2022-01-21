package com.ihc.ehr.service;

import java.util.List;

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

public interface SetupService {

	List<ORMgetAllDashBoardModule> getAllDashBoardModule(String user_id, String practice_id);

	List<ORMDashBoardModule> getUserDashBoardModule(String user_id, String practice_id);

	ServiceResponse<ORMKeyValue> saveDashboardSetting(List<ORMDashBoardSetting> ormdashSetting);
	
	List<ORMSetupAttorney> getSetupAttorney(String practice_id);

	List<ORMGetReferringProvider> getSetupRefferingPhysician(String practice_id);

	List<ORMSetupGuarantor> getSetupGuarantor(String practice_id);

	List<ORMSuperBillSetup> getSuperBillSetup(String practice_id);

	List<ORMSuperBillSetupCategory> getSuperBillCategorySetup(String bill_id);

	List<ORMSuperBillCategoryDetail> getSuperBillCategoryDetailSetup(String category_id);

	List<ORMSetupChartModuleSetting> getSetupChartModuleSetting(String practice_id);

	List<ORMGetSetupChartModuleSettingDetail> getSetupChartModuleSettingsDetail(String setting_id);

	List<ORMChartModuleAll> getSetupChartModuleAll();

	ServiceResponse<ORMKeyValue> saveEncounterSetting(ORMSetupChartModuleSetting objsave);

	ServiceResponse<ORMKeyValue> saveEncounterSettingDetail(List<ORMChartModuleTempSettingSave> lstmodule);

	ServiceResponse<ORMKeyValue> saveReferral(ORMSaveReferral ormSave);

	List<ORMGetUserAdministrationModules> getRoleAdministrationModules(String practice_id, String role_id);

	ServiceResponse<ORMKeyValue> SaveRoleAdministrationModules(List<ORMRSaveoleAdministrationModule> lstmodule);

	List<ORMGetRoleCDS> getPracticeRoleCDSRules(String practice_id, String role_id);

	ServiceResponse<ORMKeyValue> SaveRoleCDS(List<ORMRoleCDSRules> lstmodule);
	List<MAPIDGenerator> getClientPayerId(String practice_id);
	List<ORMInsurance_setup> getInsurances(SearchCriteria searchCriteria);
	ServiceResponse<ORMKeyValue> saveInsurance(ORMInsurance_setup ormSave);
	List<ORMInsurance_PayerTypes> getPayerType(String practice_id);
	List<ORMGetSetupInsuranceSearch> searchInsuranceSetup(SearchCriteria searchCriteria);
	long editWebsite(SearchCriteria searchCriteria);
	ServiceResponse<ORMKeyValue> saveGuarantor(ORMSaveGuarantor ormSave);

	ServiceResponse<ORMKeyValue> saveSuperBill(ORMSaveSuperBill ormSave);

	ServiceResponse<ORMKeyValue> saveSuperBillCategory(ORMSaveSuperBillCategory ormSave);

	ServiceResponse<ORMKeyValue> saveSuperBillCategoryDetail(List<ORMSaveSuperBillCategoryDetail> ormSave);

	List<ORMSetupLabCategory> getSuperLabCategory(String practice_id);

	List<ORMSetupSubLabCategory> getSetupSubLabCategory(String category_id);

	List<ORMSetupLabCategoryDetail> getSetupLabCategoryDetail(String category_id);

	ServiceResponse<ORMKeyValue> saveSetupLabCategory(ORMSetupLabCategory ormSave);

	ServiceResponse<ORMKeyValue> saveSetupSubLabCategory(ORMSetupSubLabCategory ormSave);
	
	ServiceResponse<ORMKeyValue> saveSetupLabCategoryDetail(List<ORMSetupLabCategoryDetail> ormSave);

	List<ORMSetupProvider> getSetupProvider(String practice_id);

	ServiceResponse<ORMKeyValue> saveSetupProvider(ORMSetupProvider ormSave);

	List<ORMSetupBillingProvider> getSetupBillingProvider(String practice_id);

	ServiceResponse<ORMKeyValue> saveSetupBillingProvider(ORMSetupBillingProvider ormSave);

	List<ORMSetupLocationGet> getSetupLocation(String practice_id);

	ServiceResponse<ORMKeyValue> saveSetupLocation(ORMSetupLocationSave ormSave);

	List<ORMSetupFacility> getSetupFacility(String practice_id);

	
	List<ORMGetTemplate> getTemplate(String practice_id, String type);
	ServiceResponse<ORMKeyValue> saveTemplateSetup(ORMGetTemplate ormSave);
	long DeleteTemplateProvider(SearchCriteria searchCriteria);
	ServiceResponse<ORMKeyValue> saveSetupFacility(ORMSetupFacility ormSave);

	List<ORMMyListICD> getSetupMyListICD(String practice_id, String provider_id);

	ServiceResponse<ORMKeyValue> saveSetupMyListICD(List<ORMMyListICD> ormSave);

	List<ORMMyListCPT> getSetupMyListCPT(String practice_id, String provider_id);

	ServiceResponse<ORMKeyValue> saveSetupMyListCPT(List<ORMMyListCPT> ormSave);
	ServiceResponse<ORMKeyValue> saveSelectedProvider(ORM_template_provider ormSave);
	List<ORMGetProvider_template> getTemplateDetails(String practice_id, String template_id);

	List<ORMUserConfigurableAppSettingGet> getUserConfigurableAppSettings(Long practice_id);

	ServiceResponse<ORMKeyValue> savetConfigurableAppSettings(List<ORMAppSettingSave> lstOrmSave);
	long deleteSeletedTemplate(SearchCriteria searchCriteria);

	long updateCategoryCodeType(SearchCriteria searchCriteria);

	List<ORMRestricted_code> getRestrictedcode(Long practice_id);

	ServiceResponse<ORMKeyValue> saveRestrictedCode(ORMRestricted_code lstOrmSave);

	List<ORMAdjustmentReasonCodes> getAdjustcode(Long practice_id);

	ServiceResponse<ORMKeyValue> saveAdjustCode(ORMAdjustmentReasonCodes lstOrmSave);

	List<ORMProcedureSetup> getprocedures(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> addProcedure(ORMProcedureSetup ormSave);

	ServiceResponse<ORMKeyValue> updateProcedure(ORMProcedureSetup ormSave);

	List<ORMDiagnosisSetup> getDiagnosis(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> addDiagnosis(ORMDiagnosisSetup ormSave);

	ServiceResponse<ORMKeyValue> updateDiagnosis(ORMDiagnosisSetup ormSave);

	List<ORMWriteOffCodes> getWriteOffcode(Long practice_id);

	ServiceResponse<ORMKeyValue> saveWriteOffcode(ORMWriteOffCodes lstOrmSave);
	List<ORMPractice> getAllPractices();
	List<ORMGetPracticeServices> GetPracticeServices(String practice_id);
	public Long saveupdatePractices(ORMPractice obj);
	public Long saveupdatePracticesServices(List<ORMPracticeServices> obj);
	List<ORMGetTemplateForProviderSettings> getTemplateProvider(SearchCriteria searchCriteria);
	ServiceResponse<ORMGetTemplate> saveProvTemplateSetup(ORMGetTemplate ormSave);
	ServiceResponse<ORM_template_provider> addEditProvTemplateSetup(ORM_template_provider ormSave);
	long deleteSeletedProvTemplate(SearchCriteria searchCriteria);	
}