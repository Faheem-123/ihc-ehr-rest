package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetGrowthChartInfo {

	@Id
	private String chart_id;
    private String final_years;
    private String final_months;
    private String dob;
    private String height_feet;
    private String height_meters;
    private String height_cm;
    private String weight_kg;
    private String weight_lbs;
    private String bmi;
    private String head_circumference_inch;
    private String head_circumference_cm;
    private String visit_date;
    private String years;
    private String months;
    private String days;
    private String lname;
    private String fname;
    private String gender;
	public String getChart_id() {
		return chart_id;
	}
	public void setChart_id(String chart_id) {
		this.chart_id = chart_id;
	}
	public String getFinal_years() {
		return final_years;
	}
	public void setFinal_years(String final_years) {
		this.final_years = final_years;
	}
	public String getFinal_months() {
		return final_months;
	}
	public void setFinal_months(String final_months) {
		this.final_months = final_months;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getHeight_feet() {
		return height_feet;
	}
	public void setHeight_feet(String height_feet) {
		this.height_feet = height_feet;
	}
	public String getHeight_meters() {
		return height_meters;
	}
	public void setHeight_meters(String height_meters) {
		this.height_meters = height_meters;
	}
	public String getHeight_cm() {
		return height_cm;
	}
	public void setHeight_cm(String height_cm) {
		this.height_cm = height_cm;
	}
	public String getWeight_kg() {
		return weight_kg;
	}
	public void setWeight_kg(String weight_kg) {
		this.weight_kg = weight_kg;
	}
	public String getWeight_lbs() {
		return weight_lbs;
	}
	public void setWeight_lbs(String weight_lbs) {
		this.weight_lbs = weight_lbs;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public String getHead_circumference_inch() {
		return head_circumference_inch;
	}
	public void setHead_circumference_inch(String head_circumference_inch) {
		this.head_circumference_inch = head_circumference_inch;
	}
	public String getHead_circumference_cm() {
		return head_circumference_cm;
	}
	public void setHead_circumference_cm(String head_circumference_cm) {
		this.head_circumference_cm = head_circumference_cm;
	}
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
    
    
}
