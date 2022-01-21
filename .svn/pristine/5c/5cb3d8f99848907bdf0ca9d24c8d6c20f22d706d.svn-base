package com.ihc.ehr.service;

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

public interface SearchService {

	//public List<SearchPatient> searchPatient(SearchCriteria searchCriteria);
	<T> List<T> searchPatient(SearchCriteria searchCriteria);

	public List<ORMGetDiagSearch> searchDiagnosis(SearchCriteria searchCriteria);
	
	public List<ORMProcedureSearch> searchLabTest(SearchCriteria searchCriteria);

	public List<ORMRaceEthnicitySearch> searchRace(SearchCriteria searchCriteria);

	public List<ORMRaceEthnicitySearch> searchEthnicity(SearchCriteria searchCriteria);

	public List<ORMInsuranceSearch> searchInsurance(SearchCriteria search_value);

	public List<ORMGuarantorSearch> searchGuarantor(SearchCriteria searchCriteria);

	public List<ORMReferralPhysicianSearch> searchRefphysician(SearchCriteria searchCriteria);	

	public List<ORMSpecialitySearch> searchSpeciality(SearchCriteria searchCriteria);

	public List<ORMGetProcedureSearch> searchProcedures(SearchCriteria searchCriteria);
	
	public List<ORMPayerSearch> searchPayer(SearchCriteria searchCriteria);

	public List<ORMGetChartDiagnosis> getChartDiagnosis(String patient_id, String chart_id);

	public List<ORMLabMappingCode> searchLabMappingTest(SearchCriteria searchCriteria);

	public List<ORMLabResultLoincCode> getLoinCode(SearchCriteria searchCriteria);
	
	public List<ORMGetAttorneyInfo> searchFirm(SearchCriteria searchCriteria);

	public List<ORMThreeColum> getAllChartPlan(SearchCriteria searchCriteria);
	public List<SearchPHRPatient> PHRsearchPatient(SearchCriteria searchCriteria);
}
