package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetProviderPayers {
	@Id
    private String provider_payer_id;
    private String payer_id;
    private String billing_provider_id;
    private String provider_modifier_code;
    private String modifier_description;
    private String provider_number;
    //private String group_number;    
    private String date_created;
    private String created_user;
    private String date_modified;
    private String modified_user;
    private String client_date_created;
    private String client_date_modified;
    private String payer_name;
    private String provider_identification_number_type;
    private String provider_identification_number;
    private String box_33_type;
    private String effective_date;
    private String validation_date;
    private String validation_expiry_date;

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

//    public String getModifier_description() {
//        return modifier_description;
//    }
//
//    public void setModifier_description(String modifier_description) {
//        this.modifier_description = modifier_description;
//    }
//    
//    
//
//    public String getGroup_number() {
//        return group_number;
//    }
//
//    public void setGroup_number(String group_number) {
//        this.group_number = group_number;
//    }

    public String getModified_user() {
        return modified_user;
    }

    public void setModified_user(String modified_user) {
        this.modified_user = modified_user;
    }

    public String getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(String payer_id) {
        this.payer_id = payer_id;
    }

    public String getPayer_name() {
        return payer_name;
    }

    public void setPayer_name(String payer_name) {
        this.payer_name = payer_name;
    }

    public String getBilling_provider_id() {
        return billing_provider_id;
    }

    public void setBilling_provider_id(String billing_provider_id) {
        this.billing_provider_id = billing_provider_id;
    }

   

//    public String getProvider_modifier_code() {
//        return provider_modifier_code;
//    }
//
//    public void setProvider_modifier_code(String provider_modifier_code) {
//        this.provider_modifier_code = provider_modifier_code;
//    }
//
//    public String getProvider_number() {
//        return provider_number;
//    }
//
//    public void setProvider_number(String provider_number) {
//        this.provider_number = provider_number;
//    }

    public String getProvider_payer_id() {
        return provider_payer_id;
    }

    public void setProvider_payer_id(String provider_payer_id) {
        this.provider_payer_id = provider_payer_id;
    }

    public String getProvider_identification_number_type() {
        return provider_identification_number_type;
    }

    public void setProvider_identification_number_type(String provider_identification_number_type) {
        this.provider_identification_number_type = provider_identification_number_type;
    }

    public String getProvider_identification_number() {
        return provider_identification_number;
    }

    public void setProvider_identification_number(String provider_identification_number) {
        this.provider_identification_number = provider_identification_number;
    }

    public String getBox_33_type() {
        return box_33_type;
    }

    public void setBox_33_type(String box_33_type) {
        this.box_33_type = box_33_type;
    }

    public String getProvider_modifier_code() {
        return provider_modifier_code;
    }

    public void setProvider_modifier_code(String provider_modifier_code) {
        this.provider_modifier_code = provider_modifier_code;
    }

    public String getModifier_description() {
        return modifier_description;
    }

    public void setModifier_description(String modifier_description) {
        this.modifier_description = modifier_description;
    }

    public String getProvider_number() {
        return provider_number;
    }

    public void setProvider_number(String provider_number) {
        this.provider_number = provider_number;
    }

    public String getEffective_date() {
        return effective_date;
    }

    public void setEffective_date(String effective_date) {
        this.effective_date = effective_date;
    }

    public String getValidation_date() {
        return validation_date;
    }

    public void setValidation_date(String validation_date) {
        this.validation_date = validation_date;
    }

    public String getValidation_expiry_date() {
        return validation_expiry_date;
    }

    public void setValidation_expiry_date(String validation_expiry_date) {
        this.validation_expiry_date = validation_expiry_date;
    }
}
