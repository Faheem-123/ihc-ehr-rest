package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMPayerWiseAgingSummary {

	 @Id
	 private String s_no;
	 private String pri_payer_type;
	 private String pri_payer_name;
	 private String pri_payer_number;
	 private String primary_insurance;
	 private String aging_30;
	 private String aging_60;
	 private String aging_90;
	 private String aging_120;
	 private String aging_120_plus;
	 
	 
	public String getPri_payer_type() {
		return pri_payer_type;
	}
	public void setPri_payer_type(String pri_payer_type) {
		this.pri_payer_type = pri_payer_type;
	}
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
	public String getPri_payer_name() {
		return pri_payer_name;
	}
	public void setPri_payer_name(String pri_payer_name) {
		this.pri_payer_name = pri_payer_name;
	}
	public String getPri_payer_number() {
		return pri_payer_number;
	}
	public void setPri_payer_number(String pri_payer_number) {
		this.pri_payer_number = pri_payer_number;
	}
	public String getPrimary_insurance() {
		return primary_insurance;
	}
	public void setPrimary_insurance(String primary_insurance) {
		this.primary_insurance = primary_insurance;
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
	 
}
