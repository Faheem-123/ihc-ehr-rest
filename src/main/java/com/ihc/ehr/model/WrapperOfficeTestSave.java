package com.ihc.ehr.model;

import java.util.List;

public class WrapperOfficeTestSave {
	ORMChartOfficeTest officeTestSave;
	List<ORMChartOfficetest_cpt> cptSave;
	
	public ORMChartOfficeTest getOfficeTestSave() {
		return officeTestSave;
	}
	public void setOfficeTestSave(ORMChartOfficeTest officeTestSave) {
		this.officeTestSave = officeTestSave;
	}
	public List<ORMChartOfficetest_cpt> getCptSave() {
		return cptSave;
	}
	public void setCptSave(List<ORMChartOfficetest_cpt> cptSave) {
		this.cptSave = cptSave;
	}
}
