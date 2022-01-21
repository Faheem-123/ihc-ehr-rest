package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMMobClaimProceduresGet {

	@Id
	private Long claim_procedures_id;
	private Integer proc_sequence;
	private String proc_code;
	private String description;
	private BigDecimal charges;
	private Integer units;
	private BigDecimal total_charges;
	private String mod1;	
	private String pos;	
	private String dx_pointer1;
	private String dx_pointer2;
	private String dx_pointer3;
	private String dx_pointer4;	
	private String date_created;
	private String client_date_created;
	private String created_user;
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
	public String getMod1() {
		return mod1;
	}
	public void setMod1(String mod1) {
		this.mod1 = mod1;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getDx_pointer1() {
		return dx_pointer1;
	}
	public void setDx_pointer1(String dx_pointer1) {
		this.dx_pointer1 = dx_pointer1;
	}
	public String getDx_pointer2() {
		return dx_pointer2;
	}
	public void setDx_pointer2(String dx_pointer2) {
		this.dx_pointer2 = dx_pointer2;
	}
	public String getDx_pointer3() {
		return dx_pointer3;
	}
	public void setDx_pointer3(String dx_pointer3) {
		this.dx_pointer3 = dx_pointer3;
	}
	public String getDx_pointer4() {
		return dx_pointer4;
	}
	public void setDx_pointer4(String dx_pointer4) {
		this.dx_pointer4 = dx_pointer4;
	}

	
	
}
