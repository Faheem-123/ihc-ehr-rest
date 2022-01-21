package com.ihc.ehr.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.DashboardDAO;
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



@Service
public class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	private DashboardDAO dashboardDao;
	
	@Override
	public List<ORMGetCashRegisterDayWisePayment> getCashRegisterLastWeekDayWisePayment(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub 
		return dashboardDao.getCashRegisterLastWeekDayWisePayment(searchCriteria);
	}
	@Override
	public List<ORMDashBoardCheckInPatient> getCheckInPatient(SearchCriteria searchcriteria) {
		// TODO Auto-generated method stub
		return dashboardDao.getCheckInPatient(searchcriteria);
	}
	
	@Override
	public List<ORMDashBoardLab> getLabPendingResults(Long ProviderID, Long locationID, String type, Long LoginUserID) {
		// TODO Auto-generated method stub
		return dashboardDao.getLabPendingResults(ProviderID, locationID, type, LoginUserID);
	}
	
	@Override
	public List<ORMDashboardPendingEncounter> getPendingEncounter(Long  provider_id, Long location_id, SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return dashboardDao.getPendingEncounter(provider_id, location_id, searchCriteria);
	}
	
	
	@Override
	public List<ORMDashBoarCashReg> getCashPaymentDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return dashboardDao.getCashPaymentDetails(searchCriteria);
	}
	@Override
	public List<ORMDashBoardMessages> getUnReadMessages(Long ReciverID) {
		// TODO Auto-generated method stub
		return dashboardDao.getUnReadMessages(ReciverID);
	}
	@Override
	public int markAsRead(String id,String loginuser,String datetime,String ip){
		Date date = new Date();
		SimpleDateFormat currentdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = currentdate.format(date);
		
		return dashboardDao.markAsRead(id,loginuser,dateStr,ip);
	}
	
	@Override
	public List<ORMFaxMyUnRead> getUnReadFaxes(String userID,String practice_id){
		return dashboardDao.getUnReadFaxes(userID,practice_id);
	}
	@Override
	public List<ORMGetGynTodayEdd> getgynEDD(String practice_id){
		return dashboardDao.getgynEDD(practice_id);
	}
	@Override
	public List<ORMDashBoardMissingClaims> getMissingClaims(SearchCriteria searchCriteria){
		return dashboardDao.getMissingClaims(searchCriteria);
	}
	/*@Override
	public List<ORMDashBoardLab> getPendingLabResults(String ProviderID, String locationID, String type, String LoginUserID) {
		// TODO Auto-generated method stub
		return dashboardDao.getPendingLabResults(ProviderID,locationID,type,LoginUserID);
	}*/
	@Override
	public List<ORMPrescriptionRefills> getRefills(ORMgetPrescriptionAllergies getRefills) {
		// TODO Auto-generated method stub
		return dashboardDao.getRefills(getRefills);
	}
	@Override
	public List<ORMgetDashBoardModule> getDashBoardModule(Long userId, Long practiceId) {
		// TODO Auto-generated method stub
		return dashboardDao.getDashBoardModule(userId,practiceId);
	}
	@Override
	public List<ORMOneColumnGeneric> getLabPendingResults_Widget(Long providerID, Long locationID, String type,
			Long loginUserID) {
		// TODO Auto-generated method stub
		return dashboardDao.getLabPendingResults_Widget(providerID,locationID,type,loginUserID);
	}
	@Override
	public List<ORMOneColumnGeneric> getCheckInPatient_Widget(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return dashboardDao.getCheckInPatient_Widget(searchCriteria);
	}
	@Override
	public List<ORMOneColumnGeneric> getPendingClaims_Widget(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return dashboardDao.getPendingClaims_Widget(searchCriteria);
	}
	@Override
	public List<ORMOneColumnGeneric> getUnReadFaxes_Widget(String userID, String practice_id) {
		// TODO Auto-generated method stub
		return dashboardDao.getUnReadFaxes_Widget(userID,practice_id);
	}
	@Override
	public List<ORMOneColumnGeneric> getCashRegister_Widget(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return dashboardDao.getCashRegister_Widget(searchCriteria);
	}
	@Override
	public ServiceResponse<ORMKeyValue> updateOrderAssignedTo(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return dashboardDao.updateOrderAssignedTo(searchCriteria);
	}
	@Override
	public ServiceResponse<ORMKeyValue> faxMarkasRead(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return dashboardDao.faxMarkasRead(searchCriteria);
	}
	@Override
	public List<ORMSevenColumnGeneric> getPaymentSummary(String practice_id) {
		// TODO Auto-generated method stub
		return dashboardDao.getPaymentSummary(practice_id);
	}
	@Override
	public List<ORMThreeColumGeneric> getDashBoardDenial(String practice_id) {
		// TODO Auto-generated method stub
		return dashboardDao.getDashBoardDenial(practice_id);
	}
	@Override
	public List<ORMThreeColumGeneric> getDashBoardClaimAging(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return dashboardDao.getDashBoardClaimAging(searchCriteria);
	}
	@Override
	public List<ORMThreeColumGeneric> getDashBoardClaimCount(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return dashboardDao.getDashBoardClaimCount(searchCriteria);
	}
	@Override
	public List<ORMDashboardClaimReminder> getDashboardClaimReminders(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return dashboardDao.getDashboardClaimReminders(searchCriteria);
	}
}
