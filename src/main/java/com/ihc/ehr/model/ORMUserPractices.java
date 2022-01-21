package com.ihc.ehr.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users_practices")
public class ORMUserPractices {
	@Id
	private String user_practice_id;
	private String practice_id;
	private String user_id;
	private String created_user;
	private String client_date_created;
	private String date_created;
	
	public String getUser_practice_id() {
		return user_practice_id;
	}
	public void setUser_practice_id(String user_practice_id) {
		this.user_practice_id = user_practice_id;
	}
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
