package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMImmTradeNameGet {

	@Id				
    private Long id;
    private String cvx_code;
    private String mvx_code;
    private String trade_name ;
    private String trade_description ;
    private String coding_system ;
    private String manufacturer_name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCvx_code() {
		return cvx_code;
	}
	public void setCvx_code(String cvx_code) {
		this.cvx_code = cvx_code;
	}
	public String getMvx_code() {
		return mvx_code;
	}
	public void setMvx_code(String mvx_code) {
		this.mvx_code = mvx_code;
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
	public String getCoding_system() {
		return coding_system;
	}
	public void setCoding_system(String coding_system) {
		this.coding_system = coding_system;
	}
	public String getManufacturer_name() {
		return manufacturer_name;
	}
	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}
}
