package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMPatientRefundGet {

	@Id
	private Long refund_id;
	private Long patient_id;
	private Long cash_register_id;
	private String refund_amount;
	private String refund_method;
	private String check_number;
	private String check_date;
	private String transaction_id;
	private String authcode;
	private Boolean voided;
	private String notes;
	private String modification_notes;
	private String created_user;
	private String date_created;
	private String modified_user;
	private String date_modified;

	public Long getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(Long refund_id) {
		this.refund_id = refund_id;
	}

	public Long getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}

	public Long getCash_register_id() {
		return cash_register_id;
	}

	public void setCash_register_id(Long cash_register_id) {
		this.cash_register_id = cash_register_id;
	}

	public String getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}

	public String getRefund_method() {
		return refund_method;
	}

	public void setRefund_method(String refund_method) {
		this.refund_method = refund_method;
	}

	public String getCheck_number() {
		return check_number;
	}

	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}

	public String getCheck_date() {
		return check_date;
	}

	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getModification_notes() {
		return modification_notes;
	}

	public void setModification_notes(String modification_notes) {
		this.modification_notes = modification_notes;
	}

	public String getCreated_user() {
		return created_user;
	}

	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public String getModified_user() {
		return modified_user;
	}

	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}

	public String getDate_modified() {
		return date_modified;
	}

	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}

}
