package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chart_annotation")
public class ORMChartAnnotation {
	@Id
    private Long chart_annotation_id;
    private String patient_id;
    private String chart_id;
    private String comments;
    private String provided_by;
    private String practice_id;
    private String deleted;
    private String created_user;
    private String client_date_created;
    private String modified_user;
    private String client_date_modified;
    private String date_created;
    private String system_ip;
    private String date_modified;
    

	public Long getChart_annotation_id() {
		return chart_annotation_id;
	}
	public void setChart_annotation_id(Long chart_annotation_id) {
		this.chart_annotation_id = chart_annotation_id;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getProvided_by() {
		return provided_by;
	}
	public void setProvided_by(String provided_by) {
		this.provided_by = provided_by;
	}
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
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
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
}
