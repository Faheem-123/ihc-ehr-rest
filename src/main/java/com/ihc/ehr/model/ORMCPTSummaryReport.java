package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMCPTSummaryReport {
	@Id
    private String proc_code;	
    private String description;
    private String proc_count;
    private String total_charges;
    private String paid_amount;
    
	public String getProc_code() {
		return proc_code;
	}
	public void setProc_code(String proc_code) {
		this.proc_code = proc_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProc_count() {
		return proc_count;
	}
	public void setProc_count(String proc_count) {
		this.proc_count = proc_count;
	}
	public String getTotal_charges() {
		return total_charges;
	}
	public void setTotal_charges(String total_charges) {
		this.total_charges = total_charges;
	}
	public String getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(String paid_amount) {
		this.paid_amount = paid_amount;
	}
}
