package com.ihc.ehr.model;

import java.util.List;

public class WrapperAssessPlanSave {

	private ORMAssessPlanSave assess_plan;
	private List<ORMAssessPlanAssessmentSave> lst_assess_plan_assessments;
	private List<ORMAssessPlanOfTreatementSave> lst_assess_plan_pot;
	
	private List<Long> lst_assess_plan_assessments_deleted_ids;
	private List<Long> lst_assess_plan_pot_deleted_ids;
	
	
	public ORMAssessPlanSave getAssess_plan() {
		return assess_plan;
	}
	public void setAssess_plan(ORMAssessPlanSave assess_plan) {
		this.assess_plan = assess_plan;
	}
	public List<ORMAssessPlanAssessmentSave> getLst_assess_plan_assessments() {
		return lst_assess_plan_assessments;
	}
	public void setLst_assess_plan_assessments(List<ORMAssessPlanAssessmentSave> lst_assess_plan_assessments) {
		this.lst_assess_plan_assessments = lst_assess_plan_assessments;
	}
	public List<ORMAssessPlanOfTreatementSave> getLst_assess_plan_pot() {
		return lst_assess_plan_pot;
	}
	public void setLst_assess_plan_pot(List<ORMAssessPlanOfTreatementSave> lst_assess_plan_pot) {
		this.lst_assess_plan_pot = lst_assess_plan_pot;
	}
	public List<Long> getLst_assess_plan_assessments_deleted_ids() {
		return lst_assess_plan_assessments_deleted_ids;
	}
	public void setLst_assess_plan_assessments_deleted_ids(List<Long> lst_assess_plan_assessments_deleted_ids) {
		this.lst_assess_plan_assessments_deleted_ids = lst_assess_plan_assessments_deleted_ids;
	}
	public List<Long> getLst_assess_plan_pot_deleted_ids() {
		return lst_assess_plan_pot_deleted_ids;
	}
	public void setLst_assess_plan_pot_deleted_ids(List<Long> lst_assess_plan_pot_deleted_ids) {
		this.lst_assess_plan_pot_deleted_ids = lst_assess_plan_pot_deleted_ids;
	}
}
