package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMImmTradeNameSearchListGet {

	@Id
    private Integer s_no;
    private String cvx_code;
    private String immunization_name;
    private String trade_name;
    private String trade_description;
    private String mvx_code;
    private String manufacturer_name;
    private String status;
	public Integer getS_no() {
		return s_no;
	}
	public void setS_no(Integer s_no) {
		this.s_no = s_no;
	}
	public String getCvx_code() {
		return cvx_code;
	}
	public void setCvx_code(String cvx_code) {
		this.cvx_code = cvx_code;
	}
	public String getImmunization_name() {
		return immunization_name;
	}
	public void setImmunization_name(String immunization_name) {
		this.immunization_name = immunization_name;
	}
	public String getTrade_name() {
		return trade_name;
	}
	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}
	public String getTrade_description() {
		return trade_description;
	}
	public void setTrade_description(String trade_description) {
		this.trade_description = trade_description;
	}
	public String getMvx_code() {
		return mvx_code;
	}
	public void setMvx_code(String mvx_code) {
		this.mvx_code = mvx_code;
	}
	public String getManufacturer_name() {
		return manufacturer_name;
	}
	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
