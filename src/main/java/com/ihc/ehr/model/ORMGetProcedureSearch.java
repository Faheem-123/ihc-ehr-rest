package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetProcedureSearch {

	@Id
	private Long id;
	private String code;
	private String description;
	private BigDecimal charges;
	private String modifier;
	private String pos;
	private String ndc_code;
	private Boolean is_expired;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getNdc_code() {
		return ndc_code;
	}
	public void setNdc_code(String ndc_code) {
		this.ndc_code = ndc_code;
	}
	public Boolean getIs_expired() {
		return is_expired;
	}
	public void setIs_expired(Boolean is_expired) {
		this.is_expired = is_expired;
	}
	
	
}
