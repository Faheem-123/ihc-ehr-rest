package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "insurance")
public class ORMGetClient_Insurances {
	 @Id
     private String insurance_id;
     private String name;
     private String payerid;
     private String address;
     private String city;
     private String state;
     private String zip;
     private String phone;
     private String fax;
     private String email;
     private String attn;
     private String website;
     private String date_created;
     private String created_user;
     private String client_date_created;
     private String date_modified;
     private String modified_user;
     private String client_date_modified;
     private Boolean deleted;
     private Boolean chk;
     private String practice_id;

 public String getPractice_id() {
     return practice_id;
 }

 public void setPractice_id(String practice_id) {
     this.practice_id = practice_id;
 }
     

 public String getAddress() {
     return address;
 }

 public void setAddress(String address) {
     this.address = address;
 }

 public String getAttn() {
     return attn;
 }

 public void setAttn(String attn) {
     this.attn = attn;
 }

 public Boolean getChk() {
     return chk;
 }

 public void setChk(Boolean chk) {
     this.chk = chk;
 }

 public String getCity() {
     return city;
 }

 public void setCity(String city) {
     this.city = city;
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

 public String getDate_modified() {
     return date_modified;
 }

 public void setDate_modified(String date_modified) {
     this.date_modified = date_modified;
 }

 public Boolean getDeleted() {
     return deleted;
 }

 public void setDeleted(Boolean deleted) {
     this.deleted = deleted;
 }

 public String getEmail() {
     return email;
 }

 public void setEmail(String email) {
     this.email = email;
 }

 public String getFax() {
     return fax;
 }

 public void setFax(String fax) {
     this.fax = fax;
 }

 public String getInsurance_id() {
     return insurance_id;
 }

 public void setInsurance_id(String insurance_id) {
     this.insurance_id = insurance_id;
 }

 public String getModified_user() {
     return modified_user;
 }

 public void setModified_user(String modified_user) {
     this.modified_user = modified_user;
 }

 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public String getPayerid() {
     return payerid;
 }

 public void setPayerid(String payerid) {
     this.payerid = payerid;
 }

 public String getPhone() {
     return phone;
 }

 public void setPhone(String phone) {
     this.phone = phone;
 }

 public String getState() {
     return state;
 }

 public void setState(String state) {
     this.state = state;
 }

 public String getWebsite() {
     return website;
 }

 public void setWebsite(String website) {
     this.website = website;
 }

 public String getZip() {
     return zip;
 }

 public void setZip(String zip) {
     this.zip = zip;
 }
     
}
