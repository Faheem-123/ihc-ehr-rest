package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientPayment {
    @Id
    private String cash_register_id;
    private String patient_id;
    private String pat_name;
    private String dob;
    private String receipt_no;
    private String check_number;
    private String cash_amt;
    private String check_amt;
    private String credit_amt;
    private String moneyorder_amt;
    private String modified_user;
    private String adjustments;
    
	public String getCash_register_id() {
		return cash_register_id;
	}
	public void setCash_register_id(String cash_register_id) {
		this.cash_register_id = cash_register_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getPat_name() {
		return pat_name;
	}
	public void setPat_name(String pat_name) {
		this.pat_name = pat_name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getReceipt_no() {
		return receipt_no;
	}
	public void setReceipt_no(String receipt_no) {
		this.receipt_no = receipt_no;
	}
	public String getCheck_number() {
		return check_number;
	}
	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}
	public String getCash_amt() {
		return cash_amt;
	}
	public void setCash_amt(String cash_amt) {
		this.cash_amt = cash_amt;
	}
	public String getCheck_amt() {
		return check_amt;
	}
	public void setCheck_amt(String check_amt) {
		this.check_amt = check_amt;
	}
	public String getCredit_amt() {
		return credit_amt;
	}
	public void setCredit_amt(String credit_amt) {
		this.credit_amt = credit_amt;
	}
	public String getMoneyorder_amt() {
		return moneyorder_amt;
	}
	public void setMoneyorder_amt(String moneyorder_amt) {
		this.moneyorder_amt = moneyorder_amt;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getAdjustments() {
		return adjustments;
	}
	public void setAdjustments(String adjustments) {
		this.adjustments = adjustments;
	}
}
