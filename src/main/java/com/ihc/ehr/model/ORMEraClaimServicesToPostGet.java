package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMEraClaimServicesToPostGet {
	
	@Id
    private Long era_claim_service_id;
    private Long era_id;
    private Long era_claim_id;
    private String paid_units;
    private String allowed_amount;
    private String deduct_amount;
    private String coins_amount;
    private String copay_amount;
    private String late_filing_reduction;
    private String paid_amount;
    private String patient_responsibility;
    private String risk_amount;    
    private Long claim_procedures_id;
    private String charged_procedure;
    private String charged_procedure_modifier;
    private String paid_procedure;
    private String paid_procedure_modifier;
    private String ehr_proc_code;
    private String total_charges;
    private String cpt_primary_balance;
    private String remark_codes;
    private Boolean payment_already_exist;
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
	public String getPaid_units() {
		return paid_units;
	}
	public void setPaid_units(String paid_units) {
		this.paid_units = paid_units;
	}
	public String getAllowed_amount() {
		return allowed_amount;
	}
	public void setAllowed_amount(String allowed_amount) {
		this.allowed_amount = allowed_amount;
	}
	public String getDeduct_amount() {
		return deduct_amount;
	}
	public void setDeduct_amount(String deduct_amount) {
		this.deduct_amount = deduct_amount;
	}
	
	
	
	public String getCoins_amount() {
		return coins_amount;
	}
	public void setCoins_amount(String coins_amount) {
		this.coins_amount = coins_amount;
	}
	public String getCopay_amount() {
		return copay_amount;
	}
	public void setCopay_amount(String copay_amount) {
		this.copay_amount = copay_amount;
	}
	public String getLate_filing_reduction() {
		return late_filing_reduction;
	}
	public void setLate_filing_reduction(String late_filing_reduction) {
		this.late_filing_reduction = late_filing_reduction;
	}
	public String getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(String paid_amount) {
		this.paid_amount = paid_amount;
	}
	public String getPatient_responsibility() {
		return patient_responsibility;
	}
	public void setPatient_responsibility(String patient_responsibility) {
		this.patient_responsibility = patient_responsibility;
	}
	public String getRisk_amount() {
		return risk_amount;
	}
	public void setRisk_amount(String risk_amount) {
		this.risk_amount = risk_amount;
	}
	public Long getClaim_procedures_id() {
		return claim_procedures_id;
	}
	public void setClaim_procedures_id(Long claim_procedures_id) {
		this.claim_procedures_id = claim_procedures_id;
	}
	public String getCharged_procedure() {
		return charged_procedure;
	}
	public void setCharged_procedure(String charged_procedure) {
		this.charged_procedure = charged_procedure;
	}
	public String getCharged_procedure_modifier() {
		return charged_procedure_modifier;
	}
	public void setCharged_procedure_modifier(String charged_procedure_modifier) {
		this.charged_procedure_modifier = charged_procedure_modifier;
	}
	public String getPaid_procedure() {
		return paid_procedure;
	}
	public void setPaid_procedure(String paid_procedure) {
		this.paid_procedure = paid_procedure;
	}
	public String getPaid_procedure_modifier() {
		return paid_procedure_modifier;
	}
	public void setPaid_procedure_modifier(String paid_procedure_modifier) {
		this.paid_procedure_modifier = paid_procedure_modifier;
	}
	public String getEhr_proc_code() {
		return ehr_proc_code;
	}
	public void setEhr_proc_code(String ehr_proc_code) {
		this.ehr_proc_code = ehr_proc_code;
	}
	public String getTotal_charges() {
		return total_charges;
	}
	public void setTotal_charges(String total_charges) {
		this.total_charges = total_charges;
	}
	public String getCpt_primary_balance() {
		return cpt_primary_balance;
	}
	public void setCpt_primary_balance(String cpt_primary_balance) {
		this.cpt_primary_balance = cpt_primary_balance;
	}
	public String getRemark_codes() {
		return remark_codes;
	}
	public void setRemark_codes(String remark_codes) {
		this.remark_codes = remark_codes;
	}
	public Boolean getPayment_already_exist() {
		return payment_already_exist;
	}
	public void setPayment_already_exist(Boolean payment_already_exist) {
		this.payment_already_exist = payment_already_exist;
	}
	@Override
	public String toString() {
		return "ORMEraClaimServicesToPostGet [era_claim_service_id=" + era_claim_service_id + ", era_id=" + era_id
				+ ", era_claim_id=" + era_claim_id + ", paid_units=" + paid_units + ", allowed_amount=" + allowed_amount
				+ ", deduct_amount=" + deduct_amount + ", coIns_amount=" + coins_amount + ", coPay_amount="
				+ copay_amount + ", late_filing_reduction=" + late_filing_reduction + ", paid_amount=" + paid_amount
				+ ", patient_responsibility=" + patient_responsibility + ", risk_amount=" + risk_amount
				+ ", claim_procedures_id=" + claim_procedures_id + ", charged_procedure=" + charged_procedure
				+ ", charged_procedure_modifier=" + charged_procedure_modifier + ", paid_procedure=" + paid_procedure
				+ ", paid_procedure_modifier=" + paid_procedure_modifier + ", ehr_proc_code=" + ehr_proc_code
				+ ", total_charges=" + total_charges + ", cpt_primary_balance=" + cpt_primary_balance
				+ ", remark_codes=" + remark_codes + ", payment_already_exist=" + payment_already_exist + "]";
	}
	
}
