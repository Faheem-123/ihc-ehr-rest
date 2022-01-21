package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chart_procedures")
public class ORMChartSurgery {

	@Id
	private String chart_procedures_id;
	private String chart_id;
	private String patient_id;
	private String procedure_code;
	private String description;
	private String comments;
	private String procedure_date;
	private String code_type;
	private String created_user;
	private String client_date_created;
	private String date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_modified;
	private Boolean education_provided;
	private String practice_id;
	private String system_ip;
	private String entry_type;
	
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public String getEntry_type() {
		return entry_type;
	}
	public void setEntry_type(String entry_type) {
		this.entry_type = entry_type;
	}
	public String getChart_procedures_id() {
		return chart_procedures_id;
	}
	public void setChart_procedures_id(String chart_procedures_id) {
		this.chart_procedures_id = chart_procedures_id;
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
	public String getProcedure_code() {
		return procedure_code;
	}
	public void setProcedure_code(String procedure_code) {
		this.procedure_code = procedure_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getProcedure_date() {
		return procedure_date;
	}
	public void setProcedure_date(String procedure_date) {
		this.procedure_date = procedure_date;
	}
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
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
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public Boolean getEducation_provided() {
		return education_provided;
	}
	public void setEducation_provided(Boolean education_provided) {
		this.education_provided = education_provided;
	}
	
	
	
}
