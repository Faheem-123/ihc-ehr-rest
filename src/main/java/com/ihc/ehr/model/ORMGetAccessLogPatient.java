package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetAccessLogPatient {

	@Id	
	private Long row_id;
	private String event_type;
	private String access_date;
	private String access_by;
	private Long patient_id;
	private String alternate_account;
	private String first_name;
	private String last_name;
	private String mname;
	private String gender;
	private String dob;
	private String patient_language;
	private String marital_status;
	private String ssn;
	private String modified_user;
	private String date_modified;
	private Boolean is_emergency_accessed;
	public Long getRow_id() {
		return row_id;
	}
	public void setRow_id(Long row_id) {
		this.row_id = row_id;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getAccess_date() {
		return access_date;
	}
	public void setAccess_date(String access_date) {
		this.access_date = access_date;
	}
	public String getAccess_by() {
		return access_by;
	}
	public void setAccess_by(String access_by) {
		this.access_by = access_by;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getAlternate_account() {
		return alternate_account;
	}
	public void setAlternate_account(String alternate_account) {
		this.alternate_account = alternate_account;
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
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPatient_language() {
		return patient_language;
	}
	public void setPatient_language(String patient_language) {
		this.patient_language = patient_language;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
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
	public Boolean getIs_emergency_accessed() {
		return is_emergency_accessed;
	}
	public void setIs_emergency_accessed(Boolean is_emergency_accessed) {
		this.is_emergency_accessed = is_emergency_accessed;
	}	
}
