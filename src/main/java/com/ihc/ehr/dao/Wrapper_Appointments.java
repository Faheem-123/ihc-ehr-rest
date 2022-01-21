package com.ihc.ehr.dao;

import java.util.List;

import javax.persistence.Id;

public class Wrapper_Appointments<T> {

	@Id
	private Long id;
	private String name;	
	private int column_count;
	private int total_appointments;
	List<T> appointments;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getColumn_count() {
		return column_count;
	}
	public void setColumn_count(int column_count) {
		this.column_count = column_count;
	}
	public int getTotal_appointments() {
		return total_appointments;
	}
	public void setTotal_appointments(int total_appointments) {
		this.total_appointments = total_appointments;
	}
	public List<T> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<T> appointments) {
		this.appointments = appointments;
	}
		
}
