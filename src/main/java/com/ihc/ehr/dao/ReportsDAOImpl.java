package com.ihc.ehr.dao;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.LogHeader;
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
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMEncounterCountDayWise;
import com.ihc.ehr.model.ORMEncounterCountMonthWise;
import com.ihc.ehr.model.ORMEncounterIcdsCpts;
import com.ihc.ehr.model.ExcelColumn;
import com.ihc.ehr.model.ORMGETrptInsVerification;
import com.ihc.ehr.model.ORMGYNMissingPatient;
import com.ihc.ehr.model.ORMGetAppointmentReport;
import com.ihc.ehr.model.ORMGetAutidLogQuery;
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
import com.ihc.ehr.model.ORMGetLogList;
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
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.UploadFile;
import com.ihc.ehr.model.WrapperLog;
import com.ihc.ehr.model.Wrapper_ExcelColumn;
import com.ihc.ehr.model.rptGetUserPerformance;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.EnumUtil;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.ExcelColumnsInfo;
import com.ihc.ehr.util.FileUtil;
import com.ihc.ehr.util.GeneralOperations;

@Repository
public class ReportsDAOImpl implements ReportsDAO {

	@Autowired
	private DBOperations db;

	@Autowired
	GeneralDAOImpl generalDAOImpl;

	@Override
	public List<ORMGetAppointmentReport> getAppointments(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String criteria = " and ap.practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";

		String dtFrom = "";
		String dtTo = "";
		String pageIndex = "0";
		String pageSize = "0";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and ap.patient_id ='" + pram.getValue() + "'";

					}
					break;
				case "provider_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and ap.provider_id ='" + pram.getValue() + "'";

					}
					break;
				case "location_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and ap.location_id ='" + pram.getValue() + "'";

					}
					break;
				case "status_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						if (pram.getValue().equalsIgnoreCase("No Show With No Call")) {
							criteria += " and ap.status_id = '50010110' and isnull((select count(appointment_id) from appointment_callslog where appointment_id=ap.appointment_id),0)=0 ";
						} else if (pram.getValue() != "0") {
							criteria += " and ap.status_id = '" + pram.getValue() + "' ";
						}

					}
					break;
				case "type_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						if (pram.getValue() != "0") {
							criteria += " and ap.apptype = '" + pram.getValue() + "' ";
						}

					}
					break;
				case "source_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						if (pram.getValue() != "0") {
							criteria += " and ap.appsource = '" + pram.getValue() + "' ";
						}

					}
					break;
				case "date_from":
					dtFrom = pram.getValue();
					break;
				case "date_to":
					dtTo = pram.getValue();
					break;
				case "pageIndex":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageIndex = pram.getValue();

					}
					break;
				case "pageSize":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageSize = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += " and convert(date,ap.appointment_date) between convert(date,'" + dtFrom
					+ "') and convert(date,'" + dtTo + "')";
		} else if (!dtFrom.isEmpty()) {
			criteria += " and convert(date,ap.appointment_date) = convert(date,'" + dtFrom + "') ";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageIndex", pageIndex.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageSize", pageSize.toString(), String.class, ParameterMode.IN));

		List<ORMGetAppointmentReport> lst = db.getStoreProcedureData("spGetAppointmentRpt_paging",
				ORMGetAppointmentReport.class, lstParam);

		return lst;
	}
	// List<ORMGETrptInsVerification> getEligibilityVerification(SearchCriteria
	// searchCriteria);

	@Override
	public List<ORMGETrptInsVerification> getEligibilityVerification(SearchCriteria searchCriteria) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id",
				searchCriteria.getPractice_id().toString() == null ? "" : searchCriteria.getPractice_id().toString(),
				String.class, ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "dateFrom":
					// if (pram.getValue() != "" && pram.getValue() != null) {
					lstParam.add(new SpParameters("FromDate", pram.getValue(), String.class, ParameterMode.IN));

					// }
					break;
				case "dateTo":
					// if (pram.getValue() != "" && pram.getValue() != null) {
					lstParam.add(new SpParameters("ToDate", pram.getValue(), String.class, ParameterMode.IN));

					// }
					break;
				case "cmbProvider":
					// if (pram.getValue() != "" && pram.getValue() != null) {
					lstParam.add(new SpParameters("Provider", pram.getValue(), String.class, ParameterMode.IN));
					// lstParam.add(new SpParameters("Provider", pram.getValue(), String.class,
					// ParameterMode.IN));

					// }
					break;
				case "cmbLocation":
					// if (pram.getValue() != "" && pram.getValue() != null) {
					lstParam.add(new SpParameters("Location", pram.getValue(), String.class, ParameterMode.IN));

					// }
					break;
				case "category":
					// if (pram.getValue() != "" && pram.getValue() != null) {
					lstParam.add(new SpParameters("category", pram.getValue(), String.class, ParameterMode.IN));

					// }
					break;
				default:
					break;
				}
			}

		}
		List<ORMGETrptInsVerification> lst = db.getStoreProcedureData("sprptInsVerification",
				ORMGETrptInsVerification.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMEncounterIcdsCpts> getEncounterProcDiag(SearchCriteria searchCriteria) {

		String criteria = " and p.practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";

		String dtFrom = "";
		String dtTo = "";
		String pageIndex = "0";
		String pageSize = "0";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and p.patient_id ='" + pram.getValue() + "'";
					}
					break;

				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().toString().equals("null") == false
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and pc.provider_id ='" + pram.getValue() + "'";
					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().toString().equals("null") == false
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and lc.location_id ='" + pram.getValue() + "'";
					}
					break;
				case "dateFrom":
					dtFrom = pram.getValue();
					break;
				case "dateTo":
					dtTo = pram.getValue();
					break;
				case "pageIndex":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageIndex = pram.getValue();

					}
					break;
				case "pageSize":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageSize = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) between  convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) and convert(datetime,convert(varchar,'" + dtTo + "',101)) ";
		} else if (!dtFrom.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) >= convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) ";
		} else if (!dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) <= CONVERT(datetime,convert(varchar,'"
					+ dtTo + "',101))";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageIndex", pageIndex.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageSize", pageSize.toString(), String.class, ParameterMode.IN));

		List<ORMEncounterIcdsCpts> lst = db.getStoreProcedureData("spGetEncounterIcdsCPts_paging",
				ORMEncounterIcdsCpts.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMGetEncounterReport> getEncounterReport(SearchCriteria searchCriteria) {

		String criteria = " and ap.practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";
		String dtFrom = "";
		String dtTo = "";
		String searchOption = "";
		String pageIndex = "0";
		String pageSize = "0";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and p.patient_id = '" + pram.getValue() + "'";
					}
					break;
				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null && !pram.getValue().equals("null")
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and ap.provider_id = '" + pram.getValue() + "'";
					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null && !pram.getValue().equals("null")
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and ap.location_id = '" + pram.getValue() + "'";
					}
					break;
				case "dateFrom":
					dtFrom = pram.getValue();
					break;
				case "dateTo":
					dtTo = pram.getValue();
					break;
				case "searchOption":
					searchOption = pram.getValue();
					break;
				case "pageIndex":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageIndex = pram.getValue();

					}
					break;
				case "pageSize":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageSize = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,ap.appointment_date,101)) between  convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) and convert(datetime,convert(varchar,'" + dtTo + "',101)) ";
		} else if (!dtFrom.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,ap.appointment_date,101)) >= convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) ";
		} else if (!dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,ap.appointment_date,101)) <= CONVERT(datetime,convert(varchar,'"
					+ dtTo + "',101))";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("SearchOption", searchOption.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageIndex", pageIndex.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageSize", pageSize.toString(), String.class, ParameterMode.IN));

		List<ORMGetEncounterReport> lst = db.getStoreProcedureData("spGetEncounterReport_paging",
				ORMGetEncounterReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMClaimwithUnsignedEncounter> getClaimwithUnsignedEncounter(SearchCriteria searchCriteria) {

		String criteria = " and pc.practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";

		String dtFrom = "";
		String dtTo = "";
		String pageIndex = "0";
		String pageSize = "0";
		// String searchOption = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and pat.patient_id = '" + pram.getValue() + "'";
					}
					break;

				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and pc.provider_id = '" + pram.getValue() + "'";
					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and pc.location_id = '" + pram.getValue() + "'";
					}
					break;
				case "dateFrom":
					dtFrom = pram.getValue();
					break;
				case "dateTo":
					dtTo = pram.getValue();
					break;
				case "pageIndex":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageIndex = pram.getValue();

					}
					break;
				case "pageSize":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageSize = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) between  convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) and convert(datetime,convert(varchar,'" + dtTo + "',101)) ";
		} else if (!dtFrom.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) >= convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) ";
		} else if (!dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) <= CONVERT(datetime,convert(varchar,'"
					+ dtTo + "',101))";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageIndex", pageIndex.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageSize", pageSize.toString(), String.class, ParameterMode.IN));
		List<ORMClaimwithUnsignedEncounter> lst = db.getStoreProcedureData("spGetClaimwithUnsignedEncounter_paging",
				ORMClaimwithUnsignedEncounter.class, lstParam);

		return lst;
	}

	////////////////////////// Dashboard
	@Override
	public List<ORMDashBoardClaim> getMissingClaims(SearchCriteria searchCriteria) {

		String criteria = " and pc.practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";

		String dtFrom = "";
		String dtTo = "";

		// String searchOption = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().toString().equals("null") == false
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and pc.provider_id = '" + pram.getValue() + "'";
					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().toString().equals("null") == false
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and pc.location_id = '" + pram.getValue() + "'";
					}
					break;
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and p.patient_id = '" + pram.getValue() + "'";
					}
					break;
				case "dateFrom":
					dtFrom = pram.getValue();
					break;
				case "dateTo":
					dtTo = pram.getValue();
					break;

				default:
					break;
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) between  convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) and convert(datetime,convert(varchar,'" + dtTo + "',101)) ";
		} else if (!dtFrom.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) >= convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) ";
		} else if (!dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) <= CONVERT(datetime,convert(varchar,'"
					+ dtTo + "',101))";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		List<ORMDashBoardClaim> lst = db.getStoreProcedureData("spGetMissingClaims", ORMDashBoardClaim.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMgetMissingClaimReport> getMissingClaimsReport(SearchCriteria searchCriteria) {

		String criteria = " and pc.practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";

		String dtFrom = "";
		String dtTo = "";
		String pageIndex = "0";
		String pageSize = "0";
		// String searchOption = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().toString().equals("null") == false
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and pc.provider_id = '" + pram.getValue() + "'";
					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().toString().equals("null") == false
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and pc.location_id = '" + pram.getValue() + "'";
					}
					break;
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and p.patient_id = '" + pram.getValue() + "'";
					}
					break;
				case "dateFrom":
					dtFrom = pram.getValue();
					break;
				case "dateTo":
					dtTo = pram.getValue();
					break;
				case "pageIndex":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageIndex = pram.getValue();

					}
					break;
				case "pageSize":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageSize = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) between  convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) and convert(datetime,convert(varchar,'" + dtTo + "',101)) ";
		} else if (!dtFrom.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) >= convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) ";
		} else if (!dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pc.visit_date,101)) <= CONVERT(datetime,convert(varchar,'"
					+ dtTo + "',101))";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageIndex", pageIndex.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageSize", pageSize.toString(), String.class, ParameterMode.IN));
		List<ORMgetMissingClaimReport> lst = db.getStoreProcedureData("spGetMissingClaims_paging",
				ORMgetMissingClaimReport.class, lstParam);

		return lst;
	}

	//////
	@Override
	public List<ORMOrderCrossReport> getOrderCross_False(SearchCriteria searchCriteria) {

		String criteria = " and p.practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";

		String dtFrom = "";
		String dtTo = "";
		String pageIndex = "0";
		String pageSize = "0";
		// String searchOption = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and p.patient_id = '" + pram.getValue() + "'";
					}
					break;

				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and po.provider_id = '" + pram.getValue() + "'";
					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and po.location_id = '" + pram.getValue() + "'";
					}
					break;
				case "dateFrom":
					dtFrom = pram.getValue();
					break;
				case "dateTo":
					dtTo = pram.getValue();
					break;
				case "pageIndex":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageIndex = pram.getValue();

					}
					break;
				case "pageSize":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageSize = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,po.order_date,101)) between  convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) and convert(datetime,convert(varchar,'" + dtTo + "',101)) ";
		} else if (!dtFrom.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,po.order_date,101)) >= convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) ";
		} else if (!dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,po.order_date,101)) <= CONVERT(datetime,convert(varchar,'"
					+ dtTo + "',101))";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageIndex", pageIndex.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageSize", pageSize.toString(), String.class, ParameterMode.IN));
		List<ORMOrderCrossReport> lst = db.getStoreProcedureData("spGetOrderCrossReport_paging",
				ORMOrderCrossReport.class, lstParam);

		return lst;
	}

	// end false
	@Override
	public List<ORMOrderCrossReport> getOrderCross_True(SearchCriteria searchCriteria) {
		String criteria = "";

		String dtFrom = "";
		String dtTo = "";
		// String searchOption = "";
		String pageIndex = "0";
		String pageSize = "0";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and p.patient_id = '" + pram.getValue() + "'";
					}
					break;
				case "dateFrom":
					dtFrom = pram.getValue();
					break;
				case "dateTo":
					dtTo = pram.getValue();
					break;
				case "pageIndex":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageIndex = pram.getValue();
					}
					break;
				case "pageSize":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageSize = pram.getValue();
					}
					break;
				default:
					break;
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += " convert(datetime,convert(varchar,'" + dtFrom
					+ "',101)) and convert(datetime,convert(varchar,'" + dtTo + "',101)) ";
			criteria += " and p.practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageIndex", pageIndex.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageSize", pageSize.toString(), String.class, ParameterMode.IN));
		List<ORMOrderCrossReport> lst = db.getStoreProcedureData("spGetOrderCrossReport_new_paging",
				ORMOrderCrossReport.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMGetDiagnosticReport> getDiagnReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String criteria = " and pd.practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";

		String pageNo = "";
		String recPerPage = "";

		String dtFrom = "";
		String dtTo = "";

		String dtStatusFrom = "";
		String dtStatusTo = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtFrom = pram.getValue();
					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtTo = pram.getValue();
					}
					break;
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and pd.patient_id ='" + pram.getValue() + "'";
					}
					break;
				case "testID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and pt.proc_code = '" + pram.getValue() + "' ";
					}
					break;
				case "LabsID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and pt.proc_code in (select proc_code from lab_category_detail ld "
								+ "join lab_category_sub ls on ls.sub_category_id = ld.sub_category_id and ISNULL(ls.deleted,0)<>1 "
								+ "join lab_categories lc on lc.category_id = ls.category_id and ISNULL(lc.deleted,0)<>1 "
								+ "where isnull(ld.deleted,0)<>1 and lc.category_id in( " + pram.getValue()
								+ ")) and pt.lab_category_id in( " + pram.getValue() + ")";
					}
					break;
				case "cmbStatus":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and pd.status = '" + pram.getValue() + "' ";
					}
					break;
				case "dateStatusFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtStatusFrom = pram.getValue();
					}
					break;
				case "dateStatusTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtStatusTo = pram.getValue();
					}
					break;
				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and pd.provider_id ='" + pram.getValue() + "'";
					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						criteria += " and pd.location_id ='" + pram.getValue() + "'";
					}
					break;
				case "pageNo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageNo = pram.getValue();
					}
					break;
				case "perPageRec":
					if (pram.getValue() != "" && pram.getValue() != null) {
						recPerPage = pram.getValue();
					}
					break;

				default:
					break;
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pd.order_date,101)) between  convert(datetime,convert(varchar,'"
					+ dtFrom + "',101)) and convert(datetime,convert(varchar,'" + dtTo + "',101)) ";
		} else if (!dtFrom.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pd.order_date,101)) >= convert(datetime,convert(varchar,'"
					+ dtFrom + "',101) ";
		} else if (!dtTo.isEmpty()) {
			criteria += " and convert(datetime,convert(varchar,pd.order_date,101)) <= CONVERT(datetime,convert(varchar,'"
					+ dtTo + "',101) ";
		}

		if (!dtStatusFrom.isEmpty() && !dtStatusTo.isEmpty()) {
			criteria += " and pd.status_detail between CONVERT(datetime,'" + dtStatusFrom + "') and CONVERT(datetime,'"
					+ dtStatusTo + "') ";
		} else if (!dtStatusFrom.isEmpty()) {
			criteria += " and pd.status_detail >= CONVERT(datetime,'" + dtStatusFrom + "') ";
		} else if (!dtStatusTo.isEmpty()) {
			criteria += " and pd.status_detail <= CONVERT(datetime,'" + dtStatusTo + "') ";
		}

		// List<SpParameters> lstParam = new ArrayList<>();
		// lstParam.add(new SpParameters("criteria", criteria.toString(), String.class,
		// ParameterMode.IN));
		// lstParam.add(new SpParameters("PAGEINDEX",pageNo, String.class,
		// ParameterMode.IN));
		// lstParam.add(new SpParameters("PAGESIZE",recPerPage, String.class,
		// ParameterMode.IN));
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("PAGEINDEX", pageNo.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("PAGESIZE", recPerPage.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetDiagnosticReport_pagging", ORMGetDiagnosticReport.class, lstParam);
		// List<ORMGetDiagnosticReport> lst =
		// db.getStoreProcedureData("spGetDiagnosticReport",
		// ORMGetDiagnosticReport.class, lstParam);
		// List<ORMGetDiagnosticReport> lst =
		// db.getStoreProcedureData("spGetDiagnosticReport_pagging",
		// ORMGetDiagnosticReport.class, lstParam);

		// return lst;
	}

	@Override
	public List<ORMPatEligReport> getPatEligReport(SearchCriteria searchCriteria) {
		String criteria = "";

		// String dtFrom = "";
		// String dtTo = "";
		// String searchOption = "";

		criteria = " and ap.practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and ap.patient_id ='" + pram.getValue() + "'";
					}
					break;
				case "provider_id":
					if (pram.getValue() != "" && pram.getValue() != null && !pram.getValue().equals("null")) {
						criteria += " and ap.provider_id ='" + pram.getValue() + "'";
					}
					break;
				case "location_id":
					if (pram.getValue() != "" && pram.getValue() != null && !pram.getValue().equals("null")) {
						criteria += " and ap.location_id ='" + pram.getValue() + "'";
					}
					break;
				case "aptDate":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and ap.appointment_date ='" + pram.getValue() + "'";
					}
					break;
				case "status_id":
					if (pram.getValue() != "" && pram.getValue() != null && !pram.getValue().equals("-1")) {
						criteria += " and ap.status_id = '" + pram.getValue() + "' ";
					}
					break;
				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		List<ORMPatEligReport> lst = db.getStoreProcedureData("spGetPatEligReport", ORMPatEligReport.class, lstParam);
		return lst;
	}

	/*
	 * @Override public String getPatientEligibility(ORMEligibilityDetail obj) { try
	 * { String PolicyNo = "";
	 * 
	 * List<SpParameters> lstParam = new ArrayList<>(); lstParam.add( new
	 * SpParameters("patient_id", obj.getPatient_id().toString(), String.class,
	 * ParameterMode.IN)); lstParam.add(new SpParameters("ins_type",
	 * obj.getIns_type().toString(), String.class, ParameterMode.IN)); lstParam.add(
	 * new SpParameters("practice_id", obj.getPractice_id().toString(),
	 * String.class, ParameterMode.IN)); List<ORMGetPatientPayer> lst = null; lst =
	 * db.getStoreProcedureData("spGetPatientPayers", ORMGetPatientPayer.class,
	 * lstParam);
	 * 
	 * if (lst != null && lst.size() > 0) { for (ORMGetPatientPayer orm :
	 * (List<ORMGetPatientPayer>) lst) { if ("".equals(orm.getId()) ||
	 * "".equals(orm.getName())) { return
	 * "Payer or patient policy number not found."; }
	 * obj.setEdipayer_id(orm.getId().toString());
	 * obj.setInsurance_num(orm.getName()); PolicyNo = orm.getName(); } } else {
	 * return "Payer or patient policy number not found."; }
	 * 
	 * 
	 * //EDIWebService ediWebService=new EDIWebService();
	 * //ediWebService.findEligibility( (ediWebService.ORMEligibilityDetail) obj);
	 * //ediwebservice.ORMEligibilityDetail abc=new
	 * ediwebservice.ORMEligibilityDetail();
	 * 
	 * //abc=(ORMEligibilityDetail) obj; String Resp =
	 * EDIWebService.findEligibility(obj) + "~" + PolicyNo;
	 * 
	 * if (Resp == null || "".equals(Resp) || "Eligibility Not Found.".equals(Resp))
	 * { UpdateInsurance(obj.getPatient_id(), "", obj.getIns_type());
	 * UpdateTodaysAppointment(obj.getPatient_id(), ""); return
	 * "Eligibility Not Found."; } else { if (obj.getAppointment_id().equals("") &&
	 * obj.getIns_type().toUpperCase().equals("PRIMARY")) {
	 * UpdateTodaysAppointment(obj.getPatient_id(), Resp); } if
	 * (!obj.getAppointment_id().equals("") &&
	 * !obj.getIns_type().equals("SECONDARY")) {
	 * UpdateAppointment(obj.getAppointment_id(), Resp); }
	 * UpdateInsurance(obj.getPatient_id(), Resp, obj.getIns_type()); }
	 * 
	 * return Resp; } catch (Exception e) { // TODO: handle exception return null; }
	 * }
	 * 
	 * private void UpdateInsurance(String patientid, String ElgResp, String Type) {
	 * String EligStatus = "U"; if (!ElgResp.equals("")) { if
	 * (ElgResp.contains("Active")) { EligStatus = "A"; } else if
	 * (ElgResp.contains("Inactive")) { EligStatus = "I"; } } String strQuery =
	 * "Update patient_insurance set elig_status='" + EligStatus +
	 * "',elig_date=GetDate(),elig_response='" + ElgResp.replaceAll("'", "`") + "'"
	 * + " Where patient_id='" + patientid + "' and insurace_type='" + Type +
	 * "' and isnull(deleted,0)<>1"; db.ExecuteUpdateQuery(strQuery); }
	 * 
	 * 
	 * private void UpdateTodaysAppointment(String PatientId, String ElgResp) {
	 * String EligStatus = "U"; if (!ElgResp.equals("")) { if
	 * (ElgResp.contains("Active")) { EligStatus = "A"; } else if
	 * (ElgResp.contains("Inactive")) { EligStatus = "I"; } }
	 * db.ExecuteUpdateQuery("Update appointment set elig_status='" + EligStatus +
	 * "',elig_date=GetDate(),elig_response='" + ElgResp.replaceAll("'", "`") + "' "
	 * + "Where patient_id='" + PatientId +
	 * "' and convert(varchar,appointment_date,101)=convert(varchar,getdate(),101) and isnull(deleted,0)<>1"
	 * ); }
	 * 
	 * private void UpdateAppointment(String appointment_Id, String ElgResp) {
	 * String EligStatus = "U"; if (!ElgResp.equals("")) { if
	 * (ElgResp.contains("Active")) { EligStatus = "A"; } else if
	 * (ElgResp.contains("Inactive")) { EligStatus = "I"; } } db.ExecuteUpdateQuery(
	 * "Update appointment set elig_status='" + EligStatus +
	 * "',elig_date=GetDate(),elig_response='" + ElgResp.replaceAll("'", "`") + "'"
	 * + " Where appointment_id='" + appointment_Id + "'"); }
	 */

	@Override
	public List<ORMEncounterCountDayWise> getDayWiseRpt(SearchCriteria searchCriteria) {
		String year_month = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "yearMonth":
					if (pram.getValue() != "" && pram.getValue() != null) {
						year_month = pram.getValue();
					}
					break;
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("year_month", year_month.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		List<ORMEncounterCountDayWise> lst = db.getStoreProcedureData("spGetMonthlyEncountersCount_daywise",
				ORMEncounterCountDayWise.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMEncounterCountMonthWise> getProviderWiseRpt(SearchCriteria searchCriteria) {
		String month = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "provyear":
					if (pram.getValue() != "" && pram.getValue() != null) {
						month = pram.getValue();
					}
					break;
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("year", month.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		List<ORMEncounterCountMonthWise> lst = db.getStoreProcedureData("spGetProviderEncountersCount_monthwise",
				ORMEncounterCountMonthWise.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMEncounterCountMonthWise> getLocationWiseRpt(SearchCriteria searchCriteria) {
		String month = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "locyear":
					if (pram.getValue() != "" && pram.getValue() != null) {
						month = pram.getValue();
					}
					break;
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("year", month.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		List<ORMEncounterCountMonthWise> lst = db.getStoreProcedureData("spGetLocationEncountersCount_monthwise",
				ORMEncounterCountMonthWise.class, lstParam);
		return lst;
	}

	/*
	 * @Override public Long saveupdateDailyDeposit(ORMDailyDeposits obj) {
	 * System.out.println("in save function");
	 * obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
	 * 
	 * if (obj.getDailydeposit_id() != null && obj.getDailydeposit_id() != 0) { if
	 * (db.SaveEntity(obj, Operation.EDIT) > 0) { System.out.println("in EDIT");
	 * return obj.getDailydeposit_id();} else return (long) 0; } else {
	 * obj.setDate_created(DateTimeUtil.getCurrentDateTime());
	 * obj.setDailydeposit_id(db.IDGenerator("dailydeposit",
	 * Long.parseLong(obj.getPractice_id())));
	 * 
	 * if (db.SaveEntity(obj, Operation.ADD) > 0) { System.out.println("in ADD");
	 * return obj.getDailydeposit_id();} else return (long) 0; } }
	 */
	public List<ORMDocumentPath> getDocPath(String practice_id, String doc_category) {
		// TODO Auto-generated method stub
		String query = " select top 1 id,category_name,upload_path,download_path from document_path where category_name='"
				+ doc_category + "' and practice_id=" + practice_id + "";

		return db.getQueryData(query, ORMDocumentPath.class);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveupdateDailyDeposit(ORMDailyDeposits patDoc, MultipartFile docFile,
			String docCategory) {
		// TODO Auto-generated method stub
		int result = 0;
		String link = "";

		Long docId = db.IDGenerator("dailydeposit", Long.parseLong(patDoc.getPractice_id()));

		String uploadPath = "";
		String autoGeneratedFileName = "";
		String docCat = "EOB";
		ORMDocumentPath objPath = generalDAOImpl.getDocumentPathByCategory(Long.parseLong(patDoc.getPractice_id()),
				"EOB");

		if (objPath != null) {
			uploadPath = objPath.getUpload_path();
		}

		autoGeneratedFileName = docId.toString() + FileUtil.GetDatetimeFileName() + "."
				+ docFile.getOriginalFilename().substring(docFile.getOriginalFilename().lastIndexOf('.') + 1);

		UploadFile uploadFile = FileUtil.UploadDocumentYearMonthDayWise(docFile, uploadPath,
				patDoc.getPractice_id().toString(), docCat, autoGeneratedFileName);

		if (uploadFile != null) {
			link = uploadFile.getLink();
		}
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (patDoc.getDailydeposit_id() == null) {
			patDoc.setDailydeposit_id(docId);
			patDoc.setDate_created(DateTimeUtil.getCurrentDateTime());
			patDoc.setDate_modified(DateTimeUtil.getCurrentDateTime());

			if (uploadFile != null) {
				patDoc.setFile_link(link);
			}
			result = db.SaveEntity(patDoc, Operation.ADD);
		} else {
			if (uploadFile != null) {
				patDoc.setFile_link(link);
			}
			patDoc.setDate_modified(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(patDoc, EnumUtil.Operation.EDIT);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An error occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("File has been uploaded/saved successfully.");
			resp.setResult(link);
		}
		return resp;

		/*
		 * System.out.println("Data Received: "+patDoc.toString()); DateFormat
		 * dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 * 
		 * Date date = new Date(); String directory=""; String category_path=""; String
		 * originalFilename=""; Boolean isDocUploaded=false;
		 * 
		 * Long generatedId;
		 * 
		 * 
		 * if (docFile!=null && !docFile.isEmpty()) { try {
		 * 
		 * List<ORMDocumentPath> lstPath =
		 * getDocPath(patDoc.getPractice_id(),docCategory);
		 * System.out.println("Get doc"+lstPath.size());
		 * 
		 * if(!lstPath.isEmpty()) { category_path=lstPath.get(0).getUpload_path();
		 * category_path=category_path+"\\"+patDoc.getPractice_id()+"\\"+docCategory;
		 * 
		 * System.out.println("Directory"+category_path); }
		 * directory=GeneralOperations.checkPathYearMonthWise(patDoc.getPractice_id(),
		 * lstPath.get(0).getUpload_path().toString(),docCategory);
		 * 
		 * System.out.println("directory "+directory); originalFilename=
		 * docFile.getOriginalFilename();
		 * 
		 * } catch (Exception e) {
		 * System.out.println("Picture Upload Error:"+e.getMessage()); } }
		 * 
		 * if (patDoc.getDailydeposit_id() != null &&
		 * patDoc.getDailydeposit_id().toString() != "") { if (docFile!=null &&
		 * !docFile.isEmpty()) { try{ String
		 * fileName=GeneralOperations.GetDatetimeFileName()+"."+FilenameUtils.
		 * getExtension(originalFilename); System.out.println("fileName"+fileName); File
		 * destinationFile = new
		 * File(GeneralOperations.CheckPath(category_path+"\\"+directory),fileName);
		 * docFile.transferTo(destinationFile);
		 * System.out.println("destinationFile"+directory);
		 * System.out.println("fileName"+fileName);
		 * 
		 * patDoc.setFile_link(directory+"\\"+fileName); isDocUploaded=true;
		 * 
		 * } catch (Exception e) {
		 * System.out.println("Deposit Upload Error:"+e.getMessage()); } }
		 * 
		 * if(isDocUploaded) { //System.out.println(""+e.getMessage());
		 * if(db.SaveEntity(patDoc,Operation.EDIT)>0) { generatedId =
		 * patDoc.getDailydeposit_id();//Long.parseLong(patDoc.getDailydeposit_id().
		 * toString()); } else { generatedId=(long) 0; } } else { generatedId=(long) 0;
		 * } } else {
		 * //patDoc.setPatient_document_id(Long.toString(db.IDGenerator("EOB",Long.
		 * parseLong(patDoc.getPractice_id()))));
		 * patDoc.setDailydeposit_id(db.IDGenerator("EOB",
		 * Long.parseLong(patDoc.getPractice_id())));
		 * patDoc.setDate_created(dateFormat.format(date));
		 * patDoc.setDate_modified(dateFormat.format(date));
		 * 
		 * if (docFile!=null && !docFile.isEmpty()) { try {
		 * //directory=directory+"\\"+empDoc.getEmp_id()+"\\"; String
		 * fileName=GeneralOperations.GetDatetimeFileName()+"."+FilenameUtils.
		 * getExtension(originalFilename);
		 * System.out.println("Final path"+category_path+"\\"+directory); File
		 * destinationFile = new
		 * File(GeneralOperations.CheckPath(category_path+"\\"+directory),fileName);
		 * System.out.println("destinationFile path" + destinationFile.toString());
		 * docFile.transferTo(destinationFile);
		 * 
		 * patDoc.setFile_link(directory+"\\"+fileName); isDocUploaded=true;
		 * 
		 * } catch (Exception e) {
		 * System.out.println("Deposit Upload Error:"+e.getMessage()); } }
		 * 
		 * if(isDocUploaded) { if(db.SaveEntity(patDoc,Operation.ADD)>0) {
		 * //generatedId=Long.parseLong(patDoc.getPatient_document_id()); generatedId =
		 * patDoc.getDailydeposit_id(); } else { generatedId=(long) 0; } } else {
		 * generatedId=(long) 0; } }
		 * System.out.println("Return "+Long.toString(generatedId)); return generatedId;
		 */
	}

	@Override
	public List<ORMGetDailyDepositReports> getDailyDeposit(SearchCriteria searchCriteria) {
		String criteria = " where ISNULL(deleted,0)<>1 and practice_id ='" + searchCriteria.getPractice_id().toString()
				+ "' ";
		String dtFrom = "";
		String dtTo = "";
		String datetype = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "txt_ins_name":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and insurance_name= '" + pram.getValue() + "' ";
					}
					break;
				case "txt_checkno":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and substring(check_number, patindex('%[^0]%',check_number), len(check_number))= substring('"
								+ pram.getValue() + "',patindex('%[^0]%','" + pram.getValue() + "'),len('"
								+ pram.getValue() + "')) ";
					}
					break;
				case "ddldatetype":
					if (pram.getValue() != "" && pram.getValue() != null) {
						datetype = pram.getValue();
					}
					break;
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtFrom = pram.getValue();
					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtTo = pram.getValue();
					}
					break;
				default:
					break;
				}
			}
		}
		if (datetype.toString() == "false") {
			if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
				criteria += " and convert(datetime,convert(varchar,check_date,101)) between convert(datetime,'" + dtFrom
						+ "') and convert(datetime,'" + dtTo + "')";
			}
		} else if (datetype.toString() == "true") {
			if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
				criteria += " and convert(datetime,convert(varchar,check_received_date,101)) between convert(datetime,'"
						+ dtFrom + "') and convert(datetime,'" + dtTo + "')  ";
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));

		List<ORMGetDailyDepositReports> lst = db.getStoreProcedureData("sp_SearchDailyDeposit",
				ORMGetDailyDepositReports.class, lstParam);

		return lst;
	}

	@Override
	public int deleteDailyDeposit(ORMDeleteRecord objDelete) {
		return db.deleteRecord(objDelete);
	}

	@Override
	public List<ORMDailyDepositReports> searchByMonthYear(SearchCriteria searchCriteria) {
		String year_month = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "yearMonth":
					if (pram.getValue() != "" && pram.getValue() != null) {
						year_month = pram.getValue();
					}
					break;
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("year_month", year_month.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		List<ORMDailyDepositReports> lst = db.getStoreProcedureData("spGetDailydepositReports",
				ORMDailyDepositReports.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMACUReport> getCallsData(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String practiceId = searchCriteria.getPractice_id().toString();
		String dtFrom = "";
		String rptType = "";
		String dtTo = "";
		String locationId = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtFrom = pram.getValue();

					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtTo = pram.getValue();

					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null) {
						locationId = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("location_id", locationId.toString().equals("-1") ? "" : locationId.toString(),
				String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("FromDate", dtFrom, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("ToDate", dtTo, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practiceId, String.class, ParameterMode.IN));

		List<ORMACUReport> lst = db.getStoreProcedureData("spGetACU_Report", ORMACUReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMAppointmentSMSReport> getAppointmentsms(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String practiceId = searchCriteria.getPractice_id().toString();
		String dtFrom = "";
		String rptType = "";
		String dtTo = "";
		String locationId = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtFrom = pram.getValue();

					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtTo = pram.getValue();

					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null) {
						locationId = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("location_id", locationId.toString().equals("-1") ? "" : locationId.toString(),
				String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("FromDate", dtFrom, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("ToDate", dtTo, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practiceId, String.class, ParameterMode.IN));

		List<ORMAppointmentSMSReport> lst = db.getStoreProcedureData("spGetAppointmentSMS_Report",
				ORMAppointmentSMSReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMGYNMissingPatient> getgynMissedVisit(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", practice_id, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetGYNMissingPatient", ORMGYNMissingPatient.class, lstParam);
	}

	@Override
	public List<ORMOBGynReport> getPregnancyData(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String criteria = " where ISNULL(omi.deleted,0)<>1 and omi.practice_id = '" + searchCriteria.getPractice_id()
				+ "' ";
		String dateFrom = "";
		String dateTo = "";
		String cmbProvider = "";
		String cmbCriteria = "";
		String rbPregComp = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "fromDate":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dateFrom = pram.getValue();

					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dateTo = pram.getValue();

					}
					break;
				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null) {
						cmbProvider = pram.getValue();
						break;
					}
				case "cmbCriteria":
					if (pram.getValue() != "" && pram.getValue() != null) {
						cmbCriteria = pram.getValue();

					}
					break;
				case "rbPregComp":
					if (pram.getValue() != "" && pram.getValue() != null) {
						rbPregComp = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		if (cmbCriteria.equals("Registered Date") && dateFrom != "" && dateTo != "") {
			criteria += " and convert(date,omi.date_created) between convert(date,'" + dateFrom
					+ "') and convert(date,'" + dateTo + "') ";
		}
		if (cmbCriteria.equals("EDD") && dateFrom != "" && dateTo != "") {
			criteria += " and convert(date,ci.edd_date) between convert(date,'" + dateFrom + "') and convert(date,'"
					+ dateTo + "') ";
		}
		if (cmbProvider != "") {
			criteria += " and omi.provider_name ='" + cmbProvider + "' ";
		}
		if (rbPregComp.equals("true")) {
			criteria += " and isnull(omi.completed,0)=1";
		} else {
			criteria += " and isnull(omi.completed,0)<>1";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));

		List<ORMOBGynReport> lst = db.getStoreProcedureData("getOBGynReport", ORMOBGynReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMPatientPayer> getPatientPayer(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String cmbCriteria = "";
		String payerNumber = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "cmbCriteria":
					if (pram.getValue() != "" && pram.getValue() != null) {
						cmbCriteria = pram.getValue();

					}
					break;
				case "payerId":
					if (pram.getValue() != "" && pram.getValue() != null) {
						payerNumber = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		if (cmbCriteria.toLowerCase().equals("selfpay") == false) {
			lstParam.add(new SpParameters("payerId", payerNumber, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("insurace_type", cmbCriteria, String.class, ParameterMode.IN));
			return db.getStoreProcedureData("spGet_PayerWisePatientRptSearch", ORMPatientPayer.class, lstParam);
		} else {
			return db.getStoreProcedureData("spGet_RptPayerwise_SelfPatient", ORMPatientPayer.class, lstParam);
		}

	}

	@Override
	public List<ORMPatientPayerDetails> getPatientPayerDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String cmbCriteria = "";
		String payerNumber = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "cmbCriteria":
					if (pram.getValue() != "" && pram.getValue() != null) {
						cmbCriteria = pram.getValue();

					}
					break;
				case "payerId":
					if (pram.getValue() != "" && pram.getValue() != null) {
						payerNumber = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("payerId", payerNumber, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("insurace_type", cmbCriteria, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGet_PayerWisePatientDetails", ORMPatientPayerDetails.class, lstParam);

	}

	@Override
	public List<ORMDashBoarCashReg> getCashPaymentDetails(SearchCriteria searchCriteria) {
		String practiceID = "";//
		String fromDate = "";//
		String toDate = "";
		String provider = "";
		String location = "";
		String pay_method = "";
		practiceID = searchCriteria.getPractice_id().toString();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {

				case "userDateFrom":
					fromDate = pram.getValue();
					break;
				case "userDateTo":
					toDate = pram.getValue();
					break;
				case "ddlUserProvider":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().toString().equals("null") == false
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						provider = pram.getValue();
						break;
					}
				case "ddlUserLocation":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().toString().equals("null") == false
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						location = pram.getValue();
						break;
					}
				case "payMethod":
					pay_method = pram.getValue();
					break;

				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practiceID", practiceID.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("fromDate", fromDate.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("toDate", toDate.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("provider", provider.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("location", location.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pay_method", pay_method.toString(), String.class, ParameterMode.IN));

		List<ORMDashBoarCashReg> lst = db.getStoreProcedureData("proGetCashRegisterChartDetailRpt",
				ORMDashBoarCashReg.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMGetPatientPayment> getPatientPaymentDetails(SearchCriteria searchCriteria) {

		String criteria = " and cr.practice_id='" + searchCriteria.getPractice_id().toString() + "' ";

		String dtFrom = "";
		String dtTo = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				if (pram.getValue() != "" && pram.getValue() != null) {
					switch (pram.getName()) {
					case "patPayDdldatetype":
						criteria += " and cr.provider_id='" + pram.getValue() + "' ";
						break;
					case "patPayDdlLocation":
						if (pram.getValue() != "" && pram.getValue() != null
								&& pram.getValue().toString().equals("null") == false
								&& pram.getValue().equalsIgnoreCase("All") == false) {
							criteria += " and cr.location_id='" + pram.getValue() + "' ";
						}
						break;
					case "patPayDdlProvider":
						if (pram.getValue() != "" && pram.getValue() != null
								&& pram.getValue().toString().equals("null") == false
								&& pram.getValue().equalsIgnoreCase("All") == false) {
							criteria += " and cr.provider_id='" + pram.getValue() + "' ";
						}
						break;

					case "rdoTypePatPay":
						if (pram.getValue().toLowerCase() == "cash") {
							criteria += " and cr.payment_method='CASH' ";
						} else if (pram.getValue().toLowerCase() == "credit card") {
							criteria += " and cr.payment_method='CREDIT CARD' ";
						}
						break;
					case "patPayDateFrom":
						dtFrom = pram.getValue();
						break;
					case "patPayDateTo":
						dtTo = pram.getValue();
						break;
					default:
						break;

					}
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += " and convert(date,convert(varchar,cr.date_created,101)) between '" + dtFrom + "' and '" + dtTo
					+ "'";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientPayment> lst = db.getStoreProcedureData("spGetPatientPayment", ORMGetPatientPayment.class,
				lstParam);
		return lst;
	}

	@Override
	public List<ORMGetCollectPayment> getCollectPaymentDetails(SearchCriteria searchCriteria) {

		String criteria = " and cp.practice_id='" + searchCriteria.getPractice_id().toString() + "' ";

		String dtFrom = "";
		String dtTo = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getValue() != "" && pram.getValue() != null) {
					switch (pram.getName()) {

					case "collPayDdlLocation":
						if (pram.getValue() != "" && pram.getValue() != null
								&& pram.getValue().toString().equals("null") == false
								&& pram.getValue().equalsIgnoreCase("All") == false) {
							criteria += " and cp.location_id= '" + pram.getValue() + "' ";
						}
						break;
					case "collPayDateFrom":
						dtFrom = pram.getValue();
						break;
					case "collPayDateTo":
						dtTo = pram.getValue();
						break;

					default:
						break;
					}
				}
			}
		}

		if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += "  and convert(date,convert(varchar,cp.date_created,101)) between '" + dtFrom + "' and '" + dtTo
					+ "'";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));

		List<ORMGetCollectPayment> lst = db.getStoreProcedureData("spGetCollectPayment", ORMGetCollectPayment.class,
				lstParam);
		return lst;
	}

	@Override
	public List<ORMgetYearlyCashRegisterColReport> getYearlyCollectionDetails(SearchCriteria searchCriteria) {
		String year = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "year":
					year = pram.getValue();
					break;

				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("year", year.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		List<ORMgetYearlyCashRegisterColReport> lst = db.getStoreProcedureData(
				"spGetYearlyCashRegisterCollectionReport", ORMgetYearlyCashRegisterColReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMGetClaimsReport> getClaimdetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		// ***********************************************
		// getReportClaimdetailFooter (if any change in this report criteria then also
		// do in others)
		// *****************************************************
		String criteria = " and c.practice_id='" + searchCriteria.getPractice_id().toString() + "' ";
		String pageIndex = "0";
		String pageSize = "0";
		// String dtFrom = "";
		// String dtTo = "";
		// String dateType = "";
		// String status = "";
		// String labCat = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {

				case "date_from":
					if (pram.getOption().equalsIgnoreCase("DOS")) {
						criteria += " and convert(date,c.dos) >= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_created")) {
						criteria += " and convert(date,c.date_created) >= convert(date,'" + pram.getValue() + "')";
					}

					break;
				case "date_to":
					if (pram.getOption().equalsIgnoreCase("DOS")) {
						criteria += " and convert(date,c.dos) <= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_created")) {
						criteria += " and convert(date,c.date_created) <= convert(date,'" + pram.getValue() + "')";
					}

					break;
				/*
				 * case "date_type": if (pram.getValue() != "" && pram.getValue() != null) {
				 * getClaimdetails = pram.getValue().toLowerCase(); break; } case "dateFrom":
				 * dtFrom = pram.getValue(); break; case "dateTo": dtTo = pram.getValue();
				 * break;
				 */
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and c.patient_id='" + pram.getValue() + "'";
					}
					break;
				case "cmbStatus":

					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						if (pram.getValue().equalsIgnoreCase("unprocessed")) {
							criteria += " and ISNULL(submission_status,'') = ''  and ISNULL(c.self_pay,0)<>1 and  isnull(c.draft,0)<>1 ";
						} else if (pram.getValue().equalsIgnoreCase("processed")) {
							criteria += " and isnull(c.deleted,0)<>1  and ISNULL(submission_status,'') <> ''  and ISNULL(c.self_pay,0)<>1 and  isnull(c.draft,0)<>1 ";
						} else if (pram.getValue().equalsIgnoreCase("self")) {
							criteria += " and isnull(c.self_pay,0)=1  and  isnull(c.draft,0)<>1 ";
						} else if (pram.getValue().equalsIgnoreCase("draft")) {
							criteria += " and isnull(c.draft,0)=1 and ISNULL(c.submission_status,'') = ''  and isnull(c.not_bill,0)<>1 ";
						} else if (pram.getValue().equalsIgnoreCase("follow up")) {
							criteria += " and isnull(c.followup,0)=1 ";
						} else if (pram.getValue().equalsIgnoreCase("don't bill")) {
							criteria += " and isnull(c.not_bill,0)=1 ";
						} else if (pram.getValue().equalsIgnoreCase("EAP")) {
							criteria += " and isnull(c.eap,0)=1 ";
						}
					}

					/*
					 * if (pram.getValue() != "" && pram.getValue() != null) { status =
					 * pram.getValue().toLowerCase(); break; }
					 */
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and c.location_id= '" + pram.getValue() + "' ";
					}
					break;
				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and c.attending_physician= '" + pram.getValue() + "' ";

					}
					break;
				case "cmbUser":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and c.created_user= '" + pram.getValue() + "' ";
					}
					break;
				case "insuranceID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and isnull(ci.insurance_id,'') in ('" + pram.getValue()
								+ "')  and isnull(ci.deleted,0)<>1 ";

					}
					break;
				case "cmbLabCat":
					if (pram.getValue() != "" && pram.getValue() != null) {
						// labCat += pram.getValue();

						criteria += " and cp.proc_code in (select proc_code from lab_category_detail ld "
								+ "join lab_category_sub ls on ls.sub_category_id = ld.sub_category_id and ISNULL(ls.deleted,0)<>1 "
								+ "join lab_categories lc on lc.category_id = ls.category_id and ISNULL(lc.deleted,0)<>1 "
								+ "where isnull(ld.deleted,0)<>1 and lc.category_id = '" + pram.getValue() + "') ";

					}
					break;
				case "cmbFacility":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and c.facility_id= '" + pram.getValue() + "' ";
					}
					break;
				case "pageIndex":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageIndex = pram.getValue();

					}
					break;
				case "pageSize":
					if (pram.getValue() != "" && pram.getValue() != null) {
						pageSize = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		///////////
		/*
		 * if (rdoBtnRange.equalsIgnoreCase("dos") && !dtFrom.isEmpty() &&
		 * !dtTo.isEmpty()) { criteria +=
		 * " and convert(datetime,convert(varchar,c.dos,101)) between convert(datetime,'"
		 * + dtFrom + "') and convert(datetime,'" + dtTo + "')"; } if
		 * (rdoBtnRange.equalsIgnoreCase("entered") && !dtFrom.isEmpty() &&
		 * !dtTo.isEmpty()) { criteria +=
		 * " and convert(datetime,convert(varchar,c.date_created,101)) between convert(datetime,'"
		 * + dtFrom + "') and convert(datetime,'" + dtTo + "')"; }
		 */
		// criteria +=
		/*
		 * if (status.equalsIgnoreCase("unprocessed")) { criteria +=
		 * " and ISNULL(submission_status,'') = ''  and ISNULL(c.self_pay,0)<>1 and  isnull(c.draft,0)<>1 "
		 * ; } else if (status.equalsIgnoreCase("processed")) { criteria +=
		 * " and isnull(c.deleted,0)<>1  and ISNULL(submission_status,'') <> ''  and ISNULL(c.self_pay,0)<>1 and  isnull(c.draft,0)<>1 "
		 * ; } else if (status.equalsIgnoreCase("self")) { criteria +=
		 * " and isnull(c.self_pay,0)=1  and  isnull(c.draft,0)<>1 "; } else if
		 * (status.equalsIgnoreCase("draft")) { criteria +=
		 * " and isnull(c.draft,0)=1 and ISNULL(c.submission_status,'') = ''  and isnull(c.not_bill,0)<>1 "
		 * ; } else if (status.equalsIgnoreCase("follow up")) { criteria +=
		 * " and isnull(c.followup,0)=1 "; } else if
		 * (status.equalsIgnoreCase("don't bill")) { criteria +=
		 * " and isnull(c.not_bill,0)=1 "; }
		 */

		/*
		 * if (labCat != "") { criteria = criteria +
		 * " and cp.proc_code in (select proc_code from lab_category_detail ld " +
		 * "join lab_category_sub ls on ls.sub_category_id = ld.sub_category_id and ISNULL(ls.deleted,0)<>1 "
		 * +
		 * "join lab_categories lc on lc.category_id = ls.category_id and ISNULL(lc.deleted,0)<>1 "
		 * + "where isnull(ld.deleted,0)<>1 and lc.category_id = '" + labCat + "') "; }
		 */
		///////////

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageIndex", pageIndex.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("pageSize", pageSize.toString(), String.class, ParameterMode.IN));

		List<ORMGetClaimsReport> lst = db.getStoreProcedureData("spGetClaimsReport_paging", ORMGetClaimsReport.class,
				lstParam);

		return lst;
	}

	@Override
	public List<ORMGetClaimReportFooter> getReportClaimdetailFooter(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String criteria = " and c.practice_id='" + searchCriteria.getPractice_id().toString() + "' ";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {

				case "date_from":
					if (pram.getOption().equalsIgnoreCase("DOS")) {
						criteria += " and convert(date,c.dos) >= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_created")) {
						criteria += " and convert(date,c.date_created) >= convert(date,'" + pram.getValue() + "')";
					}

					break;
				case "date_to":
					if (pram.getOption().equalsIgnoreCase("DOS")) {
						criteria += " and convert(date,c.dos) <= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_created")) {
						criteria += " and convert(date,c.date_created) <= convert(date,'" + pram.getValue() + "')";
					}

					break;
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and c.patient_id='" + pram.getValue() + "'";
					}
					break;
				case "cmbStatus":

					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						if (pram.getValue().equalsIgnoreCase("unprocessed")) {
							criteria += " and ISNULL(submission_status,'') = ''  and ISNULL(c.self_pay,0)<>1 and  isnull(c.draft,0)<>1 ";
						} else if (pram.getValue().equalsIgnoreCase("processed")) {
							criteria += " and isnull(c.deleted,0)<>1  and ISNULL(submission_status,'') <> ''  and ISNULL(c.self_pay,0)<>1 and  isnull(c.draft,0)<>1 ";
						} else if (pram.getValue().equalsIgnoreCase("self")) {
							criteria += " and isnull(c.self_pay,0)=1  and  isnull(c.draft,0)<>1 ";
						} else if (pram.getValue().equalsIgnoreCase("draft")) {
							criteria += " and isnull(c.draft,0)=1 and ISNULL(c.submission_status,'') = ''  and isnull(c.not_bill,0)<>1 ";
						} else if (pram.getValue().equalsIgnoreCase("follow up")) {
							criteria += " and isnull(c.followup,0)=1 ";
						} else if (pram.getValue().equalsIgnoreCase("don't bill")) {
							criteria += " and isnull(c.not_bill,0)=1 ";
						} else if (pram.getValue().equalsIgnoreCase("EAP")) {
							criteria += " and isnull(c.eap,0)=1 ";
						}
					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and c.location_id= '" + pram.getValue() + "' ";
					}
					break;
				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and c.attending_physician= '" + pram.getValue() + "' ";

					}
					break;
				case "cmbUser":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and c.created_user= '" + pram.getValue() + "' ";
					}
					break;
				case "insuranceID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and isnull(ci.insurance_id,'') in ('" + pram.getValue()
								+ "')  and isnull(ci.deleted,0)<>1 ";

					}
					break;
				case "cmbLabCat":
					if (pram.getValue() != "" && pram.getValue() != null) {
						// labCat += pram.getValue();

						criteria += " and cp.proc_code in (select proc_code from lab_category_detail ld "
								+ "join lab_category_sub ls on ls.sub_category_id = ld.sub_category_id and ISNULL(ls.deleted,0)<>1 "
								+ "join lab_categories lc on lc.category_id = ls.category_id and ISNULL(lc.deleted,0)<>1 "
								+ "where isnull(ld.deleted,0)<>1 and lc.category_id = '" + pram.getValue() + "') ";

					}
					break;
				case "cmbFacility":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and c.facility_id= '" + pram.getValue() + "' ";
					}
					break;
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));

		List<ORMGetClaimReportFooter> lst = db.getStoreProcedureData("spGetClaimsReport_footer",
				ORMGetClaimReportFooter.class, lstParam);

		return lst;
	}

	//
	@Override
	public List<ORMpaymentcategories> getPaymentCategories(String practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("getpaymentcategories", ORMpaymentcategories.class, lstParam);

	}

	@Override
	public List<ORMpaymentcategoryprocedures> getPaymentcategories_Procedures(String category_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("category_id", category_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("getpaymentprocedures_forcategory", ORMpaymentcategoryprocedures.class,
				lstParam);
	}

	//
	@Override
	public List<ORMgetPaymentCategoriesReport> getPaymentCategoriesDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		// String criteria = " and c.practice_id='" +
		// searchCriteria.getPractice_id().toString() + "' ";
		String criteria = " where ";

		String dtFrom = "";
		String dtTo = "";
		String rdoclaimtype = "";
		// String status = "";
		// String labCat = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "category_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " pc.category_id in (" + pram.getValue() + ")";
					}
					break;
				case "location_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and cl.location_id = '" + pram.getValue() + "' ";
					}
					break;
				case "attending_physician":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and cl.attending_physician = '" + pram.getValue() + "' ";
					}
					break;
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtFrom = pram.getValue();
					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtTo = pram.getValue();
					}
					break;
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and pt.patient_id = '" + pram.getValue() + "'";
					}
					break;

				case "rdoclaimtype":
					if (pram.getValue() != "" && pram.getValue() != null) {
						rdoclaimtype = pram.getValue();
					}
					break;

				default:
					break;
				}
			}
		}

		///////////
		if (dtFrom.isEmpty() && !dtTo.isEmpty()) {
			criteria += " and cl.dos between convert(date,'" + dtFrom + "') and convert(date,'" + dtTo + "')";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));

		// List<ORMgetPaymentCategoriesReport> lst = null;
		String procedure = "";
		if (rdoclaimtype.equalsIgnoreCase("0")) {
			// lst = db.getStoreProcedureData("getpaymentcategoriesreport",
			// ORMgetPaymentCategoriesReport.class, lstParam);
			procedure = "getpaymentcategoriesreport";
		} else if (rdoclaimtype.equalsIgnoreCase("1")) {
			// lst = db.getStoreProcedureData("getpaymentcategoriesreport_claimpayment",
			// ORMgetPaymentCategoriesReport.class, lstParam);
			procedure = "getpaymentcategoriesreport_claimpayment";
		}
		List<ORMgetPaymentCategoriesReport> lst = db.getStoreProcedureData(procedure.toString(),
				ORMgetPaymentCategoriesReport.class, lstParam);
		return lst;
	}

	@Override
	public Long saveupdatePaymentCategory(ORMpaymentcategories obj) {
		System.out.println(obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		obj.setDeleted(false);
		if (obj.getCategory_id() != null && obj.getCategory_id() != 0) {
			System.out.println("IN EDIT.");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return obj.getCategory_id();
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setCategory_id(db.IDGenerator("payment_categories", Long.parseLong(obj.getPractice_id())));
			System.out.println("IN SAVE.");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return obj.getCategory_id();
			else
				return (long) 0;
		}
	}

	@Override
	public int deleteCategory(ORMDeleteRecord objDelete) {
		return db.deleteRecord(objDelete);
	}

	@Override
	public int deleteProcedure(ORMDeleteRecord objDelete) {
		return db.deleteRecord(objDelete);
	}

	/*
	 * @Override public Long saveProcedures(List<ORMpaymentcategoryprocedures> obj)
	 * { Long result = null; if(obj!=null) { for(SpParameters param : obj) {
	 * 
	 * System.out.println(obj.toString());
	 * obj.setDate_modified(DateTimeUtil.getCurrentDateTime()); obj.setDeleted("0");
	 * obj.setDate_created(DateTimeUtil.getCurrentDateTime());
	 * obj.setCategory_procedure_id(db.IDGenerator("payment_category_procedures",
	 * Long.parseLong(obj.getPractice_id()))); if (db.SaveEntity(obj, Operation.ADD)
	 * > 0) result = obj.getCategory_procedure_id(); else result = (long) 0; }
	 * return result; } }
	 */
	@Override
	public Long saveProcedures(List<ORMpaymentcategoryprocedures> obj) {
		Long result = (long) 1;

		if (obj != null && obj.size() > 0) {

			for (ORMpaymentcategoryprocedures objSave : obj) {

				objSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
				objSave.setDeleted("0");
				if (objSave.getCategory_procedure_id() == null) // New
				{
					try {
						objSave.setCategory_procedure_id(db.IDGenerator("payment_category_procedures",
								Long.parseLong(objSave.getPractice_id())));
						objSave.setDate_created(DateTimeUtil.getCurrentDateTime());
						db.SaveEntity(objSave, Operation.ADD);
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		}

		return result;
	}

	@Override
	public List<ORMCrossRpt> getCrossReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String practiceId = searchCriteria.getPractice_id().toString();
		String dtFrom = "";
		String dtTo = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtFrom = pram.getValue();

					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtTo = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("datefrom", dtFrom, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("dateto", dtTo, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practiceId, String.class, ParameterMode.IN));

		List<ORMCrossRpt> lst = db.getStoreProcedureData("spGetTranscriptionCrossRpt", ORMCrossRpt.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMLabCompleted> getLabCompRpt(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String practiceId = searchCriteria.getPractice_id().toString();
		String dtFrom = "";
		String dtTo = "";
		String patient_id = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtFrom = pram.getValue();

					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtTo = pram.getValue();

					}
					break;
				case "patientID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						patient_id = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("date_from", dtFrom, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("date_to", dtTo, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practiceId, String.class, ParameterMode.IN));

		List<ORMLabCompleted> lst = db.getStoreProcedureData("spGetTrans_LabCompleted", ORMLabCompleted.class,
				lstParam);
		return lst;

	}

	@Override
	public List<ORMPatientCreationRpt> getPatCreation(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String practiceId = searchCriteria.getPractice_id().toString();
		String dtFrom = "";
		String dtTo = "";
		String Criteria = "";
		String locationId = "";
		String providerId = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "fromDate":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtFrom = pram.getValue();

					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtTo = pram.getValue();

					}
					break;
				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null) {
						providerId = pram.getValue();

					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null) {
						locationId = pram.getValue();

					}
					break;
				case "cmbCriteria":
					if (pram.getValue() != "" && pram.getValue() != null) {
						Criteria = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("date_from", dtFrom, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("date_to", dtTo, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practiceId, String.class, ParameterMode.IN));
		if (Criteria.equals("By Claim")) {
			lstParam.add(new SpParameters("provider_id", providerId, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("location_id", locationId, String.class, ParameterMode.IN));
		} else {
			lstParam.add(new SpParameters("provider_id", "", String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("location_id", "", String.class, ParameterMode.IN));
		}

		List<ORMPatientCreationRpt> lst = db.getStoreProcedureData("getPatientCreation_Report",
				ORMPatientCreationRpt.class, lstParam);
		return lst;

	}

	@Override
	public List<ORMGetUserAdministrationModules> getusermodules(String role_id, String practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("role_id", role_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetRoleAdministrationModules", ORMGetUserAdministrationModules.class,
				lstParam);

	}

	@Override
	public List<ORMGetProviderParollReport> searchPayRoll(SearchCriteria searchCriteria) {

		String cmbProvider = "";
		String chkbox_Enrolled = "";
		String cmbLocation = "";
		String cmbMonthsdaywise = "";
		String chkbox_aca = "";
		String strProce = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().toString().equals("null") == false
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						cmbProvider = pram.getValue();

					}
					break;
				case "chkbox_Enrolled":
					if (pram.getValue() != "" && pram.getValue() != null) {
						chkbox_Enrolled = pram.getValue();

					}
					break;
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null
							&& pram.getValue().toString().equals("null") == false
							&& pram.getValue().equalsIgnoreCase("All") == false) {
						cmbLocation = pram.getValue();

					}
					break;
				case "cmbMonthsdaywise":
					if (pram.getValue() != "" && pram.getValue() != null) {
						cmbMonthsdaywise = pram.getValue();

					}
					break;
				case "chkbox_aca":
					if (pram.getValue() != "" && pram.getValue() != null) {
						chkbox_aca = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}

		if (chkbox_Enrolled.equals("true")) {
			strProce = "spGetProviderPayrollReport";
		} else {
			strProce = "spGetProviderPayrollReportNotEnrolled";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("provider_id", cmbProvider, String.class, ParameterMode.IN));
		// lstParam.add(new SpParameters("practice_id", chkbox_Enrolled, String.class,
		// ParameterMode.IN));
		lstParam.add(new SpParameters("location_id", cmbLocation, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("year_month", cmbMonthsdaywise, String.class, ParameterMode.IN));
		// lstParam.add(new SpParameters("role_id", txtYeardaywise, String.class,
		// ParameterMode.IN));
		lstParam.add(new SpParameters("includeACA", chkbox_aca, String.class, ParameterMode.IN));

		List<ORMGetProviderParollReport> lst = db.getStoreProcedureData(strProce, ORMGetProviderParollReport.class,
				lstParam);

		return lst;
	}

	@Override
	public List<ORMPayrollCategories> GetPayrollCategories(Long practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPayrollCategories", ORMPayrollCategories.class, lstParam);
	}

	@Override
	public List<ORMGetPayRollProviders> getPayRollCategoryProviders(String category_id, String practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("category_id", category_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPayrollProviders", ORMGetPayRollProviders.class, lstParam);

	}

	@Override
	public List<ORMGetPayRollCategoryProcedures> getPayRollCategoryProcedures(String category_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("category_id", category_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPayrollCategoryProcedures", ORMGetPayRollCategoryProcedures.class,
				lstParam);
	}

	@Override
	public Long saveupdateCategories(ORMPayrollCategories obj) {
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		obj.setDeleted(false);
		if (obj.getCategory_id() != null && obj.getCategory_id() != "") {
			System.out.println("EDIT...");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return (Long.parseLong(obj.getCategory_id()));
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setCategory_id(db.IDGenerator("payroll_categories", Long.parseLong(obj.getPractice_id())).toString());
			System.out.println("SAVE...");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return (Long.parseLong(obj.getCategory_id()));
			else
				return (long) 0;
		}
	}

	@Override
	public int deleteSeletedCategory(ORMDeleteRecord objDelete) {
		return db.deleteRecord(objDelete);
	}

	@Override
	public List<ORMGetPayrollVisitsDetails> getProviderPayrollVisits(SearchCriteria searchCriteria) {
		String SearchProviderID = "";
		String SearchLocationID = "";
		String SearchYearMonth = "";
		String proc_code = "";
		String SearchIsEnrolled = "";
		String strProce = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "SearchProviderID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchProviderID = pram.getValue();

					}
					break;
				case "SearchLocationID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchLocationID = pram.getValue();

					}
					break;
				case "SearchYearMonth":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchYearMonth = pram.getValue();

					}
					break;
				case "proc_code":
					if (pram.getValue() != "" && pram.getValue() != null) {
						proc_code = pram.getValue();

					}
					break;
				case "SearchIsEnrolled":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchIsEnrolled = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}
		if (SearchIsEnrolled.equals("true")) {
			strProce = "spGetProviderPayrollVisits";
		} else {
			strProce = "spGetProviderPayrollVisitsNotEnrolled";
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("provider_id", SearchProviderID, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("location_id", SearchLocationID, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("year_month", SearchYearMonth, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("proc_code", proc_code, String.class, ParameterMode.IN));
		List<ORMGetPayrollVisitsDetails> lst = db.getStoreProcedureData(strProce, ORMGetPayrollVisitsDetails.class,
				lstParam);
		return lst;
	}

	@Override
	public List<ORMGetPayrollCharges> getProviderPayrollCharges(SearchCriteria searchCriteria) {
		String SearchProviderID = "";
		String SearchLocationID = "";
		String SearchYearMonth = "";
		String proc_code = "";
		String SearchIsEnrolled = "";
		String strProce = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "SearchProviderID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchProviderID = pram.getValue();

					}
					break;
				case "SearchLocationID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchLocationID = pram.getValue();

					}
					break;
				case "SearchYearMonth":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchYearMonth = pram.getValue();

					}
					break;
				case "proc_code":
					if (pram.getValue() != "" && pram.getValue() != null) {
						proc_code = pram.getValue();

					}
					break;
				case "SearchIsEnrolled":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchIsEnrolled = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}
		if (SearchIsEnrolled.equals("true")) {
			strProce = "spGetProviderPayrollCharges";
		} else {
			strProce = "spGetProviderPayrollChargesNotEnrolled";
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("provider_id", SearchProviderID, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("location_id", SearchLocationID, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("year_month", SearchYearMonth, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("proc_code", proc_code, String.class, ParameterMode.IN));
		List<ORMGetPayrollCharges> lst = db.getStoreProcedureData(strProce, ORMGetPayrollCharges.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMGetPayrollPayment> getProviderPayrollPayments(SearchCriteria searchCriteria) {
		String SearchProviderID = "";
		String SearchLocationID = "";
		String SearchYearMonth = "";
		String proc_code = "";
		String SearchIsEnrolled = "";
		String SearchIncludeACA = "";
		String strProce = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "SearchProviderID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchProviderID = pram.getValue();

					}
					break;
				case "SearchLocationID":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchLocationID = pram.getValue();

					}
					break;
				case "SearchYearMonth":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchYearMonth = pram.getValue();

					}
					break;
				case "proc_code":
					if (pram.getValue() != "" && pram.getValue() != null) {
						proc_code = pram.getValue();

					}
					break;
				case "SearchIncludeACA":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchIncludeACA = pram.getValue();

					}
					break;
				case "SearchIsEnrolled":
					if (pram.getValue() != "" && pram.getValue() != null) {
						SearchIsEnrolled = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}
		if (SearchIsEnrolled.equals("true")) {
			strProce = "spGetProviderPayrollPayments";
		} else {
			strProce = "spGetProviderPayrollPaymentsNotEnrolled";
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("provider_id", SearchProviderID, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("location_id", SearchLocationID, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("year_month", SearchYearMonth, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("proc_code", proc_code, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("includeACA", SearchIncludeACA, String.class, ParameterMode.IN));
		List<ORMGetPayrollPayment> lst = db.getStoreProcedureData(strProce, ORMGetPayrollPayment.class, lstParam);
		return lst;
	}

	@Override
	public Long SavePayRollCategoryProcedures(List<ORMPayrollCategoryProcedures> objDetail) {
		int result = 0;
		for (ORMPayrollCategoryProcedures ormSave : objDetail) {
			ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (ormSave.getCategory_procedure_id() == null) {
				ormSave.setCategory_procedure_id(
						db.IDGenerator("payroll_category_procedures", Long.parseLong(ormSave.getPractice_id())));
				ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(ormSave, Operation.ADD);
			} else {
				result = db.SaveEntity(ormSave, Operation.EDIT);
			}
		}
		return (long) result;
	}

	@Override
	public Long SavePayRollCategoryProviders(List<ORMProviderPayrollCategories> objDetail) {
		int result = 0;
		for (ORMProviderPayrollCategories ormSave : objDetail) {
			ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (ormSave.getProvider_category_id() == null) {
				ormSave.setProvider_category_id(
						db.IDGenerator("payroll_category_procedures", Long.parseLong(ormSave.getPractice_id())));
				ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(ormSave, Operation.ADD);
			} else {
				result = db.SaveEntity(ormSave, Operation.EDIT);
			}
		}
		return (long) result;
	}
	// @Override
	// public int CheckIfCPTExists(SearchCriteria searchCriteria) {
	// return db.CheckIfCPTExists(searchCriteria);
	// }

	@Override
	public int CheckIfCPTExists(SearchCriteria searchCriteria) {
		String proc_code = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().toLowerCase().equals("proc_code")) {
					proc_code = pram.getValue();
				}
			}
		}
		String strQuery = "select count(proc_code) as scalar_value from payroll_category_procedures pcp inner join payroll_categories pc on pc.category_id = pcp.category_id where ISNULL(pcp.deleted,0)<>1 and ISNULL(pc.deleted,0)<>1 and proc_code = '"
				+ proc_code + "'  and pc.practice_id =  '" + searchCriteria.getPractice_id().toString() + "' ";
		int count = Integer.parseInt(db.getQuerySingleResult(strQuery));
		return count;
	}

	@Override
	public List<ORMGetProviderWiseMonthlyPaymentCollection> getPaymentCollectionSummaryProviderWise(
			SearchCriteria searchCriteria) {

		String yearMonth = "";
		String strProviderID = "";
		String strLocationID = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "yearMonth":
					if (pram.getValue() != "" && pram.getValue() != null) {
						yearMonth = pram.getValue();

					}
					break;
				// case "dateToProviderWise":
				// if (pram.getValue() != "" && pram.getValue() != null) {
				// dateToProviderWise = pram.getValue();
				//
				// }
				// break;
				case "strProviderID":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						strProviderID = pram.getValue();

					}
					break;
				case "strLocationID":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						strLocationID = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		// lstParam.add(new SpParameters("date_from", dateFromProviderWise,
		// String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("year_month", yearMonth, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("provider_id", strProviderID, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("location_id", strLocationID, String.class, ParameterMode.IN));
		// List<ORMGetProviderWiseMonthlyPaymentCollection> lst =
		// db.getStoreProcedureData(
		// "spGetPaymentCollectionSummaryProviderWise",
		// ORMGetProviderWiseMonthlyPaymentCollection.class,
		// lstParam);
		List<ORMGetProviderWiseMonthlyPaymentCollection> lst = db.getStoreProcedureData(
				"spGetProviderWiseMonthlyPaymentCollection", ORMGetProviderWiseMonthlyPaymentCollection.class,
				lstParam);

		return lst;
	}

	@Override
	public List<ORMIdNameCount> getDenialMessageRptPayers(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String criteria = " and d.practice_id='" + searchCriteria.getPractice_id().toString() + "'";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "patient_id":
					criteria += " and c.patient_id='" + pram.getValue() + "'";
					break;
				case "date_from":
					if (pram.getOption().equalsIgnoreCase("dos")) {
						criteria += " and convert(date,c.dos) >= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_created")) {
						criteria += " and convert(date,d.date_created) >= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_resolved")) {
						criteria += " and convert(date,d.date_resolved) >= convert(date,'" + pram.getValue() + "')";
					}

					break;
				case "date_to":
					if (pram.getOption().equalsIgnoreCase("DOS")) {
						criteria += " and convert(date,c.dos) <= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_created")) {
						criteria += " and convert(date,d.date_created) <= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_resolved")) {
						criteria += " and convert(date,d.date_resolved) <= convert(date,'" + pram.getValue() + "')";
					}

					break;
				case "insurance_id":
					criteria += " and isnull(d.insurance_id,'')='" + pram.getValue() + "'";
					break;
				case "claim_id":
					criteria += " and isnull(d.claim_id,'')='" + pram.getValue() + "'";
					break;
				case "eob_id":
					criteria += " and isnull(d.eob_era_id,'')='" + pram.getValue()
							+ "' and isnull(d.eob_era_id_type,'')='EOB' ";
					break;
				case "era_id":
					criteria += " and isnull(d.eob_era_id,'')='" + pram.getValue()
							+ "' and isnull(d.eob_era_id_type,'')='ERA' ";
					break;
				case "status":
					if (pram.getValue().equalsIgnoreCase("active")) {
						criteria += " and isnull(d.status,'') <>'RESOLVED'  ";
					} else if (pram.getValue().equalsIgnoreCase("resolved")) {
						criteria += " and isnull(d.status,'') ='RESOLVED'  ";
					}
					break;
				case "adjust_reason_code":
					criteria += " and isnull(d.message,'') like '%" + pram.getValue() + "%'";
					break;
				default:
					break;
				}
			}

		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		List<ORMIdNameCount> lst = db.getStoreProcedureData("spGetDenailRptPayersNew", ORMIdNameCount.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMDenialMessagesGet> getDenialMessages(SearchCriteria searchCriteria) {

		String criteria = " and d.practice_id='" + searchCriteria.getPractice_id().toString() + "'";
		String payer_name_criteria = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "patient_id":
					criteria += " and c.patient_id='" + pram.getValue() + "'";
					break;
				case "date_from":
					if (pram.getOption().equalsIgnoreCase("dos")) {
						criteria += " and convert(date,c.dos) >= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_created")) {
						criteria += " and convert(date,d.date_created) >= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_resolved")) {
						criteria += " and convert(date,d.date_resolved) >= convert(date,'" + pram.getValue() + "')";
					}

					break;
				case "date_to":
					if (pram.getOption().equalsIgnoreCase("DOS")) {
						criteria += " and convert(date,c.dos) <= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_created")) {
						criteria += " and convert(date,d.date_created) <= convert(date,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date_resolved")) {
						criteria += " and convert(date,d.date_resolved) <= convert(date,'" + pram.getValue() + "')";
					}

					break;
				case "insurance_id":
					criteria += " and isnull(d.insurance_id,'')='" + pram.getValue() + "'";

					break;
				case "claim_id":
					criteria += " and isnull(d.claim_id,'')='" + pram.getValue() + "'";

					break;

				case "eob_id":
					criteria += " and isnull(d.eob_era_id,'')='" + pram.getValue()
							+ "' and isnull(d.eob_era_id_type,'')='EOB' ";
					break;
				case "era_id":
					criteria += " and isnull(d.eob_era_id,'')='" + pram.getValue()
							+ "' and isnull(d.eob_era_id_type,'')='ERA' ";
					break;
				case "status":
					if (pram.getValue().equalsIgnoreCase("active")) {
						criteria += " and isnull(d.status,'') <>'RESOLVED'  ";
					} else if (pram.getValue().equalsIgnoreCase("resolved")) {
						criteria += " and isnull(d.status,'') ='RESOLVED'  ";
					}
					break;
				case "adjust_reason_code":
					criteria += " and isnull(d.message,'') like '%" + pram.getValue() + "%'";
					break;
				case "payer_name":
					payer_name_criteria = " where payer_name='" + pram.getValue().replaceAll("'", "''") + "'";
					break;
				default:
					break;
				}
			}

		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("payer_name_criteria", payer_name_criteria, String.class, ParameterMode.IN));
		List<ORMDenialMessagesGet> lst = db.getStoreProcedureData("spGetDenailRptNew", ORMDenialMessagesGet.class,
				lstParam);
		return lst;
	}

	@Override
	public List<ORMGetPaymentCollectionSummaryReport> getSourceWiseCollection(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String date_to = "";
		String date_from = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				default:
					break;
				}
			}

		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("date_from", date_from, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("date_to", date_to, String.class, ParameterMode.IN));
		List<ORMGetPaymentCollectionSummaryReport> lst = db.getStoreProcedureData("spGetPaymentCollectionSummary",
				ORMGetPaymentCollectionSummaryReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMBillingTrackSheet> getBillingTrackSheetFreez(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String date_to = "";
		String range = "";
		String date_from = "";
		String patient_id = "";
		String payer_id = "";
		String payer_type = "";
		String provider_id = "";
		String location_id = "";
		String self = "";
		String amt_cond = "";
		String amt = "";
		String claim_type = "";
		String pri_status = "";
		String sec_status = "";

		String strCriteria = "and c.practice_id='" + searchCriteria.getPractice_id().toString() + "'";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				case "range":
					range = pram.getValue();
					break;
				case "patient_id":
					patient_id = pram.getValue();
					break;
				case "payer_id":
					payer_id = pram.getValue();
					break;
				case "payer_type":
					payer_type = pram.getValue();
					break;
				case "provider_id":
					provider_id = pram.getValue();
					break;
				case "location_id":
					location_id = pram.getValue();
					break;
				case "self":
					self = pram.getValue();
					break;
				case "amt_cond":
					amt_cond = pram.getValue();
					break;
				case "amt":
					amt = pram.getValue();
					break;
				case "claim_type":
					claim_type = pram.getValue();
					break;
				case "pri_status":
					pri_status = pram.getValue();
					break;
				case "sec_status":
					sec_status = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		if (range.equals("DOS Range")) {
			strCriteria += " and convert(date,c.dos) between convert(date,'" + date_from + "') and  convert(date,'"
					+ date_to + "')";
		} else if (range.equals("Created Date Range")) {
			strCriteria += " and convert(date,convert(varchar,c.date_created,101)) between '" + date_from + "' and '"
					+ date_to + "' ";
		} else if (range.equals("Bill Date")) {
			strCriteria += " and convert(date,convert(varchar,c.submission_date,101)) between '" + date_from + "' and '"
					+ date_to + "' ";
		}
		if (!patient_id.equals("")) {
			strCriteria += " and c.patient_id='" + patient_id + "' ";
		}
		if (!provider_id.equals("null") && !provider_id.equals("ALL") && !provider_id.equals("")) {
			strCriteria += " and c.attending_physician_id='" + provider_id + "' ";
		}
		if (!location_id.equals("null") && !location_id.equals("ALL") && !location_id.equals("")) {
			strCriteria += " and c.location_id='" + location_id + "' ";
		}

		if (payer_id != "") {
			if (payer_type.equals("all_payers")) {
				strCriteria += " and ( " + " ( pri_insurance_id is not null and pri_insurance_payer_id in (" + payer_id
						+ ")) " + " OR ( sec_insurance_id is not null and sec_insurance_payer_id in (" + payer_id
						+ ")) " + " OR ( oth_insurance_id is not null and oth_insurance_payer_id in (" + payer_id
						+ ")) " + " ) ";
			} else if (payer_type.equals("pri_payer")) {
				strCriteria += " and pri_insurance_id is not null and pri_insurance_payer_id in (" + payer_id + ")  "; // and
																														// isnull(c.pri_paid,
																														// 0)
																														// <
																														// 1
			} else if (payer_type.equals("sec_payer")) {
				strCriteria += " and sec_insurance_id is not null and sec_insurance_payer_id in (" + payer_id + ")    ";// and
																														// isnull(c.sec_paid,
																														// 0)
																														// <
																														// 1
			} else if (payer_type.equals("oth_payer")) {
				strCriteria += " and oth_insurance_id is not null and oth_insurance_payer_id in (" + payer_id + ")  ";// and
																														// isnull(c.oth_paid,
																														// 0)
																														// <
																														// 1
			}
		} else {
			if (payer_type.equals("all_payers")) {

			} else if (payer_type.equals("pri_payer")) {
				strCriteria += " and pri_insurance_id is not null  ";
			} else if (payer_type.equals("sec_payer")) {
				strCriteria += " and sec_insurance_id is not null  ";
			} else if (payer_type.equals("oth_payer")) {
				strCriteria += " and oth_insurance_id is not null  ";
			}

		}
		if (self.equals("true")) {
			strCriteria += " and (c.self_pay=1 or c.pat_status='Billed') ";// c.self_pay=1 or
		} else {
			strCriteria += " and (c.self_pay<>1 and c.pat_status<>'Billed') ";// c.self_pay<>1 or
		}

		if (amt != "" && amt_cond != "") {
			strCriteria += " and isnull(c.amt_due,0) " + amt_cond + " " + amt + " ";
		}
		if (claim_type.equals("office")) {
			strCriteria += " and isnull(c.lab_claim,0)= 0 ";
		} else if (claim_type.equals("lab")) {
			strCriteria += " and isnull(c.lab_claim,0)= 1 ";
		}
		if (!pri_status.equals("all")) {
			strCriteria += " and isnull(c.pri_status,'')= '" + pri_status + "' ";
		}
		if (!sec_status.equals("all")) {
			strCriteria += " and isnull(c.sec_status,'')= '" + sec_status + "' ";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", strCriteria, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetBillingTrackSheet_Freez", ORMBillingTrackSheet.class, lstParam);

	}

	@Override
	public List<ORMGetDepositReportChecksSummary> getBankDepositReportCheckSummary(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String strCriteria_sub_eob = "";
		String strCriteria_sub_era = "";
		String strSearchDateOption = "";
		String date_option = "";
		String date_to = "";
		String date_from = "";
		String payer_id = "";
		String iseobPayment = "";
		String isACAPayment = "";
		String isPatientPayment = "";
		String check_number = "";

		// String strCriteria="and
		// c.practice_id='"+searchCriteria.getPractice_id().toString()+"'";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				case "payer_id":
					payer_id = pram.getValue();
					break;
				case "date_option":
					date_option = pram.getValue();
					break;
				case "iseobPayment":
					iseobPayment = pram.getValue();
					break;
				case "isACAPayment":
					isACAPayment = pram.getValue();
					break;
				case "isPatientPayment":
					isPatientPayment = pram.getValue();
					break;
				case "check_number":
					check_number = pram.getValue();
					break;

				default:
					break;
				}
			}
		}
		if (date_option.equals("check_date")) {
			strSearchDateOption = "check_date";
			strCriteria_sub_eob = "  and eob.practice_id='" + searchCriteria.getPractice_id()
					+ "' and convert(datetime,convert(varchar,eob.check_date,101))  between convert(datetime,'"
					+ date_from + "') and convert(datetime,'" + date_to + "') ";
			strCriteria_sub_era = "  and era.practice_id='" + searchCriteria.getPractice_id()
					+ "' and convert(datetime,convert(varchar,era.check_date,101))  between convert(datetime,'"
					+ date_from + "') and convert(datetime,'" + date_to + "') ";
		} else if (date_option.equals("receiving_date")) {
			strSearchDateOption = "receiving_date";
			strCriteria_sub_eob = "  and eob.practice_id='" + searchCriteria.getPractice_id()
					+ "' and convert(datetime,convert(varchar,eob.date_created,101))  between convert(datetime,'"
					+ date_from + "') and convert(datetime,'" + date_to + "') ";
			strCriteria_sub_era = "  and era.practice_id='" + searchCriteria.getPractice_id()
					+ "' and convert(datetime,convert(varchar,era.date_created,101))  between convert(datetime,'"
					+ date_from + "') and convert(datetime,'" + date_to + "') ";
		} else if (date_option.equals("posting_date")) {
			strSearchDateOption = "posting_date";
			strCriteria_sub_eob = "  and cp.practice_id='" + searchCriteria.getPractice_id()
					+ "' and convert(datetime,convert(varchar,cp.date_created,101))  between convert(datetime,'"
					+ date_from + "') and convert(datetime,'" + date_to + "') ";
			strCriteria_sub_era = "  and cp.practice_id='" + searchCriteria.getPractice_id()
					+ "' and convert(datetime,convert(varchar,cp.date_created,101))  between convert(datetime,'"
					+ date_from + "') and convert(datetime,'" + date_to + "') ";
		}
		if (!payer_id.equals("")) {
			strCriteria_sub_eob += " and insp.payer_number in('" + payer_id + "') ";
			strCriteria_sub_era += " and convert(varchar,era.payer_identifier) in ('" + payer_id + "') ";
		}
		if (!check_number.equals("")) {
			strCriteria_sub_eob += " and eob.check_number='" + check_number + "' ";
			strCriteria_sub_era += " and era.check_number='" + check_number + "' ";
		}
		String strEOBPaymentType = "";
		// String strERAPaymentType="";

		if (iseobPayment.equals("true")) {
			if (strEOBPaymentType != "") {
				strEOBPaymentType += " OR ";
			}
			strEOBPaymentType += " eob.payment_type='EOB' ";
		} else // eliminate era record...
		{
			strCriteria_sub_era += " and era.era_id=-999 ";
		}

		if (isACAPayment.equals("true")) {
			if (strEOBPaymentType != "") {
				strEOBPaymentType += " OR ";
			}
			strEOBPaymentType += " eob.payment_type='ACA' ";
		}

		if (isPatientPayment.equals("true")) {
			if (strEOBPaymentType != "") {
				strEOBPaymentType += " OR ";
			}
			strEOBPaymentType += " (eob.patient_id is not null and isnull(eob.payment_type,'')='Patient') ";
			// strEOBPaymentType+=" eob.payment_type='Patient' ";

		} else {
			strCriteria_sub_eob += " and eob.patient_id is null and isnull(eob.payment_type,'')<>'Patient'  ";
		}

		strCriteria_sub_eob += " and (" + strEOBPaymentType + ") ";

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("era_criteria", strCriteria_sub_era, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("eob_criteria", strCriteria_sub_eob, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("search_option", strSearchDateOption, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetBankDepositReportCheckSummary", ORMGetDepositReportChecksSummary.class,
				lstParam);
	}

	@Override
	public List<ORMGetCheckPostedPayments> getCheckPostedPayments(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String criteria = "";
		String date_to = "";
		String date_from = "";
		String eob_era_id = "";
		String eob_era_id_type = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				case "eob_era_id":
					eob_era_id = pram.getValue();
					break;
				case "eob_era_id_type":
					eob_era_id_type = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		criteria = " and cp.eob_era_id='" + eob_era_id + "' and cp.eob_era_id_type='" + eob_era_id_type + "' ";
		criteria += "and convert(datetime,convert(varchar,cp.date_created,101)) between convert(datetime,'" + date_from
				+ "') and convert(datetime,'" + date_to + "')";

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));

		List<ORMGetCheckPostedPayments> lst = db.getStoreProcedureData("spGeCheckPostedPayments",
				ORMGetCheckPostedPayments.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMClaimBatchReport> getClaimBatchReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String criteria = "and c.practice_id=" + searchCriteria.getPractice_id() + "";

		String date_to = "";
		String date_from = "";
		String provider = "";
		String location = "";
		String type = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				case "provider_id":
					provider = pram.getValue();
					break;
				case "location_id":
					location = pram.getValue();
					break;
				case "type":
					type = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		if (type.equals("dos")) {
			criteria += " and convert(date,convert(varchar,c.dos,101)) between convert(date,convert(varchar,'"
					+ date_from + "',101)) and convert(date,convert(varchar,'" + date_to + "',101)) ";
		} else {
			criteria += " and convert(date,convert(varchar,cb.downloaded_date,101)) between convert(date,convert(varchar,'"
					+ date_from + "',101)) and convert(date,convert(varchar,'" + date_to + "',101)) ";
		}
		if (!provider.equals("null") && !provider.equals("ALL") && !provider.equals("")) {
			criteria += " and c.billing_physician='" + provider + "' ";
		}
		if (!location.equals("null") && !location.equals("ALL") && !location.equals("")) {
			criteria += " and c.location_id='" + location + "' ";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));

		List<ORMClaimBatchReport> lst = db.getStoreProcedureData("spGetClaimBatchReport", ORMClaimBatchReport.class,
				lstParam);
		return lst;
	}

	@Override
	public List<ORMSptWisePaymentReport> getCPTWisePaymentReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String criteria = "and clm.practice_id=" + searchCriteria.getPractice_id() + "";
		String insCriteria = "";
		String CPT_selected = "";
		String date_option = "";
		String date_to = "";
		String date_from = "";
		String provider_id = "";
		String taxonomy_id = "";
		String cpt_code = "";
		String payer_id = "";
		String insurance_Id = "";
		String paid_only = "";
		String show_dx = "";
		String insurance_type = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_option":
					date_option = pram.getValue();
					break;
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				case "provider_id":
					provider_id = pram.getValue();
					break;
				case "taxonomy_id":
					taxonomy_id = pram.getValue();
					break;
				case "cpt_code":
					cpt_code = pram.getValue();
					break;
				case "payer_id":
					payer_id = pram.getValue();
					break;
				case "insurance_Id":
					insurance_Id = pram.getValue();
					break;
				case "paid_only":
					paid_only = pram.getValue();
					break;
				case "show_dx":
					show_dx = pram.getValue();
					break;
				case "insurance_type":
					insurance_type = pram.getValue();
					break;

				default:
					break;
				}
			}
		}

		if (date_option.toLowerCase().equals("payment date")) {
			criteria += " and convert(date,convert(varchar,cpmt.date_created,101)) between convert(date,convert(varchar,'"
					+ date_from + "',101)) and convert(date,convert(varchar,'" + date_to + "',101)) ";
		} else {
			criteria += " and convert(date,convert(varchar,clm.dos,101)) between convert(date,convert(varchar,'"
					+ date_from + "',101)) and convert(date,convert(varchar,'" + date_to + "',101)) ";
		}
		if (!provider_id.equals("null") && !provider_id.equals("ALL") && !provider_id.equals("")) {
			criteria += " and clm.attending_physician='" + provider_id + "' ";
		}
		if (!taxonomy_id.equals("null") && !taxonomy_id.equals("")) {
			criteria += " and pr_att.taxonomy_id='" + taxonomy_id + "' ";
		}
		if (!cpt_code.equals("null") && !cpt_code.equals("")) {
			criteria += "and cpr.proc_code in ('" + cpt_code + "') ";
		}
		if (paid_only.equals("true")) {
			paid_only = "1";
		} else {
			paid_only = "0";
		}
		if (show_dx.equals("true")) {
			show_dx = "1";
		} else {
			show_dx = "0";
		}
		if (!insurance_Id.equals("")) {
			if (insCriteria == "") {
				insCriteria = " where ";
			} else {
				insCriteria += " and ";
			}

			insCriteria += " insurance_id='" + insurance_Id + "'";
		}
		if (payer_id != "") {
			if (insCriteria == "") {
				insCriteria = " where ";
			} else {
				insCriteria += " and ";
			}
			insCriteria += " payer_number='" + payer_id + "'";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("paid_only", paid_only, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("showDx", show_dx, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("insType", insurance_type, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("insCriteria", insCriteria, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("CPT_selected", CPT_selected, String.class, ParameterMode.IN));

		List<ORMSptWisePaymentReport> lst = db.getStoreProcedureData("spGetCPTWisePaymentReport",
				ORMSptWisePaymentReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMGetPayerWisePaymentReport> getPayerWisePayment(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String criteria = "and c.practice_id=" + searchCriteria.getPractice_id() + "";

		String date_to = "";
		String date_from = "";
		String provider = "";
		String location = "";
		String type = "";
		String payer_id = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				case "provider_id":
					provider = pram.getValue();
					break;
				case "location_id":
					location = pram.getValue();
					break;
				case "type":
					type = pram.getValue();
					break;
				case "payer_id":
					payer_id = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		if (type.equals("Claim Created")) {
			criteria += " and convert(date,convert(varchar,c.date_created,101)) between convert(date,convert(varchar,'"
					+ date_from + "',101)) and convert(date,convert(varchar,'" + date_to + "',101)) ";
		} else if (type.equals("Payment Date")) {
			criteria += " and convert(date,convert(varchar,cp.date_created,101)) between convert(date,convert(varchar,'"
					+ date_from + "',101)) and convert(date,convert(varchar,'" + date_to + "',101)) ";
		} else {
			criteria += " and convert(date,convert(varchar,c.dos,101)) between convert(date,convert(varchar,'"
					+ date_from + "',101)) and convert(date,convert(varchar,'" + date_to + "',101)) ";
		}
		if (!provider.equals("null") && !provider.equals("ALL") && !provider.equals("")) {
			criteria += " and c.attending_physician='" + provider + "' ";
		}
		if (!location.equals("null") && !location.equals("ALL") && !location.equals("")) {
			criteria += " and c.location_id='" + location + "' ";
		}
		if (!payer_id.equals("")) {
			criteria += " and p.payer_id in(" + payer_id + ") ";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));

		List<ORMGetPayerWisePaymentReport> lst = db.getStoreProcedureData("spGetPayerWisePaymentReport",
				ORMGetPayerWisePaymentReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMGetPayerWisePaymentDetailsReport> getPayerWisePaymentDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String criteria = "and c.practice_id=" + searchCriteria.getPractice_id() + "";

		String date_to = "";
		String date_from = "";
		String provider = "";
		String location = "";
		String type = "";
		String payer_id = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				case "provider_id":
					provider = pram.getValue();
					break;
				case "location_id":
					location = pram.getValue();
					break;
				case "type":
					type = pram.getValue();
					break;
				case "payer_id":
					payer_id = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		if (type.equals("Claim Created")) {
			criteria += " and convert(date,convert(varchar,c.date_created,101)) between convert(date,convert(varchar,'"
					+ date_from + "',101)) and convert(date,convert(varchar,'" + date_to + "',101)) ";
		} else if (type.equals("Payment Date")) {
			criteria += " and convert(date,convert(varchar,cp.date_created,101)) between convert(date,convert(varchar,'"
					+ date_from + "',101)) and convert(date,convert(varchar,'" + date_to + "',101)) ";
		} else {
			criteria += " and convert(date,convert(varchar,c.dos,101)) between convert(date,convert(varchar,'"
					+ date_from + "',101)) and convert(date,convert(varchar,'" + date_to + "',101)) ";
		}
		if (!provider.equals("null") && !provider.equals("ALL") && !provider.equals("")) {
			criteria += " and c.attending_physician='" + provider + "' ";
		}
		if (!location.equals("null") && !location.equals("ALL") && !location.equals("")) {
			criteria += " and c.location_id='" + location + "' ";
		}
		if (!payer_id.equals("")) {
			criteria += " and p.payer_id in(" + payer_id + ") ";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));

		List<ORMGetPayerWisePaymentDetailsReport> lst = db.getStoreProcedureData("spGetPayerWisePaymentReportDetails",
				ORMGetPayerWisePaymentDetailsReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMGetClaimSummaryReport> getClaimSummaryReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String criteria = "and c.practice_id=" + searchCriteria.getPractice_id() + "";

		String date_to = "";
		String date_from = "";
		String provider = "";
		String location = "";
		String self = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				case "provider_id":
					provider = pram.getValue();
					break;
				case "location_id":
					location = pram.getValue();
					break;
				case "self":
					self = pram.getValue();
					break;
				default:
					break;
				}
			}
		}

		criteria += " and convert(date,convert(varchar,c.dos,101)) between convert(date,convert(varchar,'" + date_from
				+ "',101)) and convert(date,convert(varchar,'" + date_to + "',101)) ";

		if (!provider.equals("null") && !provider.equals("ALL") && !provider.equals("")) {
			criteria += " and c.attending_physician='" + provider + "' ";
		}
		if (!location.equals("null") && !location.equals("ALL") && !location.equals("")) {
			criteria += " and c.location_id='" + location + "' ";
		}
		if (self.equals("true")) {
			criteria += " and isnull(c.self_pay,0)<>1 and isnull(c.pat_status,'')<>'B' ";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));

		List<ORMGetClaimSummaryReport> lst = db.getStoreProcedureData("spGetClaimSummaryReport",
				ORMGetClaimSummaryReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMGetCashRegisterReport> getCashRegisterReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String strCriteria = "and cr.practice_id='" + searchCriteria.getPractice_id() + "'";

		String date_to = "";
		String date_from = "";
		String provider = "";
		String location = "";
		String patient = "";
		String status = "";
		String payment = "";
		String user = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				case "provider_id":
					provider = pram.getValue();
					break;
				case "location_id":
					location = pram.getValue();
					break;
				case "user_id":
					user = pram.getValue();
					break;
				case "payment":
					payment = pram.getValue();
					break;
				case "status":
					status = pram.getValue();
					break;
				case "patient_id":
					patient = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		strCriteria += " and convert(date,cr.date_created)  between convert(date,'" + date_from
				+ "') and convert(date,'" + date_to + "') ";
		if (!patient.equals("")) {
			strCriteria += " and cr.patient_id='" + patient + "' ";
		}
		if (!location.equals("")) {
			strCriteria += " and cr.location_id='" + location + "' ";
		}
		if (!provider.equals("")) {
			strCriteria += " and cr.provider_id='" + provider + "' ";
		}
		if (!user.equals("")) {
			strCriteria += " and cr.created_user='" + user + "' ";
		}

		if (!payment.equals("")) {
			switch (payment) {
			case "copay":
				strCriteria += " and ( isnull(cr.copay_paid,0)>0 )";
				break;
			case "selfpay":
				strCriteria += " and (isnull(cr.selfpay_paid,0)>0 )";
				break;
			case "previousbalance":
				strCriteria += " and ( isnull(cr.previous_balance_paid,0)>0 ) ";
				break;
			case "other":
				strCriteria += " and ( isnull(cr.other_paid,0)>0 ) ";
				break;
			case "writeoff":
				strCriteria += " and ( isnull(cr.copay_write_off,0)>0 OR  isnull(cr.selfpay_write_off,0)>0 OR isnull(cr.prev_balance_write_off,0)>0 ) ";
				break;
			case "advance_adjusted":
				strCriteria += " and ( isnull(cr.copay_advance_adjusted,0)>0 OR  isnull(cr.selfpay_advance_adjusted,0)>0 OR isnull(cr.prev_balance_advance_adjusted,0)>0  OR isnull(cr.other_advance_adjusted,0)>0 ) ";
				break;
			case "advance":
				strCriteria += " and isnull(cr.advance_paid,0)>0 ";
				break;
			}
		}
		if (!status.equals("")) {
			switch (status) {
			case "resolved":
				strCriteria += " and isnull(cr.resolved,0)=1  ";
				break;
			case "check_bounce":
				strCriteria += " and isnull(cr.check_bounce,0) = 1 ";
				break;
			case "void":
				strCriteria += " and isnull(cr.voided,0) = 1 ";
				break;
			case "refund":
				strCriteria += " and isnull(cr.is_refund,0) = 1";
				break;
			case "pending":
				strCriteria += " and isnull(cr.resolved,0)<>1 and isnull(cr.check_bounce,0)<>1 and isnull(cr.voided,0)<>1 ";
				break;
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", strCriteria, String.class, ParameterMode.IN));

		List<ORMGetCashRegisterReport> lst = db.getStoreProcedureData("spGetCashRegisterReport",
				ORMGetCashRegisterReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMGetInvoiceReport> getInvoiceReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String date_to = "";
		String date_from = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("From", date_from, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("To", date_to, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		List<ORMGetInvoiceReport> lst = db.getStoreProcedureData("spGetInvoiceReport", ORMGetInvoiceReport.class,
				lstParam);
		return lst;
	}

	@Override
	public List<rptGetUserPerformance> getUserPerformanceReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String date_to = "";
		String date_from = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("date_form", date_from, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("date_to", date_to, String.class, ParameterMode.IN));

		List<rptGetUserPerformance> lst = db.getStoreProcedureData("spGetUserPerformance", rptGetUserPerformance.class,
				lstParam);
		return lst;
	}

	@Override
	public List<ORMNineColumnGeneric> getPaymentCollectionSummaryLocationWise(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String type = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "type":
					type = pram.getValue();
					break;

				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", type, String.class, ParameterMode.IN));

		List<ORMNineColumnGeneric> lst = db.getStoreProcedureData("spGetLocationWisePaymentCollectionSummary",
				ORMNineColumnGeneric.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMCPTSummaryReport> getProcedureSummaryReport(SearchCriteria searchCriteria) {
		String cptSearchValue = "";
		String fullyear = "";
		// String dateToCPTWise = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "cptSearchValue":
					if (pram.getValue() != "" && pram.getValue() != null) {
						cptSearchValue = pram.getValue();

					}
					break;
				case "fullyear":
					if (pram.getValue() != "" && pram.getValue() != null) {
						fullyear = pram.getValue();

					}
					break;
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("year_month", cptSearchValue.toString(), String.class, ParameterMode.IN));
		List<ORMCPTSummaryReport> lst = null;
		if (fullyear.equals("0"))
			lst = db.getStoreProcedureData("spGetProcedureSummaryReport_NEW", ORMCPTSummaryReport.class, lstParam);
		else
			lst = db.getStoreProcedureData("spGetCPTSummaryReport_FullYear", ORMCPTSummaryReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMProWorkReport> getProviderWork(SearchCriteria searchCriteria) {
		String criteria = "";
		String dateFrom = "";
		String dateTo = "";
		String date = "";
		// String dateToCPTWise = "";
		criteria = " and a.practice_id = " + searchCriteria.getPractice_id().toString();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "cmbLocation":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and a.location_id = '" + pram.getValue() + "'";

					}
					break;
				case "cmbProvider":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and a.provider_id = '" + pram.getValue() + "'";

					}
					break;
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dateFrom = pram.getValue();
					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dateTo = pram.getValue();

					}
					break;

				default:
					break;
				}
			}
		}

		if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
			date = " between '" + dateFrom + "' and '" + dateTo + "' ";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("Date", date, String.class, ParameterMode.IN));
		List<ORMProWorkReport> lst = db.getStoreProcedureData("spProWorkReport", ORMProWorkReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMTwoColumnGeneric> getAuthorizationUsers(String practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetAuthorizationUsers_new", ORMTwoColumnGeneric.class, lstParam);

	}

	@Override
	public List<ORMPatientNotPaidReasons> getNotPaidReasonData(SearchCriteria searchCriteria) {
		String criteria = "";
		String dateFrom = "";
		String dateTo = "";
		String patient_id = "";
		String authorizedby = "";

		criteria = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						patient_id = pram.getValue();
					}
					break;
				case "authorizedby":
					if (pram.getValue() != "" && pram.getValue() != null) {
						authorizedby = pram.getValue();
					}
					break;
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dateFrom = pram.getValue();
					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dateTo = pram.getValue();
					}
					break;

				default:
					break;
				}
			}
		}
		criteria = " where ";
		criteria += " convert(date,pnp.date_created) between convert(date,'" + dateFrom + "') and convert(date,'"
				+ dateTo + "') ";
		criteria += " and pnp.practice_id ='" + searchCriteria.getPractice_id().toString() + "' ";

		if (!patient_id.equals("")) {
			criteria += " and pnp.patient_id ='" + patient_id + "' ";
		}
		if (!authorizedby.equals("")) {
			criteria += " and pnp.authorized_by ='" + authorizedby + "' ";
		}

		criteria += " and isnull(p.deleted,0)<>1 and isnull(pnp.deleted,0)<>1 order by pnp.date_created desc ";

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		List<ORMPatientNotPaidReasons> lst = db.getStoreProcedureData("getPatientNotPaidReason_Report",
				ORMPatientNotPaidReasons.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMTwoColumnGeneric> getMonthorYearWiseRpt(SearchCriteria searchCriteria) {
		String tabType = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "tabType":
					if (pram.getValue() != "" && pram.getValue() != null) {
						tabType = pram.getValue();
					}
					break;

				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("options", tabType, String.class, ParameterMode.IN));
		List<ORMTwoColumnGeneric> lst = db.getStoreProcedureData("getbilling_summary", ORMTwoColumnGeneric.class,
				lstParam);
		return lst;
	}

	@Override
	public List<ORMTwoColumnGeneric> searchReport(SearchCriteria searchCriteria) {
		// String tabType = "";
		// String from = "";
		String dateFrom = "";
		String dateTo = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				/*
				 * case "tabType": if (pram.getValue() != "" && pram.getValue() != null) {
				 * tabType = pram.getValue(); } break;
				 */
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dateFrom = pram.getValue();
					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dateTo = pram.getValue();
					}
					break;

				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		/*
		 * lstParam.add(new SpParameters("options", tabType, String.class,
		 * ParameterMode.IN)); lstParam.add(new SpParameters("from", from, String.class,
		 * ParameterMode.IN)); List<ORMTwoColumnGeneric> lst =
		 * db.getStoreProcedureData("getbilling_summary_new", ORMTwoColumnGeneric.class,
		 * lstParam);
		 */

		lstParam.add(new SpParameters("dateFrom", dateFrom.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("dateTo", dateTo.toString(), String.class, ParameterMode.IN));
		List<ORMTwoColumnGeneric> lst = db.getStoreProcedureData("spGetBillingSummaryReport", ORMTwoColumnGeneric.class,
				lstParam);

		return lst;
	}

	@Override
	public List<ORMrptHCFAClaimsGet> getHcfaClaims(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String criteria = " and c.practice_id='" + searchCriteria.getPractice_id().toString() + "' ";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "date_from":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {

						if (pram.getOption().equalsIgnoreCase("dos")) {
							criteria += " and convert(date,c.dos) >= convert(date,'" + pram.getValue() + "') ";
						} else if (pram.getOption().equalsIgnoreCase("date_created")) {
							criteria += " and convert(date,c.date_created) >= convert(date,'" + pram.getValue() + "') ";
						}
					}
					break;
				case "date_to":
					if (pram.getOption().equalsIgnoreCase("dos")) {
						criteria += " and convert(date,c.dos) <= convert(date,'" + pram.getValue() + "') ";
					} else if (pram.getOption().equalsIgnoreCase("date_created")) {
						criteria += " and convert(date,c.date_created) <= convert(date,'" + pram.getValue() + "') ";
					}
					break;

				case "provider_id":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						criteria += " and c.attending_physician='" + pram.getValue() + "' ";
					}
					break;
				case "location_id":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						criteria += " and c.location_id='" + pram.getValue() + "' ";
					}
					break;
				case "claim_type":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						criteria += " and c.claim_type='" + pram.getValue() + "' ";
					}
					break;
				case "claim_id":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						criteria += " and c.claim_id='" + pram.getValue() + "' ";
					}
					break;
				case "hcfa_printed":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						criteria += " and isnull(hcfa_printed,0)=" + pram.getValue() + " ";
					}
					break;

				case "insurance_type":
					if (GeneralOperations.isNotNullorEmpty(pram.getValue())) {
						criteria += " and isnull(c.hcfa_ins_type,'')='" + pram.getValue() + "' ";
					}
					break;

				default:
					break;
				}
			}
		}

		criteria += " and ISNULL(c.draft,0)=0 and isnull(c.self_pay,0)=0 ";
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		List<ORMrptHCFAClaimsGet> lst = db.getStoreProcedureData("spGetRptHCFAClaims", ORMrptHCFAClaimsGet.class,
				lstParam);
		return lst;
	}

	@Override
	public List<ORMCssCallLog> getcssCallLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String fromDate = "";
		String toDate = "";
		String user = "";
		String type = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						fromDate = pram.getValue();
					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						toDate = pram.getValue();
					}
					break;
				case "user":
					if (pram.getValue() != "" && pram.getValue() != null) {
						user = pram.getValue();
					}
					break;
				case "type":
					if (pram.getValue() != "" && pram.getValue() != null) {
						type = pram.getValue();
					}
					break;
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("date_from", fromDate, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("date_to", toDate, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("create_user", user, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("log_type", type, String.class, ParameterMode.IN));
		List<ORMCssCallLog> lst = db.getStoreProcedureData("sp_getCSSCall_Log", ORMCssCallLog.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMPatientNotSeen> getPatientNotSeen(SearchCriteria searchCriteria) {
		String location_id = "";
		// String provider_id = "";
		String patient_id = "";
		String app_date = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "app_date":
					if (pram.getValue() != "" && pram.getValue() != null) {
						app_date = pram.getValue();
					}
					break;
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						patient_id = pram.getValue();
					}
					break;
				// case "cmbProvider":
				// if (pram.getValue() != "" && pram.getValue() != null) {
				// provider_id = pram.getValue();
				// }
				// break;
				case "location_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						location_id = pram.getValue();
					}
					break;
				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("location_id", location_id, String.class, ParameterMode.IN));
		// lstParam.add(new SpParameters("provider_id", provider_id, String.class,
		// ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("app_date", app_date, String.class, ParameterMode.IN));
		List<ORMPatientNotSeen> lst = db.getStoreProcedureData("spGetPatient_notseen", ORMPatientNotSeen.class,
				lstParam);
		return lst;

	}

	@Override
	public List<ORMGetGeneralRptOptions> getGeneralReportOptions(String practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetGeneralReportOptins", ORMGetGeneralRptOptions.class, lstParam);
	}

	// --------------------------------------------------------------
	@Override
	public WrapperLog searchGeneralReport(String spname, String practice_id) {
		WrapperLog wrapperLog = new WrapperLog();

		List<SpParameters> lstParam = new ArrayList<>();

		List<?> FResult;
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		FResult = db.getStoreProcedureDataWOClass(spname, lstParam);
		wrapperLog.setLst_log(FResult);

		return wrapperLog;
	}

	@Override
	public List<ORMGetCorrespondenceReport> getCorrespondenceReportData(SearchCriteria searchCriteria) {

		String dateFrom = "";
		String dateTo = "";
		String patient_id = "";
		String criteria = " p.practice_id = '" + searchCriteria.getPractice_id().toString() + "' ";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dateFrom = pram.getValue();
					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dateTo = pram.getValue();
					}
					break;
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and p.patient_id = '" + pram.getValue() + "' ";
					}
				default:
					break;
				}
			}
		}

		if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
			criteria += " and convert(date,pc.date_created) between convert(date,'" + dateFrom + "') and convert(date,'"
					+ dateTo + "') ";
		}
		criteria += " and isnull(p.deleted,0)<>1 and isnull(pc.deleted,0)<>1 order by crdate desc ";
		// else if (!dateFrom.isEmpty()) {
		// criteria += " and convert(date,pc.date_created) >= convert(date,'" + dateFrom
		// + "')";
		// }
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		List<ORMGetCorrespondenceReport> lst = db.getStoreProcedureData("spGetRptCorrespondence",
				ORMGetCorrespondenceReport.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMRptMonthWiseChargesPayment> getMonthWiseChargesPaymentReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String yearMonthFrom = "";
		String yearMonthTo = "";
		String practiceId = searchCriteria.getPractice_id().toString();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "year_month_from":
					yearMonthFrom = pram.getValue();
					break;
				case "year_month_to":
					yearMonthTo = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		List<ORMRptMonthWiseChargesPayment> lst = null;
		if (GeneralOperations.isNotNullorEmpty(practiceId) && GeneralOperations.isNotNullorEmpty(yearMonthFrom)
				&& GeneralOperations.isNotNullorEmpty(yearMonthTo)) {
			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("year_month_from", yearMonthFrom.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("year_month_to", yearMonthTo.toString(), String.class, ParameterMode.IN));

			lst = db.getStoreProcedureData("spGetMonthWiseClaimPaymentDetail", ORMRptMonthWiseChargesPayment.class,
					lstParam);
		}

		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> getExportResult(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<?> list = null;
		String strExcelPath = "";
		switch (searchCriteria.getOption()) {
		case "getEncounterReport":
			list = getEncounterReport(searchCriteria);
			break;
		case "getMissingClaimsReport":
			list = getMissingClaimsReport(searchCriteria);
			break;
		case "getOrderCross_False":
			list = getOrderCross_False(searchCriteria);
			break;
		case "getOrderCross_True":
			list = getOrderCross_True(searchCriteria);
			break;
		case "getClaimwithUnsignedEncounter":
			list = getClaimwithUnsignedEncounter(searchCriteria);
			break;
		case "getClaimdetails":
			list = getClaimdetails(searchCriteria);
			break;
		case "getAppointments":
			list = getAppointments(searchCriteria);
			break;
		case "getEncounterProcDiag":
			list = getEncounterProcDiag(searchCriteria);
			break;
		}
		Wrapper_ExcelColumn objWrapper = null;
		// get Excel Header and DataField
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "excelColumn":
					if (pram.getValue() != "" && pram.getValue() != null) {
						try {
							ObjectMapper mapper = new ObjectMapper();
							objWrapper = mapper.readValue(pram.getValue().toString(), Wrapper_ExcelColumn.class);
						} catch (Exception ex) {
							System.out.println(ex.getStackTrace());
						}
						break;
					}

				default:
					break;
				}
			}
		}
		try {
			ExcelColumnsInfo oExcel = new ExcelColumnsInfo(db, generalDAOImpl);
			strExcelPath = oExcel.WriteExcel(list, objWrapper, searchCriteria.getPractice_id().toString());

		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (strExcelPath.equals("")) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while Exporting record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(strExcelPath);
		}

		return resp;
	}

	@Override
	public List<ORMPayerWiseAgingSummary> getBillingAgingPayerWiseSummary(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String stroption = "";
		String strCriteria = " where  practice_id='" + searchCriteria.getPractice_id().toString() + "'";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "option":
					if (pram.getValue() != "" && pram.getValue() != null) {
						stroption = pram.getValue();
					}
					break;

				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		if (stroption.equals("insurance")) {
			strCriteria += " and and self_pay<>1 ";
		}
		List<ORMPayerWiseAgingSummary> lst = null;
		lstParam.add(new SpParameters("criteria", strCriteria, String.class, ParameterMode.IN));
		lst = db.getStoreProcedureData("spGetPayerWiseAging", ORMPayerWiseAgingSummary.class, lstParam);
		return lst;
	}

	public List<ORMRptDailyChargesPaymentSummary> getDailyChargesPaymentSummaryReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String dateFrom = "";
		String dateTo = "";
		String practiceId = searchCriteria.getPractice_id().toString();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "dateFrom":
					dateFrom = pram.getValue();
					break;
				case "dateTo":
					dateTo = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		List<ORMRptDailyChargesPaymentSummary> lst = null;
		if (GeneralOperations.isNotNullorEmpty(practiceId) && GeneralOperations.isNotNullorEmpty(dateFrom)
				&& GeneralOperations.isNotNullorEmpty(dateTo)) {
			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("date_from", dateFrom.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("date_to", dateTo.toString(), String.class, ParameterMode.IN));

			lst = db.getStoreProcedureData("spGetDailyChargesPaymentSummary", ORMRptDailyChargesPaymentSummary.class,
					lstParam);
		}

		return lst;
	}

	@Override
	public List<ORMRptDailyPaymentSummary> getDailyPaymentSummaryReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String dateFrom = "";
		String dateTo = "";
		String practiceId = searchCriteria.getPractice_id().toString();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "dateFrom":
					dateFrom = pram.getValue();
					break;
				case "dateTo":
					dateTo = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		List<ORMRptDailyPaymentSummary> lst = null;
		if (GeneralOperations.isNotNullorEmpty(practiceId) && GeneralOperations.isNotNullorEmpty(dateFrom)
				&& GeneralOperations.isNotNullorEmpty(dateTo)) {
			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("date_from", dateFrom.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("date_to", dateTo.toString(), String.class, ParameterMode.IN));

			lst = db.getStoreProcedureData("spGetDailyPaymentSummary", ORMRptDailyPaymentSummary.class, lstParam);
		}

		return lst;
	}

	@Override
	public List<?> getLaggedCollectionReport(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String strDateFrom = "";
		String strDateTo = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						strDateFrom = pram.getValue();
					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						strDateTo = pram.getValue();
					}
					break;

				default:
					break;
				}
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		List<?> lst = null;
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("fromDate", strDateFrom, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("toDate", strDateTo, String.class, ParameterMode.IN));

		lst = db.getStoreProcedureDataWOClass("getLaggedCollectionReport", lstParam);
		return lst;
	}

	@Override
	public List<?> getProcedureAnalysis(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String strDateFrom = "";
		String strDateTo = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						strDateFrom = pram.getValue();
					}
					break;
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						strDateTo = pram.getValue();
					}
					break;

				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		List<?> lst = null;
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("fromDate", strDateFrom, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("toDate", strDateTo, String.class, ParameterMode.IN));

		lst = db.getStoreProcedureDataWOClass("spGetProcedureSummaryReportFull", lstParam);
		return lst;
	}

	@Override
	public ORMClaimReceiptDetailGet getClaimReceiptDetail(Long claimId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		List<ORMClaimReceiptDetailGet> lst = null;
		lstParam.add(new SpParameters("claim_id", claimId.toString(), String.class, ParameterMode.IN));
		lst = db.getStoreProcedureData("spGetClaimReceiptDetails", ORMClaimReceiptDetailGet.class, lstParam);
		if (!lst.isEmpty())
			return lst.get(0);
		else
			return null;
	}
}