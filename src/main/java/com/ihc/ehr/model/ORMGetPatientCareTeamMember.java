package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientCareTeamMember {
	
	@Id  
    private Long id;
    private Long team_id;
    private Long patient_id; 
    private Long member_reference_id;
    private String member_type;
    private String title;    
    private String last_name;
    private String first_name;    
    private Boolean pcp;    
    private String taxonomy;
    private String taxonomy_description;
    private String snomed_relationship_code;
    private String relationship_description;
    private String npi;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;  
    private Long amendment_request_id;
    private String date_created;
    private String client_date_created;
    private String created_user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTeam_id() {
		return team_id;
	}
	public void setTeam_id(Long team_id) {
		this.team_id = team_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public Long getMember_reference_id() {
		return member_reference_id;
	}
	public void setMember_reference_id(Long member_reference_id) {
		this.member_reference_id = member_reference_id;
	}
	public String getMember_type() {
		return member_type;
	}
	public void setMember_type(String member_type) {
		this.member_type = member_type;
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
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public Boolean getPcp() {
		return pcp;
	}
	public void setPcp(Boolean pcp) {
		this.pcp = pcp;
	}
	public String getTaxonomy() {
		return taxonomy;
	}
	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}
	public String getTaxonomy_description() {
		return taxonomy_description;
	}
	public void setTaxonomy_description(String taxonomy_description) {
		this.taxonomy_description = taxonomy_description;
	}
	public String getSnomed_relationship_code() {
		return snomed_relationship_code;
	}
	public void setSnomed_relationship_code(String snomed_relationship_code) {
		this.snomed_relationship_code = snomed_relationship_code;
	}
	public String getRelationship_description() {
		return relationship_description;
	}
	public void setRelationship_description(String relationship_description) {
		this.relationship_description = relationship_description;
	}
	public String getNpi() {
		return npi;
	}
	public void setNpi(String npi) {
		this.npi = npi;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getAmendment_request_id() {
		return amendment_request_id;
	}
	public void setAmendment_request_id(Long amendment_request_id) {
		this.amendment_request_id = amendment_request_id;
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
