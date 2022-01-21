package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetChartReferral {

	@Id
	private String referral_id;
	private String consult_type;
	private String date_created;
	private String referral_provider_name;
	private String provider_name;
	private String location_name;
	private String referral_reason;
	private String signed_by;
	private String date_signed;
	private String notes;
	private boolean high_importance;
	private String referral_status;
	
	
	public String getReferral_status() {
		return referral_status;
	}
	public void setReferral_status(String referral_status) {
		this.referral_status = referral_status;
	}
	public boolean isHigh_importance() {
		return high_importance;
	}
	public void setHigh_importance(boolean high_importance) {
		this.high_importance = high_importance;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getReferral_id() {
		return referral_id;
	}
	public void setReferral_id(String referral_id) {
		this.referral_id = referral_id;
	}
	public String getConsult_type() {
		return consult_type;
	}
	public void setConsult_type(String consult_type) {
		this.consult_type = consult_type;
	}
	public String getReferral_provider_name() {
		return referral_provider_name;
	}
	public void setReferral_provider_name(String referral_provider_name) {
		this.referral_provider_name = referral_provider_name;
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
	public String getReferral_reason() {
		return referral_reason;
	}
	public void setReferral_reason(String referral_reason) {
		this.referral_reason = referral_reason;
	}
	public String getSigned_by() {
		return signed_by;
	}
	public void setSigned_by(String signed_by) {
		this.signed_by = signed_by;
	}
	public String getDate_signed() {
		return date_signed;
	}
	public void setDate_signed(String date_signed) {
		this.date_signed = date_signed;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
}
