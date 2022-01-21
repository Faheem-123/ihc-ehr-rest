package com.ihc.ehr.service;

import java.util.List;

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

public interface ReferralService {

	List<ORMGetPatientReferral> getPatientReferrals(SearchCriteria searchCriteria);
	List<ORMThreeColum>getConsultType(String practice_id);
	ServiceResponse<ORMKeyValue> savePatientReferral(ORMPatientReferral obj,String path,String dest_path);
	Long savePatientReferralRequest(ORMPatientReferral obj);
	int UpdateReferralRequestStatus(ORMReferralStaffNotes obj,String status);
	List<ORMReferralLabSummary> getPatientLabOrders(String patient_id);
	List<ORMReferralChartSummary> getReferralChartSummary(String patient_id);
	List<ORMGetReferralFaxDetail> getReferralsFaxDetails(String referral_id);
	List<ORMGetReferralEmailDetail> getReferralsEmailDetails(String referral_id);
	List<ORMGetChartReferral> getChartReferrals(String patient_id);
	ORMGetChartReferralDetail getChartReferralsDetail(String referral_id);
	List<ORMgetLabOrderResultsSummary> getPatientOrderResults(SearchCriteria searchCriteria);
	String GenerateTempLetter(SearchCriteria searchCriteria);
}
