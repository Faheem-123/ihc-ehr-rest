package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_order_results")
public class ORMLabResult {
	
	  @Id
	    private String result_id;
	    private String order_id;
	    private String test_id;
	    private String result_code;
	    private String result_description;
	    private String result_value;
	    private String result_value_unit;
	    private String recomended_value;
	    private String result_status;
	    private String result_status_code;
	    private String abnormal_range_code;
	    private String abnormal_range;
	    private String observation_date;
	    private String assigned_to;
	    private String reviewed_by;
	    private String reviewed_date;
	    private String physician_comments;
	    private String followup_notes;
	    private String created_user;
	    private String client_date_created;
	    private String modified_user;
	    private String client_date_modified;
	    private String date_created;
	    private String date_modified;
	    private String lab_comments;
	    private String collection_date;
	    private boolean change_med;
	    private String follow_up_action;
	    private String followup_detail;
	    private String lab_dir;
	    private Boolean education_provided;
	    private String phy_comments_date;
	    private String result_type;
		public String getResult_id() {
			return result_id;
		}
		public void setResult_id(String result_id) {
			this.result_id = result_id;
		}
		public String getOrder_id() {
			return order_id;
		}
		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}
		public String getTest_id() {
			return test_id;
		}
		public void setTest_id(String test_id) {
			this.test_id = test_id;
		}
		public String getResult_code() {
			return result_code;
		}
		public void setResult_code(String result_code) {
			this.result_code = result_code;
		}
		public String getResult_description() {
			return result_description;
		}
		public void setResult_description(String result_description) {
			this.result_description = result_description;
		}
		public String getResult_value() {
			return result_value;
		}
		public void setResult_value(String result_value) {
			this.result_value = result_value;
		}
		public String getResult_value_unit() {
			return result_value_unit;
		}
		public void setResult_value_unit(String result_value_unit) {
			this.result_value_unit = result_value_unit;
		}
		public String getRecomended_value() {
			return recomended_value;
		}
		public void setRecomended_value(String recomended_value) {
			this.recomended_value = recomended_value;
		}
		public String getResult_status() {
			return result_status;
		}
		public void setResult_status(String result_status) {
			this.result_status = result_status;
		}
		public String getResult_status_code() {
			return result_status_code;
		}
		public void setResult_status_code(String result_status_code) {
			this.result_status_code = result_status_code;
		}
		public String getAbnormal_range_code() {
			return abnormal_range_code;
		}
		public void setAbnormal_range_code(String abnormal_range_code) {
			this.abnormal_range_code = abnormal_range_code;
		}
		public String getAbnormal_range() {
			return abnormal_range;
		}
		public void setAbnormal_range(String abnormal_range) {
			this.abnormal_range = abnormal_range;
		}
		public String getObservation_date() {
			return observation_date;
		}
		public void setObservation_date(String observation_date) {
			this.observation_date = observation_date;
		}
		public String getAssigned_to() {
			return assigned_to;
		}
		public void setAssigned_to(String assigned_to) {
			this.assigned_to = assigned_to;
		}
		public String getReviewed_by() {
			return reviewed_by;
		}
		public void setReviewed_by(String reviewed_by) {
			this.reviewed_by = reviewed_by;
		}
		public String getReviewed_date() {
			return reviewed_date;
		}
		public void setReviewed_date(String reviewed_date) {
			this.reviewed_date = reviewed_date;
		}
		public String getPhysician_comments() {
			return physician_comments;
		}
		public void setPhysician_comments(String physician_comments) {
			this.physician_comments = physician_comments;
		}
		public String getFollowup_notes() {
			return followup_notes;
		}
		public void setFollowup_notes(String followup_notes) {
			this.followup_notes = followup_notes;
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
		public String getLab_comments() {
			return lab_comments;
		}
		public void setLab_comments(String lab_comments) {
			this.lab_comments = lab_comments;
		}
		public String getCollection_date() {
			return collection_date;
		}
		public void setCollection_date(String collection_date) {
			this.collection_date = collection_date;
		}
		public boolean isChange_med() {
			return change_med;
		}
		public void setChange_med(boolean change_med) {
			this.change_med = change_med;
		}
		public String getFollow_up_action() {
			return follow_up_action;
		}
		public void setFollow_up_action(String follow_up_action) {
			this.follow_up_action = follow_up_action;
		}
		public String getFollowup_detail() {
			return followup_detail;
		}
		public void setFollowup_detail(String followup_detail) {
			this.followup_detail = followup_detail;
		}
		public String getLab_dir() {
			return lab_dir;
		}
		public void setLab_dir(String lab_dir) {
			this.lab_dir = lab_dir;
		}
		public Boolean getEducation_provided() {
			return education_provided;
		}
		public void setEducation_provided(Boolean education_provided) {
			this.education_provided = education_provided;
		}
		public String getPhy_comments_date() {
			return phy_comments_date;
		}
		public void setPhy_comments_date(String phy_comments_date) {
			this.phy_comments_date = phy_comments_date;
		}
		public String getResult_type() {
			return result_type;
		}
		public void setResult_type(String result_type) {
			this.result_type = result_type;
		}
		@Override
		public String toString() {
			return "ORMLabResult [result_id=" + result_id + ", order_id=" + order_id + ", test_id=" + test_id
					+ ", result_code=" + result_code + ", result_description=" + result_description + ", result_value="
					+ result_value + ", result_value_unit=" + result_value_unit + ", recomended_value="
					+ recomended_value + ", result_status=" + result_status + ", result_status_code="
					+ result_status_code + ", abnormal_range_code=" + abnormal_range_code + ", abnormal_range="
					+ abnormal_range + ", observation_date=" + observation_date + ", assigned_to=" + assigned_to
					+ ", reviewed_by=" + reviewed_by + ", reviewed_date=" + reviewed_date + ", physician_comments="
					+ physician_comments + ", followup_notes=" + followup_notes + ", created_user=" + created_user
					+ ", client_date_created=" + client_date_created + ", modified_user=" + modified_user
					+ ", client_date_modified=" + client_date_modified + ", date_created=" + date_created
					+ ", date_modified=" + date_modified + ", lab_comments=" + lab_comments + ", collection_date="
					+ collection_date + ", change_med=" + change_med + ", follow_up_action=" + follow_up_action
					+ ", followup_detail=" + followup_detail + ", lab_dir=" + lab_dir + ", education_provided="
					+ education_provided + ", phy_comments_date=" + phy_comments_date + ", result_type=" + result_type
					+ "]";
		}

	    
	    
}
