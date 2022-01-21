package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="provider_locations")
public class ORMSaveLocationProviders {

	@Id
	private Long setting_id;
	private Long provider_id;
	private Long location_id;
	private Boolean deleted;
	private String date_created;
	private String created_user;
	private String date_modified;
	private String modified_user;
	private Long practice_id;
	private String client_date_created;
	private String client_date_modified;
	private String system_ip;
	public Long getSetting_id() {
		return setting_id;
	}
	public void setSetting_id(Long setting_id) {
		this.setting_id = setting_id;
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
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
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
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	@Override
	public String toString() {
		return "ORMSaveLocationProviders [setting_id=" + setting_id + ", provider_id=" + provider_id + ", location_id="
				+ location_id + ", deleted=" + deleted + ", date_created=" + date_created + ", created_user="
				+ created_user + ", date_modified=" + date_modified + ", modified_user=" + modified_user
				+ ", practice_id=" + practice_id + ", client_date_created=" + client_date_created
				+ ", client_date_modified=" + client_date_modified + ", system_ip=" + system_ip + "]";
	}
	
	
}
 