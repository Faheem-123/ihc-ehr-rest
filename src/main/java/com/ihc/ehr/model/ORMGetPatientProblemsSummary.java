package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientProblemsSummary {
	@Id
	private Long problem_id;
	private String prob_date;
	private String prob_code;
	private String prob_description;
	
	public Long getProblem_id() {
		return problem_id;
	}
	public void setProblem_id(Long problem_id) {
		this.problem_id = problem_id;
	}
	public String getProb_date() {
		return prob_date;
	}
	public void setProb_date(String prob_date) {
		this.prob_date = prob_date;
	}
	public String getProb_code() {
		return prob_code;
	}
	public void setProb_code(String prob_code) {
		this.prob_code = prob_code;
	}
	public String getProb_description() {
		return prob_description;
	}
	public void setProb_description(String prob_description) {
		this.prob_description = prob_description;
	}

	
}
