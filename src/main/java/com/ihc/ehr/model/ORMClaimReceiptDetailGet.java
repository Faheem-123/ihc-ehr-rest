package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMClaimReceiptDetailGet {

	@Id
	private long claim_id;
	private String dos;
	private String claim_total;
	private String pos;
	private String loc_address;
	private long location_id;
	private String loc_city;
	private String loc_state;
	private String loc_zip;
	private String bill_phone;
	private String bill_fax;
	private String bill_email;
	private String attending_physician;
	private String provider_license;
	private String provider_ein;
	private String provider_npi;
	private String bill_nip;
	private String pat_name;
	private String pat_dob;	
	private String pat_contact_no;
	private String pat_address;
	private String pat_city;
	private String pat_state;
	private String pat_zip;
	private String insurance_paid;
	private String patient_paid;
	private String adjustment;
	private String balance_due;
	public long getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(long claim_id) {
		this.claim_id = claim_id;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getClaim_total() {
		return claim_total;
	}
	public void setClaim_total(String claim_total) {
		this.claim_total = claim_total;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getLoc_address() {
		return loc_address;
	}
	public void setLoc_address(String loc_address) {
		this.loc_address = loc_address;
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
	public String getBill_phone() {
		return bill_phone;
	}
	public void setBill_phone(String bill_phone) {
		this.bill_phone = bill_phone;
	}
	public String getBill_fax() {
		return bill_fax;
	}
	public void setBill_fax(String bill_fax) {
		this.bill_fax = bill_fax;
	}
	public String getBill_email() {
		return bill_email;
	}
	public void setBill_email(String bill_email) {
		this.bill_email = bill_email;
	}
	public String getAttending_physician() {
		return attending_physician;
	}
	public void setAttending_physician(String attending_physician) {
		this.attending_physician = attending_physician;
	}
	public String getProvider_license() {
		return provider_license;
	}
	public void setProvider_license(String provider_license) {
		this.provider_license = provider_license;
	}
	public String getProvider_ein() {
		return provider_ein;
	}
	public void setProvider_ein(String provider_ein) {
		this.provider_ein = provider_ein;
	}
	public String getBill_nip() {
		return bill_nip;
	}
	public void setBill_nip(String bill_nip) {
		this.bill_nip = bill_nip;
	}
	public String getPat_name() {
		return pat_name;
	}
	public void setPat_name(String pat_name) {
		this.pat_name = pat_name;
	}
	public String getPat_dob() {
		return pat_dob;
	}
	public void setPat_dob(String pat_dob) {
		this.pat_dob = pat_dob;
	}
	public String getPat_contact_no() {
		return pat_contact_no;
	}
	public void setPat_contact_no(String pat_contact_no) {
		this.pat_contact_no = pat_contact_no;
	}
	public String getPat_address() {
		return pat_address;
	}
	public void setPat_address(String pat_address) {
		this.pat_address = pat_address;
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
	public String getInsurance_paid() {
		return insurance_paid;
	}
	public void setInsurance_paid(String insurance_paid) {
		this.insurance_paid = insurance_paid;
	}
	public String getPatient_paid() {
		return patient_paid;
	}
	public void setPatient_paid(String patient_paid) {
		this.patient_paid = patient_paid;
	}
	public String getAdjustment() {
		return adjustment;
	}
	public void setAdjustment(String adjustment) {
		this.adjustment = adjustment;
	}
	public String getBalance_due() {
		return balance_due;
	}
	public void setBalance_due(String balance_due) {
		this.balance_due = balance_due;
	}
	public String getProvider_npi() {
		return provider_npi;
	}
	public void setProvider_npi(String provider_npi) {
		this.provider_npi = provider_npi;
	}
	public long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}
	
	
	
}