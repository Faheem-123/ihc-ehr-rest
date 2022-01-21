package com.ihc.ehr.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

public interface ImmunizationService {

	List<ORMImmRegClinicsGet> getClinics(Long practice_id);

	List<ORMImmSetupAllListGet> getSetupImmAllList(Long practice_id);

	List<ORMImmSetupPracticeListGet> getSetupImmPracticeList(Long practice_id);

	List<ORMImmManufacturerGet> getImmManufacturer(String cvx_code);

	List<ORMImmTradeNameGet> getImmTradeName(String cvx_code);

	List<ORMImmProceduresGet> getImmProcedure(String cvx_code);

	List<ORMImmNDCGet> getImmNDC(String cvx_code);

	List<ORMImmVISGet> getImmVIS(String cvx_code);

	List<ORMImmRoutesListGet> getImmRouteList();

	List<ORMImmSitesListGet> getImmSiteList();

	List<ORMImmCodeSet> getImmCodeSet();

	ServiceResponse<ORMKeyValue> savePracticeImmunization(ORMPracticeImmunizationSave ormPracticeImmunizationSave);

	List<ORMImmInventoryVaccineListGet> getInventoryVaccineList(SearchCriteria searchCriteria);

	List<ORMImmInventoryDetailGet> getInventoryVaccineDetails(SearchCriteria searchCriteria);

	List<ORMImmTradeNameSearchListGet> getImmunizationTradeNamePracticeSearchList(Long practice_id);

	List<ORMImmSearchListGet> getImmunizationPracticeSearchList(String practice_id, String location_id);

	ServiceResponse<ORMKeyValue> saveImmunizationInventory(ORMImmInventorySave ormImmunizationInventorySave);

	List<ORMImmInventoryUsageGet> getImmunizationInventoryUsage(Long inventory_id);

	List<ORMImmRegMessagesGet> getImmRegMessages(SearchCriteria searchCriteria);

	List<ORMImmRegMsgImmunizationsGet> getImmRegMsgImmunizations(Long message_id);

	List<ORMImmRegMsgErrorsGet> getImmRegMsgErrors(Long message_id);

	WrapperImmRegEvaluationHistoryForecastMessageDetails getImmRegEvaluationHistoryForecastMessageDetails(
			String registryCode,Long messageId);

	ServiceResponse<HL7FileGenerationResult> generateVXUFile(ImmRegVXUCriteria immRegVXUCriteria);

	ServiceResponse<HL7FileGenerationResult> generateQBPFile(ImmRegQBPCriteria immRegQBPCriteria);

	ServiceResponse<ORMKeyValue> ProcessAcknowledgementMessageFromFileData(MultipartFile responseFileData,
			SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> ProcessAcknowledgementMessageFromDirectory(SearchCriteria searchCriteria);

	ORMGetPatImmRegInfoDisplay getPatientImmRegInfo(Long patientId);

	ServiceResponse<ORMKeyValue> markRegistryMessageAsResolved(UpdateRecord updateRecord);

}
