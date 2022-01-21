package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.SchedulerDAO;
import com.ihc.ehr.dao.Wrapper_Appointments;
import com.ihc.ehr.model.Wrapper_AppointmentsWithTiming;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMGetAppiontmentTypeSetting;
import com.ihc.ehr.model.ORMGetAppointmentRules;
import com.ihc.ehr.model.ORMGetAppointmentSourceSetting;
import com.ihc.ehr.model.ORMGetAppointmentStatusSetting;
import com.ihc.ehr.model.ORMGetCheckInOutInfo;
import com.ihc.ehr.model.ORMGetLocationProviderSetting;
import com.ihc.ehr.model.ORMGetLocationRoomsSettting;
import com.ihc.ehr.model.ORMGetProviderTempTiming;
import com.ihc.ehr.model.ORMGetProviderTiming;
import com.ihc.ehr.model.ORMGetSchedulerAppType;
import com.ihc.ehr.model.ORMGetSchedulerAppointmentDetails;
import com.ihc.ehr.model.ORMGetSchedulerAppointments;
import com.ihc.ehr.model.ORMGetSchedulerAppointmentsLocationView;
import com.ihc.ehr.model.ORMGetSchedulerAppointmentsMonthView;
import com.ihc.ehr.model.ORMGetSchedulerAppointmentsProviderView;
import com.ihc.ehr.model.Wrapper_SchedulerAppointments;
import com.ihc.ehr.model.ORMGetSchedulerGetLocationProviders;
import com.ihc.ehr.model.ORMGetSchedulerRooms;
import com.ihc.ehr.model.ORMIdDescription;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMSaveAppointment;
import com.ihc.ehr.model.ORMSaveAppointmentRules;
import com.ihc.ehr.model.ORMSaveAppointmentSource;
import com.ihc.ehr.model.ORMSaveAppointmentStatus;
import com.ihc.ehr.model.ORMSaveAppointmentTypes;
import com.ihc.ehr.model.ORMSaveLocationProviders;
import com.ihc.ehr.model.ORMSaveLocationRooms;
import com.ihc.ehr.model.ORMSaveProviderTempTiming;
import com.ihc.ehr.model.ORMSaveProviderTiming;
import com.ihc.ehr.model.ORMSchedulerTiming;
import com.ihc.ehr.model.ORMSuperbillHeadder;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORMUpdatePatientContactInfo;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;

@Service
public class SchedulerServiceImpl implements SchedulerService {
	
	@Autowired
	SchedulerDAO schedulerDAO;
	@Override
	public List<ORMGetSchedulerGetLocationProviders> getSchedulerLocationProviders(Long practice_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerLocationProviders(practice_id);
	}

	@Override
	public List<ORMGetSchedulerAppointments> getSchedulerAppointments(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerAppointments(searchCriteria);
	}


	/*
	@Override
	public List<ORMGetSchedulerProviderTiming> getSchedulerProviderTimings(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerProviderTimings(searchCriteria);
	}
*/
	/*
	@Override
	public List<ORMGetSchedulerProviderTempTiming> getSchedulerProviderTempTimings(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerProviderTempTimings(searchCriteria);
	}
	*/

	@Override
	public List<ORMIdDescription> getSchedulerStatus(Long practice_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerStatus(practice_id);
	}

	@Override
	public List<ORMGetSchedulerAppType> getSchedulerTypes(Long practice_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerTypes(practice_id);
	}

