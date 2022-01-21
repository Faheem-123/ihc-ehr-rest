package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMPracticeUsersList {

	@Id
	private Long user_id;
	private String user_name;
	private String last_name;
	private String first_name;
	private String mname;
	private String display_name;
	private String full_name;
	private String default_role;
	private String default_location;
	
	public String getDefault_role() {
		return default_role;
	}
	public void setDefault_role(String default_role) {
		this.default_role = default_role;
	}
	public String getDefault_location() {
		return default_location;
	}
	public void setDefault_location(String default_location) {
		this.default_location = default_location;
	}
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
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
}
