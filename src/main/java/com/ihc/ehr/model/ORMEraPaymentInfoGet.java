package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMEraPaymentInfoGet {

	@Id
	private Long era_claim_service_id;
    private Long era_id;    
    private Long era_claim_id;
    private String check_number;
    private String check_date;
    private String payer_name;
    private String begin_service_date;
    private String end_service_date;
    private String cpt_charged_with_modifier;
    private String cpt_paid_with_modifier;
    private Integer paid_units;
    private BigDecimal billed_amount;
    private BigDecimal allowed_amount;
    private BigDecimal paid_amount;
    private BigDecimal deduct_amount;
    private BigDecimal co_ins_amount;
    private BigDecimal co_pay_amount;
    private String patient_responsibility;
    private String late_filing_reduction;
    private BigDecimal other_adjusts;
    private String adjust_codes;
    private String remark_codes;
    private Boolean payment_posted;
    private Boolean denial_posted;
    private String posted_by;
    private String date_posted;
    private String payer_claim_control_number;
	public Long getEra_claim_service_id() {
		return era_claim_service_id;
	}
	public void setEra_claim_service_id(Long era_claim_service_id) {
		this.era_claim_service_id = era_claim_service_id;
	}
	public Long getEra_id() {
		return era_id;
	}
	public void setEra_id(Long era_id) {
		this.era_id = era_id;
	}
	public Long getEra_claim_id() {
		return era_claim_id;
	}
	public void setEra_claim_id(Long era_claim_id) {
		this.era_claim_id = era_claim_id;
	}
	public String getCheck_number() {
		return check_number;
	}
	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}
	public String getCheck_date() {
		return check_date;
	}
	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}
	public String getPayer_name() {
		return payer_name;
	}
	public void setPayer_name(String payer_name) {
		this.payer_name = payer_name;
	}
	public String getBegin_service_date() {
		return begin_service_date;
	}
	public void setBegin_service_date(String begin_service_date) {
		this.begin_service_date = begin_service_date;
	}
	public String getEnd_service_date() {
		return end_service_date;
	}
	public void setEnd_service_date(String end_service_date) {
		this.end_service_date = end_service_date;
	}
	public String getCpt_charged_with_modifier() {
		return cpt_charged_with_modifier;
	}
	public void setCpt_charged_with_modifier(String cpt_charged_with_modifier) {
		this.cpt_charged_with_modifier = cpt_charged_with_modifier;
	}
	public String getCpt_paid_with_modifier() {
		return cpt_paid_with_modifier;
	}
	public void setCpt_paid_with_modifier(String cpt_paid_with_modifier) {
		this.cpt_paid_with_modifier = cpt_paid_with_modifier;
	}
	public Integer getPaid_units() {
		return paid_units;
	}
	public void setPaid_units(Integer paid_units) {
		this.paid_units = paid_units;
	}
	public BigDecimal getBilled_amount() {
		return billed_amount;
	}
	public void setBilled_amount(BigDecimal billed_amount) {
		this.billed_amount = billed_amount;
	}
	public BigDecimal getAllowed_amount() {
		return allowed_amount;
	}
	public void setAllowed_amount(BigDecimal allowed_amount) {
		this.allowed_amount = allowed_amount;
	}
	public BigDecimal getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(BigDecimal paid_amount) {
		this.paid_amount = paid_amount;
	}
	public BigDecimal getDeduct_amount() {
		return deduct_amount;
	}
	public void setDeduct_amount(BigDecimal deduct_amount) {
		this.deduct_amount = deduct_amount;
	}
	
	
	
	public BigDecimal getCo_ins_amount() {
		return co_ins_amount;
	}
	public void setCo_ins_amount(BigDecimal co_ins_amount) {
		this.co_ins_amount = co_ins_amount;
	}
	public BigDecimal getCo_pay_amount() {
		return co_pay_amount;
	}
	public void setCo_pay_amount(BigDecimal co_pay_amount) {
		this.co_pay_amount = co_pay_amount;
	}
	public String getPatient_responsibility() {
		return patient_responsibility;
	}
	public void setPatient_responsibility(String patient_responsibility) {
		this.patient_responsibility = patient_responsibility;
	}
	public String getLate_filing_reduction() {
		return late_filing_reduction;
	}
	public void setLate_filing_reduction(String late_filing_reduction) {
		this.late_filing_reduction = late_filing_reduction;
	}
	public BigDecimal getOther_adjusts() {
		return other_adjusts;
	}
	public void setOther_adjusts(BigDecimal other_adjusts) {
		this.other_adjusts = other_adjusts;
	}
	public String getAdjust_codes() {
		return adjust_codes;
	}
	public void setAdjust_codes(String adjust_codes) {
		this.adjust_codes = adjust_codes;
	}
	public String getRemark_codes() {
		return remark_codes;
	}
	public void setRemark_codes(String remark_codes) {
		this.remark_codes = remark_codes;
	}
	public Boolean getPayment_posted() {
		return payment_posted;
	}
	public void setPayment_posted(Boolean payment_posted) {
		this.payment_posted = payment_posted;
	}
	public Boolean getDenial_posted() {
		return denial_posted;
	}
	public void setDenial_posted(Boolean denial_posted) {
		this.denial_posted = denial_posted;
	}
	public String getPosted_by() {
		return posted_by;
	}
	public void setPosted_by(String posted_by) {
		this.posted_by = posted_by;
	}
	public String getDate_posted() {
		return date_posted;
	}
	public void setDate_posted(String date_posted) {
		this.date_posted = date_posted;
	}
	public String getPayer_claim_control_number() {
		return payer_claim_control_number;
	}
	public void setPayer_claim_control_number(String payer_claim_control_number) {
		this.payer_claim_control_number = payer_claim_control_number;
	}
    
    
}
