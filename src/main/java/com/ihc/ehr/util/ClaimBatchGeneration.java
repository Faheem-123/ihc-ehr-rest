package com.ihc.ehr.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ihc.ehr.dao.GeneralDAOImpl;
import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ORMClaim_Batch_Errors;
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMSubmission_ClaimDiagnosis;
import com.ihc.ehr.model.ORMSubmission_ClaimInfo;
import com.ihc.ehr.model.ORMSubmission_ClaimInsuarnceInfo;
import com.ihc.ehr.model.ORMSubmission_ClaimProcedures;
import com.ihc.ehr.model.ORMSubmission_Claims;
import com.ihc.ehr.model.ORMSubmission_ProviderPayers;
import com.ihc.ehr.model.ORM_restricted_codes;
import com.ihc.ehr.model.SubmissionProccessedClaimInfo;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.SubmissionMethod;

public class ClaimBatchGeneration {

	private DBOperations db;

	private GeneralDAOImpl generalDaoImpl;
	private BillingGeneral billingGeneral;

	public ClaimBatchGeneration(DBOperations dbOpt, GeneralDAOImpl genImpl, BillingGeneral billingGeneral) {
		this.db = dbOpt;
		this.generalDaoImpl = genImpl;
		this.billingGeneral = billingGeneral;
	}

