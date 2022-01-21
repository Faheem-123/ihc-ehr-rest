package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMClaimProceduresGet_Pro {
	
	@Id
    private Long claim_procedures_id;
	private Integer proc_sequence;
    private String proc_code;
    private String description;
    private BigDecimal charges;
    private Integer units;
    private BigDecimal total_charges;    
    private String dos_from;
    private String dos_to;    
    private String mod1;
    private String mod2;
    private String mod3;
    private String mod4;    
    private Integer dx_pointer1;
    private Integer dx_pointer2;
    private Integer dx_pointer3;
    private Integer dx_pointer4;    
    private String ndc_code;
    private BigDecimal ndc_price;
    private String ndc_qty;
    private String ndc_measure;    
    private String pos;
    private Boolean is_resubmitted;
    private Boolean is_procedure_paid;
    private String notes;
    
    
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
	public String getMod2() {
		return mod2;
	}
	public void setMod2(String mod2) {
		this.mod2 = mod2;
	}
	public String getMod3() {
		return mod3;
	}
	public void setMod3(String mod3) {
		this.mod3 = mod3;
	}
	public String getMod4() {
		return mod4;
	}
	public void setMod4(String mod4) {
		this.mod4 = mod4;
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
	public String getNdc_code() {
		return ndc_code;
	}
	public void setNdc_code(String ndc_code) {
		this.ndc_code = ndc_code;
	}
	public BigDecimal getNdc_price() {
		return ndc_price;
	}
	public void setNdc_price(BigDecimal ndc_price) {
		this.ndc_price = ndc_price;
	}
	public String getNdc_qty() {
		return ndc_qty;
	}
	public void setNdc_qty(String ndc_qty) {
		this.ndc_qty = ndc_qty;
	}
	public String getNdc_measure() {
		return ndc_measure;
	}
	public void setNdc_measure(String ndc_measure) {
		this.ndc_measure = ndc_measure;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public Boolean getIs_resubmitted() {
		return is_resubmitted;
	}
	public void setIs_resubmitted(Boolean is_resubmitted) {
		this.is_resubmitted = is_resubmitted;
	}
	public Boolean getIs_procedure_paid() {
		return is_procedure_paid;
	}
	public void setIs_procedure_paid(Boolean is_procedure_paid) {
		this.is_procedure_paid = is_procedure_paid;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
	
	
	
}
