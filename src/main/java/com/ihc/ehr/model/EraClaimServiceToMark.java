package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Id;

public class EraClaimServiceToMark {
	
	@Id
	Long era_claim_service_id;
	String proc_code;
	BigDecimal paid_amount;
	
	
	
	
	public EraClaimServiceToMark(Long era_claim_service_id, String proc_code, BigDecimal paid_amount) {
		super();
		this.era_claim_service_id = era_claim_service_id;
		this.proc_code = proc_code;
		this.paid_amount = paid_amount;
	}
	public Long getEra_claim_service_id() {
		return era_claim_service_id;
	}
	public void setEra_claim_service_id(Long era_claim_service_id) {
		this.era_claim_service_id = era_claim_service_id;
	}
	public String getProc_code() {
		return proc_code;
	}
	public void setProc_code(String proc_code) {
		this.proc_code = proc_code;
	}
	public BigDecimal getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(BigDecimal paid_amount) {
		this.paid_amount = paid_amount;
	}
	@Override
	public String toString() {
		return "EraClaimServiceToMark [era_claim_service_id=" + era_claim_service_id + ", proc_code=" + proc_code
				+ ", paid_amount=" + paid_amount + "]";
	}
	
	
	
	
	

}