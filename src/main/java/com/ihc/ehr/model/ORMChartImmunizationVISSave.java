package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="chart_immunization_vis")
public class ORMChartImmunizationVISSave {

	
	@Id
	private Long chart_imm_vis_id;
	private Long chart_imm_id;
	private Long chart_id;
	private Long patient_id;
	private String vis_date_published;
	private String vis_date_presented;
	private String immunization_group_name;
	private String immunization_group_cvx_code;
	private String immunization_group_description;
	private String vis_encoded_text;
	private String vis_name;
	private String vis_gdti_code;
	private String coding_system;
	private Long practice_id;
	private String date_created;
	private String client_date_created;
	private String date_modified;
	private String client_date_modified;
	private String created_user;
	private String modified_user;
	private String system_ip;
	private Boolean deleted;
	
	
	
	public Long getChart_imm_vis_id() {
		return chart_imm_vis_id;
	}
	public void setChart_imm_vis_id(Long chart_imm_vis_id) {
		this.chart_imm_vis_id = chart_imm_vis_id;
	}
	public Long getChart_imm_id() {
		return chart_imm_id;
	}
	public void setChart_imm_id(Long chart_imm_id) {
		this.chart_imm_id = chart_imm_id;
	}
	public Long getChart_id() {
		return chart_id;
	}
	public void setChart_id(Long chart_id) {
		this.chart_id = chart_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getVis_date_published() {
		return vis_date_published;
	}
	public void setVis_date_published(String vis_date_published) {
		this.vis_date_published = vis_date_published;
	}
	public String getVis_date_presented() {
		return vis_date_presented;
	}
	public void setVis_date_presented(String vis_date_presented) {
		this.vis_date_presented = vis_date_presented;
	}
	public String getImmunization_group_name() {
		return immunization_group_name;
	}
	public void setImmunization_group_name(String immunization_group_name) {
		this.immunization_group_name = immunization_group_name;
	}
	public String getImmunization_group_cvx_code() {
		return immunization_group_cvx_code;
	}
	public void setImmunization_group_cvx_code(String immunization_group_cvx_code) {
		this.immunization_group_cvx_code = immunization_group_cvx_code;
	}
	public String getImmunization_group_description() {
		return immunization_group_description;
	}
	public void setImmunization_group_description(String immunization_group_description) {
		this.immunization_group_description = immunization_group_description;
	}
	public String getVis_encoded_text() {
		return vis_encoded_text;
	}
	public void setVis_encoded_text(String vis_encoded_text) {
		this.vis_encoded_text = vis_encoded_text;
	}
	public String getVis_name() {
		return vis_name;
	}
	public void setVis_name(String vis_name) {
		this.vis_name = vis_name;
	}
	public String getVis_gdti_code() {
		return vis_gdti_code;
	}
	public void setVis_gdti_code(String vis_gdti_code) {
		this.vis_gdti_code = vis_gdti_code;
	}
	public String getCoding_system() {
		return coding_system;
	}
	public void setCoding_system(String coding_system) {
		this.coding_system = coding_system;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
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
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
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
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
