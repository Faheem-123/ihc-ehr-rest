package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMImmNDCGet {
	
    @Id
    private Long cvx_ndc_id;
    private String cvx_code;
    private String mvx_code;
    private String ndc_code;
	public Long getCvx_ndc_id() {
		return cvx_ndc_id;
	}
	public void setCvx_ndc_id(Long cvx_ndc_id) {
		this.cvx_ndc_id = cvx_ndc_id;
	}
	public String getCvx_code() {
		return cvx_code;
	}
	public void setCvx_code(String cvx_code) {
		this.cvx_code = cvx_code;
	}
	public String getMvx_code() {
		return mvx_code;
	}
	public void setMvx_code(String mvx_code) {
		this.mvx_code = mvx_code;
	}
	public String getNdc_code() {
		return ndc_code;
	}
	public void setNdc_code(String ndc_code) {
		this.ndc_code = ndc_code;
	}
}
