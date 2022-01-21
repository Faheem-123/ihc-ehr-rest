package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cash_register")
public class ORMCashRegisterAdd {

	@Id
    private Long cash_register_id;
    private Long patient_id;
    private Long appointment_id;
    private Long provider_id;
    private Long location_id;
    private Long practice_id;    
    private String dos;
    private BigDecimal copay_due;
    private BigDecimal copay_paid;
    private BigDecimal selfpay_due;
    private BigDecimal selfpay_paid;
    private BigDecimal previous_balance_due;
    private BigDecimal previous_balance_paid;
    private BigDecimal copay_write_off;
    private BigDecimal selfpay_write_off;
    private BigDecimal prev_balance_write_off;
    private BigDecimal other_paid;
    private BigDecimal advance_paid;
    private BigDecimal copay_advance_adjusted;
    private BigDecimal selfpay_advance_adjusted;
    private BigDecimal prev_balance_advance_adjusted;
    private BigDecimal other_advance_adjusted;
    private String payment_method;
    private String check_date;
    private String check_number;
    private String receipt_no;    
    private String comments;
    private String modification_comments;
    private Boolean resolved;
    private String resolved_by;
    private String date_resolved;
    private String created_user;
    private String modified_user;
    private String date_created;
    private String date_modified;
    private Boolean voided;
    private Boolean deleted;
    private String system_ip;
    private String client_date_created;
    private String client_date_modified;
    private Boolean check_bounce;
    private Boolean is_refund;
    private BigDecimal refund_amount;
    private String write_off_code;
    private String refund_main_id;
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
	public Long getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(Long appointment_id) {
		this.appointment_id = appointment_id;
	}
	public Long getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(Long provider_id) {
		this.provider_id = provider_id;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public BigDecimal getCopay_due() {
		return copay_due;
	}
	public void setCopay_due(BigDecimal copay_due) {
		this.copay_due = copay_due;
	}
	public BigDecimal getCopay_paid() {
		return copay_paid;
	}
	public void setCopay_paid(BigDecimal copay_paid) {
		this.copay_paid = copay_paid;
	}
	public BigDecimal getSelfpay_due() {
		return selfpay_due;
	}
	public void setSelfpay_due(BigDecimal selfpay_due) {
		this.selfpay_due = selfpay_due;
	}
	public BigDecimal getSelfpay_paid() {
		return selfpay_paid;
	}
	public void setSelfpay_paid(BigDecimal selfpay_paid) {
		this.selfpay_paid = selfpay_paid;
	}
	public BigDecimal getPrevious_balance_due() {
		return previous_balance_due;
	}
	public void setPrevious_balance_due(BigDecimal previous_balance_due) {
		this.previous_balance_due = previous_balance_due;
	}
	public BigDecimal getPrevious_balance_paid() {
		return previous_balance_paid;
	}
	public void setPrevious_balance_paid(BigDecimal previous_balance_paid) {
		this.previous_balance_paid = previous_balance_paid;
	}
	public BigDecimal getCopay_write_off() {
		return copay_write_off;
	}
	public void setCopay_write_off(BigDecimal copay_write_off) {
		this.copay_write_off = copay_write_off;
	}
	public BigDecimal getSelfpay_write_off() {
		return selfpay_write_off;
	}
	public void setSelfpay_write_off(BigDecimal selfpay_write_off) {
		this.selfpay_write_off = selfpay_write_off;
	}
	public BigDecimal getPrev_balance_write_off() {
		return prev_balance_write_off;
	}
	public void setPrev_balance_write_off(BigDecimal prev_balance_write_off) {
		this.prev_balance_write_off = prev_balance_write_off;
	}
	public BigDecimal getOther_paid() {
		return other_paid;
	}
	public void setOther_paid(BigDecimal other_paid) {
		this.other_paid = other_paid;
	}
	public BigDecimal getAdvance_paid() {
		return advance_paid;
	}
	public void setAdvance_paid(BigDecimal advance_paid) {
		this.advance_paid = advance_paid;
	}
	public BigDecimal getCopay_advance_adjusted() {
		return copay_advance_adjusted;
	}
	public void setCopay_advance_adjusted(BigDecimal copay_advance_adjusted) {
		this.copay_advance_adjusted = copay_advance_adjusted;
	}
	public BigDecimal getSelfpay_advance_adjusted() {
		return selfpay_advance_adjusted;
	}
	public void setSelfpay_advance_adjusted(BigDecimal selfpay_advance_adjusted) {
		this.selfpay_advance_adjusted = selfpay_advance_adjusted;
	}
	public BigDecimal getPrev_balance_advance_adjusted() {
		return prev_balance_advance_adjusted;
	}
	public void setPrev_balance_advance_adjusted(BigDecimal prev_balance_advance_adjusted) {
		this.prev_balance_advance_adjusted = prev_balance_advance_adjusted;
	}
	public BigDecimal getOther_advance_adjusted() {
		return other_advance_adjusted;
	}
	public void setOther_advance_adjusted(BigDecimal other_advance_adjusted) {
		this.other_advance_adjusted = other_advance_adjusted;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
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
	public String getReceipt_no() {
		return receipt_no;
	}
	public void setReceipt_no(String receipt_no) {
		this.receipt_no = receipt_no;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getModification_comments() {
		return modification_comments;
	}
	public void setModification_comments(String modification_comments) {
		this.modification_comments = modification_comments;
	}
	public Boolean getResolved() {
		return resolved;
	}
	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}
	public String getResolved_by() {
		return resolved_by;
	}
	public void setResolved_by(String resolved_by) {
		this.resolved_by = resolved_by;
	}
	public String getDate_resolved() {
		return date_resolved;
	}
	public void setDate_resolved(String date_resolved) {
		this.date_resolved = date_resolved;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
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
	public Boolean getVoided() {
		return voided;
	}
	public void setVoided(Boolean voided) {
		this.voided = voided;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public Boolean getCheck_bounce() {
		return check_bounce;
	}
	public void setCheck_bounce(Boolean check_bounce) {
		this.check_bounce = check_bounce;
	}
	public Boolean getIs_refund() {
		return is_refund;
	}
	public void setIs_refund(Boolean is_refund) {
		this.is_refund = is_refund;
	}
	public BigDecimal getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(BigDecimal refund_amount) {
		this.refund_amount = refund_amount;
	}
	public String getWrite_off_code() {
		return write_off_code;
	}
	public void setWrite_off_code(String write_off_code) {
		this.write_off_code = write_off_code;
	}
	public String getRefund_main_id() {
		return refund_main_id;
	}
	public void setRefund_main_id(String refund_main_id) {
		this.refund_main_id = refund_main_id;
	}
	@Override
	public String toString() {
		return "ORMCashRegisterAdd [cash_register_id=" + cash_register_id + ", patient_id=" + patient_id
				+ ", appointment_id=" + appointment_id + ", provider_id=" + provider_id + ", location_id=" + location_id
				+ ", practice_id=" + practice_id + ", dos=" + dos + ", copay_due=" + copay_due + ", copay_paid="
				+ copay_paid + ", selfpay_due=" + selfpay_due + ", selfpay_paid=" + selfpay_paid
				+ ", previous_balance_due=" + previous_balance_due + ", previous_balance_paid=" + previous_balance_paid
				+ ", copay_write_off=" + copay_write_off + ", selfpay_write_off=" + selfpay_write_off
				+ ", prev_balance_write_off=" + prev_balance_write_off + ", other_paid=" + other_paid
				+ ", advance_paid=" + advance_paid + ", copay_advance_adjusted=" + copay_advance_adjusted
				+ ", selfpay_advance_adjusted=" + selfpay_advance_adjusted + ", prev_balance_advance_adjusted="
				+ prev_balance_advance_adjusted + ", other_advance_adjusted=" + other_advance_adjusted
				+ ", payment_method=" + payment_method + ", check_date=" + check_date + ", check_number=" + check_number
				+ ", receipt_no=" + receipt_no + ", comments=" + comments + ", modification_comments="
				+ modification_comments + ", resolved=" + resolved + ", resolved_by=" + resolved_by + ", date_resolved="
				+ date_resolved + ", created_user=" + created_user + ", modified_user=" + modified_user
				+ ", date_created=" + date_created + ", date_modified=" + date_modified + ", voided=" + voided
				+ ", deleted=" + deleted + ", system_ip=" + system_ip + ", client_date_created=" + client_date_created
				+ ", client_date_modified=" + client_date_modified + ", check_bounce=" + check_bounce + ", is_refund="
				+ is_refund + ", refund_amount=" + refund_amount + ", write_off_code=" + write_off_code
				+ ", refund_main_id=" + refund_main_id + "]";
	}
    
    
}
