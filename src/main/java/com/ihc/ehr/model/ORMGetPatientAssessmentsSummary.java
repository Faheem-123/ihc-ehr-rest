package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientAssessmentsSummary {
	@Id
	private Long pmh_id;
	private String pmh_date;
	private String pmh_code;
	private String pmh_description;
	public Long getPmh_id() {
		return pmh_id;
	}
	public void setPmh_id(Long pmh_id) {
		this.pmh_id = pmh_id;
	}
	public String getPmh_date() {
		return pmh_date;
	}
	public void setPmh_date(String pmh_date) {
		this.pmh_date = pmh_date;
	}
	public String getPmh_code() {
		return pmh_code;
	}
	public void setPmh_code(String pmh_code) {
		this.pmh_code = pmh_code;
	}
	public String getPmh_description() {
		return pmh_description;
	}
	public void setPmh_description(String pmh_description) {
		this.pmh_description = pmh_description;
	}	
}
