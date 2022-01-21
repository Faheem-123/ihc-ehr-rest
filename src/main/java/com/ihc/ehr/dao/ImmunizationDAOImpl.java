package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.HL7FileGenerationResult;
import com.ihc.ehr.model.ImmRegQBPCriteria;
import com.ihc.ehr.model.ImmRegVXUCriteria;
import com.ihc.ehr.model.ORMGetPatImmRegInfoDisplay;
import com.ihc.ehr.model.ORMImmCodeSet;
import com.ihc.ehr.model.ORMImmInventoryDetailGet;
import com.ihc.ehr.model.ORMImmInventorySave;
import com.ihc.ehr.model.ORMImmInventoryUsageGet;
import com.ihc.ehr.model.ORMImmInventoryVaccineListGet;
import com.ihc.ehr.model.ORMImmManufacturerGet;
import com.ihc.ehr.model.ORMImmNDCGet;
import com.ihc.ehr.model.ORMImmProceduresGet;
import com.ihc.ehr.model.ORMImmRegClinicsGet;
import com.ihc.ehr.model.ORMImmRegMessagesGet;
import com.ihc.ehr.model.ORMImmRegMsgErrorsGet;
import com.ihc.ehr.model.ORMImmRegMsgImmunizationsGet;
import com.ihc.ehr.model.ORMImmRoutesListGet;
import com.ihc.ehr.model.ORMImmSearchListGet;
import com.ihc.ehr.model.ORMImmSetupAllListGet;
import com.ihc.ehr.model.ORMImmSetupPracticeListGet;
import com.ihc.ehr.model.ORMImmSitesListGet;
import com.ihc.ehr.model.ORMImmTradeNameGet;
import com.ihc.ehr.model.ORMImmTradeNameSearchListGet;
import com.ihc.ehr.model.ORMImmVISGet;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPracticeImmunizationSave;
import com.ihc.ehr.model.ORMSavePatientImmRegInfo;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.UpdateRecord;
import com.ihc.ehr.model.WrapperImmRegEvaluationHistoryForecastMessageDetails;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.ImmunizationRegistryOperationsMU3;
import com.ihc.ehr.util.WIROperation;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@Repository
public class ImmunizationDAOImpl implements ImmunizationDAO {

	@Autowired
	DBOperations db;

	@Autowired
	private PatientDAOImpl patientDAOImpl;

	@Autowired
	private EncounterDAOImpl encounterDAOImpl;

	@Override
	public List<ORMImmRegClinicsGet> getClinics(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetImmunizationRegistryClinics", ORMImmRegClinicsGet.class, lstParam);
	}

