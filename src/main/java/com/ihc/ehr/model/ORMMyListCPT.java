package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mylist_cpt")
public class ORMMyListCPT {

	 @Id
	    private String id;
	    private String proc_code; 
	    private String proc_description;
	    private String provider_id;
	    private String practice_id; 
	    private boolean deleted;
	    private String date_created;
	    private String created_user;
	    private String client_date_created;
	    private String date_modified;
	    private String modified_user;
	    private String client_date_modified;
	    private String expiry_date;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getProc_code() {
			return proc_code;
		}
		public void setProc_code(String proc_code) {
			this.proc_code = proc_code;
		}
		public String getProc_description() {
			return proc_description;
		}
		public void setProc_description(String proc_description) {
			this.proc_description = proc_description;
		}
		public String getProvider_id() {
			return provider_id;
		}
		public void setProvider_id(String provider_id) {
			this.provider_id = provider_id;
		}
		public String getPractice_id() {
			return practice_id;
		}
		public void setPractice_id(String practice_id) {
			this.practice_id = practice_id;
		}
		public boolean isDeleted() {
			return deleted;
		}
		public void setDeleted(boolean deleted) {
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
		public String getClient_date_created() {
			return client_date_created;
		}
		public void setClient_date_created(String client_date_created) {
			this.client_date_created = client_date_created;
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
		public String getClient_date_modified() {
			return client_date_modified;
		}
		public void setClient_date_modified(String client_date_modified) {
			this.client_date_modified = client_date_modified;
		}
		public String getExpiry_date() {
			return expiry_date;
		}
		public void setExpiry_date(String expiry_date) {
			this.expiry_date = expiry_date;
		}
	    
	    

}
