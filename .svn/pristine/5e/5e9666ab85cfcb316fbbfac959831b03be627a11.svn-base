package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMSchedulerTiming {
	
	@Id
	private Integer id;
	private String timing;
	private Integer slot_size;
	private Boolean is_temp_applied;
	private Boolean enabled;
	private String info;
	
	public ORMSchedulerTiming() {
		super();		
	}

	public ORMSchedulerTiming(Integer id, String timing, Integer slot_size, Boolean is_temp_applied,Boolean enabled, String info) {
		super();
		this.id = id;
		this.timing = timing;
		this.slot_size = slot_size;
		this.is_temp_applied = is_temp_applied;
		this.enabled=enabled;
		this.info = info;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public Integer getSlot_size() {
		return slot_size;
	}

	public void setSlot_size(Integer slot_size) {
		this.slot_size = slot_size;
	}

	public Boolean getIs_temp_applied() {
		return is_temp_applied;
	}

	public void setIs_temp_applied(Boolean is_temp_applied) {
		this.is_temp_applied = is_temp_applied;
	}

	
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnable(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}	
	
}
