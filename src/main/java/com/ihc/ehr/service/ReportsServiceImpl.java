package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.dao.ReportsDAO;
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

@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	ReportsDAO reportsDAO;

	@Override
	public List<ORMGetAppointmentReport> getAppointments(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getAppointments(searchCriteria);
	}

	@Override
	public List<ORMGETrptInsVerification> getEligibilityVerification(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getEligibilityVerification(searchCriteria);
	}

	@Override
	public List<ORMEncounterIcdsCpts> getEncounterProcDiag(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getEncounterProcDiag(searchCriteria);
	}

	@Override
	public List<ORMGetEncounterReport> getEncounterReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getEncounterReport(searchCriteria);
	}

	@Override
	public List<ORMClaimwithUnsignedEncounter> getClaimwithUnsignedEncounter(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getClaimwithUnsignedEncounter(searchCriteria);
	}

	@Override
	public List<ORMDashBoardClaim> getMissingClaims(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getMissingClaims(searchCriteria);
	}

	@Override
	public List<ORMOrderCrossReport> getOrderCross_False(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getOrderCross_False(searchCriteria);
	}

	@Override
	public List<ORMOrderCrossReport> getOrderCross_True(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getOrderCross_True(searchCriteria);
	}

	@Override
	public List<ORMGetDiagnosticReport> getDiagnReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getDiagnReport(searchCriteria);
	}

	@Override
	public List<ORMACUReport> getCallsData(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getCallsData(searchCriteria);
	}

	@Override
	public List<ORMGYNMissingPatient> getgynMissedVisit(String practice_id) {
		return reportsDAO.getgynMissedVisit(practice_id);
	}

	@Override
	public List<ORMOBGynReport> getPregnancyData(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getPregnancyData(searchCriteria);
	}

	@Override
	public List<ORMPatEligReport> getPatEligReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getPatEligReport(searchCriteria);
	}

	/*
	 * @Override public String getPatientEligibility(ORMEligibilityDetail obj) {
	 * return reportsDAO.getPatientEligibility(obj); }
	 */
	@Override
	public List<ORMEncounterCountDayWise> getDayWiseRpt(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getDayWiseRpt(searchCriteria);
	}

	@Override
	public List<ORMEncounterCountMonthWise> getProviderWiseRpt(SearchCriteria searchCriteria) {
		return reportsDAO.getProviderWiseRpt(searchCriteria);
	}

	@Override
	public List<ORMEncounterCountMonthWise> getLocationWiseRpt(SearchCriteria searchCriteria) {
		return reportsDAO.getLocationWiseRpt(searchCriteria);
	}

	// @Override
	// public Long saveupdateDailyDeposit(ORMDailyDeposits obj) {
	// return reportsDAO.saveupdateDailyDeposit(obj);
	// }
	/*
	 * @Override public Long saveupdateDailyDeposit(ORMDailyDeposits patDoc,
	 * MultipartFile docFile,String docCategory) { // TODO Auto-generated method
	 * stub return reportsDAO.saveupdateDailyDeposit(patDoc,docFile,docCategory); }
	 */
	@Override
	public ServiceResponse<ORMKeyValue> saveupdateDailyDeposit(ORMDailyDeposits patDoc, MultipartFile docFile,
			String docCategory) {
		// TODO Auto-generated method stub
		return reportsDAO.saveupdateDailyDeposit(patDoc, docFile, docCategory);
	}

	@Override
	public List<ORMGetDailyDepositReports> getDailyDeposit(SearchCriteria searchCriteria) {
		return reportsDAO.getDailyDeposit(searchCriteria);
	}

	@Override
	public int deleteDailyDeposit(ORMDeleteRecord obj) {
		return reportsDAO.deleteDailyDeposit(obj);
	}

	@Override
	public List<ORMDailyDepositReports> searchByMonthYear(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.searchByMonthYear(searchCriteria);
	}

	@Override
	public List<ORMPatientPayer> getPatientPayer(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getPatientPayer(searchCriteria);
	}

	@Override
	public List<ORMPatientPayerDetails> getPatientPayerDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getPatientPayerDetails(searchCriteria);
	}

	@Override
	public List<ORMDashBoarCashReg> getCashPaymentDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getCashPaymentDetails(searchCriteria);
	}

	@Override
	public List<ORMGetPatientPayment> getPatientPaymentDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getPatientPaymentDetails(searchCriteria);
	}

	@Override
	public List<ORMGetCollectPayment> getCollectPaymentDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getCollectPaymentDetails(searchCriteria);
	}

	@Override
	public List<ORMgetYearlyCashRegisterColReport> getYearlyCollectionDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getYearlyCollectionDetails(searchCriteria);
	}

	@Override
	public List<ORMGetClaimsReport> getClaimdetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getClaimdetails(searchCriteria);
	}

	@Override
	public List<ORMgetPaymentCategoriesReport> getPaymentCategoriesDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getPaymentCategoriesDetails(searchCriteria);
	}

	@Override
	public List<ORMpaymentcategories> getPaymentCategories(String practiceId) {
		// TODO Auto-generated method stub
		return reportsDAO.getPaymentCategories(practiceId);
	}

	@Override
	public List<ORMpaymentcategoryprocedures> getPaymentcategories_Procedures(String category_id) {
		// TODO Auto-generated method stub
		return reportsDAO.getPaymentcategories_Procedures(category_id);
	}

	@Override
	public Long saveupdatePaymentCategory(ORMpaymentcategories obj) {
		// TODO Auto-generated method stub
		return reportsDAO.saveupdatePaymentCategory(obj);
	}

	@Override
	public int deleteCategory(ORMDeleteRecord obj) {
		return reportsDAO.deleteCategory(obj);
	}

	@Override
	public int deleteProcedure(ORMDeleteRecord obj) {
		return reportsDAO.deleteProcedure(obj);
	}

	@Override
	public Long saveProcedures(List<ORMpaymentcategoryprocedures> obj) {
		// TODO Auto-generated method stub
		return reportsDAO.saveProcedures(obj);
	}

	@Override
	public List<ORMCrossRpt> getCrossReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getCrossReport(searchCriteria);
	}

	@Override
	public List<ORMLabCompleted> getLabCompRpt(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getLabCompRpt(searchCriteria);
	}

	@Override
	public List<ORMPatientCreationRpt> getPatCreation(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getPatCreation(searchCriteria);
	}

	@Override
	public List<ORMGetUserAdministrationModules> getusermodules(String role_id, String practice_id) {
		return reportsDAO.getusermodules(role_id, practice_id);
	}

	@Override
	public List<ORMGetProviderParollReport> searchPayRoll(SearchCriteria searchCriteria) {
		return reportsDAO.searchPayRoll(searchCriteria);
	}

	@Override
	public List<ORMPayrollCategories> GetPayrollCategories(Long practiceId) {
		return reportsDAO.GetPayrollCategories(practiceId);
	}

	@Override
	public List<ORMGetPayRollProviders> getPayRollCategoryProviders(String category_id, String practice_id) {
		return reportsDAO.getPayRollCategoryProviders(category_id, practice_id);
	}

	@Override
	public List<ORMGetPayRollCategoryProcedures> getPayRollCategoryProcedures(String category_id) {
		return reportsDAO.getPayRollCategoryProcedures(category_id);
	}

	@Override
	public Long saveupdateCategories(ORMPayrollCategories obj) {
		return reportsDAO.saveupdateCategories(obj);
	}

	@Override
	public int deleteSeletedCategory(ORMDeleteRecord obj) {
		return reportsDAO.deleteSeletedCategory(obj);
	}

	@Override
	public List<ORMGetPayrollVisitsDetails> getProviderPayrollVisits(SearchCriteria searchCriteria) {
		return reportsDAO.getProviderPayrollVisits(searchCriteria);
	}

	@Override
	public List<ORMGetPayrollCharges> getProviderPayrollCharges(SearchCriteria searchCriteria) {
		return reportsDAO.getProviderPayrollCharges(searchCriteria);
	}

	@Override
	public List<ORMGetPayrollPayment> getProviderPayrollPayments(SearchCriteria searchCriteria) {
		return reportsDAO.getProviderPayrollPayments(searchCriteria);
	}

	@Override
	public Long SavePayRollCategoryProcedures(List<ORMPayrollCategoryProcedures> objDetail) {
		return reportsDAO.SavePayRollCategoryProcedures(objDetail);
	}

	@Override
	public Long SavePayRollCategoryProviders(List<ORMProviderPayrollCategories> objDetail) {
		return reportsDAO.SavePayRollCategoryProviders(objDetail);
	}

	@Override
	public int CheckIfCPTExists(SearchCriteria searchCriteria) {
		return reportsDAO.CheckIfCPTExists(searchCriteria);
	}

	@Override
	public List<ORMGetProviderWiseMonthlyPaymentCollection> getPaymentCollectionSummaryProviderWise(
			SearchCriteria searchCriteria) {
		return reportsDAO.getPaymentCollectionSummaryProviderWise(searchCriteria);
	}

	@Override
	public List<ORMIdNameCount> getDenialMessageRptPayers(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getDenialMessageRptPayers(searchCriteria);
	}

	@Override
	public List<ORMDenialMessagesGet> getDenialMessages(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getDenialMessages(searchCriteria);
	}

	@Override
	public List<ORMGetPaymentCollectionSummaryReport> getSourceWiseCollection(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getSourceWiseCollection(searchCriteria);
	}

	@Override
	public List<ORMBillingTrackSheet> getBillingTrackSheetFreez(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getBillingTrackSheetFreez(searchCriteria);
	}

	@Override
	public List<ORMGetDepositReportChecksSummary> getBankDepositReportCheckSummary(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getBankDepositReportCheckSummary(searchCriteria);
	}

	@Override
	public List<ORMGetCheckPostedPayments> getCheckPostedPayments(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getCheckPostedPayments(searchCriteria);
	}

	@Override
	public List<ORMClaimBatchReport> getClaimBatchReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getClaimBatchReport(searchCriteria);
	}

	@Override
	public List<ORMSptWisePaymentReport> getCPTWisePaymentReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getCPTWisePaymentReport(searchCriteria);
	}

	@Override
	public List<ORMGetPayerWisePaymentReport> getPayerWisePayment(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getPayerWisePayment(searchCriteria);
	}

	@Override
	public List<ORMGetPayerWisePaymentDetailsReport> getPayerWisePaymentDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getPayerWisePaymentDetails(searchCriteria);
	}

	@Override
	public List<ORMGetClaimSummaryReport> getClaimSummaryReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getClaimSummaryReport(searchCriteria);
	}

	@Override
	public List<ORMGetCashRegisterReport> getCashRegisterReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getCashRegisterReport(searchCriteria);
	}

	@Override
	public List<ORMGetInvoiceReport> getInvoiceReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getInvoiceReport(searchCriteria);
	}

	@Override
	public List<rptGetUserPerformance> getUserPerformanceReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getUserPerformanceReport(searchCriteria);
	}

	@Override
	public List<ORMNineColumnGeneric> getPaymentCollectionSummaryLocationWise(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getPaymentCollectionSummaryLocationWise(searchCriteria);
	}

	@Override
	public List<ORMCPTSummaryReport> getProcedureSummaryReport(SearchCriteria searchCriteria) {
		return reportsDAO.getProcedureSummaryReport(searchCriteria);
	}

	@Override
	public List<ORMProWorkReport> getProviderWork(SearchCriteria searchCriteria) {
		return reportsDAO.getProviderWork(searchCriteria);
	}

	@Override
	public List<ORMTwoColumnGeneric> getAuthorizationUsers(String practiceId) {
		return reportsDAO.getAuthorizationUsers(practiceId);
	}

	@Override
	public List<ORMPatientNotPaidReasons> getNotPaidReasonData(SearchCriteria searchCriteria) {
		return reportsDAO.getNotPaidReasonData(searchCriteria);
	}

	@Override
	public List<ORMTwoColumnGeneric> getMonthorYearWiseRpt(SearchCriteria searchCriteria) {
		return reportsDAO.getMonthorYearWiseRpt(searchCriteria);
	}

	@Override
	public List<ORMTwoColumnGeneric> searchReport(SearchCriteria searchCriteria) {
		return reportsDAO.searchReport(searchCriteria);
	}

	@Override
	public List<ORMrptHCFAClaimsGet> getHcfaClaims(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getHcfaClaims(searchCriteria);
	}

	@Override
	public List<ORMCssCallLog> getcssCallLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getcssCallLog(searchCriteria);
	}

	@Override
	public List<ORMPatientNotSeen> getPatientNotSeen(SearchCriteria searchCriteria) {
		return reportsDAO.getPatientNotSeen(searchCriteria);
	}

	@Override
	public List<ORMGetGeneralRptOptions> getGeneralReportOptions(String practice_id) {
		return reportsDAO.getGeneralReportOptions(practice_id);
	}

	@Override
	public WrapperLog searchGeneralReport(String spname, String practice_id) {
		return reportsDAO.searchGeneralReport(spname, practice_id);
	}

	@Override
	public List<ORMGetCorrespondenceReport> getCorrespondenceReportData(SearchCriteria searchCriteria) {
		return reportsDAO.getCorrespondenceReportData(searchCriteria);
	}

	@Override
	public List<ORMAppointmentSMSReport> getAppointmentsms(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getAppointmentsms(searchCriteria);
	}

	@Override
	public List<ORMRptMonthWiseChargesPayment> getMonthWiseChargesPaymentReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getMonthWiseChargesPaymentReport(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> getExportResult(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getExportResult(searchCriteria);
	}

	@Override
	public List<ORMgetMissingClaimReport> getMissingClaimsReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getMissingClaimsReport(searchCriteria);
	}

	@Override
	public List<ORMGetClaimReportFooter> getReportClaimdetailFooter(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getReportClaimdetailFooter(searchCriteria);
	}

	@Override
	public List<ORMPayerWiseAgingSummary> getBillingAgingPayerWiseSummary(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getBillingAgingPayerWiseSummary(searchCriteria);
	}
	@Override
	public List<?> getLaggedCollectionReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getLaggedCollectionReport(searchCriteria);
	}

	@Override
	public List<ORMRptDailyChargesPaymentSummary> getDailyChargesPaymentSummaryReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getDailyChargesPaymentSummaryReport(searchCriteria);
	}

	@Override
	public List<ORMRptDailyPaymentSummary> getDailyPaymentSummaryReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getDailyPaymentSummaryReport(searchCriteria);
	}

	@Override
	public List<?> getProcedureAnalysis(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return reportsDAO.getProcedureAnalysis(searchCriteria);
	}

	@Override
	public ORMClaimReceiptDetailGet getClaimReceiptDetail(Long claimId) {
		// TODO Auto-generated method stub
		return reportsDAO.getClaimReceiptDetail(claimId);
	}
}