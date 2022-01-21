package com.ihc.ehr.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "patient_message_detail")
public class ORMPatientMessageDetail {
	@Id
	private String message_detail_id;
	private String messages_id;
	private String recieve_date;
	private Boolean readed;
	private String mail_status;
	private String patient_id;
	private Boolean deleted;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private String user_id;
	private String phr_user;
	private String reciever_id;
	
	public String getMessage_detail_id() {
		return message_detail_id;
	}
	public void setMessage_detail_id(String message_detail_id) {
		this.message_detail_id = message_detail_id;
	}
	public String getMessages_id() {
		return messages_id;
	}
	public void setMessages_id(String messages_id) {
		this.messages_id = messages_id;
	}
	public String getRecieve_date() {
		return recieve_date;
	}
	public void setRecieve_date(String recieve_date) {
		this.recieve_date = recieve_date;
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
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
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
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPhr_user() {
		return phr_user;
	}
	public void setPhr_user(String phr_user) {
		this.phr_user = phr_user;
	}
	public String getReciever_id() {
		return reciever_id;
	}
	public void setReciever_id(String reciever_id) {
		this.reciever_id = reciever_id;
	}
}
