package com.ihc.ehr.model;

public class claimPostingResponse {

	Long claim_id;
	String dos;	
	Float paid_amount;
	Float write_off_amount;
	String payment_summary;
	
	
	
	
	public claimPostingResponse(Long claim_id, String dos, Float paid_amount, Float write_off_amount,
			String payment_summary) {
		super();
		this.claim_id = claim_id;
		this.dos = dos;
		this.paid_amount = paid_amount;
		this.write_off_amount = write_off_amount;
		this.payment_summary = payment_summary;
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
	public Float getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(Float paid_amount) {
		this.paid_amount = paid_amount;
	}
	public Float getWrite_off_amount() {
		return write_off_amount;
	}
	public void setWrite_off_amount(Float write_off_amount) {
		this.write_off_amount = write_off_amount;
	}
	public String getPayment_summary() {
		return payment_summary;
	}
	public void setPayment_summary(String payment_summary) {
		this.payment_summary = payment_summary;
	}
	
}
