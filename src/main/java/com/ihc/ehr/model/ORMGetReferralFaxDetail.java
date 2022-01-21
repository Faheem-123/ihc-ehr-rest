package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetReferralFaxDetail {

	 @Id    
	    private String fax_sent_id;
	    private String fax_sent_id_main;
	    private String date_created;
	    private String receiver_no;
	    private String receiver_name;
	    private String receiver_organization;
	    private String receiver_phone;
	    private String sender_no;
	    private String sender_name;
	    private String sender_organization;
	    private String sender_phone;
	    private String sender_email;
	    private Boolean include_cover_page;
	    private String subject;
	    private String cover_page_comments;
	    private String fax_status;
	    private String module_reference_id;
	    private String fax_reference_id;
	    private String patient_id;
	    private String fax_time;    
	    private String created_user;
		public String getFax_sent_id() {
			return fax_sent_id;
		}
		public void setFax_sent_id(String fax_sent_id) {
			this.fax_sent_id = fax_sent_id;
		}
		public String getFax_sent_id_main() {
			return fax_sent_id_main;
		}
		public void setFax_sent_id_main(String fax_sent_id_main) {
			this.fax_sent_id_main = fax_sent_id_main;
		}
		public String getDate_created() {
			return date_created;
		}
		public void setDate_created(String date_created) {
			this.date_created = date_created;
		}
		public String getReceiver_no() {
			return receiver_no;
		}
		public void setReceiver_no(String receiver_no) {
			this.receiver_no = receiver_no;
		}
		public String getReceiver_name() {
			return receiver_name;
		}
		public void setReceiver_name(String receiver_name) {
			this.receiver_name = receiver_name;
		}
		public String getReceiver_organization() {
			return receiver_organization;
		}
		public void setReceiver_organization(String receiver_organization) {
			this.receiver_organization = receiver_organization;
		}
		public String getReceiver_phone() {
			return receiver_phone;
		}
		public void setReceiver_phone(String receiver_phone) {
			this.receiver_phone = receiver_phone;
		}
		public String getSender_no() {
			return sender_no;
		}
		public void setSender_no(String sender_no) {
			this.sender_no = sender_no;
		}
		public String getSender_name() {
			return sender_name;
		}
		public void setSender_name(String sender_name) {
			this.sender_name = sender_name;
		}
		public String getSender_organization() {
			return sender_organization;
		}
		public void setSender_organization(String sender_organization) {
			this.sender_organization = sender_organization;
		}
		public String getSender_phone() {
			return sender_phone;
		}
		public void setSender_phone(String sender_phone) {
			this.sender_phone = sender_phone;
		}
		public String getSender_email() {
			return sender_email;
		}
		public void setSender_email(String sender_email) {
			this.sender_email = sender_email;
		}
		public Boolean getInclude_cover_page() {
			return include_cover_page;
		}
		public void setInclude_cover_page(Boolean include_cover_page) {
			this.include_cover_page = include_cover_page;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getCover_page_comments() {
			return cover_page_comments;
		}
		public void setCover_page_comments(String cover_page_comments) {
			this.cover_page_comments = cover_page_comments;
		}
		public String getFax_status() {
			return fax_status;
		}
		public void setFax_status(String fax_status) {
			this.fax_status = fax_status;
		}
		public String getModule_reference_id() {
			return module_reference_id;
		}
		public void setModule_reference_id(String module_reference_id) {
			this.module_reference_id = module_reference_id;
		}
		public String getFax_reference_id() {
			return fax_reference_id;
		}
		public void setFax_reference_id(String fax_reference_id) {
			this.fax_reference_id = fax_reference_id;
		}
		public String getPatient_id() {
			return patient_id;
		}
		public void setPatient_id(String patient_id) {
			this.patient_id = patient_id;
		}
		public String getFax_time() {
			return fax_time;
		}
		public void setFax_time(String fax_time) {
			this.fax_time = fax_time;
		}
		public String getCreated_user() {
			return created_user;
		}
		public void setCreated_user(String created_user) {
			this.created_user = created_user;
		} 
	    
	    
}
