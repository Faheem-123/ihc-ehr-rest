package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetUserAdministrationModules {

	 @Id
	    private String module_id;
	    private String module_group;
	    private String module_name;
	    private String module_swf;
	    private Boolean is_selected;
	    private Boolean is_enabled;
	    private String id;
	    private String created_user;
	    private String date_created;
	    private String client_date_created;
		public String getModule_id() {
			return module_id;
		}
		public void setModule_id(String module_id) {
			this.module_id = module_id;
		}
		public String getModule_group() {
			return module_group;
		}
		public void setModule_group(String module_group) {
			this.module_group = module_group;
		}
		public String getModule_name() {
			return module_name;
		}
		public void setModule_name(String module_name) {
			this.module_name = module_name;
		}
		public String getModule_swf() {
			return module_swf;
		}
		public void setModule_swf(String module_swf) {
			this.module_swf = module_swf;
		}
		public Boolean getIs_selected() {
			return is_selected;
		}
		public void setIs_selected(Boolean is_selected) {
			this.is_selected = is_selected;
		}
		public Boolean getIs_enabled() {
			return is_enabled;
		}
		public void setIs_enabled(Boolean is_enabled) {
			this.is_enabled = is_enabled;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
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
