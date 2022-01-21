package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim")
public class ORMClaimSave_Pro {

	@Id
	private Long claim_id;
	private Long practice_id;
	private Long patient_id;
	private Long location_id;
	private Long attending_physician;
	private Long billing_physician;
	private Long facility_id;
	private String referring_physician;	
	private String dos;
	private String pos;
	private String bill_date;
	private Boolean self_pay;
	private String pri_status;
	private String sec_status;
	private String oth_status;
	private String pat_status;	
	private BigDecimal pri_paid;
	private BigDecimal sec_paid;
	private BigDecimal oth_paid;
	private BigDecimal patient_paid;
	private BigDecimal allowed_amount;
	private BigDecimal claim_total;
	private BigDecimal amt_paid;
	private BigDecimal write_off;
	private BigDecimal amt_due;	
	private String pa_number;
	private Boolean employment;
	private Boolean accident;
	private String accident_type;
	private String accident_date;
	private String accident_state;
	private Boolean accident_auto;
	private Boolean accident_other;
	private Boolean accident_emergency;
	private String start_care_date;
	private String last_seen_date;
	private String current_illness_date;
	private String lmp_date;
	private String hospital_from_date;
	private String hospital_to_date;
	private String rebill_date;
	private String claim_number;
	private Boolean draft;
	private String referral_number;
	private String luo;
	private Boolean is_corrected;
	private String paper_payer_id;
	private String hcfa_17_a;
	private Boolean is_resubmitted;
	private String medical_resubmision_code;
	private Boolean is_process;
	private String eligibility_status;
	private String eligibility_inquiry_date;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private String xraydate;	
	private String listcpticd;
	private String notes;
	
	private String system_ip;
	private Boolean aa;
	private String coding_done_date;
	private String submission_status;
	private Boolean is_hcfa;
	private Boolean is_electronic;
	private Boolean epsdt;
	private Boolean lab_claim;
	private Boolean outside_lab;
	private BigDecimal outside_lab_charges;
	private Boolean not_bill;
	private Boolean followup;
	private String hcfa_ins_type;
	private Boolean icd_10_claim;
	private String bill_type_code;
	private String claim_type;
	private String eap;
    private String billing_provider_taxonomy;
	
