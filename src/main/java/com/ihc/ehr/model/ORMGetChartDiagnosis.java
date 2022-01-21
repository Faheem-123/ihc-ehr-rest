package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetChartDiagnosis {
	@Id
	private String row_id;
	private String diag_code;
	private String diag_description;
	private String expiry_date;
	private String code_type;
	public String getRow_id() {
		return row_id;
	}
	public void setRow_id(String row_id) {
		this.row_id = row_id;
	}
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
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	
	

}
