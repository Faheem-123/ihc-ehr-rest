package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPayRollProviders {
	 @Id
	    private String provider_id;
	    private String provider_category_id;
	    private String created_user;
	    private String date_created;
	    private String client_date_created;
	    private String provider_name;
	    private Boolean selected;
	    
		public String getProvider_id() {
			return provider_id;
		}
		public void setProvider_id(String provider_id) {
			this.provider_id = provider_id;
		}
		public String getProvider_category_id() {
			return provider_category_id;
		}
		public void setProvider_category_id(String provider_category_id) {
			this.provider_category_id = provider_category_id;
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
		public String getProvider_name() {
			return provider_name;
		}
		public void setProvider_name(String provider_name) {
			this.provider_name = provider_name;
		}
		public Boolean getSelected() {
			return selected;
		}
		public void setSelected(Boolean selected) {
			this.selected = selected;
		}
}