	public Long getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(Long claim_id) {
		this.claim_id = claim_id;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	public Long getAttending_physician() {
		return attending_physician;
	}
	public void setAttending_physician(Long attending_physician) {
		this.attending_physician = attending_physician;
	}
	public Long getBilling_physician() {
		return billing_physician;
	}
	public void setBilling_physician(Long billing_physician) {
		this.billing_physician = billing_physician;
	}
	public Long getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(Long facility_id) {
		this.facility_id = facility_id;
	}
	public String getReferring_physician() {
		return referring_physician;
	}
	public void setReferring_physician(String referring_physician) {
		this.referring_physician = referring_physician;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	public Boolean getSelf_pay() {
		return self_pay;
	}
	public void setSelf_pay(Boolean self_pay) {
		this.self_pay = self_pay;
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
	public String getPat_status() {
		return pat_status;
	}
	public void setPat_status(String pat_status) {
		this.pat_status = pat_status;
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
	public BigDecimal getOth_paid() {
		return oth_paid;
	}
	public void setOth_paid(BigDecimal oth_paid) {
		this.oth_paid = oth_paid;
	}
	public BigDecimal getPatient_paid() {
		return patient_paid;
	}
	public void setPatient_paid(BigDecimal patient_paid) {
		this.patient_paid = patient_paid;
	}
	public BigDecimal getAllowed_amount() {
		return allowed_amount;
	}
	public void setAllowed_amount(BigDecimal allowed_amount) {
		this.allowed_amount = allowed_amount;
	}
	public BigDecimal getClaim_total() {
		return claim_total;
	}
	public void setClaim_total(BigDecimal claim_total) {
		this.claim_total = claim_total;
	}
	public BigDecimal getAmt_paid() {
		return amt_paid;
	}
	public void setAmt_paid(BigDecimal amt_paid) {
		this.amt_paid = amt_paid;
	}
	public BigDecimal getAmt_due() {
		return amt_due;
	}
	public void setAmt_due(BigDecimal amt_due) {
		this.amt_due = amt_due;
	}
	public String getPa_number() {
		return pa_number;
	}
	public void setPa_number(String pa_number) {
		this.pa_number = pa_number;
	}
	public Boolean getEmployment() {
		return employment;
	}
	public void setEmployment(Boolean employment) {
		this.employment = employment;
	}
	public Boolean getAccident() {
		return accident;
	}
	public void setAccident(Boolean accident) {
		this.accident = accident;
	}
	public String getAccident_type() {
		return accident_type;
	}
	public void setAccident_type(String accident_type) {
		this.accident_type = accident_type;
	}
	public String getAccident_date() {
		return accident_date;
	}
	public void setAccident_date(String accident_date) {
		this.accident_date = accident_date;
	}
	public String getAccident_state() {
		return accident_state;
	}
	public void setAccident_state(String accident_state) {
		this.accident_state = accident_state;
	}
	public Boolean getAccident_auto() {
		return accident_auto;
	}
	public void setAccident_auto(Boolean accident_auto) {
		this.accident_auto = accident_auto;
	}
	public Boolean getAccident_other() {
		return accident_other;
	}
	public void setAccident_other(Boolean accident_other) {
		this.accident_other = accident_other;
	}
	public Boolean getAccident_emergency() {
		return accident_emergency;
	}
	public void setAccident_emergency(Boolean accident_emergency) {
		this.accident_emergency = accident_emergency;
	}
	public String getStart_care_date() {
		return start_care_date;
	}
	public void setStart_care_date(String start_care_date) {
		this.start_care_date = start_care_date;
	}
	public String getLast_seen_date() {
		return last_seen_date;
	}
	public void setLast_seen_date(String last_seen_date) {
		this.last_seen_date = last_seen_date;
	}
	public String getCurrent_illness_date() {
		return current_illness_date;
	}
	public void setCurrent_illness_date(String current_illness_date) {
		this.current_illness_date = current_illness_date;
	}
	public String getLmp_date() {
		return lmp_date;
	}
	public void setLmp_date(String lmp_date) {
		this.lmp_date = lmp_date;
	}
	public String getHospital_from_date() {
		return hospital_from_date;
	}
	public void setHospital_from_date(String hospital_from_date) {
		this.hospital_from_date = hospital_from_date;
	}
	public String getHospital_to_date() {
		return hospital_to_date;
	}
	public void setHospital_to_date(String hospital_to_date) {
		this.hospital_to_date = hospital_to_date;
	}
	public String getRebill_date() {
		return rebill_date;
	}
	public void setRebill_date(String rebill_date) {
		this.rebill_date = rebill_date;
	}
	public String getClaim_number() {
		return claim_number;
	}
	public void setClaim_number(String claim_number) {
		this.claim_number = claim_number;
	}
	public Boolean getDraft() {
		return draft;
	}
	public void setDraft(Boolean draft) {
		this.draft = draft;
	}
	public String getReferral_number() {
		return referral_number;
	}
	public void setReferral_number(String referral_number) {
		this.referral_number = referral_number;
	}
	public String getLuo() {
		return luo;
	}
	public void setLuo(String luo) {
		this.luo = luo;
	}
	public Boolean getIs_corrected() {
		return is_corrected;
	}
	public void setIs_corrected(Boolean is_corrected) {
		this.is_corrected = is_corrected;
	}
	public String getPaper_payer_id() {
		return paper_payer_id;
	}
	public void setPaper_payer_id(String paper_payer_id) {
		this.paper_payer_id = paper_payer_id;
	}
	public String getHcfa_17_a() {
		return hcfa_17_a;
	}
	public void setHcfa_17_a(String hcfa_17_a) {
		this.hcfa_17_a = hcfa_17_a;
	}
	public Boolean getIs_resubmitted() {
		return is_resubmitted;
	}
	public void setIs_resubmitted(Boolean is_resubmitted) {
		this.is_resubmitted = is_resubmitted;
	}
	public String getMedical_resubmision_code() {
		return medical_resubmision_code;
	}
	public void setMedical_resubmision_code(String medical_resubmision_code) {
		this.medical_resubmision_code = medical_resubmision_code;
	}
	public Boolean getIs_process() {
		return is_process;
	}
	public void setIs_process(Boolean is_process) {
		this.is_process = is_process;
	}
	public String getEligibility_status() {
		return eligibility_status;
	}
	public void setEligibility_status(String eligibility_status) {
		this.eligibility_status = eligibility_status;
	}
	public String getEligibility_inquiry_date() {
		return eligibility_inquiry_date;
	}
	public void setEligibility_inquiry_date(String eligibility_inquiry_date) {
		this.eligibility_inquiry_date = eligibility_inquiry_date;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getXraydate() {
		return xraydate;
	}
	public void setXraydate(String xraydate) {
		this.xraydate = xraydate;
	}
	public String getListcpticd() {
		return listcpticd;
	}
	public void setListcpticd(String listcpticd) {
		this.listcpticd = listcpticd;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public BigDecimal getWrite_off() {
		return write_off;
	}
	public void setWrite_off(BigDecimal write_off) {
		this.write_off = write_off;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public Boolean getAa() {
		return aa;
	}
	public void setAa(Boolean aa) {
		this.aa = aa;
	}
	public String getCoding_done_date() {
		return coding_done_date;
	}
	public void setCoding_done_date(String coding_done_date) {
		this.coding_done_date = coding_done_date;
	}
	public String getSubmission_status() {
		return submission_status;
	}
	public void setSubmission_status(String submission_status) {
		this.submission_status = submission_status;
	}
	public Boolean getIs_hcfa() {
		return is_hcfa;
	}
	public void setIs_hcfa(Boolean is_hcfa) {
		this.is_hcfa = is_hcfa;
	}
	public Boolean getIs_electronic() {
		return is_electronic;
	}
	public void setIs_electronic(Boolean is_electronic) {
		this.is_electronic = is_electronic;
	}
	public Boolean getEpsdt() {
		return epsdt;
	}
	public void setEpsdt(Boolean epsdt) {
		this.epsdt = epsdt;
	}
	public Boolean getLab_claim() {
		return lab_claim;
	}
	public void setLab_claim(Boolean lab_claim) {
		this.lab_claim = lab_claim;
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
	public Boolean getNot_bill() {
		return not_bill;
	}
	public void setNot_bill(Boolean not_bill) {
		this.not_bill = not_bill;
	}
	public Boolean getFollowup() {
		return followup;
	}
	public void setFollowup(Boolean followup) {
		this.followup = followup;
	}
	public String getHcfa_ins_type() {
		return hcfa_ins_type;
	}
	public void setHcfa_ins_type(String hcfa_ins_type) {
		this.hcfa_ins_type = hcfa_ins_type;
	}
	public Boolean getIcd_10_claim() {
		return icd_10_claim;
	}
	public void setIcd_10_claim(Boolean icd_10_claim) {
		this.icd_10_claim = icd_10_claim;
	}
	public String getBill_type_code() {
		return bill_type_code;
	}
	public void setBill_type_code(String bill_type_code) {
		this.bill_type_code = bill_type_code;
	}
	public String getClaim_type() {
		return claim_type;
	}
	public void setClaim_type(String claim_type) {
		this.claim_type = claim_type;
	}
	public String getEap() {
		return eap;
	}
	public void setEap(String eap) {
		this.eap = eap;
	}
	public String getBilling_provider_taxonomy() {
		return billing_provider_taxonomy;
	}
	public void setBilling_provider_taxonomy(String billing_provider_taxonomy) {
		this.billing_provider_taxonomy = billing_provider_taxonomy;
	}
	@Override
	public String toString() {
		return "ORMClaimSave_Pro [claim_id=" + claim_id + ", practice_id=" + practice_id + ", patient_id=" + patient_id
				+ ", location_id=" + location_id + ", attending_physician=" + attending_physician
				+ ", billing_physician=" + billing_physician + ", facility_id=" + facility_id + ", referring_physician="
				+ referring_physician + ", dos=" + dos + ", pos=" + pos + ", bill_date=" + bill_date + ", self_pay="
				+ self_pay + ", pri_status=" + pri_status + ", sec_status=" + sec_status + ", oth_status=" + oth_status
				+ ", pat_status=" + pat_status + ", pri_paid=" + pri_paid + ", sec_paid=" + sec_paid + ", oth_paid="
				+ oth_paid + ", patient_paid=" + patient_paid + ", allowed_amount=" + allowed_amount + ", claim_total="
				+ claim_total + ", amt_paid=" + amt_paid + ", write_off=" + write_off + ", amt_due=" + amt_due
				+ ", pa_number=" + pa_number + ", employment=" + employment + ", accident=" + accident
				+ ", accident_type=" + accident_type + ", accident_date=" + accident_date + ", accident_state="
				+ accident_state + ", accident_auto=" + accident_auto + ", accident_other=" + accident_other
				+ ", accident_emergency=" + accident_emergency + ", start_care_date=" + start_care_date
				+ ", last_seen_date=" + last_seen_date + ", current_illness_date=" + current_illness_date
				+ ", lmp_date=" + lmp_date + ", hospital_from_date=" + hospital_from_date + ", hospital_to_date="
				+ hospital_to_date + ", rebill_date=" + rebill_date + ", claim_number=" + claim_number + ", draft="
				+ draft + ", referral_number=" + referral_number + ", luo=" + luo + ", is_corrected=" + is_corrected
				+ ", paper_payer_id=" + paper_payer_id + ", hcfa_17_a=" + hcfa_17_a + ", is_resubmitted="
				+ is_resubmitted + ", medical_resubmision_code=" + medical_resubmision_code + ", is_process="
				+ is_process + ", eligibility_status=" + eligibility_status + ", eligibility_inquiry_date="
				+ eligibility_inquiry_date + ", created_user=" + created_user + ", client_date_created="
				+ client_date_created + ", modified_user=" + modified_user + ", client_date_modified="
				+ client_date_modified + ", date_created=" + date_created + ", date_modified=" + date_modified
				+ ", xraydate=" + xraydate + ", listcpticd=" + listcpticd + ", notes=" + notes + ", system_ip="
				+ system_ip + ", aa=" + aa + ", coding_done_date=" + coding_done_date + ", submission_status="
				+ submission_status + ", is_hcfa=" + is_hcfa + ", is_electronic=" + is_electronic + ", epsdt=" + epsdt
				+ ", lab_claim=" + lab_claim + ", outside_lab=" + outside_lab + ", outside_lab_charges="
				+ outside_lab_charges + ", not_bill=" + not_bill + ", followup=" + followup + ", hcfa_ins_type="
				+ hcfa_ins_type + ", icd_10_claim=" + icd_10_claim + ", bill_type_code=" + bill_type_code
				+ ", claim_type=" + claim_type + ", eap=" + eap + ", billing_provider_taxonomy="
				+ billing_provider_taxonomy + "]";
	}	
}
