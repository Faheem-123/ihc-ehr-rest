package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "practice_insurance")
public class ORMPracticeInsurance {

	  @Id
	    private String practice_insurance_id;
	    private String insurance_id;
	    private String insurance_name;    
	    private Boolean deleted;
	    private String created_user;
	    private String client_date_created;
	    private String modified_user;
	    private String client_date_modified;
	    private String date_created;
	    private String date_modified;
	    private String system_ip;
	    private String practice_id;

	    
	    public String getInsurance_name() {
			return insurance_name;
		}

		public void setInsurance_name(String insurance_name) {
			this.insurance_name = insurance_name;
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

	    public String getDate_modified() {
	        return date_modified;
	    }

	    public void setDate_modified(String date_modified) {
	        this.date_modified = date_modified;
	    }

	    public Boolean getDeleted() {
	        return deleted;
	    }

	    public void setDeleted(Boolean deleted) {
	        this.deleted = deleted;
	    }

	    public String getInsurance_id() {
	        return insurance_id;
	    }

	    public void setInsurance_id(String insurance_id) {
	        this.insurance_id = insurance_id;
	    }

	    public String getModified_user() {
	        return modified_user;
	    }

	    public void setModified_user(String modified_user) {
	        this.modified_user = modified_user;
	    }

	    public String getPractice_id() {
	        return practice_id;
	    }

	    public void setPractice_id(String practice_id) {
	        this.practice_id = practice_id;
	    }

	    public String getPractice_insurance_id() {
	        return practice_insurance_id;
	    }

	    public void setPractice_insurance_id(String practice_insurance_id) {
	        this.practice_insurance_id = practice_insurance_id;
	    }

	    public String getSystem_ip() {
	        return system_ip;
	    }

	    public void setSystem_ip(String system_ip) {
	        this.system_ip = system_ip;
	    }
}
