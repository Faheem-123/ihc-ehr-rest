package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.dao.MobileAppDAO;
import com.ihc.ehr.model.Bundle;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMMobClaimDetailGet;
import com.ihc.ehr.model.ORMMobClaimProceduresGet;
import com.ihc.ehr.model.ORMMobClamDiagnosisGet;
import com.ihc.ehr.model.ORMMobGetPatient;
import com.ihc.ehr.model.ORMMobGetLocation;
import com.ihc.ehr.model.ORMMobGetLoginUserInfo;
import com.ihc.ehr.model.ORMMobPracticeInfo;
import com.ihc.ehr.model.ORMMobPracticeUsers;
import com.ihc.ehr.model.ORMMobProviderList;
import com.ihc.ehr.model.ORMMobSavePatient;
import com.ihc.ehr.model.ORMMobileGetClaimSummary;
import com.ihc.ehr.model.ORMMobileGetPatientHeader;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchPatient;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.Wrapper_MobClaimSave;

@Service
public class MobileAppServiceImpl implements MobileAppService {

	@Autowired
	MobileAppDAO mobileAppDAO;
	
	@Override
	public <T> List<T> patientSearch(SearchCriteria searchCriteria) {
		return mobileAppDAO.patientSearch(searchCriteria);
	}
	
	@Override
	public ORMMobGetPatient getPatient(Long patientId) {
		// TODO Auto-generated method stub
		return mobileAppDAO.getPatient(patientId);
	}
	
	@Override
	public ORMMobileGetPatientHeader getPatientHeader(Long patientId) {
		// TODO Auto-generated method stub
		return mobileAppDAO.getPatientHeader(patientId);
	}

	
	
	
	@Override
	public ServiceResponse<ORMKeyValue> SavePatient(ORMMobSavePatient ormMobSavePatient, MultipartFile picData) {
		// TODO Auto-generated method stub
		return mobileAppDAO.SavePatient(ormMobSavePatient,picData);
	}

	
	@Override
	public List<ORMMobileGetClaimSummary> getDraftClaimSummary(Long patientId) {
		// TODO Auto-generated method stub
		return mobileAppDAO.getDraftClaimSummary(patientId);
	}
	
	@Override
	public ORMMobClaimDetailGet getClaimDetail(Long claimId) {
		// TODO Auto-generated method stub
		return mobileAppDAO.getClaimDetail(claimId);
	}

	
	@Override
	public List<ORMMobClamDiagnosisGet> getClaimDiagnosis(Long claim_id) {
		// TODO Auto-generated method stub
		return mobileAppDAO.getClaimDiagnosis(claim_id);
	}

	@Override
	public List<ORMMobClaimProceduresGet> getClaimProcedures(Long claim_id) {
		// TODO Auto-generated method stub
		return mobileAppDAO.getClaimProcedures(claim_id);
	}
	
	@Override
	public ServiceResponse<ORMKeyValue> saveClaim(Wrapper_MobClaimSave wrapper_MobClaimSave) {
		// TODO Auto-generated method stub
		return mobileAppDAO.saveClaim(wrapper_MobClaimSave);
	}
	
	
	
	
	@Override
	public Bundle<SearchPatient> searchPatient(Long practiceId, List<SpParameters> lstCriteriaParam) {
		return mobileAppDAO.searchPatient(practiceId, lstCriteriaParam);
	}
	
	
	
	@Override
	public ORMMobGetLoginUserInfo getMobLogedInUserDetail(Long user_id) {
		return mobileAppDAO.getMobLogedInUserDetail(user_id);
	}
	@Override
	public ORMMobPracticeInfo getMobPracticeInfo(Long practice_id) {
		return mobileAppDAO.getMobPracticeInfo(practice_id);
	}
	
	@Override
	public List<ORMMobGetLocation> getMoblocation(Long practice_id) {
		return mobileAppDAO.getMoblocation(practice_id);
	}
	@Override
	public List<ORMMobPracticeUsers> getMobPracticeUserName(Long practice_id) {
		return mobileAppDAO.getMobPracticeUserName(practice_id);
	}
	@Override
	public List<ORMMobProviderList> getMobProviderList(Long practice_id) {
		return mobileAppDAO.getMobProviderList(practice_id);
	}
	@Override
	public List<ORMMobProviderList> getMobBillingProviderList(Long practice_id) {
		return mobileAppDAO.getMobBillingProviderList(practice_id);
	}

	
	
	

	
}