package com.ihc.ehr.model;

import java.util.List;

import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

public class ServiceResponse<T> {

	private ServiceResponseStatus status; // success OR error
	private String response;
	private String result;
	private List<T> response_list;
	//private List<ORMKeyValue> response_list;
	
	public ServiceResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ServiceResponseStatus status) {
		this.status = status;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<T> getResponse_list() {
		return response_list;
	}
	public void setResponse_list(List<T> response_list) {
		this.response_list = response_list;
	}
	
	/*
	public List<ORMKeyValue> getResponse_list() {
		return response_list;
	}
	public void setResponse_list(List<ORMKeyValue> response_list) {
		this.response_list = response_list;
	}
	*/
	
	
	
	
}
