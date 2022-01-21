package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMLabResultStaffNotes {

	 @Id
	    private String row_id;
	    private String notes_id;
	    private String result_id;
	    private String notes;
	    private String test_id;
		public String getRow_id() {
			return row_id;
		}
		public void setRow_id(String row_id) {
			this.row_id = row_id;
		}
		public String getNotes_id() {
			return notes_id;
		}
		public void setNotes_id(String notes_id) {
			this.notes_id = notes_id;
		}
		public String getResult_id() {
			return result_id;
		}
		public void setResult_id(String result_id) {
			this.result_id = result_id;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
		public String getTest_id() {
			return test_id;
		}
		public void setTest_id(String test_id) {
			this.test_id = test_id;
		}
	    
	    
}
