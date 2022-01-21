package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message_attachment")
public class ORMMessageAttachmentsendfromPhr {
	@Id
	private String message_attachment_id;
	private String patient_id;
	private String practice_id;
	private String document_date;
	private String name;
	private String original_file_name;
	private String link;
	private String message_id;
	private String attach_type;
	
	public String getMessage_attachment_id() {
		return message_attachment_id;
	}
	public void setMessage_attachment_id(String message_attachment_id) {
		this.message_attachment_id = message_attachment_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public String getDocument_date() {
		return document_date;
	}
	public void setDocument_date(String document_date) {
		this.document_date = document_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginal_file_name() {
		return original_file_name;
	}
	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public String getAttach_type() {
		return attach_type;
	}
	public void setAttach_type(String attach_type) {
		this.attach_type = attach_type;
	}
}
