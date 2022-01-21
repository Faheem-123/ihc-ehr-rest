package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMImmCodeSet {

	 @Id
    private String code_set_id;
    private String code;
    private String description;
    private String coding_system;
    private String type;
	public String getCode_set_id() {
		return code_set_id;
	}
	public void setCode_set_id(String code_set_id) {
		this.code_set_id = code_set_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCoding_system() {
		return coding_system;
	}
	public void setCoding_system(String coding_system) {
		this.coding_system = coding_system;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
    
}
