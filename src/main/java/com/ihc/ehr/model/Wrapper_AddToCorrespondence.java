package com.ihc.ehr.model;

import java.util.List;

public class Wrapper_AddToCorrespondence {
	ORMHealthInformationCapture lstHealthInfoCapture;
	
	List<ORMHealthInformationCaptureAttachments> lstHealthInfoAttachments;
	List<ORMKeyValue> lstOther;
	
	public ORMHealthInformationCapture getLstHealthInfoCapture() {
		return lstHealthInfoCapture;
	}
	public void setLstHealthInfoCapture(ORMHealthInformationCapture lstHealthInfoCapture) {
		this.lstHealthInfoCapture = lstHealthInfoCapture;
	}
	public List<ORMHealthInformationCaptureAttachments> getLstHealthInfoAttachments() {
		return lstHealthInfoAttachments;
	}
	public void setLstHealthInfoAttachments(List<ORMHealthInformationCaptureAttachments> lstHealthInfoAttachments) {
		this.lstHealthInfoAttachments = lstHealthInfoAttachments;
	}
	public List<ORMKeyValue> getLstOther() {
		return lstOther;
	}
	public void setLstOther(List<ORMKeyValue> lstOther) {
		this.lstOther = lstOther;
	}
}
