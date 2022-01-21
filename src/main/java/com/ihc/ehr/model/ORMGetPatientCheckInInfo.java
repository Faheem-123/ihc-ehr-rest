package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientCheckInInfo {

	@Id
	private String appointment_id;
	private String provider_name;
	private String location;
	private String room;
	private String checkin_time;
	public String getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(String appointment_id) {
		this.appointment_id = appointment_id;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getCheckin_time() {
		return checkin_time;
	}
	public void setCheckin_time(String checkin_time) {
		this.checkin_time = checkin_time;
	}
	
	
}
