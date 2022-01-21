package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetSchedulerAppointmentsMonthView {
	
	@Id
	Integer month_day_no;
	String month_date;
	String week_day;
	Integer week_day_no;
	//Integer week_no;	
	Integer total_appointments;
	Integer completed;
		
	public Integer getMonth_day_no() {
		return month_day_no;
	}

	public void setMonth_day_no(Integer month_day_no) {
		this.month_day_no = month_day_no;
	}	
	
	
	public String getMonth_date() {
		return month_date;
	}

	public void setMonth_date(String month_date) {
		this.month_date = month_date;
	}

	public String getWeek_day() {
		return week_day;
	}

	public void setWeek_day(String week_day) {
		this.week_day = week_day;
	}

	public Integer getWeek_day_no() {
		return week_day_no;
	}

	public void setWeek_day_no(Integer week_day_no) {
		this.week_day_no = week_day_no;
	}

//	public Integer getWeek_no() {
//		return week_no;
//	}
//
//	public void setWeek_no(Integer week_no) {
//		this.week_no = week_no;
//	}

	public Integer getTotal_appointments() {
		return total_appointments;
	}

	public void setTotal_appointments(Integer total_appointments) {
		this.total_appointments = total_appointments;
	}

	public Integer getCompleted() {
		return completed;
	}

	public void setCompleted(Integer completed) {
		this.completed = completed;
	}
	
	
	
}
