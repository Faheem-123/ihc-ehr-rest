package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientSuregeriesProceduresSummary {
	
	@Id	
	private String chart_procedures_id;
	private String procedure_code;
	private String description;
	private String comments;
	private String procedure_date;
	public String getChart_procedures_id() {
		return chart_procedures_id;
	}
	public void setChart_procedures_id(String chart_procedures_id) {
		this.chart_procedures_id = chart_procedures_id;
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
}
