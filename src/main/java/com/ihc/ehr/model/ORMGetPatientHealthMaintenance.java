package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientHealthMaintenance {
    @Id
    private String detail_id;
    private String phm_id;
    private String visit_s_no;
    private String categroy_s_no;
    private String visit_date;
    private String provider_name;
    private String location_name;
    private String test_category;
    private String test_name;
    private String test_date;
    private String test_value;
    private String due_date;
    private String refusal;
    
	public String getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}
	public String getPhm_id() {
		return phm_id;
	}
	public void setPhm_id(String phm_id) {
		this.phm_id = phm_id;
	}
	public String getVisit_s_no() {
		return visit_s_no;
	}
	public void setVisit_s_no(String visit_s_no) {
		this.visit_s_no = visit_s_no;
	}
	public String getCategroy_s_no() {
		return categroy_s_no;
	}
	public void setCategroy_s_no(String categroy_s_no) {
		this.categroy_s_no = categroy_s_no;
	}
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getTest_category() {
		return test_category;
	}
	public void setTest_category(String test_category) {
		this.test_category = test_category;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public String getTest_date() {
		return test_date;
	}
	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}
	public String getTest_value() {
		return test_value;
	}
	public void setTest_value(String test_value) {
		this.test_value = test_value;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getRefusal() {
		return refusal;
	}
	public void setRefusal(String refusal) {
		this.refusal = refusal;
	}
}
