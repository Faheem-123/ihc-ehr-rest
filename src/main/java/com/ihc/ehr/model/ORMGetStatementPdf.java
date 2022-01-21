package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetStatementPdf {

	 @Id
     private String row_id;
     private String claim_id;
     private String dos;
     private String proc_code;
     private String total_charges;
     private String claim_total;
     private String amt_due;
     private String amt_paid;
     private String adjust_amount;
     private String atten_pro_name;
     
     private String patient_id;
     private String last_name;
     private String first_name;
     private String address;
     private String zip;
     private String state;
     private String city;
     private String alternate_account;
    
     
     private int statement_count;

     
    

	public int getStatement_count() {
        return statement_count;
    }

    public void setStatement_count(int statement_count) {
        this.statement_count = statement_count;
    }
     
     

    public String getRow_id() {
        return row_id;
    }

    public void setRow_id(String row_id) {
        this.row_id = row_id;
    }

    public String getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(String claim_id) {
        this.claim_id = claim_id;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getProc_code() {
        return proc_code;
    }

    public void setProc_code(String proc_code) {
        this.proc_code = proc_code;
    }

    public String getTotal_charges() {
        return total_charges;
    }

    public void setTotal_charges(String total_charges) {
        this.total_charges = total_charges;
    }

    public String getClaim_total() {
        return claim_total;
    }

    public void setClaim_total(String claim_total) {
        this.claim_total = claim_total;
    }

    public String getAmt_due() {
        return amt_due;
    }

    public void setAmt_due(String amt_due) {
        this.amt_due = amt_due;
    }

    public String getAmt_paid() {
        return amt_paid;
    }

    public void setAmt_paid(String amt_paid) {
        this.amt_paid = amt_paid;
    }

    public String getAdjust_amount() {
        return adjust_amount;
    }

    public void setAdjust_amount(String adjust_amount) {
        this.adjust_amount = adjust_amount;
    }

    public String getAtten_pro_name() {
        return atten_pro_name;
    }

    public void setAtten_pro_name(String atten_pro_name) {
        this.atten_pro_name = atten_pro_name;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAlternate_account() {
        return alternate_account;
    }

    public void setAlternate_account(String alternate_account) {
        this.alternate_account = alternate_account;
    }
     
     
}
