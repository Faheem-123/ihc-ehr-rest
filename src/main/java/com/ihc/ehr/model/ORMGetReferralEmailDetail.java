package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetReferralEmailDetail {

	 @Id
	    private String email_attachment_id;
	    private String email_sent_id;
	    private String email_from;
	    private String email_to;
	    private String email_cc;    
	    private String doc_name;
	    private String doc_link;
	    private String date_created;
		public String getEmail_attachment_id() {
			return email_attachment_id;
		}
		public void setEmail_attachment_id(String email_attachment_id) {
			this.email_attachment_id = email_attachment_id;
		}
		public String getEmail_sent_id() {
			return email_sent_id;
		}
		public void setEmail_sent_id(String email_sent_id) {
			this.email_sent_id = email_sent_id;
		}
		public String getEmail_from() {
			return email_from;
		}
		public void setEmail_from(String email_from) {
			this.email_from = email_from;
		}
		public String getEmail_to() {
			return email_to;
		}
		public void setEmail_to(String email_to) {
			this.email_to = email_to;
		}
		public String getEmail_cc() {
			return email_cc;
		}
		public void setEmail_cc(String email_cc) {
			this.email_cc = email_cc;
		}
		public String getDoc_name() {
			return doc_name;
		}
		public void setDoc_name(String doc_name) {
			this.doc_name = doc_name;
		}
		public String getDoc_link() {
			return doc_link;
		}
		public void setDoc_link(String doc_link) {
			this.doc_link = doc_link;
		}
		public String getDate_created() {
			return date_created;
		}
		public void setDate_created(String date_created) {
			this.date_created = date_created;
		}
	    
	    
}
