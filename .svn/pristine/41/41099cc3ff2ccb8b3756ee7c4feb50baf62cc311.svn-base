package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ParameterMode;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.Wrapper_AppointmentsWithTiming;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.EnumUtil;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
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
import com.ihc.ehr.model.ORMGetSchedulerProviderTempTiming;
import com.ihc.ehr.model.ORMGetSchedulerProviderTiming;
import com.ihc.ehr.model.ORMGetSchedulerRooms;
import com.ihc.ehr.model.ORMIdDescription;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMOneColumnGeneric;
import com.ihc.ehr.model.ORMPatient_Followup;
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
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORMUpdatePatientContactInfo;
import com.ihc.ehr.model.ORM_HealthInformationCaptureAttachments;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;

@Repository
public class SchedulerDAOImpl implements SchedulerDAO {

	@Autowired
	private DBOperations db;

	@Override
	public List<ORMGetSchedulerGetLocationProviders> getSchedulerLocationProviders(Long practice_id) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetSchedulerGetLocationProviders> lst = db.getStoreProcedureData("getlocationproviders",
				ORMGetSchedulerGetLocationProviders.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMGetSchedulerAppointments> getSchedulerAppointments(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMGetSchedulerAppointments> lstAppointments = db.getStoreProcedureData("getSchedulerAppointmentsNew",
				ORMGetSchedulerAppointments.class, lstParam);

		return lstAppointments;

	}

	/*
	 * @Override public List<ORMGetSchedulerProviderTiming>
	 * getSchedulerProviderTimings(SearchCriteria searchCriteria) { // TODO
	 * Auto-generated method stub List<SpParameters> lstParam = new ArrayList<>();
	 * 
	 * 
	 * lstParam.add(new SpParameters("practice_id",
	 * searchCriteria.getPractice_id().toString(), String.class, ParameterMode.IN));
	 * 
	 * if (searchCriteria.getParam_list() != null &&
	 * !searchCriteria.getParam_list().isEmpty()) { for (SearchCriteriaParamList
	 * pram : searchCriteria.getParam_list()) {
	 * 
	 * if(pram.getName().equalsIgnoreCase("selected_date")) {
	 * 
	 * continue; }
	 * 
	 * lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class,
	 * ParameterMode.IN)); } }
	 * 
	 * List<ORMGetSchedulerProviderTiming> lst =
	 * db.getStoreProcedureData("spGetScheduelrProviderTiming",
	 * ORMGetSchedulerProviderTiming.class, lstParam);
	 * 
	 * return lst; }
	 */
	/*
	 * @Override public List<ORMGetSchedulerProviderTempTiming>
	 * getSchedulerProviderTempTimings(SearchCriteria searchCriteria) { // TODO
	 * Auto-generated method stub List<SpParameters> lstParam = new ArrayList<>();
	 * 
	 * lstParam.add(new SpParameters("practice_id",
	 * searchCriteria.getPractice_id().toString(), String.class, ParameterMode.IN));
	 * 
	 * if (searchCriteria.getParam_list() != null &&
	 * !searchCriteria.getParam_list().isEmpty()) { for (SearchCriteriaParamList
	 * pram : searchCriteria.getParam_list()) { lstParam.add(new
	 * SpParameters(pram.getName(), pram.getValue(), String.class,
	 * ParameterMode.IN)); } }
	 * 
	 * List<ORMGetSchedulerProviderTempTiming> lst =
	 * db.getStoreProcedureData("getSchedulerProviderTempTiming",
	 * ORMGetSchedulerProviderTempTiming.class, lstParam);
	 * 
	 * return lst; }
	 */
	@Override
	public List<ORMIdDescription> getSchedulerStatus(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		List<ORMIdDescription> lst = db.getStoreProcedureData("spGetSchedulerStatus", ORMIdDescription.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMGetSchedulerAppType> getSchedulerTypes(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetSchedulerAppType> lst = db.getStoreProcedureData("getAppoitmentType", ORMGetSchedulerAppType.class,
				lstParam);

		return lst;
	}

	@Override
	public List<ORMIdDescription> getSchedulerReason(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		List<ORMIdDescription> lst = db.getStoreProcedureData("spGetSchedulerAppReason", ORMIdDescription.class,
				lstParam);

		return lst;
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveAppointments(ORMSaveAppointment ormSaveAppointment,
			List<ORMKeyValue> saveConfirmationList) {

		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		List<ORMKeyValue> lstConfirmationRequired = new ArrayList<>();
		List<ORMKeyValue> lstNoAllowed = new ArrayList<>();

		if (saveConfirmationList == null) {

			List<ORMKeyValue> lstValidation = ValidateAppointment(ormSaveAppointment);

			for (ORMKeyValue objValidation : lstValidation) {

				switch (objValidation.getKey()) {
				case "timing_available": // NOT ALLOWED
					if (objValidation.getValue().equalsIgnoreCase("NO"))
						lstNoAllowed.add(objValidation);
					break;
				case "slot_limit_completed": // NOT ALLOWED
				case "encounter_date_mismatched": // NOT ALLOWED
					if (objValidation.getValue().equalsIgnoreCase("YES") && ormSaveAppointment.getPatient_id() > 0)
						lstNoAllowed.add(objValidation);
					break;
				case "timing_blocked": // NOT ALLOWED
					if (objValidation.getValue().equalsIgnoreCase("YES"))
						lstNoAllowed.add(objValidation);
					break;
				case "duplicate_appointment": // ASK FOR DUPLICATE APPOINTMENT
				case "encounter_PL_mismatched": // ASK FOR ENCOUNTER PL UPDATE.
					if (objValidation.getValue().equalsIgnoreCase("YES") && ormSaveAppointment.getPatient_id() > 0)
						lstConfirmationRequired.add(objValidation);
					break;
				case "duplicate_appointment_PL":
					if (ormSaveAppointment.getPatient_id() > 0 && !objValidation.getKey().toString().isEmpty())
						lstConfirmationRequired.add(objValidation);
					break;

				default:
					break;
				}
			}

		}

		if (!lstNoAllowed.isEmpty()) {

			resp.setStatus(ServiceResponseStatus.NOT_ALLOWED);
			resp.setResponse("Not Allowed.");
			resp.setResponse_list(lstNoAllowed);

		} else if (!lstConfirmationRequired.isEmpty()) {

			resp.setStatus(ServiceResponseStatus.CONFIRMATION_REQUIRED);
			resp.setResponse("Confirmation Required.");
			resp.setResponse_list(lstConfirmationRequired);

		} else {

			boolean updateEncounterPL = false;

			if (saveConfirmationList != null) {
				System.out.println("***  Save Confirmation List ***** ");
				for (ORMKeyValue item : saveConfirmationList) {

					if (item.getKey().equalsIgnoreCase("update_encouter_PL")
							&& item.getValue().equalsIgnoreCase("YES")) {
						updateEncounterPL = true;
					}

					System.out.println(item.toString());
				}
			}

			if (ormSaveAppointment.getAppointment_id() == null) // New
			{
				Long generatedId = db.IDGenerator("Appointment", ormSaveAppointment.getPractice_id());
				ormSaveAppointment.setAppointment_id(generatedId);
				ormSaveAppointment.setDate_created(DateTimeUtil.getCurrentDateTime());
				ormSaveAppointment.setDate_modified(DateTimeUtil.getCurrentDateTime());

				result = db.SaveEntity(ormSaveAppointment, EnumUtil.Operation.ADD);

			} else // Modify
			{
				ormSaveAppointment.setDate_modified(DateTimeUtil.getCurrentDateTime());
				result = db.SaveEntity(ormSaveAppointment, EnumUtil.Operation.EDIT);

				if (result > 0 && updateEncounterPL == true) {
					// Update Encounter with PL of Appointment
					List<SpParameters> lstParam = new ArrayList<>();
					lstParam.add(new SpParameters("appointment_id", ormSaveAppointment.getAppointment_id().toString(),
							String.class, ParameterMode.IN));

					// update encounter PL with that of appointment
					db.ExecuteUpdateStoreProcedure("spUpdateEncounterPLWithAppointment", lstParam);
				}
			}

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResult(Integer.toString(result));
			}
		}

		return resp;
	}

	private List<ORMKeyValue> ValidateAppointment(ORMSaveAppointment ormSaveAppointment) {

		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", ormSaveAppointment.getPractice_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("provider_id", ormSaveAppointment.getProvider_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("location_id", ormSaveAppointment.getLocation_id().toString(), String.class,
				ParameterMode.IN));
		lstParam.add(new SpParameters("appointment_date", ormSaveAppointment.getAppointment_date().toString(),
				String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("appointment_time", ormSaveAppointment.getAppointment_time().toString(),
				String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("appointment_id",
				(ormSaveAppointment.getAppointment_id() != null ? ormSaveAppointment.getAppointment_id().toString()
						: ""),
				String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("patient_id",
				(ormSaveAppointment.getPatient_id() != null ? ormSaveAppointment.getPatient_id().toString() : ""),
				String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("duration",
				(ormSaveAppointment.getAppointment_duration() != null
						? ormSaveAppointment.getAppointment_duration().toString()
						: ""),
				String.class, ParameterMode.IN));

		List<ORMKeyValue> lstValidation = db.getStoreProcedureData("ValidateAppointmentNew", ORMKeyValue.class,
				lstParam);

		System.out.println("***  Validation List ***** ");
		for (ORMKeyValue item : lstValidation) {
			System.out.println(item.toString());
		}

		return lstValidation;
	}

	@Override
	public List<ORMSchedulerTiming> getSchedulerTiming(SearchCriteria searchCriteria) {

		List<ORMSchedulerTiming> lstSchedulerTiming = new ArrayList<>();
		List<SpParameters> lstParamTempTiming = new ArrayList<>();
		List<SpParameters> lstParamTiming = new ArrayList<>();
		Date schedulerDate = new Date();
		int slot_size = 15;
		String time_start;
		String time_end;
		boolean break_enabled = false;
		String break_start = null;
		String break_end = null;

		Date DateTimeStart = null;
		Date DateTimeEnd = null;

		boolean isTempApplied = false;
		boolean isDayOff = false;

		lstParamTempTiming.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		lstParamTiming.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				if (pram.getName().equalsIgnoreCase("scheduler_date")) {
					lstParamTempTiming
							.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
					schedulerDate = DateTimeUtil.GetDateFromString(pram.getValue(),
							DateTimeUtil.DateFormatEnum.MM_dd_yyyy);
					continue;
				}

				lstParamTempTiming
						.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
				lstParamTiming.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));

			}
		}

		String selectedDay = DateTimeUtil.GetStringFromDate(schedulerDate, DateTimeUtil.DateFormatEnum.EEEE);

		System.out.println(schedulerDate + " " + selectedDay);

		// Check If Temp Timing is Available
		List<ORMGetSchedulerProviderTempTiming> lstProviderTempTiming = db.getStoreProcedureData(
				"getSchedulerProviderTempTiming", ORMGetSchedulerProviderTempTiming.class, lstParamTempTiming);

		if (lstProviderTempTiming != null && lstProviderTempTiming.size() > 0) {

			isDayOff = lstProviderTempTiming.get(0).getOff_day();

			slot_size = lstProviderTempTiming.get(0).getSlot_size() == null ? 15
					: lstProviderTempTiming.get(0).getSlot_size();
			time_start = lstProviderTempTiming.get(0).getTime_from();
			time_end = lstProviderTempTiming.get(0).getTime_to();

			break_enabled = lstProviderTempTiming.get(0).getEnable_break();
			break_start = lstProviderTempTiming.get(0).getBreak_from();
			break_end = lstProviderTempTiming.get(0).getBreak_to();

			DateTimeStart = DateTimeUtil.GetDateFromString(time_start, DateFormatEnum.hh_mm_a);
			DateTimeEnd = DateTimeUtil.GetDateFromString(time_end, DateFormatEnum.hh_mm_a);
			isTempApplied = true;

		} else // If Temp Time Not Found then Check for Provider Timing;
		{
			List<ORMGetSchedulerProviderTiming> lstProviderTiming = db.getStoreProcedureData(
					"spGetScheduelrProviderTiming", ORMGetSchedulerProviderTiming.class, lstParamTiming);

			List<ORMGetSchedulerProviderTiming> lstProviderTimingForTheDay = lstProviderTiming.stream()
					.filter(t -> t.getWeek_day().equalsIgnoreCase(selectedDay)).collect(Collectors.toList());

			if (lstProviderTimingForTheDay != null && lstProviderTimingForTheDay.size() > 0) {

				slot_size = lstProviderTimingForTheDay.get(0).getSlot_size() == null ? 15
						: lstProviderTimingForTheDay.get(0).getSlot_size();
				time_start = lstProviderTimingForTheDay.get(0).getTime_start();
				time_end = lstProviderTimingForTheDay.get(0).getTime_end();

				break_enabled = lstProviderTimingForTheDay.get(0).getBreak_on();
				break_start = lstProviderTimingForTheDay.get(0).getBreak_start();
				break_end = lstProviderTimingForTheDay.get(0).getBreak_end();

				DateTimeStart = DateTimeUtil.GetDateFromString(time_start, DateFormatEnum.hh_mm_a);
				DateTimeEnd = DateTimeUtil.GetDateFromString(time_end, DateFormatEnum.hh_mm_a);

			}

			isTempApplied = false;
			isDayOff = false;
		}

		if (DateTimeStart != null && DateTimeEnd != null) {
			boolean isBreakAdded = false;
			int id = 1;
			while (DateTimeStart.compareTo(DateTimeEnd) < 0) {

				if (break_enabled) {

					Date DateTimeBreakStart = DateTimeUtil.GetDateFromString(break_start, DateFormatEnum.hh_mm_a);
					Date DateTimeBreakEnd = DateTimeUtil.GetDateFromString(break_end, DateFormatEnum.hh_mm_a);

					if (DateTimeStart.compareTo(DateTimeBreakStart) >= 0
							&& DateTimeStart.compareTo(DateTimeBreakEnd) < 0) {
						if (isBreakAdded == false) {

							lstSchedulerTiming.add(new ORMSchedulerTiming(id, "BREAK_TIME", slot_size, isTempApplied,
									false, break_start + " - " + break_end));
							// System.out.println(selectedDay+" : Break");
							isBreakAdded = true;
						}
						DateTimeStart = DateUtils.addMinutes(DateTimeStart, slot_size);
						continue;
					}

				}

				lstSchedulerTiming.add(new ORMSchedulerTiming(id,
						DateTimeUtil.GetStringFromDate(DateTimeStart, DateFormatEnum.hh_mm_a), slot_size, isTempApplied,
						true, ""));
				// System.out.println(selectedDay+" :
				// "+DateTimeUtil.GetStringFromDate(DateTimeStart, DateFormatEnum.hh_mm_a));
				DateTimeStart = DateUtils.addMinutes(DateTimeStart, slot_size);
			}
		} else {
			if (isDayOff) {
				lstSchedulerTiming
						.add(new ORMSchedulerTiming(1, "DAY_OFF", 15, isTempApplied, false, "Provider Day is Off"));
			} else {
				lstSchedulerTiming.add(new ORMSchedulerTiming(1, "TIMING_NOT_AVILABLE", 15, isTempApplied, false,
						"Provider Timing Not Set"));
			}
		}

		// BREAK
		// TIMING_NOT_AVILABLE
		// TEMP_TIMING_APPLY
		// PROVIDER_DAY_OFF

		return lstSchedulerTiming;
	}

	@Override
	public ServiceResponse<ORMKeyValue> deleteAppointments(ORMDeleteRecord ormDeleteRecord) {
		// TODO Auto-generated method stub

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(
				new SpParameters("appointment_id", ormDeleteRecord.getColumn_id(), String.class, ParameterMode.IN));

		List<ORMKeyValue> lstDelConirmation = db.getStoreProcedureData("spConfirmAppointmentDelete", ORMKeyValue.class,
				lstParam);
		List<ORMKeyValue> lstNoAllowed = new ArrayList<>();

		System.out.println("***  Validation List ***** ");
		for (ORMKeyValue item : lstDelConirmation) {
			System.out.println(item.toString());

			if (item.getValue().equalsIgnoreCase("YES")) {
				lstNoAllowed.add(item);
			}
		}

		if (lstNoAllowed.isEmpty()) {

			int result = db.deleteRecord(ormDeleteRecord);

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while deleting record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResult(Integer.toString(result));
			}
		} else {
			resp.setStatus(ServiceResponseStatus.NOT_ALLOWED);
			resp.setResponse("Not Allowed.");
			resp.setResponse_list(lstNoAllowed);
		}

		return resp;
	}

