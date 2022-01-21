package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_injury")
public class ORMGetPatientInjuery {

	 @Id
	    private String injury_id ;
	    private String attornery_id;
	    private String attorney_name;
	    private String firm_name;
	    private String attorney_address;
	    private String phone;
	    private String fax;
	    private String acc_date;
	    private String claim_no;
	    private String adj_name;
	    private String adj_phone;
	    private String adj_fax;
	    private String insurance_id;
	    private String ins_name;
	    private String start_date;
	    private String end_date;
	    private String created_user;
	    private String date_created;
	    private String modified_user;
	    private String date_modified;
	    private String client_date_created;
	    private String client_date_modified;
		public String getInjury_id() {
			return injury_id;
		}
		public void setInjury_id(String injury_id) {
			this.injury_id = injury_id;
		}
		public String getAttornery_id() {
			return attornery_id;
		}
		public void setAttornery_id(String attornery_id) {
			this.attornery_id = attornery_id;
		}
		public String getAttorney_name() {
			return attorney_name;
		}
		public void setAttorney_name(String attorney_name) {
			this.attorney_name = attorney_name;
		}
		public String getFirm_name() {
			return firm_name;
		}
		public void setFirm_name(String firm_name) {
			this.firm_name = firm_name;
		}
		public String getAttorney_address() {
			return attorney_address;
		}
		public void setAttorney_address(String attorney_address) {
			this.attorney_address = attorney_address;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getFax() {
			return fax;
		}
		public void setFax(String fax) {
			this.fax = fax;
		}
		public String getAcc_date() {
			return acc_date;
		}
		public void setAcc_date(String acc_date) {
			this.acc_date = acc_date;
		}
		public String getClaim_no() {
			return claim_no;
		}
		public void setClaim_no(String claim_no) {
			this.claim_no = claim_no;
		}
		public String getAdj_name() {
			return adj_name;
		}
		public void setAdj_name(String adj_name) {
			this.adj_name = adj_name;
		}
		public String getAdj_phone() {
			return adj_phone;
		}
		public void setAdj_phone(String adj_phone) {
			this.adj_phone = adj_phone;
		}
		public String getAdj_fax() {
			return adj_fax;
		}
		public void setAdj_fax(String adj_fax) {
			this.adj_fax = adj_fax;
		}
		public String getInsurance_id() {
			return insurance_id;
		}
		public void setInsurance_id(String insurance_id) {
			this.insurance_id = insurance_id;
		}
		public String getIns_name() {
			return ins_name;
		}
		public void setIns_name(String ins_name) {
			this.ins_name = ins_name;
		}
		public String getStart_date() {
			return start_date;
		}
		public void setStart_date(String start_date) {
			this.start_date = start_date;
		}
		public String getEnd_date() {
			return end_date;
		}
		public void setEnd_date(String end_date) {
			this.end_date = end_date;
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
}
