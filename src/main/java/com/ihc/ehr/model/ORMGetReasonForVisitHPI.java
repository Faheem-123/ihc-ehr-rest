package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetReasonForVisitHPI {

	@Id
    private Long reasonforvisit_id;
	private String provided_by;
    private String reason_detail;
    private String hpi_detail;    
    private String created_user;
    private String client_date_created;
    private String date_created;    
    private String modified_user;        
    private String date_modified;
    
	public Long getReasonforvisit_id() {
		return reasonforvisit_id;
	}
	public void setReasonforvisit_id(Long reasonforvisit_id) {
		this.reasonforvisit_id = reasonforvisit_id;
	}
	public String getProvided_by() {
		return provided_by;
	}
	public void setProvided_by(String provided_by) {
		this.provided_by = provided_by;
	}
	public String getReason_detail() {
		return reason_detail;
	}
	public void setReason_detail(String reason_detail) {
		this.reason_detail = reason_detail;
	}
	public String getHpi_detail() {
		return hpi_detail;
	}
	public void setHpi_detail(String hpi_detail) {
		this.hpi_detail = hpi_detail;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}    
}
