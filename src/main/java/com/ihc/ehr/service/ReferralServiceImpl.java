package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.ReferralDAO;
import com.ihc.ehr.model.ORMGetChartReferral;
import com.ihc.ehr.model.ORMGetChartReferralDetail;
import com.ihc.ehr.model.ORMGetPatientReferral;
import com.ihc.ehr.model.ORMGetReferralEmailDetail;
import com.ihc.ehr.model.ORMGetReferralFaxDetail;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientReferral;
import com.ihc.ehr.model.ORMReferralChartSummary;
import com.ihc.ehr.model.ORMReferralLabSummary;
import com.ihc.ehr.model.ORMReferralStaffNotes;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMgetLabOrderResultsSummary;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;

@Service
public class ReferralServiceImpl implements ReferralService {

	@Autowired
	private ReferralDAO referralDao;
	@Override
	public List<ORMGetPatientReferral> getPatientReferrals(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return referralDao.getPatientReferrals(searchCriteria);
	}
	@Override
	public List<ORMThreeColum> getConsultType(String practice_id) {
		// TODO Auto-generated method stub
		return referralDao.getConsultType(practice_id);
	}
	@Override
	public ServiceResponse<ORMKeyValue> savePatientReferral(ORMPatientReferral obj,String path,String dest_path) {
		// TODO Auto-generated method stub
		return referralDao.savePatientReferral(obj,path,dest_path);
	}
	@Override
	public Long savePatientReferralRequest(ORMPatientReferral obj) {
		// TODO Auto-generated method stub
		return referralDao.savePatientReferralRequest(obj);
	}
	@Override
	public int UpdateReferralRequestStatus(ORMReferralStaffNotes obj,String Status) {
		// TODO Auto-generated method stub
		return referralDao.UpdateReferralRequestStatus(obj,Status);
	}
	@Override
	public List<ORMReferralLabSummary> getPatientLabOrders(String patient_id) {
		// TODO Auto-generated method stub
		return referralDao.getPatientLabOrders(patient_id);
	}
	@Override
	public List<ORMReferralChartSummary> getReferralChartSummary(String patient_id) {
		// TODO Auto-generated method stub
		return referralDao.getReferralChartSummary(patient_id);
	}
	@Override
	public List<ORMGetReferralFaxDetail> getReferralsFaxDetails(String referral_id) {
		// TODO Auto-generated method stub
		return referralDao.getReferralsFaxDetails(referral_id);
	}
	@Override
	public List<ORMGetReferralEmailDetail> getReferralsEmailDetails(String referral_id) {
		// TODO Auto-generated method stub
		return referralDao.getReferralsEmailDetails(referral_id);
	}
	@Override
	public List<ORMGetChartReferral> getChartReferrals(String patient_id) {
		// TODO Auto-generated method stub
		return referralDao.getChartReferrals(patient_id);
	}
	@Override
	public ORMGetChartReferralDetail getChartReferralsDetail(String referral_id) {
		// TODO Auto-generated method stub
		return referralDao.getChartReferralsDetail(referral_id);
	}
	@Override
	public List<ORMgetLabOrderResultsSummary> getPatientOrderResults(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return referralDao.getPatientOrderResults(searchCriteria);
	}
	@Override
	public String GenerateTempLetter(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return referralDao.GenerateTempLetter(searchCriteria);
	}
	
}
