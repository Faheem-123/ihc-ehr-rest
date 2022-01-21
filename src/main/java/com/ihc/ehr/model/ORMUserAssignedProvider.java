package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMUserAssignedProvider {
	@Id
	private Long user_provider_id;
    private Long provider_id;
    private String provider_name;
    private Boolean is_default;
    private String created_user;
    private String date_created;
    private String client_date_created;
    
	public Long getUser_provider_id() {
		return user_provider_id;
	}
	public void setUser_provider_id(Long user_provider_id) {
		this.user_provider_id = user_provider_id;
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
	public Boolean getIs_default() {
		return is_default;
	}
	public void setIs_default(Boolean is_default) {
		this.is_default = is_default;
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
