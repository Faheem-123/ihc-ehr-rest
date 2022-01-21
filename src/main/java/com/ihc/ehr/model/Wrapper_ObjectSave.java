package com.ihc.ehr.model;

import java.util.List;

public class Wrapper_ObjectSave<T> {
	
	T ormSave;
	List<ORMKeyValue> lstKeyValue;
	
	public T getOrmSave() {
		return ormSave;
	}
	public void setOrmSave(T ormSave) {
		this.ormSave = ormSave;
	}
	public List<ORMKeyValue> getLstKeyValue() {
		return lstKeyValue;
	}
	public void setLstKeyValue(List<ORMKeyValue> lstKeyValue) {
		this.lstKeyValue = lstKeyValue;
	}


}
