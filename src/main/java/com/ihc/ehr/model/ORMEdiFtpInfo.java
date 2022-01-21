package com.ihc.ehr.model;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Ihsan Ullah
 */
@Entity
public class ORMEdiFtpInfo  {
    @Id
    private String id;
    private String ftp_type;
    private String ftp_host;
    private String ftp_port;
    private String ftp_user;
    private String ftp_password;
    private String app_hosting_website;
    
    private String batch_upload_directory;
    private String era_download_directory;
    private String batch_response_directory;
    private String statement_upload_directory;
    private String claim_status_directory;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFtp_type() {
		return ftp_type;
	}
	public void setFtp_type(String ftp_type) {
		this.ftp_type = ftp_type;
	}
	public String getFtp_host() {
		return ftp_host;
	}
	public void setFtp_host(String ftp_host) {
		this.ftp_host = ftp_host;
	}
	public String getFtp_port() {
		return ftp_port;
	}
	public void setFtp_port(String ftp_port) {
		this.ftp_port = ftp_port;
	}
	public String getFtp_user() {
		return ftp_user;
	}
	public void setFtp_user(String ftp_user) {
		this.ftp_user = ftp_user;
	}
	public String getFtp_password() {
		return ftp_password;
	}
	public void setFtp_password(String ftp_password) {
		this.ftp_password = ftp_password;
	}
	public String getApp_hosting_website() {
		return app_hosting_website;
	}
	public void setApp_hosting_website(String app_hosting_website) {
		this.app_hosting_website = app_hosting_website;
	}
	public String getBatch_upload_directory() {
		return batch_upload_directory;
	}
	public void setBatch_upload_directory(String batch_upload_directory) {
		this.batch_upload_directory = batch_upload_directory;
	}
	public String getEra_download_directory() {
		return era_download_directory;
	}
	public void setEra_download_directory(String era_download_directory) {
		this.era_download_directory = era_download_directory;
	}
	public String getBatch_response_directory() {
		return batch_response_directory;
	}
	public void setBatch_response_directory(String batch_response_directory) {
		this.batch_response_directory = batch_response_directory;
	}
	public String getStatement_upload_directory() {
		return statement_upload_directory;
	}
	public void setStatement_upload_directory(String statement_upload_directory) {
		this.statement_upload_directory = statement_upload_directory;
	}
	public String getClaim_status_directory() {
		return claim_status_directory;
	}
	public void setClaim_status_directory(String claim_status_directory) {
		this.claim_status_directory = claim_status_directory;
	}
	
	
}

