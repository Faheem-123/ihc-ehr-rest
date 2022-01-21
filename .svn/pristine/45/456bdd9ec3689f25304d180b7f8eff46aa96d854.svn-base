package com.ihc.ehr.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ORMGetChartReferral;
import com.ihc.ehr.model.ORMGetChartReferralDetail;
import com.ihc.ehr.model.ORMGetPatientReferral;
import com.ihc.ehr.model.ORMGetReferralEmailDetail;
import com.ihc.ehr.model.ORMGetReferralFaxDetail;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientReferral;
import com.ihc.ehr.model.ORMReferralChartSummary;
import com.ihc.ehr.model.ORMReferralLabSummary;
import com.ihc.ehr.model.ORMReferralStaffNotes;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMgetLabOrderResultsSummary;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.PDFOperations;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
//
@Repository
public class ReferralDAOImpl implements ReferralDAO {

	@Autowired
	private DBOperations db;

	@Override
	public List<ORMGetPatientReferral> getPatientReferrals(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			String criteria1 = " and pr.practice_id='" + searchCriteria.getPractice_id().toString() + "'";
			String criteria12 = "";
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				System.out.println(
						"param:" + pram.getName() + "    Value:" + pram.getValue() + "    Option:" + pram.getOption());

				switch (pram.getName().toUpperCase()) {
				case "PATIENT_ID":
					criteria1 += " and pr.patient_id=" + pram.getValue();
					break;
				case "REFERRAL_REQUEST":
					criteria1 += " and isnull(pr.referral_request,0)=1";// +pram.getValue();
					break;
				case "DATE_FROM":
					criteria1 += " and convert(date,pr.date_created) >= convert(date,'" + pram.getValue() + "') ";// +pram.getValue();
					break;
				case "DATE_TO":
					criteria1 += " and convert(date,pr.date_created) <= convert(date,'" + pram.getValue() + "') ";// +pram.getValue();
					break;
				case "PROVIDER_ID":
					criteria1 += " and isnull(pr.provider_id,0)='" + pram.getValue() + "' ";
					break;
				case "LOCATION_ID":
					criteria1 += " and isnull(pr.location_id,0)='" + pram.getValue() + "' ";
					break;
				case "STATUS":
					criteria1 += " and isnull(pr.referral_status,0)='" + pram.getValue() + "' ";
					break;
				case "CONSULT_TYPE":
					criteria1 += " and isnull(pr.consult_type_id,0)='" + pram.getValue() + "' ";
					break;
				case "HIGH_IMPORTANT":
					if (pram.getValue().equalsIgnoreCase("1")) {
						criteria1 += " and isnull(pr.high_importance,0)=1 ";
					}
					break;
				default:
					break;
				}

				System.out.println("criteria1" + criteria1);

			}
			lstParam.add(new SpParameters("criteria1", criteria1, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("criteria12", criteria12, String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData("spGetPatientReferrals", ORMGetPatientReferral.class, lstParam);
	}
	
	@Override
	public List<ORMGetChartReferral> getChartReferrals(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetChartReferral> lstHM = db.getStoreProcedureData("spGetChartReferrals_View",
				ORMGetChartReferral.class, lstParam);

		return lstHM;
	}

	@Override
	public List<ORMThreeColum> getConsultType(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("Practice_ID", practice_id.toString(), String.class, ParameterMode.IN));

		List<ORMThreeColum> lstHM = db.getStoreProcedureData("spGetConsultType", ORMThreeColum.class, lstParam);

		return lstHM;
	}

	public Boolean moveReferralFile(String source_path, String destination_path) {
		Boolean result = false;
		try {
			Path source = Paths.get(source_path);
			Path destination = Paths.get(destination_path);
			Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Long savePatientReferralRequest(ORMPatientReferral obj) {
		// TODO Auto-generated method stub

		if (obj.getReferral_id() != null && obj.getReferral_id() != 0) {
			obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return obj.getReferral_id();
			else
				return (long) 0;
		} else {
			obj.setReferral_id(db.IDGenerator("patient_referral", obj.getPractice_id()));
			obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			System.out.println("savePatientReferralRequestDAO: " + obj.toString());
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return obj.getReferral_id();
			else
				return (long) 0;
		}
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePatientReferral(ORMPatientReferral obj, String path,
			String source_file_path) {
		// TODO Auto-generated method stub
		String generated_id = "";
		int result = 0;

		if (GeneralOperations.isNullorEmpty(obj.getReferral_id())) {
			generated_id = db.IDGenerator("patient_referral", obj.getPractice_id()).toString();
		} else {
			generated_id = obj.getReferral_id().toString();
		}
		String file_name = generated_id + GeneralOperations.GetDatetimeFileName() + ".pdf";
		String destination_path = GeneralOperations.checkPathYearMonthWise(obj.getPractice_id().toString(), path,
				"PatientDocuments") + "\\" + file_name;

		String file_path = "";
		if (moveReferralFile(source_file_path,
				path + obj.getPractice_id().toString() + "\\PatientDocuments\\" + destination_path) == true) {
			String[] splitedPath = destination_path.split("\\\\");
			file_path = splitedPath[splitedPath.length - 4] + "\\" + splitedPath[splitedPath.length - 3] + "\\"
					+ splitedPath[splitedPath.length - 2] + "\\" + splitedPath[splitedPath.length - 1];

			if (GeneralOperations.isNotNullorEmpty(file_path)) {
				obj.setReferral_path(file_path);
			}
		}

		if (obj.getReferral_id() != null && obj.getReferral_id() != 0) {
			obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(obj, Operation.EDIT);
		} else {
			obj.setReferral_id(db.IDGenerator("patient_referral", obj.getPractice_id()));
			obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			System.out.println("savePatientReferralRequestDAO: " + obj.toString());
			result = db.SaveEntity(obj, Operation.ADD);
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);

			List<ORMKeyValue> response_list = new ArrayList<>();
			response_list.add(new ORMKeyValue("doc_link", obj.getReferral_path()));
			resp.setResponse_list(response_list);

			resp.setResponse(obj.getReferral_id().toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);

			List<ORMKeyValue> response_list = new ArrayList<>();
			response_list.add(new ORMKeyValue("doc_link", obj.getReferral_path()));
			resp.setResponse_list(response_list);

			resp.setResponse(obj.getReferral_id().toString());
		}
		return resp;
	}

	@Override
	public int UpdateReferralRequestStatus(ORMReferralStaffNotes obj, String Status) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_created(DateTimeUtil.getCurrentDateTime());
		if (db.SaveEntity(obj, Operation.ADD) > 0) {
			String strQuery = "update patient_referral set referral_status='" + Status + "',client_date_modified='"
					+ obj.getClient_date_created() + "',date_modified=getdate(),modified_user='" + obj.getCreated_user()
					+ "' where referral_id='" + obj.getReferral_id() + "'";
			result = db.ExecuteUpdateQuery(strQuery);
		}
		return result;
	}

	@Override
	public List<ORMReferralLabSummary> getPatientLabOrders(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		List<ORMReferralLabSummary> lstHM = db.getStoreProcedureData("spGetPatientOrdersSummary",
				ORMReferralLabSummary.class, lstParam);

		return lstHM;
	}

	@Override
	public List<ORMReferralChartSummary> getReferralChartSummary(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		List<ORMReferralChartSummary> lstHM = db.getStoreProcedureData("spGetReferralChartSummary",
				ORMReferralChartSummary.class, lstParam);

		return lstHM;
	}

	@Override
	public List<ORMGetReferralFaxDetail> getReferralsFaxDetails(String referral_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("referral_id", referral_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetReferralFaxDetail> lstHM = db.getStoreProcedureData("spGetReferralFaxDetails",
				ORMGetReferralFaxDetail.class, lstParam);

		return lstHM;
	}

	@Override
	public List<ORMGetReferralEmailDetail> getReferralsEmailDetails(String referral_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("referral_id", referral_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetReferralEmailDetail> lstHM = db.getStoreProcedureData("spGetReferralEmailDetails",
				ORMGetReferralEmailDetail.class, lstParam);

		return lstHM;
	}

	@Override
	public ORMGetChartReferralDetail getChartReferralsDetail(String referral_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("referral_id", referral_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetChartReferralDetail> lstHM = db.getStoreProcedureData("spGetChartReferrals_Detail",
				ORMGetChartReferralDetail.class, lstParam);
		if (lstHM != null && !lstHM.isEmpty())
			return lstHM.get(0);
		else
			return null;

	}

	@Override
	public List<ORMgetLabOrderResultsSummary> getPatientOrderResults(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			String criteria1 = "";
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				System.out.println(
						"param:" + pram.getName() + "    Value:" + pram.getValue() + "    Option:" + pram.getOption());
				if (pram.getName().equals("criteria")) {
					criteria1 += " and po.order_id in (" + pram.getValue() + ")";
				}
			}
			lstParam.add(new SpParameters("criteria", criteria1, String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData("spGetLabOrderResultsSummary", ORMgetLabOrderResultsSummary.class, lstParam);
	}

	@Override
	public String GenerateTempLetter(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String doc_url = "";
		try {

			PDFOperations objPDF = new PDFOperations();
			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				String path = "";
				String FooterText = "";
				String strHTMLString = "";
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
					if (pram.getName().equals("path")) {
						path = pram.getValue();
					} else if (pram.getName().equals("footer")) {
						FooterText = pram.getValue();
					} else if (pram.getName().equals("html")) {
						strHTMLString = pram.getValue();
					}
				}

				String f_name = searchCriteria.getPractice_id() + GeneralOperations.GetDatetimeFileName() + ".pdf";
				String full_file_path = GeneralOperations.checkPathYearMonthWise(
						searchCriteria.getPractice_id().toString(), path, "PatientDocuments\\temp");
				System.out.println("Full PDF Path:" + full_file_path);
				// String file_name=practice_id+GenericOperations.getDatetimeFileName()+".pdf";

				if (objPDF.create_pdf_fromHTMLStream(strHTMLString, path + searchCriteria.getPractice_id().toString()
						+ "\\" + "PatientDocuments\\temp\\" + full_file_path, f_name, FooterText)) {
					System.out.println("PDF File Created:-" + full_file_path);
					String[] splitedPath = full_file_path.split("\\\\");
					doc_url = splitedPath[splitedPath.length - 3] + "\\" + splitedPath[splitedPath.length - 2] + "\\"
							+ splitedPath[splitedPath.length - 1] + "\\" + f_name;
					System.out.println("Retun Path:-" + doc_url);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc_url;
	}

}
