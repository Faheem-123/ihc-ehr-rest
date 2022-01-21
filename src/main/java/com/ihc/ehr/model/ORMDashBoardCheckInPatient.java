package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMDashBoardCheckInPatient {
	 @Id
	    private String appointment_id;
	    private String patient_name;
	    private String room_name;
	    private String location_name;
	    private String app_time; 
	    private String checkin_time;
	    private String location_id;
	    private String provider_id;
	    private String provider_name;
	    private String rooms_id;
	    private String patient_id;
	    private Boolean is_color;
	    private String dob;
	    
	    
	    
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getAppointment_id() {
			return appointment_id;
		}
		public void setAppointment_id(String appointment_id) {
			this.appointment_id = appointment_id;
		}
		public String getPatient_name() {
			return patient_name;
		}
		public void setPatient_name(String patient_name) {
			this.patient_name = patient_name;
		}
		public String getRoom_name() {
			return room_name;
		}
		public void setRoom_name(String room_name) {
			this.room_name = room_name;
		}
		public String getLocation_name() {
			return location_name;
		}
		public void setLocation_name(String location_name) {
			this.location_name = location_name;
		}
		public String getApp_time() {
			return app_time;
		}
		public void setApp_time(String app_time) {
			this.app_time = app_time;
		}
		public String getCheckin_time() {
			return checkin_time;
		}
		public void setCheckin_time(String checkin_time) {
			this.checkin_time = checkin_time;
		}
		public String getLocation_id() {
			return location_id;
		}
		public void setLocation_id(String location_id) {
			this.location_id = location_id;
		}
		public String getProvider_id() {
			return provider_id;
		}
		public void setProvider_id(String provider_id) {
			this.provider_id = provider_id;
		}
		public String getProvider_name() {
			return provider_name;
		}
		public void setProvider_name(String provider_name) {
			this.provider_name = provider_name;
		}
		public String getRooms_id() {
			return rooms_id;
		}
		public void setRooms_id(String rooms_id) {
			this.rooms_id = rooms_id;
		}
		public String getPatient_id() {
			return patient_id;
		}
		public void setPatient_id(String patient_id) {
			this.patient_id = patient_id;
		}
		public Boolean getIs_color() {
			return is_color;
		}
		public void setIs_color(Boolean is_color) {
			this.is_color = is_color;
		}
	    
	    
}
