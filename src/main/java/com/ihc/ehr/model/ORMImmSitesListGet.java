package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMImmSitesListGet {

	 @Id
    private Long site_id;
    private String site_code;	
    private String site_description;
    
	public Long getSite_id() {
		return site_id;
	}
	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}
	public String getSite_code() {
		return site_code;
	}
	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}
	public String getSite_description() {
		return site_description;
	}
	public void setSite_description(String site_description) {
		this.site_description = site_description;
	}
    
    
}
