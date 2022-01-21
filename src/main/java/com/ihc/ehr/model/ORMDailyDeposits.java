package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table (name ="dailydeposit")
@Entity
public class ORMDailyDeposits {
	@Id
    private Long dailydeposit_id;
    private String Check_number;
    private String check_date;
    private String check_amount;
    private String insurance_name;
    private String comments;
    private String created_user;
    private String date_created;
    private String modified_user;
    private String date_modified;
    private String client_date_created;
    private String client_date_modified;
    private String system_ip;
    private String deleted;
    private String practice_id;
    private String original_file_name;
    private String file_link;
    private String check_received_date;
    
	public Long getDailydeposit_id() {
		return dailydeposit_id;
	}
	public void setDailydeposit_id(Long dailydeposit_id) {
		this.dailydeposit_id = dailydeposit_id;
	}
	
	public String getCheck_number() {
		return Check_number;
	}
	public void setCheck_number(String check_number) {
		Check_number = check_number;
	}
	public String getCheck_date() {
		return check_date;
	}
	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}
	public String getCheck_amount() {
		return check_amount;
	}
	public void setCheck_amount(String check_amount) {
		this.check_amount = check_amount;
	}
	public String getInsurance_name() {
		return insurance_name;
	}
	public void setInsurance_name(String insurance_name) {
		this.insurance_name = insurance_name;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public String getOriginal_file_name() {
		return original_file_name;
	}
	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}
	public String getFile_link() {
		return file_link;
	}
	public void setFile_link(String file_link) {
		this.file_link = file_link;
	}
	public String getCheck_received_date() {
		return check_received_date;
	}
	public void setCheck_received_date(String check_received_date) {
		this.check_received_date = check_received_date;
	}
}
