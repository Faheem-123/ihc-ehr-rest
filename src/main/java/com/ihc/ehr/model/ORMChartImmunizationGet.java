package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMChartImmunizationGet {

	@Id
	private Long chart_immunization_id;
	private Long chart_id;
	private Long location_id;
	private Long provider_id;
	private String immunization_name;
	private String datetime_administered;
	private String manufacturer;
	private String dose_detail;
	private String dose;
	private String units;
	private String expiration_date;
	private String site_description;
	private String route_description;
	private String lot_number;
	private String manufacturer_detail;
	private String mvx_code;
	private String cvx_code;
	private String comments;
	private String adverse_reaction;
	private String client_date_created;
	private String created_user;
	private String date_created;
	private String amount;
	private String proc_code;
	private String proc_description;
	private String vfc_code;
	private String vfc_description;
	private String route_code;
	private String site_code;
	private String entry_type;
	private String administered_code;
	private String administered_code_description;
	private String provider_name;
	private String completion_status_code;
	private String reason_type;
	private String reason_code_snomed;
	private String reason_code_cdc;
	private String reason_description;
	private Boolean billable;
	private String funding_code;
	private String funding_description;
	private String funding_coding_system;
	private String trade_name;
	private String trade_description;
	private String trade_coding_system;
	private String registry_status;
	private String registry_date;
	private String administering_user_info;
	private String entered_by_user_info;
	private String ndc_code;
	private String action_code;
	private String route_code_system;

	public Long getChart_immunization_id() {
		return chart_immunization_id;
	}

	public void setChart_immunization_id(Long chart_immunization_id) {
		this.chart_immunization_id = chart_immunization_id;
	}

	public Long getChart_id() {
		return chart_id;
	}

	public void setChart_id(Long chart_id) {
		this.chart_id = chart_id;
	}

	public Long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}

	public Long getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(Long provider_id) {
		this.provider_id = provider_id;
	}

	public String getImmunization_name() {
		return immunization_name;
	}

	public void setImmunization_name(String immunization_name) {
		this.immunization_name = immunization_name;
	}

	public String getDatetime_administered() {
		return datetime_administered;
	}

	public void setDatetime_administered(String datetime_administered) {
		this.datetime_administered = datetime_administered;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDose_detail() {
		return dose_detail;
	}

	public void setDose_detail(String dose_detail) {
		this.dose_detail = dose_detail;
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

	public String getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}

	public String getSite_description() {
		return site_description;
	}

	public void setSite_description(String site_description) {
		this.site_description = site_description;
	}

	public String getRoute_description() {
		return route_description;
	}

	public void setRoute_description(String route_description) {
		this.route_description = route_description;
	}

	public String getLot_number() {
		return lot_number;
	}

	public void setLot_number(String lot_number) {
		this.lot_number = lot_number;
	}

	public String getManufacturer_detail() {
		return manufacturer_detail;
	}

	public void setManufacturer_detail(String manufacturer_detail) {
		this.manufacturer_detail = manufacturer_detail;
	}

	public String getMvx_code() {
		return mvx_code;
	}

	public void setMvx_code(String mvx_code) {
		this.mvx_code = mvx_code;
	}

	public String getCvx_code() {
		return cvx_code;
	}

	public void setCvx_code(String cvx_code) {
		this.cvx_code = cvx_code;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAdverse_reaction() {
		return adverse_reaction;
	}

	public void setAdverse_reaction(String adverse_reaction) {
		this.adverse_reaction = adverse_reaction;
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

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getProc_code() {
		return proc_code;
	}

	public void setProc_code(String proc_code) {
		this.proc_code = proc_code;
	}

	public String getProc_description() {
		return proc_description;
	}

	public void setProc_description(String proc_description) {
		this.proc_description = proc_description;
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

	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
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

	public Boolean getBillable() {
		return billable;
	}

	public void setBillable(Boolean billable) {
		this.billable = billable;
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

	public String getRegistry_status() {
		return registry_status;
	}

	public void setRegistry_status(String registry_status) {
		this.registry_status = registry_status;
	}

	public String getRegistry_date() {
		return registry_date;
	}

	public void setRegistry_date(String registry_date) {
		this.registry_date = registry_date;
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

	public String getRoute_code_system() {
		return route_code_system;
	}

	public void setRoute_code_system(String route_code_system) {
		this.route_code_system = route_code_system;
	}
}
