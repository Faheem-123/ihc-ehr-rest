package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMHealthConcernViewDetail {
	@Id
	 private String id;
	private String health_id;
	private String concern;
	private String problem_id;
	private String diag_code;
	private String diag_description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHealth_id() {
		return health_id;
	}
	public void setHealth_id(String health_id) {
		this.health_id = health_id;
	}
	public String getConcern() {
		return concern;
	}
	public void setConcern(String concern) {
		this.concern = concern;
	}
	public String getProblem_id() {
		return problem_id;
	}
	public void setProblem_id(String problem_id) {
		this.problem_id = problem_id;
	}
	public String getDiag_code() {
		return diag_code;
	}
	public void setDiag_code(String diag_code) {
		this.diag_code = diag_code;
	}
	public String getDiag_description() {
		return diag_description;
	}
	public void setDiag_description(String diag_description) {
		this.diag_description = diag_description;
	}
	
	
	

}
