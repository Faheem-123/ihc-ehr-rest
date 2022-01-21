package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
	

@Entity
public class ORMClaimBatchReport {

	 @Id
	    private String claim_id;
	    private String patient_id;
	    private String pat_name;
	    private String alternate_account;
	    private String dos;
	    private String batch_name;
	    private String pro_name;
	    private String submission_date;
	    private String claim_total;
	    private String pri_paid;
	    private String sec_paid;
	    private String pat_paid;
	    private String amt_due;

	    public String getAmt_due() {
	        return amt_due;
	    }

	    public void setAmt_due(String amt_due) {
	        this.amt_due = amt_due;
	    }

	    public String getPat_paid() {
	        return pat_paid;
	    }

	    public void setPat_paid(String pat_paid) {
	        this.pat_paid = pat_paid;
	    }

	    public String getPri_paid() {
	        return pri_paid;
	    }

	    public void setPri_paid(String pri_paid) {
	        this.pri_paid = pri_paid;
	    }

	    public String getSec_paid() {
	        return sec_paid;
	    }

	    public void setSec_paid(String sec_paid) {
	        this.sec_paid = sec_paid;
	    }
	    
	    

	    public String getClaim_total() {
	        return claim_total;
	    }

	    public void setClaim_total(String claim_total) {
	        this.claim_total = claim_total;
	    }

	    public String getAlternate_account() {
	        return alternate_account;
	    }

	    public void setAlternate_account(String alternate_account) {
	        this.alternate_account = alternate_account;
	    }

	    public String getBatch_name() {
	        return batch_name;
	    }

	    public void setBatch_name(String batch_name) {
	        this.batch_name = batch_name;
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

	    public String getPat_name() {
	        return pat_name;
	    }

	    public void setPat_name(String pat_name) {
	        this.pat_name = pat_name;
	    }

	    public String getPatient_id() {
	        return patient_id;
	    }

	    public void setPatient_id(String patient_id) {
	        this.patient_id = patient_id;
	    }

	    public String getPro_name() {
	        return pro_name;
	    }

	    public void setPro_name(String pro_name) {
	        this.pro_name = pro_name;
	    }

	    public String getSubmission_date() {
	        return submission_date;
	    }

	    public void setSubmission_date(String submission_date) {
	        this.submission_date = submission_date;
	    }
}
