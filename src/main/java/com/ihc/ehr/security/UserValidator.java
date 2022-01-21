package com.ihc.ehr.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.ValidatedUser;

@Repository
public class UserValidator {
	
	@Autowired
	private DBOperations db; 
	
	public ValidatedUser ValidateUser(String user_name, String password,String app) {
		
		
		List<SpParameters> lstParam=new ArrayList<>();		
		
		lstParam.add(new SpParameters("username",user_name, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("password",password,String.class, ParameterMode.IN));
		
		List<ValidatedUser> lstValidateUser=null;
		
		
		switch (app) {
		case "APP_EHR":
			System.out.println("Validate EHR User: '"+user_name+"','"+password+"'");
			lstValidateUser=db.getStoreProcedureData("spValidateUser", ValidatedUser.class,lstParam);	
			break;
		case "APP_PHR":
			System.out.println("Validate PHR User: '"+user_name+"','"+password+"'");
			lstValidateUser=db.getStoreProcedureData("spVerifyAPILoginUser", ValidatedUser.class,lstParam);	
			break;
		default:
			break;
		}		
		
		if(lstValidateUser!=null && lstValidateUser.size()>0)
		{
			return (ValidatedUser) lstValidateUser.get(0);
		}
		else {
			return null;
		}
	}
	
	

}
