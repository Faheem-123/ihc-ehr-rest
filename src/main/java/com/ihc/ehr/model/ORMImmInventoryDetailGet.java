package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMImmInventoryDetailGet {

	@Id
    private Long inventory_id;
    private String cvx_code;
    private String mvx_code;
    private String manufacturer_name;
    private String trade_name;
    private String lot_number;
    private String expiry_date;
    private Integer inventory;
    private String inventory_status;        
    private Long location_id;
    private String clinic_id;
    private String clinic_name;
    private String created_user;
    private String modified_user;
    private String date_created;
    private String date_modified;
    private String client_date_created;
    private String client_date_modified;
    private String trade_description;
    private String ndc;
	public Long getInventory_id() {
		return inventory_id;
	}
	public void setInventory_id(Long inventory_id) {
		this.inventory_id = inventory_id;
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
	public String getManufacturer_name() {
		return manufacturer_name;
	}
	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}
	public String getTrade_name() {
		return trade_name;
	}
	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}
	public String getLot_number() {
		return lot_number;
	}
	public void setLot_number(String lot_number) {
		this.lot_number = lot_number;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public String getInventory_status() {
		return inventory_status;
	}
	public void setInventory_status(String inventory_status) {
		this.inventory_status = inventory_status;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	public String getClinic_id() {
		return clinic_id;
	}
	public void setClinic_id(String clinic_id) {
		this.clinic_id = clinic_id;
	}
	public String getClinic_name() {
		return clinic_name;
	}
	public void setClinic_name(String clinic_name) {
		this.clinic_name = clinic_name;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public String getTrade_description() {
		return trade_description;
	}
	public void setTrade_description(String trade_description) {
		this.trade_description = trade_description;
	}
	public String getNdc() {
		return ndc;
	}
	public void setNdc(String ndc) {
		this.ndc = ndc;
	}
    
    
}
