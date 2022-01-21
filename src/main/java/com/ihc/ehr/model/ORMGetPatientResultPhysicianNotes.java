package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientResultPhysicianNotes {

	  @Id
	    private String s_no;
	    private String test_id;
	    private String order_id;
	    private String result_description;
	    private String physician_comments;
	    private Boolean is_attachement;
	    private String phy_comments_date;
		public String getS_no() {
			return s_no;
		}
		public void setS_no(String s_no) {
			this.s_no = s_no;
		}
		public String getTest_id() {
			return test_id;
		}
		public void setTest_id(String test_id) {
			this.test_id = test_id;
		}
		public String getOrder_id() {
			return order_id;
		}
		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}
		public String getResult_description() {
			return result_description;
		}
		public void setResult_description(String result_description) {
			this.result_description = result_description;
		}
		public String getPhysician_comments() {
			return physician_comments;
		}
		public void setPhysician_comments(String physician_comments) {
			this.physician_comments = physician_comments;
		}
		public Boolean getIs_attachement() {
			return is_attachement;
		}
		public void setIs_attachement(Boolean is_attachement) {
			this.is_attachement = is_attachement;
		}
		public String getPhy_comments_date() {
			return phy_comments_date;
		}
		public void setPhy_comments_date(String phy_comments_date) {
			this.phy_comments_date = phy_comments_date;
		}
	    
	    
}
