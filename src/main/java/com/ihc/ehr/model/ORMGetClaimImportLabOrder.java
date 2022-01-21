package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetClaimImportLabOrder {

	@Id
	private Long order_id;
	private String order_date;
	private String lab_name;
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getLab_name() {
		return lab_name;
	}
	public void setLab_name(String lab_name) {
		this.lab_name = lab_name;
	}
}
