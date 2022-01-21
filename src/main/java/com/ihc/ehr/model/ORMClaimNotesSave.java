package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="claim_notes")
public class ORMClaimNotesSave {
	
	@Id	
	private Long notes_id;
	private Long claim_id;
	private Long patient_id;
	private String notes;
	private String date_created;
	private String created_user;
	private String date_modified;
	private String modified_user;
	private Long practice_id;
	private String client_date_created;
	private String client_date_modified;
	private Boolean is_auto;
	
	public Long getNotes_id() {
		return notes_id;
	}
	public void setNotes_id(Long notes_id) {
		this.notes_id = notes_id;
	}
	public Long getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(Long claim_id) {
		this.claim_id = claim_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public Boolean getIs_auto() {
		return is_auto;
	}
	public void setIs_auto(Boolean is_auto) {
		this.is_auto = is_auto;
	}
	@Override
	public String toString() {
		return "ORMClaimNotesSave [notes_id=" + notes_id + ", claim_id=" + claim_id + ", patient_id=" + patient_id
				+ ", notes=" + notes + ", date_created=" + date_created + ", created_user=" + created_user
				+ ", date_modified=" + date_modified + ", modified_user=" + modified_user + ", practice_id="
				+ practice_id + ", client_date_created=" + client_date_created + ", client_date_modified="
				+ client_date_modified + ", is_auto=" + is_auto + "]";
	}	
	
	
	
}
