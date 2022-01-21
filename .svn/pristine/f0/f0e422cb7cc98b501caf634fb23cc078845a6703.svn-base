package com.ihc.ehr.dao;

import java.util.List;

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
import com.ihc.ehr.model.SearchPHRPatient;

public interface SearchDAO {
	
	//List<SearchPatient> searchPatient(SearchCriteria searchCriteria);
	<T> List<T> searchPatient(SearchCriteria searchCriteria);
	
	List<ORMGetDiagSearch> searchDiagnosis(SearchCriteria searchCriteria);
	
	List<ORMProcedureSearch> searchLabTest(SearchCriteria searchCriteria);
	
	List<ORMRaceEthnicitySearch> searchRace(SearchCriteria searchCriteria);
	
	List<ORMRaceEthnicitySearch> searchEthnicity(SearchCriteria searchCriteria);
	
	List<ORMInsuranceSearch> searchInsurance(SearchCriteria searchCriteria);
	
	List<ORMGuarantorSearch> searchGuarantor(SearchCriteria searchCriteria);

	List<ORMReferralPhysicianSearch> searchRefphysician(SearchCriteria searchCriteria);

	List<ORMSpecialitySearch> searchSpeciality(SearchCriteria searchCriteria);

	List<ORMGetProcedureSearch> searchProcedures(SearchCriteria searchCriteria);

	List<ORMPayerSearch> searchPayer(SearchCriteria searchCriteria);	

	List<ORMGetChartDiagnosis> getChartDiagnosis(String patient_id, String chart_id);

	List<ORMLabMappingCode> searchLabMappingTest(SearchCriteria searchCriteria);

	List<ORMLabResultLoincCode> getLoinCode(SearchCriteria searchCriteria);
	List<ORMGetAttorneyInfo> searchFirm(SearchCriteria searchCriteria);

	List<ORMThreeColum> getAllChartPlan(SearchCriteria searchCriteria);
	public List<SearchPHRPatient> PHRsearchPatient(SearchCriteria searchCriteria);

	
}
