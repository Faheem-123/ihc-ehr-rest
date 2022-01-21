package com.ihc.ehr.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import com.ihc.ehr.model.HcfaFileGenerationResponse;
import com.ihc.ehr.model.ORMHcfaFilesSave;
import com.ihc.ehr.model.ORMSubmission_ClaimDiagnosis;
import com.ihc.ehr.model.ORMSubmission_ClaimInfo;
import com.ihc.ehr.model.ORMSubmission_ClaimInsuarnceInfo;
import com.ihc.ehr.model.ORMSubmission_ClaimProcedures;
import com.ihc.ehr.model.ORMSubmission_Claims;
import com.ihc.ehr.model.ORMSubmission_ProviderPayers;
import com.ihc.ehr.model.SubmissionProccessedClaimInfo;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;
import com.ihc.ehr.util.EnumUtil.SubmissionMethod;

public class Hcfa {

	// private DBOperations db;
	private BillingGeneral billingGeneral;
	// private GeneralDAOImpl generalDaoImpl;
	// private ClaimDAOImpl claimDAOImpl;
	// private BillingDAOImpl billingDAOImpl;
	String serverDateTime = "";
	Long practiceId;
	String logedInUser = "";
	String clientDateTime = "";
	String clientIp = "";
	String hcfaFileUploadPath = "";
	Boolean repeatHcfaPage = false;
	Boolean hcfaWithBg = false;
	int repeatePageNo = 0;
	String hcfaFormDirectoryPath = "";
	List<SubmissionProccessedClaimInfo> lstUpdateBatchClaim = new ArrayList<>();

	public Hcfa(BillingGeneral billingGeneral) {

		// this.db = dbOpt;
		this.billingGeneral = billingGeneral;
		// this.generalDaoImpl = generalDaoImpl;
		// this.claimDAOImpl = claimDAOImpl;
		// this.billingDAOImpl = billingDAOImpl;
		serverDateTime = DateTimeUtil.getCurrentDateTime();

	}

