package com.ihc.ehr.model;

public class ERAPaymentPostingResponse {
	
	String status;
	String message;
	String message_type;
	
	Integer payment_posted;
	Integer denial_posted;
	
	
	
	public ERAPaymentPostingResponse(String status, String message, String message_type, Integer payment_posted,
			Integer denial_posted) {
		super();
		this.status = status;
		this.message = message;
		this.message_type = message_type;
		this.payment_posted = payment_posted;
		this.denial_posted = denial_posted;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public Integer getPayment_posted() {
		return payment_posted;
	}
	public void setPayment_posted(Integer payment_posted) {
		this.payment_posted = payment_posted;
	}
	public Integer getDenial_posted() {
		return denial_posted;
	}
	public void setDenial_posted(Integer denial_posted) {
		this.denial_posted = denial_posted;
	}	
	
	

	
	
}