	@Override
	public List<ORMImmSetupAllListGet> getSetupImmAllList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetImmunizationSetupAllList", ORMImmSetupAllListGet.class, lstParam);
	}

	@Override
	public List<ORMImmSetupPracticeListGet> getSetupImmPracticeList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetImmunizationSetupPracticeList", ORMImmSetupPracticeListGet.class,
				lstParam);
	}

	@Override
	public List<ORMImmManufacturerGet> getImmManufacturer(String cvx_code) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("cvx_code", cvx_code.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetImmManufacturer", ORMImmManufacturerGet.class, lstParam);
	}

	@Override
	public List<ORMImmTradeNameGet> getImmTradeName(String cvx_code) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("cvx_code", cvx_code.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetImmTradeName", ORMImmTradeNameGet.class, lstParam);
	}

	@Override
	public List<ORMImmProceduresGet> getImmProcedure(String cvx_code) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("cvx_code", cvx_code.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetImmProcedures", ORMImmProceduresGet.class, lstParam);
	}

	@Override
	public List<ORMImmNDCGet> getImmNDC(String cvx_code) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("cvx_code", cvx_code.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetImmunizationNDC", ORMImmNDCGet.class, lstParam);
	}

	@Override
	public List<ORMImmVISGet> getImmVIS(String cvx_code) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("cvx_code", cvx_code.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("getImmunizationVIS", ORMImmVISGet.class, lstParam);
	}

	@Override
	public List<ORMImmRoutesListGet> getImmRouteList() {
		// TODO Auto-generated method stub
		List<ORMImmRoutesListGet> lstData = db.getStoreProcedureData("getImmunizationRoute", ORMImmRoutesListGet.class,
				null);
		return lstData;
	}

	@Override
	public List<ORMImmSitesListGet> getImmSiteList() {
		// TODO Auto-generated method stub
		List<ORMImmSitesListGet> lstData = db.getStoreProcedureData("getImmunizationSite", ORMImmSitesListGet.class,
				null);
		return lstData;
	}

	@Override
	public List<ORMImmCodeSet> getImmCodeSet() {
		// TODO Auto-generated method stub
		List<ORMImmCodeSet> lstData = db.getStoreProcedureData("getImmunizationCodeSet", ORMImmCodeSet.class, null);
		return lstData;
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePracticeImmunization(
			ORMPracticeImmunizationSave ormPracticeImmunizationSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		ormPracticeImmunizationSave.setDate_modified(serverDateTime);
		if (GeneralOperations.isNullorEmpty(ormPracticeImmunizationSave.getPractice_immunization_id())) {

			ormPracticeImmunizationSave.setPractice_immunization_id(
					db.IDGenerator("chart_immunization", ormPracticeImmunizationSave.getPractice_id()));
			ormPracticeImmunizationSave.setDate_created(serverDateTime);

			result = db.SaveEntity(ormPracticeImmunizationSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormPracticeImmunizationSave, Operation.EDIT);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormPracticeImmunizationSave.getCvx_code().toString());
		}

		return resp;
	}

	@Override
	public List<ORMImmInventoryVaccineListGet> getInventoryVaccineList(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spGetImmInventoryVaccineList", ORMImmInventoryVaccineListGet.class, lstParam);

	}

	@Override
	public List<ORMImmInventoryDetailGet> getInventoryVaccineDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue() == null ? "" : pram.getValue().toString(),
						String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spGetmmInventoryDetails", ORMImmInventoryDetailGet.class, lstParam);
	}

	@Override
	public List<ORMImmTradeNameSearchListGet> getImmunizationTradeNamePracticeSearchList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetImmunizationTradeNamePracticeSearchList",
				ORMImmTradeNameSearchListGet.class, lstParam);
	}

	@Override
	public List<ORMImmSearchListGet> getImmunizationPracticeSearchList(String practice_id, String location_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("location_id", location_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetImmunizationPracticeSearchList", ORMImmSearchListGet.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveImmunizationInventory(ORMImmInventorySave ormImmunizationInventorySave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		ormImmunizationInventorySave.setDate_modified(serverDateTime);
		if (GeneralOperations.isNullorEmpty(ormImmunizationInventorySave.getInventory_id())) {

			ormImmunizationInventorySave.setInventory_id(
					db.IDGenerator("immunization_inventory", ormImmunizationInventorySave.getPractice_id()));

			ormImmunizationInventorySave.setDate_created(serverDateTime);

			result = db.SaveEntity(ormImmunizationInventorySave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormImmunizationInventorySave, Operation.EDIT);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormImmunizationInventorySave.getInventory_id().toString());
		}

		return resp;
	}

	@Override
	public List<ORMImmInventoryUsageGet> getImmunizationInventoryUsage(Long inventory_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("inventory_id", inventory_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetImmunizationInventoryUsage", ORMImmInventoryUsageGet.class, lstParam);
	}

	@Override
	public List<ORMImmRegMessagesGet> getImmRegMessages(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String criteriaMsg = "";
		// String criteriaImm = "";

		String patientId = "";
		String practiceId = "";
		String statusCriteria = "";
		String dateType = "";
		String dateFrom = "";
		String dateTo = "";
		String msgType = "";

		practiceId = searchCriteria.getPractice_id().toString();
		msgType = searchCriteria.getOption();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "message_type":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						patientId = pram.getValue();

						criteriaMsg += " and r.message_type='" + pram.getValue() + "' ";
					}
					break;

				case "patient_id":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						patientId = pram.getValue();
						criteriaMsg += " and r.patient_id='" + pram.getValue() + "' ";
					}
					break;

				case "status_code":

					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						if (pram.getValue().equalsIgnoreCase("RESOLVED")) {
							statusCriteria = " and isnull(r.resolved,0)=1 ";
						} else if (pram.getValue().equalsIgnoreCase("ERROR")) {
							statusCriteria = "  and r.message_status in ('AE','AR','TF','AF') and isnull(r.resolved,0)<>1 ";
						} else if (pram.getValue().equalsIgnoreCase("AA")) {
							statusCriteria = "  and r.message_status in ('AA','AA_IW') and isnull(r.resolved,0)<>1 ";
						} else {
							statusCriteria = "  and r.message_status='" + pram.getValue()
									+ "' and isnull(r.resolved,0)<>1 ";
						}
					}

					break;
				case "clinic_id":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						criteriaMsg += " and r.clinic_id='" + pram.getValue() + "' ";
					}
					break;
				case "registry_code":
					criteriaMsg += " and r.registry_code='" + pram.getValue() + "' ";
					break;
				case "date_from":
					dateFrom = pram.getValue();
					dateType = pram.getOption();
					break;
				case "date_to":
					dateTo = pram.getValue();
					dateType = pram.getOption();
					break;

				default:
					break;
				}

			}
		}

		if (GeneralOperations.isNullorEmpty(statusCriteria)) {
			statusCriteria = " and isnull(r.resolved,0)<>1 ";
		}
		criteriaMsg += statusCriteria;

		if (dateType.equalsIgnoreCase("dose_date")) {

			String criteriaImm = " select message_id from imm_reg_message_immunizations where convert(date,date_administered) between convert(date,'"
					+ dateFrom + "') and convert(date,'" + dateTo + "') " + " and practice_id='" + practiceId + "'";

			if (patientId != "") {
				criteriaImm += " and patient_id='" + patientId + "' ";
			}

			criteriaMsg += " and message_id in (" + criteriaImm + ")";

		} else {
			criteriaMsg += " and convert(date,r.message_sent_date) between convert(date,'" + dateFrom
					+ "') and convert(date,'" + dateTo + "') ";
		}

		if (GeneralOperations.isNotNullorEmpty(msgType)) {
			criteriaMsg += " and r.message_type='" + msgType + "' ";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteriaMsg, String.class, ParameterMode.IN));
		// lstParam.add(new SpParameters("criteria_immunization", criteriaImm,
		// String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetImmunizationRegistryMessages", ORMImmRegMessagesGet.class, lstParam);

	}

	@Override
	public List<ORMImmRegMsgImmunizationsGet> getImmRegMsgImmunizations(Long message_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("message_id", message_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetRegistryMessageImmunizations", ORMImmRegMsgImmunizationsGet.class,
				lstParam);
	}

	@Override
	public List<ORMImmRegMsgErrorsGet> getImmRegMsgErrors(Long message_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("message_id", message_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetImmunizationMessageErrors", ORMImmRegMsgErrorsGet.class, lstParam);
	}

	@Override
	public WrapperImmRegEvaluationHistoryForecastMessageDetails getImmRegEvaluationHistoryForecastMessageDetails(
			String registryCode,Long messageId) {
		// TODO Auto-generated method stub
	
		WrapperImmRegEvaluationHistoryForecastMessageDetails wrapperImmRegEvaluationHistoryForecastMessageDetails = null;

		if (registryCode.equalsIgnoreCase("MU3")) {

			ImmunizationRegistryOperationsMU3 immunizationRegistryOperationsMU3 = new ImmunizationRegistryOperationsMU3(
					db, patientDAOImpl, encounterDAOImpl, this);
			wrapperImmRegEvaluationHistoryForecastMessageDetails = immunizationRegistryOperationsMU3
					.getImmRegEvaluationHistoryForecastMessageDetails(messageId);

		} else if (registryCode.equalsIgnoreCase("WIR") || registryCode.equalsIgnoreCase("WIR_TEST")) {

			WIROperation wirOperation = new WIROperation(db, patientDAOImpl, encounterDAOImpl, this);
			wrapperImmRegEvaluationHistoryForecastMessageDetails = wirOperation
					.getImmRegEvaluationHistoryForecastMessageDetails(messageId);

		}

		return wrapperImmRegEvaluationHistoryForecastMessageDetails;
	}

	@Override
	public ServiceResponse<HL7FileGenerationResult> generateVXUFile(
			ImmRegVXUCriteria immRegistryFileGenerationCriteria) {
		// TODO Auto-generated method stub

		ServiceResponse<HL7FileGenerationResult> resp = new ServiceResponse<HL7FileGenerationResult>();
		List<HL7FileGenerationResult> lstResult = null;

		// PATIENT IMMUNIZATION REGISTRY INFO

		if (immRegistryFileGenerationCriteria.getPatient_imm_registry_info() != null) {
			ORMSavePatientImmRegInfo ormSavePatientImmRegInfo = immRegistryFileGenerationCriteria
					.getPatient_imm_registry_info();
			if (ormSavePatientImmRegInfo != null) {

				int result = 0;
				ormSavePatientImmRegInfo.setDate_modified(DateTimeUtil.getCurrentDateTime());
				if (ormSavePatientImmRegInfo.getRegistry_info_id() == null
						|| ormSavePatientImmRegInfo.getRegistry_info_id() <= 0) {
					ormSavePatientImmRegInfo.setRegistry_info_id(db.IDGenerator("patient_immunization_registry_info",
							ormSavePatientImmRegInfo.getPractice_id()));
					ormSavePatientImmRegInfo.setDate_created(DateTimeUtil.getCurrentDateTime());

					result = db.SaveEntity(ormSavePatientImmRegInfo, Operation.ADD);

				} else {
					result = db.SaveEntity(ormSavePatientImmRegInfo, Operation.EDIT);
				}

				if (result == 0) {
					resp.setStatus(ServiceResponseStatus.ERROR);
					resp.setResponse("An Error Occured while saving patient Registry Info.");
					return resp;
				}
			}
		}
		// end IMMUNIZATION REGISTRY INFO

		if (immRegistryFileGenerationCriteria.getRegistry_code().equalsIgnoreCase("MU3")) {

			ImmunizationRegistryOperationsMU3 immunizationRegistryOperationsMU3 = new ImmunizationRegistryOperationsMU3(
					db, patientDAOImpl, encounterDAOImpl, this);

			lstResult = immunizationRegistryOperationsMU3.generateVXU_HL7(immRegistryFileGenerationCriteria);

		} else if (immRegistryFileGenerationCriteria.getRegistry_code().equalsIgnoreCase("WIR")
				|| immRegistryFileGenerationCriteria.getRegistry_code().equalsIgnoreCase("WIR_TEST")) {

			WIROperation wirOperation = new WIROperation(db, patientDAOImpl, encounterDAOImpl, this);

			lstResult = wirOperation.generateVXU_HL7(immRegistryFileGenerationCriteria);

		}

		if (lstResult != null && lstResult.size() > 0) {

			if (lstResult.get(0).getType().equals("SUCCESSFULL")) {

				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse(lstResult.get(0).getMessage());
				resp.setResult(lstResult.get(0).getId().toString());

			} else {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while generating Registry File. Details are in response_list");
				resp.setResponse_list(lstResult);
				resp.setResult(lstResult.get(0).getId().toString());
			}

		}

		return resp;
	}

	@Override
	public ServiceResponse<HL7FileGenerationResult> generateQBPFile(ImmRegQBPCriteria immRegQBPCriteria) {
		// TODO Auto-generated method stub

		ImmunizationRegistryOperationsMU3 immunizationRegistryOperationsMU3 = new ImmunizationRegistryOperationsMU3(db,
				patientDAOImpl, encounterDAOImpl, this);

		ServiceResponse<HL7FileGenerationResult> resp = new ServiceResponse<HL7FileGenerationResult>();
		List<HL7FileGenerationResult> lstResult = null;
		if (immRegQBPCriteria.getRegistry_code().equalsIgnoreCase("MU3")) {

			lstResult = immunizationRegistryOperationsMU3.generateQBP_HL7(immRegQBPCriteria);

		} else if (immRegQBPCriteria.getRegistry_code().equalsIgnoreCase("WIR")
				|| immRegQBPCriteria.getRegistry_code().equalsIgnoreCase("WIR_TEST")) {

			WIROperation wirOperation = new WIROperation(db, patientDAOImpl, encounterDAOImpl, this);

			lstResult = wirOperation.generateQBP_HL7(immRegQBPCriteria);

		}

		if (lstResult != null && lstResult.size() > 0) {

			if (lstResult.get(0).getType().equals("SUCCESSFULL")) {

				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse(lstResult.get(0).getMessage());
				resp.setResult(lstResult.get(0).getId().toString());

			} else {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while generating Registry File. Details are in response_list");
				resp.setResponse_list(lstResult);
				resp.setResult(lstResult.get(0).getId().toString());
			}

		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> ProcessAcknowledgementMessageFromFileData(MultipartFile responseFileData,
			SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String registryCode = "";
		ServiceResponse<ORMKeyValue> response = null;

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "registry_code":
					registryCode = pram.getValue();
					break;
				default:
					break;
				}
			}
		}

		if (registryCode.equalsIgnoreCase("MU3")) {

			ImmunizationRegistryOperationsMU3 immunizationRegistryOperationsMU3 = new ImmunizationRegistryOperationsMU3(
					db, patientDAOImpl, encounterDAOImpl, this);

			response = immunizationRegistryOperationsMU3.ProcessAcknowledgementMessageFromFileData(responseFileData,
					searchCriteria);

		} else if (registryCode.equalsIgnoreCase("WIR") || registryCode.equalsIgnoreCase("WIR_TEST")) {

			WIROperation wirOperation = new WIROperation(db, patientDAOImpl, encounterDAOImpl, this);

			response = wirOperation.ProcessAcknowledgementMessageFromFileData(responseFileData, searchCriteria);

		}

		return response;

	}

	@Override
	public ServiceResponse<ORMKeyValue> ProcessAcknowledgementMessageFromDirectory(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String registryCode = "";
		ServiceResponse<ORMKeyValue> response = null;

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "registry_code":
					registryCode = pram.getValue();
					break;
				default:
					break;
				}
			}
		}

		if (registryCode.equalsIgnoreCase("MU3")) {

			ImmunizationRegistryOperationsMU3 immunizationRegistryOperationsMU3 = new ImmunizationRegistryOperationsMU3(
					db, patientDAOImpl, encounterDAOImpl, this);

			response = immunizationRegistryOperationsMU3.ProcessAcknowledgementMessageFromDirectory(searchCriteria);

		} else if (registryCode.equalsIgnoreCase("WIR") || registryCode.equalsIgnoreCase("WIR_TEST")) {

			WIROperation wirOperation = new WIROperation(db, patientDAOImpl, encounterDAOImpl, this);

			response = wirOperation.ProcessAcknowledgementResponseMessageFromDirectory(searchCriteria);

		}

		return response;
	}

	@Override
	public ORMGetPatImmRegInfoDisplay getPatientImmRegInfo(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatImmRegInfoDisplay> lst = db.getStoreProcedureData("getPatImmunizationRegInfoDisplay",
				ORMGetPatImmRegInfoDisplay.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return (ORMGetPatImmRegInfoDisplay) lst.get(0);
		} else {
			return null;
		}

	}

	@Override
	public ServiceResponse<ORMKeyValue> markRegistryMessageAsResolved(UpdateRecord updateRecord) {
		// TODO Auto-generated method stub

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		String query = " update immunization_registry_messages set resolved=1,resend_enable=0,resolved_by='"
				+ updateRecord.getUser_name() + "',system_ip='" + updateRecord.getClient_ip()
				+ "',date_resolved=getdate() where message_id='" + updateRecord.getId() + "'";

		if (db.ExecuteUpdateQuery(query) > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(updateRecord.getId().toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		}

		return resp;
	}

}
