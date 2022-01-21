package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="appointment_type")
public class ORMSaveAppointmentTypes {
	
	@Id
	private Long type_id;
	private String description;
	private Long practice_id;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private String type_color;
	private String type_duration;
	private String comments_color;
	private String label_color;
	
	public Long getType_id() {
		return type_id;
	}
	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
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
	public String getType_color() {
		return type_color;
	}
	public void setType_color(String type_color) {
		this.type_color = type_color;
	}
	public String getType_duration() {
		return type_duration;
	}
	public void setType_duration(String type_duration) {
		this.type_duration = type_duration;
	}
	public String getComments_color() {
		return comments_color;
	}
	public void setComments_color(String comments_color) {
		this.comments_color = comments_color;
	}
	public String getLabel_color() {
		return label_color;
	}
	public void setLabel_color(String label_color) {
		this.label_color = label_color;
	}
	@Override
	public String toString() {
		return "ORMSaveAppointmentTypes [type_id=" + type_id + ", description=" + description + ", practice_id="
				+ practice_id + ", created_user=" + created_user + ", client_date_created=" + client_date_created
				+ ", modified_user=" + modified_user + ", client_date_modified=" + client_date_modified
				+ ", date_created=" + date_created + ", date_modified=" + date_modified + ", type_color=" + type_color
				+ ", type_duration=" + type_duration + ", comments_color=" + comments_color + ", label_color="
				+ label_color + "]";
	}
	
	

}
