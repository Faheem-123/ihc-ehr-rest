package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetClaimRulePatientInfo {
	
	@Id
	private Long patient_id;
	private String gender_code;
	private String dob;
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getGender_code() {
		return gender_code;
	}
	public void setGender_code(String gender_code) {
		this.gender_code = gender_code;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	
}
