package com.ihc.ehr.dao;

import java.util.List;

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
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.UserDetails;
import com.ihc.ehr.model.ValidatedUser;
import com.ihc.ehr.model.Wrapper_UserSave;
import com.ihc.ehr.security.JwtAuthenticationRequest;
import com.ihc.ehr.model.ORMsearchCityZipState;

public interface UserDAO {
	
	//public ValidatedUser ValidateUser(String user_name,String password);
	public ORMGetLoginUserInfo getLogedInUserDetail(Long user_id);
	
	public Boolean validateIP(String ip);

	public UserDetails getUserDetail(String user_id);
	
	public List<ORMGetUserSetup> getAllUsers(String practice_id);

	public ORMGetUserDetail getUserDetails(String user_id);

	public List<ORMUserRoleList> getuserRole(String practice_id);

	public List<ORMGetChartModuleSetting> getchartModuleSetting(String practice_id);

	public List<ORMIdName> getSuperBills(String practice_id);

	public List<ORMUserAssignedProvider> getUserAssignProvider(String user_id);

	public List<ORMIdName> getUserProviderAll(String user_id, String practice_id);

	public Boolean checkIfUserExsist(SearchCriteria searchCriteria);

	public ServiceResponse<ORMKeyValue> saveUser(Wrapper_UserSave wrapper_user);

	public List<ORMModuleRole> getRoleList(String practice_id);

	public List<ORMModuleDetail> getModuleDetails(String role_id);

	public List<ORMModules> getAllModulesList(String practice_id);

	public ServiceResponse<ORMKeyValue> saveRoleModule(List<ORMModuleDetail> lstmodule);

	public ServiceResponse<ORMKeyValue> saveRole(ORMModuleRole objmodule);

	public List<ORMGetUserAdministrationModules> getRoleAdministrationModules(String practice_id, String role_id);

	public int resetPassword(SearchCriteria searchCriteria);

	public ValidatedUser authenticateUser(JwtAuthenticationRequest authenticationRequest);
	List<ORMTwoColumnGeneric> AuthenticatePU(SearchCriteria searchCriteria);
	List<ORMThreeColum> getAllPractices();
	List<ORMThreeColum> getUserPractices();
	List<ORMUser> getBillingUserDetails(String practice_id);
	public Long saveupdateBillingUser(ORMUserInsert obj);
	public Long SavePracticeList(List<ORMUserPractices> obj);
	List<ORMsearchCityZipState> searchCityZipState(SearchCriteria searchCriteria);
	int saveCityZipState(SearchCriteria searchCriteria);
	int EditCityZipState(SearchCriteria searchCriteria);
	public Boolean getIsRecordExist(SearchCriteria searchCriteria);
}
