package com.ihc.ehr.model;

import javax.persistence.Id;

public class ImmRegForecast {
	
	@Id
    private Integer s_no; 
    private String due_date; 
    private String imm_group;
    private String ealiest_date_to_give;
    private String latest_date_to_give;
	public Integer getS_no() {
		return s_no;
	}
	public void setS_no(Integer s_no) {
		this.s_no = s_no;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getImm_group() {
		return imm_group;
	}
	public void setImm_group(String imm_group) {
		this.imm_group = imm_group;
	}
	public String getEaliest_date_to_give() {
		return ealiest_date_to_give;
	}
	public void setEaliest_date_to_give(String ealiest_date_to_give) {
		this.ealiest_date_to_give = ealiest_date_to_give;
	}
	public String getLatest_date_to_give() {
		return latest_date_to_give;
	}
	public void setLatest_date_to_give(String latest_date_to_give) {
		this.latest_date_to_give = latest_date_to_give;
	}
}
