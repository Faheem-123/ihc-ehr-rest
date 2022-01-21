package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.dao.ImmunizationDAO;
import com.ihc.ehr.model.ImmRegVXUCriteria;
import com.ihc.ehr.model.ORMGetPatImmRegInfoDisplay;
import com.ihc.ehr.model.HL7FileGenerationResult;
import com.ihc.ehr.model.ImmRegQBPCriteria;
import com.ihc.ehr.model.ORMImmCodeSet;
import com.ihc.ehr.model.ORMImmInventoryDetailGet;
import com.ihc.ehr.model.ORMImmInventorySave;
import com.ihc.ehr.model.ORMImmInventoryUsageGet;
import com.ihc.ehr.model.ORMImmInventoryVaccineListGet;
import com.ihc.ehr.model.ORMImmManufacturerGet;
import com.ihc.ehr.model.ORMImmNDCGet;
import com.ihc.ehr.model.ORMImmProceduresGet;
import com.ihc.ehr.model.ORMImmRegClinicsGet;
import com.ihc.ehr.model.ORMImmRegMessagesGet;
import com.ihc.ehr.model.ORMImmRegMsgErrorsGet;
import com.ihc.ehr.model.ORMImmRegMsgImmunizationsGet;
import com.ihc.ehr.model.ORMImmRoutesListGet;
import com.ihc.ehr.model.ORMImmSearchListGet;
import com.ihc.ehr.model.ORMImmSetupAllListGet;
import com.ihc.ehr.model.ORMImmSetupPracticeListGet;
import com.ihc.ehr.model.ORMImmSitesListGet;
import com.ihc.ehr.model.ORMImmTradeNameGet;
import com.ihc.ehr.model.ORMImmTradeNameSearchListGet;
import com.ihc.ehr.model.ORMImmVISGet;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPracticeImmunizationSave;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.UpdateRecord;
import com.ihc.ehr.model.WrapperImmRegEvaluationHistoryForecastMessageDetails;

@Service
public class ImmunizationServiceImpl implements ImmunizationService {

	@Autowired
	ImmunizationDAO immDAO;

	@Override
	public List<ORMImmRegClinicsGet> getClinics(Long practice_id) {
		// TODO Auto-generated method stub
		return immDAO.getClinics(practice_id);
	}

	@Override
	public List<ORMImmSetupAllListGet> getSetupImmAllList(Long practice_id) {
		// TODO Auto-generated method stub
		return immDAO.getSetupImmAllList(practice_id);
	}

	@Override
	public List<ORMImmSetupPracticeListGet> getSetupImmPracticeList(Long practice_id) {
		// TODO Auto-generated method stub
		return immDAO.getSetupImmPracticeList(practice_id);
	}

	@Override
	public List<ORMImmManufacturerGet> getImmManufacturer(String cvx_code) {
		// TODO Auto-generated method stub
		return immDAO.getImmManufacturer(cvx_code);
	}

	@Override
	public List<ORMImmTradeNameGet> getImmTradeName(String cvx_code) {
		// TODO Auto-generated method stub
		return immDAO.getImmTradeName(cvx_code);
	}

	@Override
	public List<ORMImmProceduresGet> getImmProcedure(String cvx_code) {
		// TODO Auto-generated method stub
		return immDAO.getImmProcedure(cvx_code);
	}

	@Override
	public List<ORMImmNDCGet> getImmNDC(String cvx_code) {
		// TODO Auto-generated method stub
		return immDAO.getImmNDC(cvx_code);
	}

	@Override
	public List<ORMImmVISGet> getImmVIS(String cvx_code) {
		// TODO Auto-generated method stub
		return immDAO.getImmVIS(cvx_code);
	}

	@Override
	public List<ORMImmRoutesListGet> getImmRouteList() {
		// TODO Auto-generated method stub
		return immDAO.getImmRouteList();
	}

	@Override
	public List<ORMImmSitesListGet> getImmSiteList() {
		// TODO Auto-generated method stub
		return immDAO.getImmSiteList();
	}

