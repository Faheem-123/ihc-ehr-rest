package com.ihc.ehr.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetUserRights {

	 @Id
	    private String module_role_detail_id;
	    private String name;
	    private String actual_name;
	    private String operation;
		public String getModule_role_detail_id() {
			return module_role_detail_id;
		}
		public void setModule_role_detail_id(String module_role_detail_id) {
			this.module_role_detail_id = module_role_detail_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getActual_name() {
			return actual_name;
		}
		public void setActual_name(String actual_name) {
			this.actual_name = actual_name;
		}
		public String getOperation() {
			return operation;
		}
		public void setOperation(String operation) {
			this.operation = operation;
		}
	    
}
