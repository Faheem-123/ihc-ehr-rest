package com.ihc.ehr.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihc.ehr.model.ImmRegVXUCriteria;
import com.ihc.ehr.model.HL7FileGenerationResult;
import com.ihc.ehr.model.ImmRegQBPCriteria;
import com.ihc.ehr.model.ORMDeleteRecord;
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
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.UpdateRecord;
import com.ihc.ehr.model.WrapperImmRegEvaluationHistoryForecastMessageDetails;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.service.ImmunizationService;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@RestController
@RequestMapping("/immunization")
public class ImmunizationController {

	@Autowired
	ImmunizationService immService;

	@Autowired
	GeneralService generalService;

	@RequestMapping("/getClinics/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMImmRegClinicsGet>> getClinics(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getImmRegistryClinics:  practice_id=" + practice_id.toString());

		List<ORMImmRegClinicsGet> lst = immService.getClinics(practice_id);
		return new ResponseEntity<List<ORMImmRegClinicsGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getSetupImmAllList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMImmSetupAllListGet>> getSetupImmAllList(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getSetupImmAllList:  practice_id=" + practice_id.toString());

		List<ORMImmSetupAllListGet> lst = immService.getSetupImmAllList(practice_id);
		return new ResponseEntity<List<ORMImmSetupAllListGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getSetupImmPracticeList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMImmSetupPracticeListGet>> getSetupImmPracticeList(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getSetupImmPracticeList:  practice_id=" + practice_id.toString());

		List<ORMImmSetupPracticeListGet> lst = immService.getSetupImmPracticeList(practice_id);
		return new ResponseEntity<List<ORMImmSetupPracticeListGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getImmManufacturer/{cvx_code}")
	public @ResponseBody ResponseEntity<List<ORMImmManufacturerGet>> getImmManufacturer(
			@PathVariable(value = "cvx_code") String cvx_code) {
		GeneralOperations.logMsg("Inside getImmManufacturer");

		List<ORMImmManufacturerGet> lst = immService.getImmManufacturer(cvx_code);
		return new ResponseEntity<List<ORMImmManufacturerGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getImmTradeName/{cvx_code}")
	public @ResponseBody ResponseEntity<List<ORMImmTradeNameGet>> getImmTradeName(
			@PathVariable(value = "cvx_code") String cvx_code) {
		GeneralOperations.logMsg("Inside getImmTradeName");

		List<ORMImmTradeNameGet> lst = immService.getImmTradeName(cvx_code);
		return new ResponseEntity<List<ORMImmTradeNameGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getImmProcedure/{cvx_code}")
	public @ResponseBody ResponseEntity<List<ORMImmProceduresGet>> getImmProcedure(
			@PathVariable(value = "cvx_code") String cvx_code) {
		GeneralOperations.logMsg("Inside getImmProcedure");

		List<ORMImmProceduresGet> lst = immService.getImmProcedure(cvx_code);
		return new ResponseEntity<List<ORMImmProceduresGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getImmNDC/{cvx_code}")
	public @ResponseBody ResponseEntity<List<ORMImmNDCGet>> getImmNDC(
			@PathVariable(value = "cvx_code") String cvx_code) {

		GeneralOperations.logMsg("Inside getImmNDC");
		List<ORMImmNDCGet> lst = immService.getImmNDC(cvx_code);
		return new ResponseEntity<List<ORMImmNDCGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getImmVIS/{cvx_code}")
	public @ResponseBody ResponseEntity<List<ORMImmVISGet>> getImmVIS(
			@PathVariable(value = "cvx_code") String cvx_code) {

		GeneralOperations.logMsg("Inside getImmVIS");
		List<ORMImmVISGet> lst = immService.getImmVIS(cvx_code);
		return new ResponseEntity<List<ORMImmVISGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getImmRouteList")
	public @ResponseBody ResponseEntity<List<ORMImmRoutesListGet>> getImmRouteList() {
		List<ORMImmRoutesListGet> obj = immService.getImmRouteList();
		return new ResponseEntity<List<ORMImmRoutesListGet>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getImmSiteList")
	public @ResponseBody ResponseEntity<List<ORMImmSitesListGet>> getImmSiteList() {
		List<ORMImmSitesListGet> obj = immService.getImmSiteList();
		return new ResponseEntity<List<ORMImmSitesListGet>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getImmCodeSet")
	public @ResponseBody ResponseEntity<List<ORMImmCodeSet>> getImmCodeSet() {
		List<ORMImmCodeSet> obj = immService.getImmCodeSet();
		return new ResponseEntity<List<ORMImmCodeSet>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/savePracticeImmunization")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> savePracticeImmunization(
			@RequestBody ORMPracticeImmunizationSave ormPracticeImmunizationSave) {

		GeneralOperations.logMsg("Inside savePracticeImmunization");

		ServiceResponse<ORMKeyValue> resp = immService.savePracticeImmunization(ormPracticeImmunizationSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deletePracticeImmunization")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deletePatientInjury(
			@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deletePracticeImmunization: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("practice_immunization");
		ormDeleteRecord.setColumn_name("practice_immunization_id");

		int result = generalService.deleteRecord(ormDeleteRecord);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormDeleteRecord.getColumn_id().toString());
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getInventoryVaccineList")
	public @ResponseBody ResponseEntity<List<ORMImmInventoryVaccineListGet>> getInventoryVaccineList(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMImmInventoryVaccineListGet> lstSearchPatient = immService.getInventoryVaccineList(searchCriteria);
		return new ResponseEntity<List<ORMImmInventoryVaccineListGet>>(lstSearchPatient, HttpStatus.OK);
	}

	@RequestMapping("/getInventoryVaccineDetails")
	public @ResponseBody ResponseEntity<List<ORMImmInventoryDetailGet>> getInventoryVaccineDetails(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMImmInventoryDetailGet> lstSearchPatient = immService.getInventoryVaccineDetails(searchCriteria);
		return new ResponseEntity<List<ORMImmInventoryDetailGet>>(lstSearchPatient, HttpStatus.OK);
	}

	@RequestMapping("/getImmTradeNameSearchList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMImmTradeNameSearchListGet>> getImmunizationTradeNameSearchList(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMImmTradeNameSearchListGet> lst = immService.getImmunizationTradeNamePracticeSearchList(practice_id);
		return new ResponseEntity<List<ORMImmTradeNameSearchListGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getImmPracticeSearchList/{practice_id}/{location_id}")
	public @ResponseBody ResponseEntity<List<ORMImmSearchListGet>> getImmunizationPracticeSearchList(
			@PathVariable(value = "practice_id") String practice_id,
			@PathVariable(value = "location_id") String location_id) {
		List<ORMImmSearchListGet> obj = immService.getImmunizationPracticeSearchList(practice_id, location_id);
		return new ResponseEntity<List<ORMImmSearchListGet>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/saveImmInventory")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveImmunizationInventory(
			@RequestBody ORMImmInventorySave ormImmunizationInventorySave) {

		GeneralOperations.logMsg("Inside saveImmunizationInventory");

		ServiceResponse<ORMKeyValue> resp = immService.saveImmunizationInventory(ormImmunizationInventorySave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deleteImmInventory")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteImmunizationInventory(
			@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteImmunizationInventory: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("immunization_inventory");
		ormDeleteRecord.setColumn_name("inventory_id");

		int result = generalService.deleteRecord(ormDeleteRecord);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormDeleteRecord.getColumn_id().toString());
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getImmInventoryUsage/{inventory_id}")
	public @ResponseBody ResponseEntity<List<ORMImmInventoryUsageGet>> getImmunizationInventoryUsage(
			@PathVariable(value = "inventory_id") Long inventory_id) {
		List<ORMImmInventoryUsageGet> obj = immService.getImmunizationInventoryUsage(inventory_id);
		return new ResponseEntity<List<ORMImmInventoryUsageGet>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getImmRegMessages")
	public @ResponseBody ResponseEntity<List<ORMImmRegMessagesGet>> getImmRegMessages(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getImmRegMessages");

		List<ORMImmRegMessagesGet> lst = immService.getImmRegMessages(searchCriteria);
		return new ResponseEntity<List<ORMImmRegMessagesGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getImmRegMsgImmunizations/{message_id}")
	public @ResponseBody ResponseEntity<List<ORMImmRegMsgImmunizationsGet>> getImmRegMsgImmunizations(
			@PathVariable(value = "message_id") Long message_id) {
		GeneralOperations.logMsg("Inside getImmRegMessageImmunizations");

		List<ORMImmRegMsgImmunizationsGet> lst = immService.getImmRegMsgImmunizations(message_id);
		return new ResponseEntity<List<ORMImmRegMsgImmunizationsGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getImmRegMsgErrors/{message_id}")
	public @ResponseBody ResponseEntity<List<ORMImmRegMsgErrorsGet>> getImmRegMsgErrors(
			@PathVariable(value = "message_id") Long message_id) {
		GeneralOperations.logMsg("Inside getImmRegMsgErrors");

		List<ORMImmRegMsgErrorsGet> lst = immService.getImmRegMsgErrors(message_id);
		return new ResponseEntity<List<ORMImmRegMsgErrorsGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getImmRegEvaluationHistoryForecastMessageDetails/{registry_code}/{message_id}")
	public @ResponseBody ResponseEntity<WrapperImmRegEvaluationHistoryForecastMessageDetails> getImmRegEvaluationHistoryForecastMessageDetails(			
			@PathVariable(value = "registry_code") String registryCode,
			@PathVariable(value = "message_id") Long messageId) {
		GeneralOperations.logMsg("Inside getImmRegEvaluationHistoryForecastMessageDetails");

		WrapperImmRegEvaluationHistoryForecastMessageDetails obj = immService
				.getImmRegEvaluationHistoryForecastMessageDetails(registryCode,messageId);
		return new ResponseEntity<WrapperImmRegEvaluationHistoryForecastMessageDetails>(obj, HttpStatus.OK);
	}

	@RequestMapping("/generateVXUFile")
	public @ResponseBody ResponseEntity<ServiceResponse<HL7FileGenerationResult>> generateVXUFile(
			@RequestBody ImmRegVXUCriteria immRegVXUCriteria) {
		GeneralOperations.logMsg("Inside generateVXUFile");

		ServiceResponse<HL7FileGenerationResult> resp = immService.generateVXUFile(immRegVXUCriteria);
		return new ResponseEntity<ServiceResponse<HL7FileGenerationResult>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/generateQBPFile")
	public @ResponseBody ResponseEntity<ServiceResponse<HL7FileGenerationResult>> generateQBPFile(
			@RequestBody ImmRegQBPCriteria immRegQBPCriteria) {
		GeneralOperations.logMsg("Inside generateQBPFile");

		ServiceResponse<HL7FileGenerationResult> resp = immService.generateQBPFile(immRegQBPCriteria);
		return new ResponseEntity<ServiceResponse<HL7FileGenerationResult>>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/processAcknowledgementMessageFromFileData", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> ProcessAcknowledgementMessageFromFileData(
			@RequestParam(value = "response_file") MultipartFile responseFileData, // inputFile,
			@RequestParam("criteria_data") String criteriaData)
			throws JsonParseException, JsonMappingException, IOException
	// @RequestParam("practice_id") String practiceId,
	// @RequestParam("registry_code") String registryCode,
	// @RequestParam("loged_in_user") String logedInUser
	// )
	{

		ObjectMapper mapper = new ObjectMapper();
		SearchCriteria searchCriteria = mapper.readValue(criteriaData, SearchCriteria.class);

		System.out.println("processAcknowledgementMessageFromFileData");

		ServiceResponse<ORMKeyValue> resp = immService.ProcessAcknowledgementMessageFromFileData(responseFileData,
				searchCriteria);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/processAcknowledgementMessageFromDirectory")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> ProcessAcknowledgementMessageFromDirectory(
			@RequestBody SearchCriteria searchCriteria) {

		System.out.println("processAcknowledgementMessageFromDirectory");

		ServiceResponse<ORMKeyValue> resp = immService.ProcessAcknowledgementMessageFromDirectory(searchCriteria);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/getPatientImmRegInfo/{patient_id}")
	public @ResponseBody ResponseEntity<ORMGetPatImmRegInfoDisplay> getPatientImmRegInfo(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientImmRegInfo:  patientId=" + patientId);

		ORMGetPatImmRegInfoDisplay obj = immService.getPatientImmRegInfo(patientId);

		return new ResponseEntity<ORMGetPatImmRegInfoDisplay>(obj, HttpStatus.OK);
	}

	@RequestMapping("/markRegistryMessageAsResolved")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> markRegistryMessageAsResolved(
			@RequestBody UpdateRecord updateRecord) {

		System.out.println("markRegistryMessageAsResolved");

		ServiceResponse<ORMKeyValue> resp = immService.markRegistryMessageAsResolved(updateRecord);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}	
}
