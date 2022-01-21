package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGETrptInsVerification {

		@Id
		private String row_id;
	    private String pid;
	    private String patient;
	    private String appointment_date;
	    private String provider;
	    private String location;
	    private String document_date;
	    private String name;
	    private String doc_avaliable;
	    private String uploaded_by;
	    private String checked_in_by;
	    
		public String getRow_id() {
			return row_id;
		}
		public void setRow_id(String row_id) {
			this.row_id = row_id;
		}
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public String getPatient() {
			return patient;
		}
		public void setPatient(String patient) {
			this.patient = patient;
		}
		public String getAppointment_date() {
			return appointment_date;
		}
		public void setAppointment_date(String appointment_date) {
			this.appointment_date = appointment_date;
		}
		public String getProvider() {
			return provider;
		}
		public void setProvider(String provider) {
			this.provider = provider;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getDocument_date() {
			return document_date;
		}
		public void setDocument_date(String document_date) {
			this.document_date = document_date;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDoc_avaliable() {
			return doc_avaliable;
		}
		public void setDoc_avaliable(String doc_avaliable) {
			this.doc_avaliable = doc_avaliable;
		}
		public String getUploaded_by() {
			return uploaded_by;
		}
		public void setUploaded_by(String uploaded_by) {
			this.uploaded_by = uploaded_by;
		}
		public String getChecked_in_by() {
			return checked_in_by;
		}
		public void setChecked_in_by(String checked_in_by) {
			this.checked_in_by = checked_in_by;
		}
	    
}