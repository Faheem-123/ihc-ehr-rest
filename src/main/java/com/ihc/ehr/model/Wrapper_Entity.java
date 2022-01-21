package com.ihc.ehr.model;

import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;

public class Wrapper_Entity {	
	
	EntityType type;	
	Operation operation;
	Object entity;
	
	
	
	public Wrapper_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Wrapper_Entity(EntityType type, Operation operation, Object entity) {
		super();
		this.type = type;
		this.operation = operation;
		this.entity = entity;
	}


	public EntityType getType() {
		return type;
	}
	public void setType(EntityType type) {
		this.type = type;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}	
}