	// Professional
	public List<ORMClaim_Batch_Errors> GenerateBatch_5010_P(String batchID, String BatchName, String PracticeID,
			String loggedInUser, String clientDateTime, Boolean ignore_ref_phy_error) {

		List<ORMClaim_Batch_Errors> objErrorList = new ArrayList<>();
		List<SubmissionProccessedClaimInfo> objUpdateBatchClaim = new ArrayList<>();

		String practice_type = db.getQuerySingleResult(
				"select isnull(options,'')as scalar_value  from app_setting a join app_setting_detail ad on ad.setting_id=a.setting_id where description='PRACTICE_TYPE' and practice_id='"
						+ PracticeID + "'");

		String data = "";
		String selectedCliamID = "";
		String selectedDOS = "";
		String selectedPatientID = "";
		String selectedPatientName = "";

		ORMClaim_Batch_Errors objErrors;
		try {
			int segment = 0;
			// --Array List-----
			int Bigerror_flag = 0;
			int ErrorFlag = 0;
			// ----Final List of Ready Claims
			List<ORMSubmission_Claims> ReadyClaims = new ArrayList<>();
			String providerSubmitterID = "";
			// String BillingLocationName = "";
			// -------Claims Info List------------
			// BatchName = "billing,DELL, DELL,06/14/2012 10:15:51 PM";

			List<ORMSubmission_ClaimInfo> ClaimsInfo = this.billingGeneral.getBatchClaimsInfoDetail(PracticeID, batchID,
					true);
			List<ORMSubmission_ClaimInsuarnceInfo> ClaimsInsuranceInfo = this.billingGeneral
					.getBatchClaimInsurancesDetail(PracticeID, batchID, true);
			List<ORMSubmission_ClaimDiagnosis> ClaimsDiagnosisInfo = this.billingGeneral
					.getBatchClaimDiagnosisDetail(PracticeID, batchID, true);
			List<ORMSubmission_ClaimProcedures> ClaimsProceduresInfo = this.billingGeneral
					.getBatchClaimProceduresDetail(PracticeID, batchID, true);
			// get all Restricted codes(Payer wise)
			List<ORM_restricted_codes> Claims_restricted_codes = this.billingGeneral
					.getClaim_restricted_codes(PracticeID);

			// -----------

			for (ORMSubmission_ClaimInfo objClaim : ClaimsInfo) {

				selectedCliamID = objClaim.getClaim_id();
				selectedDOS = objClaim.getDos();
				selectedPatientID = objClaim.getPatient_id();
				selectedPatientName = objClaim.getLname() + ", " + objClaim.getFname();

				ORMSubmission_Claims objClaims = new ORMSubmission_Claims();

				List<ORMSubmission_ClaimInsuarnceInfo> objClaimInsurances = new ArrayList<>();
				List<ORMSubmission_ClaimDiagnosis> objClaimDiagosis = new ArrayList<>();
				List<ORMSubmission_ClaimProcedures> objClaimProcedures = new ArrayList<>();
				objErrors = null;
				//////////////////////////
				if (objClaim.getPatient_id() == null || objClaim.getPatient_id().equals("")) {
					objErrors = new ORMClaim_Batch_Errors();
					objErrors.setError(
							"gpi_M_B_L_91!Patient identifier is missing. Please update your encounter again. DOS="
									+ objClaim.getDos() + ", Claim Id=" + objClaim.getClaim_id() + " ");
					objErrors.setDos(objClaim.getDos());
					objErrors.setBatch_id(batchID);
					objErrors.setPatient_id(objClaim.getPatient_id());
					objErrors.setClaim_id(objClaim.getClaim_id());
					objErrors.setPatient_name(objClaim.getLname() + ", " + objClaim.getFname());
					
					objErrorList.add(objErrors);
					
					Bigerror_flag++;
				} else if (objClaim.getBilling_physician() == null || objClaim.getBilling_physician().equals("")) {
					objErrors = new ORMClaim_Batch_Errors();
					objErrors.setError(
							"gpi_M_B_L_91!Provider identifier is missing. Please update your encounter again, DOS="
									+ objClaim.getDos() + ", Claim Id=" + objClaim.getClaim_id() + ", PID="
									+ objClaim.getPatient_id() + "");
					objErrors.setDos(objClaim.getDos());
					objErrors.setBatch_id(batchID);
					objErrors.setPatient_id(objClaim.getPatient_id());
					objErrors.setClaim_id(objClaim.getClaim_id());
					objErrors.setPatient_name(objClaim.getLname() + ", " + objClaim.getFname());
					
					objErrorList.add(objErrors);
					
					Bigerror_flag++;
				} else if (GeneralOperations.isNullorEmpty(objClaim.getAppointment_id())
						&& !practice_type.toUpperCase().equals("BILLING_ONLY")) {
					if (!objClaim.getLab_claim()) {

						if (Integer.parseInt(db.getQuerySingleResult(
								" select count(*) as scalar_value from claim_batch_error where isnull(deleted,0)<>1 and error_type='MISSING_APPOINTMENT' and isnull(ignore_error,0)=1 and claim_id='"
										+ objClaim.getClaim_id() + "' and batch_id='" + batchID + "'")) == 0) {
							objErrors = new ORMClaim_Batch_Errors();
							objErrors.setError("MISISING APPOINTMENT : Patient has no Appointment on DOS : "
									+ objClaim.getDos() + "");
							objErrors.setError_type("MISSING_APPOINTMENT");
							objErrors.setDos(objClaim.getDos());
							objErrors.setBatch_id(batchID);
							objErrors.setClaim_id(objClaim.getClaim_id());
							objErrors.setPatient_id(objClaim.getPatient_id());
							objErrors.setPatient_name(objClaim.getLname() + ", " + objClaim.getFname());
							
							objErrorList.add(objErrors);
							
							Bigerror_flag++;
						}
					}
				}
				
				

				

				if (objClaim.getEap() == true && Integer.parseInt(db.getQuerySingleResult(
						" select count(*) as scalar_value from claim_batch_error where isnull(deleted,0)<>1 and error_type='EAP_CLAIM' and isnull(ignore_error,0)=1 and claim_id='"
								+ objClaim.getClaim_id() + "' and batch_id='" + batchID + "'")) == 0) {

					objErrors = new ORMClaim_Batch_Errors();
					objErrors.setError(
							"EAP CLAIM : This is an EAP Claim. Click Ignore if you want to send electronically.");
					objErrors.setError_type("EAP_CLAIM");
					objErrors.setDos(objClaim.getDos());
					objErrors.setBatch_id(batchID);
					objErrors.setClaim_id(objClaim.getClaim_id());
					objErrors.setPatient_id(objClaim.getPatient_id());
					objErrors.setPatient_name(objClaim.getLname() + ", " + objClaim.getFname());
					
					objErrorList.add(objErrors);
					Bigerror_flag++;
				}
				// bellow block was in else before.08/18/2014
				{
					objClaims.setClaimInfo(objClaim);
					String claimID = objClaim.getClaim_id();
					objClaims.setClaim_id(claimID);

					// Extracting Claim Insurance Info from Main Insurance Arry and add it to
					// ClaimnSubmission Object
					for (ORMSubmission_ClaimInsuarnceInfo objClaimIns : ClaimsInsuranceInfo) {
						if (objClaimIns.getClaim_id().equals(claimID)) {
							objClaimInsurances.add(objClaimIns);
						}
					}
					objClaims.setClaimInsurance(objClaimInsurances);
					// Extracting Diagnosis Info of selected Claim from Main Claim Diagnosis Arry
					// and add it to ClaimnSubmission Object
					for (ORMSubmission_ClaimDiagnosis objClaimDiag : ClaimsDiagnosisInfo) {
						if (objClaimDiag.getClaim_id().equals(claimID)) {
							objClaimDiagosis.add(objClaimDiag);
						}
					}
					objClaims.setClaimDiagnosis(objClaimDiagosis);
					// Extracting Diagnosis Info of selected Claim from Main Claim Diagnosis Arry
					// and add it to ClaimnSubmission Object
					for (ORMSubmission_ClaimProcedures objClaimProc : ClaimsProceduresInfo) {
						if (objClaimProc.getClaim_id().equals(claimID)) {
							objClaimProcedures.add(objClaimProc);
						}
					}
					for (ORMSubmission_ClaimInsuarnceInfo obj : objClaimInsurances) {
						if (obj.getInsurace_type().trim().toUpperCase().equals("PRIMARY")
								&& GeneralOperations.isNotNullorEmpty(obj.getInspayer_id())) {
							System.out.println("Claim Insurance ID = " + obj.getClaiminsurance_id()
									+ "       Claim ID = " + objClaim.getClaim_id());
							List<ORMSubmission_ProviderPayers> lst = this.billingGeneral.getBatchClaimProviderPayers(
									objClaim.getBilling_physician(), obj.getInspayer_id(), objClaim.getPractice_id());
							ORMSubmission_ProviderPayers ClaimsProviderPayersInfo = null;
							if (lst.size() > 0) {
								ClaimsProviderPayersInfo = lst.get(0);
							}

							objClaims.setBillingProviderPayersInfo(ClaimsProviderPayersInfo);

							// objClaims.setBillingProviderPayersInfo(getBatchClaimProviderPayers(objClaim.getBilling_physician(),
							// obj.getInspayer_id(), objClaim.getPractice_id(), true));
							// objClaims.setAttendingProviderPayersInfo(getBatchClaimProviderPayers(objClaim.getAttending_physician(),
							// obj.getInspayer_id(), objClaim.getPractice_id(), true));
							break;
						}
					}

					objClaims.setClaimProcedures(objClaimProcedures);

					ReadyClaims.add(objClaims);

				}
				/*
				if (objErrors != null) {
					objErrorList.add(objErrors);
				}
				*/
				
				// -----Adding CurrentClaim Info To UPDATEBATCHCLAIM list for updating
				// statuses------
				SubmissionProccessedClaimInfo objUpdateClaim = new SubmissionProccessedClaimInfo();
				objUpdateClaim.setClaim_id(Long.parseLong(objClaim.getClaim_id()));
				for (ORMSubmission_ClaimInsuarnceInfo objClaimIns : objClaimInsurances) {
					if (GeneralOperations.isNotNullorEmpty(objClaimIns)) {
						if (objClaimIns.getInsurace_type().toUpperCase().trim().equals("PRIMARY")) {
							objUpdateClaim.setHas_primary_ins(true);
						}

						if (objClaimIns.getInsurace_type().toUpperCase().trim().equals("SECONDARY")) {
							objUpdateClaim.setHas_secondary_ins(true);
						}

						if (objClaimIns.getInsurace_type().toUpperCase().trim().equals("TERITIARY")
								|| objClaimIns.getInsurace_type().toUpperCase().trim().equals("OTHER")) {
							objUpdateClaim.setHas_oth_ins(true);
						}
					}
				}
				if (objClaim.getIs_resubmitted()) {
					objUpdateClaim.setIs_resubmitted(true);
				}
				objUpdateClaim.setSubmission_method(SubmissionMethod.ELECTRONIC);
				objUpdateClaim.setUser_name(loggedInUser);
				objUpdateClaim.setClient_date_time(clientDateTime);
				objUpdateClaim.setPractice_id(Long.parseLong(PracticeID));
				
				objUpdateClaim.setPri_status(objClaim.getPri_status());
				objUpdateClaim.setSec_status(objClaim.getSec_status());
				objUpdateClaim.setOth_status(objClaim.getOth_status());
				
				objUpdateBatchClaim.add(objUpdateClaim);
				// ------
			}
			// -----Now Claims Information has been completed--------
			if (ReadyClaims.size() > 0) {
				if (providerSubmitterID.equals("") && (ReadyClaims.get(0).getClaimInfo().getSubmitter_id() != null
						&& !"".equals(ReadyClaims.get(0).getClaimInfo().getSubmitter_id()))) {
					providerSubmitterID = ReadyClaims.get(0).getClaimInfo().getSubmitter_id().trim();
				} else {
					objErrors = new ORMClaim_Batch_Errors();
					objErrors.setBatch_id(batchID);
					objErrors.setError("gpi_M_B_L_1!GatewayEdi  provider  Id is missing");
					objErrors.setDos(ReadyClaims.get(0).getClaimInfo().getDos());
					objErrors.setPatient_name(ReadyClaims.get(0).getClaimInfo().getLname() + ", "
							+ ReadyClaims.get(0).getClaimInfo().getFname());
					objErrorList.add(objErrors);
					Bigerror_flag++;
				}
				if (!providerSubmitterID.equals("")) {

					// <editor-fold desc="ISA Header">
					// INTERCHANGE CONTROL HEADER
					data += "ISA*";
					data += "00*          *00*          *ZZ*" + providerSubmitterID + "           *ZZ*263923727000000*";
					data += DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.yyMMdd) + "*";
					data += DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.HHmm) + "*";
					data += "^*00501*000000001*0*P*:~";
					segment = 1;
					// FUNCTIONAL GROUP HEADER
					data += "GS*HC*" + providerSubmitterID + "*263923727*";
					data += DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.yyyyMMdd) + "*";
					data += DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.HHmm) + "*";
					data += batchID + "*X*005010X222A1~"; // -->5010 GS08 Changed from 004010X098A1 to 005010X222 in
															// 5010
					// need to send batch_id in GS06 instead of 16290 so that can be traced from 997
					// response file
					segment++;
					// TRANSACTION SET HEADER
					data += "ST*837*0001*005010X222A1~"; // -->5010 new element addedd. ST03 Implementation Convention
															// Reference (005010X222)
					segment++;
					// BEGINNING OF HIERARCHICAL TRANSACTION
					data += "BHT*0019*00*000000001*";
					data += DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.yyyyMMdd) + "*";
					data += DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.HHmm) + "*";
					data += "CH~";
					segment++;
					// </editor-fold>

					// <editor-fold desc="LOOP 1000A (Sumbitter Information)">
					if ((ReadyClaims.get(0).getClaimInfo().getBl_organization_name() != null
							&& !ReadyClaims.get(0).getClaimInfo().getBl_organization_name().equals(""))
							|| (ReadyClaims.get(0).getClaimInfo().getBl_lname() != null
									&& !ReadyClaims.get(0).getClaimInfo().getBl_lname().equals(""))) {

						// if
						// (ReadyClaims.get(0).getClaimInfo().getBillingprovidertype().toUpperCase().equals("GROUP"))
						// {
						// BillingLocationName =
						// ReadyClaims.get(0).getClaimInfo().getBl_organization_name().trim();
						// } else {
						// BillingLocationName = ReadyClaims.get(0).getClaimInfo().getBl_lname().trim();
						// }
						// <editor-fold desc="SUBMITTER NAME">
						data += "NM1*41*2*"; // -->5010 NM103 Increase from 35 - 60
						data += ReadyClaims.get(0).getClaimInfo().getCompany_name(); // -->5010 NM104 Increase from 25 -
																						// 35
						data += "*****46*" + providerSubmitterID;// -->5010 New element added NM112 Name Last or
																	// Organization Name 1-60
						data += "~";
						segment++;
						// </editor-fold>

					} else {
						objErrors = new ORMClaim_Batch_Errors();
						objErrors.setBatch_id(batchID);
						objErrors.setError("No_Bi_Lo-Se_1!Please enter Company ClearingHouse information.");
						objErrors.setDos(ReadyClaims.get(0).getClaimInfo().getDos());
						objErrors.setPatient_name(ReadyClaims.get(0).getClaimInfo().getLname() + ", "
								+ ReadyClaims.get(0).getClaimInfo().getFname());
						objErrorList.add(objErrors);
						Bigerror_flag++;
					}

					// <editor-fold desc="SUBMITTER EDI CONTACT INFORMATION">
					data += "PER*IC*";
					if (GeneralOperations.isNullorEmpty(ReadyClaims.get(0).getClaimInfo().getContact_person())) {
						data += ReadyClaims.get(0).getClaimInfo().getContact_person();
					}

					if (GeneralOperations.isNotNullorEmpty(ReadyClaims.get(0).getClaimInfo().getCompany_phone())
							|| GeneralOperations
									.isNotNullorEmpty(ReadyClaims.get(0).getClaimInfo().getCompany_email())) {
						if (GeneralOperations.isNotNullorEmpty(ReadyClaims.get(0).getClaimInfo().getCompany_phone())) {
							data += "*TE*" + ReadyClaims.get(0).getClaimInfo().getCompany_phone().replace("-", "")
									.replace("(", "").replace(")", "").replace(" ", "").trim();
						}
						if (GeneralOperations.isNotNullorEmpty(ReadyClaims.get(0).getClaimInfo().getCompany_email())) {
							data += "*EM*" + ReadyClaims.get(0).getClaimInfo().getCompany_email();
						}
					} else {
						objErrors = new ORMClaim_Batch_Errors();
						objErrors.setBatch_id(batchID);
						objErrors.setError("No_Bi_Lo-Se_1! SUBMITTER EDI CONTACT INFORMATION is missing.");
						objErrors.setDos(ReadyClaims.get(0).getClaimInfo().getDos());
						objErrors.setPatient_name(ReadyClaims.get(0).getClaimInfo().getLname() + ", "
								+ ReadyClaims.get(0).getClaimInfo().getFname());
						objErrorList.add(objErrors);
						Bigerror_flag++;
					}
					data += "~";
					segment++;
					// </editor-fold>
					// </editor-fold>

					// <editor-fold desc="LOOP 1000B (RECEIVER NAME)">
					data += "NM1*40*2*263923727000000*****46*" + providerSubmitterID + "~";
					segment++;
					// </editor-fold>

				}
			}

			//String listOfEncounterIds = "";
			ORMClaim_Batch_Errors claimError = null;
			int HL = 1;
			for (ORMSubmission_Claims objClaim : ReadyClaims) {

				selectedCliamID = objClaim.getClaim_id();
				selectedDOS = objClaim.getClaimInfo().getDos();
				selectedPatientID = objClaim.getClaimInfo().getPatient_id();
				selectedPatientName = objClaim.getClaimInfo().getFname() + " " + objClaim.getClaimInfo().getLname();

				String paperPayerID = "";
				String Billing_Provider_NPI = "";
				String TaxonomyCode = "";
				String FederalTaxID = "";
				String FederalTaxIDType = "";

				String box_33_type = "";

				// String patientIDforCorrecton = "";
				//listOfEncounterIds += objClaim.getClaim_id() + "-";
				claimError = new ORMClaim_Batch_Errors();
				claimError.setBatch_id(batchID);
				claimError.setDos(objClaim.getClaimInfo().getDos());
				claimError.setPatient_name(
						objClaim.getClaimInfo().getLname() + ", " + objClaim.getClaimInfo().getFname());
				claimError.setPatient_id(objClaim.getClaimInfo().getPatient_id());
				claimError.setClaim_id(objClaim.getClaimInfo().getClaim_id());

				// check if payer validation expires
				if (GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo())) {
					if (GeneralOperations
							.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo().getValidation_expiry_date())) {
						if (!objClaim.getBillingProviderPayersInfo().getValidation_expiry_date()
								.equalsIgnoreCase("01/01/1900")) {
							String validationExpriyDate = objClaim.getBillingProviderPayersInfo()
									.getValidation_expiry_date();
							Date dtExpiry = new SimpleDateFormat("MM/dd/yyyy").parse(validationExpriyDate);
							Date dtToday = new SimpleDateFormat("MM/dd/yyyy")
									.parse(DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.MM_dd_yyyy));

							if (dtToday.compareTo(dtExpiry) >= 0) // expires
							{
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError(
										"VALIDATION EXPIRED : Provider validation with the Payer has been expired.");
								objErrorList.add(obj);
								ErrorFlag++;
							}
						}
					}
				}

				// Provider NPI/Group NPI on the basis of Box 33 Type . Group or Individual
				// Federal Tax ID
				if (GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo())) {
					if (GeneralOperations.isNotNullorEmpty(
							objClaim.getBillingProviderPayersInfo().getProvider_identification_number_type())
							&& GeneralOperations.isNotNullorEmpty(
									objClaim.getBillingProviderPayersInfo().getProvider_identification_number())) {

						FederalTaxIDType = objClaim.getBillingProviderPayersInfo()
								.getProvider_identification_number_type();
						FederalTaxID = objClaim.getBillingProviderPayersInfo().getProvider_identification_number();
					} else {
						FederalTaxIDType = objClaim.getClaimInfo().getFederal_taxidnumbertype();
						FederalTaxID = objClaim.getClaimInfo().getFederal_taxid();
					}

					if (GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo().getBox_33_type())) {

						box_33_type = objClaim.getBillingProviderPayersInfo().getBox_33_type();
					}
				} else {
					FederalTaxIDType = objClaim.getClaimInfo().getFederal_taxidnumbertype();
					FederalTaxID = objClaim.getClaimInfo().getFederal_taxid();
				}

				if (GeneralOperations.isNullorEmpty(box_33_type)) {
					switch (FederalTaxIDType) {
					case "EIN": // Group
						box_33_type = "GROUP";
						break;
					case "SSN": // Individual
						box_33_type = "INDIVIDUAL";
						break;
					}
				}

				switch (box_33_type) {
				case "GROUP": // Group
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getBl_group_npi())) {
						Billing_Provider_NPI = objClaim.getClaimInfo().getBl_group_npi().trim();
					}
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getGrp_taxonomy_id())) {
						TaxonomyCode = objClaim.getClaimInfo().getGrp_taxonomy_id().trim();
					}
					break;
				case "INDIVIDUAL": // Individual
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getBl_npi())) {
						Billing_Provider_NPI = objClaim.getClaimInfo().getBl_npi().trim();
					}
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getTaxonomy_code())) {
						TaxonomyCode = objClaim.getClaimInfo().getTaxonomy_code().trim();
					}
					break;
				}
				
				// override taxonomy code mentioned in claim over the default taxonomy
				if(GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getBilling_provider_taxonomy())) {
					TaxonomyCode = objClaim.getClaimInfo().getBilling_provider_taxonomy();
				}

				// <editor-fold desc="LOOP 2000A">
				// <editor-fold desc="BILLING PROVIDER HIERARCHICAL LEVEL">
				data += "HL*" + HL + "**";
				data += "20*1~";
				segment++;
				// </editor-fold>

				// <editor-fold desc="BILLING PROVIDER SPECIALTY INFORMATION">
				if (GeneralOperations.isNotNullorEmpty(TaxonomyCode)) {
					data += "PRV*BI*PXC*" + TaxonomyCode + "~";
					segment++;
				}
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo())
				// &&
				// GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo().getProvider_identification_number_type())
				// &&
				// GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo().getProvider_identification_number()))
				// {
				// if
				// (objClaim.getBillingProviderPayersInfo().getProvider_identification_number_type().equals("EIN"))
				// {
				//
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getGrp_taxonomy_id()))
				// {
				// data += "PRV*BI*PXC*" + objClaim.getClaimInfo().getGrp_taxonomy_id() + "~";
				// segment++;
				// }
				//
				// } else if
				// (objClaim.getBillingProviderPayersInfo().getProvider_identification_number_type().equals("SSN"))
				// {
				//
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getTaxonomy_code()))
				// {
				// data += "PRV*BI*PXC*" + objClaim.getClaimInfo().getTaxonomy_code() + "~";
				// segment++;
				// }
				//
				// }
				// } else {
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getFederal_taxidnumbertype())
				// && objClaim.getClaimInfo().getFederal_taxidnumbertype().equals("EIN")) {
				//
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getGrp_taxonomy_id()))
				// {
				// data += "PRV*BI*PXC*" + objClaim.getClaimInfo().getGrp_taxonomy_id() + "~";
				// segment++;
				// }
				//
				// } else if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getFederal_taxidnumbertype())
				// && objClaim.getClaimInfo().getFederal_taxidnumbertype().equals("SSN")) {
				//
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getTaxonomy_code()))
				// {
				// data += "PRV*BI*PXC*" + objClaim.getClaimInfo().getTaxonomy_code() + "~";
				// segment++;
				// }
				// }
				// }

				// if
				// (objClaim.getClaimInfo().getBillingprovidertype().toUpperCase().equals("GROUP"))
				// {
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getGrp_taxonomy_id()))
				// {
				// data += "PRV*BI*PXC*" + objClaim.getClaimInfo().getGrp_taxonomy_id() + "~";
				// segment++;
				// }
				// } else {
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getTaxonomy_code()))
				// {
				// data += "PRV*BI*PXC*" + objClaim.getClaimInfo().getTaxonomy_code() + "~";
				// segment++;
				// }
				// }
				// </editor-fold>
				// </editor-fold>
				// <editor-fold desc="LOOP 2010AA (Billing Provider Information)">
				// <editor-fold desc="Billing Provider Name">
				switch (box_33_type) {
				case "GROUP": // Group
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getBl_organization_name())) {

						data += "NM1*85*2*";
						data += objClaim.getClaimInfo().getBl_organization_name().trim() + "*****XX*";

					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("2010AA - Billing Provider Organization Name Missing.");
						objErrorList.add(obj);
						ErrorFlag++;
					}

					if (GeneralOperations.isNotNullorEmpty(Billing_Provider_NPI)) {
						data += Billing_Provider_NPI;
					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("2010AA - Billing Provider Group NPI Missing.");
						objErrorList.add(obj);
						ErrorFlag++;
					}
					break;
				case "INDIVIDUAL": // Individual
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getBl_lname())
							&& GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getBl_fname())) {

						data += "NM1*85*1*";
						data += objClaim.getClaimInfo().getBl_lname().trim() + "*"
								+ objClaim.getClaimInfo().getBl_fname().trim() + "*"
								+ objClaim.getClaimInfo().getBl_mi().trim() + "***XX*";

					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("2010AA - Billing Provider Name Missing.");
						objErrorList.add(obj);
						ErrorFlag++;
					}

					if (GeneralOperations.isNotNullorEmpty(Billing_Provider_NPI)) {
						data += Billing_Provider_NPI;
					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("2010AA - Billing Provider Individual NPI Missing.");
						objErrorList.add(obj);
						ErrorFlag++;
					}

					break;
				}
				data += "~";
				segment++;
				// if
				// ((objClaim.getClaimInfo().getBillingprovidertype().toUpperCase().equals("GROUP")
				// &&
				// GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getBl_organization_name()))
				// || (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getBl_lname())
				// &&
				// GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getBl_fname()))) {
				//
				// if
				// (objClaim.getClaimInfo().getBillingprovidertype().toUpperCase().equals("GROUP"))
				// {
				// data += "NM1*85*2*";
				// data += objClaim.getClaimInfo().getBl_organization_name().trim() +
				// "*****XX*";
				// } else {
				// data += "NM1*85*1*";
				// data += objClaim.getClaimInfo().getBl_lname().trim() + "*" +
				// objClaim.getClaimInfo().getBl_fname().trim() + "*" +
				// objClaim.getClaimInfo().getBl_mi().trim() + "***XX*";
				// }
				//
				// } else {
				// ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
				// obj.setError("2010AA - Billing Provider Name Missing.");
				// objErrorList.add(obj);
				// ErrorFlag++;
				// }
				//
				// if (GeneralOperations.isNotNullorEmpty(Billing_Provider_NPI)) {
				// data += Billing_Provider_NPI;
				// } else {
				// ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
				// obj.setError("2010AA - Billing Provider Group NPI Missing.");
				// objErrorList.add(obj);
				// ErrorFlag++;
				// }
				// data += "~";
				// segment++;

				// </editor-fold>
				// <editor-fold desc="BILLING PROVIDER ADDRESS">
				switch (box_33_type) {
				case "GROUP": // Group
					if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getBill_address_grp().trim())
							|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getBill_city_grp().trim())
							|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getBill_state_grp().trim())
							|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getBill_zip_grp().trim())) {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("BILLING ADDRESS ! Billing Provider Group Address is Missing.");
						objErrorList.add(obj);
						ErrorFlag++;
					} else {
						data += "N3*";
						data += objClaim.getClaimInfo().getBill_address_grp() + "~";
						segment++;
						data += "N4*";
						data += objClaim.getClaimInfo().getBill_city_grp().trim() + "*";
						data += objClaim.getClaimInfo().getBill_state_grp() + "*";
						if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getBill_zip_grp().trim())) {
							data += "     ";
						} else {
							data += objClaim.getClaimInfo().getBill_zip_grp().trim() + "~";
						}
						segment++;
					}
					break;
				case "INDIVIDUAL": // Individual
					if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getBl_address().trim())
							|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getBl_city().trim())
							|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getBl_state().trim())
							|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getBl_zip().trim())) {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("BILLING ADDRESS ! Billing Provider Individual Address is Missing.");
						objErrorList.add(obj);
						ErrorFlag++;
					} else {
						data += "N3*";
						data += objClaim.getClaimInfo().getBl_address() + "~";
						segment++;
						data += "N4*";
						data += objClaim.getClaimInfo().getBl_city().trim() + "*";
						data += objClaim.getClaimInfo().getBl_state() + "*";
						if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getBl_zip().trim())) {
							data += "     ";
						} else {
							data += objClaim.getClaimInfo().getBl_zip().trim() + "~";
						}
						segment++;
					}
					break;
				}

				// </editor-fold>
				// <editor-fold desc="BILLING PROVIDER Tax Identification">
				// hcfa box 25..
				if (GeneralOperations.isNotNullorEmpty(FederalTaxIDType)
						&& GeneralOperations.isNotNullorEmpty(FederalTaxID)) {
					if (FederalTaxIDType.equalsIgnoreCase("EIN")) {
						data += "REF*EI*";
					} else if (FederalTaxIDType.equalsIgnoreCase("SSN")) {
						data += "REF*SY*";
					}
					data += FederalTaxID + "~";
					segment += 1;
				} else {
					ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
					obj.setError("B_L_EIN_M_3!Billing provider federal tax id number/type missing…");
					objErrorList.add(obj);
					ErrorFlag++;
				}

				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo())
				// &&
				// GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo().getProvider_identification_number_type())
				// &&
				// GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo().getProvider_identification_number()))
				// {
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo().getProvider_identification_number_type())
				// &&
				// objClaim.getBillingProviderPayersInfo().getProvider_identification_number_type().equals("EIN"))
				// {
				//
				// data += "REF*EI*";
				//
				// } else if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo().getProvider_identification_number_type())
				// &&
				// objClaim.getBillingProviderPayersInfo().getProvider_identification_number_type().equals("SSN"))
				// {
				//
				// data += "REF*SY*";
				//
				// }
				//
				// data +=
				// objClaim.getBillingProviderPayersInfo().getProvider_identification_number() +
				// "~";
				// segment += 1;
				// } else {
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getFederal_taxidnumbertype())
				// && objClaim.getClaimInfo().getFederal_taxidnumbertype().equals("EIN")) {
				// data += "REF*EI*";
				// } else if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getFederal_taxidnumbertype())
				// && objClaim.getClaimInfo().getFederal_taxidnumbertype().equals("SSN")) {
				// data += "REF*SY*";
				// }
				// if
				// (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getFederal_taxid())
				// ||
				// GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getFederal_taxidnumbertype()))
				// {
				// ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
				// obj.setError("B_L_EIN_M_3!Billing provider federal tax id number/type
				// missing…");
				// objErrorList.add(obj);
				// ErrorFlag++;
				// }
				// data += objClaim.getClaimInfo().getFederal_taxid() + "~";
				// segment++;
				// }
				// </editor-fold>
				// <editor-fold desc="BILLING PROVIDER UPIN/LICENSE INFORMATION">
				// String modifier = "";
				// ---Setting Provider Payers Info if have--- ONLY OB and IG is allowed in 5010
				// format
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo())
				// &&
				// GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo().getProvider_modifier_code())
				// &&
				// (objClaim.getBillingProviderPayersInfo().getProvider_modifier_code().equalsIgnoreCase("OB")
				// ||
				// objClaim.getBillingProviderPayersInfo().getProvider_modifier_code().equalsIgnoreCase("IG")))
				// {
				// modifier =
				// objClaim.getBillingProviderPayersInfo().getProvider_modifier_code();
				// data += "REF*" + modifier + "*"; // 5010 ==> Only OB and IG is allowed
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getBillingProviderPayersInfo().getGroup_number()))
				// {
				// data += objClaim.getBillingProviderPayersInfo().getGroup_number() + "~";
				// } else {
				// data += objClaim.getBillingProviderPayersInfo().getProvider_number() + "~";
				// }
				// segment++;
				// }
				// </editor-fold>
				// <editor-fold desc="BILLING PROVIDER CONTACT INFORMATION">
				// change1
				switch (FederalTaxIDType) {
				case "EIN":
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getBl_organization_name())
							&& GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPhone_no())) {
						data += "PER*IC*" + objClaim.getClaimInfo().getBl_organization_name();
						data += "*TE*" + objClaim.getClaimInfo().getPhone_no().replace("-", "").replace("(", "")
								.replace(")", "").replace(" ", "").trim() + "~";
						segment++;
					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("Billing Provider Contact Information Missing.");
						objErrorList.add(obj);
						ErrorFlag++;
					}
					break;
				case "SSN":
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getBl_lname())
							&& GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPhone_no())) {
						data += "PER*IC*" + objClaim.getClaimInfo().getBl_lname();
						data += "*TE*" + objClaim.getClaimInfo().getPhone_no().replace("-", "").replace("(", "")
								.replace(")", "").replace(" ", "").trim() + "~";
						segment++;
					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("Billing Provider Contact Information Missing.");
						objErrorList.add(obj);
						ErrorFlag++;
					}
					break;
				}

				// if (GeneralOperations.isNotNullorEmpty(BillingLocationName)) {
				// data += "PER*IC*";
				// data += BillingLocationName;
				//
				// if
				// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPhone_no())) {
				// data += "*TE*" + objClaim.getClaimInfo().getPhone_no().replace("-",
				// "").replace("(", "").replace(")", "").replace(" ", "").trim() + "~";
				// } else {
				// ORMClaim_Batch_Errors objErrors = new ORMClaim_Batch_Errors();
				// objErrors.setError("No_Bi_Lo-Se_1! BILLING PROVIDER CONTACT INFORMATION IS
				// MISSING.");
				// objErrors.setDos(ReadyClaims.get(0).getClaimInfo().getDos());
				// objErrors.setPatient_name(ReadyClaims.get(0).getClaimInfo().getLname() + ", "
				// + ReadyClaims.get(0).getClaimInfo().getFname());
				// objErrorList.add(objErrors);
				// Bigerror_flag++;
				// }
				// segment++;
				// } else {
				// ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
				// obj.setError("BlC_M_B_L_1!name missing for billing location.");
				// objErrorList.add(obj);
				// ErrorFlag++;
				// }
				// </editor-fold>
				// </editor-fold>
				// <editor-fold desc="LOOP 2010AB (PAY-TO ADDRESS NAME)">
				switch (box_33_type) {
				case "GROUP": // Group
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPay_to_address_grp().trim())
							|| GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPay_to_city_grp().trim())
							|| GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPay_to_state_grp().trim())
							|| GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPay_to_zip_grp().trim())) {

						if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getPay_to_address_grp().trim())
								|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getPay_to_city_grp().trim())
								|| GeneralOperations
										.isNullorEmpty(objClaim.getClaimInfo().getPay_to_state_grp().trim())) {

							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("2010AB : Pay to Provider Group Address is incomplete.");
							objErrorList.add(obj);
							ErrorFlag++;
						} else {
							switch (FederalTaxIDType) {
							case "EIN":
								data += "NM1*87*2~";
								segment++;
								break;
							case "SSN":
								data += "NM1*87*1~";
								segment++;
								break;
							}

							data += "N3*";
							data += objClaim.getClaimInfo().getPay_to_address_grp() + "~";
							segment++;

							data += "N4*";
							data += objClaim.getClaimInfo().getPay_to_city_grp().trim() + "*";
							data += objClaim.getClaimInfo().getPay_to_state_grp() + "*";
							if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getPay_to_zip_grp().trim())) {
								data += "     ";
							} else {
								data += objClaim.getClaimInfo().getPay_to_zip_grp().trim() + "~";
							}
							segment++;

						}
					}
					break;
				case "INDIVIDUAL": // Individual
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPay_to_address().trim())
							|| GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPay_to_city().trim())
							|| GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPay_to_state().trim())
							|| GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPay_to_zip().trim())) {

						if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getPay_to_address().trim())
								|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getPay_to_city().trim())
								|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getPay_to_state().trim())) {

							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("2010AB : Pay to Provider Individual Address is incomplete.");
							objErrorList.add(obj);
							ErrorFlag++;
						} else {
							switch (FederalTaxIDType) {
							case "EIN":
								data += "NM1*87*2~";
								segment++;
								break;
							case "SSN":
								data += "NM1*87*1~";
								segment++;
								break;
							}

							data += "N3*";
							data += objClaim.getClaimInfo().getPay_to_address() + "~";
							segment++;

							data += "N4*";
							data += objClaim.getClaimInfo().getPay_to_city().trim() + "*";
							data += objClaim.getClaimInfo().getPay_to_state() + "*";
							if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getPay_to_zip().trim())) {
								data += "     ";
							} else {
								data += objClaim.getClaimInfo().getPay_to_zip().trim() + "~";
							}
							segment++;

						}
					}
					break;
				}

				// </editor-fold>
				int P = HL;
				HL = HL + 1;
				int CHILD = 0;

				String SBR02 = "18";

				// ---Extract Primar Secondary and Other Insurance Information before
				// processing-----------
				ORMSubmission_ClaimInsuarnceInfo primaryIns = new ORMSubmission_ClaimInsuarnceInfo();
				ORMSubmission_ClaimInsuarnceInfo SecondaryIns = new ORMSubmission_ClaimInsuarnceInfo();
				ORMSubmission_ClaimInsuarnceInfo otherIns = new ORMSubmission_ClaimInsuarnceInfo();
				for (ORMSubmission_ClaimInsuarnceInfo obj : objClaim.getClaimInsurance()) {
					switch (obj.getInsurace_type().toUpperCase().trim()) {
					case "PRIMARY":
						primaryIns = obj;
						break;
					case "SECONDARY":
						SecondaryIns = obj;
						break;
					case "OTHER":
						otherIns = obj;
						break;
					}
				}
				// --End

				if (GeneralOperations.isNullorEmpty(primaryIns.getClaiminsurance_id())) {
					ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
					obj.setError("Ins_Inf_M!Patient has no primary insurance.");
					objErrorList.add(obj);
					ErrorFlag++;
				} else {
					if (GeneralOperations.isNotNullorEmpty(primaryIns.getGuarantor_relationship())
							&& primaryIns.getGuarantor_relationship().trim().toUpperCase().equals("SELF")) {
						primaryIns.setGlname(objClaim.getClaimInfo().getLname());
						primaryIns.setGfname(objClaim.getClaimInfo().getFname());
						primaryIns.setGmi(objClaim.getClaimInfo().getMname());
						primaryIns.setGaddress(objClaim.getClaimInfo().getAddress());
						primaryIns.setGcity(objClaim.getClaimInfo().getCity());
						primaryIns.setGdob(objClaim.getClaimInfo().getDob());
						primaryIns.setGgender(objClaim.getClaimInfo().getGender());
						primaryIns.setGstate(objClaim.getClaimInfo().getState());
						primaryIns.setGzip(objClaim.getClaimInfo().getZip());
					}

					// --End
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInsurance())) {

						if (GeneralOperations.isNullorEmpty(primaryIns)) // ||
																			// GeneralOperations.isNullorEmpty(primaryIns.getGuarantor_id()))
						{
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("S_Inf_M_1!Subscriber information missing.");
							objErrorList.add(obj);
							ErrorFlag++;
						}
						if (GeneralOperations.isNullorEmpty(primaryIns.getInspayer_id())) {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("P_Inf_N_1!Payer's information not entered.");
							objErrorList.add(obj);
							ErrorFlag++;
						}
						// ---IF Subscriber is not SELF-----
						if (GeneralOperations.isNotNullorEmpty(primaryIns)
								&& GeneralOperations.isNotNullorEmpty(primaryIns.getGuarantor_relationship())
								&& !primaryIns.getGuarantor_relationship().toUpperCase().trim().equals("SELF")) {
							// SBR02 = "00";
							SBR02 = "";
							CHILD = 1;
						}

						// <editor-fold desc="LOOP 2000B">
						// <editor-fold desc="HL: SUBSCRIBER HIERARCHICAL LEVEL">
						data += "HL*";
						data += HL + "*" + P + "*";
						data += "22*" + CHILD + "~";
						segment++;
						// </editor-fold>

						// flage1
						// <editor-fold desc="SBR: SUBSCRIBER INFORMATION">
						data += "SBR*";
						if (GeneralOperations.isNotNullorEmpty(primaryIns)) {
							data += "P";
						} else if (GeneralOperations.isNotNullorEmpty(SecondaryIns)) {
							data += "S";
						} else if (GeneralOperations.isNotNullorEmpty(otherIns)) {
							data += "T";
						}
						data += "*";
						String groupNo = "";
						String planName = "";

						if (GeneralOperations.isNotNullorEmpty(primaryIns.getGroup_number())) {
							groupNo = primaryIns.getGroup_number();
						} else {
							groupNo = "";
						}
						if (GeneralOperations.isNotNullorEmpty(primaryIns.getPayertype_name())
								&& primaryIns.getPayertype_name().equals("MEDICARE")) {
							if (GeneralOperations.isNotNullorEmpty(primaryIns.getPlan_name())
									&& primaryIns.getPlan_name().toUpperCase().contains("MEDICARE")) {
								planName = primaryIns.getPlan_name();
							} else {
								planName = "MEDICARE";
							}
						} else {
							planName = primaryIns.getPlan_name();
						}

						// ---------***********************************-------------
						data += SBR02 + "*" + groupNo + "*" + planName + "*****" + primaryIns.getPayertype_code() + "~";
						segment++;
						// --------------------

						// </editor-fold>
						// </editor-fold>
						// <editor-fold desc="LOOP 2000BA (SUBSCRIBER Information)">
						data += "NM1*IL*1*";
						if ((GeneralOperations.isNullorEmpty(primaryIns.getGlname())
								|| GeneralOperations.isNullorEmpty(primaryIns.getGfname()))
								&& GeneralOperations.isNullorEmpty(primaryIns.getGuarantor_relationship())
								&& !primaryIns.getGuarantor_relationship().trim().toUpperCase().equals("SELF")) {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("S_N_M_1!Subscriber Last/First Name missing.");
							objErrorList.add(obj);
							ErrorFlag++;
						}
						// Entering Subscriber Information if Relationship is SELF-----
						if (SBR02.equals("18")) {

							if (!GeneralOperations.isAlphaNumericWithSpace(objClaim.getClaimInfo().getLname())
									&& !GeneralOperations.isAlphaNumericWithSpace(objClaim.getClaimInfo().getFname())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("RPH_INF_M_6!Subscriber Name must be Alpha Numeric.");
								objErrorList.add(obj);
								ErrorFlag++;
							} else {

								data += objClaim.getClaimInfo().getLname() + "*" + objClaim.getClaimInfo().getFname()
										+ "*" + objClaim.getClaimInfo().getMname() + "***MI*"
										+ primaryIns.getPolicy_number().toUpperCase() + "~";
								segment++;

							}

							if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getAddress())
									|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getCity())
									|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getState())
									|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getZip())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("S_A_In_1!Patient Address incomplete.");
								objErrorList.add(obj);
								ErrorFlag++;
							}

							data += "N3*" + objClaim.getClaimInfo().getAddress() + "~";
							segment++;
							data += "N4*" + objClaim.getClaimInfo().getCity() + "*" + objClaim.getClaimInfo().getState()
									+ "*";
							data += ((GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getZip()))
									? objClaim.getClaimInfo().getZip()
									: "     ") + "~";
							segment++;

							data += "DMG*D8*";
							if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getDob())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("S_D_B_M_1!Patient Date of birth missing.");
								objErrorList.add(obj);
								ErrorFlag++;
							}
							/*
							 * data += GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getDob())
							 * ? objClaim.getClaimInfo().getDob().split("/")[0] +
							 * objClaim.getClaimInfo().getDob().split("/")[1] +
							 * objClaim.getClaimInfo().getDob().split("/")[2] : "";
							 */
							data += GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getDob())
									? objClaim.getClaimInfo().getDob()
									: "";

							data += "*";

							if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getGender())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("S_R_M_X_2!Patient gender missing.");
								objErrorList.add(obj);
								ErrorFlag++;
							} else {
								data += (objClaim.getClaimInfo().getGender().length() > 0
										? objClaim.getClaimInfo().getGender().substring(0, 1)
										: "") + "~";
								segment++;
							}
						} // --END
						else // ---Entering Subscriber Information In case of other than SELF---------
						{
							data += primaryIns.getGlname() + "*" + primaryIns.getGfname() + "*" + primaryIns.getGmi()
									+ "***MI*" + primaryIns.getPolicy_number().toUpperCase() + "~";
							segment++;

							if (GeneralOperations.isNullorEmpty(primaryIns.getGaddress())
									|| GeneralOperations.isNullorEmpty(primaryIns.getGcity())
									|| GeneralOperations.isNullorEmpty(primaryIns.getGstate())
									|| GeneralOperations.isNullorEmpty(primaryIns.getGzip())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("S_A_In_1!Subscriber Address incomplete.");
								objErrorList.add(obj);
								ErrorFlag++;
							}

							data += "N3*" + primaryIns.getGaddress() + "~";
							segment++;
							data += "N4*" + primaryIns.getGcity() + "*" + primaryIns.getGstate() + "*";
							data += (GeneralOperations.isNotNullorEmpty(primaryIns.getGzip()) ? primaryIns.getGzip()
									: "     ") + "~";
							segment++;

							data += "DMG*D8*";
							if (GeneralOperations.isNullorEmpty(primaryIns.getGdob())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("S_D_B_M_1!Subscriber date of birthmissing.");
								objErrorList.add(obj);
								ErrorFlag++;
							}
							/*
							 * data += GeneralOperations.isNotNullorEmpty(primaryIns.getGdob()) ?
							 * primaryIns.getGdob().split("/")[0] + primaryIns.getGdob().split("/")[1] +
							 * primaryIns.getGdob().split("/")[2] : "";
							 */
							data += GeneralOperations.isNotNullorEmpty(primaryIns.getGdob()) ? primaryIns.getGdob()
									: "";
							data += "*";
							if (GeneralOperations.isNullorEmpty(primaryIns.getGgender())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("S_R_M_X_2!Subscriber gendermissing.");
								objErrorList.add(obj);
								ErrorFlag++;
							} else {
								data += primaryIns.getGgender().substring(0, 1) + "~";
								segment++;
							}
						}
						// </editor-fold>

						// <editor-fold desc="LOOP 2010BB (PAYER INFORMATION)">
						if (GeneralOperations.isNullorEmpty(primaryIns.getPlan_name())) {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("P_Na_M_1!Payer name missing.");
							objErrorList.add(obj);
							ErrorFlag++;
						}
						String paperPayerName = "";
						if (GeneralOperations.isNotNullorEmpty(primaryIns.getPayertype_name())
								&& primaryIns.getPayertype_name().trim().toUpperCase().equals("MEDICARE")) {
							paperPayerName = "MEDICARE";
						} else {
							paperPayerName = primaryIns.getPlan_name();
						}
						if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getPaper_payer_id())) {
							if (GeneralOperations.isNotNullorEmpty(primaryIns.getPayer_number())) {
								paperPayerID = primaryIns.getPayer_number();
							}
						} else {
							paperPayerID = objClaim.getClaimInfo().getPaper_payer_id();
						}

						if (GeneralOperations.isNotNullorEmpty(paperPayerID)) {
							data += "NM1*PR*2*" + paperPayerName + "*****PI*" + paperPayerID + "~";
							segment++;
						} else {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("Payerid_Miss_2!Payer id is compulsory in case of Gateway EDI Clearing house");
							objErrorList.add(obj);
							ErrorFlag++;
						}

						if (GeneralOperations.isNotNullorEmpty(primaryIns.getPayertype_name())
								&& primaryIns.getPayertype_name().trim().toUpperCase().equals("WORK COMP")) {
							if (GeneralOperations.isNullorEmpty(primaryIns.getSub_empaddress())
									|| GeneralOperations.isNullorEmpty(primaryIns.getSub_emp_city())
									|| GeneralOperations.isNullorEmpty(primaryIns.getSub_emp_state())
									|| GeneralOperations.isNullorEmpty(primaryIns.getSub_emp_zip())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError(
										"P_Ad_M_2!Payer is Worker Company, so its subscriber employer’s address is necessary.");
								objErrorList.add(obj);
								ErrorFlag++;
							}
							data += "N3*" + primaryIns.getSub_empaddress() + "~";
							segment++;

							data += "N4*" + primaryIns.getSub_emp_city() + "*" + primaryIns.getSub_emp_state() + "*";
							if (GeneralOperations.isNullorEmpty(primaryIns.getSub_emp_zip())) {
								data += "     " + "~";
							} else {
								data += primaryIns.getSub_emp_zip() + "~";
							}
							segment++;
						} else {
							if (GeneralOperations.isNullorEmpty(primaryIns.getIns_address())
									|| GeneralOperations.isNullorEmpty(primaryIns.getIns_city())
									|| GeneralOperations.isNullorEmpty(primaryIns.getIns_state())
									|| GeneralOperations.isNullorEmpty(primaryIns.getIns_zip())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("P_Ad_M_2!Payer address incomplete.");
								objErrorList.add(obj);
								ErrorFlag++;
							}
							data += "N3*" + primaryIns.getIns_address() + "~";
							segment++;

							data += "N4*" + primaryIns.getIns_city() + "*" + primaryIns.getIns_state() + "*";
							data += (GeneralOperations.isNullorEmpty(primaryIns.getIns_zip())) ? "     "
									: primaryIns.getIns_zip().trim() + "~";
							segment++;
						}

						// </editor-fold>
						// <editor-fold desc="LOOP 2010C , 2010CA">
						if (GeneralOperations.isNotNullorEmpty(primaryIns.getGuarantor_relationship())
								&& !primaryIns.getGuarantor_relationship().toUpperCase().trim().equals("SELF")) {

							// <editor-fold desc="LOOP 2000C">
							// <editor-fold desc="HL : (PATIENT HIERARCHICAL LEVEL)">
							int PHL = HL;
							HL++;
							data += "HL*" + HL + "*" + PHL + "*23*0~";
							segment++;
							// </editor-fold>

							// <editor-fold desc="PAT : (PATIENT RELATIONAL INFORMATION)">
							data += "PAT*";

							if (GeneralOperations.isNullorEmpty(primaryIns.getGuarantor_relationship())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("S_R_M_2!Subscriber relationship missing.");
								objErrorList.add(obj);
								ErrorFlag++;
							}

							String temp = "";
							if (primaryIns.getGuarantor_relationship().trim().toUpperCase().equals("SELF")) {
								temp = "18";
							} else if (primaryIns.getGuarantor_relationship().trim().toUpperCase().equals("SPOUSE")) {
								temp = "01";
							} else if (primaryIns.getGuarantor_relationship().trim().toUpperCase().equals("CHILD")) {
								temp = "19";
							} else if (primaryIns.getGuarantor_relationship().trim().toUpperCase().equals("OTHER")) {
								temp = "G8";
							}
							data += temp + "****D8***~";
							segment++;
							// </editor-fold>

							// </editor-fold>
							// <editor-fold desc="LOOP 2010CA">
							// <editor-fold desc="PATIENT NAME INFORMATION">
							data += "NM1*QC*1*";

							// ----ENTERING PATIENT INFORMATION NOW------------
							data += objClaim.getClaimInfo().getLname() + "*";
							data += objClaim.getClaimInfo().getFname() + "*";
							data += objClaim.getClaimInfo().getMname() + "***MI*";
							if (GeneralOperations.isNullorEmpty(primaryIns.getPolicy_number())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("S_R_M_2122!Subscriber policy number missing.");
								objErrorList.add(obj);
								ErrorFlag++;
							}
							data += primaryIns.getPolicy_number().toUpperCase() + "~";
							segment++;
							data += "N3*" + objClaim.getClaimInfo().getAddress() + "~";
							segment++;
							data += "N4*" + objClaim.getClaimInfo().getCity().trim() + "*"
									+ objClaim.getClaimInfo().getState().trim() + "*"
									+ objClaim.getClaimInfo().getZip().trim() + "~";
							segment++;

							if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getGender())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("S_R_M_X_2!Patient gender missing.");
								objErrorList.add(obj);
								ErrorFlag++;
							}
							/*
							 * data += "DMG*D8*" + objClaim.getClaimInfo().getDob().split("/")[0] +
							 * objClaim.getClaimInfo().getDob().split("/")[1] +
							 * objClaim.getClaimInfo().getDob().split("/")[2] + "*" +
							 * objClaim.getClaimInfo().getGender().trim().substring(0, 1) + "~";
							 */

							data += "DMG*D8*" + objClaim.getClaimInfo().getDob() + "*" // YYYYMMDD
									+ objClaim.getClaimInfo().getGender().trim().substring(0, 1) + "~";

							segment++;

							// </editor-fold>
							// </editor-fold>
						}

					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError(
								"S_R_M_X_2!PIND_M_2!Patient Insurance Information missing, if in demographics information is exist , then check insurance from encounter");
						objErrorList.add(obj);
						ErrorFlag++;
					}
					// </editor-fold>

					HL++;

					// <editor-fold desc="LOOP 2300">
					data += "CLM*" + objClaim.getClaim_id() + "*";
					BigDecimal total_amount = new BigDecimal(0);

					if (objClaim.getClaimInfo().getIs_resubmitted()) {
						for (ORMSubmission_ClaimProcedures objProc : objClaim.getClaimProcedures()) {
							if (objProc.getIs_resubmitted()) {
								total_amount = total_amount.add(objProc.getTotal_charges().setScale(2));
							}
						}
					} else {
						total_amount = objClaim.getClaimInfo().getClaim_total().setScale(2);
					}

					String ClaimFrequencyCode = objClaim.getClaimInfo().getIs_corrected() ? "7" : "1";
					String PatFirstVisitDate = "";

					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getFirst_visit_date())) {
						// String[] splitedFVD =
						// objClaim.getClaimInfo().getFirst_visit_date().split("/");
						// PatFirstVisitDate = "DTP*454*D8*" + splitedFVD[0] + splitedFVD[1] +
						// splitedFVD[2] + "~";
						PatFirstVisitDate = "DTP*454*D8*" + objClaim.getClaimInfo().getFirst_visit_date() + "~";
					}
					data += total_amount.setScale(2).toString() + "***" + objClaim.getClaimInfo().getClaim_pos() + ":B:"
							+ ClaimFrequencyCode + "*Y*A*Y*Y*P"; // 5010

					int isErrorInAccident = 0;
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getAccident_type().trim())) {
						switch (objClaim.getClaimInfo().getAccident_type()) {
						case "OA":
							data += "*OA";
							break;
						case "AA":
							data += "*AA";
							break;
						case "EM":
							data += "*EM";
							break;
						default:
							isErrorInAccident = 1;
							break;
						}
						if (isErrorInAccident == 0) {
							if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getAccident_state())) {
								data += ":::" + objClaim.getClaimInfo().getAccident_state() + "~";
							} else {
								if (objClaim.getClaimInfo().getAccident_type().equals("OA")
										|| objClaim.getClaimInfo().getAccident_type().equals("EM")) {
									data += "~";
									segment++;
								} else {
									isErrorInAccident = 2;
								}
							}

							if (isErrorInAccident == 0) {
								// <editor-fold desc="DATE INITIAL TREATMENT">
								if (GeneralOperations.isNotNullorEmpty(PatFirstVisitDate)) {
									data += PatFirstVisitDate;
									segment++;
								}
								// </editor-fold>

								// <editor-fold desc="DATE ACCIDENT">
								data += "DTP*439*D8*";
								if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getAccident_date())
										&& objClaim.getClaimInfo().getAccident_date().equals("19000101")) {

									/*
									 * String[] splitedAccidentDate = objClaim.getClaimInfo().getAccident_date()
									 * .split("/"); if (splitedAccidentDate.length != 3) { isErrorInAccident = 3; }
									 * data += splitedAccidentDate[0] + splitedAccidentDate[1] +
									 * splitedAccidentDate[2] + "~";
									 */
									data += objClaim.getClaimInfo().getAccident_date() + "~";
									segment++;
								} else {
									isErrorInAccident = 3;
								}
								// </editor-fold>
							}
						}

					} else {
						data += "~";
						segment++;

						// <editor-fold desc="DATE INITIAL TREATMENT">
						if (GeneralOperations.isNotNullorEmpty(PatFirstVisitDate)) {
							data += PatFirstVisitDate;
							segment++;
						}
						// </editor-fold>
					}

					// <editor-fold desc="DATE - LAST X-RAY">
					// String LastXrayDate = "";
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getLast_xray_date())
							&& !objClaim.getClaimInfo().getLast_xray_date().equals("19000101")) {
						// String[] spltdlastXrayDate =
						// objClaim.getClaimInfo().getLast_xray_date().split("/");
						// LastXrayDate = spltdlastXrayDate[0] + spltdlastXrayDate[1] +
						// spltdlastXrayDate[2];
						// data += "DTP*455*D8*" + LastXrayDate + "~";
						data += "DTP*455*D8*" + objClaim.getClaimInfo().getLast_xray_date() + "~";
						segment++;
					}
					// </editor-fold>

					// <editor-fold desc="DATE - ADMISSION (HOSPITALIZATION)">
					// String hospitalFromDate = "";
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getHospital_from())
							&& !objClaim.getClaimInfo().getHospital_from().equals("19000101")) {
						// String[] spltdHospitalFromDate =
						// objClaim.getClaimInfo().getHospital_from().split("/");
						// if (spltdHospitalFromDate.length != 3) {
						// isErrorInAccident = 4;
						// }
						// hospitalFromDate = spltdHospitalFromDate[0] + spltdHospitalFromDate[1]
						// + spltdHospitalFromDate[2];

						data += "DTP*435*D8*" + objClaim.getClaimInfo().getHospital_from() + "~"; // YYYYMMDD
						segment++;
					}
					// </editor-fold>

					// <editor-fold desc="DATE - DISCHARGE (HOSPITALIZATION)">
					// String hospitalToDate = "";
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getHospital_to())
							&& !objClaim.getClaimInfo().getHospital_to().equals("19000101")) {
						// String[] spltdHospitalToDate =
						// objClaim.getClaimInfo().getHospital_to().split("/");
						// if (spltdHospitalToDate.length != 3) {
						// isErrorInAccident = 5;
						// }
						// hospitalFromDate = spltdHospitalToDate[0] + spltdHospitalToDate[1] +
						// spltdHospitalToDate[2];
						data += "DTP*096*D8*" + objClaim.getClaimInfo().getHospital_to() + "~"; // YYYYMMDD
						segment++;
					}
					// </editor-fold>

					if (isErrorInAccident >= 1) {
						if (isErrorInAccident == 1) {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("AC_INF_M_5!Accident Type is necessary");
							objErrorList.add(obj);
							ErrorFlag++;
						} else if (isErrorInAccident == 2) {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("AC_INF_M_5!In case of Auto Accident   , state of accident is necessary");
							objErrorList.add(obj);
							ErrorFlag++;
						} else if (isErrorInAccident == 3) {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("AC_INF_M_5!Format of date of accident is not correct");
							objErrorList.add(obj);
							ErrorFlag++;
						} else if (isErrorInAccident == 4) {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("AC_INF_M_5!In Case of accident  date of accident is necessary ");
							objErrorList.add(obj);
							ErrorFlag++;
						}
					}

					// <editor-fold desc="PRIOR AUTHORIZATION">
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPrior_authorization())) {
						data += "REF*G1*" + objClaim.getClaimInfo().getPrior_authorization() + "~";
						segment++;
					}
					// </editor-fold>

					// <editor-fold desc="PAYER CLAIM CONTROL NUMBER">
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getClaim_number())) {
						data += "REF*F8*" + objClaim.getClaimInfo().getClaim_number() + "~";
						segment++;
					}
					// </editor-fold>

					// <editor-fold desc="CLINICAL LABORATORY IMPROVEMENT AMENDMENT (CLIA) NUMBER">
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getClia_number())) {
						data += "REF*X4*" + objClaim.getClaimInfo().getClia_number() + "~";
						segment++;
					}
					// </editor-fold>

					// <editor-fold desc="CLAIM NOTE">
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getLuo())) {
						data += "NTE*ADD*" + objClaim.getClaimInfo().getLuo() + "~";
						segment++;
					}
					// </editor-fold>

					// <editor-fold desc="HEALTH CARE DIAGNOSIS CODE">
					data += "HI*";

					// ICD-10 Claim
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getIcd_10_claim())
							&& objClaim.getClaimInfo().getIcd_10_claim() == true) {
						data += "ABK:"; // BK=ICD-9 ABK=ICD-10
					} else // ICD-9 Claim
					{
						data += "BK:"; // BK=ICD-9 ABK=ICD-10
					}

					// Adding claim ICDS Diagnosis COdes
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimDiagnosis())
							&& objClaim.getClaimDiagnosis().size() > 0) {
						for (int i = 0; i < 12; i++) {
							if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimDiagnosis())
									&& objClaim.getClaimDiagnosis().size() > i) {
								if (i == 0 && GeneralOperations.isNotNullorEmpty(objClaim.getClaimDiagnosis().get(i))) {
									data += objClaim.getClaimDiagnosis().get(i).getDiag_code().trim().replace(".", "");
								} else {

									// ICD-10 Claim
									if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getIcd_10_claim())
											&& objClaim.getClaimInfo().getIcd_10_claim() == true) {
										data += "*ABF:" + objClaim.getClaimDiagnosis().get(i).getDiag_code().trim()
												.replace(".", "");
										// BF==ICD-9 ABF=ICD-10
									} else // ICD-9 Claim
									{
										data += "*BF:" + objClaim.getClaimDiagnosis().get(i).getDiag_code().trim()
												.replace(".", "");
										// BF==ICD-9 ABF=ICD-10
									}

								}
							}
						}
					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getIcd_10_claim())
								&& objClaim.getClaimInfo().getIcd_10_claim() == true) {
							obj.setError("HI*ABK:ABF!Claims Diagnosis (ICD-10) are missing");
						} else {
							obj.setError("HI*BK:BF!Claims Diagnosis (ICD-9) are missing");
						}

						objErrorList.add(obj);
						ErrorFlag++;
					}
					data += "~";
					segment++;
					// </editor-fold>

					// </editor-fold>
					// <editor-fold desc="LOOP 2310A (REFERRING PROVIDER)">
					if (ignore_ref_phy_error == false
							&& GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getReferring_physician())) {
						if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getRef_npi())) {

							if (!GeneralOperations.isAlphaNumericWithSpace(objClaim.getClaimInfo().getRef_lname())
									&& !GeneralOperations
											.isAlphaNumericWithSpace(objClaim.getClaimInfo().getRef_fname())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("RPH_INF_M_6!Referring provider’s Name must be Alpha Numeric.");
								objErrorList.add(obj);
								ErrorFlag++;
							} else {
								data += "NM1*DN*1*" + objClaim.getClaimInfo().getRef_lname() + "*"
										+ objClaim.getClaimInfo().getRef_fname() + "****XX*"
										+ objClaim.getClaimInfo().getRef_npi() + "~";

								segment++;
							}

							// --Committing the ref secondary identification segment Loop 2310A (REFERRING
							// PROVIDER SECONDARY IDENTIFICATION)
							// if (GeneralOperations.isNotNullorEmpty(primaryIns.getPayertype_code()) &&
							// primaryIns.getPayertype_code().trim().equals("MC")) {
							// if
							// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getHcfa_17_a())
							// &&
							// GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPaper_payer_id()))
							// {
							// data += "REF*1G*" + objClaim.getClaimInfo().getHcfa_17_a() + "~";
							// segment++;
							// } else if
							// (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getHcfa_17_a()) &&
							// GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getPaper_payer_id()))
							// {
							// ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							// obj.setError("RPH_INF_M_156!Hcfa 17 A is missing in Encounter.");
							// objErrorList.add(obj);
							// ErrorFlag++;
							// } else if
							// (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getHcfa_17_a())
							// &&
							// GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getPaper_payer_id()))
							// {
							// ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							// obj.setError("RPH_INF_M_166!Paper Payer id is missing in Encounter.");
							// objErrorList.add(obj);
							// ErrorFlag++;
							// } else {
							// data += "REF*1G*" + objClaim.getClaimInfo().getRef_upin() + "~";
							// segment++;
							// }
							// } else {
							// data += "REF*1G*" + objClaim.getClaimInfo().getRef_upin() + "~";
							// segment++;
							// }
						} else {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("RPH_INF_M_6!Referring provider’s NPI is missing.");
							objErrorList.add(obj);
							ErrorFlag++;
						}
					}
					// </editor-fold>

					// <editor-fold desc="LOOP 2310B (RENDERING PROVIDER)">
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getAttending_physician())) {

						// <editor-fold desc="RENDERING PROVIDER NAME">
						if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getAtt_npi())) {

							if (!GeneralOperations.isAlphaNumericWithSpace(objClaim.getClaimInfo().getAtt_lname())
									&& !GeneralOperations
											.isAlphaNumericWithSpace(objClaim.getClaimInfo().getAtt_fname())) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("RPH_INF_M_6!Rendering provider’s Name must be Alpha Numeric.");
								objErrorList.add(obj);
								ErrorFlag++;
							} else {
								data += "NM1*82*1*" + objClaim.getClaimInfo().getAtt_lname() + "*"
										+ objClaim.getClaimInfo().getAtt_fname() + "****XX*"
										+ objClaim.getClaimInfo().getAtt_npi() + "~";
								segment++;
							}

						} else {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("P_INF_M_69!Rendering Provider NPI Missing.");
							objErrorList.add(obj);
							ErrorFlag++;
						}

						// </editor-fold>
						// <editor-fold desc="RENDERING PROVIDER SPECIALTY INFORMATION">
						if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getAtt_taxonomy_code())) {
							data += "PRV*PE*PXC*" + objClaim.getClaimInfo().getAtt_taxonomy_code() + "~"; // 5010 CODE
																											// CHAGED
																											// FROM ZZ
																											// TO PXC
							segment++;
						} else {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("P_INF_M_5!Gateway edi require Rendering Provider Taxonomy Code");
							objErrorList.add(obj);
							ErrorFlag++;
						}
						// </editor-fold>

						// <editor-fold desc="RENDERING PROVIDER SPECIALTY INFORMATION">
						if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getAtt_state_license())) {

							data += "REF*0B*" + objClaim.getClaimInfo().getAtt_state_license() + "~";
							segment++;
						}
						// </editor-fold>

					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("P_INF_M_5!Provider Information missing.");
						objErrorList.add(obj);
						ErrorFlag++;
					}
				}
				// </editor-fold>

				// <editor-fold desc="LOOP 2310C (SERVICE FACILITY LOCATION)">
				if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getFacility_npi())) {
					data += "NM1*77*2*" + objClaim.getClaimInfo().getFacility_name() + "*****XX*"
							+ objClaim.getClaimInfo().getFacility_npi() + "~";
				} else {
					data += "NM1*77*2*" + objClaim.getClaimInfo().getFacility_name() + "*****XX*~";
				}
				segment++;

				if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getFacility_address())
						|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getFacility_city())
						|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getFacility_state())
						|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getFacility_zip())) {
					ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
					obj.setError("F_Ad_I_5!Facility's address incomplete.");
					objErrorList.add(obj);
					ErrorFlag++;
				}

				data += "N3*" + objClaim.getClaimInfo().getFacility_address() + "~";
				segment++;
				data += "N4*" + objClaim.getClaimInfo().getFacility_city() + "*"
						+ objClaim.getClaimInfo().getFacility_state() + "*";
				if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getFacility_zip())) {
					data += "     " + "~";
				} else {
					data += objClaim.getClaimInfo().getFacility_zip() + "~";
				}
				segment++;
				// </editor-fold>

				if (GeneralOperations.isNotNullorEmpty(SecondaryIns)
						&& GeneralOperations.isNotNullorEmpty(SecondaryIns.getInsurance_id())) {

					// flage2
					// <editor-fold desc="LOOP 2320">
					// <editor-fold desc="OTHER SUBSCRIBER INFORMATION">
					String SBR02_secondary = "18";

					if (GeneralOperations.isNotNullorEmpty(SecondaryIns.getGuarantor_relationship())) {

						switch (SecondaryIns.getGuarantor_relationship().toUpperCase()) {
						case "CHILD":
							SBR02_secondary = "19";
							break;
						case "SPOUSE":
							SBR02_secondary = "01";
							break;
						case "SELF":
							SBR02_secondary = "18";
							break;
						case "OTHER":
							SBR02_secondary = "G8";
							break;
						}
					}

					data += "SBR*S*";
					String PlanName = "", InsPayerTypeCode = "";

					if (GeneralOperations.isNotNullorEmpty(SecondaryIns.getPayertype_name())
							&& SecondaryIns.getPayertype_name().contains("MEDICARE")) {
						if (GeneralOperations.isNotNullorEmpty(SecondaryIns.getPlan_name())
								&& SecondaryIns.getPlan_name().toUpperCase().contains("MEDICARE")) {
							PlanName = SecondaryIns.getPlan_name();
						} else {
							PlanName = "MEDICARE";
						}

						InsPayerTypeCode = "47"; // 5010 required in case of medicare is secondary or ter.
						/*
						 * 12 Medicare Secondary Working Aged Beneficiary or Spouse with Employer Group
						 * Health Plan 13 Medicare Secondary End Stage Renal Disease 14 Medicare
						 * Secondary , No Fault Insurance including Auto is Primary 15 Medicare
						 * Secondary Worker’s Compensation 16 Medicare Secondary Public Health Service
						 * (PHS) or other Federal Agency 16 Medicare Secondary Public Health Service 41
						 * Medicare Secondary Black Lung 42 Medicare Secondary Veteran’s Administration
						 * 43 Medicare Secondary Veteran’s Administration 47 Medicare Secondary, Other
						 * Liability Insurance is Primary
						 */

					} else {
						PlanName = SecondaryIns.getPlan_name();
					}

					data += SBR02_secondary + "*" + SecondaryIns.getGroup_number() + "*" + PlanName + "*"
							+ InsPayerTypeCode + "****" + SecondaryIns.getPayertype_code() + "~";
					segment++;
					// </editor-fold>

					// <editor-fold desc="OTHER INSURANCE COVERAGE INFORMATION">
					if (GeneralOperations.isNotNullorEmpty(SecondaryIns.getGuarantor_relationship())
							&& SecondaryIns.getGuarantor_relationship().toUpperCase().trim().equals("SELF")) {
						data += "OI***Y*P**Y~"; // - Changed C to P as per 5010
						segment++;

					} else {
						data += "OI***Y*P**Y~"; // - Changed C to P as per 5010
						segment++;
					}

					// </editor-fold>
					// </editor-fold>
					// <editor-fold desc="LOOP 2330A (OTHER SUBSCRIBER NAME and Address)">
					if (GeneralOperations.isNotNullorEmpty(SecondaryIns.getGuarantor_relationship())
							&& SecondaryIns.getGuarantor_relationship().toUpperCase().trim().equals("SELF")) {

						data += "NM1*IL*1*";

						if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getLname())
								|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getFname())) {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("S_N_M_1! Self -- Secondary Insurnace'subscriber Last/First Name missing.");
							objErrorList.add(obj);
							ErrorFlag++;
						} else {
							data += objClaim.getClaimInfo().getLname() + "*" + objClaim.getClaimInfo().getFname() + "*"
									+ objClaim.getClaimInfo().getMname() + "***MI*"
									+ SecondaryIns.getPolicy_number().toUpperCase() + "~";
							segment++;
						}
						if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getAddress())
								|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getCity())
								|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getState())
								|| GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getZip())) {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("S_A_In_1! Self -- Subscriber Address incomplete.");
							objErrorList.add(obj);
							ErrorFlag++;
						} else {
							data += "N3*" + objClaim.getClaimInfo().getAddress() + "~";
							segment++;

							data += "N4*" + objClaim.getClaimInfo().getCity() + "*" + objClaim.getClaimInfo().getState()
									+ "*";
							if (GeneralOperations.isNullorEmpty(objClaim.getClaimInfo().getZip())) {
								data += "     " + "~";
							} else {
								data += objClaim.getClaimInfo().getZip() + "~";
							}
							segment++;
						}
					} else {
						data += "NM1*IL*1*";

						if (GeneralOperations.isNullorEmpty(SecondaryIns.getGlname())
								|| GeneralOperations.isNullorEmpty(SecondaryIns.getGfname())) {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("S_N_M_1!Secondary Insurnace'subscriber Last/First Name missing.");
							objErrorList.add(obj);
							ErrorFlag++;
						} else {
							data += SecondaryIns.getGlname() + "*" + SecondaryIns.getGfname() + "*"
									+ SecondaryIns.getGmi() + "***MI*" + SecondaryIns.getPolicy_number().toUpperCase()
									+ "~";
							segment++;
						}
						if (GeneralOperations.isNullorEmpty(SecondaryIns.getGaddress())
								|| GeneralOperations.isNullorEmpty(SecondaryIns.getGcity())
								|| GeneralOperations.isNullorEmpty(SecondaryIns.getGstate())
								|| GeneralOperations.isNullorEmpty(SecondaryIns.getGzip())) {
							ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
							obj.setError("S_A_In_1!Sec Subscriber Address incomplete.");
							objErrorList.add(obj);
							ErrorFlag++;
						} else {
							data += "N3*" + SecondaryIns.getGaddress() + "~";
							segment++;

							data += "N4*" + SecondaryIns.getGcity() + "*" + SecondaryIns.getGstate() + "*";
							if (GeneralOperations.isNullorEmpty(SecondaryIns.getGzip())) {
								data += "     " + "~";
							} else {
								data += SecondaryIns.getGzip() + "~";
							}
							segment++;
						}
					}
					// </editor-fold>

					// <editor-fold desc="LOOP 2330B (OTHER PAYER AND AND ADDRESS)">
					String SecInsPayerName = "";
					if (GeneralOperations.isNullorEmpty(SecondaryIns.getPlan_name())) {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("P_Na_M_1!Secondary's payer name missing.");
						objErrorList.add(obj);
						ErrorFlag++;
					} else {
						if (SecondaryIns.getPayertype_name().trim().contains("MEDICARE")) {
							SecInsPayerName = "MEDICARE";
						} else {
							SecInsPayerName = SecondaryIns.getPlan_name();
						}
					}
					if (GeneralOperations.isNotNullorEmpty(SecondaryIns.getPayer_number())) {
						String secPayerNumber = primaryIns.getPayer_number().equals(SecondaryIns.getPayer_number())
								? SecondaryIns.getPayer_number() + "A"
								: SecondaryIns.getPayer_number();
						data += "NM1*PR*2*" + SecInsPayerName + "*****PI*" + secPayerNumber + "~";
						segment++;
					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError(
								"Payerid_Miss_2s!secondary's insurance payer id is compulsory in case of Gateway EDI Clearing house");
						objErrorList.add(obj);
						ErrorFlag++;
					}

					data += "N3*" + SecondaryIns.getIns_address() + "~";
					segment++;
					data += "N4*" + SecondaryIns.getIns_city() + "*" + SecondaryIns.getIns_state() + "*"
							+ SecondaryIns.getIns_zip().trim() + "~";
					segment++;
					// </editor-fold>
				}

				// Restricted CPT's/ICD's Checks
				for (ORMSubmission_ClaimProcedures objProc : objClaim.getClaimProcedures()) {
					// { MB=MEDICARE PART B }
					for (ORM_restricted_codes objRestrictedCode : Claims_restricted_codes) {
						switch (objRestrictedCode.getCode_type().toLowerCase()) {
						case "cpt":
							if (objClaim.getClaimInfo().getIs_resubmitted()) {
								if ((objRestrictedCode.getPayer_id().equals("0") // Run On Payer TYpe like Medicare &
																					// meeicaid
										&& objProc.getIs_resubmitted())
										&& objRestrictedCode.getCode().trim().toUpperCase()
												.equals(objProc.getProc_code().trim().toUpperCase())
										&& ((objRestrictedCode.getPayer_ins_type().equals("PRI")
												&& primaryIns.getPayertype_code().toUpperCase()
														.equals(objRestrictedCode.getPayer_type().toUpperCase()))
												|| (objRestrictedCode.getPayer_ins_type().equals("SEC")
														&& GeneralOperations.isNotNullorEmpty(SecondaryIns)
														&& GeneralOperations
																.isNotNullorEmpty(SecondaryIns.getPayertype_code())
														&& SecondaryIns.getPayertype_code().equals(
																objRestrictedCode.getPayer_type().toUpperCase()))
												|| (objRestrictedCode.getPayer_ins_type().equals("")
														&& (primaryIns.getPayertype_code().toUpperCase()
																.equals(objRestrictedCode.getPayer_type().toUpperCase())
																|| (GeneralOperations.isNotNullorEmpty(SecondaryIns)
																		&& GeneralOperations.isNotNullorEmpty(
																				SecondaryIns.getPayertype_code())
																		&& SecondaryIns.getPayertype_code()
																				.equals(objRestrictedCode
																						.getPayer_type()
																						.toUpperCase())))))) {
									ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
									obj.setBatch_id(batchID);
									obj.setError("ClaimRestrictedCodes_ERR_M_5! In Case of "
											+ objRestrictedCode.getPayer_name() + " Procedure Code: "
											+ objRestrictedCode.getCode() + " " + objRestrictedCode.getErr_message());
									obj.setDos(objClaim.getClaimInfo().getDos());
									obj.setPatient_id(objClaim.getClaimInfo().getPatient_id());
									obj.setClaim_id(objClaim.getClaim_id());
									obj.setPatient_name(objClaim.getClaimInfo().getLname() + ", "
											+ objClaim.getClaimInfo().getFname());
									objErrorList.add(obj);
									ErrorFlag++;
								} else if ((!objRestrictedCode.getPayer_id().equals("0") // Run On Payer Id
										&& objProc.getIs_resubmitted())
										&& objRestrictedCode.getCode().trim().toUpperCase()
												.equals(objProc.getProc_code().trim().toUpperCase())
										&& ((objRestrictedCode.getPayer_ins_type().equals("PRI")
												&& primaryIns.getPayertype_code().toUpperCase()
														.equals(objRestrictedCode.getPayer_type().toUpperCase())
												&& objRestrictedCode.getPayer_id().equals(primaryIns.getInspayer_id()))
												|| (objRestrictedCode.getPayer_ins_type().equals("SEC")
														&& GeneralOperations.isNotNullorEmpty(SecondaryIns)
														&& GeneralOperations
																.isNotNullorEmpty(SecondaryIns.getPayertype_code())
														&& SecondaryIns.getPayertype_code().toUpperCase()
																.equals(objRestrictedCode.getPayer_type().toUpperCase())
														&& objRestrictedCode.getPayer_id()
																.equals(SecondaryIns.getInspayer_id()))
												|| (objRestrictedCode.getPayer_ins_type().equals("")
														&& (primaryIns.getPayertype_code().toUpperCase()
																.equals(objRestrictedCode.getPayer_type().toUpperCase())
																&& objRestrictedCode.getPayer_id()
																		.equals(primaryIns.getInspayer_id())
																|| (GeneralOperations.isNotNullorEmpty(SecondaryIns)
																		&& GeneralOperations.isNotNullorEmpty(
																				SecondaryIns.getPayertype_code())
																		&& SecondaryIns.getPayertype_code()
																				.equals(objRestrictedCode
																						.getPayer_type().toUpperCase())
																		&& objRestrictedCode.getPayer_id().equals(
																				SecondaryIns.getInspayer_id())))))) {
									ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
									obj.setBatch_id(batchID);
									obj.setError("ClaimRestrictedCodes_ERR_M_5! In Case of "
											+ objRestrictedCode.getPayer_name() + " Procedure Code: "
											+ objRestrictedCode.getCode() + " " + objRestrictedCode.getErr_message());
									obj.setDos(objClaim.getClaimInfo().getDos());
									obj.setPatient_id(objClaim.getClaimInfo().getPatient_id());
									obj.setClaim_id(objClaim.getClaim_id());
									obj.setPatient_name(objClaim.getClaimInfo().getLname() + ", "
											+ objClaim.getClaimInfo().getFname());
									objErrorList.add(obj);
									ErrorFlag++;
								}
							} else {
								if (objRestrictedCode.getPayer_id().equals("0") // All
										&& objRestrictedCode.getCode().trim().toUpperCase()
												.equals(objProc.getProc_code().trim().toUpperCase())
										&& ((objRestrictedCode.getPayer_ins_type().equals("PRI")
												&& primaryIns.getPayertype_code().toUpperCase()
														.equals(objRestrictedCode.getPayer_type().toUpperCase()))
												|| (objRestrictedCode.getPayer_ins_type().equals("SEC")
														&& GeneralOperations.isNotNullorEmpty(SecondaryIns)
														&& GeneralOperations
																.isNotNullorEmpty(SecondaryIns.getPayertype_code())
														&& SecondaryIns.getPayertype_code().equals(
																objRestrictedCode.getPayer_type().toUpperCase()))
												|| (objRestrictedCode.getPayer_ins_type().equals("")
														&& (primaryIns.getPayertype_code().toUpperCase()
																.equals(objRestrictedCode.getPayer_type().toUpperCase())
																|| (GeneralOperations.isNotNullorEmpty(SecondaryIns)
																		&& GeneralOperations.isNotNullorEmpty(
																				SecondaryIns.getPayertype_code())
																		&& SecondaryIns.getPayertype_code()
																				.equals(objRestrictedCode
																						.getPayer_type()
																						.toUpperCase())))))) {
									ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
									obj.setBatch_id(batchID);
									obj.setError("ClaimRestrictedCodes_ERR_M_5! In Case of "
											+ objRestrictedCode.getPayer_name() + " Procedure Code: "
											+ objRestrictedCode.getCode() + " " + objRestrictedCode.getErr_message());
									obj.setDos(objClaim.getClaimInfo().getDos());
									obj.setPatient_id(objClaim.getClaimInfo().getPatient_id());
									obj.setClaim_id(objClaim.getClaim_id());
									obj.setPatient_name(objClaim.getClaimInfo().getLname() + ", "
											+ objClaim.getClaimInfo().getFname());
									objErrorList.add(obj);
									ErrorFlag++;
								} else if (!objRestrictedCode.getPayer_id().equals("0")
										&& objRestrictedCode.getCode().trim().toUpperCase()
												.equals(objProc.getProc_code().trim().toUpperCase())
										&& ((objRestrictedCode.getPayer_ins_type().equals("PRI")
												&& primaryIns.getPayertype_code().toUpperCase()
														.equals(objRestrictedCode.getPayer_type().toUpperCase())
												&& objRestrictedCode.getPayer_id().equals(primaryIns.getInspayer_id()))
												|| (objRestrictedCode.getPayer_ins_type().equals("SEC")
														&& GeneralOperations.isNotNullorEmpty(SecondaryIns)
														&& GeneralOperations
																.isNotNullorEmpty(SecondaryIns.getPayertype_code())
														&& SecondaryIns.getPayertype_code().toUpperCase()
																.equals(objRestrictedCode.getPayer_type().toUpperCase())
														&& objRestrictedCode.getPayer_id()
																.equals(SecondaryIns.getInspayer_id()))
												|| (objRestrictedCode.getPayer_ins_type().equals("")
														&& (primaryIns.getPayertype_code().toUpperCase()
																.equals(objRestrictedCode.getPayer_type().toUpperCase())
																&& objRestrictedCode.getPayer_id()
																		.equals(primaryIns.getInspayer_id())
																|| (GeneralOperations.isNotNullorEmpty(SecondaryIns)
																		&& GeneralOperations.isNotNullorEmpty(
																				SecondaryIns.getPayertype_code())
																		&& SecondaryIns.getPayertype_code()
																				.equals(objRestrictedCode
																						.getPayer_type().toUpperCase())
																		&& objRestrictedCode.getPayer_id().equals(
																				SecondaryIns.getInspayer_id()))))
										// && (objRestrictedCode.getPayer_id().equals(primaryIns.getInspayer_id())) //
										// || (GeneralOperations.isNotNullorEmpty(SecondaryIns)
										// && GeneralOperations.isNotNullorEmpty(SecondaryIns.getInspayer_id())
										// && SecondaryIns.getInspayer_id().equals(objRestrictedCode.getPayer_id()))
										)) {
									ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
									obj.setBatch_id(batchID);
									obj.setError("ClaimRestrictedCodes_ERR_M_5! In Case of "
											+ objRestrictedCode.getPayer_name() + " Procedure Code: "
											+ objRestrictedCode.getCode() + " " + objRestrictedCode.getErr_message());
									obj.setDos(objClaim.getClaimInfo().getDos());
									obj.setPatient_id(objClaim.getClaimInfo().getPatient_id());
									obj.setClaim_id(objClaim.getClaim_id());
									obj.setPatient_name(objClaim.getClaimInfo().getLname() + ", "
											+ objClaim.getClaimInfo().getFname());
									objErrorList.add(obj);
									ErrorFlag++;
								}
							}
							break;
						case "modifier":
							if (objRestrictedCode.getPayer_id().equals("0") // All
									&& (objRestrictedCode.getCode().trim().toUpperCase()
											.equals(objProc.getMod1().trim().toUpperCase())
											|| objRestrictedCode.getCode().trim().toUpperCase()
													.equals(objProc.getMod2().trim().toUpperCase())
											|| objRestrictedCode.getCode().trim().toUpperCase()
													.equals(objProc.getMod3().trim().toUpperCase())
											|| objRestrictedCode.getCode().trim().toUpperCase()
													.equals(objProc.getMod4().trim().toUpperCase()))
									&& ((objRestrictedCode.getPayer_ins_type().equals("PRI")
											&& primaryIns.getPayertype_code().toUpperCase()
													.equals(objRestrictedCode.getPayer_type().toUpperCase()))
											|| (objRestrictedCode.getPayer_ins_type().equals("SEC")
													&& GeneralOperations.isNotNullorEmpty(SecondaryIns)
													&& GeneralOperations
															.isNotNullorEmpty(SecondaryIns.getPayertype_code())
													&& SecondaryIns.getPayertype_code()
															.equals(objRestrictedCode.getPayer_type().toUpperCase()))
											|| (objRestrictedCode.getPayer_ins_type().equals("") && (primaryIns
													.getPayertype_code().toUpperCase()
													.equals(objRestrictedCode.getPayer_type().toUpperCase())
													|| (GeneralOperations.isNotNullorEmpty(SecondaryIns)
															&& GeneralOperations
																	.isNotNullorEmpty(SecondaryIns.getPayertype_code())
															&& SecondaryIns.getPayertype_code().equals(objRestrictedCode
																	.getPayer_type().toUpperCase())))))) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setBatch_id(batchID);
								obj.setError("ClaimRestrictedCodes_ERR_M_5! In Case of "
										+ objRestrictedCode.getPayer_name() + " Modifier: "
										+ objRestrictedCode.getCode() + " " + objRestrictedCode.getErr_message());
								obj.setDos(objClaim.getClaimInfo().getDos());
								obj.setPatient_id(objClaim.getClaimInfo().getPatient_id());
								obj.setClaim_id(objClaim.getClaim_id());
								obj.setPatient_name(
										objClaim.getClaimInfo().getLname() + ", " + objClaim.getClaimInfo().getFname());
								objErrorList.add(obj);
								ErrorFlag++;
							}
							break;
						}
					}
				}
				// --End Checking restricted codes
				// ---Adding Submit/RESUBMIT CLAIM CPTS-----------
				int line_no = 0;
				if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimProcedures())) {
					if (objClaim.getClaimInfo().getIs_resubmitted()) {
						for (ORMSubmission_ClaimProcedures objProc : objClaim.getClaimProcedures()) {
							if (objProc.getIs_resubmitted()) {
								if (objProc.getProc_code().trim().equals("j7325")) {
									if (objProc.getUnits() < 16) {
										objProc.setUnits(16);
									}
								}

								// <editor-fold desc="LOOP 2400">
								// <editor-fold desc="SERVICE LINE">
								line_no = line_no + 1;
								data += "LX*" + line_no + "~";
								segment++;
								// </editor-fold>

								// <editor-fold desc="PROFESSIONAL SERVICE">
								if (GeneralOperations.isNotNullorEmpty(objProc.getTotal_charges())
										&& !objProc.getTotal_charges().setScale(2).toString().equals("0.00")) {
									String modifiers = "";
									if (GeneralOperations.isNotNullorEmpty(objProc.getMod1().trim())) {
										modifiers = objProc.getMod1().trim();
									}
									if (GeneralOperations.isNotNullorEmpty(objProc.getMod2().trim())) {
										modifiers += ":" + objProc.getMod2().trim();
									}
									if (GeneralOperations.isNotNullorEmpty(objProc.getMod3().trim())) {
										modifiers += ":" + objProc.getMod3().trim();
									}
									if (GeneralOperations.isNotNullorEmpty(objProc.getMod4().trim())) {
										modifiers += ":" + objProc.getMod4().trim();
									}

									data += "SV1*HC:" + objProc.getProc_code().trim()
											+ ("".equals(modifiers) ? "" : ":" + modifiers) + "*"
											+ objProc.getTotal_charges().setScale(2).toString() + "*UN*"
											+ objProc.getUnits() + "*" 
											//+ objClaim.getClaimInfo().getClaim_pos() + "*"
											+ objProc.getPos()+"*"
											+ "*";// description
									// + objProc.getDescription().substring(0,
									// Math.min(objProc.getDescription().length(), 80))+"*"; // max 80 characters
									// for description
								} else {
									ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
									obj.setError("F_Ad_I_5!Procedure Code:  " + objProc.getProc_code().trim()
											+ " has ZERO charges");
									objErrorList.add(obj);
									ErrorFlag++;
								}

								String pointers = "";
								if (GeneralOperations.isNotNullorEmpty(objProc.getDx_pointer1())
										&& !objProc.getDx_pointer1().equals("0")) {
									pointers = objProc.getDx_pointer1();
								}
								if (GeneralOperations.isNotNullorEmpty(objProc.getDx_pointer2())
										&& !objProc.getDx_pointer2().equals("0")) {
									pointers += ":" + objProc.getDx_pointer2();
								}
								if (GeneralOperations.isNotNullorEmpty(objProc.getDx_pointer3())
										&& !objProc.getDx_pointer3().equals("0")) {
									pointers += ":" + objProc.getDx_pointer3();
								}
								if (GeneralOperations.isNotNullorEmpty(objProc.getDx_pointer4())
										&& !objProc.getDx_pointer4().equals("0")) {
									pointers += ":" + objProc.getDx_pointer4();
								}

								data += pointers + "~";
								segment++;
								// </editor-fold>

								// <editor-fold desc="SERVICE DATE">
								if (GeneralOperations.isNullorEmpty(objProc.getDos_from())
										|| GeneralOperations.isNullorEmpty(objProc.getDos_to())
										|| objProc.getDos_from().equalsIgnoreCase("190001101")
										|| objProc.getDos_to().equalsIgnoreCase("19000101")) {
									ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
									obj.setError("DOS From and/or DOS To for :  " + objProc.getProc_code().trim()
											+ " is required.");
									objErrorList.add(obj);
									ErrorFlag++;
								} else {
									data += "DTP*472*RD8*";
									// String[] DOS = objClaim.getClaimInfo().getDos().split("/");
									// String Date_Of_Service = DOS[0] + DOS[1] + DOS[2];
									// data += Date_Of_Service + "-" + Date_Of_Service + "~";
									data += objProc.getDos_from() + "-" + objProc.getDos_to() + "~"; // YYYYMMDD
									segment++;
								}
								// </editor-fold>

								// <editor-fold desc="LINE ITEM CONTROL NUMBER (CLAIM PROCEDURES ID)">
								// -------------------REF*6R, Page 74 CLAIM PROCEDURE ID--------------
								data += "REF*6R*" + objProc.getClaim_procedures_id() + "~";
								segment++;
								// ----------------------
								// </editor-fold>

								// <editor-fold desc="LINE NOTE">
								// if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getLuo())) {
								if (GeneralOperations.isNotNullorEmpty(objProc.getNotes().trim())) {
									data += "NTE*ADD*" + objProc.getNotes().trim() + "~";
									segment++;
								}
								// </editor-fold>

								// </editor-fold>
								// <editor-fold desc="LOOP 2410 (DRUG IDENTIFICATION)">
								if (GeneralOperations.isNotNullorEmpty(objProc.getNdc_code().trim())) {
									data += "LIN**N4*" + objProc.getNdc_code() + "~";
									segment++;
									if (GeneralOperations.isNotNullorEmpty(objProc.getNdc_qty())) {
										if (GeneralOperations.isNotNullorEmpty(objProc.getNdc_measure())
												&& GeneralOperations.isNotNullorEmpty(objProc.getNdc_price())) {
											data += "CTP****" + objProc.getNdc_qty() + "*" + objProc.getNdc_measure()
													+ "*~"; // CTP03 change to not used in 5010
											segment++;
										} else {
											ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
											obj.setError(
													"F_Ad_I_6!Procedure NDC Quantity/Qual or Unit Price is missing");
											objErrorList.add(obj);
											ErrorFlag++;
										}
									}

								}
							}
						}
						// </editor-fold>

					} else {
						for (ORMSubmission_ClaimProcedures objProc : objClaim.getClaimProcedures()) {

							// <editor-fold desc="LOOP 2400">
							// <editor-fold desc="SERVICE LINE">
							line_no = line_no + 1;
							data += "LX*" + line_no + "~";
							segment++;
							// </editor-fold>

							// <editor-fold desc="PROFESSIONAL SERVICE">
							if (objProc.getProc_code().trim().equals("j7325")) {
								if (objProc.getUnits() < 16) {
									objProc.setUnits(16);
								}
							}

							if (GeneralOperations.isNotNullorEmpty(objProc.getPos().trim())) {
								if (GeneralOperations.isNotNullorEmpty(objProc.getTotal_charges())
										&& !objProc.getTotal_charges().setScale(2).toString().equals("0.00")) {
									String modifiers = "";
									if (GeneralOperations.isNotNullorEmpty(objProc.getMod1().trim())) {
										modifiers = objProc.getMod1().trim();
									}
									if (GeneralOperations.isNotNullorEmpty(objProc.getMod2().trim())) {
										modifiers += ":" + objProc.getMod2().trim();
									}
									if (GeneralOperations.isNotNullorEmpty(objProc.getMod3().trim())) {
										modifiers += ":" + objProc.getMod3().trim();
									}
									if (GeneralOperations.isNotNullorEmpty(objProc.getMod4().trim())) {
										modifiers += ":" + objProc.getMod4().trim();
									}
									data += "SV1*HC:" + objProc.getProc_code().trim()
											+ ("".equals(modifiers) ? "" : ":" + modifiers) + "*"
											+ objProc.getTotal_charges().setScale(2).toString() + "*UN*"
											+ objProc.getUnits() + "*" 
											//+ objClaim.getClaimInfo().getClaim_pos() + "*"
											+ objProc.getPos()+"*"
											+ "*";// description
									// + objProc.getDescription().substring(0,
									// Math.min(objProc.getDescription().length(), 80))+"*"; // max 80 characters
									// for description
								} else {
									// claimError.setError("Procedure Code : "+objProc.getProcedure_code()+" has
									// zero charges.");
									ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(
											new ORMClaim_Batch_Errors(claimError));
									obj.setError(
											"Procedure Code : " + objProc.getProc_code().trim() + " has zero charges.");
									objErrorList.add(obj);
									ErrorFlag++;
								}
							} else {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								// obj.setError("F_Ad_I_5!Facility's pos code missing.");
								obj.setError("F_Ad_I_5!Claim's pos code missing.");
								objErrorList.add(obj);
								ErrorFlag++;
							}

							String pointers = "";
							if (GeneralOperations.isNotNullorEmpty(objProc.getDx_pointer1())
									&& !objProc.getDx_pointer1().equals("0")) {
								pointers = objProc.getDx_pointer1();
							}
							if (GeneralOperations.isNotNullorEmpty(objProc.getDx_pointer2())
									&& !objProc.getDx_pointer2().equals("0")) {
								pointers += ":" + objProc.getDx_pointer2();
							}
							if (GeneralOperations.isNotNullorEmpty(objProc.getDx_pointer3())
									&& !objProc.getDx_pointer3().equals("0")) {
								pointers += ":" + objProc.getDx_pointer3();
							}
							if (GeneralOperations.isNotNullorEmpty(objProc.getDx_pointer4())
									&& !objProc.getDx_pointer4().equals("0")) {
								pointers += ":" + objProc.getDx_pointer4();
							}

							data += pointers + "~";
							segment++;
							// </editor-fold>

							// <editor-fold desc="SERVICE DATE">
							if (GeneralOperations.isNullorEmpty(objProc.getDos_from())
									|| GeneralOperations.isNullorEmpty(objProc.getDos_to())
									|| objProc.getDos_from().equalsIgnoreCase("190001101")
									|| objProc.getDos_to().equalsIgnoreCase("19000101")) {
								ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
								obj.setError("DOS From and/or DOS To for :  " + objProc.getProc_code().trim()
										+ " is required.");
								objErrorList.add(obj);
								ErrorFlag++;
							} else {
								data += "DTP*472*RD8*";
								// String[] DOS = objClaim.getClaimInfo().getDos().split("/");
								// String Date_Of_Service = DOS[0] + DOS[1] + DOS[2];
								// data += Date_Of_Service + "-" + Date_Of_Service + "~";
								data += objProc.getDos_from() + "-" + objProc.getDos_to() + "~"; // YYYYMMDD
								segment++;
							}
							// </editor-fold>

							// <editor-fold desc="LINE ITEM CONTROL NUMBER (CLAIM PROCEDURES ID)">
							// -------------------REF*6R, Page 74 CLAIM PROCEDURE ID--------------
							data += "REF*6R*" + objProc.getClaim_procedures_id() + "~";
							segment++;
							// ----------------------
							// </editor-fold>

							// <editor-fold desc="LINE NOTE">
							if (GeneralOperations.isNotNullorEmpty(objProc.getNotes().trim())) {
								data += "NTE*ADD*" + objProc.getNotes().trim() + "~";
								segment++;
							}
							// </editor-fold>

							// </editor-fold>
							// <editor-fold desc="LOOP 2410 (DRUG IDENTIFICATION)">
							if (GeneralOperations.isNotNullorEmpty(objProc.getNdc_code().trim())) {
								data += "LIN**N4*" + objProc.getNdc_code() + "~";
								segment++;
								if (GeneralOperations.isNotNullorEmpty(objProc.getNdc_qty())) {
									if (GeneralOperations.isNotNullorEmpty(objProc.getNdc_measure())
											&& GeneralOperations.isNotNullorEmpty(objProc.getNdc_price())) {
										data += "CTP****" + objProc.getNdc_qty() + "*" + objProc.getNdc_measure()
												+ "*~"; // CTP03 change to not used in 5010
										segment++;
									} else {
										ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
										obj.setError("F_Ad_I_6!Procedure NDC Quantity/Qual or Unit Price is missing");
										objErrorList.add(obj);
										ErrorFlag++;
									}
								}
							}
						}
					}
					// </editor-fold>
				}
				if (line_no == 0) // if there is no claim procedures in the list
				{
					ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
					obj.setError("Claim Procedures missing.");
					objErrorList.add(obj);
					ErrorFlag++;
				}
				// <editor-fold desc="HCFA box 20 -- Outside Lab">
				if (objClaim.getClaimInfo().getOutside_lab()) {

					// <editor-fold desc="LOOP 2400 PURCHASED SERVICE INFORMATION">
					data += "PS1*";
					if (GeneralOperations.isNotNullorEmpty(Billing_Provider_NPI)) {
						data += Billing_Provider_NPI.trim() + "*";
					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("Billing NPI!Billing NPI is missing.");
						objErrorList.add(obj);
						ErrorFlag++;
					}
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getOutside_lab_charges())
							&& !objClaim.getClaimInfo().getOutside_lab_charges().setScale(2).toString()
									.equals("0.00")) {
						data += objClaim.getClaimInfo().getOutside_lab_charges().setScale(2).toString() + "~";
						segment++;
					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("OUTSIDE_LAB!Outside lab Charges are not entered.");
						objErrorList.add(obj);
						ErrorFlag++;
					}
					// </editor-fold>

					// <editor-fold desc="LOOP 2420B PURCHASED SERVICE PROVIDER NAME">
					if (GeneralOperations.isNotNullorEmpty(objClaim.getClaimInfo().getFacility_npi())) {
						data += "NM1*QB*2*" + objClaim.getClaimInfo().getFacility_name() + "*****XX*"
								+ objClaim.getClaimInfo().getFacility_npi() + "~";
					} else {
						data += "NM1*QB*2*" + objClaim.getClaimInfo().getFacility_name() + "*****XX*~";
					}
					segment++;
					// </editor-fold>
				}
				// </editor-fold>
			}

			// by Ihsan
			db.ExecuteUpdateQuery(
					"update claim_batch_error set deleted=1 where isnull(ignore_error,0)<>1 and batch_id='" + batchID
							+ "'");

			if (objErrorList.size() < 1) {

				// <editor-fold desc="TRANSACTION SET TRAILER">
				data += "SE*" + segment + "*0001~GE*1*" + batchID + "~IEA*1*000000001~";
				segment += 3;
				String writeFile = writeFile(data, PracticeID, BatchName);
				String Query = "update claim_batch set file_generated = 1, file_path = '" + writeFile
						+ "' , downloaded_by = '" + loggedInUser + "', downloaded_date = '"
						+ GeneralOperations.CurrentDateTime() + "' where batch_id = '" + batchID + "' ";
				// HibernateTransactionUtil objHibernateTransUtil =
				// HibernateTransactionUtil.getInstance();
				int result = db.ExecuteUpdateQuery(Query);

				// @OMER
				// Delete claim batch errors which are included in this batch after success
				// objHibernateTransUtil.CustomUpdate("update claim_batch_error set deleted=1
				// where claim_id in (select claim_id from claim_batch_detail where batch_id='"
				// + batchID + "')");
				// moved to outside
				if (result > 0) {
					if (billingGeneral.updateProcessedClaims(objUpdateBatchClaim) > 0) {
						// ORMSubmission_DownloadFile obj = new ORMSubmission_DownloadFile();
						// obj.setFileData(getFileBytes(writeFile));
						// obj.setNoErrors(Boolean.TRUE);
						// obj.setFileName(writeFile.substring(writeFile.lastIndexOf("\\") + 1,
						// writeFile.length()));
						// List<ORMSubmission_DownloadFile> objList = new ArrayList<>();
						// objList.add(obj);
						// return objList;
					} else {
						ORMClaim_Batch_Errors obj = new ORMClaim_Batch_Errors(claimError);
						obj.setError("Unable to Update claims, Please contact Administrator");
						objErrorList.add(obj);
						ErrorFlag++;
					}
				}
				// </editor-fold>
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// return null;

			objErrors = new ORMClaim_Batch_Errors();
			objErrors.setError("An Error Occured while generating file. DOS: " + selectedDOS + ", Claim Id="
					+ selectedCliamID + " ");
			objErrors.setDos(selectedDOS);
			objErrors.setBatch_id(batchID);
			objErrors.setPatient_id(selectedPatientID);
			objErrors.setClaim_id(selectedCliamID);
			objErrors.setPatient_name(selectedPatientName);
			objErrorList.add(objErrors);
		}
		// @OMER
		if (objErrorList.size() > 0) {
			BigDecimal newId = new BigDecimal(BigInteger.ZERO);
			newId = new BigDecimal(
					db.IDGenerator("claim_batch_error", Long.parseLong(PracticeID), objErrorList.size()));
			String error_claim_id = "";
			// HibernateTransactionUtil objHibernateTransUtil =
			// HibernateTransactionUtil.getInstance();
			for (ORMClaim_Batch_Errors obj : objErrorList) {
				if (!error_claim_id.equals(obj.getClaim_id())) {
					// objHibernateTransUtil.CustomUpdate("update claim_batch_error set deleted=1
					// where claim_id in ('"+error_claim_id+"')");
					// error_claim_id=obj.getClaimID();
				}
				// if(obj.getClaimID()!=null && !obj.getClaimID().equals(""))
				{

					// ORMClaim_Batch_Errors objerror = new ORMClaim_Batch_Errors();
					obj.setId(newId.toString());
					// objerror.setPatient_id(obj.getPatientID());
					// objerror.setClaim_id(obj.getClaimID());
					// objerror.setDos(obj.getDOS());
					// objerror.setError(obj.getError());
					// objerror.setPatient_name(obj.getPatient_Name());
					obj.setDeleted(false);
					obj.setDate_created(GeneralOperations.CurrentDateTime());
					obj.setCreated_user(loggedInUser);
					db.SaveEntity(obj, Operation.ADD);
				}
				newId = newId.add(BigDecimal.ONE);
			}
		}
		return objErrorList;
	}

	public String writeFile(String Data, String practiceID, String Batch_Name) {
		String DocumentPath = "";
		try {
			List<ORMDocumentPath> lst = (List<ORMDocumentPath>) generalDaoImpl.getDocPath(practiceID, "BatchFiles");
			for (ORMDocumentPath orm : (List<ORMDocumentPath>) lst) {
				DocumentPath = orm.getUpload_path();
			}

			if (!DocumentPath.equals("")) {
				String Path = GeneralOperations.checkPathYearMonthWise(practiceID, DocumentPath, "BatchFiles");
				Path += "\\" + Batch_Name + "_" + GeneralOperations.GetDatetimeFileName() + ".txt";
				String fullPath = DocumentPath + "\\" + practiceID + "\\BatchFiles\\" + Path;
				Writer output = null;
				File file = new File(fullPath);
				output = new BufferedWriter(new FileWriter(file));
				output.write(Data);
				output.close();
				DocumentPath = Path;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DocumentPath;
	}

	public byte[] getFileBytes(String compFilePath) {
		byte[] data = null;
		FileInputStream fis;
		FileChannel fc;

		try {
			fis = new FileInputStream(compFilePath);
			fc = fis.getChannel();
			data = new byte[(int) (fc.size())];
			ByteBuffer bb = ByteBuffer.wrap(data);
			fc.read(bb);

		} catch (FileNotFoundException e) {
			// TODO
		} catch (IOException e) {
			// TODO
		}
		return data;
	}

}
