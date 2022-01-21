package com.ihc.ehr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihc.ehr.model.ORMDeleteRecord;
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
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.service.UserService;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userServcie;
	@Autowired
	GeneralService generalService;

	@RequestMapping("/hello")
	public String sayHello() {

		System.out.println("hello");
		return "Hello";
	}

	@RequestMapping("/getLogedInUserDetail/{user_id}")
	public @ResponseBody ResponseEntity<ORMGetLoginUserInfo> getLogedInUserDetail(
			@PathVariable(value = "user_id") Long user_id) {
		ORMGetLoginUserInfo user = userServcie.getLogedInUserDetail(user_id);
		return new ResponseEntity<ORMGetLoginUserInfo>(user, HttpStatus.OK);
	}

	@RequestMapping("getAllUsers/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetUserSetup>> getAllUsers(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMGetUserSetup> lst = userServcie.getAllUsers(practice_id);
		return new ResponseEntity<List<ORMGetUserSetup>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getUserDetails/{user_id}")
	public @ResponseBody ResponseEntity<ORMGetUserDetail> getUserDetails(
			@PathVariable(value = "user_id") String user_id) {
		ORMGetUserDetail user = userServcie.getUserDetails(user_id);

		return new ResponseEntity<ORMGetUserDetail>(user, HttpStatus.OK);
	}

	@RequestMapping("getuserRole/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMUserRoleList>> getuserRole(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMUserRoleList> lst = userServcie.getuserRole(practice_id);
		return new ResponseEntity<List<ORMUserRoleList>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getchartModuleSetting/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetChartModuleSetting>> getchartModuleSetting(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMGetChartModuleSetting> lst = userServcie.getchartModuleSetting(practice_id);
		return new ResponseEntity<List<ORMGetChartModuleSetting>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getSuperBills/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdName>> getSuperBills(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMIdName> lst = userServcie.getSuperBills(practice_id);
		return new ResponseEntity<List<ORMIdName>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/detail/{user_id}")
	public @ResponseBody ResponseEntity<UserDetails> getUserDetail(@PathVariable(value = "user_id") String user_id) {
		UserDetails user = userServcie.getUserDetail(user_id);

		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	}

	@RequestMapping("getUserAssignProvider/{user_id}")
	public @ResponseBody ResponseEntity<List<ORMUserAssignedProvider>> getUserAssignProvider(
			@PathVariable(value = "user_id") String user_id) {

		List<ORMUserAssignedProvider> lst = userServcie.getUserAssignProvider(user_id);
		return new ResponseEntity<List<ORMUserAssignedProvider>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getUserProviderAll/{user_id}/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdName>> getUserProviderAll(
			@PathVariable(value = "user_id") String user_id, @PathVariable(value = "practice_id") String practice_id) {

		List<ORMIdName> lst = userServcie.getUserProviderAll(user_id, practice_id);
		return new ResponseEntity<List<ORMIdName>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/checkIfUserExsist")
	public @ResponseBody ResponseEntity<Boolean> checkIfUserExsist(@RequestBody SearchCriteria searchCriteria) {

		return new ResponseEntity<Boolean>(userServcie.checkIfUserExsist(searchCriteria), HttpStatus.OK);

	}

	@RequestMapping("/saveUser")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveUser(
			@RequestBody Wrapper_UserSave wrapper_user) {

		ServiceResponse<ORMKeyValue> resp = userServcie.saveUser(wrapper_user);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}
	
	@RequestMapping("/deleteUser")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteUser(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteUser: " + ormDeleteRecord.getColumn_id());

		int result = 0;
		ormDeleteRecord.setTable_name("users");
		ormDeleteRecord.setColumn_name("user_id");
		result = generalService.deleteRecord(ormDeleteRecord);

		ormDeleteRecord.setTable_name("user_providers");
		ormDeleteRecord.setColumn_name("user_id");
		result += generalService.deleteRecord(ormDeleteRecord);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteUser.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/deleteUserProvider")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteUserProvider(
			@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteUser: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("user_providers");
		ormDeleteRecord.setColumn_name("user_provider_id");

		int result = generalService.updateQuery(
				"update user_providers set deleted=1 where user_provider_id='" + ormDeleteRecord.getColumn_id() + "'");

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteUserProvider.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	/*
	 * @RequestMapping("/setDefaultUserProvider") public
	 * ResponseEntity<ServiceResponse<ORMKeyValue>>
	 * setDefaultUserProvider(@RequestBody ORMDeleteRecord ormDeleteRecord) {
	 * 
	 * ormDeleteRecord.setTable_name("user_providers");
	 * ormDeleteRecord.setColumn_name("user_provider_id");
	 * 
	 * int result= generalService.updateQuery("update users set default_physician='"
	 * +ormDeleteRecord.getColumn_id().split("\\^")[1]+"' where user_id='"
	 * +ormDeleteRecord.getColumn_id().split("\\^")[0]+"'");
	 * 
	 * ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>(); if
	 * (result == 0) { resp.setStatus(ServiceResponseStatus.ERROR);
	 * resp.setResponse("An Error Occured while setDefaultUserProvider."); } else {
	 * resp.setStatus(ServiceResponseStatus.SUCCESS);
	 * resp.setResult(Long.toString(result)); } return new
	 * ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
	 * 
	 * }
	 */
	@RequestMapping("getRoleList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMModuleRole>> getRoleList(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMModuleRole> lst = userServcie.getRoleList(practice_id);
		return new ResponseEntity<List<ORMModuleRole>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getModuleDetails/{role_id}")
	public @ResponseBody ResponseEntity<List<ORMModuleDetail>> getModuleDetails(
			@PathVariable(value = "role_id") String role_id) {

		List<ORMModuleDetail> lst = userServcie.getModuleDetails(role_id);
		return new ResponseEntity<List<ORMModuleDetail>>(lst, HttpStatus.OK);
	}

	@RequestMapping("getAllModulesList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMModules>> getAllModulesList(
			@PathVariable(value = "practice_id") String practice_id) {

		List<ORMModules> lst = userServcie.getAllModulesList(practice_id);
		return new ResponseEntity<List<ORMModules>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveRoleModule")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveRoleModule(
			@RequestBody List<ORMModuleDetail> lstmodule) {

		ServiceResponse<ORMKeyValue> resp = userServcie.saveRoleModule(lstmodule);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/saveRole")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveRole(@RequestBody ORMModuleRole objmodule) {

		ServiceResponse<ORMKeyValue> resp = userServcie.saveRole(objmodule);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/deleteRole")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> deleteRole(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("deleteUser: " + ormDeleteRecord.getColumn_id());

		ormDeleteRecord.setTable_name("module_role");
		ormDeleteRecord.setColumn_name("role_id");

		int result = generalService.deleteRecord(ormDeleteRecord);
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteRole.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("getRoleAdministrationModules")
	public @ResponseBody ResponseEntity<List<ORMGetUserAdministrationModules>> getRoleAdministrationModules(
			@PathVariable(value = "practice_id") String practice_id, @PathVariable(value = "role_id") String role_id) {

		List<ORMGetUserAdministrationModules> lst = userServcie.getRoleAdministrationModules(practice_id, role_id);
		return new ResponseEntity<List<ORMGetUserAdministrationModules>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/resetPassword")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> resetPassword(@RequestBody SearchCriteria searchCriteria) {
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		int result = userServcie.resetPassword(searchCriteria);
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleteRole.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Long.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ValidatedUser>> authenticateUser(
			@RequestBody JwtAuthenticationRequest authenticationRequest) {

		ValidatedUser validatedUser = userServcie.authenticateUser(authenticationRequest);

		ServiceResponse<ValidatedUser> response = new ServiceResponse<>();

		if (validatedUser != null) {
			List<ValidatedUser> lst = new ArrayList<>();
			lst.add(validatedUser);
			response.setStatus(ServiceResponseStatus.AUTHORIZED);
			response.setResponse_list(lst);
			response.setResponse("authenticated");
			response.setResult(validatedUser.getUser_name());
		} else {
			response.setStatus(ServiceResponseStatus.UNAUTHORIZED);
			response.setResponse("un-authenticated");
		}

		return new ResponseEntity<ServiceResponse<ValidatedUser>>(response, HttpStatus.OK);
	}
	@RequestMapping("/AuthenticatePU")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> AuthenticatePU(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMTwoColumnGeneric> lst = userServcie.AuthenticatePU(searchCriteria);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/getAllPractices")
	public @ResponseBody ResponseEntity<List<ORMThreeColum>> getAllPractices() {
		List<ORMThreeColum> lst = userServcie.getAllPractices();
		return new ResponseEntity<List<ORMThreeColum>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/getUserPractices")
	public @ResponseBody ResponseEntity<List<ORMThreeColum>> getUserPractices() {
		List<ORMThreeColum> lst = userServcie.getUserPractices();
		return new ResponseEntity<List<ORMThreeColum>>(lst, HttpStatus.OK);
	}
	@RequestMapping("getBillingUserDetails/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMUser>> getBillingUserDetails(
			@PathVariable(value = "practice_id") String practice_id) {
		List<ORMUser> lst = userServcie.getBillingUserDetails(practice_id);
		return new ResponseEntity<List<ORMUser>>(lst, HttpStatus.OK);
	}
	@RequestMapping("/deleteBillingUser")
	public int deleteBillingUser(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("users");
		ormDeleteRecord.setColumn_name("user_id");
		return generalService.deleteRecord(ormDeleteRecord);
	}
	@RequestMapping("/saveupdateBillingUser")
	public ResponseEntity<ORMUserInsert> saveupdateBillingUser(
			@RequestBody ORMUserInsert obj) {
		userServcie.saveupdateBillingUser(obj);
		return new ResponseEntity<ORMUserInsert>(obj, HttpStatus.OK);
	}
	@RequestMapping("/SavePracticeList")
	public ResponseEntity<List<ORMUserPractices>> SavePracticeList(
			@RequestBody List<ORMUserPractices> obj) {
		userServcie.SavePracticeList(obj);
		return new ResponseEntity<List<ORMUserPractices>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/searchCityZipState")
	public @ResponseBody ResponseEntity<List<ORMsearchCityZipState>> searchCityZipState(
			@RequestBody SearchCriteria searchCriteria){	
		List<ORMsearchCityZipState> lst=userServcie.searchCityZipState(searchCriteria);
		return new ResponseEntity<List<ORMsearchCityZipState>>(lst , HttpStatus.OK);
	}
	@RequestMapping("/saveCityZipState")
	public long saveCityZipState(@RequestBody SearchCriteria searchCriteria) {
		return userServcie.saveCityZipState(searchCriteria);

	}
	@RequestMapping("/EditCityZipState")
	public long EditCityZipState(@RequestBody SearchCriteria searchCriteria) {
		return userServcie.EditCityZipState(searchCriteria);

	}
	@RequestMapping("/getIsRecordExist")
	public @ResponseBody ResponseEntity<Boolean> getIsRecordExist(@RequestBody SearchCriteria searchCriteria) {
		return new ResponseEntity<Boolean>(userServcie.getIsRecordExist(searchCriteria), HttpStatus.OK);
	}
}
