package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetCorrespondenceReport {
	@Id
	private String row_no;
	private String alternate_account;
	private String communicate_with;
	private String communication;
	private String communicationdate;
	private String created_user;
	private String crdate;
	
	
	public String getRow_no() {
		return row_no;
	}
	public void setRow_no(String row_no) {
		this.row_no = row_no;
	}
	public String getCrdate() {
		return crdate;
	}
	public void setCrdate(String crdate) {
		this.crdate = crdate;
	}
	public String getAlternate_account() {
		return alternate_account;
	}
	public void setAlternate_account(String alternate_account) {
		this.alternate_account = alternate_account;
	}
	public String getCommunicate_with() {
		return communicate_with;
	}
	public void setCommunicate_with(String communicate_with) {
		this.communicate_with = communicate_with;
	}
	public String getCommunication() {
		return communication;
	}
	public void setCommunication(String communication) {
		this.communication = communication;
	}
	public String getCommunicationdate() {
		return communicationdate;
	}
	public void setCommunicationdate(String communicationdate) {
		this.communicationdate = communicationdate;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
}
