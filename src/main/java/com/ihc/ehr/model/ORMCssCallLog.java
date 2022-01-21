package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMCssCallLog {

	  @Id
	     private String s_no;
	     private String pid;
	     private String pat_name;
	     private String created_user;
	     private String date_created;
	     private String details;
	     private String type;

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getS_no() {
	        return s_no;
	    }

	    public void setS_no(String s_no) {
	        this.s_no = s_no;
	    }

	    public String getPid() {
	        return pid;
	    }

	    public void setPid(String pid) {
	        this.pid = pid;
	    }

	    public String getPat_name() {
	        return pat_name;
	    }

	    public void setPat_name(String pat_name) {
	        this.pat_name = pat_name;
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

	    public String getDetails() {
	        return details;
	    }

	    public void setDetails(String details) {
	        this.details = details;
	    }
}
