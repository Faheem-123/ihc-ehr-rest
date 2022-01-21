package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientDrugAbuseSummary{
	@Id
    private String health_check_id	;
    private String form_id	;
    private String form_name	;
    private String form_description	;
    private String patient_id	;
    private String chart_id	;
    private String practice_id	;
    private Boolean deleted	;
    private String created_user	;
    private String modified_user	;
    private String client_date_created;	
    private String client_date_modified;	
    private String date_created	;
    private String date_modified;
    private String visit_date;
    private String sequence;
    private String file_link;
    private String provider1_sign_info;
    private String test_date;
    private String score;
    private String reference_id;
    private String send_date;
    private String drugresults;
	public String getHealth_check_id() {
		return health_check_id;
	}
	public void setHealth_check_id(String health_check_id) {
		this.health_check_id = health_check_id;
	}
	public String getForm_id() {
		return form_id;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}
	public String getForm_name() {
		return form_name;
	}
	public void setForm_name(String form_name) {
		this.form_name = form_name;
	}
	public String getForm_description() {
		return form_description;
	}
	public void setForm_description(String form_description) {
		this.form_description = form_description;
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
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
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
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getFile_link() {
		return file_link;
	}
	public void setFile_link(String file_link) {
		this.file_link = file_link;
	}
	public String getProvider1_sign_info() {
		return provider1_sign_info;
	}
	public void setProvider1_sign_info(String provider1_sign_info) {
		this.provider1_sign_info = provider1_sign_info;
	}
	public String getTest_date() {
		return test_date;
	}
	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getReference_id() {
		return reference_id;
	}
	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	public String getDrugresults() {
		return drugresults;
	}
	public void setDrugresults(String drugresults) {
		this.drugresults = drugresults;
	}
}
