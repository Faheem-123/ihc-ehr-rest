package com.ihc.ehr.model;

import java.util.List;

public class Wrapper_AppointmentsWithTiming {
	
	List<ORMGetSchedulerAppointments> lstAppointments;
	List<ORMSchedulerTiming> lstTiming;
	List<ORMKeyValue> lstTimingInfo;
	
	public List<ORMGetSchedulerAppointments> getLstAppointments() {
		return lstAppointments;
	}
	public void setLstAppointments(List<ORMGetSchedulerAppointments> lstAppointments) {
		this.lstAppointments = lstAppointments;
	}
	public List<ORMSchedulerTiming> getLstTiming() {
		return lstTiming;
	}
	public void setLstTiming(List<ORMSchedulerTiming> lstTiming) {
		this.lstTiming = lstTiming;
	}
	public List<ORMKeyValue> getLstTimingInfo() {
		return lstTimingInfo;
	}
	public void setLstTimingInfo(List<ORMKeyValue> lstTimingInfo) {
		this.lstTimingInfo = lstTimingInfo;
	}
	
	
	
	
}
