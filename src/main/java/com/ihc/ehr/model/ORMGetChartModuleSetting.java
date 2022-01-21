package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetChartModuleSetting {

	@Id
	private String setting_id;
	 private String setting_name;
	 
	public String getSetting_id() {
		return setting_id;
	}
	public void setSetting_id(String setting_id) {
		this.setting_id = setting_id;
	}
	public String getSetting_name() {
		return setting_name;
	}
	public void setSetting_name(String setting_name) {
		this.setting_name = setting_name;
	}
	 
	 
}
