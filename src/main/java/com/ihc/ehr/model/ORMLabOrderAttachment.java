package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_order_attachment")
public class ORMLabOrderAttachment {

	 @Id
	    private String patient_order_attachment_id;
	    private String document_date;
	    private String name;
	    private String link;
	    private String followup_note;
	    private String follow_up_action;
	    private String reviewed_by;
	    private String reviewed_date;
	    private String assigned_to;
	    private String patient_document_id;
	    private String order_id;
	    private String created_user;
	    private String client_date_created;
	    private String date_created;
	    private String deleted;
	    private String date_modified;
	    private String client_date_modified;
	    private String modified_user;
	    private String status;
	    private String lab_category_id;
	    private String doc_size;
	    private String doc_created_user;
	    private String doc_created_date;
	    private String doc_modified_user;
	    private String doc_modified_date;
	    private String doc_type;
	    private String test_id;
	    private String physician_comments;
	    private String followup_detail;
	    private String phy_comments_date;
		public String getPatient_order_attachment_id() {
			return patient_order_attachment_id;
		}
		public void setPatient_order_attachment_id(String patient_order_attachment_id) {
			this.patient_order_attachment_id = patient_order_attachment_id;
		}
		public String getDocument_date() {
			return document_date;
		}
		public void setDocument_date(String document_date) {
			this.document_date = document_date;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public String getFollowup_note() {
			return followup_note;
		}
		public void setFollowup_note(String followup_note) {
			this.followup_note = followup_note;
		}
		public String getFollow_up_action() {
			return follow_up_action;
		}
		public void setFollow_up_action(String follow_up_action) {
			this.follow_up_action = follow_up_action;
		}
		public String getReviewed_by() {
			return reviewed_by;
		}
		public void setReviewed_by(String reviewed_by) {
			this.reviewed_by = reviewed_by;
		}
		public String getReviewed_date() {
			return reviewed_date;
		}
		public void setReviewed_date(String reviewed_date) {
			this.reviewed_date = reviewed_date;
		}
		public String getAssigned_to() {
			return assigned_to;
		}
		public void setAssigned_to(String assigned_to) {
			this.assigned_to = assigned_to;
		}
		public String getPatient_document_id() {
			return patient_document_id;
		}
		public void setPatient_document_id(String patient_document_id) {
			this.patient_document_id = patient_document_id;
		}
		public String getOrder_id() {
			return order_id;
		}
		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}
		public String getCreated_user() {
			return created_user;
		}
		public void setCreated_user(String created_user) {
			this.created_user = created_user;
		}
		public String getClient_date_created() {
			return client_date_created;
		}
		public void setClient_date_created(String client_date_created) {
			this.client_date_created = client_date_created;
		}
		public String getDate_created() {
			return date_created;
		}
		public void setDate_created(String date_created) {
			this.date_created = date_created;
		}
		public String getDeleted() {
			return deleted;
		}
		public void setDeleted(String deleted) {
			this.deleted = deleted;
		}
		public String getDate_modified() {
			return date_modified;
		}
		public void setDate_modified(String date_modified) {
			this.date_modified = date_modified;
		}
		public String getClient_date_modified() {
			return client_date_modified;
		}
		public void setClient_date_modified(String client_date_modified) {
			this.client_date_modified = client_date_modified;
		}
		public String getModified_user() {
			return modified_user;
		}
		public void setModified_user(String modified_user) {
			this.modified_user = modified_user;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getLab_category_id() {
			return lab_category_id;
		}
		public void setLab_category_id(String lab_category_id) {
			this.lab_category_id = lab_category_id;
		}
		public String getDoc_size() {
			return doc_size;
		}
		public void setDoc_size(String doc_size) {
			this.doc_size = doc_size;
		}
		  
		public String getDoc_type() {
			return doc_type;
		}
		public void setDoc_type(String doc_type) {
			this.doc_type = doc_type;
		}
		public String getTest_id() {
			return test_id;
		}
		public void setTest_id(String test_id) {
			this.test_id = test_id;
		}
		public String getPhysician_comments() {
			return physician_comments;
		}
		public void setPhysician_comments(String physician_comments) {
			this.physician_comments = physician_comments;
		}
		public String getFollowup_detail() {
			return followup_detail;
		}
		public void setFollowup_detail(String followup_detail) {
			this.followup_detail = followup_detail;
		}
		public String getPhy_comments_date() {
			return phy_comments_date;
		}
		public void setPhy_comments_date(String phy_comments_date) {
			this.phy_comments_date = phy_comments_date;
		}
		public String getDoc_created_user() {
			return doc_created_user;
		}
		public void setDoc_created_user(String doc_created_user) {
			this.doc_created_user = doc_created_user;
		}
		public String getDoc_created_date() {
			return doc_created_date;
		}
		public void setDoc_created_date(String doc_created_date) {
			this.doc_created_date = doc_created_date;
		}
		public String getDoc_modified_user() {
			return doc_modified_user;
		}
		public void setDoc_modified_user(String doc_modified_user) {
			this.doc_modified_user = doc_modified_user;
		}
		public String getDoc_modified_date() {
			return doc_modified_date;
		}
		public void setDoc_modified_date(String doc_modified_date) {
			this.doc_modified_date = doc_modified_date;
		}
		@Override
		public String toString() {
			return "ORMLabOrderAttachment [patient_order_attachment_id=" + patient_order_attachment_id
					+ ", document_date=" + document_date + ", name=" + name + ", link=" + link + ", followup_note="
					+ followup_note + ", follow_up_action=" + follow_up_action + ", reviewed_by=" + reviewed_by
					+ ", reviewed_date=" + reviewed_date + ", assigned_to=" + assigned_to + ", patient_document_id="
					+ patient_document_id + ", order_id=" + order_id + ", created_user=" + created_user
					+ ", client_date_created=" + client_date_created + ", date_created=" + date_created + ", deleted="
					+ deleted + ", date_modified=" + date_modified + ", client_date_modified=" + client_date_modified
					+ ", modified_user=" + modified_user + ", status=" + status + ", lab_category_id=" + lab_category_id
					+ ", doc_size=" + doc_size + ", doc_created_user=" + doc_created_user + ", doc_created_date="
					+ doc_created_date + ", doc_modified_user=" + doc_modified_user + ", doc_modified_date="
					+ doc_modified_date + ", doc_type=" + doc_type + ", test_id=" + test_id + ", physician_comments="
					+ physician_comments + ", followup_detail=" + followup_detail + ", phy_comments_date="
					+ phy_comments_date + "]";
		}
	    
	    
}
