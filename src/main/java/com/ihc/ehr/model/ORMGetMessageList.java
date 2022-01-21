package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetMessageList {

	 @Id
	    private String message_detail_id;
	    private String message_id;
	    private String mess_subject;
	    private Boolean priority;
	    private Boolean readed;
	    private String mail_status;
	    private String created_user;
	    private String client_date_modified;
		public String getMessage_detail_id() {
			return message_detail_id;
		}
		public void setMessage_detail_id(String message_detail_id) {
			this.message_detail_id = message_detail_id;
		}
		public String getMessage_id() {
			return message_id;
		}
		public void setMessage_id(String message_id) {
			this.message_id = message_id;
		}
		public String getMess_subject() {
			return mess_subject;
		}
		public void setMess_subject(String mess_subject) {
			this.mess_subject = mess_subject;
		}
		public Boolean getPriority() {
			return priority;
		}
		public void setPriority(Boolean priority) {
			this.priority = priority;
		}
		public Boolean getReaded() {
			return readed;
		}
		public void setReaded(Boolean readed) {
			this.readed = readed;
		}
		public String getMail_status() {
			return mail_status;
		}
		public void setMail_status(String mail_status) {
			this.mail_status = mail_status;
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
		 
	    
	    
}
