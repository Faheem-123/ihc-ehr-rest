package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMLabResultAbnormalFlags {

	 @Id
	    private String range_code;
	    private String range_name;
		public String getRange_code() {
			return range_code;
		}
		public void setRange_code(String range_code) {
			this.range_code = range_code;
		}
		public String getRange_name() {
			return range_name;
		}
		public void setRange_name(String range_name) {
			this.range_name = range_name;
		}
	    
	    
}
