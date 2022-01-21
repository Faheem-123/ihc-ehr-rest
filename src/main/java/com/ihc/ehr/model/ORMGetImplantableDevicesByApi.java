package com.ihc.ehr.model;

import javax.persistence.Id;

public class ORMGetImplantableDevicesByApi {
	@Id
    private String device_id;
	private String device_id_issuing_agency;
    private String gmdn_pt_name;
    private String gmdn_pt_description;
    
    private String expiry_date;
    private String manufacturing_date;
    
    private String mri_safety_status;
    private String company_name;
    private String brand_name;
    private String labeled_contains_nrl;
    private String labeled_no_nrl;
    
    private String serial_number;
    private String lot_batch_number;
    private String version_model_number;
    private String device_description;
    private String device_hctp;
    private String snomed_ct_id;
    private String snomed_ct_description;
    private String error;
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getDevice_id_issuing_agency() {
		return device_id_issuing_agency;
	}
	public void setDevice_id_issuing_agency(String device_id_issuing_agency) {
		this.device_id_issuing_agency = device_id_issuing_agency;
	}
	public String getGmdn_pt_name() {
		return gmdn_pt_name;
	}
	public void setGmdn_pt_name(String gmdn_pt_name) {
		this.gmdn_pt_name = gmdn_pt_name;
	}
	public String getGmdn_pt_description() {
		return gmdn_pt_description;
	}
	public void setGmdn_pt_description(String gmdn_pt_description) {
		this.gmdn_pt_description = gmdn_pt_description;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	
	public String getManufacturing_date() {
		return manufacturing_date;
	}
	public void setManufacturing_date(String manufacturing_date) {
		this.manufacturing_date = manufacturing_date;
	}
	public String getMri_safety_status() {
		return mri_safety_status;
	}
	public void setMri_safety_status(String mri_safety_status) {
		this.mri_safety_status = mri_safety_status;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getLabeled_contains_nrl() {
		return labeled_contains_nrl;
	}
	public void setLabeled_contains_nrl(String labeled_contains_nrl) {
		this.labeled_contains_nrl = labeled_contains_nrl;
	}
	public String getLabeled_no_nrl() {
		return labeled_no_nrl;
	}
	public void setLabeled_no_nrl(String labeled_no_nrl) {
		this.labeled_no_nrl = labeled_no_nrl;
	}
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	public String getLot_batch_number() {
		return lot_batch_number;
	}
	public void setLot_batch_number(String lot_batch_number) {
		this.lot_batch_number = lot_batch_number;
	}
	public String getVersion_model_number() {
		return version_model_number;
	}
	public void setVersion_model_number(String version_model_number) {
		this.version_model_number = version_model_number;
	}
	public String getDevice_description() {
		return device_description;
	}
	public void setDevice_description(String device_description) {
		this.device_description = device_description;
	}
	public String getDevice_hctp() {
		return device_hctp;
	}
	public void setDevice_hctp(String device_hctp) {
		this.device_hctp = device_hctp;
	}
	public String getSnomed_ct_id() {
		return snomed_ct_id;
	}
	public void setSnomed_ct_id(String snomed_ct_id) {
		this.snomed_ct_id = snomed_ct_id;
	}
	public String getSnomed_ct_description() {
		return snomed_ct_description;
	}
	public void setSnomed_ct_description(String snomed_ct_description) {
		this.snomed_ct_description = snomed_ct_description;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
