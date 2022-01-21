package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetProviderParollReport {
	@Id
	private String s_no;
    private String cat_sub_sno;
    private String provider_name;
    private String category_name;
    private String proc_code;
    private String proc_description;    
    private String patient_count;
    private String visit_count;
    private String proc_count;    
    private String proc_charges;
    private String proc_payment;
    private Boolean is_aggregate;
    private String provider_actual_visits;
    
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
	public String getCat_sub_sno() {
		return cat_sub_sno;
	}
	public void setCat_sub_sno(String cat_sub_sno) {
		this.cat_sub_sno = cat_sub_sno;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getProc_code() {
		return proc_code;
	}
	public void setProc_code(String proc_code) {
		this.proc_code = proc_code;
	}
	public String getProc_description() {
		return proc_description;
	}
	public void setProc_description(String proc_description) {
		this.proc_description = proc_description;
	}
	public String getPatient_count() {
		return patient_count;
	}
	public void setPatient_count(String patient_count) {
		this.patient_count = patient_count;
	}
	public String getVisit_count() {
		return visit_count;
	}
	public void setVisit_count(String visit_count) {
		this.visit_count = visit_count;
	}
	public String getProc_count() {
		return proc_count;
	}
	public void setProc_count(String proc_count) {
		this.proc_count = proc_count;
	}
	public String getProc_charges() {
		return proc_charges;
	}
	public void setProc_charges(String proc_charges) {
		this.proc_charges = proc_charges;
	}
	public String getProc_payment() {
		return proc_payment;
	}
	public void setProc_payment(String proc_payment) {
		this.proc_payment = proc_payment;
	}
	public Boolean getIs_aggregate() {
		return is_aggregate;
	}
	public void setIs_aggregate(Boolean is_aggregate) {
		this.is_aggregate = is_aggregate;
	}
	public String getProvider_actual_visits() {
		return provider_actual_visits;
	}
	public void setProvider_actual_visits(String provider_actual_visits) {
		this.provider_actual_visits = provider_actual_visits;
	}
}
