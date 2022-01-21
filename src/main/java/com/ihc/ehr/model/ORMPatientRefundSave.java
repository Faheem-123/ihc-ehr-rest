package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patient_refund")
public class ORMPatientRefundSave {

	@Id
	private Long refund_id;
	private BigDecimal refund_amount;
	private String refund_method;
	private String check_number;
	private String check_date;
	private String transaction_id;
	private String authcode;
	private Long patient_id;
	private Long cash_register_id;
	private Long location_id;
	private Long provider_id;
	private Long practice_id;
	private Boolean voided;
	private String notes;
	private String modification_notes;
	private String created_user;
	private String date_created;
	private String modified_user;
	private String date_modified;
	private String client_date_created;
	private String client_date_modified;
	private String system_ip;
	public Long getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(Long refund_id) {
		this.refund_id = refund_id;
	}
	public BigDecimal getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(BigDecimal refund_amount) {
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
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	public Long getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(Long provider_id) {
		this.provider_id = provider_id;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
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
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	@Override
	public String toString() {
		return "ORMPatientRefundSave [refund_id=" + refund_id + ", refund_amount=" + refund_amount + ", refund_method="
				+ refund_method + ", check_number=" + check_number + ", check_date=" + check_date + ", transaction_id="
				+ transaction_id + ", authcode=" + authcode + ", patient_id=" + patient_id + ", cash_register_id="
				+ cash_register_id + ", location_id=" + location_id + ", provider_id=" + provider_id + ", practice_id="
				+ practice_id + ", voided=" + voided + ", notes=" + notes + ", modification_notes=" + modification_notes
				+ ", created_user=" + created_user + ", date_created=" + date_created + ", modified_user="
				+ modified_user + ", date_modified=" + date_modified + ", client_date_created=" + client_date_created
				+ ", client_date_modified=" + client_date_modified + ", system_ip=" + system_ip + "]";
	}	
	
}
