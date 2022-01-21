package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetDepositReportChecksSummary {

	  @Id
	    private String s_no;
	    private String eob_era_id;
	    private String eob_era_id_type;
	    private String check_date;
	    private String check_number;
	    private String check_amount;
	    private String payment_type;
	    private String payment_source;
	    private String total_posted_amount;
	    private String patient_id;
	    private String date_created;
	    

	    public String getS_no() {
	        return s_no;
	    }

	    public void setS_no(String s_no) {
	        this.s_no = s_no;
	    }

	    public String getEob_era_id() {
	        return eob_era_id;
	    }

	    public void setEob_era_id(String eob_era_id) {
	        this.eob_era_id = eob_era_id;
	    }

	    public String getEob_era_id_type() {
	        return eob_era_id_type;
	    }

	    public void setEob_era_id_type(String eob_era_id_type) {
	        this.eob_era_id_type = eob_era_id_type;
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

	    public String getPayment_type() {
	        return payment_type;
	    }

	    public void setPayment_type(String payment_type) {
	        this.payment_type = payment_type;
	    }

	    public String getPayment_source() {
	        return payment_source;
	    }

	    public void setPayment_source(String payment_source) {
	        this.payment_source = payment_source;
	    }

	    public String getTotal_posted_amount() {
	        return total_posted_amount;
	    }

	    public void setTotal_posted_amount(String total_posted_amount) {
	        this.total_posted_amount = total_posted_amount;
	    }
	   
	    public String getPatient_id() {
	        return patient_id;
	    }

	    public void setPatient_id(String patient_id) {
	        this.patient_id = patient_id;
	    }

	    public String getDate_created() {
	        return date_created;
	    }

	    public void setDate_created(String date_created) {
	        this.date_created = date_created;
	    }
}
