package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ORMGetBillingUsersList {
	 @Id
	    private String user_id;
	    private String id;
	    private String name;
	    private String user_type;
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUser_type() {
			return user_type;
		}
		public void setUser_type(String user_type) {
			this.user_type = user_type;
		}
}
