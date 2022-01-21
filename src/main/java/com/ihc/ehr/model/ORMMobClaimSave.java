package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim")
public class ORMMobClaimSave {
		
	@Id
	private Long claim_id;
	private Long practice_id;
	private Long patient_id;
	private Long location_id;
	private Long attending_physician;
	private Long billing_physician;
	private Long facility_id;		
	private String dos;
	private String pos;
	private BigDecimal claim_total;	
	private BigDecimal amt_due;
	private Boolean draft;	
	private Boolean is_electronic;	
	private Boolean icd_10_claim;
	private String notes;	
	private String claim_type;	
	private String listcpticd;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;	
	
	private String system_ip;
	public Long getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(Long claim_id) {
		this.claim_id = claim_id;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	public Long getAttending_physician() {
		return attending_physician;
	}
	public void setAttending_physician(Long attending_physician) {
		this.attending_physician = attending_physician;
	}
	public Long getBilling_physician() {
		return billing_physician;
	}
	public void setBilling_physician(Long billing_physician) {
		this.billing_physician = billing_physician;
	}
	public Long getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(Long facility_id) {
		this.facility_id = facility_id;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public BigDecimal getClaim_total() {
		return claim_total;
	}
	public void setClaim_total(BigDecimal claim_total) {
		this.claim_total = claim_total;
	}
	public Boolean getDraft() {
		return draft;
	}
	public void setDraft(Boolean draft) {
		this.draft = draft;
	}
	public Boolean getIs_electronic() {
		return is_electronic;
	}
	public void setIs_electronic(Boolean is_electronic) {
		this.is_electronic = is_electronic;
	}
	public String getClaim_type() {
		return claim_type;
	}
	public void setClaim_type(String claim_type) {
		this.claim_type = claim_type;
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public Boolean getIcd_10_claim() {
		return icd_10_claim;
	}
	public void setIcd_10_claim(Boolean icd_10_claim) {
		this.icd_10_claim = icd_10_claim;
	}
	public BigDecimal getAmt_due() {
		return amt_due;
	}
	public void setAmt_due(BigDecimal amt_due) {
		this.amt_due = amt_due;
	}
	public String getListcpticd() {
		return listcpticd;
	}
	public void setListcpticd(String listcpticd) {
		this.listcpticd = listcpticd;
	}
	
	
}
