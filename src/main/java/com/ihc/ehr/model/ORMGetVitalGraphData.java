package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chart_vital")
public class ORMGetVitalGraphData {
	@Id
    private Long chart_id;
    private String visit_date;
    private String height_feet;
    private String height_meters;
    private String weight_kg;
    private String weight_lbs;
    private String bmi;
    private String temperature_centi;
    private String temperature_fahren;
    private String systolic_bp1;
    private String systolic_bp2;
    private String diastolic_bp1;
    private String diastolic_bp2;
    private String pulse;
    private String pulse_bp2;
    
	
	public Long getChart_id() {
		return chart_id;
	}
	public void setChart_id(Long chart_id) {
		this.chart_id = chart_id;
	}
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
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
	public String getTemperature_centi() {
		return temperature_centi;
	}
	public void setTemperature_centi(String temperature_centi) {
		this.temperature_centi = temperature_centi;
	}
	public String getTemperature_fahren() {
		return temperature_fahren;
	}
	public void setTemperature_fahren(String temperature_fahren) {
		this.temperature_fahren = temperature_fahren;
	}
	public String getSystolic_bp1() {
		return systolic_bp1;
	}
	public void setSystolic_bp1(String systolic_bp1) {
		this.systolic_bp1 = systolic_bp1;
	}
	public String getSystolic_bp2() {
		return systolic_bp2;
	}
	public void setSystolic_bp2(String systolic_bp2) {
		this.systolic_bp2 = systolic_bp2;
	}
	public String getDiastolic_bp1() {
		return diastolic_bp1;
	}
	public void setDiastolic_bp1(String diastolic_bp1) {
		this.diastolic_bp1 = diastolic_bp1;
	}
	public String getDiastolic_bp2() {
		return diastolic_bp2;
	}
	public void setDiastolic_bp2(String diastolic_bp2) {
		this.diastolic_bp2 = diastolic_bp2;
	}
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	public String getPulse_bp2() {
		return pulse_bp2;
	}
	public void setPulse_bp2(String pulse_bp2) {
		this.pulse_bp2 = pulse_bp2;
	}
}
