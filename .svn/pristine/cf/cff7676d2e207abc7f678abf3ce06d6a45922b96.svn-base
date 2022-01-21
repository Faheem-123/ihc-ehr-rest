package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
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
import com.ihc.ehr.model.ORMSaveUser;
import com.ihc.ehr.model.ORMScalarValue;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORMUser;
import com.ihc.ehr.model.ORMUserAssignedProvider;
import com.ihc.ehr.model.ORMUserInsert;
import com.ihc.ehr.model.ORMUserPractices;
import com.ihc.ehr.model.ORMUserRoleList;
import com.ihc.ehr.model.ORMUser_Providers;
import com.ihc.ehr.model.ORMsearchCityZipState;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.UserDetails;
import com.ihc.ehr.model.ValidatedUser;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.model.Wrapper_UserSave;
import com.ihc.ehr.security.JwtAuthenticationRequest;
import com.ihc.ehr.security.UserValidator;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private DBOperations db;

	@Autowired
	private UserValidator userValidator;

	@Override
	public ORMGetLoginUserInfo getLogedInUserDetail(Long user_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetLoginUserInfo> lst = db.getStoreProcedureData("spGetLogedInuserDetail", ORMGetLoginUserInfo.class,
				lstParam);

		if (!lst.isEmpty())
			return lst.get(0);
		else
			return null;
	}

	/*
	 * @Override public ValidatedUser ValidateUser(String user_name, String
	 * password) {
	 * 
	 * List<SpParameters> lstParam=new ArrayList<>();
	 * 
	 * lstParam.add(new SpParameters("username",user_name, String.class,
	 * ParameterMode.IN)); lstParam.add(new
	 * SpParameters("password",password,String.class, ParameterMode.IN));
	 * 
	 * System.out.println("spValidateUser '"+user_name+"','"+password+"'");
	 * 
	 * List<ValidatedUser>
	 * lstValidateUser=db.getStoreProcedureData("spValidateUser",
	 * ValidatedUser.class,lstParam);
	 * 
	 * 
	 * 
	 * if(lstValidateUser!=null && lstValidateUser.size()>0) { return
	 * (ValidatedUser) lstValidateUser.get(0); } else { return null; } }
	 */

	@Override
	public Boolean validateIP(String ip) {
		// TODO Auto-generated method stub

		String query = " select count(*) from ihc_ip_allowed where ip='" + ip + "'";

		int count = Integer.parseInt(db.getQuerySingleResult(query));

		return count > 0;

	}

	@Override
	public UserDetails getUserDetail(String user_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("user_id", user_id, String.class, ParameterMode.IN));

		List<UserDetails> lstUserDetail = db.getStoreProcedureData("spUserDetails", UserDetails.class, lstParam);

		if (lstUserDetail != null && lstUserDetail.size() > 0) {
			return (UserDetails) lstUserDetail.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMGetUserSetup> getAllUsers(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetUserSetup", ORMGetUserSetup.class, lstParam);
	}

	@Override
	public ORMGetUserDetail getUserDetails(String user_id) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id, String.class, ParameterMode.IN));

		List<ORMGetUserDetail> lstUserDetail = db.getStoreProcedureData("spUserDetails", ORMGetUserDetail.class,
				lstParam);

		if (lstUserDetail != null && lstUserDetail.size() > 0) {
			return (ORMGetUserDetail) lstUserDetail.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMUserRoleList> getuserRole(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetRole", ORMUserRoleList.class, lstParam);
	}

	@Override
	public List<ORMGetChartModuleSetting> getchartModuleSetting(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetChartModuleSettings", ORMGetChartModuleSetting.class, lstParam);
	}

	@Override
	public List<ORMIdName> getSuperBills(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetSuperbill", ORMIdName.class, lstParam);
	}

	@Override
	public List<ORMUserAssignedProvider> getUserAssignProvider(String user_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetUserAssignProvider", ORMUserAssignedProvider.class, lstParam);
	}

	@Override
	public List<ORMIdName> getUserProviderAll(String user_id, String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetUserProvider", ORMIdName.class, lstParam);
	}

	@Override
	public Boolean checkIfUserExsist(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		// purpose : new_record|patient_api_active_user|ehr_active_user

		Boolean isUserExist = false;
		List<SpParameters> lstCheck = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstCheck.add(
						new SpParameters(pram.getName(), pram.getValue().toString(), String.class, ParameterMode.IN));
			}
		}

		List<ORMScalarValue> lstResult = db.getStoreProcedureData("spCheckIfUserExists", ORMScalarValue.class,
				lstCheck);

		if (lstResult != null && lstResult.size() > 0) {
			ORMScalarValue obj = (ORMScalarValue) lstResult.get(0);

			System.out.println("User Already Exists:" + obj.getScalar_value());
			isUserExist = obj.getScalar_value().equalsIgnoreCase("1");// ? true : false ;
		}

		return isUserExist;
		/*
		 * List<SpParameters> lstParam=new ArrayList<>(); String user_name=""; String
		 * operatioin=""; String user_id=""; lstParam.add(new
		 * SpParameters("practice_id", searchCriteria.getPractice_id().toString(),
		 * String.class, ParameterMode.IN));
		 * 
		 * if (searchCriteria.getParam_list() != null &&
		 * !searchCriteria.getParam_list().isEmpty()) { for (SearchCriteriaParamList
		 * pram : searchCriteria.getParam_list()) {
		 * if(pram.getName().equals("user_name")) { user_name=pram.getValue(); } else
		 * if(pram.getName().equals("operatioin")) { operatioin=pram.getValue(); } else
		 * if(pram.getName().equals("user_id")) { user_id=pram.getValue(); } } } String
		 * Query = "select top 1 u.user_id as scalar_value " + "from users as u  " +
		 * "where ISNULL(u.deleted, 0)= 0 and u.practice_id='" +
		 * searchCriteria.getPractice_id().toString() + "'  " + "and u.user_name='" +
		 * user_name + "'"; if (!operatioin.equals("newrecord")) { Query +=
		 * " and u.user_id not in ('" + user_id + "')"; } String result=
		 * db.getQuerySingleResult(Query);
		 * GeneralOperations.logMsg("checkUserExsist result: "+result);
		 * if(GeneralOperations.isNotNullorEmpty(result)) return false; else return
		 * true;
		 */
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveUser(Wrapper_UserSave wrapper_user) {

		Boolean isUserAlreadyExist = false;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ORMSaveUser userSave = wrapper_user.getUserSave();

		List<ORMUser_Providers> lst_user_provider = wrapper_user.getLst_user_provider();
		List<Long> lst_user_provider_deleted_ids = wrapper_user.getLst_user_provider_deleted_ids();

		// in case of new user add
		if (userSave != null) {
			if (userSave.getUser_id() == null || userSave.getUser_id() <= 0) {

				SearchCriteria searchCriteria = new SearchCriteria();

				List<SearchCriteriaParamList> lst = new ArrayList<SearchCriteriaParamList>();
				lst.add(new SearchCriteriaParamList("user_name", userSave.getUser_name(), ""));
				lst.add(new SearchCriteriaParamList("purpose", "new_record", ""));

				searchCriteria.setParam_list(lst);

				isUserAlreadyExist = checkIfUserExsist(searchCriteria);
			}
		}

		if (!isUserAlreadyExist) {

			List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

			Long userId = (long) 0;

			if (userSave != null) {

				userSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
				if (userSave.getUser_id() == null || userSave.getUser_id() <= 0) {

					userId = db.IDGenerator("users", userSave.getPractice_id());

					userSave.setUser_id(userId);
					userSave.setDate_created(DateTimeUtil.getCurrentDateTime());

					
					/*if (userSave.getPractice_id() == 500 || userSave.getPractice_id() == 504) {
						userSave.setAllowed_ip(false);
					} else {
						userSave.setAllowed_ip(true);
					}*/

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, userSave));
				} else {
					userId = userSave.getUser_id();

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, userSave));
				}

				if (lst_user_provider != null && !lst_user_provider.isEmpty()) {
					for (ORMUser_Providers userProvider : lst_user_provider) {
						userProvider.setDate_modified(DateTimeUtil.getCurrentDateTime());

						if (userProvider.getUser_id() == null || userProvider.getUser_id() <= 0) {
							userProvider.setUser_id(userId);
						}

						if (userProvider.getUser_provider_id() == null || userProvider.getUser_provider_id() <= 0) {

							userProvider.setUser_provider_id(
									db.IDGenerator("user_providers", userProvider.getPractice_id()));
							userProvider.setDate_created(DateTimeUtil.getCurrentDateTime());

							lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, userProvider));

						} else {
							lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, userProvider));
						}
					}

				}

				if (lst_user_provider_deleted_ids != null && !lst_user_provider_deleted_ids.isEmpty()) {

					String strIds = "";

					for (Long id : lst_user_provider_deleted_ids) {
						if (strIds != "") {
							strIds += ",";
						}
						strIds += id;
					}

					if (strIds != "") {
						String query = " update user_providers set deleted=1,date_modified=getdate(),modified_user='"
								+ userSave.getModified_user() + "',client_date_modified='"
								+ userSave.getClient_date_created() + "',system_ip='" + userSave.getSystem_ip()
								+ "' where user_provider_id in (" + strIds + ") ";

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));
					}
				}

			}

			int result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
				resp.setResult(userSave.getUser_id().toString());
			}
		} else {
			resp.setStatus(ServiceResponseStatus.NOT_ALLOWED);
			resp.setResponse("Another user already exist with the same user name.");
		}
		return resp;
	}

	@Override
	public List<ORMModuleRole> getRoleList(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("PracticeID", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetModuleRole", ORMModuleRole.class, lstParam);
	}

	@Override
	public List<ORMModuleDetail> getModuleDetails(String role_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("role_id", role_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetModuleRoleDetail", ORMModuleDetail.class, lstParam);
	}

	@Override
	public List<ORMModules> getAllModulesList(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("PracticeID", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetModule", ORMModules.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveRoleModule(List<ORMModuleDetail> lstmodule) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		for (ORMModuleDetail ormModule : lstmodule) {
			db.ExecuteUpdateQuery("update module_role_detail set deleted=1 where role_id='" + ormModule.getRole_id()
					+ "' and practice_id='" + ormModule.getPractice_id() + "'");
			break;
		}
		for (ORMModuleDetail ormModule : lstmodule) {
			ormModule.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormModule.getModule_role_detail_id())) {

				ormModule.setModule_role_detail_id(
						db.IDGenerator("module_role_detail", Long.parseLong(ormModule.getPractice_id())).toString());
				ormModule.setDate_created(DateTimeUtil.getCurrentDateTime());

				result = db.SaveEntity(ormModule, Operation.ADD);
			} else {
				result = db.SaveEntity(ormModule, Operation.EDIT);
			}
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveRole(ORMModuleRole objmodule) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		objmodule.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(objmodule.getRole_id())) {

			objmodule.setRole_id(
					db.IDGenerator("module_role_detail", Long.parseLong(objmodule.getPractice_id())).toString());
			objmodule.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(objmodule, Operation.ADD);
		} else {
			result = db.SaveEntity(objmodule, Operation.EDIT);
		}
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(objmodule.getRole_id());
		}

		return resp;
	}

	@Override
	public List<ORMGetUserAdministrationModules> getRoleAdministrationModules(String practice_id, String role_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("role_id", role_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetRoleAdministrationModules", ORMGetUserAdministrationModules.class,
				lstParam);
	}

	@Override
	public int resetPassword(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String newPwd = "";
		String user_name = "";
		String user_id = "";
		String client_Date = "";
		int result = 0;
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("newPwd")) {
					newPwd = pram.getValue().toString();
				} else if (pram.getName().equals("user_name")) {
					user_name = pram.getValue().toString();
				} else if (pram.getName().equals("user_id")) {
					user_id = pram.getValue().toString();
				} else if (pram.getName().equals("client_Date")) {
					client_Date = pram.getValue().toString();
				}
			}
			String strQuery = "update users set password ='" + newPwd + "', modified_user = '" + user_name
					+ "', date_modified =getdate() ,client_date_modified = '" + client_Date + "' where user_id = '"
					+ user_id + "' ";
			result = db.ExecuteUpdateQuery(strQuery);
		}
		return result;
	}

	@Override
	public ValidatedUser authenticateUser(JwtAuthenticationRequest authenticationRequest) {
		// TODO Auto-generated method stub
		return userValidator.ValidateUser(authenticationRequest.getUsername(), authenticationRequest.getPassword(),
				"APP_" + authenticationRequest.getApp());
	}
	/*@Override
	public int AuthenticatePU(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String user = "";
		String ps = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("user")) {
					user = pram.getValue();
				} else if (pram.getName().equals("ps")) {
					ps = pram.getValue();
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("provider_id", user.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("password", ps.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class, ParameterMode.IN));
		List<ORMTwoColumnGeneric> lst = db.getStoreProcedureData("spAuthenticateProviderUser",
				ORMTwoColumnGeneric.class, lstParam);
		return lst;
	}*/
	@Override
	public List<ORMTwoColumnGeneric> AuthenticatePU(SearchCriteria searchCriteria) {
		//String user = "";
		String ps = "";
		String provID = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("user")) {
					//user = pram.getValue();
				} else if (pram.getName().equals("ps")) {
					ps = pram.getValue();
				} else if (pram.getName().equals("provID")) {
					provID = pram.getValue();
				}
			}
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("provider_id", provID.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("password", ps.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class, ParameterMode.IN));
		List<ORMTwoColumnGeneric> lst = db.getStoreProcedureData("spAuthenticateProviderUser_new",
				ORMTwoColumnGeneric.class, lstParam);
		return lst;
	}
	@Override
	public List<ORMThreeColum> getAllPractices() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetUserALLPractices", ORMThreeColum.class, null);
	}
	@Override
	public List<ORMThreeColum> getUserPractices() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetUserPractices", ORMThreeColum.class, null);
	}
	@Override
	public List<ORMUser> getBillingUserDetails(String practice_id) {        
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("PracticeId", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetUsers", ORMUser.class, lstParam);
	}
	@Override
	public Long saveupdateBillingUser(ORMUserInsert obj) {
		System.out.println("**** saveupdateBillingUser: "+obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		obj.setSignature_path("");
	
		
		if (obj.getUser_id() != null && obj.getUser_id() != "") {
			System.out.println("EDIT...");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return (Long.parseLong(obj.getUser_id()));
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setUser_id(db.IDGenerator("users", Long.parseLong(obj.getPractice_id())).toString());
			System.out.println("SAVE...");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return (Long.parseLong(obj.getUser_id()));
			else
				return (long) 0;
		}
	}
	@Override
	public Long SavePracticeList(List<ORMUserPractices> obj) {
			int result = 0;
			Boolean isDel = false;
			if (obj != null && obj.size() > 0) {
				for (ORMUserPractices orm : obj) {
					if(isDel!=true) {
						String strQuery = "delete from users_practices where user_id = '" + orm.getUser_id() + "' ";
						db.ExecuteUpdateQuery(strQuery);
						isDel = true;
					}
					if (orm.getUser_practice_id() == null || orm.getUser_practice_id() == "") // New
					{
						orm.setUser_practice_id(db.IDGenerator("users_practices", Long.parseLong("499")).toString());
						orm.setDate_created(DateTimeUtil.getCurrentDateTime());
						result += db.SaveEntity(orm, Operation.ADD);
					} else// modification
					{
						result += db.SaveEntity(orm, Operation.EDIT);
					}
				}
			}
			isDel = false;
			return (long) result;
	}
	@Override
	public List<ORMsearchCityZipState> searchCityZipState(SearchCriteria searchCriteria) {
		String rdoType = "";
		String textValue = "";
		String strquery = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "rdoType":
					if (pram.getValue() != "" && pram.getValue() != null) {
						rdoType = pram.getValue();
					}
					break;
				case "textValue":
					if (pram.getValue() != "" && pram.getValue() != null) {
						textValue = pram.getValue();
					}
					break;
					
				default:
					break;
				}
			}
		}
		if(rdoType.toLowerCase().equals("city")){
			strquery+=" and city like '%"+ textValue +"%'";
		}
		if(rdoType.toLowerCase().equals("zipcode")){
			strquery=" and zip_code like '%"+ textValue +"%'";
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", strquery, String.class, ParameterMode.IN));
		List<ORMsearchCityZipState> lst = db.getStoreProcedureData("spGetZipCityState_new", ORMsearchCityZipState.class, lstParam);
		return lst;
	}
	@Override
	public int saveCityZipState(SearchCriteria searchCriteria) {
		String zip_code = "";
		String city = "";
		String state = "";
		String created_user = "";
		String client_date_created = "";
		String modified_user = "";
		String client_date_modified = "";
		String strQry= "";
		//String option = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "zip_code":
					if (pram.getValue() != "" && pram.getValue() != null) {
						zip_code = pram.getValue();
					}
					break;
				case "city":
					if (pram.getValue() != "" && pram.getValue() != null) {
						city = pram.getValue();
					}
					break;
					
				case "state":
					if (pram.getValue() != "" && pram.getValue() != null) {
						state = pram.getValue();
					}
					break;
				case "created_user":
					if (pram.getValue() != "" && pram.getValue() != null) {
						created_user = pram.getValue();
					}
					break;
				case "client_date_created":
					if (pram.getValue() != "" && pram.getValue() != null) {
						client_date_created = pram.getValue();
					}
					break;
				case "modified_user":
					if (pram.getValue() != "" && pram.getValue() != null) {
						modified_user = pram.getValue();
					}
					break;
				case "client_date_modified":
					if (pram.getValue() != "" && pram.getValue() != null) {
						client_date_modified = pram.getValue();
					}
					break;
				//case "option":
					//if (pram.getValue() != "" && pram.getValue() != null) {
					//	option = pram.getValue();
					//}
					//break;
					
				default:
					break;
				}
			}
		}
		try{
			//if(option.equals("new")) {
				strQry = " insert into zip_state (zip_code, city, state, deleted, created_user, client_date_created, modified_user, client_date_modified, date_created, date_modified) values ('"+ zip_code +"','"+ city +"','"+ state +"','0','"+ created_user +"','"+ client_date_created +"','"+ modified_user +"','"+ client_date_modified +"', getdate() , getdate() )";	
			//}else {
			//	strQry = " update zip_state set zip_code='"+ zip_code +"', city= '"+ city +"', state= '"+ state +"' ,modified_user='"+ modified_user +"',client_date_modified='"+ client_date_modified +"',date_modified=getdate() where zip_code= '"+ zip_code +"' and city= '"+ city +"' and state = '"+ state +"' ";
			//}
			
			return db.ExecuteUpdateQuery(strQry);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int EditCityZipState(SearchCriteria searchCriteria) {
		String zip_code = "";
		String city = "";
		String state = "";
		String oldcity = "";
		String oldstate = "";
		//String created_user = "";
		//String client_date_created = "";
		String modified_user = "";
		String client_date_modified = "";
		String strQry= "";
		//String option = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "zip_code":
					if (pram.getValue() != "" && pram.getValue() != null) {
						zip_code = pram.getValue();
					}
					break;
				case "city":
					if (pram.getValue() != "" && pram.getValue() != null) {
						city = pram.getValue();
					}
					break;
					
				case "state":
					if (pram.getValue() != "" && pram.getValue() != null) {
						state = pram.getValue();
					}
					break;
				//case "created_user":
				//	if (pram.getValue() != "" && pram.getValue() != null) {
				//		created_user = pram.getValue();
				//	}
				//	break;
				//case "client_date_created":
				//	if (pram.getValue() != "" && pram.getValue() != null) {
				//		client_date_created = pram.getValue();
				//	}
				//	break;
				case "modified_user":
					if (pram.getValue() != "" && pram.getValue() != null) {
						modified_user = pram.getValue();
					}
					break;
				case "client_date_modified":
					if (pram.getValue() != "" && pram.getValue() != null) {
						client_date_modified = pram.getValue();
					}
					break;
				case "oldcity":
					if (pram.getValue() != "" && pram.getValue() != null) {
						oldcity = pram.getValue();
					}
					break;
				case "oldstate":
					if (pram.getValue() != "" && pram.getValue() != null) {
						oldstate = pram.getValue();
					}
					break;
					
				default:
					break;
				}
			}
		}
		try{
			strQry = " update zip_state set zip_code='"+ zip_code +"', city= '"+ city +"', state= '"+ state +"' ,modified_user='"+ modified_user +"',client_date_modified='"+ client_date_modified +"',date_modified=getdate() where zip_code= '"+ zip_code +"' and city= '"+ oldcity +"' and state = '"+ oldstate +"' ";
			return db.ExecuteUpdateQuery(strQry);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public Boolean getIsRecordExist(SearchCriteria searchCriteria) {
		Boolean isUserExist = false;
		String txtZip = "";
		String ddlCity = "";
		String txtState = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "txtZip":
					if (pram.getValue() != "" && pram.getValue() != null) {
						txtZip = pram.getValue();
					}
					break;
				case "ddlCity":
					if (pram.getValue() != "" && pram.getValue() != null) {
						ddlCity = pram.getValue();
					}
					break;
					
				case "txtState":
					if (pram.getValue() != "" && pram.getValue() != null) {
						txtState = pram.getValue();
					}
					break;
				default:
					break;
				}
			}
		}
		int count;
		try{
			String strQry = " select COUNT(*)as scalar_value FROM zip_state where zip_code='"+txtZip+"' and replace(city,' ','')=replace('"+ddlCity+"',' ','') and state='"+txtState+"' and isnull(deleted,0)<>1 ";
			count = Integer.parseInt(db.getQuerySingleResult(strQry));
		}catch(Exception e){
			e.printStackTrace();
			count = 0;
		}
		if (count > 0) {
			isUserExist = true;
		}else {
			isUserExist = false;
		}

		return isUserExist;
	}
}