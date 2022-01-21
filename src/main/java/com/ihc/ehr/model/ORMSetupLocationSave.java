package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class ORMSetupLocationSave {

	@Id
	private String location_id;
	private String practice_id;
	private String name;
	private String zip;
	private String city;
	private String state;
	private String address;
	private String address2;
	private String phone;
	private String fax;
	private String clia_number;
	private String clia_expiry_date;
	private Boolean is_block;
	private Boolean deleted;
	private String created_user;
	private String modified_user;
	private String client_date_created;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private String facility_id;

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public String getPractice_id() {
		return practice_id;
	}

	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getClia_number() {
		return clia_number;
	}

	public void setClia_number(String clia_number) {
		this.clia_number = clia_number;
	}

	public String getClia_expiry_date() {
		return clia_expiry_date;
	}

	public void setClia_expiry_date(String clia_expiry_date) {
		this.clia_expiry_date = clia_expiry_date;
	}

	public Boolean getIs_block() {
		return is_block;
	}

	public void setIs_block(Boolean is_block) {
		this.is_block = is_block;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getCreated_user() {
		return created_user;
	}

	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}

	public String getModified_user() {
		return modified_user;
	}

	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
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

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public String getDate_modified() {
		return date_modified;
	}

	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}

	public String getFacility_id() {
		return facility_id;
	}

	public void setFacility_id(String facility_id) {
		this.facility_id = facility_id;
	}
	
	

}
