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

import com.ihc.ehr.model.ORMClaimReminderGet;
import com.ihc.ehr.model.ORMDashBoarCashReg;
import com.ihc.ehr.model.ORMDashBoardCheckInPatient;
import com.ihc.ehr.model.ORMDashBoardLab;
import com.ihc.ehr.model.ORMDashBoardMessages;
import com.ihc.ehr.model.ORMDashBoardMissingClaims;
import com.ihc.ehr.model.ORMDashboardClaimReminder;
import com.ihc.ehr.model.ORMDashboardPendingEncounter;
import com.ihc.ehr.model.ORMFaxMyUnRead;
import com.ihc.ehr.model.ORMGetCashRegisterDayWisePayment;
import com.ihc.ehr.model.ORMGetGynTodayEdd;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMOneColumnGeneric;
import com.ihc.ehr.model.ORMPrescriptionRefills;
import com.ihc.ehr.model.ORMSevenColumnGeneric;
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.ORMgetDashBoardModule;
import com.ihc.ehr.model.ORMgetPrescriptionAllergies;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
//import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.service.DashboardService;
import com.ihc.ehr.util.GeneralOperations;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	DashboardService dashboardService;
	
	 
	@RequestMapping("/getDashBoardModule/{UserId}/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMgetDashBoardModule>> getDashBoardModule(
			@PathVariable(value="UserId")  Long UserId,
			@PathVariable(value="practiceId")  Long practiceId)
	{
		List<ORMgetDashBoardModule> obj=dashboardService.getDashBoardModule(UserId,practiceId);
		return new ResponseEntity<List<ORMgetDashBoardModule>>(obj , HttpStatus.OK);	
	}
	
	@RequestMapping("/getCheckInPatient")
	public @ResponseBody ResponseEntity<List<ORMDashBoardCheckInPatient>> getCheckInPatient(
			@RequestBody SearchCriteria searchCriteria)
	{	
		
		List<ORMDashBoardCheckInPatient> obj=dashboardService.getCheckInPatient(searchCriteria);
		
		return new ResponseEntity<List<ORMDashBoardCheckInPatient>>(obj , HttpStatus.OK);
		
	}
	@RequestMapping("/getCheckInPatient_Widget")
	public @ResponseBody ResponseEntity<List<ORMOneColumnGeneric>> getCheckInPatient_Widget(
			@RequestBody SearchCriteria searchCriteria)
	{	
		
		List<ORMOneColumnGeneric> obj=dashboardService.getCheckInPatient_Widget(searchCriteria);
		
		return new ResponseEntity<List<ORMOneColumnGeneric>>(obj , HttpStatus.OK);
		
	}
	
	@RequestMapping("/getLabPendingResults/{ProviderID}/{locationID}/{type}/{LoginUserID}")
	public @ResponseBody ResponseEntity<List<ORMDashBoardLab>> getLabPendingResults(
			@PathVariable(value="ProviderID")  Long ProviderID,
			@PathVariable(value="locationID")  Long locationID,
			@PathVariable(value="type")  String type,
			@PathVariable(value="LoginUserID")  Long LoginUserID)
	{
		List<ORMDashBoardLab> obj=dashboardService.getLabPendingResults(ProviderID,locationID,type,LoginUserID);
		return new ResponseEntity<List<ORMDashBoardLab>>(obj , HttpStatus.OK);	
	}
	@RequestMapping("/getLabPendingResults_Widget/{ProviderID}/{locationID}/{type}/{LoginUserID}")
	public @ResponseBody ResponseEntity<List<ORMOneColumnGeneric>> getLabPendingResults_Widget(
			@PathVariable(value="ProviderID")  Long ProviderID,
			@PathVariable(value="locationID")  Long locationID,
			@PathVariable(value="type")  String type,
			@PathVariable(value="LoginUserID")  Long LoginUserID)
	{
		List<ORMOneColumnGeneric> obj=dashboardService.getLabPendingResults_Widget(ProviderID,locationID,type,LoginUserID);
		return new ResponseEntity<List<ORMOneColumnGeneric>>(obj , HttpStatus.OK);	
	}
	
	
	@RequestMapping("/getPendingEncounter/{provider_id}/{location_id}")
	public @ResponseBody ResponseEntity<List<ORMDashboardPendingEncounter>> getPendingEncounter1(
		@PathVariable(value="provider_id") Long provider_id,
		@PathVariable(value="location_id") Long location_id,
		@RequestBody SearchCriteria searchCriteria){
		List<ORMDashboardPendingEncounter> obj=dashboardService.getPendingEncounter(provider_id,location_id,searchCriteria);
		return new ResponseEntity<List<ORMDashboardPendingEncounter>>(obj , HttpStatus.OK);
	}
	
	@RequestMapping("/getCashRegisterLastWeekDayWisePayment")
	public @ResponseBody ResponseEntity<List<ORMGetCashRegisterDayWisePayment>> getCashRegisterLastWeekDayWisePayment(
			@RequestBody SearchCriteria searchCriteria)
	{
		List<ORMGetCashRegisterDayWisePayment> obj=dashboardService.getCashRegisterLastWeekDayWisePayment(searchCriteria);
		return new ResponseEntity<List<ORMGetCashRegisterDayWisePayment>>(obj , HttpStatus.OK);
	}
	@RequestMapping("/getCashRegister_Widget")
	public @ResponseBody ResponseEntity<List<ORMOneColumnGeneric>> getCashRegister_Widget(
			@RequestBody SearchCriteria searchCriteria)
	{
		List<ORMOneColumnGeneric> obj=dashboardService.getCashRegister_Widget(searchCriteria);
		return new ResponseEntity<List<ORMOneColumnGeneric>>(obj , HttpStatus.OK);
	}
	//cash register details	
	@RequestMapping("/getCashPaymentDetails")
	public @ResponseBody List<ORMDashBoarCashReg> getCashPaymentDetails(@RequestBody SearchCriteria searchCriteria){
		List<ORMDashBoarCashReg> obj = dashboardService.getCashPaymentDetails(searchCriteria);
		return obj;
	}
	//unread msg
	@RequestMapping("/getUnReadMessages/{ReciverID}")
	public @ResponseBody ResponseEntity<List<ORMDashBoardMessages>> getUnReadMessages(
			@PathVariable(value="ReciverID")  Long ReciverID)
	{
		List<ORMDashBoardMessages> obj=dashboardService.getUnReadMessages(ReciverID);
		return new ResponseEntity<List<ORMDashBoardMessages>>(obj , HttpStatus.OK);
	}
	//mesages
	
	@RequestMapping("/markAsRead/{id}/{loginuser}/{datetime}/{ip}")
	public @ResponseBody ResponseEntity<Integer> markAsRead(
		@PathVariable(value="id") String id,
		@PathVariable(value="loginuser") String loginuser,
		@PathVariable(value="datetime") String datetime,
		@PathVariable(value="ip") String ip
		){
		int obj=dashboardService.markAsRead(id,loginuser,datetime,ip);
		return new ResponseEntity<Integer>(obj , HttpStatus.OK);
	}
	//unread faxes
	
	@RequestMapping("/getUnReadFaxes/{userID}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMFaxMyUnRead>> getUnReadFaxes(
			@PathVariable(value="userID")  String userID,
			@PathVariable(value="practice_id")  String practice_id){
		List<ORMFaxMyUnRead> obj=dashboardService.getUnReadFaxes(userID,practice_id);
		return new ResponseEntity<List<ORMFaxMyUnRead>>(obj , HttpStatus.OK);
	}
	@RequestMapping("/getUnReadFaxes_Widget/{userID}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMOneColumnGeneric>> getUnReadFaxes_Widget(
			@PathVariable(value="userID")  String userID,
			@PathVariable(value="practice_id")  String practice_id){
		List<ORMOneColumnGeneric> obj=dashboardService.getUnReadFaxes_Widget(userID,practice_id);
		return new ResponseEntity<List<ORMOneColumnGeneric>>(obj , HttpStatus.OK);
	}
	
	//
	@RequestMapping("/getgynEDD/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetGynTodayEdd>> getUnReadFaxes(
			@PathVariable(value="practice_id")  String practice_id){
		List<ORMGetGynTodayEdd> obj=dashboardService.getgynEDD(practice_id);
		return new ResponseEntity<List<ORMGetGynTodayEdd>>(obj , HttpStatus.OK);
	}
	
	@RequestMapping("/getMissingClaims/")
	public @ResponseBody ResponseEntity<List<ORMDashBoardMissingClaims>> getMissingClaims(
		@RequestBody SearchCriteria searchCriteria){
		List<ORMDashBoardMissingClaims> obj=dashboardService.getMissingClaims(searchCriteria);
		System.out.println("total size of result are: " + obj.size());
		return new ResponseEntity<List<ORMDashBoardMissingClaims>>(obj , HttpStatus.OK);
	}
	
	@RequestMapping("/getPendingClaims_Widget/")
	public @ResponseBody ResponseEntity<List<ORMOneColumnGeneric>> getPendingClaims_Widget(
		@RequestBody SearchCriteria searchCriteria){
		List<ORMOneColumnGeneric> obj=dashboardService.getPendingClaims_Widget(searchCriteria);
		System.out.println("total size of result are: " + obj.size());
		return new ResponseEntity<List<ORMOneColumnGeneric>>(obj , HttpStatus.OK);
	}
	
	@RequestMapping("/getRefills")
	public @ResponseBody ResponseEntity<List<ORMPrescriptionRefills>> getPrescriptionXml(
			@RequestBody ORMgetPrescriptionAllergies getRefills) {
		List<ORMPrescriptionRefills> lst = dashboardService.getRefills(getRefills);
		return new ResponseEntity<List<ORMPrescriptionRefills>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/updateOrderAssignedTo")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> updateOrderAssignedTo(
			@RequestBody SearchCriteria searchCriteria)
	{	
		ServiceResponse<ORMKeyValue> resp = dashboardService.updateOrderAssignedTo(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping("/faxMarkasRead")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> faxMarkasRead(
		@RequestBody SearchCriteria searchCriteria){
		ServiceResponse<ORMKeyValue> resp = dashboardService.faxMarkasRead(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
	}
	@RequestMapping("/getPaymentSummary/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMSevenColumnGeneric>> getPaymentSummary(
			@PathVariable(value="practice_id")  String practice_id){
		List<ORMSevenColumnGeneric> obj=dashboardService.getPaymentSummary(practice_id);
		return new ResponseEntity<List<ORMSevenColumnGeneric>>(obj , HttpStatus.OK);
	}
	@RequestMapping("/getDashBoardDenial/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMThreeColumGeneric>> getDashBoardDenial(
			@PathVariable(value="practice_id")  String practice_id){
		List<ORMThreeColumGeneric> obj=dashboardService.getDashBoardDenial(practice_id);
		return new ResponseEntity<List<ORMThreeColumGeneric>>(obj , HttpStatus.OK);
	}
	@RequestMapping("/getDashBoardClaimAging")
	public @ResponseBody ResponseEntity<List<ORMThreeColumGeneric>> getDashBoardClaimAging(
			@RequestBody SearchCriteria searchCriteria){
		List<ORMThreeColumGeneric> obj=dashboardService.getDashBoardClaimAging(searchCriteria);
		return new ResponseEntity<List<ORMThreeColumGeneric>>(obj , HttpStatus.OK);
	}
	@RequestMapping("/getDashBoardClaimCount")
	public @ResponseBody ResponseEntity<List<ORMThreeColumGeneric>> getDashBoardClaimCount(
			@RequestBody SearchCriteria searchCriteria)
	{	
		List<ORMThreeColumGeneric> obj = dashboardService.getDashBoardClaimCount(searchCriteria);
		return new ResponseEntity<List<ORMThreeColumGeneric>>(obj , HttpStatus.OK);
	}
	
	
	@RequestMapping("/getDashboardClaimReminders")
	public @ResponseBody ResponseEntity<List<ORMDashboardClaimReminder>>  getDashboardClaimReminders(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside resolveClaimReminder: ");

		List<ORMDashboardClaimReminder> lst = dashboardService.getDashboardClaimReminders(searchCriteria);
		return new ResponseEntity<List<ORMDashboardClaimReminder>>(lst, HttpStatus.OK);
	}
	
}
