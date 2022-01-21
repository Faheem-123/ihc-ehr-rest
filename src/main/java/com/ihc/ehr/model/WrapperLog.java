package com.ihc.ehr.model;

import java.util.List;

public class WrapperLog {
	
	List<LogHeader> lst_headers;
	List<?> lst_log;
	List<ORMGetLogList> lst_child_logs;	
	
	public List<LogHeader> getLst_headers() {
		return lst_headers;
	}
	public void setLst_headers(List<LogHeader> lst_headers) {
		this.lst_headers = lst_headers;
	}
	public List<?> getLst_log() {
		return lst_log;
	}
	public void setLst_log(List<?> lst_log) {
		this.lst_log = lst_log;
	}
	public List<ORMGetLogList> getLst_child_logs() {
		return lst_child_logs;
	}
	public void setLst_child_logs(List<ORMGetLogList> lst_child_logs) {
		this.lst_child_logs = lst_child_logs;
	}
	
	
}
