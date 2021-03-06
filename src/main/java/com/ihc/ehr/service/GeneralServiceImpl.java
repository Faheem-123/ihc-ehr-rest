package com.ihc.ehr.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.dao.GeneralDAO;
import com.ihc.ehr.model.ORMAppSetting;
import com.ihc.ehr.model.ORMAuditLog;
import com.ihc.ehr.model.ORMChartReportDetailsforPHR;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMDocCategories;
import com.ihc.ehr.model.ORMDocCategoriesList;
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMFourColumGeneric;
import com.ihc.ehr.model.ORMGetBillingUsersList;
import com.ihc.ehr.model.ORMGetChartSummary;
import com.ihc.ehr.model.ORMGetCityState;
import com.ihc.ehr.model.ORMGetLocation;
import com.ihc.ehr.model.ORMGetPatientAllergiesSummary;
import com.ihc.ehr.model.ORMGetPatientHeaderInfo;
import com.ihc.ehr.model.ORMGetPatientInsuranceName;
import com.ihc.ehr.model.ORMGetPatientMedicationSummary;
import com.ihc.ehr.model.ORMGetPatientProblemsSummary;
import com.ihc.ehr.model.ORMGetProviderUsers;
import com.ihc.ehr.model.ORMGetReferringProvider;
import com.ihc.ehr.model.ORMGetUserChartModuleSetting;
import com.ihc.ehr.model.ORMGetUserRights;
import com.ihc.ehr.model.ORMHeaderVitals;
import com.ihc.ehr.model.ORMHeaderVitals_PHR;
import com.ihc.ehr.model.ORMIdCodeDescription;
import com.ihc.ehr.model.ORMIdCodeDescriptionDisplay;
import com.ihc.ehr.model.ORMIdName;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMLabelPrintDataGet;
import com.ihc.ehr.model.ORMLoginUserLog;
import com.ihc.ehr.model.ORMModules;
import com.ihc.ehr.model.ORMOneColumnGeneric;
import com.ihc.ehr.model.ORMPhysicianSearch;
import com.ihc.ehr.model.ORMPracticeInfo;
import com.ihc.ehr.model.ORMPracticeUsers;
import com.ihc.ehr.model.ORMPracticeUsersList;
import com.ihc.ehr.model.ORMProviderList;
import com.ihc.ehr.model.ORMSaveDocument;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORMUserRoleList;
import com.ihc.ehr.model.ORMZipCityState;
import com.ihc.ehr.model.ScanDocumentData;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;

@Service
public class GeneralServiceImpl implements GeneralService {

	@Autowired
	private GeneralDAO generalDao;
	
	@Override
	public ORMPracticeInfo getPracticeInfo(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getPracticeInfo(practice_id);
	}
	
	@Override
	public List<ORMProviderList> getProvider(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getProvider(practice_id);
	}
	@Override
	public List<ORMThreeColum> getLabCategory(Long practiceID) {
		// TODO Auto-generated method stub
		return generalDao.getLabCategory(practiceID);
	}
	@Override
	public List<ORMGetLocation> getLocation(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getLocation(practice_id);
	}
	@Override
	public List<ORMPracticeUsers> getPracticeUserName(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getPracticeUserName(practice_id);
	}
	@Override
	public int deleteRecord(ORMDeleteRecord obj) {
		// TODO Auto-generated method stub
		return generalDao.deleteRecord(obj);
	}
	@Override
	public List<ORMGetUserChartModuleSetting> getUserChartModuleSetting(Long setting_id) {
		// TODO Auto-generated method stub
		return generalDao.getUserChartModuleSetting(setting_id);
	}
	@Override
	public List<ORMGetCityState> getCityState(String zip_code) {
		// TODO Auto-generated method stub
		return generalDao.getCityState(zip_code);
	}
	@Override
	public List<ORMGetReferringProvider> searchReferringProvider(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return generalDao.searchReferringProvider(searchCriteria);
	}
	@Override
	public List<ORMDocCategories> getDocCategories(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getDocCategories(practice_id);
	}
	@Override
	public List<ORMDocCategoriesList> getDocCategoriesList(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getDocCategoriesList(practice_id);
	}
	
