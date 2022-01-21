package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tele_health_survey")
public class ORMTeleHealthSurveySave {
	
	@Id    
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String patient_name;
	private String patient_dob;
	private String patient_age;
	private String patient_address;
	private String patient_city;
	private String patient_zip;
	private String patient_phone;
	private String patient_weight;
	private String patient_height;
	private String pharmacy_name;
	private String pharmacy_phone;
	private String pharmacy_fax;
	private String pharmacy_address;
	private String pharmacy_city;
	private String pharmacy_zip;
	private String medication_1;
	private String medication_2;
	private String medication_3;
	private String medication_4;
	private String insurance_name;
	private String policy_no;
	private String q1_answer;
	private String q2_answer;
	private String q3_answer;
	private String q4_answer;
	private String extender_name;
	private String date_extended;	
	private String client_date_created;
	private String date_created;
	private String system_ip;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getPatient_dob() {
		return patient_dob;
	}
	public void setPatient_dob(String patient_dob) {
		this.patient_dob = patient_dob;
	}
	public String getPatient_age() {
		return patient_age;
	}
	public void setPatient_age(String patient_age) {
		this.patient_age = patient_age;
	}
	public String getPatient_address() {
		return patient_address;
	}
	public void setPatient_address(String patient_address) {
		this.patient_address = patient_address;
	}
	public String getPatient_city() {
		return patient_city;
	}
	public void setPatient_city(String patient_city) {
		this.patient_city = patient_city;
	}
	public String getPatient_zip() {
		return patient_zip;
	}
	public void setPatient_zip(String patient_zip) {
		this.patient_zip = patient_zip;
	}
	public String getPatient_phone() {
		return patient_phone;
	}
	public void setPatient_phone(String patient_phone) {
		this.patient_phone = patient_phone;
	}
	public String getPatient_weight() {
		return patient_weight;
	}
	public void setPatient_weight(String patient_weight) {
		this.patient_weight = patient_weight;
	}
	public String getPatient_height() {
		return patient_height;
	}
	public void setPatient_height(String patient_height) {
		this.patient_height = patient_height;
	}
	public String getPharmacy_name() {
		return pharmacy_name;
	}
	public void setPharmacy_name(String pharmacy_name) {
		this.pharmacy_name = pharmacy_name;
	}
	public String getPharmacy_phone() {
		return pharmacy_phone;
	}
	public void setPharmacy_phone(String pharmacy_phone) {
		this.pharmacy_phone = pharmacy_phone;
	}
	public String getPharmacy_fax() {
		return pharmacy_fax;
	}
	public void setPharmacy_fax(String pharmacy_fax) {
		this.pharmacy_fax = pharmacy_fax;
	}
	public String getPharmacy_address() {
		return pharmacy_address;
	}
	public void setPharmacy_address(String pharmacy_address) {
		this.pharmacy_address = pharmacy_address;
	}
	public String getPharmacy_city() {
		return pharmacy_city;
	}
	public void setPharmacy_city(String pharmacy_city) {
		this.pharmacy_city = pharmacy_city;
	}
	public String getPharmacy_zip() {
		return pharmacy_zip;
	}
	public void setPharmacy_zip(String pharmacy_zip) {
		this.pharmacy_zip = pharmacy_zip;
	}
	public String getMedication_1() {
		return medication_1;
	}
	public void setMedication_1(String medication_1) {
		this.medication_1 = medication_1;
	}
	public String getMedication_2() {
		return medication_2;
	}
	public void setMedication_2(String medication_2) {
		this.medication_2 = medication_2;
	}
	public String getMedication_3() {
		return medication_3;
	}
	public void setMedication_3(String medication_3) {
		this.medication_3 = medication_3;
	}
	public String getMedication_4() {
		return medication_4;
	}
	public void setMedication_4(String medication_4) {
		this.medication_4 = medication_4;
	}
	public String getInsurance_name() {
		return insurance_name;
	}
	public void setInsurance_name(String insurance_name) {
		this.insurance_name = insurance_name;
	}
	public String getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}
	public String getQ1_answer() {
		return q1_answer;
	}
	public void setQ1_answer(String q1_answer) {
		this.q1_answer = q1_answer;
	}
	public String getQ2_answer() {
		return q2_answer;
	}
	public void setQ2_answer(String q2_answer) {
		this.q2_answer = q2_answer;
	}
	public String getQ3_answer() {
		return q3_answer;
	}
	public void setQ3_answer(String q3_answer) {
		this.q3_answer = q3_answer;
	}
	public String getQ4_answer() {
		return q4_answer;
	}
	public void setQ4_answer(String q4_answer) {
		this.q4_answer = q4_answer;
	}
	public String getExtender_name() {
		return extender_name;
	}
	public void setExtender_name(String extender_name) {
		this.extender_name = extender_name;
	}
	public String getDate_extended() {
		return date_extended;
	}
	public void setDate_extended(String date_extended) {
		this.date_extended = date_extended;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	@Override
	public String toString() {
		return "ORMTeleHealthSurveySave [id=" + id + ", patient_name=" + patient_name + ", patient_dob=" + patient_dob
				+ ", patient_age=" + patient_age + ", patient_address=" + patient_address + ", patient_city="
				+ patient_city + ", patient_zip=" + patient_zip + ", patient_phone=" + patient_phone
				+ ", patient_weight=" + patient_weight + ", patient_height=" + patient_height + ", pharmacy_name="
				+ pharmacy_name + ", pharmacy_phone=" + pharmacy_phone + ", pharmacy_fax=" + pharmacy_fax
				+ ", pharmacy_address=" + pharmacy_address + ", pharmacy_city=" + pharmacy_city + ", pharmacy_zip="
				+ pharmacy_zip + ", medication_1=" + medication_1 + ", medication_2=" + medication_2 + ", medication_3="
				+ medication_3 + ", medication_4=" + medication_4 + ", insurance_name=" + insurance_name
				+ ", policy_no=" + policy_no + ", q1_answer=" + q1_answer + ", q2_answer=" + q2_answer + ", q3_answer="
				+ q3_answer + ", q4_answer=" + q4_answer + ", extender_name=" + extender_name + ", date_extended="
				+ date_extended + ", client_date_created=" + client_date_created + ", date_created=" + date_created
				+ ", system_ip=" + system_ip + "]";
	}
	
	
}
