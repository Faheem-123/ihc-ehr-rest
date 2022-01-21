package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMLabResultStatus {

	 @Id
	    private String result_status_code;
	    private String result_status_description;
		public String getResult_status_code() {
			return result_status_code;
		}
		public void setResult_status_code(String result_status_code) {
			this.result_status_code = result_status_code;
		}
		public String getResult_status_description() {
			return result_status_description;
		}
		public void setResult_status_description(String result_status_description) {
			this.result_status_description = result_status_description;
		}
	    
	    
}


