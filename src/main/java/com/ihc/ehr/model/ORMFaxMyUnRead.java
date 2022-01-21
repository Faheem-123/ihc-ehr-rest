package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMFaxMyUnRead {
	@Id
	private String fax_users_id;
    private String fax_recived_id;
    private String name;
    private String fax_name;
    private String recived_date;
    private String sender;
    private String fax_status;
    private String comments;
    private String link;
    private String location_id;
    private Boolean important;
    private String assigned_to;
    
	public String getFax_users_id() {
		return fax_users_id;
	}
	public void setFax_users_id(String fax_users_id) {
		this.fax_users_id = fax_users_id;
	}
	public String getFax_recived_id() {
		return fax_recived_id;
	}
	public void setFax_recived_id(String fax_recived_id) {
		this.fax_recived_id = fax_recived_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFax_name() {
		return fax_name;
	}
	public void setFax_name(String fax_name) {
		this.fax_name = fax_name;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public Boolean getImportant() {
		return important;
	}
	public void setImportant(Boolean important) {
		this.important = important;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
}
