package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMClaimSummary {

	@Id
	private Long claim_id;
	private String dos;
	private String billing_physician;
	private String attending_physician;
	private String location_name;
	//private String pos;
	private Boolean draft;
	private Boolean self_pay;
	private String cpt;
	private String icd;
	private String created_user;
	private String created_Date;
	//private Long attending_physician_id;
	//private Long location_id;
	private BigDecimal  claim_total;
	private BigDecimal  pri_paid;
	private BigDecimal  sec_paid;
	private BigDecimal  oth_paid;
	private BigDecimal  patient_paid;
	private BigDecimal  total_adjustments;
	private BigDecimal  amt_due;
	private Boolean deleted;
	private String deleted_notes;
	private String pri_status;
	private String sec_status;
	private String oth_status;
	private String pat_status;	
	private Boolean hcfa_printed;
	private Boolean icd_10_claim;
	private String claim_type;

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

	public String getBilling_physician() {
		return billing_physician;
	}

	public void setBilling_physician(String billing_physician) {
		this.billing_physician = billing_physician;
	}

	public String getAttending_physician() {
		return attending_physician;
	}

	public void setAttending_physician(String attending_physician) {
		this.attending_physician = attending_physician;
	}
	
	public Boolean getDraft() {
		return draft;
	}

	public void setDraft(Boolean draft) {
		this.draft = draft;
	}

	public Boolean getSelf_pay() {
		return self_pay;
	}

	public void setSelf_pay(Boolean self_pay) {
		this.self_pay = self_pay;
	}

	public String getCpt() {
		return cpt;
	}

	public void setCpt(String cpt) {
		this.cpt = cpt;
	}

	public String getIcd() {
		return icd;
	}

	public void setIcd(String icd) {
		this.icd = icd;
	}

	public String getCreated_user() {
		return created_user;
	}

	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}

	public String getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(String created_Date) {
		this.created_Date = created_Date;
	}

	public BigDecimal getClaim_total() {
		return claim_total;
	}

	public void setClaim_total(BigDecimal claim_total) {
		this.claim_total = claim_total;
	}

	public BigDecimal getPri_paid() {
		return pri_paid;
	}

	public void setPri_paid(BigDecimal pri_paid) {
		this.pri_paid = pri_paid;
	}

	public BigDecimal getSec_paid() {
		return sec_paid;
	}

	public void setSec_paid(BigDecimal sec_paid) {
		this.sec_paid = sec_paid;
	}

	public BigDecimal getOth_paid() {
		return oth_paid;
	}

	public void setOth_paid(BigDecimal oth_paid) {
		this.oth_paid = oth_paid;
	}

	public BigDecimal getPatient_paid() {
		return patient_paid;
	}

	public void setPatient_paid(BigDecimal patient_paid) {
		this.patient_paid = patient_paid;
	}

	public BigDecimal getTotal_adjustments() {
		return total_adjustments;
	}

	public void setTotal_adjustments(BigDecimal total_adjustments) {
		this.total_adjustments = total_adjustments;
	}

	public BigDecimal getAmt_due() {
		return amt_due;
	}

	public void setAmt_due(BigDecimal amt_due) {
		this.amt_due = amt_due;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getDeleted_notes() {
		return deleted_notes;
	}

	public void setDeleted_notes(String deleted_notes) {
		this.deleted_notes = deleted_notes;
	}

	public String getPri_status() {
		return pri_status;
	}

	public void setPri_status(String pri_status) {
		this.pri_status = pri_status;
	}

	public String getSec_status() {
		return sec_status;
	}

	public void setSec_status(String sec_status) {
		this.sec_status = sec_status;
	}

	public String getOth_status() {
		return oth_status;
	}

	public void setOth_status(String oth_status) {
		this.oth_status = oth_status;
	}

	public String getPat_status() {
		return pat_status;
	}

	public void setPat_status(String pat_status) {
		this.pat_status = pat_status;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public Boolean getHcfa_printed() {
		return hcfa_printed;
	}

	public void setHcfa_printed(Boolean hcfa_printed) {
		this.hcfa_printed = hcfa_printed;
	}

	public Boolean getIcd_10_claim() {
		return icd_10_claim;
	}

	public void setIcd_10_claim(Boolean icd_10_claim) {
		this.icd_10_claim = icd_10_claim;
	}

	public String getClaim_type() {
		return claim_type;
	}

	public void setClaim_type(String claim_type) {
		this.claim_type = claim_type;
	}
}
