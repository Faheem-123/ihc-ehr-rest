package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMScalarValue {

	@Id
	private String scalar_value;

	public String getScalar_value() {
		return scalar_value;
	}

	public void setScalar_value(String scalar_value) {
		this.scalar_value = scalar_value;
	}
	  
	  
}
