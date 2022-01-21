package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class ORMMessageInsert {
	@Id
	private String message_id;
	private String mess_subject;
	private String mess_body_html;
	private String mess_body_text;
	private String mess_to;
	private String mess_cc;
	private String deleted;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private String practice_id;
	private String priority;
	private String sender;
	private String to_user;
	private String cc_user;
	private Boolean is_draft;
	private String to_location;
	private String to_role;
	private String cc_location;
	private String cc_role;
	private String system_ip;
	private String module_id;
	private String module_name;
	
	
	
	
	public String getModule_id() {
		return module_id;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
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
	public String getMess_cc() {
		return mess_cc;
	}
	public void setMess_cc(String mess_cc) {
		this.mess_cc = mess_cc;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
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
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTo_user() {
		return to_user;
	}
	public void setTo_user(String to_user) {
		this.to_user = to_user;
	}
	public String getCc_user() {
		return cc_user;
	}
	public void setCc_user(String cc_user) {
		this.cc_user = cc_user;
	}
	public Boolean getIs_draft() {
		return is_draft;
	}
	public void setIs_draft(Boolean is_draft) {
		this.is_draft = is_draft;
	}
	public String getTo_location() {
		return to_location;
	}
	public void setTo_location(String to_location) {
		this.to_location = to_location;
	}
	public String getTo_role() {
		return to_role;
	}
	public void setTo_role(String to_role) {
		this.to_role = to_role;
	}
	public String getCc_location() {
		return cc_location;
	}
	public void setCc_location(String cc_location) {
		this.cc_location = cc_location;
	}
	public String getCc_role() {
		return cc_role;
	}
	public void setCc_role(String cc_role) {
		this.cc_role = cc_role;
	}
	@Override
	public String toString() {
		return "ORMMessageInsert [message_id=" + message_id + ", mess_subject=" + mess_subject + ", mess_body_html="
				+ mess_body_html + ", mess_body_text=" + mess_body_text + ", mess_to=" + mess_to + ", mess_cc="
				+ mess_cc + ", deleted=" + deleted + ", created_user=" + created_user + ", client_date_created="
				+ client_date_created + ", modified_user=" + modified_user + ", client_date_modified="
				+ client_date_modified + ", date_created=" + date_created + ", date_modified=" + date_modified
				+ ", practice_id=" + practice_id + ", priority=" + priority + ", sender=" + sender + ", to_user="
				+ to_user + ", cc_user=" + cc_user + ", is_draft=" + is_draft + ", to_location=" + to_location
				+ ", to_role=" + to_role + ", cc_location=" + cc_location + ", cc_role=" + cc_role + ", system_ip="
				+ system_ip + ", module_id=" + module_id + ", module_name=" + module_name + "]";
	}
	
}
