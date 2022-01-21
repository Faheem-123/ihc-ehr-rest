package com.ihc.ehr.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.MAPIDGenerator;
import com.ihc.ehr.model.ORMClaimNotesSave;
import com.ihc.ehr.model.ORMClaimPaymentSave;
import com.ihc.ehr.model.ORMEdiFtpInfo;
import com.ihc.ehr.model.ORMGetClaimUnResolvedCahRegisterPayment;
import com.ihc.ehr.model.ORMGetProcesuresForPosting;
import com.ihc.ehr.model.ORMHcfaFilesSave;
import com.ihc.ehr.model.ORMSubmission_ClaimDiagnosis;
import com.ihc.ehr.model.ORMSubmission_ClaimInfo;
import com.ihc.ehr.model.ORMSubmission_ClaimInsuarnceInfo;
import com.ihc.ehr.model.ORMSubmission_ClaimProcedures;
import com.ihc.ehr.model.ORMSubmission_ProviderPayers;
import com.ihc.ehr.model.ORM_restricted_codes;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.SubmissionProccessedClaimInfo;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.SubmissionMethod;

@Repository
public class BillingGeneral {

	@Autowired
	DBOperations db;

	public int AutoResolveCashRegister(Long cash_register_id, String user) {

		String str = " update cash_register set resolved=1,resolved_by='" + user
				+ "[Auto]',date_resolved=GETDATE() where cash_register_id = (  " + "	select eob_era_id "
				+ "	from claim_payment" + "	where eob_era_id_type='CASH_REGISTER' and eob_era_id='"
				+ cash_register_id.toString() + "' " + "   and ISNULL(deleted,0)<>1 " + "	group by eob_era_id "
				+ "	having " + "	( " + "			SUM(isnull(paid_amount,0))=isnull((select ("
				+ "			ISNULL(copay_paid,0)+ISNULL(copay_advance_adjusted,0)"
				+ "			+ISNULL(selfpay_paid,0)+ISNULL(selfpay_advance_adjusted,0)"
				+ "			+ISNULL(previous_balance_paid,0)+ISNULL(prev_balance_advance_adjusted,0))  "
				+ "			from cash_register where cash_register_id= " + cash_register_id.toString() + "),0) "
				+ "			and " + "			SUM(isnull(writeoff_amount,0)) = " + "			isnull((select ("
				+ "			ISNULL(copay_write_off,0)+ISNULL(selfpay_write_off,0) "
				+ "			+ISNULL(prev_balance_write_off,0))  "
				+ "			from cash_register where cash_register_id= " + cash_register_id.toString() + "),0) "
				+ "	) " + ")";

		return db.ExecuteUpdateQuery(str);
	}

