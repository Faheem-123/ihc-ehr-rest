package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientScannedCards {
	
	@Id
	private Long patient_id;
	private String id_card;
	private String driving_license;
	private String patient_agreement;
	
	private String primary_insurance;
	private String secondary_insurance;
	private String other_insurance;
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getDriving_license() {
		return driving_license;
	}
	public void setDriving_license(String driving_license) {
		this.driving_license = driving_license;
	}
	public String getPatient_agreement() {
		return patient_agreement;
	}
	public void setPatient_agreement(String patient_agreement) {
		this.patient_agreement = patient_agreement;
	}
	public String getPrimary_insurance() {
		return primary_insurance;
	}
	public void setPrimary_insurance(String primary_insurance) {
		this.primary_insurance = primary_insurance;
	}
	public String getSecondary_insurance() {
		return secondary_insurance;
	}
	public void setSecondary_insurance(String secondary_insurance) {
		this.secondary_insurance = secondary_insurance;
	}
	public String getOther_insurance() {
		return other_insurance;
	}
	public void setOther_insurance(String other_insurance) {
		this.other_insurance = other_insurance;
	}
}
