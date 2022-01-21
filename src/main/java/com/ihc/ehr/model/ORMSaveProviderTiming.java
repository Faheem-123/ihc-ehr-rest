package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="provider_timing")
public class ORMSaveProviderTiming {
	
	@Id
	private Long timing_id;
	private String week_day;
	private Boolean day_on;
	private String time_start;
	private String time_end;
	private Boolean break_on;
	private String break_start;
	private String break_end;
	private Integer slot_size;
	private String created_user;
	private String date_created;
	private String modified_user;
	private String date_modified;
	private String client_date_created;
	private String client_date_modified;
	private Long location_id;
	private Long provider_id;
	private Long practice_id;
	private String system_ip;
	public Long getTiming_id() {
		return timing_id;
	}
	public void setTiming_id(Long timing_id) {
		this.timing_id = timing_id;
	}
	public String getWeek_day() {
		return week_day;
	}
	public void setWeek_day(String week_day) {
		this.week_day = week_day;
	}
	public Boolean getDay_on() {
		return day_on;
	}
	public void setDay_on(Boolean day_on) {
		this.day_on = day_on;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public Boolean getBreak_on() {
		return break_on;
	}
	public void setBreak_on(Boolean break_on) {
		this.break_on = break_on;
	}
	public String getBreak_start() {
		return break_start;
	}
	public void setBreak_start(String break_start) {
		this.break_start = break_start;
	}
	public String getBreak_end() {
		return break_end;
	}
	public void setBreak_end(String break_end) {
		this.break_end = break_end;
	}
	public Integer getSlot_size() {
		return slot_size;
	}
	public void setSlot_size(Integer slot_size) {
		this.slot_size = slot_size;
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
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	public Long getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(Long provider_id) {
		this.provider_id = provider_id;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	@Override
	public String toString() {
		return "ORMSaveProviderTiming [timing_id=" + timing_id + ", week_day=" + week_day + ", day_on=" + day_on
				+ ", time_start=" + time_start + ", time_end=" + time_end + ", break_on=" + break_on + ", break_start="
				+ break_start + ", break_end=" + break_end + ", slot_size=" + slot_size + ", created_user="
				+ created_user + ", date_created=" + date_created + ", modified_user=" + modified_user
				+ ", date_modified=" + date_modified + ", client_date_created=" + client_date_created
				+ ", client_date_modified=" + client_date_modified + ", location_id=" + location_id + ", provider_id="
				+ provider_id + ", practice_id=" + practice_id + ", system_ip=" + system_ip + "]";
	}	
	
}
