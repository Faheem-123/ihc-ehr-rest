package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetSchedulerProviderTempTiming {

	@Id
	private Long temp_timing_id;
	private String date_from;
	private String date_to;
	private String time_from;
	private String time_to;
	private String break_from;
	private String break_to;
	private Boolean off_day;
	private Boolean enable_break;
	private Integer slot_size;
	
	public Long getTemp_timing_id() {
		return temp_timing_id;
	}
	public void setTemp_timing_id(Long temp_timing_id) {
		this.temp_timing_id = temp_timing_id;
	}
	public String getDate_from() {
		return date_from;
	}
	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}
	public String getDate_to() {
		return date_to;
	}
	public void setDate_to(String date_to) {
		this.date_to = date_to;
	}
	public String getTime_from() {
		return time_from;
	}
	public void setTime_from(String time_from) {
		this.time_from = time_from;
	}
	public String getTime_to() {
		return time_to;
	}
	public void setTime_to(String time_to) {
		this.time_to = time_to;
	}
	public String getBreak_from() {
		return break_from;
	}
	public void setBreak_from(String break_from) {
		this.break_from = break_from;
	}
	public String getBreak_to() {
		return break_to;
	}
	public void setBreak_to(String break_to) {
		this.break_to = break_to;
	}
	public Boolean getOff_day() {
		return off_day;
	}
	public void setOff_day(Boolean off_day) {
		this.off_day = off_day;
	}
	public Boolean getEnable_break() {
		return enable_break;
	}
	public void setEnable_break(Boolean enable_break) {
		this.enable_break = enable_break;
	}
	public Integer getSlot_size() {
		return slot_size;
	}
	public void setSlot_size(Integer slot_size) {
		this.slot_size = slot_size;
	}	
}

