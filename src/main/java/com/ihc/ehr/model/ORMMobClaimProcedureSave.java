package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim_procedure")
public class ORMMobClaimProcedureSave {

	@Id
	private Long claim_procedures_id;
	private Integer proc_sequence;

	private Long patient_id;
	private Long claim_id;
	private Long practice_id;

	private String proc_code;
	private String description;
	private String pos;
	private String pos_detail;
	private BigDecimal charges;
	private Integer units;
	private BigDecimal total_charges;
	private String dos_from;
	private String dos_to;
	private String mod1;

	private Integer dx_pointer1;
	private Integer dx_pointer2;
	private Integer dx_pointer3;
	private Integer dx_pointer4;

	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;

	public Long getClaim_procedures_id() {
		return claim_procedures_id;
	}

	public void setClaim_procedures_id(Long claim_procedures_id) {
		this.claim_procedures_id = claim_procedures_id;
	}

	public Integer getProc_sequence() {
		return proc_sequence;
	}

	public void setProc_sequence(Integer proc_sequence) {
		this.proc_sequence = proc_sequence;
	}

	public Long getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}

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

	public String getProc_code() {
		return proc_code;
	}

	public void setProc_code(String proc_code) {
		this.proc_code = proc_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getPos_detail() {
		return pos_detail;
	}

	public void setPos_detail(String pos_detail) {
		this.pos_detail = pos_detail;
	}

	public BigDecimal getCharges() {
		return charges;
	}

	public void setCharges(BigDecimal charges) {
		this.charges = charges;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public BigDecimal getTotal_charges() {
		return total_charges;
	}

	public void setTotal_charges(BigDecimal total_charges) {
		this.total_charges = total_charges;
	}

	public String getDos_from() {
		return dos_from;
	}

	public void setDos_from(String dos_from) {
		this.dos_from = dos_from;
	}

	public String getDos_to() {
		return dos_to;
	}

	public void setDos_to(String dos_to) {
		this.dos_to = dos_to;
	}

	public String getMod1() {
		return mod1;
	}

	public void setMod1(String mod1) {
		this.mod1 = mod1;
	}

	public Integer getDx_pointer1() {
		return dx_pointer1;
	}

	public void setDx_pointer1(Integer dx_pointer1) {
		this.dx_pointer1 = dx_pointer1;
	}

	public Integer getDx_pointer2() {
		return dx_pointer2;
	}

	public void setDx_pointer2(Integer dx_pointer2) {
		this.dx_pointer2 = dx_pointer2;
	}

	public Integer getDx_pointer3() {
		return dx_pointer3;
	}

	public void setDx_pointer3(Integer dx_pointer3) {
		this.dx_pointer3 = dx_pointer3;
	}

	public Integer getDx_pointer4() {
		return dx_pointer4;
	}

	public void setDx_pointer4(Integer dx_pointer4) {
		this.dx_pointer4 = dx_pointer4;
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
}
