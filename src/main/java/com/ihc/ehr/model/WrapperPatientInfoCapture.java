package com.ihc.ehr.model;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class WrapperPatientInfoCapture {
	ORMHealthInformationCapture objHealthInfoCapture;
	List<ORM_HealthInformationCaptureAttachments> arrHealthInfoAttachmentsLinks;
	private String path;
	
	public ORMHealthInformationCapture getObjHealthInfoCapture() {
		return objHealthInfoCapture;
	}
	public void setObjHealthInfoCapture(ORMHealthInformationCapture objHealthInfoCapture) {
		this.objHealthInfoCapture = objHealthInfoCapture;
	}
	public List<ORM_HealthInformationCaptureAttachments> getArrHealthInfoAttachmentsLinks() {
		return arrHealthInfoAttachmentsLinks;
	}
	public void setArrHealthInfoAttachmentsLinks(
			List<ORM_HealthInformationCaptureAttachments> arrHealthInfoAttachmentsLinks) {
		this.arrHealthInfoAttachmentsLinks = arrHealthInfoAttachmentsLinks;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}