package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="immunization_registry_errors")
public class ORMImmRegMsgErrorsSave {

	@Id    
    @GeneratedValue(strategy= GenerationType.IDENTITY)      
    private String error_id;
    private String message_id;
    private String severity;
    private String error_location;
    private String error_code;
    private String user_message;
    private String error_description;
    private Boolean deleted;
	public String getError_id() {
		return error_id;
	}
	public void setError_id(String error_id) {
		this.error_id = error_id;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getError_location() {
		return error_location;
	}
	public void setError_location(String error_location) {
		this.error_location = error_location;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getUser_message() {
		return user_message;
	}
	public void setUser_message(String user_message) {
		this.user_message = user_message;
	}
	public String getError_description() {
		return error_description;
	}
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
