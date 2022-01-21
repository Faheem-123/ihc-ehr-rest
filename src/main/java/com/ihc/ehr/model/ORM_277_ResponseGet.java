package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORM_277_ResponseGet {
	
	@Id	
	private Long id;

	private String info_source_name;
	private String info_source_identifer_code;
		
	private Long patient_id;
	private String pid;
	private String patient_name;
	
	private Long claim_id;
	private String dos;	
	private String icn;
	private String amount_billed;	
			
	private String status_category_code;	
	private String status_description;	
	private String status_effective_date;
	private String status;
	
	
	private String date_created;
	
	private Boolean worked;
	private String date_worked;
	private String worked_by;
	
	private String location_name;
	private String billing_provider_name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInfo_source_name() {
		return info_source_name;
	}
	public void setInfo_source_name(String info_source_name) {
		this.info_source_name = info_source_name;
	}
	public String getInfo_source_identifer_code() {
		return info_source_identifer_code;
	}
	public void setInfo_source_identifer_code(String info_source_identifer_code) {
		this.info_source_identifer_code = info_source_identifer_code;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public Long getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(Long claim_id) {
		this.claim_id = claim_id;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getIcn() {
		return icn;
	}
	public void setIcn(String icn) {
		this.icn = icn;
	}
	public String getAmount_billed() {
		return amount_billed;
	}
	public void setAmount_billed(String amount_billed) {
		this.amount_billed = amount_billed;
	}
	public String getStatus_category_code() {
		return status_category_code;
	}
	public void setStatus_category_code(String status_category_code) {
		this.status_category_code = status_category_code;
	}
	public String getStatus_description() {
		return status_description;
	}
	public void setStatus_description(String status_description) {
		this.status_description = status_description;
	}
	public String getStatus_effective_date() {
		return status_effective_date;
	}
	public void setStatus_effective_date(String status_effective_date) {
		this.status_effective_date = status_effective_date;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public Boolean getWorked() {
		return worked;
	}
	public void setWorked(Boolean worked) {
		this.worked = worked;
	}
	public String getDate_worked() {
		return date_worked;
	}
	public void setDate_worked(String date_worked) {
		this.date_worked = date_worked;
	}
	public String getWorked_by() {
		return worked_by;
	}
	public void setWorked_by(String worked_by) {
		this.worked_by = worked_by;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getBilling_provider_name() {
		return billing_provider_name;
	}
	public void setBilling_provider_name(String billing_provider_name) {
		this.billing_provider_name = billing_provider_name;
	}	
}
