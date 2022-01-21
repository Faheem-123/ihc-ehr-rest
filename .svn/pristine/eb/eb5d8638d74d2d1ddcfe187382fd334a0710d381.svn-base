package com.ihc.ehr.service;

import java.util.List;

import com.ihc.ehr.model.Wrapper_AppointmentsWithTiming;
import com.ihc.ehr.dao.Wrapper_Appointments;
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

public interface SchedulerService {

	List<ORMGetSchedulerGetLocationProviders> getSchedulerLocationProviders(Long practice_id);

	List<ORMGetSchedulerAppointments> getSchedulerAppointments(SearchCriteria searchCriteria);

	//List<ORMGetSchedulerProviderTiming> getSchedulerProviderTimings(SearchCriteria searchCriteria);

	//List<ORMGetSchedulerProviderTempTiming> getSchedulerProviderTempTimings(SearchCriteria searchCriteria);

	List<ORMIdDescription> getSchedulerStatus(Long practice_id);

	List<ORMGetSchedulerAppType> getSchedulerTypes(Long practice_id);

	List<ORMIdDescription> getSchedulerReason(Long practice_id);

	ServiceResponse<ORMKeyValue> saveAppointments(ORMSaveAppointment ormSaveAppointment,List<ORMKeyValue> saveConfirmation);

	List<ORMSchedulerTiming> getSchedulerTiming(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> deleteAppointments(ORMDeleteRecord ormDeleteRecord);

	List<ORMGetSchedulerAppointmentDetails> getSchedulerAppointmentDetails(Long appointment_id);

	List<ORMGetSchedulerRooms> getSchedulerRooms(Long practice_id, Long location_id);

	List<ORMGetCheckInOutInfo> getCheckInOutInfo(SearchCriteria searchCriteria);

	int UpdatePatientContactInfo(ORMUpdatePatientContactInfo objContactInfo);

	int updateCheckInCheckOutRoom(List<ORMKeyValue> lstKeyValue);

	List<ORMIdDescription> getSchedulerSources(Long practice_id);

	Wrapper_AppointmentsWithTiming getSchedulerAppointmentsWithTiming(SearchCriteria searchCriteria);

	List<ORMGetProviderTiming> getProviderTimingSettings(SearchCriteria searchCriteria);

	int saveProviderTimingSettings(List<ORMSaveProviderTiming> lstORMSaveProvider);

	List<ORMGetProviderTempTiming> getProviderTempTimingSettings(SearchCriteria searchCriteria);

	int saveProviderTempTimingSettings(ORMSaveProviderTempTiming ormSaveTempTiming);

	List<ORMGetLocationProviderSetting> getLocationProviderSetting(SearchCriteria searchCriteria);

	int saveLocationProviderSetting(List<ORMSaveLocationProviders> lstSaveLocationProviders);

	List<ORMGetAppointmentSourceSetting> getAppointmentSourceSettings(Long practice_id);

	int saveAppointmentSourceSettings(ORMSaveAppointmentSource ormSave);

	List<ORMGetAppiontmentTypeSetting> getAppointmentTypeSettings(Long practice_id);

	int saveAppointmentTypeSettings(ORMSaveAppointmentTypes ormSave);

	List<ORMGetAppointmentStatusSetting> getAppointmentStatusSettings(Long practice_id);

	int saveAppointmentStatusSettings(ORMSaveAppointmentStatus ormSave);

	List<ORMGetLocationRoomsSettting> getLocationRoomsSetting(SearchCriteria searchCriteria);

	int saveLocationRoomsSettings(ORMSaveLocationRooms ormSave);

	List<ORMGetAppointmentRules> getAppointmentRules(Long provider_id);

	int saveAppointmentRules(List<ORMSaveAppointmentRules> lstObjSave);

	List<ORMTwoColumnGeneric> getSuperBillcodes(String provider_id, String superbill, String patient_id);

	List<ORMSuperbillHeadder> getSuperbillHeaddertDetails(String appointment_id);

	Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView>> getAppointmentsByLocation(SearchCriteria searchCriteria);

	Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView>> getAppointmentsByProvider(
			SearchCriteria searchCriteria);

	List<ORMGetSchedulerAppointmentsMonthView> getAppointmentsMonthView(SearchCriteria searchCriteria);

	List<ORMTwoColumnGeneric> getPatientDataonCheckedIn(SearchCriteria searchCriteria);

}
