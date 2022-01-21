package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SuperBill_CategoryDetail")
public class ORMChartOfficetestCpt {

	@Id
    private String detail_id;
    private String code1;
    private String unit1;
    private String default_amt1;
    private String default_modifier1;
    private String default_ndc1;
    private String description1;
	public String getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}
	public String getCode1() {
		return code1;
	}
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	public String getUnit1() {
		return unit1;
	}
	public void setUnit1(String unit1) {
		this.unit1 = unit1;
	}
	public String getDefault_amt1() {
		return default_amt1;
	}
	public void setDefault_amt1(String default_amt1) {
		this.default_amt1 = default_amt1;
	}
	public String getDefault_modifier1() {
		return default_modifier1;
	}
	public void setDefault_modifier1(String default_modifier1) {
		this.default_modifier1 = default_modifier1;
	}
	public String getDefault_ndc1() {
		return default_ndc1;
	}
	public void setDefault_ndc1(String default_ndc1) {
		this.default_ndc1 = default_ndc1;
	}
	public String getDescription1() {
		return description1;
	}
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
}
