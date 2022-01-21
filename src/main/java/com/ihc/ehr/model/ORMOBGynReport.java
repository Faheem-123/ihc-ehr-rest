package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMOBGynReport {
	 @Id
	    private String id;
	    private String patient_name;
	    private String patient_id;
	    private String care_notes;
	    private String provider_name;
	    private String facility;
	    private String hospital;
	    private String date_created;
	    private String created_user;
	    private String completed;
	    private String completed_date;
	    private String edd_date;
	    
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPatient_name() {
			return patient_name;
		}
		public void setPatient_name(String patient_name) {
			this.patient_name = patient_name;
		}
		public String getPatient_id() {
			return patient_id;
		}
		public void setPatient_id(String patient_id) {
			this.patient_id = patient_id;
		}
		public String getCare_notes() {
			return care_notes;
		}
		public void setCare_notes(String care_notes) {
			this.care_notes = care_notes;
		}
		public String getProvider_name() {
			return provider_name;
		}
		public void setProvider_name(String provider_name) {
			this.provider_name = provider_name;
		}
		public String getFacility() {
			return facility;
		}
		public void setFacility(String facility) {
			this.facility = facility;
		}
		public String getHospital() {
			return hospital;
		}
		public void setHospital(String hospital) {
			this.hospital = hospital;
		}
		public String getDate_created() {
			return date_created;
		}
		public void setDate_created(String date_created) {
			this.date_created = date_created;
		}
		public String getCreated_user() {
			return created_user;
		}
		public void setCreated_user(String created_user) {
			this.created_user = created_user;
		}
		public String getCompleted() {
			return completed;
		}
		public void setCompleted(String completed) {
			this.completed = completed;
		}
		public String getCompleted_date() {
			return completed_date;
		}
		public void setCompleted_date(String completed_date) {
			this.completed_date = completed_date;
		}
		public String getEdd_date() {
			return edd_date;
		}
		public void setEdd_date(String edd_date) {
			this.edd_date = edd_date;
		}

}
