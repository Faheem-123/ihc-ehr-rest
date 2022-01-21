package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ORMGetPatientElibility;
import com.ihc.ehr.model.ORMGetPatientInfoForEligibility;
import com.ihc.ehr.model.ORMGetPatientPayer;
import com.ihc.ehr.model.ORMProvider_Eligibility;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.PatientEligibility;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

import ediwebservice.EDIWebService;
import ediwebservice.ORMEligibilityDetail;

@Repository
public class EligibilityDOAImpl implements EligibilityDOA {

	@Autowired
	private DBOperations db;

	@Override
	public ServiceResponse<PatientEligibility> getPatientEligibility(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		boolean checkLive = false;
		Boolean isError = false;
		
		Long PracticeId = (long) 0;
		Long patientId = (long) 0;
		String insuranceType = "";
		String PolicyNo = "";
		Long id = (long) 0;
		String idType = "";
		String dos = DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.yyyyMMdd);

		PatientEligibility objPatElibility = null;
		List<ORMGetPatientElibility> lstElibility = null;

		PracticeId = searchCriteria.getPractice_id();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "check_live":
					if (pram.getValue().equals("true")) {
						checkLive = true;
					}
					break;
				case "patient_id":
					patientId = Long.parseLong(pram.getValue());
					break;
				case "insurance_type":
					insuranceType = pram.getValue();
					break;
				case "id":
					id = Long.parseLong(pram.getValue());
					break;
				case "id_type":
					idType = pram.getValue();
					break;
				case "dos":
					dos = pram.getValue();
					break;