	@Override
	public List<ORMIdDescription> getSchedulerReason(Long practice_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerReason(practice_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveAppointments(ORMSaveAppointment ormSaveAppointment,List<ORMKeyValue> saveConfirmation) {
		// TODO Auto-generated method stub
		return schedulerDAO.saveAppointments(ormSaveAppointment,saveConfirmation);
	}

	@Override
	public List<ORMSchedulerTiming> getSchedulerTiming(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerTiming(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> deleteAppointments(ORMDeleteRecord ormDeleteRecord) {
		// TODO Auto-generated method stub
		return schedulerDAO.deleteAppointments(ormDeleteRecord);
	}

	@Override
	public List<ORMGetSchedulerAppointmentDetails> getSchedulerAppointmentDetails(Long appointment_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerAppointmentDetails(appointment_id);
	}

	@Override
	public List<ORMGetSchedulerRooms> getSchedulerRooms(Long practice_id, Long location_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerRooms(practice_id,location_id);
	}

	@Override
	public List<ORMGetCheckInOutInfo> getCheckInOutInfo(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getCheckInOutInfo(searchCriteria);
	}

	@Override
	public int UpdatePatientContactInfo(ORMUpdatePatientContactInfo objContactInfo) {
		// TODO Auto-generated method stub
		return schedulerDAO.UpdatePatientContactInfo(objContactInfo);
	}

	@Override
	public int updateCheckInCheckOutRoom(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub
		return schedulerDAO.updateCheckInCheckOutRoom(lstKeyValue);
	}

	@Override
	public List<ORMIdDescription> getSchedulerSources(Long practice_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerSources(practice_id);
	}

	@Override
	public Wrapper_AppointmentsWithTiming getSchedulerAppointmentsWithTiming(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSchedulerAppointmentsWithTiming(searchCriteria);
	}

	@Override
	public List<ORMGetProviderTiming> getProviderTimingSettings(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getProviderTimingSettings(searchCriteria);
	}

	@Override
	public int saveProviderTimingSettings(List<ORMSaveProviderTiming> lstORMSaveProvider) {
		// TODO Auto-generated method stub
		return schedulerDAO.saveProviderTimingSettings(lstORMSaveProvider);
	}

	@Override
	public List<ORMGetProviderTempTiming> getProviderTempTimingSettings(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getProviderTempTimingSettings(searchCriteria);
	}

	@Override
	public int saveProviderTempTimingSettings(ORMSaveProviderTempTiming ormSaveTempTiming) {
		// TODO Auto-generated method stub
		return schedulerDAO.saveProviderTempTimingSettings(ormSaveTempTiming);
	}

	@Override
	public List<ORMGetLocationProviderSetting> getLocationProviderSetting(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getLocationProviderSetting(searchCriteria);
	}

	@Override
	public int saveLocationProviderSetting(List<ORMSaveLocationProviders> lstSaveLocationProviders) {
		// TODO Auto-generated method stub
		return schedulerDAO.saveLocationProviderSetting(lstSaveLocationProviders);
	}

	@Override
	public List<ORMGetAppointmentSourceSetting> getAppointmentSourceSettings(Long practice_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getAppointmentSourceSettings(practice_id);
	}

	@Override
	public int saveAppointmentSourceSettings(ORMSaveAppointmentSource ormSave) {
		// TODO Auto-generated method stub
		return schedulerDAO.saveAppointmentSourceSettings(ormSave);
	}

	@Override
	public List<ORMGetAppiontmentTypeSetting> getAppointmentTypeSettings(Long practice_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getAppointmentTypeSettings(practice_id);
	}

	@Override
	public int saveAppointmentTypeSettings(ORMSaveAppointmentTypes ormSave) {
		// TODO Auto-generated method stub
		return schedulerDAO.saveAppointmentTypeSettings(ormSave);
	}

	@Override
	public List<ORMGetAppointmentStatusSetting> getAppointmentStatusSettings(Long practice_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getAppointmentStatusSettings(practice_id);
	}

	@Override
	public int saveAppointmentStatusSettings(ORMSaveAppointmentStatus ormSave) {
		// TODO Auto-generated method stub
		return schedulerDAO.saveAppointmentStatusSettings(ormSave);
	}

	@Override
	public List<ORMGetLocationRoomsSettting> getLocationRoomsSetting(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getLocationRoomsSetting(searchCriteria);
	}

	@Override
	public int saveLocationRoomsSettings(ORMSaveLocationRooms ormSave) {
		// TODO Auto-generated method stub
		return schedulerDAO.saveLocationRoomsSettings(ormSave);
	}

	@Override
	public List<ORMGetAppointmentRules> getAppointmentRules(Long provider_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getAppointmentRules(provider_id);
	}

	@Override
	public int saveAppointmentRules(List<ORMSaveAppointmentRules> lstObjSave) {
		// TODO Auto-generated method stub
		return schedulerDAO.saveAppointmentRules(lstObjSave);
	}

	@Override
	public List<ORMTwoColumnGeneric> getSuperBillcodes(String provider_id, String superbill, String patient_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSuperBillcodes(provider_id, superbill, patient_id);
	}

	@Override
	public List<ORMSuperbillHeadder> getSuperbillHeaddertDetails(String appointment_id) {
		// TODO Auto-generated method stub
		return schedulerDAO.getSuperbillHeaddertDetails(appointment_id);
	}

	@Override
	public Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView>> getAppointmentsByLocation(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getAppointmentsByLocation(searchCriteria);
	}

	@Override
	public Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView>> getAppointmentsByProvider(
			SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getAppointmentsByProvider(searchCriteria);
	}

	@Override
	public List<ORMGetSchedulerAppointmentsMonthView> getAppointmentsMonthView(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getAppointmentsMonthView(searchCriteria);
	}

	@Override
	public List<ORMTwoColumnGeneric> getPatientDataonCheckedIn(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return schedulerDAO.getPatientDataonCheckedIn(searchCriteria);
	}
	
}
