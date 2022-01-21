package com.ihc.ehr.model;

 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "write_off_codes")
public class ORMWriteOffCodes {

	  @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)       
	    private String id;
	    private String code;
	    private String description;
	    private Boolean deleted;
	    private String client_date_created;
	    private String client_date_modified;
	    private String date_created;
	    private String date_modified;
	    private String created_user;
	    private String modified_user;

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

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getModified_user() {
	        return modified_user;
	    }

	    public void setModified_user(String modified_user) {
	        this.modified_user = modified_user;
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }    
}
