package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.LogHeader;
import com.ihc.ehr.model.ORMACUCallsLogGet;
import com.ihc.ehr.model.ORMAppointmentCallsLogGet;
import com.ihc.ehr.model.ORMAppointmentCallsLogSave;
import com.ihc.ehr.model.ORMGetAccessLogEncounter;
import com.ihc.ehr.model.ORMGetAutidLogQuery;
import com.ihc.ehr.model.ORMGetLogList;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMGetAccessLogPatient;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.WrapperLog;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@Repository
public class LogDAOImp implements LogDAO {

	@Autowired
	DBOperations db;

	@Override
	public List<ORMAppointmentCallsLogGet> getAppointmentCallsLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		String patient_id = "";
		String appointment_id = "";

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					patient_id = pram.getValue();
					break;
				case "appointment_id":
					appointment_id = pram.getValue();
					break;
				}
			}
		}

		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("appointment_id", appointment_id, String.class, ParameterMode.IN));

		List<ORMAppointmentCallsLogGet> lst = db.getStoreProcedureData("spGetAppointmentCallsLog",
				ORMAppointmentCallsLogGet.class, lstParam);
		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveAppointmentCallsLog(ORMAppointmentCallsLogSave obj) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		if (obj != null) {
			try {

				String strQuery = " insert into appointment_callslog (appointment_id,status_id,details,created_user,client_date_created,date_created,practice_id,system_ip)values "
						+ " ('" + obj.getAppointment_id() + "','" + obj.getStatus_id() + "','" + obj.getDetails()
						+ "','" + obj.getCreated_user() + "','" + obj.getClient_date_created() + "',getdate(),'"
						+ obj.getPractice_id() + "','" + obj.getSystem_ip() + "')";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				// update appointment status

				if (obj.getAppointment_id() != null && obj.getAppointment_id() > 0) {
					strQuery = "update appointment set status_id ='" + obj.getStatus_id() + "',client_date_modified='"
							+ obj.getClient_date_created() + "',modified_user='" + obj.getCreated_user()
							+ "',system_ip='" + obj.getSystem_ip() + "' where appointment_id = '"
							+ obj.getAppointment_id() + "' ";

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));
				}

				result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(obj.getPractice_id().toString());
		}

		return resp;

	}

	@Override
	public List<ORMACUCallsLogGet> getACUCallsLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String patient_id = "";
		String location_id = "";
		String appointment_id = "";
		String date_from = "";
		String date_to = "";

		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					patient_id = pram.getValue();
					break;
				case "location_id":
					location_id = pram.getValue();
					break;
				case "appointment_id":
					appointment_id = pram.getValue();
					break;
				case "date_from":
					date_from = pram.getValue();
					break;
				case "date_to":
					date_to = pram.getValue();
					break;
				}

			}
		}

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("appointment_id", appointment_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("location_id", location_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("date_from", date_from, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("date_to", date_to, String.class, ParameterMode.IN));

		List<ORMACUCallsLogGet> lst = db.getStoreProcedureData("spGetACUCallsLog", ORMACUCallsLogGet.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMGetAccessLogPatient> getPatientAccessLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		// String patient_id = "";
		// String datetime_from = "";
		// String datetime_to = "";
		// String user_name = "";
		// String is_emergency_accessed = "";
		String criteria = "";

		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					criteria += " and adl.patient_id='" + pram.getValue() + "' ";
					break;
				case "datetime_from":
					if (pram.getOption().equalsIgnoreCase("datetime")) {
						criteria += " and adl.access_date >= convert(datetime,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date")) {
						criteria += " and convert(date,adl.access_date) >= convert(date,'" + pram.getValue() + "')";
					}

					break;
				case "datetime_to":
					if (pram.getOption().equalsIgnoreCase("datetime")) {
						criteria += " and adl.access_date <= convert(datetime,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date")) {
						criteria += " and convert(date,adl.access_date) <= convert(date,'" + pram.getValue() + "')";
					}
					break;
				case "user_name":
					criteria += " and adl.user_name='" + pram.getValue() + "' ";
					break;
				case "is_emergency_accessed":
					criteria += " and isnull(adl.is_emergency_accessed,0) = convert(bit, '" + pram.getValue() + "')";
					break;
				}

			}
		}

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		List<ORMGetAccessLogPatient> lst = db.getStoreProcedureData("spGetPatientAccessLog",
				ORMGetAccessLogPatient.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMGetAccessLogEncounter> getEncounterAccessLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// String patient_id = "";
		// String datetime_from = "";
		// String datetime_to = "";
		// String user_name = "";
		// String is_emergency_accessed = "";
		String criteria = "";

		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "patient_id":
					criteria += " and adl.patient_id='" + pram.getValue() + "' ";
					break;
				case "datetime_from":
					if (pram.getOption().equalsIgnoreCase("datetime")) {
						criteria += " and adl.access_date >= convert(datetime,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date")) {
						criteria += " and convert(date,adl.access_date) >= convert(date,'" + pram.getValue() + "')";
					}

					break;
				case "datetime_to":
					if (pram.getOption().equalsIgnoreCase("datetime")) {
						criteria += " and adl.access_date <= convert(datetime,'" + pram.getValue() + "')";
					} else if (pram.getOption().equalsIgnoreCase("date")) {
						criteria += " and convert(date,adl.access_date) <= convert(date,'" + pram.getValue() + "')";
					}
					break;
				case "user_name":
					criteria += " and adl.user_name='" + pram.getValue() + "' ";
					break;
				case "is_emergency_accessed":
					criteria += " and isnull(adl.is_emergency_accessed,0) = convert(bit, '" + pram.getValue() + "')";
					break;
				}

			}
		}

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		List<ORMGetAccessLogEncounter> lst = db.getStoreProcedureData("spGetEncounterAccessLog",
				ORMGetAccessLogEncounter.class, lstParam);

		return lst;
	}

	@Override
	public WrapperLog getAuditLog(String moduleName, SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		WrapperLog wrapperLog = new WrapperLog();

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("module_name", moduleName.toString(), String.class, ParameterMode.IN));
		List<ORMGetAutidLogQuery> lstQyery = db.getStoreProcedureData("spGetAuditLogQueryByModuleName",
				ORMGetAutidLogQuery.class, lstParam);

		String practiceId = searchCriteria.getPractice_id().toString();

		if (lstQyery != null && lstQyery.size() > 0) {

			String logQuery = lstQyery.get(0).getLog_query();
			String orderByClause = lstQyery.get(0).getQuery_order();
			String logTableAlias = lstQyery.get(0).getLog_table_alias();
			String[] lstHeaders = lstQyery.get(0).getLog_headers().split("\\|");

			List<LogHeader> lst_log_headers = new ArrayList<>();
			for (int i = 0; i < lstHeaders.length; i++) {

				String[] headerPortions = lstHeaders[i].split("~");

				String display = headerPortions[0];
				String data_type = "";
				Boolean sortable = false;

				Integer id = i + 1;
				if (headerPortions.length > 1) {
					data_type = headerPortions[1];
				}
				if (headerPortions.length > 2) {
					sortable = headerPortions[2].equalsIgnoreCase("1") ? true : false;
				}

				lst_log_headers.add(new LogHeader(id, display, data_type, sortable));

			}

			wrapperLog.setLst_headers(lst_log_headers);

			String query = logQuery + practiceId;

			if (searchCriteria.getParam_list() != null) {
				for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {
					switch (param.getName()) {
					case "patient_id":
					case "user_name":
						query += " and " + logTableAlias + "." + param.getName() + "='" + param.getValue() + "'";
						break;
					case "datetime_from":

						if (param.getOption().equalsIgnoreCase("datetime")) {
							query += " and convert(datetime," + logTableAlias + ".date_modified)>=convert(datetime,'"
									+ param.getValue() + "')";
						} else if (param.getOption().equalsIgnoreCase("date")) {
							query += " and convert(date," + logTableAlias + ".date_modified)>=convert(date,'"
									+ param.getValue() + "')";
						}
						break;
					case "datetime_to":
						if (param.getOption().equalsIgnoreCase("datetime")) {
							query += " and convert(datetime," + logTableAlias + ".date_modified)<=convert(datetime,'"
									+ param.getValue() + "')";
						} else if (param.getOption().equalsIgnoreCase("date")) {
							query += " and convert(date," + logTableAlias + ".date_modified)<=convert(date,'"
									+ param.getValue() + "')";
						}
						break;

					case "date_to":
						query += " and convert(date," + logTableAlias + ".date_modified)<=convert(date,'"
								+ param.getValue() + "')";
						break;
					default:
						query += " and " + logTableAlias + "." + param.getName() + "='" + param.getValue() + "'";
						break;
					}
					// query+= logTable+"."+param.getName()
				}
			}

			query += " " + orderByClause;

			System.out.println("Audit Log Query ==> " + query);

			List<?> lstLog = db.getQueryDataWOClass(query);

			wrapperLog.setLst_log(lstLog);

			if (lstLog != null && lstLog.size() > 0) {
				String childLogListCriteria = " and parent_query_id='" + lstQyery.get(0).getQuery_id() + "'";

				List<ORMGetLogList> lstChildLogs = getLogListByCriteria(childLogListCriteria);
				wrapperLog.setLst_child_logs(lstChildLogs);
			}

		}

		/*
		 * List<SpParameters> lstParam = new ArrayList<>(); lstParam.add(new
		 * SpParameters("patient_id", patient_id.toString(), String.class,
		 * ParameterMode.IN));
		 * 
		 * List<ORMPatientAccessLogGet> lst =
		 * db.getStoreProcedureData("spGetPatientAccessLog",
		 * ORMPatientAccessLogGet.class, lstParam);
		 */

		return wrapperLog;
	}

	@Override
	public List<ORMGetLogList> getLogList(String log_category) {
		// TODO Auto-generated method stub
		String criteria = "";

		// List<SpParameters> lstParam = new ArrayList<>();

		switch (log_category) {
		case "patient_log":
			criteria = " and isnull(is_patient_log,0)=1";
			break;
		case "ccda_log":
			criteria = " and isnull(is_ccda_log,0)=1";
			break;
		case "data_export_log":
			criteria = " and isnull(is_data_export_log,0)=1";
			break;
		case "encounter_log":
			criteria = " and isnull(is_encounter_log,0)=1";
			break;
		case "audit_log":
			criteria = " and isnull(is_audit_log,0)=1";
			break;
		case "genderal_log":
			criteria = " and isnull(is_genderal_log,0)=1";
			break;

		default:
			break;
		}

		// lstParam.add(new SpParameters("criteria", criteria, String.class,
		// ParameterMode.IN));
		// List<ORMGetLogList> lst = db.getStoreProcedureData("spGetLogList",
		// ORMGetLogList.class, lstParam);

		List<ORMGetLogList> lst = getLogListByCriteria(criteria);
		return lst;
	}

	private List<ORMGetLogList> getLogListByCriteria(String criteria) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		List<ORMGetLogList> lst = db.getStoreProcedureData("spGetLogList", ORMGetLogList.class, lstParam);

		return lst;
	}

}
