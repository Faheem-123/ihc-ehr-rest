package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lab_category_sub")
public class ORMSetupSubLabCategory {

	 @Id
	    private String sub_category_id; 
	    private String category_id;
	    private String sub_category_name;
	    private Boolean deleted;
	    private String created_user;
	    private String client_date_created;
	    private String modified_user;
	    private String client_date_modified;
	    private String date_created;
	    private String date_modified;
	    private String system_ip;
	    private String practice_id;
	    
	    
	    
		public String getPractice_id() {
			return practice_id;
		}
		public void setPractice_id(String practice_id) {
			this.practice_id = practice_id;
		}
		public String getSub_category_id() {
			return sub_category_id;
		}
		public void setSub_category_id(String sub_category_id) {
			this.sub_category_id = sub_category_id;
		}
		public String getCategory_id() {
			return category_id;
		}
		public void setCategory_id(String category_id) {
			this.category_id = category_id;
		}
		public String getSub_category_name() {
			return sub_category_name;
		}
		public void setSub_category_name(String sub_category_name) {
			this.sub_category_name = sub_category_name;
		}
		public Boolean getDeleted() {
			return deleted;
		}
		public void setDeleted(Boolean deleted) {
			this.deleted = deleted;
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
		public String getModified_user() {
			return modified_user;
		}
		public void setModified_user(String modified_user) {
			this.modified_user = modified_user;
		}
		public String getClient_date_modified() {
			return client_date_modified;
		}
		public void setClient_date_modified(String client_date_modified) {
			this.client_date_modified = client_date_modified;
		}
		public String getDate_created() {
			return date_created;
		}
		public void setDate_created(String date_created) {
			this.date_created = date_created;
		}
		public String getDate_modified() {
			return date_modified;
		}
		public void setDate_modified(String date_modified) {
			this.date_modified = date_modified;
		}
		public String getSystem_ip() {
			return system_ip;
		}
		public void setSystem_ip(String system_ip) {
			this.system_ip = system_ip;
		}

	    
}
