package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetImmunizationProcedures {

	@Id
    private String cvx_cpt_id;
    private String cvx_code;
    private String proc_code;
    private String proc_description;
    
	public String getCvx_cpt_id() {
		return cvx_cpt_id;
	}
	public void setCvx_cpt_id(String cvx_cpt_id) {
		this.cvx_cpt_id = cvx_cpt_id;
	}
	public String getCvx_code() {
		return cvx_code;
	}
	public void setCvx_code(String cvx_code) {
		this.cvx_code = cvx_code;
	}
	public String getProc_code() {
		return proc_code;
	}
	public void setProc_code(String proc_code) {
		this.proc_code = proc_code;
	}
	public String getProc_description() {
		return proc_description;
	}
	public void setProc_description(String proc_description) {
		this.proc_description = proc_description;
	}
    
    
}
