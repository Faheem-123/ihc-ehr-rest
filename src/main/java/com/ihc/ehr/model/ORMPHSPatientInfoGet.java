package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMPHSPatientInfoGet {

	@Id
	private Long chart_id;
	private Long patient_id;
	private String dob;
	private String gender_code;	
	private String zip;
	private Integer age_years;
	private Integer age_months;
	private Integer age_days;
	private String reason_detail;
	private String hpi_detail;
	private String visit_date;
	private Boolean complete;
	private String adress;
	private String city;
	private String state;
	private String visit_reason;
	private String visit_type;
	private String practice_short_name;
	private String practice_npi;
	private String county_code;
	private String height_feet;
	private String weight_lbs;
	private String smoking_snomed;
	private String smoking_status;
	private String discharge_code;
	private String discharge_date;
	public Long getChart_id() {
		return chart_id;
	}
	public void setChart_id(Long chart_id) {
		this.chart_id = chart_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender_code() {
		return gender_code;
	}
	public void setGender_code(String gender_code) {
		this.gender_code = gender_code;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Integer getAge_years() {
		return age_years;
	}
	public void setAge_years(Integer age_years) {
		this.age_years = age_years;
	}
	public Integer getAge_months() {
		return age_months;
	}
	public void setAge_months(Integer age_months) {
		this.age_months = age_months;
	}
	public Integer getAge_days() {
		return age_days;
	}
	public void setAge_days(Integer age_days) {
		this.age_days = age_days;
	}
	public String getReason_detail() {
		return reason_detail;
	}
	public void setReason_detail(String reason_detail) {
		this.reason_detail = reason_detail;
	}
	public String getHpi_detail() {
		return hpi_detail;
	}
	public void setHpi_detail(String hpi_detail) {
		this.hpi_detail = hpi_detail;
	}
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	public Boolean getComplete() {
		return complete;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
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
	public String getVisit_reason() {
		return visit_reason;
	}
	public void setVisit_reason(String visit_reason) {
		this.visit_reason = visit_reason;
	}
	public String getVisit_type() {
		return visit_type;
	}
	public void setVisit_type(String visit_type) {
		this.visit_type = visit_type;
	}
	public String getPractice_short_name() {
		return practice_short_name;
	}
	public void setPractice_short_name(String practice_short_name) {
		this.practice_short_name = practice_short_name;
	}
	public String getPractice_npi() {
		return practice_npi;
	}
	public void setPractice_npi(String practice_npi) {
		this.practice_npi = practice_npi;
	}
	public String getCounty_code() {
		return county_code;
	}
	public void setCounty_code(String county_code) {
		this.county_code = county_code;
	}
	public String getHeight_feet() {
		return height_feet;
	}
	public void setHeight_feet(String height_feet) {
		this.height_feet = height_feet;
	}
	public String getWeight_lbs() {
		return weight_lbs;
	}
	public void setWeight_lbs(String weight_lbs) {
		this.weight_lbs = weight_lbs;
	}
	public String getSmoking_snomed() {
		return smoking_snomed;
	}
	public void setSmoking_snomed(String smoking_snomed) {
		this.smoking_snomed = smoking_snomed;
	}
	public String getSmoking_status() {
		return smoking_status;
	}
	public void setSmoking_status(String smoking_status) {
		this.smoking_status = smoking_status;
	}
	public String getDischarge_code() {
		return discharge_code;
	}
	public void setDischarge_code(String discharge_code) {
		this.discharge_code = discharge_code;
	}
	public String getDischarge_date() {
		return discharge_date;
	}
	public void setDischarge_date(String discharge_date) {
		this.discharge_date = discharge_date;
	}	
}
