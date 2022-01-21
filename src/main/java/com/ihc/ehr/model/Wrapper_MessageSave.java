package com.ihc.ehr.model;

import java.util.List;

public class Wrapper_MessageSave {
	ORMMessageInsert messageSave_Pro;
	List<ORMMessageDetailInsert> lstMessageDetails;
	
	public ORMMessageInsert getMessageSave_Pro() {
		return messageSave_Pro;
	}
	public void setMessageSave_Pro(ORMMessageInsert messageSave_Pro) {
		this.messageSave_Pro = messageSave_Pro;
	}
	public List<ORMMessageDetailInsert> getLstMessageDetails() {
		return lstMessageDetails;
	}
	public void setLstMessageDetails(List<ORMMessageDetailInsert> lstMessageDetails) {
		this.lstMessageDetails = lstMessageDetails;
	}
}
