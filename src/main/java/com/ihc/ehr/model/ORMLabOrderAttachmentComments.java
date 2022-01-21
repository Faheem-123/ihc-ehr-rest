package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_order_attachment_comment")
public class ORMLabOrderAttachmentComments {

	 @Id
	    private String comment_id;
	    private String patient_order_attachment_id;
	    private String order_id;
	    private String comments;
	    private Boolean deleted;
	    private String created_user;
	    private String client_date_created;
	    private String date_created;
	    private String test_id;
	    private Boolean alert;
	    private String practice_id;
	    
		public String getPractice_id() {
			return practice_id;
		}
		public void setPractice_id(String practice_id) {
			this.practice_id = practice_id;
		}
		public String getComment_id() {
			return comment_id;
		}
		public void setComment_id(String comment_id) {
			this.comment_id = comment_id;
		}
		public String getPatient_order_attachment_id() {
			return patient_order_attachment_id;
		}
		public void setPatient_order_attachment_id(String patient_order_attachment_id) {
			this.patient_order_attachment_id = patient_order_attachment_id;
		}
		public String getOrder_id() {
			return order_id;
		}
		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}
		public Boolean getDeleted() {
			return deleted;
		}
		public void setDeleted(Boolean deleted) {
			this.deleted = deleted;
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
		public String getTest_id() {
			return test_id;
		}
		public void setTest_id(String test_id) {
			this.test_id = test_id;
		}
		public Boolean getAlert() {
			return alert;
		}
		public void setAlert(Boolean alert) {
			this.alert = alert;
		}
		@Override
		public String toString() {
			return "ORMLabOrderAttachmentComments [comment_id=" + comment_id + ", patient_order_attachment_id="
					+ patient_order_attachment_id + ", order_id=" + order_id + ", comments=" + comments + ", deleted="
					+ deleted + ", created_user=" + created_user + ", client_date_created=" + client_date_created
					+ ", date_created=" + date_created + ", test_id=" + test_id + ", alert=" + alert + ", practice_id="
					+ practice_id + "]";
		}
	    
	    
}
