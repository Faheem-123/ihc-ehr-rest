package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "billing_provider")
public class ORMSetupBillingProvider {

	@Id
	private String billing_provider_id;
	private String practice_id;
	private String title;
	private String last_name;
	private String mname;
	private String first_name;
	private String dob;
	private String ssn;
	private String email;
	private String phone_no;
	private String upin;
	private String dea_no;
	private String dea_expiry_date;
	private String clia_number;
	private String clia_expiry_date;
	private String npi;
	private String spi;
	private String state_license;
	private String spec_license;
	private String billing_fax;
	private String taxonomy_id;
	private String bill_address;
	private String bill_zip;
	private String bill_city;
	private String bill_state;
	private String federal_taxid;
	private String federal_taxidnumbertype;
	private String billingprovidertype;
	private String acceptassignment;
	private String gateway_id;
	private String gateway_password;
	private String provider_number;
	private Boolean is_blocked;
	private Boolean deleted;
	private String created_user;
	private String modified_user;
	private String client_date_created;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private String organization_name;

	private String grp_taxonomy_id;
	private String tpi;
	private String group_tpi;
	private String pay_to_address;
	private String pay_to_city;
	private String pay_to_state;
	private String pay_to_zip;
	private String group_npi;

	private String pay_to_address_grp;
	private String pay_to_city_grp;
	private String pay_to_state_grp;
	private String pay_to_zip_grp;

	private String bill_address_grp;
	private String bill_zip_grp;
	private String bill_city_grp;
	private String bill_state_grp;

	private String secondary_taxonomy_id;

	public String getBilling_provider_id() {
		return billing_provider_id;
	}

	public void setBilling_provider_id(String billing_provider_id) {
		this.billing_provider_id = billing_provider_id;
	}

	public String getPractice_id() {
		return practice_id;
	}

	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getUpin() {
		return upin;
	}

	public void setUpin(String upin) {
		this.upin = upin;
	}

	public String getDea_no() {
		return dea_no;
	}

	public void setDea_no(String dea_no) {
		this.dea_no = dea_no;
	}

	public String getDea_expiry_date() {
		return dea_expiry_date;
	}

	public void setDea_expiry_date(String dea_expiry_date) {
		this.dea_expiry_date = dea_expiry_date;
	}

	public String getClia_number() {
		return clia_number;
	}

	public void setClia_number(String clia_number) {
		this.clia_number = clia_number;
	}

	public String getClia_expiry_date() {
		return clia_expiry_date;
	}

	public void setClia_expiry_date(String clia_expiry_date) {
		this.clia_expiry_date = clia_expiry_date;
	}

	public String getNpi() {
		return npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}

	public String getSpi() {
		return spi;
	}

	public void setSpi(String spi) {
		this.spi = spi;
	}

	public String getState_license() {
		return state_license;
	}

	public void setState_license(String state_license) {
		this.state_license = state_license;
	}

	public String getSpec_license() {
		return spec_license;
	}

	public void setSpec_license(String spec_license) {
		this.spec_license = spec_license;
	}

	public String getBilling_fax() {
		return billing_fax;
	}

	public void setBilling_fax(String billing_fax) {
		this.billing_fax = billing_fax;
	}

	public String getTaxonomy_id() {
		return taxonomy_id;
	}

	public void setTaxonomy_id(String taxonomy_id) {
		this.taxonomy_id = taxonomy_id;
	}

	public String getBill_address() {
		return bill_address;
	}

	public void setBill_address(String bill_address) {
		this.bill_address = bill_address;
	}

	public String getBill_zip() {
		return bill_zip;
	}

	public void setBill_zip(String bill_zip) {
		this.bill_zip = bill_zip;
	}

	public String getBill_city() {
		return bill_city;
	}

	public void setBill_city(String bill_city) {
		this.bill_city = bill_city;
	}

	public String getBill_state() {
		return bill_state;
	}

	public void setBill_state(String bill_state) {
		this.bill_state = bill_state;
	}

	public String getFederal_taxid() {
		return federal_taxid;
	}

	public void setFederal_taxid(String federal_taxid) {
		this.federal_taxid = federal_taxid;
	}

	public String getFederal_taxidnumbertype() {
		return federal_taxidnumbertype;
	}

	public void setFederal_taxidnumbertype(String federal_taxidnumbertype) {
		this.federal_taxidnumbertype = federal_taxidnumbertype;
	}

	public String getBillingprovidertype() {
		return billingprovidertype;
	}

