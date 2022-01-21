package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SearchPatientClaim {

	@Id
	private Long claim_id;
    private Long patient_id;
    private String name;
    private String dob;
    private String alternate_account;
    private String patient_status;
    private String dos;
    private String attending_physician;
    private String billing_physician;
    private String location_name;
    private Boolean draft;
    private Boolean self_pay;
    private String cpt;
    private String icd;
    private BigDecimal claim_total;
    private BigDecimal pri_paid;
    private BigDecimal sec_paid;
    private BigDecimal oth_paid;
    private BigDecimal patient_paid;
    private BigDecimal total_adjustments;
    private BigDecimal amt_due;
    private String pri_status;
    private String sec_status;
    private String oth_status;
    private String pat_status;
	public Long getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(Long claim_id) {
		this.claim_id = claim_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAlternate_account() {
		return alternate_account;
	}
	public void setAlternate_account(String alternate_account) {
		this.alternate_account = alternate_account;
	}
	public String getPatient_status() {
		return patient_status;
	}
	public void setPatient_status(String patient_status) {
		this.patient_status = patient_status;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getAttending_physician() {
		return attending_physician;
	}
	public void setAttending_physician(String attending_physician) {
		this.attending_physician = attending_physician;
	}
	public String getBilling_physician() {
		return billing_physician;
	}
	public void setBilling_physician(String billing_physician) {
		this.billing_physician = billing_physician;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
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
}
