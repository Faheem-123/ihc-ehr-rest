package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "procedure_code")
public class ORMProcedureSetup {

	  @Id
	    private String proc_code;
	    private String description;
	    private String default_charge;
	    private String default_modifier;
	    private String pos;
	    private String long_description;
	    private String gender_applied;
	    private String expiry_date;
	    private Boolean deleted;
	    private String created_user;
	    private String date_created;
	    private String client_date_created;
	    private String modified_user;
	    private String date_modified;
	    private String client_date_modified;
	    
	    private String age;
	    private String age_max;
	    private String condation;
	    
	    public String getCondation() {
	        return condation;
	    }

	    public void setCondation(String condation) {
	        this.condation = condation;
	    }
	    

	    public String getAge() {
	        return age;
	    }

	    public void setAge(String age) {
	        this.age = age;
	    }

	    public String getAge_max() {
	        return age_max;
	    }

	    public void setAge_max(String age_max) {
	        this.age_max = age_max;
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

	    public String getDate_modified() {
	        return date_modified;
	    }

	    public void setDate_modified(String date_modified) {
	        this.date_modified = date_modified;
	    }

	    

	    public String getDefault_charge() {
	        return default_charge;
	    }

	    public void setDefault_charge(String default_charge) {
	        this.default_charge = default_charge;
	    }

	    public String getDefault_modifier() {
	        return default_modifier;
	    }

	    public void setDefault_modifier(String default_modifier) {
	        this.default_modifier = default_modifier;
	    }

	    public Boolean getDeleted() {
	        return deleted;
	    }

	    public void setDeleted(Boolean deleted) {
	        this.deleted = deleted;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getExpiry_date() {
	        return expiry_date;
	    }

	    public void setExpiry_date(String expiry_date) {
	        this.expiry_date = expiry_date;
	    }

	    public String getGender_applied() {
	        return gender_applied;
	    }

	    public void setGender_applied(String gender_applied) {
	        this.gender_applied = gender_applied;
	    }

	    public String getLong_description() {
	        return long_description;
	    }

	    public void setLong_description(String long_description) {
	        this.long_description = long_description;
	    }

	    public String getModified_user() {
	        return modified_user;
	    }

	    public void setModified_user(String modified_user) {
	        this.modified_user = modified_user;
	    }

	    public String getPos() {
	        return pos;
	    }

	    public void setPos(String pos) {
	        this.pos = pos;
	    }

	    public String getProc_code() {
	        return proc_code;
	    }

	    public void setProc_code(String proc_code) {
	        this.proc_code = proc_code;
	    }
}
