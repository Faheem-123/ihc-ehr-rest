package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "audit_log")
public class ORMAuditLog {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long log_id;
	    private String module_name;
	    private String patient_id;
	    private String access;
	    private String access_date;
	    private String user_name;
	    private String practice_id;
	    private String system_ip;
	    private String client_access_date;
	    private String chart_id;
		public Long getLog_id() {
			return log_id;
		}
		public void setLog_id(Long log_id) {
			this.log_id = log_id;
		}
		public String getModule_name() {
			return module_name;
		}
		public void setModule_name(String module_name) {
			this.module_name = module_name;
		}
		public String getPatient_id() {
			return patient_id;
		}
		public void setPatient_id(String patient_id) {
			this.patient_id = patient_id;
		}
		public String getAccess() {
			return access;
		}
		public void setAccess(String access) {
			this.access = access;
		}
		public String getAccess_date() {
			return access_date;
		}
		public void setAccess_date(String access_date) {
			this.access_date = access_date;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public String getPractice_id() {
			return practice_id;
		}
		public void setPractice_id(String practice_id) {
			this.practice_id = practice_id;
		}
		public String getSystem_ip() {
			return system_ip;
		}
		public void setSystem_ip(String system_ip) {
			this.system_ip = system_ip;
		}
		public String getClient_access_date() {
			return client_access_date;
		}
		public void setClient_access_date(String client_access_date) {
			this.client_access_date = client_access_date;
		}
		public String getChart_id() {
			return chart_id;
		}
		public void setChart_id(String chart_id) {
			this.chart_id = chart_id;
		}
	    
	    

}
