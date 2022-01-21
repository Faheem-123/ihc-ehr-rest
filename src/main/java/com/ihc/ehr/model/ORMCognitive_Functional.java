package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chart_cognitive_functional")
public class ORMCognitive_Functional {
		@Id 
		private Long cognitive_functional_id;
	    private String patient_id;
	    private String chart_id;
	    private String type;
	    private String status;
	    private String effective_date;
	    private String snomed_code;
	    private String snomed_description;
	    private String deleted;
	    private String created_user;
	    private String date_created;
	    private String client_date_created;
	    private String client_date_modified;
	    private String modified_user;
	    private String date_modified;
	    private String practice_id;
	    private String system_ip;
	    private String notes;
	    
		
	    
		public Long getCognitive_functional_id() {
			return cognitive_functional_id;
		}
		public void setCognitive_functional_id(Long cognitive_functional_id) {
			this.cognitive_functional_id = cognitive_functional_id;
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
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getEffective_date() {
			return effective_date;
		}
		public void setEffective_date(String effective_date) {
			this.effective_date = effective_date;
		}
		public String getSnomed_code() {
			return snomed_code;
		}
		public void setSnomed_code(String snomed_code) {
			this.snomed_code = snomed_code;
		}
		public String getSnomed_description() {
			return snomed_description;
		}
		public void setSnomed_description(String snomed_description) {
			this.snomed_description = snomed_description;
		}
		public String getDeleted() {
			return deleted;
		}
		public void setDeleted(String deleted) {
			this.deleted = deleted;
		}
		public String getCreated_user() {
			return created_user;
		}
		public void setCreated_user(String created_user) {
			this.created_user = created_user;
		}
		public String getDate_created() {
			return date_created;
		}
		public void setDate_created(String date_created) {
			this.date_created = date_created;
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
		public String getModified_user() {
			return modified_user;
		}
		public void setModified_user(String modified_user) {
			this.modified_user = modified_user;
		}
		public String getDate_modified() {
			return date_modified;
		}
		public void setDate_modified(String date_modified) {
			this.date_modified = date_modified;
		}
		public String getPractice_id() {
			return practice_id;
		}
		public void setPractice_id(String practice_id) {
			this.practice_id = practice_id;
		}
		public String getSystem_ip() {
			return system_ip;
		}
		public void setSystem_ip(String system_ip) {
			this.system_ip = system_ip;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
}
