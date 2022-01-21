package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

 
@Entity
public class ORMPatientStatementNotes {

	 @Id
	    private String id;
	    private String claim_id;
	    private String statement_message;
	    private String claim_message;
	    private String created_user;
	    private String date_created;
	    private String practice_id;

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getClaim_id() {
	        return claim_id;
	    }

	    public void setClaim_id(String claim_id) {
	        this.claim_id = claim_id;
	    }

	    public String getStatement_message() {
	        return statement_message;
	    }

	    public void setStatement_message(String statement_message) {
	        this.statement_message = statement_message;
	    }

	    public String getClaim_message() {
	        return claim_message;
	    }

	    public void setClaim_message(String claim_message) {
	        this.claim_message = claim_message;
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

	    public String getPractice_id() {
	        return practice_id;
	    }

	    public void setPractice_id(String practice_id) {
	        this.practice_id = practice_id;
	    }
}
