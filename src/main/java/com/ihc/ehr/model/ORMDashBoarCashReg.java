package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMDashBoarCashReg {
	@Id
	private String cash_register_id;
    private String payment_date;
    private String name;
    private String payment_method;
    private String amount_paid;
    private String created_user;
    private String adjusted;
    
	public String getCash_register_id() {
		return cash_register_id;
	}
	public void setCash_register_id(String cash_register_id) {
		this.cash_register_id = cash_register_id;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(String amount_paid) {
		this.amount_paid = amount_paid;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getAdjusted() {
		return adjusted;
	}
	public void setAdjusted(String adjusted) {
		this.adjusted = adjusted;
	}
}
