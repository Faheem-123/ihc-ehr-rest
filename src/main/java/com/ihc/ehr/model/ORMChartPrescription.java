package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chart_prescription")
public class ORMChartPrescription {

	 @Id
	    private String chart_prescription_id;
	    private String patient_GUID;
	    private String drug_id;
	    private String drug_name;
	    private String drug_info;
	    private String start_date;
	    private String end_date;
	    private String issued_date;
	    private String strength;
	    private String strength_UOM;
	    private String take;
	    private String dosage_form;
	    private String route;
	    private String days_supply;
	    private String dosage_frequency;
	    private String quantity;
	    private String prn;
	    private String num_of_refills_allowed;
	    private String status;
	    private String sub_status;
	    private String archive;
	    private String prescription_Guid;
	    private String original_prescription_Guid;
	    private String formularytype_id;
	    private String Diagnosis;
	    private String diagnosis_source;
	    private String sig_text;
	    private String modified_sig;
	    private String print_leaflet;
	    private String deaclass_code;
	    private String finalstatus_type;
	    private String pharmacy_info;
	    private String pharmacy_ncdpdp;
	    private String takes_no_meds_flag;
	    private String drug_history_viewed_flag;
	    private String is_eprescription;
	    private String prescription_notes;
	    private String pharmacist_notes;
	    private String provider_name;
	    private String provider_id;
	    private String location_name;
	    private String patient_id;
	    private String chart_id;
	    private String practice_id;
	    private String deleted;
	    private String created_user;
	    private String client_date_created;
	    private String modified_user;
	    private String client_date_modified;
	    private String date_created;
	    private String date_modified;
	    private String pharmacy_type;
	    private String pharmacydetail_type;
	    private String finaldestination_type;
	    private String rxnorm;
	    private String drug_generic_name;
	    private String external_source;
	    private String drug_type_id;
	    private String substitution_allowed;
		public String getChart_prescription_id() {
			return chart_prescription_id;
		}
		public void setChart_prescription_id(String chart_prescription_id) {
			this.chart_prescription_id = chart_prescription_id;
		}
		public String getPatient_GUID() {
			return patient_GUID;
		}
		public void setPatient_GUID(String patient_GUID) {
			this.patient_GUID = patient_GUID;
		}
		public String getDrug_id() {
			return drug_id;
		}
		public void setDrug_id(String drug_id) {
			this.drug_id = drug_id;
		}
		public String getDrug_name() {
			return drug_name;
		}
		public void setDrug_name(String drug_name) {
			this.drug_name = drug_name;
		}
		public String getDrug_info() {
			return drug_info;
		}
		public void setDrug_info(String drug_info) {
			this.drug_info = drug_info;
		}
		public String getStart_date() {
			return start_date;
		}
		public void setStart_date(String start_date) {
			this.start_date = start_date;
		}
		public String getEnd_date() {
			return end_date;
		}
		public void setEnd_date(String end_date) {
			this.end_date = end_date;
		}
		public String getIssued_date() {
			return issued_date;
		}
		public void setIssued_date(String issued_date) {
			this.issued_date = issued_date;
		}
		public String getStrength() {
			return strength;
		}
		public void setStrength(String strength) {
			this.strength = strength;
		}
		public String getStrength_UOM() {
			return strength_UOM;
		}
		public void setStrength_UOM(String strength_UOM) {
			this.strength_UOM = strength_UOM;
		}
		public String getTake() {
			return take;
		}
		public void setTake(String take) {
			this.take = take;
		}
		public String getDosage_form() {
			return dosage_form;
		}
		public void setDosage_form(String dosage_form) {
			this.dosage_form = dosage_form;
		}
		public String getRoute() {
			return route;
		}
		public void setRoute(String route) {
			this.route = route;
		}
		public String getDays_supply() {
			return days_supply;
		}
		public void setDays_supply(String days_supply) {
			this.days_supply = days_supply;
		}
		public String getDosage_frequency() {
			return dosage_frequency;
		}
		public void setDosage_frequency(String dosage_frequency) {
			this.dosage_frequency = dosage_frequency;
		}
		public String getQuantity() {
			return quantity;
		}
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
		public String getPrn() {
			return prn;
		}
		public void setPrn(String prn) {
			this.prn = prn;
		}
		public String getNum_of_refills_allowed() {
			return num_of_refills_allowed;
		}
		public void setNum_of_refills_allowed(String num_of_refills_allowed) {
			this.num_of_refills_allowed = num_of_refills_allowed;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getSub_status() {
			return sub_status;
		}
		public void setSub_status(String sub_status) {
			this.sub_status = sub_status;
		}
		public String getArchive() {
			return archive;
		}
		public void setArchive(String archive) {
			this.archive = archive;
		}
		public String getPrescription_Guid() {
			return prescription_Guid;
		}
		public void setPrescription_Guid(String prescription_Guid) {
			this.prescription_Guid = prescription_Guid;
		}
		public String getOriginal_prescription_Guid() {
			return original_prescription_Guid;
		}
		public void setOriginal_prescription_Guid(String original_prescription_Guid) {
			this.original_prescription_Guid = original_prescription_Guid;
		}
		public String getFormularytype_id() {
			return formularytype_id;
		}
		public void setFormularytype_id(String formularytype_id) {
			this.formularytype_id = formularytype_id;
		}
		public String getDiagnosis() {
			return Diagnosis;
		}
		public void setDiagnosis(String diagnosis) {
			Diagnosis = diagnosis;
		}
		public String getDiagnosis_source() {
			return diagnosis_source;
		}
		public void setDiagnosis_source(String diagnosis_source) {
			this.diagnosis_source = diagnosis_source;
		}
		public String getSig_text() {
			return sig_text;
		}
		public void setSig_text(String sig_text) {
			this.sig_text = sig_text;
		}
		public String getModified_sig() {
			return modified_sig;
		}
		public void setModified_sig(String modified_sig) {
			this.modified_sig = modified_sig;
		}
		public String getPrint_leaflet() {
			return print_leaflet;
		}
		public void setPrint_leaflet(String print_leaflet) {
			this.print_leaflet = print_leaflet;
		}
		public String getDeaclass_code() {
			return deaclass_code;
		}
		public void setDeaclass_code(String deaclass_code) {
			this.deaclass_code = deaclass_code;
		}
		
		public String getFinalstatus_type() {
			return finalstatus_type;
		}
		public void setFinalstatus_type(String finalstatus_type) {
			this.finalstatus_type = finalstatus_type;
		}
		public String getPharmacy_info() {
			return pharmacy_info;
		}
		public void setPharmacy_info(String pharmacy_info) {
			this.pharmacy_info = pharmacy_info;
		}
		public String getPharmacy_ncdpdp() {
			return pharmacy_ncdpdp;
		}
		public void setPharmacy_ncdpdp(String pharmacy_ncdpdp) {
			this.pharmacy_ncdpdp = pharmacy_ncdpdp;
		}
		public String getTakes_no_meds_flag() {
			return takes_no_meds_flag;
		}
		public void setTakes_no_meds_flag(String takes_no_meds_flag) {
			this.takes_no_meds_flag = takes_no_meds_flag;
		}
		public String getDrug_history_viewed_flag() {
			return drug_history_viewed_flag;
		}
		public void setDrug_history_viewed_flag(String drug_history_viewed_flag) {
			this.drug_history_viewed_flag = drug_history_viewed_flag;
		}
		public String getIs_eprescription() {
			return is_eprescription;
		}
		public void setIs_eprescription(String is_eprescription) {
			this.is_eprescription = is_eprescription;
		}
		public String getPrescription_notes() {
			return prescription_notes;
		}
		public void setPrescription_notes(String prescription_notes) {
			this.prescription_notes = prescription_notes;
		}
		public String getPharmacist_notes() {
			return pharmacist_notes;
		}
		public void setPharmacist_notes(String pharmacist_notes) {
			this.pharmacist_notes = pharmacist_notes;
		}
		public String getProvider_name() {
			return provider_name;
		}
		public void setProvider_name(String provider_name) {
			this.provider_name = provider_name;
		}
		public String getProvider_id() {
			return provider_id;
		}
		public void setProvider_id(String provider_id) {
			this.provider_id = provider_id;
		}
		public String getLocation_name() {
			return location_name;
		}
		public void setLocation_name(String location_name) {
			this.location_name = location_name;
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
		public String getDeleted() {
			return deleted;
		}
		public void setDeleted(String deleted) {
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
		public String getPharmacy_type() {
			return pharmacy_type;
		}
		public void setPharmacy_type(String pharmacy_type) {
			this.pharmacy_type = pharmacy_type;
		}
		public String getPharmacydetail_type() {
			return pharmacydetail_type;
		}
		public void setPharmacydetail_type(String pharmacydetail_type) {
			this.pharmacydetail_type = pharmacydetail_type;
		}
		public String getFinaldestination_type() {
			return finaldestination_type;
		}
		public void setFinaldestination_type(String finaldestination_type) {
			this.finaldestination_type = finaldestination_type;
		}
		public String getRxnorm() {
			return rxnorm;
		}
		public void setRxnorm(String rxnorm) {
			this.rxnorm = rxnorm;
		}
		public String getDrug_generic_name() {
			return drug_generic_name;
		}
		public void setDrug_generic_name(String drug_generic_name) {
			this.drug_generic_name = drug_generic_name;
		}
		public String getExternal_source() {
			return external_source;
		}
		public void setExternal_source(String external_source) {
			this.external_source = external_source;
		}
		public String getDrug_type_id() {
			return drug_type_id;
		}
		public void setDrug_type_id(String drug_type_id) {
			this.drug_type_id = drug_type_id;
		}
		public String getSubstitution_allowed() {
			return substitution_allowed;
		}
		public void setSubstitution_allowed(String substitution_allowed) {
			this.substitution_allowed = substitution_allowed;
		}
	    
	    
}
