package com.ihc.ehr.model;

public class ORMgetPrescriptionAllergies {
	
    @Override
	public String toString() {
		return "ORMgetPrescriptionAllergies [patientid=" + patientid + ", practiceid=" + practiceid + ", loginuser="
				+ loginuser + ", rxproduction=" + rxproduction + ", rxusername=" + rxusername + ", rxpartnername="
				+ rxpartnername + ", rxuserpassword=" + rxuserpassword + ", rxsiteid=" + rxsiteid + ", prescreqstatus="
				+ prescreqstatus + ", presreqsubstatus=" + presreqsubstatus + ", loginuserid=" + loginuserid
				+ ", patinfousertype=" + patinfousertype + ", clientdatetimestring=" + clientdatetimestring
				+ ", options=" + options + ", providerid=" + providerid + "]";
	}
	private String patientid; 
	private String practiceid;
	private String loginuser;
	private boolean rxproduction;
	private String rxusername;
	private String rxpartnername;
	private String rxuserpassword;
	private String rxsiteid;
	private String prescreqstatus;
	private String presreqsubstatus;
	private String loginuserid;
	private String patinfousertype;
	private String clientdatetimestring;
	private String options;
	private Long providerid;
	
	public Long getProviderid() {
		return providerid;
	}
	public void setProviderid(Long providerid) {
		this.providerid = providerid;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getPracticeid() {
		return practiceid;
	}
	public void setPracticeid(String practiceid) {
		this.practiceid = practiceid;
	}
	public String getLoginuser() {
		return loginuser;
	}
	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}
	public boolean isRxproduction() {
		return rxproduction;
	}
	public void setRxproduction(boolean rxproduction) {
		this.rxproduction = rxproduction;
	}
	public String getRxusername() {
		return rxusername;
	}
	public void setRxusername(String rxusername) {
		this.rxusername = rxusername;
	}
	public String getRxpartnername() {
		return rxpartnername;
	}
	public void setRxpartnername(String rxpartnername) {
		this.rxpartnername = rxpartnername;
	}
	public String getRxuserpassword() {
		return rxuserpassword;
	}
	public void setRxuserpassword(String rxuserpassword) {
		this.rxuserpassword = rxuserpassword;
	}
	public String getRxsiteid() {
		return rxsiteid;
	}
	public void setRxsiteid(String rxsiteid) {
		this.rxsiteid = rxsiteid;
	}
	public String getPrescreqstatus() {
		return prescreqstatus;
	}
	public void setPrescreqstatus(String prescreqstatus) {
		this.prescreqstatus = prescreqstatus;
	}
	public String getPresreqsubstatus() {
		return presreqsubstatus;
	}
	public void setPresreqsubstatus(String presreqsubstatus) {
		this.presreqsubstatus = presreqsubstatus;
	}
	public String getLoginuserid() {
		return loginuserid;
	}
	public void setLoginuserid(String loginuserid) {
		this.loginuserid = loginuserid;
	}
	public String getPatinfousertype() {
		return patinfousertype;
	}
	public void setPatinfousertype(String patinfousertype) {
		this.patinfousertype = patinfousertype;
	}
	public String getClientdatetimestring() {
		return clientdatetimestring;
	}
	public void setClientdatetimestring(String clientdatetimestring) {
		this.clientdatetimestring = clientdatetimestring;
	}	
}
