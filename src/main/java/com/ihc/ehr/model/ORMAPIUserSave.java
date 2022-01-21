package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="api_users")
public class ORMAPIUserSave {

	@Id
    private Long user_id;
    private String user_type;
    private String user_name;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private String contact_no;
    private Boolean can_access_all_patients;    
    private Long practice_id;
    private Boolean is_blocked;
    private String created_user;
    private String date_created;
    private String client_date_created;
    private String modified_user;
    private String date_modified;
    private String client_date_modified;
    private String system_ip;
    private Boolean change_password;
    private String user_rights;    
    
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
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
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public Boolean getCan_access_all_patients() {
		return can_access_all_patients;
	}
	public void setCan_access_all_patients(Boolean can_access_all_patients) {
		this.can_access_all_patients = can_access_all_patients;
	}	
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public Boolean getIs_blocked() {
		return is_blocked;
	}
	public void setIs_blocked(Boolean is_blocked) {
		this.is_blocked = is_blocked;
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
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public Boolean getChange_password() {
		return change_password;
	}
	public void setChange_password(Boolean change_password) {
		this.change_password = change_password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUser_rights() {
		return user_rights;
	}
	public void setUser_rights(String user_rights) {
		this.user_rights = user_rights;
	}
}
