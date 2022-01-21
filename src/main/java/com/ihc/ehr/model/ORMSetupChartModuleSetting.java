package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chart_module_setting")
public class ORMSetupChartModuleSetting {

	 @Id
	    private String setting_id;
	    private String setting_name;
	    private String practice_id;
	    private String deleted;
	    private String created_user;
	    private String date_created;
	    private String modified_user;
	    private String date_modified;
	    private String client_date_created;
	    private String client_date_modified;
	    private Boolean is_print_setting;
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
		public String getPractice_id() {
			return practice_id;
		}
		public void setPractice_id(String practice_id) {
			this.practice_id = practice_id;
		}
		public String getDeleted() {
			return deleted;
		}
		public void setDeleted(String deleted) {
			this.deleted = deleted;
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
		public Boolean getIs_print_setting() {
			return is_print_setting;
		}
		public void setIs_print_setting(Boolean is_print_setting) {
			this.is_print_setting = is_print_setting;
		}
	    
	    
}
