package com.ihc.ehr.model;

import java.util.List;

public class FaxAttachmentFileGenerationResponse {
	
	List<SendFaxAttachments> lst_attachment;
	String error_message;
	public List<SendFaxAttachments> getLst_attachment() {
		return lst_attachment;
	}
	public void setLst_attachment(List<SendFaxAttachments> lst_attachment) {
		this.lst_attachment = lst_attachment;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	
	

}
