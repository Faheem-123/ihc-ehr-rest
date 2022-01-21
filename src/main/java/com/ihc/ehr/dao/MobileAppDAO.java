package com.ihc.ehr.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

public interface MobileAppDAO {

	<T> List<T> patientSearch(SearchCriteria searchCriteria);
	ORMMobGetPatient getPatient(Long patientId);	
	ORMMobileGetPatientHeader getPatientHeader(Long patientId);	
	ServiceResponse<ORMKeyValue> SavePatient(ORMMobSavePatient ormMobSavePatient, MultipartFile picData);
	List<ORMMobileGetClaimSummary> getDraftClaimSummary(Long patientId);
	ORMMobClaimDetailGet getClaimDetail(Long claimId);
	List<ORMMobClamDiagnosisGet> getClaimDiagnosis(Long claim_id);
	List<ORMMobClaimProceduresGet> getClaimProcedures(Long claim_id);
	ServiceResponse<ORMKeyValue> saveClaim(Wrapper_MobClaimSave wrapper_MobClaimSave);
	
	
	
	Bundle<SearchPatient> searchPatient(Long practiceId, List<SpParameters> lstCriteriaParam);	
	public ORMMobGetLoginUserInfo getMobLogedInUserDetail(Long user_id);
	ORMMobPracticeInfo getMobPracticeInfo(Long practice_id);	
	List<ORMMobGetLocation> getMoblocation(Long practice_id);
	List<ORMMobPracticeUsers> getMobPracticeUserName(Long practice_id);
	List<ORMMobProviderList> getMobProviderList(Long practice_id);
	List<ORMMobProviderList> getMobBillingProviderList(Long practice_id);
	
	
	
	

}