	public String GenerateCashRegisterReceiptNo(Long practice_id) {
		try {

			String receiptNo = "";
			String query = " select 	"
					+ " case when max_value like convert(varchar,GETDATE(),112)+'%' then convert(varchar,GETDATE(),112)+'-'+convert(varchar,REPLACE(max_value,convert(varchar,GETDATE(),112),'')+1)  "
					+ "     else convert(varchar,GETDATE(),112)+'-1' " + " end as id "
					+ " from table_id With (xlock,ROWLOCK)  " + " where table_name = 'cr_receipt' and practice_id = '"
					+ practice_id.toString() + "' " + " update table_id set max_value =  "
					+ " case when max_value like convert(varchar,GETDATE(),112)+'%' then convert(varchar,GETDATE(),112)+''+convert(varchar,REPLACE(max_value,convert(varchar,GETDATE(),112),'')+1) "
					+ " 	 else convert(varchar,GETDATE(),112)+'1' " + " end "
					+ " where table_name = 'cr_receipt' and practice_id = '" + practice_id.toString() + "' ";

			List<?> list = db.getQueryData(query, MAPIDGenerator.class);
			if (list.size() > 0) {
				for (Object orm : list) {
					receiptNo = ((MAPIDGenerator) orm).getId();
				}
			}
			return receiptNo;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public int UpdateClaimBalance(Long claimId, String clientDateTime, String modifiedUser, String clientIp) {

		int result = 0;

		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("claim_id", claimId.toString(), String.class, ParameterMode.IN));
		lstParam.add(
				new SpParameters("client_modified_date", clientDateTime.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("modified_user", modifiedUser.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("client_ip", clientIp.toString(), String.class, ParameterMode.IN));

		// result = db.ExecuteUpdateStoreProcedure("spUpdateClaimBalance", lstParam);

		result = Integer.parseInt(db.ExecuteUpdateStoreProcedureWithOutputResult("spUpdateClaimBalance", lstParam));

		if (result > 0) {
			System.out.println("Claim Balance has been Updated.--- CLAIM ID: " + claimId);

			// update claim status
			UpdateClaimStatus(claimId, clientDateTime, modifiedUser, clientIp);

		} else {
			System.out.println("Unable to update Claim Balance.--- CLAIM ID: " + claimId);
		}
		return result;

	}

	public int UpdateClaimStatus(Long claimId, String clientDateTime, String modifiedUser, String clientIp) {
		int result = 0;

		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("claim_id", claimId.toString(), String.class, ParameterMode.IN));
		lstParam.add(
				new SpParameters("client_modified_date", clientDateTime.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("modified_user", modifiedUser.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("client_ip", clientIp.toString(), String.class, ParameterMode.IN));

		result = db.ExecuteUpdateStoreProcedure("spUpdateClaimStatus", lstParam);

		if (result > 0) {
			System.out.println("Claim Status has been Updated.--- CLAIM ID: " + claimId);
		} else {
			System.out.println("Unable to update Claim Status.--- CLAIM ID: " + claimId);
		}
		return result;
	}

	public List<ORMEdiFtpInfo> getEdiFtpInfo(String PracticeId) {

		try {

			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", PracticeId, String.class, ParameterMode.IN));
			//lstParam.add(new SpParameters("category", category, String.class, ParameterMode.IN));

			//return db.getStoreProcedureData("spGetFTPInfo", ORMEdiFtpInfo.class, lstParam);
			return db.getStoreProcedureData("spGetEdiFtpInfo", ORMEdiFtpInfo.class, lstParam);

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public List<ORMSubmission_ClaimInfo> getBatchClaimsInfoDetail(String practiceID, String batchID, Boolean isBatch) {
		try {
			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", practiceID.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("batch_id", batchID, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("isBatch", (isBatch ? "1" : "0"), String.class, ParameterMode.IN));
			// return db.getStoreProcedureData("spGetBatchClaimsInfo",
			// ORMSubmission_ClaimInfo.class, lstParam);
			return db.getStoreProcedureData("spGetBatchClaimsInfoNew", ORMSubmission_ClaimInfo.class, lstParam);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ORMSubmission_ClaimInsuarnceInfo> getBatchClaimInsurancesDetail(String practiceID, String batchID,
			Boolean isBatch) {
		try {
			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", practiceID.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("batch_id", batchID, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("isBatch", (isBatch ? "1" : "0"), String.class, ParameterMode.IN));
			return db.getStoreProcedureData("spGetBatchClaimsInsurancesInfo", ORMSubmission_ClaimInsuarnceInfo.class,
					lstParam);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ORMSubmission_ClaimDiagnosis> getBatchClaimDiagnosisDetail(String practiceID, String batchID,
			Boolean isBatch) {
		try {
			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", practiceID.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("batch_id", batchID, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("isBatch", (isBatch ? "1" : "0"), String.class, ParameterMode.IN));
			return db.getStoreProcedureData("spGetBatchClaimsDiagnosis", ORMSubmission_ClaimDiagnosis.class, lstParam);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ORMSubmission_ClaimProcedures> getBatchClaimProceduresDetail(String practiceID, String batchID,
			Boolean isBatch) {
		try {

			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", practiceID.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("batch_id", batchID, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("isBatch", (isBatch ? "1" : "0"), String.class, ParameterMode.IN));
			// return db.getStoreProcedureData("spGetBatchClaimsProcedures",
			// ORMSubmission_ClaimProcedures.class,
			// lstParam);
			return db.getStoreProcedureData("spGetBatchClaimsProceduresNew", ORMSubmission_ClaimProcedures.class,
					lstParam);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ORMSubmission_ProviderPayers> getBatchClaimProviderPayers(String providerID, String payerID,
			String practiceID) {

		try {
			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", practiceID.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("provider_id", providerID, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("payer_id", payerID, String.class, ParameterMode.IN));
			// lstParam.add(new SpParameters("isBatch", (isBatch ? "1" : "0"), String.class,
			// ParameterMode.IN));

			// return db.getStoreProcedureData("spGetBatchClaimsProviderPayers",
			// ORMSubmission_ProviderPayers.class,
			// lstParam);

			return db.getStoreProcedureData("spGetBatchClaimsProviderPayersNew", ORMSubmission_ProviderPayers.class,
					lstParam);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ORM_restricted_codes> getClaim_restricted_codes(String practiceID) {
		try {

			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", practiceID.toString(), String.class, ParameterMode.IN));
			return db.getStoreProcedureData("spGetPayer_claim_Rule", ORM_restricted_codes.class, lstParam);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int updateProcessedClaims(List<SubmissionProccessedClaimInfo> objProcessedClaim) {
		int result = 0;
		try {
			List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();
			String serverDateTime = DateTimeUtil.getCurrentDateTime();

			if (GeneralOperations.isNotNullorEmpty(objProcessedClaim)) {

				for (SubmissionProccessedClaimInfo objClaim : objProcessedClaim) {
					// String QRY = "";
					// QRY += " update claim set ";
					String query = "";
					if (objClaim.getIs_resubmitted()) {

						query = " update claim set is_resubmitted = '0', submission_status = 'RE-BILLED', submission_date = '"
								+ serverDateTime + "',date_modified = '" + serverDateTime + "' , modified_user = '"
								+ objClaim.getUser_name() + "', client_date_modified='" + objClaim.getClient_date_time()
								+ "' ";

						if (objClaim.getSubmission_method() == SubmissionMethod.HCFA) {
							query += ", hcfa_printed = '1' ";
						}						
						
						
						if (objClaim.getHas_primary_ins() && GeneralOperations.isNullorEmpty(objClaim.getPri_status())) {
							query += ", pri_status = 'B'  ";
						}
						if (objClaim.getHas_secondary_ins() && GeneralOperations.isNullorEmpty(objClaim.getSec_status())) {
							query += ", sec_status = 'B'  ";
						}
						if (objClaim.getHas_oth_ins() && GeneralOperations.isNullorEmpty(objClaim.getOth_status())) {
							query += ", oth_status = 'B' ";
						}
						
						query += " where claim_id =  '" + objClaim.getClaim_id() + "'";

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

						/*
						 * QRY += (objClaim.getSubmission_method() == SubmissionMethod.HCFA ?
						 * "hcfa_printed = '1', " : "") +
						 * " is_resubmitted = '0', submission_status = 'RE-BILLED', submission_date = getdate() ,date_modified = '"
						 * + DateTimeUtil.getCurrentDateTime() + "' , modified_user = '" +
						 * objClaim.getUser_name() + "' where claim_id =  '" + objClaim.getClaim_id() +
						 * "' ;     " + System.getProperty("line.separator");
						 */

						query = " update claim_procedure set is_resubmitted = '0' ,date_modified = '" + serverDateTime
								+ "' , modified_user = '" + objClaim.getUser_name() + "', client_date_modified='"
								+ objClaim.getClient_date_time() + "' "
								+ " where isnull(is_resubmitted,0)=1 and claim_id = '" + objClaim.getClaim_id() + "'";

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

					} else {

						query = " update claim set is_resubmitted = '0',pat_status='', submission_status = 'BILLED', submission_date = '"
								+ serverDateTime + "',date_modified = '" + serverDateTime + "' , modified_user = '"
								+ objClaim.getUser_name() + "', client_date_modified='" + objClaim.getClient_date_time()
								+ "' ";

						if (objClaim.getSubmission_method() == SubmissionMethod.HCFA) {
							query += ", hcfa_printed = '1' ";
						}

						if (objClaim.getSubmission_method() == SubmissionMethod.HCFA) {
							if (objClaim.getIs_bill_to_secondary()) {
								if (objClaim.getHas_secondary_ins()) {
									query += ", sec_status = 'B'  ";
								}
							} else {
								if (objClaim.getHas_primary_ins()) {
									query += ", pri_status = 'B'  ";
								}
							}
						} else {
							if (objClaim.getHas_primary_ins()) {
								query += ", pri_status = 'B'  ";
							}
							if (objClaim.getHas_secondary_ins()) {
								query += ", sec_status = 'B'  ";
							}
							if (objClaim.getHas_oth_ins()) {
								query += ", oth_status = 'B' ";
							}
						}

						query += " where claim_id =  '" + objClaim.getClaim_id() + "'";

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

						/*
						 * query += (objClaim.getSubmission_method() == SubmissionMethod.HCFA ?
						 * "hcfa_printed = '1', " : "") +
						 * " is_resubmitted = '0', pat_status = '',submission_status = 'BILLED', submission_date = getdate() ,date_modified = '"
						 * + DateTimeUtil.getCurrentDateTime() + "' , modified_user = '" +
						 * objClaim.getUser_name() + "' where claim_id =  '" + objClaim.getClaim_id() +
						 * "' ;     " + System.getProperty("line.separator"); // QRY +=
						 * " update claim_procedure set is_resubmitted = '0' where claim_id = '" // +
						 * objClaim.getClaim_id() + "' ; " + System.getProperty("line.separator");
						 */

					}

					// lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, QRY));

					if (objClaim.getAdd_claim_note()) {

						ORMClaimNotesSave ormClaimNotesSave = new ORMClaimNotesSave();

						ormClaimNotesSave.setNotes_id(db.IDGenerator("claim_notes", objClaim.getPractice_id()));

						ormClaimNotesSave.setDate_created(serverDateTime);
						ormClaimNotesSave.setDate_modified(serverDateTime);

						ormClaimNotesSave.setClient_date_created(objClaim.getClient_date_time());
						ormClaimNotesSave.setClient_date_modified(objClaim.getClient_date_time());

						ormClaimNotesSave.setCreated_user("Billing_Admin");
						ormClaimNotesSave.setModified_user("Billing_Admin");
						ormClaimNotesSave.setPatient_id(objClaim.getPatient_id());
						ormClaimNotesSave.setClaim_id(objClaim.getClaim_id());

						ormClaimNotesSave.setPractice_id(objClaim.getPractice_id());

						if (objClaim.getSubmission_method() == SubmissionMethod.HCFA) {
							ormClaimNotesSave.setNotes("HCFA has been sent to " + objClaim.getInsurance_name() + " on ("
									+ serverDateTime + ") by " + objClaim.getUser_name());
						} else {
							ormClaimNotesSave.setNotes("Claim has been built to " + objClaim.getInsurance_name()
									+ " on (" + serverDateTime + ") by " + objClaim.getUser_name());
						}

						ormClaimNotesSave.setIs_auto(true);

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimNotesSave));

					}
				}

				result = db.AddUpdateEntityWrapper(lstEntityWrapper);

				// result = db.ExecuteUpdateQuery(QRY);

			}
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}

		return result;
	}

	public Long saveHcfaGeneratedFile(ORMHcfaFilesSave objSave) {
		Long id = (long) 0;
		try {
			ORMHcfaFilesSave objResult = (ORMHcfaFilesSave) this.db.AddEntityWithIdentity(objSave);

			if (objResult != null) {
				id = objResult.getId();
			}

		} catch (Exception e) {
			System.out.println("\nError Occurrerd : \n");
			e.printStackTrace();
		}
		return id;

	}
	
	
	private int MapCashRegisterPayment(Long practiceId, Long patientId, Long claimId, boolean isSelfPay,
			String clientDate, String userName, String clientIp,String cashRegistrId) {

		int result = 0;

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("claim_id", claimId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("cash_register_id", cashRegistrId.toString(), String.class, ParameterMode.IN));

		List<ORMGetClaimUnResolvedCahRegisterPayment> lstCash = db.getStoreProcedureData(
				"spGetClaimUnResovledCashRegisterPayment", ORMGetClaimUnResolvedCahRegisterPayment.class, lstParam);

		if (lstCash != null && lstCash.size() > 0) {

			List<SpParameters> lstParamCpts = new ArrayList<>();
			lstParamCpts.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
			lstParamCpts.add(new SpParameters("claim_id", claimId.toString(), String.class, ParameterMode.IN));
			lstParamCpts.add(new SpParameters("dos", "", String.class, ParameterMode.IN));
			lstParamCpts.add(new SpParameters("provider_id", "", String.class, ParameterMode.IN));
			lstParamCpts.add(new SpParameters("location_id", "", String.class, ParameterMode.IN));
			lstParamCpts.add(new SpParameters("cash_register_id", cashRegistrId, String.class, ParameterMode.IN));

			List<ORMGetProcesuresForPosting> lstCPTs = db.getStoreProcedureData("spGetProceduresForPosting",
					ORMGetProcesuresForPosting.class, lstParamCpts);

			float cpt_balance = 0;
			float paid_amount = 0;
			float write_off_amount = 0;

			float total_payment = 0;
			float total_write_off = 0;
			String serverDateTime = DateTimeUtil.getCurrentDateTime();

			// ArrayList<ORMClaimPaymentSave> lstClaimPaymentSave=new ArrayList<>();

			// auto post only if no of cpt=1 or claim is self pay
			if (lstCPTs != null && lstCPTs.size() > 0 && (lstCPTs.size() == 1 || isSelfPay == true)) {

				// auto post only if no of cpt=1 or claim is self pay
				// if (lstCPTs.size() > 1
				// && (claimSave_Pro.getSelf_pay() == null || claimSave_Pro.getSelf_pay() ==
				// false)) {
				// return 0;
				// }

				for (ORMGetClaimUnResolvedCahRegisterPayment objCash : lstCash) {

					List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

					total_payment = objCash.getPaid_pending().floatValue();
					total_write_off = objCash.getWrite_off_pending().floatValue();

					for (ORMGetProcesuresForPosting objProc : lstCPTs) {

						cpt_balance = objProc.getCpt_balance().floatValue();
						paid_amount = 0;
						write_off_amount = 0;

						if (cpt_balance > 0 && (total_payment > 0 || total_write_off > 0)) {
							if (total_payment > 0) {
								if (total_payment >= cpt_balance) {
									paid_amount = cpt_balance;
									cpt_balance = 0;
									total_payment = total_payment - paid_amount;
								} else {
									paid_amount = total_payment;
									total_payment = 0;
									cpt_balance = cpt_balance - paid_amount;
								}
							}

							if (cpt_balance > 0 && total_write_off > 0) {
								if (total_write_off >= cpt_balance) {
									write_off_amount = cpt_balance;
									// cpt_balance=0;
									total_write_off = total_write_off - write_off_amount;
								} else {
									write_off_amount = total_write_off;
									total_write_off = 0;
									// cpt_balance=cpt_balance-write_off_amount;
								}
							}

							if (paid_amount > 0 || write_off_amount > 0) {

								ORMClaimPaymentSave ormPaymentSave = new ORMClaimPaymentSave();

								ormPaymentSave.setClaim_payments_id(db.IDGenerator("claim_payment", practiceId));
								ormPaymentSave.setClaim_procedures_id(objProc.getClaim_procedures_id());
								ormPaymentSave.setCharged_procedure(objProc.getProc_code());
								ormPaymentSave.setPaid_procedure(objProc.getProc_code());
								ormPaymentSave.setPayment_source("Patient");
								ormPaymentSave.setEntry_type("Cash Register");
								ormPaymentSave.setEob_era_id(objCash.getCash_register_id());
								ormPaymentSave.setEob_era_id_type("CASH_REGISTER");
								ormPaymentSave.setClaim_id(objProc.getClaim_id());
								ormPaymentSave.setPatient_id(objCash.getPatient_id());
								ormPaymentSave.setPaid_amount(BigDecimal.valueOf(paid_amount));
								ormPaymentSave.setWriteoff_amount(BigDecimal.valueOf(write_off_amount));
								ormPaymentSave.setPractice_id(practiceId);

								if (GeneralOperations.isNotNullorEmpty(objCash.getCheck_number())) {
									ormPaymentSave.setCheck_number(objCash.getCheck_number());
									ormPaymentSave.setCheck_date(objCash.getCheck_date());
								}

								ormPaymentSave.setClient_date_created(clientDate);
								ormPaymentSave.setClient_date_modified(clientDate);
								ormPaymentSave.setCreated_user(userName);
								ormPaymentSave.setModified_user(userName);
								ormPaymentSave.setSystem_ip(clientIp);

								ormPaymentSave.setDate_modified(serverDateTime);
								ormPaymentSave.setDate_created(serverDateTime);

								lstEntityWrapper
										.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormPaymentSave));
								// result = result + hibernate.add(ormPayment);
							}

						}
					}

					if (lstEntityWrapper != null && lstEntityWrapper.size() > 0) {

						ORMClaimNotesSave ormClaimNotesSave = new ORMClaimNotesSave();

						ormClaimNotesSave.setNotes_id(db.IDGenerator("claim_notes", practiceId));

						ormClaimNotesSave.setDate_created(serverDateTime);
						ormClaimNotesSave.setDate_modified(serverDateTime);

						ormClaimNotesSave.setClient_date_created(clientDate);
						ormClaimNotesSave.setClient_date_modified(clientDate);

						ormClaimNotesSave.setCreated_user(userName);
						ormClaimNotesSave.setModified_user(userName);

						ormClaimNotesSave.setPatient_id(patientId);
						ormClaimNotesSave.setClaim_id(claimId);

						ormClaimNotesSave.setPractice_id(practiceId);

						ormClaimNotesSave.setNotes("Cash Register Payment Auto Imported.");

						ormClaimNotesSave.setIs_auto(true);

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimNotesSave));

						result = db.AddUpdateEntityWrapper(lstEntityWrapper);

						if (result > 0) {
							AutoResolveCashRegister(objCash.getCash_register_id(), userName);
						}

					}

				}
			}
		}

		return result;

	}

	
	public int AutoPostCashRegistrPayment(Long practiceId, Long patientId, Long claimId, boolean isSelfPay,
			String clientDate, String userName, String clientIp,String cashRegisterId) {

		
		int cashRegPaymentPostedResult = MapCashRegisterPayment(practiceId, patientId, claimId, isSelfPay, clientDate,
				userName, clientIp,cashRegisterId);

		UpdateClaimBalance(claimId, clientDate, userName, clientIp);

		if (cashRegPaymentPostedResult > 0) {
			UpdateClaimStatus(claimId, clientDate, userName, clientIp);
		}
		
		return cashRegPaymentPostedResult;
	}

}
