package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPhrUsersNames {
	
	@Id
	private String user_name;
	private String user_relationship;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_relationship() {
		return user_relationship;
	}
	public void setUser_relationship(String user_relationship) {
		this.user_relationship = user_relationship;
	}
}
