package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.TasksDOA;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMTaskDetailGet;
import com.ihc.ehr.model.ORMTaskLogGet;
import com.ihc.ehr.model.ORMTasksGet;
import com.ihc.ehr.model.ORMTasksSave;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.UpdateRecord;


@Service
public class TasksServiceImpl implements TasksService {
	
	@Autowired
	private TasksDOA taskDOA;

	@Override
	public List<ORMTasksGet> getTasks(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return taskDOA.getTasks(searchCriteria);
	}

	
	@Override
	public ServiceResponse<ORMKeyValue> saveTask(ORMTasksSave ormTasksSave) {
		// TODO Auto-generated method stub
		return taskDOA.saveTask(ormTasksSave);
	}


	@Override
	public ORMTaskDetailGet getTaskById( Long taskId) {
		// TODO Auto-generated method stub
		return taskDOA.getTaskById( taskId);
	}


	@Override
	public List<ORMTaskLogGet> getTaskLog(Long taskId) {
		// TODO Auto-generated method stub
		return taskDOA.getTaskLog(taskId);
	}


	@Override
	public ServiceResponse<ORMKeyValue> markAsCompleted(UpdateRecord updateRecord) {
		// TODO Auto-generated method stub
		return taskDOA.markAsCompleted(updateRecord);
	}


	@Override
	public ServiceResponse<ORMKeyValue> deleteTask(UpdateRecord updateRecord) {
		// TODO Auto-generated method stub
		return taskDOA.deleteTask(updateRecord);
	}

}
