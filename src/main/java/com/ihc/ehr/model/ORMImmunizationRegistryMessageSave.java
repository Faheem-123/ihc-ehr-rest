package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "immunization_registry_messages")
public class ORMImmunizationRegistryMessageSave {

	@Id
	private Long message_id;
	private Long patient_id;
	private Long chart_id;
	private String message_status;	
	private String date_modified;
	private String modified_user;
	private String date_created;
	private String created_user;
	private String system_ip;
	private String response_file_name;
	private String immunization_message;
	private String transport_message;
	private String response_message;
	private String registry_code;
	private String clinic_id;
	private Long practice_id;
	private String error_details;
	private String clinic_code;
	private String message_sent_date;
	private String message_sent_by;
	private String message_type;
	public Long getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Long message_id) {
		this.message_id = message_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public Long getChart_id() {
		return chart_id;
	}
	public void setChart_id(Long chart_id) {
		this.chart_id = chart_id;
	}
	public String getMessage_status() {
		return message_status;
	}
	public void setMessage_status(String message_status) {
		this.message_status = message_status;
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
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public String getResponse_file_name() {
		return response_file_name;
	}
	public void setResponse_file_name(String response_file_name) {
		this.response_file_name = response_file_name;
	}
	public String getImmunization_message() {
		return immunization_message;
	}
	public void setImmunization_message(String immunization_message) {
		this.immunization_message = immunization_message;
	}
	public String getTransport_message() {
		return transport_message;
	}
	public void setTransport_message(String transport_message) {
		this.transport_message = transport_message;
	}
	public String getResponse_message() {
		return response_message;
	}
	public void setResponse_message(String response_message) {
		this.response_message = response_message;
	}
	public String getRegistry_code() {
		return registry_code;
	}
	public void setRegistry_code(String registry_code) {
		this.registry_code = registry_code;
	}
	public String getClinic_id() {
		return clinic_id;
	}
	public void setClinic_id(String clinic_id) {
		this.clinic_id = clinic_id;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public String getError_details() {
		return error_details;
	}
	public void setError_details(String error_details) {
		this.error_details = error_details;
	}
	public String getClinic_code() {
		return clinic_code;
	}
	public void setClinic_code(String clinic_code) {
		this.clinic_code = clinic_code;
	}
	public String getMessage_sent_date() {
		return message_sent_date;
	}
	public void setMessage_sent_date(String message_sent_date) {
		this.message_sent_date = message_sent_date;
	}
	public String getMessage_sent_by() {
		return message_sent_by;
	}
	public void setMessage_sent_by(String message_sent_by) {
		this.message_sent_by = message_sent_by;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	
	
}
