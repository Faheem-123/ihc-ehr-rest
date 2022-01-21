package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "era_adjustment")
public class ORMEraAdjustmentSave {
	
	@Id    
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
    private Long era_adjustment_id;
    private String adjustment_group_code;
    private String adjustment_reason_code;
    private String adjustment_amount;
    private Long era_id;
    private Long era_claim_id;
    private Long era_claim_service_id;
    private String claim_procedures_id;
    private String claim_id;
    private Long practice_id;
    private String created_user;
    private String client_date_created;
    private String modified_user;
    private String client_date_modified;
    private String date_created;
    private String date_modified;
    private String system_ip;
    private Boolean deleted;
    
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
	public String getAdjustment_amount() {
		return adjustment_amount;
	}
	public void setAdjustment_amount(String adjustment_amount) {
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
	public String getClaim_procedures_id() {
		return claim_procedures_id;
	}
	public void setClaim_procedures_id(String claim_procedures_id) {
		this.claim_procedures_id = claim_procedures_id;
	}
	public String getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(String claim_id) {
		this.claim_id = claim_id;
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
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
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
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "ORMEraAdjustmentSave [era_adjustment_id=" + era_adjustment_id + ", adjustment_group_code="
				+ adjustment_group_code + ", adjustment_reason_code=" + adjustment_reason_code + ", adjustment_amount="
				+ adjustment_amount + ", era_id=" + era_id + ", era_claim_id=" + era_claim_id
				+ ", era_claim_service_id=" + era_claim_service_id + ", claim_procedures_id=" + claim_procedures_id
				+ ", claim_id=" + claim_id + ", practice_id=" + practice_id + ", created_user=" + created_user
				+ ", client_date_created=" + client_date_created + ", modified_user=" + modified_user
				+ ", client_date_modified=" + client_date_modified + ", date_created=" + date_created
				+ ", date_modified=" + date_modified + ", system_ip=" + system_ip + ", deleted=" + deleted
				+ ", getEra_adjustment_id()=" + getEra_adjustment_id() + ", getAdjustment_group_code()="
				+ getAdjustment_group_code() + ", getAdjustment_reason_code()=" + getAdjustment_reason_code()
				+ ", getAdjustment_amount()=" + getAdjustment_amount() + ", getEra_id()=" + getEra_id()
				+ ", getEra_claim_id()=" + getEra_claim_id() + ", getEra_claim_service_id()="
				+ getEra_claim_service_id() + ", getClaim_procedures_id()=" + getClaim_procedures_id()
				+ ", getClaim_id()=" + getClaim_id() + ", getPractice_id()=" + getPractice_id() + ", getCreated_user()="
				+ getCreated_user() + ", getClient_date_created()=" + getClient_date_created() + ", getModified_user()="
				+ getModified_user() + ", getClient_date_modified()=" + getClient_date_modified()
				+ ", getDate_created()=" + getDate_created() + ", getDate_modified()=" + getDate_modified()
				+ ", getSystem_ip()=" + getSystem_ip() + ", getDeleted()=" + getDeleted() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
