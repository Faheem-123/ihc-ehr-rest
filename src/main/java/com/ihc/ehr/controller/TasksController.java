package com.ihc.ehr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMTaskDetailGet;
import com.ihc.ehr.model.ORMTaskLogGet;
import com.ihc.ehr.model.ORMTasksGet;
import com.ihc.ehr.model.ORMTasksSave;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.UpdateRecord;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.service.TasksService;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/tasks")
public class TasksController {

	@Autowired
	private TasksService taskService;

	@Autowired
	GeneralService generalService;

	@RequestMapping("getTasks")
	public @ResponseBody ResponseEntity<List<ORMTasksGet>> getTasks(@RequestBody SearchCriteria searchCriteria

	// @PathVariable(value = "practice_id") Long practiceId,
	// @PathVariable(value = "user_name") String userName,
	// @PathVariable(value = "in_out_type") String inOutType,
	// @RequestParam(value = "status", defaultValue = "") String status,
	// @RequestParam(value = "date_from", defaultValue = "") String dateFrom,
	// @RequestParam(value = "date_to", defaultValue = "") String dateTo,
	// @RequestParam(value = "date_type", defaultValue = "") String dateType,
	// @RequestParam(value = "created_by", defaultValue = "") String createdBy
	) {

		System.out.println("Inside getTasks");

		/*
		 * List<SearchCriteriaParamList> lstCriteriaParam = new ArrayList<>();
		 * lstCriteriaParam.add(new SearchCriteriaParamList("status", status.toString(),
		 * "")); lstCriteriaParam.add(new SearchCriteriaParamList("date_from",
		 * dateFrom.toString(), "")); lstCriteriaParam.add(new
		 * SearchCriteriaParamList("date_to", dateTo.toString(), ""));
		 * lstCriteriaParam.add(new SearchCriteriaParamList("date_type",
		 * dateType.toString(), "")); lstCriteriaParam.add(new
		 * SearchCriteriaParamList("created_by", createdBy.toString(), ""));
		 */

		List<ORMTasksGet> lst = taskService.getTasks(searchCriteria);

		return new ResponseEntity<List<ORMTasksGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("saveTask")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveTask(@RequestBody ORMTasksSave ormTasksSave) {

		GeneralOperations.logMsg("Inside saveTask: ");

		ServiceResponse<ORMKeyValue> serviceResponse = taskService.saveTask(ormTasksSave);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.OK);

	}

	@RequestMapping("getTaskById/{task_id}")
	public @ResponseBody ResponseEntity<ORMTaskDetailGet> getTaskById(@PathVariable(value = "task_id") Long taskId) {

		System.out.println("Inside getTaskById");

		ORMTaskDetailGet objTask = taskService.getTaskById(taskId);

		return new ResponseEntity<ORMTaskDetailGet>(objTask, HttpStatus.OK);
	}

	@RequestMapping("getTaskLog/{task_id}")
	public @ResponseBody ResponseEntity<List<ORMTaskLogGet>> getTaskLog(@PathVariable(value = "task_id") Long taskId) {

		System.out.println("Inside getTaskLog");

		List<ORMTaskLogGet> lst = taskService.getTaskLog(taskId);

		return new ResponseEntity<List<ORMTaskLogGet>>(lst, HttpStatus.OK);
	}

	@RequestMapping("markAsCompleted")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> markAsCompleted(
			@RequestBody UpdateRecord updateRecord) {

		GeneralOperations.logMsg("Inside markAsCompleted: ");

		ServiceResponse<ORMKeyValue> serviceResponse = taskService.markAsCompleted(updateRecord);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.OK);

	}

	@RequestMapping("deleteTask")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> deleteTask(
			@RequestBody UpdateRecord updateRecord) {

		GeneralOperations.logMsg("Inside deleteTask: ");

		ServiceResponse<ORMKeyValue> serviceResponse = taskService.deleteTask(updateRecord);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.OK);

	}
}
