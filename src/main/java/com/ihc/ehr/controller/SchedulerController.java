package com.ihc.ehr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import com.ihc.ehr.model.ORMProviderList;
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
import com.ihc.ehr.model.Wrapper_ObjectSave;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.service.SchedulerService;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

	@Autowired
	SchedulerService schedulerService;

	@Autowired
	GeneralService generalService;

	@RequestMapping("/getlocationproviders/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetSchedulerGetLocationProviders>> getSchedulerLocationProviders(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getSchedulerLocationProviders:  practice_id=" + practice_id.toString());

		List<ORMGetSchedulerGetLocationProviders> lst = schedulerService.getSchedulerLocationProviders(practice_id);
		return new ResponseEntity<List<ORMGetSchedulerGetLocationProviders>>(lst, HttpStatus.OK);
	}

	/*
	 * @RequestMapping("/getprovidertimings") public @ResponseBody
	 * ResponseEntity<List<ORMGetSchedulerProviderTiming>>
	 * getSchedulerProviderTimings(
	 * 
	 * @RequestBody SearchCriteria searchCriteria) {
	 * GeneralOperations.logMsg("Inside getSchedulerAppointments:  Criteria="
	 * +searchCriteria.toString());
	 * 
	 * List<ORMGetSchedulerProviderTiming>
	 * lst=schedulerService.getSchedulerProviderTimings(searchCriteria); return new
	 * ResponseEntity<List<ORMGetSchedulerProviderTiming>>(lst , HttpStatus.OK); }
	 */

	/*
	 * @RequestMapping("/getprovidertemptimings") public @ResponseBody
	 * ResponseEntity<List<ORMGetSchedulerProviderTempTiming>>
	 * getSchedulerProviderTempTimings(
	 * 
	 * @RequestBody SearchCriteria searchCriteria) {
	 * GeneralOperations.logMsg("Inside getSchedulerProviderTempTimings:  Criteria="
	 * +searchCriteria.toString());
	 * 
	 * List<ORMGetSchedulerProviderTempTiming>
	 * lst=schedulerService.getSchedulerProviderTempTimings(searchCriteria); return
	 * new ResponseEntity<List<ORMGetSchedulerProviderTempTiming>>(lst ,
	 * HttpStatus.OK); }
	 */

	@RequestMapping("/getschedulertiming")
	public @ResponseBody ResponseEntity<List<ORMSchedulerTiming>> getSchedulerTiming(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getSchedulerTiming:  Criteria=" + searchCriteria.toString());

		List<ORMSchedulerTiming> lst = schedulerService.getSchedulerTiming(searchCriteria);
		return new ResponseEntity<List<ORMSchedulerTiming>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getstatus/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdDescription>> getSchedulerStatus(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getSchedulerStatus:  practice_id=" + practice_id.toString());

		List<ORMIdDescription> lst = schedulerService.getSchedulerStatus(practice_id);
		return new ResponseEntity<List<ORMIdDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/gettypes/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetSchedulerAppType>> getSchedulerTypes(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getSchedulerTypes:  practice_id=" + practice_id.toString());

		List<ORMGetSchedulerAppType> lst = schedulerService.getSchedulerTypes(practice_id);
		return new ResponseEntity<List<ORMGetSchedulerAppType>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getreasons/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdDescription>> getSchedulerReason(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getSchedulerReason:  practice_id=" + practice_id.toString());

		List<ORMIdDescription> lst = schedulerService.getSchedulerReason(practice_id);
		return new ResponseEntity<List<ORMIdDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getsources/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdDescription>> getSchedulerSources(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getSchedulerSources:  practice_id=" + practice_id.toString());

		List<ORMIdDescription> lst = schedulerService.getSchedulerSources(practice_id);
		return new ResponseEntity<List<ORMIdDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getappointments")
	public @ResponseBody ResponseEntity<List<ORMGetSchedulerAppointments>> getSchedulerAppointments(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getSchedulerAppointments:  Criteria=" + searchCriteria.toString());

		List<ORMGetSchedulerAppointments> lst = schedulerService.getSchedulerAppointments(searchCriteria);
		return new ResponseEntity<List<ORMGetSchedulerAppointments>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getappointmentdetails/{appointment_id}")
	public @ResponseBody ResponseEntity<ORMGetSchedulerAppointmentDetails> getSchedulerAppointmentDetails(
			@PathVariable(value = "appointment_id") Long appointment_id) {
		GeneralOperations.logMsg("Inside getAppointmentDetails");

		List<ORMGetSchedulerAppointmentDetails> lst = schedulerService.getSchedulerAppointmentDetails(appointment_id);

		return new ResponseEntity<ORMGetSchedulerAppointmentDetails>(lst.get(0), HttpStatus.OK);

	}

	@RequestMapping("/saveappointment")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveAppointments(
			@RequestBody Wrapper_ObjectSave<ORMSaveAppointment> objAppSaveWrapper) {
		GeneralOperations.logMsg("Inside saveAppointments");

		ORMSaveAppointment obj = objAppSaveWrapper.getOrmSave();

		ServiceResponse<ORMKeyValue> resp = schedulerService.saveAppointments(obj, objAppSaveWrapper.getLstKeyValue());

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/deleteappointment")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> deleteAppointments(
			@RequestBody ORMDeleteRecord ormDeleteRecord) {
		GeneralOperations.logMsg("Inside deleteAppointments");

		ormDeleteRecord.setTable_name("appointment");
		ormDeleteRecord.setColumn_name("appointment_id");

		ServiceResponse<ORMKeyValue> resp = schedulerService.deleteAppointments(ormDeleteRecord);

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/getrooms/{practice_id}/{location_id}")
	public @ResponseBody ResponseEntity<List<ORMGetSchedulerRooms>> getSchedulerRooms(
			@PathVariable(value = "practice_id") Long practice_id,
			@PathVariable(value = "location_id") Long location_id) {
		GeneralOperations.logMsg("Inside getSchedulerRooms");

		List<ORMGetSchedulerRooms> lst = schedulerService.getSchedulerRooms(practice_id, location_id);

		return new ResponseEntity<List<ORMGetSchedulerRooms>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getcheckinoutinfo")
	public @ResponseBody ResponseEntity<ORMGetCheckInOutInfo> getCheckInOutInfo(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getCheckInOutInfo");

		List<ORMGetCheckInOutInfo> lst = schedulerService.getCheckInOutInfo(searchCriteria);

		return new ResponseEntity<ORMGetCheckInOutInfo>(lst.get(0), HttpStatus.OK);
	}

	@RequestMapping("/updatepatientcontactinfo")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> UpdatePatientContactInfo(
			@RequestBody ORMUpdatePatientContactInfo objContactInfo) {
		GeneralOperations.logMsg("Inside getCheckInOutInfo");

		int result = schedulerService.UpdatePatientContactInfo(objContactInfo);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while updating patient contact information.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/updatecheckinoutroom")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> updateCheckInCheckOutRoom(
			@RequestBody List<ORMKeyValue> lstKeyValue) {
		GeneralOperations.logMsg("Inside updateCheckInCheckOutRoom");

		int result = schedulerService.updateCheckInCheckOutRoom(lstKeyValue);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while updating check in/ Check out/ Room");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getappointmentswithtiming")
	public @ResponseBody ResponseEntity<Wrapper_AppointmentsWithTiming> getSchedulerAppointmentsWithTiming(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getSchedulerAppointmentsWithTiming:  Criteria=" + searchCriteria.toString());

		Wrapper_AppointmentsWithTiming appointmentsWithTimingObjectsWrapper = schedulerService
				.getSchedulerAppointmentsWithTiming(searchCriteria);
		return new ResponseEntity<Wrapper_AppointmentsWithTiming>(appointmentsWithTimingObjectsWrapper, HttpStatus.OK);
	}

	@RequestMapping("/getprovidertimingsettings")
	public @ResponseBody ResponseEntity<List<ORMGetProviderTiming>> getProviderTimingSettings(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getProviderTimingSettings:  ");

		List<ORMGetProviderTiming> lst = schedulerService.getProviderTimingSettings(searchCriteria);

		return new ResponseEntity<List<ORMGetProviderTiming>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveprovidertimingsettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveProviderTimingSettings(
			@RequestBody List<ORMSaveProviderTiming> lstORMSaveProvider) {
		GeneralOperations.logMsg("Inside saveProviderTimingSettings:  ");

		int result = schedulerService.saveProviderTimingSettings(lstORMSaveProvider);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while updating provider timing.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/getprovidertemptimingsettings")
	public @ResponseBody ResponseEntity<List<ORMGetProviderTempTiming>> getProviderTempTimingSettings(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getProviderTempTimingSettings:  ");

		List<ORMGetProviderTempTiming> lst = schedulerService.getProviderTempTimingSettings(searchCriteria);

		return new ResponseEntity<List<ORMGetProviderTempTiming>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveprovidertemptimingsettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveProviderTempTimingSettings(
			@RequestBody ORMSaveProviderTempTiming ormSaveTempTiming) {
		GeneralOperations.logMsg("Inside saveProviderTimingSettings:  ");

		int result = schedulerService.saveProviderTempTimingSettings(ormSaveTempTiming);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while updating provider timing.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/deletetemptiming")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> deleteTempTiming(
			@RequestBody ORMDeleteRecord ormDeleteRecord) {
		GeneralOperations.logMsg("Inside deleteTempTiming");

		ormDeleteRecord.setTable_name("provider_temp_office_timing");
		ormDeleteRecord.setColumn_name("temp_timing_id");

		int result = generalService.deleteRecord(ormDeleteRecord);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/getlocationprovidersettings")
	public @ResponseBody ResponseEntity<List<ORMGetLocationProviderSetting>> getLocationProviderSetting(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getLocationProviderSetting:  ");

		List<ORMGetLocationProviderSetting> lst = schedulerService.getLocationProviderSetting(searchCriteria);

		return new ResponseEntity<List<ORMGetLocationProviderSetting>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/savelocationprovidersettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveLocationProviderSetting(
			@RequestBody List<ORMSaveLocationProviders> lstSaveLocationProviders) {
		GeneralOperations.logMsg("Inside saveLocationProviderSetting:  ");

		int result = schedulerService.saveLocationProviderSetting(lstSaveLocationProviders);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getappointmentsourcesettings/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetAppointmentSourceSetting>> getAppointmentSourceSettings(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getAppointmentSourceSettings:  ");

		List<ORMGetAppointmentSourceSetting> lst = schedulerService.getAppointmentSourceSettings(practice_id);

		return new ResponseEntity<List<ORMGetAppointmentSourceSetting>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/deleteappointmentsourcesettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> deleteAppointmentSourceSettings(
			@RequestBody ORMDeleteRecord ormDeleteRecord) {

		GeneralOperations.logMsg("Inside deleteappointmentsourcesettings");

		ormDeleteRecord.setTable_name("appointment_source");
		ormDeleteRecord.setColumn_name("source_id");

		int result = generalService.deleteRecord(ormDeleteRecord);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/saveappointmentsourcesettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveAppointmentSourceSettings(
			@RequestBody ORMSaveAppointmentSource ormSave) {
		GeneralOperations.logMsg("Inside saveAppointmentsourcesettings:  ");

		int result = schedulerService.saveAppointmentSourceSettings(ormSave);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving appointment source.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/getappointmenttypesettings/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetAppiontmentTypeSetting>> getAppointmentTypeSettings(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getAppointmentSourceSettings:  ");

		List<ORMGetAppiontmentTypeSetting> lst = schedulerService.getAppointmentTypeSettings(practice_id);

		return new ResponseEntity<List<ORMGetAppiontmentTypeSetting>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveappointmenttypesettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveAppointmentTypeSettings(
			@RequestBody ORMSaveAppointmentTypes ormSave) {
		GeneralOperations.logMsg("Inside saveAppointmentsourcesettings:  ");

		int result = schedulerService.saveAppointmentTypeSettings(ormSave);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving Appointment Type.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/deleteappointmenttypesettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> deleteAppointmentTypeSettings(
			@RequestBody ORMDeleteRecord ormDeleteRecord) {

		GeneralOperations.logMsg("Inside deleteAppointmentTypeSettings");

		ormDeleteRecord.setTable_name("appointment_type");
		ormDeleteRecord.setColumn_name("type_id");

		int result = generalService.deleteRecord(ormDeleteRecord);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/getappointmentstatussettings/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetAppointmentStatusSetting>> getAppointmentStatusSettings(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getAppointmentStatusSettings:  ");

		List<ORMGetAppointmentStatusSetting> lst = schedulerService.getAppointmentStatusSettings(practice_id);

		return new ResponseEntity<List<ORMGetAppointmentStatusSetting>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveappointmentstatussettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveAppointmentStatusSettings(
			@RequestBody ORMSaveAppointmentStatus ormSave) {
		GeneralOperations.logMsg("Inside saveAppointmentStatusSettings:  ");

		int result = schedulerService.saveAppointmentStatusSettings(ormSave);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving Appointment Status.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/deleteappointmentstatussettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> deleteAppointmentStatusSettings(
			@RequestBody ORMDeleteRecord ormDeleteRecord) {

		GeneralOperations.logMsg("Inside deleteAppointmentStatusSettings");

		ormDeleteRecord.setTable_name("appointment_status");
		ormDeleteRecord.setColumn_name("status_id");

		int result = generalService.deleteRecord(ormDeleteRecord);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getlocationroomssettings")
	public @ResponseBody ResponseEntity<List<ORMGetLocationRoomsSettting>> getLocationRoomsSetting(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getAppointmentStatusSettings:  ");

		List<ORMGetLocationRoomsSettting> lst = schedulerService.getLocationRoomsSetting(searchCriteria);

		return new ResponseEntity<List<ORMGetLocationRoomsSettting>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/savelocationroomssettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveLocationRoomsSettings(
			@RequestBody ORMSaveLocationRooms ormSave) {
		GeneralOperations.logMsg("Inside saveAppointmentStatusSettings:  ");

		int result = schedulerService.saveLocationRoomsSettings(ormSave);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving Location Room.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/deletealocationroomssettings")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> deleteLocationRoomsSettings(
			@RequestBody ORMDeleteRecord ormDeleteRecord) {

		GeneralOperations.logMsg("Inside deleteLocationRoomsSettings");

		ormDeleteRecord.setTable_name("rooms");
		ormDeleteRecord.setColumn_name("rooms_id");

		int result = generalService.deleteRecord(ormDeleteRecord);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/getappointmentrules/{provider_id}")
	public @ResponseBody ResponseEntity<List<ORMGetAppointmentRules>> getAppointmentRules(
			@PathVariable(value = "provider_id") Long provider_id) {
		GeneralOperations.logMsg("Inside getAppointmentRules: ");

		List<ORMGetAppointmentRules> lst = schedulerService.getAppointmentRules(provider_id);

		return new ResponseEntity<List<ORMGetAppointmentRules>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/saveappointmentrules")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> saveAppointmentRules(
			@RequestBody List<ORMSaveAppointmentRules> lstObjSave) {
		GeneralOperations.logMsg("Inside saveAppointmentRules:  ");

		int result = schedulerService.saveAppointmentRules(lstObjSave);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while deleting record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(Integer.toString(result));
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getdistinctlocationproviders/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMProviderList>> getDistinctLocationProviders(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getDistinctLocationProviders: ");

		List<ORMProviderList> lst = generalService.getDistinctLocationProviders(practice_id);

		return new ResponseEntity<List<ORMProviderList>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getSuperBillcodes/{provider_id}/{superbill}/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> getSuperBillcodes(
			@PathVariable(value = "provider_id") String provider_id,
			@PathVariable(value = "superbill") String superbill,
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMTwoColumnGeneric> lst = schedulerService.getSuperBillcodes(provider_id, superbill, patient_id);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getSuperbillHeaddertDetails/{appointment_id}")
	public @ResponseBody ResponseEntity<List<ORMSuperbillHeadder>> getSuperbillHeaddertDetails(
			@PathVariable(value = "appointment_id") String appointment_id) {
		List<ORMSuperbillHeadder> lst = schedulerService.getSuperbillHeaddertDetails(appointment_id);

		return new ResponseEntity<List<ORMSuperbillHeadder>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getAppointmentsByLocation")
	public @ResponseBody ResponseEntity<Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView>>> getAppointmentsByLocation(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getAppointmentsByLocation:  Criteria=" + searchCriteria.toString());

		Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView>> lst = schedulerService.getAppointmentsByLocation(searchCriteria);
		return new ResponseEntity<Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView>>>(lst, HttpStatus.OK);
	}
	
	
	@RequestMapping("/getAppointmentsByProvider")
	public @ResponseBody ResponseEntity<Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView>>> getAppointmentsByProvider(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getAppointmentsByProvider:  Criteria=" + searchCriteria.toString());

		Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView>> lst = schedulerService.getAppointmentsByProvider(searchCriteria);
		return new ResponseEntity<Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView>>>(lst, HttpStatus.OK);
	}	

	@RequestMapping("/getAppointmentsMonthView")
	public @ResponseBody ResponseEntity<List<ORMGetSchedulerAppointmentsMonthView>> getAppointmentsMonthView(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getAppointmentsMonthView:  Criteria=" + searchCriteria.toString());

		List<ORMGetSchedulerAppointmentsMonthView>lst = schedulerService.getAppointmentsMonthView(searchCriteria);
		return new ResponseEntity<List<ORMGetSchedulerAppointmentsMonthView>>(lst, HttpStatus.OK);
	}	
	@RequestMapping("/getPatientDataonCheckedIn")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> getPatientDataonCheckedIn(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMTwoColumnGeneric>lst = schedulerService.getPatientDataonCheckedIn(searchCriteria);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(lst, HttpStatus.OK);
	}
	
	
	
}
