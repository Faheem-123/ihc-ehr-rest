package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMImmunizationRegistryInfoGet {

	@Id    
    private String clinic_id;
    private String clinic_code;
    private String clinic_name;
    private String sending_facility_uid;  
    private String rec_facility_name;
    private String rec_facility_uid;   
    private String rec_app_name;
    private String rec_app_uid;    
    private String rec_org_id;
    private String sending_app;
    private String sending_app_uid;
    private String inventory_site_id;
    private String registry_code;
    private Boolean include_uid;
    private String org_assign_authority;
    private String org_assign_authority_uid;
    private String filer_placer_assign_auth;
    private String filer_placer_assign_auth_uid;
    private String pid_assign_auth;
    private String pid_assign_auth_uid;
    private String clinic_staff_assign_auth;
    private String clinic_staff_assign_auth_uid;
	public String getClinic_id() {
		return clinic_id;
	}
	public void setClinic_id(String clinic_id) {
		this.clinic_id = clinic_id;
	}
	public String getClinic_code() {
		return clinic_code;
	}
	public void setClinic_code(String clinic_code) {
		this.clinic_code = clinic_code;
	}
	public String getClinic_name() {
		return clinic_name;
	}
	public void setClinic_name(String clinic_name) {
		this.clinic_name = clinic_name;
	}
	public String getSending_facility_uid() {
		return sending_facility_uid;
	}
	public void setSending_facility_uid(String sending_facility_uid) {
		this.sending_facility_uid = sending_facility_uid;
	}
	public String getRec_facility_name() {
		return rec_facility_name;
	}
	public void setRec_facility_name(String rec_facility_name) {
		this.rec_facility_name = rec_facility_name;
	}
	public String getRec_facility_uid() {
		return rec_facility_uid;
	}
	public void setRec_facility_uid(String rec_facility_uid) {
		this.rec_facility_uid = rec_facility_uid;
	}
	public String getRec_app_name() {
		return rec_app_name;
	}
	public void setRec_app_name(String rec_app_name) {
		this.rec_app_name = rec_app_name;
	}
	public String getRec_app_uid() {
		return rec_app_uid;
	}
	public void setRec_app_uid(String rec_app_uid) {
		this.rec_app_uid = rec_app_uid;
	}
	public String getRec_org_id() {
		return rec_org_id;
	}
	public void setRec_org_id(String rec_org_id) {
		this.rec_org_id = rec_org_id;
	}
	public String getSending_app() {
		return sending_app;
	}
	public void setSending_app(String sending_app) {
		this.sending_app = sending_app;
	}
	public String getSending_app_uid() {
		return sending_app_uid;
	}
	public void setSending_app_uid(String sending_app_uid) {
		this.sending_app_uid = sending_app_uid;
	}
	public String getInventory_site_id() {
		return inventory_site_id;
	}
	public void setInventory_site_id(String inventory_site_id) {
		this.inventory_site_id = inventory_site_id;
	}
	public String getRegistry_code() {
		return registry_code;
	}
	public void setRegistry_code(String registry_code) {
		this.registry_code = registry_code;
	}
	public Boolean getInclude_uid() {
		return include_uid;
	}
	public void setInclude_uid(Boolean include_uid) {
		this.include_uid = include_uid;
	}
	public String getOrg_assign_authority() {
		return org_assign_authority;
	}
	public void setOrg_assign_authority(String org_assign_authority) {
		this.org_assign_authority = org_assign_authority;
	}
	public String getOrg_assign_authority_uid() {
		return org_assign_authority_uid;
	}
	public void setOrg_assign_authority_uid(String org_assign_authority_uid) {
		this.org_assign_authority_uid = org_assign_authority_uid;
	}
	public String getFiler_placer_assign_auth() {
		return filer_placer_assign_auth;
	}
	public void setFiler_placer_assign_auth(String filer_placer_assign_auth) {
		this.filer_placer_assign_auth = filer_placer_assign_auth;
	}
	public String getFiler_placer_assign_auth_uid() {
		return filer_placer_assign_auth_uid;
	}
	public void setFiler_placer_assign_auth_uid(String filer_placer_assign_auth_uid) {
		this.filer_placer_assign_auth_uid = filer_placer_assign_auth_uid;
	}
	public String getPid_assign_auth() {
		return pid_assign_auth;
	}
	public void setPid_assign_auth(String pid_assign_auth) {
		this.pid_assign_auth = pid_assign_auth;
	}
	public String getPid_assign_auth_uid() {
		return pid_assign_auth_uid;
	}
	public void setPid_assign_auth_uid(String pid_assign_auth_uid) {
		this.pid_assign_auth_uid = pid_assign_auth_uid;
	}
	public String getClinic_staff_assign_auth() {
		return clinic_staff_assign_auth;
	}
	public void setClinic_staff_assign_auth(String clinic_staff_assign_auth) {
		this.clinic_staff_assign_auth = clinic_staff_assign_auth;
	}
	public String getClinic_staff_assign_auth_uid() {
		return clinic_staff_assign_auth_uid;
	}
	public void setClinic_staff_assign_auth_uid(String clinic_staff_assign_auth_uid) {
		this.clinic_staff_assign_auth_uid = clinic_staff_assign_auth_uid;
	}    
}
