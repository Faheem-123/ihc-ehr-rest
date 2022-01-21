package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMEraAdjustmentsToPostGet {

	@Id
	private Long era_adjustment_id;
	private String adjustment_group_code;
	private String adjustment_reason_code;
	private BigDecimal adjustment_amount;
	private Long era_id;
	private Long era_claim_id;
	private Long era_claim_service_id;
	private Long claim_procedures_id;
	private Long claim_id;
	private Boolean post_adjustment;
	public Long getEra_adjustment_id() {
		return era_adjustment_id;
	}
	public void setEra_adjustment_id(Long era_adjustment_id) {
		this.era_adjustment_id = era_adjustment_id;
	}
	public String getAdjustment_group_code() {
		return adjustment_group_code;
	}
	public void setAdjustment_group_code(String adjustment_group_code) {
		this.adjustment_group_code = adjustment_group_code;
	}
	public String getAdjustment_reason_code() {
		return adjustment_reason_code;
	}
	public void setAdjustment_reason_code(String adjustment_reason_code) {
		this.adjustment_reason_code = adjustment_reason_code;
	}
	public BigDecimal getAdjustment_amount() {
		return adjustment_amount;
	}
	public void setAdjustment_amount(BigDecimal adjustment_amount) {
		this.adjustment_amount = adjustment_amount;
	}
	public Long getEra_id() {
		return era_id;
	}
	public void setEra_id(Long era_id) {
		this.era_id = era_id;
	}
	public Long getEra_claim_id() {
		return era_claim_id;
	}
	public void setEra_claim_id(Long era_claim_id) {
		this.era_claim_id = era_claim_id;
	}
	public Long getEra_claim_service_id() {
		return era_claim_service_id;
	}
	public void setEra_claim_service_id(Long era_claim_service_id) {
		this.era_claim_service_id = era_claim_service_id;
	}
	public Long getClaim_procedures_id() {
		return claim_procedures_id;
	}
	public void setClaim_procedures_id(Long claim_procedures_id) {
		this.claim_procedures_id = claim_procedures_id;
	}
	public Long getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(Long claim_id) {
		this.claim_id = claim_id;
	}
	public Boolean getPost_adjustment() {
		return post_adjustment;
	}
	public void setPost_adjustment(Boolean post_adjustment) {
		this.post_adjustment = post_adjustment;
	}
	@Override
	public String toString() {
		return "ORMEraAdjustmentsToPostGet [era_adjustment_id=" + era_adjustment_id + ", adjustment_group_code="
				+ adjustment_group_code + ", adjustment_reason_code=" + adjustment_reason_code + ", adjustment_amount="
				+ adjustment_amount + ", era_id=" + era_id + ", era_claim_id=" + era_claim_id
				+ ", era_claim_service_id=" + era_claim_service_id + ", claim_procedures_id=" + claim_procedures_id
				+ ", claim_id=" + claim_id + ", post_adjustment=" + post_adjustment + "]";
	}
	
	

}
