package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "patient_not_paid_reason")
public class ORMSavePatientNotPaidReason {
	
	@Id
    private Long not_paid_reason_id;
    private Long patient_id;
    private Long appointment_id;
    private Long cash_register_id;
    private String not_paid_reson;
    private String authorized_by;
    private String date_created;
    private String created_user;    
    private Long practice_id;
	public Long getNot_paid_reason_id() {
		return not_paid_reason_id;
	}
	public void setNot_paid_reason_id(Long not_paid_reason_id) {
		this.not_paid_reason_id = not_paid_reason_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public Long getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(Long appointment_id) {
		this.appointment_id = appointment_id;
	}
	public Long getCash_register_id() {
		return cash_register_id;
	}
	public void setCash_register_id(Long cash_register_id) {
		this.cash_register_id = cash_register_id;
	}
	public String getNot_paid_reson() {
		return not_paid_reson;
	}
	public void setNot_paid_reson(String not_paid_reson) {
		this.not_paid_reson = not_paid_reson;
	}
	public String getAuthorized_by() {
		return authorized_by;
	}
	public void setAuthorized_by(String authorized_by) {
		this.authorized_by = authorized_by;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
}
