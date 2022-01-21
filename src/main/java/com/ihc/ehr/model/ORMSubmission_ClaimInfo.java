package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMSubmission_ClaimInfo  {

    @Id
    private String claim_id = "";
    private String claim_number = "";
    private String appointment_id = "" ;
    private String patient_id = "" ;
   
    private String lname = "" ;
    private String fname = "" ;
    private String mname = "" ;
    private String gender = "" ;
    private String address = "" ;
    private String city = "" ;
    private String state = "" ;
    private String zip = "" ;
    private String dob = "" ;
    private String marital_status = "";
    private String pat_phone = "";
    private String first_visit_date = "" ;
    private String location_id = "" ;
    private String attending_physician = "" ;
    
    private String billing_physician = "" ;    
    private String bl_lname = "" ;
    private String bl_fname = "" ;
    private String bl_mi = "" ;
    private String bl_organization_name = "" ;
    private String bl_npi = "" ;
    private String bl_group_npi = "" ;
    private String billingprovidertype = "" ;
    private String bl_address = "" ;
    private String bl_city = "" ;
    private String bl_state = "" ;
    private String bl_zip = "" ;
    
    private String submitter_id = "" ;
    private String company_name = "";
    private String contact_person = "";
    private String company_phone = "";
    private String company_email = "";    
    private String federal_taxid = "" ;
    private String federal_taxidnumbertype = "" ;
    private String phone_no = "" ;
    
    private String att_lname = "" ;
    private String att_fname = "" ;
    private String att_mi = "" ;
    private String attendingprovidertype = "" ;
    private String att_organization_name = "" ;
    private String att_npi = "" ;
    private String att_federaltaxidnumber = "" ;
    private String att_federaltaxidnumbertype = "" ;
    private String att_address = "" ;
    private String att_city = "" ;
    private String att_state = "" ;
    private String att_zip = "" ;
    private String att_taxonomy_code = "" ;
    private String facility_code = "" ;
    private String ref_lname = "" ;
    private String ref_fname = "" ;
    private String ref_npi = "" ;
    private String ref_upin = "" ;
    private String taxonomy_code = "" ;
    private String reffering_type = "" ;
    private String referring_physician = "" ;
    private String dos = "" ;
    private String claim_pos = "" ;
    private String bill_date = "" ;
    private Boolean self_pay  = false;
    private BigDecimal claim_total = new BigDecimal(0);
    private BigDecimal amt_paid = new BigDecimal(0);
    private BigDecimal amt_due = new BigDecimal(0);
    private BigDecimal pri_paid = new BigDecimal(0);
    private BigDecimal sec_paid = new BigDecimal(0);
    private BigDecimal patient_paid = new BigDecimal(0);
    private String referral_number = "" ;
    private String rebill_date = "" ;
    private String prior_authorization = "" ;
    private String luo = "" ;
    private String paper_payer_id = "" ;
    private String hcfa_17_a = "" ;
    private Boolean is_resubmitted = false;
    private Boolean is_corrected = false;
    private String medical_resubmision_code = "" ;
    private String practice_id = "" ;
    private String accident_type = "" ;
    private String accident_date = "" ;
    private String accident_state = "" ;
    private Boolean accident_other = false ;
    private Boolean accident_auto  = false;
    private Boolean accident_emergency = false ;
//    private Boolean accident = false;
    private Boolean employment = false;
    private String hospital_from = "" ;
    private String hospital_to = "" ;
    private String clia_number = "" ;
    private String clia_expiry_date = "" ;
    private String facility_name = "" ;
    private String facility_address = "" ;
    private String facility_city = "" ;
    private String facility_state = "" ;
    private String facility_zip = "" ;
    private String facility_npi = "";
    private String last_xray_date = "" ;
    private String lmp_date = "";
    private String start_care_date="";
    private String ref_taxonomy_code="";
    private Boolean accept_assignment = false;
    private String att_title = "";
    private String bl_title = "";
    private Boolean lab_claim = false;
    private Boolean outside_lab = false;
    private BigDecimal outside_lab_charges = new BigDecimal(0);
    private String grp_taxonomy_id="";
    private String att_state_license;
    private Boolean icd_10_claim;
    private String tpi;
    private String group_tpi;
    private String bill_type_code;    
    
    private String operating_physician = "" ;
    private String operating_lname = "" ;
    private String operating_fname = "" ;
    private String operating_npi = "" ;
    
    private String pay_to_address = "" ;
    private String pay_to_city = "" ;
    private String pay_to_state = "" ;
    private String pay_to_zip = "" ;
    
    
    private String pay_to_address_grp;
    private String pay_to_city_grp;
    private String pay_to_state_grp;
    private String pay_to_zip_grp;

    private String bill_address_grp;
    private String bill_zip_grp;
    private String bill_city_grp;
    private String bill_state_grp;
        
    private Boolean eap;
    
    private String pri_status;
	private String sec_status;
	private String oth_status;
	
	private String billing_provider_taxonomy;

    public Boolean getIcd_10_claim() {
        return icd_10_claim;
    }

    public void setIcd_10_claim(Boolean icd_10_claim) {
        this.icd_10_claim = icd_10_claim;
    }

    public String getAtt_state_license() {
        return att_state_license;
    }

    public void setAtt_state_license(String att_state_license) {
        this.att_state_license = att_state_license;
    }

    public String getGrp_taxonomy_id() {
        return grp_taxonomy_id;
    }

    public void setGrp_taxonomy_id(String grp_taxonomy_id) {
        this.grp_taxonomy_id = grp_taxonomy_id;
    }
    

    public Boolean getOutside_lab() {
        return outside_lab;
    }

    public void setOutside_lab(Boolean outside_lab) {
        this.outside_lab = outside_lab;
    }

    public BigDecimal getOutside_lab_charges() {
        return outside_lab_charges;
    }

    public void setOutside_lab_charges(BigDecimal outside_lab_charges) {
        this.outside_lab_charges = outside_lab_charges;
    }

    public Boolean getLab_claim() {
        return lab_claim;
    }

    public void setLab_claim(Boolean lab_claim) {
        this.lab_claim = lab_claim;
    }

    public String getFacility_npi() {
        return facility_npi;
    }

    public void setFacility_npi(String facility_npi) {
        this.facility_npi = facility_npi;
    }

    public String getAtt_title() {
        return att_title;
    }

    public void setAtt_title(String att_title) {
        this.att_title = att_title;
    }

    public String getBl_title() {
        return bl_title;
    }

    public void setBl_title(String bl_title) {
        this.bl_title = bl_title;
    }

    public BigDecimal getAmt_due() {
        return amt_due;
    }

    public void setAmt_due(BigDecimal amt_due) {
        this.amt_due = amt_due;
    }

    public BigDecimal getPri_paid() {
        return pri_paid;
    }

    public void setPri_paid(BigDecimal pri_paid) {
        this.pri_paid = pri_paid;
    }

    public BigDecimal getSec_paid() {
        return sec_paid;
    }

    public void setSec_paid(BigDecimal sec_paid) {
        this.sec_paid = sec_paid;
    }

    public BigDecimal getPatient_paid() {
        return patient_paid;
    }

    public void setPatient_paid(BigDecimal patient_paid) {
        this.patient_paid = patient_paid;
    }

    public Boolean getAccept_assignment() {
        return accept_assignment;
    }

    public void setAccept_assignment(Boolean accept_assignment) {
        this.accept_assignment = accept_assignment;
    }

    public String getRef_taxonomy_code() {
        return ref_taxonomy_code;
    }

    public void setRef_taxonomy_code(String ref_taxonomy_code) {
        this.ref_taxonomy_code = ref_taxonomy_code;
    }
    
   

    public String getStart_care_date() {
		return start_care_date;
	}

	public void setStart_care_date(String start_care_date) {
		this.start_care_date = start_care_date;
	}

	public String getLmp_date() {
        return lmp_date;
    }

    public void setLmp_date(String lmp_date) {
        this.lmp_date = lmp_date;
    }

    public Boolean getEmployment() {
        return employment;
    }

    public void setEmployment(Boolean employment) {
        this.employment = employment;
    }
//
//    public Boolean getAccident() {
//        return accident;
//    }
//
//    public void setAccident(Boolean accident) {
//        this.accident = accident;
//    }

    public Boolean getAccident_auto() {
        return accident_auto;
    }

    public void setAccident_auto(Boolean accident_auto) {
        this.accident_auto = accident_auto;
    }

    public String getAccident_date() {
        return accident_date;
    }

    public void setAccident_date(String accident_date) {
        this.accident_date = accident_date;
    }

    public Boolean getAccident_emergency() {
        return accident_emergency;
    }

    public void setAccident_emergency(Boolean accident_emergency) {
        this.accident_emergency = accident_emergency;
    }

    public Boolean getAccident_other() {
        return accident_other;
    }

    public void setAccident_other(Boolean accident_other) {
        this.accident_other = accident_other;
    }

    public String getAccident_state() {
        return accident_state;
    }

    public void setAccident_state(String accident_state) {
        this.accident_state = accident_state;
    }

    public String getAccident_type() {
        return accident_type;
    }

    public void setAccident_type(String accident_type) {
        this.accident_type = accident_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAmt_paid() {
        return amt_paid;
    }

    public void setAmt_paid(BigDecimal amt_paid) {
        this.amt_paid = amt_paid;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getAtt_address() {
        return att_address;
    }

    public void setAtt_address(String att_address) {
        this.att_address = att_address;
    }

    public String getAtt_city() {
        return att_city;
    }

    public void setAtt_city(String att_city) {
        this.att_city = att_city;
    }

    public String getAtt_federaltaxidnumber() {
        return att_federaltaxidnumber;
    }

    public void setAtt_federaltaxidnumber(String att_federaltaxidnumber) {
        this.att_federaltaxidnumber = att_federaltaxidnumber;
    }

    public String getAtt_federaltaxidnumbertype() {
        return att_federaltaxidnumbertype;
    }

    public void setAtt_federaltaxidnumbertype(String att_federaltaxidnumbertype) {
        this.att_federaltaxidnumbertype = att_federaltaxidnumbertype;
    }

    public String getAtt_fname() {
        return att_fname;
    }

    public void setAtt_fname(String att_fname) {
        this.att_fname = att_fname;
    }

    public String getAtt_lname() {
        return att_lname;
    }

    public void setAtt_lname(String att_lname) {
        this.att_lname = att_lname;
    }

    public String getAtt_mi() {
        return att_mi;
    }

    public void setAtt_mi(String att_mi) {
        this.att_mi = att_mi;
    }

    public String getAtt_npi() {
        return att_npi;
    }

    public void setAtt_npi(String att_npi) {
        this.att_npi = att_npi;
    }

    public String getAtt_organization_name() {
        return att_organization_name;
    }

    public void setAtt_organization_name(String att_organization_name) {
        this.att_organization_name = att_organization_name;
    }

    public String getAtt_state() {
        return att_state;
    }

    public void setAtt_state(String att_state) {
        this.att_state = att_state;
    }

    public String getAtt_taxonomy_code() {
        return att_taxonomy_code;
    }

    public void setAtt_taxonomy_code(String att_taxonomy_code) {
        this.att_taxonomy_code = att_taxonomy_code;
    }

    public String getAtt_zip() {
        return att_zip;
    }

    public void setAtt_zip(String att_zip) {
        this.att_zip = att_zip;
    }

    public String getAttending_physician() {
        return attending_physician;
    }

    public void setAttending_physician(String attending_physician) {
        this.attending_physician = attending_physician;
    }

    public String getAttendingprovidertype() {
        return attendingprovidertype;
    }

    public void setAttendingprovidertype(String attendingprovidertype) {
        this.attendingprovidertype = attendingprovidertype;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getBilling_physician() {
        return billing_physician;
    }

    public void setBilling_physician(String billing_physician) {
        this.billing_physician = billing_physician;
    }

    public String getBillingprovidertype() {
        return billingprovidertype;
    }

    public void setBillingprovidertype(String billingprovidertype) {
        this.billingprovidertype = billingprovidertype;
    }

    public String getBl_address() {
        return bl_address;
    }

    public void setBl_address(String bl_address) {
        this.bl_address = bl_address;
    }

    public String getBl_city() {
        return bl_city;
    }

    public void setBl_city(String bl_city) {
        this.bl_city = bl_city;
    }

    public String getBl_fname() {
        return bl_fname;
    }

    public void setBl_fname(String bl_fname) {
        this.bl_fname = bl_fname;
    }

    public String getBl_lname() {
        return bl_lname;
    }

    public void setBl_lname(String bl_lname) {
        this.bl_lname = bl_lname;
    }

    public String getBl_mi() {
        return bl_mi;
    }

    public void setBl_mi(String bl_mi) {
        this.bl_mi = bl_mi;
    }

    public String getBl_npi() {
        return bl_npi;
    }

    public void setBl_npi(String bl_npi) {
        this.bl_npi = bl_npi;
    }

    public String getBl_organization_name() {
        return bl_organization_name;
    }

    public void setBl_organization_name(String bl_organization_name) {
        this.bl_organization_name = bl_organization_name;
    }

    public String getBl_state() {
        return bl_state;
    }

    public void setBl_state(String bl_state) {
        this.bl_state = bl_state;
    }

    public String getBl_zip() {
        return bl_zip;
    }

    public void setBl_zip(String bl_zip) {
        this.bl_zip = bl_zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(String claim_id) {
        this.claim_id = claim_id;
    }

    public String getClaim_pos() {
        return claim_pos;
    }

    public void setClaim_pos(String claim_pos) {
        this.claim_pos = claim_pos;
    }

    public BigDecimal getClaim_total() {
        return claim_total;
    }

    public void setClaim_total(BigDecimal claim_total) {
        this.claim_total = claim_total;
    }

    public String getClia_expiry_date() {
        return clia_expiry_date;
    }

    public void setClia_expiry_date(String clia_expiry_date) {
        this.clia_expiry_date = clia_expiry_date;
    }

    public String getClia_number() {
        return clia_number;
    }

    public void setClia_number(String clia_number) {
        this.clia_number = clia_number;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    
    public String getPat_phone() {
		return pat_phone;
	}

	public void setPat_phone(String pat_phone) {
		this.pat_phone = pat_phone;
	}

	public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getFacility_address() {
        return facility_address;
    }

    public void setFacility_address(String facility_address) {
        this.facility_address = facility_address;
    }

    public String getFacility_city() {
        return facility_city;
    }

    public void setFacility_city(String facility_city) {
        this.facility_city = facility_city;
    }

    public String getFacility_code() {
        return facility_code;
    }

    public void setFacility_code(String facility_code) {
        this.facility_code = facility_code;
    }

    public String getFacility_name() {
        return facility_name;
    }

    public void setFacility_name(String facility_name) {
        this.facility_name = facility_name;
    }

    public String getFacility_state() {
        return facility_state;
    }

    public void setFacility_state(String facility_state) {
        this.facility_state = facility_state;
    }

    public String getFacility_zip() {
        return facility_zip;
    }

    public void setFacility_zip(String facility_zip) {
        this.facility_zip = facility_zip;
    }

    public String getFederal_taxid() {
        return federal_taxid;
    }

    public void setFederal_taxid(String federal_taxid) {
        this.federal_taxid = federal_taxid;
    }

    public String getFederal_taxidnumbertype() {
        return federal_taxidnumbertype;
    }

    public void setFederal_taxidnumbertype(String federal_taxidnumbertype) {
        this.federal_taxidnumbertype = federal_taxidnumbertype;
    }

    public String getFirst_visit_date() {
        return first_visit_date;
    }

    public void setFirst_visit_date(String first_visit_date) {
        this.first_visit_date = first_visit_date;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSubmitter_id() {
        return submitter_id;
    }

    public void setSubmitter_id(String submitter_id) {
        this.submitter_id = submitter_id;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHcfa_17_a() {
        return hcfa_17_a;
    }

    public void setHcfa_17_a(String hcfa_17_a) {
        this.hcfa_17_a = hcfa_17_a;
    }

    public String getHospital_from() {
        return hospital_from;
    }

    public void setHospital_from(String hospital_from) {
        this.hospital_from = hospital_from;
    }

    public String getHospital_to() {
        return hospital_to;
    }

    public void setHospital_to(String hospital_to) {
        this.hospital_to = hospital_to;
    }

    public Boolean getIs_corrected() {
        return is_corrected;
    }

    public void setIs_corrected(Boolean is_corrected) {
        this.is_corrected = is_corrected;
    }

    public Boolean getIs_resubmitted() {
        return is_resubmitted;
    }

    public void setIs_resubmitted(Boolean is_resubmitted) {
        this.is_resubmitted = is_resubmitted;
    }

    public String getLast_xray_date() {
        return last_xray_date;
    }

    public void setLast_xray_date(String last_xray_date) {
        this.last_xray_date = last_xray_date;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getLuo() {
        return luo;
    }

    public void setLuo(String luo) {
        this.luo = luo;
    }

    public String getMedical_resubmision_code() {
        return medical_resubmision_code;
    }

    public void setMedical_resubmision_code(String medical_resubmision_code) {
        this.medical_resubmision_code = medical_resubmision_code;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getPaper_payer_id() {
        return paper_payer_id;
    }

    public void setPaper_payer_id(String paper_payer_id) {
        this.paper_payer_id = paper_payer_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getPractice_id() {
        return practice_id;
    }

    public void setPractice_id(String practice_id) {
        this.practice_id = practice_id;
    }

    public String getPrior_authorization() {
        return prior_authorization;
    }

    public void setPrior_authorization(String prior_authorization) {
        this.prior_authorization = prior_authorization;
    }

    public String getRebill_date() {
        return rebill_date;
    }

    public void setRebill_date(String rebill_date) {
        this.rebill_date = rebill_date;
    }

    public String getRef_fname() {
        return ref_fname;
    }

    public void setRef_fname(String ref_fname) {
        this.ref_fname = ref_fname;
    }

    public String getRef_lname() {
        return ref_lname;
    }

    public void setRef_lname(String ref_lname) {
        this.ref_lname = ref_lname;
    }

    public String getRef_npi() {
        return ref_npi;
    }

    public void setRef_npi(String ref_npi) {
        this.ref_npi = ref_npi;
    }

    public String getRef_upin() {
        return ref_upin;
    }

    public void setRef_upin(String ref_upin) {
        this.ref_upin = ref_upin;
    }

    public String getReferral_number() {
        return referral_number;
    }

    public void setReferral_number(String referral_number) {
        this.referral_number = referral_number;
    }

    public String getReferring_physician() {
        return referring_physician;
    }

    public void setReferring_physician(String referring_physician) {
        this.referring_physician = referring_physician;
    }

    public String getReffering_type() {
        return reffering_type;
    }

    public void setReffering_type(String reffering_type) {
        this.reffering_type = reffering_type;
    }

    public Boolean getSelf_pay() {
        return self_pay;
    }

    public void setSelf_pay(Boolean self_pay) {
        this.self_pay = self_pay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTaxonomy_code() {
        return taxonomy_code;
    }

    public void setTaxonomy_code(String taxonomy_code) {
        this.taxonomy_code = taxonomy_code;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getClaim_number() {
        return claim_number;
    }

    public void setClaim_number(String claim_number) {
        this.claim_number = claim_number;
    }

    public String getTpi() {
        return tpi;
    }

    public void setTpi(String tpi) {
        this.tpi = tpi;
    }

    public String getGroup_tpi() {
        return group_tpi;
    }

    public void setGroup_tpi(String group_tpi) {
        this.group_tpi = group_tpi;
    }

    public String getBill_type_code() {
        return bill_type_code;
    }

    public void setBill_type_code(String bill_type_code) {
        this.bill_type_code = bill_type_code;
    }

    public String getOperating_lname() {
        return operating_lname;
    }

    public void setOperating_lname(String operating_lname) {
        this.operating_lname = operating_lname;
    }

    public String getOperating_fname() {
        return operating_fname;
    }

    public void setOperating_fname(String operating_fname) {
        this.operating_fname = operating_fname;
    }

    public String getOperating_npi() {
        return operating_npi;
    }

    public void setOperating_npi(String operating_npi) {
        this.operating_npi = operating_npi;
    }

    public String getOperating_physician() {
        return operating_physician;
    }

    public void setOperating_physician(String operating_physician) {
        this.operating_physician = operating_physician;
    }

    public String getPay_to_address() {
        return pay_to_address;
    }

    public void setPay_to_address(String pay_to_address) {
        this.pay_to_address = pay_to_address;
    }

    public String getPay_to_city() {
        return pay_to_city;
    }

    public void setPay_to_city(String pay_to_city) {
        this.pay_to_city = pay_to_city;
    }

    public String getPay_to_state() {
        return pay_to_state;
    }

    public void setPay_to_state(String pay_to_state) {
        this.pay_to_state = pay_to_state;
    }

    public String getPay_to_zip() {
        return pay_to_zip;
    }

    public void setPay_to_zip(String pay_to_zip) {
        this.pay_to_zip = pay_to_zip;
    }

    public String getBl_group_npi() {
        return bl_group_npi;
    }

    public void setBl_group_npi(String bl_group_npi) {
        this.bl_group_npi = bl_group_npi;
    }

    public String getPay_to_address_grp() {
        return pay_to_address_grp;
    }

    public void setPay_to_address_grp(String pay_to_address_grp) {
        this.pay_to_address_grp = pay_to_address_grp;
    }

    public String getPay_to_city_grp() {
        return pay_to_city_grp;
    }

    public void setPay_to_city_grp(String pay_to_city_grp) {
        this.pay_to_city_grp = pay_to_city_grp;
    }

    public String getPay_to_state_grp() {
        return pay_to_state_grp;
    }

    public void setPay_to_state_grp(String pay_to_state_grp) {
        this.pay_to_state_grp = pay_to_state_grp;
    }

    public String getPay_to_zip_grp() {
        return pay_to_zip_grp;
    }

    public void setPay_to_zip_grp(String pay_to_zip_grp) {
        this.pay_to_zip_grp = pay_to_zip_grp;
    }

    public String getBill_address_grp() {
        return bill_address_grp;
    }

    public void setBill_address_grp(String bill_address_grp) {
        this.bill_address_grp = bill_address_grp;
    }

    public String getBill_zip_grp() {
        return bill_zip_grp;
    }

    public void setBill_zip_grp(String bill_zip_grp) {
        this.bill_zip_grp = bill_zip_grp;
    }

    public String getBill_city_grp() {
        return bill_city_grp;
    }

    public void setBill_city_grp(String bill_city_grp) {
        this.bill_city_grp = bill_city_grp;
    }

    public String getBill_state_grp() {
        return bill_state_grp;
    }

    public void setBill_state_grp(String bill_state_grp) {
        this.bill_state_grp = bill_state_grp;
    }

	public Boolean getEap() {
		return eap;
	}

	public void setEap(Boolean eap) {
		this.eap = eap;
	}

	public String getPri_status() {
		return pri_status;
	}

	public void setPri_status(String pri_status) {
		this.pri_status = pri_status;
	}

	public String getSec_status() {
		return sec_status;
	}

	public void setSec_status(String sec_status) {
		this.sec_status = sec_status;
	}

	public String getOth_status() {
		return oth_status;
	}

	public void setOth_status(String oth_status) {
		this.oth_status = oth_status;
	}

	public String getBilling_provider_taxonomy() {
		return billing_provider_taxonomy;
	}

	public void setBilling_provider_taxonomy(String billing_provider_taxonomy) {
		this.billing_provider_taxonomy = billing_provider_taxonomy;
	}

   
}
