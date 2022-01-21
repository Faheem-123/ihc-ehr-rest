package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetCurrentEncounterTemplate {

	@Id
	private String chart_id;
	private String hpi;
	private String rfv;
	private String ros;
	private String pe;
	private String pmh;
	private String plan;
	private String  plan_html;
	
	
	public String getPlan_html() {
		return plan_html;
	}
	public void setPlan_html(String plan_html) {
		this.plan_html = plan_html;
	}
	public String getChart_id() {
		return chart_id;
	}
	public void setChart_id(String chart_id) {
		this.chart_id = chart_id;
	}
	public String getHpi() {
		return hpi;
	}
	public void setHpi(String hpi) {
		this.hpi = hpi;
	}
	public String getRfv() {
		return rfv;
	}
	public void setRfv(String rfv) {
		this.rfv = rfv;
	}
	public String getRos() {
		return ros;
	}
	public void setRos(String ros) {
		this.ros = ros;
	}
	public String getPe() {
		return pe;
	}
	public void setPe(String pe) {
		this.pe = pe;
	}
	public String getPmh() {
		return pmh;
	}
	public void setPmh(String pmh) {
		this.pmh = pmh;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	
	
}
