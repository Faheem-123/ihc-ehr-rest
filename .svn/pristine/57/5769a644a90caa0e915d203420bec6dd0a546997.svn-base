package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMKeyValue {
	
	@Id
	private String key;
	private String value;		
	
	public ORMKeyValue() {
		super();		
	}
	
	public ORMKeyValue(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ORMKeyValue [key=" + key + ", value=" + value + "]";
	}
}
