package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "era_claim_service")
public class ORMEraClaimServiceSave {
	@Id    
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
    private Long era_claim_service_id;
    private Long era_id;
    private Long era_claim_id;
    private Long practice_id;
    private String begin_service_date;
    private String end_service_date;
    private String paid_units;
    private String allowed_amount;
    private String deduct_amount;
    
    private String coins_amount;
    private String copay_amount;
    
    private String late_filing_reduction;
    private String other_adjusts;
    private String adjust_codes;
    private String remark_codes;
    private String provider_control_number;
    private String billed_amount;
    private String paid_amount;
    private String patient_responsibility;
    private String correction_reversals;
    private String rendering_provider_identifier_qualifier;
    private String rendering_provider_identifier;
    private String risk_amount;
    private String per_day_limit;
    private String net_billed;
    private String tax;
    private String claim_procedures_id;
    private String posted_amount;
    private Boolean payment_posted;
    private Boolean denial_posted;
    private Boolean auto_posted;
    private String posted_by;
    private String date_posted;
    private String created_user;
    private String client_date_created;
    private String modified_user;
    private String client_date_modified;
    private String date_created;
    private String date_modified;
    private String system_ip;
    private Boolean deleted;
    private String charged_procedure;
    private String charged_procedure_modifier;
    private String paid_procedure;
    private String paid_procedure_modifier;
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
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
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
	public String getOther_adjusts() {
		return other_adjusts;
	}
	public void setOther_adjusts(String other_adjusts) {
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
	public String getProvider_control_number() {
		return provider_control_number;
	}
	public void setProvider_control_number(String provider_control_number) {
		this.provider_control_number = provider_control_number;
	}
	public String getBilled_amount() {
		return billed_amount;
	}
	public void setBilled_amount(String billed_amount) {
		this.billed_amount = billed_amount;
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
	public String getCorrection_reversals() {
		return correction_reversals;
	}
	public void setCorrection_reversals(String correction_reversals) {
		this.correction_reversals = correction_reversals;
	}
	public String getRendering_provider_identifier_qualifier() {
		return rendering_provider_identifier_qualifier;
	}
	public void setRendering_provider_identifier_qualifier(String rendering_provider_identifier_qualifier) {
		this.rendering_provider_identifier_qualifier = rendering_provider_identifier_qualifier;
	}
	public String getRendering_provider_identifier() {
		return rendering_provider_identifier;
	}
	public void setRendering_provider_identifier(String rendering_provider_identifier) {
		this.rendering_provider_identifier = rendering_provider_identifier;
	}
	public String getRisk_amount() {
		return risk_amount;
	}
	public void setRisk_amount(String risk_amount) {
		this.risk_amount = risk_amount;
	}
	public String getPer_day_limit() {
		return per_day_limit;
	}
	public void setPer_day_limit(String per_day_limit) {
		this.per_day_limit = per_day_limit;
	}
	public String getNet_billed() {
		return net_billed;
	}
	public void setNet_billed(String net_billed) {
		this.net_billed = net_billed;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getClaim_procedures_id() {
		return claim_procedures_id;
	}
	public void setClaim_procedures_id(String claim_procedures_id) {
		this.claim_procedures_id = claim_procedures_id;
	}
	public String getPosted_amount() {
		return posted_amount;
	}
	public void setPosted_amount(String posted_amount) {
		this.posted_amount = posted_amount;
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
	public Boolean getAuto_posted() {
		return auto_posted;
	}
	public void setAuto_posted(Boolean auto_posted) {
		this.auto_posted = auto_posted;
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
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
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
	@Override
	public String toString() {
		return "ORMEraClaimServiceSave [era_claim_service_id=" + era_claim_service_id + ", era_id=" + era_id
				+ ", era_claim_id=" + era_claim_id + ", practice_id=" + practice_id + ", begin_service_date="
				+ begin_service_date + ", end_service_date=" + end_service_date + ", paid_units=" + paid_units
				+ ", allowed_amount=" + allowed_amount + ", deduct_amount=" + deduct_amount + ", coins_amount="
				+ coins_amount + ", copay_amount=" + copay_amount + ", late_filing_reduction=" + late_filing_reduction
				+ ", other_adjusts=" + other_adjusts + ", adjust_codes=" + adjust_codes + ", remark_codes="
				+ remark_codes + ", provider_control_number=" + provider_control_number + ", billed_amount="
				+ billed_amount + ", paid_amount=" + paid_amount + ", patient_responsibility=" + patient_responsibility
				+ ", correction_reversals=" + correction_reversals + ", rendering_provider_identifier_qualifier="
				+ rendering_provider_identifier_qualifier + ", rendering_provider_identifier="
				+ rendering_provider_identifier + ", risk_amount=" + risk_amount + ", per_day_limit=" + per_day_limit
				+ ", net_billed=" + net_billed + ", tax=" + tax + ", claim_procedures_id=" + claim_procedures_id
				+ ", posted_amount=" + posted_amount + ", payment_posted=" + payment_posted + ", denial_posted="
				+ denial_posted + ", auto_posted=" + auto_posted + ", posted_by=" + posted_by + ", date_posted="
				+ date_posted + ", created_user=" + created_user + ", client_date_created=" + client_date_created
				+ ", modified_user=" + modified_user + ", client_date_modified=" + client_date_modified
				+ ", date_created=" + date_created + ", date_modified=" + date_modified + ", system_ip=" + system_ip
				+ ", deleted=" + deleted + ", charged_procedure=" + charged_procedure + ", charged_procedure_modifier="
				+ charged_procedure_modifier + ", paid_procedure=" + paid_procedure + ", paid_procedure_modifier="
				+ paid_procedure_modifier + "]";
	}
	
	

}
