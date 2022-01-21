package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientRaceEthnicity {
	
	@Id
    private Long id;
    private Long patient_id;
    private String entry_type;
    private String cdc_code;
    private String cdc_description;
    private String omb_code;
    private String omb_description;
    private String date_created;
    private String client_date_created;
    private String created_user;   
	
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getEntry_type() {
		return entry_type;
	}
	public void setEntry_type(String entry_type) {
		this.entry_type = entry_type;
	}
	public String getCdc_code() {
		return cdc_code;
	}
	public void setCdc_code(String cdc_code) {
		this.cdc_code = cdc_code;
	}
	public String getCdc_description() {
		return cdc_description;
	}
	public void setCdc_description(String cdc_description) {
		this.cdc_description = cdc_description;
	}
	public String getOmb_code() {
		return omb_code;
	}
	public void setOmb_code(String omb_code) {
		this.omb_code = omb_code;
	}
	public String getOmb_description() {
		return omb_description;
	}
	public void setOmb_description(String omb_description) {
		this.omb_description = omb_description;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}   
	
}
