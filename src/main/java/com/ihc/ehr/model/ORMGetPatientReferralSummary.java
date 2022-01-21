package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientReferralSummary {
	
	@Id
	private Long referral_id;
	private String referral_provider_name;
	private String provider_name;
	private String referral_reason;
	private String date_created;
	public Long getReferral_id() {
		return referral_id;
	}
	public void setReferral_id(Long referral_id) {
		this.referral_id = referral_id;
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
	public String getReferral_reason() {
		return referral_reason;
	}
	public void setReferral_reason(String referral_reason) {
		this.referral_reason = referral_reason;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
}
