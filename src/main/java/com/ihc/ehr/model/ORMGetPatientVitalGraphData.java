package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMGetPatientVitalGraphData {
	
	@Id
	private Long chart_id;
	private String visit_date;
	private String height_feet;	
	private String weight_kg;
	private String temperature_fahren;
	private Integer systolic_bp1;
	private Integer diastolic_bp1;
	
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
	public String getWeight_kg() {
		return weight_kg;
	}
	public void setWeight_kg(String weight_kg) {
		this.weight_kg = weight_kg;
	}
	public String getTemperature_fahren() {
		return temperature_fahren;
	}
	public void setTemperature_fahren(String temperature_fahren) {
		this.temperature_fahren = temperature_fahren;
	}
	public Integer getSystolic_bp1() {
		return systolic_bp1;
	}
	public void setSystolic_bp1(Integer systolic_bp1) {
		this.systolic_bp1 = systolic_bp1;
	}
	public Integer getDiastolic_bp1() {
		return diastolic_bp1;
	}
	public void setDiastolic_bp1(Integer diastolic_bp1) {
		this.diastolic_bp1 = diastolic_bp1;
	}		
}
