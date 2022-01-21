package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMChartImmunizationSummaryGet {
	
	@Id
	private Long chart_immunization_id;    
    private Long chart_id;
    private Long location_id;
    private String immunization_name;
    private String trade_name; 
    private String datetime_administered;        
    private String entry_type; 
    private String administering_user_info;  
    private String entered_by_user_info;  
    private String registry_status;  
    private String registry_status_description;
    private String completion_status_code;
    private String reason_description;
    private String administered_code;
    private String administered_code_description;    
    private Boolean deleted;
    private String date_modified;
    private String cvx_code;
    private String ndc_code;
    private String manufacturer_info;
    private String lot_number;
    private String expiration_date;
    private String action_code;
    
    
    
	public Long getChart_immunization_id() {
		return chart_immunization_id;
	}
	public void setChart_immunization_id(Long chart_immunization_id) {
		this.chart_immunization_id = chart_immunization_id;
	}
	public Long getChart_id() {
		return chart_id;
	}
	public void setChart_id(Long chart_id) {
		this.chart_id = chart_id;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
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
	public String getDatetime_administered() {
		return datetime_administered;
	}
	public void setDatetime_administered(String datetime_administered) {
		this.datetime_administered = datetime_administered;
	}
	public String getEntry_type() {
		return entry_type;
	}
	public void setEntry_type(String entry_type) {
		this.entry_type = entry_type;
	}
	public String getAdministering_user_info() {
		return administering_user_info;
	}
	public void setAdministering_user_info(String administering_user_info) {
		this.administering_user_info = administering_user_info;
	}
	public String getEntered_by_user_info() {
		return entered_by_user_info;
	}
	public void setEntered_by_user_info(String entered_by_user_info) {
		this.entered_by_user_info = entered_by_user_info;
	}
	public String getRegistry_status() {
		return registry_status;
	}
	public void setRegistry_status(String registry_status) {
		this.registry_status = registry_status;
	}
	public String getRegistry_status_description() {
		return registry_status_description;
	}
	public void setRegistry_status_description(String registry_status_description) {
		this.registry_status_description = registry_status_description;
	}
	public String getCompletion_status_code() {
		return completion_status_code;
	}
	public void setCompletion_status_code(String completion_status_code) {
		this.completion_status_code = completion_status_code;
	}
	public String getReason_description() {
		return reason_description;
	}
	public void setReason_description(String reason_description) {
		this.reason_description = reason_description;
	}
	public String getAdministered_code() {
		return administered_code;
	}
	public void setAdministered_code(String administered_code) {
		this.administered_code = administered_code;
	}
	public String getAdministered_code_description() {
		return administered_code_description;
	}
	public void setAdministered_code_description(String administered_code_description) {
		this.administered_code_description = administered_code_description;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getCvx_code() {
		return cvx_code;
	}
	public void setCvx_code(String cvx_code) {
		this.cvx_code = cvx_code;
	}
	public String getNdc_code() {
		return ndc_code;
	}
	public void setNdc_code(String ndc_code) {
		this.ndc_code = ndc_code;
	}
	public String getManufacturer_info() {
		return manufacturer_info;
	}
	public void setManufacturer_info(String manufacturer_info) {
		this.manufacturer_info = manufacturer_info;
	}
	public String getLot_number() {
		return lot_number;
	}
	public void setLot_number(String lot_number) {
		this.lot_number = lot_number;
	}
	public String getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	public String getAction_code() {
		return action_code;
	}
	public void setAction_code(String action_code) {
		this.action_code = action_code;
	}    
	
	
}
