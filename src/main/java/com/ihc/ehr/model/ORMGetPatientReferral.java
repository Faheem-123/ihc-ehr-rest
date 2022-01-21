package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientReferral {

	 @Id
	    private String referral_id;
	    private String patient_id;
	    private String pid;
	    private String patient_name;
	    private String referral_provider_name;
	    private String referral_reason;
	    private String notes;
	    private String referral_path;
	    private String client_date_created;
	    private String provider_name;
	    private String location_name;
	    private String referral_provider_id;
	    private String referral_provider_fax;
	    private String referral_provider_email;
	    private String referral_provider_address;
	    private String referral_provider_phone;
	    private String provider_id;
	    private String location_id;
	    private Boolean referral_request;
	    private String referral_status;
	    private String signed_by;
	    private String date_signed;
	    private String date_created;
	    private String date_modified;
	    private String modified_user;
	    private String created_user;
	    private String client_date_modified;
	    private String consult_type_id;
	    private String consult_type;
	    private String fax_status;
	    private Boolean high_importance;
	    private String date_created_mmddyy;
	    private String chart_id;
		public String getReferral_id() {
			return referral_id;
		}
		public void setReferral_id(String referral_id) {
			this.referral_id = referral_id;
		}
		public String getPatient_id() {
			return patient_id;
		}
		public void setPatient_id(String patient_id) {
			this.patient_id = patient_id;
		}
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public String getPatient_name() {
			return patient_name;
		}
		public void setPatient_name(String patient_name) {
			this.patient_name = patient_name;
		}
		public String getReferral_provider_name() {
			return referral_provider_name;
		}
		public void setReferral_provider_name(String referral_provider_name) {
			this.referral_provider_name = referral_provider_name;
		}
		public String getReferral_reason() {
			return referral_reason;
		}
		public void setReferral_reason(String referral_reason) {
			this.referral_reason = referral_reason;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
		public String getReferral_path() {
			return referral_path;
		}
		public void setReferral_path(String referral_path) {
			this.referral_path = referral_path;
		}
		public String getClient_date_created() {
			return client_date_created;
		}
		public void setClient_date_created(String client_date_created) {
			this.client_date_created = client_date_created;
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
		public String getReferral_provider_id() {
			return referral_provider_id;
		}
		public void setReferral_provider_id(String referral_provider_id) {
			this.referral_provider_id = referral_provider_id;
		}
		public String getReferral_provider_fax() {
			return referral_provider_fax;
		}
		public void setReferral_provider_fax(String referral_provider_fax) {
			this.referral_provider_fax = referral_provider_fax;
		}
		public String getReferral_provider_email() {
			return referral_provider_email;
		}
		public void setReferral_provider_email(String referral_provider_email) {
			this.referral_provider_email = referral_provider_email;
		}
		public String getReferral_provider_address() {
			return referral_provider_address;
		}
		public void setReferral_provider_address(String referral_provider_address) {
			this.referral_provider_address = referral_provider_address;
		}
		public String getReferral_provider_phone() {
			return referral_provider_phone;
		}
		public void setReferral_provider_phone(String referral_provider_phone) {
			this.referral_provider_phone = referral_provider_phone;
		}
		public String getProvider_id() {
			return provider_id;
		}
		public void setProvider_id(String provider_id) {
			this.provider_id = provider_id;
		}
		public String getLocation_id() {
			return location_id;
		}
		public void setLocation_id(String location_id) {
			this.location_id = location_id;
		}
		public Boolean getReferral_request() {
			return referral_request;
		}
		public void setReferral_request(Boolean referral_request) {
			this.referral_request = referral_request;
		}
		public String getReferral_status() {
			return referral_status;
		}
		public void setReferral_status(String referral_status) {
			this.referral_status = referral_status;
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
		public String getModified_user() {
			return modified_user;
		}
		public void setModified_user(String modified_user) {
			this.modified_user = modified_user;
		}
		public String getCreated_user() {
			return created_user;
		}
		public void setCreated_user(String created_user) {
			this.created_user = created_user;
		}
		public String getClient_date_modified() {
			return client_date_modified;
		}
		public void setClient_date_modified(String client_date_modified) {
			this.client_date_modified = client_date_modified;
		}
		public String getConsult_type_id() {
			return consult_type_id;
		}
		public void setConsult_type_id(String consult_type_id) {
			this.consult_type_id = consult_type_id;
		}
		public String getConsult_type() {
			return consult_type;
		}
		public void setConsult_type(String consult_type) {
			this.consult_type = consult_type;
		}
		public String getFax_status() {
			return fax_status;
		}
		public void setFax_status(String fax_status) {
			this.fax_status = fax_status;
		}
		public Boolean getHigh_importance() {
			return high_importance;
		}
		public void setHigh_importance(Boolean high_importance) {
			this.high_importance = high_importance;
		}
		public String getDate_created_mmddyy() {
			return date_created_mmddyy;
		}
		public void setDate_created_mmddyy(String date_created_mmddyy) {
			this.date_created_mmddyy = date_created_mmddyy;
		}
		public String getChart_id() {
			return chart_id;
		}
		public void setChart_id(String chart_id) {
			this.chart_id = chart_id;
		}
	    
	    
}
