package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "edi_claim_status")
public class ORM_277_ResponseSave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String response_ref_id;
	private String response_date_created;
	
	private String info_source_entity_code;
	private String info_source_name;
	private String info_source_identifer_code;
	private String info_source_identifer_code_qualifier;
		
	//private String info_receiver_code;
	//private String submitter_name;
	//private String submitter_identifer_code;
	//private String submitter_identifer_code_qualifier;
	
	//private String billing_provider_name;
	//private String billing_provider_identifer_code;
	//private String billing_provider_identifer_code_qualifier;
		
	private String patient_last_name;
	private String patient_first_name;
	private String patient_policy_number;
		
	
	private String claim_id;
	private String status_effective_date;
	private String status_category_code;
	private String status_code;
	private String status_description;	
	//private String action_code;
	private String amount_billed;
	
	private String icn;
	
	private String file_path;
		
	private String practice_id;
	private Boolean deleted;
	private String system_ip;
	private String created_user;
	private String date_created;	
	private String modified_user;
	private String date_modified;
	
	private String client_date_created;
	private String client_date_modified;
		
	public ORM_277_ResponseSave() {
		super();
	}
	
	public ORM_277_ResponseSave(ORM_277_ResponseSave obj) {
		
		//this.transaction_set_no=obj.getTransaction_set_no();
		
		this.response_ref_id=obj.getResponse_ref_id();
		this.response_date_created=obj.getResponse_date_created();
			
		this.info_source_entity_code=obj.getInfo_source_entity_code();
		this.info_source_name=obj.getInfo_source_name();
		this.info_source_identifer_code=obj.getInfo_source_identifer_code();
		this.info_source_identifer_code_qualifier=obj.getInfo_source_identifer_code_qualifier();
			
		
		//this.submitter_name=obj.getSubmitter_name();
		//this.submitter_identifer_code=obj.getSubmitter_identifer_code();
		//this.submitter_identifer_code_qualifier=obj.getSubmitter_identifer_code_qualifier();
		
		//this.billing_provider_name=obj.getBilling_provider_name();
		//this.billing_provider_identifer_code=obj.getBilling_provider_identifer_code();
		//this.billing_provider_identifer_code_qualifier=obj.getBilling_provider_identifer_code_qualifier();
		
		this.patient_first_name=obj.getPatient_first_name();
		this.patient_last_name=obj.getPatient_last_name();
		this.patient_policy_number=obj.getPatient_policy_number();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	/*
	public String getTransaction_set_no() {
		return transaction_set_no;
	}

	public void setTransaction_set_no(String transaction_set_no) {
		this.transaction_set_no = transaction_set_no;
	}

	public String getResponse_ref_id() {
		return response_ref_id;
	}

	public void setResponse_ref_id(String response_ref_id) {
		this.response_ref_id = response_ref_id;
	}

	public String getResponse_date_created() {
		return response_date_created;
	}


	public void setResponse_date_created(String response_date_created) {
		this.response_date_created = response_date_created;
	}
		*/

	public String getResponse_ref_id() {
		return response_ref_id;
	}

	public void setResponse_ref_id(String response_ref_id) {
		this.response_ref_id = response_ref_id;
	}

	public String getResponse_date_created() {
		return response_date_created;
	}

	public void setResponse_date_created(String response_date_time_created) {
		this.response_date_created = response_date_time_created;
	}

	public String getInfo_source_entity_code() {
		return info_source_entity_code;
	}

	public void setInfo_source_entity_code(String info_source_entity_code) {
		this.info_source_entity_code = info_source_entity_code;
	}

	public String getInfo_source_name() {
		return info_source_name;
	}

	public void setInfo_source_name(String info_source_name) {
		this.info_source_name = info_source_name;
	}

	public String getInfo_source_identifer_code() {
		return info_source_identifer_code;
	}

	public void setInfo_source_identifer_code(String info_source_identifer_code) {
		this.info_source_identifer_code = info_source_identifer_code;
	}

	public String getInfo_source_identifer_code_qualifier() {
		return info_source_identifer_code_qualifier;
	}

	public void setInfo_source_identifer_code_qualifier(String info_source_identifer_code_qualifier) {
		this.info_source_identifer_code_qualifier = info_source_identifer_code_qualifier;
	}

	
	/*
	public String getSubmitter_name() {
		return submitter_name;
	}

	public void setSubmitter_name(String submitter_name) {
		this.submitter_name = submitter_name;
	}

	public String getSubmitter_identifer_code() {
		return submitter_identifer_code;
	}

	public void setSubmitter_identifer_code(String submitter_name_identifer_code) {
		this.submitter_identifer_code = submitter_name_identifer_code;
	}

	public String getSubmitter_identifer_code_qualifier() {
		return submitter_identifer_code_qualifier;
	}

	public void setSubmitter_identifer_code_qualifier(String submitter_name_identifer_code_qualifier) {
		this.submitter_identifer_code_qualifier = submitter_name_identifer_code_qualifier;
	}

	public String getBilling_provider_name() {
		return billing_provider_name;
	}

	public void setBilling_provider_name(String billing_provider_name) {
		this.billing_provider_name = billing_provider_name;
	}

	public String getBilling_provider_identifer_code() {
		return billing_provider_identifer_code;
	}

	public void setBilling_provider_identifer_code(String billing_provider_identifer_code) {
		this.billing_provider_identifer_code = billing_provider_identifer_code;
	}

	public String getBilling_provider_identifer_code_qualifier() {
		return billing_provider_identifer_code_qualifier;
	}

	public void setBilling_provider_identifer_code_qualifier(String billing_provider_identifer_code_qualifier) {
		this.billing_provider_identifer_code_qualifier = billing_provider_identifer_code_qualifier;
	}
	*/

	public String getPatient_last_name() {
		return patient_last_name;
	}

	public void setPatient_last_name(String patient_last_name) {
		this.patient_last_name = patient_last_name;
	}

	public String getPatient_first_name() {
		return patient_first_name;
	}

	public void setPatient_first_name(String patient_first_name) {
		this.patient_first_name = patient_first_name;
	}

	
	

	public String getPatient_policy_number() {
		return patient_policy_number;
	}

	public void setPatient_policy_number(String patient_policy_number) {
		this.patient_policy_number = patient_policy_number;
	}

	public String getClaim_id() {
		return claim_id;
	}

	public void setClaim_id(String claim_id) {
		this.claim_id = claim_id;
	}

	
	public String getStatus_effective_date() {
		return status_effective_date;
	}

	public void setStatus_effective_date(String claim_status_effective_date) {
		this.status_effective_date = claim_status_effective_date;
	}

	public String getStatus_category_code() {
		return status_category_code;
	}

	public void setStatus_category_code(String claim_status_category_code) {
		this.status_category_code = claim_status_category_code;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String claim_status_code) {
		this.status_code = claim_status_code;
	}

	public String getStatus_description() {
		return status_description;
	}

	public void setStatus_description(String claim_status_description) {
		this.status_description = claim_status_description;
	}

	/*
	public String getAction_code() {
		return action_code;
	}

	public void setAction_code(String action_code) {
		this.action_code = action_code;
	}
	*/

	public String getAmount_billed() {
		return amount_billed;
	}

	public void setAmount_billed(String amount_billed) {
		this.amount_billed = amount_billed;
	}

	public String getIcn() {
		return icn;
	}

	public void setIcn(String icn) {
		this.icn = icn;
	}	

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getPractice_id() {
		return practice_id;
	}

	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
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
	
	public String getSystem_ip() {
		return system_ip;
	}

	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
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
}