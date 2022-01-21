package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim_insurance")
public class ORMClaimInsuranceSave {
	
	
	@Id
    private Long claiminsurance_id;
    private Long insurance_id;
    private Long claim_id;
    private Long patient_id;
    
    private String insurace_type;
    private String policy_number;
    private String group_number;
    private BigDecimal copay;
    private String start_date;
    private String end_date;
    private Long guarantor_id;
    private String guarantor_relationship;
    private String created_user;
    private String client_date_created;
    private String modified_user;
    private String client_date_modified;
    private String date_created;
    private String date_modified;
   
    private String workercomp_name;
    private String workercomp_address;
    private String workercomp_city;
    private String workercomp_state;
    private String workercomp_zip;
    private String auth_no;
    private String auth_no_type;
    private String claim_no;
    private String elig_date;
    private String elig_status;
    private String elig_response;
    
    
    
	public String getElig_date() {
		return elig_date;
	}
	public void setElig_date(String elig_date) {
		this.elig_date = elig_date;
	}
	public String getElig_status() {
		return elig_status;
	}
	public void setElig_status(String elig_status) {
		this.elig_status = elig_status;
	}
	public String getElig_response() {
		return elig_response;
	}
	public void setElig_response(String elig_response) {
		this.elig_response = elig_response;
	}
	public Long getClaiminsurance_id() {
		return claiminsurance_id;
	}
	public void setClaiminsurance_id(Long claiminsurance_id) {
		this.claiminsurance_id = claiminsurance_id;
	}
	public Long getInsurance_id() {
		return insurance_id;
	}
	public void setInsurance_id(Long insurance_id) {
		this.insurance_id = insurance_id;
	}
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
	public String getInsurace_type() {
		return insurace_type;
	}
	public void setInsurace_type(String insurace_type) {
		this.insurace_type = insurace_type;
	}
	public String getPolicy_number() {
		return policy_number;
	}
	public void setPolicy_number(String policy_number) {
		this.policy_number = policy_number;
	}
	public String getGroup_number() {
		return group_number;
	}
	public void setGroup_number(String group_number) {
		this.group_number = group_number;
	}
	public BigDecimal getCopay() {
		return copay;
	}
	public void setCopay(BigDecimal copay) {
		this.copay = copay;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public Long getGuarantor_id() {
		return guarantor_id;
	}
	public void setGuarantor_id(Long guarantor_id) {
		this.guarantor_id = guarantor_id;
	}
	public String getGuarantor_relationship() {
		return guarantor_relationship;
	}
	public void setGuarantor_relationship(String guarantor_relationship) {
		this.guarantor_relationship = guarantor_relationship;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getWorkercomp_name() {
		return workercomp_name;
	}
	public void setWorkercomp_name(String workercomp_name) {
		this.workercomp_name = workercomp_name;
	}
	public String getWorkercomp_address() {
		return workercomp_address;
	}
	public void setWorkercomp_address(String workercomp_address) {
		this.workercomp_address = workercomp_address;
	}
	public String getWorkercomp_city() {
		return workercomp_city;
	}
	public void setWorkercomp_city(String workercomp_city) {
		this.workercomp_city = workercomp_city;
	}
	public String getWorkercomp_state() {
		return workercomp_state;
	}
	public void setWorkercomp_state(String workercomp_state) {
		this.workercomp_state = workercomp_state;
	}
	public String getWorkercomp_zip() {
		return workercomp_zip;
	}
	public void setWorkercomp_zip(String workercomp_zip) {
		this.workercomp_zip = workercomp_zip;
	}
	public String getAuth_no() {
		return auth_no;
	}
	public void setAuth_no(String auth_no) {
		this.auth_no = auth_no;
	}
	public String getAuth_no_type() {
		return auth_no_type;
	}
	public void setAuth_no_type(String auth_no_type) {
		this.auth_no_type = auth_no_type;
	}
	public String getClaim_no() {
		return claim_no;
	}
	public void setClaim_no(String claim_no) {
		this.claim_no = claim_no;
	}
	@Override
	public String toString() {
		return "ORMClaimInsuranceSave [claiminsurance_id=" + claiminsurance_id + ", insurance_id=" + insurance_id
				+ ", claim_id=" + claim_id + ", patient_id=" + patient_id + ", insurace_type=" + insurace_type
				+ ", policy_number=" + policy_number + ", group_number=" + group_number + ", copay=" + copay
				+ ", start_date=" + start_date + ", end_date=" + end_date + ", guarantor_id=" + guarantor_id
				+ ", guarantor_relationship=" + guarantor_relationship + ", created_user=" + created_user
				+ ", client_date_created=" + client_date_created + ", modified_user=" + modified_user
				+ ", client_date_modified=" + client_date_modified + ", date_created=" + date_created
				+ ", date_modified=" + date_modified + ", workercomp_name=" + workercomp_name + ", workercomp_address="
				+ workercomp_address + ", workercomp_city=" + workercomp_city + ", workercomp_state=" + workercomp_state
				+ ", workercomp_zip=" + workercomp_zip + ", auth_no=" + auth_no + ", auth_no_type=" + auth_no_type
				+ ", claim_no=" + claim_no + "]";
	}
	
}
