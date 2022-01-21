package com.ihc.ehr.model;

public class SearchCriteriaParamList {
	
	private String name;
	private String value;
	private String option;
	
	
	public SearchCriteriaParamList() {
		super();
	}
	public SearchCriteriaParamList(String name, String value, String option) {
		super();
		this.name = name;
		this.value = value;
		this.option = option;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	
	
}