	@Override
	public List<ORMGetSchedulerAppointmentDetails> getSchedulerAppointmentDetails(Long appointment_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("appointment_id", appointment_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetSchedulerAppointmentDetails> lst = db.getStoreProcedureData("getSchedulerAppointmentDetail",
				ORMGetSchedulerAppointmentDetails.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMGetSchedulerRooms> getSchedulerRooms(Long practice_id, Long location_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practiceID", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("locationID", location_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetSchedulerRooms> lst = db.getStoreProcedureData("spGetRooms", ORMGetSchedulerRooms.class, lstParam);

		return lst;
	}

	@Override
	public List<ORMGetCheckInOutInfo> getCheckInOutInfo(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMGetCheckInOutInfo> lstAppointments = db.getStoreProcedureData("spGetCheckInOutInfo",
				ORMGetCheckInOutInfo.class, lstParam);

		return lstAppointments;
	}

	@Override
	public int UpdatePatientContactInfo(ORMUpdatePatientContactInfo objContactInfo) {
		// TODO Auto-generated method stub

		String strQuery = " update patient set address='" + objContactInfo.getAddressLine1() + "',address2='"
				+ objContactInfo.getAddressLine2() + "',zip='" + objContactInfo.getZip() + "',city='"
				+ objContactInfo.getCity() + "',state='" + objContactInfo.getState() + "',cell_phone='"
				+ objContactInfo.getCellPhone() + "',home_phone='" + objContactInfo.getHomePhone() + "',"
				+ " date_modified=getdate(),client_date_modified='" + objContactInfo.getClientDateTime()
				+ "',modified_user='" + objContactInfo.getLogedInUser() + "', system_ip='"
				+ objContactInfo.getSystemIP() + "',primary_contact_type='" + objContactInfo.getPrimaryContactType()
				+ "' " + " where patient_id='" + objContactInfo.getPatientId().toString() + "' ";

		return db.ExecuteUpdateQuery(strQuery);
	}

	@Override
	public int updateCheckInCheckOutRoom(List<ORMKeyValue> lstKeyValue) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();

		if (lstKeyValue != null && !lstKeyValue.isEmpty()) {
			for (ORMKeyValue pram : lstKeyValue) {
				lstParam.add(new SpParameters(pram.getKey(),
						(pram.getValue() != null ? pram.getValue().toString() : ""), String.class, ParameterMode.IN));
			}
		}

		return db.ExecuteUpdateStoreProcedure("spUpdateCheckInCheckOutRoom", lstParam);
	}

	@Override
	public List<ORMIdDescription> getSchedulerSources(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		List<ORMIdDescription> lst = db.getStoreProcedureData("getAppoitmentSource", ORMIdDescription.class, lstParam);

		return lst;
	}

	@Override
	public Wrapper_AppointmentsWithTiming getSchedulerAppointmentsWithTiming(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		SearchCriteria searchCriteriaTiming = new SearchCriteria();
		searchCriteriaTiming.setParam_list(null);

		searchCriteriaTiming.setPractice_id(searchCriteria.getPractice_id());
		List<SearchCriteriaParamList> param_list = new ArrayList<>();
		for (SearchCriteriaParamList p : searchCriteria.getParam_list()) {
			switch (p.getName()) {
			case "appointment_date":
				param_list.add(new SearchCriteriaParamList("scheduler_date", p.getValue(), p.getOption()));
				break;
			case "location_id":
			case "provider_id":
				param_list.add(new SearchCriteriaParamList(p.getName(), p.getValue(), p.getOption()));
				break;
			default:
				break;
			}
		}
		searchCriteriaTiming.setParam_list(param_list);

		List<ORMGetSchedulerAppointments> lstAppointments = getSchedulerAppointments(searchCriteria);
		List<ORMSchedulerTiming> lstSchedulerTiming = getSchedulerTiming(searchCriteriaTiming);

		Boolean isDayOff = false;
		// Boolean isProviderTimingNotAvailable=false;
		Boolean isTempApplied = false;
		int slotSize = 15;

		List<ORMKeyValue> lstTimingInfo = new ArrayList<>();

		// to calculate total no of columns
		List<ORMKeyValue> lstAppTimeSlots = new ArrayList<>();

		if (lstSchedulerTiming != null && !lstSchedulerTiming.isEmpty()) {
			isTempApplied = lstSchedulerTiming.get(0).getIs_temp_applied();
			lstTimingInfo.add(new ORMKeyValue("temp_applied", isTempApplied.toString()));
			if (lstSchedulerTiming.get(0).getTiming().equalsIgnoreCase("DAY_OFF")) {
				isDayOff = true;
				lstSchedulerTiming.remove(0);
				lstTimingInfo.add(new ORMKeyValue("off_day", isDayOff.toString()));
			} else if (lstSchedulerTiming.get(0).getTiming().equalsIgnoreCase("TIMING_NOT_AVILABLE")) {
				lstTimingInfo.add(new ORMKeyValue("timing_not_avilable", "true"));
				lstSchedulerTiming.remove(0);
			} else {
				slotSize = lstSchedulerTiming.get(0).getSlot_size();
			}

		}
		int index = 0;
		int appCount = 0;
		if (lstAppointments != null && !lstAppointments.isEmpty()) {
			for (ORMGetSchedulerAppointments app : lstAppointments) {

				lstAppTimeSlots.add(new ORMKeyValue("timing", app.getAppointment_time()));

				if (app.getPatient_id() > 0)
					appCount++;

				Boolean isTimingExist = false;
				Date timeToSearch = DateTimeUtil.GetDateFromString(app.getAppointment_time(), DateFormatEnum.hh_mm_a);
				Date timeToCompare;
				// check if appointment slot is available or not if not then insert in timing
				// array.
				if (lstSchedulerTiming != null && !lstSchedulerTiming.isEmpty()) {
					index = 0;
					for (ORMSchedulerTiming timing : lstSchedulerTiming) {

						if (timing.getTiming().equalsIgnoreCase("BREAK_TIME")) {
							index++;
							continue;
						}

						if (timing.getTiming().equalsIgnoreCase(app.getAppointment_time())) {
							isTimingExist = true;

							if (app.getPatient_id() == -1) {
								timing.setEnable(false);
								timing.setInfo("Provider timing is blocked.");
							}
							break;
						}

						timeToCompare = DateTimeUtil.GetDateFromString(timing.getTiming(), DateFormatEnum.hh_mm_a);
						if (timeToSearch.compareTo(timeToCompare) < 0) {
							isTimingExist = false;
							break;
						}

						index++;

					}
				}

				if (isTimingExist == false) {
					if (lstSchedulerTiming == null) {
						lstSchedulerTiming = new ArrayList<ORMSchedulerTiming>();
					}
					lstSchedulerTiming.add(index,
							new ORMSchedulerTiming(-1, app.getAppointment_time(), slotSize, isTempApplied, false,
									isDayOff ? "Provider Day is Off." : "Provider Timing is Not Available."));
				}

				// check for extended slots
				if (app.getAppointment_duration() > slotSize) {

					int slot_count = app.getAppointment_duration() / slotSize;
					boolean isExtendedFound = false;
					// set slot count in appointment list
					app.setSlot_count(slot_count);

					// Date extendedStartDateTime =
					// DateTimeUtil.GetDateFromString(app.getAppointment_time(),
					// DateFormatEnum.hh_mm_a);

					for (int i = 1; i < slot_count; i++) {
						isExtendedFound = false;

						timeToSearch = DateUtils.addMinutes(timeToSearch, slotSize);
						String extededTime = DateTimeUtil.GetStringFromDate(timeToSearch, DateFormatEnum.hh_mm_a);

						for (int j = index; j < lstSchedulerTiming.size(); j++) {

							if (lstSchedulerTiming.get(j).getTiming().equalsIgnoreCase("BREAK_TIME")) {
								index++;
								continue;
							}

							if (lstSchedulerTiming.get(j).getTiming().equalsIgnoreCase(extededTime)) {
								isExtendedFound = true;
								lstAppTimeSlots.add(new ORMKeyValue("timing", extededTime));
								if (app.getPatient_id() == -1) {
									lstSchedulerTiming.get(j).setEnable(false);
									lstSchedulerTiming.get(j).setInfo("Provider timing is blocked.");
								}
								break;
							}

							timeToCompare = DateTimeUtil.GetDateFromString(lstSchedulerTiming.get(j).getTiming(),
									DateFormatEnum.hh_mm_a);
							if (timeToSearch.compareTo(timeToCompare) < 0) {
								isExtendedFound = false;
								break;
							}

							index++;

						}

						if (isExtendedFound == false) {
							lstSchedulerTiming.add(index,
									new ORMSchedulerTiming(-1, extededTime, slotSize, isTempApplied, false,
											isDayOff ? "Provider Day is Off." : "Provider Timing Not Available."));

							lstAppTimeSlots.add(new ORMKeyValue("timing", extededTime));
						}

					}

				}

			}
		} else { // add empty column
			lstAppointments = new ArrayList<>();
			/*
			for (ORMSchedulerTiming timing : lstSchedulerTiming) {
				lstAppointments.add(new ORMGetSchedulerAppointments(null, null, timing.getTiming(), null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, 1, null, null,null, null));

				// new ORMGetSchedulerAppointments(null, null, timing.getTiming(), null, null,
				// null,
				// null, null, null, null, null, null, null, null, null,
				// null, null, null, 1, null));

				break;
			}
			*/
		}

		lstTimingInfo.add(new ORMKeyValue("appointment_count", Integer.toString(appCount)));

		// ****** total number of columns
		// ***************************************************
		String appTime = "";
		int tempColCount = 2;
		int columnCount = 2;
		for (ORMKeyValue objSlot : lstAppTimeSlots) {

			if (objSlot.getValue().equalsIgnoreCase(appTime)) {
				tempColCount++;
				if (tempColCount > columnCount)
					columnCount = tempColCount;
			} else {
				if (tempColCount > columnCount)
					columnCount = tempColCount;

				tempColCount = 2;
				appTime = objSlot.getValue();
			}
		}
		lstTimingInfo.add(new ORMKeyValue("column_count", Integer.toString(columnCount)));
		// ***************************************************

		Wrapper_AppointmentsWithTiming obj = new Wrapper_AppointmentsWithTiming();
		obj.setLstAppointments(lstAppointments);
		obj.setLstTiming(lstSchedulerTiming);
		obj.setLstTimingInfo(lstTimingInfo);

		return obj;
	}

	@Override
	public List<ORMGetProviderTiming> getProviderTimingSettings(SearchCriteria searchCriteria) {
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMGetProviderTiming> lst = db.getStoreProcedureData("getProviderTiming", ORMGetProviderTiming.class,
				lstParam);

		return lst;
	}

	@Override
	public int saveProviderTimingSettings(List<ORMSaveProviderTiming> lstORMSaveProvider) {
		// TODO Auto-generated method stub
		int result = 0;

		if (lstORMSaveProvider != null && lstORMSaveProvider.size() > 0) {

			for (ORMSaveProviderTiming timing : lstORMSaveProvider) {

				timing.setDate_modified(DateTimeUtil.getCurrentDateTime());

				if (timing.getTiming_id() == null) // New
				{
					timing.setTiming_id(db.IDGenerator("provider_timing", timing.getPractice_id()));
					timing.setDate_created(DateTimeUtil.getCurrentDateTime());

					result += db.SaveEntity(timing, Operation.ADD);
				} else// modification
				{
					result += db.SaveEntity(timing, Operation.EDIT);
				}
			}
		}

		return result;
	}

	@Override
	public List<ORMGetProviderTempTiming> getProviderTempTimingSettings(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMGetProviderTempTiming> lst = db.getStoreProcedureData("getTempProviderTiming",
				ORMGetProviderTempTiming.class, lstParam);

		return lst;
	}

	@Override
	public int saveProviderTempTimingSettings(ORMSaveProviderTempTiming ormSaveTempTiming) {
		// TODO Auto-generated method stub
		int result = 0;

		ormSaveTempTiming.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (ormSaveTempTiming.getTemp_timing_id() == null) // New
		{
			ormSaveTempTiming.setTemp_timing_id(
					db.IDGenerator("provider_temp_office_timing", ormSaveTempTiming.getPractice_id()));
			ormSaveTempTiming.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormSaveTempTiming, Operation.ADD);
		} else// modification
		{
			result = db.SaveEntity(ormSaveTempTiming, Operation.EDIT);
		}

		return result;
	}

	@Override
	public List<ORMGetLocationProviderSetting> getLocationProviderSetting(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMGetLocationProviderSetting> lst = db.getStoreProcedureData("spGetLocationProvidersSetting",
				ORMGetLocationProviderSetting.class, lstParam);

		return lst;
	}

	@Override
	public int saveLocationProviderSetting(List<ORMSaveLocationProviders> lstSaveLocationProviders) {
		// TODO Auto-generated method stub
		int result = 0;

		if (lstSaveLocationProviders != null && lstSaveLocationProviders.size() > 0) {

			for (ORMSaveLocationProviders orm : lstSaveLocationProviders) {

				orm.setDate_modified(DateTimeUtil.getCurrentDateTime());

				if (orm.getSetting_id() == null) // New
				{
					orm.setSetting_id(db.IDGenerator("provider_locations", orm.getPractice_id()));
					orm.setDate_created(DateTimeUtil.getCurrentDateTime());

					result += db.SaveEntity(orm, Operation.ADD);
				} else// modification
				{
					result += db.SaveEntity(orm, Operation.EDIT);
				}
			}
		}

		return result;
	}

	@Override
	public List<ORMGetAppointmentSourceSetting> getAppointmentSourceSettings(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetAppointmentSourceSetting> lst = db.getStoreProcedureData("getAppointmentSourceSetting",
				ORMGetAppointmentSourceSetting.class, lstParam);

		return lst;
	}

	@Override
	public int saveAppointmentSourceSettings(ORMSaveAppointmentSource ormSave) {
		// TODO Auto-generated method stub
		int result = 0;

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (ormSave.getSource_id() == null) // New
		{
			ormSave.setSource_id(db.IDGenerator("appointment_source", ormSave.getPractice_id()));
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result += db.SaveEntity(ormSave, Operation.ADD);
		} else// modification
		{
			result += db.SaveEntity(ormSave, Operation.EDIT);
		}

		return result;
	}

	@Override
	public List<ORMGetAppiontmentTypeSetting> getAppointmentTypeSettings(Long practice_id) {
		// TODO Auto-generated method stub

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetAppiontmentTypeSetting> lst = db.getStoreProcedureData("getAppointmentType",
				ORMGetAppiontmentTypeSetting.class, lstParam);

		return lst;
	}

	@Override
	public int saveAppointmentTypeSettings(ORMSaveAppointmentTypes ormSave) {
		// TODO Auto-generated method stub
		int result = 0;

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (ormSave.getType_id() == null) // New
		{
			ormSave.setType_id(db.IDGenerator("appointment_type", ormSave.getPractice_id()));
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result += db.SaveEntity(ormSave, Operation.ADD);
		} else// modification
		{
			result += db.SaveEntity(ormSave, Operation.EDIT);
		}

		return result;
	}

	@Override
	public List<ORMGetAppointmentStatusSetting> getAppointmentStatusSettings(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetAppointmentStatusSetting> lst = db.getStoreProcedureData("getAppointmentStatus",
				ORMGetAppointmentStatusSetting.class, lstParam);

		return lst;
	}

	@Override
	public int saveAppointmentStatusSettings(ORMSaveAppointmentStatus ormSave) {
		// TODO Auto-generated method stub
		int result = 0;

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (ormSave.getStatus_id() == null) // New
		{
			ormSave.setStatus_id(db.IDGenerator("appointment_status", ormSave.getPractice_id()));
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result += db.SaveEntity(ormSave, Operation.ADD);
		} else// modification
		{
			result += db.SaveEntity(ormSave, Operation.EDIT);
		}

		return result;
	}

	@Override
	public List<ORMGetLocationRoomsSettting> getLocationRoomsSetting(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMGetLocationRoomsSettting> lst = db.getStoreProcedureData("spGetLocation_Rooms",
				ORMGetLocationRoomsSettting.class, lstParam);

		return lst;
	}

	@Override
	public int saveLocationRoomsSettings(ORMSaveLocationRooms ormSave) {
		// TODO Auto-generated method stub
		int result = 0;

		ormSave.setDate_modified(DateTimeUtil.getCurrentDateTime());

		if (ormSave.getRooms_id() == null) // New
		{
			ormSave.setRooms_id(db.IDGenerator("rooms", ormSave.getPractice_id()));
			ormSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result += db.SaveEntity(ormSave, Operation.ADD);
		} else// modification
		{
			result += db.SaveEntity(ormSave, Operation.EDIT);
		}

		// update default room
		if (result > 0 && ormSave.getIs_default() == true) {

			String sqlQuery = "update rooms set is_default=0,date_modified=getdate(), client_date_modified='"
					+ ormSave.getClient_date_modified() + "', modified_user='" + ormSave.getModified_user()
					+ "' where location_id='" + ormSave.getLocation_id() + "' and rooms_id<>'" + ormSave.getRooms_id()
					+ "' and isnull(is_default,0)=1";
			db.ExecuteUpdateQuery(sqlQuery);
		}
		return result;
	}

	@Override
	public List<ORMGetAppointmentRules> getAppointmentRules(Long provider_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("provider_id", provider_id.toString(), String.class, ParameterMode.IN));

		List<ORMGetAppointmentRules> lst = db.getStoreProcedureData("getAppointmentRule", ORMGetAppointmentRules.class,
				lstParam);

		return lst;
	}

