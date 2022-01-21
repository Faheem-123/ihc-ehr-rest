package com.ihc.ehr.service;

import java.util.List;

import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMTaskDetailGet;
import com.ihc.ehr.model.ORMTaskLogGet;
import com.ihc.ehr.model.ORMTasksGet;
import com.ihc.ehr.model.ORMTasksSave;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.UpdateRecord;


public interface TasksService {

	List<ORMTasksGet> getTasks(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> saveTask(ORMTasksSave ormTasksSave);

	ORMTaskDetailGet getTaskById( Long taskId);

	List<ORMTaskLogGet> getTaskLog(Long taskId);

	ServiceResponse<ORMKeyValue> markAsCompleted(UpdateRecord updateRecord);

	ServiceResponse<ORMKeyValue> deleteTask(UpdateRecord updateRecord);

}
