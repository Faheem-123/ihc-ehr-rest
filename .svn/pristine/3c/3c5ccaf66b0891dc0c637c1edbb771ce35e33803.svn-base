package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ORMChartOfficeTest;
import com.ihc.ehr.model.ORMChartOfficetest_cpt;
import com.ihc.ehr.model.ORMChartSurgery;
import com.ihc.ehr.model.ORMGetClient_Insurances;
import com.ihc.ehr.model.ORMGetProviderModifier;
import com.ihc.ehr.model.ORMGetProviderPayers;
import com.ihc.ehr.model.ORMGetVitalGraphData;
import com.ihc.ehr.model.ORMInsurance_PayerTypes;
import com.ihc.ehr.model.ORMInsurance_Payers;
import com.ihc.ehr.model.ORMInsurance_setup;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPracticeInsurance;
import com.ihc.ehr.model.ORMSaveProvider_Payers;
import com.ihc.ehr.model.ORMgetMappingPracticeInsurances;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@Repository
public class InsurancesDAOImpl implements InsurancesDAO {

	@Autowired
	DBOperations db;
	
	@Override
	public List<ORMInsurance_PayerTypes> getInsurancePayerTypes() {
		// TODO Auto-generated method stub		
		List<ORMInsurance_PayerTypes> lstData = db.getStoreProcedureData("spPayerTypes_New", ORMInsurance_PayerTypes.class,null);
		return lstData;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveInsurancePayerTypes(ORMInsurance_PayerTypes obj) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getPayertype_id() != null && !obj.getPayertype_id().equals("")) {
			result = db.SaveEntity(obj, Operation.EDIT);
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setPayertype_id(db.IDGeneratorInsurance("insurance_payertypes", obj.getPractice_id()).toString());
			result = db.SaveEntity(obj, Operation.ADD);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(obj.getPayertype_id().toString());
		}

		return resp;
	}

	@Override
	public List<ORMInsurance_Payers> getPayerTypePayer(String payerType_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("PayerType_id", payerType_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPayerType_Payer", ORMInsurance_Payers.class, lstParam);

	}

	@Override
	public ServiceResponse<ORMKeyValue> saveInsurancePayer(ORMInsurance_Payers obj) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getPayer_id() != null && !obj.getPayer_id().equals("")) {
			result = db.SaveEntity(obj, Operation.EDIT);
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setPayer_id(db.IDGeneratorInsurance("payer", obj.getPractice_id()).toString());
			result = db.SaveEntity(obj, Operation.ADD);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(obj.getPayer_id().toString());
		}

		return resp;
	}

	@Override
	public List<ORMInsurance_setup> getPayerInsurances(String payer_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("payer_id", payer_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPayerInsurances", ORMInsurance_setup.class, lstParam);

	}

	@Override
	public ServiceResponse<ORMKeyValue> saveInsurances(ORMInsurance_setup obj) {
		// TODO Auto-generated method stub
				ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
				int result = 0;
				obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
				if (obj.getInsurance_id() != null && !obj.getInsurance_id().equals("")) {
					result = db.SaveEntity(obj, Operation.EDIT);
				} else {
					obj.setDate_created(DateTimeUtil.getCurrentDateTime());
					obj.setInsurance_id(db.IDGeneratorInsurance("insurance", obj.getPractice_id()).toString());
					result = db.SaveEntity(obj, Operation.ADD);
				}

				if (result == 0) {
					resp.setStatus(ServiceResponseStatus.ERROR);
					resp.setResponse("An Error Occured while saving record.");
				} else {
					resp.setStatus(ServiceResponseStatus.SUCCESS);
					resp.setResponse("Data has been saved successfully.");
					resp.setResult(obj.getInsurance_id().toString());
				}

				return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveProviderPayer(ORMSaveProvider_Payers obj) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getProvider_payer_id() != null && !obj.getProvider_payer_id().equals("")) {
			result = db.SaveEntity(obj, Operation.EDIT);
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setProvider_payer_id(db.IDGenerator("Provider_payers", Long.parseLong(obj.getPractice_id())).toString());
			result = db.SaveEntity(obj, Operation.ADD);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(obj.getProvider_payer_id().toString());
		}

		return resp;
	}

	@Override
	public List<ORMGetProviderPayers> getProviderPayer(String provider_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("provider_id", provider_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetProviderPayers", ORMGetProviderPayers.class, lstParam);

	}

	@Override
	public List<ORMGetProviderModifier> getProviderModifier() {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", "", String.class, ParameterMode.IN));
		List<ORMGetProviderModifier> lstData = db.getStoreProcedureData("spGetProviderModifier", ORMGetProviderModifier.class,lstParam);
		return lstData;
	}

	@Override
	public List<ORMGetClient_Insurances> getClient_Insurances(String criteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		List<ORMGetClient_Insurances> lstData = db.getStoreProcedureData("spGetClient_Insurances", ORMGetClient_Insurances.class,lstParam);
		return lstData;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveInsuranceslist(List<ORMInsurance_setup> lstSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		 
		for (ORMInsurance_setup ormSave : lstSave) {
			ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormSave.getInsurance_id())) {

				ormSave.setInsurance_id(db.IDGenerator("insurance", Long.parseLong(ormSave.getPractice_id())).toString());
				ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

				result = db.SaveEntity(ormSave, Operation.ADD);
			} else {
				result = db.SaveEntity(ormSave, Operation.EDIT);
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
	public List<ORMgetMappingPracticeInsurances> getUnMappedPracticeInsurances(String practice_id, String is_mapped,
			String payerid) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("is_mapped", is_mapped, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("payerid", payerid, String.class, ParameterMode.IN));
		List<ORMgetMappingPracticeInsurances> lstData = db.getStoreProcedureData("spGetMappingPracitceInsurance", ORMgetMappingPracticeInsurances.class,lstParam);
		return lstData;
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePracticeInsurance( List<ORMPracticeInsurance> lstSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		 
		for (ORMPracticeInsurance ormSave : lstSave) {
			ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
			if (GeneralOperations.isNullorEmpty(ormSave.getPractice_insurance_id())) {
				ormSave.setPractice_insurance_id(db.IDGenerator("practice_insurance", Long.parseLong(ormSave.getPractice_id())).toString());
				ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(ormSave, Operation.ADD);
			} else {
				result = db.SaveEntity(ormSave, Operation.EDIT);
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
}
