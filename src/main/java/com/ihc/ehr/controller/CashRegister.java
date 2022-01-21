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

import com.ihc.ehr.model.ORMCashRegisterAdd;
import com.ihc.ehr.model.ORMCashRegisterGet;
import com.ihc.ehr.model.ORMCashRegisterModify;
import com.ihc.ehr.model.ORMCashRegisterPatInfo;
import com.ihc.ehr.model.ORMCashRegisterUnResolvedPaymentGet;
import com.ihc.ehr.model.ORMFourColumGeneric;
import com.ihc.ehr.model.ORMIdCodeDescription;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientRefundGet;
import com.ihc.ehr.model.ORMSavePatientNotPaidReason;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.Wrapper_PatientRefundSave;
import com.ihc.ehr.model.Wrapper_TwoObjectsSave;
import com.ihc.ehr.service.CashRegisterService;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/cashregister")
public class CashRegister {

	@Autowired
	CashRegisterService cashRegisterService;
	
	@RequestMapping("/getwriteoffcodes/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getWriteOffCodes(
			@PathVariable(value = "practice_id") Long practice_id)
	{	
		GeneralOperations.logMsg("Inside getWriteOffCodes:  practice_id="+practice_id.toString());
		
		List<ORMIdCodeDescription> lst=cashRegisterService.getWriteOffCodes(practice_id); 
		return new ResponseEntity<List<ORMIdCodeDescription>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/getcashregisterinfo")
	public @ResponseBody ResponseEntity<ORMCashRegisterPatInfo> getCahRegisterInfo(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside getCahRegisterInfo: ");
		
		ORMCashRegisterPatInfo ormInfo=cashRegisterService.getCahRegisterInfo(searchCriteria); 
		
		return new ResponseEntity<ORMCashRegisterPatInfo>(ormInfo , HttpStatus.OK);
	}
	
	@RequestMapping("/getpaymentplan/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMFourColumGeneric>> getPaymentPlan(
			@PathVariable(value = "patient_id") Long patient_id)
	{	
		GeneralOperations.logMsg("Inside getPaymentPlan:  patient_id="+patient_id.toString());
		
		List<ORMFourColumGeneric> lst=cashRegisterService.getPaymentPlan(patient_id); 
		return new ResponseEntity<List<ORMFourColumGeneric>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/getcashregister")
	public @ResponseBody ResponseEntity<List<ORMCashRegisterGet>> getCashRegister(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside getCashRegister: ");
		
		List<ORMCashRegisterGet> lst=cashRegisterService.getCashRegister(searchCriteria); 
		
		return new ResponseEntity<List<ORMCashRegisterGet>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/addCashRegisterPayment")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> addCashRegisterPayment(
			@RequestBody Wrapper_TwoObjectsSave<ORMCashRegisterAdd,ORMSavePatientNotPaidReason> objAppSaveWrapper)
	{	
		GeneralOperations.logMsg("Inside addCashRegisterPayment");				
	
		ORMCashRegisterAdd objCashRegister=objAppSaveWrapper.getOrmSave1();
		ORMSavePatientNotPaidReason objNotPaidReason=objAppSaveWrapper.getOrmSave2();
		
		ServiceResponse<ORMKeyValue> resp=cashRegisterService.addCashRegisterPayment(objCashRegister,objNotPaidReason); 		
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
		
	}
	@RequestMapping("/modifyCashRegisterPayment")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> modifyCashRegisterPayment(
			@RequestBody ORMCashRegisterModify objCashRegisterModify)
	{	
		GeneralOperations.logMsg("Inside modifyCashRegisterPayment");
		
		ServiceResponse<ORMKeyValue> resp=cashRegisterService.modifyCashRegisterPayment(objCashRegisterModify); 		
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
		
	}
	
	
	
	
	@RequestMapping("/getnotpaidreason/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMFourColumGeneric>> getNotPaidReason(
			@PathVariable(value = "patient_id") Long patient_id)
	{	
		GeneralOperations.logMsg("Inside getNotPaidReason:  patient_id="+patient_id.toString());
		
		List<ORMFourColumGeneric> lst=cashRegisterService.getNotPaidReason(patient_id); 
		
		return new ResponseEntity<List<ORMFourColumGeneric>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/getpatientrefund")
	public @ResponseBody ResponseEntity<List<ORMPatientRefundGet>> getPatientRefund(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside getPatientRefund: ");
		
		List<ORMPatientRefundGet> lst=cashRegisterService.getPatientRefund(searchCriteria); 
		
		return new ResponseEntity<List<ORMPatientRefundGet>>(lst , HttpStatus.OK);
	}
	
	@RequestMapping("/savePatientRefund")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> savePatientRefund(
			@RequestBody Wrapper_PatientRefundSave wrapperPatientRefundSave
	) {

		GeneralOperations.logMsg("Inside savePatientRefund: ");

		ServiceResponse<ORMKeyValue> serviceResponse = cashRegisterService.savePatientRefund(wrapperPatientRefundSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.OK);

	}
	
	@RequestMapping("/voidCashRegisterEntry")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> voidCashRegisterEntry(
			@RequestBody List<ORMKeyValue> lstKeyValue
	) {

		GeneralOperations.logMsg("Inside voidCashRegisterEntry");

		ServiceResponse<ORMKeyValue> serviceResponse = cashRegisterService.voidCashRegisterEntry(lstKeyValue);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.OK);

	}
	
	@RequestMapping("/checkBounceCashRegisterEntry")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> checkBounceCashRegisterEntry(
			@RequestBody List<ORMKeyValue> lstKeyValue
	) {

		GeneralOperations.logMsg("Inside checkBounceCashRegisterEntry");

		ServiceResponse<ORMKeyValue> serviceResponse = cashRegisterService.checkBounceCashRegisterEntry(lstKeyValue);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.OK);

	}
	
	@RequestMapping("/markAsResolvedCashRegisterEntry")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> markAsResolvedCashRegisterEntry(
			@RequestBody List<ORMKeyValue> lstKeyValue
	) {

		GeneralOperations.logMsg("Inside markAsResolvedCashRegisterEntry");

		ServiceResponse<ORMKeyValue> serviceResponse = cashRegisterService.markAsResolvedCashRegisterEntry(lstKeyValue);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.OK);

	}
	