	@Override
	public Long SaveDocument(ORMSaveDocument patDoc, MultipartFile docFile,String docCategory) {
		// TODO Auto-generated method stub
		return generalDao.SaveDocument(patDoc,docFile,docCategory);
	}
	@Override
	public List<ORMDocumentPath> getDocumentPaths(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getDocumentPaths(practice_id);
	}
	@Override
	public InputStream GetDocument() throws IOException {
		// TODO Auto-generated method stub
		return generalDao.GetDocument();
	}
	@Override
	public byte[] downloadFile(String file_name) {
		// TODO Auto-generated method stub
		return generalDao.downloadFile(file_name);
	}
	@Override
	public List<ORMGetUserRights> getUserRights(String user_id) {
		// TODO Auto-generated method stub
		return generalDao.getUserRights(user_id);
	}
	@Override
	public List<ORMProviderList> getDistinctLocationProviders(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getDistinctLocationProviders(practice_id);
	}
	@Override
	public List<ORMGetProviderUsers> getProviderUser(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getProviderUser(practice_id);
	}
	@Override
	public List<ORMIdName> AuthenticateProviderUser(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return generalDao.AuthenticateProviderUser(searchCriteria);
	}
	@Override
	public int updateQuery(String query) {
		// TODO Auto-generated method stub
		return generalDao.updateQuery(query);
	}
	@Override
	public List<ORMIdName> getAuthorizationUsers(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getAuthorizationUsers(practice_id);
	}
	@Override
	public ORMGetPatientInsuranceName getPatientInsuranceName(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return generalDao.getPatientInsuranceName(searchCriteria);
	}
	@Override
	public List<ORMIdCodeDescriptionDisplay> getMaritalStatusList() {
		// TODO Auto-generated method stub
		return generalDao.getMaritalStatusList();
	}
	@Override
	public List<ORMIdCodeDescriptionDisplay> getLanguageList(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getLanguageList(practice_id);
	}
	@Override
	public List<ORMIdCodeDescription> getGenderIdentityList() {
		// TODO Auto-generated method stub
		return generalDao.getGenderIdentityList();
	}
	@Override
	public List<ORMIdCodeDescription> getSexualOrientationList() {
		// TODO Auto-generated method stub
		return generalDao.getSexualOrientationList();
	}
	@Override
	public List<ORMIdCodeDescription> getOMBRaceList() {
		// TODO Auto-generated method stub
		return generalDao.getOMBRaceList();
	}
	@Override
	public List<ORMIdCodeDescription> getOMBEthnicityList() {
		// TODO Auto-generated method stub
		return generalDao.getOMBEthnicityList();
	}
	@Override
	public List<ORMIdCodeDescription> getCountryParishCodeByState(String state) {
		// TODO Auto-generated method stub
		return generalDao.getCountryParishCodeByState(state);
	}
	@Override
	public List<ORMZipCityState> getCityStateByZip(String zip) {
		// TODO Auto-generated method stub
		return generalDao.getCityStateByZip(zip);
	}

	/*
	@Override
	public List<ORMProvider_Eligibility> getProvider_Eligibility(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getProvider_Eligibility(practice_id);
	}
	*/

	@Override
	public List<ORMIdCodeDescription> getRelationshipList() {
		// TODO Auto-generated method stub
		return generalDao.getRelationshipList();
	}
	@Override
	public List<ORMIdCodeDescription> getImmRegistryStatusList() {
		// TODO Auto-generated method stub
		return generalDao.getImmRegistryStatusList();
	}
	@Override
	public List<ORMIdCodeDescription> getImmRegistryPublicityCodeList() {
		// TODO Auto-generated method stub
		return generalDao.getImmRegistryPublicityCodeList();
	}
	@Override
	public List<ORMIdCodeDescription> getImmRegistryProcectionIndicatorList() {
		// TODO Auto-generated method stub
		return generalDao.getImmRegistryProcectionIndicatorList();
	}
	@Override
	public List<ORMPhysicianSearch> getProviderInfoById(Long providerId) {
		// TODO Auto-generated method stub
		return generalDao.getProviderInfoById(providerId);
	}
	@Override
	public ORMDocumentPath getDocumentPathByCategory(Long practice_id, String cateogry) {
		// TODO Auto-generated method stub
		return generalDao.getDocumentPathByCategory(practice_id,cateogry);
	}
	@Override
	public List<ORMIdCodeDescription> getSnomedRelationshipList() {
		// TODO Auto-generated method stub
		return generalDao.getSnomedRelationshipList();
	}	
	
	@Override
	public ServiceResponse<ORMKeyValue> uploadScan(MultipartFile multipartFile, ScanDocumentData scanDocument) {
		// TODO Auto-generated method stub
		return generalDao.uploadScan(multipartFile,scanDocument);
	}

