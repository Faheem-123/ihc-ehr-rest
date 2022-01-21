package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMMobGetPatient {

	@Id
	private Long patient_id;
	private String alternate_account;
	private String first_name;
	private String last_name;
	private String mname;
	private String gender_code;
	private String dob;
	private String pic;
	private String patient_language;
	private String language_code;
	private Boolean interpreter_req;	
	private String ssn;
	private String address;
	private String address2;
	private String zip;
	private String city;
	private String state;
	private String primary_contact_type;
	private String home_phone;	
	private String cell_phone;	
	private String primary_provider;
	private String location_id;
	private String comment;
	private String practice_id;
	private String patient_status;
	private String created_user;
	private String date_created;
	private String modified_user;
	private String date_modified;
	private String client_date_created;
	private String client_date_modified;
	private String primary_provider_name;
	private String location_name;

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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPatient_language() {
		return patient_language;
	}

	public void setPatient_language(String patient_language) {
		this.patient_language = patient_language;
	}

	public String getLanguage_code() {
		return language_code;
	}

	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}

	public Boolean getInterpreter_req() {
		return interpreter_req;
	}

	public void setInterpreter_req(Boolean interpreter_req) {
		this.interpreter_req = interpreter_req;
	}


	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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

	public String getPrimary_contact_type() {
		return primary_contact_type;
	}

	public void setPrimary_contact_type(String primary_contact_type) {
		this.primary_contact_type = primary_contact_type;
	}

	public String getHome_phone() {
		return home_phone;
	}

	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}

	public String getCell_phone() {
		return cell_phone;
	}

	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}

	public String getPrimary_provider() {
		return primary_provider;
	}

	public void setPrimary_provider(String primary_provider) {
		this.primary_provider = primary_provider;
	}

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

	public String getPractice_id() {
		return practice_id;
	}

	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}

	public String getPatient_status() {
		return patient_status;
	}

	public void setPatient_status(String patient_status) {
		this.patient_status = patient_status;
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

	public String getPrimary_provider_name() {
		return primary_provider_name;
	}

	public void setPrimary_provider_name(String primary_provider_name) {
		this.primary_provider_name = primary_provider_name;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
}
