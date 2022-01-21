package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMLabRequisitionProc {

	 @Id
	    private String test_id;
	    private String proc_code;
	    private String proc_description;
	    private String units;
	    private String test_instructions;
	    private String source;
	    private String volume;
	    private String lab_assigned_cpt;
	    private String lab_question_txt;
	    private String lab_category_id;
	    private String aoe_segment;
		public String getTest_id() {
			return test_id;
		}
		public void setTest_id(String test_id) {
			this.test_id = test_id;
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
		public String getUnits() {
			return units;
		}
		public void setUnits(String units) {
			this.units = units;
		}
		public String getTest_instructions() {
			return test_instructions;
		}
		public void setTest_instructions(String test_instructions) {
			this.test_instructions = test_instructions;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getVolume() {
			return volume;
		}
		public void setVolume(String volume) {
			this.volume = volume;
		}
		public String getLab_assigned_cpt() {
			return lab_assigned_cpt;
		}
		public void setLab_assigned_cpt(String lab_assigned_cpt) {
			this.lab_assigned_cpt = lab_assigned_cpt;
		}
		public String getLab_question_txt() {
			return lab_question_txt;
		}
		public void setLab_question_txt(String lab_question_txt) {
			this.lab_question_txt = lab_question_txt;
		}
		public String getLab_category_id() {
			return lab_category_id;
		}
		public void setLab_category_id(String lab_category_id) {
			this.lab_category_id = lab_category_id;
		}
		public String getAoe_segment() {
			return aoe_segment;
		}
		public void setAoe_segment(String aoe_segment) {
			this.aoe_segment = aoe_segment;
		}
	    
	    
}