	@Override
	public int saveAppointmentRules(List<ORMSaveAppointmentRules> lstObjSave) {
		int result = 0;

		if (lstObjSave != null && lstObjSave.size() > 0) {

			for (ORMSaveAppointmentRules objSave : lstObjSave) {

				objSave.setDate_modified(DateTimeUtil.getCurrentDateTime());

				if (objSave.getRule_id() == null) // New
				{
					objSave.setRule_id(db.IDGenerator("scheduler_rule", objSave.getPractice_id()));
					objSave.setDate_created(DateTimeUtil.getCurrentDateTime());

					result += db.SaveEntity(objSave, Operation.ADD);
				} else// modification
				{
					result += db.SaveEntity(objSave, Operation.EDIT);
				}
			}
		}

		return result;
	}

	@Override
	public List<ORMTwoColumnGeneric> getSuperBillcodes(String provider_id, String superbill, String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("provider_id", provider_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("value", superbill.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id", patient_id.toString(), String.class, ParameterMode.IN));

		List<ORMTwoColumnGeneric> lst = db.getStoreProcedureData("spGet_SuperBillDiag", ORMTwoColumnGeneric.class,
				lstParam);

		return lst;
	}

	@Override
	public List<ORMSuperbillHeadder> getSuperbillHeaddertDetails(String appointment_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("appointment_id", appointment_id.toString(), String.class, ParameterMode.IN));

		List<ORMSuperbillHeadder> lst = db.getStoreProcedureData("sp_get_Superbill_Print_headder",
				ORMSuperbillHeadder.class, lstParam);

		return lst;
	}

	@Override
	public Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView>> getAppointmentsByLocation(
			SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMGetSchedulerAppointmentsLocationView> lstAppointments = db.getStoreProcedureData(
				"getSchedulerAppointmentsByLocation", ORMGetSchedulerAppointmentsLocationView.class, lstParam);

		Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView>> wrapperSchedulerAppointments = new Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView>>();
		// wrapper.setLstAppointments(lstAppointments);
		List<ORMSchedulerTiming> lstSchedulerTiming = new ArrayList<ORMSchedulerTiming>();
		List<Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView>> lstWrapperAppointments = new ArrayList<Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView>>();

		// Wrapper_SchedulerAppointmentsLocationView wrapperLocView = null;

		if (lstAppointments != null && !lstAppointments.isEmpty()) {

			// lstWraper = new ArrayList<>();
			int slot_size = 60;// app.getAppointment_duration() / slotSize;

			for (ORMGetSchedulerAppointmentsLocationView app : lstAppointments) {

				Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView> providerApp = null;
				// List<Wrapper_ProviderAppointments> lstProviderApp =
				// wrapper.getLstProviderAppointments();

				Boolean isProviderFound = false;
				if (lstWrapperAppointments != null && !lstWrapperAppointments.isEmpty()) {
					for (int i = 0; i < lstWrapperAppointments.size(); i++) {
						if (lstWrapperAppointments.get(i).getId().equals(app.getProvider_id())) {
							providerApp = lstWrapperAppointments.get(i);
							isProviderFound = true;
							break;
						}
					}
				}
				// else {
				// lstProviderApp = new ArrayList<>();
				// wrapper.setLstProviderAppointments(lstProviderApp);
				// }

				if (!isProviderFound) {
					providerApp = new Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView>();
					providerApp.setId(app.getProvider_id());
					providerApp.setName(app.getProvider_name());
					providerApp.setAppointments(new ArrayList<>());

				}
				providerApp.getAppointments().add(app);
				// wrapper.getLstProviderAppointments().add(providerApp);

				// if (lstWrapperProviderAppointments == null ||
				// lstWrapperProviderAppointments.isEmpty())
				if (!isProviderFound) {
					{
						lstWrapperAppointments.add(providerApp);

						// wrapper.setLstProviderAppointments(new ArrayList<>());
						// wrapper.getLstProviderAppointments().add(providerApp);
					}

					if (app.getAppointment_duration() < slot_size) {
						slot_size = app.getAppointment_duration();
					}
				}
			}

			// to calculate total no of columns
			List<ORMKeyValue> lstAppTimeSlots = new ArrayList<>();

			// lstSchedulerTiming.add(index,
			// new ORMSchedulerTiming(-1, app.getAppointment_time(), slotSize,
			// isTempApplied, false,
			// isDayOff ? "Provider Day is Off." : "Provider Timing is Not Available."));
			int index = 0;
			for (ORMGetSchedulerAppointmentsLocationView app : lstAppointments) {

				lstAppTimeSlots.add(new ORMKeyValue("timing", app.getAppointment_time()));

				// if (app.getPatient_id() > 0)
				// appCount++;

				Boolean isTimingExist = false;
				Date timeToSearch = DateTimeUtil.GetDateFromString(app.getAppointment_time(), DateFormatEnum.hh_mm_a);
				Date timeToCompare;
				// check if appointment slot is available or not if not then insert in timing
				// array.
				if (lstSchedulerTiming != null && !lstSchedulerTiming.isEmpty()) {
					index = 0;
					for (ORMSchedulerTiming timing : lstSchedulerTiming) {

						// if (timing.getTiming().equalsIgnoreCase("BREAK_TIME")) {
						// index++;
						// continue;
						// }

						if (timing.getTiming().equalsIgnoreCase(app.getAppointment_time())) {
							isTimingExist = true;
							if (app.getPatient_id() == -1) {
								timing.setEnable(false);
								timing.setInfo("");
							}
							break;
						}

						timeToCompare = DateTimeUtil.GetDateFromString(timing.getTiming(), DateFormatEnum.hh_mm_a);
						if (timeToSearch.compareTo(timeToCompare) < 0) {
							isTimingExist = false;
							break;
						}

						index++;

					}
				}

				if (isTimingExist == false) {
					if (lstSchedulerTiming == null) {
						lstSchedulerTiming = new ArrayList<ORMSchedulerTiming>();
					}
					lstSchedulerTiming.add(index,
							new ORMSchedulerTiming(-1, app.getAppointment_time(), slot_size, null, false, ""));
				}

				// check for extended slots
				if (app.getAppointment_duration() > slot_size) {

					int slot_count = app.getAppointment_duration() / slot_size;
					boolean isExtendedFound = false;
					// set slot count in appointment list
					app.setSlot_count(slot_count);

					// Date extendedStartDateTime =
					// DateTimeUtil.GetDateFromString(app.getAppointment_time(),
					// DateFormatEnum.hh_mm_a);

					for (int i = 1; i < slot_count; i++) {
						isExtendedFound = false;

						timeToSearch = DateUtils.addMinutes(timeToSearch, slot_size);
						String extededTime = DateTimeUtil.GetStringFromDate(timeToSearch, DateFormatEnum.hh_mm_a);

						for (int j = index; j < lstSchedulerTiming.size(); j++) {

							// if (lstSchedulerTiming.get(j).getTiming().equalsIgnoreCase("BREAK_TIME")) {
							// index++;
							// continue;
							// }

							if (lstSchedulerTiming.get(j).getTiming().equalsIgnoreCase(extededTime)) {
								isExtendedFound = true;
								lstAppTimeSlots.add(new ORMKeyValue("timing", extededTime));
								if (app.getPatient_id() == -1) {
									lstSchedulerTiming.get(j).setEnable(false);
									lstSchedulerTiming.get(j).setInfo("Provider timing is blocked.");
								}
								break;
							}

							timeToCompare = DateTimeUtil.GetDateFromString(lstSchedulerTiming.get(j).getTiming(),
									DateFormatEnum.hh_mm_a);
							if (timeToSearch.compareTo(timeToCompare) < 0) {
								isExtendedFound = false;
								break;
							}

							index++;

						}

						if (isExtendedFound == false) {
							lstSchedulerTiming.add(index,
									new ORMSchedulerTiming(-1, extededTime, slot_size, null, null, null));
							lstAppTimeSlots.add(new ORMKeyValue("timing", extededTime));
						}

					}
				}
			}

		}

		wrapperSchedulerAppointments.setTiming(lstSchedulerTiming);

		if (lstWrapperAppointments != null) {
			for (Wrapper_Appointments<ORMGetSchedulerAppointmentsLocationView> wrapper_ProviderAppointments : lstWrapperAppointments) {

				String appTime = "";
				int tempColCount = 1;
				int columnCount = 1;
				int total_appointments = 0;

				for (ORMGetSchedulerAppointmentsLocationView app : wrapper_ProviderAppointments.getAppointments()) {

					if (app.getPatient_id() > 0) {
						total_appointments++;
					}

					if (app.getAppointment_time().equalsIgnoreCase(appTime)) {
						tempColCount++;
						if (tempColCount > columnCount)
							columnCount = tempColCount;
					} else {
						if (tempColCount > columnCount)
							columnCount = tempColCount;

						tempColCount = 1;
						appTime = app.getAppointment_time();
					}
				}

				wrapper_ProviderAppointments.setColumn_count(columnCount);
				wrapper_ProviderAppointments.setTotal_appointments(total_appointments);

			}
		}

		wrapperSchedulerAppointments.setAppointments(lstWrapperAppointments);

		return wrapperSchedulerAppointments;
	}

	@Override
	public Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView>> getAppointmentsByProvider(
			SearchCriteria searchCriteria) {

		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMGetSchedulerAppointmentsProviderView> lstAppointments = db.getStoreProcedureData(
				"getSchedulerAppointmentsByProvider", ORMGetSchedulerAppointmentsProviderView.class, lstParam);

		Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView>> wrapperSchedulerAppointments = new Wrapper_SchedulerAppointments<Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView>>();
		// wrapper.setLstAppointments(lstAppointments);
		List<ORMSchedulerTiming> lstSchedulerTiming = new ArrayList<ORMSchedulerTiming>();
		List<Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView>> lstWrapperAppointments = new ArrayList<Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView>>();

		// Wrapper_SchedulerAppointmentsLocationView wrapperLocView = null;

		if (lstAppointments != null && !lstAppointments.isEmpty()) {

			// lstWraper = new ArrayList<>();
			int slot_size = 60;// app.getAppointment_duration() / slotSize;

			for (ORMGetSchedulerAppointmentsProviderView app : lstAppointments) {

				Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView> providerApp = null;
				// List<Wrapper_ProviderAppointments> lstProviderApp =
				// wrapper.getLstProviderAppointments();

				Boolean isProviderFound = false;
				if (lstWrapperAppointments != null && !lstWrapperAppointments.isEmpty()) {
					for (int i = 0; i < lstWrapperAppointments.size(); i++) {
						if (lstWrapperAppointments.get(i).getId().equals(app.getLocation_id())) {
							providerApp = lstWrapperAppointments.get(i);
							isProviderFound = true;
							break;
						}
					}
				}
				// else {
				// lstProviderApp = new ArrayList<>();
				// wrapper.setLstProviderAppointments(lstProviderApp);
				// }

				if (!isProviderFound) {
					providerApp = new Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView>();
					providerApp.setId(app.getLocation_id());
					providerApp.setName(app.getLocation_name());
					providerApp.setAppointments(new ArrayList<>());
				}
				providerApp.getAppointments().add(app);
				// wrapper.getLstProviderAppointments().add(providerApp);

				// if (lstWrapperProviderAppointments == null ||
				// lstWrapperProviderAppointments.isEmpty())
				if (!isProviderFound) {
					{
						lstWrapperAppointments.add(providerApp);

						// wrapper.setLstProviderAppointments(new ArrayList<>());
						// wrapper.getLstProviderAppointments().add(providerApp);
					}

					if (app.getAppointment_duration() < slot_size) {
						slot_size = app.getAppointment_duration();
					}
				}
			}

			// to calculate total no of columns
			List<ORMKeyValue> lstAppTimeSlots = new ArrayList<>();

			// lstSchedulerTiming.add(index,
			// new ORMSchedulerTiming(-1, app.getAppointment_time(), slotSize,
			// isTempApplied, false,
			// isDayOff ? "Provider Day is Off." : "Provider Timing is Not Available."));
			int index = 0;
			for (ORMGetSchedulerAppointmentsProviderView app : lstAppointments) {

				lstAppTimeSlots.add(new ORMKeyValue("timing", app.getAppointment_time()));

				// if (app.getPatient_id() > 0)
				// appCount++;

				Boolean isTimingExist = false;
				Date timeToSearch = DateTimeUtil.GetDateFromString(app.getAppointment_time(), DateFormatEnum.hh_mm_a);
				Date timeToCompare;
				// check if appointment slot is available or not if not then insert in timing
				// array.
				if (lstSchedulerTiming != null && !lstSchedulerTiming.isEmpty()) {
					index = 0;
					for (ORMSchedulerTiming timing : lstSchedulerTiming) {

						// if (timing.getTiming().equalsIgnoreCase("BREAK_TIME")) {
						// index++;
						// continue;
						// }

						if (timing.getTiming().equalsIgnoreCase(app.getAppointment_time())) {
							isTimingExist = true;
							if (app.getPatient_id() == -1) {
								timing.setEnable(false);
								timing.setInfo("");
							}
							break;
						}

						timeToCompare = DateTimeUtil.GetDateFromString(timing.getTiming(), DateFormatEnum.hh_mm_a);
						if (timeToSearch.compareTo(timeToCompare) < 0) {
							isTimingExist = false;
							break;
						}

						index++;

					}
				}

				if (isTimingExist == false) {
					if (lstSchedulerTiming == null) {
						lstSchedulerTiming = new ArrayList<ORMSchedulerTiming>();
					}
					lstSchedulerTiming.add(index,
							new ORMSchedulerTiming(-1, app.getAppointment_time(), slot_size, null, false, ""));
				}

				// check for extended slots
				if (app.getAppointment_duration() > slot_size) {

					int slot_count = app.getAppointment_duration() / slot_size;
					boolean isExtendedFound = false;
					// set slot count in appointment list
					app.setSlot_count(slot_count);

					// Date extendedStartDateTime =
					// DateTimeUtil.GetDateFromString(app.getAppointment_time(),
					// DateFormatEnum.hh_mm_a);

					for (int i = 1; i < slot_count; i++) {
						isExtendedFound = false;

						timeToSearch = DateUtils.addMinutes(timeToSearch, slot_size);
						String extededTime = DateTimeUtil.GetStringFromDate(timeToSearch, DateFormatEnum.hh_mm_a);

						for (int j = index; j < lstSchedulerTiming.size(); j++) {

							// if (lstSchedulerTiming.get(j).getTiming().equalsIgnoreCase("BREAK_TIME")) {
							// index++;
							// continue;
							// }

							if (lstSchedulerTiming.get(j).getTiming().equalsIgnoreCase(extededTime)) {
								isExtendedFound = true;
								lstAppTimeSlots.add(new ORMKeyValue("timing", extededTime));
								if (app.getPatient_id() == -1) {
									lstSchedulerTiming.get(j).setEnable(false);
									lstSchedulerTiming.get(j).setInfo("Provider timing is blocked.");
								}
								break;
							}

							timeToCompare = DateTimeUtil.GetDateFromString(lstSchedulerTiming.get(j).getTiming(),
									DateFormatEnum.hh_mm_a);
							if (timeToSearch.compareTo(timeToCompare) < 0) {
								isExtendedFound = false;
								break;
							}

							index++;

						}

						if (isExtendedFound == false) {
							lstSchedulerTiming.add(index,
									new ORMSchedulerTiming(-1, extededTime, slot_size, null, null, null));
							lstAppTimeSlots.add(new ORMKeyValue("timing", extededTime));
						}

					}
				}
			}

		}

		wrapperSchedulerAppointments.setTiming(lstSchedulerTiming);

		if (lstWrapperAppointments != null) {
			for (Wrapper_Appointments<ORMGetSchedulerAppointmentsProviderView> wrapper_ProviderAppointments : lstWrapperAppointments) {

				String appTime = "";
				int tempColCount = 1;
				int columnCount = 1;
				int total_appointments = 0;

				for (ORMGetSchedulerAppointmentsProviderView app : wrapper_ProviderAppointments.getAppointments()) {

					if (app.getPatient_id() > 0) {
						total_appointments++;
					}

					if (app.getAppointment_time().equalsIgnoreCase(appTime)) {
						tempColCount++;
						if (tempColCount > columnCount)
							columnCount = tempColCount;
					} else {
						if (tempColCount > columnCount)
							columnCount = tempColCount;

						tempColCount = 1;
						appTime = app.getAppointment_time();
					}
				}

				wrapper_ProviderAppointments.setColumn_count(columnCount);
				wrapper_ProviderAppointments.setTotal_appointments(total_appointments);

			}
		}
		wrapperSchedulerAppointments.setAppointments(lstWrapperAppointments);

		return wrapperSchedulerAppointments;
	}

	@Override
	public List<ORMGetSchedulerAppointmentsMonthView> getAppointmentsMonthView(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		List<ORMGetSchedulerAppointmentsMonthView> lstAppointments = db.getStoreProcedureData(
				"spGetSchedulerAppiotmentsMonthView", ORMGetSchedulerAppointmentsMonthView.class, lstParam);
		
		return lstAppointments;
	}

	@Override
	public List<ORMTwoColumnGeneric> getPatientDataonCheckedIn(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String patient_id="";
		String visit_date="";
		String appointment_id="";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						patient_id = pram.getValue();
						break;
					}
				case "visit_date":
					if (pram.getValue() != "" && pram.getValue() != null) {
						visit_date = pram.getValue();
						break;
					}
				case "appointment_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						appointment_id = pram.getValue();
						break;
					}
				}
			}
		}
		
		 List<ORMTwoColumnGeneric> listResult = new ArrayList<>();
		 ORMTwoColumnGeneric objNew = new ORMTwoColumnGeneric();
	        List<?> listTest = db.getQueryData("select "
	                + "(SELECT '' + ('('+upper(ltrim(rtrim(proc_code))+') '+proc_description)+'\n') "
	                + "from patient_order_test pot  where pot.order_id=po.order_id and isnull(pot.deleted,0)<>1 FOR XML PATH(''))  as col1 "
	                + "from patient_order po "
	                + "where  po.patient_id=" + patient_id + " "
	                + "and convert(date,po.order_date)=convert(date,'" + visit_date + "')", ORMOneColumnGeneric.class);

	        if (listTest != null && listTest.size() > 0) {
	            for (ORMOneColumnGeneric obj : (List<ORMOneColumnGeneric>) listTest) {
	                objNew = new ORMTwoColumnGeneric();
	                objNew.setCol1(Integer.toString(listResult.size() + 1));
	                objNew.setCol2("Patient Order is created for " + obj.getCol1());
	                listResult.add(objNew);
	            }
	        }
	        //Referral
	        List<?> listreferral = db.getQueryData("select distinct "
	                + "upper(isnull(pr.referral_provider_name,'')) as id "
	                + ",upper(isnull(p.last_name,'')+', '+isnull(p.first_name,'')) as name ,  "
	                + "isnull(pr.referral_request,0) as address "
	                + "from patient_referral pr "
	                + "left outer join provider p on p.provider_id=pr.provider_id  and ISNULL(p.deleted,0)<>1 "
	                + "left outer join location l on l.location_id=pr.location_id and ISNULL(l.deleted,0)<>1 "
	                + "left outer join consult_type ct on ct.consult_type_id=pr.consult_type_id "
	                + "where ISNULL(pr.deleted,0)<>1 "
	                + "and pr.patient_id=" + patient_id + " and convert(date,pr.date_created)=convert(date,'" + visit_date + "')", ORMThreeColum.class);
	        if (listreferral != null && listreferral.size() > 0) {
	            for (ORMThreeColum obj : (List<ORMThreeColum>) listreferral) {
	                objNew = new ORMTwoColumnGeneric();
	                objNew.setCol1(Integer.toString(listResult.size() + 1));
	                objNew.setCol2("Patient is Referred to " + obj.getId());

	                listResult.add(objNew);
	            }
	        }

	        List<?> listFollowup = getPatientFollowupApp(appointment_id);
	        if (listFollowup != null && listFollowup.size() > 0) {
	            String day = "";
	            String week = "";
	            String month = "";
	            for (ORMPatient_Followup obj : (List<ORMPatient_Followup>) listFollowup) {
	                day = obj.getDay();
	                week = obj.getWeek();
	                month = obj.getMonth();

	                if (!day.equals("") || !week.equals("") || !month.equals("")) {
	                    String message = "";
	                    if (!month.equals("")) {
	                        message = month + " Month(s)";
	                    }
	                    if (!week.equals("")) {
	                        if (!message.equals("")) {
	                            message += ", " + week + " Week(s)";
	                        } else {
	                            message += week + " Week(s)";
	                        }

	                    }
	                    if (!day.equals("")) {
	                        if (!message.equals("")) {
	                            message += ", " + day + " Days(s)";
	                        } else {
	                            message += day + " Days(s)";
	                        }
	                    }
	                    objNew = new ORMTwoColumnGeneric();
	                    objNew.setCol1(Integer.toString(listResult.size() + 1));
	                    objNew.setCol2("FollowUp Required after " + message + ".\n Please take action accordingly.");
	                    listResult.add(objNew);
	                }

	            }
	        }
	        //Follow Up
	        return listResult;
	}
	public List<?> getPatientFollowupApp(String appointment_id) {
		
        List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("appointment_id", appointment_id, String.class, ParameterMode.IN));	
		List<ORMPatient_Followup> lst = db.getStoreProcedureData(
				"spGetPatientAppointmentFollowUp", ORMPatient_Followup.class, lstParam);
		return lst;
			
	}
}
