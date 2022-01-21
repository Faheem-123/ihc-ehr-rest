package com.ihc.ehr.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ClaimStatusUpdateModel;
import com.ihc.ehr.model.ORMBillingProviderTaxomonyListGet;
import com.ihc.ehr.model.ORMCashRegisterAdd;
import com.ihc.ehr.model.ORMClaimDiagnosisGet_Pro;
import com.ihc.ehr.model.ORMClaimDiagnosisSave_Pro;
import com.ihc.ehr.model.ORMClaimGet_Pro;
import com.ihc.ehr.model.ORMClaimInsuranceGet;
import com.ihc.ehr.model.ORMClaimInsuranceSave;
import com.ihc.ehr.model.ORMClaimNotesGet;
import com.ihc.ehr.model.ORMClaimNotesSave;
import com.ihc.ehr.model.ORMClaimPaymentAdjustmentGet;
import com.ihc.ehr.model.ORMClaimPaymentAdjustmentSave;
import com.ihc.ehr.model.ORMClaimPaymentGet;
import com.ihc.ehr.model.ORMClaimPaymentSave;
import com.ihc.ehr.model.ORMClaimProceduresGet_Pro;
import com.ihc.ehr.model.ORMClaimProceduresSave_Pro;
import com.ihc.ehr.model.ORMClaimReminderGet;
import com.ihc.ehr.model.ORMClaimReminderSave;
import com.ihc.ehr.model.ORMClaimSave_Pro;
import com.ihc.ehr.model.ORMClaimSummary;
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMEobEraCheckDetails;
import com.ihc.ehr.model.ORMEobGet;
import com.ihc.ehr.model.ORMEraPaymentInfoGet;
import com.ihc.ehr.model.ORMFacilityList;
import com.ihc.ehr.model.ORMFile_Name;
import com.ihc.ehr.model.ORMGetAttorneyDetail;
import com.ihc.ehr.model.ORMGetCPTWisePatientPaymentForRefund;
import com.ihc.ehr.model.ORMGetCPTsWithBalance;
import com.ihc.ehr.model.ORMGetClaimImportDiagnosis;
import com.ihc.ehr.model.ORMGetClaimImportLabOrder;
import com.ihc.ehr.model.ORMGetClaimImportProcedures;
import com.ihc.ehr.model.ORMGetClaimPostedPayment;
import com.ihc.ehr.model.ORMGetClaimRulePatientInfo;
import com.ihc.ehr.model.ORMGetClaimSuperBillDetail;
import com.ihc.ehr.model.ORMGetClaimUnResolvedCahRegisterPayment;
import com.ihc.ehr.model.ORMGetPatientStatement;
import com.ihc.ehr.model.ORMGetPatientStatementLog;
import com.ihc.ehr.model.ORMGetPaymentInsurances;
import com.ihc.ehr.model.ORMGetPreviousCalimImport;
import com.ihc.ehr.model.ORMGetProcedureSearch;
import com.ihc.ehr.model.ORMGetProcesuresForPosting;
import com.ihc.ehr.model.ORMGetStatementDetail;
import com.ihc.ehr.model.ORMGetStatementPdf;
import com.ihc.ehr.model.ORMGetSuperBillCategoryList;
import com.ihc.ehr.model.ORMIdCode;
import com.ihc.ehr.model.ORMIdCodeDescription;
import com.ihc.ehr.model.ORMIdCodeDescriptionType;
import com.ihc.ehr.model.ORMIdName;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientNotesSave;
import com.ihc.ehr.model.ORMPatientStatementNotes;
import com.ihc.ehr.model.ORMPracticeInfo;
import com.ihc.ehr.model.ORMSavePatientAttorney_log;
import com.ihc.ehr.model.ORMSavePatientStatement_log;
import com.ihc.ehr.model.ORMStatementPDF;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORMspGetPatientClaims;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.Wrapper_ClaimPaymentSave;
import com.ihc.ehr.model.Wrapper_ClaimSave_Pro;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.util.BillingGeneral;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;
import com.ihc.ehr.util.EnumUtil;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.FileUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.PDFOperations;

@Repository
public class ClaimDAOImpl implements ClaimDAO {

	@Autowired
	private DBOperations db;

	@Autowired
	private BillingGeneral billingGeneral;
	@Autowired
	private GeneralDAOImpl generalDaoImpl;

	@Override
	public List<ORMClaimSummary> getClaimSummary(Long patient_id, Boolean due_only, Boolean showdeleted) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("show_deleted", showdeleted.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("due_only", due_only.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetClaimSummary", ORMClaimSummary.class, lstParam);
	}

	@Override
	public List<ORMFacilityList> getFacilityList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetFacilityList", ORMFacilityList.class, lstParam);

	}

