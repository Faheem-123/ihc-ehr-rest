package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMAPIUserPatientGet {

	@Id
	private Long id;
	private Long user_id;
	private String user_name;
	private String alternate_account;
	private Long patient_id;
	private Long practice_id;
	private String created_user;
	private String date_created;
	private String client_date_created;
	private String name;
	private String dob;
	private String gender;
	private String address;
	private String home_phone;
	private String cell_phone;
	private String patient_status;
	private String user_relationship;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAlternate_account() {
		return alternate_account;
	}

	public void setAlternate_account(String alternate_account) {
		this.alternate_account = alternate_account;
	}

	public Long getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}

	public Long getPractice_id() {
		return practice_id;
	}

	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPatient_status() {
		return patient_status;
	}

	public void setPatient_status(String patient_status) {
		this.patient_status = patient_status;
	}

	public String getUser_relationship() {
		return user_relationship;
	}

	public void setUser_relationship(String user_relationship) {
		this.user_relationship = user_relationship;
	}	
	
	
}
