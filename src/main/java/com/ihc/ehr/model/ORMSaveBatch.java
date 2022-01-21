package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim_batch")
public class ORMSaveBatch {

	 @Id
	    private String batch_id;
	    private String batch_name;
	    private String provider_id;
	    private String date;
	    private String date_created;
	    private String created_user;    
	    private String modified_user;
	    private String date_modified;
	    private String client_date_created;
	    private String client_date_modified;
	    private Boolean deleted;
	    private String practice_id;
	    private String batch_type;
	    
		public String getBatch_id() {
			return batch_id;
		}
		public void setBatch_id(String batch_id) {
			this.batch_id = batch_id;
		}
		public String getBatch_name() {
			return batch_name;
		}
		public void setBatch_name(String batch_name) {
			this.batch_name = batch_name;
		}
		public String getProvider_id() {
			return provider_id;
		}
		public void setProvider_id(String provider_id) {
			this.provider_id = provider_id;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
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
		public Boolean getDeleted() {
			return deleted;
		}
		public void setDeleted(Boolean deleted) {
			this.deleted = deleted;
		}
		public String getPractice_id() {
			return practice_id;
		}
		public void setPractice_id(String practice_id) {
			this.practice_id = practice_id;
		}
		public String getBatch_type() {
			return batch_type;
		}
		public void setBatch_type(String batch_type) {
			this.batch_type = batch_type;
		}
	    
}
