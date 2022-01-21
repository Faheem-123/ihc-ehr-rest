package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetMessageCount {

	
	@Id
	private String mail_status;
	private String cnt;
	public String getMail_status() {
		return mail_status;
	}
	public void setMail_status(String mail_status) {
		this.mail_status = mail_status;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	
	
}
