package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetAppointmentRules {
	
	@Id
	private Long setting_id;
    private Long rule_id;
    private Long provider_id;
    private Long location_id;
    private String location_name;
    private Integer slot_appointment_size;
    private String date_created;
    private String created_user;
    private String client_date_created;
	public Long getSetting_id() {
		return setting_id;
	}
	public void setSetting_id(Long setting_id) {
		this.setting_id = setting_id;
	}
	public Long getRule_id() {
		return rule_id;
	}
	public void setRule_id(Long rule_id) {
		this.rule_id = rule_id;
	}
	public Long getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(Long provider_id) {
		this.provider_id = provider_id;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public Integer getSlot_appointment_size() {
		return slot_appointment_size;
	}
	public void setSlot_appointment_size(Integer slot_appointment_size) {
		this.slot_appointment_size = slot_appointment_size;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
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
}
