package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientHeaderInfo {

	@Id
	private Long patient_id;
	private String pid;
	private String last_name;
	private String first_name;	
	private String gender_code;
	private String dob;
	private String address;
	private String zip;
	private String city;
	private String state;	
	private String primary_contact_no;
	private String marital_status;
	private Integer age_year;
	private Integer age_month;
	private Integer age_days;
	private String pic;
	private String ssn;
	private String dob_101;
	
	
	
	public String getDob_101() {
		return dob_101;
	}
	public void setDob_101(String dob_101) {
		this.dob_101 = dob_101;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
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
	public String getGender_code() {
		return gender_code;
	}
	public void setGender_code(String gender_code) {
		this.gender_code = gender_code;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPrimary_contact_no() {
		return primary_contact_no;
	}
	public void setPrimary_contact_no(String primary_contact_no) {
		this.primary_contact_no = primary_contact_no;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public Integer getAge_year() {
		return age_year;
	}
	public void setAge_year(Integer age_year) {
		this.age_year = age_year;
	}
	public Integer getAge_month() {
		return age_month;
	}
	public void setAge_month(Integer age_month) {
		this.age_month = age_month;
	}
	public Integer getAge_days() {
		return age_days;
	}
	public void setAge_days(Integer age_days) {
		this.age_days = age_days;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
