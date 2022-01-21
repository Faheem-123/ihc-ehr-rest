package com.ihc.ehr.util;

public class EnumUtil {

	public static enum Status {
		  ALL,
		  ACTIVE,
		  INACTIVE
	}
	
	public static enum Operation {
		  ADD,
		  EDIT,
		  DELETE
	}
	
	public static enum EntityType {
		  ENTITY,
		  QUERY
	}
	
	public static enum ServiceResponseStatus {
		  NOT_ALLOWED,
		  CONFIRMATION_REQUIRED,
		  SUCCESS,
		  ERROR,
		  NOT_FOUND,
		  AUTHORIZED,
		  UNAUTHORIZED,
		  INFO
	}
	
	public static enum ImmRegFileProcessingStatus {
		  
		SUCCESS,
		ERROR,
		NOT_MAPPED,
		INVALID_FORMAT
		  
	}
	
	public static enum SubmissionMethod {
		  ELECTRONIC,
		  HCFA
	}
	
	public static enum FaxServerEnum {
		  FAXAGE,
		  MFAX
	}
	
	
}
