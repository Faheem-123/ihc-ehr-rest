package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMInsuranceSearch {
	@Id
	private String insurance_id;
	private Boolean chk;
	private String insurance_name;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String payer_number;
	
	
	
	public String getPayer_number() {
		return payer_number;
	}
	public void setPayer_number(String payer_number) {
		this.payer_number = payer_number;
	}
	public String getInsurance_id() {
		return insurance_id;
	}
	public void setInsurance_id(String insurance_id) {
		this.insurance_id = insurance_id;
	}
	public Boolean getChk() {
		return chk;
	}
	public void setChk(Boolean chk) {
		this.chk = chk;
	}
	public String getInsurance_name() {
		return insurance_name;
	}
	public void setInsurance_name(String insurance_name) {
		this.insurance_name = insurance_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
}
