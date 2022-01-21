package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMPatientGenderAgeLanguageGet {
	
	@Id
	private Long patient_id;
	private String gender_code;
	private Integer age_year;
	private Integer age_month;
	private String language_code;
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getGender_code() {
		return gender_code;
	}
	public void setGender_code(String gender_code) {
		this.gender_code = gender_code;
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
	public String getLanguage_code() {
		return language_code;
	}
	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}
}
