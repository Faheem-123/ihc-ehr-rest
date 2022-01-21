package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientTestStaffNotes {

	  @Id
	    private String s_no;
	    private String notes_id;
	    private String order_id;        
	    private String notes;
	    private String created_user;
	    private String client_date_created;
	    private Boolean is_attachement;
		public String getS_no() {
			return s_no;
		}
		public void setS_no(String s_no) {
			this.s_no = s_no;
		}
		public String getNotes_id() {
			return notes_id;
		}
		public void setNotes_id(String notes_id) {
			this.notes_id = notes_id;
		}
		public String getOrder_id() {
			return order_id;
		}
		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
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
		public Boolean getIs_attachement() {
			return is_attachement;
		}
		public void setIs_attachement(Boolean is_attachement) {
			this.is_attachement = is_attachement;
		}
	    
	    
}
