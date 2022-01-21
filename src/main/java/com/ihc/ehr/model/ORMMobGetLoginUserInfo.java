package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMMobGetLoginUserInfo {
	@Id
	private Long user_id;
	private String user_name;
	private String last_name;
	private String mname;
	private String first_name;
	private String user_fullname;
	private Long default_physician;
	private Long default_billing_phy;
	private Long default_location;
	private String default_role;
	//private String default_chart_setting;
	//private String default_bill;
	private String password;
	private String user_type;
	private String login_provider_id;
	private String login_provider_name;
	//private String reset_password_date;
	private String practice_id;
	private String default_prescription_role;
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
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getUser_fullname() {
		return user_fullname;
	}
	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}
	public Long getDefault_physician() {
		return default_physician;
	}
	public void setDefault_physician(Long default_physician) {
		this.default_physician = default_physician;
	}
	public Long getDefault_billing_phy() {
		return default_billing_phy;
	}
	public void setDefault_billing_phy(Long default_billing_phy) {
		this.default_billing_phy = default_billing_phy;
	}
	public Long getDefault_location() {
		return default_location;
	}
	public void setDefault_location(Long default_location) {
		this.default_location = default_location;
	}
	public String getDefault_role() {
		return default_role;
	}
	public void setDefault_role(String default_role) {
		this.default_role = default_role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getLogin_provider_id() {
		return login_provider_id;
	}
	public void setLogin_provider_id(String login_provider_id) {
		this.login_provider_id = login_provider_id;
	}
	public String getLogin_provider_name() {
		return login_provider_name;
	}
	public void setLogin_provider_name(String login_provider_name) {
		this.login_provider_name = login_provider_name;
	}
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public String getDefault_prescription_role() {
		return default_prescription_role;
	}
	public void setDefault_prescription_role(String default_prescription_role) {
		this.default_prescription_role = default_prescription_role;
	}
}