	@Override
	public List<ORMProviderList> getBillingProviderList(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getBillingProviderList(practice_id);
	}
	@Override
	public List<ORMOneColumnGeneric> getStatesList() {
		// TODO Auto-generated method stub
		return generalDao.getStatesList();
	}

	@Override
	public List<ORMGetBillingUsersList> getBillingUsersList(String billing_practice_id, String practiceID) {
		// TODO Auto-generated method stub
		return generalDao.getBillingUsersList(billing_practice_id,practiceID);
	}
	@Override
	public List<ORMAppSetting> getAppSetting(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getAppSetting(practice_id);
	}
	@Override
	public List<ORMUserRoleList> getUserAllRoles(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getUserAllRoles(practice_id);
	}
	
	@Override
	public List<ORMPracticeUsersList> getPracticeUsersList(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getPracticeUsersList(practice_id);
	}
	@Override
	public List<ORMIdCodeDescription> getSmokingStatusList() {
		// TODO Auto-generated method stub
		return generalDao.getSmokingStatusList();
	}
	@Override
	public List<ORMIdCodeDescription> getDischargeDispositionList() {
		// TODO Auto-generated method stub
		return generalDao.getDischargeDispositionList();
	}

	@Override
	public ORMGetPatientHeaderInfo getPatientHeader(Long patientId) {
		return generalDao.getPatientHeader(patientId);
	}
	@Override
	public ORMHeaderVitals getPatientVitals(Long patientId) {
		return generalDao.getPatientVitals(patientId);
	}
	@Override
	public ORMHeaderVitals_PHR getPatientVitalsPHR(Long patientId) {
		return generalDao.getPatientVitalsPHR(patientId);
	}
	@Override
	public List<ORMGetPatientAllergiesSummary> getPatientAllergies(Long patientId) {
		// TODO Auto-generated method stub
		return generalDao.getPatientAllergies(patientId);
	}
	@Override
	public List<ORMGetPatientMedicationSummary> getPatientMedicationSummary(Long patientId) {
		// TODO Auto-generated method stub
		return generalDao.getPatientMedicationSummary(patientId);
	}
	@Override
	public List<ORMGetPatientProblemsSummary> getPatientProblems(Long patientId) {
		return generalDao.getPatientProblems(patientId);
	}
	@Override
	public List<ORMGetChartSummary> getPHREncounterSummary(String patient_id) {
		return generalDao.getPHREncounterSummary(patient_id);
	}
	@Override
	public List<ORMChartReportDetailsforPHR> getReportHeader(String chart_id) {
		return generalDao.getReportHeader(chart_id);
	}
	@Override
	public List<ORMFourColumGeneric> getInsuranceDetails(String patientId) {
		return generalDao.getInsuranceDetails(patientId);
	}
	@Override
	public ServiceResponse<ORMKeyValue> loginUserLog(ORMLoginUserLog ormSave) {
		// TODO Auto-generated method stub
		return generalDao.loginUserLog(ormSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> logoutUserLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return generalDao.logoutUserLog(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> auditLog(ORMAuditLog ormSave) {
		// TODO Auto-generated method stub
		return generalDao.auditLog(ormSave);
	}

	@Override
	public List<ORMThreeColumGeneric> getOverrideSecuritySettings(Long practice_id, String role_id) {
		// TODO Auto-generated method stub
		return generalDao.getOverrideSecuritySettings(practice_id,role_id);
	}

	@Override
	public List<ORMModules> getAllModules(Long practice_id) {
		// TODO Auto-generated method stub
		return generalDao.getAllModules(practice_id);
	}

	@Override
	public String ConvertHtmltoPDF(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return generalDao.ConvertHtmltoPDF(searchCriteria);
	}

	@Override
	public List<ORMTwoColumnGeneric> getBillingPractices(String user_id) {
		// TODO Auto-generated method stub
		return generalDao.getBillingPractices(user_id);
	}

	@Override
	public ORMLabelPrintDataGet getLabelPrintData(String label_type, String id) {
		// TODO Auto-generated method stub
		return generalDao.getLabelPrintData(label_type,id);
	}

	@Override
	public List<ORMIdCodeDescription> getPOSList() {
		// TODO Auto-generated method stub
		return generalDao.getPOSList();
	}
	
	@Override
	public byte[] getDocumentBytes(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return generalDao.getDocumentBytes(searchCriteria);
	}

	@Override
	public int getUnreadMessageCount(String receiverId) {
		// TODO Auto-generated method stub
		return generalDao.getUnreadMessageCount(receiverId);
	}

	@Override
	public String getHPPublicKey(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return generalDao.getHPPublicKey(searchCriteria);
	}
	

}
