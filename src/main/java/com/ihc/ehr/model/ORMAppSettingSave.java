package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_setting_detail")
public class ORMAppSettingSave {

	@Id
	private Long detail_id;
	private Long setting_id;
	private String options;
	private Long practice_id;	
	private String created_user;
	private String date_created;
	private String client_date_created;
	private String modified_user;
	private String date_modified;
	private String client_date_modified;

	public Long getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}

	public Long getSetting_id() {
		return setting_id;
	}

	public void setSetting_id(Long setting_id) {
		this.setting_id = setting_id;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Long getPractice_id() {
		return practice_id;
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

	public String getClient_date_modified() {
		return client_date_modified;
	}

	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}

}
