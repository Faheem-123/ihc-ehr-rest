package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatImmRegInfoDisplay {
	
	@Id
	private Long registry_info_id;
	private Long patient_id;
	private String registry_status;
	private String registry_status_description;
	private String registry_status_effective_date;
	private String publicity_code;
	private String publicity_code_description;
	private String publicity_code_effective_date;
	private String protection_indicator;
	private String protection_indicator_description;
	private String protection_indicator_effective_date;
	
	public Long getRegistry_info_id() {
		return registry_info_id;
	}
	public void setRegistry_info_id(Long registry_info_id) {
		this.registry_info_id = registry_info_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getRegistry_status() {
		return registry_status;
	}
	public void setRegistry_status(String registry_status) {
		this.registry_status = registry_status;
	}
	public String getRegistry_status_description() {
		return registry_status_description;
	}
	public void setRegistry_status_description(String registry_status_description) {
		this.registry_status_description = registry_status_description;
	}
	public String getRegistry_status_effective_date() {
		return registry_status_effective_date;
	}
	public void setRegistry_status_effective_date(String registry_status_effective_date) {
		this.registry_status_effective_date = registry_status_effective_date;
	}
	public String getPublicity_code() {
		return publicity_code;
	}
	public void setPublicity_code(String publicity_code) {
		this.publicity_code = publicity_code;
	}
	public String getPublicity_code_description() {
		return publicity_code_description;
	}
	public void setPublicity_code_description(String publicity_code_description) {
		this.publicity_code_description = publicity_code_description;
	}
	public String getPublicity_code_effective_date() {
		return publicity_code_effective_date;
	}
	public void setPublicity_code_effective_date(String publicity_code_effective_date) {
		this.publicity_code_effective_date = publicity_code_effective_date;
	}
	public String getProtection_indicator() {
		return protection_indicator;
	}
	public void setProtection_indicator(String protection_indicator) {
		this.protection_indicator = protection_indicator;
	}
	public String getProtection_indicator_description() {
		return protection_indicator_description;
	}
	public void setProtection_indicator_description(String protection_indicator_description) {
		this.protection_indicator_description = protection_indicator_description;
	}
	public String getProtection_indicator_effective_date() {
		return protection_indicator_effective_date;
	}
	public void setProtection_indicator_effective_date(String protection_indicator_effective_date) {
		this.protection_indicator_effective_date = protection_indicator_effective_date;
	}
	
	

}
