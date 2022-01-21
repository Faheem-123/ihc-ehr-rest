package com.ihc.ehr.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.*;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.newCrop.newCropPreproduction;

//import scala.annotation.meta.setter;

@Repository
public class EncounterDAOImpl implements EncounterDAO {

	@Autowired
	private DBOperations db;

	@Value("${ihc.common.api.endpiont}")
	private String ihcCommonApiEndpiont;

	@Override
	public List<ORMGetChartSummary> getChartSummary(String patient_id) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetChartSummary", ORMGetChartSummary.class, lstParam);

	}

	@Override
	public List<ORMGetAppointmentDate> getAppointmentDates(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetAppointmentDates", ORMGetAppointmentDate.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> createNewChart(ORMPatientChart obj) {
		// TODO Auto-generated method stub
		int result = 0;

		long chart_id = (long) 0;

		chart_id = checkIfEncounterAlreadyExists(obj.getPatient_id(), obj.getAppointment_id(), obj.getLocation_id(),
				obj.getProvider_id(), obj.getVisit_date());

		if (chart_id == 0) {
			chart_id = db.IDGenerator("patient_chart", Long.parseLong(obj.getPractice_id()));
			obj.setChart_id(chart_id);
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (db.SaveEntity(obj, Operation.ADD) > 0) {
				if (obj.getChart_id() != null && obj.getChart_id() > 0) {
					if (forwardChart(Long.toString(obj.getChart_id()), obj.getPatient_id(), obj.getPractice_id(), "",
							obj.getVisit_date())) {
						System.out.println("\nChart has been Forwarded Successfully.\n");
					}
				}
				result = 1;

			} else {
				result = 0;
			}
		} else {
			result = 1;
			System.out.println("\nChart already exists. chartId:" + chart_id + " \n");
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(chart_id));
			resp.setResponse("Data has been saved successfully.");
		}

		return resp;

	}

	private Long checkIfEncounterAlreadyExists(String patientId, String appointmentId, String locationId,
			String providerId, String visitDate) {

		Long chartId = (long) 0;

		List<SpParameters> lstCheck = new ArrayList<>();

		lstCheck.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		lstCheck.add(new SpParameters("appointment_id", appointmentId.toString(), String.class, ParameterMode.IN));
		lstCheck.add(new SpParameters("location_id", locationId.toString(), String.class, ParameterMode.IN));
		lstCheck.add(new SpParameters("provider_id", providerId.toString(), String.class, ParameterMode.IN));
		lstCheck.add(new SpParameters("visit_date", visitDate.toString(), String.class, ParameterMode.IN));

		List<ORMScalarValue> lstResult = db.getStoreProcedureData("spCheckIfEncounterAlreadyExists",
				ORMScalarValue.class, lstCheck);

		if (lstResult != null && lstResult.size() > 0) {
			ORMScalarValue obj = (ORMScalarValue) lstResult.get(0);

			chartId = Long.parseLong(obj.getScalar_value());
		}

		return chartId;
	}

	public Boolean forwardChart(String chartID, String patientID, String practiceID, String ip, String visitDate) {
		Boolean result = true;
		try {
			// String strProce = "spForwardChart";

			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(new SpParameters("chartID", chartID.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("patientID", patientID.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("practiceID", practiceID.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("ip", ip.toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("visit_date", visitDate.toString(), String.class, ParameterMode.IN));

			db.getStoreProcedureDataWOClass("spForwardChart", lstParam);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// RFV/HPI-start
	@Override
	public ORMGetReasonForVisitHPI getChartReasonForVisit_HPI(Long chart_id) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetReasonForVisitHPI> lst = db.getStoreProcedureData("spGetChartReasonForVisitHPI_View",
				ORMGetReasonForVisitHPI.class, lstParam);

		if (!lst.isEmpty())
			return lst.get(0);
		else
			return null;
	}

	@Override
	public Long saveupdateChartRFV_HPI(ORMChartReasonForVisit_HPI obj) {
		// System.out.println(obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (obj.getReasonforvisit_id() != null && obj.getReasonforvisit_id() != 0) {
			// System.out.println("IN EDIT SECTION.");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return obj.getReasonforvisit_id();
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setReasonforvisit_id(db.IDGenerator("chart_reasonforvisit_hpi", Long.parseLong(obj.getPractice_id())));
			// System.out.println("IN SAVE SECTION.");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return obj.getReasonforvisit_id();
			else
				return (long) 0;
		}
	}

	// RFV/HPI-end
	// <ORMChartVital> getChartVital(Long chart_id);
	@Override
	public ORMChartVital getChartVital(Long chart_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));
		List<ORMChartVital> lst = db.getStoreProcedureData("spGetChartVital", ORMChartVital.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return (ORMChartVital) lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMGetVitalGraphData> getVitalGraphData(Long patientID) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patientID", patientID.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartVital", ORMGetVitalGraphData.class, lstParam);

	}

	// public Long saveupdateChartVital(ORMChartVital obj);
	@Override
	public ServiceResponse<ORMKeyValue> saveupdateChartVital(ORMChartVital obj) {

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getVital_id() != null && obj.getVital_id() != 0) {
			result = db.SaveEntity(obj, Operation.EDIT);
		} else {
			obj.setVital_id(db.IDGenerator("chart_vital", Long.parseLong(obj.getPractice_id())));
			result = db.SaveEntity(obj, Operation.ADD);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(obj.getVital_id().toString());
		}

		return resp;
	}

	// @Override
	// public List<ORMChartVital> getQuickVitalsLoad(String pat_ID) {
	// List<SpParameters> lstParam = new ArrayList<>();
	// lstParam.add(new SpParameters("patient_id", pat_ID, String.class,
	// ParameterMode.IN));
	// return db.getStoreProcedureData("spGetQuickVital", ORMChartVital.class,
	// lstParam);
	//
	// }
	@Override
	public List<ORMGetCummulativeVitals> getCummulativeVitals(Long patientId, String unitType) {
		// String vtype = "english";
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("measureType", unitType.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetCummulativeVital", ORMGetCummulativeVitals.class, lstParam);
	}

	// vitals-end
	// officetest-start
	// vitals-end
	// officetest-start
	@Override
	public List<ORMChartOfficeTest> getOfficeTest(Long chartID) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chartID", chartID.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetOfficeTest", ORMChartOfficeTest.class, lstParam);

	}

	// public Long addUpdateTest(ORMChartOfficeTest obj); officetest_id
	//

	@Override
	public ServiceResponse<ORMKeyValue> saveOfficeTest(WrapperOfficeTestSave WrapperOfficeTestSave) {
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ORMChartOfficeTest objOfc = WrapperOfficeTestSave.getOfficeTestSave();
		List<ORMChartOfficetest_cpt> objCpt = WrapperOfficeTestSave.getCptSave();
		if (objOfc != null) {
			// System.out.println(objOfc.toString());
			objOfc.setDate_modified(DateTimeUtil.getCurrentDateTime());
			objOfc.setDeleted(false);

			if (objOfc.getOfficetest_id() == null) {
				objOfc.setOfficetest_id(db.IDGenerator("patient_officetest", Long.parseLong(objOfc.getPractice_id())));
				// System.out.println("SAVE...");
				result = db.SaveEntity(objOfc, Operation.ADD);
			} else {
				result = db.SaveEntity(objOfc, Operation.EDIT);
			}
		}

		if (objCpt != null && objCpt.size() > 0) {
			// if (!"".equals(objCpt) && !"0".equals(objCpt)) {
			String strQuery = "delete from patient_officetest_cpts where chart_id = '" + objOfc.getChart_id() + "' ";
			db.ExecuteUpdateQuery(strQuery);

			for (ORMChartOfficetest_cpt ormobjCpt : objCpt) {

				ormobjCpt.setOfficetest_cpt_id(
						db.IDGenerator("patient_officetest_cpts", Long.parseLong(objOfc.getPractice_id())).toString());
				ormobjCpt.setOfficetest_id(objOfc.getOfficetest_id().toString());
				ormobjCpt.setDate_created(DateTimeUtil.getCurrentDateTime());
				ormobjCpt.setDate_modified(DateTimeUtil.getCurrentDateTime());

				result = db.SaveEntity(ormobjCpt, Operation.ADD);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(objOfc.getOfficetest_id().toString());
		}
		return resp;
	}

	/*
	 * @Override public Long saveOfficeTest(ORMChartOfficeTest obj) {
	 * System.out.println(obj.toString());
	 * obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
	 * obj.setDeleted(false); if (obj.getOfficetest_id() != null &&
	 * obj.getOfficetest_id() != 0) { System.out.println("EDIT..."); if
	 * (db.SaveEntity(obj, Operation.EDIT) > 0) return obj.getOfficetest_id(); else
	 * return (long) 0; } else {
	 * obj.setDate_created(DateTimeUtil.getCurrentDateTime());
	 * obj.setOfficetest_id(db.IDGenerator("patient_officetest",
	 * Long.parseLong(obj.getPractice_id()))); System.out.println("SAVE..."); if
	 * (db.SaveEntity(obj, Operation.ADD) > 0) return obj.getOfficetest_id(); else
	 * return (long) 0; } }
	 */
	/*
	 * @Override public Long addUpdateTest(ORMChartOfficeTest obj) { if
	 * (obj.getOfficetest_id() != null && obj.getOfficetest_id() != 0) { if
	 * (db.SaveEntity(obj, Operation.EDIT) > 0) return obj.getOfficetest_id(); else
	 * return (long) 0; } else {
	 * obj.setOfficetest_id(db.IDGenerator("patient_officetest",
	 * Long.parseLong(obj.getPractice_id()))); if (db.SaveEntity(obj, Operation.ADD)
	 * > 0) return obj.getOfficetest_id(); else return (long) 0; } }
	 */

	// officetest-end
	// Asthma Control Test - start
	@Override
	public List<ORMGetPatientHealthCheckSummary> getPatientHealthCheckSummary(String practice_id, String patient_id,
			String form_type) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("form_type", form_type, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientHealthCheckSummary", ORMGetPatientHealthCheckSummary.class,
				lstParam);
	}

	// <ORMGetPatientHealthCheckForm> getPatientHealthCheckSummary();
	@Override
	public List<ORMGetPatientHealthCheckForm> getPatientHealthCheckForm(SearchCriteria searchCriteria) {
		List<ORMGetPatientHealthCheckForm> list = null;
		List<SpParameters> lstParam = new ArrayList<>();
		// lstParam.add(new SpParameters("practice_id", practice_id, String.class,
		// ParameterMode.IN));
		// lstParam.add(new SpParameters("patient_id", patient_id, String.class,
		// ParameterMode.IN));
		// lstParam.add(new SpParameters("chart_id", chart_id, String.class,
		// ParameterMode.IN));
		// lstParam.add(new SpParameters("form_id", form_id, String.class,
		// ParameterMode.IN));
		// lstParam.add(new SpParameters("health_check_id", health_check_id,
		// String.class, ParameterMode.IN));
		// list= db.getStoreProcedureData("spGetPatientHealthCheckForm",
		// ORMGetPatientHealthCheckForm.class, lstParam);
		String docPath = "";
		String formID = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("doc_path"))
					docPath = pram.getValue();
				else
					lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));

				if (pram.getName().equals("form_id")) {
					formID = pram.getValue();
				}
			}
		}
		if (formID.equals("84")) {
			list = db.getStoreProcedureData("spGetPatientHealthCheckForm_gyn", ORMGetPatientHealthCheckForm.class,
					lstParam);
		} else {
			list = db.getStoreProcedureData("spGetPatientHealthCheckForm", ORMGetPatientHealthCheckForm.class,
					lstParam);
		}

		// for (ORMGetPatientHealthCheckForm orm : (List<ORMGetPatientHealthCheckForm>)
		// list) {
		// if (orm.getForm_html() != null && !orm.getForm_html().equals("")) {
		// orm.setForm_html(getHTMLString(orm.getForm_html()));
		// System.out.println("final query: " + orm.getForm_html());
		// break;
		// }
		// }
		//
		// For HTML String from FILE -- need to check and verify this.

		if (docPath != null && !docPath.equals("")) {
			// System.out.println("inside docPath: " + docPath);
			for (ORMGetPatientHealthCheckForm orm : (List<ORMGetPatientHealthCheckForm>) list) {
				if (orm.getFile_link() != null && !orm.getFile_link().equals("")) {
					orm.setForm_html(getHTMLString(docPath + searchCriteria.getPractice_id().toString()
							+ "\\Health_Check\\" + orm.getFile_link()));
					// System.out.println("Gethtml : " + orm.getForm_html());
					break;
				}
			}
		}
		return list;
	}

	public String getHTMLString(String file_full_path) {
		String content;
		try {
			content = new String(Files.readAllBytes(Paths.get(file_full_path)));
		} catch (IOException ex) {
			ex.printStackTrace();
			return "";
		}
		return content;
	}

	// Asthma Control Test - end
	// annotation-start
	// public List<> getChartAnnotation(String chartID);
	@Override
	public List<ORMChartAnnotation> getChartAnnotation(String chartID) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chartID, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartAnnotation", ORMChartAnnotation.class, lstParam);

	}

	//
	@Override
	public Long saveupdateChartAnnotation(ORMChartAnnotation obj) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		obj.setDate_modified(dateFormat.format(date));
		if (obj.getChart_annotation_id() != null && obj.getChart_annotation_id() != 0) {
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return obj.getChart_annotation_id();
			else
				return (long) 0;
		} else {
			obj.setChart_annotation_id(db.IDGenerator("chart_annotation", Long.parseLong(obj.getPractice_id())));
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return obj.getChart_annotation_id();
			else
				return (long) 0;
		}
	}

	// annotation-end
	// health maintenanceList-start
	@Override
	public List<ORMGetPatientHealthMaintenanceList> getPatientHealthMaintenanceList(String patient_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientHealthMaintenanceList", ORMGetPatientHealthMaintenanceList.class,
				lstParam);
	}
	// health maintenanceList-end

	@Override
	public List<ORMGetPatientHealthMaintenance> getPatientHealthMaintenance(String patient_id, String phm_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("phm_id", phm_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientHealthMaintenance", ORMGetPatientHealthMaintenance.class,
				lstParam);
	}

	// Chart Report
	@Override
	public List<ORMChartReportDetails> getChartReportDetails(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartReportHeader", ORMChartReportDetails.class, lstParam);
	}

	@Override
	public List<ORMChartProcedure> getChartSurgery(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria",
				"and cp.chart_id=" + chart_id + "  and cp.entry_type IN ('CHART_SURGERY') ", String.class,
				ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartProcedure", ORMChartProcedure.class, lstParam);
	}

	@Override
	public List<ORMChartProcedure> getChartProcedures(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria",
				"and cp.chart_id=" + chart_id + "  and cp.entry_type IN ('CHART_PROCEDURE') ", String.class,
				ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartProcedure", ORMChartProcedure.class, lstParam);
	}

	// @Override
	// public List<ORMThreeColumGeneric> getSexualOrientDDL() {
	// return
	// db.getStoreProcedureData("spGetSexualOrientation",ORMThreeColumGeneric.class,
	// null);
	// }
	// @Override
	// public List<ORMThreeColumGeneric> getGenderIdentityDDL() {
	// return
	// db.getStoreProcedureData("spGetGenderIdentity",ORMThreeColumGeneric.class,
	// null);
	// }
	@Override
	public List<ORMGetChartFamilyHis> getChartFamilyHis(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}
		return db.getStoreProcedureData("spGetChartFamilyHX", ORMGetChartFamilyHis.class, lstParam);

	}

	@Override
	public List<ORMGetChartPrescription> getPatPrescription(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}
		return db.getStoreProcedureData("spGetChartPrescription", ORMGetChartPrescription.class, lstParam);
	}

	@Override
	public List<ORMChartAllergies> getPatAllergies(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}
		return db.getStoreProcedureData("spGetChartAllergies", ORMChartAllergies.class, lstParam);
	}

	@Override
	public List<ORMGetPatientRos> getPatROS(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientROS", ORMGetPatientRos.class, lstParam);
	}

	@Override
	public List<ORMChartProgressNotes> getPatProgressNotes(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartProgressNote", ORMChartProgressNotes.class, lstParam);
	}

	@Override
	public List<ORMCarePlan> getPatCarePlan(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetCarePlan", ORMCarePlan.class, lstParam);
	}

	@Override
	public List<ORMCognitiveFunctional> getChartCognitiveFunctional(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartCognitiveFunctional", ORMCognitiveFunctional.class, lstParam);
	}

	@Override
	public List<ORMChartPhysicalExam> getPatPhysicalExam(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatient_PhysicalExam", ORMChartPhysicalExam.class, lstParam);
	}

	@Override
	public List<ORMPatientInjuryTreatmentNotes> getPatInjuryNotes(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientInjurytreatment_Notes", ORMPatientInjuryTreatmentNotes.class,
				lstParam);
	}

	@Override
	public List<ORMGetChartFollowUpPlan> getChartFollowUpProblem(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", "", String.class, ParameterMode.IN));
		return db.getStoreProcedureData("getChartFollowUpPlan", ORMGetChartFollowUpPlan.class, lstParam);
	}

	@Override
	public List<ORMThreeColum> getNQFPlan() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("getNQFPlan", ORMThreeColum.class, null);
	}

	@Override
	public List<ORMNQFPlanDetail> getNQFPlanDetail() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("getNQFPlanDetail_new", ORMNQFPlanDetail.class, null);
	}

	@Override
	public List<ORMPastMedicalHistory> getPastMedHistory(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartPMH_history", ORMPastMedicalHistory.class, lstParam);
	}

	@Override
	public List<ORMAssessment> getAssessments(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartAssessment", ORMAssessment.class, lstParam);
	}

	@Override
	public List<ORMPHRAuditLog> getPatientAWVPrint(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetpatient_AWV_Print", ORMPHRAuditLog.class, lstParam);
	}

	@Override
	public List<ORMHealthMentPrint> getHealthMaintPrint(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetpatient_health_maintenance", ORMHealthMentPrint.class, lstParam);
	}

	@Override
	public List<ORMTwoColumnGeneric> getLabOrderTestPrint(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientOrderCode_Des", ORMTwoColumnGeneric.class, lstParam);
	}

	@Override
	public List<ORMFourColumGeneric> getAmendmentsPrint(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetchartAmendments_print", ORMFourColumGeneric.class, lstParam);
	}

	// cognative history-start
	@Override
	public List<ORMThreeColumGeneric> getCognitiveValues(String chart_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spgetCognitiveValues_new", ORMThreeColumGeneric.class, lstParam);
	}

	@Override
	public Long CognitiveAddUpdate(ORMCognitive_Functional obj) {

		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getCognitive_functional_id() == null) {

			obj.setCognitive_functional_id(
					db.IDGenerator("chart_cognitive_functional", Long.parseLong(obj.getPractice_id())));
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);

			// if (db.SaveEntity(obj, Operation.EDIT) > 0)
			// return obj.getCognitive_functional_id();
			// else
			// return (long) 0;
		} else {
			result = db.SaveEntity(obj, Operation.EDIT);
		}

		if (result > 0) {
			return obj.getCognitive_functional_id();
		} else {
			return (long) 0;
		}
	}

	// cognative history-end
	// physicians-care start
	@Override
	public List<ORMPhysicians_Care> GetPhyCare(String practice_id, String patient_id, String chart_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("sp_getall_chart_physicians_care", ORMPhysicians_Care.class, lstParam);
	}

	@Override
	public Long saveEditPhyCare(ORMPhysicians_Care obj) {
		// System.out.println(obj.toString());
		if (obj.getPhysicians_care_id() != null && obj.getPhysicians_care_id() != 0) {
			// System.out.println("IN EDIT SECTION.");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return obj.getPhysicians_care_id();
			else
				return (long) 0;
		} else {
			obj.setPhysicians_care_id(db.IDGenerator("chart_physicians_care", Long.parseLong(obj.getPractice_id())));
			// System.out.println("IN SAVE SECTION.");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return obj.getPhysicians_care_id();
			else
				return (long) 0;
		}
	}

	// physicians-care end
	// Chart Module History--start
	@Override
	public List<?> LoadChartModuleHistory(Long practice_id, String moduleName, SearchCriteria searchCriteria) {
		List<SpParameters> objParm = new ArrayList<>();
		// lstParam.add(new SpParameters("titleString", titleString, String.class,
		// ParameterMode.IN));
		objParm.add(new SpParameters("module_name", moduleName, String.class, ParameterMode.IN));
		// lstParam.add(new SpParameters("criteria", criteria, String.class,
		// ParameterMode.IN));
		List<ORMTwoColumnGeneric> result = db.getStoreProcedureData("spGetLogQuery_new", ORMTwoColumnGeneric.class,
				objParm);
		String Query = "";

		for (ORMTwoColumnGeneric ormTwoColumnGeneric : result) {
			Query = ormTwoColumnGeneric.getCol1() + practice_id + searchCriteria.getCriteria().toString()
					+ ormTwoColumnGeneric.getCol2();
			// headder.add(ormTwoColumnGeneric.getCol1().split("[")[0]);
		}
		// System.out.println("final query: " + Query);
		// String delimiters = "\\[";
		// Query=Query.replaceAll("[", "(").replaceAll("]", ")");

		/*
		 * String[] array = Query.split(",");// rfv.system_ip (IP Address),pc.patient_id
		 * as (Patient ID) String col2; for(int i=0;i<array.length;i++) { col2 =
		 * array[i].split("[)]")[0].substring(array[i].split("[)]")[0].indexOf("(")+1,
		 * array[i].split("[)]")[0].length()); }
		 */

		// String[] array = Query.split(",");

		// String[] array = Query.split("\\[");

		// System.out.println("got an arry : " + array);
		// ArrayList<String> headder = new ArrayList<>();
		// String[] a = Query.toString().split("[");
		// for (int i = 0; i < a.length; i++)
		// {

		// }

		List<?> FResult;
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("strQuery", Query, String.class, ParameterMode.IN));
		if (Query != null && Query != "") {
			FResult = db.getStoreProcedureDataWOClass("spGetDynamicRpt", lstParam);
		} else {
			FResult = null;
		}

		/*
		 * List<?> head; for (int i = 0; i < array.length; i++) {
		 * 
		 * }
		 */

		return FResult;
		// return db.getStoreProcedureData("SearchPatient", SearchPatient.class,
		// lstParam);

		// return db.getStoreProcedureData("", lstParam);
	}

	@Override
	public List<ORMTwoColumnGeneric> LoadChartModuleHistoryHeader(String moduleName) {
		List<SpParameters> objParm = new ArrayList<>();
		objParm.add(new SpParameters("module_name", moduleName, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLogQuery_new", ORMTwoColumnGeneric.class, objParm);
	}

	// Chart Module History--end
	@Override
	public List<ORMChartModuleSummary> getPatientVisitSnapShot(SearchCriteria searchCriteria) {
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}
		return db.getStoreProcedureData("spGetChartModuleSummary", ORMChartModuleSummary.class, lstParam);
	}

	@Override
	public List<ORMGetFutureAppointments> getFutureAppointments(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> objParm = new ArrayList<>();
		objParm.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		objParm.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("sp_GetFutureAppointments", ORMGetFutureAppointments.class, objParm);
	}

	// public Long ( obj);
	@Override
	public Long saveProcedure(ORMChartProcedure obj) {
		/*
		 * if(obj.getChart_procedures_id() !=null && obj.getChart_procedures_id()!= 0) {
		 * if(db.SaveEntity(obj,Operation.EDIT)>0) return obj.getChart_procedures_id();
		 * else return (long) 0; } else {
		 */
		obj.setChart_procedures_id(db.IDGenerator("chart_procedures", Long.parseLong(obj.getPractice_id())));
		if (db.SaveEntity(obj, Operation.ADD) > 0)
			return obj.getChart_procedures_id();
		else
			return (long) 0;
		// }
	}

	@Override
	public Long SavePatientHealthExamToFile(ORMPatientHealthCheck objORMHealthCheck, String hTML, String docPath) {
		long result = 0;
		// TODO Auto-generated method stub
		objORMHealthCheck.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (objORMHealthCheck.getHealth_check_id().equals("") || objORMHealthCheck.getHealth_check_id() == null) {
			objORMHealthCheck.setHealth_check_id(Long.toString(
					db.IDGenerator("patient_health_check", Long.parseLong(objORMHealthCheck.getPractice_id()))));
			objORMHealthCheck.setDate_created(DateTimeUtil.getCurrentDateTime());
			String dir_path = GeneralOperations.checkPathYearMonthWise(objORMHealthCheck.getPractice_id(), docPath,
					"Health_Check");
			// System.out.println("directory " + dir_path);
			String fileName = objORMHealthCheck.getHealth_check_id() + ".html";
			try {
				Files.write(Paths.get(docPath + "\\" + objORMHealthCheck.getPractice_id() + "\\Health_Check\\"
						+ dir_path + "\\" + fileName), hTML.getBytes(), StandardOpenOption.CREATE);
				objORMHealthCheck.setFile_link(dir_path + "\\" + fileName);
			} catch (IOException ex) {
				System.out.println("IOException " + ex);
				result = 0;
			}
			if (db.SaveEntity(objORMHealthCheck, Operation.ADD) > 0) {
				result = 1;
			}
			//save score in drug table
			if (objORMHealthCheck.getForm_id().equals("88") || objORMHealthCheck.getForm_id().equals("89") ) {
					String strAdjQuery = "insert into drug_abuse_score(id,patient_id,chart_id,health_check_id,test_date,score,practice_id,deleted,created_user,"
							+ "client_date_created,modified_user,client_date_modified,date_created,date_modified) "
							+ "values ('" + db.IDGenerator("drug_abuse_score", Long.parseLong(objORMHealthCheck.getPractice_id())) + "','"
							+ objORMHealthCheck.getPatient_id() + "','"
							+ objORMHealthCheck.getChart_id() + "','"
							+ objORMHealthCheck.getHealth_check_id() + "', getdate(),'"
							+ hTML.split("~~")[1] + "','"
							+ objORMHealthCheck.getPractice_id() + "','"
							+ false + "','"
							+ objORMHealthCheck.getCreated_user() + "','"
							+ objORMHealthCheck.getClient_date_created() + "','"
							+ objORMHealthCheck.getModified_user() + "','"
							+ objORMHealthCheck.getClient_date_modified() + "','"
							+ objORMHealthCheck.getDate_created() + "','"
							+ objORMHealthCheck.getDate_modified() + "')";
							
					db.ExecuteUpdateQuery(strAdjQuery);
			}
		} else {
			if (!objORMHealthCheck.getFile_link().equals("")) {
				// System.out.println(docPath + "\\" + objORMHealthCheck.getPractice_id() +
				// "\\Health_Check\\"
				// + objORMHealthCheck.getFile_link());
				try {
					Files.write(Paths.get(docPath + "\\" + objORMHealthCheck.getPractice_id() + "\\Health_Check\\"
							+ objORMHealthCheck.getFile_link()), hTML.getBytes(), StandardOpenOption.CREATE);
				} catch (IOException ex) {
					System.out.println("IOException " + ex);
					result = 0;
				}
			}
			if (db.SaveEntity(objORMHealthCheck, Operation.EDIT) > 0)
				result = 1;
			
			//save score in drug table
			if(objORMHealthCheck.getForm_id().equals("88") || objORMHealthCheck.getForm_id().equals("89")){
				String strUpdateQuery = "update drug_abuse_score set score = '"+ hTML.split("~~")[1] +"' , modified_user = '"+ objORMHealthCheck.getModified_user() +"', date_modified = GETDATE(), client_date_modified = '"+ objORMHealthCheck.getClient_date_modified() +"'"
		                + "where health_check_id = '"+ objORMHealthCheck.getHealth_check_id() +"'";
				
				db.ExecuteUpdateQuery(strUpdateQuery);
			}
			
		}

		return result;
	}

	@Override
	public List<ORMGetHealthCheckForms> getHealthCheckFormsList(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}
		return db.getStoreProcedureData("spGetHealthCheckFormsList", ORMGetHealthCheckForms.class, lstParam);
	}

	@Override
	public int SignHealthCheckForm(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String sign_type = "";
		String sign_info = "";
		String user = "";
		String client_date = "";
		String system_ip = "";
		String id = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("sign_type")) {
					sign_type = pram.getValue();
				} else if (pram.getName().equals("sign_info")) {
					sign_info = pram.getValue();
				} else if (pram.getName().equals("user")) {
					user = pram.getValue();
				} else if (pram.getName().equals("client_date")) {
					client_date = pram.getValue();
				} else if (pram.getName().equals("system_ip")) {
					system_ip = pram.getValue();
				} else if (pram.getName().equals("id")) {
					id = pram.getValue();
				}

			}

		}
		String Query = "update patient_health_check set " + sign_type + "= '" + sign_info
				+ "',date_modified=getdate(),modified_user='" + user + "',client_date_modified='" + client_date
				+ "',system_ip='" + system_ip + "' where health_check_id='" + id + "' ";
		return db.ExecuteUpdateQuery(Query);

	}

	/***** IU ****/
	// PROBLEM LIST
	@Override
	public List<ORMGetEncounterProblemsSummary> getChartProblem(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(
						new SpParameters(pram.getName(), pram.getValue().toString(), String.class, ParameterMode.IN));
			}
		}
		// lstParam.add(new SpParameters("chart_id", chart_id, String.class,
		// ParameterMode.IN));
		// lstParam.add(new SpParameters("criteria", "and isnull(status,'') = 'Active'
		// and pr.modified_user <> 'CCD' ", String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetEncounterProblemSummary", ORMGetEncounterProblemsSummary.class, lstParam);
	}

	@Override
	public ORMGetEncounterProblemDetail getChartProblemDetail(Long problem_id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<SpParameters> objParm = new ArrayList<>();
		objParm.add(new SpParameters("problem_id", problem_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetEncounterProblemDetail> lst = db.getStoreProcedureData("spGetEncounterProblemDetail",
				ORMGetEncounterProblemDetail.class, objParm);

		if (!lst.isEmpty())
			return lst.get(0);
		else
			return null;
	}

	@Override
	public Long saveProblem(ORMSaveEncounterProblem obj) {

		int result = 0;

		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (obj.getProblem_id() == null) // New
		{
			obj.setProblem_id(db.IDGenerator("chart_problem", obj.getPractice_id()));
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
			if (result > 0) {
				/*
				 * Long pmhID = db.IDGenerator("chart_assessment", obj.getPractice_id()); String
				 * strQuery = "INSERT INTO chart_assessment ( " +
				 * " id,patient_id,chart_id,practice_id,code,description,date,notes,deleted,created_user,client_date_created,date_created,modified_user, "
				 * + " client_date_modified,date_modified,system_ip) " + " VALUES( " + " '" +
				 * pmhID +"','" + obj.getPatient_id() + "','" + obj.getChart_id() + "','" +
				 * obj.getPractice_id() + "','" + obj.getDiag_code() + "', " + " '" +
				 * obj.getDiag_description() + "','" + obj.getProb_date() + "','" +
				 * obj.getComments().replaceAll("'", "`") + "',0,'" + obj.getCreated_user() +
				 * "', " + " '" + obj.getClient_date_created() + "', getdate(),'" +
				 * obj.getModified_user() + "','" + obj.getClient_date_modified() + "',  " +
				 * " getdate(), '" + obj.getSystem_ip() + "')"; db.ExecuteUpdateQuery(strQuery);
				 * //public Long savePatientAssessments(ORMSaveAssessments obj) {
				 */

				ORMSaveAssessments objAsses = new ORMSaveAssessments();
				objAsses.setId(null);
				objAsses.setPatient_id(obj.getPatient_id().toString());
				objAsses.setChart_id(obj.getChart_id().toString());
				objAsses.setPractice_id(obj.getPractice_id().toString());
				objAsses.setCode(obj.getDiag_code());
				objAsses.setDescription(obj.getDiag_description());
				objAsses.setDate(obj.getProb_date());
				objAsses.setNotes(obj.getComments());
				objAsses.setCreated_user(obj.getCreated_user());
				objAsses.setClient_date_created(obj.getClient_date_created());
				objAsses.setDate_created(DateTimeUtil.getCurrentDateTime());
				objAsses.setModified_user(obj.getModified_user());
				objAsses.setClient_date_modified(obj.getClient_date_modified());
				objAsses.setDate_modified(DateTimeUtil.getCurrentDateTime());
				objAsses.setSystem_ip(obj.getSystem_ip());
				savePatientAssessments(objAsses);

			}
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}

		if (result > 0) {
			return obj.getProblem_id();
		} else {
			return (long) 0;
		}
	}
	// END PROBLEM LIST

	@Override
	public List<ORMChartAssessmentView> getChartAssessmentView(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		// lstParam.add(new SpParameters("patient_id", patient_id.toString(),
		// String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		List<ORMChartAssessmentView> lstData = db.getStoreProcedureData("spGetChartAssessment_View",
				ORMChartAssessmentView.class, lstParam);

		return lstData;
	}

	@Override
	public List<ORMChartPrescriptionView> getChartPrescriptionView(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		List<ORMChartPrescriptionView> lstData = db.getStoreProcedureData("spGetChartPrescription_View",
				ORMChartPrescriptionView.class, lstParam);

		return lstData;
	}

	@Override
	public List<ORMChartAllergyView> getChartAllergyView(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		List<ORMChartAllergyView> lstData = db.getStoreProcedureData("spGetChartAllergies_View",
				ORMChartAllergyView.class, lstParam);

		return lstData;
	}

	@Override
	public List<ORMChartProgressNotesListView> getChartProgressNoteListView(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		List<ORMChartProgressNotesListView> lstData = db.getStoreProcedureData("spGetChartProgressNoteList_View",
				ORMChartProgressNotesListView.class, lstParam);

		return lstData;
	}

	@Override
	public List<ORMChartProgressNotesTextView> getChartProgressNoteTextView(String note_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("note_id", note_id.toString(), String.class, ParameterMode.IN));

		List<ORMChartProgressNotesTextView> lstData = db.getStoreProcedureData("spGetChartProgressNoteText_View",
				ORMChartProgressNotesTextView.class, lstParam);

		return lstData;
	}

	@Override
	public List<ORMSurgeryView> getChartSurgeryView(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		List<ORMSurgeryView> lstData = db.getStoreProcedureData("spGetChartSurgery_View", ORMSurgeryView.class,
				lstParam);

		return lstData;
	}

	@Override
	public List<ORMSurgeryView> getChartProcedureView(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		List<ORMSurgeryView> lstData = db.getStoreProcedureData("spGetChartProcedure_View", ORMSurgeryView.class,
				lstParam);

		return lstData;
	}

	@Override
	public List<ORMFamilyHxView> getChartFamilyHxView(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		List<ORMFamilyHxView> lstData = db.getStoreProcedureData("spGetChartFamilyHX_View", ORMFamilyHxView.class,
				lstParam);

		return lstData;
	}

	@Override
	public Long savePatientAssessments(ORMSaveAssessments obj) {
		// TODO Auto-generated method stub
		int result = 0;

		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (obj.getId() == null) // New
		{
			obj.setId(db.IDGenerator("chart_assessment", Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}

		if (result > 0) {
			return Long.parseLong(obj.getId());
		} else {
			return (long) 0;
		}
	}

	@Override
	public ORMChartAssessmentDetail getChartAssessmentDetail(String id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("id", id.toString(), String.class, ParameterMode.IN));

		List<ORMChartAssessmentDetail> lstData = db.getStoreProcedureData("spGetChartAssessment_Detail",
				ORMChartAssessmentDetail.class, lstParam);

		if (!lstData.isEmpty())
			return lstData.get(0);
		else
			return null;
	}

	@Override
	public Long saveChartProgressNotes(ORMSavePlanOfCare obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getNote_id() == null) // New
		{
			obj.setNote_id(db.IDGenerator("chart_progress_note", Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
			if (result > 0) {
				updateProgressLog("Insert", obj.getNote_id());
			}
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
			if (result > 0) {
				updateProgressLog("Modified", obj.getNote_id());
			}
		}
		// System.out.print(" method " + obj.toString());
		if (result > 0) {
			return Long.parseLong(obj.getNote_id());
		} else {
			return (long) 0;
		}
	} 
	@Override
	public long deleteProgressNotes(SearchCriteria searchCriteria) {
		String column_id = "";
		String modified_user = "";
		String client_date_modified = "";
		String client_ip = "";
		String msg_Type = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("column_id")) {
					column_id = pram.getValue();
				} else if (pram.getName().equals("modified_user")) {
					modified_user = pram.getValue();
				} else if (pram.getName().equals("client_date_modified")) {
					client_date_modified = pram.getValue();
				} else if (pram.getName().equals("client_ip")) {
					client_ip = pram.getValue();
				} else if (pram.getName().equals("msg_Type")) {
					msg_Type = pram.getValue();
				}
			}
		}
		String Query = "";
		if (msg_Type.equals("deleted")) {
			Query = " update chart_progress_note set date_modified = getdate() , deleted= '1', modified_user='" + modified_user
					+ "',system_ip='" + client_ip 
					+ "',client_date_modified = '" + client_date_modified + "' where note_id in (" + column_id
					+ ")";
		}
		int result = 0;
		result = db.ExecuteUpdateQuery(Query);
		if (result > 0) {
			updateProgressLog("deleted", column_id);
		}
		return result;
	}

	public int updateProgressLog(String strOperation, String NoteID) {
		try {
			String strQuery = " insert into newdblog.dbo.chart_progress_note_log "

					+ " (note_id,patient_id,chart_id,notes_html,notes_text,practice_id,deleted,created_user,client_date_created,modified_user,client_date_modified,"
					+ " date_created,date_modified,system_ip,signed_by,signed_date,notes_new_html,"
					+ "	[user],[event],[event_type])"

					+ " select note_id,patient_id,chart_id,notes_html,notes_text,practice_id,deleted,created_user,client_date_created,modified_user,client_date_modified, "
					+ " date_created,date_modified,system_ip,signed_by,signed_date,notes_new_html, "
					+ " system_user,current_timestamp,'" + strOperation + "' from chart_progress_note "
					+ " where note_id = " + NoteID;

			return db.ExecuteUpdateQuery(strQuery);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<ORMGetPatientHealthCheckForm> getHealthCheckFormFromId(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}
		return db.getStoreProcedureData("spGetPatientHealthCheckForm", ORMGetPatientHealthCheckForm.class, lstParam);
	}

	@Override
	public List<ORMGetPatientPhysicalExamView> getPatPhysicalExamView(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetPatientPhysicalExamView> lstData = db.getStoreProcedureData("spGetPatient_PhysicalExam_View",
				ORMGetPatientPhysicalExamView.class, lstParam);

		return lstData;
	}

	@Override
	public ORMChartPhysicalExam getPatPhysicalExamDetail(String id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("chart_id", id.toString(), String.class, ParameterMode.IN));

		List<ORMChartPhysicalExam> lstData = db.getStoreProcedureData("spGetPatient_PhysicalExam_Detail",
				ORMChartPhysicalExam.class, lstParam);
		if (!lstData.isEmpty())
			return lstData.get(0);
		else
			return null;
	}

	@Override
	public Long savePatientPhysicalExam(ORMChartPhysicalExam obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getPatient_organ_id() == null) // New
		{
			obj.setPatient_organ_id(
					db.IDGenerator("chart_physical_exam", Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
			if (result > 0) {
				updatePhysicalLog("Insert", obj.getPatient_organ_id());
			}
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
			if (result > 0) {
				updatePhysicalLog("Modified", obj.getPatient_organ_id());
			}
		}
		if (result > 0) {
			return Long.parseLong(obj.getPatient_organ_id());

		} else {
			return (long) 0;
		}
	}

	public int updatePhysicalLog(String strOperation, String org_ID) {
		try {
			String strQuery = " insert into newDbLog.dbo.chart_physical_exam_log "

					// + "
					// (patient_organ_id,patient_id,chart_id,organ_value,pe_detail,created_user,modified_user,date_modified,date_created,client_date_modified,
					// "
					// + " client_date_created,deleted,practice_id,[user], [event], [event_type])"

					+ " select patient_organ_id,patient_id,chart_id,organ_value,pe_detail,created_user,modified_user,date_modified,date_created,client_date_modified, "
					+ " client_date_created,deleted,practice_id, " + " system_user,current_timestamp,'" + strOperation
					+ "' from chart_physical_exam " + " where patient_organ_id = " + org_ID;

			return db.ExecuteUpdateQuery(strQuery);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ORMChartSurgery getChartSurgeryDetail(String id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", "and cp.chart_procedures_id=" + id, String.class, // and cp.entry_type
																									// IN
																									// ('CHART_SURGERY')
				ParameterMode.IN));
		List<ORMChartSurgery> lstData = db.getStoreProcedureData("spGetChartProcedure", ORMChartSurgery.class,
				lstParam);

		if (!lstData.isEmpty())
			return lstData.get(0);
		else
			return null;
	}

	@Override
	public Long saveChartSurgery(ORMChartSurgery obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getChart_procedures_id() == null) // New
		{
			obj.setChart_procedures_id(
					db.IDGenerator("chart_procedures", Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		if (result > 0) {
			return Long.parseLong(obj.getChart_procedures_id());
		} else {
			return (long) 0;
		}
	}

	@Override
	public Long saveFamilyHx(ORMFamilyHx obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getFamilyhistory_id() == null) // New
		{
			obj.setFamilyhistory_id(
					db.IDGenerator("chart_familyhistory", Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		if (result > 0) {
			return Long.parseLong(obj.getFamilyhistory_id());
		} else {
			return (long) 0;
		}
	}

	@Override
	public ORMFamilyHx getChartFamilyHxDetail(String id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("id", id.toString(), String.class, ParameterMode.IN));

		List<ORMFamilyHx> lstData = db.getStoreProcedureData("spGetChartFamilyHX_Detail", ORMFamilyHx.class, lstParam);

		if (!lstData.isEmpty())
			return lstData.get(0);
		else
			return null;
	}

	@Override
	public ORMChartROS getChartROS(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		List<ORMChartROS> lstData = db.getStoreProcedureData("spGetChartROS_Detail", ORMChartROS.class, lstParam);

		if (!lstData.isEmpty())
			return lstData.get(0);
		else
			return null;
	}

	@Override
	public Long saveChartRos(ORMChartROS obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getPatient_ros_id() == null) // New
		{
			obj.setPatient_ros_id(db.IDGenerator("patient_ros", Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
			if (result > 0) {
				updatepatientROSLog("Insert", obj.getPatient_ros_id());
			}
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
			if (result > 0) {
				updatepatientROSLog("Modified", obj.getPatient_ros_id());
			}
		}
		if (result > 0) {
			return Long.parseLong(obj.getPatient_ros_id());
		} else {
			return (long) 0;
		} 
	}
	public int updatepatientROSLog(String strOperation, String ros_ID) {
		try {
			String strQuery = " insert into newDbLog.dbo.patient_ros_log "
					+ " select patient_ros_id,ros_detail,patient_id,chart_id,practice_id,deleted, created_user,client_date_created,modified_user,"
					+ "client_date_modified, date_created,date_modified,system_ip," 
					+ " system_user,current_timestamp,'" + strOperation
					+ "' from patient_ros " + " where patient_ros_id = " + ros_ID;

			return db.ExecuteUpdateQuery(strQuery);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<ORMGetChartPMHView> getChartPMHView(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetChartPMHView> lstData = db.getStoreProcedureData("spGetChartPMH_View", ORMGetChartPMHView.class,
				lstParam);

		return lstData;
	}

	@Override
	public ORMChartPMH getChartPMHDetail(String id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("id", id.toString(), String.class, ParameterMode.IN));

		List<ORMChartPMH> lstData = db.getStoreProcedureData("getChartPMHDetail", ORMChartPMH.class, lstParam);

		if (!lstData.isEmpty())
			return lstData.get(0);
		else
			return null;
	}

	@Override
	public Long savePMH(ORMChartPMH obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getId() == null) // New
		{
			obj.setId(db.IDGenerator("chart_past_medical_history", Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		if (result > 0) {
			return Long.parseLong(obj.getId());
		} else {
			return (long) 0;
		}
	}

	@Override
	public List<ORMGetHealthMaintList> getChartHealthMainList(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetHealthMaintList> lstData = db.getStoreProcedureData("spGetPatientHealthMaintenanceList",
				ORMGetHealthMaintList.class, lstParam);

		return lstData;
	}

	@Override
	public List<ORMGetHealthMaintDetail_View> getChartHealthMainDetail_View(String patient_id, String maint_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("phm_id", maint_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetHealthMaintDetail_View> lstData = db.getStoreProcedureData("spGetPatientHealthMaintenance",
				ORMGetHealthMaintDetail_View.class, lstParam);

		return lstData;
	}

	@Override
	public Long saveHealthMaintDetail(List<ORM_HealthMaintenanceDetail> objDetail) {
		// TODO Auto-generated method stub
		int result = 0;

		for (ORM_HealthMaintenanceDetail ormSave : objDetail) {
			ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (ormSave.getDetail_id() == null) {
				ormSave.setDetail_id(
						db.IDGenerator("patient_health_maintenance_detail", Long.parseLong(ormSave.getPractice_id())));
				ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(ormSave, Operation.ADD);
			} else {
				result = db.SaveEntity(ormSave, Operation.EDIT);
			}

		}
		return (long) result;
	}

	@Override
	public Long saveHealthMaint(ORM_HealthMaintenance objMain) {
		// TODO Auto-generated method stub
		int result = 0;
		objMain.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (objMain.getPhm_id() == null) // New
		{
			objMain.setPhm_id(
					db.IDGenerator("patient_health_maintenance", Long.parseLong(objMain.getPractice_id())).toString());
			objMain.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(objMain, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(objMain, Operation.EDIT);
		}
		if (result > 0) {
			return Long.parseLong(objMain.getPhm_id());
		} else {
			return (long) 0;
		}
	}

	// **** Prescripton XML *****//
	@Override
	public ServiceResponse<ORMKeyValue> getPrescriptionXml(ORMPrescriptionXml PrescriptionXml) {
		// TODO Auto-generated method stub

		// String post_XML = "";
		String userType = "";
		String userRole = "";
		String requestedPage = "";
		List<ORMGetNewCropIrxInfo> acRxDetails;
		// List<ORMGetChartProbs> acDiagnosis;
		List<ORMGetEncounterProblemsSummary> acDiagnosis;

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", PrescriptionXml.getChartid(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", PrescriptionXml.getPatient_id(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("provider_id", PrescriptionXml.getProviderid(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("location_id", PrescriptionXml.getLocationid(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", PrescriptionXml.getPractice_id(), String.class, ParameterMode.IN));

		acRxDetails = db.getStoreProcedureData("spGetNewCropIrxInfoNew", ORMGetNewCropIrxInfo.class, lstParam);

		lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", PrescriptionXml.getChartid(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("diag_option", "active", String.class, ParameterMode.IN));

		acDiagnosis = db.getStoreProcedureData("spGetEncounterProblemSummary", ORMGetEncounterProblemsSummary.class,
				lstParam);

		String ErrorMsgs = "";
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (acRxDetails.size() > 0) {
			String providerName = "";
			if (acRxDetails.get(0).getIs_prescription_disable().equals(true)) {
				providerName = acRxDetails.get(0).getPrv_lname() + ", " + acRxDetails.get(0).getPrv_fname();
				ErrorMsgs += "Provider(" + providerName + ") has no prescription rights.\n";
			}

			if (acRxDetails.get(0).getIs_blocked().equals(null) || acRxDetails.get(0).getIs_blocked().equals(true)) {
				providerName = acRxDetails.get(0).getPrv_lname() + ", " + acRxDetails.get(0).getPrv_fname();
				ErrorMsgs += "Medication can't be prescriped against Inactive Provider(" + providerName + ").\n";
			}

			if (acRxDetails.get(0).getPrv_lname().equals(null) || acRxDetails.get(0).getPrv_lname().equals("")) {
				ErrorMsgs += "Provider Last name is missing.\n";
			}

			if (acRxDetails.get(0).getPrv_fname().equals(null) || acRxDetails.get(0).getPrv_fname().equals("")) {
				ErrorMsgs += "Provider First name is missing.\n";
			}

			if (acRxDetails.get(0).getPrv_dea_no() == null || acRxDetails.get(0).getPrv_dea_no() == "") {
				ErrorMsgs += "Provider DEA is missing.\n";
			}

			if (acRxDetails.get(0).getPrv_npi() == null || acRxDetails.get(0).getPrv_npi() == "") {
				ErrorMsgs += "Provider NPI is missing.\n";
			}
			if (PrescriptionXml.isAddpatientinfo()) {
				if ((acRxDetails.get(0).getPat_lname().equals(null) || acRxDetails.get(0).getPat_lname().equals(""))
						|| (acRxDetails.get(0).getPat_fname().equals(null)
								|| acRxDetails.get(0).getPat_fname().equals(""))) {
					ErrorMsgs += "Patient Last or First name is missing.\n";
				}

				if (acRxDetails.get(0).getPat_gender().equals(null) || acRxDetails.get(0).getPat_gender().equals("")) {
					ErrorMsgs += "Patient Gender is missing.\n";
				}

				if ((acRxDetails.get(0).getPat_zip().equals(null) || acRxDetails.get(0).getPat_zip().equals(""))
						|| (acRxDetails.get(0).getPat_city().equals(null)
								|| acRxDetails.get(0).getPat_city().equals(""))
						|| (acRxDetails.get(0).getPat_state().equals(null)
								|| acRxDetails.get(0).getPat_state().equals(""))) {
					ErrorMsgs += "Patient Zip is not valid.\n";
				}
			}
			if (ErrorMsgs.equals("") == false && ErrorMsgs.trim().length() > 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResult(ErrorMsgs);
				return resp;
			}
		}

		requestedPage = PrescriptionXml.getRequestedpage();
		if (PrescriptionXml.getPrescriber_role().toLowerCase().equals("manager")) {
			userType = "Staff";
			userRole = "manager";
			requestedPage = "manager";
		} else if (PrescriptionXml.getPrescriber_role().toLowerCase().equals("administration only")) {
			userType = "Staff";
			userRole = "admin";
			requestedPage = "medentry";
		} else if (PrescriptionXml.getPrescriber_role().toLowerCase().equals("doctor")) {
			userType = "LicensedPrescriber";
			userRole = "doctor";
		} else if (PrescriptionXml.getPrescriber_role().toLowerCase().equals("midlevel")) {
			userType = "MidlevelPrescriber";
			userRole = "midlevelPrescriber";
		} else {
			userType = "Staff";
			userRole = "nurse";
		}

		if (requestedPage == null || requestedPage == "") {
			ErrorMsgs += "Requested Page is missing. Please contact EHR vendor.\n";
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResult(ErrorMsgs);
			return resp;
		}

		System.out.print("\n Site id is " + PrescriptionXml.getRxsiteid());

		String generatedXML = "<?xml version=\"1.0\"?> ";
		generatedXML += "<NCScript xmlns=\"http://secure.newcropaccounts.com/interfaceV7\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
		generatedXML += "  <Credentials>";
		generatedXML += "    <partnerName>" + PrescriptionXml.getRxpartnername() + "</partnerName>";
		generatedXML += "    <name>" + PrescriptionXml.getRxusername() + "</name>";
		generatedXML += "    <password>" + PrescriptionXml.getRxuserpassword() + "</password>";
		generatedXML += "    <productName>" + PrescriptionXml.getRxproductname() + "</productName>";
		generatedXML += "    <productVersion>" + PrescriptionXml.getRxproductversion() + "</productVersion>";
		generatedXML += "  </Credentials>";
		generatedXML += "  <UserRole>";
		generatedXML += "    <user>" + userType + "</user>";
		generatedXML += "    <role>" + userRole + "</role>";
		generatedXML += "  </UserRole>";
		generatedXML += "  <Destination>";
		generatedXML += "    <requestedPage>" + requestedPage + "</requestedPage>";
		generatedXML += "  </Destination>";
		generatedXML += "  <Account ID=\"";
		generatedXML += PrescriptionXml.getPractice_id() == "504" ? "500" : PrescriptionXml.getPractice_id();
		generatedXML += "\">";
		generatedXML += "    <accountName>" + PrescriptionXml.getPractice_name().toString().trim() + "</accountName>";
		generatedXML += "    <siteID>" + PrescriptionXml.getRxsiteid() + "</siteID>";
		generatedXML += "    <AccountAddress>";
		generatedXML += "      <address1>" + PrescriptionXml.getAddress1().toString().trim() + "</address1>";

		if (PrescriptionXml.getAddress2().toString().trim().isEmpty())
			generatedXML += "<address2 />";
		else
			generatedXML += "      <address2>" + PrescriptionXml.getAddress2().toString().trim() + "</address2>";

		generatedXML += "      <city>" + PrescriptionXml.getCity().toString().trim() + "</city>";
		generatedXML += "      <state>" + PrescriptionXml.getState().toString().trim() + "</state>";
		generatedXML += "      <zip>" + PrescriptionXml.getZip().toString().trim() + "</zip>";
		generatedXML += "      <country>US</country>";
		generatedXML += "    </AccountAddress>";

		if (PrescriptionXml.getPhone().trim().isEmpty())
			generatedXML += "<accountPrimaryPhoneNumber />";
		else
			generatedXML += "    <accountPrimaryPhoneNumber>" + PrescriptionXml.getPhone().replaceAll("-", "")
					.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", "") + "</accountPrimaryPhoneNumber>";

		if (PrescriptionXml.getFax().trim().isEmpty())
			generatedXML += "<accountPrimaryFaxNumber />";
		else
			generatedXML += "    <accountPrimaryFaxNumber>" + PrescriptionXml.getFax().replaceAll("-", "")
					.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", "") + "</accountPrimaryFaxNumber>";

		generatedXML += "  </Account>";

		System.out.print("\n Prescription before manager role ");
		if (userRole != "manager") {
			generatedXML += "  <Location ID=\"" + acRxDetails.get(0).getLocation_id() + "\">";
			generatedXML += "    <locationName>" + acRxDetails.get(0).getLoc_name().toString().trim()
					+ "</locationName>";
			generatedXML += "    <LocationAddress>";
			generatedXML += "      <address1>" + acRxDetails.get(0).getLoc_address().toString().trim() + "</address1>";

			if (acRxDetails.get(0).getLoc_address2().trim().isEmpty())
				generatedXML += "<address2 />";
			else
				generatedXML += "      <address2>" + acRxDetails.get(0).getLoc_address2().toString().trim()
						+ "</address2>";

			generatedXML += "      <city>" + acRxDetails.get(0).getLoc_city().toString().trim() + "</city>";
			generatedXML += "      <state>" + acRxDetails.get(0).getLoc_state().toString().trim() + "</state>";
			generatedXML += "      <zip>" + acRxDetails.get(0).getLoc_zip().toString().trim() + "</zip>";
			generatedXML += "      <country>US</country>";
			generatedXML += "    </LocationAddress>";
			generatedXML += "    <primaryPhoneNumber>" + acRxDetails.get(0).getLoc_phone().toString()
					.replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", "")
					+ "</primaryPhoneNumber>";
			generatedXML += "    <primaryFaxNumber>" + acRxDetails.get(0).getLoc_fax().toString().replaceAll("-", "")
					.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", "") + "</primaryFaxNumber>";
			generatedXML += "    <pharmacyContactNumber>" + acRxDetails.get(0).getLoc_phone().toString()
					.replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", "")
					+ "</pharmacyContactNumber>";
			generatedXML += "  </Location>";
		}
		if (userRole == "doctor" || userRole == "nurse") {
			generatedXML += "  <LicensedPrescriber ID=\"";
			generatedXML += acRxDetails.get(0).getProvider_id() + "\">";
			generatedXML += "    <LicensedPrescriberName>";
			generatedXML += "      <last>" + acRxDetails.get(0).getPrv_lname().toString().trim() + "</last>";

			generatedXML += "      <first>" + acRxDetails.get(0).getPrv_fname().toString().trim() + "</first>";

			if (acRxDetails.get(0).getPrv_mi().toString().trim().isEmpty())
				generatedXML += "<middle />";
			else
				generatedXML += "      <middle>" + acRxDetails.get(0).getPrv_mi().toString().trim() + "</middle>";
			generatedXML += "    </LicensedPrescriberName>";
			generatedXML += "    <dea>" + acRxDetails.get(0).getPrv_dea_no().toString().trim() + "</dea>";
			// generatedXML+="<upin>"+acRxDetails.get(0).prv_upin+"</upin>" ;
			generatedXML += "    <licenseState>" + acRxDetails.get(0).getPrv_state().toString().trim()
					+ "</licenseState>";
			generatedXML += "    <licenseNumber>" + acRxDetails.get(0).getPrv_state_license().toString().trim()
					+ "</licenseNumber>";
			generatedXML += "    <npi>" + acRxDetails.get(0).getPrv_npi().toString().trim() + "</npi>";
			generatedXML += "  </LicensedPrescriber>";
		}
		if (userRole == "midlevelPrescriber") {
			generatedXML += "  <MidlevelPrescriber ID=\"";
			generatedXML += acRxDetails.get(0).getProvider_id() + "\">";
			generatedXML += "    <LicensedPrescriberName>";
			generatedXML += "      <last>" + acRxDetails.get(0).getPrv_lname().toString().trim() + "</last>";
			generatedXML += "      <first>" + acRxDetails.get(0).getPrv_fname().toString().trim() + "</first>";
			if (acRxDetails.get(0).getPrv_mi().toString().trim().isEmpty())
				generatedXML += "<middle />";
			else
				generatedXML += "      <middle>" + acRxDetails.get(0).getPrv_mi().toString().trim() + "</middle>";

			generatedXML += "    </LicensedPrescriberName>";
			generatedXML += "    <dea>" + acRxDetails.get(0).getPrv_dea_no().toString().trim() + "</dea>";
			// generatedXML+="<upin>"+acRxDetails.get(0).prv_upin+"</upin>" ;
			generatedXML += "    <licenseState>" + acRxDetails.get(0).getPrv_state().toString().trim()
					+ "</licenseState>";
			generatedXML += "    <licenseNumber>" + acRxDetails.get(0).getPrv_state_license().toString().trim()
					+ "</licenseNumber>";
			generatedXML += "    <npi>" + acRxDetails.get(0).getPrv_npi().toString().trim() + "</npi>";
			generatedXML += "  </MidlevelPrescriber>";
		}
		if (userType == "Staff") {
			generatedXML += "  <Staff ID=\"";
			generatedXML += PrescriptionXml.getUser_id();
			generatedXML += "\">";
			generatedXML += "    <StaffName>";
			generatedXML += "      <last>" + PrescriptionXml.getLast_name().toString().trim() + "</last>";
			generatedXML += "      <first>" + PrescriptionXml.getFirst_name().toString().trim() + "</first>";
			if (PrescriptionXml.getMname() != null && PrescriptionXml.getMname().toString().trim() != "")
				generatedXML += "      <middle>" + PrescriptionXml.getMname().toString().trim() + "</middle>";
			else
				generatedXML += "<middle />";

			generatedXML += "      <prefix>Mr.</prefix>";
			generatedXML += "    </StaffName>";
			generatedXML += "  </Staff>";
		}

		if (PrescriptionXml.isAddpatientinfo() && userRole != "manager") {
			generatedXML += "  <Patient ID=\"";
			generatedXML += acRxDetails.get(0).getPatient_id();
			generatedXML += "\">";
			generatedXML += "    <PatientName>";
			generatedXML += "      <last>" + acRxDetails.get(0).getPat_lname().toString().trim() + "</last>";
			generatedXML += "      <first>" + acRxDetails.get(0).getPat_fname().toString().trim() + "</first>";
			if (acRxDetails.get(0).getPat_mname() != "")
				generatedXML += "      <middle>" + acRxDetails.get(0).getPat_mname().toString().trim() + "</middle>";
			generatedXML += "    </PatientName>";
			generatedXML += "    <medicalRecordNumber>";
			generatedXML += acRxDetails.get(0).getPatient_id();
			generatedXML += "</medicalRecordNumber>";
			// generatedXML+="<memo></memo>" ;
			generatedXML += "    <PatientAddress>";
			generatedXML += "      <address1>" + acRxDetails.get(0).getPat_address().toString().trim() + "</address1>";
			generatedXML += "      <address2>" + acRxDetails.get(0).getPat_address2().toString().trim() + "</address2>";
			generatedXML += "      <city>" + acRxDetails.get(0).getPat_city().toString().trim() + "</city>";
			generatedXML += "      <state>" + acRxDetails.get(0).getPat_state().toString().trim() + "</state>";
			generatedXML += "      <zip>" + acRxDetails.get(0).getPat_zip().toString().trim() + "</zip>";
			generatedXML += "      <country>US</country>";
			generatedXML += "    </PatientAddress>";
			generatedXML += "    <PatientContact>";
			if (acRxDetails.get(0).getPat_home_phone() != "")
				generatedXML += "      <homeTelephone>" + acRxDetails.get(0).getPat_home_phone().toString()
						.replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", "")
						+ "</homeTelephone>";
			generatedXML += "    </PatientContact>";
			generatedXML += "    <PatientCharacteristics>";
			generatedXML += "      <dob>" + acRxDetails.get(0).getPat_dob() + "</dob>";
			generatedXML += "      <gender>"
					+ (acRxDetails.get(0).getPat_gender().toString().toLowerCase().equals("male")
							|| acRxDetails.get(0).getPat_gender().toString().toLowerCase().equals("m")
									? "M"
									: acRxDetails.get(0).getPat_gender().toString().toLowerCase().equals("female")
											|| acRxDetails.get(0).getPat_gender().toString().toLowerCase().equals("f")
													? "F"
													: "")
					+ "</gender>";

			if (acRxDetails.get(0).getPat_age_year() <= 18) {
				if (acRxDetails.get(0).getPat_height_inches().equals("")
						|| acRxDetails.get(0).getPat_height_inches().equals("0.0")) {

					generatedXML += "      <height />";
					generatedXML += "      <heightUnits />";
				} else {
					generatedXML += "      <height>" + acRxDetails.get(0).getPat_height_inches() + "</height>";
					generatedXML += "      <heightUnits>inches</heightUnits>";
				}
				if (acRxDetails.get(0).getPat_weight_lbs().equals("")) {
					generatedXML += "      <weight />";
					generatedXML += "      <weightUnits />";
				} else {
					generatedXML += "      <weight>" + acRxDetails.get(0).getPat_weight_lbs() + "</weight>";
					generatedXML += "      <weightUnits>lbs.</weightUnits>";
				}
				// if(acRxDetails.get(0).getPat_language().equals(""))
				// {
				// generatedXML += " <language></language>";
				// }
				// else
				// {
				// generatedXML += " <language>" + acRxDetails.get(0).getPat_language() +
				// "</language>";
				// }

			}
			generatedXML += "    </PatientCharacteristics>";

			// diagnosis
			String xmlDiagnosis = "";
			if (PrescriptionXml.isSendrxdiagnosis()) {
				for (int d = 0; d < acDiagnosis.size(); d++) {
					if ((acDiagnosis.get(d).getDiag_code() != null && acDiagnosis.get(d).getDiag_code() != "")
							&& (acDiagnosis.get(d).getDiag_description() != null
									&& acDiagnosis.get(d).getDiag_description() != "")
							&& acDiagnosis.get(d).getCode_type().equals("ICD-10")) {
						xmlDiagnosis += "<PatientDiagnosis>" + "<diagnosisID>"
								+ acDiagnosis.get(d).getDiag_code().toString().trim() + "</diagnosisID>"
								+ "<diagnosisType>" + acDiagnosis.get(d).getCode_type().replaceAll("-", "")
								+ "</diagnosisType>";

						if (acDiagnosis.get(d).getProb_date() != null && acDiagnosis.get(d).getProb_date() != "")
							xmlDiagnosis += "<onsetDate>"
									+ GeneralOperations.DateFormatYYYYMMDD(acDiagnosis.get(d).getProb_date())
									+ "</onsetDate>";
						// xmlDiagnosis+="<onsetDate>"+FormatDate(acDiagnosis.get(d).prob_date)+"</onsetDate>";

						xmlDiagnosis += "<diagnosisName>"
								+ acDiagnosis.get(d).getDiag_description().replaceAll("`|'|&|%|@", "").toString().trim()
								+ "</diagnosisName>";

						if (acDiagnosis.get(d).getProb_date() != null && acDiagnosis.get(d).getProb_date() != "")
							xmlDiagnosis += "<recordedDate>"
									+ GeneralOperations.DateFormatYYYYMMDD(acDiagnosis.get(d).getProb_date())
									+ "</recordedDate>";

						xmlDiagnosis += "</PatientDiagnosis>";
					}
				}
				generatedXML += xmlDiagnosis;
			}

			// if(xmlDiagnosis=="")
			// generatedXML+="<PatientDiagnosis><diagnosisID></diagnosisID><diagnosisType></diagnosisType><diagnosisName></diagnosisName></PatientDiagnosis>";
			// else

			if (PrescriptionXml.getChartid() != null && PrescriptionXml.getChartid() != "")
				generatedXML += "    <EncounterIdentifier>" + PrescriptionXml.getChartid() + "</EncounterIdentifier>";
			generatedXML += "  </Patient>";
		}

		System.out.print("\n getExternalprescriptionid " + PrescriptionXml.getExternalprescriptionid());
		System.out.print("\n reqPage from source " + PrescriptionXml.getRequestedpage());
		System.out.print("\n req page forwed " + requestedPage);

		if (requestedPage.equals("rxdetail")
				&& GeneralOperations.isNotNullorEmpty(PrescriptionXml.getExternalprescriptionid()))
			generatedXML += "  <OutsidePrescription ID=\"" + PrescriptionXml.getExternalprescriptionid() + "\"/>";

		if (requestedPage.equals("renewal")
				&& GeneralOperations.isNotNullorEmpty(PrescriptionXml.getExternalprescriptionid())) {
			generatedXML += "<PrescriptionRenewalResponse><renewalRequestIdentifier>"
					+ PrescriptionXml.getExternalprescriptionid()
					+ "</renewalRequestIdentifier><responseCode>Undetermined</responseCode></PrescriptionRenewalResponse>";
		}

		generatedXML += "</NCScript>";

		resp.setStatus(ServiceResponseStatus.SUCCESS);
		resp.setResponse("Data has been saved successfully.");
		resp.setResult(generatedXML);

		// System.out.println(generatedXML);
		return resp;
	}

	@Override
	public List<ORMChartAllergyView> GetPrescriptionAllergies(ORMgetPrescriptionAllergies getPresAllerg) {
		// TODO Auto-generated method stub
		int result = 0;
		newCropPreproduction objNewCrop = new newCropPreproduction();

		// System.out.println("\n Producation env is :" +
		// getPresAllerg.isRxproduction());
		NodeList nList = objNewCrop.xmlToData(getPresAllerg.isRxproduction(), "allergies",
				getPresAllerg.getRxusername(), getPresAllerg.getRxpartnername(), getPresAllerg.getRxuserpassword(),
				getPresAllerg.getPracticeid().equals("504") ? "500" : getPresAllerg.getPracticeid(),
				getPresAllerg.getRxsiteid(), getPresAllerg.getPatientid(), "", getPresAllerg.getPrescreqstatus(),
				getPresAllerg.getPresreqsubstatus(), getPresAllerg.getLoginuserid(),
				getPresAllerg.getPatinfousertype());

		// List<ORMChartAllergies> oldAllergies = getChartPresc ?
		// (List<ORMChartAllergies>) getChartAllergy(getPresAllerg.getPracticeID(),
		// getPresAllerg.getPatientID(), "") : null;
		List<ORMChartAllergies> oldAllergies = null;
		if (getChartPresc) {
			SearchCriteria searchcriteria = new SearchCriteria();
			searchcriteria.setPractice_id(Long.parseLong(getPresAllerg.getPracticeid()));
			List<SearchCriteriaParamList> param_list = new ArrayList<>();
			SearchCriteriaParamList obj = new SearchCriteriaParamList();
			obj.setName("patient_id");
			obj.setValue(getPresAllerg.getPatientid());
			param_list.add(obj);
			obj = new SearchCriteriaParamList();
			obj.setName("criteria");
			obj.setValue("");
			param_list.add(obj);
			searchcriteria.setParam_list(param_list);

			oldAllergies = getPatAllergies(searchcriteria);
		}

		if (nList != null) {
			String Query = "update chart_allergies set deleted=1 where patient_id = '" + getPresAllerg.getPatientid()
					+ "' and isnull(is_eallergy,0) = 1 and practice_id = '" + getPresAllerg.getPracticeid() + "'";
			db.ExecuteUpdateQuery(Query);
			result = saveEAllergies(nList, getPresAllerg.getPracticeid(), getPresAllerg.getPatientid(),
					getPresAllerg.getLoginuser(), getPresAllerg.getClientdatetimestring(), oldAllergies);
		}

		if (result > 0)
			return getChartAllergyView(getPresAllerg.getPatientid());

		return null;
	}

	boolean getChartPresc = true; // need to implement its logic
	private Boolean isNewCropPreproduction = false;

	private int saveEAllergies(NodeList nList, String practiceID, String patientID, String LoggedInUser,
			String ClientDate, List<ORMChartAllergies> currAllergies) {
		int result = 1;
		try {

			if (nList != null && nList.getLength() > 0) {
				Long AllergiesID = db.IDGenerator("chart_allergies", Long.parseLong(practiceID), nList.getLength());

				ORMChartAllergies objChartAllergies;

				for (int row = 0; row < nList.getLength(); row++) {
					Node nNode = nList.item(row);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						objChartAllergies = new ORMChartAllergies();
						objChartAllergies.setChart_allergies_id(AllergiesID.toString());
						objChartAllergies.setExternal_composite_id(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("CompositeAllergyID"))
										&& eElement.getElementsByTagName("CompositeAllergyID").getLength() > 0
												? eElement.getElementsByTagName("CompositeAllergyID").item(0)
														.getTextContent()
												: "");
						objChartAllergies.setExternal_allergy_id(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("AllergyId"))
										&& eElement.getElementsByTagName("AllergyId").getLength() > 0
												? eElement.getElementsByTagName("AllergyId").item(0).getTextContent()
												: "");
						objChartAllergies.setDescription(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("AllergyName"))
										&& eElement.getElementsByTagName("AllergyName").getLength() > 0
												? eElement.getElementsByTagName("AllergyName").item(0).getTextContent()
												: "");
						objChartAllergies
								.setStatus(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Status"))
										&& eElement.getElementsByTagName("Status").getLength() > 0
												? eElement.getElementsByTagName("Status").item(0).getTextContent()
												: "");
						objChartAllergies.setSeverity(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("AllergySeverityName"))
										&& eElement.getElementsByTagName("AllergySeverityName").getLength() > 0
												? eElement.getElementsByTagName("AllergySeverityName").item(0)
														.getTextContent()
												: "");
						objChartAllergies.setConcept_type(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("ConceptType"))
										&& eElement.getElementsByTagName("ConceptType").getLength() > 0
												? eElement.getElementsByTagName("ConceptType").item(0).getTextContent()
												: "");
						objChartAllergies.setNotes(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("AllergyNotes"))
										&& eElement.getElementsByTagName("AllergyNotes").getLength() > 0
												? eElement.getElementsByTagName("AllergyNotes").item(0).getTextContent()
												: "");
						objChartAllergies.setOnset_date(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("OnsetDateCCYYMMDD"))
										&& eElement.getElementsByTagName("OnsetDateCCYYMMDD").getLength() > 0
												? eElement.getElementsByTagName("OnsetDateCCYYMMDD").item(0)
														.getTextContent().split("T")[0]
												: null);
						objChartAllergies.setExternal_allergy_date(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("DateTimeStamp"))
										&& eElement.getElementsByTagName("DateTimeStamp").getLength() > 0
												? eElement.getElementsByTagName("DateTimeStamp").item(0)
														.getTextContent().split("T")[0]
												: null);
						objChartAllergies
								.setRxnorm(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("rxcui"))
										&& eElement.getElementsByTagName("rxcui").getLength() > 0
												? eElement.getElementsByTagName("rxcui").item(0).getTextContent()
												: "");
						objChartAllergies.setIs_eallergy(true);
						objChartAllergies.setPractice_id(practiceID);
						objChartAllergies.setPatient_id(patientID);
						// -----------------
						objChartAllergies.setCreated_user(LoggedInUser);
						objChartAllergies.setClient_date_created(ClientDate);
						objChartAllergies.setModified_user(LoggedInUser);
						objChartAllergies.setClient_date_modified(ClientDate);
						if (!getChartPresc) {
							objChartAllergies.setDate_created(ClientDate);
							objChartAllergies.setAlergy_date(ClientDate);
						} else {
							objChartAllergies.setDate_created(GeneralOperations.CurrentDateTime());
							objChartAllergies.setAlergy_date(GeneralOperations.CurrentDateTime());
						}
						objChartAllergies.setDate_modified(GeneralOperations.CurrentDateTime());
						// ---Updating dates
						if (GeneralOperations.isNotNullorEmpty(currAllergies) && currAllergies.size() > 0) {
							for (ORMChartAllergies objOldAllergy : currAllergies) {
								if ((GeneralOperations.isNotNullorEmpty(objOldAllergy.getPatient_id())
										&& objOldAllergy.getPatient_id().equals(objChartAllergies.getPatient_id()))
										&& (GeneralOperations.isNotNullorEmpty(objOldAllergy.getExternal_allergy_id())
												&& objOldAllergy.getExternal_allergy_id()
														.equals(objChartAllergies.getExternal_allergy_id()))
										&& (GeneralOperations.isNotNullorEmpty(objOldAllergy.getExternal_composite_id())
												&& objOldAllergy.getExternal_composite_id()
														.equals(objChartAllergies.getExternal_composite_id()))) {
									objChartAllergies.setAlergy_date(objOldAllergy.getDate_created());
									objChartAllergies.setDate_created(objOldAllergy.getDate_created());
									objChartAllergies.setClient_date_created(objOldAllergy.getClient_date_created());
									objChartAllergies.setCreated_user(objOldAllergy.getCreated_user());
								}
							}
						}
						AllergiesID = AllergiesID + 1;
						db.SaveEntity(objChartAllergies, Operation.ADD);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	// **** End of Prescripton XML *****//

	@Override
	public List<ORMChartPrescriptionView> getPatPrescriptionServer(ORMgetPrescriptionAllergies getPresAllerg) {
		// TODO Auto-generated method stub
		int result = 0;
		// String prescreqArchiveStatus = "";
		try {
			System.out.print("\n in getPatPrescriptionServer  :");
			System.out.print("\n in getPatPrescriptionServer  is production :" + getPresAllerg.isRxproduction());
			// System.out.println("client date " + getPresAllerg.getClientdatetimestring());
			// System.out.println("login user id getLoginuserid : " +
			// getPresAllerg.getLoginuserid());

			// -------------UnArchived /Active Meds----------
			newCropPreproduction objNewCrop = new newCropPreproduction();
			// prescreqArchiveStatus = "%";

			NodeList nList = objNewCrop.xmlToData(getPresAllerg.isRxproduction(), "prescription",
					getPresAllerg.getRxusername(), getPresAllerg.getRxpartnername(), getPresAllerg.getRxuserpassword(),
					getPresAllerg.getPracticeid().equals("504") ? "500" : getPresAllerg.getPracticeid(),
					getPresAllerg.getRxsiteid(), getPresAllerg.getPatientid(), "%", getPresAllerg.getPrescreqstatus(),
					getPresAllerg.getPresreqsubstatus(), getPresAllerg.getLoginuserid(),
					getPresAllerg.getPatinfousertype());

			// NodeList nList = objNewCrop.xmlToData(false,"prescription", "demo",
			// "InstantHealthCare", "demo",
			// "500", "SITE01", "50010180533", "%", "C", "%", "", "");

			// System.out.print("\n in getPatPrescriptionServer and nlist :" +
			// nList.getLength());
			if (nList != null) {
				if (!getChartPresc) {
					if (!isNewCropPreproduction) {
						String Query = "delete from chart_prescription where patient_id = '"
								+ getPresAllerg.getPatientid()
								+ "' and (isnull(is_eprescription,0) = 1 OR isnull(external_source,'') ='O') and practice_id = '"
								+ getPresAllerg.getPracticeid() + "'";
						db.ExecuteUpdateQuery(Query);
					}
					// result = saveAllpatientEPrescriptions(nList, accAccountId, LoggedInUser,
					// ClientDate); this is for all patient prescription not implemented
				} else {
					result = saveEPrescription(nList, getPresAllerg.getPracticeid(), getPresAllerg.getLoginuser(),
							getPresAllerg.getClientdatetimestring());
				}
			}
			// if(getPresAllerg.getPracticeID().equals("512"))
			// savePatAllergies(preProductionEnv, LoggedInUser, ClientDate, credName,
			// credPartner, credPassword, accAccountId, accSiteId, patreqPatientID,
			// prescreqPrescriptionStatus, presreqPrescriptionSubStatus, patinfoUserId,
			// patinfoUserType);

			if (result > 0 && getChartPresc) {
				return getChartPrescriptionView(getPresAllerg.getPatientid());
				// return getChartPrescription(accAccountId, patreqPatientID, "");
			}

			// Rmove Patient Freezed CDS Alerts **********************
			// ClinicalDecisionSupportOperation objCDS=new
			// ClinicalDecisionSupportOperation();
			// objCDS.RemovePatientFreezedAlerts("is_medication",patreqPatientID);

		} catch (Exception e) {
			e.printStackTrace();
			db.logger.error("Error=> getPatPrescriptionServer : orm getpresAllerg " + getPresAllerg.toString()
					+ " \n Error is : " + e.toString(), e);
		}

		return null;
	}

	private int saveEPrescription(NodeList nList, String practiceID, String LoggedInUser, String ClientDate) {
		int result = 1;
		try {
			System.out.print("\n in saveEPrescription and list size is :" + nList.getLength());
			if (nList != null && nList.getLength() > 0) {
				String patMostRecentChartID = "";
				// String prescriptionID = GenericOperations.IDGenerator("chart_prescription",
				// practiceID, nList.getLength());
				// HibernateTransactionUtil hibernate = HibernateTransactionUtil.getInstance();
				List<ORMFourColumGeneric> providerList = new ArrayList<>();
				ORMChartPrescription objChartPrescription = null;
				List<ORMChartPrescription> PrescriptionList = new ArrayList<>();
				String prescriptionGUIDNotToDelete = "";
				String isArchive = "";
				String external_source = "";
				String prescription_Guid = "";
				String original_prescription_Guid = "";
				String patient_id = "";
				for (int row = 0; row < nList.getLength(); row++) {
					Node nNode = nList.item(row);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;

						isArchive = GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Archive"))
								&& eElement.getElementsByTagName("Archive").getLength() > 0
										? eElement.getElementsByTagName("Archive").item(0).getTextContent()
										: "";
						external_source = GeneralOperations
								.isNotNullorEmpty(eElement.getElementsByTagName("ExternalSource"))
								&& eElement.getElementsByTagName("ExternalSource").getLength() > 0
										? eElement.getElementsByTagName("ExternalSource").item(0).getTextContent()
										: "";
						prescription_Guid = GeneralOperations
								.isNotNullorEmpty(eElement.getElementsByTagName("PrescriptionGuid"))
								&& eElement.getElementsByTagName("PrescriptionGuid").getLength() > 0
										? eElement.getElementsByTagName("PrescriptionGuid").item(0).getTextContent()
										: "";
						original_prescription_Guid = GeneralOperations
								.isNotNullorEmpty(eElement.getElementsByTagName("OriginalPrescriptionGuid"))
								&& eElement.getElementsByTagName("OriginalPrescriptionGuid").getLength() > 0
										? eElement.getElementsByTagName("OriginalPrescriptionGuid").item(0)
												.getTextContent()
										: "";
						patient_id = GeneralOperations
								.isNotNullorEmpty(eElement.getElementsByTagName("ExternalPatientID"))
								&& eElement.getElementsByTagName("ExternalPatientID").getLength() > 0
										? eElement.getElementsByTagName("ExternalPatientID").item(0).getTextContent()
										: "";

						if (isArchive.equalsIgnoreCase("Y")) {
							int CustomUpdate = db.ExecuteUpdateQuery(
									" update chart_prescription set archive = 'Y', external_source = '"
											+ external_source + "' , prescription_guid = '" + prescription_Guid
											+ "', original_prescription_guid = '" + original_prescription_Guid
											+ "',date_modified = getdate(),client_date_modified='" + ClientDate
											+ "',modified_user = '" + LoggedInUser + "'  where patient_id = '"
											+ patient_id + "' " + " and original_prescription_guid = '"
											+ original_prescription_Guid + "' ");

							if (CustomUpdate > 0) {
								prescriptionGUIDNotToDelete += "'" + original_prescription_Guid + "',";
								continue; // continue to next record.
							}
						}
						// else {
						// PrescriptionList.add(objChartPrescription);
						// }

						objChartPrescription = new ORMChartPrescription();
						// objChartPrescription.setChart_prescription_id(prescriptionID);
						objChartPrescription.setPatient_GUID(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PatientGUID"))
										&& eElement.getElementsByTagName("PatientGUID").getLength() > 0
												? eElement.getElementsByTagName("PatientGUID").item(0).getTextContent()
												: "");
						objChartPrescription
								.setDrug_id(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("DrugID"))
										&& eElement.getElementsByTagName("DrugID").getLength() > 0
												? eElement.getElementsByTagName("DrugID").item(0).getTextContent()
												: "");
						objChartPrescription.setDrug_type_id(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("DrugTypeID"))
										&& eElement.getElementsByTagName("DrugTypeID").getLength() > 0
												? eElement.getElementsByTagName("DrugTypeID").item(0).getTextContent()
												: "");
						objChartPrescription.setDrug_name(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("DrugName"))
										&& eElement.getElementsByTagName("DrugName").getLength() > 0
												? eElement.getElementsByTagName("DrugName").item(0).getTextContent()
												: "");
						objChartPrescription.setDrug_info(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("DrugInfo"))
										&& eElement.getElementsByTagName("DrugInfo").getLength() > 0
												? eElement.getElementsByTagName("DrugInfo").item(0).getTextContent()
												: "");
						objChartPrescription.setStart_date(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PrescriptionDate"))
										&& eElement.getElementsByTagName("PrescriptionDate").getLength() > 0
												? eElement.getElementsByTagName("PrescriptionDate").item(0)
														.getTextContent().split("T")[0]
												: "");
						// objChartPrescription.setEnd_date(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Strength"))
						// && eElement.getElementsByTagName("Strength").getLength() > 0 ?
						// eElement.getElementsByTagName("ExternalAccountID").item(0).getTextContent():
						// "" );
						objChartPrescription.setIssued_date(GeneralOperations
								.isNotNullorEmpty(eElement.getElementsByTagName("PrescriptionTimestamp"))
								&& eElement.getElementsByTagName("PrescriptionTimestamp").getLength() > 0
										? eElement.getElementsByTagName("PrescriptionTimestamp").item(0)
												.getTextContent().split("T")[0]
										: "");
						objChartPrescription.setStrength(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Strength"))
										&& eElement.getElementsByTagName("Strength").getLength() > 0
												? eElement.getElementsByTagName("Strength").item(0).getTextContent()
												: "");
						objChartPrescription.setStrength_UOM(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("StrengthUOM"))
										&& eElement.getElementsByTagName("StrengthUOM").getLength() > 0
												? eElement.getElementsByTagName("StrengthUOM").item(0).getTextContent()
												: "");
						objChartPrescription.setTake(GeneralOperations
								.isNotNullorEmpty(eElement.getElementsByTagName("DosageNumberDescription"))
								&& eElement.getElementsByTagName("DosageNumberDescription").getLength() > 0
										? eElement.getElementsByTagName("DosageNumberDescription").item(0)
												.getTextContent()
										: "");
						objChartPrescription.setDosage_form(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("DosageForm"))
										&& eElement.getElementsByTagName("DosageForm").getLength() > 0
												? eElement.getElementsByTagName("DosageForm").item(0).getTextContent()
												: "");
						objChartPrescription
								.setRoute(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Route"))
										&& eElement.getElementsByTagName("Route").getLength() > 0
												? eElement.getElementsByTagName("Route").item(0).getTextContent()
												: "");
						objChartPrescription.setDays_supply(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("DaysSupply"))
										&& eElement.getElementsByTagName("DaysSupply").getLength() > 0
												? eElement.getElementsByTagName("DaysSupply").item(0).getTextContent()
												: "");
						objChartPrescription.setDosage_frequency(GeneralOperations
								.isNotNullorEmpty(eElement.getElementsByTagName("DosageFrequencyDescription"))
								&& eElement.getElementsByTagName("DosageFrequencyDescription").getLength() > 0
										? eElement.getElementsByTagName("DosageFrequencyDescription").item(0)
												.getTextContent()
										: "");
						objChartPrescription.setQuantity(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Dispense"))
										&& eElement.getElementsByTagName("Dispense").getLength() > 0
												? eElement.getElementsByTagName("Dispense").item(0).getTextContent()
												: "");
						objChartPrescription.setPrn(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("TakeAsNeeded"))
										&& eElement.getElementsByTagName("TakeAsNeeded").getLength() > 0
												? eElement.getElementsByTagName("TakeAsNeeded").item(0).getTextContent()
												: "");
						objChartPrescription.setNum_of_refills_allowed(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Refills"))
										&& eElement.getElementsByTagName("Refills").getLength() > 0
												? eElement.getElementsByTagName("Refills").item(0).getTextContent()
												: "");
						objChartPrescription
								.setStatus(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Status"))
										&& eElement.getElementsByTagName("Status").getLength() > 0
												? eElement.getElementsByTagName("Status").item(0).getTextContent()
												: "");
						objChartPrescription.setSub_status(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("SubStatus"))
										&& eElement.getElementsByTagName("SubStatus").getLength() > 0
												? eElement.getElementsByTagName("SubStatus").item(0).getTextContent()
												: "");

						// objChartPrescription.setExternal_source(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("ExternalSource"))
						// && eElement.getElementsByTagName("ExternalSource").getLength() > 0 ?
						// eElement.getElementsByTagName("ExternalSource").item(0).getTextContent() :
						// "");
						// objChartPrescription.setArchive(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Archive"))
						// && eElement.getElementsByTagName("Archive").getLength() > 0 ?
						// eElement.getElementsByTagName("Archive").item(0).getTextContent() : "");
						// objChartPrescription.setPrescription_Guid(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PrescriptionGuid"))
						// && eElement.getElementsByTagName("PrescriptionGuid").getLength() > 0 ?
						// eElement.getElementsByTagName("PrescriptionGuid").item(0).getTextContent() :
						// "");
						// objChartPrescription.setPatient_id(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("ExternalPatientID"))
						// && eElement.getElementsByTagName("ExternalPatientID").getLength() > 0 ?
						// eElement.getElementsByTagName("ExternalPatientID").item(0).getTextContent() :
						// "");
						objChartPrescription.setOriginal_prescription_Guid(original_prescription_Guid);
						objChartPrescription.setArchive(isArchive);
						objChartPrescription.setExternal_source(external_source);
						objChartPrescription.setPrescription_Guid(prescription_Guid);
						objChartPrescription.setPatient_id(patient_id);

						objChartPrescription.setFormularytype_id(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("FormularyTypeID"))
										&& eElement.getElementsByTagName("FormularyTypeID").getLength() > 0
												? eElement.getElementsByTagName("FormularyTypeID").item(0)
														.getTextContent()
												: "");
						objChartPrescription.setDiagnosis(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Diagnosis"))
										&& eElement.getElementsByTagName("Diagnosis").getLength() > 0
												? eElement.getElementsByTagName("Diagnosis").item(0).getTextContent()
												: "");
						objChartPrescription.setDiagnosis_source(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("DiagnosisSource"))
										&& eElement.getElementsByTagName("DiagnosisSource").getLength() > 0
												? eElement.getElementsByTagName("DiagnosisSource").item(0)
														.getTextContent()
												: "");
						objChartPrescription.setSig_text(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PatientFriendlySIG"))
										&& eElement.getElementsByTagName("PatientFriendlySIG").getLength() > 0
												? eElement.getElementsByTagName("PatientFriendlySIG").item(0)
														.getTextContent()
												: "");
						// ---Newcrop webservice error... They are saving Additional Sig in Prescription
						// Notes
						objChartPrescription.setModified_sig(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PrescriptionNotes"))
										&& eElement.getElementsByTagName("PrescriptionNotes").getLength() > 0
												? eElement.getElementsByTagName("PrescriptionNotes").item(0)
														.getTextContent()
												: "");
						objChartPrescription.setPrint_leaflet(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PrintLeaflet"))
										&& eElement.getElementsByTagName("PrintLeaflet").getLength() > 0
												? eElement.getElementsByTagName("PrintLeaflet").item(0).getTextContent()
												: "");
						objChartPrescription.setDeaclass_code(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("DeaClassCode"))
										&& eElement.getElementsByTagName("DeaClassCode").getLength() > 0
												? eElement.getElementsByTagName("DeaClassCode").item(0).getTextContent()
												: "");
						objChartPrescription.setFinalstatus_type(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("FinalStatusType"))
										&& eElement.getElementsByTagName("FinalStatusType").getLength() > 0
												? eElement.getElementsByTagName("FinalStatusType").item(0)
														.getTextContent()
												: "");
						objChartPrescription.setPharmacy_info(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PharmacyFullInfo"))
										&& eElement.getElementsByTagName("PharmacyFullInfo").getLength() > 0
												? eElement.getElementsByTagName("PharmacyFullInfo").item(0)
														.getTextContent()
												: "");
						objChartPrescription.setPharmacy_ncdpdp(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PharmacyNCPDP"))
										&& eElement.getElementsByTagName("PharmacyNCPDP").getLength() > 0
												? eElement.getElementsByTagName("PharmacyNCPDP").item(0)
														.getTextContent()
												: "");
						// objChartPrescription.setTakes_no_meds_flag(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Strength"))
						// && eElement.getElementsByTagName("Strength").getLength() > 0 ?
						// eElement.getElementsByTagName("ExternalAccountID").item(0).getTextContent():
						// "" );
						// objChartPrescription.setDrug_history_viewed_flag(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("Strength"))
						// && eElement.getElementsByTagName("Strength").getLength() > 0 ?
						// eElement.getElementsByTagName("ExternalAccountID").item(0).getTextContent():
						// "" );
						objChartPrescription.setPharmacy_type(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PharmacyType"))
										&& eElement.getElementsByTagName("PharmacyType").getLength() > 0
												? eElement.getElementsByTagName("PharmacyType").item(0).getTextContent()
												: "");
						objChartPrescription.setPharmacydetail_type(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PharmacyDetailType"))
										&& eElement.getElementsByTagName("PharmacyDetailType").getLength() > 0
												? eElement.getElementsByTagName("PharmacyDetailType").item(0)
														.getTextContent()
												: "");
						objChartPrescription.setFinaldestination_type(GeneralOperations
								.isNotNullorEmpty(eElement.getElementsByTagName("FinalDestinationType"))
								&& eElement.getElementsByTagName("FinalDestinationType").getLength() > 0
										? eElement.getElementsByTagName("FinalDestinationType").item(0).getTextContent()
										: "");

						if (GeneralOperations.isNotNullorEmpty(objChartPrescription.getExternal_source())
								&& objChartPrescription.getExternal_source().equals("O")) {
							objChartPrescription.setIs_eprescription("0");
						} else {
							objChartPrescription.setIs_eprescription("1");
						}

						objChartPrescription.setPharmacist_notes(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PharmacistNotes"))
										&& eElement.getElementsByTagName("PharmacistNotes").getLength() > 0
												? eElement.getElementsByTagName("PharmacistNotes").item(0)
														.getTextContent()
												: "");
						objChartPrescription.setProvider_id(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("ExternalPhysicianID"))
										&& eElement.getElementsByTagName("ExternalPhysicianID").getLength() > 0
												? eElement.getElementsByTagName("ExternalPhysicianID").item(0)
														.getTextContent()
												: "");
						objChartPrescription.setProvider_name(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PhysicianName"))
										&& eElement.getElementsByTagName("PhysicianName").getLength() > 0
												? eElement.getElementsByTagName("PhysicianName").item(0)
														.getTextContent().toString().trim()
												: "");
						objChartPrescription.setLocation_name(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("LocationName"))
										&& eElement.getElementsByTagName("LocationName").getLength() > 0
												? eElement.getElementsByTagName("LocationName").item(0).getTextContent()
														.toString().trim()
												: "");

						objChartPrescription.setChart_id(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("EncounterIdentifier"))
										&& eElement.getElementsByTagName("EncounterIdentifier").getLength() > 0
												? eElement.getElementsByTagName("EncounterIdentifier").item(0)
														.getTextContent()
												: "");

						// --Incase of 504 (Accident Practice of Procare) sending practice id 504
						if (practiceID.equals("504")) {
							objChartPrescription.setPractice_id(practiceID);
						} else {
							objChartPrescription.setPractice_id(GeneralOperations
									.isNotNullorEmpty(eElement.getElementsByTagName("ExternalAccountID"))
									&& eElement.getElementsByTagName("ExternalAccountID").getLength() > 0
											? eElement.getElementsByTagName("ExternalAccountID").item(0)
													.getTextContent()
											: "");
						}
						// --Rxnorm Changes
						objChartPrescription
								.setRxnorm(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("rxcui"))
										&& eElement.getElementsByTagName("rxcui").getLength() > 0
												? eElement.getElementsByTagName("rxcui").item(0).getTextContent()
												: "");
						objChartPrescription.setDrug_generic_name(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("GenericName"))
										&& eElement.getElementsByTagName("GenericName").getLength() > 0
												? eElement.getElementsByTagName("GenericName").item(0).getTextContent()
												: "");
						// --
						// --substitution allowed -- DAW/DNS checkbox at newcrop compserx page.
						objChartPrescription.setSubstitution_allowed(
								GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("DispenseAsWritten"))
										&& eElement.getElementsByTagName("DispenseAsWritten").getLength() > 0
												? eElement.getElementsByTagName("DispenseAsWritten").item(0)
														.getTextContent()
												: "");
						// --
						objChartPrescription.setCreated_user(LoggedInUser);
						objChartPrescription.setDate_created(DateTimeUtil.getCurrentDateTime());
						objChartPrescription.setClient_date_created(ClientDate);
						objChartPrescription.setModified_user(LoggedInUser);
						objChartPrescription.setClient_date_modified(ClientDate);
						objChartPrescription.setDate_modified(DateTimeUtil.getCurrentDateTime());

						// --if Chart Id is Empty

						if (GeneralOperations.isNullorEmpty(objChartPrescription.getChart_id())
								|| objChartPrescription.getChart_id().equals("0")) {
							if (patMostRecentChartID.equals("")) {
								patMostRecentChartID = getRecentPatientChartID(objChartPrescription.getPatient_id());
							}
							objChartPrescription.setChart_id(patMostRecentChartID);
						} // -END

						// --if provider id is missing.
						if (GeneralOperations.isNullorEmpty(objChartPrescription.getProvider_id())) {
							if (providerList.size() < 1) {
								providerList = getNewCropProviderInfo(practiceID);
							}

							objChartPrescription.setProvider_id(findProviderByName(providerList, objChartPrescription
									.getProvider_name().trim().replaceAll(" ", "").replaceAll(",", "")));
						} // --END

						// prescriptionID = String.valueOf(Long.parseLong(prescriptionID) + 1);

						// if (objChartPrescription.getArchive().equals("Y")) {
						// int CustomUpdate = hibernate.CustomUpdate(" update chart_prescription set
						// archive = 'Y', external_source = '" +
						// objChartPrescription.getExternal_source() + "' , prescription_guid = '" +
						// objChartPrescription.getPrescription_Guid() + "', original_prescription_guid
						// = '" + objChartPrescription.getOriginal_prescription_Guid() +
						// "',date_modified = getdate(),client_date_modified='" + ClientDate +
						// "',modified_user = '" + LoggedInUser + "' where patient_id = '" +
						// objChartPrescription.getPatient_id() + "' and original_prescription_guid = '"
						// + objChartPrescription.getOriginal_prescription_Guid() + "' ");
						// if (CustomUpdate < 1) {
						// PrescriptionList.add(objChartPrescription);
						// } else {
						// prescriptionGUID += "'" +
						// objChartPrescription.getOriginal_prescription_Guid() + "',";
						// }
						// } else {
						// PrescriptionList.add(objChartPrescription);
						// }

						PrescriptionList.add(objChartPrescription);
					}
				}
				//
				if (PrescriptionList.size() > 0) {

					String query = "delete from chart_prescription where (original_prescription_guid not in ("
							+ (GeneralOperations.isNotNullorEmpty(prescriptionGUIDNotToDelete) == true
									? prescriptionGUIDNotToDelete.substring(0, prescriptionGUIDNotToDelete.length() - 1)
									: "''")
							+ ") OR [status] = 'p' ) and patient_id = '" + PrescriptionList.get(0).getPatient_id()
							+ "' and (isnull(is_eprescription,0) = 1 OR isnull(external_source,'') ='O') and practice_id = '"
							+ practiceID + "'";
					db.ExecuteUpdateQuery(query);

					long prescriptionID = db.IDGenerator("chart_prescription", Long.parseLong(practiceID),
							PrescriptionList.size());

					// System.out.println("in save section total prescription are :" +
					// PrescriptionList.size());
					for (ORMChartPrescription objORM : PrescriptionList) {
						// session.save(obj);
						objORM.setChart_prescription_id(Long.toString(prescriptionID));
						db.SaveEntity(objORM, Operation.ADD);
						// hibernate.add(objORM); Check with omer
						prescriptionID = prescriptionID + 1;
					}
					// hibernate.addWithTransaction(PrescriptionList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	private String getRecentPatientChartID(String patient_id) {
		return db.getQuerySingleResult("Select top 1 chart_id as scalar_value from patient_chart where patient_id = '"
				+ patient_id + "' and isnull(deleted,0)<>1 order by convert(date,visit_date) desc");
	}

	public List<ORMFourColumGeneric> getNewCropProviderInfo(String practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetProviderLastFirstMname", ORMFourColumGeneric.class, lstParam);
	}

	public String findProviderByName(List<ORMFourColumGeneric> lstProvider, String Providername) {

		for (ORMFourColumGeneric objPrv : lstProvider) {
			String prvLFname = objPrv.getCol4() + objPrv.getCol2();
			String prvLMFname = objPrv.getCol4() + objPrv.getCol3() + objPrv.getCol2();
			String prvFLname = objPrv.getCol2() + objPrv.getCol4();
			String prvFMLname = objPrv.getCol2() + objPrv.getCol3() + objPrv.getCol4();

			if (prvLFname.equals(Providername) || prvLMFname.equals(Providername) || prvFLname.equals(Providername)
					|| prvFMLname.equals(Providername)) {
				return objPrv.getCol1();
			}
		}
		return "";
	}

	@Override
	public int signEncounter(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String sign_type = "";
		String user = "";
		String signed_provider_id = "";
		String signed_provider_name = "";
		String system_ip = "";
		String id = "";
		String pat_id = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("sign_type")) {
					sign_type = pram.getValue();
				} else if (pram.getName().equals("user")) {
					user = pram.getValue();
				} else if (pram.getName().equals("signed_provider_id")) {
					signed_provider_id = pram.getValue();
				} else if (pram.getName().equals("system_ip")) {
					system_ip = pram.getValue();
				} else if (pram.getName().equals("chart_id")) {
					id = pram.getValue();
				} else if (pram.getName().equals("patient_id")) {
					pat_id = pram.getValue();
				}

				else if (pram.getName().equals("signed_provider_name")) {
					signed_provider_name = pram.getValue();
				}
			}
		}
		String Query = "";
		String queryHealthCheck = "";
		if (sign_type.equals("sign")) {
			Query = "update patient_chart set complete=1,complete_by='" + user + "',complete_date='"
					+ DateTimeUtil.getCurrentDateTime() + "',Signed=1,signed_by='" + user + "',signed_date='"
					+ DateTimeUtil.getCurrentDateTime() + "',signed_provider_id='" + signed_provider_id
					+ "', system_ip ='" + system_ip + "' where chart_id='" + id + "'";
			queryHealthCheck = "update patient_health_check set provider1_sign_info='SIGNED BY : "
					+ signed_provider_name + "    DATED:" + DateTimeUtil.getCurrentDateTime() + "', system_ip ='"
					+ system_ip + "' where patient_id='" + pat_id + "' and chart_id='" + id
					+ "' and isnull(deleted,0)<>1";
		} else if (sign_type.equals("co-sign")) {
			Query = "update patient_chart set co_signed=1,co_signed_by='" + user + "',co_signed_date='"
					+ DateTimeUtil.getCurrentDateTime() + "',co_signed_provider_id='" + signed_provider_id
					+ "', system_ip ='" + system_ip + "' where chart_id='" + id + "'";
			queryHealthCheck = "update patient_health_check set provider2_sign_info='CO-SIGNED BY : "
					+ signed_provider_name + "    DATED:" + DateTimeUtil.getCurrentDateTime() + "', system_ip ='"
					+ system_ip + "' where patient_id='" + pat_id + "' and chart_id='" + id
					+ "' and isnull(deleted,0)<>1";
		} else if (sign_type.equals("unsign")) {
			Query = "update patient_chart set complete=0,complete_by='',complete_date=NULL,Signed=0,signed_by='',signed_date=NULL,signed_provider_id='',co_signed=0,co_signed_by='',co_signed_date=NULL,co_signed_provider_id='',modified_user='"
					+ user + "' ,system_ip ='" + system_ip + "' where chart_id='" + id + "'";
			queryHealthCheck = "update patient_health_check set provider1_sign_info='',provider2_sign_info='',modified_user='"
					+ user + "', system_ip ='" + system_ip + "' where patient_id='" + pat_id + "' and chart_id='" + id
					+ "' and isnull(deleted,0)<>1";
		}
		db.ExecuteUpdateQuery(queryHealthCheck);
		return db.ExecuteUpdateQuery(Query);
	}

	@Override
	public List<ORMChartAmendments_View> getChartAmendmentsView(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		List<ORMChartAmendments_View> lstData = db.getStoreProcedureData("spGetchartAmendments_View",
				ORMChartAmendments_View.class, lstParam);

		return lstData;
	}

	@Override
	public ORMChartAmendmentsSave getChartAmendmentsDetail(String id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("amendment_id", id.toString(), String.class, ParameterMode.IN));

		List<ORMChartAmendmentsSave> lstData = db.getStoreProcedureData("spGetchartAmendments_Detail",
				ORMChartAmendmentsSave.class, lstParam);

		if (!lstData.isEmpty())
			return lstData.get(0);
		else
			return null;
	}

	@Override
	public Long saveChartAmendments(ORMChartAmendmentsSave obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getAmendment_id() == null) // New
		{
			obj.setAmendment_id(db.IDGenerator("chart_amendments", Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		if (result > 0) {
			return Long.parseLong(obj.getAmendment_id());
		} else {
			return (long) 0;
		}
	}

	// IMMUNIZATION
	@Override
	public List<ORMChartImmunizationSummaryGet> getChartImmunizationSummary(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spGetChartImmunizationSummaryView", ORMChartImmunizationSummaryGet.class,
				lstParam);
	}

	@Override
	public List<ORMChartImunizationVISGet> getChartImmunizationVIS(Long chart_immunization_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_immunization_id", chart_immunization_id.toString(), String.class,
				ParameterMode.IN));

		return db.getStoreProcedureData("getChartImmunizationVIS", ORMChartImunizationVISGet.class, lstParam);
	}

	@Override
	public ORMChartImmunizationGet getChartImmunizationById(Long chart_immunization_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_immunization_id", chart_immunization_id.toString(), String.class,
				ParameterMode.IN));

		List<ORMChartImmunizationGet> lst = db.getStoreProcedureData("spGetSingleChartImmunizationInfo",
				ORMChartImmunizationGet.class, lstParam);

		if (lst != null && lst.size() > 0)
			return (ORMChartImmunizationGet) lst.get(0);
		else
			return null;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveChartImmunization(
			Wrapper_ChartImmunizationSave wrapperChartImmunizationSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ORMChartImmunizationSave chartImmunizationSave = wrapperChartImmunizationSave.getChartImmunizationSave();
		List<ORMChartImmunizationVISSave> lstChartImmunizationVISSave = wrapperChartImmunizationSave
				.getLstChartImmunizationVISSave();
		List<Long> lstVISIdsDeleted = wrapperChartImmunizationSave.getLstVISIdsDeleted();

		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		if (chartImmunizationSave != null) {

			chartImmunizationSave.setDate_modified(serverDateTime);

			if (chartImmunizationSave.getChart_immunization_id() == null
					|| chartImmunizationSave.getChart_immunization_id() <= 0) {
				chartImmunizationSave.setChart_immunization_id(
						db.IDGenerator("chart_immunization", chartImmunizationSave.getPractice_id()));
				chartImmunizationSave.setDate_created(serverDateTime);

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, chartImmunizationSave));

				// Decrement Immunization Inventory
				if (chartImmunizationSave.getEntry_type().equalsIgnoreCase("administered")
						&& (chartImmunizationSave.getCompletion_status_code().equalsIgnoreCase("CP")
								|| chartImmunizationSave.getCompletion_status_code().equalsIgnoreCase("PA"))) {

					if (GeneralOperations.isNotNullorEmpty(chartImmunizationSave.getTrade_name())
							&& GeneralOperations.isNotNullorEmpty(chartImmunizationSave.getMvx_code())
							&& GeneralOperations.isNotNullorEmpty(chartImmunizationSave.getLot_number())
							&& GeneralOperations.isNotNullorEmpty(chartImmunizationSave.getExpiration_date())
							&& GeneralOperations.isNotNullorEmpty(wrapperChartImmunizationSave.getLocationId())) {

						String query = " update immunization_inventory set inventory= case when isnull(inventory,0) > 0 then inventory-1 else 0 end "
								+ " where isnull(deleted,0)<>1 and isnull(inventory_status,'')='ACTIVE' and trade_name='"
								+ chartImmunizationSave.getTrade_name() + "' and mvx_code='"
								+ chartImmunizationSave.getMvx_code() + "' and lot_number='"
								+ chartImmunizationSave.getLot_number()
								+ "' COLLATE SQL_Latin1_General_CP1_CS_AS and expiry_date='"
								+ chartImmunizationSave.getExpiration_date() + "' and location_id='"
								+ wrapperChartImmunizationSave.getLocationId() + "'";

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));
					}

				}

			} else {
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, chartImmunizationSave));
			}

			if (lstChartImmunizationVISSave != null) {

				for (ORMChartImmunizationVISSave ormVIS : lstChartImmunizationVISSave) {

					ormVIS.setDate_modified(serverDateTime);
					ormVIS.setChart_imm_id(chartImmunizationSave.getChart_immunization_id());

					if (ormVIS.getChart_imm_vis_id() == null || ormVIS.getChart_imm_vis_id() <= 0) {

						ormVIS.setChart_imm_vis_id(
								db.IDGenerator("chart_immunization_vis", chartImmunizationSave.getPractice_id()));
						ormVIS.setDate_created(serverDateTime);

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormVIS));
					} else {
						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormVIS));
					}

				}
			}

			if (lstVISIdsDeleted != null && lstVISIdsDeleted.size() > 0) {

				String strDelIds = "";
				for (Long delId : lstVISIdsDeleted) {
					if (strDelIds != "")
						strDelIds += ",";
					strDelIds += "'" + delId.toString() + "'";
				}

				String strDiagDelQuery = "update chart_immunization_vis set deleted=1,date_modified='" + serverDateTime
						+ "',client_date_modified='" + chartImmunizationSave.getClient_date_modified() + "',system_ip='"
						+ chartImmunizationSave.getSystem_ip() + "' where chart_imm_vis_id in (" + strDelIds + ")";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strDiagDelQuery));
			}

			result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
				resp.setResult(chartImmunizationSave.getChart_immunization_id().toString());
			}
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("No Immunization data is provided");
		}

		return resp;

	}
	// END IMMUNIZATION

	@Override
	public List<ORMGetGrowthChartInfo> getGrowthChartData(String practice_id, String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practiceID", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patientID", patient_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetGrowthChartInfo> lstData = db.getStoreProcedureData("spGetGrowthChartData",
				ORMGetGrowthChartInfo.class, lstParam);

		return lstData;
	}

	@Override
	public ORMChartSocialHisDisplayGet ORMChartSocialHisDisplayGet(Long chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));
		List<ORMChartSocialHisDisplayGet> lst = db.getStoreProcedureData("spGetChartSocialHistoryDisplay",
				ORMChartSocialHisDisplayGet.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ORMChartSocialHistoryGet getChartSocialHistDetailById(Long socialhistoryId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("socialhistory_id", socialhistoryId.toString(), String.class, ParameterMode.IN));
		List<ORMChartSocialHistoryGet> lst = db.getStoreProcedureData("spGetSocialHistoryDetail",
				ORMChartSocialHistoryGet.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveSocialHistory(ORMChartSocialHistorySave ormChartSocialHistorySave) {

		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		ormChartSocialHistorySave.setDate_modified(serverDateTime);
		if (GeneralOperations.isNullorEmpty(ormChartSocialHistorySave.getSocialhistory_id())) {

			ormChartSocialHistorySave.setSocialhistory_id(
					db.IDGenerator("chart_socialhistory", ormChartSocialHistorySave.getPractice_id()));
			ormChartSocialHistorySave.setDate_created(serverDateTime);

			result = db.SaveEntity(ormChartSocialHistorySave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormChartSocialHistorySave, Operation.EDIT);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormChartSocialHistorySave.getSocialhistory_id().toString());
		}

		return resp;

	}

	// ***** IMPLANTABLE DEVICE **** //

	@Override
	public Object getDeviceDetailFromGlobalUDIDB(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String UDI = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "UDI":
					if (pram.getValue() != "" && pram.getValue() != null) {
						UDI = pram.getValue();
						break;
					}
				}
			}
		}

		ORMGetImplantableDevicesByApi deviceobj = new ORMGetImplantableDevicesByApi();
		int responseStatus;
		// HttpURLConnection urlConn = null;
		InputStreamReader in = null;
		StringBuilder sb = new StringBuilder();

		// byte[] encodedBytes = GeneralOperations.encodeBas64(UDI.toString());
		// //Base64.getEncoder().encode(UDI.getBytes());
		// String convertedValue = Base64.getEncoder().encodeToString(encodedBytes);
		String convertedValue = GeneralOperations.encodeBas64(UDI.toString());

		// String DiUrl =
		// "http://192.168.2.65:8080/ihc-common-api/device/getDeviceWithSnomed?id=" +
		// convertedValue;

		// String DiUrl =
		// "https://instanthealthcare.net/ihc-common-api/device/getDeviceWithSnomed?id="
		// + convertedValue;
