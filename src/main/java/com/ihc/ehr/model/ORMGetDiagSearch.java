package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetDiagSearch {

	@Id
	private String diag_code;
	private String diag_description;
	private String condition_gender;
	private String condition_age;
	private Boolean is_expired;
	private Boolean hcc_flag;
	private String code_type;
	
	public String getDiag_code() {
		return diag_code;
	}

	public void setDiag_code(String diag_code) {
		this.diag_code = diag_code;
	}

	public String getDiag_description() {
		return diag_description;
	}

	public void setDiag_description(String diag_description) {
		this.diag_description = diag_description;
	}

	public String getCondition_gender() {
		return condition_gender;
	}

	public void setCondition_gender(String condition_gender) {
		this.condition_gender = condition_gender;
	}

	public String getCondition_age() {
		return condition_age;
	}

	public void setCondition_age(String condition_age) {
		this.condition_age = condition_age;
	}

	public Boolean getIs_expired() {
		return is_expired;
	}

	public void setIs_expired(Boolean is_expired) {
		this.is_expired = is_expired;
	}

	public Boolean getHcc_flag() {
		return hcc_flag;
	}

	public void setHcc_flag(Boolean hcc_flag) {
		this.hcc_flag = hcc_flag;
	}

	public String getCode_type() {
		return code_type;
	}

	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	
	

}
