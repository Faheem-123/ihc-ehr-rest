package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMgetLabOrderResultsSummary {

	 @Id
	   private String result_id;	
	   private String test_id;
	   private String order_id;
	   private String order_date;
	   private String observation_date;
	   private String result_code;
	   private String result_description;
	   private String test_code;
	   private String test_description;
	   private String result_value;
	   private String recomended_value;
	   private String abnormal_range_code;
	   private String result_status_code;
	   private String lab_name;
	   private String category_name;
	public String getResult_id() {
		return result_id;
	}
	public void setResult_id(String result_id) {
		this.result_id = result_id;
	}
	public String getTest_id() {
		return test_id;
	}
	public void setTest_id(String test_id) {
		this.test_id = test_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getObservation_date() {
		return observation_date;
	}
	public void setObservation_date(String observation_date) {
		this.observation_date = observation_date;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getResult_description() {
		return result_description;
	}
	public void setResult_description(String result_description) {
		this.result_description = result_description;
	}
	public String getTest_code() {
		return test_code;
	}
	public void setTest_code(String test_code) {
		this.test_code = test_code;
	}
	public String getTest_description() {
		return test_description;
	}
	public void setTest_description(String test_description) {
		this.test_description = test_description;
	}
	public String getResult_value() {
		return result_value;
	}
	public void setResult_value(String result_value) {
		this.result_value = result_value;
	}
	public String getRecomended_value() {
		return recomended_value;
	}
	public void setRecomended_value(String recomended_value) {
		this.recomended_value = recomended_value;
	}
	public String getAbnormal_range_code() {
		return abnormal_range_code;
	}
	public void setAbnormal_range_code(String abnormal_range_code) {
		this.abnormal_range_code = abnormal_range_code;
	}
	public String getResult_status_code() {
		return result_status_code;
	}
	public void setResult_status_code(String result_status_code) {
		this.result_status_code = result_status_code;
	}
	public String getLab_name() {
		return lab_name;
	}
	public void setLab_name(String lab_name) {
		this.lab_name = lab_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	   
	   
}
