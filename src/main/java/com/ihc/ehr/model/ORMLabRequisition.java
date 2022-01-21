package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMLabRequisition {

	 @Id
	    private String patient_id;
	    private String last_name;
	    private String first_name;
	    private String mname;
	    private String gender;
	    private String dob;
	    private String age;
	    private String age_month;
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
	    private String location_name;
	    private String order_date;
	    private String lab_acc_number;
	    private String pid;
	    private String facility_name;
	    private String status;
	    private boolean fax_sent;
	    private String patient_fax_number;
	    private String order_comments;
	    private String race;
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
		public String getAge_month() {
			return age_month;
		}
		public void setAge_month(String age_month) {
			this.age_month = age_month;
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
		public String getLocation_name() {
			return location_name;
		}
		public void setLocation_name(String location_name) {
			this.location_name = location_name;
		}
		public String getOrder_date() {
			return order_date;
		}
		public void setOrder_date(String order_date) {
			this.order_date = order_date;
		}
		public String getLab_acc_number() {
			return lab_acc_number;
		}
		public void setLab_acc_number(String lab_acc_number) {
			this.lab_acc_number = lab_acc_number;
		}
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public String getFacility_name() {
			return facility_name;
		}
		public void setFacility_name(String facility_name) {
			this.facility_name = facility_name;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public boolean isFax_sent() {
			return fax_sent;
		}
		public void setFax_sent(boolean fax_sent) {
			this.fax_sent = fax_sent;
		}
		public String getPatient_fax_number() {
			return patient_fax_number;
		}
		public void setPatient_fax_number(String patient_fax_number) {
			this.patient_fax_number = patient_fax_number;
		}
		public String getOrder_comments() {
			return order_comments;
		}
		public void setOrder_comments(String order_comments) {
			this.order_comments = order_comments;
		}
		public String getRace() {
			return race;
		}
		public void setRace(String race) {
			this.race = race;
		}
	    
	    
}