	public void setBillingprovidertype(String billingprovidertype) {
		this.billingprovidertype = billingprovidertype;
	}

	public String getAcceptassignment() {
		return acceptassignment;
	}

	public void setAcceptassignment(String acceptassignment) {
		this.acceptassignment = acceptassignment;
	}

	public String getGateway_id() {
		return gateway_id;
	}

	public void setGateway_id(String gateway_id) {
		this.gateway_id = gateway_id;
	}

	public String getGateway_password() {
		return gateway_password;
	}

	public void setGateway_password(String gateway_password) {
		this.gateway_password = gateway_password;
	}

	public String getProvider_number() {
		return provider_number;
	}

	public void setProvider_number(String provider_number) {
		this.provider_number = provider_number;
	}

	public Boolean getIs_blocked() {
		return is_blocked;
	}

	public void setIs_blocked(Boolean is_blocked) {
		this.is_blocked = is_blocked;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getCreated_user() {
		return created_user;
	}

	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}

	public String getModified_user() {
		return modified_user;
	}

	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}

	public String getClient_date_created() {
		return client_date_created;
	}

	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
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

	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}

	public String getGrp_taxonomy_id() {
		return grp_taxonomy_id;
	}

	public void setGrp_taxonomy_id(String grp_taxonomy_id) {
		this.grp_taxonomy_id = grp_taxonomy_id;
	}

	public String getTpi() {
		return tpi;
	}

	public void setTpi(String tpi) {
		this.tpi = tpi;
	}

	public String getGroup_tpi() {
		return group_tpi;
	}

	public void setGroup_tpi(String group_tpi) {
		this.group_tpi = group_tpi;
	}

	public String getPay_to_address() {
		return pay_to_address;
	}

	public void setPay_to_address(String pay_to_address) {
		this.pay_to_address = pay_to_address;
	}

	public String getPay_to_city() {
		return pay_to_city;
	}

	public void setPay_to_city(String pay_to_city) {
		this.pay_to_city = pay_to_city;
	}

	public String getPay_to_state() {
		return pay_to_state;
	}

	public void setPay_to_state(String pay_to_state) {
		this.pay_to_state = pay_to_state;
	}

	public String getPay_to_zip() {
		return pay_to_zip;
	}

	public void setPay_to_zip(String pay_to_zip) {
		this.pay_to_zip = pay_to_zip;
	}

	public String getGroup_npi() {
		return group_npi;
	}

	public void setGroup_npi(String group_npi) {
		this.group_npi = group_npi;
	}

	public String getPay_to_address_grp() {
		return pay_to_address_grp;
	}

	public void setPay_to_address_grp(String pay_to_address_grp) {
		this.pay_to_address_grp = pay_to_address_grp;
	}

	public String getPay_to_city_grp() {
		return pay_to_city_grp;
	}

	public void setPay_to_city_grp(String pay_to_city_grp) {
		this.pay_to_city_grp = pay_to_city_grp;
	}

	public String getPay_to_state_grp() {
		return pay_to_state_grp;
	}

	public void setPay_to_state_grp(String pay_to_state_grp) {
		this.pay_to_state_grp = pay_to_state_grp;
	}

	public String getPay_to_zip_grp() {
		return pay_to_zip_grp;
	}

	public void setPay_to_zip_grp(String pay_to_zip_grp) {
		this.pay_to_zip_grp = pay_to_zip_grp;
	}

	public String getBill_address_grp() {
		return bill_address_grp;
	}

	public void setBill_address_grp(String bill_address_grp) {
		this.bill_address_grp = bill_address_grp;
	}

	public String getBill_zip_grp() {
		return bill_zip_grp;
	}

	public void setBill_zip_grp(String bill_zip_grp) {
		this.bill_zip_grp = bill_zip_grp;
	}

	public String getBill_city_grp() {
		return bill_city_grp;
	}

	public void setBill_city_grp(String bill_city_grp) {
		this.bill_city_grp = bill_city_grp;
	}

	public String getBill_state_grp() {
		return bill_state_grp;
	}

	public void setBill_state_grp(String bill_state_grp) {
		this.bill_state_grp = bill_state_grp;
	}

	public String getSecondary_taxonomy_id() {
		return secondary_taxonomy_id;
	}

	public void setSecondary_taxonomy_id(String secondary_taxonomy_id) {
		this.secondary_taxonomy_id = secondary_taxonomy_id;
	}

}
