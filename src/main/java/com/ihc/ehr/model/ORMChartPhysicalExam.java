package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chart_physical_exam")
public class ORMChartPhysicalExam {

	@Id
    private String patient_organ_id;
    private String patient_id;
    private String chart_id;
    private String organ_value;
    private String pe_detail;
    private String created_user;
    private String date_created;
    private String modified_user;
    private String date_modified;
    private String client_date_created;
    private String client_date_modified;
    private Boolean deleted;
    private String practice_id;
	public String getPatient_organ_id() {
		return patient_organ_id;
	}
	public void setPatient_organ_id(String patient_organ_id) {
		this.patient_organ_id = patient_organ_id;
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
	public String getOrgan_value() {
		return organ_value;
	}
	public void setOrgan_value(String organ_value) {
		this.organ_value = organ_value;
	}
	public String getPe_detail() {
		return pe_detail;
	}
	public void setPe_detail(String pe_detail) {
		this.pe_detail = pe_detail;
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
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
    
}
