package com.ihc.ehr.dao;

import java.util.List;

import com.ihc.ehr.model.ORMDashBoarCashReg;
import com.ihc.ehr.model.ORMDashBoardCheckInPatient;
import com.ihc.ehr.model.ORMDashBoardLab;
import com.ihc.ehr.model.ORMDashBoardMessages;
import com.ihc.ehr.model.ORMDashBoardMissingClaims;
import com.ihc.ehr.model.ORMDashboardClaimReminder;
import com.ihc.ehr.model.ORMDashboardPendingEncounter;
import com.ihc.ehr.model.ORMFaxMyUnRead;
import com.ihc.ehr.model.ORMGetCashRegisterDayWisePayment;
import com.ihc.ehr.model.ORMGetGynTodayEdd;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMOneColumnGeneric;
import com.ihc.ehr.model.ORMPrescriptionRefills;
import com.ihc.ehr.model.ORMSevenColumnGeneric;
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.ORMgetDashBoardModule;
import com.ihc.ehr.model.ORMgetPrescriptionAllergies;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;

public interface DashboardDAO {

	List<ORMDashBoardCheckInPatient> getCheckInPatient(SearchCriteria searchcriteria);
	List<ORMDashBoardLab> getLabPendingResults (Long ProviderID, Long locationID, String type, Long LoginUserID);
	List<ORMDashboardPendingEncounter> getPendingEncounter(Long provider_id, Long location_id, SearchCriteria searchCriteria);
	List<ORMGetCashRegisterDayWisePayment> getCashRegisterLastWeekDayWisePayment(SearchCriteria searchCriteria);
	//dashboard cash register
	List<ORMDashBoarCashReg> getCashPaymentDetails(SearchCriteria searchCriteria);
	//dashboard lab pending results
	//List<ORMDashBoardLab> getPendingLabResults(String ProviderID,String locationID,String type,String LoginUserID);
	List<ORMDashBoardMessages> getUnReadMessages(Long ReciverID);
	int markAsRead(String id, String loginuser,String datetime,String ip);
	List<ORMFaxMyUnRead> getUnReadFaxes(String userID,String practice_id);
	List<ORMGetGynTodayEdd> getgynEDD(String practice_id);
	List<ORMDashBoardMissingClaims> getMissingClaims (SearchCriteria searchCriteria);
	List<ORMPrescriptionRefills> getRefills(ORMgetPrescriptionAllergies getRefills);
	List<ORMgetDashBoardModule> getDashBoardModule(Long userId, Long practiceId);
	List<ORMOneColumnGeneric> getLabPendingResults_Widget(Long providerID, Long locationID, String type,
			Long loginUserID);
	List<ORMOneColumnGeneric> getCheckInPatient_Widget(SearchCriteria searchCriteria);
	List<ORMOneColumnGeneric> getPendingClaims_Widget(SearchCriteria searchCriteria);
	List<ORMOneColumnGeneric> getUnReadFaxes_Widget(String userID, String practice_id);
	List<ORMOneColumnGeneric> getCashRegister_Widget(SearchCriteria searchCriteria);
	ServiceResponse<ORMKeyValue> updateOrderAssignedTo(SearchCriteria searchCriteria);
	ServiceResponse<ORMKeyValue> faxMarkasRead(SearchCriteria searchCriteria);
	List<ORMSevenColumnGeneric> getPaymentSummary(String practice_id);
	List<ORMThreeColumGeneric> getDashBoardDenial(String practice_id);
	List<ORMThreeColumGeneric> getDashBoardClaimAging(SearchCriteria searchCriteria);
	List<ORMThreeColumGeneric> getDashBoardClaimCount(SearchCriteria searchCriteria);
	List<ORMDashboardClaimReminder> getDashboardClaimReminders(SearchCriteria searchCriteria);
}
