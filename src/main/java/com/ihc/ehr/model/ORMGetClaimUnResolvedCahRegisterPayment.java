package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetClaimUnResolvedCahRegisterPayment {

	@Id
	private Long cash_register_id;
	private Long patient_id;
	private String check_date;
	private String check_number;
	private BigDecimal paid_pending;
	private BigDecimal write_off_pending;
	public Long getCash_register_id() {
		return cash_register_id;
	}
	public void setCash_register_id(Long cash_register_id) {
		this.cash_register_id = cash_register_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
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
	public BigDecimal getPaid_pending() {
		return paid_pending;
	}
	public void setPaid_pending(BigDecimal paid_pending) {
		this.paid_pending = paid_pending;
	}
	public BigDecimal getWrite_off_pending() {
		return write_off_pending;
	}
	public void setWrite_off_pending(BigDecimal write_off_pending) {
		this.write_off_pending = write_off_pending;
	}
}
