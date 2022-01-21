package com.ihc.ehr.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.ihc.ehr.model.ORMACUReport;
import com.ihc.ehr.model.ORMAppointmentSMSReport;
import com.ihc.ehr.model.ORMBillingTrackSheet;
import com.ihc.ehr.model.ORMCPTSummaryReport;
import com.ihc.ehr.model.ORMClaimBatchReport;
import com.ihc.ehr.model.ORMClaimReceiptDetailGet;
import com.ihc.ehr.model.ORMClaimwithUnsignedEncounter;
import com.ihc.ehr.model.ORMCrossRpt;
import com.ihc.ehr.model.ORMCssCallLog;
import com.ihc.ehr.model.ORMDailyDepositReports;
import com.ihc.ehr.model.ORMDailyDeposits;
import com.ihc.ehr.model.ORMDashBoarCashReg;
import com.ihc.ehr.model.ORMDashBoardClaim;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMDenialMessagesGet;
import com.ihc.ehr.model.ORMEncounterCountDayWise;
import com.ihc.ehr.model.ORMEncounterCountMonthWise;
import com.ihc.ehr.model.ORMEncounterIcdsCpts;
import com.ihc.ehr.model.ORMGETrptInsVerification;
import com.ihc.ehr.model.ORMGYNMissingPatient;
import com.ihc.ehr.model.ORMGetAppointmentReport;
import com.ihc.ehr.model.ORMGetCashRegisterReport;
import com.ihc.ehr.model.ORMGetCheckPostedPayments;
import com.ihc.ehr.model.ORMGetClaimReportFooter;
import com.ihc.ehr.model.ORMGetClaimSummaryReport;
import com.ihc.ehr.model.ORMGetClaimsReport;
import com.ihc.ehr.model.ORMGetCollectPayment;
import com.ihc.ehr.model.ORMGetCorrespondenceReport;
import com.ihc.ehr.model.ORMGetDailyDepositReports;
import com.ihc.ehr.model.ORMGetDepositReportChecksSummary;
import com.ihc.ehr.model.ORMGetDiagnosticReport;
import com.ihc.ehr.model.ORMGetEncounterReport;
import com.ihc.ehr.model.ORMGetGeneralRptOptions;
import com.ihc.ehr.model.ORMGetInvoiceReport;
import com.ihc.ehr.model.ORMGetPatientPayment;
import com.ihc.ehr.model.ORMGetPayRollCategoryProcedures;
import com.ihc.ehr.model.ORMGetPayRollProviders;
import com.ihc.ehr.model.ORMGetPayerWisePaymentDetailsReport;
import com.ihc.ehr.model.ORMGetPayerWisePaymentReport;
import com.ihc.ehr.model.ORMGetPaymentCollectionSummaryReport;
import com.ihc.ehr.model.ORMGetPayrollCharges;
import com.ihc.ehr.model.ORMGetPayrollPayment;
import com.ihc.ehr.model.ORMGetPayrollVisitsDetails;
import com.ihc.ehr.model.ORMGetProviderParollReport;
import com.ihc.ehr.model.ORMGetProviderWiseMonthlyPaymentCollection;
import com.ihc.ehr.model.ORMGetUserAdministrationModules;
import com.ihc.ehr.model.ORMIdNameCount;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMLabCompleted;
import com.ihc.ehr.model.ORMNineColumnGeneric;
import com.ihc.ehr.model.ORMOBGynReport;
import com.ihc.ehr.model.ORMOrderCrossReport;
import com.ihc.ehr.model.ORMPatEligReport;
import com.ihc.ehr.model.ORMPatientCreationRpt;
import com.ihc.ehr.model.ORMPatientNotPaidReasons;
import com.ihc.ehr.model.ORMPatientNotSeen;
import com.ihc.ehr.model.ORMPatientPayer;
import com.ihc.ehr.model.ORMPatientPayerDetails;
import com.ihc.ehr.model.ORMPayerWiseAgingSummary;
import com.ihc.ehr.model.ORMPayrollCategories;
import com.ihc.ehr.model.ORMPayrollCategoryProcedures;
import com.ihc.ehr.model.ORMProWorkReport;
import com.ihc.ehr.model.ORMProviderPayrollCategories;
import com.ihc.ehr.model.ORMRptDailyChargesPaymentSummary;
import com.ihc.ehr.model.ORMRptDailyPaymentSummary;
import com.ihc.ehr.model.ORMRptMonthWiseChargesPayment;
import com.ihc.ehr.model.ORMSptWisePaymentReport;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORMgetMissingClaimReport;
import com.ihc.ehr.model.ORMgetPaymentCategoriesReport;
import com.ihc.ehr.model.ORMgetYearlyCashRegisterColReport;
import com.ihc.ehr.model.ORMpaymentcategories;
import com.ihc.ehr.model.ORMpaymentcategoryprocedures;
import com.ihc.ehr.model.ORMrptHCFAClaimsGet;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.WrapperLog;
import com.ihc.ehr.model.rptGetUserPerformance;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.service.ReportsService;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	ReportsService reportsService;
	@Autowired
	GeneralService generalService;

	@RequestMapping("/getappointments")
	public @ResponseBody ResponseEntity<List<ORMGetAppointmentReport>> getAppointments(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getCheckInOutInfo");

		List<ORMGetAppointmentReport> lst = reportsService.getAppointments(searchCriteria);

		return new ResponseEntity<List<ORMGetAppointmentReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getEligibilityVerification")
	public @ResponseBody ResponseEntity<List<ORMGETrptInsVerification>> getEligibilityVerification(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("GET Eligibility Verification");
		List<ORMGETrptInsVerification> lst = reportsService.getEligibilityVerification(searchCriteria);
		return new ResponseEntity<List<ORMGETrptInsVerification>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getcssCallLog")
	public @ResponseBody ResponseEntity<List<ORMCssCallLog>> getcssCallLog(@RequestBody SearchCriteria searchCriteria) {
		List<ORMCssCallLog> lst = reportsService.getcssCallLog(searchCriteria);
		return new ResponseEntity<List<ORMCssCallLog>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getEncounterProcDiag")
	public @ResponseBody ResponseEntity<List<ORMEncounterIcdsCpts>> getEncounterProcDiag(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMEncounterIcdsCpts> lst = reportsService.getEncounterProcDiag(searchCriteria);
		return new ResponseEntity<List<ORMEncounterIcdsCpts>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getEncounterReport")
	public @ResponseBody ResponseEntity<List<ORMGetEncounterReport>> getEncounterReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetEncounterReport> lst = reportsService.getEncounterReport(searchCriteria);
		return new ResponseEntity<List<ORMGetEncounterReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getClaimwithUnsignedEncounter")
	public @ResponseBody ResponseEntity<List<ORMClaimwithUnsignedEncounter>> getClaimwithUnsignedEncounter(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMClaimwithUnsignedEncounter> lst = reportsService.getClaimwithUnsignedEncounter(searchCriteria);
		return new ResponseEntity<List<ORMClaimwithUnsignedEncounter>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getMissingClaims")
	public @ResponseBody ResponseEntity<List<ORMDashBoardClaim>> getMissingClaims(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMDashBoardClaim> lst = reportsService.getMissingClaims(searchCriteria);
		return new ResponseEntity<List<ORMDashBoardClaim>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getMissingClaimsReport")
	public @ResponseBody ResponseEntity<List<ORMgetMissingClaimReport>> getMissingClaimsReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMgetMissingClaimReport> lst = reportsService.getMissingClaimsReport(searchCriteria);
		return new ResponseEntity<List<ORMgetMissingClaimReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getOrderCross_False")
	public @ResponseBody ResponseEntity<List<ORMOrderCrossReport>> getOrderCross_False(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMOrderCrossReport> lst = reportsService.getOrderCross_False(searchCriteria);
		return new ResponseEntity<List<ORMOrderCrossReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getOrderCross_True")
	public @ResponseBody ResponseEntity<List<ORMOrderCrossReport>> getOrderCross_True(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMOrderCrossReport> lst = reportsService.getOrderCross_True(searchCriteria);
		return new ResponseEntity<List<ORMOrderCrossReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getDiagnReport")
	public @ResponseBody ResponseEntity<List<ORMGetDiagnosticReport>> getDiagnReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetDiagnosticReport> lst = reportsService.getDiagnReport(searchCriteria);
		return new ResponseEntity<List<ORMGetDiagnosticReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getCallsData")
	public @ResponseBody ResponseEntity<List<ORMACUReport>> getCallsData(@RequestBody SearchCriteria searchCriteria) {
		List<ORMACUReport> lst = reportsService.getCallsData(searchCriteria);
		return new ResponseEntity<List<ORMACUReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getAppointmentsms")
	public @ResponseBody ResponseEntity<List<ORMAppointmentSMSReport>> getAppointmentsms(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMAppointmentSMSReport> lst = reportsService.getAppointmentsms(searchCriteria);
		return new ResponseEntity<List<ORMAppointmentSMSReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getgynMissedVisit/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGYNMissingPatient>> getgynMissedVisit(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMGYNMissingPatient> obj = reportsService.getgynMissedVisit(practice_id);

		return new ResponseEntity<List<ORMGYNMissingPatient>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getPregnancyData")
	public @ResponseBody ResponseEntity<List<ORMOBGynReport>> getPregnancyData(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMOBGynReport> lst = reportsService.getPregnancyData(searchCriteria);
		return new ResponseEntity<List<ORMOBGynReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatEligReport")
	public @ResponseBody ResponseEntity<List<ORMPatEligReport>> getPatEligReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMPatEligReport> lst = reportsService.getPatEligReport(searchCriteria);
		return new ResponseEntity<List<ORMPatEligReport>>(lst, HttpStatus.OK);
	}

	/*
	 * @RequestMapping("/getPatientEligibility") public
	 * ResponseEntity<ServiceResponse<ORMKeyValue>> getPatientEligibility(
	 * 
	 * @RequestBody ORMEligibilityDetail obj) {
	 * System.out.println("getPatientEligibility: "+obj.toString()); String
	 * res=reportsService.getPatientEligibility(obj);
	 * 
	 * 
	 * ServiceResponse<ORMKeyValue> serviceResponse =new ServiceResponse<>();
	 * serviceResponse.setResult(res);
	 * 
	 * return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse,
	 * HttpStatus.OK);
	 * 
	 * }
	 */
	@RequestMapping("/getDayWiseRpt")
	public @ResponseBody ResponseEntity<List<ORMEncounterCountDayWise>> getDayWiseRpt(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMEncounterCountDayWise> lst = reportsService.getDayWiseRpt(searchCriteria);
		return new ResponseEntity<List<ORMEncounterCountDayWise>>(lst, HttpStatus.OK);
	}

	//
	@RequestMapping("/getProviderWiseRpt")
	public @ResponseBody ResponseEntity<List<ORMEncounterCountMonthWise>> getProviderWiseRpt(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMEncounterCountMonthWise> lst = reportsService.getProviderWiseRpt(searchCriteria);
		return new ResponseEntity<List<ORMEncounterCountMonthWise>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getLocationWiseRpt")
	public @ResponseBody ResponseEntity<List<ORMEncounterCountMonthWise>> getLocationWiseRpt(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMEncounterCountMonthWise> lst = reportsService.getLocationWiseRpt(searchCriteria);
		return new ResponseEntity<List<ORMEncounterCountMonthWise>>(lst, HttpStatus.OK);
	}
	// @RequestMapping("/saveupdateDailyDeposit")
	// public ResponseEntity<ORMDailyDeposits> saveupdateDailyDeposit(
	// @RequestBody ORMDailyDeposits obj) {
	// reportsService.saveupdateDailyDeposit(obj);
	// return new ResponseEntity<ORMDailyDeposits>(obj, HttpStatus.OK);
	// }
	/*
	 * @RequestMapping(value = "/saveupdateDailyDeposit",headers =
	 * ("content-type=multipart/*"), method = RequestMethod.POST) public
	 * ResponseEntity<ServiceResponse> saveupdateDailyDeposit(
	 * 
	 * @RequestParam("docFile") MultipartFile docFile,
	 * 
	 * @RequestParam("docData") String docData,
	 * 
	 * @RequestParam("docCategory") String docCategory) throws JsonParseException,
	 * JsonMappingException, IOException {
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); ORMDailyDeposits patDoc =
	 * mapper.readValue(docData, ORMDailyDeposits.class);
	 * System.out.println("Document Category:"+docCategory);
	 * docCategory="PatientDocuments"; Long result =
	 * reportsService.saveupdateDailyDeposit(patDoc,docFile,docCategory);
	 * ServiceResponse resp=new ServiceResponse(); if(result>0) {
	 * resp.setStatus(ServiceResponseStatus.SUCCESS);
	 * resp.setResult(result.toString()); } else {
	 * resp.setStatus(ServiceResponseStatus.ERROR); } return new
	 * ResponseEntity<ServiceResponse>(resp, HttpStatus.OK); }
	 */

	@RequestMapping(value = "/saveupdateDailyDeposit", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<?> saveupdateDailyDeposit(@RequestParam("docFile") MultipartFile docFile,
			@RequestParam("docData") String docData, @RequestParam("docCategory") String docCategory)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		ORMDailyDeposits patDoc = mapper.readValue(docData.toString(), ORMDailyDeposits.class);

		System.out.println("Document Category:" + docCategory + "Document pat file:" + docFile);
		docCategory = "EOB";

		ServiceResponse<ORMKeyValue> serviceResponse = reportsService.saveupdateDailyDeposit(patDoc, docFile,
				docCategory);

		if (serviceResponse.getStatus() == ServiceResponseStatus.SUCCESS) {
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/getDailyDeposit")
	public @ResponseBody ResponseEntity<List<ORMGetDailyDepositReports>> getDailyDeposit(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetDailyDepositReports> lst = reportsService.getDailyDeposit(searchCriteria);
		return new ResponseEntity<List<ORMGetDailyDepositReports>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/deleteDailyDeposit")
	public int deleteProblem(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("Delete Daily Deposit ID: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("dailydeposit");
		ormDeleteRecord.setColumn_name("dailydeposit_id");
		return reportsService.deleteDailyDeposit(ormDeleteRecord);
	}

	@RequestMapping("/searchByMonthYear")
	public @ResponseBody ResponseEntity<List<ORMDailyDepositReports>> searchByMonthYear(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMDailyDepositReports> lst = reportsService.searchByMonthYear(searchCriteria);
		return new ResponseEntity<List<ORMDailyDepositReports>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatientPayer")
	public @ResponseBody ResponseEntity<List<ORMPatientPayer>> getPatientPayer(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMPatientPayer> lst = reportsService.getPatientPayer(searchCriteria);
		return new ResponseEntity<List<ORMPatientPayer>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatientPayerDetails")
	public @ResponseBody ResponseEntity<List<ORMPatientPayerDetails>> getPatientPayerDetails(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMPatientPayerDetails> lst = reportsService.getPatientPayerDetails(searchCriteria);
		return new ResponseEntity<List<ORMPatientPayerDetails>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getCashPaymentDetails")
	public @ResponseBody ResponseEntity<List<ORMDashBoarCashReg>> getCashPaymentDetails(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMDashBoarCashReg> lst = reportsService.getCashPaymentDetails(searchCriteria);
		return new ResponseEntity<List<ORMDashBoarCashReg>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatientPaymentDetails")
	public @ResponseBody ResponseEntity<List<ORMGetPatientPayment>> getPatientPaymentDetails(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPatientPayment> lst = reportsService.getPatientPaymentDetails(searchCriteria);
		return new ResponseEntity<List<ORMGetPatientPayment>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getCollectPaymentDetails")
	public @ResponseBody ResponseEntity<List<ORMGetCollectPayment>> getCollectPaymentDetails(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetCollectPayment> lst = reportsService.getCollectPaymentDetails(searchCriteria);
		return new ResponseEntity<List<ORMGetCollectPayment>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getYearlyCollectionDetails")
	public @ResponseBody ResponseEntity<List<ORMgetYearlyCashRegisterColReport>> getYearlyCollectionDetails(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMgetYearlyCashRegisterColReport> lst = reportsService.getYearlyCollectionDetails(searchCriteria);
		return new ResponseEntity<List<ORMgetYearlyCashRegisterColReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getClaimdetails")
	public @ResponseBody ResponseEntity<List<ORMGetClaimsReport>> getClaimdetails(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetClaimsReport> lst = reportsService.getClaimdetails(searchCriteria);
		return new ResponseEntity<List<ORMGetClaimsReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getReportClaimdetailFooter")
	public @ResponseBody ResponseEntity<List<ORMGetClaimReportFooter>> getReportClaimdetailFooter(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetClaimReportFooter> lst = reportsService.getReportClaimdetailFooter(searchCriteria);
		return new ResponseEntity<List<ORMGetClaimReportFooter>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPaymentCategoriesDetails")
	public @ResponseBody ResponseEntity<List<ORMgetPaymentCategoriesReport>> getPaymentCategoriesDetails(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMgetPaymentCategoriesReport> lst = reportsService.getPaymentCategoriesDetails(searchCriteria);
		return new ResponseEntity<List<ORMgetPaymentCategoriesReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getPaymentCategories/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMpaymentcategories>> getPaymentCategories(
			@PathVariable(value = "practiceId") String practiceId) {
		List<ORMpaymentcategories> lst = reportsService.getPaymentCategories(practiceId);
		return new ResponseEntity<List<ORMpaymentcategories>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getPaymentcategories_Procedures/{category_id}")
	public @ResponseBody ResponseEntity<List<ORMpaymentcategoryprocedures>> getPaymentcategories_Procedures(
			@PathVariable(value = "category_id") String category_id) {
		List<ORMpaymentcategoryprocedures> lst = reportsService.getPaymentcategories_Procedures(category_id);
		return new ResponseEntity<List<ORMpaymentcategoryprocedures>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveupdatePaymentCategory")
	public ResponseEntity<ORMpaymentcategories> saveupdatePaymentCategory(@RequestBody ORMpaymentcategories obj) {
		reportsService.saveupdatePaymentCategory(obj);
		return new ResponseEntity<ORMpaymentcategories>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getCrossReport")
	public @ResponseBody ResponseEntity<List<ORMCrossRpt>> getCrossReport(@RequestBody SearchCriteria searchCriteria) {
		List<ORMCrossRpt> lst = reportsService.getCrossReport(searchCriteria);
		return new ResponseEntity<List<ORMCrossRpt>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getLabCompRpt")
	public @ResponseBody ResponseEntity<List<ORMLabCompleted>> getLabCompRpt(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMLabCompleted> lst = reportsService.getLabCompRpt(searchCriteria);
		return new ResponseEntity<List<ORMLabCompleted>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/deleteCategory")
	public int deleteCategory(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("Delete Category ID: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("payment_categories");
		ormDeleteRecord.setColumn_name("category_id");
		return reportsService.deleteCategory(ormDeleteRecord);
	}

	@RequestMapping("/deleteProcedure")
	public int deleteProcedure(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("Delete Category ID: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("payment_category_procedures");
		ormDeleteRecord.setColumn_name("category_procedure_id");
		return reportsService.deleteProcedure(ormDeleteRecord);
	}

	@RequestMapping("/saveProcedures")
	public ResponseEntity<List<ORMpaymentcategoryprocedures>> saveProcedures(
			@RequestBody List<ORMpaymentcategoryprocedures> obj) {
		reportsService.saveProcedures(obj);
		return new ResponseEntity<List<ORMpaymentcategoryprocedures>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getPatCreation")
	public @ResponseBody ResponseEntity<List<ORMPatientCreationRpt>> getPatCreation(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMPatientCreationRpt> lst = reportsService.getPatCreation(searchCriteria);
		return new ResponseEntity<List<ORMPatientCreationRpt>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getusermodules/{role_id}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetUserAdministrationModules>> getusermodules(
			@PathVariable(value = "role_id") String role_id, @PathVariable(value = "practice_id") String practice_id) {
		List<ORMGetUserAdministrationModules> obj = reportsService.getusermodules(role_id, practice_id);
		return new ResponseEntity<List<ORMGetUserAdministrationModules>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/searchPayRoll")
	public @ResponseBody ResponseEntity<List<ORMGetProviderParollReport>> searchPayRoll(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetProviderParollReport> lst = reportsService.searchPayRoll(searchCriteria);
		return new ResponseEntity<List<ORMGetProviderParollReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/GetPayrollCategories/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMPayrollCategories>> GetPayrollCategories(
			@PathVariable(value = "practiceId") Long practiceId) {
		List<ORMPayrollCategories> obj = reportsService.GetPayrollCategories(practiceId);
		return new ResponseEntity<List<ORMPayrollCategories>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getPayRollCategoryProviders/{category_id}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPayRollProviders>> getPayRollCategoryProviders(
			@PathVariable(value = "category_id") String category_id,
			@PathVariable(value = "practice_id") String practice_id) {
		List<ORMGetPayRollProviders> obj = reportsService.getPayRollCategoryProviders(category_id, practice_id);
		return new ResponseEntity<List<ORMGetPayRollProviders>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getPayRollCategoryProcedures/{category_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPayRollCategoryProcedures>> getPayRollCategoryProcedures(
			@PathVariable(value = "category_id") String category_id) {
		List<ORMGetPayRollCategoryProcedures> obj = reportsService.getPayRollCategoryProcedures(category_id);
		return new ResponseEntity<List<ORMGetPayRollCategoryProcedures>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/saveupdateCategories")
	public ResponseEntity<ORMPayrollCategories> saveupdateCategories(@RequestBody ORMPayrollCategories obj) {
		reportsService.saveupdateCategories(obj);
		return new ResponseEntity<ORMPayrollCategories>(obj, HttpStatus.OK);
	}

	@RequestMapping("/deleteSeletedCategory")
	public int deleteSelectedHeader(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("Delete Payroll Category: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("payroll_categories");
		ormDeleteRecord.setColumn_name("category_id");
		return reportsService.deleteSeletedCategory(ormDeleteRecord);
	}

	@RequestMapping("/getProviderPayrollVisits")
	public @ResponseBody ResponseEntity<List<ORMGetPayrollVisitsDetails>> getProviderPayrollVisits(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPayrollVisitsDetails> lst = reportsService.getProviderPayrollVisits(searchCriteria);
		return new ResponseEntity<List<ORMGetPayrollVisitsDetails>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getProviderPayrollCharges")
	public @ResponseBody ResponseEntity<List<ORMGetPayrollCharges>> getProviderPayrollCharges(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPayrollCharges> lst = reportsService.getProviderPayrollCharges(searchCriteria);
		return new ResponseEntity<List<ORMGetPayrollCharges>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getProviderPayrollPayments")
	public @ResponseBody ResponseEntity<List<ORMGetPayrollPayment>> getProviderPayrollPayments(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPayrollPayment> lst = reportsService.getProviderPayrollPayments(searchCriteria);
		return new ResponseEntity<List<ORMGetPayrollPayment>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/SavePayRollCategoryProcedures")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> SavePayRollCategoryProcedures(
			@RequestBody List<ORMPayrollCategoryProcedures> objDetail) {
		Long result = reportsService.SavePayRollCategoryProcedures(objDetail);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving PayRoll report Procedures.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deleteSeletedProcedures")
	public int deleteSeletedProcedures(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteSeletedProcedures: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("payroll_category_procedures");
		ormDeleteRecord.setColumn_name("category_procedure_id");
		return generalService.deleteRecord(ormDeleteRecord);
	}

	@RequestMapping("/SavePayRollCategoryProviders")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> SavePayRollCategoryProviders(
			@RequestBody List<ORMProviderPayrollCategories> objDetail) {
		Long result = reportsService.SavePayRollCategoryProviders(objDetail);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving PayRoll report Providers.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/CheckIfCPTExists")
	public int CheckIfCPTExists(@RequestBody SearchCriteria searchCriteria) {
		return reportsService.CheckIfCPTExists(searchCriteria);
	}
	/*
	 * @RequestMapping("/CheckIfCPTExists") public int CheckIfCPTExists(@RequestBody
	 * SearchCriteria searchCriteria) { String Query =
	 * " select count(proc_code) as scalar_value " +
	 * " from payroll_category_procedures pcp " +
	 * " inner join payroll_categories pc on pc.category_id=pcp.category_id " +
	 * " where ISNULL(pcp.deleted,0)<>1 and ISNULL(pc.deleted,0)<>1 " +
	 * " and proc_code='"+
	 * searchCriteria.proc_code+"' and pc.practice_id='"+practice_id+"' " ;
	 * 
	 * int count = Integer.parseInt(db.getQuerySingleResult(qry));
	 * 
	 * // return count > 0; if (count > 0) {
	 * //ormDeleteRecord.setTable_name("payroll_category_procedures");
	 * //ormDeleteRecord.setColumn_name("category_procedure_id"); //return
	 * generalService.deleteRecord(ormDeleteRecord); }
	 */

	@RequestMapping("/getPaymentCollectionSummaryProviderWise")
	public @ResponseBody ResponseEntity<List<ORMGetProviderWiseMonthlyPaymentCollection>> getPaymentCollectionSummaryProviderWise(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetProviderWiseMonthlyPaymentCollection> lst = reportsService
				.getPaymentCollectionSummaryProviderWise(searchCriteria);
		return new ResponseEntity<List<ORMGetProviderWiseMonthlyPaymentCollection>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getDenialMessageRptPayers")
	public @ResponseBody ResponseEntity<List<ORMIdNameCount>> getDenialMessageRptPayers(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getDenialMessageRptPayers");

		List<ORMIdNameCount> lst = reportsService.getDenialMessageRptPayers(searchCriteria);

		return new ResponseEntity<List<ORMIdNameCount>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getDenialMessages")
	public @ResponseBody ResponseEntity<List<ORMDenialMessagesGet>> getDenialMessages(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getDenialMessages");

		List<ORMDenialMessagesGet> lst = reportsService.getDenialMessages(searchCriteria);

		return new ResponseEntity<List<ORMDenialMessagesGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getSourceWiseCollection")
	public @ResponseBody ResponseEntity<List<ORMGetPaymentCollectionSummaryReport>> getSourceWiseCollection(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getDenialMessages");

		List<ORMGetPaymentCollectionSummaryReport> lst = reportsService.getSourceWiseCollection(searchCriteria);

		return new ResponseEntity<List<ORMGetPaymentCollectionSummaryReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getBillingTrackSheetFreez")
	public @ResponseBody ResponseEntity<List<ORMBillingTrackSheet>> getBillingTrackSheetFreez(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMBillingTrackSheet> lst = reportsService.getBillingTrackSheetFreez(searchCriteria);

		return new ResponseEntity<List<ORMBillingTrackSheet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getBillingAgingPayerWiseSummary")
	public @ResponseBody ResponseEntity<List<ORMPayerWiseAgingSummary>> getBillingAgingPayerWiseSummary(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMPayerWiseAgingSummary> lst = reportsService.getBillingAgingPayerWiseSummary(searchCriteria);

		return new ResponseEntity<List<ORMPayerWiseAgingSummary>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getBankDepositReportCheckSummary")
	public @ResponseBody ResponseEntity<List<ORMGetDepositReportChecksSummary>> getBankDepositReportCheckSummary(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetDepositReportChecksSummary> lst = reportsService.getBankDepositReportCheckSummary(searchCriteria);

		return new ResponseEntity<List<ORMGetDepositReportChecksSummary>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getCheckPostedPayments")
	public @ResponseBody ResponseEntity<List<ORMGetCheckPostedPayments>> getCheckPostedPayments(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetCheckPostedPayments> lst = reportsService.getCheckPostedPayments(searchCriteria);

		return new ResponseEntity<List<ORMGetCheckPostedPayments>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getClaimBatchReport")
	public @ResponseBody ResponseEntity<List<ORMClaimBatchReport>> getClaimBatchReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMClaimBatchReport> lst = reportsService.getClaimBatchReport(searchCriteria);

		return new ResponseEntity<List<ORMClaimBatchReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getCPTWisePaymentReport")
	public @ResponseBody ResponseEntity<List<ORMSptWisePaymentReport>> getCPTWisePaymentReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMSptWisePaymentReport> lst = reportsService.getCPTWisePaymentReport(searchCriteria);

		return new ResponseEntity<List<ORMSptWisePaymentReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPayerWisePayment")
	public @ResponseBody ResponseEntity<List<ORMGetPayerWisePaymentReport>> getPayerWisePayment(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPayerWisePaymentReport> lst = reportsService.getPayerWisePayment(searchCriteria);

		return new ResponseEntity<List<ORMGetPayerWisePaymentReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPayerWisePaymentDetails")
	public @ResponseBody ResponseEntity<List<ORMGetPayerWisePaymentDetailsReport>> getPayerWisePaymentDetails(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetPayerWisePaymentDetailsReport> lst = reportsService.getPayerWisePaymentDetails(searchCriteria);

		return new ResponseEntity<List<ORMGetPayerWisePaymentDetailsReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getClaimSummaryReport")
	public @ResponseBody ResponseEntity<List<ORMGetClaimSummaryReport>> getClaimSummaryReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetClaimSummaryReport> lst = reportsService.getClaimSummaryReport(searchCriteria);

		return new ResponseEntity<List<ORMGetClaimSummaryReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getCashRegisterReport")
	public @ResponseBody ResponseEntity<List<ORMGetCashRegisterReport>> getCashRegisterReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetCashRegisterReport> lst = reportsService.getCashRegisterReport(searchCriteria);

		return new ResponseEntity<List<ORMGetCashRegisterReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getInvoiceReport")
	public @ResponseBody ResponseEntity<List<ORMGetInvoiceReport>> getInvoiceReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetInvoiceReport> lst = reportsService.getInvoiceReport(searchCriteria);

		return new ResponseEntity<List<ORMGetInvoiceReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getUserPerformanceReport")
	public @ResponseBody ResponseEntity<List<rptGetUserPerformance>> getUserPerformanceReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<rptGetUserPerformance> lst = reportsService.getUserPerformanceReport(searchCriteria);

		return new ResponseEntity<List<rptGetUserPerformance>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPaymentCollectionSummaryLocationWise")
	public @ResponseBody ResponseEntity<List<ORMNineColumnGeneric>> getPaymentCollectionSummaryLocationWise(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMNineColumnGeneric> lst = reportsService.getPaymentCollectionSummaryLocationWise(searchCriteria);

		return new ResponseEntity<List<ORMNineColumnGeneric>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getProcedureSummaryReport")
	public @ResponseBody ResponseEntity<List<ORMCPTSummaryReport>> getProcedureSummaryReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMCPTSummaryReport> lst = reportsService.getProcedureSummaryReport(searchCriteria);
		return new ResponseEntity<List<ORMCPTSummaryReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getProviderWork")
	public @ResponseBody ResponseEntity<List<ORMProWorkReport>> getProviderWork(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMProWorkReport> lst = reportsService.getProviderWork(searchCriteria);
		return new ResponseEntity<List<ORMProWorkReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getAuthorizationUsers/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> getAuthorizationUsers(
			@PathVariable(value = "practiceId") String practiceId) {
		List<ORMTwoColumnGeneric> lst = reportsService.getAuthorizationUsers(practiceId);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getNotPaidReasonData")
	public @ResponseBody ResponseEntity<List<ORMPatientNotPaidReasons>> getNotPaidReasonData(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMPatientNotPaidReasons> lst = reportsService.getNotPaidReasonData(searchCriteria);
		return new ResponseEntity<List<ORMPatientNotPaidReasons>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getMonthorYearWiseRpt")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> getMonthorYearWiseRpt(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMTwoColumnGeneric> lst = reportsService.getMonthorYearWiseRpt(searchCriteria);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/searchReport")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> searchReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMTwoColumnGeneric> lst = reportsService.searchReport(searchCriteria);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getHcfaClaims")
	public @ResponseBody ResponseEntity<List<ORMrptHCFAClaimsGet>> getHcfaClaims(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMrptHCFAClaimsGet> lst = reportsService.getHcfaClaims(searchCriteria);
		return new ResponseEntity<List<ORMrptHCFAClaimsGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatientNotSeen")
	public @ResponseBody ResponseEntity<List<ORMPatientNotSeen>> getPatientNotSeen(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMPatientNotSeen> lst = reportsService.getPatientNotSeen(searchCriteria);
		return new ResponseEntity<List<ORMPatientNotSeen>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getGeneralReportOptions/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetGeneralRptOptions>> getGeneralReportOptions(
			@PathVariable(value = "practice_id") String practice_id) {
		List<ORMGetGeneralRptOptions> obj = reportsService.getGeneralReportOptions(practice_id);
		return new ResponseEntity<List<ORMGetGeneralRptOptions>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/searchGeneralReport/{spname}/{practice_id}")
	public @ResponseBody ResponseEntity<WrapperLog> searchGeneralReport(@PathVariable(value = "spname") String spname,
			@PathVariable(value = "practice_id") String practice_id) {
		WrapperLog wrapperLog = reportsService.searchGeneralReport(spname, practice_id);
		return new ResponseEntity<WrapperLog>(wrapperLog, HttpStatus.OK);
	}

	@RequestMapping("/getCorrespondenceReportData")
	public @ResponseBody ResponseEntity<List<ORMGetCorrespondenceReport>> getCorrespondenceReportData(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMGetCorrespondenceReport> lst = reportsService.getCorrespondenceReportData(searchCriteria);
		return new ResponseEntity<List<ORMGetCorrespondenceReport>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getMonthWiseChargesPaymentReport")
	public @ResponseBody ResponseEntity<List<ORMRptMonthWiseChargesPayment>> getMonthWiseChargesPaymentReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMRptMonthWiseChargesPayment> lst = reportsService.getMonthWiseChargesPaymentReport(searchCriteria);
		return new ResponseEntity<List<ORMRptMonthWiseChargesPayment>>(lst, HttpStatus.OK);
	}

	@RequestMapping(value = "/getExportResult", method = RequestMethod.POST)
	public @ResponseBody byte[] getExportResult(@RequestBody SearchCriteria searchCriteria) throws IOException {
		byte[] document = null;
		try {
			ServiceResponse<ORMKeyValue> resp = reportsService.getExportResult(searchCriteria);
			File file = new File(resp.getResult());
			if (file.exists()) {
				document = org.springframework.util.FileCopyUtils.copyToByteArray(file);
				HttpHeaders header = new HttpHeaders();
				header.setContentType(new MediaType("application", "pdf"));
				header.set("Content-Disposition", "inline; filename=" + file.getName());
				header.setContentLength(document.length);

			} else
				return null;
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
			return null;
		}
		return document;
	}
	@RequestMapping("/getLaggedCollectionReport")
	public @ResponseBody ResponseEntity<List<?>> getLaggedCollectionReport(
			@RequestBody SearchCriteria searchCriteria){	
		List<?> lst=reportsService.getLaggedCollectionReport(searchCriteria);
		return new ResponseEntity<List<?>>(lst , HttpStatus.OK);
	}	

	@RequestMapping("/getDailyChargesPaymentSummaryReport")
	public @ResponseBody ResponseEntity<List<ORMRptDailyChargesPaymentSummary>> getDailyChargesPaymentSummaryReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMRptDailyChargesPaymentSummary> lst = reportsService.getDailyChargesPaymentSummaryReport(searchCriteria);
		return new ResponseEntity<List<ORMRptDailyChargesPaymentSummary>>(lst, HttpStatus.OK);
	}
	
	
	@RequestMapping("/getDailyPaymentSummaryReport")
	public @ResponseBody ResponseEntity<List<ORMRptDailyPaymentSummary>> getDailyPaymentSummaryReport(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMRptDailyPaymentSummary> lst = reportsService.getDailyPaymentSummaryReport(searchCriteria);
		return new ResponseEntity<List<ORMRptDailyPaymentSummary>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/getProcedureAnalysis")
	public @ResponseBody ResponseEntity<List<?>> getProcedureAnalysis(
			@RequestBody SearchCriteria searchCriteria){	
		List<?> lst=reportsService.getProcedureAnalysis(searchCriteria);
		return new ResponseEntity<List<?>>(lst , HttpStatus.OK);
	}	
	
	@RequestMapping("/getClaimReceiptDetail/{claim_id}")
	public @ResponseBody ResponseEntity<ORMClaimReceiptDetailGet> getClaimReceiptDetail(
			@PathVariable(value = "claim_id") Long claimId) {
		ORMClaimReceiptDetailGet obj = reportsService.getClaimReceiptDetail(claimId);
		return new ResponseEntity<ORMClaimReceiptDetailGet>(obj, HttpStatus.OK);
	}
	
}