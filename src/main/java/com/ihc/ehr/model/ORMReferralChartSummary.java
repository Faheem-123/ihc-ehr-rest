package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMReferralChartSummary {
	@Id
	private String chart_id;
    private Boolean chkbx;
    private String visit_date;
    private String reason_detail;
	public String getChart_id() {
		return chart_id;
	}
	public void setChart_id(String chart_id) {
		this.chart_id = chart_id;
	}
	public Boolean getChkbx() {
		return chkbx;
	}
	public void setChkbx(Boolean chkbx) {
		this.chkbx = chkbx;
	}
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	public String getReason_detail() {
		return reason_detail;
	}
	public void setReason_detail(String reason_detail) {
		this.reason_detail = reason_detail;
	}
    
    

}
