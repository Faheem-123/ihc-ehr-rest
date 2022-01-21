package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPayRollCategoryProcedures {
	@Id
    private String category_procedure_id;
    private String category_id;
    private String proc_code;
    private String created_user;
    private String date_created;
    private String client_date_created;
    private String proc_description;
	public String getCategory_procedure_id() {
		return category_procedure_id;
	}
	public void setCategory_procedure_id(String category_procedure_id) {
		this.category_procedure_id = category_procedure_id;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getProc_code() {
		return proc_code;
	}
	public void setProc_code(String proc_code) {
		this.proc_code = proc_code;
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
	public String getProc_description() {
		return proc_description;
	}
	public void setProc_description(String proc_description) {
		this.proc_description = proc_description;
	}
}