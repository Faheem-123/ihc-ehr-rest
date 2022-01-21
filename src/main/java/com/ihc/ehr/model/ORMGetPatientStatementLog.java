package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ORMGetPatientStatementLog {

	@Id
    private String row_id;
    private String claim_id;
    private String dos;
    private String statement_date;
    private String generated_by;
    private String notes;
    private String amt_due;
    private String amt_sent;
    private String total_amt_sent;
    private String path;
    
    

    public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTotal_amt_sent() {
        return total_amt_sent;
    }

    public void setTotal_amt_sent(String total_amt_sent) {
        this.total_amt_sent = total_amt_sent;
    }
    
    public String getAmt_due() {
        return amt_due;
    }

    public void setAmt_due(String amt_due) {
        this.amt_due = amt_due;
    }

    public String getAmt_sent() {
        return amt_sent;
    }

    public void setAmt_sent(String amt_sent) {
        this.amt_sent = amt_sent;
    }
    
    

    public String getRow_id() {
        return row_id;
    }

    public void setRow_id(String row_id) {
        this.row_id = row_id;
    }

    public String getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(String claim_id) {
        this.claim_id = claim_id;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getStatement_date() {
        return statement_date;
    }

    public void setStatement_date(String statement_date) {
        this.statement_date = statement_date;
    }

    public String getGenerated_by() {
        return generated_by;
    }

    public void setGenerated_by(String generated_by) {
        this.generated_by = generated_by;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
