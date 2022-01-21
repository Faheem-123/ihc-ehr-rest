package com.ihc.ehr.model;

import java.util.List;

public class HealthConcernSaveWrapper {

	private String operation;	
	private ORMSavehealthconcern concern;
	
	List<ORMSavehealthconcerndetail> lst_detail;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public ORMSavehealthconcern getConcern() {
		return concern;
	}

	public void setConcern(ORMSavehealthconcern concern) {
		this.concern = concern;
	}

	public List<ORMSavehealthconcerndetail> getLst_detail() {
		return lst_detail;
	}

	public void setLst_detail(List<ORMSavehealthconcerndetail> lst_detail) {
		this.lst_detail = lst_detail;
	}
	
	
	
	
	
}
