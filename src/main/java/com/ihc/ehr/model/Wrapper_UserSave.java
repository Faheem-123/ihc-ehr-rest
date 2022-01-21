package com.ihc.ehr.model;

import java.util.List;

public class Wrapper_UserSave {

	private ORMSaveUser userSave;
	private List<ORMUser_Providers> lst_user_provider;
	private List<Long> lst_user_provider_deleted_ids;	

	public ORMSaveUser getUserSave() {
		return userSave;
	}

	public void setUserSave(ORMSaveUser userSave) {
		this.userSave = userSave;
	}

	public List<ORMUser_Providers> getLst_user_provider() {
		return lst_user_provider;
	}

	public void setLst_user_provider(List<ORMUser_Providers> lst_user_provider) {
		this.lst_user_provider = lst_user_provider;
	}

	public List<Long> getLst_user_provider_deleted_ids() {
		return lst_user_provider_deleted_ids;
	}

	public void setLst_user_provider_deleted_ids(List<Long> lst_user_provider_deleted_ids) {
		this.lst_user_provider_deleted_ids = lst_user_provider_deleted_ids;
	}
	
}
