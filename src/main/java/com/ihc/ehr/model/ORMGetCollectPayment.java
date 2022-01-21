package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetCollectPayment {
	@Id
	private String collect_payment_id;
    private String cash;
    private String cradit_card;
    private String check_amt;
    private String mony_order;
    private String total;
    private String adjusted_amt;
    private String comments;
    private String collect_by;
    private String collect_date;
    private String location_name;
    
	public String getCollect_payment_id() {
		return collect_payment_id;
	}
	public void setCollect_payment_id(String collect_payment_id) {
		this.collect_payment_id = collect_payment_id;
	}
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	public String getCradit_card() {
		return cradit_card;
	}
	public void setCradit_card(String cradit_card) {
		this.cradit_card = cradit_card;
	}
	public String getCheck_amt() {
		return check_amt;
	}
	public void setCheck_amt(String check_amt) {
		this.check_amt = check_amt;
	}
	public String getMony_order() {
		return mony_order;
	}
	public void setMony_order(String mony_order) {
		this.mony_order = mony_order;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getAdjusted_amt() {
		return adjusted_amt;
	}
	public void setAdjusted_amt(String adjusted_amt) {
		this.adjusted_amt = adjusted_amt;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCollect_by() {
		return collect_by;
	}
	public void setCollect_by(String collect_by) {
		this.collect_by = collect_by;
	}
	public String getCollect_date() {
		return collect_date;
	}
	public void setCollect_date(String collect_date) {
		this.collect_date = collect_date;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
}
