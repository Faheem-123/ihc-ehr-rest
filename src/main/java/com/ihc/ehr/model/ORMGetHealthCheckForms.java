package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ORMGetHealthCheckForms {

	 @Id
	    private String form_id;
	    private String form_name;
	    private String form_description;
	    private int no_of_forms;
		public String getForm_id() {
			return form_id;
		}
		public void setForm_id(String form_id) {
			this.form_id = form_id;
		}
		public String getForm_name() {
			return form_name;
		}
		public void setForm_name(String form_name) {
			this.form_name = form_name;
		}
		public String getForm_description() {
			return form_description;
		}
		public void setForm_description(String form_description) {
			this.form_description = form_description;
		}
		public int getNo_of_forms() {
			return no_of_forms;
		}
		public void setNo_of_forms(int no_of_forms) {
			this.no_of_forms = no_of_forms;
		}
	    
	    
}
