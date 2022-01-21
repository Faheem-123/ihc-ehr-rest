package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="appointment")
public class ORMSaveAppointment {
	
	@Id
	private Long appointment_id; 
    private Long patient_id; 
    private String appointment_date; 
    private String appointment_time; 
    private String appointment_duration; 
    private String appointment_comments; 
    private Long status_id; 
    private Long location_id; 
    private Long practice_id; 
    private Long provider_id; 
    private Long appsource; 
    private Long apptype; 
    private Long appreason;    
    private String created_user; 
    private String client_date_created; 
    private String modified_user; 
    private String client_date_modified; 
    private String date_created; 
    private String date_modified;
    private String system_ip;
    
	public Long getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(Long appointment_id) {
		this.appointment_id = appointment_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(String appointment_date) {
		this.appointment_date = appointment_date;
	}
	public String getAppointment_time() {
		return appointment_time;
	}
	public void setAppointment_time(String appointment_time) {
		this.appointment_time = appointment_time;
	}
	public String getAppointment_duration() {
		return appointment_duration;
	}
	public void setAppointment_duration(String appointment_duration) {
		this.appointment_duration = appointment_duration;
	}
	public String getAppointment_comments() {
		return appointment_comments;
	}
	public void setAppointment_comments(String appointment_comments) {
		this.appointment_comments = appointment_comments;
	}
	public Long getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Long status_id) {
		this.status_id = status_id;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public Long getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(Long provider_id) {
		this.provider_id = provider_id;
	}
	public Long getAppsource() {
		return appsource;
	}
	public void setAppsource(Long appsource) {
		this.appsource = appsource;
	}
	public Long getApptype() {
		return apptype;
	}
	public void setApptype(Long apptype) {
		this.apptype = apptype;
	}
	public Long getAppreason() {
		return appreason;
	}
	public void setAppreason(Long appreason) {
		this.appreason = appreason;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
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
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	@Override
	public String toString() {
		return "ORMSaveAppointment [appointment_id=" + appointment_id + ", patient_id=" + patient_id
				+ ", appointment_date=" + appointment_date + ", appointment_time=" + appointment_time
				+ ", appointment_duration=" + appointment_duration + ", appointment_comments=" + appointment_comments
				+ ", status_id=" + status_id + ", location_id=" + location_id + ", practice_id=" + practice_id
				+ ", provider_id=" + provider_id + ", appsource=" + appsource + ", apptype=" + apptype + ", appreason="
				+ appreason + ", created_user=" + created_user + ", client_date_created=" + client_date_created
				+ ", modified_user=" + modified_user + ", client_date_modified=" + client_date_modified
				+ ", date_created=" + date_created + ", date_modified=" + date_modified + ", system_ip=" + system_ip
				+ "]";
	}
	
	
}
