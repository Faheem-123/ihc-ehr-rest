package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="chart_familyhistory")
public class ORMFamilyHx {

	@Id
	private String familyhistory_id;
	private String chart_id;
	private String patient_id;
	private String relation;
	private String comment;
	private String icd_code;
	private String practice_id;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private String system_ip;
	private String onset_date;
	private String code_type;	
	private String description;
	public String getFamilyhistory_id() {
		return familyhistory_id;
	}
	public void setFamilyhistory_id(String familyhistory_id) {
		this.familyhistory_id = familyhistory_id;
	}
	public String getChart_id() {
		return chart_id;
	}
	public void setChart_id(String chart_id) {
		this.chart_id = chart_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getIcd_code() {
		return icd_code;
	}
	public void setIcd_code(String icd_code) {
		this.icd_code = icd_code;
	}
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
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
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public String getOnset_date() {
		return onset_date;
	}
	public void setOnset_date(String onset_date) {
		this.onset_date = onset_date;
	}
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
