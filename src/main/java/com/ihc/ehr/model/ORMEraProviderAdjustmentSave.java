package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "era_provider_adjustment")
public class ORMEraProviderAdjustmentSave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long provider_adjustment_id;
	private String reason_code;
	private String adjustment_identifier;
	private String provider_identifier;
	private String fiscal_year_end_date;
	private String amount;
	private Long era_id;
	private Boolean deleted;
	private Long practice_id;

	public Long getProvider_adjustment_id() {
		return provider_adjustment_id;
	}

	public void setProvider_adjustment_id(Long provider_adjustment_id) {
		this.provider_adjustment_id = provider_adjustment_id;
	}

	public String getReason_code() {
		return reason_code;
	}

	public void setReason_code(String reason_code) {
		this.reason_code = reason_code;
	}

	public String getAdjustment_identifier() {
		return adjustment_identifier;
	}

	public void setAdjustment_identifier(String adjustment_identifier) {
		this.adjustment_identifier = adjustment_identifier;
	}

	public String getProvider_identifier() {
		return provider_identifier;
	}

	public void setProvider_identifier(String provider_identifier) {
		this.provider_identifier = provider_identifier;
	}

	public String getFiscal_year_end_date() {
		return fiscal_year_end_date;
	}

	public void setFiscal_year_end_date(String fiscal_year_end_date) {
		this.fiscal_year_end_date = fiscal_year_end_date;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Long getEra_id() {
		return era_id;
	}

	public void setEra_id(Long era_id) {
		this.era_id = era_id;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Long getPractice_id() {
		return practice_id;
	}

	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}

	@Override
	public String toString() {
		return "ORMEraProviderAdjustmentSave [provider_adjustment_id=" + provider_adjustment_id + ", reason_code="
				+ reason_code + ", adjustment_identifier=" + adjustment_identifier + ", provider_identifier="
				+ provider_identifier + ", fiscal_year_end_date=" + fiscal_year_end_date + ", amount=" + amount
				+ ", era_id=" + era_id + ", deleted=" + deleted + ", practice_id=" + practice_id + "]";
	}
}