	public HcfaFileGenerationResponse generateHcfa(String claimIds, String billToInsuranceType, Long practiceId,
			String logedInUser, String clientDateTime, String clientIp, String directoryPath, Boolean withBg)
			throws IOException {

		HcfaFileGenerationResponse hcfaFileGenerationResponse = new HcfaFileGenerationResponse();

		this.practiceId = practiceId;
		this.logedInUser = logedInUser;
		this.clientDateTime = clientDateTime;
		this.clientIp = clientIp;
		this.hcfaWithBg = withBg;
		// hcfaPath+"\\"+this.practiceId+"\\HCFA\\";
		this.hcfaFormDirectoryPath = directoryPath + "\\HCFA_Forms";
		String generatedHcfaFilePath = "";

		List<String> lstHcfaFiles = new ArrayList<>();

		serverDateTime = DateTimeUtil.getCurrentDateTime();
		lstUpdateBatchClaim = new ArrayList<>();

		List<ORMSubmission_ClaimInfo> ClaimsInfo = this.billingGeneral
				.getBatchClaimsInfoDetail(this.practiceId.toString(), claimIds, false);
		List<ORMSubmission_ClaimInsuarnceInfo> ClaimsInsuranceInfo = this.billingGeneral
				.getBatchClaimInsurancesDetail(this.practiceId.toString(), claimIds, false);
		List<ORMSubmission_ClaimDiagnosis> ClaimsDiagnosisInfo = this.billingGeneral
				.getBatchClaimDiagnosisDetail(this.practiceId.toString(), claimIds, false);
		List<ORMSubmission_ClaimProcedures> ClaimsProceduresInfo = this.billingGeneral
				.getBatchClaimProceduresDetail(this.practiceId.toString(), claimIds, false);

		for (ORMSubmission_ClaimInfo claimInfo : ClaimsInfo) {

			String selectedCliamID = claimInfo.getClaim_id();
			// String selectedAttendingProviderID = claimInfo.getAttending_physician();
			String selectedBillingProviderID = claimInfo.getBilling_physician();

			List<ORMSubmission_ClaimInsuarnceInfo> objClaimInsurances = new ArrayList<>();
			List<ORMSubmission_ClaimDiagnosis> objClaimDiagosis = new ArrayList<>();
			List<ORMSubmission_ClaimProcedures> objClaimProcedures = new ArrayList<>();

			ORMSubmission_Claims objClaims = new ORMSubmission_Claims();
			objClaims.setClaim_id(selectedCliamID);
			objClaims.setClaimInfo(claimInfo);
			// Extracting Claim Insurance Info from Main Insurance Arry and add it to
			// ClaimnSubmission Object
			for (ORMSubmission_ClaimInsuarnceInfo objClaimIns : ClaimsInsuranceInfo) {
				if (objClaimIns.getClaim_id().equals(selectedCliamID)) {
					objClaimInsurances.add(objClaimIns);
				}
			}
			objClaims.setClaimInsurance(objClaimInsurances);
			// Extracting Diagnosis Info of selected Claim from Main Claim Diagnosis Arry
			// and add it to ClaimnSubmission Object
			for (ORMSubmission_ClaimDiagnosis objClaimDiag : ClaimsDiagnosisInfo) {
				if (objClaimDiag.getClaim_id().equals(selectedCliamID)) {
					objClaimDiagosis.add(objClaimDiag);
				}
			}
			objClaims.setClaimDiagnosis(objClaimDiagosis);
			// Extracting Diagnosis Info of selected Claim from Main Claim Diagnosis Arry
			// and add it to ClaimnSubmission Object
			for (ORMSubmission_ClaimProcedures objClaimProc : ClaimsProceduresInfo) {
				if (objClaimProc.getClaim_id().equals(selectedCliamID)) {
					objClaimProcedures.add(objClaimProc);
				}
			}
			objClaims.setClaimProcedures(objClaimProcedures);

			/*
			 * for (ORMSubmission_ClaimInsuarnceInfo obj : objClaimInsurances) {
			 * 
			 * if (billToSecondary) { if
			 * (obj.getInsurace_type().trim().toUpperCase().equals("SECONDARY") &&
			 * GeneralOperations.isNotNullorEmpty(obj.getInspayer_id())) {
			 * System.out.println("Claim Insurance ID = " + obj.getClaiminsurance_id() +
			 * "       Claim ID = " + claimInfo.getClaim_id());
			 * List<ORMSubmission_ProviderPayers> lst =
			 * this.billingGeneral.getBatchClaimProviderPayers( selectedBillingProviderID,
			 * obj.getInspayer_id(), claimInfo.getPractice_id().toString(), true);
			 * ORMSubmission_ProviderPayers ClaimsProviderPayersInfo = null; if (lst.size()
			 * > 0) { ClaimsProviderPayersInfo = lst.get(0); }
			 * objClaims.setBillingProviderPayersInfo(ClaimsProviderPayersInfo); break; }
			 * 
			 * } else if (obj.getInsurace_type().trim().toUpperCase().equals("PRIMARY") &&
			 * GeneralOperations.isNotNullorEmpty(obj.getInspayer_id())) {
			 * System.out.println("Claim Insurance ID = " + obj.getClaiminsurance_id() +
			 * "       Claim ID = " + claimInfo.getClaim_id());
			 * List<ORMSubmission_ProviderPayers> lst =
			 * this.billingGeneral.getBatchClaimProviderPayers( selectedBillingProviderID,
			 * obj.getInspayer_id(), claimInfo.getPractice_id().toString(), true);
			 * ORMSubmission_ProviderPayers ClaimsProviderPayersInfo = null; if (lst.size()
			 * > 0) { ClaimsProviderPayersInfo = lst.get(0); }
			 * objClaims.setBillingProviderPayersInfo(ClaimsProviderPayersInfo); break; } }
			 */

			// statuses------
			Boolean billToSecondary = false;
			if (billToInsuranceType.equalsIgnoreCase("Secondary")) {
				billToSecondary = true;
			}
			SubmissionProccessedClaimInfo objUpdateClaim = new SubmissionProccessedClaimInfo();
			objUpdateClaim.setClaim_id(Long.parseLong(claimInfo.getClaim_id()));
			objUpdateClaim.setIs_bill_to_secondary(billToSecondary);
			objUpdateClaim.setSubmission_method(SubmissionMethod.HCFA);
			objUpdateClaim.setUser_name(logedInUser);
			objUpdateClaim.setClient_date_time(clientDateTime);
			objUpdateClaim.setPractice_id(practiceId);

			objUpdateClaim.setPri_status(claimInfo.getPri_status());
			objUpdateClaim.setSec_status(claimInfo.getSec_status());
			objUpdateClaim.setOth_status(claimInfo.getOth_status());

			for (ORMSubmission_ClaimInsuarnceInfo objClaimIns : objClaimInsurances) {
				if (GeneralOperations.isNotNullorEmpty(objClaimIns)) {
					if (objClaimIns.getInsurace_type().toUpperCase().trim().equals("PRIMARY")) {
						objUpdateClaim.setHas_primary_ins(true);

						if (!billToSecondary) {
							if (GeneralOperations.isNotNullorEmpty(objClaimIns.getInspayer_id())) {
								System.out.println("Claim Insurance ID = " + objClaimIns.getClaiminsurance_id()
										+ "       Claim ID = " + claimInfo.getClaim_id());
								List<ORMSubmission_ProviderPayers> lst = this.billingGeneral
										.getBatchClaimProviderPayers(selectedBillingProviderID,
												objClaimIns.getInspayer_id(), claimInfo.getPractice_id().toString());
								ORMSubmission_ProviderPayers ClaimsProviderPayersInfo = null;
								if (lst.size() > 0) {
									ClaimsProviderPayersInfo = lst.get(0);
								}
								objClaims.setBillingProviderPayersInfo(ClaimsProviderPayersInfo);
							}
							objUpdateClaim.setInsurance_name(objClaimIns.getInsurance_name().toUpperCase());

						}
					}

					if (objClaimIns.getInsurace_type().toUpperCase().trim().equals("SECONDARY")) {
						objUpdateClaim.setHas_secondary_ins(true);

						if (billToSecondary) {
							if (GeneralOperations.isNotNullorEmpty(objClaimIns.getInspayer_id())) {
								System.out.println("Claim Insurance ID = " + objClaimIns.getClaiminsurance_id()
										+ "       Claim ID = " + claimInfo.getClaim_id());
								List<ORMSubmission_ProviderPayers> lst = this.billingGeneral
										.getBatchClaimProviderPayers(selectedBillingProviderID,
												objClaimIns.getInspayer_id(), claimInfo.getPractice_id().toString());
								ORMSubmission_ProviderPayers ClaimsProviderPayersInfo = null;
								if (lst.size() > 0) {
									ClaimsProviderPayersInfo = lst.get(0);
								}
								objClaims.setBillingProviderPayersInfo(ClaimsProviderPayersInfo);
							}
							objUpdateClaim.setInsurance_name(objClaimIns.getInsurance_name().toUpperCase());
						}

					}

					if (objClaimIns.getInsurace_type().toUpperCase().trim().equals("TERITIARY")
							|| objClaimIns.getInsurace_type().toUpperCase().trim().equals("OTHER")) {
						objUpdateClaim.setHas_oth_ins(true);
					}
				}
			}
			if (claimInfo.getIs_resubmitted()) {
				objUpdateClaim.setIs_resubmitted(true);
			}

			lstUpdateBatchClaim.add(objUpdateClaim);
			// ------

			this.repeatHcfaPage = false;
			this.repeatePageNo = 0;

			this.hcfaFileUploadPath = FileUtil.GetYearMonthDayWisePath(directoryPath, this.practiceId.toString(),
					"HCFA") + "\\" + claimInfo.getClaim_id() + "_" + FileUtil.GetDatetimeFileName();
			String generatedHcfaFile = getHcfaPage(objClaims, objUpdateClaim);
			System.out.println("Generated Hcfa:" + generatedHcfaFile);
			// generatedHcfaFilePath = generatedHcfaFile;

			if (GeneralOperations.isNotNullorEmpty(generatedHcfaFile)) {
				lstHcfaFiles.add(generatedHcfaFile);

				ORMHcfaFilesSave ormHcfaFilesSave = new ORMHcfaFilesSave();
				ormHcfaFilesSave.setPatient_id(Long.parseLong(claimInfo.getPatient_id()));
				ormHcfaFilesSave.setClaim_id(Long.parseLong(claimInfo.getClaim_id()));
				ormHcfaFilesSave.setCreated_user(logedInUser);
				ormHcfaFilesSave.setDate_created(serverDateTime);
				ormHcfaFilesSave.setDeleted(false);
				ormHcfaFilesSave.setPractice_id(practiceId);
				ormHcfaFilesSave.setLink(generatedHcfaFile);
				this.billingGeneral.saveHcfaGeneratedFile(ormHcfaFilesSave);

			} else {
				lstHcfaFiles = null;
				break;
			}
		}

		if (lstHcfaFiles != null && lstHcfaFiles.size() > 0) {

			String hcfaCombinedPath = FileUtil.GetYearMonthDayWisePath(directoryPath, this.practiceId.toString(),
					"HCFA") + "\\commbined_hcfa_" + FileUtil.GetDatetimeFileName() + ".pdf";

			String hcfaDirecotry = FileUtil.GetDirectoryPath(directoryPath, this.practiceId.toString(), "HCFA");

			PDFMergerUtility pcFMergerUtility = new PDFMergerUtility();
			pcFMergerUtility.setDestinationFileName(hcfaCombinedPath);

			for (String fileLink : lstHcfaFiles) {
				pcFMergerUtility.addSource(hcfaDirecotry + "\\" + fileLink);
			}

			pcFMergerUtility.mergeDocuments(null);

			String[] splitedPath = hcfaCombinedPath.split("\\\\");
			generatedHcfaFilePath = splitedPath[splitedPath.length - 4] + "\\" + splitedPath[splitedPath.length - 3]
					+ "\\" + splitedPath[splitedPath.length - 2] + "\\" + splitedPath[splitedPath.length - 1];

			hcfaFileGenerationResponse.setHcfa_link(generatedHcfaFilePath);
			hcfaFileGenerationResponse.setProcessed_claim(lstUpdateBatchClaim);

		} else {
			hcfaFileGenerationResponse.setError("Unable to generate HCFA File.");
		}

		return hcfaFileGenerationResponse;
		// return generatedHcfaFilePath;
	}

