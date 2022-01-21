package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

 
@Entity
public class ORMGetCPTsWithBalance {
	 @Id
	    private String claim_procedures_id;
	    private String claim_id;
	    private String dos;
	    private String proc_code;
	    private String cpt_charges;
	    private String patient_paid;
	    private String cpt_balance;
	    private String refund_amount;
	    private String paid_amount;
	    private String write_off;
	    private String prev_paid_amount;
	    private String prev_write_off;
	    
	   
	    public String getClaim_procedures_id() {
	        return claim_procedures_id;
	    }

	    public void setClaim_procedures_id(String claim_procedures_id) {
	        this.claim_procedures_id = claim_procedures_id;
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

	    public String getCpt_charges() {
	        return cpt_charges;
	    }

	    public void setCpt_charges(String cpt_charges) {
	        this.cpt_charges = cpt_charges;
	    }

	    

	    public String getPatient_paid() {
	        return patient_paid;
	    }

	    public void setPatient_paid(String patient_paid) {
	        this.patient_paid = patient_paid;
	    }

	    public String getCpt_balance() {
	        return cpt_balance;
	    }

	    public void setCpt_balance(String cpt_balance) {
	        this.cpt_balance = cpt_balance;
	    }

	    public String getRefund_amount() {
	        return refund_amount;
	    }

	    public String getPaid_amount() {
	        return paid_amount;
	    }

	    public void setPaid_amount(String paid_amount) {
	        this.paid_amount = paid_amount;
	    }

	    public String getWrite_off() {
	        return write_off;
	    }

	    public void setWrite_off(String write_off) {
	        this.write_off = write_off;
	    }

	    public String getPrev_paid_amount() {
	        return prev_paid_amount;
	    }

	    public void setPrev_paid_amount(String prev_paid_amount) {
	        this.prev_paid_amount = prev_paid_amount;
	    }
	    
	    

	    public String getPrev_write_off() {
	        return prev_write_off;
	    }

	    public void setPrev_write_off(String prev_write_off) {
	        this.prev_write_off = prev_write_off;
	    }
	    
}
