package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMFamilyHxView {

	@Id
	private String familyhistory_id;
	private String relation;
	private String comment;
	private String description;
	private String icd_code;
	private String onset_date;
	private String code_type;
	public String getFamilyhistory_id() {
		return familyhistory_id;
	}
	public void setFamilyhistory_id(String familyhistory_id) {
		this.familyhistory_id = familyhistory_id;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcd_code() {
		return icd_code;
	}
	public void setIcd_code(String icd_code) {
		this.icd_code = icd_code;
	}
	public String getOnset_date() {
		return onset_date;
	}
	public void setOnset_date(String onset_date) {
		this.onset_date = onset_date;
	}
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	
	
	
}
