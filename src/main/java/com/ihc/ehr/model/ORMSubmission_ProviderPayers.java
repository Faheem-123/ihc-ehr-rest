package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMSubmission_ProviderPayers  {

    @Id
    private String provider_payer_id;
    private String payer_id;
    private String practice_id;
    private String billing_provider_id;
    //private String provider_modifier_code;
    //private String provider_number;
    //private String group_number;
    private String provider_identification_number;
    private String provider_identification_number_type;
    private String box_33_type;
    private String validation_expiry_date;    

//    public String getGroup_number() {
//        return group_number;
//    }
//
//    public void setGroup_number(String group_number) {
//        this.group_number = group_number;
//    }

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

    public String getProvider_identification_number() {
        return provider_identification_number;
    }

    public void setProvider_identification_number(String provider_identification_number) {
        this.provider_identification_number = provider_identification_number;
    }

    public String getProvider_identification_number_type() {
        return provider_identification_number_type;
    }

    public void setProvider_identification_number_type(String provider_identification_number_type) {
        this.provider_identification_number_type = provider_identification_number_type;
    }

    public String getBox_33_type() {
        return box_33_type;
    }

    public void setBox_33_type(String box_33_type) {
        this.box_33_type = box_33_type;
    }

    public String getValidation_expiry_date() {
        return validation_expiry_date;
    }

    public void setValidation_expiry_date(String validation_expiry_date) {
        this.validation_expiry_date = validation_expiry_date;
    }
    
    
}