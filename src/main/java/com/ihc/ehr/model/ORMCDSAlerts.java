package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMCDSAlerts {
	@Id
	private Long rule_id;
	private Long patient_id;
	private Boolean show;
	private String system_ip;
	private String created_user;
	private String date_created;

	public Long getRule_id() {
		return rule_id;
	}

	public void setRule_id(Long rule_id) {
		this.rule_id = rule_id;
	}

	public Long getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

	public String getSystem_ip() {
		return system_ip;
	}

	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
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

}
