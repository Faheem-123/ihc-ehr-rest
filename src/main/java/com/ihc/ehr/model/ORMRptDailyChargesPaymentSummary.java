package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMRptDailyChargesPaymentSummary {
	
	@Id
	private Long sno;
	private String summary_date;	
	private String total_charges;
	private String total_payment;
	private String total_adjusted;
	private String total_refund;
	private String ar_balance;
	private Boolean is_aggregate;
	public Long getSno() {
		return sno;
	}
	public void setSno(Long sno) {
		this.sno = sno;
	}
	public String getSummary_date() {
		return summary_date;
	}
	public void setSummary_date(String summary_date) {
		this.summary_date = summary_date;
	}
	public String getTotal_charges() {
		return total_charges;
	}
	public void setTotal_charges(String total_charges) {
		this.total_charges = total_charges;
	}
	public String getTotal_payment() {
		return total_payment;
	}
	public void setTotal_payment(String total_payment) {
		this.total_payment = total_payment;
	}
	public String getTotal_adjusted() {
		return total_adjusted;
	}
	public void setTotal_adjusted(String total_adjusted) {
		this.total_adjusted = total_adjusted;
	}
	public String getTotal_refund() {
		return total_refund;
	}
	public void setTotal_refund(String total_refund) {
		this.total_refund = total_refund;
	}


	public String getAr_balance() {
		return ar_balance;
	}
	public void setAr_balance(String ar_balance) {
		this.ar_balance = ar_balance;
	}
	public Boolean getIs_aggregate() {
		return is_aggregate;
	}
	public void setIs_aggregate(Boolean is_aggregate) {
		this.is_aggregate = is_aggregate;
	}
	
	
}
