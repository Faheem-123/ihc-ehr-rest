package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMPatientNotSeen{
	@Id
	private String sno;
	private String patient_id;
	private String pid;
	private String pat_name;
	private String location;
	private String provider;
	private String last_app_date;
	private String next_app;
	private String not_seen_days;
	
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPat_name() {
		return pat_name;
	}
	public void setPat_name(String pat_name) {
		this.pat_name = pat_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getLast_app_date() {
		return last_app_date;
	}
	public void setLast_app_date(String last_app_date) {
		this.last_app_date = last_app_date;
	}
	public String getNext_app() {
		return next_app;
	}
	public void setNext_app(String next_app) {
		this.next_app = next_app;
	}
	public String getNot_seen_days() {
		return not_seen_days;
	}
	public void setNot_seen_days(String not_seen_days) {
		this.not_seen_days = not_seen_days;
	}
}
