package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetFaxDetailsForStatusUpdate {

	
	@Id
	private Long fax_sent_id;
	private String fax_reference_id;
	private String fax_status;
	private String sender_no;
	private String fax_type;
	private Long practice_id;
	
	
	public Long getFax_sent_id() {
		return fax_sent_id;
	}
	public void setFax_sent_id(Long fax_sent_id) {
		this.fax_sent_id = fax_sent_id;
	}
	public String getFax_reference_id() {
		return fax_reference_id;
	}
	public void setFax_reference_id(String fax_reference_id) {
		this.fax_reference_id = fax_reference_id;
	}
	public String getFax_status() {
		return fax_status;
	}
	public void setFax_status(String fax_status) {
		this.fax_status = fax_status;
	}
	public String getSender_no() {
		return sender_no;
	}
	public void setSender_no(String sender_no) {
		this.sender_no = sender_no;
	}
	public String getFax_type() {
		return fax_type;
	}
	public void setFax_type(String fax_type) {
		this.fax_type = fax_type;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	
	
	
}
