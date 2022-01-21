package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMUserConfigurableAppSettingGet {

	 @Id
     private Long setting_id;
     private Long detail_id;
     private String description;
     private String control_type;
     private String default_values;
     private String value;
     private String detail;     
     private String created_user;
     private String date_created;
     private String client_date_created;    
     
     
	public Long getSetting_id() {
		return setting_id;
	}
	public void setSetting_id(Long setting_id) {
		this.setting_id = setting_id;
	}
	public Long getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getControl_type() {
		return control_type;
	}
	public void setControl_type(String control_type) {
		this.control_type = control_type;
	}
	public String getDefault_values() {
		return default_values;
	}
	public void setDefault_values(String default_values) {
		this.default_values = default_values;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
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
    
	
	
}