	private String getHcfaPage(ORMSubmission_Claims objClaims, SubmissionProccessedClaimInfo objUpdateClaim) {

		String generatedHCFAPath = "";

		ORMSubmission_ClaimInfo claimInfo = objClaims.getClaimInfo();
		List<ORMSubmission_ClaimDiagnosis> lstClaimDiagnosis = objClaims.getClaimDiagnosis();
		List<ORMSubmission_ClaimProcedures> lstClaimProcedures = objClaims.getClaimProcedures();

		PDDocument pDDocument = null;
		try {

			if (hcfaWithBg) {
				pDDocument = PDDocument.load(new File(this.hcfaFormDirectoryPath + "\\hcfa_02_12_fillable_WithBg.pdf"));
			} else {
				pDDocument = PDDocument.load(new File(this.hcfaFormDirectoryPath + "\\hcfa_02_12_fillable_NoBg.pdf"));
			}

			PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();

			// clear all feilds
			for (PDField field : pDAcroForm.getFields()) {
				field.setValue("");
				field.setReadOnly(true);
			}

			String strInsGroup = "";
			String primGuarRel = "";
			String secGuarRel = "";
			Boolean primGurantExists = false;
			Boolean secGurantExists = false;

			int i = 0;

			ORMSubmission_ClaimInsuarnceInfo insPriamry = null;
			ORMSubmission_ClaimInsuarnceInfo insSecondary = null;
			for (ORMSubmission_ClaimInsuarnceInfo objClaimIns : objClaims.getClaimInsurance()) {

				if (objClaimIns.getInsurace_type().toUpperCase().trim().equals("PRIMARY")) {
					if (objUpdateClaim.getIs_bill_to_secondary()) {
						insSecondary = objClaimIns;
					} else {
						insPriamry = objClaimIns;
					}
				}
				if (objClaimIns.getInsurace_type().toUpperCase().trim().equals("SECONDARY")) {

					if (objUpdateClaim.getIs_bill_to_secondary()) {
						insPriamry = objClaimIns;
					} else {
						insSecondary = objClaimIns;
					}

				}
			}

			if (insPriamry != null) {

				strInsGroup = insPriamry.getPayertype_code();

				pDAcroForm.getField("lbl_Top_Payer").setValue(insPriamry.getInsurance_name().toUpperCase());
				pDAcroForm.getField("lbl_Top_Address").setValue(insPriamry.getIns_address().toUpperCase());
				pDAcroForm.getField("lbl_Top_Address_Line2").setValue("");
				pDAcroForm.getField("lbl_Top_ZipCityState").setValue(insPriamry.getIns_city().toUpperCase() + " "
						+ insPriamry.getIns_state().toUpperCase() + " " + insPriamry.getIns_zip().toUpperCase());

				if (insPriamry.getGuarantor_relationship().equalsIgnoreCase("SELF")) {
					pDAcroForm.getField("lbl_6_Self").setValue("X");
					primGuarRel = "SELF";
				} else if (insPriamry.getGuarantor_relationship().equalsIgnoreCase("SPOUSE")) {
					pDAcroForm.getField("lbl_6_spouse").setValue("X");
					primGurantExists = true;
				} else if (insPriamry.getGuarantor_relationship().equalsIgnoreCase("CHILD")) {
					pDAcroForm.getField("lbl_6_child").setValue("X");
					primGurantExists = true;
				} else if (insPriamry.getGuarantor_relationship().equalsIgnoreCase("OTHER")) {
					pDAcroForm.getField("lbl_6_other").setValue("X");
					primGurantExists = true;
				}

			}

			if (insSecondary != null) {
				if (insPriamry.getGuarantor_relationship().equalsIgnoreCase("SELF")) {
					secGuarRel = "SELF";
				} else if (GeneralOperations.isNotNullorEmpty(insPriamry.getGuarantor_relationship())) {
					secGurantExists = true;
				}
			}

			// BOX 1
			switch (strInsGroup.toUpperCase()) {
			case "MB":
				pDAcroForm.getField("lbl_1_mdcare").setValue("X");
				break;
			case "MC":
				pDAcroForm.getField("lbl_1_mdcaid").setValue("X");
				break;
			default:
				pDAcroForm.getField("lbl_1_other").setValue("X");
				break;
			}
			/*
			 * if (strInsGroup == "MB") { pDAcroForm.getField("lbl_1_mdcare").setValue("X");
			 * } else if (strInsGroup == "MC") {
			 * pDAcroForm.getField("lbl_1_mdcaid").setValue("X"); } else {
			 * pDAcroForm.getField("lbl_1_other").setValue("X"); }
			 */
			pDAcroForm.getField("lbl_1A").setValue(insPriamry.getPolicy_number().toUpperCase());

			// BOX 2
			String patName = claimInfo.getLname() + ", " + claimInfo.getFname();
			if (GeneralOperations.isNotNullorEmpty(claimInfo.getMname())) {
				patName += ", " + claimInfo.getMname();
			}
			pDAcroForm.getField("lbl_2").setValue(patName.toUpperCase());

			// BOX 3
			pDAcroForm.getField("lbl_3MM").setValue(claimInfo.getDob().split("/")[0]); // MM
			pDAcroForm.getField("lbl_3DD").setValue(claimInfo.getDob().split("/")[1]); // DD
			pDAcroForm.getField("lbl_3YYYY").setValue(claimInfo.getDob().split("/")[2]); // YYYY
			if (claimInfo.getGender().equalsIgnoreCase("M") || claimInfo.getGender().equalsIgnoreCase("MALE")) {
				pDAcroForm.getField("lbl_3Sex_M").setValue("X");
			}
			if (claimInfo.getGender().equalsIgnoreCase("F") || claimInfo.getGender().equalsIgnoreCase("FEMALE")) {
				pDAcroForm.getField("lbl_3Sex_F").setValue("X");
			}

			// BOX 5
			String strPhone = GeneralOperations.getNumbersOnlyFromString(claimInfo.getPat_phone());
			String strPhoneCode = "";
			String strPhoneNo = "";
			if (GeneralOperations.isNotNullorEmpty(strPhone) && strPhone.length() >= 10) {
				// String strPhone = claimInfo.getPhone_no().replaceAll("-", "").replaceAll("(",
				// "").replaceAll(")", "");
				strPhone = (strPhone.substring(0, 1) == "1") ? (strPhone.substring(1, strPhone.length())) : strPhone;
				strPhoneCode = strPhone.substring(0, 3);
				strPhoneNo = strPhone.substring(3, 6) + " " + strPhone.substring(6, strPhone.length());
			}
			pDAcroForm.getField("lbl_5").setValue(claimInfo.getAddress());
			pDAcroForm.getField("lbl_5City").setValue(claimInfo.getCity());
			pDAcroForm.getField("lbl_5State").setValue(claimInfo.getState());
			pDAcroForm.getField("lbl_5Zip").setValue(claimInfo.getZip());
			pDAcroForm.getField("lbl_5PhCode").setValue(strPhoneCode);
			pDAcroForm.getField("lbl_5PhNo").setValue(strPhoneNo);

			// BOX 11 b
			String WorkerCompClaimNo = GeneralOperations.isNullorEmpty(claimInfo.getClaim_number()) ? ""
					: claimInfo.getClaim_number().trim();
			if (WorkerCompClaimNo != "" && WorkerCompClaimNo != "0") {
				pDAcroForm.getField("lbl_11b_Qualf").setValue("Y4");
				pDAcroForm.getField("lbl_11b_no").setValue(WorkerCompClaimNo);
			}

			if (primGuarRel == "SELF" || primGurantExists) {
				// BOX 11

				pDAcroForm.getField("lbl_11").setValue(
						insPriamry.getGroup_number() == null ? "" : insPriamry.getGroup_number().toUpperCase());
				pDAcroForm.getField("lbl_11c")
						.setValue(insPriamry.getPayer_name() == null ? "" : insPriamry.getPayer_name().toUpperCase());

				if (primGuarRel == "SELF") {
					pDAcroForm.getField("lbl_4").setValue("SAME");
					pDAcroForm.getField("lbl_7").setValue("SAME");
				} else if (primGurantExists) {

					String gName = insPriamry.getGlname() + ", " + insPriamry.getGfname();
					if (GeneralOperations.isNotNullorEmpty(insPriamry.getGmi())) {
						gName += ", " + insPriamry.getGmi();
					}

					pDAcroForm.getField("lbl_4").setValue(gName.toUpperCase());
					pDAcroForm.getField("lbl_7").setValue(insPriamry.getIns_address().toUpperCase());
					pDAcroForm.getField("lbl_7City").setValue(insPriamry.getGcity().toUpperCase());
					pDAcroForm.getField("lbl_7State").setValue(insPriamry.getGstate().toUpperCase());
					pDAcroForm.getField("lbl_7Zip").setValue(insPriamry.getGzip().toUpperCase());

					String strGPhone = GeneralOperations.getNumbersOnlyFromString(insPriamry.getGphone());
					String strGPhoneCode = "";
					String strGPhoneNo = "";
					if (GeneralOperations.isNotNullorEmpty(strGPhone) && strGPhone.length() >= 10) {
						// String strPhone = claimInfo.getPhone_no().replaceAll("-", "").replaceAll("(",
						// "").replaceAll(")", "");
						strGPhone = (strGPhone.substring(0, 1) == "1") ? (strGPhone.substring(1, strPhone.length()))
								: strGPhone;
						strGPhoneCode = strGPhone.substring(0, 3);
						strGPhoneNo = strGPhone.substring(3, 6) + " " + strGPhone.substring(6, strGPhone.length());
					}
					pDAcroForm.getField("lbl_7PhCode").setValue(strGPhoneCode);
					pDAcroForm.getField("lbl_7PhNo").setValue(strGPhoneNo);

					// BOX 11 a
					pDAcroForm.getField("lbl_11aMM").setValue(insPriamry.getGdob().split("/")[0]); // MM
					pDAcroForm.getField("lbl_11aDD").setValue(insPriamry.getGdob().split("/")[1]); // DD
					pDAcroForm.getField("lbl_11aYYYY").setValue(insPriamry.getGdob().split("/")[2]); // YYYY
					if (insPriamry.getGgender().equalsIgnoreCase("M")
							|| insPriamry.getGgender().equalsIgnoreCase("MALE")) {
						pDAcroForm.getField("lbl_11aSex_M").setValue("X");
					}
					if (insPriamry.getGgender().equalsIgnoreCase("F")
							|| insPriamry.getGgender().equalsIgnoreCase("FEMALE")) {
						pDAcroForm.getField("lbl_11aSex_F").setValue("X");
					}
				}

				if (secGuarRel == "SELF" || secGurantExists) {
					pDAcroForm.getField("lbl_11d_Yes").setValue("X");
					pDAcroForm.getField("lbl_9A").setValue(insSecondary.getPolicy_number() == null ? ""
							: insSecondary.getPolicy_number().toUpperCase());
					pDAcroForm.getField("lbl_9D").setValue(
							insSecondary.getPayer_name() == null ? "" : insSecondary.getPayer_name().toUpperCase());

					if (secGuarRel == "SELF") {
						pDAcroForm.getField("lbl_9").setValue("SAME");
					}

					else if (secGurantExists) {
						pDAcroForm.getField("lbl_11d_Yes").setValue("X");
						pDAcroForm.getField("lbl_9")
								.setValue((insSecondary.getGlname() + ", " + insSecondary.getGfname()
										+ (GeneralOperations.isNotNullorEmpty(insSecondary.getGmi())
												? ", " + insSecondary.getGmi()
												: "")).toUpperCase());
						pDAcroForm.getField("lbl_9D").setValue(
								insSecondary.getPayer_name() == null ? "" : insSecondary.getPayer_name().toUpperCase());
					}
				} else {
					pDAcroForm.getField("lbl_11d_No").setValue("X");
				}

				if (claimInfo.getEmployment()) {
					pDAcroForm.getField("lbl_10a_Yes").setValue("X");
				} else {
					pDAcroForm.getField("lbl_10a_No").setValue("X");
				}

				if (claimInfo.getAccident_auto()) {
					pDAcroForm.getField("lbl_10b_Yes").setValue("X");
					pDAcroForm.getField("lbl_10bState").setValue(claimInfo.getAccident_state().toUpperCase());
				} else {
					pDAcroForm.getField("lbl_10b_No").setValue("X");
				}

				if (claimInfo.getAccident_other()) {
					pDAcroForm.getField("lbl_10c_Yes").setValue("X");
				} else {
					pDAcroForm.getField("lbl_10c_No").setValue("X");
				}

				// --Signed by Patient and date etc //
				pDAcroForm.getField("lbl_12Date").setValue(claimInfo.getDos()); // MM/DD/YYYY
				pDAcroForm.getField("lbl_13_SigOnFile").setValue("SIGNATURE ON FILE");
				pDAcroForm.getField("lbl_12Signed").setValue("SIGNATURE ON FILE");

				// BOTTOM PORTION
				if ((claimInfo.getAccident_auto() || claimInfo.getAccident_other())
						&& GeneralOperations.isNotNullorEmpty(claimInfo.getAccident_date())) {
					pDAcroForm.getField("lbl_14MM").setValue(claimInfo.getAccident_date().toString().split("/")[0]);
					pDAcroForm.getField("lbl_14DD").setValue(claimInfo.getAccident_date().toString().split("/")[1]);
					pDAcroForm.getField("lbl_14YYYY").setValue(claimInfo.getAccident_date().toString().split("/")[2]);
				} else if (GeneralOperations.isNotNullorEmpty(claimInfo.getStart_care_date())) {
					pDAcroForm.getField("lbl_14MM").setValue(claimInfo.getStart_care_date().toString().split("/")[0]);
					pDAcroForm.getField("lbl_14DD").setValue(claimInfo.getStart_care_date().toString().split("/")[1]);
					pDAcroForm.getField("lbl_14YYYY").setValue(claimInfo.getStart_care_date().toString().split("/")[2]);

					// 431 Onset of Current Symptoms or Illness
					// 484 Last Menstrual Period
					pDAcroForm.getField("lbl_14Qual").setValue("431");

				} else if (GeneralOperations.isNotNullorEmpty(claimInfo.getLmp_date())) {
					// 431 Onset of Current Symptoms or Illness
					// 484 Last Menstrual Period

					pDAcroForm.getField("lbl_14Qual").setValue("484");

					pDAcroForm.getField("lbl_14MM").setValue(claimInfo.getLmp_date().toString().split("/")[0]);
					pDAcroForm.getField("lbl_14DD").setValue(claimInfo.getLmp_date().toString().split("/")[1]);
					pDAcroForm.getField("lbl_14YYYY").setValue(claimInfo.getLmp_date().toString().split("/")[2]);
				}

				if (GeneralOperations.isNotNullorEmpty(claimInfo.getReferring_physician())) {
					pDAcroForm.getField("lbl_17_qual").setValue("DN");
					pDAcroForm.getField("lbl_17").setValue(
							claimInfo.getRef_lname().toUpperCase() + ", " + claimInfo.getRef_fname().toUpperCase());

					if (GeneralOperations.isNotNullorEmpty(claimInfo.getRef_npi())) {
						pDAcroForm.getField("lbl_17b").setValue(claimInfo.getRef_npi());
					} else if (GeneralOperations.isNotNullorEmpty(claimInfo.getRef_taxonomy_code())) {
						pDAcroForm.getField("lbl_17aQual").setValue("ZZ");
						pDAcroForm.getField("lbl_17aCode").setValue(claimInfo.getRef_taxonomy_code());
					}
				}

				// --Entering Hospitalization Dates
				if (GeneralOperations.isNotNullorEmpty(claimInfo.getHospital_from())
						&& !claimInfo.getHospital_from().equalsIgnoreCase("01/01/1900")) {

					pDAcroForm.getField("lbl_18MM_from")
							.setValue(claimInfo.getHospital_from().toString().split("/")[0]);
					pDAcroForm.getField("lbl_18DD_from")
							.setValue(claimInfo.getHospital_from().toString().split("/")[1]);
					pDAcroForm.getField("lbl_18YYYY_from")
							.setValue(claimInfo.getHospital_from().toString().split("/")[2]);

				}
				if (GeneralOperations.isNotNullorEmpty(claimInfo.getHospital_to())
						&& !claimInfo.getHospital_to().equalsIgnoreCase("01/01/1900")) {

					pDAcroForm.getField("lbl_18MM_to").setValue(claimInfo.getHospital_to().toString().split("/")[0]);
					pDAcroForm.getField("lbl_18DD_to").setValue(claimInfo.getHospital_to().toString().split("/")[1]);
					pDAcroForm.getField("lbl_18YYYY_to").setValue(claimInfo.getHospital_to().toString().split("/")[2]);

				}

				if (GeneralOperations.isNotNullorEmpty(claimInfo.getLuo())) {
					pDAcroForm.getField("lbl_19").setValue(claimInfo.getLuo());
				}

				if (claimInfo.getIs_resubmitted()
						&& GeneralOperations.isNotNullorEmpty(claimInfo.getMedical_resubmision_code())) {
					pDAcroForm.getField("lbl_22code").setValue(claimInfo.getMedical_resubmision_code());
				}
				if (GeneralOperations.isNotNullorEmpty(claimInfo.getPrior_authorization())) {
					pDAcroForm.getField("lbl_23").setValue(claimInfo.getPrior_authorization());
				}

				// --Box 20 Outside Lab
				if (claimInfo.getOutside_lab()
						&& GeneralOperations.isNotNullorEmpty(claimInfo.getOutside_lab_charges())) {

					pDAcroForm.getField("lbl_20_Yes").setValue("X");

					BigDecimal labCharges = claimInfo.getOutside_lab_charges();
					labCharges = labCharges.setScale(2);
					pDAcroForm.getField("lbl_20Charges").setValue(labCharges.toString().replace(".", "    "));

				} else {
					pDAcroForm.getField("lbl_20_No").setValue("X");
				}

				// --setting up ICD 9/10 qualifier--- currently m sending only ICD 9
				if (claimInfo.getIcd_10_claim()) {
					pDAcroForm.getField("lbl_ICD_Ind").setValue("0");

				} else {
					pDAcroForm.getField("lbl_ICD_Ind").setValue("9");
				}

				// --Defining Diagnosis Codes
				for (i = 0; i < lstClaimDiagnosis.size(); i++) {
					if (i > 11)
						break;
					switch (i) {
					case 0: {
						pDAcroForm.getField("lbl_21dxA")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}
					case 1: {

						pDAcroForm.getField("lbl_21dxB")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}
					case 2: {
						pDAcroForm.getField("lbl_21dxC")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}
					case 3: {
						pDAcroForm.getField("lbl_21dxD")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}
					case 4: {

						pDAcroForm.getField("lbl_21dxE")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}
					case 5: {

						pDAcroForm.getField("lbl_21dxF")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}
					case 6: {

						pDAcroForm.getField("lbl_21dxG")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}
					case 7: {

						pDAcroForm.getField("lbl_21dxH")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}
					case 8: {

						pDAcroForm.getField("lbl_21dxI")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}
					case 9: {

						pDAcroForm.getField("lbl_21dxJ")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}
					case 10: {
						pDAcroForm.getField("lbl_21dxK")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}
					case 11: {

						pDAcroForm.getField("lbl_21dxL")
								.setValue(lstClaimDiagnosis.get(i).getDiag_code().toString().trim());
						break;
					}

					}
				}

				// --Entering CPTS Info 12 12 12
				int line = 1;
				// int procCount = 0;
				if (repeatHcfaPage == false) {
					i = 0;

					/*
					 * if (lstClaimProcedures.size() >= 6) { procCount = 6; } else { procCount =
					 * lstClaimProcedures.size(); }
					 */

					if (lstClaimProcedures.size() > 6) {
						repeatHcfaPage = true;
						repeatePageNo = 1;
					}

				} else {
					i = 6;
					repeatHcfaPage = false;
					repeatePageNo = 2;

					/*
					 * if (lstClaimProcedures.size() >= 12) procCount = 6; else procCount =
					 * lstClaimProcedures.size()-6;
					 */

					/*
					 * if (lstClaimProcedures.size() > 5) { procCount = 6; } else { procCount =
					 * lstClaimProcedures.size() - 6; }
					 */
				}

				/*
				 * if (procCount > 6) { repeatHcfaPage = true; } else { repeatHcfaPage = false;
				 * }
				 */

				for (; i < lstClaimProcedures.size(); i++) {
					// if (i > 5)
					// repeatePageNo++;

					populateProcLine(pDAcroForm, lstClaimProcedures.get(i), claimInfo, strInsGroup, line);

					line++;

					if (line > 6) {
						break;
					}

					/*
					 * switch (i) { case 0: case 6: { populateProcLine(pDAcroForm,
					 * lstClaimProcedures.get(i), claimInfo, strInsGroup, 1); break; } case 1: case
					 * 7: { populateProcLine(pDAcroForm, lstClaimProcedures.get(i), claimInfo,
					 * strInsGroup, 2); break; } case 2: case 8: { populateProcLine(pDAcroForm,
					 * lstClaimProcedures.get(i), claimInfo, strInsGroup, 3);
					 * 
					 * break; } case 3: case 9: { populateProcLine(pDAcroForm,
					 * lstClaimProcedures.get(i), claimInfo, strInsGroup, 4);
					 * 
					 * break; } case 4: case 10: { populateProcLine(pDAcroForm,
					 * lstClaimProcedures.get(i), claimInfo, strInsGroup, 5); break; } case 5: case
					 * 11: { populateProcLine(pDAcroForm, lstClaimProcedures.get(i), claimInfo,
					 * strInsGroup, 6); break; } }
					 */
				} // --End of Cpts Loop

				pDAcroForm.getField("lbl_26").setValue(claimInfo.getClaim_id());

				if (claimInfo.getAccept_assignment()) {
					pDAcroForm.getField("lbl_27_Yes").setValue("X");
				} else {
					pDAcroForm.getField("lbl_27_No").setValue("X");
				}

				BigDecimal claimTotal = claimInfo.getClaim_total();
				claimTotal = claimTotal.setScale(2);
				pDAcroForm.getField("lbl_28").setValue(claimTotal.toString().replace(".", "    "));

				if (objUpdateClaim.getIs_bill_to_secondary()) {
					BigDecimal amtPaid = claimInfo.getAmt_paid();
					amtPaid = amtPaid.setScale(2);
					pDAcroForm.getField("lbl_29").setValue(amtPaid.toString().replace(".", "    "));

					BigDecimal amtDue = claimInfo.getAmt_due();
					amtDue = amtDue.setScale(2);
					pDAcroForm.getField("lbl_30").setValue(amtDue.toString().replace(".", "    "));

				} else {
					pDAcroForm.getField("lbl_29").setValue("0    00");
					pDAcroForm.getField("lbl_30").setValue(claimTotal.toString().replace(".", "    "));
				}

				pDAcroForm.getField("lbl_31_SignedBy")
						.setValue((claimInfo.getAtt_lname() + ", " + claimInfo.getAtt_fname()
								+ (GeneralOperations.isNotNullorEmpty(claimInfo.getAtt_mi())
										? ", " + claimInfo.getAtt_mi()
										: "")).toUpperCase());
				pDAcroForm.getField("lbl_31_SignedDate")
						.setValue(DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.MM_dd_yyyy));

				// --Facility Information
				if (GeneralOperations.isNotNullorEmpty(claimInfo.getFacility_name())
						&& GeneralOperations.isNotNullorEmpty(claimInfo.getFacility_address())) {
					pDAcroForm.getField("lbl_32_Name").setValue(claimInfo.getFacility_name().toUpperCase());
					pDAcroForm.getField("lbl_32_address").setValue(claimInfo.getFacility_address().toUpperCase());
					pDAcroForm.getField("lbl_32_city_state_zip")
							.setValue(claimInfo.getFacility_city().toUpperCase() + " "
									+ claimInfo.getFacility_state().toUpperCase() + " "
									+ claimInfo.getFacility_zip().toUpperCase());
				}
				if (GeneralOperations.isNotNullorEmpty(claimInfo.getFacility_npi())
						&& !insPriamry.getDont_print_facility_npi()) {
					pDAcroForm.getField("lbl_32a").setValue(claimInfo.getFacility_npi().toUpperCase());
				}

				String strBPrPhone = GeneralOperations.getNumbersOnlyFromString(claimInfo.getPhone_no());
				String strBPrPhoneCode = "";
				String strBPrPhoneNo = "";
				if (GeneralOperations.isNotNullorEmpty(strBPrPhone) && strBPrPhone.length() >= 10) {
					strBPrPhone = (strBPrPhone.substring(0, 1) == "1") ? (strBPrPhone.substring(1, strPhone.length()))
							: strBPrPhone;
					strBPrPhoneCode = strBPrPhone.substring(0, 3);
					strBPrPhoneNo = strBPrPhone.substring(3, 6) + " " + strBPrPhone.substring(6, strBPrPhone.length());

					pDAcroForm.getField("lbl_33PhCode").setValue(strBPrPhoneCode);
					pDAcroForm.getField("lbl_33PhNo").setValue(strBPrPhoneNo);

				}

				String FederalTaxIDType = "";
				String FederalTaxID = "";
				String box_33_type = "";
				String Billing_Provider_NPI = "";
				String TaxonomyCode = "";
				String box33Name = "";
				String box33Address = "";
				String box33CityStateZip = "";

				if (objClaims.getBillingProviderPayersInfo() != null) {
					if (GeneralOperations.isNotNullorEmpty(
							objClaims.getBillingProviderPayersInfo().getProvider_identification_number_type())
							&& GeneralOperations.isNotNullorEmpty(
									objClaims.getBillingProviderPayersInfo().getProvider_identification_number())) {
						FederalTaxIDType = objClaims.getBillingProviderPayersInfo()
								.getProvider_identification_number_type().toString().toUpperCase();
						FederalTaxID = objClaims.getBillingProviderPayersInfo().getProvider_identification_number()
								.toString();
					} else {
						FederalTaxIDType = claimInfo.getAtt_federaltaxidnumbertype().toString().toUpperCase();
						FederalTaxID = claimInfo.getFederal_taxid().toString();
					}

					if (GeneralOperations.isNotNullorEmpty(objClaims.getBillingProviderPayersInfo().getBox_33_type())) {
						box_33_type = objClaims.getBillingProviderPayersInfo().getBox_33_type().toString()
								.toUpperCase();
					} else {
						switch (FederalTaxIDType) {
						case "EIN": // Group
							box_33_type = "GROUP";
							break;
						case "SSN": // Individual
							box_33_type = "INDIVIDUAL";
							break;
						}
					}
				} else {
					FederalTaxIDType = claimInfo.getFederal_taxidnumbertype().toString().toUpperCase();
					FederalTaxID = claimInfo.getFederal_taxid().toString();

					switch (FederalTaxIDType) {
					case "EIN": // Group
						box_33_type = "GROUP";
						break;
					case "SSN": // Individual
						box_33_type = "INDIVIDUAL";
						break;
					}
				}

				if (GeneralOperations.isNotNullorEmpty(box_33_type)) {
					switch (box_33_type) {
					case "GROUP": // Group
						if (GeneralOperations.isNotNullorEmpty(claimInfo.getBl_group_npi())) {
							Billing_Provider_NPI = claimInfo.getBl_group_npi().toString();
						}
						if (GeneralOperations.isNotNullorEmpty(claimInfo.getGrp_taxonomy_id())) {
							TaxonomyCode = claimInfo.getGrp_taxonomy_id().toString();
						}

						box33Name = claimInfo.getBl_organization_name().toUpperCase();
						box33Address = claimInfo.getBill_address_grp().toUpperCase();
						box33CityStateZip = claimInfo.getBill_city_grp().toUpperCase() + " "
								+ claimInfo.getBill_state_grp().toUpperCase() + " "
								+ claimInfo.getBill_zip_grp().toUpperCase();

						break;
					case "INDIVIDUAL": // Individual

						if (GeneralOperations.isNotNullorEmpty(claimInfo.getBl_npi())) {
							Billing_Provider_NPI = claimInfo.getBl_npi().toString();
						}
						if (GeneralOperations.isNotNullorEmpty(claimInfo.getTaxonomy_code())) {
							TaxonomyCode = claimInfo.getTaxonomy_code().toString();
						}

						box33Name = claimInfo.getBl_lname().toUpperCase() + ", " + claimInfo.getBl_fname().toUpperCase()
								+ " " + claimInfo.getBl_mi().toUpperCase();
						box33Address = claimInfo.getBl_address().toUpperCase();
						box33CityStateZip = claimInfo.getBl_city().toUpperCase() + " "
								+ claimInfo.getBl_state().toUpperCase() + " " + claimInfo.getBl_zip().toUpperCase();

						break;
					}
				}
				// override taxonomy code mentioned in claim over the default taxonomy
				if(GeneralOperations.isNotNullorEmpty(claimInfo.getBilling_provider_taxonomy())) {
					TaxonomyCode = claimInfo.getBilling_provider_taxonomy();
				}
				

				switch (FederalTaxIDType) {
				case "EIN": // Group
					pDAcroForm.getField("lbl_25_EIN").setValue("X");
					break;
				case "SSN": // Individual
					pDAcroForm.getField("lbl_25_SSN").setValue("X");
					break;
				}

				pDAcroForm.getField("lbl_25_TaxId").setValue(FederalTaxID);
				pDAcroForm.getField("lbl_33_Name").setValue(box33Name);
				pDAcroForm.getField("lbl_33_address").setValue(box33Address);
				pDAcroForm.getField("lbl_33_city_state_zip").setValue(box33CityStateZip);

				pDAcroForm.getField("lbl_33a").setValue(Billing_Provider_NPI);
				pDAcroForm.getField("lbl_33b").setValue(TaxonomyCode);

			}

			String fullFilePath = hcfaFileUploadPath;
			if (repeatePageNo > 0) {
				pDAcroForm.getField("lblPageNo").setValue("Page " + repeatePageNo + " of 2");
				fullFilePath = fullFilePath + "_" + repeatePageNo;
			}
			fullFilePath = fullFilePath + ".pdf";
			pDDocument.save(fullFilePath);
			System.out.println("file generated:" + fullFilePath);

			if (repeatePageNo == 1) {

				pDDocument.close();
				getHcfaPage(objClaims, objUpdateClaim);

				fullFilePath = hcfaFileUploadPath + ".pdf";
				PDFMergerUtility pcFMergerUtility = new PDFMergerUtility();
				pcFMergerUtility.setDestinationFileName(fullFilePath);
				System.out.println("Hcfa Files Merged." + hcfaFileUploadPath + "_1.pdf + " + hcfaFileUploadPath
						+ "_2.pdf ==>" + fullFilePath);

				pcFMergerUtility.addSource(hcfaFileUploadPath + "_1.pdf");
				pcFMergerUtility.addSource(hcfaFileUploadPath + "_2.pdf");

				pcFMergerUtility.mergeDocuments(null);

				File file1 = new File(hcfaFileUploadPath + "_1.pdf");
				File file2 = new File(hcfaFileUploadPath + "_2.pdf");

				file1.delete();
				file2.delete();

				String[] splitedPath = fullFilePath.split("\\\\");
				generatedHCFAPath = splitedPath[splitedPath.length - 4] + "\\" + splitedPath[splitedPath.length - 3]
						+ "\\" + splitedPath[splitedPath.length - 2] + "\\" + splitedPath[splitedPath.length - 1];

			} else if (repeatePageNo == 0) {
				String[] splitedPath = fullFilePath.split("\\\\");
				generatedHCFAPath = splitedPath[splitedPath.length - 4] + "\\" + splitedPath[splitedPath.length - 3]
						+ "\\" + splitedPath[splitedPath.length - 2] + "\\" + splitedPath[splitedPath.length - 1];
			}

			// System.out.println("Generated HCFA File Path:"+generatedHCFAPath);

		} catch (Exception e) {

			// TODO: handle exception
			System.out.println(e.getMessage());

		} finally {
			if (pDDocument != null) {
				try {
					pDDocument.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return generatedHCFAPath;
	}

	private void populateProcLine(PDAcroForm pDAcroForm, ORMSubmission_ClaimProcedures objProc,
			ORMSubmission_ClaimInfo claimInfo, String strInsGroup, int line) throws IOException {

		pDAcroForm.getField("lbl_24a" + line + "_from_MM").setValue(objProc.getDos_from().split("/")[0]);
		pDAcroForm.getField("lbl_24a" + line + "_from_DD").setValue(objProc.getDos_from().split("/")[1]);
		pDAcroForm.getField("lbl_24a" + line + "_from_YY")
				.setValue(objProc.getDos_from().split("/")[2].substring(2, 4));

		pDAcroForm.getField("lbl_24a" + line + "_to_MM").setValue(objProc.getDos_to().split("/")[0]);
		pDAcroForm.getField("lbl_24a" + line + "_to_DD").setValue(objProc.getDos_to().split("/")[1]);
		pDAcroForm.getField("lbl_24a" + line + "_to_YY").setValue(objProc.getDos_to().split("/")[2].substring(2, 4));

		pDAcroForm.getField("lbl_24a" + line + "_pos").setValue(objProc.getPos().toUpperCase());
		pDAcroForm.getField("lbl_24a" + line + "_cpt").setValue(objProc.getProc_code().toUpperCase());

		pDAcroForm.getField("lbl_24a" + line + "_mod1").setValue(objProc.getMod1().toUpperCase());
		pDAcroForm.getField("lbl_24a" + line + "_mod2").setValue(objProc.getMod2().toUpperCase());
		pDAcroForm.getField("lbl_24a" + line + "_mod3").setValue(objProc.getMod3().toUpperCase());
		pDAcroForm.getField("lbl_24a" + line + "_mod4").setValue(objProc.getMod4().toUpperCase());

		String dxPointer = getPointerString(objProc.getDx_pointer1(), objProc.getDx_pointer2(),
				objProc.getDx_pointer3(), objProc.getDx_pointer4());
		pDAcroForm.getField("lbl_24a" + line + "_pointer").setValue(dxPointer);

		BigDecimal charges = objProc.getCharges();
		charges = charges.setScale(2);
		pDAcroForm.getField("lbl_24a" + line + "_charges").setValue(charges.toString().replace(".", "    "));

		pDAcroForm.getField("lbl_24a" + line + "_units").setValue(String.valueOf(objProc.getUnits()));

		if (strInsGroup == "MC" && GeneralOperations.isNotNullorEmpty(claimInfo.getTpi())) {
			if (GeneralOperations.isNotNullorEmpty(claimInfo.getAtt_npi())) {
				pDAcroForm.getField("lbl_24J" + line + "_NPI").setValue(claimInfo.getAtt_npi());
			}
			pDAcroForm.getField("lbl_24J" + line + "_Qual").setValue("");
			pDAcroForm.getField("lbl_24J" + line + "_QualNo").setValue(claimInfo.getTpi());
		} else {
			/*
			 * if (GeneralOperations.isNotNullorEmpty(claimInfo.getAtt_npi())) {
			 * pDAcroForm.getField("lbl_24J" + line +
			 * "_NPI").setValue(claimInfo.getAtt_npi()); } else if
			 * (GeneralOperations.isNotNullorEmpty(claimInfo.getAtt_taxonomy_code())) {
			 * pDAcroForm.getField("lbl_24J" + line + "_Qual").setValue("ZZ");
			 * pDAcroForm.getField("lbl_24J" + line +
			 * "_QualNo").setValue(claimInfo.getAtt_taxonomy_code()); }
			 */

			if (GeneralOperations.isNotNullorEmpty(claimInfo.getAtt_npi())) {
				pDAcroForm.getField("lbl_24J" + line + "_NPI").setValue(claimInfo.getAtt_npi());
			}

			if (GeneralOperations.isNotNullorEmpty(claimInfo.getAtt_taxonomy_code())) {
				pDAcroForm.getField("lbl_24J" + line + "_Qual").setValue("ZZ");
				pDAcroForm.getField("lbl_24J" + line + "_QualNo").setValue(claimInfo.getAtt_taxonomy_code());
			}
		}

	}

	private String getPointerString(String pointer1, String pointer2, String pointer3, String pointer4) {
		String finalPointers = "";
		String pointer = "";
		if (pointer1 != "0" || pointer2 != "0" || pointer3 != "0" || pointer4 != "0") {

			if (pointer1 != "")
				pointer = pointer1;
			else if (pointer2 != "")
				pointer = pointer2;
			else if (pointer3 != "")
				pointer = pointer3;
			else if (pointer4 != "")
				pointer = pointer4;

			switch (pointer) {
			case "1": {
				finalPointers += "A";
				break;
			}
			case "2": {
				finalPointers += "B";
				break;
			}
			case "3": {
				finalPointers += "C";
				break;
			}
			case "4": {
				finalPointers += "D";
				break;
			}
			case "5": {
				finalPointers += "E";
				break;
			}
			case "6": {
				finalPointers += "F";
				break;
			}
			case "7": {
				finalPointers += "G";
				break;
			}
			case "8": {
				finalPointers += "H";
				break;
			}
			case "9": {
				finalPointers += "I";
				break;
			}
			case "10": {
				finalPointers += "J";
				break;
			}
			case "11": {
				finalPointers += "K";
				break;
			}
			case "12": {
				finalPointers += "L";
				break;
			}
			default: {
				break;
			}
			}
		}

		if (pointer2 != "" && pointer2 != pointer) {
			finalPointers += getPointerString("", pointer2, "", "");
		}
		if (pointer3 != "" && pointer3 != pointer) {
			finalPointers += getPointerString("", "", pointer3, "");
		}
		if (pointer4 != "" && pointer4 != pointer) {
			finalPointers += getPointerString("", "", "", pointer4);
		}

		return finalPointers;
	}

}
