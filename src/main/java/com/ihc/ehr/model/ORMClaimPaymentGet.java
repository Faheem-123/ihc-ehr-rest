package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMClaimPaymentGet {
	
	@Id
	private Long claim_payments_id;
	private Long claim_procedures_id;
	private String source_description;
	private String payment_source;
	private String charged_procedure_with_modifier;
	private String paid_procedure;
	private Integer units;
	private String check_date;
	private String check_number;
	private BigDecimal total_charges;
	private BigDecimal cpt_balance;
	private BigDecimal paid_amount;
	private BigDecimal risk_amount;
	private BigDecimal writeoff_amount;
	private BigDecimal adjusted_amount;
	private BigDecimal allowed_amount;
	private BigDecimal deductable_amount;
	private BigDecimal coinsurance_amount;
	private BigDecimal copay_amount;
	private Long 	eob_era_id;
	private String eob_era_id_type;
	private Integer eob_page_no;
	private Boolean autoposted_era;
	private String created_user;
	private String client_date_created;
	private String date_created;
	private String policy_number;
	private String entry_type;
	public Long getClaim_payments_id() {
		return claim_payments_id;
	}
	public void setClaim_payments_id(Long claim_payments_id) {
		this.claim_payments_id = claim_payments_id;
	}
	public Long getClaim_procedures_id() {
		return claim_procedures_id;
	}
	public void setClaim_procedures_id(Long claim_procedures_id) {
		this.claim_procedures_id = claim_procedures_id;
	}
	public String getSource_description() {
		return source_description;
	}
	public void setSource_description(String source_description) {
		this.source_description = source_description;
	}
	public String getPayment_source() {
		return payment_source;
	}
	public void setPayment_source(String payment_source) {
		this.payment_source = payment_source;
	}
	public String getCharged_procedure_with_modifier() {
		return charged_procedure_with_modifier;
	}
	public void setCharged_procedure_with_modifier(String charged_procedure_with_modifier) {
		this.charged_procedure_with_modifier = charged_procedure_with_modifier;
	}
	public String getPaid_procedure() {
		return paid_procedure;
	}
	public void setPaid_procedure(String paid_procedure) {
		this.paid_procedure = paid_procedure;
	}
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
	}
	public String getCheck_date() {
		return check_date;
	}
	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}
	public String getCheck_number() {
		return check_number;
	}
	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}
	public BigDecimal getTotal_charges() {
		return total_charges;
	}
	public void setTotal_charges(BigDecimal total_charges) {
		this.total_charges = total_charges;
	}
	public BigDecimal getCpt_balance() {
		return cpt_balance;
	}
	public void setCpt_balance(BigDecimal cpt_balance) {
		this.cpt_balance = cpt_balance;
	}
	public BigDecimal getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(BigDecimal paid_amount) {
		this.paid_amount = paid_amount;
	}
	public BigDecimal getRisk_amount() {
		return risk_amount;
	}
	public void setRisk_amount(BigDecimal risk_amount) {
		this.risk_amount = risk_amount;
	}
	public BigDecimal getWriteoff_amount() {
		return writeoff_amount;
	}
	public void setWriteoff_amount(BigDecimal writeoff_amount) {
		this.writeoff_amount = writeoff_amount;
	}
	public BigDecimal getAdjusted_amount() {
		return adjusted_amount;
	}
	public void setAdjusted_amount(BigDecimal adjusted_amount) {
		this.adjusted_amount = adjusted_amount;
	}
	
	public BigDecimal getAllowed_amount() {
		return allowed_amount;
	}
	public void setAllowed_amount(BigDecimal allowed_amount) {
		this.allowed_amount = allowed_amount;
	}
	public BigDecimal getDeductable_amount() {
		return deductable_amount;
	}
	public void setDeductable_amount(BigDecimal deductable_amount) {
		this.deductable_amount = deductable_amount;
	}
	public BigDecimal getCoinsurance_amount() {
		return coinsurance_amount;
	}
	public void setCoinsurance_amount(BigDecimal coinsurance_amount) {
		this.coinsurance_amount = coinsurance_amount;
	}
	public BigDecimal getCopay_amount() {
		return copay_amount;
	}
	public void setCopay_amount(BigDecimal copay_amount) {
		this.copay_amount = copay_amount;
	}
	public Long getEob_era_id() {
		return eob_era_id;
	}
	public void setEob_era_id(Long eob_era_id) {
		this.eob_era_id = eob_era_id;
	}
	public String getEob_era_id_type() {
		return eob_era_id_type;
	}
	public void setEob_era_id_type(String eob_era_id_type) {
		this.eob_era_id_type = eob_era_id_type;
	}
	public Integer getEob_page_no() {
		return eob_page_no;
	}
	public void setEob_page_no(Integer eob_page_no) {
		this.eob_page_no = eob_page_no;
	}
	public Boolean getAutoposted_era() {
		return autoposted_era;
	}
	public void setAutoposted_era(Boolean autoposted_era) {
		this.autoposted_era = autoposted_era;
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
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getPolicy_number() {
		return policy_number;
	}
	public void setPolicy_number(String policy_number) {
		this.policy_number = policy_number;
	}
	public String getEntry_type() {
		return entry_type;
	}
	public void setEntry_type(String entry_type) {
		this.entry_type = entry_type;
	}
}
