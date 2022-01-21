package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMTaskDetailGet;
import com.ihc.ehr.model.ORMTaskLogGet;
import com.ihc.ehr.model.ORMTaskUsersGet;
import com.ihc.ehr.model.ORMTaskUsersSave;
import com.ihc.ehr.model.ORMTasksGet;
import com.ihc.ehr.model.ORMTasksSave;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.UpdateRecord;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@Repository
public class TasksDOAImpl implements TasksDOA {

	@Autowired
	private DBOperations db;

	@Override
	public List<ORMTasksGet> getTasks(SearchCriteria searchCriteria) {

		// String userName = "";
		String inOutType = searchCriteria.getOption().toString();

		// TODO Auto-generated method stub
		// String criteria=" and practice_id='"+practiceId+"' and
		// assigned_to='"+assigned_to+"' ";
		// lstCriteriaParam.add(new SearchCriteriaParamList("status", status.toString(),
		// ""));
		// lstCriteriaParam.add(new SearchCriteriaParamList("date_from",
		// dateFrom.toString(), ""));
		// lstCriteriaParam.add(new SearchCriteriaParamList("date_to",
		// dateTo.toString(), ""));
		// lstCriteriaParam.add(new SearchCriteriaParamList("date_type",
		// dateType.toString(), ""));
		// lstCriteriaParam.add(new SearchCriteriaParamList("created_by",
		// createdBy.toString(), ""));

		// lstParam.add(
		// new SpParameters("in_out_type", searchCriteria.getOption().toString(),
		// String.class, ParameterMode.IN));

		String criteria = "";
		if (searchCriteria.getParam_list() != null && searchCriteria.getParam_list().size() > 0) {

			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {

				switch (param.getName()) {
				case "user_name":
					if (GeneralOperations.isNotNullorEmpty(param.getValue())) {

						if (inOutType.equalsIgnoreCase("INBOX")) {
							criteria += " and tu.receiver='" + param.getValue() + "' and isnull(receiver_deleted,0)<>1 ";
						} else if (inOutType.equalsIgnoreCase("OUTBOX")) {
							criteria += " and tu.sender='" + param.getValue() + "' ";
						}
					}
					break;
				case "status":

					if (GeneralOperations.isNotNullorEmpty(param.getValue())) {

						if (param.getValue().equalsIgnoreCase("In Complete")) {
							criteria += " and t.status <> 'Completed' ";
						} else if (param.getValue().equalsIgnoreCase("Overdue")) {
							criteria += " and t.status <> 'Completed' and convert(date,t.due_date) > convert(date,getdate()";
						} else {
							criteria += " and t.status='" + param.getValue() + "' ";
						}
					}

					break;
				case "date_from":
					if (GeneralOperations.isNotNullorEmpty(param.getValue())) {

						if (param.getOption().equalsIgnoreCase("date_created")) {

							criteria += " and convert(date,t.date_created) >= convert(date,'" + param.getValue()
									+ "') ";

						} else if (param.getOption().equalsIgnoreCase("date_completed")) {

							criteria += " and convert(date,t.date_completed) >= convert(date,'" + param.getValue()
									+ "') ";

						} else if (param.getOption().equalsIgnoreCase("due_date")) {

							criteria += "  and convert(date,t.due_date) >= convert(date,'" + param.getValue() + "') ";

						}

					}
					break;
				case "date_to":
					if (GeneralOperations.isNotNullorEmpty(param.getValue())) {

						if (param.getOption().equalsIgnoreCase("date_created")) {

							criteria += " and convert(date,t.date_created) <= convert(date,'" + param.getValue()
									+ "') ";

						} else if (param.getOption().equalsIgnoreCase("date_completed")) {

							criteria += " and convert(date,t.date_completed) <= convert(date,'" + param.getValue()
									+ "') ";

						} else if (param.getOption().equalsIgnoreCase("due_date")) {

							criteria += " and convert(date,t.due_date) <= convert(date,'" + param.getValue() + "') ";

						}

					}
					break;

				case "created_by":
					if (GeneralOperations.isNotNullorEmpty(param.getValue())) {

						criteria += " and t.created_user='" + param.getValue() + "' ";
					}
					break;
				case "assinged_to":
					if (GeneralOperations.isNotNullorEmpty(param.getValue())) {
						criteria += " and t.assigned_to='" + param.getValue() + "' ";
					}
					break;
				case "priority":
					if (GeneralOperations.isNotNullorEmpty(param.getValue())) {

						criteria += " and t.priority='" + param.getValue() + "' ";
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
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		List<ORMTasksGet> lst = db.getStoreProcedureData("spGetTaskts", ORMTasksGet.class, lstParam);
		return lst;

	}

	@Override
	public ServiceResponse<ORMKeyValue> saveTask(ORMTasksSave ormTasksSave) {
		// TODO Auto-generated method stub

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		int result = 0;

		List<ORMTaskUsersGet> lstTaskUsers = null;
		if (GeneralOperations.isNotNullorEmpty(ormTasksSave.getTask_id()) && ormTasksSave.getTask_id() != 0) {
			List<SpParameters> lstParam = new ArrayList<>();
			lstParam.add(
					new SpParameters("task_id", ormTasksSave.getTask_id().toString(), String.class, ParameterMode.IN));
			// lstParam.add(new SpParameters("user_name",
			// ormTasksSave.getAssigned_from().toString(), String.class,
			// ParameterMode.IN));
			// lstParam.add(new SpParameters("user_type", "SENDER", String.class,
			// ParameterMode.IN));
			lstTaskUsers = db.getStoreProcedureData("spGetTasktUsers", ORMTaskUsersGet.class, lstParam);
		}

		String serverDateTime = DateTimeUtil.getCurrentDateTime();

		ormTasksSave.setDate_modified(serverDateTime);

		if (ormTasksSave.getStatus().equalsIgnoreCase("completed")) {
			ormTasksSave.setCompleted_user(ormTasksSave.getModified_user());
			ormTasksSave.setDate_completed(serverDateTime);
		}

		if (ormTasksSave.getTask_id() != null && ormTasksSave.getTask_id() != 0) {
			// result = db.SaveEntity(ormTasksSave, Operation.EDIT);
			lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, ormTasksSave));
		} else {
			ormTasksSave.setTask_id(db.IDGenerator("tasks", ormTasksSave.getPractice_id()));
			ormTasksSave.setDate_created(serverDateTime);
			// result = db.SaveEntity(ormTasksSave, Operation.ADD);
			lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormTasksSave));
		}

		Boolean isUserExist = false;

		if (lstTaskUsers != null && !lstTaskUsers.isEmpty()) {

			for (ORMTaskUsersGet taskUser : lstTaskUsers) {

				if (taskUser.getSender().equalsIgnoreCase(ormTasksSave.getAssigned_from())
						&& taskUser.getReceiver().equalsIgnoreCase(ormTasksSave.getAssigned_to())
						&& !taskUser.getReceiver_deleted()) {
					isUserExist = true;
				}
			}
		}

		if (!isUserExist) {
			ORMTaskUsersSave taskUserSave = new ORMTaskUsersSave();
			taskUserSave.setTask_id(ormTasksSave.getTask_id());
			taskUserSave.setSender(ormTasksSave.getAssigned_from());
			taskUserSave.setReceiver(ormTasksSave.getAssigned_to());
			taskUserSave.setReceiver_deleted(false);
			taskUserSave.setDate_modified(serverDateTime);
			taskUserSave.setDate_created(serverDateTime);
			taskUserSave.setClient_date_created(ormTasksSave.getClient_date_modified());
			taskUserSave.setClient_date_modified(ormTasksSave.getClient_date_modified());
			taskUserSave.setModified_user(ormTasksSave.getModified_user());
			taskUserSave.setCreated_user(ormTasksSave.getModified_user());
			taskUserSave.setSystem_ip(ormTasksSave.getSystem_ip());
			taskUserSave.setDeleted(false);
			lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, taskUserSave));
		}

		result = db.AddUpdateEntityWrapper(lstEntityWrapper);

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormTasksSave.getTask_id().toString());
		}

		return resp;
	}

	@Override
	public ORMTaskDetailGet getTaskById(Long taskId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("task_id", taskId.toString(), String.class, ParameterMode.IN));
		List<ORMTaskDetailGet> lst = db.getStoreProcedureData("spGetTaskDetail", ORMTaskDetailGet.class, lstParam);

		if (!lst.isEmpty()) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMTaskLogGet> getTaskLog(Long taskId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("task_id", taskId.toString(), String.class, ParameterMode.IN));
		List<ORMTaskLogGet> lst = db.getStoreProcedureData("spGetTaskLog", ORMTaskLogGet.class, lstParam);

		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> markAsCompleted(UpdateRecord updateRecord) {
		// TODO Auto-generated method stub
		String query = "update tasks set status='Completed',operation_performed='Completed', date_completed=getdate(),completed_user='"
				+ updateRecord.getUser_name() + "', " + " date_modified=getdate(),client_date_modified='"
				+ updateRecord.getClient_datetime() + "', " + "modified_user='" + updateRecord.getUser_name() + "', "
				+ "system_ip='" + updateRecord.getClient_ip() + "' " + " where task_id='"
				+ updateRecord.getId().toString() + "'";

		int result = this.db.ExecuteUpdateQuery(query);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Record has been deleted successfully.");
			resp.setResult(updateRecord.getId().toString());
		}

		return resp;

	}

	@Override
	public ServiceResponse<ORMKeyValue> deleteTask(UpdateRecord updateRecord) {
		// TODO Auto-generated method stub

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		String queryTask = "update tasks set operation_performed='Deleted', date_modified=getdate(),client_date_modified='"
				+ updateRecord.getClient_datetime() + "', " + "modified_user='" + updateRecord.getUser_name() + "', "
				+ "system_ip='" + updateRecord.getClient_ip() + "' ";

		String queryTaskUser = "update task_users set date_modified=getdate(),client_date_modified='"
				+ updateRecord.getClient_datetime() + "', modified_user='" + updateRecord.getUser_name() + "', "
				+ "system_ip='" + updateRecord.getClient_ip() + "' ";

		if (updateRecord.getOption().equalsIgnoreCase("receiver")) {
			queryTaskUser += ",receiver_deleted=1  where task_id='" + updateRecord.getId().toString()
					+ "' and receiver='" + updateRecord.getUser_name() + "'";
		} else if (updateRecord.getOption().equalsIgnoreCase("sender")) {
			queryTaskUser += ",deleted=1  where task_id='" + updateRecord.getId().toString() + "' and sender='"
					+ updateRecord.getUser_name() + "'";
		} else {
			queryTaskUser += ",deleted=1  where task_id='" + updateRecord.getId().toString() + "'";

			queryTask += ",deleted=1";
		}

		queryTask += " where task_id='" + updateRecord.getId().toString() + "'";

		lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, queryTask));
		lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, queryTaskUser));
		
		int result = db.AddUpdateEntityWrapper(lstEntityWrapper);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Record has been deleted successfully.");
			resp.setResult(updateRecord.getId().toString());
		}

		return resp;
	}

}
