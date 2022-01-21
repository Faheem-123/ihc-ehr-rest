package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMImmRegClinicsGet {

	@Id
	private Long id;
	private Long registry_id;
	private String registry_code;
	private String rec_facility_name;
	private String clinic_id;
	private String clinic_code;
	private String clinic_name;
	private String clinic_type;
	private Long location_id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRegistry_id() {
		return registry_id;
	}
	public void setRegistry_id(Long registry_id) {
		this.registry_id = registry_id;
	}
	public String getRegistry_code() {
		return registry_code;
	}
	public void setRegistry_code(String registry_code) {
		this.registry_code = registry_code;
	}
	public String getRec_facility_name() {
		return rec_facility_name;
	}
	public void setRec_facility_name(String rec_facility_name) {
		this.rec_facility_name = rec_facility_name;
	}
	public String getClinic_id() {
		return clinic_id;
	}
	public void setClinic_id(String clinic_id) {
		this.clinic_id = clinic_id;
	}
	public String getClinic_code() {
		return clinic_code;
	}
	public void setClinic_code(String clinic_code) {
		this.clinic_code = clinic_code;
	}
	public String getClinic_name() {
		return clinic_name;
	}
	public void setClinic_name(String clinic_name) {
		this.clinic_name = clinic_name;
	}
	public String getClinic_type() {
		return clinic_type;
	}
	public void setClinic_type(String clinic_type) {
		this.clinic_type = clinic_type;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
}
