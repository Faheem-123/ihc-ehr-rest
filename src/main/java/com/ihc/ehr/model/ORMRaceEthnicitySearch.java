package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMRaceEthnicitySearch {

	@Id
	private Long sno;
	private String omb_code;	
	private String omb_description;
	private String cdc_code;
	private String cdc_description;
	public Long getSno() {
		return sno;
	}
	public void setSno(Long sno) {
		this.sno = sno;
	}
	public String getOmb_code() {
		return omb_code;
	}
	public void setOmb_code(String omb_code) {
		this.omb_code = omb_code;
	}
	public String getOmb_description() {
		return omb_description;
	}
	public void setOmb_description(String omb_description) {
		this.omb_description = omb_description;
	}
	public String getCdc_code() {
		return cdc_code;
	}
	public void setCdc_code(String cdc_code) {
		this.cdc_code = cdc_code;
	}
	public String getCdc_description() {
		return cdc_description;
	}
	public void setCdc_description(String cdc_description) {
		this.cdc_description = cdc_description;
	}	
}
