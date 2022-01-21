package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.SearchDAO;
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

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDAO searchDAO;

	/*
	 * @Override public List<SearchPatient> searchPatient(SearchCriteria
	 * searchCriteria) { // TODO Auto-generated method stub return
	 * searchDAO.searchPatient(searchCriteria); }
	 */

	@Override
	public <T> List<T> searchPatient(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.searchPatient(searchCriteria);
	}

	@Override
	public List<ORMGetDiagSearch> searchDiagnosis(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.searchDiagnosis(searchCriteria);
	}

	@Override
	public List<ORMProcedureSearch> searchLabTest(SearchCriteria searchCriteria) {
		return searchDAO.searchLabTest(searchCriteria);
	}

	@Override
	public List<ORMRaceEthnicitySearch> searchRace(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.searchRace(searchCriteria);
	}

	@Override
	public List<ORMRaceEthnicitySearch> searchEthnicity(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.searchEthnicity(searchCriteria);
	}

	@Override
	public List<ORMInsuranceSearch> searchInsurance(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.searchInsurance(searchCriteria);
	}

	@Override
	public List<ORMGuarantorSearch> searchGuarantor(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.searchGuarantor(searchCriteria);
	}

	@Override
	public List<ORMReferralPhysicianSearch> searchRefphysician(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.searchRefphysician(searchCriteria);
	}

	@Override
	public List<ORMSpecialitySearch> searchSpeciality(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.searchSpeciality(searchCriteria);

	}

	@Override
	public List<ORMGetProcedureSearch> searchProcedures(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.searchProcedures(searchCriteria);
	}

	@Override
	public List<ORMPayerSearch> searchPayer(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.searchPayer(searchCriteria);
	}

	@Override
	public List<ORMGetChartDiagnosis> getChartDiagnosis(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		return searchDAO.getChartDiagnosis(patient_id, chart_id);
	}

	@Override
	public List<ORMLabMappingCode> searchLabMappingTest(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.searchLabMappingTest(searchCriteria);
	}

	@Override
	public List<ORMLabResultLoincCode> getLoinCode(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.getLoinCode(searchCriteria);
	}

	@Override
	public List<ORMGetAttorneyInfo> searchFirm(SearchCriteria searchCriteria) {
		return searchDAO.searchFirm(searchCriteria);
	}

	@Override
	public List<ORMThreeColum> getAllChartPlan(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return searchDAO.getAllChartPlan(searchCriteria);
	}

	@Override
	public List<SearchPHRPatient> PHRsearchPatient(SearchCriteria searchCriteria) {
		return searchDAO.PHRsearchPatient(searchCriteria);
	}

}
