package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetNewCropIrxInfo {

	@Id
    private String chart_id;
    private String provider_id;
    private String prv_lname;
    private String prv_fname;
    private String prv_mi;
    private String prv_dea_no;
    private String prv_upin;
    private String prv_state;
    private String prv_state_license;
    private String prv_npi;
    private String location_id;
    private String loc_name;
    private String loc_address;
    private String loc_address2;
    private String loc_city;
    private String loc_state;
    private String loc_zip;
    private String loc_phone;
    private String loc_fax;
    private String patient_id;
    private String pat_lname;
    private String pat_fname;
    private String pat_mname;
    private String pat_address;
    private String pat_address2;
    private String pat_city;
    private String pat_state;
    private String pat_zip;
    private String pat_home_phone;
    private String pat_dob;
    private String pat_gender;
    private Boolean is_blocked;
    private Boolean is_prescription_disable;
    private String pat_language;
    private String pat_height_inches;
    private String pat_weight_lbs;
    private Integer pat_age_year;

	public String getPat_language() {
		return pat_language;
	}
	public void setPat_language(String pat_language) {
		this.pat_language = pat_language;
	}
	public String getPat_height_inches() {
		return pat_height_inches;
	}
	public void setPat_height_inches(String pat_height_inches) {
		this.pat_height_inches = pat_height_inches;
	}
	public String getPat_weight_lbs() {
		return pat_weight_lbs;
	}
	public void setPat_weight_lbs(String pat_weight_lbs) {
		this.pat_weight_lbs = pat_weight_lbs;
	}
	public String getChart_id() {
		return chart_id;
	}
	public void setChart_id(String chart_id) {
		this.chart_id = chart_id;
	}
	public String getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(String provider_id) {
		this.provider_id = provider_id;
	}
	public String getPrv_lname() {
		return prv_lname;
	}
	public void setPrv_lname(String prv_lname) {
		this.prv_lname = prv_lname;
	}
	public String getPrv_fname() {
		return prv_fname;
	}
	public void setPrv_fname(String prv_fname) {
		this.prv_fname = prv_fname;
	}
	public String getPrv_mi() {
		return prv_mi;
	}
	public void setPrv_mi(String prv_mi) {
		this.prv_mi = prv_mi;
	}
	public String getPrv_dea_no() {
		return prv_dea_no;
	}
	public void setPrv_dea_no(String prv_dea_no) {
		this.prv_dea_no = prv_dea_no;
	}
	public String getPrv_upin() {
		return prv_upin;
	}
	public void setPrv_upin(String prv_upin) {
		this.prv_upin = prv_upin;
	}
	public String getPrv_state() {
		return prv_state;
	}
	public void setPrv_state(String prv_state) {
		this.prv_state = prv_state;
	}
	public String getPrv_state_license() {
		return prv_state_license;
	}
	public void setPrv_state_license(String prv_state_license) {
		this.prv_state_license = prv_state_license;
	}
	public String getPrv_npi() {
		return prv_npi;
	}
	public void setPrv_npi(String prv_npi) {
		this.prv_npi = prv_npi;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	public String getLoc_address() {
		return loc_address;
	}
	public void setLoc_address(String loc_address) {
		this.loc_address = loc_address;
	}
	public String getLoc_address2() {
		return loc_address2;
	}
	public void setLoc_address2(String loc_address2) {
		this.loc_address2 = loc_address2;
	}
	public String getLoc_city() {
		return loc_city;
	}
	public void setLoc_city(String loc_city) {
		this.loc_city = loc_city;
	}
	public String getLoc_state() {
		return loc_state;
	}
	public void setLoc_state(String loc_state) {
		this.loc_state = loc_state;
	}
	public String getLoc_zip() {
		return loc_zip;
	}
	public void setLoc_zip(String loc_zip) {
		this.loc_zip = loc_zip;
	}
	public String getLoc_phone() {
		return loc_phone;
	}
	public void setLoc_phone(String loc_phone) {
		this.loc_phone = loc_phone;
	}
	public String getLoc_fax() {
		return loc_fax;
	}
	public void setLoc_fax(String loc_fax) {
		this.loc_fax = loc_fax;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getPat_lname() {
		return pat_lname;
	}
	public void setPat_lname(String pat_lname) {
		this.pat_lname = pat_lname;
	}
	public String getPat_fname() {
		return pat_fname;
	}
	public void setPat_fname(String pat_fname) {
		this.pat_fname = pat_fname;
	}
	public String getPat_mname() {
		return pat_mname;
	}
	public void setPat_mname(String pat_mname) {
		this.pat_mname = pat_mname;
	}
	public String getPat_address() {
		return pat_address;
	}
	public void setPat_address(String pat_address) {
		this.pat_address = pat_address;
	}
	public String getPat_address2() {
		return pat_address2;
	}
	public void setPat_address2(String pat_address2) {
		this.pat_address2 = pat_address2;
	}
	public String getPat_city() {
		return pat_city;
	}
	public void setPat_city(String pat_city) {
		this.pat_city = pat_city;
	}
	public String getPat_state() {
		return pat_state;
	}
	public void setPat_state(String pat_state) {
		this.pat_state = pat_state;
	}
	public String getPat_zip() {
		return pat_zip;
	}
	public void setPat_zip(String pat_zip) {
		this.pat_zip = pat_zip;
	}
	public String getPat_home_phone() {
		return pat_home_phone;
	}
	public void setPat_home_phone(String pat_home_phone) {
		this.pat_home_phone = pat_home_phone;
	}
	public String getPat_dob() {
		return pat_dob;
	}
	public void setPat_dob(String pat_dob) {
		this.pat_dob = pat_dob;
	}
	public String getPat_gender() {
		return pat_gender;
	}
	public void setPat_gender(String pat_gender) {
		this.pat_gender = pat_gender;
	}
	public Boolean getIs_blocked() {
		return is_blocked;
	}
	public void setIs_blocked(Boolean is_blocked) {
		this.is_blocked = is_blocked;
	}
	public Boolean getIs_prescription_disable() {
		return is_prescription_disable;
	}
	public void setIs_prescription_disable(Boolean is_prescription_disable) {
		this.is_prescription_disable = is_prescription_disable;
	}
	public Integer getPat_age_year() {
		return pat_age_year;
	}
	public void setPat_age_year(Integer pat_age_year) {
		this.pat_age_year = pat_age_year;
	}
    
	
}
