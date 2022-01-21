package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetCommunication {
	@Id
    private String communication_id;
    private String name;
    private String link;
    private String comment;
    private String communication;
    private String communicate_with;
    private String communicate_date;
    private String patient_id;
    private String created_user;
    private String client_date_created;
    private String date_created;
    private String modified_by;
    private String client_modified_date;
    private String modified_date;
    private String patient_document_id;
    
	public String getCommunication_id() {
		return communication_id;
	}
	public void setCommunication_id(String communication_id) {
		this.communication_id = communication_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommunication() {
		return communication;
	}
	public void setCommunication(String communication) {
		this.communication = communication;
	}
	public String getCommunicate_with() {
		return communicate_with;
	}
	public void setCommunicate_with(String communicate_with) {
		this.communicate_with = communicate_with;
	}
	public String getCommunicate_date() {
		return communicate_date;
	}
	public void setCommunicate_date(String communicate_date) {
		this.communicate_date = communicate_date;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
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
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getClient_modified_date() {
		return client_modified_date;
	}
	public void setClient_modified_date(String client_modified_date) {
		this.client_modified_date = client_modified_date;
	}
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}
	public String getPatient_document_id() {
		return patient_document_id;
	}
	public void setPatient_document_id(String patient_document_id) {
		this.patient_document_id = patient_document_id;
	}    
}
