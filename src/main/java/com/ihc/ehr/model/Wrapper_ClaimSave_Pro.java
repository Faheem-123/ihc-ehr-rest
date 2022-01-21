package com.ihc.ehr.model;

import java.util.List;

public class Wrapper_ClaimSave_Pro {

	ORMClaimSave_Pro claimSave_Pro;
	List<ORMClaimDiagnosisSave_Pro> lstClaimDiagnosisSave_Pro;
	List<ORMClaimProceduresSave_Pro> lstClaimProceduresSave_Pro;
	List<ORMClaimInsuranceSave> lstClaimInsuranceSave;
	
	List<Long> lstDiagIdsDeleted;
	List<Long> lstProcIdsDeleted;
	List<Long> lstInsIdsDeleted;
	public ORMClaimSave_Pro getClaimSave_Pro() {
		return claimSave_Pro;
	}
	public void setClaimSave_Pro(ORMClaimSave_Pro claimSave_Pro) {
		this.claimSave_Pro = claimSave_Pro;
	}
	public List<ORMClaimDiagnosisSave_Pro> getLstClaimDiagnosisSave_Pro() {
		return lstClaimDiagnosisSave_Pro;
	}
	public void setLstClaimDiagnosisSave_Pro(List<ORMClaimDiagnosisSave_Pro> lstClaimDiagnosisSave_Pro) {
		this.lstClaimDiagnosisSave_Pro = lstClaimDiagnosisSave_Pro;
	}
	public List<ORMClaimProceduresSave_Pro> getLstClaimProceduresSave_Pro() {
		return lstClaimProceduresSave_Pro;
	}
	public void setLstClaimProceduresSave_Pro(List<ORMClaimProceduresSave_Pro> lstClaimProceduresSave_Pro) {
		this.lstClaimProceduresSave_Pro = lstClaimProceduresSave_Pro;
	}
	public List<ORMClaimInsuranceSave> getLstClaimInsuranceSave() {
		return lstClaimInsuranceSave;
	}
	public void setLstClaimInsuranceSave(List<ORMClaimInsuranceSave> lstClaimInsuranceSave) {
		this.lstClaimInsuranceSave = lstClaimInsuranceSave;
	}
	public List<Long> getLstDiagIdsDeleted() {
		return lstDiagIdsDeleted;
	}
	public void setLstDiagIdsDeleted(List<Long> lstDiagIdsDeleted) {
		this.lstDiagIdsDeleted = lstDiagIdsDeleted;
	}
	public List<Long> getLstProcIdsDeleted() {
		return lstProcIdsDeleted;
	}
	public void setLstProcIdsDeleted(List<Long> lstProcIdsDeleted) {
		this.lstProcIdsDeleted = lstProcIdsDeleted;
	}
	public List<Long> getLstInsIdsDeleted() {
		return lstInsIdsDeleted;
	}
	public void setLstInsIdsDeleted(List<Long> lstInsIdsDeleted) {
		this.lstInsIdsDeleted = lstInsIdsDeleted;
	}
	
	
	
}