//mine before update
		// String DiUrl = ihcCommonApiEndpiont +
		// "/ihc-common-api/device/getDeviceWithSnomed?id=" + convertedValue;
		// String DiUrl = ihcCommonApiEndpiont +
		// "/ihc-common-api/device/getDeviceWithSnomed";
//updated line
		String DiUrl = "";
		DiUrl = ihcCommonApiEndpiont + "device/getDeviceWithSnomed?id=" + convertedValue;

		System.out.println("Requeted URL:" + DiUrl);
		String params = "id=" + convertedValue;
		try {
			URL url = new URL(DiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(40000); // 40 seconds time out
			// String line = "";
			String user_pass = "ihc" + ":" + "ihc@5133";
			String encoded = "";
			try {
				encoded = Base64.getEncoder().encodeToString(user_pass.getBytes()); // DatatypeConverter.printBase64Binary(user_pass.getBytes());
			} catch (Exception e) {
				throw new RuntimeException("Exception while encoding URL:--" + e);
			}
			conn.setRequestProperty("Authorization", "Basic " + encoded);

			// conn.setDoOutput(true);
			// OutputStreamWriter outputStreamWriter = new
			// OutputStreamWriter(conn.getOutputStream());
			// outputStreamWriter.write(params.toString());
			// outputStreamWriter.flush();
			// outputStreamWriter.close();

			responseStatus = conn.getResponseCode();
			if (responseStatus == 200) {
				if (conn != null && conn.getInputStream() != null) {
					in = new InputStreamReader(conn.getInputStream(), Charset.defaultCharset());
					BufferedReader bufferedReader = new BufferedReader(in);
					if (bufferedReader != null) {
						int cp;
						while ((cp = bufferedReader.read()) != -1) {
							sb.append((char) cp);
						}
						bufferedReader.close();
					}
				}
			} else {
				in = new InputStreamReader(conn.getErrorStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
				JSONObject objError = new JSONObject(sb.toString());
				String err = objError.get("error").toString();
				deviceobj.setError(err);
			}
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + DiUrl + "--" + e);
		}
		try {
			if (responseStatus == 200) {
				JSONObject objDevice = new JSONObject(sb.toString());

				// Object objRespnose;

				// https://github.com/json-path/JsonPath

				// objRespnose =
				// JsonPath.using(config).parse(output).read("$.gudid.*",Object.class);

				/*
				 * Configuration config = Configuration.builder().mappingProvider(new
				 * JacksonMappingProvider()).build(); if(response.getStatusCode()==200) {
				 * objRespnose =
				 * JsonPath.using(config).parse(output).read("$.gudid.*",Object.class); } else {
				 * objRespnose = JsonPath.using(config).parse(output).read("$",Object.class); }
				 */

				if (objDevice.getJSONObject("device").getString("response_status_code").toString().equals("200")) {
					JSONArray objDeviceBody = objDevice.getJSONObject("device").getJSONArray("response_body");
					for (int i = 0; i < objDeviceBody.length(); i++) {
						JSONObject objDeviceBodyResp = objDeviceBody.getJSONObject(i);
						if (objDeviceBodyResp.has("companyName")) {
							String companyName = objDeviceBodyResp.get("companyName").toString();
							deviceobj.setCompany_name(companyName);
						}
						if (objDeviceBodyResp.has("deviceDescription")) {
							String deviceDescription = objDeviceBodyResp.get("deviceDescription").toString();
							deviceobj.setDevice_description(deviceDescription);
						}
						if (objDeviceBodyResp.has("brandName")) {
							String brandName = objDeviceBodyResp.get("brandName").toString();
							deviceobj.setBrand_name(brandName);
						}
						if (objDeviceBodyResp.has("versionModelNumber")) {
							String versionModelNumber = objDeviceBodyResp.get("versionModelNumber").toString();
							deviceobj.setVersion_model_number(versionModelNumber);
						}
						try {
							// if
							// (objDeviceBody.getJSONObject(i).getJSONObject("identifiers").getJSONArray("identifier").length()
							// > 1) {
							if (objDeviceBody.getJSONObject(i).getJSONObject("identifiers").getString("identifier")
									.length() > 1) {
								String identifier = objDeviceBody.getJSONObject(i).getJSONObject("identifiers")
										.getString("identifier");
								// JSONArray myarray =
								// objDeviceBody.getJSONObject(i).getJSONObject("identifiers").getString("identifier");
								JSONArray myarray = new JSONArray(identifier);

								if (myarray.length() > 1) {
									for (int x = 0; x < myarray.length(); x++) {
										JSONObject myObj = myarray.getJSONObject(x);
										if (myObj.get("deviceId").toString().toLowerCase()
												.equals(UDI.toString().toLowerCase())) {
											if (myObj.has("deviceId")) {
												String deviceId = myObj.get("deviceId").toString();
												deviceobj.setDevice_id(deviceId);
											}
											if (myObj.has("deviceIdIssuingAgency")) {
												String deviceIdIssuingAgency = myObj.get("deviceIdIssuingAgency")
														.toString();
												deviceobj.setDevice_id_issuing_agency(deviceIdIssuingAgency);
											}
											break;
										}
									}
								} else {
									JSONObject objIdentifiers = objDeviceBody.getJSONObject(i)
											.getJSONObject("identifiers").getJSONObject("identifier");// .getString("deviceId")
									if (objIdentifiers.has("deviceId")) {
										String did = objIdentifiers.getString("deviceId");
										deviceobj.setDevice_id(did);
									}
								}
							}
						} catch (Exception e) {
							JSONObject objIdentifiers1 = objDeviceBody.getJSONObject(i).getJSONObject("identifiers")
									.getJSONObject("identifier");// .getString("deviceId")
							if (objIdentifiers1.has("deviceId")) {
								String did = objIdentifiers1.getString("deviceId");
								deviceobj.setDevice_id(did);
							}
							if (objIdentifiers1.has("deviceIdIssuingAgency")) {
								String deviceIdIssuingAgency = objIdentifiers1.getString("deviceIdIssuingAgency")
										.toString();
								deviceobj.setDevice_id_issuing_agency(deviceIdIssuingAgency);
							}

						}

						String labeledContainsNRL = objDeviceBody.getJSONObject(i).getString("labeledContainsNRL");
						deviceobj.setLabeled_contains_nrl(labeledContainsNRL);

						String labeledNoNRL = objDeviceBody.getJSONObject(i).getString("labeledNoNRL");
						deviceobj.setLabeled_no_nrl(labeledNoNRL);

						String MRISafetyStatus = objDeviceBody.getJSONObject(i).getString("MRISafetyStatus");
						deviceobj.setMri_safety_status(MRISafetyStatus);

						String gmdnPTName = objDeviceBody.getJSONObject(i).getJSONObject("gmdnTerms")
								.getJSONObject("gmdn").getString("gmdnPTName");
						deviceobj.setGmdn_pt_name(gmdnPTName);

						String gmdnPTDefinition = objDeviceBody.getJSONObject(i).getJSONObject("gmdnTerms")
								.getJSONObject("gmdn").getString("gmdnPTDefinition");
						deviceobj.setGmdn_pt_description(gmdnPTDefinition);

						String deviceHCTP = objDeviceBody.getJSONObject(i).getString("deviceHCTP");
						deviceobj.setDevice_hctp(deviceHCTP);

					}

					if (objDevice.getJSONObject("snomed").getString("response_status_code").toString().equals("200")) {
						JSONArray objSnomed = objDevice.getJSONObject("snomed").getJSONArray("response_body");

						for (int i = 0; i < objSnomed.length(); i++) {
							JSONObject objSnomedBodyResp = objSnomed.getJSONObject(i);
							if (objSnomedBodyResp.has("snomedCTName")) {
								String snomedCTName = objSnomedBodyResp.get("snomedCTName").toString();
								deviceobj.setSnomed_ct_description(snomedCTName);
							}
							if (objSnomedBodyResp.has("snomedIdentifier")) {
								String snomedIdentifier = objSnomedBodyResp.get("snomedIdentifier").toString();
								deviceobj.setSnomed_ct_id(snomedIdentifier);
							}
						}
					}
					if (objDevice.getJSONObject("parsed_udi").getString("response_status_code").toString()
							.equals("200")) {
						JSONObject objParseUDIBodyResp = objDevice.getJSONObject("parsed_udi")
								.getJSONObject("response_body");
						for (int i = 0; i < objParseUDIBodyResp.length(); i++) {
							if (objParseUDIBodyResp.has("issuing_agency")) {
								String issuing_agency = objParseUDIBodyResp.get("issuing_agency").toString();
								deviceobj.setDevice_id_issuing_agency(issuing_agency);
							}
							if (objParseUDIBodyResp.has("manufacturing_date")) {
								String manufacturing_date = objParseUDIBodyResp.get("manufacturing_date").toString();
								deviceobj.setManufacturing_date(manufacturing_date);
							}
							if (objParseUDIBodyResp.has("lot_number")) {
								String lot_number = objParseUDIBodyResp.get("lot_number").toString();
								deviceobj.setLot_batch_number(lot_number);
							}
							if (objParseUDIBodyResp.has("serial_number")) {
								String serial_number = objParseUDIBodyResp.get("serial_number").toString();
								deviceobj.setSerial_number(serial_number);
							}
							if (objParseUDIBodyResp.has("expiration_date")) {
								String expiration_date = objParseUDIBodyResp.get("expiration_date").toString();
								deviceobj.setExpiry_date(expiration_date);
							}
							deviceobj.setError("");
						}
					}
				}
				deviceobj.setError("");
			}
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + DiUrl + "--" + e);
		}
		List<ORMGetImplantableDevicesByApi> lstHeader = new ArrayList<>();
		lstHeader.add(deviceobj);
		return new Object[] { lstHeader };
	}

	@Override
	public List<ORMPatientImplantableDeviceGetSummary> getPatImplantableDevicesSummary(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		List<ORMPatientImplantableDeviceGetSummary> lst = db.getStoreProcedureData(
				"spGetPatientImplantabledevicesSummary", ORMPatientImplantableDeviceGetSummary.class, lstParam);

		return lst;
	}

	@Override
	public ORMPatientImplantableDeviceGetDetail getImplantableDevicesDetailById(Long implantable_device_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("implantable_device_id", implantable_device_id.toString(), String.class,
				ParameterMode.IN));
		List<ORMPatientImplantableDeviceGetDetail> lst = db.getStoreProcedureData(
				"spGetPatientImplantabledevicesDetailById", ORMPatientImplantableDeviceGetDetail.class, lstParam);

		if (lst != null && !lst.isEmpty()) {
			return (ORMPatientImplantableDeviceGetDetail) lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePatImplantDevice(ORMPatientImplantableDeviceSave obj) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getImplantable_device_id() == null) // New
		{
			obj.setImplantable_device_id(db.IDGenerator("patient_implantable_device", obj.getPractice_id()));
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(obj, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(obj.getImplantable_device_id().toString());
		}

		return resp;
	}

	@Override
	public Long saveCarePlan(ORMCarePlan obj) {
		// TODO Auto-generated method stub
		int result = 0;

		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (obj.getChart_careplanid() == null) // New
		{
			obj.setChart_careplanid(db.IDGenerator("chart_care_plan", Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		if (result > 0) {
			return Long.parseLong(obj.getChart_careplanid());
		} else {
			return (long) 0;
		}
	}

	// ***** END IMPLANTABLE DEVICE **** //

	@Override
	public ORMPatientDischargeGetSummary getPatientDischargeDispositionSummary(Long chartId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chartId.toString(), String.class, ParameterMode.IN));
		List<ORMPatientDischargeGetSummary> lst = db.getStoreProcedureData("spGetPatientDischargeDispositionSummary",
				ORMPatientDischargeGetSummary.class, lstParam);

		if (lst != null && !lst.isEmpty()) {
			return (ORMPatientDischargeGetSummary) lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ORMPatientDischargeDisposition getPatientDischargeDispositionDetail(Long dischargeId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("discharge_id", dischargeId.toString(), String.class, ParameterMode.IN));
		List<ORMPatientDischargeDisposition> lst = db.getStoreProcedureData("spGetPatientDischargeDispositionDetails",
				ORMPatientDischargeDisposition.class, lstParam);

		if (lst != null && !lst.isEmpty()) {
			return (ORMPatientDischargeDisposition) lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePatientDischargeDisposition(ORMPatientDischargeDisposition obj) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getDischarge_id() == null) // New
		{
			obj.setDischarge_id(db.IDGenerator("patient_discharge_disposition", obj.getPractice_id()));
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(obj, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(obj.getDischarge_id().toString());
		}

		return resp;
	}

	@Override
	public List<ORMHealthConcernView> getChartHealthConcernView(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));
		List<ORMHealthConcernView> lst = db.getStoreProcedureData("spGetChartCarePlan_View", ORMHealthConcernView.class,
				lstParam);

		return lst;
	}

	@Override
	public List<ORMHealthConcernViewDetail> getChartHealthConcernViewDetail(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));
		List<ORMHealthConcernViewDetail> lst = db.getStoreProcedureData("sp_getpatient_health_concern_details",
				ORMHealthConcernViewDetail.class, lstParam);

		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> SaveHealthConcern(HealthConcernSaveWrapper wrapper) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		ORMSavehealthconcern objconcern = wrapper.getConcern();
		List<ORMSavehealthconcerndetail> lstdetail = wrapper.getLst_detail();

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();
		if (objconcern != null) {
			objconcern.setDate_modified(DateTimeUtil.getCurrentDateTime());

			if (objconcern.getHealth_id() == null || objconcern.getHealth_id() == "") {
				objconcern.setHealth_id(
						db.IDGenerator("health_concern", Long.parseLong(objconcern.getPractice_id())).toString());
				objconcern.setDate_created(DateTimeUtil.getCurrentDateTime());

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objconcern));
			} else {
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, objconcern));
			}
			if (lstdetail != null && lstdetail.size() > 0) {
				for (ORMSavehealthconcerndetail objdetail : lstdetail) {
					objdetail.setDate_modified(DateTimeUtil.getCurrentDateTime());
					objdetail.setHealth_id(objconcern.getHealth_id());
					if (objdetail.getId() == null || objdetail.getId() == "") {
						objdetail.setId(
								db.IDGenerator("health_concern_details", Long.parseLong(objconcern.getPractice_id()))
										.toString());
						objdetail.setDate_created(DateTimeUtil.getCurrentDateTime());

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objdetail));
					} else {
						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, objdetail));
					}
				}
			}
			result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
				resp.setResult(objconcern.getHealth_id());
			}
		}
		return resp;
	}

	// ASSESSMENT & PLAN OF TREATMENT
	@Override
	public List<ORMAssessPlanGet> getPatientAssessmentPlan(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPatientAssessmentPlan", ORMAssessPlanGet.class, lstParam);
	}

	@Override
	public List<ORMAssessPlanAssessmentGet> getAssessPlanAssessment(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetAssessPlanAssessment", ORMAssessPlanAssessmentGet.class, lstParam);
	}

	@Override
	public List<ORMAssessPlanOfTreatementGet> getAssessPlanPOT(Long patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetAssessPlan_PlanOfTreatement", ORMAssessPlanOfTreatementGet.class,
				lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveAssessPlan(WrapperAssessPlanSave wrapper) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ORMAssessPlanSave assess_plan = wrapper.getAssess_plan();
		List<ORMAssessPlanAssessmentSave> lst_assess_plan_assessments = wrapper.getLst_assess_plan_assessments();
		List<ORMAssessPlanOfTreatementSave> lst_assess_plan_pot = wrapper.getLst_assess_plan_pot();

		List<Long> lst_assess_plan_assessments_deleted_ids = wrapper.getLst_assess_plan_assessments_deleted_ids();
		List<Long> lst_assess_plan_pot_deleted_ids = wrapper.getLst_assess_plan_pot_deleted_ids();

		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		if (assess_plan != null) {

			assess_plan.setDate_modified(serverDateTime);

			if (assess_plan.getAssess_plan_id() == null || assess_plan.getAssess_plan_id() <= 0) {
				assess_plan.setAssess_plan_id(db.IDGenerator("assess_plan", assess_plan.getPractice_id()));
				assess_plan.setDate_created(serverDateTime);

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, assess_plan));
			} else {
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, assess_plan));
			}

			if (lst_assess_plan_assessments != null) {

				for (ORMAssessPlanAssessmentSave ormAssessment : lst_assess_plan_assessments) {

					ormAssessment.setDate_modified(serverDateTime);
					ormAssessment.setAssess_plan_id(assess_plan.getAssess_plan_id());

					if (ormAssessment.getAssessment_id() == null || ormAssessment.getAssessment_id() <= 0) {

						ormAssessment.setAssessment_id(
								db.IDGenerator("assess_plan_assessment", assess_plan.getPractice_id()));
						ormAssessment.setDate_created(serverDateTime);

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormAssessment));
					} else {
						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormAssessment));
					}

				}
			}

			if (lst_assess_plan_pot != null) {

				for (ORMAssessPlanOfTreatementSave ormPOT : lst_assess_plan_pot) {

					ormPOT.setDate_modified(serverDateTime);
					ormPOT.setAssess_plan_id(assess_plan.getAssess_plan_id());

					if (ormPOT.getPlanoftreatment_id() == null || ormPOT.getPlanoftreatment_id() <= 0) {

						ormPOT.setPlanoftreatment_id(
								db.IDGenerator("assess_plan_planoftreatment", assess_plan.getPractice_id()));
						ormPOT.setDate_created(serverDateTime);

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormPOT));
					} else {
						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormPOT));
					}

				}
			}

			if (lst_assess_plan_assessments_deleted_ids != null && lst_assess_plan_assessments_deleted_ids.size() > 0) {

				String strDelIds = "";
				for (Long delId : lst_assess_plan_assessments_deleted_ids) {
					if (strDelIds != "")
						strDelIds += ",";
					strDelIds += "'" + delId.toString() + "'";
				}

				String strDiagDelQuery = "update assess_plan_assessment set deleted=1,date_modified='" + serverDateTime
						+ "',client_date_modified='" + assess_plan.getClient_date_modified() + "',system_ip='"
						+ assess_plan.getSystem_ip() + "' where assessment_id in (" + strDelIds + ")";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strDiagDelQuery));
			}

			if (lst_assess_plan_pot_deleted_ids != null && lst_assess_plan_pot_deleted_ids.size() > 0) {

				String strDelIds = "";
				for (Long delId : lst_assess_plan_pot_deleted_ids) {
					if (strDelIds != "")
						strDelIds += ",";
					strDelIds += "'" + delId.toString() + "'";
				}

				String strDiagDelQuery = "update assess_plan_planoftreatment set deleted=1,date_modified='"
						+ serverDateTime + "',client_date_modified='" + assess_plan.getClient_date_modified()
						+ "',system_ip='" + assess_plan.getSystem_ip() + "' where planoftreatment_id in (" + strDelIds
						+ ")";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strDiagDelQuery));
			}

			result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
				resp.setResult(assess_plan.getAssess_plan_id().toString());
			}
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("No Assessment & Plan of Care data is provided");
		}

		return resp;
	}

	// END ASSESSMENT & PLAN OF TREATMENT
	@Override
	public List<ORMGetTemplateText> getTemlplateText(String practice_id, String provider_id, String type) {
		// TODO Auto-generated method stub
		
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("type", type.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("provider_id",provider_id.toString().equals("-1")?"":provider_id.toString(), String.class, ParameterMode.IN));
		if(type.equals("extender"))
		{
			return db.getStoreProcedureData("spGetExtenderNotesTemplate", ORMGetTemplateText.class, lstParam);
		}
		return db.getStoreProcedureData("spGetTemplateForProvider", ORMGetTemplateText.class, lstParam);	
	}
	@Override
	public ServiceResponse<ORMKeyValue> ApplyChartTemplate(ORMChartTemplateApply obj) {
		// TODO Auto-generated method stub
		String result_id = "";
		// Reason For Visit
		if ((!obj.getRfv().equals("")) || (!obj.getHpi().equals(""))) {
			result_id = db.getQuerySingleResult(
					"select top 1 reasonforvisit_id as id from chart_reasonforvisit_hpi where patient_id='"
							+ obj.getPatient_id() + "' and chart_id='" + obj.getChart_id()
							+ "' and isnull(deleted,0)<>1 order by 1 desc");
			if (result_id.equals("")) {

				ORMChartReasonForVisit_HPI objSave = new ORMChartReasonForVisit_HPI();
				objSave.setPatient_id(obj.getPatient_id());
				objSave.setChart_id(obj.getChart_id());
				objSave.setPractice_id(obj.getPractice_id());
				objSave.setCreated_user(obj.getUser());
				objSave.setModified_user(obj.getUser());
				objSave.setClient_date_created(DateTimeUtil.getCurrentDateTime());
				objSave.setClient_date_modified(DateTimeUtil.getCurrentDateTime());
				objSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				objSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
				objSave.setHpi_detail(obj.getHpi());
				objSave.setReason_detail(obj.getRfv());

				saveupdateChartRFV_HPI(objSave);
			} else {
				db.ExecuteUpdateQuery("update chart_reasonforvisit_hpi set reason_detail='"
						+ obj.getRfv().replace("'", "`") + "',hpi_detail='" + obj.getHpi().replace("'", "`")
						+ "',modified_user='" + obj.getUser() + "',date_modified=getdate() where reasonforvisit_id='"
						+ result_id + "' ");
			}
		}
		// ROS
		if (!obj.getRos().equals("")) {
			result_id = db.getQuerySingleResult(
					"select top 1 patient_ros_id as id from patient_ros where patient_id='" + obj.getPatient_id()
							+ "' and chart_id='" + obj.getChart_id() + "' and isnull(deleted,0)<>1 order by 1 desc");
			if (result_id.equals("")) {

				ORMChartROS objSave = new ORMChartROS();
				objSave.setPatient_id(obj.getPatient_id());
				objSave.setChart_id(obj.getChart_id());
				objSave.setPractice_id(obj.getPractice_id());
				objSave.setCreated_user(obj.getUser());
				objSave.setModified_user(obj.getUser());
				objSave.setClient_date_created(DateTimeUtil.getCurrentDateTime());
				objSave.setClient_date_modified(DateTimeUtil.getCurrentDateTime());
				objSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				objSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
				objSave.setRos_detail(obj.getRos());
				saveChartRos(objSave);
			} else {
				db.ExecuteUpdateQuery("update patient_ros set ros_detail='" + obj.getRos().replace("'", "`")
						+ "',modified_user='" + obj.getUser() + "',date_modified=getdate() where patient_ros_id='"
						+ result_id + "' ");
			}
		}
		// Physical Exam
		if (!obj.getPe().equals("")) {
			result_id = db.getQuerySingleResult(
					"select top 1 patient_organ_id as id from chart_physical_exam where patient_id='"
							+ obj.getPatient_id() + "' and chart_id='" + obj.getChart_id()
							+ "' and isnull(deleted,0)<>1 order by 1 desc");
			if (result_id.equals("")) {

				ORMChartPhysicalExam objSave = new ORMChartPhysicalExam();
				objSave.setPatient_id(obj.getPatient_id());
				objSave.setChart_id(obj.getChart_id());
				objSave.setPractice_id(obj.getPractice_id());
				objSave.setCreated_user(obj.getUser());
				objSave.setModified_user(obj.getUser());
				objSave.setClient_date_created(DateTimeUtil.getCurrentDateTime());
				objSave.setClient_date_modified(DateTimeUtil.getCurrentDateTime());
				objSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				objSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
				objSave.setPe_detail(obj.getPe());
				savePatientPhysicalExam(objSave);
			} else {
				db.ExecuteUpdateQuery("update chart_physical_exam set pe_detail='" + obj.getPe().replace("'", "`")
						+ "',modified_user='" + obj.getUser() + "',date_modified=getdate() where patient_organ_id='"
						+ result_id + "' ");
			}
		}
		// PMH
		if (!obj.getPmh().equals("")) {

			ORMChartPMH objSave = new ORMChartPMH();
			objSave.setPatient_id(obj.getPatient_id());
			objSave.setChart_id(obj.getChart_id());
			objSave.setPractice_id(obj.getPractice_id());
			objSave.setCreated_user(obj.getUser());
			objSave.setModified_user(obj.getUser());
			objSave.setClient_date_created(DateTimeUtil.getCurrentDateTime());
			objSave.setClient_date_modified(DateTimeUtil.getCurrentDateTime());
			objSave.setDate_created(DateTimeUtil.getCurrentDateTime());
			objSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			objSave.setNotes(obj.getPmh());
			objSave.setId(null);

			savePMH(objSave);
		}
		if (!obj.getPlan().equals("")) {

			ORMSavePlanOfCare objSave = new ORMSavePlanOfCare();
			objSave.setPatient_id(obj.getPatient_id());
			objSave.setChart_id(obj.getChart_id());
			objSave.setPractice_id(obj.getPractice_id());
			objSave.setCreated_user(obj.getUser());
			objSave.setModified_user(obj.getUser());
			objSave.setClient_date_created(DateTimeUtil.getCurrentDateTime());
			objSave.setClient_date_modified(DateTimeUtil.getCurrentDateTime());
			objSave.setDate_created(DateTimeUtil.getCurrentDateTime());
			objSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			objSave.setNotes_text(obj.getPlan());
			objSave.setNotes_html(obj.getPlan());
			objSave.setNotes_new_html(obj.getPlan_html());
			objSave.setNote_id(null);

			saveChartProgressNotes(objSave);
		}
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		resp.setStatus(ServiceResponseStatus.SUCCESS);
		resp.setResponse("Data has been saved successfully.");
		resp.setResult("1");
		return resp;

	}

	@Override
	public List<ORMGetCurrentEncounterTemplate> getCurrentEncounterTemplate(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetCurrentEncounterTemplate", ORMGetCurrentEncounterTemplate.class,
				lstParam);
	}

	@Override
	public List<ORMGetTemplateEncounterSummary> getTemplateEncSummary(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetTemplateEncSummary", ORMGetTemplateEncounterSummary.class, lstParam);
	}

	@Override
	public List<ORMGetPatientVisit_CCD> getPatientVisit_CCD(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetpatvisit_ccd", ORMGetPatientVisit_CCD.class, lstParam);
	}

	@Override
	public List<ORMCCDSetting> getCCDSetting(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetCCD_Setting", ORMCCDSetting.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveCCD_Setting(List<ORMCCDSetting> obj) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		for (ORMCCDSetting ormSave : obj) {

			result = db.SaveEntity(ormSave, Operation.EDIT);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
		}

		return resp;
	}

	@Override
	public long resolveProblem(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			String user = "";
			String problem_id = "";
			String status = "";
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("user")) {
					user = pram.getValue();
				} else if (pram.getName().equals("problem_id")) {
					problem_id = pram.getValue();
				} else if (pram.getName().equals("status")) {
					status = pram.getValue();
				}
			}
			String qry = "update chart_problem set status='" + status + "',resolved_date=getdate(),modified_user='"
					+ user + "',date_modified=getdate() where problem_id='" + problem_id + "'";
			return db.ExecuteUpdateQuery(qry);
		}
		return 0;
	}

	@Override
	public Long saveSessionInfo(ORMSessionInfo obj) {
		// System.out.println(obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		obj.setDeleted(false);
		if (obj.getSessional_id() != null && obj.getSessional_id() != 0) {
			// System.out.println("EDIT...");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return obj.getSessional_id();
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setSessional_id(db.IDGenerator("chart_sessionalinfo", Long.parseLong(obj.getPractice_id())));
			// System.out.println("SAVE...");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return obj.getSessional_id();
			else
				return (long) 0;
		}
	}

	@Override
	public List<ORMSessionInfo> getSessionInformation(Long chartID, Long patientID) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chartID", chartID.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patientID", patientID.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartsessioninfo", ORMSessionInfo.class, lstParam);

	}

	@Override
	public List<ORMClosingSummary> getClosingSummary(String chartID, String patientID, String session_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chartID", chartID.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patientID", patientID.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("session_id", session_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetclosesummary", ORMClosingSummary.class, lstParam);
	}

	@Override
	public Long saveClosingSummary(ORMClosingSummary obj) {
		// System.out.println(obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		obj.setDeleted("false");
		if (obj.getClosing_id() != null && obj.getClosing_id() != "0") {
			// System.out.println("EDIT...");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return Long.parseLong(obj.getClosing_id());
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setClosing_id(db.IDGenerator("chart_closingsummary", Long.parseLong(obj.getPractice_id())).toString());
			// System.out.println("SAVE...");
			if (db.SaveEntity(obj, Operation.ADD) > 0) {
				db.ExecuteUpdateQuery("update patient_chart set encounter_closing_id='" + obj.getClosing_id()
						+ "' where patient_id='" + obj.getPatient_id() + "'  and isnull(deleted,0)<>1 and chart_id<='"
						+ obj.getChart_id() + "' and isnull(encounter_closing_id,0)=0");
				return Long.parseLong(obj.getClosing_id());
			}

			else
				return (long) 0;
		}
	}

	@Override
	public List<ORMChartDiagnosis> getChartDiagnosis(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPreviousDiag", ORMChartDiagnosis.class, lstParam);
	}
	/*
	 * @Override public Long SaveDepressionScreeningToFile(ORMGYNForm objGYNForm,
	 * String hTML, String docPath) { long result = 0;
	 * objGYNForm.setDate_modified(DateTimeUtil.getCurrentDateTime());
	 * objGYNForm.setId(Long.toString(db.IDGenerator("gyn_form",
	 * Long.parseLong(objGYNForm.getPractice_id()))));
	 * objGYNForm.setDate_created(DateTimeUtil.getCurrentDateTime()); String
	 * dir_path =
	 * GeneralOperations.checkPathYearMonthWise(objGYNForm.getPractice_id(),
	 * docPath,"Health_Check"); System.out.println("directory " + dir_path); String
	 * fileName = objGYNForm.getId() + ".html"; try { Files.write(Paths.get(docPath
	 * + "\\" + objGYNForm.getPractice_id() + "\\Health_Check\\" + dir_path + "\\" +
	 * fileName), hTML.getBytes(), StandardOpenOption.CREATE);
	 * objGYNForm.setFile_link(dir_path + "\\" + fileName); } catch (IOException ex)
	 * { System.out.println("IOException " + ex); result = 0; } if
	 * (db.SaveEntity(objGYNForm, Operation.ADD) > 0) {
	 * System.out.println("add now **********"); result = 1; } return result; }
	 */

	@Override
	public Long SaveDepressionScreeningToFile(ORMGYNForm objGYNForm, String hTML, String docPath) {
		long result = 0;
		// TODO Auto-generated method stub
		objGYNForm.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (objGYNForm.getId().equals("") || objGYNForm.getId() == null) {
			objGYNForm.setId(Long.toString(db.IDGenerator("gyn_form", Long.parseLong(objGYNForm.getPractice_id()))));
			objGYNForm.setDate_created(DateTimeUtil.getCurrentDateTime());
			String dir_path = GeneralOperations.checkPathYearMonthWise(objGYNForm.getPractice_id(), docPath,
					"Health_Check");
			// System.out.println("directory " + dir_path);
			String fileName = objGYNForm.getId() + ".html";
			try {
				Files.write(Paths.get(
						docPath + "\\" + objGYNForm.getPractice_id() + "\\Health_Check\\" + dir_path + "\\" + fileName),
						hTML.getBytes(), StandardOpenOption.CREATE);
				objGYNForm.setFile_link(dir_path + "\\" + fileName);
			} catch (IOException ex) {
				System.out.println("IOException " + ex);
				result = 0;
			}
			if (db.SaveEntity(objGYNForm, Operation.ADD) > 0) {
				result = 1;
			}
		} else {
			// System.out.println("****IN EDIT**********: " + objGYNForm.getId());
			if (!objGYNForm.getFile_link().equals("")) {
				// System.out.println(
				// docPath + "\\" + objGYNForm.getPractice_id() + "\\Health_Check\\" +
				// objGYNForm.getFile_link());
				try {
					Files.write(Paths.get(docPath + "\\" + objGYNForm.getPractice_id() + "\\Health_Check\\"
							+ objGYNForm.getFile_link()), hTML.getBytes(), StandardOpenOption.CREATE);
				} catch (IOException ex) {
					System.out.println("IOException " + ex);
					result = 0;
				}
			}
			if (db.SaveEntity(objGYNForm, Operation.EDIT) > 0)
				result = 1;
		}
		return result;
	}

	@Override
	public List<ORMGetPatientHealthCheckSummary> getDepressionScreening(SearchCriteria searchCriteria) {
		String patient_id = "";
		String form_type = "";
		String chartid = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						patient_id = pram.getValue();
						break;
					}
				case "form_type":
					if (pram.getValue() != "" && pram.getValue() != null) {
						form_type = pram.getValue();
						break;
					}
				case "chartid":
					if (pram.getValue() != "" && pram.getValue() != null) {
						chartid = pram.getValue();
						break;
					}
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("form_type", form_type, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("ChartID", chartid, String.class, ParameterMode.IN));
		List<ORMGetPatientHealthCheckSummary> lst = db.getStoreProcedureData("spGetOBGYN_Form_Summary",
				ORMGetPatientHealthCheckSummary.class, lstParam);
		return lst;
	}

	@Override
	public List<ORMFourColumGeneric> getChartPreviousData(String type, String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("type", type.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGet_previous_chart_data", ORMFourColumGeneric.class, lstParam);
	}

	@Override
	public List<ORMPatient_Followup> getPatientFollowup(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientFollowUp", ORMPatient_Followup.class, lstParam);
	}

	@Override
	public Long savePatientFollowupTask(ORMPatient_Followup obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getId() == null) // New
		{
			obj.setId(db.IDGenerator("patient_followup", Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		if (result > 0) {
			return Long.parseLong(obj.getId());
		} else {
			return (long) 0;
		}
	}

	@Override
	public List<ORMChartOfficetestCpt> getOfficeCpts(String practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practiceId", practiceId, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetOfficeCpts", ORMChartOfficetestCpt.class, lstParam);
	}

	@Override
	public List<ORMTwoColumnGeneric> spGetNoKnown(String patient_id, String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartNoKnown", ORMTwoColumnGeneric.class, lstParam);
	}

	@Override
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateNoKnownData(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		String chart_id = "";
		String no_allergy = "false";
		String no_med = "false";
		String no_problem = "false";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "chart_id":
					chart_id = pram.getValue();
					break;
				case "no_allergy":
					no_allergy = pram.getValue();
					break;
				case "no_med":
					no_med = pram.getValue();
					break;
				case "no_problem":
					no_problem = pram.getValue();
					break;
				default:
					break;
				}
			}
			String Qry = "update patient_chart set no_allergy='" + no_allergy + "',no_med='" + no_med + "',no_problem='"
					+ no_problem + "' where chart_id='" + chart_id + "'";
			int result = db.ExecuteUpdateQuery(Qry);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResult("OK");
				resp.setResponse("Data has been saved successfully.");
			}

		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateEncounterEducationProvided(
			SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		String chart_id = "";
		String education = "false";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "chart_id":
					chart_id = pram.getValue();
					break;
				case "education":
					education = pram.getValue();
					break;

				default:
					break;
				}
			}
			String Qry = "update patient_chart set external_education='" + education + "' where chart_id='" + chart_id
					+ "'";
			int result = db.ExecuteUpdateQuery(Qry);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResult("OK");
				resp.setResponse("Data has been saved successfully.");
			}

		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ServiceResponse<ORMKeyValue>> updateEncounterMedicationReviewed(
			SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		String chart_id = "";
		String med_reviewed = "false";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "chart_id":
					chart_id = pram.getValue();
					break;
				case "med_reviewed":
					med_reviewed = pram.getValue();
					break;

				default:
					break;
				}
			}
			String Qry = "update patient_chart set med_reviewed='" + med_reviewed + "' where chart_id='" + chart_id
					+ "'";
			int result = db.ExecuteUpdateQuery(Qry);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResult("OK");
				resp.setResponse("Data has been saved successfully.");
			}

		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@Override
	public List<ORMFourColumGeneric> GetChartOfficeTestCodeAmt(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartOfficeTestCodeAmt", ORMFourColumGeneric.class, lstParam);
	}

	@Override
	public List<ORMChartSocialHistoryGet> getChartSocialHistory(Long chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartSocialHistoryPrint", ORMChartSocialHistoryGet.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> MoveProblemToCurrent(SearchCriteria searchCriteria) {
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		String modified_user = "";
		String problem_id = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().toString().equals("modified_user"))
					modified_user = pram.getValue().toString();
				else if (pram.getName().toString().equals("problem_id"))
					problem_id = pram.getValue().toString();
			}
			String qry = "update chart_problem set prob_date = getdate(), modified_user = '" + modified_user
					+ "', date_modified = getdate(), client_date_modified = getdate() where problem_id ='" + problem_id
					+ "'";
			int res = db.ExecuteUpdateQuery(qry);

			if (res == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
			}
		}
		return resp;
	}

	@Override
	public int signSelectedEncounter(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String complete_by = "";
		// String complete_date = "";
		String signed_by = "";
		// String signed_date = "";
		String signed_provider_id = "";
		String signed_provider_name = "";
		String chart_id = "";
		String system_ip = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("complete_by")) {
					complete_by = pram.getValue();
				} else if (pram.getName().equals("complete_date")) {
					// complete_date = pram.getValue();
				} else if (pram.getName().equals("signed_by")) {
					signed_by = pram.getValue();
				} else if (pram.getName().equals("signed_date")) {
					// signed_date = pram.getValue();
				} else if (pram.getName().equals("signed_provider_id")) {
					signed_provider_id = pram.getValue();
				} else if (pram.getName().equals("signed_provider_name")) {
					signed_provider_name = pram.getValue();
				} else if (pram.getName().equals("chart_id")) {
					chart_id = pram.getValue();
				} else if (pram.getName().equals("system_ip")) {
					system_ip = pram.getValue();
				}
			}
		}

		String Query = "";
		String queryHealthCheck = "";
		Query = "update patient_chart set complete=1,complete_by='" + complete_by + "',complete_date='"
				+ DateTimeUtil.getCurrentDateTime() + "',Signed=1,signed_by='" + signed_by + "',signed_date='"
				+ DateTimeUtil.getCurrentDateTime() + "',signed_provider_id='" + signed_provider_id + "', system_ip ='"
				+ system_ip + "' where chart_id in(" + chart_id + ")";// in '" + chart_id + "'";
		queryHealthCheck = "update patient_health_check set provider1_sign_info='SIGNED BY : " + signed_provider_name
				+ " DATED:" + DateTimeUtil.getCurrentDateTime() + "', system_ip ='" + system_ip
				+ "' where chart_id in (" + chart_id + ") and isnull(deleted,0)<>1";
		db.ExecuteUpdateQuery(queryHealthCheck);
		return db.ExecuteUpdateQuery(Query);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveChartProcedure(List<ORMChartSurgery> lstSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		for (ORMChartSurgery ormdashSetting : lstSave) {
			ormdashSetting.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormdashSetting.getChart_procedures_id())) {

				ormdashSetting.setChart_procedures_id(
						db.IDGenerator("chart_procedures", Long.parseLong(ormdashSetting.getPractice_id())).toString());
				ormdashSetting.setDate_created(DateTimeUtil.getCurrentDateTime());

				result = db.SaveEntity(ormdashSetting, Operation.ADD);
			} else {
				result = db.SaveEntity(ormdashSetting, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public long MarkEncounterAsClaimCreated(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String chart_id = "";
		String user = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				// System.out.println(
				// "param:" + pram.getName() + " Value:" + pram.getValue() + " Option:" +
				// pram.getOption());

				if (pram.getName().toLowerCase().equals("chart_id")) {
					chart_id = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("user")) {
					user = pram.getValue();
				}
			}
		}
		String strQuery = "update patient_chart set missing_claim_exluded =1,client_date_modified=getdate(),modified_user='"
				+ user + "' where chart_id = '" + chart_id + "' ";
		return db.ExecuteUpdateQuery(strQuery);

	}

	@Override
	public List<ORMProblemBasedTemplate> getProblemBasedChartTemplate(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("type", "", String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartTemplate", ORMProblemBasedTemplate.class, lstParam);
	}

	@Override
	public List<ORMGetProvider_template> getProviderOfProblemBasedTemplate(String practice_id, String template_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("template_id", template_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("sp_GetChartTemplate_Provider", ORMGetProvider_template.class, lstParam);
	}

	@Override
	public Long saveProblemBasedTemplateSetup(ORMProblemBasedTemplate obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getTemplate_id() == null) // New
		{
			obj.setTemplate_id(db.IDGenerator("chart_template", Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(obj, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		if (result > 0) {
			return Long.parseLong(obj.getTemplate_id());
		} else {
			return (long) 0;
		}
	}

	@Override
	public List<ORMProblemBasedTemplate> getProblemBasedTemplateEncounter(String practice_id, String provider_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("type", "", String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("provider_id", provider_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartTemplateForProvider", ORMProblemBasedTemplate.class, lstParam);
	}

	@Override
	public ORMGetLocalPrescriptions getLocalPrescriptionById(Long prescriptionId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("prescriptionId", prescriptionId.toString(), String.class, ParameterMode.IN));

		List<ORMGetLocalPrescriptions> lst = db.getStoreProcedureData("spGetLocalPrescription",
				ORMGetLocalPrescriptions.class, lstParam);

		if (lst.isEmpty()) {
			return null;
		} else {
			return lst.get(0);
		}
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveLocalPrescription(ORMSaveLocalPrescription ormPrescription) {
		// TODO Auto-generated method stub

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		int result = 0;

		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		ormPrescription.setDate_modified(serverDateTime);

		if (ormPrescription.getChart_prescription_id() != null && ormPrescription.getChart_prescription_id() != 0) {
			result = db.SaveEntity(ormPrescription, Operation.EDIT);

		} else {
			ormPrescription
					.setChart_prescription_id(db.IDGenerator("chart_prescription", ormPrescription.getPractice_id()));
			ormPrescription.setDate_created(serverDateTime);
			result = db.SaveEntity(ormPrescription, Operation.ADD);

		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormPrescription.getChart_prescription_id().toString());
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> markPrescriptionAsInactive(UpdateRecord updateRecord) {

		String query = " update chart_prescription set archive='Y' ,date_modified=getdate(),client_date_modified='"
				+ updateRecord.getClient_datetime() + "',modified_user='" + updateRecord.getUser_name()
				+ "',system_ip='" + updateRecord.getClient_ip() + "'" + " where chart_prescription_id='"
				+ updateRecord.getId() + "'";

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (this.db.ExecuteUpdateQuery(query) > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(updateRecord.getId().toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		}

		return resp;
	}

	@Override
	public ORMGetLocalAllergy getLocalAllergyById(Long chartAllergyId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("allergyId", chartAllergyId.toString(), String.class, ParameterMode.IN));

		List<ORMGetLocalAllergy> lst = db.getStoreProcedureData("spGetLocalAllergy", ORMGetLocalAllergy.class,
				lstParam);

		if (lst.isEmpty()) {
			return null;
		} else {
			return lst.get(0);
		}
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveLocalAllergy(ORMSaveLocalAllergy ormSave) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		int result = 0;

		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		ormSave.setDate_modified(serverDateTime);

		if (ormSave.getChart_allergies_id() != null && ormSave.getChart_allergies_id() != 0) {
			result = db.SaveEntity(ormSave, Operation.EDIT);

		} else {
			ormSave.setChart_allergies_id(db.IDGenerator("chart_allergies", ormSave.getPractice_id()));
			ormSave.setDate_created(serverDateTime);
			result = db.SaveEntity(ormSave, Operation.ADD);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getChart_allergies_id().toString());
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> markAllergyAsInactive(UpdateRecord updateRecord) {
		// TODO Auto-generated method stub
		String query = " update chart_allergies set status='I' ,date_modified=getdate(),client_date_modified='"
				+ updateRecord.getClient_datetime() + "',modified_user='" + updateRecord.getUser_name()
				+ "',system_ip='" + updateRecord.getClient_ip() + "'" + " where chart_allergies_id='"
				+ updateRecord.getId() + "'";

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (this.db.ExecuteUpdateQuery(query) > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(updateRecord.getId().toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		}

		return resp;
	}

	@Override
	public List<ORMPatient_Followup> getPatientFullFollowup(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientFullFollowUp", ORMPatient_Followup.class, lstParam);
	}

	@Override
	public List<ORMgetAllChartPrintModule> getAllChartPrintModule(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetAllChartPrintModule", ORMgetAllChartPrintModule.class, lstParam);
	}
	@Override
	public List<ORMGetPatientHealthCheckSummary> getDrugAbuse(SearchCriteria searchCriteria) {
		String patient_id = "";
		String form_type = "";
		String chartid = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						patient_id = pram.getValue();
						break;
					}
				case "form_type":
					if (pram.getValue() != "" && pram.getValue() != null) {
						form_type = pram.getValue();
						break;
					}
				case "chartid":
					if (pram.getValue() != "" && pram.getValue() != null) {
						chartid = pram.getValue();
						break;
					}
				default:
					break;
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("form_type", form_type, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("ChartID", chartid, String.class, ParameterMode.IN));
		List<ORMGetPatientHealthCheckSummary> lst = db.getStoreProcedureData("spGetOBGYN_Form_Summary",
				ORMGetPatientHealthCheckSummary.class, lstParam);
		return lst;
	}
	@Override
	public List<ORMThreeColumGeneric> getPreviewDrigAbuse(SearchCriteria searchCriteria) {
		String patientID = "";
		String healthCheckID = "";
		String practiceID = "";
		String upload_path = "";
		practiceID = searchCriteria.getPractice_id().toString();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()){
				if (pram.getName().toLowerCase().equals("health_check_id")) {
					healthCheckID = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("patient_id")) {
					patientID = pram.getValue();
				}
				if (pram.getName().toLowerCase().equals("upload_path")) {
					upload_path = pram.getValue();
				}
				
			}
		}
		
		List<ORMThreeColumGeneric> FResult;
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientID, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("health_check_id", healthCheckID, String.class, ParameterMode.IN));
		FResult = db.getStoreProcedureData("spgetpreviewdrigabuse", ORMThreeColumGeneric.class, lstParam);
		
		String strHTMLDrugAbuse = "";
		if(FResult.get(0).getCol3().toString().equals("0") || FResult.get(0).getCol3().toString().equals("1")){
			//neg letter here
			 strHTMLDrugAbuse =  " <!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'> "
							+ " <html xmlns='http://www.w3.org/1999/xhtml'> "
							+ " <head> "
							+ " <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> "
							+ " <title>Results Letter</title> "
				
							+ " <style type='text/css'> "
							+ " body { "
							+ " text-align: left; "
							+ " white-space: normal; "
							+ " font-family: 'Times New Roman', Times, serif; "
							+ " margin:0; "
							+ " padding:0; "
							+ " height: 11in; "
							+ " width: 8.5in; "
							+ " margin-left: 0.5in; "
							+ " margin-top: 0.5in; "
							+ " margin-bottom: 0.5in; "
							+ " } "
							+ " #Addresses { "
							+ " position:relative; "
							+ " height:2.875in;	 "
							+ " width:7.4in; "
							+ " } "
							+ " #ReturnAddress { "
							+ " position:absolute; "
							+ " width:3.5in;  "
							+ " height:0.792in; "
							+ " font-size: 9pt; "
							+ " z-index:100; "
							+ " } "
							+ " #CompanyLogo { "
							+ " position:absolute; "
							+ " left:2in; "
							+ " width:1.45in; "
							+ " height:0.792in; "
							+ " z-index:10; "
							+ " text-align:right; "
							+ " } "
							+ " #RecipientAddress { "
							+ " position:absolute; "
							+ " top:1.542in; "
							+ " width:3.5in; "
							+ " height:0.875in; "
							+ " text-transform: uppercase; "
							+ " font-family:Arial, Helvetica, sans-serif; "
							+ " font-size: 10pt; "
							+ " } "
							+ " #RightSideContent { "
							+ " position:relative; "
							+ " border:thin #000000; "
							+ " left: 4.5in; "
							+ " height: 2.8in; "
							+ " width: 2.9in; "
							+ " } "
							+ " #BodyContent { "
							+ " position:relative; "
							+ " left:0; "
							+ " width:7.4in; "
							+ " font-size: 11pt; "
							+ " line-height: 1.5; "
							+ " white-space: normal; "
							+ " margin:0; "
							+ " padding:0; "
							+ " } "
							+ " .PageBreak { "
							+ " page-break-after:always;  "
							+ " height:1px; "
							+ " margin:0; "
							+ " padding:0; "
							+ " } "
							+ " </style> "
							+ " </head> "
							+ " <body> "
							+ " <div id='Addresses'> "
				
							+ " <div id='ReturnAddress'> "
							+ " American TelePsychiatry&trade;<br> "
							+ " 4650 South Howell Avenue<br> "
							+ " Milwaukee, WI 53207 "
							+ " </div> "
				
				
							+ "     <div id='RecipientAddress'> "
							+ " 	"+ FResult.get(0).getCol1().toUpperCase() + " <br><br> "
							+ " 	"+ FResult.get(0).getCol2().toUpperCase() + "<br> "
							+ "     </div> "
				
							+ " <div id='RightSideContent'></div> "
							+ " </div> "
				
							+ " <div id='BodyContent'> "
							+ " "+DateTimeUtil.getCurrentDateTime()+" <br/><br/>Dear Client<br /> "
							+ " <br /> "
							+ " <b><u><i>Note: This letter does not pertain to Medication Management or other services or treatment you may be receiving from our Nurse Practitioner,Psychiatrist or Physician. You should continue to see them as you normally do.</b></u></i><br /> "
							+ " <br /> "
							+ " <b><i>Your diagnostic test results are as follows: Negative. Congratulations! We encourage you to maintain a healthy lifestyle and a positive and stress-free approach to life.</b></i><br /> "
							+ " <br /> "
							+ " If you have any questions about this letter, please feel free to call us at 414.376.5577.We also encourage you to show this letter to your Primary Care Physician. If your physician has any questions about the behavioral healthcare screen that you have participated in, the physician may also contact us at this number.<br /> "
							+ " <br /> "
							+ " Your caring Professionals at American TelePsychiatry<br /> "
							+ " <br /> "
							+ " An affiliate of American Telehealthcare/Horizon Healthcare, Inc. "
				
				
							+ " </div> "
							+ " </body> "
							+ " </html> ";
		}else {
			//positive lter here
			 strHTMLDrugAbuse =  " <!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'> "
						+ " <html xmlns='http://www.w3.org/1999/xhtml'><head> "
						+ "     <meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' /> "
						+ "     <title></title> "
						+ "     <style type='text/css'> "
						
						+ " body { "
						+ "         text-align: left; "
						+ "         white-space: normal; "
						+ "         font-family: 'Times New Roman', Times, serif; "
						+ "         margin:0; "
						+ "         padding:0; "
						+ "         height: 11in; "
						+ "         width: 8.5in; "
						+ " 	margin-left: 0.5in; "
						+ " 	margin-top: 0.5in; "
						+ " 	margin-bottom: 0.5in; "
						+ " } "
						+ " #Addresses { "
						+ " 	position:relative; "
						+ " 	height:2.875in;	 "
						+ "         width:7.4in; "
						+ " } "
						+ " #ReturnAddress { "
						+ "     position:absolute; "
						+ "     width:3.5in;  "
						+ "     height:0.792in; "
						+ "     font-size: 9pt; "
						+ "     z-index:100; "
						+ " } "
						+ " #CompanyLogo { "
						+ "     position:absolute; "
						+ "     left:2in; "
						+ "     width:1.45in; "
						+ "     height:0.792in; "
						+ "     z-index:10; "
						+ " 	text-align:right; "
						+ " } "
						+ " #RecipientAddress { "
						+ "     position:absolute; "
						+ "     top:1.542in; "
						+ "     width:3.5in; "
						+ "     height:0.875in; "
						+ "     text-transform: uppercase; "
						+ " 	font-family:Arial, Helvetica, sans-serif; "
						+ "     font-size: 10pt; "
						+ " } "
						
						+ " #RightSideContent { "
						+ " 	position:relative; "
						+ " 	border:thin #000000; "
						+ " 	left: 4.5in; "
						+ " 	height: 2.8in; "
						+ " 	width: 2.9in; "
						+ " } "
						+ " #BodyContent { "
						+ "     position:relative; "
						+ "     left:0; "
						+ "     width:7.4in; "
						+ "     font-size: 11pt; "
						+ "     line-height: 1.5; "
						+ "     white-space: normal; "
						+ "     margin:0; "
						+ "     padding:0; "
						+ " } "
						+ " .PageBreak { "
						+ " 	page-break-after:always; "
						+ " 	height:1px; "
						+ " 	margin:0; "
						+ " 	padding:0; "
						+ " } "
						+ " </style> "
						+ " </head> "
						+ " <body> "
						
						+ " <div id='Addresses'> "
						
						+ "     <div id='ReturnAddress'> "
						+ " 	American TelePsychiatryT<br> "
						+ " 	4650 South Howell Avenue<br> "
						+ "         Milwaukee, WI 53207 "
						+ "     </div> "
						
						
						+ "     <div id='RecipientAddress'> "
						+ " 	"+ FResult.get(0).getCol1().toUpperCase() + " <br><br> "
						+ " 	"+ FResult.get(0).getCol2().toUpperCase() + "<br> "
						+ "     </div> "
						
						+ "     <div id='RightSideContent'></div> "
						+ " </div> "
						
						+ " <div id='BodyContent'> "
						+ " "+DateTimeUtil.getCurrentDateTime()+"<br><br>Dear Client<br> "
						+ " <br> "
						+ " <b><u><i>Note: This letter does not pertain to Medication Management or other services or treatment you may be receiving from our Nurse Practitioner,Psychiatrist or Physician. You should continue to see them as you normally do.</i></u></b><br> "
						+ " <br> "	  
						+ " <b><i>Your diagnostic test results are as follows: Positive</i></b><br> "
						+ " <br> "
						+ " We believe that you may benefit from AODA counseling.We recommend that you contact  "
						+ " our offices and schedule with one of our AODA counsolors. "
						+ " <br> "
						+ " <br> "
						+ " Horizon Health Care, INC (414) 376-5577<br> "
						+ " <br> "
						+ " In addition to these agencies,there are many other reputable providers that will provide you the services you may require. In particular, the Mental Health Association of America lists quality providers throughout Wisconsin. You can access these providers through this link: http://www.mhawisconsin.org/. Click 'Resource Directories' from this home page to customize a provider search that meets your needs.<br> "
						+ " <br> "
						+ " If you have any questions about this letter, please feel free to call us at 414.376.5577.We also encourage you to show this letter to your Primary Care Physician. If your physician has any questions about the behavioral healthcare screen that you have participated in, the physician may also contact us at this number.<br> "
						+ " <br> "
						+ " Your caring Professionals at American TelePsychiatry<br> "
						+ " <br> "
						+ " An affiliate of American Telehealthcare/Horizon Healthcare, Inc. "
						+ " </div> "
						
						+ " </body> "
						+ " </html> ";
		}
		
		  
		 
		
							String dir_path = GeneralOperations.checkPathYearMonthWise(practiceID, upload_path, "Health_Check");
							String fileName = "result_" +healthCheckID + ".html";
							try {
								Files.write(Paths.get(upload_path + "\\" + practiceID + "\\Health_Check\\"
										+ dir_path + "\\" + fileName), strHTMLDrugAbuse.getBytes(), StandardOpenOption.CREATE);
								
								
								
								String strUpdateQuery = "update drug_abuse_score set file_link = '"+ dir_path + "\\" + fileName +"' "
						                + "where health_check_id = '"+ healthCheckID +"'";
								
								db.ExecuteUpdateQuery(strUpdateQuery);
							
							
							} catch (IOException ex) {
								System.out.println("IOException " + ex);
							}
		
		FResult.get(0).setCol3(dir_path + "\\" + fileName);
		
		return FResult;
	}
	@Override
	public List<ORMGetPatientDrugAbuseSummary> getPatientDrugAbuseSummary(String practice_id, String patient_id, String form_type) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("form_type", form_type, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPatientDrugAbuseSummary", ORMGetPatientDrugAbuseSummary.class, lstParam);
	}
}