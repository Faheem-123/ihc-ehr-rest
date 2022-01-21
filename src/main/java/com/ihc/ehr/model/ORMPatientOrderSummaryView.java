package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMPatientOrderSummaryView {

	@Id
	private String order_id;
	private String patient_id;
	private String chart_id;
	private String order_date;
	private String order_status;
	private String provider_name;
	private String lab_name;
	private String location_name;
	private String follow_up_notes;
	private String cpt;
	private String icd;
	private String order_type;
	private String facility_filter;
	
	
	public String getFacility_filter() {
		return facility_filter;
	}
	public void setFacility_filter(String facility_filter) {
		this.facility_filter = facility_filter;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getChart_id() {
		return chart_id;
	}
	public void setChart_id(String chart_id) {
		this.chart_id = chart_id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
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
	public String getFollow_up_notes() {
		return follow_up_notes;
	}
	public void setFollow_up_notes(String follow_up_notes) {
		this.follow_up_notes = follow_up_notes;
	}
	public String getCpt() {
		return cpt;
	}
	public void setCpt(String cpt) {
		this.cpt = cpt;
	}
	public String getIcd() {
		return icd;
	}
	public void setIcd(String icd) {
		this.icd = icd;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	
}
