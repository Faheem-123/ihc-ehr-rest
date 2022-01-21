package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="module")
public class ORMModules {

	 @Id
	    private String module_id;
	    private String practice_id;
	    private String name;
	    private String operation;
	    private String actual_name;
	    private boolean deleted;
		public String getModule_id() {
			return module_id;
		}
		public void setModule_id(String module_id) {
			this.module_id = module_id;
		}
		public String getPractice_id() {
			return practice_id;
		}
		public void setPractice_id(String practice_id) {
			this.practice_id = practice_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getOperation() {
			return operation;
		}
		public void setOperation(String operation) {
			this.operation = operation;
		}
		public String getActual_name() {
			return actual_name;
		}
		public void setActual_name(String actual_name) {
			this.actual_name = actual_name;
		}
		public boolean isDeleted() {
			return deleted;
		}
		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}
	    
	    
}
