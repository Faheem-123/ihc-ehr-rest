package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientLetters {

	 @Id
	    private String patient_letter_id;
	    private String template_id;
	    private String patient_id;
	    private String patient_name;
	    private String patient_address;
	    private String patient_city_state_zip;
	    private String cell_phone;
	    private String dob;
	    private String letter_name;
	    private String patient_detail;
	    private String dos;
	    private String header_id;
	    private String header_text;
	    private String section_id;
	    private String optional_section_id;
	    private String section_text;
	    private String optional_section_text;
	    private String body_text;
	    private String signed_by_name;
	    private String signed;
	    private String signed_date;
	    private String created_user;
	    private String date_created;
	    private String client_date_created;
	    private String modified_user;
	    private String date_modified;
	    private String client_date_modified;
	    private String signature_path;
	    private String fax_status;
	    
	    
		public String getCell_phone() {
			return cell_phone;
		}
		public void setCell_phone(String cell_phone) {
			this.cell_phone = cell_phone;
		}
		public String getPatient_letter_id() {
			return patient_letter_id;
		}
		public void setPatient_letter_id(String patient_letter_id) {
			this.patient_letter_id = patient_letter_id;
		}
		public String getTemplate_id() {
			return template_id;
		}
		public void setTemplate_id(String template_id) {
			this.template_id = template_id;
		}
		public String getPatient_id() {
			return patient_id;
		}
		public void setPatient_id(String patient_id) {
			this.patient_id = patient_id;
		}
		public String getPatient_name() {
			return patient_name;
		}
		public void setPatient_name(String patient_name) {
			this.patient_name = patient_name;
		}
		public String getPatient_address() {
			return patient_address;
		}
		public void setPatient_address(String patient_address) {
			this.patient_address = patient_address;
		}
		public String getPatient_city_state_zip() {
			return patient_city_state_zip;
		}
		public void setPatient_city_state_zip(String patient_city_state_zip) {
			this.patient_city_state_zip = patient_city_state_zip;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getLetter_name() {
			return letter_name;
		}
		public void setLetter_name(String letter_name) {
			this.letter_name = letter_name;
		}
		public String getPatient_detail() {
			return patient_detail;
		}
		public void setPatient_detail(String patient_detail) {
			this.patient_detail = patient_detail;
		}
		public String getDos() {
			return dos;
		}
		public void setDos(String dos) {
			this.dos = dos;
		}
		public String getHeader_id() {
			return header_id;
		}
		public void setHeader_id(String header_id) {
			this.header_id = header_id;
		}
		public String getHeader_text() {
			return header_text;
		}
		public void setHeader_text(String header_text) {
			this.header_text = header_text;
		}
		public String getSection_id() {
			return section_id;
		}
		public void setSection_id(String section_id) {
			this.section_id = section_id;
		}
		public String getOptional_section_id() {
			return optional_section_id;
		}
		public void setOptional_section_id(String optional_section_id) {
			this.optional_section_id = optional_section_id;
		}
		public String getSection_text() {
			return section_text;
		}
		public void setSection_text(String section_text) {
			this.section_text = section_text;
		}
		public String getOptional_section_text() {
			return optional_section_text;
		}
		public void setOptional_section_text(String optional_section_text) {
			this.optional_section_text = optional_section_text;
		}
		public String getBody_text() {
			return body_text;
		}
		public void setBody_text(String body_text) {
			this.body_text = body_text;
		}
		public String getSigned_by_name() {
			return signed_by_name;
		}
		public void setSigned_by_name(String signed_by_name) {
			this.signed_by_name = signed_by_name;
		}
		public String getSigned() {
			return signed;
		}
		public void setSigned(String signed) {
			this.signed = signed;
		}
		public String getSigned_date() {
			return signed_date;
		}
		public void setSigned_date(String signed_date) {
			this.signed_date = signed_date;
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
		public String getDate_modified() {
			return date_modified;
		}
		public void setDate_modified(String date_modified) {
			this.date_modified = date_modified;
		}
		public String getClient_date_modified() {
			return client_date_modified;
		}
		public void setClient_date_modified(String client_date_modified) {
			this.client_date_modified = client_date_modified;
		}
		public String getSignature_path() {
			return signature_path;
		}
		public void setSignature_path(String signature_path) {
			this.signature_path = signature_path;
		}
		public String getFax_status() {
			return fax_status;
		}
		public void setFax_status(String fax_status) {
			this.fax_status = fax_status;
		}
	    
	    
}
