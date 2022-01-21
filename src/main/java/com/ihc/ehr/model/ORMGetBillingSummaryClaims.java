package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetBillingSummaryClaims {


    @Id
    private String claim_id;
    private String patient_id;
    private Boolean chk;
    private String pname;
    private String proname;
    private String name;
    private String batch_id;
    private String detail_id;
    private String facility_name;
    private String insurance_name;
    private String cpt;
    private String icd;
    private String dos;
    //private String reason;
    private String billing_physician;
    private Boolean is_resubmitted;
    private Boolean batch_lock;
    private String file_path;
    private String claim_total;
    private String date_created;    
    private String policy_no;
    private String claim_type;
    
    private String batch_date_created;
    private String batch_client_date_created;
    
	public String getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(String claim_id) {
		this.claim_id = claim_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public Boolean getChk() {
		return chk;
	}
	public void setChk(Boolean chk) {
		this.chk = chk;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public String getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}
	public String getFacility_name() {
		return facility_name;
	}
	public void setFacility_name(String facility_name) {
		this.facility_name = facility_name;
	}
	public String getInsurance_name() {
		return insurance_name;
	}
	public void setInsurance_name(String insurance_name) {
		this.insurance_name = insurance_name;
	}
	public String getCpt() {
		return cpt;
	}
	public void setCpt(String cpt) {
		this.cpt = cpt;
	}
	public String getIcd() {
		return icd;
	}
	public void setIcd(String icd) {
		this.icd = icd;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getBilling_physician() {
		return billing_physician;
	}
	public void setBilling_physician(String billing_physician) {
		this.billing_physician = billing_physician;
	}
	public Boolean getIs_resubmitted() {
		return is_resubmitted;
	}
	public void setIs_resubmitted(Boolean is_resubmitted) {
		this.is_resubmitted = is_resubmitted;
	}
	public Boolean getBatch_lock() {
		return batch_lock;
	}
	public void setBatch_lock(Boolean batch_lock) {
		this.batch_lock = batch_lock;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getClaim_total() {
		return claim_total;
	}
	public void setClaim_total(String claim_total) {
		this.claim_total = claim_total;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}
	public String getClaim_type() {
		return claim_type;
	}
	public void setClaim_type(String claim_type) {
		this.claim_type = claim_type;
	}
	public String getBatch_date_created() {
		return batch_date_created;
	}
	public void setBatch_date_created(String batch_date_created) {
		this.batch_date_created = batch_date_created;
	}
	public String getBatch_client_date_created() {
		return batch_client_date_created;
	}
	public void setBatch_client_date_created(String batch_client_date_created) {
		this.batch_client_date_created = batch_client_date_created;
	}
}
