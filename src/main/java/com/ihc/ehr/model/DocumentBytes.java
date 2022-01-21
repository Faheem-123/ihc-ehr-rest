package com.ihc.ehr.model;

import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

public class DocumentBytes {

	byte[] bytes;
	ServiceResponseStatus status; // SUCCESS | ERROR
	String error_message; // ERROR_MESSAGE
	
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public ServiceResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ServiceResponseStatus status) {
		this.status = status;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}	
}
