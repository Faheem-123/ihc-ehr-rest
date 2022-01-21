package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMAssessPlanAssessmentGet {
	
	@Id
    private Long assessment_id;
    private Long assess_plan_id;
    private Long patient_id;
    private Long chart_id;
    private String assessment;        
    private String created_user;
    private String date_created;
    private String client_date_created;
	public Long getAssessment_id() {
		return assessment_id;
	}
	public void setAssessment_id(Long assessment_id) {
		this.assessment_id = assessment_id;
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
	public String getAssessment() {
		return assessment;
	}
	public void setAssessment(String assessment) {
		this.assessment = assessment;
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
