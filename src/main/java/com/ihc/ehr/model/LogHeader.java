package com.ihc.ehr.model;

import javax.persistence.Id;

public class LogHeader {

	@Id
	Integer id;
	String display;
	String data_type;
	Boolean sortable;

	public LogHeader(Integer id, String display, String data_type,Boolean sortable) {
		super();
		this.id = id;		
		this.display = display;
		this.data_type = data_type;
		this.sortable=sortable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public Boolean getSortable() {
		return sortable;
	}

	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	
}
