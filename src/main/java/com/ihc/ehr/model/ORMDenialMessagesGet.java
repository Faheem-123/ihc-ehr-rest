package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMDenialMessagesGet {

	@Id
	private Long denial_id;	
	private Long claim_id;
	private Long patient_id;
	private String dos;
	private String patient_name;
	private String message;
	private String status;
	private String resolved_user;
	private String date_resolved;
	private Long eob_era_id;
	private String eob_era_id_type;
	private Long era_claim_id;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String resolved_message;
	private Boolean is_era_auto_denial;
	private String insurance_name;
	private String policy_no;
	private String remarks_codes_details;
	private String check_number;
	private String check_date;
	private String no_of_days;
	private String payer_name;
	public Long getDenial_id() {
		return denial_id;
	}
	public void setDenial_id(Long denial_id) {
		this.denial_id = denial_id;
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
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResolved_user() {
		return resolved_user;
	}
	public void setResolved_user(String resolved_user) {
		this.resolved_user = resolved_user;
	}
	public String getDate_resolved() {
		return date_resolved;
	}
	public void setDate_resolved(String date_resolved) {
		this.date_resolved = date_resolved;
	}
	public Long getEob_era_id() {
		return eob_era_id;
	}
	public void setEob_era_id(Long eob_era_id) {
		this.eob_era_id = eob_era_id;
	}
	public String getEob_era_id_type() {
		return eob_era_id_type;
	}
	public void setEob_era_id_type(String eob_era_id_type) {
		this.eob_era_id_type = eob_era_id_type;
	}
	public Long getEra_claim_id() {
		return era_claim_id;
	}
	public void setEra_claim_id(Long era_claim_id) {
		this.era_claim_id = era_claim_id;
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
	public String getResolved_message() {
		return resolved_message;
	}
	public void setResolved_message(String resolved_message) {
		this.resolved_message = resolved_message;
	}
	public Boolean getIs_era_auto_denial() {
		return is_era_auto_denial;
	}
	public void setIs_era_auto_denial(Boolean is_era_auto_denial) {
		this.is_era_auto_denial = is_era_auto_denial;
	}
	public String getInsurance_name() {
		return insurance_name;
	}
	public void setInsurance_name(String insurance_name) {
		this.insurance_name = insurance_name;
	}
	public String getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}
	public String getRemarks_codes_details() {
		return remarks_codes_details;
	}
	public void setRemarks_codes_details(String remarks_codes_details) {
		this.remarks_codes_details = remarks_codes_details;
	}
	public String getCheck_number() {
		return check_number;
	}
	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}
	public String getCheck_date() {
		return check_date;
	}
	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}
	public String getNo_of_days() {
		return no_of_days;
	}
	public void setNo_of_days(String no_of_days) {
		this.no_of_days = no_of_days;
	}
	public String getPayer_name() {
		return payer_name;
	}
	public void setPayer_name(String payer_name) {
		this.payer_name = payer_name;
	}
}
