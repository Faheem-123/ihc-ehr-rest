package com.ihc.ehr.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
import com.ihc.ehr.model.ORMGetProviderWiseMonthlyPaymentCollection;
import com.ihc.ehr.model.ORMGetPaymentCollectionSummaryReport;
import com.ihc.ehr.model.ORMGetPayrollCharges;
import com.ihc.ehr.model.ORMGetPayrollPayment;
import com.ihc.ehr.model.ORMGetPayrollVisitsDetails;
import com.ihc.ehr.model.ORMGetProviderParollReport;
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

public interface ReportsService {

	List<ORMGetAppointmentReport> getAppointments(SearchCriteria searchCriteria);

	List<ORMGETrptInsVerification> getEligibilityVerification(SearchCriteria searchCriteria);

	List<ORMEncounterIcdsCpts> getEncounterProcDiag(SearchCriteria searchCriteria);

	List<ORMGetEncounterReport> getEncounterReport(SearchCriteria searchCriteria);

	List<ORMClaimwithUnsignedEncounter> getClaimwithUnsignedEncounter(SearchCriteria searchCriteria);

	List<ORMDashBoardClaim> getMissingClaims(SearchCriteria searchCriteria);

	List<ORMOrderCrossReport> getOrderCross_False(SearchCriteria searchCriteria);

	List<ORMOrderCrossReport> getOrderCross_True(SearchCriteria searchCriteria);

	List<ORMGetDiagnosticReport> getDiagnReport(SearchCriteria searchCriteria);

	List<ORMACUReport> getCallsData(SearchCriteria searchCriteria);

	List<ORMGYNMissingPatient> getgynMissedVisit(String practice_id);

	List<ORMOBGynReport> getPregnancyData(SearchCriteria searchCriteria);

	List<ORMPatEligReport> getPatEligReport(SearchCriteria searchCriteria);

	// String getPatientEligibility(ORMEligibilityDetail obj);
	List<ORMEncounterCountDayWise> getDayWiseRpt(SearchCriteria searchCriteria);

	List<ORMEncounterCountMonthWise> getProviderWiseRpt(SearchCriteria searchCriteria);

	List<ORMEncounterCountMonthWise> getLocationWiseRpt(SearchCriteria searchCriteria);

	// public Long saveupdateDailyDeposit(ORMDailyDeposits obj);
	// Long saveupdateDailyDeposit(ORMDailyDeposits patDoc, MultipartFile docFile,
	// String docCategory);
	ServiceResponse<ORMKeyValue> saveupdateDailyDeposit(ORMDailyDeposits patDoc, MultipartFile docFile,
			String docCategory);

	List<ORMGetDailyDepositReports> getDailyDeposit(SearchCriteria searchCriteria);

	int deleteDailyDeposit(ORMDeleteRecord obj);

	List<ORMDailyDepositReports> searchByMonthYear(SearchCriteria searchCriteria);

	List<ORMPatientPayer> getPatientPayer(SearchCriteria searchCriteria);

	List<ORMPatientPayerDetails> getPatientPayerDetails(SearchCriteria searchCriteria);

	List<ORMDashBoarCashReg> getCashPaymentDetails(SearchCriteria searchCriteria);

	List<ORMGetPatientPayment> getPatientPaymentDetails(SearchCriteria searchCriteria);

	List<ORMGetCollectPayment> getCollectPaymentDetails(SearchCriteria searchCriteria);

	List<ORMgetYearlyCashRegisterColReport> getYearlyCollectionDetails(SearchCriteria searchCriteria);

	List<ORMGetClaimsReport> getClaimdetails(SearchCriteria searchCriteria);

	List<ORMgetPaymentCategoriesReport> getPaymentCategoriesDetails(SearchCriteria searchCriteria);

	List<ORMpaymentcategories> getPaymentCategories(String practiceId);

	List<ORMpaymentcategoryprocedures> getPaymentcategories_Procedures(String category_id);

	public Long saveupdatePaymentCategory(ORMpaymentcategories obj);

	int deleteCategory(ORMDeleteRecord obj);

	int deleteProcedure(ORMDeleteRecord obj);

	public Long saveProcedures(List<ORMpaymentcategoryprocedures> obj);

	List<ORMCrossRpt> getCrossReport(SearchCriteria searchCriteria);

	List<ORMLabCompleted> getLabCompRpt(SearchCriteria searchCriteria);

	List<ORMPatientCreationRpt> getPatCreation(SearchCriteria searchCriteria);

	List<ORMGetUserAdministrationModules> getusermodules(String role_id, String practice_id);

	List<ORMGetProviderParollReport> searchPayRoll(SearchCriteria searchCriteria);

	List<ORMPayrollCategories> GetPayrollCategories(Long practiceId);

	List<ORMGetPayRollProviders> getPayRollCategoryProviders(String category_id, String practice_id);

	List<ORMGetPayRollCategoryProcedures> getPayRollCategoryProcedures(String category_id);

