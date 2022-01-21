package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMLabMappingCode {

	 @Id
    private String row_id;
    private String proc_code;
    private String proc_description;
    private String lab_code;
    private String lab_description;
    private String aoe_Segment;
	public String getRow_id() {
		return row_id;
	}
	public void setRow_id(String row_id) {
		this.row_id = row_id;
	}
	public String getProc_code() {
		return proc_code;
	}
	public void setProc_code(String proc_code) {
		this.proc_code = proc_code;
	}
	public String getProc_description() {
		return proc_description;
	}
	public void setProc_description(String proc_description) {
		this.proc_description = proc_description;
	}
	public String getLab_code() {
		return lab_code;
	}
	public void setLab_code(String lab_code) {
		this.lab_code = lab_code;
	}
	public String getLab_description() {
		return lab_description;
	}
	public void setLab_description(String lab_description) {
		this.lab_description = lab_description;
	}
	public String getAoe_Segment() {
		return aoe_Segment;
	}
	public void setAoe_Segment(String aoe_Segment) {
		this.aoe_Segment = aoe_Segment;
	}
    
	 
    
    
}
