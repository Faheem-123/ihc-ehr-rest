package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document_categories")
public class ORMDocCategories {
	@Id
    private String document_categories_id;
    private String category_name;
    private String parent_category;
    private String practice_id;
    private String category_order;
    private Boolean is_default_category;
    private String deleted;
    private String date_created;
    private String created_user;
    private String date_modified;
    private String modified_user;
    private String client_date_created;
    private String client_date_modified;
	public String getDocument_categories_id() {
		return document_categories_id;
	}
	public void setDocument_categories_id(String document_categories_id) {
		this.document_categories_id = document_categories_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getParent_category() {
		return parent_category;
	}
	public void setParent_category(String parent_category) {
		this.parent_category = parent_category;
	}
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public String getCategory_order() {
		return category_order;
	}
	public void setCategory_order(String category_order) {
		this.category_order = category_order;
	}
	public Boolean getIs_default_category() {
		return is_default_category;
	}
	public void setIs_default_category(Boolean is_default_category) {
		this.is_default_category = is_default_category;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
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
    
	@Override
	public String toString() {
		return "ORMDocCategories [document_categories_id=" + document_categories_id + ", category_name=" + category_name
				+ ", parent_category=" + parent_category + ", practice_id=" + practice_id + ", category_order="
				+ category_order + ", is_default_category=" + is_default_category + ", deleted=" + deleted
				+ ", date_created=" + date_created + ", created_user=" + created_user + ", date_modified="
				+ date_modified + ", modified_user=" + modified_user + ", client_date_created=" + client_date_created
				+ ", client_date_modified=" + client_date_modified + "]";
	}    
}