	@Override
	public List<ORMImmCodeSet> getImmCodeSet() {
		// TODO Auto-generated method stub
		return immDAO.getImmCodeSet();
	}

	@Override
	public ServiceResponse<ORMKeyValue> savePracticeImmunization(
			ORMPracticeImmunizationSave ormPracticeImmunizationSave) {
		// TODO Auto-generated method stub
		return immDAO.savePracticeImmunization(ormPracticeImmunizationSave);
	}

	@Override
	public List<ORMImmInventoryVaccineListGet> getInventoryVaccineList(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return immDAO.getInventoryVaccineList(searchCriteria);
	}

	@Override
	public List<ORMImmInventoryDetailGet> getInventoryVaccineDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return immDAO.getInventoryVaccineDetails(searchCriteria);
	}

	@Override
	public List<ORMImmTradeNameSearchListGet> getImmunizationTradeNamePracticeSearchList(Long practice_id) {
		// TODO Auto-generated method stub
		return immDAO.getImmunizationTradeNamePracticeSearchList(practice_id);
	}

	@Override
	public List<ORMImmSearchListGet> getImmunizationPracticeSearchList(String practice_id, String location_id) {
		// TODO Auto-generated method stub
		return immDAO.getImmunizationPracticeSearchList(practice_id, location_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveImmunizationInventory(ORMImmInventorySave ormImmunizationInventorySave) {
		// TODO Auto-generated method stub
		return immDAO.saveImmunizationInventory(ormImmunizationInventorySave);
	}

	@Override
	public List<ORMImmInventoryUsageGet> getImmunizationInventoryUsage(Long inventory_id) {
		// TODO Auto-generated method stub
		return immDAO.getImmunizationInventoryUsage(inventory_id);
	}

	@Override
	public List<ORMImmRegMessagesGet> getImmRegMessages(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return immDAO.getImmRegMessages(searchCriteria);
	}

	@Override
	public List<ORMImmRegMsgImmunizationsGet> getImmRegMsgImmunizations(Long message_id) {
		// TODO Auto-generated method stub
		return immDAO.getImmRegMsgImmunizations(message_id);
	}

	@Override
	public List<ORMImmRegMsgErrorsGet> getImmRegMsgErrors(Long message_id) {
		// TODO Auto-generated method stub
		return immDAO.getImmRegMsgErrors(message_id);
	}

	@Override
	public WrapperImmRegEvaluationHistoryForecastMessageDetails getImmRegEvaluationHistoryForecastMessageDetails(
			String registryCode,Long messageId) {
		// TODO Auto-generated method stub
		return immDAO.getImmRegEvaluationHistoryForecastMessageDetails(registryCode,messageId);
	}

	@Override
	public ServiceResponse<HL7FileGenerationResult> generateVXUFile(ImmRegVXUCriteria immRegVXUCriteria) {
		// TODO Auto-generated method stub
		return immDAO.generateVXUFile(immRegVXUCriteria);
	}

	@Override
	public ServiceResponse<HL7FileGenerationResult> generateQBPFile(ImmRegQBPCriteria immRegQBPCriteria) {
		// TODO Auto-generated method stub
		return immDAO.generateQBPFile(immRegQBPCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> ProcessAcknowledgementMessageFromFileData(MultipartFile responseFileData,
			SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return immDAO.ProcessAcknowledgementMessageFromFileData(responseFileData, searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> ProcessAcknowledgementMessageFromDirectory(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return immDAO.ProcessAcknowledgementMessageFromDirectory(searchCriteria);
	}

	@Override
	public ORMGetPatImmRegInfoDisplay getPatientImmRegInfo(Long patientId) {
		// TODO Auto-generated method stub
		return immDAO.getPatientImmRegInfo(patientId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> markRegistryMessageAsResolved(UpdateRecord updateRecord) {
		// TODO Auto-generated method stub
		return immDAO.markRegistryMessageAsResolved(updateRecord);
	}

}
