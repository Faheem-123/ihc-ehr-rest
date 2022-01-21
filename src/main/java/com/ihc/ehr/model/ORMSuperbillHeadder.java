package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMSuperbillHeadder {

	@Id
    private String patient_id;
    private String alternate_account;
    private String practice_name;
    private String patient_name;
    private String gender;
    private String dob;
    private String pphonenumber;
    private String patient_address;
    private String primary_ins_name;
    private String primary_policy_number;
    private String primary_elig_date;
    private String primary_elig_status;
    private String secondary_ins_name;
    private String secondary_policy_number;
    private String secondary_elig_date;
    private String secondary_elig_status;
    private String appointmentdate;
    private String providername;
    private String locationname;
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getAlternate_account() {
		return alternate_account;
	}
	public void setAlternate_account(String alternate_account) {
		this.alternate_account = alternate_account;
	}
	public String getPractice_name() {
		return practice_name;
	}
	public void setPractice_name(String practice_name) {
		this.practice_name = practice_name;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
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
	public String getPphonenumber() {
		return pphonenumber;
	}
	public void setPphonenumber(String pphonenumber) {
		this.pphonenumber = pphonenumber;
	}
	public String getPatient_address() {
		return patient_address;
	}
	public void setPatient_address(String patient_address) {
		this.patient_address = patient_address;
	}
	public String getPrimary_ins_name() {
		return primary_ins_name;
	}
	public void setPrimary_ins_name(String primary_ins_name) {
		this.primary_ins_name = primary_ins_name;
	}
	public String getPrimary_policy_number() {
		return primary_policy_number;
	}
	public void setPrimary_policy_number(String primary_policy_number) {
		this.primary_policy_number = primary_policy_number;
	}
	public String getPrimary_elig_date() {
		return primary_elig_date;
	}
	public void setPrimary_elig_date(String primary_elig_date) {
		this.primary_elig_date = primary_elig_date;
	}
	public String getPrimary_elig_status() {
		return primary_elig_status;
	}
	public void setPrimary_elig_status(String primary_elig_status) {
		this.primary_elig_status = primary_elig_status;
	}
	public String getSecondary_ins_name() {
		return secondary_ins_name;
	}
	public void setSecondary_ins_name(String secondary_ins_name) {
		this.secondary_ins_name = secondary_ins_name;
	}
	public String getSecondary_policy_number() {
		return secondary_policy_number;
	}
	public void setSecondary_policy_number(String secondary_policy_number) {
		this.secondary_policy_number = secondary_policy_number;
	}
	public String getSecondary_elig_date() {
		return secondary_elig_date;
	}
	public void setSecondary_elig_date(String secondary_elig_date) {
		this.secondary_elig_date = secondary_elig_date;
	}
	public String getSecondary_elig_status() {
		return secondary_elig_status;
	}
	public void setSecondary_elig_status(String secondary_elig_status) {
		this.secondary_elig_status = secondary_elig_status;
	}
	public String getAppointmentdate() {
		return appointmentdate;
	}
	public void setAppointmentdate(String appointmentdate) {
		this.appointmentdate = appointmentdate;
	}
	public String getProvidername() {
		return providername;
	}
	public void setProvidername(String providername) {
		this.providername = providername;
	}
	public String getLocationname() {
		return locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
    
    
    
}
