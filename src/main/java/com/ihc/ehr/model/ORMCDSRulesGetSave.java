package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cds_rules")
public class ORMCDSRulesGetSave {

	@Id
	private Long rule_id;
	private String rule_group;
	private String rule_name;
	private String notification;
	private String description_html;
	private String description;
	private String reference_link;
	private String comments;
	private Long practice_id;
	private String system_ip;
	private Boolean deleted;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private String sp_name;
	private Boolean built_in;
	private String criteria_description;
	private Boolean is_demographics;
	private Boolean is_vitals;
	private Boolean is_diagnosis;
	private Boolean is_labs;
	private Boolean is_allergy;
	private Boolean is_medication;
	private Boolean is_procedures;
	private Boolean is_lifestyle;
	private Boolean is_healthmaintenance;
	private Boolean is_immunization;

	private String intervention_developer;
	private String funding_source;
	private String release_version;
	private String diagnostic_therapeutic_link;
	private String citation;
	public Long getRule_id() {
		return rule_id;
	}
	public void setRule_id(Long rule_id) {
		this.rule_id = rule_id;
	}
	public String getRule_group() {
		return rule_group;
	}
	public void setRule_group(String rule_group) {
		this.rule_group = rule_group;
	}
	public String getRule_name() {
		return rule_name;
	}
	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	public String getDescription_html() {
		return description_html;
	}
	public void setDescription_html(String description_html) {
		this.description_html = description_html;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReference_link() {
		return reference_link;
	}
	public void setReference_link(String reference_link) {
		this.reference_link = reference_link;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
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
	public String getSp_name() {
		return sp_name;
	}
	public void setSp_name(String sp_name) {
		this.sp_name = sp_name;
	}
	public Boolean getBuilt_in() {
		return built_in;
	}
	public void setBuilt_in(Boolean built_in) {
		this.built_in = built_in;
	}
	public String getCriteria_description() {
		return criteria_description;
	}
	public void setCriteria_description(String criteria_description) {
		this.criteria_description = criteria_description;
	}
	public Boolean getIs_demographics() {
		return is_demographics;
	}
	public void setIs_demographics(Boolean is_demographics) {
		this.is_demographics = is_demographics;
	}
	public Boolean getIs_vitals() {
		return is_vitals;
	}
	public void setIs_vitals(Boolean is_vitals) {
		this.is_vitals = is_vitals;
	}
	public Boolean getIs_diagnosis() {
		return is_diagnosis;
	}
	public void setIs_diagnosis(Boolean is_diagnosis) {
		this.is_diagnosis = is_diagnosis;
	}
	public Boolean getIs_labs() {
		return is_labs;
	}
	public void setIs_labs(Boolean is_labs) {
		this.is_labs = is_labs;
	}
	public Boolean getIs_allergy() {
		return is_allergy;
	}
	public void setIs_allergy(Boolean is_allergy) {
		this.is_allergy = is_allergy;
	}
	public Boolean getIs_medication() {
		return is_medication;
	}
	public void setIs_medication(Boolean is_medication) {
		this.is_medication = is_medication;
	}
	public Boolean getIs_procedures() {
		return is_procedures;
	}
	public void setIs_procedures(Boolean is_procedures) {
		this.is_procedures = is_procedures;
	}
	public Boolean getIs_lifestyle() {
		return is_lifestyle;
	}
	public void setIs_lifestyle(Boolean is_lifestyle) {
		this.is_lifestyle = is_lifestyle;
	}
	public Boolean getIs_healthmaintenance() {
		return is_healthmaintenance;
	}
	public void setIs_healthmaintenance(Boolean is_healthmaintenance) {
		this.is_healthmaintenance = is_healthmaintenance;
	}
	public Boolean getIs_immunization() {
		return is_immunization;
	}
	public void setIs_immunization(Boolean is_immunization) {
		this.is_immunization = is_immunization;
	}
	public String getIntervention_developer() {
		return intervention_developer;
	}
	public void setIntervention_developer(String intervention_developer) {
		this.intervention_developer = intervention_developer;
	}
	public String getFunding_source() {
		return funding_source;
	}
	public void setFunding_source(String funding_source) {
		this.funding_source = funding_source;
	}
	public String getRelease_version() {
		return release_version;
	}
	public void setRelease_version(String release_version) {
		this.release_version = release_version;
	}
	public String getDiagnostic_therapeutic_link() {
		return diagnostic_therapeutic_link;
	}
	public void setDiagnostic_therapeutic_link(String diagnostic_therapeutic_link) {
		this.diagnostic_therapeutic_link = diagnostic_therapeutic_link;
	}
	public String getCitation() {
		return citation;
	}
	public void setCitation(String citation) {
		this.citation = citation;
	}
	
	

}
