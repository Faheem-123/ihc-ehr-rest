package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.UserDAO;
import com.ihc.ehr.model.ORMGetChartModuleSetting;
import com.ihc.ehr.model.ORMGetLoginUserInfo;
import com.ihc.ehr.model.ORMGetUserAdministrationModules;
import com.ihc.ehr.model.ORMGetUserDetail;
import com.ihc.ehr.model.ORMGetUserSetup;
import com.ihc.ehr.model.ORMIdName;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMModuleDetail;
import com.ihc.ehr.model.ORMModuleRole;
import com.ihc.ehr.model.ORMModules;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORMUser;
import com.ihc.ehr.model.ORMUserAssignedProvider;
import com.ihc.ehr.model.ORMUserInsert;
import com.ihc.ehr.model.ORMUserPractices;
import com.ihc.ehr.model.ORMUserRoleList;
import com.ihc.ehr.model.ORMsearchCityZipState;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.UserDetails;
import com.ihc.ehr.model.ValidatedUser;
import com.ihc.ehr.model.Wrapper_UserSave;
import com.ihc.ehr.security.JwtAuthenticationRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public ORMGetLoginUserInfo getLogedInUserDetail(Long user_id) {
		// TODO Auto-generated method stub
		return userDAO.getLogedInUserDetail(user_id);
	}
	
	/*
	@Override
	public ValidatedUser ValidateUser(String user_name, String password) {
		// TODO Auto-generated method stub
		return userDAO.ValidateUser(user_name, password);
	}
	*/

	@Override
	public Boolean validateIP(String ip) {
		// TODO Auto-generated method stub
		return userDAO.validateIP(ip);
	}

	@Override
	public UserDetails getUserDetail(String user_id) {
		// TODO Auto-generated method stub
		return userDAO.getUserDetail(user_id);
	}
	
	@Override
	public List<ORMGetUserSetup> getAllUsers(String practice_id) {
		// TODO Auto-generated method stub
		return userDAO.getAllUsers(practice_id);
	}

	@Override
	public ORMGetUserDetail getUserDetails(String user_id) {
		// TODO Auto-generated method stub
		return userDAO.getUserDetails(user_id);
	}

	@Override
	public List<ORMUserRoleList> getuserRole(String practice_id) {
		// TODO Auto-generated method stub
		return userDAO.getuserRole(practice_id);
	}

	@Override
	public List<ORMGetChartModuleSetting> getchartModuleSetting(String practice_id) {
		// TODO Auto-generated method stub
		return userDAO.getchartModuleSetting(practice_id);
	}

	@Override
	public List<ORMIdName> getSuperBills(String practice_id) {
		// TODO Auto-generated method stub
		return userDAO.getSuperBills(practice_id);
	}

	@Override
	public List<ORMUserAssignedProvider> getUserAssignProvider(String user_id) {
		// TODO Auto-generated method stub
		return userDAO.getUserAssignProvider(user_id);
	}

	@Override
	public List<ORMIdName> getUserProviderAll(String user_id, String practice_id) {
		// TODO Auto-generated method stub
		return userDAO.getUserProviderAll(user_id,practice_id);
	}

	@Override
	public Boolean checkIfUserExsist(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return userDAO.checkIfUserExsist(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveUser(Wrapper_UserSave wrapper_user) {
		// TODO Auto-generated method stub
		return userDAO.saveUser(wrapper_user);
	}

	@Override
	public List<ORMModuleRole> getRoleList(String practice_id) {
		// TODO Auto-generated method stub
		return userDAO.getRoleList(practice_id);
	}

	@Override
	public List<ORMModuleDetail> getModuleDetails(String role_id) {
		// TODO Auto-generated method stub
		return userDAO.getModuleDetails(role_id);
	}

	@Override
	public List<ORMModules> getAllModulesList(String practice_id) {
		// TODO Auto-generated method stub
		return userDAO.getAllModulesList(practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveRoleModule(List<ORMModuleDetail> lstmodule) {
		// TODO Auto-generated method stub
		return userDAO.saveRoleModule(lstmodule);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveRole(ORMModuleRole objmodule) {
		// TODO Auto-generated method stub
		return userDAO.saveRole(objmodule);
	}

	@Override
	public List<ORMGetUserAdministrationModules> getRoleAdministrationModules(String practice_id, String role_id) {
		// TODO Auto-generated method stub
		return userDAO.getRoleAdministrationModules(practice_id,role_id);
	}

	@Override
	public int resetPassword(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return userDAO.resetPassword(searchCriteria);
	}

	@Override
	public ValidatedUser authenticateUser(JwtAuthenticationRequest authenticationRequest) {
		// TODO Auto-generated method stub
		return userDAO.authenticateUser(authenticationRequest);
	}
	@Override
	public List<ORMTwoColumnGeneric> AuthenticatePU(SearchCriteria searchCriteria) {
		return userDAO.AuthenticatePU(searchCriteria);
	}
	@Override
	public List<ORMThreeColum> getAllPractices() {
		return userDAO.getAllPractices();
	}
	@Override
	public List<ORMThreeColum> getUserPractices() {
		return userDAO.getUserPractices();
	}
	@Override
	public List<ORMUser> getBillingUserDetails(String practice_id) {
		return userDAO.getBillingUserDetails(practice_id);
	}
	@Override
	public Long saveupdateBillingUser(ORMUserInsert obj) {
		return userDAO.saveupdateBillingUser(obj);
	}
	@Override
	public Long SavePracticeList(List<ORMUserPractices> obj) {
		return userDAO.SavePracticeList(obj);
	}
	@Override
	public List<ORMsearchCityZipState> searchCityZipState(SearchCriteria searchCriteria) {
		return userDAO.searchCityZipState(searchCriteria);
	}
	@Override
	public int saveCityZipState(SearchCriteria searchCriteria) {
		return userDAO.saveCityZipState(searchCriteria);
	}
	@Override
	public int EditCityZipState(SearchCriteria searchCriteria) {
		return userDAO.EditCityZipState(searchCriteria);
	}
	@Override
	public Boolean getIsRecordExist(SearchCriteria searchCriteria) {
		return userDAO.getIsRecordExist(searchCriteria);
	}
}
