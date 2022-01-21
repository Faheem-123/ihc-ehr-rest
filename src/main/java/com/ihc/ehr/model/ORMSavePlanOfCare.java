package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="chart_progress_note")
public class ORMSavePlanOfCare {

	@Id
	private String note_id;
	private String patient_id;
	private String chart_id;
	private String notes_html;
	private String notes_new_html;
	private String notes_text;
	private String practice_id;
	//private boolean deleted;
	private String created_user;
	private String client_date_created;
	private String date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_modified;
	private String system_ip;
	
	
	
	public String getNotes_new_html() {
		return notes_new_html;
	}
	public void setNotes_new_html(String notes_new_html) {
		this.notes_new_html = notes_new_html;
	}
	public String getNote_id() {
		return note_id;
	}
	public void setNote_id(String note_id) {
		this.note_id = note_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getChart_id() {
		return chart_id;
	}
	public void setChart_id(String chart_id) {
		this.chart_id = chart_id;
	}
	public String getNotes_html() {
		return notes_html;
	}
	public void setNotes_html(String notes_html) {
		this.notes_html = notes_html;
	}
	public String getNotes_text() {
		return notes_text;
	}
	public void setNotes_text(String notes_text) {
		this.notes_text = notes_text;
	}
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
//	public boolean isDeleted() {
//		return deleted;
//	}
//	public void setDeleted(boolean deleted) {
//		this.deleted = deleted;
//	}
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
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	@Override
	public String toString() {
		return "ORMSavePlanOfCare [note_id=" + note_id + ", patient_id=" + patient_id + ", chart_id=" + chart_id
				+ ", notes_html=" + notes_html + ", notes_text=" + notes_text + ", practice_id=" + practice_id
				+ ", created_user=" + created_user + ", client_date_created="
				+ client_date_created + ", date_created=" + date_created + ", modified_user=" + modified_user
				+ ", client_date_modified=" + client_date_modified + ", date_modified=" + date_modified + ", system_ip="
				+ system_ip + "]";
	}
	
	
	
}
