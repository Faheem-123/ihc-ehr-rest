package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "icd_10")
public class ORMDiagnosisSetup {

	@Id
    private String code;
    private String description;
    private String long_description;
    private String deleted;
    private String gender;
    private String condition;
    private String expiry_date;
    
    private String created_user;
    private String date_created;
    private String client_date_created;
    private String modified_user;
    private String date_modified;
    private String client_date_modified;
    private String age;
    private String age_max;

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
    


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
}
