package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMClaimDiagnosisGet_Pro {
	
	@Id	
    private Long claim_diagnosis_id;   
    private Integer diag_sequence; 
    private String diag_code;
    private String description;    
    private String code_type;
    
    private String date_created;
    private String client_date_created;
    private String created_user;
    
	public Long getClaim_diagnosis_id() {
		return claim_diagnosis_id;
	}
	public void setClaim_diagnosis_id(Long claim_diagnosis_id) {
		this.claim_diagnosis_id = claim_diagnosis_id;
	}
	public Integer getDiag_sequence() {
		return diag_sequence;
	}
	public void setDiag_sequence(Integer diag_sequence) {
		this.diag_sequence = diag_sequence;
	}
	public String getDiag_code() {
		return diag_code;
	}
	public void setDiag_code(String diag_code) {
		this.diag_code = diag_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
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
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
}
