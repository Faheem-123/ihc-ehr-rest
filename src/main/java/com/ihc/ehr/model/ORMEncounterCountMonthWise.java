package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMEncounterCountMonthWise {

	@Id
    private String s_no;    
    private String location_name;
    private String provider_name;
    private Boolean is_aggregate;       
    private Integer total;
    private Integer jan;
    private Integer feb;
    private Integer mar;
    private Integer apr;
    private Integer may;
    private Integer jun;
    private Integer jul;
    private Integer aug;
    private Integer sep;
    private Integer oct;
    private Integer nov;
    private Integer dec;
    
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
	public Boolean getIs_aggregate() {
		return is_aggregate;
	}
	public void setIs_aggregate(Boolean is_aggregate) {
		this.is_aggregate = is_aggregate;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getJan() {
		return jan;
	}
	public void setJan(Integer jan) {
		this.jan = jan;
	}
	public Integer getFeb() {
		return feb;
	}
	public void setFeb(Integer feb) {
		this.feb = feb;
	}
	public Integer getMar() {
		return mar;
	}
	public void setMar(Integer mar) {
		this.mar = mar;
	}
	public Integer getApr() {
		return apr;
	}
	public void setApr(Integer apr) {
		this.apr = apr;
	}
	public Integer getMay() {
		return may;
	}
	public void setMay(Integer may) {
		this.may = may;
	}
	public Integer getJun() {
		return jun;
	}
	public void setJun(Integer jun) {
		this.jun = jun;
	}
	public Integer getJul() {
		return jul;
	}
	public void setJul(Integer jul) {
		this.jul = jul;
	}
	public Integer getAug() {
		return aug;
	}
	public void setAug(Integer aug) {
		this.aug = aug;
	}
	public Integer getSep() {
		return sep;
	}
	public void setSep(Integer sep) {
		this.sep = sep;
	}
	public Integer getOct() {
		return oct;
	}
	public void setOct(Integer oct) {
		this.oct = oct;
	}
	public Integer getNov() {
		return nov;
	}
	public void setNov(Integer nov) {
		this.nov = nov;
	}
	public Integer getDec() {
		return dec;
	}
	public void setDec(Integer dec) {
		this.dec = dec;
	}
}
