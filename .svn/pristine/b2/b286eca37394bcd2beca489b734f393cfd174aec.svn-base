package com.ihc.ehr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihc.ehr.model.MAPIDGenerator;
import com.ihc.ehr.model.ORMAdjustmentReasonCodes;
import com.ihc.ehr.model.ORMAppSettingSave;
import com.ihc.ehr.model.ORMChartModuleAll;
import com.ihc.ehr.model.ORMChartModuleTempSettingSave;
import com.ihc.ehr.model.ORMDashBoardModule;
import com.ihc.ehr.model.ORMDashBoardSetting;
import com.ihc.ehr.model.ORMDeleteRecord;
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
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.service.SetupService;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@RestController
@RequestMapping("/setup")
public class SetupController {
	@Autowired
	SetupService setupService;
	@Autowired
	GeneralService generalService;
	@RequestMapping("getAllDashBoardModule/{user_id}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMgetAllDashBoardModule>> getAllDashBoardModule(
			@PathVariable(value = "user_id") String user_id, 
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMgetAllDashBoardModule> lst = setupService.getAllDashBoardModule(user_id, practice_id);
		return new ResponseEntity<List<ORMgetAllDashBoardModule>>(lst, HttpStatus.OK);
	}
	@RequestMapping("getUserDashBoardModule/{user_id}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMDashBoardModule>> getUserDashBoardModule(
			@PathVariable(value = "user_id") String user_id, 
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMDashBoardModule> lst = setupService.getUserDashBoardModule(user_id, practice_id);
		return new ResponseEntity<List<ORMDashBoardModule>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveDashboardSetting")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveDashboardSetting(
			@RequestBody List<ORMDashBoardSetting> ormdashSetting) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveDashboardSetting(ormdashSetting);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping("getSetupAttorney/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMSetupAttorney>> getSetupAttorney(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMSetupAttorney> lst = setupService.getSetupAttorney(practice_id);
		return new ResponseEntity<List<ORMSetupAttorney>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getSetupRefferingPhysician/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetReferringProvider>> getSetupRefferingPhysician(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMGetReferringProvider> lst = setupService.getSetupRefferingPhysician(practice_id);
		return new ResponseEntity<List<ORMGetReferringProvider>>(lst, HttpStatus.OK);
	}
	@RequestMapping("getSetupGuarantor/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMSetupGuarantor>> getSetupGuarantor(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMSetupGuarantor> lst = setupService.getSetupGuarantor(practice_id);
		return new ResponseEntity<List<ORMSetupGuarantor>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveGuarantor")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveGuarantor(
			@RequestBody ORMSaveGuarantor ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveGuarantor(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteGuarantor")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteGuarantor(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("guarantor");
		ormDeleteRecord.setColumn_name("guarantor_id");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while delete guarantor.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("getSuperBillSetup/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMSuperBillSetup>> getSuperBillSetup(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMSuperBillSetup> lst = setupService.getSuperBillSetup(practice_id);
		return new ResponseEntity<List<ORMSuperBillSetup>>(lst, HttpStatus.OK);
	}
	@RequestMapping("getSuperBillCategorySetup/{bill_id}")
	public @ResponseBody ResponseEntity<List<ORMSuperBillSetupCategory>> getSuperBillCategorySetup(
			@PathVariable(value = "bill_id") String bill_id) {

		List<ORMSuperBillSetupCategory> lst = setupService.getSuperBillCategorySetup(bill_id);
		return new ResponseEntity<List<ORMSuperBillSetupCategory>>(lst, HttpStatus.OK);
	}
	@RequestMapping("getSuperBillCategoryDetailSetup/{category_id}")
	public @ResponseBody ResponseEntity<List<ORMSuperBillCategoryDetail>> getSuperBillCategoryDetailSetup(
			@PathVariable(value = "category_id") String category_id) {

		List<ORMSuperBillCategoryDetail> lst = setupService.getSuperBillCategoryDetailSetup(category_id);
		return new ResponseEntity<List<ORMSuperBillCategoryDetail>>(lst, HttpStatus.OK);
	}
	@RequestMapping("getSetupChartModuleSetting/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMSetupChartModuleSetting>> getSetupChartModuleSetting(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMSetupChartModuleSetting> lst = setupService.getSetupChartModuleSetting(practice_id);
		return new ResponseEntity<List<ORMSetupChartModuleSetting>>(lst, HttpStatus.OK);
	}
	@RequestMapping("getSetupChartModuleSettingsDetail/{setting_id}")
	public @ResponseBody ResponseEntity<List<ORMGetSetupChartModuleSettingDetail>> getSetupChartModuleSettingsDetail(
			@PathVariable(value = "setting_id") String setting_id) {

		List<ORMGetSetupChartModuleSettingDetail> lst = setupService.getSetupChartModuleSettingsDetail(setting_id);
		return new ResponseEntity<List<ORMGetSetupChartModuleSettingDetail>>(lst, HttpStatus.OK);
	}
	@RequestMapping("getSetupChartModuleAll")
	public @ResponseBody ResponseEntity<List<ORMChartModuleAll>> getSetupChartModuleAll() {

		List<ORMChartModuleAll> lst = setupService.getSetupChartModuleAll();
		return new ResponseEntity<List<ORMChartModuleAll>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("getPracticeRoleCDSRules/{practice_id}/{role_id}")
	public @ResponseBody ResponseEntity<List<ORMGetRoleCDS>> getPracticeRoleCDSRules(
			@PathVariable(value = "practice_id") String practice_id
			,@PathVariable(value = "role_id") String role_id) {

		List<ORMGetRoleCDS> lst = setupService.getPracticeRoleCDSRules(practice_id,role_id);
		return new ResponseEntity<List<ORMGetRoleCDS>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("/SaveRoleCDS")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> SaveRoleCDS(
			@RequestBody List<ORMRoleCDSRules> lstmodule) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.SaveRoleCDS(lstmodule);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("getRoleAdministrationModules/{practice_id}/{role_id}")
	public @ResponseBody ResponseEntity<List<ORMGetUserAdministrationModules>> getRoleAdministrationModules(
			@PathVariable(value = "practice_id") String practice_id
			,@PathVariable(value = "role_id") String role_id) {

		List<ORMGetUserAdministrationModules> lst = setupService.getRoleAdministrationModules(practice_id,role_id);
		return new ResponseEntity<List<ORMGetUserAdministrationModules>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/SaveRoleAdministrationModules")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> SaveRoleAdministrationModules(
			@RequestBody List<ORMRSaveoleAdministrationModule> lstmodule) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.SaveRoleAdministrationModules(lstmodule);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping("/saveEncounterSetting")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveEncounterSetting(
			@RequestBody ORMSetupChartModuleSetting objsave) {
	

		ServiceResponse<ORMKeyValue> resp=setupService.saveEncounterSetting(objsave); 		
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
		
	}	
	
	@RequestMapping("/saveEncounterSettingDetail")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveEncounterSettingDetail(
			@RequestBody List<ORMChartModuleTempSettingSave> lstmodule) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveEncounterSettingDetail(lstmodule);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteEncounterSetting")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteEncounterSetting(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("chart_module_setting");
		ormDeleteRecord.setColumn_name("setting_id");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteEncounterSetting.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("/deleteEncounterSettingModule")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteEncounterSettingModule(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("user_chartmodule_setting");
		ormDeleteRecord.setColumn_name("chartmodule_setting_id");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteEncounterSettingModule.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	
	@RequestMapping("/deleteEncounterSettingPage")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteEncounterSettingPage(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("user_chartmodule_setting");
		ormDeleteRecord.setColumn_name("chartmodule_setting_id");
		
		String qry="update user_chartmodule_setting set deleted=1,modified_user='"+ormDeleteRecord.getModified_user()+"',date_modified=getdate() where chartmodule_setting_id in("+ormDeleteRecord.getColumn_id()+")";
		int result= generalService.updateQuery(qry);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteEncounterSettingModule.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("/saveReferral")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveReferral(
			@RequestBody ORMSaveReferral ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveReferral(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteReferral")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteReferral(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("referring_provider");
		ormDeleteRecord.setColumn_name("referral_id");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteReferral.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("getClientPayerId/{practice_id}")
	public @ResponseBody ResponseEntity<List<MAPIDGenerator>> getClientPayerId(
			@PathVariable(value = "practice_id") String practice_id) {
		List<MAPIDGenerator> lst = setupService.getClientPayerId(practice_id);
		return new ResponseEntity<List<MAPIDGenerator>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/getInsurances")
	public @ResponseBody ResponseEntity<List<ORMInsurance_setup>> getInsurances(
			@RequestBody SearchCriteria searchCriteria)	{	
		List<ORMInsurance_setup> lst=setupService.getInsurances(searchCriteria);
		return new ResponseEntity<List<ORMInsurance_setup>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/saveInsurance")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveInsurance(
			@RequestBody ORMInsurance_setup ormSave) {
		ServiceResponse<ORMKeyValue> resp = setupService.saveInsurance(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("getPayerType/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMInsurance_PayerTypes>> getPayerType(
			@PathVariable(value = "practice_id") String practice_id) {
		List<ORMInsurance_PayerTypes> lst = setupService.getPayerType(practice_id);
		return new ResponseEntity<List<ORMInsurance_PayerTypes>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/searchInsuranceSetup")
	public @ResponseBody ResponseEntity<List<ORMGetSetupInsuranceSearch>> searchInsuranceSetup(
			@RequestBody SearchCriteria searchCriteria)	{	
		List<ORMGetSetupInsuranceSearch> lst=setupService.searchInsuranceSetup(searchCriteria);
		return new ResponseEntity<List<ORMGetSetupInsuranceSearch>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/editWebsite")
	public long editWebsite(@RequestBody SearchCriteria searchCriteria) {
		return setupService.editWebsite(searchCriteria);

	}
	@RequestMapping("/saveSuperBill")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSuperBill(
			@RequestBody ORMSaveSuperBill ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSuperBill(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSuperBill")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSuperBill(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("superbill");
		ormDeleteRecord.setColumn_name("bill_id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSuperBill.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("/saveSuperBillCategory")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSuperBillCategory(
			@RequestBody ORMSaveSuperBillCategory ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSuperBillCategory(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSuperBillCategory")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSuperBillCategory(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("SuperBill_Category");
		ormDeleteRecord.setColumn_name("category_id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSuperBillCategory.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("/saveSuperBillCategoryDetail")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSuperBillCategoryDetail(
			@RequestBody List<ORMSaveSuperBillCategoryDetail> ormSave  ) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSuperBillCategoryDetail(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSuperBillCategoryDetail")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSuperBillCategoryDetail(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("SuperBill_CategoryDetail");
		ormDeleteRecord.setColumn_name("detail_id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSuperBillCategoryDetail.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	
	@RequestMapping("getSetupLabCategory/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMSetupLabCategory>> getSuperLabCategory(
			@PathVariable(value = "practice_id") String practice_id) 
	{
		List<ORMSetupLabCategory> lst = setupService.getSuperLabCategory(practice_id);
		return new ResponseEntity<List<ORMSetupLabCategory>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveSetupLabCategory")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSetupLabCategory(
			@RequestBody ORMSetupLabCategory ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSetupLabCategory(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSetupLabCategory")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSetupLabCategory(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("lab_categories");
		ormDeleteRecord.setColumn_name("category_id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSetupLabCategory.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	
	@RequestMapping("getSetupSubLabCategory/{category_id}")
	public @ResponseBody ResponseEntity<List<ORMSetupSubLabCategory>> getSetupSubLabCategory(
			@PathVariable(value = "category_id") String category_id) {

		List<ORMSetupSubLabCategory> lst = setupService.getSetupSubLabCategory(category_id);
		return new ResponseEntity<List<ORMSetupSubLabCategory>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveSetupSubLabCategory")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSetupSubLabCategory(
			@RequestBody ORMSetupSubLabCategory ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSetupSubLabCategory(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSetupLabCategorysub")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSetupLabCategorysub(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("lab_category_sub");
		ormDeleteRecord.setColumn_name("sub_category_id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSetupLabCategorysub.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("getSetupLabCategoryDetail/{category_id}")
	public @ResponseBody ResponseEntity<List<ORMSetupLabCategoryDetail>> getSetupLabCategoryDetail(
			@PathVariable(value = "category_id") String category_id) {

		List<ORMSetupLabCategoryDetail> lst = setupService.getSetupLabCategoryDetail(category_id);
		return new ResponseEntity<List<ORMSetupLabCategoryDetail>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveSetupLabCategoryDetail")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSetupLabCategoryDetail(
			@RequestBody List<ORMSetupLabCategoryDetail> ormSave  ) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSetupLabCategoryDetail(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSetupLabCategoryDetail")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSetupLabCategoryDetail(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("lab_category_detail");
		ormDeleteRecord.setColumn_name("detail_id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSetupLabCategoryDetail.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	
	@RequestMapping("getSetupProvider/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMSetupProvider>> getSetupProvider(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMSetupProvider> lst = setupService.getSetupProvider(practice_id);
		return new ResponseEntity<List<ORMSetupProvider>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveSetupProvider")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSetupProvider(
			@RequestBody ORMSetupProvider ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSetupProvider(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSetupProvider")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSetupProvider(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("provider");
		ormDeleteRecord.setColumn_name("provider_id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSetupProvider.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("getSetupBillingProvider/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMSetupBillingProvider>> getSetupBillingProvider(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMSetupBillingProvider> lst = setupService.getSetupBillingProvider(practice_id);
		return new ResponseEntity<List<ORMSetupBillingProvider>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveSetupBillingProvider")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSetupBillingProvider(
			@RequestBody ORMSetupBillingProvider ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSetupBillingProvider(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSetupBillingProvider")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSetupBillingProvider(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("billing_provider");
		ormDeleteRecord.setColumn_name("billing_provider_id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSetupBillingProvider.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	
	@RequestMapping("getSetupLocation/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMSetupLocationGet>> getSetupLocation(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMSetupLocationGet> lst = setupService.getSetupLocation(practice_id);
		return new ResponseEntity<List<ORMSetupLocationGet>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveSetupLocation")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSetupLocation(
			@RequestBody ORMSetupLocationSave ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSetupLocation(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSetupLocation")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSetupLocation(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("location");
		ormDeleteRecord.setColumn_name("location_id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSetupLocation.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("getSetupFacility/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMSetupFacility>> getSetupFacility(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMSetupFacility> lst = setupService.getSetupFacility(practice_id);
		return new ResponseEntity<List<ORMSetupFacility>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveSetupFacility")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSetupFacility(
			@RequestBody ORMSetupFacility ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSetupFacility(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSetupFacility")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSetupFacility(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("facilities");
		ormDeleteRecord.setColumn_name("facility_id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSetupLocation.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("getTemplate/{practice_id}/{type}")
	public @ResponseBody ResponseEntity<List<ORMGetTemplate>> getTemplate(
			@PathVariable(value = "practice_id") String practice_id
			,@PathVariable(value = "type") String type) {
		List<ORMGetTemplate> lst = setupService.getTemplate(practice_id,type);
		return new ResponseEntity<List<ORMGetTemplate>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveTemplateSetup")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveTemplateSetup(
			@RequestBody ORMGetTemplate ormSave) {
		ServiceResponse<ORMKeyValue> resp = setupService.saveTemplateSetup(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/DeleteTemplateProvider")
	public long DeleteTemplateProvider(@RequestBody SearchCriteria searchCriteria) {
		return setupService.DeleteTemplateProvider(searchCriteria);

	}
	@RequestMapping("getSetupMyListICD/{practice_id}/{provider_id}")
	public @ResponseBody ResponseEntity<List<ORMMyListICD>> getSetupMyListICD(
			@PathVariable(value = "practice_id") String practice_id,@PathVariable(value = "provider_id") String provider_id) {

		List<ORMMyListICD> lst = setupService.getSetupMyListICD(practice_id,provider_id);
		return new ResponseEntity<List<ORMMyListICD>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveSetupMyListICD")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSetupMyListICD(
			@RequestBody List<ORMMyListICD> ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSetupMyListICD(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSetupMyListICD")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSetupMyListICD(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("mylist_icd_10");
		ormDeleteRecord.setColumn_name("id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSetupLocation.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("getSetupMyListCPT/{practice_id}/{provider_id}")
	public @ResponseBody ResponseEntity<List<ORMMyListCPT>> getSetupMyListCPT(
			@PathVariable(value = "practice_id") String practice_id,@PathVariable(value = "provider_id") String provider_id) {

		List<ORMMyListCPT> lst = setupService.getSetupMyListCPT(practice_id,provider_id);
		return new ResponseEntity<List<ORMMyListCPT>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveSetupMyListCPT")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSetupMyListCPT(
			@RequestBody List<ORMMyListCPT> ormSave) {
		
		ServiceResponse<ORMKeyValue> resp = setupService.saveSetupMyListCPT(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSetupMyListCPT")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteSetupMyListCPT(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("mylist_cpt");
		ormDeleteRecord.setColumn_name("id");
		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteSetupLocation.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("/saveSelectedProvider")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveSelectedProvider(
			@RequestBody ORM_template_provider ormSave) {
		ServiceResponse<ORMKeyValue> resp = setupService.saveSelectedProvider(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("getTemplateDetails/{practice_id}/{template_id}")
	public @ResponseBody ResponseEntity<List<ORMGetProvider_template>> getTemplateDetails(
			@PathVariable(value = "practice_id") String practice_id,@PathVariable(value = "template_id") String template_id) {
		List<ORMGetProvider_template> lst = setupService.getTemplateDetails(practice_id,template_id);
		return new ResponseEntity<List<ORMGetProvider_template>>(lst, HttpStatus.OK);
	}
	
	
	@RequestMapping("getUserConfigurableAppSettings/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMUserConfigurableAppSettingGet>> getUserConfigurableAppSettings(
			@PathVariable(value = "practice_id") Long practice_id) {

		List<ORMUserConfigurableAppSettingGet> lst = setupService.getUserConfigurableAppSettings(practice_id);
		return new ResponseEntity<List<ORMUserConfigurableAppSettingGet>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping("savetConfigurableAppSettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> savetConfigurableAppSettings(
			@RequestBody List<ORMAppSettingSave> lstOrmSave) {

		ServiceResponse<ORMKeyValue> resp = setupService.savetConfigurableAppSettings(lstOrmSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	/*@RequestMapping("/deleteSeletedTemplate")
	public int deleteSeletedProcedures(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("Delete Template: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("template_text");
		ormDeleteRecord.setColumn_name("id");
		return generalService.deleteRecord(ormDeleteRecord);
	}*/
	@RequestMapping("/deleteSeletedTemplate")
	public long deleteSeletedTemplate(@RequestBody SearchCriteria searchCriteria) {
		return setupService.deleteSeletedTemplate(searchCriteria);
	}
	@RequestMapping("/updateCategoryCodeType")
	public long updateCategoryCodeType(@RequestBody SearchCriteria searchCriteria) {
		return setupService.updateCategoryCodeType(searchCriteria);
	}
	@RequestMapping("getRestrictedcode/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMRestricted_code>> getRestrictedcode(
			@PathVariable(value = "practice_id") Long practice_id) {

		List<ORMRestricted_code> lst = setupService.getRestrictedcode(practice_id);
		return new ResponseEntity<List<ORMRestricted_code>>(lst, HttpStatus.OK);
	}
	@RequestMapping("saveRestrictedCode")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveRestrictedCode(
			@RequestBody ORMRestricted_code lstOrmSave) {

		ServiceResponse<ORMKeyValue> resp = setupService.saveRestrictedCode(lstOrmSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteRestrictedCode")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteRestrictedCode(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("restricted_procedure");
		ormDeleteRecord.setColumn_name("restproc_id");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while delete restricted_procedure.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("getAdjustcode/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMAdjustmentReasonCodes>> getAdjustcode(
			@PathVariable(value = "practice_id") Long practice_id) {

		List<ORMAdjustmentReasonCodes> lst = setupService.getAdjustcode(practice_id);
		return new ResponseEntity<List<ORMAdjustmentReasonCodes>>(lst, HttpStatus.OK);
	}
	@RequestMapping("saveAdjustCode")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveAdjustCode(
			@RequestBody ORMAdjustmentReasonCodes lstOrmSave) {

		ServiceResponse<ORMKeyValue> resp = setupService.saveAdjustCode(lstOrmSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteAdjustCode")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteAdjustCode(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("adjust_codes");
		ormDeleteRecord.setColumn_name("id");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while delete adjust_codes.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("/getprocedures")
	public @ResponseBody ResponseEntity<List<ORMProcedureSetup>> getprocedures(
			@RequestBody SearchCriteria searchCriteria)	{	
		List<ORMProcedureSetup> lst=setupService.getprocedures(searchCriteria);
		return new ResponseEntity<List<ORMProcedureSetup>>(lst , HttpStatus.OK);
	}
	@RequestMapping("addProcedure")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> addProcedure(
			@RequestBody ORMProcedureSetup ormSave) {

		ServiceResponse<ORMKeyValue> resp = setupService.addProcedure(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("updateProcedure")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> updateProcedure(
			@RequestBody ORMProcedureSetup ormSave) {

		ServiceResponse<ORMKeyValue> resp = setupService.updateProcedure(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteProcedure")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteProcedure(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("procedure_code");
		ormDeleteRecord.setColumn_name("proc_code");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while delete procedure_code.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("/getDiagnosis")
	public @ResponseBody ResponseEntity<List<ORMDiagnosisSetup>> getDiagnosis(
			@RequestBody SearchCriteria searchCriteria)	{	
		List<ORMDiagnosisSetup> lst=setupService.getDiagnosis(searchCriteria);
		return new ResponseEntity<List<ORMDiagnosisSetup>>(lst , HttpStatus.OK);
	}
	@RequestMapping("addDiagnosis")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> addProcedure(
			@RequestBody ORMDiagnosisSetup ormSave) {

		ServiceResponse<ORMKeyValue> resp = setupService.addDiagnosis(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("updateDiagnosis")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> updateDiagnosis(
			@RequestBody ORMDiagnosisSetup ormSave) {

		ServiceResponse<ORMKeyValue> resp = setupService.updateDiagnosis(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteDiagnosis")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteDiagnosis(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("icd_10");
		ormDeleteRecord.setColumn_name("code");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while delete icd_10.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);		
	}
	@RequestMapping("getWriteOffcode/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMWriteOffCodes>> getWriteOffcode(
			@PathVariable(value = "practice_id") Long practice_id) {

		List<ORMWriteOffCodes> lst = setupService.getWriteOffcode(practice_id);
		return new ResponseEntity<List<ORMWriteOffCodes>>(lst, HttpStatus.OK);
	}
	@RequestMapping("saveWriteOffcode")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveWriteOffcode(
			@RequestBody ORMWriteOffCodes lstOrmSave) {

		ServiceResponse<ORMKeyValue> resp = setupService.saveWriteOffcode(lstOrmSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteWriteOffcode")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteWriteOffcode(@RequestBody ORMDeleteRecord ormDeleteRecord) {

		ormDeleteRecord.setTable_name("write_off_codes");
		ormDeleteRecord.setColumn_name("id");

		int result= generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while delete write_off_codes.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		
	}
	@RequestMapping("/getAllPractices")
	public @ResponseBody ResponseEntity<List<ORMPractice>> getAllPractices() {
		List<ORMPractice> lst = setupService.getAllPractices();
		return new ResponseEntity<List<ORMPractice>>(lst, HttpStatus.OK);
	}
	@RequestMapping("GetPracticeServices/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPracticeServices>> GetPracticeServices(
			@PathVariable(value = "practice_id") String practice_id) {
		List<ORMGetPracticeServices> lst = setupService.GetPracticeServices(practice_id);
		return new ResponseEntity<List<ORMGetPracticeServices>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveupdatePractices")
	public ResponseEntity<ORMPractice> saveupdatePractices(
			@RequestBody ORMPractice obj) {
		setupService.saveupdatePractices(obj);
		return new ResponseEntity<ORMPractice>(obj, HttpStatus.OK);
	}
	@RequestMapping("/saveupdatePracticesServices")
	public ResponseEntity<List<ORMPracticeServices>> saveupdatePracticesServices(
			@RequestBody List<ORMPracticeServices> obj) {
		setupService.saveupdatePracticesServices(obj);
		return new ResponseEntity<List<ORMPracticeServices>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/getTemplateProvider")
	public @ResponseBody ResponseEntity<List<ORMGetTemplateForProviderSettings>> getTemplateProvider(
			@RequestBody SearchCriteria searchCriteria)	{	
		List<ORMGetTemplateForProviderSettings> lst=setupService.getTemplateProvider(searchCriteria);
		return new ResponseEntity<List<ORMGetTemplateForProviderSettings>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/saveProvTemplateSetup")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMGetTemplate>> saveProvTemplateSetup(
			@RequestBody ORMGetTemplate ormSave) {
		ServiceResponse<ORMGetTemplate> resp = setupService.saveProvTemplateSetup(ormSave);
		return new ResponseEntity<ServiceResponse<ORMGetTemplate>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/addEditProvTemplateSetup")
	public @ResponseBody ResponseEntity<ServiceResponse<ORM_template_provider>> addEditProvTemplateSetup(
			@RequestBody ORM_template_provider ormSave) {
		ServiceResponse<ORM_template_provider> resp = setupService.addEditProvTemplateSetup(ormSave);
		return new ResponseEntity<ServiceResponse<ORM_template_provider>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteSeletedProvTemplate")
	public long deleteSeletedProvTemplate(@RequestBody SearchCriteria searchCriteria) {
		return setupService.deleteSeletedProvTemplate(searchCriteria);
	}
}