package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMMCHC {
	@Id
    private String total_encounter;
	private String no_of_claims;
	private String total_claim_charges;
	private String total_payment_received;
	private String total_payment_posted;
	private String total_denial_received;
	private String total_denial_worked;
	private String patient_payment;
	
	public String getTotal_encounter() {
		return total_encounter;
	}
	public void setTotal_encounter(String total_encounter) {
		this.total_encounter = total_encounter;
	}
	public String getNo_of_claims() {
		return no_of_claims;
	}
	public void setNo_of_claims(String no_of_claims) {
		this.no_of_claims = no_of_claims;
	}
	public String getTotal_claim_charges() {
		return total_claim_charges;
	}
	public void setTotal_claim_charges(String total_claim_charges) {
		this.total_claim_charges = total_claim_charges;
	}
	public String getTotal_payment_received() {
		return total_payment_received;
	}
	public void setTotal_payment_received(String total_payment_received) {
		this.total_payment_received = total_payment_received;
	}
	public String getTotal_payment_posted() {
		return total_payment_posted;
	}
	public void setTotal_payment_posted(String total_payment_posted) {
		this.total_payment_posted = total_payment_posted;
	}
	public String getTotal_denial_received() {
		return total_denial_received;
	}
	public void setTotal_denial_received(String total_denial_received) {
		this.total_denial_received = total_denial_received;
	}
	public String getTotal_denial_worked() {
		return total_denial_worked;
	}
	public void setTotal_denial_worked(String total_denial_worked) {
		this.total_denial_worked = total_denial_worked;
	}
	public String getPatient_payment() {
		return patient_payment;
	}
	public void setPatient_payment(String patient_payment) {
		this.patient_payment = patient_payment;
	}
}
