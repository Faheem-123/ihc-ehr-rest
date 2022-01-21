package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "role_cds_rules")
public class ORMRoleCDSRules {

	 @Id
	    private String id;
	    private String rule_id;
	    private String role_id;
	    private String practice_id;
	    private Boolean is_active;
	    private String system_ip;
	    private String modified_user;
	    private String client_date_modified;
	    private String date_modified;
	    private Boolean show_reference_link;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getRule_id() {
			return rule_id;
		}
		public void setRule_id(String rule_id) {
			this.rule_id = rule_id;
		}
		public String getRole_id() {
			return role_id;
		}
		public void setRole_id(String role_id) {
			this.role_id = role_id;
		}
		public String getPractice_id() {
			return practice_id;
		}
		public void setPractice_id(String practice_id) {
			this.practice_id = practice_id;
		}
		public Boolean getIs_active() {
			return is_active;
		}
		public void setIs_active(Boolean is_active) {
			this.is_active = is_active;
		}
		public String getSystem_ip() {
			return system_ip;
		}
		public void setSystem_ip(String system_ip) {
			this.system_ip = system_ip;
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
		public String getDate_modified() {
			return date_modified;
		}
		public void setDate_modified(String date_modified) {
			this.date_modified = date_modified;
		}
		public Boolean getShow_reference_link() {
			return show_reference_link;
		}
		public void setShow_reference_link(Boolean show_reference_link) {
			this.show_reference_link = show_reference_link;
		}
	    
	    
}
