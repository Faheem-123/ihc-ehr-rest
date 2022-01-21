package com.ihc.ehr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihc.ehr.model.ORMChartReasonForVisit_HPI;
import com.ihc.ehr.model.ORMChartSurgery;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMGetChartSummary;
import com.ihc.ehr.model.ORMGetClient_Insurances;
import com.ihc.ehr.model.ORMGetProviderModifier;
import com.ihc.ehr.model.ORMGetProviderPayers;
import com.ihc.ehr.model.ORMInsurance_PayerTypes;
import com.ihc.ehr.model.ORMInsurance_Payers;
import com.ihc.ehr.model.ORMInsurance_setup;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPracticeInsurance;
import com.ihc.ehr.model.ORMSaveProvider_Payers;
import com.ihc.ehr.model.ORMgetMappingPracticeInsurances;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.service.InsurancesService;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/insurances")
public class InsurancesController {

	@Autowired
	InsurancesService insService;

	@Autowired
	GeneralService generalService;

	@RequestMapping("/getInsurancePayerTypes")
	public @ResponseBody ResponseEntity<List<ORMInsurance_PayerTypes>> getInsurancePayerTypes() {
		List<ORMInsurance_PayerTypes> lst = insService.getInsurancePayerTypes();
		return new ResponseEntity<List<ORMInsurance_PayerTypes>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/saveInsurancePayerTypes")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveInsurancePayerTypes(
			@RequestBody ORMInsurance_PayerTypes obj) {
		ServiceResponse<ORMKeyValue> resp = insService.saveInsurancePayerTypes(obj);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteInsurancePayerTypes")
	public int deleteInsurancePayerTypes(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("PayerType");
		ormDeleteRecord.setColumn_name("payertype_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}
	
	@RequestMapping("/getPayerTypePayer/{payerType_id}")
	public @ResponseBody ResponseEntity<List<ORMInsurance_Payers>> getPayerTypePayer(
			@PathVariable(value = "payerType_id") String payerType_id) {

		List<ORMInsurance_Payers> obj = insService.getPayerTypePayer(payerType_id);

		return new ResponseEntity<List<ORMInsurance_Payers>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/saveInsurancePayer")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveInsurancePayer(
			@RequestBody ORMInsurance_Payers obj) {
		ServiceResponse<ORMKeyValue> resp = insService.saveInsurancePayer(obj);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteInsurancePayer")
	public int deleteInsurancePayer(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("Payer");
		ormDeleteRecord.setColumn_name("payer_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}
	@RequestMapping("/getPayerInsurances/{payer_id}")
	public @ResponseBody ResponseEntity<List<ORMInsurance_setup>> getPayerInsurances(
			@PathVariable(value = "payer_id") String payer_id) {

		List<ORMInsurance_setup> obj = insService.getPayerInsurances(payer_id);

		return new ResponseEntity<List<ORMInsurance_setup>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/saveInsurances")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveInsurances(
			@RequestBody ORMInsurance_setup obj) {
		ServiceResponse<ORMKeyValue> resp = insService.saveInsurances(obj);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/saveInsuranceslist")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveInsuranceslist(
			@RequestBody List<ORMInsurance_setup> lstSave) {
		ServiceResponse<ORMKeyValue> resp = insService.saveInsuranceslist(lstSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteInsurance")
	public int deleteInsurance(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("insurance");
		ormDeleteRecord.setColumn_name("insurance_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}
	@RequestMapping("/getProviderPayer/{provider_id}")
	public @ResponseBody ResponseEntity<List<ORMGetProviderPayers>> getProviderPayer(
			@PathVariable(value = "provider_id") String provider_id) {

		List<ORMGetProviderPayers> obj = insService.getProviderPayer(provider_id);

		return new ResponseEntity<List<ORMGetProviderPayers>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/saveProviderPayer")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> saveProviderPayer(
			@RequestBody ORMSaveProvider_Payers obj) {
		ServiceResponse<ORMKeyValue> resp = insService.saveProviderPayer(obj);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	@RequestMapping("/deleteProviderPayer")
	public int deleteProviderPayer(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("Provider_payers");
		ormDeleteRecord.setColumn_name("provider_payer_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}
	@RequestMapping("/getProviderModifier")
	public @ResponseBody ResponseEntity<List<ORMGetProviderModifier>> getProviderModifier() {
		List<ORMGetProviderModifier> lst = insService.getProviderModifier();
		return new ResponseEntity<List<ORMGetProviderModifier>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/getClient_Insurances/{criteria}")
	public @ResponseBody ResponseEntity<List<ORMGetClient_Insurances>> getClient_Insurances(
			@PathVariable(value = "criteria") String criteria) {

		List<ORMGetClient_Insurances> obj = insService.getClient_Insurances(criteria);

		return new ResponseEntity<List<ORMGetClient_Insurances>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/getUnMappedPracticeInsurances/{practice_id}/{is_mapped}/{payerid}")
	public @ResponseBody ResponseEntity<List<ORMgetMappingPracticeInsurances>> getUnMappedPracticeInsurances(
			@PathVariable(value = "practice_id") String practice_id,
			@PathVariable(value = "is_mapped") String is_mapped,
			@PathVariable(value = "payerid") String payerid) {

		List<ORMgetMappingPracticeInsurances> obj = insService.getUnMappedPracticeInsurances(practice_id,is_mapped,payerid);

		return new ResponseEntity<List<ORMgetMappingPracticeInsurances>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/deletePracticeInsurance")
	public int deletePracticeInsurance(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("practice_insurance");
		ormDeleteRecord.setColumn_name("practice_insurance_id");

		return generalService.deleteRecord(ormDeleteRecord);
	}
	@RequestMapping("/savePracticeInsurance")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> savePracticeInsurance(
			@RequestBody  List<ORMPracticeInsurance> obj) {
		ServiceResponse<ORMKeyValue> resp = insService.savePracticeInsurance(obj);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}	
	
	
}
