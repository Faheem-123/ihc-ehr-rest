package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetEncounterReport {
	@Id
	private String row_id;
	private int total_count;
    private String appointment_id;
    private String alternate_account;
    private String app_date;
    private String app_time;
    private String app_status;
    private String patient_name;
    private String dob;
    private String provider;
    private String location;
    private String listcpticd;
    private String claim_total;
    private String cptlist;
    private String listicd;
    private String source;
    private String draft_status;
    private String patient_id;
    private String claim_id;
    private String reason_detail;
    
    
	 
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public String getRow_id() {
		return row_id;
	}
	public void setRow_id(String row_id) {
		this.row_id = row_id;
	}
	public String getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(String appointment_id) {
		this.appointment_id = appointment_id;
	}
	public String getAlternate_account() {
		return alternate_account;
	}
	public void setAlternate_account(String alternate_account) {
		this.alternate_account = alternate_account;
	}
	public String getApp_date() {
		return app_date;
	}
	public void setApp_date(String app_date) {
		this.app_date = app_date;
	}
	public String getApp_time() {
		return app_time;
	}
	public void setApp_time(String app_time) {
		this.app_time = app_time;
	}
	public String getApp_status() {
		return app_status;
	}
	public void setApp_status(String app_status) {
		this.app_status = app_status;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getListcpticd() {
		return listcpticd;
	}
	public void setListcpticd(String listcpticd) {
		this.listcpticd = listcpticd;
	}
	public String getClaim_total() {
		return claim_total;
	}
	public void setClaim_total(String claim_total) {
		this.claim_total = claim_total;
	}
	public String getCptlist() {
		return cptlist;
	}
	public void setCptlist(String cptlist) {
		this.cptlist = cptlist;
	}
	public String getListicd() {
		return listicd;
	}
	public void setListicd(String listicd) {
		this.listicd = listicd;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDraft_status() {
		return draft_status;
	}
	public void setDraft_status(String draft_status) {
		this.draft_status = draft_status;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(String claim_id) {
		this.claim_id = claim_id;
	}
	public String getReason_detail() {
		return reason_detail;
	}
	public void setReason_detail(String reason_detail) {
		this.reason_detail = reason_detail;
	}
}
