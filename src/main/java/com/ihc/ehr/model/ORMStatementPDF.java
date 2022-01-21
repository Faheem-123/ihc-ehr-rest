package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
 
@Entity
public class ORMStatementPDF {

	 @Id
     private  String claim_id;
private  String patient_id;
private  String prac_address;
private  String prac_citystatezip;
private  String prac_name;
private  String prac_phone;

private  String pat_last_name;
private  String pat_first_name;
private  String pat_address;
private  String pat_city;
private  String pat_state;
private  String pat_zip;
private  String alternate_account;

private  String claim_dos;
private  String claim_atten_pro_name;
private  String claim_proc_code;
private  String claim_total_charges;
private  String claim_total;
private  String claim_amt_paid;
private  String claim_adjust_amount;
private  String claim_amt_due;
private  String statement_message;
private  String practice_id;



public String getPractice_id() {
	return practice_id;
}

public void setPractice_id(String practice_id) {
	this.practice_id = practice_id;
}

public String getStatement_message() {
return statement_message;
}

public void setStatement_message(String statement_message) {
this.statement_message = statement_message;
}
     

public String getClaim_id() {
return claim_id;
}

public void setClaim_id(String claim_id) {
this.claim_id = claim_id;
}

public String getPatient_id() {
return patient_id;
}

public void setPatient_id(String patient_id) {
this.patient_id = patient_id;
}

public String getPrac_address() {
return prac_address;
}

public void setPrac_address(String prac_address) {
this.prac_address = prac_address;
}

public String getPrac_citystatezip() {
return prac_citystatezip;
}

public void setPrac_citystatezip(String prac_citystatezip) {
this.prac_citystatezip = prac_citystatezip;
}

public String getPrac_name() {
return prac_name;
}

public void setPrac_name(String prac_name) {
this.prac_name = prac_name;
}

public String getPrac_phone() {
return prac_phone;
}

public void setPrac_phone(String prac_phone) {
this.prac_phone = prac_phone;
}

public String getPat_last_name() {
return pat_last_name;
}

public void setPat_last_name(String pat_last_name) {
this.pat_last_name = pat_last_name;
}

public String getPat_first_name() {
return pat_first_name;
}

public void setPat_first_name(String pat_first_name) {
this.pat_first_name = pat_first_name;
}

public String getPat_address() {
return pat_address;
}

public void setPat_address(String pat_address) {
this.pat_address = pat_address;
}

public String getPat_city() {
return pat_city;
}

public void setPat_city(String pat_city) {
this.pat_city = pat_city;
}

public String getPat_state() {
return pat_state;
}

public void setPat_state(String pat_state) {
this.pat_state = pat_state;
}

public String getPat_zip() {
return pat_zip;
}

public void setPat_zip(String pat_zip) {
this.pat_zip = pat_zip;
}

public String getAlternate_account() {
return alternate_account;
}

public void setAlternate_account(String alternate_account) {
this.alternate_account = alternate_account;
}

public String getClaim_dos() {
return claim_dos;
}

public void setClaim_dos(String claim_dos) {
this.claim_dos = claim_dos;
}

public String getClaim_atten_pro_name() {
return claim_atten_pro_name;
}

public void setClaim_atten_pro_name(String claim_atten_pro_name) {
this.claim_atten_pro_name = claim_atten_pro_name;
}

public String getClaim_proc_code() {
return claim_proc_code;
}

public void setClaim_proc_code(String claim_proc_code) {
this.claim_proc_code = claim_proc_code;
}

public String getClaim_total_charges() {
return claim_total_charges;
}

public void setClaim_total_charges(String claim_total_charges) {
this.claim_total_charges = claim_total_charges;
}

public String getClaim_total() {
return claim_total;
}

public void setClaim_total(String claim_total) {
this.claim_total = claim_total;
}

public String getClaim_amt_paid() {
return claim_amt_paid;
}

public void setClaim_amt_paid(String claim_amt_paid) {
this.claim_amt_paid = claim_amt_paid;
}

public String getClaim_adjust_amount() {
return claim_adjust_amount;
}

public void setClaim_adjust_amount(String claim_adjust_amount) {
this.claim_adjust_amount = claim_adjust_amount;
}

public String getClaim_amt_due() {
return claim_amt_due;
}

public void setClaim_amt_due(String claim_amt_due) {
this.claim_amt_due = claim_amt_due;
}
}
