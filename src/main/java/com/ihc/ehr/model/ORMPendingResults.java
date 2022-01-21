package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMPendingResults {

	 @Id
	    private String result_id;
	    private String order_id;
	    private String order_date;
	    private String patient_name;    
	    private String patient_id;
	    private String test_description;
	    private String result_description;
	    private String result_value;
	    private String result_value_unit;     
	    private String recomended_value;
	    private String abnormal_range_code;
	    private String observation_date;
	    private String provider_name;
	    private String lab_collected_date;
	    private String lab_entered_date;
	    private String lab_reported_date;
	    private String physician_comments;
	    private String lab_category_id;
	    private String reviewed_by;
	    private String reviewed_date;
	    private String followup_notes;
	    private String follow_up_action;
	    private String staff_notes;
	    private String gender;
	    private String test_id;
	    
		public String getTest_id() {
			return test_id;
		}
		public void setTest_id(String test_id) {
			this.test_id = test_id;
		}
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
		public String getOrder_date() {
			return order_date;
		}
		public void setOrder_date(String order_date) {
			this.order_date = order_date;
		}
		public String getPatient_name() {
			return patient_name;
		}
		public void setPatient_name(String patient_name) {
			this.patient_name = patient_name;
		}
		public String getPatient_id() {
			return patient_id;
		}
		public void setPatient_id(String patient_id) {
			this.patient_id = patient_id;
		}
		public String getTest_description() {
			return test_description;
		}
		public void setTest_description(String test_description) {
			this.test_description = test_description;
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
		public String getAbnormal_range_code() {
			return abnormal_range_code;
		}
		public void setAbnormal_range_code(String abnormal_range_code) {
			this.abnormal_range_code = abnormal_range_code;
		}
		public String getObservation_date() {
			return observation_date;
		}
		public void setObservation_date(String observation_date) {
			this.observation_date = observation_date;
		}
		public String getProvider_name() {
			return provider_name;
		}
		public void setProvider_name(String provider_name) {
			this.provider_name = provider_name;
		}
		public String getLab_collected_date() {
			return lab_collected_date;
		}
		public void setLab_collected_date(String lab_collected_date) {
			this.lab_collected_date = lab_collected_date;
		}
		public String getLab_entered_date() {
			return lab_entered_date;
		}
		public void setLab_entered_date(String lab_entered_date) {
			this.lab_entered_date = lab_entered_date;
		}
		public String getLab_reported_date() {
			return lab_reported_date;
		}
		public void setLab_reported_date(String lab_reported_date) {
			this.lab_reported_date = lab_reported_date;
		}
		public String getPhysician_comments() {
			return physician_comments;
		}
		public void setPhysician_comments(String physician_comments) {
			this.physician_comments = physician_comments;
		}
		public String getLab_category_id() {
			return lab_category_id;
		}
		public void setLab_category_id(String lab_category_id) {
			this.lab_category_id = lab_category_id;
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
		public String getFollowup_notes() {
			return followup_notes;
		}
		public void setFollowup_notes(String followup_notes) {
			this.followup_notes = followup_notes;
		}
		public String getFollow_up_action() {
			return follow_up_action;
		}
		public void setFollow_up_action(String follow_up_action) {
			this.follow_up_action = follow_up_action;
		}
		public String getStaff_notes() {
			return staff_notes;
		}
		public void setStaff_notes(String staff_notes) {
			this.staff_notes = staff_notes;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
	    
	    
}
