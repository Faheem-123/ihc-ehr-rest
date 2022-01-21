package com.ihc.ehr.model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMProWorkReport {
	@Id
    private String row_id;
    private String provider_id;
    private String provider_name;
    private String location_name;
    private String enc_total;
    private String total_app;
    private String notexist;
    private String loc_total;
    private String total_enc;
    private String claim_loc_total;
    private String claim_total;
    private String amt_loc_total;
    private String amt_total;
    private String is_exist;
    private String amt_paid;
    
	public String getRow_id() {
		return row_id;
	}
	public void setRow_id(String row_id) {
		this.row_id = row_id;
	}
	public String getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(String provider_id) {
		this.provider_id = provider_id;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getEnc_total() {
		return enc_total;
	}
	public void setEnc_total(String enc_total) {
		this.enc_total = enc_total;
	}
	public String getTotal_app() {
		return total_app;
	}
	public void setTotal_app(String total_app) {
		this.total_app = total_app;
	}
	public String getNotexist() {
		return notexist;
	}
	public void setNotexist(String notexist) {
		this.notexist = notexist;
	}
	public String getLoc_total() {
		return loc_total;
	}
	public void setLoc_total(String loc_total) {
		this.loc_total = loc_total;
	}
	public String getTotal_enc() {
		return total_enc;
	}
	public void setTotal_enc(String total_enc) {
		this.total_enc = total_enc;
	}
	public String getClaim_loc_total() {
		return claim_loc_total;
	}
	public void setClaim_loc_total(String claim_loc_total) {
		this.claim_loc_total = claim_loc_total;
	}
	public String getClaim_total() {
		return claim_total;
	}
	public void setClaim_total(String claim_total) {
		this.claim_total = claim_total;
	}
	public String getAmt_loc_total() {
		return amt_loc_total;
	}
	public void setAmt_loc_total(String amt_loc_total) {
		this.amt_loc_total = amt_loc_total;
	}
	public String getAmt_total() {
		return amt_total;
	}
	public void setAmt_total(String amt_total) {
		this.amt_total = amt_total;
	}
	public String getIs_exist() {
		return is_exist;
	}
	public void setIs_exist(String is_exist) {
		this.is_exist = is_exist;
	}
	public String getAmt_paid() {
		return amt_paid;
	}
	public void setAmt_paid(String amt_paid) {
		this.amt_paid = amt_paid;
	}
}
