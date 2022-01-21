package com.ihc.ehr.model;

import java.util.List;

public class Wrapper_APIUserSave {

	private ORMAPIUserSave api_user_save;
	private List<ORMAPIUserPatientSave> lst_api_user_patients;
	private List<Long> lst_user_patient_ids_deleted;

	public ORMAPIUserSave getApi_user_save() {
		return api_user_save;
	}

	public void setApi_user_save(ORMAPIUserSave api_user_save) {
		this.api_user_save = api_user_save;
	}

	public List<ORMAPIUserPatientSave> getLst_api_user_patients() {
		return lst_api_user_patients;
	}

	public void setLst_api_user_patients(List<ORMAPIUserPatientSave> lst_api_user_patients) {
		this.lst_api_user_patients = lst_api_user_patients;
	}

	public List<Long> getLst_user_patient_ids_deleted() {
		return lst_user_patient_ids_deleted;
	}

	public void setLst_user_patient_ids_deleted(List<Long> lst_user_patient_ids_deleted) {
		this.lst_user_patient_ids_deleted = lst_user_patient_ids_deleted;
	}

}
