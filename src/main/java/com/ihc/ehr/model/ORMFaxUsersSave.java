package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fax_users")
public class ORMFaxUsersSave {

	@Id
	private Long fax_users_id;
	private Long fax_recived_id;
	private String assigned_to;
	private String assigned_to_id;
	private String fax_status;
	private String comments;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private Boolean important;
	private String system_ip;

	public Long getFax_users_id() {
		return fax_users_id;
	}

	public void setFax_users_id(Long fax_users_id) {
		this.fax_users_id = fax_users_id;
	}

	public Long getFax_recived_id() {
		return fax_recived_id;
	}

	public void setFax_recived_id(Long fax_recived_id) {
		this.fax_recived_id = fax_recived_id;
	}

	public String getAssigned_to() {
		return assigned_to;
	}

	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}

	public String getAssigned_to_id() {
		return assigned_to_id;
	}

	public void setAssigned_to_id(String assigned_to_id) {
		this.assigned_to_id = assigned_to_id;
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

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public String getSystem_ip() {
		return system_ip;
	}

	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}

	@Override
	public String toString() {
		return "ORMFaxUsersSave [fax_users_id=" + fax_users_id + ", fax_recived_id=" + fax_recived_id + ", assigned_to="
				+ assigned_to + ", assigned_to_id=" + assigned_to_id + ", fax_status=" + fax_status + ", comments="
				+ comments + ", created_user=" + created_user + ", client_date_created=" + client_date_created
				+ ", modified_user=" + modified_user + ", client_date_modified=" + client_date_modified
				+ ", date_created=" + date_created + ", date_modified=" + date_modified + ", important=" + important
				+ ", system_ip=" + system_ip + "]";
	}
	
}
