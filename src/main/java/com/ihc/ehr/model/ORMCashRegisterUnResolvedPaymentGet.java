package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMCashRegisterUnResolvedPaymentGet {

	@Id
	private Long cash_register_id;
	private Boolean selected;
	private String provider_name;
	private String location_name;
	private BigDecimal total_dos_paid;
	private String paid_posted;
	private BigDecimal paid_pending;
	private BigDecimal total_dos_write_off;
	private BigDecimal write_off_posted;
	private BigDecimal write_off_pending;
	private String client_date_created;
	private String check_date;
	private String check_number;

	private BigDecimal total_prev_paid;
	private BigDecimal prev_paid_posted;
	private BigDecimal prev_paid_pending;
	private BigDecimal total_prev_write_off;
	private BigDecimal prev_write_off_posted;
	private BigDecimal prev_write_off_pending;
	public Long getCash_register_id() {
		return cash_register_id;
	}
	public void setCash_register_id(Long cash_register_id) {
		this.cash_register_id = cash_register_id;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public BigDecimal getTotal_dos_paid() {
		return total_dos_paid;
	}
	public void setTotal_dos_paid(BigDecimal total_dos_paid) {
		this.total_dos_paid = total_dos_paid;
	}
	public String getPaid_posted() {
		return paid_posted;
	}
	public void setPaid_posted(String paid_posted) {
		this.paid_posted = paid_posted;
	}
	public BigDecimal getPaid_pending() {
		return paid_pending;
	}
	public void setPaid_pending(BigDecimal paid_pending) {
		this.paid_pending = paid_pending;
	}
	public BigDecimal getTotal_dos_write_off() {
		return total_dos_write_off;
	}
	public void setTotal_dos_write_off(BigDecimal total_dos_write_off) {
		this.total_dos_write_off = total_dos_write_off;
	}
	public BigDecimal getWrite_off_posted() {
		return write_off_posted;
	}
	public void setWrite_off_posted(BigDecimal write_off_posted) {
		this.write_off_posted = write_off_posted;
	}
	public BigDecimal getWrite_off_pending() {
		return write_off_pending;
	}
	public void setWrite_off_pending(BigDecimal write_off_pending) {
		this.write_off_pending = write_off_pending;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
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
	public BigDecimal getTotal_prev_paid() {
		return total_prev_paid;
	}
	public void setTotal_prev_paid(BigDecimal total_prev_paid) {
		this.total_prev_paid = total_prev_paid;
	}
	public BigDecimal getPrev_paid_posted() {
		return prev_paid_posted;
	}
	public void setPrev_paid_posted(BigDecimal prev_paid_posted) {
		this.prev_paid_posted = prev_paid_posted;
	}
	public BigDecimal getPrev_paid_pending() {
		return prev_paid_pending;
	}
	public void setPrev_paid_pending(BigDecimal prev_paid_pending) {
		this.prev_paid_pending = prev_paid_pending;
	}
	public BigDecimal getTotal_prev_write_off() {
		return total_prev_write_off;
	}
	public void setTotal_prev_write_off(BigDecimal total_prev_write_off) {
		this.total_prev_write_off = total_prev_write_off;
	}
	public BigDecimal getPrev_write_off_posted() {
		return prev_write_off_posted;
	}
	public void setPrev_write_off_posted(BigDecimal prev_write_off_posted) {
		this.prev_write_off_posted = prev_write_off_posted;
	}
	public BigDecimal getPrev_write_off_pending() {
		return prev_write_off_pending;
	}
	public void setPrev_write_off_pending(BigDecimal prev_write_off_pending) {
		this.prev_write_off_pending = prev_write_off_pending;
	}	
}
