package com.ihc.ehr.model;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ORMGetEOB {

	@Id
    private String eob_id;        
    private String eob_date;
    private String check_date;
    private String check_number;
    private String check_amount;
    private String payer_id;
    private String practice_id;
    private String system_ip;
    private Boolean is_posted;
    private Boolean deleted;
    private String created_user;
    private String client_date_created;
    private String modified_user;
    private String client_date_modified;
    private String date_created;
    private String date_modified;
    private String payer_number;
    private String payer_name;
    private String payment_type;
    private String patient_name;
    private String payment_source;
    private String patient_id;
    private String attorney_id;
    private String attorney_firm_name;

    public String getAttorney_id() {
        return attorney_id;
    }

    public void setAttorney_id(String attorney_id) {
        this.attorney_id = attorney_id;
    }
    
    public String getEob_id() {
        return eob_id;
    }

    public void setEob_id(String eob_id) {
        this.eob_id = eob_id;
    }

    public String getEob_date() {
        return eob_date;
    }

    public void setEob_date(String eob_date) {
        this.eob_date = eob_date;
    }

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public String getCheck_number() {
        return check_number;
    }

    public void setCheck_number(String check_number) {
        this.check_number = check_number;
    }

    public String getCheck_amount() {
        return check_amount;
    }

    public void setCheck_amount(String check_amount) {
        this.check_amount = check_amount;
    }

    public String getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(String payer_id) {
        this.payer_id = payer_id;
    }

    public String getPractice_id() {
        return practice_id;
    }

    public void setPractice_id(String practice_id) {
        this.practice_id = practice_id;
    }

    public String getSystem_ip() {
        return system_ip;
    }

    public void setSystem_ip(String system_ip) {
        this.system_ip = system_ip;
    }

    public Boolean getIs_posted() {
        return is_posted;
    }

    public void setIs_posted(Boolean is_posted) {
        this.is_posted = is_posted;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
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

    public String getPayer_number() {
        return payer_number;
    }

    public void setPayer_number(String payer_number) {
        this.payer_number = payer_number;
    }

    public String getPayer_name() {
        return payer_name;
    }

    public void setPayer_name(String payer_name) {
        this.payer_name = payer_name;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPayment_source() {
        return payment_source;
    }

    public void setPayment_source(String payment_source) {
        this.payment_source = payment_source;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getAttorney_firm_name() {
        return attorney_firm_name;
    }

    public void setAttorney_firm_name(String attorney_firm_name) {
        this.attorney_firm_name = attorney_firm_name;
    }
}