	public Long saveupdateCategories(ORMPayrollCategories obj);

	int deleteSeletedCategory(ORMDeleteRecord obj);

	List<ORMGetPayrollVisitsDetails> getProviderPayrollVisits(SearchCriteria searchCriteria);

	List<ORMGetPayrollCharges> getProviderPayrollCharges(SearchCriteria searchCriteria);

	List<ORMGetPayrollPayment> getProviderPayrollPayments(SearchCriteria searchCriteria);

	Long SavePayRollCategoryProcedures(List<ORMPayrollCategoryProcedures> objDetail);

	Long SavePayRollCategoryProviders(List<ORMProviderPayrollCategories> objDetail);

	int CheckIfCPTExists(SearchCriteria searchCriteria);

	List<ORMGetProviderWiseMonthlyPaymentCollection> getPaymentCollectionSummaryProviderWise(
			SearchCriteria searchCriteria);

	List<ORMIdNameCount> getDenialMessageRptPayers(SearchCriteria searchCriteria);

	List<ORMDenialMessagesGet> getDenialMessages(SearchCriteria searchCriteria);

	List<ORMGetPaymentCollectionSummaryReport> getSourceWiseCollection(SearchCriteria searchCriteria);

	List<ORMBillingTrackSheet> getBillingTrackSheetFreez(SearchCriteria searchCriteria);

	List<ORMGetDepositReportChecksSummary> getBankDepositReportCheckSummary(SearchCriteria searchCriteria);

	List<ORMGetCheckPostedPayments> getCheckPostedPayments(SearchCriteria searchCriteria);

	List<ORMClaimBatchReport> getClaimBatchReport(SearchCriteria searchCriteria);

	List<ORMSptWisePaymentReport> getCPTWisePaymentReport(SearchCriteria searchCriteria);

	List<ORMGetPayerWisePaymentReport> getPayerWisePayment(SearchCriteria searchCriteria);

	List<ORMGetPayerWisePaymentDetailsReport> getPayerWisePaymentDetails(SearchCriteria searchCriteria);

	List<ORMGetClaimSummaryReport> getClaimSummaryReport(SearchCriteria searchCriteria);

	List<ORMGetCashRegisterReport> getCashRegisterReport(SearchCriteria searchCriteria);

	List<ORMGetInvoiceReport> getInvoiceReport(SearchCriteria searchCriteria);

	List<rptGetUserPerformance> getUserPerformanceReport(SearchCriteria searchCriteria);

	List<ORMNineColumnGeneric> getPaymentCollectionSummaryLocationWise(SearchCriteria searchCriteria);

	List<ORMCPTSummaryReport> getProcedureSummaryReport(SearchCriteria searchCriteria);

	List<ORMProWorkReport> getProviderWork(SearchCriteria searchCriteria);

	List<ORMTwoColumnGeneric> getAuthorizationUsers(String practiceId);

	List<ORMPatientNotPaidReasons> getNotPaidReasonData(SearchCriteria searchCriteria);

	List<ORMTwoColumnGeneric> getMonthorYearWiseRpt(SearchCriteria searchCriteria);

	List<ORMTwoColumnGeneric> searchReport(SearchCriteria searchCriteria);

	List<ORMrptHCFAClaimsGet> getHcfaClaims(SearchCriteria searchCriteria);

	List<ORMCssCallLog> getcssCallLog(SearchCriteria searchCriteria);

	List<ORMPatientNotSeen> getPatientNotSeen(SearchCriteria searchCriteria);

	List<ORMGetGeneralRptOptions> getGeneralReportOptions(String practice_id);

	WrapperLog searchGeneralReport(String spname, String practice_id);

	List<ORMGetCorrespondenceReport> getCorrespondenceReportData(SearchCriteria searchCriteria);

	List<ORMAppointmentSMSReport> getAppointmentsms(SearchCriteria searchCriteria);

	List<ORMRptMonthWiseChargesPayment> getMonthWiseChargesPaymentReport(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> getExportResult(SearchCriteria searchCriteria);

	List<ORMgetMissingClaimReport> getMissingClaimsReport(SearchCriteria searchCriteria);

	List<ORMGetClaimReportFooter> getReportClaimdetailFooter(SearchCriteria searchCriteria);

	List<ORMPayerWiseAgingSummary> getBillingAgingPayerWiseSummary(SearchCriteria searchCriteria);
	List<?> getLaggedCollectionReport(SearchCriteria searchCriteria);

	List<ORMRptDailyChargesPaymentSummary> getDailyChargesPaymentSummaryReport(SearchCriteria searchCriteria);

	List<ORMRptDailyPaymentSummary> getDailyPaymentSummaryReport(SearchCriteria searchCriteria);

	List<?> getProcedureAnalysis(SearchCriteria searchCriteria);
	
	ORMClaimReceiptDetailGet getClaimReceiptDetail(Long claimId);
	
}