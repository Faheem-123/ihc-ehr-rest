package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetLocalPrescriptions {

	@Id
	private Long chart_prescription_id;
	private Long patient_id;
	private Long chart_id;	
	private String drug_name;	
	private String drug_info;
	private String start_date;
	private String end_date;
	private String issued_date;	
	private String sig_text;	
	private String archive;	
	private String provider_name;
	private String location_name;	
	private String provider_id;
	private String created_user;
	private String date_created;
	private String client_date_created;
	private String pharmacist_notes;
	private Integer num_of_refills_allowed;
	
	public Long getChart_prescription_id() {
		return chart_prescription_id;
	}
	public void setChart_prescription_id(Long chart_prescription_id) {
		this.chart_prescription_id = chart_prescription_id;
	}
	
	
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public Long getChart_id() {
		return chart_id;
	}
	public void setChart_id(Long chart_id) {
		this.chart_id = chart_id;
	}
	public String getDrug_name() {
		return drug_name;
	}
	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}
	public String getDrug_info() {
		return drug_info;
	}
	public void setDrug_info(String drug_info) {
		this.drug_info = drug_info;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getIssued_date() {
		return issued_date;
	}
	public void setIssued_date(String issued_date) {
		this.issued_date = issued_date;
	}
	public String getSig_text() {
		return sig_text;
	}
	public void setSig_text(String sig_text) {
		this.sig_text = sig_text;
	}
	public String getArchive() {
		return archive;
	}
	public void setArchive(String archive) {
		this.archive = archive;
	}
	
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(String provider_id) {
		this.provider_id = provider_id;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getPharmacist_notes() {
		return pharmacist_notes;
	}
	public void setPharmacist_notes(String pharmacist_notes) {
		this.pharmacist_notes = pharmacist_notes;
	}
	public Integer getNum_of_refills_allowed() {
		return num_of_refills_allowed;
	}
	public void setNum_of_refills_allowed(Integer num_of_refills_allowed) {
		this.num_of_refills_allowed = num_of_refills_allowed;
	}												
	
	
}
