package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMImmunizationForRegistryMessageGet {

	@Id
	private Long chart_immunization_id;
	private Long patient_id;
	private Long chart_id;
	private Long provider_id;
	private String provider_last_name;
	private String provider_first_name;
	private String provider_mname;
	private String datetime_administered;
	private String cvx_code;
	private String immunization_name;
	private String dose;
	private String units;
	private String lot_number;
	private String mvx_code;
	private String manufacturer;
	private String expiration_date;
	private String route_description;
	private String site_description;
	private String vfc_code;
	private String vfc_description;
	private String route_code;
	private String site_code;
	private String entry_type;
	private String administered_code;
	private String administered_code_description;
	private String completion_status_code;
	private String reason_type;
	private String reason_code_snomed;
	private String reason_code_cdc;
	private String reason_description;
	private String trade_name;
	private String trade_description;
	private String trade_coding_system;
	private String funding_code;
	private String funding_description;
	private String funding_coding_system;
	private Long practice_id;
	private String administering_user_info;
	private String entered_by_user_info;
	private String ndc_code;
	private String action_code;
	private Long registry_msg_imm_id;
	private String route_code_system;
	public Long getChart_immunization_id() {
		return chart_immunization_id;
	}
	public void setChart_immunization_id(Long chart_immunization_id) {
		this.chart_immunization_id = chart_immunization_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public Long getChart_id() {
		return chart_id;
	}
	public void setChart_id(Long chart_id) {
		this.chart_id = chart_id;
	}
	public Long getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(Long provider_id) {
		this.provider_id = provider_id;
	}
	public String getProvider_last_name() {
		return provider_last_name;
	}
	public void setProvider_last_name(String provider_last_name) {
		this.provider_last_name = provider_last_name;
	}
	public String getProvider_first_name() {
		return provider_first_name;
	}
	public void setProvider_first_name(String provider_first_name) {
		this.provider_first_name = provider_first_name;
	}
	public String getProvider_mname() {
		return provider_mname;
	}
	public void setProvider_mname(String provider_mname) {
		this.provider_mname = provider_mname;
	}
	public String getDatetime_administered() {
		return datetime_administered;
	}
	public void setDatetime_administered(String datetime_administered) {
		this.datetime_administered = datetime_administered;
	}
	public String getCvx_code() {
		return cvx_code;
	}
	public void setCvx_code(String cvx_code) {
		this.cvx_code = cvx_code;
	}
	public String getImmunization_name() {
		return immunization_name;
	}
	public void setImmunization_name(String immunization_name) {
		this.immunization_name = immunization_name;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getLot_number() {
		return lot_number;
	}
	public void setLot_number(String lot_number) {
		this.lot_number = lot_number;
	}
	public String getMvx_code() {
		return mvx_code;
	}
	public void setMvx_code(String mvx_code) {
		this.mvx_code = mvx_code;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	public String getRoute_description() {
		return route_description;
	}
	public void setRoute_description(String route_description) {
		this.route_description = route_description;
	}
	public String getSite_description() {
		return site_description;
	}
	public void setSite_description(String site_description) {
		this.site_description = site_description;
	}
	public String getVfc_code() {
		return vfc_code;
	}
	public void setVfc_code(String vfc_code) {
		this.vfc_code = vfc_code;
	}
	public String getVfc_description() {
		return vfc_description;
	}
	public void setVfc_description(String vfc_description) {
		this.vfc_description = vfc_description;
	}
	public String getRoute_code() {
		return route_code;
	}
	public void setRoute_code(String route_code) {
		this.route_code = route_code;
	}
	public String getSite_code() {
		return site_code;
	}
	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}
	public String getEntry_type() {
		return entry_type;
	}
	public void setEntry_type(String entry_type) {
		this.entry_type = entry_type;
	}
	public String getAdministered_code() {
		return administered_code;
	}
	public void setAdministered_code(String administered_code) {
		this.administered_code = administered_code;
	}
	public String getAdministered_code_description() {
		return administered_code_description;
	}
	public void setAdministered_code_description(String administered_code_description) {
		this.administered_code_description = administered_code_description;
	}
	public String getCompletion_status_code() {
		return completion_status_code;
	}
	public void setCompletion_status_code(String completion_status_code) {
		this.completion_status_code = completion_status_code;
	}
	public String getReason_type() {
		return reason_type;
	}
	public void setReason_type(String reason_type) {
		this.reason_type = reason_type;
	}
	public String getReason_code_snomed() {
		return reason_code_snomed;
	}
	public void setReason_code_snomed(String reason_code_snomed) {
		this.reason_code_snomed = reason_code_snomed;
	}
	public String getReason_code_cdc() {
		return reason_code_cdc;
	}
	public void setReason_code_cdc(String reason_code_cdc) {
		this.reason_code_cdc = reason_code_cdc;
	}
	public String getReason_description() {
		return reason_description;
	}
	public void setReason_description(String reason_description) {
		this.reason_description = reason_description;
	}
	public String getTrade_name() {
		return trade_name;
	}
	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}
	public String getTrade_description() {
		return trade_description;
	}
	public void setTrade_description(String trade_description) {
		this.trade_description = trade_description;
	}
	public String getTrade_coding_system() {
		return trade_coding_system;
	}
	public void setTrade_coding_system(String trade_coding_system) {
		this.trade_coding_system = trade_coding_system;
	}
	public String getFunding_code() {
		return funding_code;
	}
	public void setFunding_code(String funding_code) {
		this.funding_code = funding_code;
	}
	public String getFunding_description() {
		return funding_description;
	}
	public void setFunding_description(String funding_description) {
		this.funding_description = funding_description;
	}
	public String getFunding_coding_system() {
		return funding_coding_system;
	}
	public void setFunding_coding_system(String funding_coding_system) {
		this.funding_coding_system = funding_coding_system;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public String getAdministering_user_info() {
		return administering_user_info;
	}
	public void setAdministering_user_info(String administering_user_info) {
		this.administering_user_info = administering_user_info;
	}
	public String getEntered_by_user_info() {
		return entered_by_user_info;
	}
	public void setEntered_by_user_info(String entered_by_user_info) {
		this.entered_by_user_info = entered_by_user_info;
	}
	public String getNdc_code() {
		return ndc_code;
	}
	public void setNdc_code(String ndc_code) {
		this.ndc_code = ndc_code;
	}
	public String getAction_code() {
		return action_code;
	}
	public void setAction_code(String action_code) {
		this.action_code = action_code;
	}
	public Long getRegistry_msg_imm_id() {
		return registry_msg_imm_id;
	}
	public void setRegistry_msg_imm_id(Long registry_msg_imm_id) {
		this.registry_msg_imm_id = registry_msg_imm_id;
	}
	public String getRoute_code_system() {
		return route_code_system;
	}
	public void setRoute_code_system(String route_code_system) {
		this.route_code_system = route_code_system;
	}
}
