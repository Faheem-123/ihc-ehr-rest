package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMBillingTrackSheet {

	 @Id
	    private String s_no	;
	    private String claim_id	;
	    private String patient_id	;
	    private String alternate_account	;
	    private String dos	;
	    private String patient_name	;
	    private String dob	;
	    private String pri_status	;
	    private String sec_status	;
	    private String oth_status	;
	    private String pat_status	;
	    private String last_claim_note_date	;
	    private String coding_done_date	;
	    private String batch_generated_date;	
	    private String primary_insurance	;
	    private String secondary_insurance	;
	    private String other_insurance	;
	    private String elig_status_primary	;
	    private String elig_status_secondary;	
	    private String pri_paid	;
	    private String sec_paid	;
	    private String oth_paid	;
	    private String patient_paid	;
	    private String write_off	;
	    private String discount	;
	    private String risk_amount	;
	    private String adjust_amount;	
	    private String claim_total	;
	    private String amt_due	;
	    private String patient_statement_dates	;
	    private String aging_30	;
	    private String aging_60	;
	    private String aging_90	;
	    private String aging_120	;
	    private String aging_120_plus;
	    private String attending_physician;
	    private String location_name;
	    
	    private String no_of_days_pri;
	    private String no_of_days_sec;
	    private String no_of_days_oth;
	    private String submission_date;

	    public String getSubmission_date() {
	        return submission_date;
	    }

	    public void setSubmission_date(String submission_date) {
	        this.submission_date = submission_date;
	    }
	    

	    public String getAlternate_account() {
	        return alternate_account;
	    }

	    public void setAlternate_account(String alternate_account) {
	        this.alternate_account = alternate_account;
	    }

	    
	    
	    public String getLocation_name() {
	        return location_name;
	    }

	    public void setLocation_name(String location_name) {
	        this.location_name = location_name;
	    }
	    

	    public String getPatient_id() {
	        return patient_id;
	    }

	    public void setPatient_id(String patient_id) {
	        this.patient_id = patient_id;
	    }
	    
	    public String getAttending_physician() {
	        return attending_physician;
	    }

	    public void setAttending_physician(String attending_physician) {
	        this.attending_physician = attending_physician;
	    }
	    
	    public String getAdjust_amount() {
	        return adjust_amount;
	    }

	    public void setAdjust_amount(String adjust_amount) {
	        this.adjust_amount = adjust_amount;
	    }

	    public String getAging_120() {
	        return aging_120;
	    }

	    public void setAging_120(String aging_120) {
	        this.aging_120 = aging_120;
	    }

	    public String getAging_120_plus() {
	        return aging_120_plus;
	    }

	    public void setAging_120_plus(String aging_120_plus) {
	        this.aging_120_plus = aging_120_plus;
	    }

	    public String getAging_30() {
	        return aging_30;
	    }

	    public void setAging_30(String aging_30) {
	        this.aging_30 = aging_30;
	    }

	    public String getAging_60() {
	        return aging_60;
	    }

	    public void setAging_60(String aging_60) {
	        this.aging_60 = aging_60;
	    }

	    public String getAging_90() {
	        return aging_90;
	    }

	    public void setAging_90(String aging_90) {
	        this.aging_90 = aging_90;
	    }

	    public String getAmt_due() {
	        return amt_due;
	    }

	    public void setAmt_due(String amt_due) {
	        this.amt_due = amt_due;
	    }

	    public String getBatch_generated_date() {
	        return batch_generated_date;
	    }

	    public void setBatch_generated_date(String batch_generated_date) {
	        this.batch_generated_date = batch_generated_date;
	    }

	    public String getClaim_id() {
	        return claim_id;
	    }

	    public void setClaim_id(String claim_id) {
	        this.claim_id = claim_id;
	    }

	    public String getClaim_total() {
	        return claim_total;
	    }

	    public void setClaim_total(String claim_total) {
	        this.claim_total = claim_total;
	    }

	    public String getCoding_done_date() {
	        return coding_done_date;
	    }

	    public void setCoding_done_date(String coding_done_date) {
	        this.coding_done_date = coding_done_date;
	    }

	    public String getDiscount() {
	        return discount;
	    }

	    public void setDiscount(String discount) {
	        this.discount = discount;
	    }

	    public String getDos() {
	        return dos;
	    }

	    public void setDos(String dos) {
	        this.dos = dos;
	    }

	    public String getElig_status_primary() {
	        return elig_status_primary;
	    }

	    public void setElig_status_primary(String elig_status_primary) {
	        this.elig_status_primary = elig_status_primary;
	    }

	    public String getElig_status_secondary() {
	        return elig_status_secondary;
	    }

	    public void setElig_status_secondary(String elig_status_secondary) {
	        this.elig_status_secondary = elig_status_secondary;
	    }

	    public String getLast_claim_note_date() {
	        return last_claim_note_date;
	    }

	    public void setLast_claim_note_date(String last_claim_note_date) {
	        this.last_claim_note_date = last_claim_note_date;
	    }

	    public String getOth_paid() {
	        return oth_paid;
	    }

	    public void setOth_paid(String oth_paid) {
	        this.oth_paid = oth_paid;
	    }

	    public String getOth_status() {
	        return oth_status;
	    }

	    public void setOth_status(String oth_status) {
	        this.oth_status = oth_status;
	    }

	    public String getOther_insurance() {
	        return other_insurance;
	    }

	    public void setOther_insurance(String other_insurance) {
	        this.other_insurance = other_insurance;
	    }

	    public String getPat_status() {
	        return pat_status;
	    }

	    public void setPat_status(String pat_status) {
	        this.pat_status = pat_status;
	    }

	    public String getPatient_name() {
	        return patient_name;
	    }

	    public void setPatient_name(String patient_name) {
	        this.patient_name = patient_name;
	    }

	    public String getPatient_paid() {
	        return patient_paid;
	    }

	    public void setPatient_paid(String patient_paid) {
	        this.patient_paid = patient_paid;
	    }

	    public String getPatient_statement_dates() {
	        return patient_statement_dates;
	    }

	    public void setPatient_statement_dates(String patient_statement_dates) {
	        this.patient_statement_dates = patient_statement_dates;
	    }

	    public String getPri_paid() {
	        return pri_paid;
	    }

	    public void setPri_paid(String pri_paid) {
	        this.pri_paid = pri_paid;
	    }

	    public String getPri_status() {
	        return pri_status;
	    }

	    public void setPri_status(String pri_status) {
	        this.pri_status = pri_status;
	    }

	    public String getPrimary_insurance() {
	        return primary_insurance;
	    }

	    public void setPrimary_insurance(String primary_insurance) {
	        this.primary_insurance = primary_insurance;
	    }

	    public String getRisk_amount() {
	        return risk_amount;
	    }

	    public void setRisk_amount(String risk_amount) {
	        this.risk_amount = risk_amount;
	    }

	    public String getS_no() {
	        return s_no;
	    }

	    public void setS_no(String s_no) {
	        this.s_no = s_no;
	    }

	    public String getSec_paid() {
	        return sec_paid;
	    }

	    public void setSec_paid(String sec_paid) {
	        this.sec_paid = sec_paid;
	    }

	    public String getSec_status() {
	        return sec_status;
	    }

	    public void setSec_status(String sec_status) {
	        this.sec_status = sec_status;
	    }

	    public String getSecondary_insurance() {
	        return secondary_insurance;
	    }

	    public void setSecondary_insurance(String secondary_insurance) {
	        this.secondary_insurance = secondary_insurance;
	    }

	    public String getWrite_off() {
	        return write_off;
	    }

	    public void setWrite_off(String write_off) {
	        this.write_off = write_off;
	    }

	    public String getDob() {
	        return dob;
	    }

	    public void setDob(String dob) {
	        this.dob = dob;
	    }

	    public String getNo_of_days_pri() {
	        return no_of_days_pri;
	    }

	    public void setNo_of_days_pri(String no_of_days_pri) {
	        this.no_of_days_pri = no_of_days_pri;
	    }

	    public String getNo_of_days_sec() {
	        return no_of_days_sec;
	    }

	    public void setNo_of_days_sec(String no_of_days_sec) {
	        this.no_of_days_sec = no_of_days_sec;
	    }

	    public String getNo_of_days_oth() {
	        return no_of_days_oth;
	    }

	    public void setNo_of_days_oth(String no_of_days_oth) {
	        this.no_of_days_oth = no_of_days_oth;
	    }
	    
}
