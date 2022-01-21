package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientElibility {

	@Id
	private Long patient_id;
	private String patient_name;
	private String elig_date;
	private String elig_status;
	private String elig_response;
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
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
}
