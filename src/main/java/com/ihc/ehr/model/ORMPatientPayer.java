package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMPatientPayer {
	@Id	
	private Long payer_id;
	private String total_patient;
	private String name;
	
	public Long getPayer_id() {
		return payer_id;
	}
	public void setPayer_id(Long payer_id) {
		this.payer_id = payer_id;
	}
	public String getTotal_patient() {
		return total_patient;
	}
	public void setTotal_patient(String total_patient) {
		this.total_patient = total_patient;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
