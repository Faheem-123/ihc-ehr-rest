package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetSchedulerRooms {

	@Id
	private Long rooms_id;
	private String name;
	private Boolean is_color;
	private Boolean is_default;
	
	public Long getRooms_id() {
		return rooms_id;
	}
	public void setRooms_id(Long rooms_id) {
		this.rooms_id = rooms_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIs_color() {
		return is_color;
	}
	public void setIs_color(Boolean is_color) {
		this.is_color = is_color;
	}
	public Boolean getIs_default() {
		return is_default;
	}
	public void setIs_default(Boolean is_default) {
		this.is_default = is_default;
	}
	
	
	
	
}
