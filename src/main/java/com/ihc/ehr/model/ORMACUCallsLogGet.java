package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMACUCallsLogGet {

	@Id
	private Long row_id;
	private String call_date;
	private String alternate_account;
	private String pat_name;
	private String appointment_date;
	private String location_name;
	private String provider_name;	
	private String status;
	public Long getRow_id() {
		return row_id;
	}
	public void setRow_id(Long row_id) {
		this.row_id = row_id;
	}
	public String getCall_date() {
		return call_date;
	}
	public void setCall_date(String call_date) {
		this.call_date = call_date;
	}
	public String getAlternate_account() {
		return alternate_account;
	}
	public void setAlternate_account(String alternate_account) {
		this.alternate_account = alternate_account;
	}
	public String getPat_name() {
		return pat_name;
	}
	public void setPat_name(String pat_name) {
		this.pat_name = pat_name;
	}
	public String getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(String appointment_date) {
		this.appointment_date = appointment_date;
	}	
	
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
