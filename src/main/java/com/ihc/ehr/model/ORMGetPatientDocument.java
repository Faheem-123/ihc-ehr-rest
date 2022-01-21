package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientDocument {

	@Id
    private String patient_document_id;
    private String patient_id;
    private String document_date;
    private String name;
    private String original_file_name;
    private String link;
    private String category_name;
    private String doc_size;
    private String require_sign_off;
    private String sign_off;
    private String sign_off_by;
    private String sign_off_date;
    private String comment;
    private String doc_categories_id;
    private String doc_type;
    private String doc_type_id;
    private String deleted;
    private String created_user;
    private String client_date_created;
    private String modified_user;
    private String client_date_modified;
    private String date_created;
    private String date_modified;
    private Boolean check_bx;
    
	public String getPatient_document_id() {
		return patient_document_id;
	}
	public void setPatient_document_id(String patient_document_id) {
		this.patient_document_id = patient_document_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
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
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getDoc_size() {
		return doc_size;
	}
	public void setDoc_size(String doc_size) {
		this.doc_size = doc_size;
	}
	public String getRequire_sign_off() {
		return require_sign_off;
	}
	public void setRequire_sign_off(String require_sign_off) {
		this.require_sign_off = require_sign_off;
	}
	public String getSign_off() {
		return sign_off;
	}
	public void setSign_off(String sign_off) {
		this.sign_off = sign_off;
	}
	public String getSign_off_by() {
		return sign_off_by;
	}
	public void setSign_off_by(String sign_off_by) {
		this.sign_off_by = sign_off_by;
	}
	public String getSign_off_date() {
		return sign_off_date;
	}
	public void setSign_off_date(String sign_off_date) {
		this.sign_off_date = sign_off_date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDoc_categories_id() {
		return doc_categories_id;
	}
	public void setDoc_categories_id(String doc_categories_id) {
		this.doc_categories_id = doc_categories_id;
	}
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	public String getDoc_type_id() {
		return doc_type_id;
	}
	public void setDoc_type_id(String doc_type_id) {
		this.doc_type_id = doc_type_id;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
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
	public Boolean getCheck_bx() {
		return check_bx;
	}
	public void setCheck_bx(Boolean check_bx) {
		this.check_bx = check_bx;
	}
	
	
    
}
