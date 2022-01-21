package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_statement_log")
public class ORMSavePatientStatement_log {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statement_id;
	private String patient_id;
	private String claim_id;
	private String statement_date;
	private String generated_by;
	private String date_created;
	private String system_ip;
	private String path;
	private String notes;
	private String amt_sent;
	private String claim_message;
	public Long getStatement_id() {
		return statement_id;
	}
	public void setStatement_id(Long statement_id) {
		this.statement_id = statement_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(String claim_id) {
		this.claim_id = claim_id;
	}
	public String getStatement_date() {
		return statement_date;
	}
	public void setStatement_date(String statement_date) {
		this.statement_date = statement_date;
	}
	public String getGenerated_by() {
		return generated_by;
	}
	public void setGenerated_by(String generated_by) {
		this.generated_by = generated_by;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getAmt_sent() {
		return amt_sent;
	}
	public void setAmt_sent(String amt_sent) {
		this.amt_sent = amt_sent;
	}
	public String getClaim_message() {
		return claim_message;
	}
	public void setClaim_message(String claim_message) {
		this.claim_message = claim_message;
	}
	
}
