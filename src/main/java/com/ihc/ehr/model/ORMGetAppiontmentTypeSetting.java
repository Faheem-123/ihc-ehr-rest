package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetAppiontmentTypeSetting {

	@Id
	private Long type_id;
	private String description;	
	private String type_color;
	private Integer type_duration;
	private String comments_color;
	private String label_color;
	private String created_user;
	private String client_date_created;
	private String date_created;
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
	public String getType_color() {
		return type_color;
	}
	public void setType_color(String type_color) {
		this.type_color = type_color;
	}
	public Integer getType_duration() {
		return type_duration;
	}
	public void setType_duration(Integer type_duration) {
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
}