	@Override
	public ORMClaimGet_Pro getClaim(Long claim_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));

		List<ORMClaimGet_Pro> lst = db.getStoreProcedureData("spGetClaim_Pro", ORMClaimGet_Pro.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return (ORMClaimGet_Pro) lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMClaimDiagnosisGet_Pro> getProClaimDiagnosis(Long claim_id, Boolean showdeleted) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("show_deleted", showdeleted.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetProClaimDiagnosis_Pro", ORMClaimDiagnosisGet_Pro.class, lstParam);
	}

	@Override
	public List<ORMClaimProceduresGet_Pro> getProClaimProcedures(Long claim_id, Boolean showdeleted) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("show_deleted", showdeleted.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetClaimProcedures_Pro", ORMClaimProceduresGet_Pro.class, lstParam);
	}

	@Override
	public List<ORMIdCodeDescription> getPracticePOSList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPracticePOSList", ORMIdCodeDescription.class, lstParam);

	}

	@Override
	public List<ORMIdCodeDescription> getModifierList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetModifierList", ORMIdCodeDescription.class, null);
	}

	@Override
	public List<ORMClaimInsuranceGet> getClaimInsurance(Long claim_id, Boolean showdeleted) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("show_deleted", showdeleted.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetClaimInsurance", ORMClaimInsuranceGet.class, lstParam);
	}

	@Override
	public ORMGetClaimRulePatientInfo getClaimRulePatientInfo(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetClaimRulePatientInfo> lst = db.getStoreProcedureData("spGetClaimRulePatientInfo",
				ORMGetClaimRulePatientInfo.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return (ORMGetClaimRulePatientInfo) lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMGetClaimImportProcedures> getClaimImportProc(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<ORMGetClaimImportProcedures> lst = null;
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getOption().equalsIgnoreCase("encounter_procedures")) {
			lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
					ParameterMode.IN));

			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
					lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
				}
			}

			lst = db.getStoreProcedureData("spClaimImportEncounterProcedures", ORMGetClaimImportProcedures.class,
					lstParam);

		} else if (searchCriteria.getOption().equalsIgnoreCase("lab_procedures")) {

			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
					lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
				}
			}

			lst = db.getStoreProcedureData("spCalimImportLabProcedures", ORMGetClaimImportProcedures.class, lstParam);
		}

		return lst;
	}

	@Override
	public List<ORMGetClaimImportDiagnosis> getClaimImportDiag(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<ORMGetClaimImportDiagnosis> lst = null;
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getOption().equalsIgnoreCase("encounter_diagnosis")) {
			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
					lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
				}
			}

			lst = db.getStoreProcedureData("spClaimImportEncounterDiagnosis", ORMGetClaimImportDiagnosis.class,
					lstParam);

		} else if (searchCriteria.getOption().equalsIgnoreCase("lab_diagnosis")) {

			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
					lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
				}
			}

			lst = db.getStoreProcedureData("spClaimImportLabDiagnosis", ORMGetClaimImportDiagnosis.class, lstParam);
		}

		return lst;
	}

	@Override
	public List<ORMGetClaimImportLabOrder> getClaimImportLabOrders(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<ORMGetClaimImportLabOrder> lst = null;
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		lst = db.getStoreProcedureData("spGetCalimImportLabOrders", ORMGetClaimImportLabOrder.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMIdName> getSuperBillList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetSuperBillList", ORMIdName.class, lstParam);
	}

	@Override
	public List<ORMGetSuperBillCategoryList> getSuperBillCategoriesList(Long bill_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("bill_id", bill_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetSuperbillCategory", ORMGetSuperBillCategoryList.class, lstParam);
	}

	@Override
	public List<ORMGetClaimSuperBillDetail> getSuperBillDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<ORMGetClaimSuperBillDetail> lst = null;
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		lst = db.getStoreProcedureData("spGetSuperbillDetail", ORMGetClaimSuperBillDetail.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMClaimNotesGet> getClaimNotes(Long claim_id, String user, String editableOnly) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("user", user.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("editable_only", editableOnly.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetClaimNotes", ORMClaimNotesGet.class, lstParam);
	}

	@Override
	public List<ORMIdCodeDescriptionType> getChartFollowUpDiagnosis(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<ORMIdCodeDescriptionType> lst = null;
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		lst = db.getStoreProcedureData("spChartFollowUpProblems", ORMIdCodeDescriptionType.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMGetProcedureSearch> getChartFollowUpProcedures(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<ORMGetProcedureSearch> lst = null;
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		lst = db.getStoreProcedureData("spChartFollowUpProcedures", ORMGetProcedureSearch.class, lstParam);

		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveClaimNotes(ORMClaimNotesSave ormClaimNotesSave,
			Boolean add_to_patient_notes) {
		// TODO Auto-generated method stub
		int result = 0;

		Long notesId;

		Boolean isNew = false;

		ormClaimNotesSave.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (ormClaimNotesSave.getNotes_id() == null) {
			isNew = true;
			notesId = db.IDGenerator("claim_notes", ormClaimNotesSave.getPractice_id());
			ormClaimNotesSave.setNotes_id(notesId);
			ormClaimNotesSave.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(ormClaimNotesSave, EnumUtil.Operation.ADD);

		} else {
			notesId = ormClaimNotesSave.getNotes_id();
			result = db.SaveEntity(ormClaimNotesSave, EnumUtil.Operation.EDIT);
		}

		if (add_to_patient_notes) {
			if (isNew) {

				Long patNotesId = db.IDGenerator("patient_note", ormClaimNotesSave.getPractice_id());
				ORMPatientNotesSave objPatNote = new ORMPatientNotesSave();
				objPatNote.setPatient_note_id(patNotesId);
				objPatNote.setPatient_id(ormClaimNotesSave.getPatient_id());
				objPatNote.setNotes(ormClaimNotesSave.getNotes());
				objPatNote.setAlert(true);
				objPatNote.setPractice_id(ormClaimNotesSave.getPractice_id());
				objPatNote.setModified_user(ormClaimNotesSave.getModified_user());
				objPatNote.setClient_date_modified(ormClaimNotesSave.getClient_date_modified());
				objPatNote.setNotes_type("billing");
				objPatNote.setCreated_user(ormClaimNotesSave.getCreated_user());
				objPatNote.setClient_date_created(ormClaimNotesSave.getClient_date_created());
				objPatNote.setDate_created(ormClaimNotesSave.getDate_created());
				objPatNote.setDate_modified(ormClaimNotesSave.getDate_modified());
				objPatNote.setNote_type_id(ormClaimNotesSave.getNotes_id());

				db.SaveEntity(objPatNote, EnumUtil.Operation.ADD);

			} else {

				String strUpdateQuery = "update patient_note set notes='"
						+ ormClaimNotesSave.getNotes().replaceAll("'", "`") + "',modified_user='"
						+ ormClaimNotesSave.getModified_user() + "',client_date_modified='"
						+ ormClaimNotesSave.getClient_date_modified() + "',date_modified='"
						+ ormClaimNotesSave.getDate_modified() + "' where note_type_id='"
						+ ormClaimNotesSave.getNotes_id() + "' and notes_type='billing' ";

				db.ExecuteUpdateQuery(strUpdateQuery);

			}
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(notesId.toString());
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveClaimPro(Wrapper_ClaimSave_Pro wrapper_ClaimSave_Pro) {
		// TODO Auto-generated method stub
		int result = 0;

		ORMClaimSave_Pro claimSave_Pro = wrapper_ClaimSave_Pro.getClaimSave_Pro();
		List<ORMClaimDiagnosisSave_Pro> lstClaimDiagnosisSave_Pro = wrapper_ClaimSave_Pro
				.getLstClaimDiagnosisSave_Pro();
		List<ORMClaimProceduresSave_Pro> lstClaimProceduresSave_Pro = wrapper_ClaimSave_Pro
				.getLstClaimProceduresSave_Pro();
		List<ORMClaimInsuranceSave> lstClaimInsuranceSave = wrapper_ClaimSave_Pro.getLstClaimInsuranceSave();

		List<Long> lstDiagIdsDeleted = wrapper_ClaimSave_Pro.getLstDiagIdsDeleted();
		List<Long> lstProcIdsDeleted = wrapper_ClaimSave_Pro.getLstProcIdsDeleted();
		List<Long> lstInsIdsDeleted = wrapper_ClaimSave_Pro.getLstInsIdsDeleted();

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (claimSave_Pro != null) {

			// *** Claim
			claimSave_Pro.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (claimSave_Pro.getClaim_id() == null) {

				claimSave_Pro.setClaim_id(db.IDGenerator("claim", claimSave_Pro.getPractice_id()));
				claimSave_Pro.setDate_created(DateTimeUtil.getCurrentDateTime());

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, claimSave_Pro));
			} else {
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, claimSave_Pro));
			}
			// *** END Claim

			// *** Claim Diagnosis
			if (lstClaimDiagnosisSave_Pro != null && lstClaimDiagnosisSave_Pro.size() > 0) {

				for (ORMClaimDiagnosisSave_Pro ormClaimDiagnosisSave_Pro : lstClaimDiagnosisSave_Pro) {

					ormClaimDiagnosisSave_Pro.setDate_modified(DateTimeUtil.getCurrentDateTime());
					ormClaimDiagnosisSave_Pro.setClaim_id(claimSave_Pro.getClaim_id());
					if (ormClaimDiagnosisSave_Pro.getClaim_diagnosis_id() == null) {

						ormClaimDiagnosisSave_Pro
								.setClaim_diagnosis_id(db.IDGenerator("claim_diagnos", claimSave_Pro.getPractice_id()));
						ormClaimDiagnosisSave_Pro.setDate_created(DateTimeUtil.getCurrentDateTime());

						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimDiagnosisSave_Pro));
					} else {
						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormClaimDiagnosisSave_Pro));
					}
				}

			}

			if (lstDiagIdsDeleted != null && lstDiagIdsDeleted.size() > 0) {

				String strDelIds = "";
				for (Long delId : lstDiagIdsDeleted) {
					if (strDelIds != "")
						strDelIds += ",";
					strDelIds += "'" + delId.toString() + "'";
				}

				String strDiagDelQuery = "update claim_diagnos set deleted=1,date_modified=getdate(),client_date_modified='"
						+ claimSave_Pro.getClient_date_modified() + "',system_ip='" + claimSave_Pro.getSystem_ip()
						+ "' where claim_diagnosis_id in (" + strDelIds + ")";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strDiagDelQuery));
			}

			// ** END Claim Diagnosis

			// *** Claim Procedures
			if (lstClaimProceduresSave_Pro != null && lstClaimProceduresSave_Pro.size() > 0) {

				for (ORMClaimProceduresSave_Pro ormClaimProceduresSave_Pro : lstClaimProceduresSave_Pro) {

					ormClaimProceduresSave_Pro.setDate_modified(DateTimeUtil.getCurrentDateTime());
					ormClaimProceduresSave_Pro.setClaim_id(claimSave_Pro.getClaim_id());
					if (ormClaimProceduresSave_Pro.getClaim_procedures_id() == null) {

						ormClaimProceduresSave_Pro.setClaim_procedures_id(
								db.IDGenerator("claim_procedure", claimSave_Pro.getPractice_id()));
						ormClaimProceduresSave_Pro.setDate_created(DateTimeUtil.getCurrentDateTime());

						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimProceduresSave_Pro));
					} else {
						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormClaimProceduresSave_Pro));
					}
				}

			}

			if (lstProcIdsDeleted != null && lstProcIdsDeleted.size() > 0) {

				String strDelIds = "";
				for (Long delId : lstProcIdsDeleted) {
					if (strDelIds != "")
						strDelIds += ",";
					strDelIds += "'" + delId.toString() + "'";
				}

				String strDiagDelQuery = "update claim_procedure set deleted=1,date_modified=getdate(),client_date_modified='"
						+ claimSave_Pro.getClient_date_modified() + "',system_ip='" + claimSave_Pro.getSystem_ip()
						+ "' where claim_procedures_id in (" + strDelIds + ")";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strDiagDelQuery));
			}

			// ** END Claim Procedures

			// *** Claim Insurance
			if (lstClaimInsuranceSave != null && lstClaimInsuranceSave.size() > 0) {

				for (ORMClaimInsuranceSave ormClaimInsuranceSave : lstClaimInsuranceSave) {

					ormClaimInsuranceSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
					ormClaimInsuranceSave.setClaim_id(claimSave_Pro.getClaim_id());
					if (ormClaimInsuranceSave.getClaiminsurance_id() == null) {

						ormClaimInsuranceSave.setClaiminsurance_id(
								db.IDGenerator("claim_insurance", claimSave_Pro.getPractice_id()));
						ormClaimInsuranceSave.setDate_created(DateTimeUtil.getCurrentDateTime());

						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimInsuranceSave));
					} else {
						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormClaimInsuranceSave));
					}
				}

			}

			if (lstInsIdsDeleted != null && lstInsIdsDeleted.size() > 0) {

				String strDelIds = "";
				for (Long delId : lstInsIdsDeleted) {
					if (strDelIds != "")
						strDelIds += ",";
					strDelIds += "'" + delId.toString() + "'";
				}

				String strDiagDelQuery = "update claim_insurance set deleted=1,date_modified=getdate(),client_date_modified='"
						+ claimSave_Pro.getClient_date_modified() + "',system_ip='" + claimSave_Pro.getSystem_ip()
						+ "' where claiminsurance_id in (" + strDelIds + ")";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strDiagDelQuery));
			}

			// ** END Claim Insurance

			// if patient paid cpt is deleted then restore patient payment.
			if (lstProcIdsDeleted != null && lstProcIdsDeleted.size() > 0) {

				String query;

				for (Long claimProcid : lstProcIdsDeleted) {

					query = " update cash_register set resolved=0,date_resolved=null,resolved_by=null where cash_register_id in "
							+ " ("
							+ "	select eob_era_id from claim_payment where ISNULL(deleted,0)<>1 and ISNULL(eob_era_id_type,'')='CASH_REGISTER' "
							+ "	AND claim_procedures_id='" + claimProcid.toString() + "' " + " ); "
							+ " update claim_payment set deleted=1,date_modified=GETDATE(),modified_user='"
							+ claimSave_Pro.getModified_user() + "',client_date_modified='"
							+ claimSave_Pro.getClient_date_modified() + "',system_ip='" + claimSave_Pro.getSystem_ip()
							+ "' " + " where claim_procedures_id='" + claimProcid.toString() + "' ;";

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));
				}
			}

			result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result > 0) {

				if (!claimSave_Pro.getDraft()) {

					billingGeneral.AutoPostCashRegistrPayment(claimSave_Pro.getPractice_id(),
							claimSave_Pro.getPatient_id(), claimSave_Pro.getClaim_id(), claimSave_Pro.getSelf_pay(),
							claimSave_Pro.getClient_date_created(), claimSave_Pro.getCreated_user(),
							claimSave_Pro.getSystem_ip(), "");
					/*
					 * int cashRegPaymentPostedResult =
					 * billingGeneral.MapCashRegisterPayment(claimSave_Pro.getPractice_id(),
					 * claimSave_Pro.getPatient_id(), claimSave_Pro.getClaim_id(),
					 * claimSave_Pro.getSelf_pay(), claimSave_Pro.getClient_date_created(),
					 * claimSave_Pro.getCreated_user(), claimSave_Pro.getSystem_ip());
					 * 
					 * billingGeneral.UpdateClaimBalance(claimSave_Pro.getClaim_id(),
					 * claimSave_Pro.getClient_date_modified(), claimSave_Pro.getModified_user(),
					 * claimSave_Pro.getSystem_ip());
					 * 
					 * if (cashRegPaymentPostedResult > 0) {
					 * billingGeneral.UpdateClaimStatus(claimSave_Pro.getClaim_id(),
					 * claimSave_Pro.getClient_date_modified(), claimSave_Pro.getModified_user(),
					 * claimSave_Pro.getSystem_ip()); }
					 */
				}
			}

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
				resp.setResult(claimSave_Pro.getClaim_id().toString());
			}

		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("No claim data is provided");
		}

		return resp;
	}

	private int MapCashRegisterPayment(ORMClaimSave_Pro claimSave_Pro) {

		int result = 0;

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(
				new SpParameters("claim_id", claimSave_Pro.getClaim_id().toString(), String.class, ParameterMode.IN));

		List<ORMGetClaimUnResolvedCahRegisterPayment> lstCash = db.getStoreProcedureData(
				"spGetClaimUnResovledCashRegisterPayment", ORMGetClaimUnResolvedCahRegisterPayment.class, lstParam);

		if (lstCash != null && lstCash.size() > 0) {

			List<SpParameters> lstParamCpts = new ArrayList<>();
			lstParamCpts.add(new SpParameters("patient_id", claimSave_Pro.getPatient_id().toString(), String.class,
					ParameterMode.IN));
			lstParamCpts.add(new SpParameters("claim_id", claimSave_Pro.getClaim_id().toString(), String.class,
					ParameterMode.IN));
			lstParamCpts.add(new SpParameters("dos", "", String.class, ParameterMode.IN));
			lstParamCpts.add(new SpParameters("provider_id", "", String.class, ParameterMode.IN));
			lstParamCpts.add(new SpParameters("location_id", "", String.class, ParameterMode.IN));
			lstParamCpts.add(new SpParameters("cash_register_id", "", String.class, ParameterMode.IN));

			List<ORMGetProcesuresForPosting> lstCPTs = db.getStoreProcedureData("spGetProceduresForPosting",
					ORMGetProcesuresForPosting.class, lstParamCpts);

			float cpt_balance = 0;
			float paid_amount = 0;
			float write_off_amount = 0;

			float total_payment = 0;
			float total_write_off = 0;
			String serverDateTime = DateTimeUtil.getCurrentDateTime();

			// ArrayList<ORMClaimPaymentSave> lstClaimPaymentSave=new ArrayList<>();

			// auto post only if no of cpt=1 or claim is self pay
			if (lstCPTs != null && lstCPTs.size() > 0 && (lstCPTs.size() == 1 || claimSave_Pro.getSelf_pay() == true)) {

				// auto post only if no of cpt=1 or claim is self pay
				// if (lstCPTs.size() > 1
				// && (claimSave_Pro.getSelf_pay() == null || claimSave_Pro.getSelf_pay() ==
				// false)) {
				// return 0;
				// }

				for (ORMGetClaimUnResolvedCahRegisterPayment objCash : lstCash) {

					List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

					total_payment = objCash.getPaid_pending().floatValue();
					total_write_off = objCash.getWrite_off_pending().floatValue();

					for (ORMGetProcesuresForPosting objProc : lstCPTs) {

						cpt_balance = objProc.getCpt_balance().floatValue();
						paid_amount = 0;
						write_off_amount = 0;

						if (cpt_balance > 0 && (total_payment > 0 || total_write_off > 0)) {
							if (total_payment > 0) {
								if (total_payment >= cpt_balance) {
									paid_amount = cpt_balance;
									cpt_balance = 0;
									total_payment = total_payment - paid_amount;
								} else {
									paid_amount = total_payment;
									total_payment = 0;
									cpt_balance = cpt_balance - paid_amount;
								}
							}

							if (cpt_balance > 0 && total_write_off > 0) {
								if (total_write_off >= cpt_balance) {
									write_off_amount = cpt_balance;
									// cpt_balance=0;
									total_write_off = total_write_off - write_off_amount;
								} else {
									write_off_amount = total_write_off;
									total_write_off = 0;
									// cpt_balance=cpt_balance-write_off_amount;
								}
							}

							if (paid_amount > 0 || write_off_amount > 0) {

								ORMClaimPaymentSave ormPaymentSave = new ORMClaimPaymentSave();

								ormPaymentSave.setClaim_payments_id(
										db.IDGenerator("claim_payment", claimSave_Pro.getPractice_id()));
								ormPaymentSave.setClaim_procedures_id(objProc.getClaim_procedures_id());
								ormPaymentSave.setCharged_procedure(objProc.getProc_code());
								ormPaymentSave.setPaid_procedure(objProc.getProc_code());
								ormPaymentSave.setPayment_source("Patient");
								ormPaymentSave.setEntry_type("Cash Register");
								ormPaymentSave.setEob_era_id(objCash.getCash_register_id());
								ormPaymentSave.setEob_era_id_type("CASH_REGISTER");
								ormPaymentSave.setClaim_id(objProc.getClaim_id());
								ormPaymentSave.setPatient_id(objCash.getPatient_id());
								ormPaymentSave.setPaid_amount(BigDecimal.valueOf(paid_amount));
								ormPaymentSave.setWriteoff_amount(BigDecimal.valueOf(write_off_amount));
								ormPaymentSave.setPractice_id(claimSave_Pro.getPractice_id());

								if (GeneralOperations.isNotNullorEmpty(objCash.getCheck_number())) {
									ormPaymentSave.setCheck_number(objCash.getCheck_number());
									ormPaymentSave.setCheck_date(objCash.getCheck_date());
								}

								ormPaymentSave.setClient_date_created(claimSave_Pro.getClient_date_created());
								ormPaymentSave.setClient_date_modified(claimSave_Pro.getClient_date_modified());
								ormPaymentSave.setCreated_user(claimSave_Pro.getCreated_user());
								ormPaymentSave.setModified_user(claimSave_Pro.getModified_user());
								ormPaymentSave.setSystem_ip(claimSave_Pro.getSystem_ip());

								ormPaymentSave.setDate_modified(serverDateTime);
								ormPaymentSave.setDate_created(serverDateTime);

								lstEntityWrapper
										.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormPaymentSave));
								// result = result + hibernate.add(ormPayment);
							}

						}
					}

					if (lstEntityWrapper != null && lstEntityWrapper.size() > 0) {

						ORMClaimNotesSave ormClaimNotesSave = new ORMClaimNotesSave();

						ormClaimNotesSave.setNotes_id(db.IDGenerator("claim_notes", claimSave_Pro.getPractice_id()));

						ormClaimNotesSave.setDate_created(serverDateTime);
						ormClaimNotesSave.setDate_modified(serverDateTime);

						ormClaimNotesSave.setClient_date_created(claimSave_Pro.getClient_date_modified());
						ormClaimNotesSave.setClient_date_modified(claimSave_Pro.getClient_date_modified());

						ormClaimNotesSave.setCreated_user(claimSave_Pro.getCreated_user());
						ormClaimNotesSave.setModified_user(claimSave_Pro.getCreated_user());

						ormClaimNotesSave.setPatient_id(claimSave_Pro.getPatient_id());
						ormClaimNotesSave.setClaim_id(claimSave_Pro.getClaim_id());

						ormClaimNotesSave.setPractice_id(claimSave_Pro.getPractice_id());

						ormClaimNotesSave.setNotes("Cash Register Payment Auto Imported.");

						ormClaimNotesSave.setIs_auto(true);

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimNotesSave));

						result = db.AddUpdateEntityWrapper(lstEntityWrapper);

						if (result > 0) {
							billingGeneral.AutoResolveCashRegister(objCash.getCash_register_id(),
									claimSave_Pro.getCreated_user());
						}

					}

				}
			}
		}

		return result;

	}

	@Override
	public List<ORMIdCodeDescription> getClaimAdjustmentGroupCodesList(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));

			}

		}

		return db.getStoreProcedureData("spGetClaimAdjusmtnetGroupCodesList", ORMIdCodeDescription.class, lstParam);

	}

	@Override
	public List<ORMIdCode> getClaimAdjustmentReasonCodesList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetAdjustmentReasonCodesList", ORMIdCode.class, null);
	}

	@Override
	public List<ORMGetPaymentInsurances> getPaymentInsurances(Long claim_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPaymentInsurances", ORMGetPaymentInsurances.class, lstParam);
	}

	@Override
	public List<ORMClaimPaymentGet> getClaimPayment(Long claim_id, Boolean showdeleted) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("show_deleted", showdeleted.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetClaimPayment", ORMClaimPaymentGet.class, lstParam);
	}

	@Override
	public List<ORMClaimPaymentAdjustmentGet> getClaimPaymentAdjustments(Long claim_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetClaimPaymentAdjustment", ORMClaimPaymentAdjustmentGet.class, lstParam);
	}

	@Override
	public List<ORMGetProcesuresForPosting> getProceduresForPosting(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spGetProceduresForPosting", ORMGetProcesuresForPosting.class, lstParam);

	}

	@Override
	public ORMEobEraCheckDetails getEobEraCheckDetailsById(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue().trim(), String.class, ParameterMode.IN));
			}
		}

		List<ORMEobEraCheckDetails> lst = db.getStoreProcedureData("spGetEobEraCheckDetailsById",
				ORMEobEraCheckDetails.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return (ORMEobEraCheckDetails) lst.get(0);
		} else {
			return null;
		}

	}

	@Override
	public ServiceResponse<ORMKeyValue> saveClaimPayment(Wrapper_ClaimPaymentSave wrapperClaimPaymentSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		String claimNote = "";
		Boolean autoNotes = false;
		Boolean isCashRegResolve = false;
		Long cashRegisterId = (long) 0;
		Long selectedCashRegisterId = (long) 0;

		List<ORMClaimPaymentSave> lstClaimPaymentSave = wrapperClaimPaymentSave.getLstClaimPaymentSave();
		List<ORMClaimPaymentAdjustmentSave> lstClaimPaymentAdjustmentSave = wrapperClaimPaymentSave
				.getLstClaimPaymentAdjustmentSave();
		List<ORMKeyValue> lstKeyValue = wrapperClaimPaymentSave.getLstKeyValue();

		ORMCashRegisterAdd cashRegisterAdd = wrapperClaimPaymentSave.getCashRegisterAdd();

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		if (lstKeyValue != null) {

			for (ORMKeyValue item : lstKeyValue) {

				switch (item.getKey()) {
				case "CLAIM_NOTE":
					claimNote = item.getValue();
					break;
				case "RESOLVE_CASH_REGISTER_ENTRY":
					isCashRegResolve = Boolean.parseBoolean(item.getValue());
					break;
				case "IS_AUTO_CLAIM_NOTE":
					autoNotes = Boolean.parseBoolean(item.getValue());
					break;

				default:
					break;
				}
			}
		}

		if (lstClaimPaymentSave != null && lstClaimPaymentSave.size() > 0) {

			Long patientId = lstClaimPaymentSave.get(0).getPatient_id();
			String serverDateTime = DateTimeUtil.getCurrentDateTime();
			Long practiceId = lstClaimPaymentSave.get(0).getPractice_id();
			String clientDateTime = lstClaimPaymentSave.get(0).getClient_date_modified();
			String clientUser = lstClaimPaymentSave.get(0).getModified_user();

			if (cashRegisterAdd != null) {

				cashRegisterAdd.setDate_modified(DateTimeUtil.getCurrentDateTime());

				cashRegisterId = db.IDGenerator("cash_register", cashRegisterAdd.getPractice_id());
				cashRegisterAdd.setCash_register_id(cashRegisterId);

				cashRegisterAdd.setDate_created(DateTimeUtil.getCurrentDateTime());
				cashRegisterAdd
						.setReceipt_no(billingGeneral.GenerateCashRegisterReceiptNo(cashRegisterAdd.getPractice_id()));

				if (isCashRegResolve) {

					cashRegisterAdd.setResolved(true);
					cashRegisterAdd.setResolved_by(clientUser);
					cashRegisterAdd.setDate_resolved(clientDateTime);
				}
				// result = db.SaveEntity(cashRegisterAdd, EnumUtil.Operation.ADD);
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, cashRegisterAdd));
			}

			Long selectedClaimId = (long) 0;
			for (ORMClaimPaymentSave ormClaimPaymentSave : lstClaimPaymentSave) {

				Long claimPaymentIdTemp = ormClaimPaymentSave.getClaim_payments_id();

				Long claimPaymentId = db.IDGenerator("claim_payment", ormClaimPaymentSave.getPractice_id());
				ormClaimPaymentSave.setClaim_payments_id(claimPaymentId);
				ormClaimPaymentSave.setDate_created(serverDateTime);
				ormClaimPaymentSave.setDate_modified(serverDateTime);

				if (cashRegisterId > 0 && ormClaimPaymentSave.getEob_era_id_type().equalsIgnoreCase("CASH_REGISTER")
						&& (ormClaimPaymentSave.getEob_era_id() == null || ormClaimPaymentSave.getEob_era_id() == 0)) {
					ormClaimPaymentSave.setEob_era_id(cashRegisterId);
				}

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimPaymentSave));

				if (lstClaimPaymentAdjustmentSave != null && lstClaimPaymentAdjustmentSave.size() > 0) {
					for (ORMClaimPaymentAdjustmentSave ormClaimPaymentAdjustmentSave : lstClaimPaymentAdjustmentSave) {

						if (ormClaimPaymentAdjustmentSave.getClaim_payments_id().equals(claimPaymentIdTemp)) {

							ormClaimPaymentAdjustmentSave.setAdjustment_id(
									db.IDGenerator("claim_payment_adjustments", ormClaimPaymentSave.getPractice_id()));
							ormClaimPaymentAdjustmentSave.setClaim_payments_id(claimPaymentId);
							ormClaimPaymentAdjustmentSave.setDate_created(serverDateTime);
							ormClaimPaymentAdjustmentSave.setDate_modified(serverDateTime);

							lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD,
									ormClaimPaymentAdjustmentSave));
						}
					}
				}

				if (!claimNote.isEmpty()) {
					if (!selectedClaimId.equals(ormClaimPaymentSave.getClaim_id())) {
						selectedClaimId = ormClaimPaymentSave.getClaim_id();

						ORMClaimNotesSave ormClaimNotesSave = new ORMClaimNotesSave();

						ormClaimNotesSave.setNotes_id(db.IDGenerator("claim_notes", practiceId));

						ormClaimNotesSave.setDate_created(serverDateTime);
						ormClaimNotesSave.setDate_modified(serverDateTime);

						ormClaimNotesSave.setClient_date_created(clientDateTime);
						ormClaimNotesSave.setClient_date_modified(clientDateTime);

						ormClaimNotesSave.setCreated_user(clientUser);
						ormClaimNotesSave.setModified_user(clientUser);

						ormClaimNotesSave.setPatient_id(patientId);
						ormClaimNotesSave.setClaim_id(selectedClaimId);

						ormClaimNotesSave.setPractice_id(practiceId);
						ormClaimNotesSave.setIs_auto(autoNotes);

						ormClaimNotesSave.setNotes(claimNote);

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimNotesSave));
					}
				}

				if (isCashRegResolve && cashRegisterAdd == null) {

					if (!selectedCashRegisterId.equals(ormClaimPaymentSave.getEob_era_id())
							&& ormClaimPaymentSave.getEob_era_id_type().equalsIgnoreCase("CASH_REGISTER")) {
						selectedCashRegisterId = ormClaimPaymentSave.getEob_era_id();

						String str = " update cash_register set resolved=1,resolved_by='" + clientUser
								+ "',date_resolved='" + clientDateTime + "' where cash_register_id ='"
								+ selectedCashRegisterId + "' ";
						lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, str));

					}

				}

			}

			result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result > 0) {

				selectedClaimId = (long) 0;
				for (ORMClaimPaymentSave ormClaimPaymentSave : lstClaimPaymentSave) {

					if (!selectedClaimId.equals(ormClaimPaymentSave.getClaim_id())) {
						selectedClaimId = ormClaimPaymentSave.getClaim_id();
						billingGeneral.UpdateClaimBalance(selectedClaimId, clientDateTime, clientUser,
								ormClaimPaymentSave.getSystem_ip());
					}
				}
			}

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
				resp.setResult(selectedClaimId.toString());
			}

		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("No claim data is provided");
		}

		return resp;

	}

	@Override
	public ServiceResponse<ORMKeyValue> rectifyPayment(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		int result = 0;
		Long practiceId = null;
		Long claimId = null;
		Long claimPaymentId = null;
		String clientUser = "", clientDateTime = "", eob_era_id_type = "", clientIp = "";

		for (ORMKeyValue ormKeyValue : lstKeyValue) {
			switch (ormKeyValue.getKey()) {
			case "practice_id":
				practiceId = Long.parseLong(ormKeyValue.getValue());
				break;
			case "claim_id":
				claimId = Long.parseLong(ormKeyValue.getValue());
				break;
			case "claim_payment_id":
				claimPaymentId = Long.parseLong(ormKeyValue.getValue());
				break;
			case "client_date_time":
				clientDateTime = ormKeyValue.getValue();
				break;
			case "client_user":
				clientUser = ormKeyValue.getValue();
				break;
			case "eob_era_id_type":
				eob_era_id_type = ormKeyValue.getValue();
				break;
			case "clientIp":
				clientIp = ormKeyValue.getValue();
				break;

			default:
				break;
			}
		}

		if (claimPaymentId != null && GeneralOperations.isNotNullorEmpty(clientDateTime)
				&& GeneralOperations.isNotNullorEmpty(clientUser)) {

			List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

			Long generatedClaimPaymentId = db.IDGenerator("claim_payment", practiceId);

			String strRectify = " insert into claim_payment (claim_payments_id,	claim_procedures_id,	patient_id	,claim_id,	payment_source,	payment_type,	 "
					+ "charged_procedure,	paid_procedure,	units,	check_date	,check_number,	paid_amount,	risk_amount,	writeoff_amount,	 "
					+ "adjusted_amount,	patient_responsibility,	allowed_amount,	payer_id,	practice_id,	deleted	,created_user, "
					+ "client_date_created,	modified_user,	client_date_modified,	date_created,	date_modified,	system_ip,	deductable_amount,	 "
					+ "coinsurance_amount,	copay_amount,	eob_era_id,	eob_era_id_type,insurance_id,policy_number,entry_type) "
					+ "select '" + generatedClaimPaymentId
					+ "' as claim_payments_id,	claim_procedures_id,	patient_id,	claim_id,	payment_source,	payment_type,	 "
					+ "charged_procedure,	paid_procedure,	units,	check_date,	check_number,	isnull(paid_amount,0)*(-1) as paid_amount,	 "
					+ "isnull(risk_amount,0)*(-1) as risk_amount ,	isnull(writeoff_amount,0)*(-1) as writeoff_amount,	isnull(adjusted_amount,0)*(-1) as adjusted_amount,	 "
					+ "isnull(patient_responsibility,0)*(-1) as patient_responsibility,	isnull(allowed_amount,0)*(-1) as allowed_amount,	payer_id,	practice_id,	deleted,'"
					+ clientUser + "' as created_user,'" + clientDateTime + "'	as client_date_created,	 " + "'"
					+ clientUser + " [Rectify]' as modified_user,'" + clientDateTime
					+ "' as client_date_modified,	GETDATE() as date_created,getdate() as date_modified,	system_ip,	 "
					+ "isnull(deductable_amount,0)*(-1) as deductable_amount,	isnull(coinsurance_amount,0)*(-1) as coinsurance_amount ,	 "
					+ "isnull(copay_amount,0)*(-1) as copay_amount, "
					+ "eob_era_id,eob_era_id_type,insurance_id,policy_number,entry_type "
					+ " from claim_payment where claim_payments_id='" + claimPaymentId + "'  ";

			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strRectify));

			List<ORMClaimPaymentAdjustmentGet> lstAdjustment = this.getClaimPaymentAdjustments(claimId);
			if (lstAdjustment != null) {
				for (ORMClaimPaymentAdjustmentGet ormAdjustment : lstAdjustment) {

					if (ormAdjustment.getClaim_payments_id().equals(claimPaymentId)) {

						String strAdjQuery = "insert into claim_payment_adjustments(adjustment_id,adjust_code,adjust_amount,claim_payments_id,claim_procedures_id,"
								+ "claim_id,practice_id,date_created,created_user,client_date_created,date_modified,	modified_user,	client_date_modified	,system_ip) "
								+ "values ('" + db.IDGenerator("claim_payment_adjustments", practiceId) + "','"
								+ ormAdjustment.getAdjust_code() + "','"
								+ ormAdjustment.getAdjust_amount().multiply(new BigDecimal(-1)) + "','"
								+ generatedClaimPaymentId + "','" + ormAdjustment.getClaim_procedures_id() + "','"
								+ ormAdjustment.getClaim_id() + "','" + practiceId + "',getdate(),'" + clientUser
								+ "','" + clientDateTime + "',getdate(),'" + clientUser + "','" + clientDateTime + "','"
								+ clientIp + "')";

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strAdjQuery));

					}
				}
			}

			if (result > 0 && eob_era_id_type.equalsIgnoreCase("CASH_REGISTER")) {
				String strUnResolve = " update cash_register set resolved=0,resolved_by=null, date_resolved=null "
						+ " where cash_register_id=isnull((select top 1 eob_era_id from claim_payment "
						+ " where eob_era_id_type='CASH_REGISTER' AND claim_payments_id='" + claimPaymentId + "'),-1) ";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strUnResolve));

			}

			result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result > 0) {
				billingGeneral.UpdateClaimBalance(claimId, clientDateTime, clientUser, clientIp);
			}

		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(claimId.toString());
		}

		return resp;
	}

	@Override
	public List<ORMGetClaimPostedPayment> getClaimPostedPayment(Long eob_era_id, String eob_era_id_type) {
		// TODO Auto-generated method stub
		List<ORMGetClaimPostedPayment> lst = null;

		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("eob_era_id", eob_era_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("eob_era_id_type", eob_era_id_type.toString(), String.class, ParameterMode.IN));

		lst = db.getStoreProcedureData("spGetClaimPostedPayment", ORMGetClaimPostedPayment.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMEobGet> getEOBInfoByClaimId(Long claim_id) {
		// TODO Auto-generated method stub
		List<ORMEobGet> lst = null;
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));
		lst = db.getStoreProcedureData("spGetEOBInfoByClaimId", ORMEobGet.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMEraPaymentInfoGet> getERAPaymentInfoByClaimId(Long claim_id) {
		// TODO Auto-generated method stub
		List<ORMEraPaymentInfoGet> lst = null;
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claim_id.toString(), String.class, ParameterMode.IN));
		lst = db.getStoreProcedureData("spGetERAInfoByClaimId", ORMEraPaymentInfoGet.class, lstParam);
		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> deleteClaim(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String claim_id = "";
		String patient_id = "";
		String modified_user = "";
		String client_date_time = "";
		String system_ip = "";
		String deletion_notes = "";

		int result = 0;
		String errorMsg = "";

		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "claim_id":
					claim_id = pram.getValue();
					break;
				case "patient_id":
					patient_id = pram.getValue();
					break;
				case "modified_user":
					modified_user = pram.getValue();
					break;
				case "client_date_time":
					client_date_time = pram.getValue();
					break;
				case "system_ip":
					system_ip = pram.getValue();
					break;
				case "deletion_notes":
					deletion_notes = pram.getValue();
				default:
					break;
				}
			}
		}

		if (claim_id == "" || modified_user == "" || client_date_time == "" || system_ip == ""
				|| deletion_notes == "") {
			errorMsg = "One of the reqruied parameter is missing.";
		} else {

			lstParam.add(new SpParameters("claim_id", claim_id, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("modified_user", modified_user, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("client_date_time", client_date_time, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("system_ip", system_ip, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("deletion_notes", deletion_notes, String.class, ParameterMode.IN));

			result = Integer.parseInt(db.ExecuteUpdateStoreProcedureWithOutputResult("spDeleteClaim", lstParam));
			if (result == 0) {
				errorMsg = "An Error Occured while saving record.";
			} else if (result == -1) // if there is any unrectified payment|writeoff|risk_amount|adjustment entry
										// against this claim.
			{
				errorMsg = "Claim cannot be deleted.<br>There is is un-rectified payment.";
			} else {
				ORMClaimNotesSave ormClaimNotesSave = new ORMClaimNotesSave();
				ormClaimNotesSave.setClient_date_created(client_date_time);
				ormClaimNotesSave.setClient_date_modified(client_date_time);
				ormClaimNotesSave.setCreated_user(modified_user);
				ormClaimNotesSave.setModified_user(modified_user);
				ormClaimNotesSave.setPatient_id(Long.parseLong(patient_id));
				ormClaimNotesSave.setClaim_id(Long.parseLong(claim_id));
				ormClaimNotesSave.setPractice_id(searchCriteria.getPractice_id());
				ormClaimNotesSave.setNotes("Claim Deleted: " + deletion_notes);
				saveClaimNotes(ormClaimNotesSave, false);
			}
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (errorMsg != "") {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse(errorMsg);
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Claim has been deleted successfully.");
			resp.setResult(claim_id.toString());
		}

		return resp;

	}

	@Override
	public ServiceResponse<ORMKeyValue> updateClaimStatus(ClaimStatusUpdateModel claimStatusUpdateModel) {
		// TODO Auto-generated method stub
		int result = 0;

		String bill_to_patient_notes = "";

		Long notesId = (long) 0;
		String errMsg = "";
		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		System.out.println(claimStatusUpdateModel.toString());
		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();
		if (claimStatusUpdateModel != null) {

			if (GeneralOperations.isNullorEmpty(claimStatusUpdateModel.getClaim_id())
					|| GeneralOperations.isNullorEmpty(claimStatusUpdateModel.getUser_name())
					|| GeneralOperations.isNullorEmpty(claimStatusUpdateModel.getClient_date_time())
					|| GeneralOperations.isNullorEmpty(claimStatusUpdateModel.getSystem_ip())) {

				errMsg = "One of the required parameter is missing.";

			} else {

				if (GeneralOperations.isNotNullorEmpty(claimStatusUpdateModel.getPat_status())
						&& claimStatusUpdateModel.getPat_status().equalsIgnoreCase("B")) {
					bill_to_patient_notes = claimStatusUpdateModel.getClaim_notes().getNotes().replaceAll("'", "''")
							+ " Dated:" + claimStatusUpdateModel.getClient_date_time();
				}

				String strQuery = " update claim set pat_status='" + claimStatusUpdateModel.getPat_status() + "', "
						+ " bill_to_patient_notes= '" + bill_to_patient_notes + "' ," + " pri_status= '"
						+ claimStatusUpdateModel.getPri_status() + "' ," + " sec_status= '"
						+ claimStatusUpdateModel.getSec_status() + "' ," + " oth_status= '"
						+ claimStatusUpdateModel.getOth_status() + "' ," + " date_modified='" + serverDateTime
						+ "',client_date_modified='" + claimStatusUpdateModel.getClient_date_time()
						+ "',modified_user='" + claimStatusUpdateModel.getUser_name() + "',system_ip='"
						+ claimStatusUpdateModel.getSystem_ip() + "' ";

				if (GeneralOperations.isNotNullorEmpty(claimStatusUpdateModel.getSubmission_status())
						&& claimStatusUpdateModel.getSubmission_status().equalsIgnoreCase("BILLED")) {
					strQuery += " , submission_status='BILLED',submission_date='" + serverDateTime + "'";
				}
				strQuery += " where claim_id='" + claimStatusUpdateModel.getClaim_id() + "'";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				if (claimStatusUpdateModel.getClaim_notes() != null) {

					ORMClaimNotesSave ormClaimNotesSave = claimStatusUpdateModel.getClaim_notes();

					ormClaimNotesSave.setDate_modified(DateTimeUtil.getCurrentDateTime());

					if (ormClaimNotesSave.getNotes_id() == null) {
						notesId = db.IDGenerator("claim_notes", ormClaimNotesSave.getPractice_id());
						ormClaimNotesSave.setNotes_id(notesId);
						ormClaimNotesSave.setDate_created(DateTimeUtil.getCurrentDateTime());
						// result = db.SaveEntity(ormClaimNotesSave, EnumUtil.Operation.ADD);
						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimNotesSave));

					} else {
						notesId = ormClaimNotesSave.getNotes_id();
						// result = db.SaveEntity(ormClaimNotesSave, EnumUtil.Operation.EDIT);
						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormClaimNotesSave));
					}

					/*
					 * ormClaimNotesSave .setNotes_id(db.IDGenerator("claim_notes",
					 * claimStatusUpdateModel.getPractice_id()));
					 * 
					 * ormClaimNotesSave.setDate_created(serverDateTime);
					 * ormClaimNotesSave.setDate_modified(serverDateTime);
					 * 
					 * ormClaimNotesSave.setClient_date_created(claimStatusUpdateModel.
					 * getClient_date_time());
					 * ormClaimNotesSave.setClient_date_modified(claimStatusUpdateModel.
					 * getClient_date_time());
					 * 
					 * ormClaimNotesSave.setCreated_user(claimStatusUpdateModel.getUser_name());
					 * ormClaimNotesSave.setModified_user(claimStatusUpdateModel.getUser_name());
					 * 
					 * ormClaimNotesSave.setPatient_id(claimStatusUpdateModel.getPatient_id());
					 * ormClaimNotesSave.setClaim_id(claimStatusUpdateModel.getClaim_id());
					 * 
					 * ormClaimNotesSave.setPractice_id(claimStatusUpdateModel.getPractice_id());
					 * 
					 * ormClaimNotesSave.setNotes(claimStatusUpdateModel.getClaim_notes());
					 * 
					 * lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD,
					 * ormClaimNotesSave));
					 * 
					 * System.out.println(ormClaimNotesSave.toString());
					 */
				}

				result = db.AddUpdateEntityWrapper(lstEntityWrapper);
			}

		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0 || errMsg != "") {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse(errMsg != "" ? errMsg : "An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(notesId.toString());
		}

		return resp;
	}

	@Override
	public List<ORMIdName> getSuperBillEncounterList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetSuperBillListEncounter", ORMIdName.class, lstParam);
	}

	@Override
	public List<ORMGetPatientStatement> getPatientStatement(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String Criteria1 = "";
		String Criteria2 = "";
		String patient_id = "";
		String pat_status = "";

		String range = "";
		String date_from = "";
		String date_to = "";
		String status = "";

		String amt_cond = "";
		String amt_value = "";

		String sent_day_cond = "";
		String sent_day_value = "";

		String self_pay = "";
		String patient_bill = "";
		String calling_from = "";

		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					patient_id = pram.getValue();
					break;
				case "pat_status":
					pat_status = pram.getValue();
					break;
				case "range":
					range = pram.getValue();
					break;
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				case "status":
					status = pram.getValue();
					break;
				case "amt_cond":
					amt_cond = pram.getValue();
					break;
				case "amt_value":
					amt_value = pram.getValue();
					break;
				case "sent_day_cond":
					sent_day_cond = pram.getValue();
					break;
				case "sent_day_value":
					sent_day_value = pram.getValue();
					break;
				case "self_pay":
					self_pay = pram.getValue();
					break;
				case "patient_bill":
					patient_bill = pram.getValue();
					break;
				case "calling_from":
					calling_from = pram.getValue();
					break;

				default:
					break;
				}
			}
		}
		if (calling_from.equals("report")) {

			Criteria1 = " and practice_id='" + searchCriteria.getPractice_id().toString() + "'";
			if (range.equals("DOS")) {
				Criteria1 += " and convert(date,dos) between convert(date,'" + date_from + "') and convert(date,'"
						+ date_to + "') ";
			} else if (range.equals("Entered")) {
				Criteria1 += " and convert(date,date_created) between convert(date,'" + date_from
						+ "') and convert(date,'" + date_to + "') ";
			}

			if (patient_id != "") {
				Criteria1 += "and c.patient_id='" + patient_id + "'";
			}
			Criteria1 += " and amt_due >0 ";

			if (!amt_cond.equals("") && !amt_value.equals("")) {
				Criteria2 = "  where convert(Decimal(38,2),a.pat_due) " + amt_cond + " " + amt_value + "";
			}
			if (!sent_day_value.equals("") && !sent_day_cond.equals("")) {
				if (status.toLowerCase().equals("all")) {
					if (Criteria2 == "")
						Criteria2 = " where  (isnull(a.patient_statement_days,0) " + sent_day_cond + " "
								+ sent_day_value
								+ " or isnull(a.patient_statement_days,0)=0) and convert(date,isnull(a.statement_date,''))!=convert(date,getdate()) ";
					else
						Criteria2 += " and  (isnull(a.patient_statement_days,0) " + sent_day_cond + " " + sent_day_value
								+ " or isnull(a.patient_statement_days,0)=0) and convert(date,isnull(a.statement_date,''))!=convert(date,getdate()) ";
				} else {
					if (Criteria2 == "")
						Criteria2 = "  where  isnull(a.patient_statement_days,0) " + sent_day_cond + " "
								+ sent_day_value + "";
					else
						Criteria2 += " and isnull(a.patient_statement_days,0) " + sent_day_cond + " " + sent_day_value
								+ "";
				}

			}
			if (self_pay.equals("true")) {
				Criteria1 += " and isnull(self_pay,0)=1 ";
			}
			if (patient_bill.equals("true")) {
				Criteria1 += " and pat_status='B' ";
			}
			if (self_pay.equals("true")) {
				Criteria2 += " and isnull(self_pay,0)=1 ";
			}
			if (patient_bill.equals("true")) {
				Criteria2 += " and pat_status='B' ";
			}
			if (status.toLowerCase().equals("sent"))// Sent change
			{
				Criteria1 = " and c.amt_due>0 and c.patient_id in (select patient_id from claim where isnull(deleted,0)<>1"
						+ Criteria1 + " and claim_id in (select claim_id from patient_statement_log) )";
			} else if (status.toLowerCase().equals("unsent"))// Unsent
			{
				Criteria1 = " and c.amt_due>0 and c.patient_id in (select patient_id from claim where isnull(deleted,0)<>1 "
						+ Criteria1 + " and claim_id not in (select claim_id from patient_statement_log) )";
				// strquery+=" and claim_id not in (select claim_id from
				// patient_statement_log)";
			} else
				Criteria1 = " and c.amt_due>0  and c.patient_id in ( select patient_id from claim where isnull(deleted,0)<>1 "
						+ Criteria1 + " )";
		} else {
			if (patient_id != "") {
				Criteria1 += "and c.patient_id='" + patient_id + "'";
			}

			if (pat_status != "") {
				Criteria1 += "and c.pat_status='" + pat_status + "'";
			}
		}

		lstParam.add(new SpParameters("criteria", Criteria1, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("amt_criteria", Criteria2, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPatientClaims_new", ORMGetPatientStatement.class, lstParam);

	}

	@Override
	public List<ORMGetStatementDetail> getStatementDetail(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String claim_id = "";
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "claim_id":
					claim_id = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		lstParam.add(new SpParameters("claim_id", claim_id, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetStatementDetail", ORMGetStatementDetail.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveStatementLog(List<ORMSavePatientStatement_log> lstSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		for (ORMSavePatientStatement_log ormSave : lstSave) {
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
			ormSave.setStatement_date(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(ormSave, Operation.ADD);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public List<ORMGetAttorneyDetail> getPrintAttorneyDetail(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String claim_id = "";
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "claim_id":
					claim_id = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		lstParam.add(new SpParameters("claim_id", claim_id, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetAttorneyDetail", ORMGetAttorneyDetail.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveAttorneyLog(List<ORMSavePatientAttorney_log> lstSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		for (ORMSavePatientAttorney_log ormSave : lstSave) {
			ormSave.setDate(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(ormSave, Operation.ADD);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving saveAttorneyLog.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public List<ORMTwoColumnGeneric> getPatientSelfPay(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetIsPatientSelfpay", ORMTwoColumnGeneric.class, lstParam);
	}

	@Override
	public List<ORMGetCPTWisePatientPaymentForRefund> getProceduresForPatientRefund(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		String patientId = "";
		String claimId = "";
		String cashRegisterId = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					patientId = pram.getValue();
					break;
				case "claim_id":
					claimId = pram.getValue();
					break;
				case "cash_register_id":
					cashRegisterId = pram.getValue();
					break;

				default:
					break;
				}
			}
		}

		lstParam.add(new SpParameters("patient_id", patientId, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("claim_id", claimId, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("cash_register_id", cashRegisterId, String.class, ParameterMode.IN));

		List<ORMGetCPTWisePatientPaymentForRefund> lstCPTs = db.getStoreProcedureData("spGetProceduresForPatientRefund",
				ORMGetCPTWisePatientPaymentForRefund.class, lstParam);

		return lstCPTs;
	}

	@Override
	public Long saveStatementNotes(List<ORMPatientStatementNotes> objDetail) {
		// TODO Auto-generated method stub
		int result = 0;

		int loopIner = 0;
		for (ORMPatientStatementNotes ormSave : objDetail) {
			if (loopIner == 0) {
				String query = " delete from pateint_statement_comments where created_user='"
						+ ormSave.getCreated_user() + "' and practice_id='" + ormSave.getPractice_id() + "'";
				db.ExecuteUpdateQuery(query);
			}
			loopIner++;
			String query = " INSERT INTO pateint_statement_comments (claim_id,statement_message,claim_message,created_user,date_created,practice_id)"
					+ " values('" + ormSave.getClaim_id() + "','" + ormSave.getStatement_message() + "','"
					+ ormSave.getClaim_message() + "','" + ormSave.getCreated_user() + "',getdate(),'"
					+ ormSave.getPractice_id() + "')";

			result += db.ExecuteUpdateQuery(query);
		}
		return (long) result;
	}

	@Override
	public List<ORMPatientStatementNotes> getPatientStatementSavedNotes(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		String user = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "user":
					user = pram.getValue();
					break;
				default:
					break;
				}
			}
		}

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("user", user, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientStatementSavedNotes", ORMPatientStatementNotes.class, lstParam);

	}

	@Override
	public List<ORMGetStatementPdf> getStatementPDF(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		String claim_id = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "claim_id":
					claim_id = pram.getValue();
					break;
				default:
					break;
				}
			}
		}

		lstParam.add(new SpParameters("claim_id", claim_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetStatementDetail_new", ORMGetStatementPdf.class, lstParam);

	}

	public String strXml = "";
	public float total_amt_due = 0;
	public String statement_message = "";
	public boolean is_Statement_message_changed = false;
	public DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public String generatePDFStatement(List<ORMStatementPDF> objStatement) {
		// TODO Auto-generated method stub
		String res = "";
		System.out.println(objStatement.size());
		ArrayList Patient_List = new ArrayList();
		ArrayList<ORMFile_Name> file_List = new ArrayList<ORMFile_Name>();

		total_amt_due = 0;
		strXml = "";
		String practice_id = "";
		String path = "";
		for (ORMStatementPDF objs : objStatement) {
			Patient_List.add(objs.getPatient_id());
			practice_id = objs.getPractice_id();
		}
		List<ORMDocumentPath> lstPath = generalDaoImpl.getDocPath(practice_id, "PatientStatement");
		for (ORMDocumentPath orm : (List<ORMDocumentPath>) lstPath) {
			path = orm.getUpload_path() + "\\" + practice_id + "\\PatientStatement\\";
		}
		Set set = new HashSet(Patient_List);
		ArrayList uniquePatient = new ArrayList(set);
		int mainPatientCount = 0;
		if (uniquePatient.size() > 0) {

			for (int j = 0; j < uniquePatient.size(); j++) {
				total_amt_due = 0;
				strPdfStatement = "";
				String file_name = "";
				List<ORMStatementPDF> listSinglePatient = new ArrayList<>();
				boolean NodeAdded = false;
				// for (int k = 0; k < objStatement.length; k++)
				for (ORMStatementPDF objs : objStatement) {
					if (uniquePatient.get(j).equals(objs.getPatient_id())) {
						listSinglePatient.add(objs);
						file_name = objs.getPat_last_name() + ", " + objs.getPat_first_name();
					}
				}
				//
				pdfStatementHeader(listSinglePatient, listSinglePatient.get(0));
				try {
					String file_path = HTMLTOPDF(strPdfStatement, path, file_name + ".pdf", "", practice_id);
					System.out.println(file_path);
					// file_List[0].setId(file_path);
					ORMFile_Name o = new ORMFile_Name();
					o.setId(Integer.toString(mainPatientCount));
					o.setName(file_name + ".pdf");
					o.setPath(path + file_path);
					file_List.add(o);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mainPatientCount++;

			for (ORMStatementPDF objs : objStatement) {
				String strq = "insert into patient_statement_log(patient_id,claim_id,statement_date,generated_by,date_created,path,amt_sent) "
						+ "values ('" + objs.getPatient_id() + "','" + objs.getClaim_id()
						+ "',GETDATE(),'System',GETDATE(),'"
						+ DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.MM_dd_yyyy).replaceAll("/", "") + "\\"
						+ objs.getPat_last_name() + ", " + objs.getPat_first_name() + ".pdf" + "','"
						+ objs.getClaim_amt_due() + "');";
				db.ExecuteUpdateQuery(strq);
			}
		}
		String DocumentPath = "";
		String Patho = "";
		if (file_List.size() > 0) {
			// PatientPortal o=new PatientPortal();
			// ORMFile_Name[] item = file_List.toArray(new ORMFile_Name[file_List.size()]);
			// res=o.CreateZipWithoutDelete(item,
			// path+GenericOperations.CurrentDate().replaceAll("/", "")+"\\",
			// "PatientStatement_"+GenericOperations.CurrentDateTime().replaceAll("/",
			// "").replaceAll(":", "") +".zip");
			// System.out.println(GenericOperations.CurrentDate().replaceAll("/",
			// "")+"\\"+res);

			for (ORMDocumentPath orm : (List<ORMDocumentPath>) lstPath) {
				DocumentPath = orm.getUpload_path() + "\\";
			}
			Patho = GeneralOperations.checkPathYearMonthWise(practice_id, DocumentPath, "PatientStatement");
			res = FileUtil.CreateZipWithoutDelete(file_List,
					DocumentPath + practice_id + "\\PatientStatement\\" + Patho + "\\",
					"PatientStatement_" + DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.yyyy_MM_dd_HH_mm_ss_SSS)
							.replaceAll("/", "").replaceAll(":", "") + ".zip");
		}
		return Patho + "\\" + res;
	}

	public String HTMLTOPDF(String HTML, String Path, String File_Name, String insert, String practice_id)
			throws Exception {

		String DocumentPath = "";
		List<ORMDocumentPath> lstPath = generalDaoImpl.getDocPath(practice_id, "PatientStatement");
		for (ORMDocumentPath orm : (List<ORMDocumentPath>) lstPath) {
			DocumentPath = orm.getUpload_path();
		}
		String Patho = GeneralOperations.checkPathYearMonthWise(practice_id, DocumentPath, "PatientStatement");
		// System.out.println(Patho);
		PDFOperations objPDF = new PDFOperations();
		String path = objPDF.ConvertHtmltoPDF(HTML, DocumentPath + practice_id + "\\PatientStatement\\" + Patho,
				File_Name);
		String aa = path.substring(path.lastIndexOf("PatientStatement") + 17, path.length());
		System.out.println(aa);

		if (!insert.equals("")) {
			db.ExecuteUpdateQuery(insert.replace("@File_Name", aa));
		}

		return aa;
	}

	public void pdfStatementHeader(List<ORMStatementPDF> listStatement, ORMStatementPDF objStatement) {
		strPdfStatement = "<table border='.5' width='100%'> " + "<tr bgcolor='white'> " + "<td width='40%'> "
				+ "<table  border='0'> " + "<tr bgcolor=\"0376a8\"> "
				+ "<td bgcolor=\"0376a8\" style=\"color:#FFFFFF\"><div align=\"center\" class=\"style19\">Make Checks Payable To: </div></td> "
				+ "</tr> " + "<tr > "
				+ "<td width='424' ><p align='left' style=' font-size: 10pt; font-family:Verdana, Arial, Helvetica, sans-serif'> <span class=\"style20\"><strong>"
				+ objStatement.getPrac_name() + "</strong> <br> " + objStatement.getPrac_address() + "<br>"
				+ objStatement.getPrac_citystatezip() + "<br>" + "</span>           "
				+ "<p align='left' style=' font-size: 10pt; font-family:Verdana, Arial, Helvetica, sans-serif'><span class=\"style20\"><br> "
				+ "<strong>For All Billing Questions, Please Call<br> " + "at: " + "" + objStatement.getPrac_phone()
				+ " </strong><br> " + "<br> "
				+ "<span style=\"color: #3C5A84\"><strong>Send To:</strong></span></span><span class=\"style20\"><br> "
				+ "<b>" + objStatement.getPat_last_name().toUpperCase() + ", "
				+ objStatement.getPat_first_name().toUpperCase() + "<br> " + " </b> " + objStatement.getPat_address()
				+ "<br> " + "  " + objStatement.getPat_city() + ", " + objStatement.getPat_state() + " "
				+ objStatement.getPat_zip() + "<br> " + "   </span> " + "  </td> " + " </tr> " + "</table> "
				+ "<td width='60%'> "
				+ "<table  width='100%' border='.3' align='left'  style=\"font-family:Verdana, Arial, Helvetica, sans-serif\" style=\"font-size:8px\"  > "
				+ "	<tr> "
				+ "	  <td colspan=\"4\" style=\"font-family:Verdana, Arial, Helvetica, sans-serif\" style=\"font-size:6px\"><b>IF PAYING BY VISA, MASTERCARD, DISCOVER AND AMERICAN EXPRESS FILL OUT BELOW<b> </td> "
				+ "	</tr> " + "	<tr  bgcolor=\"cee6f8\"> " + "	  <td><span >Visa </span></td> "
				+ "	  <td><span >Master Card</span></td> " + "	  <td><span >Discover </span></td> "
				+ "	  <td><span >American Express </span></td> " + "	</tr> " + "	<tr> "
				+ "	  <td colspan='2'><span class=\"style8\">Card Number<br> " + "		<br> " + "		</span></td> "
				+ "  <td colspan='1'><span class=\"style8\">Exp Date <br>" + "	  <br>" + "	  </span></td>"
				+ "	  <td colspan='1'><span class=\"style8\">Amount <br> " + "		<br> " + "		</span></td> "
				+ "	</tr> " + "	<tr> " + "	  <td colspan='2' ><span class=\"style8\">Signature<br> " + "		<br> "
				+ "		</span></td> "
				+ " <td colspan='2'><span class=\"style8\">Must Include 3 or 4 <br>Digit Security Code From Front<br>(Amer.Exp) or Back of card"
				+ "	 <br> " + "	 </span></td> " + "	</tr> " + "	<tr bgcolor=\"cee6f8\"> "
				+ "	  <td colspan='2' align=center style=\"color:#000000\"><span class=\"style18\">Statement Date</span></td> "
				+ "	  <td align=center style=\"color:#000000\"><span class=\"style18\">Pay This Amount</span></td> "
				+ "	  <td align=center style=\"color:#000000\"><span class=\"style18\">Account No</span></td> "
				+ "	</tr> " + "	<tr> " + "	  <td colspan='2' align=center  ><span class=\"style8\">"
				+ DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.MM_dd_yyyy) + "</span></td> "
				+ "	  <td  align=center  ><span class=\"style8\">@DUE</span></td> "
				+ "	  <td   align='center'  ><span class=\"style8\">" + objStatement.getAlternate_account()
				+ "</span></td> " + "	</tr> " + "	<tr> "
				+ "	  <td colspan='4'   style=\"color:#FF0000\"><span class=\"style6\">Changes And Credits Made After Statement Date Will Appear On Next Statement</span></td> "
				+ "	</tr> " + "</table> " +

				"<table  width='100%' border='0' align='right' cellpadding='0' cellspacing='0' > " + "<tr>"
				+ "<td  align='left'  "
				+ "<span style=\"color: #3C5A84\"><strong>Remit To:</strong></span></span><span class=\"style20\">"
				+ "</tr>" + "<tr> "
				+ " <td style='font-size: 10pt; font-family:Verdana, Arial, Helvetica, sans-serif' align='center'><strong>"
				+ objStatement.getPrac_name() + "</strong></td> " + "</tr> " + "<tr> "
				+ "  <td style='font-size: 10pt; font-family:Verdana, Arial, Helvetica, sans-serif' align='center'  >"
				+ objStatement.getPrac_address() + "</td> " + " </tr> " + "  <tr> "
				+ "     <td style='font-size: 10pt; font-family:Verdana, Arial, Helvetica, sans-serif' align='center'  >"
				+ objStatement.getPrac_citystatezip() + "</td> " + "    </tr> " + "   </table> " + "  </td> "
				+ " </tr> " + "</table> " + "<br><table align=\"center\" border='0'>" + "<tr bgcolor=\"0376a8\">"
				+ "<th  align=\"center\" style=\"color:#FFFFFF\"> <b><span class=\"style25\">STATEMENT </span></b></th>"
				+ "</tr>" + "</table>"
				+ "<br><table width='98%' align='center' border='.8'  cellpadding='0' cellspacing='0' style='font-family:Verdana, Arial, Helvetica, sans-serif'> "
				+ " <tr bgcolor=\"cee6f8\">  "
				+ "<th style= 'color:#000000' align='center'><span class=\"style24\">&nbsp; Date</span></th> "
				+ "<th style= 'color:#000000' align='center'><span class=\"style24\">&nbsp; Physician</span></th> "
				+ "<th style= 'color:#000000' align='center'><p class=\"style24\"><b>Procedure</b></p> </th>" +
				// "<p class=\"style24\" ><b>&nbsp; Code </b></p> "+
				"<th style= 'color:#000000' align='center'><span class=\"style24\"><b>&nbsp; Charges </b></span></th> "
				+ "<th style= 'color:#000000' align='center'><span class=\"style24\"><b>&nbsp; Total Fee </b></span></th> "
				+ "<th style= 'color:#000000' align='center'><span class=\"style24\"><b>&nbsp; Credit </b></span></th>  "
				+ "<th style= 'color:#000000' align='center'><span class=\"style24\"><b>&nbsp; Adjustment </b></span></th>  "
				+ "<th style= 'color:#000000' align='center'><span class=\"style24\"><b>&nbsp; Balance </b></span></th>  "
				+ "</tr> ";
		pdfStatementDetail(listStatement);
		strPdfStatement += "<tr> " + "<td align=\"center\"></td> " + "<td align=\"center\"></td> "
				+ "<td align=\"center\"></td> " + "<td align=\"center\"></td> " + "<td align=\"center\"></td> "
				+ "<td align=\"center\"></td> "
				+ "<td align=\"center\" bgcolor=\"0376a8\" style= 'color:#FFFFFF;font-style:normal; font-size:11px'><strong>Amt. Due:</strong></td> "
				+ "<td align=\"center\" bgcolor=\"0376a8\" style= 'color:#FFFFFF;font-style:normal; font-size:11px'><strong>$ "
				+ Float.toString(total_amt_due) + "</strong></td> " + "</tr> ";
		strPdfStatement += "</table>";
		strPdfStatement += "<br><table width='100%' align='left'>" + "<tr bgcolor=\"F5C0C2\">"
				+ "<td bgcolor=\"F5C0C2\" style=\"font-family:Arial, Helvetica, sans-serif;font-size:6.5px\">"
				+ objStatement.getStatement_message() + "<br>" +
				// "Your Insurance Has Paid Its Portion For Services Please Remit Balance
				// Promptly.<br><br>"+
				// "Your Account Is Seriously Past Due, Please Call Our Office At "+strphone+""+

				"</td>" + "</tr>" + "</table>";
		strPdfStatement = strPdfStatement.replaceAll("@DUE", df.format(total_amt_due));

	}

	String strPdfStatement = "";

	public void pdfStatementDetail(List<ORMStatementPDF> lstStatement) {
		ArrayList Claim_List = new ArrayList();
		for (ORMStatementPDF objStatement : (List<ORMStatementPDF>) lstStatement) {
			Claim_List.add(objStatement.getClaim_id());
		}

		Set set = new HashSet(Claim_List);
		ArrayList uniqueClaim = new ArrayList(set);
		if (uniqueClaim.size() > 0) {
			for (int j = 0; j < uniqueClaim.size(); j++) {
				Boolean sameclaim = false;
				Boolean loopclaim = false;
				List<ORMStatementPDF> listSinglePatient = new ArrayList<>();
				for (ORMStatementPDF objStatement : (List<ORMStatementPDF>) lstStatement) {
					if (uniqueClaim.get(j).equals(objStatement.getClaim_id().toString())) {
						strPdfStatement += "<tr>"
								+ "<td align=\"center\" style= 'color:10294E;font-style:normal; font-size:9px'>"
								+ objStatement.getClaim_dos() + "</td>"
								+ "<td align=\"center\" style= 'color:10294E;font-style:normal; font-size:9px'>"
								+ objStatement.getClaim_atten_pro_name() + "</td>"
								+ "<td align=\"center\" style= 'color:10294E;font-style:normal; font-size:9px'>"
								+ objStatement.getClaim_proc_code() + "</td>"
								+ "<td align=\"center\" style= 'color:10294E;font-style:normal; font-size:9px'>$"
								+ df.format(Double.parseDouble(objStatement.getClaim_total_charges())) + "</td>";
						if (sameclaim == false) {
							loopclaim = true;
							strPdfStatement += "<td align=\"center\" style= 'color:10294E;font-style:normal; font-size:9px'>$"
									+ df.format(Double.parseDouble(objStatement.getClaim_total())) + "</td>"
									+ "<td align=\"center\" style= 'color:10294E;font-style:normal; font-size:9px'>$"
									+ df.format(Double.parseDouble(objStatement.getClaim_amt_paid())) + "</td>"
									+ "<td align=\"center\" style= 'color:10294E;font-style:normal; font-size:9px'>$"
									+ df.format(Double.parseDouble(objStatement.getClaim_adjust_amount())) + "</td>"
									+ "<td align=\"center\" style= 'color:10294E;font-style:normal; font-size:9px'>$"
									+ df.format(Double.parseDouble(objStatement.getClaim_amt_due())) + "</td>";
							total_amt_due = total_amt_due + Float.parseFloat(objStatement.getClaim_amt_due());
						} else {
							strPdfStatement += "<td align=\"center\"></td>" + "<td align=\"center\"></td>"
									+ "<td align=\"center\"></td>" + "<td align=\"center\"></td>";
						}
						strPdfStatement += "</tr>";
					}
					sameclaim = loopclaim;
				}
			}
		}
	}

	@Override
	public String generateStatement(List<ORMspGetPatientClaims> objDetail) {
		// TODO Auto-generated method stub
		String path = "";
		ArrayList Patient_List = new ArrayList();
		total_amt_due = 0;
		strXml = "";
		statement_message = "";
		is_Statement_message_changed = false;
		String practice_id = "";
		String statement_phone = "";
		String user = "";
		String client_ip = "";
		for (ORMspGetPatientClaims objStatement : (List<ORMspGetPatientClaims>) objDetail) {
			Patient_List.add(objStatement.getPatient_id());
			practice_id = objStatement.getPractice_id();
			statement_phone = objStatement.getPrac_phone();
			user = objStatement.getUser();
			client_ip = objStatement.getSystem_ip();
		}
		ORMPracticeInfo objPracticeInfo = generalDaoImpl.getPracticeInfo(Long.parseLong(practice_id));
		Set set = new HashSet(Patient_List);
		ArrayList uniquePatient = new ArrayList(set);
		if (uniquePatient.size() > 0)
			CreateXMLHeader(objPracticeInfo.getPractice_name().toUpperCase(), statement_phone,
					objPracticeInfo.getAddress1().toUpperCase(), objPracticeInfo.getCity().toUpperCase(),
					objPracticeInfo.getState().toUpperCase(), objPracticeInfo.getZip(), "@####@");
		for (int j = 0; j < uniquePatient.size(); j++) {
			total_amt_due = 0;
			statement_message = "";
			is_Statement_message_changed = false;
			// Get Single patient all record
			List<ORMspGetPatientClaims> listSinglePatient = new ArrayList<>();
			// for (ORMspGetPatientClaims orm : (List<ORMspGetPatientClaims>) list)
			boolean NodeAdded = false;
			for (ORMspGetPatientClaims objStatement : (List<ORMspGetPatientClaims>) objDetail) {
				if (uniquePatient.get(j).equals(objStatement.getPatient_id().toString())) {
					listSinglePatient.add(objStatement);
					if (!NodeAdded) {
						CreateStatementNode(objStatement, "@####@", objPracticeInfo.getPractice_name(),
								objPracticeInfo.getAddress1(), objPracticeInfo.getCity(), objPracticeInfo.getState(),
								objPracticeInfo.getZip());
						NodeAdded = true;
					}
				}
				// if(uniquePatient.get(j).equals(orm.getPatient_id().toString()))
				// {
				// listSinglePatient.add(orm);
				// }
			}

			// Get DuPlicate Rows on Billing provider & Facility Id
			List<ORMspGetPatientClaims> listClaimProvider = new ArrayList<>();
			listClaimProvider.addAll(listSinglePatient);

			Set uniqueEntries = new HashSet();
			for (Iterator iter = listClaimProvider.iterator(); iter.hasNext();) {
				ORMspGetPatientClaims element = (ORMspGetPatientClaims) iter.next();
				if (!uniqueEntries.add(element.getBilling_physician() + "~" + element.getFacility_id()))
					iter.remove();
			}
			CreateDetail(listClaimProvider, listSinglePatient, uniquePatient.get(j).toString());
			strXml = strXml.replaceAll("@AmountDue", df.format(total_amt_due));
			if (is_Statement_message_changed)
				strXml = strXml.replaceAll("@statementMessage", statement_message + "(" + statement_phone + ")");
			else
				strXml = strXml.replaceAll("@statementMessage", statement_message);

			strXml += "</Providers>";
			strXml += "</Statement>";
		}
		strXml += "</Statements>";
		strXml += "</StatementFile>";

		String log = "";
		String notes = "";
		for (ORMspGetPatientClaims objStatement : (List<ORMspGetPatientClaims>) objDetail) {
			if (log.equals("")) {
				log = "insert into patient_statement_log(patient_id,claim_id,amt_sent,statement_date,generated_by,date_created,path,system_ip,notes,claim_message) "
						+ "values ('" + objStatement.getPatient_id() + "','" + objStatement.getClaim_id() + "','"
						+ df.format(Double.parseDouble(objStatement.getAmt_due())) + "',GETDATE(),'" + user
						+ "',GETDATE(),@Path,'" + client_ip + "','" + objStatement.getStatement_message() + "','"
						+ objStatement.getPos_name() + "')";
			} else {
				log += "~ insert into patient_statement_log(patient_id,claim_id,amt_sent,statement_date,generated_by,date_created,path,system_ip,notes,claim_message) "
						+ "values ('" + objStatement.getPatient_id() + "','" + objStatement.getClaim_id() + "','"
						+ df.format(Double.parseDouble(objStatement.getAmt_due())) + "',GETDATE(),'" + user
						+ "',GETDATE(),@Path,'" + client_ip + "','" + objStatement.getStatement_message() + "','"
						+ objStatement.getPos_name() + "')";
			}
			if (notes.equals("")) {
				notes = "insert into claim_notes(notes_id, claim_id, patient_id, notes, date_created, created_user, date_modified, modified_user, practice_id) "
						+ "values(@notes_id,'" + objStatement.getClaim_id() + "','" + objStatement.getPatient_id()
						+ "','" + objStatement.getPos_name() + ", Copay: $" + objStatement.getCopay_amt()
						+ " , Co-Ins: $" + objStatement.getCoinsurance_amt() + " , Deductable: $"
						+ objStatement.getDeductable_amt() + " (Auto.Statement)" + "',getdate(),'" + user
						+ "',getdate(),'" + user + "','" + practice_id + "')";
			} else {
				notes += "~ insert into claim_notes(notes_id, claim_id, patient_id, notes, date_created, created_user, date_modified, modified_user, practice_id) "
						+ "values(@notes_id,'" + objStatement.getClaim_id() + "','" + objStatement.getPatient_id()
						+ "','" + objStatement.getPos_name() + ", Copay: $" + objStatement.getCopay_amt()
						+ " , Co-Ins: $" + objStatement.getCoinsurance_amt() + " , Deductable: $"
						+ objStatement.getDeductable_amt() + " (Auto.Statement)" + "',getdate(),'" + user
						+ "',getdate(),'" + user + "','" + practice_id + "')";
			}
		}
		path = writeStatementFile(strXml, practice_id, user, log, notes);
		// path=path.substring(path.lastIndexOf("PatientStatement")+17, path.length());
		return path;
	}

	public String writeStatementFile(String Data, String practiceID, String LoggedInUser, String StatementLog,
			String claimNotes) {
		String DocumentPath = "";
		try {
			List<ORMDocumentPath> lstPath = generalDaoImpl.getDocPath(practiceID, "PatientStatement");
			for (ORMDocumentPath orm : (List<ORMDocumentPath>) lstPath) {
				DocumentPath = orm.getUpload_path() + "\\";
			}

			if (!DocumentPath.equals("")) {
				String Path = GeneralOperations.checkPathYearMonthWise(practiceID, DocumentPath, "PatientStatement");
				Path += "\\" + FileUtil.getUserFileName(LoggedInUser) + ".xml";
				Writer output = null;
				File file = new File(DocumentPath + practiceID + "\\PatientStatement\\" + Path);
				output = new BufferedWriter(new FileWriter(file));
				output.write(Data);
				output.close();
				DocumentPath = Path;

				String aa = DocumentPath.substring(DocumentPath.lastIndexOf("PatientStatement") + 17,
						DocumentPath.length());
				// System.out.println(aa);
				insertStatementLog(practiceID, StatementLog.replace("@Path", "'" + Path + "'"));

				// insertStatementLog(practiceID,StatementLog.replace("@Path",
				// "'"+DocumentPath+"'"));
				InsertClaimNotesFromStatement(claimNotes, practiceID);

			}
		} catch (Exception e) {
			e.printStackTrace();
			DocumentPath = "";
		}
		return DocumentPath;
	}

	public String insertStatementLog(String practice_id, String Query) {
		String[] insertQuery = Query.split("~");
		for (int i = 0; i < insertQuery.length; i++) {
			if (!insertQuery[i].equals(""))
				db.ExecuteUpdateQuery(insertQuery[i]);
		}
		return "";

	}

	public void InsertClaimNotesFromStatement(String Query, String Practice_id) {
		try {
			String[] ids = Query.split("~");
			for (int i = 0; i < ids.length; i++) {
				if (!ids[i].equals("")) {
					String id = db.IDGenerator("claim_notes", Long.parseLong(Practice_id)).toString();
					ids[i] = ids[i].replace("@notes_id", id);
					db.ExecuteUpdateQuery(ids[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CreateXMLHeader(String practice_name, String practice_phone, String address1, String city, String state,
			String zip, String strSpecialChar) {
		if (practice_phone.length() > 9)
			practice_phone = practice_phone.substring(0, 3) + "-" + practice_phone.substring(3, 6) + "-"
					+ practice_phone.substring(6, 10);

		strXml = "<?xml version='1.0'?>";
		strXml += "<StatementFile xmlns='http://www.instant-healthcare.com'>";

		strXml += "<Sender>" + "<Name>" + practice_name + "</Name>" + "<Address>" + practice_name + strSpecialChar
				+ address1 + strSpecialChar + city + ", " + state + " " + zip + "</Address>" + "<BillingPhoneNumber>"
				+ practice_phone + "</BillingPhoneNumber>" +
				// "<BillingPhoneNumber>414-755-1754</BillingPhoneNumber>"+
				"<BillingExtension>1</BillingExtension>" + "<BillingFaxNumber>414-446-3965</BillingFaxNumber>"
				+ "</Sender>";
		strXml += "<Statements>";
	}

	public void CreateStatementNode(ORMspGetPatientClaims objStatement, String strSpecialChar, String practice_name,
			String address1, String city, String state, String zip) {
		String practice_phone = "";
		if (objStatement.getPrac_phone().length() > 9)
			practice_phone = objStatement.getPrac_phone().substring(0, 3) + "-"
					+ objStatement.getPrac_phone().substring(3, 6) + "-"
					+ objStatement.getPrac_phone().substring(6, 10);

		statement_message = objStatement.getStatement_message().toString().toUpperCase();

		strXml += "<Statement>" + "<AccountNumber>" + objStatement.getAlternate_account() + "</AccountNumber>"
				+ "<MailingAddress>" + objStatement.getPat_name().toString().toUpperCase() + strSpecialChar
				+ objStatement.getPat_address().toUpperCase() + strSpecialChar
				+ objStatement.getPat_city().toString().toUpperCase() + ", "
				+ objStatement.getPat_state().toString().toUpperCase() + " " + objStatement.getPat_zip()
				+ "</MailingAddress>" + "<MailPaymentsTo>" + practice_name.toUpperCase() + strSpecialChar
				+ address1.toString().toUpperCase() + strSpecialChar + city.toString().toUpperCase() + ", "
				+ state.toString().toUpperCase() + " " + zip + "</MailPaymentsTo>" +
				// "<DunningMessage>"+objStatement.getStatement_message().toString().toUpperCase()+"</DunningMessage>"+
				"<DunningMessage>@statementMessage</DunningMessage>" + "<ProviderPhoneNumber>" + practice_phone
				+ "</ProviderPhoneNumber>" +
				// "<ProviderPhoneNumber>414-755-1754</ProviderPhoneNumber>"+
				"<ProviderExtension>1</ProviderExtension>" + "<CutOffDate>"
				+ DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.yyyy_MM_dd) + "</CutOffDate>" + // CurrentDateYYYYMMDD()
				"<AmountDue>@AmountDue</AmountDue>";
		strXml += "<Providers>";

	}

	public void CreateDetail(List<ORMspGetPatientClaims> Duplist, List<ORMspGetPatientClaims> arrlist,
			String Patient_id) {
		for (ORMspGetPatientClaims ormClaimProvider : (List<ORMspGetPatientClaims>) Duplist) {
			List<ORMspGetPatientClaims> listClaim = new ArrayList<>();
			for (ORMspGetPatientClaims ormarrlist : (List<ORMspGetPatientClaims>) arrlist) {
				if (ormClaimProvider.getBilling_physician().equals(ormarrlist.getBilling_physician())
						&& ormClaimProvider.getFacility_id().equals(ormarrlist.getFacility_id())) {
					listClaim.add(ormarrlist);
				}
			}
			Boolean Prov_Header = false;
			int ormlistClaimLength = 0;

			for (ORMspGetPatientClaims ormlistClaim : (List<ORMspGetPatientClaims>) listClaim) {

				// if(ormlistClaimLength==listClaim.size()-1 && Prov_Header)
				// {
				// strXml+="</Details>";
				// strXml+="</Provider>";
				// }
				if (Prov_Header == false) {
					strXml += "<Provider>" + "<ProviderName>" + ormlistClaim.getProv_name().toString().toUpperCase()
							+ "</ProviderName>" + "<FacilityName>" + ormlistClaim.getLoc_name().toString().toUpperCase()
							+ "</FacilityName>";
					strXml += "<Details>";
					Prov_Header = true;
				}
				total_amt_due = total_amt_due + Float.parseFloat(ormlistClaim.getAmt_due());
				strXml += "<Detail>" + "<DateOfService>" + ormlistClaim.getDos() + "</DateOfService>" + "<PatientName>"
						+ ormlistClaim.getPat_name().toString().toUpperCase() + "</PatientName>" + "<Charge>"
						+ df.format(Double.parseDouble(ormlistClaim.getClaim_total().toString())) + "</Charge>"
						+ "<PrimaryPaid>" + df.format(Double.parseDouble(ormlistClaim.getPri_paid().toString()))
						+ "</PrimaryPaid>" + "<SecondariesPaid>"
						+ df.format(Double.parseDouble(ormlistClaim.getSec_paid().toString())) + "</SecondariesPaid>"
						+ "<Discount>" + df.format(Double.parseDouble(ormlistClaim.getAdjust_amount().toString()))
						+ "</Discount>" + "<PatientPayments>"
						+ df.format(Double.parseDouble(ormlistClaim.getPatient_paid().toString()))
						+ "</PatientPayments>" + "<RemainingBalance>"
						+ df.format(Double.parseDouble(ormlistClaim.getAmt_due().toString())) + "</RemainingBalance>"
						+ "<Comment>" + ormlistClaim.getPos_name().toString().toUpperCase() + "</Comment>";
				strXml += "</Detail>";
				if (ormlistClaimLength == listClaim.size() - 1 && Prov_Header) {
					strXml += "</Details>";
					strXml += "</Provider>";
				}
				ormlistClaimLength++;
				// check if self pay claim then change message
//				if (ormlistClaim.isSelf_pay() && is_Statement_message_changed == false) {
//					statement_message = "You. IN CASE OF ANY CONCERNS PLEASE CONTACT US AT ";
//					is_Statement_message_changed = true;
//				}
			}
		}

	}

	@Override
	public List<ORMGetPatientStatementLog> getPatientStatementLog(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spgetPatientStatementLog", ORMGetPatientStatementLog.class, lstParam);
	}

	@Override
	public List<ORMGetCPTsWithBalance> getCPTsWithBalance(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String patient_id = "";
		String claim_id = "";
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "claim_id":
					claim_id = pram.getValue();
					break;
				case "patient_id":
					patient_id = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		lstParam.add(new SpParameters("claim_id", claim_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("payment_source", "", String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("cash_register_id", "", String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spgetCPTsWithBalance", ORMGetCPTsWithBalance.class, lstParam);
	}

	@Override
	public List<ORMGetPreviousCalimImport> getPreviousCalimImport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<ORMGetPreviousCalimImport> lst = null;
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		lst = db.getStoreProcedureData("spGetPreviousCalimImport", ORMGetPreviousCalimImport.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMBillingProviderTaxomonyListGet> GetBillingProviderTaxonomyList(Long practice_id,
			Long billing_provider_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("billing_provider_id", billing_provider_id.toString(), String.class,
				ParameterMode.IN));

		return db.getStoreProcedureData("spGetBillingProviderTaxonomy", ORMBillingProviderTaxomonyListGet.class,
				lstParam);

	}

	@Override
	public List<ORMClaimReminderGet> getClaimRemdinder(Long practiceId, Long claimId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("claim_id", claimId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("ORMClaimReminderGet", ORMClaimReminderGet.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveClaimReminder(ORMClaimReminderSave ormClaimReminderSave) {
		// TODO Auto-generated method stub
		int result = 0;
		Long reminderId;

		ormClaimReminderSave.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (ormClaimReminderSave.getReminder_id() == null) {
			reminderId = db.IDGenerator("claims_reminder", ormClaimReminderSave.getPractice_id());
			ormClaimReminderSave.setReminder_id(reminderId);
			ormClaimReminderSave.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(ormClaimReminderSave, EnumUtil.Operation.ADD);

		} else {
			reminderId = ormClaimReminderSave.getReminder_id();
			result = db.SaveEntity(ormClaimReminderSave, EnumUtil.Operation.EDIT);
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(reminderId.toString());
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> resolveClaimReminder(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String reminderId="";
		String resolvedNotes="";
		String logedInUser="";
		
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "reminder_id":
					reminderId = pram.getValue();
					break;
				case "resolved_notes":
					resolvedNotes = pram.getValue();
					break;
				case "user_name":
					logedInUser = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		
		String str=" update claims_reminder set resolved=1,resolved_notes='"+resolvedNotes.replaceAll("'", "`")+"',modified_user='"+logedInUser+"',date_modified=getdate(),date_resolved=getdate(),resolved_user='"+logedInUser+"' where reminder_id='"+reminderId+"'" ;
		
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if(this.db.ExecuteUpdateQuery(str)>0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		}
		else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(reminderId.toString());
		}
		
		return resp;
	}

}
