package com.ihc.ehr.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.MAPIDGenerator;
import com.ihc.ehr.model.ORMCashRegisterAdd;
import com.ihc.ehr.model.ORMCashRegisterGet;
import com.ihc.ehr.model.ORMCashRegisterModify;
import com.ihc.ehr.model.ORMCashRegisterPatInfo;
import com.ihc.ehr.model.ORMCashRegisterUnResolvedPaymentGet;
import com.ihc.ehr.model.ORMClaimNotesSave;
import com.ihc.ehr.model.ORMClaimPaymentSave;
import com.ihc.ehr.model.ORMCreditCardPaymentSave;
import com.ihc.ehr.model.ORMFourColumGeneric;
import com.ihc.ehr.model.ORMGetProcesuresForPosting;
import com.ihc.ehr.model.ORMIdCodeDescription;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientNotesSave;
import com.ihc.ehr.model.ORMPatientRefundGet;
import com.ihc.ehr.model.ORMPatientRefundSave;
import com.ihc.ehr.model.ORMSavePatientNotPaidReason;
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.model.Wrapper_PatientRefundSave;
import com.ihc.ehr.model.claimPostingResponse;
import com.ihc.ehr.util.BillingGeneral;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.EnumUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@Repository
public class CashRegisterDOAImpl implements CashRegisterDOA {

	@Autowired
	DBOperations db;

	@Autowired
	BillingGeneral billingGeneral;

	@Override
	public List<ORMIdCodeDescription> getWriteOffCodes(Long practice_id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPracticePatientWriteOffCodes", ORMIdCodeDescription.class, lstParam);
	}

	@Override
	public List<ORMFourColumGeneric> getPaymentPlan(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPatientPaymentPlan", ORMFourColumGeneric.class, lstParam);
	}

	@Override
	public String getPatientBalance(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		List<MAPIDGenerator> lst = db.getStoreProcedureData("proGetCashRegAmtDue", MAPIDGenerator.class, lstParam);

		String patBal = lst.get(0).getId();

		return patBal;
	}

