package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chart_socialhistory")
public class ORMChartSocialHistorySave {
	@Id
	private Long socialhistory_id;
	private Long patient_id;
	private Long chart_id;
	private String marital_status;	
	private String marital_status_code;
	private String sexual_orientation;	
	private String sexual_orientation_code;
	private String gender_identity;	
	private String gender_identity_code;
	private String children;
	private String education;
	private String occupation;
	private String diet_education;
	private String home_environment;
	
	private String smoking_status;
	private String smoking_snomed;	
	private String tobacco_type;
	private String packs_per_day;
	private String year_started;	
	private String smoking_start_date;
	private String smoking_end_date;
	private String tobacco_cessation;
	
	private String alcohol_per_day;
	private String caffeine_per_day;
	private String etoh;
	
	private String drug_use;
	private String quit_date;	
	private String exercise;
	private String seatbelt;
	private String exposure;		
	
	private String lmp;
	private String cycle;
	private String flow;
	private String gravida;
	private String para;
	private String dysmenomhea;	
	private String age_at_menarche;
	private String year_of_menopause;
	private Boolean pregnant;
	private String edd;	
	
	private String comment;
	
	private Long practice_id;	
	private String modified_user;
	private String created_user;
	private String client_date_created;	
	private String client_date_modified;
	private String date_modified;
	private String date_created;	
	private String system_ip;
	public Long getSocialhistory_id() {
		return socialhistory_id;
	}
	public void setSocialhistory_id(Long socialhistory_id) {
		this.socialhistory_id = socialhistory_id;
	}
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public Long getChart_id() {
		return chart_id;
	}
	public void setChart_id(Long chart_id) {
		this.chart_id = chart_id;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getMarital_status_code() {
		return marital_status_code;
	}
	public void setMarital_status_code(String marital_status_code) {
		this.marital_status_code = marital_status_code;
	}
	public String getSexual_orientation() {
		return sexual_orientation;
	}
	public void setSexual_orientation(String sexual_orientation) {
		this.sexual_orientation = sexual_orientation;
	}
	public String getSexual_orientation_code() {
		return sexual_orientation_code;
	}
	public void setSexual_orientation_code(String sexual_orientation_code) {
		this.sexual_orientation_code = sexual_orientation_code;
	}
	public String getGender_identity() {
		return gender_identity;
	}
	public void setGender_identity(String gender_identity) {
		this.gender_identity = gender_identity;
	}
	public String getGender_identity_code() {
		return gender_identity_code;
	}
	public void setGender_identity_code(String gender_identity_code) {
		this.gender_identity_code = gender_identity_code;
	}
	public String getChildren() {
		return children;
	}
	public void setChildren(String children) {
		this.children = children;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getDiet_education() {
		return diet_education;
	}
	public void setDiet_education(String diet_education) {
		this.diet_education = diet_education;
	}
	public String getHome_environment() {
		return home_environment;
	}
	public void setHome_environment(String home_environment) {
		this.home_environment = home_environment;
	}
	public String getSmoking_status() {
		return smoking_status;
	}
	public void setSmoking_status(String smoking_status) {
		this.smoking_status = smoking_status;
	}
	public String getSmoking_snomed() {
		return smoking_snomed;
	}
	public void setSmoking_snomed(String smoking_snomed) {
		this.smoking_snomed = smoking_snomed;
	}
	public String getTobacco_type() {
		return tobacco_type;
	}
	public void setTobacco_type(String tobacco_type) {
		this.tobacco_type = tobacco_type;
	}
	public String getPacks_per_day() {
		return packs_per_day;
	}
	public void setPacks_per_day(String packs_per_day) {
		this.packs_per_day = packs_per_day;
	}
	public String getYear_started() {
		return year_started;
	}
	public void setYear_started(String year_started) {
		this.year_started = year_started;
	}
	public String getSmoking_start_date() {
		return smoking_start_date;
	}
	public void setSmoking_start_date(String smoking_start_date) {
		this.smoking_start_date = smoking_start_date;
	}
	public String getSmoking_end_date() {
		return smoking_end_date;
	}
	public void setSmoking_end_date(String smoking_end_date) {
		this.smoking_end_date = smoking_end_date;
	}
	public String getTobacco_cessation() {
		return tobacco_cessation;
	}
	public void setTobacco_cessation(String tobacco_cessation) {
		this.tobacco_cessation = tobacco_cessation;
	}
	public String getAlcohol_per_day() {
		return alcohol_per_day;
	}
	public void setAlcohol_per_day(String alcohol_per_day) {
		this.alcohol_per_day = alcohol_per_day;
	}
	public String getCaffeine_per_day() {
		return caffeine_per_day;
	}
	public void setCaffeine_per_day(String caffeine_per_day) {
		this.caffeine_per_day = caffeine_per_day;
	}
	public String getEtoh() {
		return etoh;
	}
	public void setEtoh(String etoh) {
		this.etoh = etoh;
	}
	public String getDrug_use() {
		return drug_use;
	}
	public void setDrug_use(String drug_use) {
		this.drug_use = drug_use;
	}
	public String getQuit_date() {
		return quit_date;
	}
	public void setQuit_date(String quit_date) {
		this.quit_date = quit_date;
	}
	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	public String getSeatbelt() {
		return seatbelt;
	}
	public void setSeatbelt(String seatbelt) {
		this.seatbelt = seatbelt;
	}
	public String getExposure() {
		return exposure;
	}
	public void setExposure(String exposure) {
		this.exposure = exposure;
	}
	public String getLmp() {
		return lmp;
	}
	public void setLmp(String lmp) {
		this.lmp = lmp;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public String getGravida() {
		return gravida;
	}
	public void setGravida(String gravida) {
		this.gravida = gravida;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getDysmenomhea() {
		return dysmenomhea;
	}
	public void setDysmenomhea(String dysmenomhea) {
		this.dysmenomhea = dysmenomhea;
	}
	public String getAge_at_menarche() {
		return age_at_menarche;
	}
	public void setAge_at_menarche(String age_at_menarche) {
		this.age_at_menarche = age_at_menarche;
	}
	public String getYear_of_menopause() {
		return year_of_menopause;
	}
	public void setYear_of_menopause(String year_of_menopause) {
		this.year_of_menopause = year_of_menopause;
	}
	public Boolean getPregnant() {
		return pregnant;
	}
	public void setPregnant(Boolean pregnant) {
		this.pregnant = pregnant;
	}
	public String getEdd() {
		return edd;
	}
	public void setEdd(String edd) {
		this.edd = edd;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(Long practice_id) {
		this.practice_id = practice_id;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
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
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public String getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	@Override
	public String toString() {
		return "ORMChartSocialHistorySave [socialhistory_id=" + socialhistory_id + ", patient_id=" + patient_id
				+ ", chart_id=" + chart_id + ", marital_status=" + marital_status + ", marital_status_code="
				+ marital_status_code + ", sexual_orientation=" + sexual_orientation + ", sexual_orientation_code="
				+ sexual_orientation_code + ", gender_identity=" + gender_identity + ", gender_identity_code="
				+ gender_identity_code + ", children=" + children + ", education=" + education + ", occupation="
				+ occupation + ", diet_education=" + diet_education + ", home_environment=" + home_environment
				+ ", smoking_status=" + smoking_status + ", smoking_snomed=" + smoking_snomed + ", tobacco_type="
				+ tobacco_type + ", packs_per_day=" + packs_per_day + ", year_started=" + year_started
				+ ", smoking_start_date=" + smoking_start_date + ", smoking_end_date=" + smoking_end_date
				+ ", tobacco_cessation=" + tobacco_cessation + ", alcohol_per_day=" + alcohol_per_day
				+ ", caffeine_per_day=" + caffeine_per_day + ", etoh=" + etoh + ", drug_use=" + drug_use
				+ ", quit_date=" + quit_date + ", exercise=" + exercise + ", seatbelt=" + seatbelt + ", exposure="
				+ exposure + ", lmp=" + lmp + ", cycle=" + cycle + ", flow=" + flow + ", gravida=" + gravida + ", para="
				+ para + ", dysmenomhea=" + dysmenomhea + ", age_at_menarche=" + age_at_menarche
				+ ", year_of_menopause=" + year_of_menopause + ", pregnant=" + pregnant + ", edd=" + edd + ", comment="
				+ comment + ", practice_id=" + practice_id + ", modified_user=" + modified_user + ", created_user="
				+ created_user + ", client_date_created=" + client_date_created + ", client_date_modified="
				+ client_date_modified + ", date_modified=" + date_modified + ", date_created=" + date_created
				+ ", system_ip=" + system_ip + "]";
	}
	
	
}
