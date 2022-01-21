package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMPatientImplantableDeviceGetSummary {
	
	@Id
	private Long implantable_device_id;
	private Long patient_id;
	private Long chart_id;
	private String device_id;
	private Boolean device_active;
	private String gmdn_pt_name;
	private String gmdn_pt_description;
	private String implant_date;
	private String expiry_date;
	private String modified_user;
	private String date_modified;
	
	
	
	
	public Long getImplantable_device_id() {
		return implantable_device_id;
	}
	public void setImplantable_device_id(Long implantable_device_id) {
		this.implantable_device_id = implantable_device_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public Long getChart_id() {
		return chart_id;
	}
	public void setChart_id(Long chart_id) {
		this.chart_id = chart_id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	
	
	
	public Boolean getDevice_active() {
		return device_active;
	}
	public void setDevice_active(Boolean device_active) {
		this.device_active = device_active;
	}
	public String getGmdn_pt_name() {
		return gmdn_pt_name;
	}
	public void setGmdn_pt_name(String gmdn_pt_name) {
		this.gmdn_pt_name = gmdn_pt_name;
	}
	
	
	public String getGmdn_pt_description() {
		return gmdn_pt_description;
	}
	public void setGmdn_pt_definition(String gmdn_pt_description) {
		this.gmdn_pt_description = gmdn_pt_description;
	}
	public String getImplant_date() {
		return implant_date;
	}
	public void setImplant_date(String implant_date) {
		this.implant_date = implant_date;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}	
}
