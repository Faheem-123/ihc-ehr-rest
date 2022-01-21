package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="claim_attorney_log")
public class ORMSavePatientAttorney_log {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
		private String patient_id;
		private String claim_id;
		private String attorney_id;
		private String date;
		private String created_user;
		private String path;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getPatient_id() {
			return patient_id;
		}
		public void setPatient_id(String patient_id) {
			this.patient_id = patient_id;
		}
		public String getClaim_id() {
			return claim_id;
		}
		public void setClaim_id(String claim_id) {
			this.claim_id = claim_id;
		}
		public String getAttorney_id() {
			return attorney_id;
		}
		public void setAttorney_id(String attorney_id) {
			this.attorney_id = attorney_id;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getCreated_user() {
			return created_user;
		}
		public void setCreated_user(String created_user) {
			this.created_user = created_user;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		
		
}