				default:
					break;
				}
			}
		}
		// List<SpParameters> lstParam = new ArrayList<>();
		// if (searchCriteria.getCriteria().equalsIgnoreCase("true")) {
		// checkLive = true;
		// }
		

		ServiceResponse<PatientEligibility> resp = new ServiceResponse<PatientEligibility>();
		//Check if Patient eligiblity is already checked then return already get response
		lstElibility = getValidatePatientEligibility(patientId.toString(),insuranceType,dos,id.toString(),idType);
		if (lstElibility != null && lstElibility.size() > 0 && GeneralOperations.isNotNullorEmpty(lstElibility.get(0).getElig_response())) 
		{
			objPatElibility = parseElibilityFromDB(lstElibility.get(0));
			objPatElibility.setIs_error(false);
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			List<PatientEligibility> lst = new ArrayList<PatientEligibility>();
			lst.add(objPatElibility);
			resp.setResponse_list(lst);
			return resp;
		}
		
		if (checkLive) {

			
			ORMEligibilityDetail ormEligibilityDetail = new ORMEligibilityDetail();
			ORMGetPatientInfoForEligibility patientInfo;
			ormEligibilityDetail.setIns_type(insuranceType);
			ormEligibilityDetail.setPatient_id(patientId.toString());
			if (idType.equals("appointment")) {
				ormEligibilityDetail.setAppointment_id(id.toString());
			}

			ORMProvider_Eligibility eligibiltyConfig = getProviderEligiblityDetails(searchCriteria.getPractice_id());

			if (eligibiltyConfig != null) {
				ormEligibilityDetail.setElig_user(eligibiltyConfig.getUser());
				ormEligibilityDetail.setElig_password(eligibiltyConfig.getPassword());
				ormEligibilityDetail.setProvider_fname(eligibiltyConfig.getProvider_fname());
				ormEligibilityDetail.setProvider_lname(eligibiltyConfig.getProvider_lname());
				ormEligibilityDetail.setProvider_npi(eligibiltyConfig.getProvider_npi());
				ormEligibilityDetail.setElig_password(eligibiltyConfig.getPassword());
			} else {
				isError = true;
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("Provider Elibiglity Information not found.");
			}

			if (!isError) {
				patientInfo = getPatientInfoForEligiblity(patientId);

				if (patientInfo != null) {
					ormEligibilityDetail.setInsured_fname(patientInfo.getFirst_name());
					ormEligibilityDetail.setInsured_lname(patientInfo.getLast_name());
					ormEligibilityDetail.setInsured_ssn(patientInfo.getSsn());
					ormEligibilityDetail.setInsured_state(patientInfo.getState());
					ormEligibilityDetail.setInsured_dob(patientInfo.getDob());
					ormEligibilityDetail.setDos(dos);

				} else {
					isError = true;
					resp.setStatus(ServiceResponseStatus.ERROR);
					resp.setResponse("Patient Info not found.");
				}
			}

			if (!isError) {

				ORMGetPatientPayer payerInfo = getPatientPayerInfo(PracticeId, patientId, insuranceType);

				if (payerInfo != null) {

					if (GeneralOperations.isNotNullorEmpty(payerInfo.getId())
							&& GeneralOperations.isNotNullorEmpty(payerInfo.getName())) {
						ormEligibilityDetail.setEdipayer_id(payerInfo.getId().toString());
						ormEligibilityDetail.setInsurance_num(payerInfo.getName());
						PolicyNo = payerInfo.getName();
						// return "Payer or patient policy number not found.";
					} else {
						isError = true;
						resp.setStatus(ServiceResponseStatus.ERROR);
						resp.setResponse("Payer or patient policy number not found.");
					}

				} else {
					isError = true;
					resp.setStatus(ServiceResponseStatus.ERROR);
					resp.setResponse("Payer or patient policy number not found.");
				}

			}

			// ORMEligibilityDetail ormEligibilityDetail =
			// wrapperEligiblityCriteria.getOrmEligibilityDetail();
			if (isError == false) {

				String Resp = EDIWebService.findEligibility(ormEligibilityDetail);
//				System.out.println(Resp);
//				System.out.println(Resp.split("~")[0]);

				if (GeneralOperations.isNullorEmpty(Resp) || Resp.split("~")[0].equalsIgnoreCase("ERROR")) {

					// if (Resp == null || "".equals(Resp) || Resp.startsWith("ERROR")) {
					UpdateInsurance(ormEligibilityDetail.getPatient_id(), "", ormEligibilityDetail.getIns_type());
					UpdateTodaysAppointment(ormEligibilityDetail.getPatient_id(), "");
					// return "Eligibility Not Found.";
					isError=true;
					resp.setStatus(ServiceResponseStatus.ERROR);					
					resp.setResponse("An Error Occured while getting Eligibility.");
					
					if(GeneralOperations.isNotNullorEmpty(Resp)) {
						
						objPatElibility = new PatientEligibility();
						objPatElibility.setIs_error(true);						
						
						String[] str = Resp.split("~");
						if (str.length > 1) {
							objPatElibility.setError_message(str[1]);
						}						
						if (str.length > 2) {
							List<String> lstErrors = Arrays.asList(str[2].split("\\*"));
							objPatElibility.setLst_errors(lstErrors);
						}
						
						List<PatientEligibility> lst = new ArrayList<PatientEligibility>();
						lst.add(objPatElibility);
						resp.setResponse_list(lst);
					}

				} else {

					Resp=Resp + "~" + PolicyNo;
					//System.out.println(Resp);
					if (GeneralOperations.isNotNullorEmpty(ormEligibilityDetail.getAppointment_id())
							&& ormEligibilityDetail.getIns_type().toUpperCase().equals("PRIMARY")) {
						UpdateAppointment(ormEligibilityDetail.getAppointment_id(), Resp);
					} else if (GeneralOperations.isNullorEmpty(ormEligibilityDetail.getAppointment_id())
							&& ormEligibilityDetail.getIns_type().toUpperCase().equals("PRIMARY")) {
						UpdateTodaysAppointment(ormEligibilityDetail.getPatient_id(), Resp);
					}
					UpdateInsurance(ormEligibilityDetail.getPatient_id(), Resp, ormEligibilityDetail.getIns_type());
				}

			}

		}

		if (isError == false) {

			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", PracticeId.toString(), String.class, ParameterMode.IN));

			lstParam.add(new SpParameters("id", id.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("type", idType.toString(), String.class, ParameterMode.IN));

			lstElibility = db.getStoreProcedureData("spGetPatientEligibililty", ORMGetPatientElibility.class, lstParam);

			if (lstElibility != null && lstElibility.size() > 0 && GeneralOperations.isNotNullorEmpty(lstElibility.get(0).getElig_response())) {
				objPatElibility = parseElibilityFromDB(lstElibility.get(0));
				objPatElibility.setIs_error(false);
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				List<PatientEligibility> lst = new ArrayList<PatientEligibility>();
				lst.add(objPatElibility);
				resp.setResponse_list(lst);
			} else {				
				resp.setResponse("Eligibility Information Not Found.");
				resp.setStatus(ServiceResponseStatus.NOT_FOUND);
				// resp.setResponse("Eligibility Information Not Found.");
			}
		}
		// resp.setStatus(ServiceResponseStatus.SUCCESS);
		
		return resp;
	}

	private PatientEligibility parseElibilityFromDB(ORMGetPatientElibility ormGetPatientElibility) {

		PatientEligibility objPatElibility = new PatientEligibility();

		objPatElibility.setPatient_id(ormGetPatientElibility.getPatient_id());
		objPatElibility.setEhr_patient_name(ormGetPatientElibility.getPatient_name().toUpperCase());

		if (GeneralOperations.isNotNullorEmpty(ormGetPatientElibility.getElig_date())) {
			objPatElibility.setValidattion_date(ormGetPatientElibility.getElig_date());
		}

		if (GeneralOperations.isNotNullorEmpty(ormGetPatientElibility.getElig_status())) {
			objPatElibility.setEligibility_status(ormGetPatientElibility.getElig_status());
		}

		if (GeneralOperations.isNotNullorEmpty(ormGetPatientElibility.getElig_response())) {

			String lstElibililtyDetail[] = ormGetPatientElibility.getElig_response().split("~");

			///////

			objPatElibility.setInsurance_patient_name(lstElibililtyDetail[0].toUpperCase());
			objPatElibility.setInsurance_name(lstElibililtyDetail[1]);
			objPatElibility.setEligibility_status_description(lstElibililtyDetail[2]);

			
			objPatElibility.setInsurance_policy_number(lstElibililtyDetail[6].toUpperCase());
			objPatElibility.setEhr_policy_number(lstElibililtyDetail[8].toUpperCase());

			if (lstElibililtyDetail[3] != null && lstElibililtyDetail[3] != "") {

				String[] benefits = lstElibililtyDetail[3].toString().split("\\*");

				List<String> benefitsList = Arrays.asList(benefits);

				objPatElibility.setLst_benefits_coverage(benefitsList);
			}

			if (lstElibililtyDetail[4] != null && !lstElibililtyDetail[4].isEmpty()) {

				String[] copayments = lstElibililtyDetail[4].toString().split("<BR>");

				List<String> copaymentsLisr = Arrays.asList(copayments);

				objPatElibility.setLst_co_payment(copaymentsLisr);
			}

			if (lstElibililtyDetail[5] != null && !lstElibililtyDetail[5].isEmpty()) {

				String[] otherInfo = lstElibililtyDetail[5].toString().split("<BR>");

				List<String> otherInfoList = Arrays.asList(otherInfo);

				objPatElibility.setLst_other_info(otherInfoList);
			}

			if (lstElibililtyDetail[7] != null && !lstElibililtyDetail[7].isEmpty()) {

				String[] deductable = lstElibililtyDetail[7].toString().split("<BR>");

				List<String> deductableList = Arrays.asList(deductable);

				objPatElibility.setLst_deductable(deductableList);
			}
		}

		return objPatElibility;
	}
	private void UpdateClaimInsurance(String claimInsuranceId, String ElgResp, String Type) {
		String EligStatus = "U";
		if (!ElgResp.equals("")) {
			if (ElgResp.contains("Active")) {
				EligStatus = "A";
			} else if (ElgResp.contains("Inactive")) {
				EligStatus = "I";
			}
		}
		String strQuery = "Update claim_insurance set elig_status='" + EligStatus
				+ "',elig_date=GetDate(),elig_response='" + ElgResp.replaceAll("'", "`") + "'" + " Where claiminsurance_id='"
				+ claimInsuranceId + "'  and isnull(deleted,0)<>1";
		db.ExecuteUpdateQuery(strQuery);
	}
	private void UpdateInsurance(String patientid, String ElgResp, String Type) {
		String EligStatus = "U";
		if (!ElgResp.equals("")) {
			if (ElgResp.contains("Active")) {
				EligStatus = "A";
			} else if (ElgResp.contains("Inactive")) {
				EligStatus = "I";
			}
		}
		String strQuery = "Update patient_insurance set elig_status='" + EligStatus
				+ "',elig_date=GetDate(),elig_response='" + ElgResp.replaceAll("'", "`") + "'" + " Where patient_id='"
				+ patientid + "' and insurace_type='" + Type + "' and isnull(deleted,0)<>1";
		db.ExecuteUpdateQuery(strQuery);
	}

	private void UpdateTodaysAppointment(String PatientId, String ElgResp) {
		String EligStatus = "U";
		if (!ElgResp.equals("")) {
			if (ElgResp.contains("Active")) {
				EligStatus = "A";
			} else if (ElgResp.contains("Inactive")) {
				EligStatus = "I";
			}
		}
		db.ExecuteUpdateQuery("Update appointment set elig_status='" + EligStatus
				+ "',elig_date=GetDate(),elig_response='" + ElgResp.replaceAll("'", "`") + "' " + "Where patient_id='"
				+ PatientId
				+ "' and convert(varchar,appointment_date,101)=convert(varchar,getdate(),101) and isnull(deleted,0)<>1");
	}

	private void UpdateAppointment(String appointment_Id, String ElgResp) {
		String EligStatus = "U";
		if (!ElgResp.equals("")) {
			if (ElgResp.contains("Active")) {
				EligStatus = "A";
			} else if (ElgResp.contains("Inactive")) {
				EligStatus = "I";
			}
		}
		db.ExecuteUpdateQuery(
				"Update appointment set elig_status='" + EligStatus + "',elig_date=GetDate(),elig_response='"
						+ ElgResp.replaceAll("'", "`") + "'" + " Where appointment_id='" + appointment_Id + "'");
	}

	private ORMProvider_Eligibility getProviderEligiblityDetails(Long practiceId) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		List<ORMProvider_Eligibility> lst = db.getStoreProcedureData("spGetProviderEligibilityInfo",
				ORMProvider_Eligibility.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}

	}

	private ORMGetPatientInfoForEligibility getPatientInfoForEligiblity(Long patientId) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		List<ORMGetPatientInfoForEligibility> lst = db.getStoreProcedureData("spGetPatientInfoForEligibility",
				ORMGetPatientInfoForEligibility.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}

	}

	private ORMGetPatientPayer getPatientPayerInfo(Long practiceId, Long patientId, String insuranceType) {

		List<SpParameters> lstPar = new ArrayList<>();
		lstPar.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		lstPar.add(new SpParameters("ins_type", insuranceType.toString(), String.class, ParameterMode.IN));
		lstPar.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientPayer> lst = db.getStoreProcedureData("spGetPatientPayers", ORMGetPatientPayer.class, lstPar);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}

	}
	/*
	private ORMGetPatientPayer getClaimPayerInfo(Long practiceId, Long claimId, String insuranceType) {

		List<SpParameters> lstPar = new ArrayList<>();
		lstPar.add(new SpParameters("claim_id", claimId.toString(), String.class, ParameterMode.IN));
		lstPar.add(new SpParameters("ins_type", insuranceType.toString(), String.class, ParameterMode.IN));
		lstPar.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientPayer> lst = db.getStoreProcedureData("spGetClaimPayers", ORMGetPatientPayer.class, lstPar);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}

	}
	*/
	private ORMGetPatientPayer getClaimInsurancePayerInfo(String insurance_id,  String practiceId) {

		List<SpParameters> lstPar = new ArrayList<>();
		lstPar.add(new SpParameters("id", insurance_id.toString(), String.class, ParameterMode.IN));
		lstPar.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientPayer> lst = db.getStoreProcedureData("spGetClaimInusrancePayer", ORMGetPatientPayer.class, lstPar);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}

	}
	private ORMGetPatientPayer getInsurancePayerInfo(String insurance_id,  String practiceId) {

		List<SpParameters> lstPar = new ArrayList<>();
		lstPar.add(new SpParameters("id", insurance_id.toString(), String.class, ParameterMode.IN));
		lstPar.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientPayer> lst = db.getStoreProcedureData("spGetInusrancePayer", ORMGetPatientPayer.class, lstPar);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}

	}
	
	@Override
	public ServiceResponse<PatientEligibility> getClaimElibility(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		boolean checkLive = false;
		Boolean isError = false;
		
		Long PracticeId = (long) 0;
		Long patientId = (long) 0;
		Long claimId = (long) 0;
		String insuranceType = "";
		String PolicyNo = "";
		Long id = (long) 0;
		String idType = "";
		String dos = DateTimeUtil.getFormatedCurrentDate(DateFormatEnum.yyyyMMdd);

		PatientEligibility objPatElibility = null;
		List<ORMGetPatientElibility> lstElibility = null;

		PracticeId = searchCriteria.getPractice_id();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "check_live":
					if (pram.getValue().equals("true")) {
						checkLive = true;
					}
					break;
				case "patient_id":
					patientId = Long.parseLong(pram.getValue());
					break;
				case "insurance_type":
					insuranceType = pram.getValue();
					break;
				case "id":
					id = Long.parseLong(pram.getValue());
					break;
				case "id_type":
					idType = pram.getValue();
					break;
				case "dos":
					dos = pram.getValue();
					break;
				case "claim_id":
					claimId = Long.parseLong(pram.getValue());
					break;
				case "policy_no":
					PolicyNo = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		ServiceResponse<PatientEligibility> resp = new ServiceResponse<PatientEligibility>();
		ORMGetPatientInfoForEligibility patientInfo=new ORMGetPatientInfoForEligibility();
		String Resp="";
		if (checkLive) {

			
			ORMEligibilityDetail ormEligibilityDetail = new ORMEligibilityDetail();
			
			ormEligibilityDetail.setIns_type(insuranceType);
			ormEligibilityDetail.setPatient_id(patientId.toString());
			if (idType.equals("appointment")) {
				ormEligibilityDetail.setAppointment_id(id.toString());
			}

			ORMProvider_Eligibility eligibiltyConfig = getProviderEligiblityDetails(searchCriteria.getPractice_id());

			if (eligibiltyConfig != null) {
				ormEligibilityDetail.setElig_user(eligibiltyConfig.getUser());
				ormEligibilityDetail.setElig_password(eligibiltyConfig.getPassword());
				ormEligibilityDetail.setProvider_fname(eligibiltyConfig.getProvider_fname());
				ormEligibilityDetail.setProvider_lname(eligibiltyConfig.getProvider_lname());
				ormEligibilityDetail.setProvider_npi(eligibiltyConfig.getProvider_npi());
				ormEligibilityDetail.setElig_password(eligibiltyConfig.getPassword());
			} else {
				isError = true;
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("Provider Elibiglity Information not found.");
			}

			if (!isError) {
				patientInfo = getPatientInfoForEligiblity(patientId);

				if (patientInfo != null) {
					ormEligibilityDetail.setInsured_fname(patientInfo.getFirst_name());
					ormEligibilityDetail.setInsured_lname(patientInfo.getLast_name());
					ormEligibilityDetail.setInsured_ssn(patientInfo.getSsn());
					ormEligibilityDetail.setInsured_state(patientInfo.getState());
					ormEligibilityDetail.setInsured_dob(patientInfo.getDob());
					ormEligibilityDetail.setDos(dos);

				} else {
					isError = true;
					resp.setStatus(ServiceResponseStatus.ERROR);
					resp.setResponse("Patient Info not found.");
				}
			}

			if (!isError) {
				ORMGetPatientPayer payerInfo;
					if(claimId>0)
						payerInfo = getClaimInsurancePayerInfo(id.toString(), PracticeId.toString());//Edit claim
					else
						payerInfo = getInsurancePayerInfo(id.toString(), PracticeId.toString());//new claim
					
					if (payerInfo != null) {
						if (GeneralOperations.isNotNullorEmpty(payerInfo.getId())) {
							ormEligibilityDetail.setEdipayer_id(payerInfo.getId().toString());
							ormEligibilityDetail.setInsurance_num(PolicyNo);
							//PolicyNo = payerInfo.getName();
							// return "Payer or patient policy number not found.";
						} else {
							isError = true;
							resp.setStatus(ServiceResponseStatus.ERROR);
							resp.setResponse("Payer or patient policy number not found.");
						}

					} else {
						isError = true;
						resp.setStatus(ServiceResponseStatus.ERROR);
						resp.setResponse("Payer or patient policy number not found.");
					}
			}

			if (isError == false) {

				Resp = EDIWebService.findEligibility(ormEligibilityDetail);
				//System.out.println(Resp);
				//System.out.println(Resp.split("~")[0]);

				if (GeneralOperations.isNullorEmpty(Resp) || Resp.split("~")[0].equalsIgnoreCase("ERROR")) {

					// if (Resp == null || "".equals(Resp) || Resp.startsWith("ERROR")) {
					//UpdateInsurance(ormEligibilityDetail.getPatient_id(), "", ormEligibilityDetail.getIns_type());
					//UpdateTodaysAppointment(ormEligibilityDetail.getPatient_id(), "");
					if(claimId>0)
						UpdateClaimInsurance(id.toString(),"", ormEligibilityDetail.getIns_type());
					// return "Eligibility Not Found.";
					isError=true;
					resp.setStatus(ServiceResponseStatus.ERROR);					
					resp.setResponse("An Error Occured while getting Eligibility.");
					
					if(GeneralOperations.isNotNullorEmpty(Resp)) {
						
						objPatElibility = new PatientEligibility();
						objPatElibility.setIs_error(true);						
						
						String[] str = Resp.split("~");
						if (str.length > 1) {
							objPatElibility.setError_message(str[1]);
						}						
						if (str.length > 2) {
							List<String> lstErrors = Arrays.asList(str[2].split("\\*"));
							objPatElibility.setLst_errors(lstErrors);
						}
						
						List<PatientEligibility> lst = new ArrayList<PatientEligibility>();
						lst.add(objPatElibility);
						resp.setResponse_list(lst);
					}

				} else {

					Resp=Resp + "~" + PolicyNo;
					//System.out.println(Resp);
					
//					if (GeneralOperations.isNotNullorEmpty(ormEligibilityDetail.getAppointment_id())
//							&& ormEligibilityDetail.getIns_type().toUpperCase().equals("PRIMARY")) {
//						UpdateAppointment(ormEligibilityDetail.getAppointment_id(), Resp);
//					} else if (GeneralOperations.isNullorEmpty(ormEligibilityDetail.getAppointment_id())
//							&& ormEligibilityDetail.getIns_type().toUpperCase().equals("PRIMARY")) {
//						UpdateTodaysAppointment(ormEligibilityDetail.getPatient_id(), Resp);
//					}
//					UpdateInsurance(ormEligibilityDetail.getPatient_id(), Resp, ormEligibilityDetail.getIns_type());
					if(claimId>0)
						UpdateClaimInsurance(id.toString(),Resp, ormEligibilityDetail.getIns_type());
				}

			}

		}
		else
		{
			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("practice_id", PracticeId.toString(), String.class, ParameterMode.IN));

			lstParam.add(new SpParameters("id", id.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("type", idType.toString(), String.class, ParameterMode.IN));

			lstElibility = db.getStoreProcedureData("spGetPatientEligibililty", ORMGetPatientElibility.class, lstParam);

			if (lstElibility != null && lstElibility.size() > 0 && GeneralOperations.isNotNullorEmpty(lstElibility.get(0).getElig_response())) {
				objPatElibility = parseElibilityFromDB(lstElibility.get(0));
				objPatElibility.setIs_error(false);
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				List<PatientEligibility> lst = new ArrayList<PatientEligibility>();
				lst.add(objPatElibility);
				resp.setResponse_list(lst);
			} else {				
				resp.setResponse("Eligibility Information Not Found.");
				resp.setStatus(ServiceResponseStatus.NOT_FOUND);
			}
			return resp;
		}

		if (isError == false) {

//			List<SpParameters> lstParam = new ArrayList<>();
//			lstParam.add(new SpParameters("practice_id", PracticeId.toString(), String.class, ParameterMode.IN));
//
//			lstParam.add(new SpParameters("id", id.toString(), String.class, ParameterMode.IN));
//			lstParam.add(new SpParameters("type", idType.toString(), String.class, ParameterMode.IN));
//
//			lstElibility = db.getStoreProcedureData("spGetPatientEligibililty", ORMGetPatientElibility.class, lstParam);

			//if (lstElibility != null && lstElibility.size() > 0 && GeneralOperations.isNotNullorEmpty(lstElibility.get(0).getElig_response())) 
			{
				
				ORMGetPatientElibility objPateElig=new ORMGetPatientElibility();
				objPateElig.setPatient_id(patientId);
				objPateElig.setPatient_name(patientInfo.getLast_name()+", "+patientInfo.getFirst_name());
				objPateElig.setElig_date(dos);
				if (!Resp.equals("")) {
					if (Resp.contains("Active")) {
						objPateElig.setElig_status("A");
					} else if (Resp.contains("Inactive")) {
						objPateElig.setElig_status("I");
					}
				}
				objPateElig.setElig_response(Resp);
				
				objPatElibility = parseElibilityFromDB(objPateElig);
				
				objPatElibility.setIs_error(false);
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResult(Resp);
				List<PatientEligibility> lst = new ArrayList<PatientEligibility>();
				lst.add(objPatElibility);
				resp.setResponse_list(lst);
			} 
			//else {				
				//resp.setResponse("Eligibility Information Not Found.");
				//resp.setStatus(ServiceResponseStatus.NOT_FOUND);
				// resp.setResponse("Eligibility Information Not Found.");
			//}
		}
		// resp.setStatus(ServiceResponseStatus.SUCCESS);
		
		return resp;
	}
	private List<ORMGetPatientElibility> getValidatePatientEligibility(String patient_id,String ins_type,String dos,String id,String type ) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("ins_type", ins_type.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("dos", dos.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("id", id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("type", type.toString(), String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spValidatePatientEligibility",ORMGetPatientElibility.class, lstParam);
	}

}