	@Override
	public ORMCashRegisterPatInfo getCahRegisterInfo(SearchCriteria searchCriteria) {

		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMCashRegisterPatInfo> lst = db.getStoreProcedureData("spGetCashRegisterInfo",
				ORMCashRegisterPatInfo.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMCashRegisterGet> getCashRegister(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMCashRegisterGet> lst = db.getStoreProcedureData("spGetCashRegister", ORMCashRegisterGet.class,
				lstParam);

		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> addCashRegisterPayment(ORMCashRegisterAdd objCashRegister,
			ORMSavePatientNotPaidReason objNotPaidReason) {

		int result = 0;

		Long cashRegisterId = (long) 0;
		objCashRegister.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (objCashRegister.getCash_register_id() == null) {
			cashRegisterId = db.IDGenerator("cash_register", objCashRegister.getPractice_id());
			objCashRegister.setCash_register_id(cashRegisterId);

			objCashRegister.setDate_created(DateTimeUtil.getCurrentDateTime());
			// objCashRegister.setReceipt_no(db.CashRegisterReceiptNoGenerator(objCashRegister.getPractice_id()));
			objCashRegister
					.setReceipt_no(billingGeneral.GenerateCashRegisterReceiptNo(objCashRegister.getPractice_id()));

			result = db.SaveEntity(objCashRegister, EnumUtil.Operation.ADD);

			if (result > 0) {

				// -- Posting current DOS payment only if claim exists with single CPT

				// get matching claim id for dos,loc,provider
				String sql = " select top 1 claim_id as col1,isnull(self_pay,0) as col2,isnull(draft,0) as col3 from claim "
						+ " where isnull(deleted,0)<>1 and isnull(draft,0)<>1 and practice_id='"
						+ objCashRegister.getPractice_id() + "' and patient_id='"+objCashRegister.getPatient_id()+"' and convert(date,dos)=convert(date,'"
						+ objCashRegister.getDos() + "') and location_id='" + objCashRegister.getLocation_id()
						+ "' and attending_physician='" + objCashRegister.getProvider_id() + "' order by date_created desc";

				List<ORMThreeColumGeneric> lstThreeColumnGeneric = db.getQueryData(sql, ORMThreeColumGeneric.class);

				if (lstThreeColumnGeneric != null && lstThreeColumnGeneric.size() > 0) {

					// if not draft
					if (Boolean.parseBoolean(lstThreeColumnGeneric.get(0).getCol2()) == false) {
						billingGeneral.AutoPostCashRegistrPayment(objCashRegister.getPractice_id(),
								objCashRegister.getPatient_id(), Long.parseLong(lstThreeColumnGeneric.get(0).getCol1()),
								Boolean.parseBoolean(lstThreeColumnGeneric.get(0).getCol2()),
								objCashRegister.getClient_date_created(), objCashRegister.getCreated_user(),
								objCashRegister.getSystem_ip(),objCashRegister.getCash_register_id().toString());
					}
				}

				// ---- Posting Previous Claims Balance if any
				float total_prev_payment_posting = Float
						.parseFloat(GeneralOperations.isNullorEmpty(objCashRegister.getPrevious_balance_paid()) ? "0.00"
								: objCashRegister.getPrevious_balance_paid().toString())
						+ Float.parseFloat(
								GeneralOperations.isNullorEmpty(objCashRegister.getPrev_balance_advance_adjusted())
										? "0.00"
										: objCashRegister.getPrev_balance_advance_adjusted().toString());
				float total_prev_write_off = Float.parseFloat(
						GeneralOperations.isNullorEmpty(objCashRegister.getPrev_balance_write_off()) ? "0.00"
								: objCashRegister.getPrev_balance_write_off().toString());

				if ((total_prev_payment_posting > 0 || total_prev_write_off > 0)) {

					System.out.println("Posting Previous Balance :-  ");
					postPreviousBalance(objCashRegister, total_prev_payment_posting, total_prev_write_off);
				}
			}

			if (objNotPaidReason != null) {
				objNotPaidReason.setNot_paid_reason_id(
						db.IDGenerator("patient_not_paid_reason", objNotPaidReason.getPractice_id()));
				objNotPaidReason.setCash_register_id(cashRegisterId);
				objNotPaidReason.setDate_created(DateTimeUtil.getCurrentDateTime());

				result = db.SaveEntity(objNotPaidReason, EnumUtil.Operation.ADD);
			}

		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(cashRegisterId.toString());
		}

		return resp;
	}


	@Override
	public ServiceResponse<ORMKeyValue> modifyCashRegisterPayment(ORMCashRegisterModify objCashRegisterModify) {
		// TODO Auto-generated method stub
		int result = 0;

		Long cashRegisterId = (long) 0;

		if (objCashRegisterModify.getCash_register_id() != null) {
			cashRegisterId = objCashRegisterModify.getCash_register_id();

			objCashRegisterModify.setDate_modified(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(objCashRegisterModify, EnumUtil.Operation.EDIT);
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(cashRegisterId.toString());
		}

		return resp;
	}

	@Override
	public List<ORMFourColumGeneric> getNotPaidReason(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPaymentNotPaidReaon", ORMFourColumGeneric.class, lstParam);
	}

	@Override
	public List<ORMPatientRefundGet> getPatientRefund(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMPatientRefundGet> lst = db.getStoreProcedureData("spGetPatientRefund", ORMPatientRefundGet.class,
				lstParam);

		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePatientRefund(Wrapper_PatientRefundSave wrapperPatientRefundSave) {
		// TODO Auto-generated method stub
		int result = 0;
		Long selectedClaimId = (long) 0;
		String claimNote = "";
		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();
		Long refundId = (long) 0;
		String serverDateTime = DateTimeUtil.getCurrentDateTime();
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		List<ORMKeyValue> lstKeyValue = wrapperPatientRefundSave.getLstKeyValue();

		if (lstKeyValue != null) {
			for (ORMKeyValue item : lstKeyValue) {
				if (item.getKey().equalsIgnoreCase("CLAIM_NOTE")) {
					claimNote = item.getValue();
				}
			}
		}

		ORMPatientRefundSave objPatientRefundSave = wrapperPatientRefundSave.getPatientRefundSave();

		if (objPatientRefundSave.getRefund_id() == null) {

			refundId = db.IDGenerator("patient_refund", objPatientRefundSave.getPractice_id());
			objPatientRefundSave.setRefund_id(refundId);

			objPatientRefundSave.setDate_created(serverDateTime);
			objPatientRefundSave.setDate_modified(serverDateTime);
			// objPatientRefundSave.setReceipt_no(cashRegisterDOAImpl.GenerateCashRegisterReceiptNo(objCashRegister.getPractice_id()));

			lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objPatientRefundSave));
			// result = db.SaveEntity(objCashRegister, EnumUtil.Operation.ADD);
		}

		ORMCreditCardPaymentSave objCreditCardPaymentSave = wrapperPatientRefundSave.getCreditCardPaymentSave();
		if (objCreditCardPaymentSave != null) {

			objCreditCardPaymentSave
					.setCcpayment_id(db.IDGenerator("credit_card_payment", objCreditCardPaymentSave.getPractice_id()));
			objCreditCardPaymentSave.setDate_created(serverDateTime);
			objCreditCardPaymentSave.setDate_modified(serverDateTime);
			objCreditCardPaymentSave.setCash_register_id(refundId);
			objCreditCardPaymentSave.setComments("Refund");

			lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objCreditCardPaymentSave));
		}

		List<ORMClaimPaymentSave> lstClaimPaymentSave = wrapperPatientRefundSave.getLstClaimPaymentSave();
		if (lstClaimPaymentSave != null) {

			for (ORMClaimPaymentSave ormClaimPaymentSave : lstClaimPaymentSave) {

				Long claimPaymentId = db.IDGenerator("claim_payment", ormClaimPaymentSave.getPractice_id());
				ormClaimPaymentSave.setClaim_payments_id(claimPaymentId);
				ormClaimPaymentSave.setDate_created(serverDateTime);
				ormClaimPaymentSave.setDate_modified(serverDateTime);
				ormClaimPaymentSave.setEob_era_id_type("PATIENT_REFUND");
				ormClaimPaymentSave.setEob_era_id(refundId);
				ormClaimPaymentSave.setEntry_type("Patient Refund");
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimPaymentSave));

				if (!selectedClaimId.equals(ormClaimPaymentSave.getClaim_id())) {
					selectedClaimId = ormClaimPaymentSave.getClaim_id();

					if (!claimNote.isEmpty()) {

						ORMClaimNotesSave ormClaimNotesSave = new ORMClaimNotesSave();

						ormClaimNotesSave
								.setNotes_id(db.IDGenerator("claim_notes", ormClaimPaymentSave.getPractice_id()));

						ormClaimNotesSave.setDate_created(serverDateTime);
						ormClaimNotesSave.setDate_modified(serverDateTime);

						ormClaimNotesSave.setClient_date_created(ormClaimPaymentSave.getClient_date_created());
						ormClaimNotesSave.setClient_date_modified(ormClaimPaymentSave.getClient_date_modified());

						ormClaimNotesSave.setCreated_user(ormClaimPaymentSave.getCreated_user());
						ormClaimNotesSave.setModified_user(ormClaimPaymentSave.getModified_user());

						ormClaimNotesSave.setPatient_id(ormClaimPaymentSave.getPatient_id());
						ormClaimNotesSave.setClaim_id(selectedClaimId);

						ormClaimNotesSave.setPractice_id(ormClaimPaymentSave.getPractice_id());

						ormClaimNotesSave.setNotes(claimNote);

						ormClaimNotesSave.setIs_auto(true);

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimNotesSave));
					}
				}

			}
		}

		result = db.AddUpdateEntityWrapper(lstEntityWrapper);

		if (result > 0 && lstClaimPaymentSave!=null && lstClaimPaymentSave.size()>0) {
			selectedClaimId = (long) 0;
			for (ORMClaimPaymentSave ormClaimPaymentSave : lstClaimPaymentSave) {

				if (!selectedClaimId.equals(ormClaimPaymentSave.getClaim_id())) {
					selectedClaimId = ormClaimPaymentSave.getClaim_id();
					billingGeneral.UpdateClaimBalance(selectedClaimId, ormClaimPaymentSave.getClient_date_modified(),
							ormClaimPaymentSave.getModified_user(), ormClaimPaymentSave.getSystem_ip());
				}
			}
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(selectedClaimId.toString());
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> voidCashRegisterEntry(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		List<SpParameters> lstParam = new ArrayList<>();

		Long cashRegisterId = (long) 0;

		if (lstKeyValue != null) {

			for (ORMKeyValue item : lstKeyValue) {

				if (item.getKey().equals("cash_register_id")) {
					cashRegisterId = Long.parseLong(item.getValue().toString());
				}
				lstParam.add(
						new SpParameters(item.getKey(), item.getValue().toString(), String.class, ParameterMode.IN));
			}
			
			result = Integer.parseInt(db.ExecuteUpdateStoreProcedureWithOutputResult("spSetPatientPaymentVoid", lstParam));			
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(cashRegisterId.toString());
		}

		return resp;

	}

	@Override
	public ServiceResponse<ORMKeyValue> checkBounceCashRegisterEntry(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		List<SpParameters> lstParam = new ArrayList<>();

		Long cashRegisterId = (long) 0;

		if (lstKeyValue != null) {

			for (ORMKeyValue item : lstKeyValue) {

				if (item.getKey().equals("cash_register_id")) {
					cashRegisterId = Long.parseLong(item.getValue().toString());
				}
				lstParam.add(
						new SpParameters(item.getKey(), item.getValue().toString(), String.class, ParameterMode.IN));
			}

			result = db.ExecuteUpdateStoreProcedure("spSetPatientPaymentCheckBounce", lstParam);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(cashRegisterId.toString());
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> markAsResolvedCashRegisterEntry(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		List<SpParameters> lstParam = new ArrayList<>();

		Long cashRegisterId = (long) 0;
		String clientDateTime = "";
		String clientUser = "";
		String clientIP = "";
		String comments = "";

		if (lstKeyValue != null) {

			for (ORMKeyValue item : lstKeyValue) {

				switch (item.getKey()) {
				case "cash_register_id":
					cashRegisterId = Long.parseLong(item.getValue().toString());
					break;
				case "client_modified_date":
					clientDateTime = item.getValue().toString();
					break;
				case "modified_user":
					clientUser = item.getValue().toString();
					break;
				case "client_ip":
					clientIP = item.getValue().toString();
					break;
				case "comments":
					comments = item.getValue().toString();
					break;

				default:
					break;
				}
				if (item.getKey().equals("cash_register_id")) {
					cashRegisterId = Long.parseLong(item.getValue().toString());
				}
				lstParam.add(
						new SpParameters(item.getKey(), item.getValue().toString(), String.class, ParameterMode.IN));
			}

			String strQuery = " update cash_register set resolved=1,resolved_by='" + clientUser + "',date_resolved='"
					+ clientDateTime + "',system_ip='" + clientIP + "', " + " modified_user='" + clientUser
					+ "',date_modified=getdate(),client_date_modified='" + clientDateTime + "',modification_comments='"
					+ comments + "' " + " where cash_register_id ='" + cashRegisterId.toString() + "'";

			result = db.ExecuteUpdateQuery(strQuery);

		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(cashRegisterId.toString());
		}

		return resp;
	}

	@Override
	public List<ORMCashRegisterUnResolvedPaymentGet> getUnResolvedCashRegisterPayments(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();

		if (lstKeyValue != null) {

			for (ORMKeyValue item : lstKeyValue) {

				switch (item.getKey()) {
				case "patient_id":

					lstParam.add(new SpParameters(item.getKey(), item.getValue().toString(), String.class,
							ParameterMode.IN));
					break;

				case "dos":

					lstParam.add(new SpParameters(item.getKey(), item.getValue().toString(), String.class,
							ParameterMode.IN));
					break;

				default:
					break;
				}
			}

		}

		return db.getStoreProcedureData("spGetUnResovledCashRegisterPayment", ORMCashRegisterUnResolvedPaymentGet.class,
				lstParam);

	}

	@Override
	public ServiceResponse<ORMKeyValue> savePaymentPlan(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub

		int result = 0;

		String practice_id = "";
		String patient_id = "";
		String payment_plan_id = "";
		String payment_plan = "";
		String user_name = "";
		String client_date_time = "";
		String system_ip = "";
		Long notesId = (long) 0;
		String errMsg = "";
		if (lstKeyValue != null && lstKeyValue.size() > 0) {

			for (ORMKeyValue item : lstKeyValue) {

				switch (item.getKey()) {
				case "practice_id":
					practice_id = item.getValue() == null ? "" : item.getValue();
					break;
				case "patient_id":
					patient_id = item.getValue() == null ? "" : item.getValue();
					break;
				case "payment_plan_id":
					payment_plan_id = item.getValue() == null ? "" : item.getValue();
					break;
				case "payment_plan":
					payment_plan = item.getValue() == null ? "" : item.getValue();
					break;
				case "user_name":
					user_name = item.getValue() == null ? "" : item.getValue();
					break;
				case "client_date_time":
					client_date_time = item.getValue() == null ? "" : item.getValue();
					break;
				case "system_ip":
					system_ip = item.getValue() == null ? "" : item.getValue();
					break;

				default:
					break;
				}
			}

			if (GeneralOperations.isNullorEmpty(practice_id) || GeneralOperations.isNullorEmpty(patient_id)
					|| GeneralOperations.isNullorEmpty(payment_plan) || GeneralOperations.isNullorEmpty(user_name)
					|| GeneralOperations.isNullorEmpty(client_date_time)
					|| GeneralOperations.isNullorEmpty(system_ip)) {

				errMsg = "One of the required parameter is missing.";

			} else {
				if (GeneralOperations.isNotNullorEmpty(payment_plan_id)) {

					String query = " update patient_note set notes='" + payment_plan
							+ "',date_modified=getdate(),client_date_modified='" + client_date_time
							+ "',modified_user='" + user_name + "',system_ip='" + system_ip + "' "
							+ " where patient_id='" + patient_id + "' and patient_note_id='" + payment_plan_id + "' ";

					result = this.db.ExecuteUpdateQuery(query);

				} else {

					ORMPatientNotesSave ormPatientNotesSave = new ORMPatientNotesSave();

					notesId = db.IDGenerator("patient_note", Long.parseLong(practice_id));
					ormPatientNotesSave.setPatient_note_id(notesId);

					ormPatientNotesSave.setPractice_id(Long.parseLong(practice_id));
					ormPatientNotesSave.setPatient_id(Long.parseLong(patient_id));
					ormPatientNotesSave.setCreated_user(user_name);
					ormPatientNotesSave.setModified_user(user_name);
					ormPatientNotesSave.setClient_date_created(client_date_time);
					ormPatientNotesSave.setClient_date_modified(client_date_time);
					ormPatientNotesSave.setDate_created(DateTimeUtil.getCurrentDateTime());
					ormPatientNotesSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
					ormPatientNotesSave.setSystem_ip(system_ip);
					ormPatientNotesSave.setNotes_type("PAYMENT_PLAN");
					ormPatientNotesSave.setNotes(payment_plan);

					result = db.SaveEntity(ormPatientNotesSave, EnumUtil.Operation.ADD);
				}
			}

		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0 || errMsg != "") {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse(errMsg != "" ? errMsg : "An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(notesId.toString());
		}

		return resp;
	}

	private List<claimPostingResponse> postPreviousBalance(ORMCashRegisterAdd ormCash, float totalPayment,
			float totalWriteOff) {

		int result = 0;

		if (totalPayment <= 0 && totalWriteOff <= 0) {

			return null;
		}

		String location_id = ormCash.getLocation_id() == null ? "" : ormCash.getLocation_id().toString();
		String provider_id = ormCash.getProvider_id() == null ? "" : ormCash.getProvider_id().toString();
		String dos = ormCash.getDos() == null ? "" : ormCash.getDos();

		List<SpParameters> lstParamCpts = new ArrayList<>();
		lstParamCpts.add(
				new SpParameters("patient_id", ormCash.getPatient_id().toString(), String.class, ParameterMode.IN));
		lstParamCpts.add(new SpParameters("claim_id", "", String.class, ParameterMode.IN));
		lstParamCpts.add(new SpParameters("dos", dos, String.class, ParameterMode.IN));
		lstParamCpts.add(new SpParameters("provider_id", provider_id, String.class, ParameterMode.IN));
		lstParamCpts.add(new SpParameters("location_id", location_id, String.class, ParameterMode.IN));
		lstParamCpts.add(new SpParameters("cash_register_id", "", String.class, ParameterMode.IN));

		List<ORMGetProcesuresForPosting> lstCPTs = db.getStoreProcedureData("spGetProceduresForPosting",
				ORMGetProcesuresForPosting.class, lstParamCpts);

		float cptBalance = 0;
		float paidAmout = 0;
		float writeOffAmount = 0;
		//Long selectedClaimId = (long) 0;

		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		ORMClaimPaymentSave ormPaymentSave = new ORMClaimPaymentSave();

		// List<Long> lstClaimIds = new ArrayList<>();
		List<claimPostingResponse> lstResponse = new ArrayList<>();

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		for (ORMGetProcesuresForPosting objProc : lstCPTs) {

			//selectedClaimId = objProc.getClaim_id();

			/*
			 * boolean isCalimIdAddes = false; for (int i = 0; i < lstClaimIds.size(); i++)
			 * { if (lstClaimIds.get(i).equals(selectedClaimId)) { isCalimIdAddes = true;
			 * break; } } if (!isCalimIdAddes) { lstClaimIds.add(selectedClaimId); }
			 */

			cptBalance = objProc.getCpt_balance().floatValue();
			paidAmout = 0;
			writeOffAmount = 0;

			if (cptBalance > 0 && (totalPayment > 0 || totalWriteOff > 0)) {
				if (totalPayment > 0) {
					if (totalPayment >= cptBalance) {
						paidAmout = cptBalance;
						cptBalance = 0;
						totalPayment = totalPayment - paidAmout;
					} else {
						paidAmout = totalPayment;
						totalPayment = 0;
						cptBalance = cptBalance - paidAmout;
					}
				}

				if (cptBalance > 0 && totalWriteOff > 0) {
					if (totalWriteOff >= cptBalance) {
						writeOffAmount = cptBalance;
						// cpt_balance=0;
						totalWriteOff = totalWriteOff - writeOffAmount;
					} else {
						writeOffAmount = totalWriteOff;
						totalWriteOff = 0;
						// cpt_balance=cpt_balance-write_off_amount;
					}
				}

				if (paidAmout > 0 || writeOffAmount > 0) {

					ormPaymentSave = new ORMClaimPaymentSave();

					ormPaymentSave.setClaim_payments_id(db.IDGenerator("claim_payment", ormCash.getPractice_id()));
					ormPaymentSave.setClaim_procedures_id(objProc.getClaim_procedures_id());
					ormPaymentSave.setCharged_procedure(objProc.getProc_code());
					ormPaymentSave.setPaid_procedure(objProc.getProc_code());
					ormPaymentSave.setPayment_source("Patient");
					ormPaymentSave.setEntry_type("Previous Balance");
					ormPaymentSave.setEob_era_id(ormCash.getCash_register_id());
					ormPaymentSave.setEob_era_id_type("CASH_REGISTER");
					ormPaymentSave.setClaim_id(objProc.getClaim_id());
					ormPaymentSave.setPatient_id(ormCash.getPatient_id());
					ormPaymentSave.setPaid_amount(BigDecimal.valueOf(paidAmout));
					ormPaymentSave.setWriteoff_amount(BigDecimal.valueOf(writeOffAmount));
					ormPaymentSave.setPractice_id(ormCash.getPractice_id());

					if (GeneralOperations.isNotNullorEmpty(ormCash.getCheck_number())) {
						ormPaymentSave.setCheck_number(ormCash.getCheck_number());
						ormPaymentSave.setCheck_date(ormCash.getCheck_date());
					}

					ormPaymentSave.setClient_date_created(ormCash.getClient_date_created());
					ormPaymentSave.setClient_date_modified(ormCash.getClient_date_modified());
					ormPaymentSave.setCreated_user(ormCash.getCreated_user());
					ormPaymentSave.setModified_user(ormCash.getModified_user());
					ormPaymentSave.setSystem_ip(ormCash.getSystem_ip());

					ormPaymentSave.setDate_modified(serverDateTime);
					ormPaymentSave.setDate_created(serverDateTime);

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormPaymentSave));

					boolean isFoundInResponse = false;
					for (claimPostingResponse resp : lstResponse) {

						if (resp.getClaim_id().equals(objProc.getClaim_id())) {

							String paymentSummary = objProc.getProc_code() + ": Paid=$" + paidAmout + " W/O=$"
									+ writeOffAmount;

							resp.setPaid_amount(resp.getPaid_amount() + paidAmout);
							resp.setWrite_off_amount(resp.getWrite_off_amount() + writeOffAmount);
							resp.setPayment_summary(resp.getPayment_summary() + ", " + paymentSummary);

							isFoundInResponse = true;
						}
					}
					if (!isFoundInResponse) {

						String paymentSummary = objProc.getProc_code() + ": Paid=$" + paidAmout + " W/O=$"
								+ writeOffAmount;

						lstResponse.add(new claimPostingResponse(objProc.getClaim_id(), objProc.getDos(), paidAmout,
								writeOffAmount, paymentSummary));
					}

				}
			}
		}

		for (claimPostingResponse clmResp : lstResponse) {

			ORMClaimNotesSave ormClaimNotesSave = new ORMClaimNotesSave();

			ormClaimNotesSave.setNotes_id(db.IDGenerator("claim_notes", ormCash.getPractice_id()));

			ormClaimNotesSave.setDate_created(serverDateTime);
			ormClaimNotesSave.setDate_modified(serverDateTime);

			ormClaimNotesSave.setClient_date_created(ormCash.getClient_date_modified());
			ormClaimNotesSave.setClient_date_modified(ormCash.getClient_date_modified());

			ormClaimNotesSave.setCreated_user(ormCash.getCreated_user());
			ormClaimNotesSave.setModified_user(ormCash.getCreated_user());

			ormClaimNotesSave.setPatient_id(ormCash.getPatient_id());
			ormClaimNotesSave.setClaim_id(clmResp.getClaim_id());

			ormClaimNotesSave.setPractice_id(ormCash.getPractice_id());

			ormClaimNotesSave.setNotes("Patient Previous Balance Posted. " + clmResp.getPayment_summary());

			ormClaimNotesSave.setIs_auto(true);

			lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormClaimNotesSave));

		}

		if (lstEntityWrapper != null && lstEntityWrapper.size() > 0) {

			result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result > 0) {

				billingGeneral.AutoResolveCashRegister(ormCash.getCash_register_id(), ormCash.getCreated_user());

				for (claimPostingResponse clmResp : lstResponse) {

					billingGeneral.UpdateClaimBalance(clmResp.getClaim_id(), ormCash.getClient_date_modified(),
							ormCash.getModified_user(), ormCash.getSystem_ip());
				}

			}
		}

		return lstResponse;

	}
}
