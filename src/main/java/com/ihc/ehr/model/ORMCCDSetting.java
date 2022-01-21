package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ccd_setting")
public class ORMCCDSetting {

	  @Id
	    private String id;
	    private String description;
	    private String actual_name;
	    private Boolean enabled;
	    private Boolean deleted;
	    private String position;
	    private String practice_id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getActual_name() {
			return actual_name;
		}
		public void setActual_name(String actual_name) {
			this.actual_name = actual_name;
		}
		public Boolean getEnabled() {
			return enabled;
		}
		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}
		public Boolean getDeleted() {
			return deleted;
		}
		public void setDeleted(Boolean deleted) {
			this.deleted = deleted;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getPractice_id() {
			return practice_id;
		}
		public void setPractice_id(String practice_id) {
			this.practice_id = practice_id;
		}
	    
	    
}
