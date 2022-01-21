package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMLabResultUnits {

	 @Id
	    private String result_unit_id;
	    private String result_value_unit;
		public String getResult_unit_id() {
			return result_unit_id;
		}
		public void setResult_unit_id(String result_unit_id) {
			this.result_unit_id = result_unit_id;
		}
		public String getResult_value_unit() {
			return result_value_unit;
		}
		public void setResult_value_unit(String result_value_unit) {
			this.result_value_unit = result_value_unit;
		}
	    
	    
}
