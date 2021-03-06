package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMFaxConfigGet {
	
	@Id
	private Long id;
	private String fax_no;
	private String display_name;
	private String fax_cover_path;
	private String user_name;
	private String password;
	private String account_id;
	private String api_key;
	private String company_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFax_no() {
		return fax_no;
	}
	public void setFax_no(String fax_no) {
		this.fax_no = fax_no;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getFax_cover_path() {
		return fax_cover_path;
	}
	public void setFax_cover_path(String fax_cover_path) {
		this.fax_cover_path = fax_cover_path;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getApi_key() {
		return api_key;
	}
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
	
	

}
