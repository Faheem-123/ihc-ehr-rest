package com.ihc.ehr.model;

import javax.persistence.ParameterMode;

public class SpParameters {
	
	private String paramName;
	private String paramValue;
	private Class<?> parmClass;
	private ParameterMode parmMode;
	
	
	
	public SpParameters(String paramName, String paramValue, Class<?> parmClass, ParameterMode parmMode) {
		super();
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.parmClass = parmClass;
		this.parmMode = parmMode;
	}
	
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public Class<?> getParmClass() {
		return parmClass;
	}
	public void setParmClass(Class<?> parmClass) {
		this.parmClass = parmClass;
	}
	public ParameterMode getParmMode() {
		return parmMode;
	}
	public void setParmMode(ParameterMode parmMode) {
		this.parmMode = parmMode;
	}
	
	

}
