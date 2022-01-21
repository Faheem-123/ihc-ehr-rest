package com.ihc.ehr.model;

import java.util.List;

public class WrapperPatientMessage {
	ORMPatientMessage objPatMsg;
	List<ORMPatientMessageDetail> objPatMsgDetail;
    //ORMPatientMessageDetail objPatMsgDetail;
	ORMMessageAttachment objMsgAttach;
	
	public ORMPatientMessage getObjPatMsg() {
		return objPatMsg;
	}
	public void setObjPatMsg(ORMPatientMessage objPatMsg) {
		this.objPatMsg = objPatMsg;
	}
	public List<ORMPatientMessageDetail> getObjPatMsgDetail() {
		return objPatMsgDetail;
	}
	public void setObjPatMsgDetail(List<ORMPatientMessageDetail> objPatMsgDetail) {
		this.objPatMsgDetail = objPatMsgDetail;
	}
	public ORMMessageAttachment getObjMsgAttach() {
		return objMsgAttach;
	}
	public void setObjMsgAttach(ORMMessageAttachment objMsgAttach) {
		this.objMsgAttach = objMsgAttach;
	}
}
