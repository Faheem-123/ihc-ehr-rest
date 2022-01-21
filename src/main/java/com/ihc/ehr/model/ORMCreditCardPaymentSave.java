package com.ihc.ehr.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card_payment")
public class ORMCreditCardPaymentSave {
	
	@Id
    private Long ccpayment_id;
    private Long patient_id;
    private Long cash_register_id;
    private String card_number;
    private String card_type;
    private String authcode;
    private String holder_first_name;
    private String holder_last_name;
    private String address;
    private String zip;
    private String city;
    private String state;
    private String comments;
    private String transaction_id;
    private String transaction_datetime;
    private BigDecimal charges;
    private String api_message;
    private Long practice_id;
    private String created_user;
    private String client_date_created;
    private String modified_user;
    private String client_date_modified;
    private String date_created;
    private String date_modified;
    private String phone;
    private String email;
    private String service_name;
	public Long getCcpayment_id() {
		return ccpayment_id;
	}
	public void setCcpayment_id(Long ccpayment_id) {
		this.ccpayment_id = ccpayment_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public Long getCash_register_id() {
		return cash_register_id;
	}
	public void setCash_register_id(Long cash_register_id) {
		this.cash_register_id = cash_register_id;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getHolder_first_name() {
		return holder_first_name;
	}
	public void setHolder_first_name(String holder_first_name) {
		this.holder_first_name = holder_first_name;
	}
	public String getHolder_last_name() {
		return holder_last_name;
	}
	public void setHolder_last_name(String holder_last_name) {
		this.holder_last_name = holder_last_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTransaction_datetime() {
		return transaction_datetime;
	}
	public void setTransaction_datetime(String transaction_datetime) {
		this.transaction_datetime = transaction_datetime;
	}
	public BigDecimal getCharges() {
		return charges;
	}
	public void setCharges(BigDecimal charges) {
		this.charges = charges;
	}
	public String getApi_message() {
		return api_message;
	}
	public void setApi_message(String api_message) {
		this.api_message = api_message;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	@Override
	public String toString() {
		return "ORMCreditCardPaymentSave [ccpayment_id=" + ccpayment_id + ", patient_id=" + patient_id
				+ ", cash_register_id=" + cash_register_id + ", card_number=" + card_number + ", card_type=" + card_type
				+ ", authcode=" + authcode + ", holder_first_name=" + holder_first_name + ", holder_last_name="
				+ holder_last_name + ", address=" + address + ", zip=" + zip + ", city=" + city + ", state=" + state
				+ ", comments=" + comments + ", transaction_id=" + transaction_id + ", transaction_datetime="
				+ transaction_datetime + ", charges=" + charges + ", api_message=" + api_message + ", practice_id="
				+ practice_id + ", created_user=" + created_user + ", client_date_created=" + client_date_created
				+ ", modified_user=" + modified_user + ", client_date_modified=" + client_date_modified
				+ ", date_created=" + date_created + ", date_modified=" + date_modified + ", phone=" + phone
				+ ", email=" + email + ", service_name=" + service_name + "]";
	}
	
}
