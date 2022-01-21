package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMLabResultReportHeader {
	@Id
    private String patient_id;
    private String last_name;
    private String first_name;
    private String mname;
    private String gender;
    private String dob;
    private String age;
    private String address;
    private String ssn;
    private String zip;
    private String city;
    private String state;
    private String home_phone;
    private String work_phone;
    private String providername;
    private String providerphone;
    private String fax;
    private String npi;
    private String ordertime;
    private String fasting;
    private String stat;
    private String bill_type;
    private String lab_name;
    private String order_date;
    private String entered_date;
    private String reported_date;
    private String lab_acc_number;
    private String loc_name;
    private String lab_address;
    private String  lab_phone;
    private String alternate_account;
    private String race;
    private String order_comment;
    private String pid_comment;
    private String specimen_number;
    private String clinical_info;
    
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
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
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
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
	public String getHome_phone() {
		return home_phone;
	}
	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}
	public String getWork_phone() {
		return work_phone;
	}
	public void setWork_phone(String work_phone) {
		this.work_phone = work_phone;
	}
	public String getProvidername() {
		return providername;
	}
	public void setProvidername(String providername) {
		this.providername = providername;
	}
	public String getProviderphone() {
		return providerphone;
	}
	public void setProviderphone(String providerphone) {
		this.providerphone = providerphone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getNpi() {
		return npi;
	}
	public void setNpi(String npi) {
		this.npi = npi;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getFasting() {
		return fasting;
	}
	public void setFasting(String fasting) {
		this.fasting = fasting;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getBill_type() {
		return bill_type;
	}
	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}
	public String getLab_name() {
		return lab_name;
	}
	public void setLab_name(String lab_name) {
		this.lab_name = lab_name;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getEntered_date() {
		return entered_date;
	}
	public void setEntered_date(String entered_date) {
		this.entered_date = entered_date;
	}
	public String getReported_date() {
		return reported_date;
	}
	public void setReported_date(String reported_date) {
		this.reported_date = reported_date;
	}
	public String getLab_acc_number() {
		return lab_acc_number;
	}
	public void setLab_acc_number(String lab_acc_number) {
		this.lab_acc_number = lab_acc_number;
	}
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	public String getLab_address() {
		return lab_address;
	}
	public void setLab_address(String lab_address) {
		this.lab_address = lab_address;
	}
	public String getLab_phone() {
		return lab_phone;
	}
	public void setLab_phone(String lab_phone) {
		this.lab_phone = lab_phone;
	}
	public String getAlternate_account() {
		return alternate_account;
	}
	public void setAlternate_account(String alternate_account) {
		this.alternate_account = alternate_account;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getOrder_comment() {
		return order_comment;
	}
	public void setOrder_comment(String order_comment) {
		this.order_comment = order_comment;
	}
	public String getPid_comment() {
		return pid_comment;
	}
	public void setPid_comment(String pid_comment) {
		this.pid_comment = pid_comment;
	}
	public String getSpecimen_number() {
		return specimen_number;
	}
	public void setSpecimen_number(String specimen_number) {
		this.specimen_number = specimen_number;
	}
	public String getClinical_info() {
		return clinical_info;
	}
	public void setClinical_info(String clinical_info) {
		this.clinical_info = clinical_info;
	}
}
