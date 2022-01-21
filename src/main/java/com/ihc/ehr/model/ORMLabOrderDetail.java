package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMLabOrderDetail {

	@Id
	 private String order_id;
	private String order_date;
	private String order_due_date;
	private String patient_id;
	private String provider_id;
	private String location_id;
	private String assigned_to;
	private String prompt_result_to;
	private String status;
	private Boolean fasting;
	private String not_perform_reason;
	private String lab_id;
	private String chart_id;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_modified;
	private String date_created;
	private Boolean stat;
	private String bill_type;
	private Boolean lab_draw;
	private String signed_by;
	private String signed_date;
	private String follow_up_notes;
	private String follow_up_action;
	private String status_detail;
	private String status_by;
	private Boolean order_sent;
	private Boolean imaging_order;
	private String order_type;
	private String facility_id;
	private Boolean is_abnormal;
	private Boolean discussed;
	private String labcorp_discount;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getOrder_due_date() {
		return order_due_date;
	}
	public void setOrder_due_date(String order_due_date) {
		this.order_due_date = order_due_date;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(String provider_id) {
		this.provider_id = provider_id;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public String getPrompt_result_to() {
		return prompt_result_to;
	}
	public void setPrompt_result_to(String prompt_result_to) {
		this.prompt_result_to = prompt_result_to;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean isFasting() {
		return fasting;
	}
	public void setFasting(Boolean fasting) {
		this.fasting = fasting;
	}
	public String getNot_perform_reason() {
		return not_perform_reason;
	}
	public void setNot_perform_reason(String not_perform_reason) {
		this.not_perform_reason = not_perform_reason;
	}
	public String getLab_id() {
		return lab_id;
	}
	public void setLab_id(String lab_id) {
		this.lab_id = lab_id;
	}
	public String getChart_id() {
		return chart_id;
	}
	public void setChart_id(String chart_id) {
		this.chart_id = chart_id;
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
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public Boolean isStat() {
		return stat;
	}
	public void setStat(Boolean stat) {
		this.stat = stat;
	}
	public String getBill_type() {
		return bill_type;
	}
	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}
	public Boolean isLab_draw() {
		return lab_draw;
	}
	public void setLab_draw(Boolean lab_draw) {
		this.lab_draw = lab_draw;
	}
	public String getSigned_by() {
		return signed_by;
	}
	public void setSigned_by(String signed_by) {
		this.signed_by = signed_by;
	}
	public String getSigned_date() {
		return signed_date;
	}
	public void setSigned_date(String signed_date) {
		this.signed_date = signed_date;
	}
	public String getFollow_up_notes() {
		return follow_up_notes;
	}
	public void setFollow_up_notes(String follow_up_notes) {
		this.follow_up_notes = follow_up_notes;
	}
	public String getFollow_up_action() {
		return follow_up_action;
	}
	public void setFollow_up_action(String follow_up_action) {
		this.follow_up_action = follow_up_action;
	}
	public String getStatus_detail() {
		return status_detail;
	}
	public void setStatus_detail(String status_detail) {
		this.status_detail = status_detail;
	}
	public String getStatus_by() {
		return status_by;
	}
	public void setStatus_by(String status_by) {
		this.status_by = status_by;
	}
	public Boolean getOrder_sent() {
		return order_sent;
	}
	public void setOrder_sent(Boolean order_sent) {
		this.order_sent = order_sent;
	}
	public Boolean isImaging_order() {
		return imaging_order;
	}
	public void setImaging_order(Boolean imaging_order) {
		this.imaging_order = imaging_order;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public String getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(String facility_id) {
		this.facility_id = facility_id;
	}
	public Boolean isIs_abnormal() {
		return is_abnormal;
	}
	public void setIs_abnormal(Boolean is_abnormal) {
		this.is_abnormal = is_abnormal;
	}
	public Boolean isDiscussed() {
		return discussed;
	}
	public void setDiscussed(Boolean discussed) {
		this.discussed = discussed;
	}
	public String getLabcorp_discount() {
		return labcorp_discount;
	}
	public void setLabcorp_discount(String labcorp_discount) {
		this.labcorp_discount = labcorp_discount;
	}
}
