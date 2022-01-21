package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPracticeLabs {
	@Id
	private String id;
	private String name;
	private String address;
	private String phone;
	private boolean is_external_lab;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isIs_external_lab() {
		return is_external_lab;
	}
	public void setIs_external_lab(boolean is_external_lab) {
		this.is_external_lab = is_external_lab;
	}
	
	

}
