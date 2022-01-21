package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table (name ="chart_physicians_care")
@Entity
public class ORMPhysicians_Care {
	  @Id
	    private Long physicians_care_id;
	    private String patient_id;
	    private String chart_id;
	    private String practice_id;
	    private String type_of_care;
	    private String date_started;
	    private String location;
	    private String created_user;
	    private String client_date_created;
	    private String modified_user;
	    private String client_date_modified;
	    private String date_created;
	    private String date_modified;
	    private String deleted;
	    private String provider_name;
	    private String system_ip;
	    
		public Long getPhysicians_care_id() {
			return physicians_care_id;
		}
		public void setPhysicians_care_id(Long physicians_care_id) {
			this.physicians_care_id = physicians_care_id;
		}
		public String getPatient_id() {
			return patient_id;
		}
		public void setPatient_id(String patient_id) {
			this.patient_id = patient_id;
		}
		public String getChart_id() {
			return chart_id;
		}
		public void setChart_id(String chart_id) {
			this.chart_id = chart_id;
		}
		public String getPractice_id() {
			return practice_id;
		}
		public void setPractice_id(String practice_id) {
			this.practice_id = practice_id;
		}
		public String getType_of_care() {
			return type_of_care;
		}
		public void setType_of_care(String type_of_care) {
			this.type_of_care = type_of_care;
		}
		public String getDate_started() {
			return date_started;
		}
		public void setDate_started(String date_started) {
			this.date_started = date_started;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
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
		public String getDeleted() {
			return deleted;
		}
		public void setDeleted(String deleted) {
			this.deleted = deleted;
		}
		public String getProvider_name() {
			return provider_name;
		}
		public void setProvider_name(String provider_name) {
			this.provider_name = provider_name;
		}
		public String getSystem_ip() {
			return system_ip;
		}
		public void setSystem_ip(String system_ip) {
			this.system_ip = system_ip;
		}
}
