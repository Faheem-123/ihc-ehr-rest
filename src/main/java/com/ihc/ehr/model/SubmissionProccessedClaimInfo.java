package com.ihc.ehr.model;

import com.ihc.ehr.util.EnumUtil.SubmissionMethod;

public class SubmissionProccessedClaimInfo {

    private Long claim_id;
    private Long patient_id;
    private String insurance_name;    
    private Boolean has_primary_ins = false;
    private Boolean has_secondary_ins = false;
    private Boolean has_oth_ins = false;    
    private Boolean is_resubmitted = false;    
    private Boolean is_bill_to_secondary = false;    
    private SubmissionMethod submission_method;
    private String user_name;
    private String client_date_time;    
    private Boolean add_claim_note=false;
    private Long practice_id;
    
    private String pri_status;
	private String sec_status;
	private String oth_status;
	
	public Long getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(Long claim_id) {
		this.claim_id = claim_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getInsurance_name() {
		return insurance_name;
	}
	public void setInsurance_name(String insurance_name) {
		this.insurance_name = insurance_name;
	}
	public Boolean getHas_primary_ins() {
		return has_primary_ins;
	}
	public void setHas_primary_ins(Boolean has_primary_ins) {
		this.has_primary_ins = has_primary_ins;
	}
	public Boolean getHas_secondary_ins() {
		return has_secondary_ins;
	}
	public void setHas_secondary_ins(Boolean has_secondary_ins) {
		this.has_secondary_ins = has_secondary_ins;
	}
	public Boolean getHas_oth_ins() {
		return has_oth_ins;
	}
	public void setHas_oth_ins(Boolean has_oth_ins) {
		this.has_oth_ins = has_oth_ins;
	}
	public Boolean getIs_resubmitted() {
		return is_resubmitted;
	}
	public void setIs_resubmitted(Boolean is_resubmitted) {
		this.is_resubmitted = is_resubmitted;
	}
	public Boolean getIs_bill_to_secondary() {
		return is_bill_to_secondary;
	}
	public void setIs_bill_to_secondary(Boolean is_bill_to_secondary) {
		this.is_bill_to_secondary = is_bill_to_secondary;
	}
	public SubmissionMethod getSubmission_method() {
		return submission_method;
	}
	public void setSubmission_method(SubmissionMethod submission_method) {
		this.submission_method = submission_method;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getClient_date_time() {
		return client_date_time;
	}
	public void setClient_date_time(String client_date_time) {
		this.client_date_time = client_date_time;
	}
	
	
	
	public Boolean getAdd_claim_note() {
		return add_claim_note;
	}
	public void setAdd_claim_note(Boolean add_claim_note) {
		this.add_claim_note = add_claim_note;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public String getPri_status() {
		return pri_status;
	}
	public void setPri_status(String pri_status) {
		this.pri_status = pri_status;
	}
	public String getSec_status() {
		return sec_status;
	}
	public void setSec_status(String sec_status) {
		this.sec_status = sec_status;
	}
	public String getOth_status() {
		return oth_status;
	}
	public void setOth_status(String oth_status) {
		this.oth_status = oth_status;
	}
	
	
}
