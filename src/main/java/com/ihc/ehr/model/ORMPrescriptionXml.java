package com.ihc.ehr.model;

public class ORMPrescriptionXml {
	private String practice_id;
	private String patient_id;
	private String prescriber_role;
	private String rxpartnername;
	private String rxusername;
	private String rxuserpassword;
	private String rxproductname;
	private String rxproductversion;
	private String rxsiteid;
	private String practice_name;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String fax;
//user info
	private String user_id;
	private String last_name;
	private String first_name;
	private String mname;
	private String providerid;
	private String chartid;
	private String locationid;  
	private boolean addpatientinfo;
	private boolean sendrxdiagnosis;
	private String externalprescriptionid;
	private String requestedpage;
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getPrescriber_role() {
		return prescriber_role;
	}
	public void setPrescriber_role(String prescriber_role) {
		this.prescriber_role = prescriber_role;
	}
	public String getRxpartnername() {
		return rxpartnername;
	}
	public void setRxpartnername(String rxpartnername) {
		this.rxpartnername = rxpartnername;
	}
	public String getRxusername() {
		return rxusername;
	}
	public void setRxusername(String rxusername) {
		this.rxusername = rxusername;
	}
	public String getRxuserpassword() {
		return rxuserpassword;
	}
	public void setRxuserpassword(String rxuserpassword) {
		this.rxuserpassword = rxuserpassword;
	}
	public String getRxproductname() {
		return rxproductname;
	}
	public void setRxproductname(String rxproductname) {
		this.rxproductname = rxproductname;
	}
	public String getRxproductversion() {
		return rxproductversion;
	}
	public void setRxproductversion(String rxproductversion) {
		this.rxproductversion = rxproductversion;
	}
	public String getRxsiteid() {
		return rxsiteid;
	}
	public void setRxsiteid(String rxsiteid) {
		this.rxsiteid = rxsiteid;
	}
	public String getPractice_name() {
		return practice_name;
	}
	public void setPractice_name(String practice_name) {
		this.practice_name = practice_name;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getProviderid() {
		return providerid;
	}
	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}
	public String getChartid() {
		return chartid;
	}
	public void setChartid(String chartid) {
		this.chartid = chartid;
	}
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public boolean isAddpatientinfo() {
		return addpatientinfo;
	}
	public void setAddpatientinfo(boolean addpatientinfo) {
		this.addpatientinfo = addpatientinfo;
	}
	public boolean isSendrxdiagnosis() {
		return sendrxdiagnosis;
	}
	public void setSendrxdiagnosis(boolean sendrxdiagnosis) {
		this.sendrxdiagnosis = sendrxdiagnosis;
	}
	public String getExternalprescriptionid() {
		return externalprescriptionid;
	}
	public void setExternalprescriptionid(String externalprescriptionid) {
		this.externalprescriptionid = externalprescriptionid;
	}
	public String getRequestedpage() {
		return requestedpage;
	}
	public void setRequestedpage(String requestedpage) {
		this.requestedpage = requestedpage;
	}	
}
