package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rooms")
public class ORMSaveLocationRooms {
	
	@Id
	private Long rooms_id;
	private String name;
	private String short_name;
	private Long location_id;
	private Long practice_id;	
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	private String system_ip;
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
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
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
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
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
	@Override
	public String toString() {
		return "ORMSaveLocationRooms [rooms_id=" + rooms_id + ", name=" + name + ", short_name=" + short_name
				+ ", location_id=" + location_id + ", practice_id=" + practice_id + ", created_user=" + created_user
				+ ", client_date_created=" + client_date_created + ", modified_user=" + modified_user
				+ ", client_date_modified=" + client_date_modified + ", date_created=" + date_created
				+ ", date_modified=" + date_modified + ", system_ip=" + system_ip + ", is_color=" + is_color
				+ ", is_default=" + is_default + "]";
	}
	
}
