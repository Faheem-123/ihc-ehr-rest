package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetProviderUsers {
	 @Id
	    private Long user_id;
	    private String user_name;
	    private Long provider_id;
	    private String provider_name;
	    
		public Long getUser_id() {
			return user_id;
		}
		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public Long getProvider_id() {
			return provider_id;
		}
		public void setProvider_id(Long provider_id) {
			this.provider_id = provider_id;
		}
		public String getProvider_name() {
			return provider_name;
		}
		public void setProvider_name(String provider_name) {
			this.provider_name = provider_name;
		}
	    
	    
}