	@RequestMapping("/getUnResolvedCashRegisterPayments")
	public @ResponseBody ResponseEntity<List<ORMCashRegisterUnResolvedPaymentGet>> getUnResolvedCashRegisterPayments(
			@RequestBody List<ORMKeyValue> lstKeyValue
	) {

		GeneralOperations.logMsg("Inside getUnResolvedCashRegisterPayments");

		List<ORMCashRegisterUnResolvedPaymentGet> lst = cashRegisterService.getUnResolvedCashRegisterPayments(lstKeyValue);

		return new ResponseEntity<List<ORMCashRegisterUnResolvedPaymentGet>>(lst, HttpStatus.OK);

	}
	
	
	/*
	@RequestMapping("/getpatientbalance/{patient_id}")
	public @ResponseBody ResponseEntity<String> getPatientBalance(
			@PathVariable(value = "patient_id") Long patient_id)
	{	
		GeneralOperations.logMsg("Inside getPatientBalance:  patient_id="+patient_id.toString());
		
		String patBalance=cashRegisterService.getPatientBalance(patient_id);
		
		return new ResponseEntity<String>(patBalance , HttpStatus.OK);
	}
	*/
	
	@RequestMapping("/savePaymentPlan")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> savePaymentPlan(
			@RequestBody List<ORMKeyValue> lstKeyValue)
	{	
		GeneralOperations.logMsg("Inside savePaymentPlan: ");
		
		ServiceResponse<ORMKeyValue> resp=cashRegisterService.savePaymentPlan(lstKeyValue); 
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
}
