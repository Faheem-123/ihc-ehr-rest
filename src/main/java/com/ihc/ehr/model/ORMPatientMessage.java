package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_message")
public class ORMPatientMessage {
	@Id
	private String message_id;
	private String mess_subject;
	private String mess_body_html;
	private String mess_body_text;
	private String mess_to;
	private Boolean deleted;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private String practice_id;
	private String message_type;
	private String from_id;
	private String to_id;
	private String sender_id;
	private Boolean is_draft;
	private String system_ip;
	private Boolean is_amendments;
	
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
	public String getMess_body_html() {
		return mess_body_html;
	}
	public void setMess_body_html(String mess_body_html) {
		this.mess_body_html = mess_body_html;
	}
	public String getMess_body_text() {
		return mess_body_text;
	}
	public void setMess_body_text(String mess_body_text) {
		this.mess_body_text = mess_body_text;
	}
	public String getMess_to() {
		return mess_to;
	}
	public void setMess_to(String mess_to) {
		this.mess_to = mess_to;
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
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getFrom_id() {
		return from_id;
	}
	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}
	public String getTo_id() {
		return to_id;
	}
	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	public Boolean getIs_draft() {
		return is_draft;
	}
	public void setIs_draft(Boolean is_draft) {
		this.is_draft = is_draft;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public Boolean getIs_amendments() {
		return is_amendments;
	}
	public void setIs_amendments(Boolean is_amendments) {
		this.is_amendments = is_amendments;
	}
}