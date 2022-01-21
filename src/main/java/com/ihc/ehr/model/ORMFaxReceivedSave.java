package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fax_recived")
public class ORMFaxReceivedSave {

	@Id
	private Long fax_recived_id;
	private Long location_id;
	private Long practice_id;
	private String fax_name;
	private String link;
	private String recived_date;
	private String sender;
	private String fax_status;
	private String assigned_to;
	private String comments;	
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private String assigned_to_id;
	private String receiver;
	private String fax_reference_id;
	private String fax_type;
	private String system_ip;
	
	public Long getFax_recived_id() {
		return fax_recived_id;
	}
	public void setFax_recived_id(Long fax_recived_id) {
		this.fax_recived_id = fax_recived_id;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public String getFax_name() {
		return fax_name;
	}
	public void setFax_name(String fax_name) {
		this.fax_name = fax_name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getRecived_date() {
		return recived_date;
	}
	public void setRecived_date(String recived_date) {
		this.recived_date = recived_date;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getFax_status() {
		return fax_status;
	}
	public void setFax_status(String fax_status) {
		this.fax_status = fax_status;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getAssigned_to_id() {
		return assigned_to_id;
	}
	public void setAssigned_to_id(String assigned_to_id) {
		this.assigned_to_id = assigned_to_id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getFax_reference_id() {
		return fax_reference_id;
	}
	public void setFax_reference_id(String fax_reference_id) {
		this.fax_reference_id = fax_reference_id;
	}
	public String getFax_type() {
		return fax_type;
	}
	public void setFax_type(String fax_type) {
		this.fax_type = fax_type;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	@Override
	public String toString() {
		return "ORMFaxReceivedSave [fax_recived_id=" + fax_recived_id + ", location_id=" + location_id
				+ ", practice_id=" + practice_id + ", fax_name=" + fax_name + ", link=" + link + ", recived_date="
				+ recived_date + ", sender=" + sender + ", fax_status=" + fax_status + ", assigned_to=" + assigned_to
				+ ", comments=" + comments + ", created_user=" + created_user + ", client_date_created="
				+ client_date_created + ", modified_user=" + modified_user + ", client_date_modified="
				+ client_date_modified + ", date_created=" + date_created + ", date_modified=" + date_modified
				+ ", assigned_to_id=" + assigned_to_id + ", receiver=" + receiver + ", fax_reference_id="
				+ fax_reference_id + ", fax_type=" + fax_type + ", system_ip=" + system_ip + "]";
	}
	
	

}
