package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatient {
	
	
    @Id
	private Long patient_id;
    private String guarantor_name;
    private Long guarantor_id;
    private String referral_name;
    private Long referral_id;
    private String alternate_account;
    private String first_name;
    private String last_name;
    private String suffix;
    private String preffix;
    private String previous_name;  
    private String mname;
    private String gender;
    private String gender_code;
    private String patient_language;
    private String language_code;
    private String marital_status;
    private String ssn;
    private String address;
    private String address2;
    private String zip;
    private String city;
    private String state;
    private String home_phone;
    private String work_phone;
    private String cell_phone;
    private String email;
    private String emrg_contact;
    private String emrg_contact_name;
    private String emrg_contact_relation;
    private String comment;
    private Boolean access_restricted;
    private String confid_contact_type;
    private String confid_contact;
    private Boolean patient_disabled;
    private String disabled_date;
    private String race;
    private String ethnicity;
    private String race_code;
    private String ethnicity_code;    
    private Long practice_id;
    private String created_user;
    private String date_created;
    private String modified_user;
    private String date_modified;
    private String client_date_created;
    private String client_date_modified;    
    private String pic;
    private String primary_contact_type;    
    private String dob;
    private Long primary_provider;
    private String primary_provider_name;
    private Boolean is_transition_of_care;
    private String patient_status;
    private Boolean interpreter_req;
    private String id_card;
    private String driving_license;
    private String patient_agreement;
    private Boolean acu;
    private String mother_maiden_last_name;
    private String mother_maiden_first_name;
    private Boolean self_pay;
    private String county_code;
    private Long location_id;
    private String location_name;
    private String advance_directive;
    private Boolean pat_statement;
    private String multi_birth;
    private Integer birth_order;
    private Integer family_size;
    private String annual_income;
    private String gender_identity;    
    private String gender_identity_code;
    private String sexual_orientation;    
    private String sexual_orientation_code;
    private String death_cause;
    private String expired_date;
    private String marital_status_code;
    private Boolean is_dental;
    
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getGuarantor_name() {
		return guarantor_name;
	}
	public void setGuarantor_name(String guarantor_name) {
		this.guarantor_name = guarantor_name;
	}
	public Long getGuarantor_id() {
		return guarantor_id;
	}
	public void setGuarantor_id(Long guarantor_id) {
		this.guarantor_id = guarantor_id;
	}
	public String getReferral_name() {
		return referral_name;
	}
	public void setReferral_name(String referral_name) {
		this.referral_name = referral_name;
	}
	public Long getReferral_id() {
		return referral_id;
	}
	public void setReferral_id(Long referral_id) {
		this.referral_id = referral_id;
	}
	public String getAlternate_account() {
		return alternate_account;
	}
	public void setAlternate_account(String alternate_account) {
		this.alternate_account = alternate_account;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getPreffix() {
		return preffix;
	}
	public void setPreffix(String preffix) {
		this.preffix = preffix;
	}
	public String getPrevious_name() {
		return previous_name;
	}
	public void setPrevious_name(String previous_name) {
		this.previous_name = previous_name;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender_code() {
		return gender_code;
	}
	public void setGender_code(String gender_code) {
		this.gender_code = gender_code;
	}
	public String getPatient_language() {
		return patient_language;
	}
	public void setPatient_language(String patient_language) {
		this.patient_language = patient_language;
	}
	public String getLanguage_code() {
		return language_code;
	}
	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHome_phone() {
		return home_phone;
	}
	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}
	public String getWork_phone() {
		return work_phone;
	}
	public void setWork_phone(String work_phone) {
		this.work_phone = work_phone;
	}
	public String getCell_phone() {
		return cell_phone;
	}
	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmrg_contact() {
		return emrg_contact;
	}
	public void setEmrg_contact(String emrg_contact) {
		this.emrg_contact = emrg_contact;
	}
	public String getEmrg_contact_name() {
		return emrg_contact_name;
	}
	public void setEmrg_contact_name(String emrg_contact_name) {
		this.emrg_contact_name = emrg_contact_name;
	}
	public String getEmrg_contact_relation() {
		return emrg_contact_relation;
	}
	public void setEmrg_contact_relation(String emrg_contact_relation) {
		this.emrg_contact_relation = emrg_contact_relation;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Boolean getAccess_restricted() {
		return access_restricted;
	}
	public void setAccess_restricted(Boolean access_restricted) {
		this.access_restricted = access_restricted;
	}
	public String getConfid_contact_type() {
		return confid_contact_type;
	}
	public void setConfid_contact_type(String confid_contact_type) {
		this.confid_contact_type = confid_contact_type;
	}
	public String getConfid_contact() {
		return confid_contact;
	}
	public void setConfid_contact(String confid_contact) {
		this.confid_contact = confid_contact;
	}
	public Boolean getPatient_disabled() {
		return patient_disabled;
	}
	public void setPatient_disabled(Boolean patient_disabled) {
		this.patient_disabled = patient_disabled;
	}
	public String getDisabled_date() {
		return disabled_date;
	}
	public void setDisabled_date(String disabled_date) {
		this.disabled_date = disabled_date;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getRace_code() {
		return race_code;
	}
	public void setRace_code(String race_code) {
		this.race_code = race_code;
	}
	public String getEthnicity_code() {
		return ethnicity_code;
	}
	public void setEthnicity_code(String ethnicity_code) {
		this.ethnicity_code = ethnicity_code;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
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
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPrimary_contact_type() {
		return primary_contact_type;
	}
	public void setPrimary_contact_type(String primary_contact_type) {
		this.primary_contact_type = primary_contact_type;
	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Long getPrimary_provider() {
		return primary_provider;
	}
	public void setPrimary_provider(Long primary_provider) {
		this.primary_provider = primary_provider;
	}
	public String getPrimary_provider_name() {
		return primary_provider_name;
	}
	public void setPrimary_provider_name(String primary_provider_name) {
		this.primary_provider_name = primary_provider_name;
	}
	public Boolean getIs_transition_of_care() {
		return is_transition_of_care;
	}
	public void setIs_transition_of_care(Boolean is_transition_of_care) {
		this.is_transition_of_care = is_transition_of_care;
	}
	public String getPatient_status() {
		return patient_status;
	}
	public void setPatient_status(String patient_status) {
		this.patient_status = patient_status;
	}
	public Boolean getInterpreter_req() {
		return interpreter_req;
	}
	public void setInterpreter_req(Boolean interpreter_req) {
		this.interpreter_req = interpreter_req;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getDriving_license() {
		return driving_license;
	}
	public void setDriving_license(String driving_license) {
		this.driving_license = driving_license;
	}
	public String getPatient_agreement() {
		return patient_agreement;
	}
	public void setPatient_agreement(String patient_agreement) {
		this.patient_agreement = patient_agreement;
	}
	public Boolean getAcu() {
		return acu;
	}
	public void setAcu(Boolean acu) {
		this.acu = acu;
	}
	public String getMother_maiden_last_name() {
		return mother_maiden_last_name;
	}
	public void setMother_maiden_last_name(String mother_maiden_last_name) {
		this.mother_maiden_last_name = mother_maiden_last_name;
	}
	public String getMother_maiden_first_name() {
		return mother_maiden_first_name;
	}
	public void setMother_maiden_first_name(String mother_maiden_first_name) {
		this.mother_maiden_first_name = mother_maiden_first_name;
	}
	public Boolean getSelf_pay() {
		return self_pay;
	}
	public void setSelf_pay(Boolean self_pay) {
		this.self_pay = self_pay;
	}
	public String getCounty_code() {
		return county_code;
	}
	public void setCounty_code(String county_code) {
		this.county_code = county_code;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getAdvance_directive() {
		return advance_directive;
	}
	public void setAdvance_directive(String advance_directive) {
		this.advance_directive = advance_directive;
	}
	public Boolean getPat_statement() {
		return pat_statement;
	}
	public void setPat_statement(Boolean pat_statement) {
		this.pat_statement = pat_statement;
	}
	public String getMulti_birth() {
		return multi_birth;
	}
	public void setMulti_birth(String multi_birth) {
		this.multi_birth = multi_birth;
	}
	public Integer getBirth_order() {
		return birth_order;
	}
	public void setBirth_order(Integer birth_order) {
		this.birth_order = birth_order;
	}
	public Integer getFamily_size() {
		return family_size;
	}
	public void setFamily_size(Integer family_size) {
		this.family_size = family_size;
	}
	public String getAnnual_income() {
		return annual_income;
	}
	public void setAnnual_income(String annual_income) {
		this.annual_income = annual_income;
	}
	public String getGender_identity() {
		return gender_identity;
	}
	public void setGender_identity(String gender_identity) {
		this.gender_identity = gender_identity;
	}
	public String getGender_identity_code() {
		return gender_identity_code;
	}
	public void setGender_identity_code(String gender_identity_code) {
		this.gender_identity_code = gender_identity_code;
	}
	public String getSexual_orientation() {
		return sexual_orientation;
	}
	public void setSexual_orientation(String sexual_orientation) {
		this.sexual_orientation = sexual_orientation;
	}
	public String getSexual_orientation_code() {
		return sexual_orientation_code;
	}
	public void setSexual_orientation_code(String sexual_orientation_code) {
		this.sexual_orientation_code = sexual_orientation_code;
	}
	public String getDeath_cause() {
		return death_cause;
	}
	public void setDeath_cause(String death_cause) {
		this.death_cause = death_cause;
	}
	
	public String getExpired_date() {
		return expired_date;
	}
	public void setExpired_date(String expired_date) {
		this.expired_date = expired_date;
	}
	public String getMarital_status_code() {
		return marital_status_code;
	}
	public void setMarital_status_code(String marital_status_code) {
		this.marital_status_code = marital_status_code;
	}
	public Boolean getIs_dental() {
		return is_dental;
	}
	public void setIs_dental(Boolean is_dental) {
		this.is_dental = is_dental;
	}
}
