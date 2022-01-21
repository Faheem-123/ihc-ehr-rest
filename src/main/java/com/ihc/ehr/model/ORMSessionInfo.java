package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chart_sessionalinfo")
public class ORMSessionInfo {
	@Id
	private Long sessional_id;
	private String patient_id;
	private String chart_id;
	private String practice_id;
	private String time_in;
	private String time_out;
	private String session_no;
	private boolean individual;
	private boolean couple_family;
	private boolean initial_assessment;
	private boolean collateral_consult;
	private boolean grouptype;
	private boolean significant_improv;
	private boolean suicide;
	private boolean homicide;
	private boolean violence;
	private String ddl_shv;
	private boolean moderate_improv;
	private boolean mental_status;
	private String ddl_msc;
	private boolean minimal_improv;
	private boolean no_change;
	private boolean deteriorated;
	private boolean changeinmedno;
	private boolean changeinmedyes;
	private boolean treatmentplanunchanged;
	private boolean treatmentplanmodified;
	private String notes;
	private boolean deleted;
	private String created_user;
	private String client_date_created;
	private String modified_user;
	private String client_date_modified;
	private String date_created;
	private String date_modified;
	
	public Long getSessional_id() {
		return sessional_id;
	}
	public void setSessional_id(Long sessional_id) {
		this.sessional_id = sessional_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getChart_id() {
		return chart_id;
	}
	public void setChart_id(String chart_id) {
		this.chart_id = chart_id;
	}
	public String getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(String practice_id) {
		this.practice_id = practice_id;
	}
	public String getTime_in() {
		return time_in;
	}
	public void setTime_in(String time_in) {
		this.time_in = time_in;
	}
	public String getTime_out() {
		return time_out;
	}
	public void setTime_out(String time_out) {
		this.time_out = time_out;
	}
	public String getSession_no() {
		return session_no;
	}
	public void setSession_no(String session_no) {
		this.session_no = session_no;
	}
	public boolean isIndividual() {
		return individual;
	}
	public void setIndividual(boolean individual) {
		this.individual = individual;
	}
	public boolean isCouple_family() {
		return couple_family;
	}
	public void setCouple_family(boolean couple_family) {
		this.couple_family = couple_family;
	}
	public boolean isInitial_assessment() {
		return initial_assessment;
	}
	public void setInitial_assessment(boolean initial_assessment) {
		this.initial_assessment = initial_assessment;
	}
	public boolean isCollateral_consult() {
		return collateral_consult;
	}
	public void setCollateral_consult(boolean collateral_consult) {
		this.collateral_consult = collateral_consult;
	}
	public boolean isGrouptype() {
		return grouptype;
	}
	public void setGrouptype(boolean grouptype) {
		this.grouptype = grouptype;
	}
	public boolean isSignificant_improv() {
		return significant_improv;
	}
	public void setSignificant_improv(boolean significant_improv) {
		this.significant_improv = significant_improv;
	}
	public boolean isSuicide() {
		return suicide;
	}
	public void setSuicide(boolean suicide) {
		this.suicide = suicide;
	}
	public boolean isHomicide() {
		return homicide;
	}
	public void setHomicide(boolean homicide) {
		this.homicide = homicide;
	}
	public boolean isViolence() {
		return violence;
	}
	public void setViolence(boolean violence) {
		this.violence = violence;
	}
	public String getDdl_shv() {
		return ddl_shv;
	}
	public void setDdl_shv(String ddl_shv) {
		this.ddl_shv = ddl_shv;
	}
	public boolean isModerate_improv() {
		return moderate_improv;
	}
	public void setModerate_improv(boolean moderate_improv) {
		this.moderate_improv = moderate_improv;
	}
	public boolean isMental_status() {
		return mental_status;
	}
	public void setMental_status(boolean mental_status) {
		this.mental_status = mental_status;
	}
	public String getDdl_msc() {
		return ddl_msc;
	}
	public void setDdl_msc(String ddl_msc) {
		this.ddl_msc = ddl_msc;
	}
	public boolean isMinimal_improv() {
		return minimal_improv;
	}
	public void setMinimal_improv(boolean minimal_improv) {
		this.minimal_improv = minimal_improv;
	}
	public boolean isNo_change() {
		return no_change;
	}
	public void setNo_change(boolean no_change) {
		this.no_change = no_change;
	}
	public boolean isDeteriorated() {
		return deteriorated;
	}
	public void setDeteriorated(boolean deteriorated) {
		this.deteriorated = deteriorated;
	}
	public boolean isChangeinmedno() {
		return changeinmedno;
	}
	public void setChangeinmedno(boolean changeinmedno) {
		this.changeinmedno = changeinmedno;
	}
	public boolean isChangeinmedyes() {
		return changeinmedyes;
	}
	public void setChangeinmedyes(boolean changeinmedyes) {
		this.changeinmedyes = changeinmedyes;
	}
	public boolean isTreatmentplanunchanged() {
		return treatmentplanunchanged;
	}
	public void setTreatmentplanunchanged(boolean treatmentplanunchanged) {
		this.treatmentplanunchanged = treatmentplanunchanged;
	}
	public boolean isTreatmentplanmodified() {
		return treatmentplanmodified;
	}
	public void setTreatmentplanmodified(boolean treatmentplanmodified) {
		this.treatmentplanmodified = treatmentplanmodified;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
	@Override
	public String toString() {
		return "ORMSessionInfo [sessional_id=" + sessional_id + ", patient_id=" + patient_id + ", chart_id=" + chart_id
				+ ", practice_id=" + practice_id + ", time_in=" + time_in + ", time_out=" + time_out + ", session_no="
				+ session_no + ", individual=" + individual + ", couple_family=" + couple_family
				+ ", initial_assessment=" + initial_assessment + ", collateral_consult=" + collateral_consult
				+ ", grouptype=" + grouptype + ", significant_improv=" + significant_improv + ", suicide=" + suicide
				+ ", homicide=" + homicide + ", violence=" + violence + ", ddl_shv=" + ddl_shv + ", moderate_improv="
				+ moderate_improv + ", mental_status=" + mental_status + ", ddl_msc=" + ddl_msc + ", minimal_improv="
				+ minimal_improv + ", no_change=" + no_change + ", deteriorated=" + deteriorated + ", changeinmedno="
				+ changeinmedno + ", changeinmedyes=" + changeinmedyes + ", treatmentplanunchanged="
				+ treatmentplanunchanged + ", treatmentplanmodified=" + treatmentplanmodified + ", notes=" + notes
				+ ", deleted=" + deleted + ", created_user=" + created_user + ", client_date_created="
				+ client_date_created + ", modified_user=" + modified_user + ", client_date_modified="
				+ client_date_modified + ", date_created=" + date_created + ", date_modified=" + date_modified + "]";
	}
	
}
