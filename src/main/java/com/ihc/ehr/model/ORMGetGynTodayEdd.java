package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetGynTodayEdd {
	@Id
    private String patient_id;
    private String edd_date;
    private String patient_name;
    
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getEdd_date() {
		return edd_date;
	}
	public void setEdd_date(String edd_date) {
		this.edd_date = edd_date;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
}
