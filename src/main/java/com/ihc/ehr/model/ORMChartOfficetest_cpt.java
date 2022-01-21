package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_officetest_cpts")
public class ORMChartOfficetest_cpt {
	@Id
    private String officetest_cpt_id;
    private String officetest_id;
    private String patient_id;
    private String chart_id;
    private String testname;
    private String proc_code;
    private String charges;
    private String units;
    private String ndc_code;
    private String description;
    private String pos;
    private Boolean deleted;
    private String created_user;
    private String client_date_created;
    private String modified_user;
    private String client_date_modified;
    private String date_created;
    private String date_modified;
    private String default_modifier;
	public String getOfficetest_cpt_id() {
		return officetest_cpt_id;
	}
	public void setOfficetest_cpt_id(String officetest_cpt_id) {
		this.officetest_cpt_id = officetest_cpt_id;
	}
	public String getOfficetest_id() {
		return officetest_id;
	}
	public void setOfficetest_id(String officetest_id) {
		this.officetest_id = officetest_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getChart_id() {
		return chart_id;
	}
	public void setChart_id(String chart_id) {
		this.chart_id = chart_id;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public String getProc_code() {
		return proc_code;
	}
	public void setProc_code(String proc_code) {
		this.proc_code = proc_code;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getNdc_code() {
		return ndc_code;
	}
	public void setNdc_code(String ndc_code) {
		this.ndc_code = ndc_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getDefault_modifier() {
		return default_modifier;
	}
	public void setDefault_modifier(String default_modifier) {
		this.default_modifier = default_modifier;
	}
}
