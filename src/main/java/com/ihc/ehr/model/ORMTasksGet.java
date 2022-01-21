package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMTasksGet {

	@Id
	private Long task_id;
	private String title;	
	private String priority;
	private String status;
	private String due_date;
	private String date_completed;	
	private String completed_user;	
	private String assigned_from;
	private String assigned_to;
	private String created_user;
	private String date_created;
	
	private String assigned_from_name;
	private String assigned_to_name;
	//private String created_user_name;
	
	private Boolean over_due;
	
	public Long getTask_id() {
		return task_id;
	}
	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getDate_completed() {
		return date_completed;
	}
	public void setDate_completed(String date_completed) {
		this.date_completed = date_completed;
	}
	public String getCompleted_user() {
		return completed_user;
	}
	public void setCompleted_user(String completed_user) {
		this.completed_user = completed_user;
	}
	public String getAssigned_from() {
		return assigned_from;
	}
	public void setAssigned_from(String assigned_from) {
		this.assigned_from = assigned_from;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getAssigned_from_name() {
		return assigned_from_name;
	}
	public void setAssigned_from_name(String assigned_from_name) {
		this.assigned_from_name = assigned_from_name;
	}
	public String getAssigned_to_name() {
		return assigned_to_name;
	}
	public void setAssigned_to_name(String assigned_to_name) {
		this.assigned_to_name = assigned_to_name;
	}
	
	public Boolean getOver_due() {
		return over_due;
	}
	public void setOver_due(Boolean over_due) {
		this.over_due = over_due;
	}
	
}
