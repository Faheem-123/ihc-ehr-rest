package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMEraClaimListGet {

	@Id
	private Long era_claim_id;
	private Long era_id;
	private String claim_id;
	private String patient_id;
	private String patient_fname;
	private String patient_lname;
	private String patient_mname;
	private Boolean posted;
	private String claim_status_code;
	private Boolean claim_exist;
	
	
	public Long getEra_claim_id() {
		return era_claim_id;
	}
	public void setEra_claim_id(Long era_claim_id) {
		this.era_claim_id = era_claim_id;
	}
	public Long getEra_id() {
		return era_id;
	}
	public void setEra_id(Long era_id) {
		this.era_id = era_id;
	}
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
	public String getPatient_fname() {
		return patient_fname;
	}
	public void setPatient_fname(String patient_fname) {
		this.patient_fname = patient_fname;
	}
	public String getPatient_lname() {
		return patient_lname;
	}
	public void setPatient_lname(String patient_lname) {
		this.patient_lname = patient_lname;
	}
	public String getPatient_mname() {
		return patient_mname;
	}
	public void setPatient_mname(String patient_mname) {
		this.patient_mname = patient_mname;
	}
	public Boolean getPosted() {
		return posted;
	}
	public void setPosted(Boolean posted) {
		this.posted = posted;
	}
	public String getClaim_status_code() {
		return claim_status_code;
	}
	public void setClaim_status_code(String claim_status_code) {
		this.claim_status_code = claim_status_code;
	}
	public Boolean getClaim_exist() {
		return claim_exist;
	}
	public void setClaim_exist(Boolean claim_exist) {
		this.claim_exist = claim_exist;
	}
	@Override
	public String toString() {
		return "ORMEraClaimListGet [era_claim_id=" + era_claim_id + ", era_id=" + era_id + ", claim_id=" + claim_id
				+ ", patient_id=" + patient_id + ", patient_fname=" + patient_fname + ", patient_lname=" + patient_lname
				+ ", patient_mname=" + patient_mname + ", posted=" + posted + ", claim_status_code=" + claim_status_code
				+ "]";
	}
	
	
	
}
