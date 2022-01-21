package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMHealthMentPrint {

	 @Id
	    private String detail_id;
	    private String phm_id;
	    private String provider_id;
	    private String location_id;
	    private String visit_date;
	    private String test_category;
	    private String test_name;
	    private String test_date;
	    private String test_value;
	    private String due_date;
	    private String refusal;
		public String getDetail_id() {
			return detail_id;
		}
		public void setDetail_id(String detail_id) {
			this.detail_id = detail_id;
		}
		public String getPhm_id() {
			return phm_id;
		}
		public void setPhm_id(String phm_id) {
			this.phm_id = phm_id;
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
		public String getVisit_date() {
			return visit_date;
		}
		public void setVisit_date(String visit_date) {
			this.visit_date = visit_date;
		}
		public String getTest_category() {
			return test_category;
		}
		public void setTest_category(String test_category) {
			this.test_category = test_category;
		}
		public String getTest_name() {
			return test_name;
		}
		public void setTest_name(String test_name) {
			this.test_name = test_name;
		}
		public String getTest_date() {
			return test_date;
		}
		public void setTest_date(String test_date) {
			this.test_date = test_date;
		}
		public String getTest_value() {
			return test_value;
		}
		public void setTest_value(String test_value) {
			this.test_value = test_value;
		}
		public String getDue_date() {
			return due_date;
		}
		public void setDue_date(String due_date) {
			this.due_date = due_date;
		}
		public String getRefusal() {
			return refusal;
		}
		public void setRefusal(String refusal) {
			this.refusal = refusal;
		}
	    
	    
}
