package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phr_login_log")
public class ORMPHRLoginuser {
	@Id
	private String logid;
    private String user_id;
    private String logintime;
    private String logouttime;
    private String hostname;
    private Boolean loginfail;
    private String practice_id;
    private String logintime_server;
    private String logouttime_server;
    private String system_ip;
    
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getLogouttime() {
		return logouttime;
	}
	public void setLogouttime(String logouttime) {
		this.logouttime = logouttime;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Boolean getLoginfail() {
		return loginfail;
	}
	public void setLoginfail(Boolean loginfail) {
		this.loginfail = loginfail;
	}
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public String getLogintime_server() {
		return logintime_server;
	}
	public void setLogintime_server(String logintime_server) {
		this.logintime_server = logintime_server;
	}
	public String getLogouttime_server() {
		return logouttime_server;
	}
	public void setLogouttime_server(String logouttime_server) {
		this.logouttime_server = logouttime_server;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
}
