package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMAssessPlanOfTreatementGet {

	@Id
	private Long planoftreatment_id;
	private Long assess_plan_id;
	private Long patient_id;
	private Long chart_id;
	private String plan_date;
	private String plan_of_treatment_code;
	private String plan_of_treatment;
	private String code_type;
	private String created_user;
	private String date_created;
	private String client_date_created;

	public Long getPlanoftreatment_id() {
		return planoftreatment_id;
	}

	public void setPlanoftreatment_id(Long planoftreatment_id) {
		this.planoftreatment_id = planoftreatment_id;
	}

	public Long getAssess_plan_id() {
		return assess_plan_id;
	}

	public void setAssess_plan_id(Long assess_plan_id) {
		this.assess_plan_id = assess_plan_id;
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

	public String getPlan_date() {
		return plan_date;
	}

	public void setPlan_date(String plan_date) {
		this.plan_date = plan_date;
	}

	public String getPlan_of_treatment_code() {
		return plan_of_treatment_code;
	}

	public void setPlan_of_treatment_code(String plan_of_treatment_code) {
		this.plan_of_treatment_code = plan_of_treatment_code;
	}

	public String getPlan_of_treatment() {
		return plan_of_treatment;
	}

	public void setPlan_of_treatment(String plan_of_treatment) {
		this.plan_of_treatment = plan_of_treatment;
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
}
