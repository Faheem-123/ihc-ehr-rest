package com.ihc.ehr.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ParameterMode;

import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.dao.EncounterDAOImpl;
import com.ihc.ehr.dao.ImmunizationDAOImpl;
import com.ihc.ehr.dao.PatientDAOImpl;
import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.HL7FileGenerationResult;
import com.ihc.ehr.model.ImmRegEvaluatedHistory;
import com.ihc.ehr.model.ImmRegVXUCriteria;
import com.ihc.ehr.model.ImmRegForecast;
import com.ihc.ehr.model.ImmRegQBPCriteria;
import com.ihc.ehr.model.ORMChartImunizationVISGet;
import com.ihc.ehr.model.ORMGetPatImmRegInfoDisplay;
import com.ihc.ehr.model.ORMGetPatientNextOfKin;
import com.ihc.ehr.model.ORMGetPatientRaceEthnicity;
import com.ihc.ehr.model.ORMImmRegMessageImmunizationSave;
import com.ihc.ehr.model.ORMImmRegMsgErrorsSave;
import com.ihc.ehr.model.ORMImmRegPathGet;
import com.ihc.ehr.model.ORMImmRegQueryMessageDetailsGet;
import com.ihc.ehr.model.ORMImmRegQueryMessageDetailsSave;
import com.ihc.ehr.model.ORMImmunizationForRegistryMessageGet;
import com.ihc.ehr.model.ORMImmunizationRegistryInfoGet;
import com.ihc.ehr.model.ORMImmunizationRegistryMessagePatientInfoGet;
import com.ihc.ehr.model.ORMImmunizationRegistryMessageSave;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.WrapperImmRegEvaluationHistoryForecastMessageDetails;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.ImmRegFileProcessingStatus;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

/**
 * 
 * @author Ihsan Ullah
 * @apiNote Do Not use @AutoWired for this class. This class needs to be
 *          instantiated each time
 * 
 */
public class ImmunizationRegistryOperationsMU3 {

	private DBOperations db;
	private PatientDAOImpl patientDAOImpl;
	private EncounterDAOImpl encounterDAOImpl;
	private ImmunizationDAOImpl immunizationDAOImpl;

	public String seperator = "|"; // Aurgument seperator
	public String caret = "^"; // sub-Aurgument-seperator
	public String ampersand = "&"; // sub-Aurgument-seperator
	public String encoding_characters = "^~\\&"; // encoding characters.
	private String strMSH = "";
	private String strPID = "";
	private String strPD1 = "";
	private String strNK1 = "";
	private String strORC = "";
	private String strRXA = "";
	private String strRXR = "";
	private String strOBX = "";
	private String strORCMain;

	private Boolean includeUIDs = false;
	private String strSendingApplicationName = "";
	private String strSendingApplicationUID = "";
	// private String strSendingApplicationUIDType = "";

	private String strReceivingApplicationName = "";
	private String strReceivingApplicationUID = "";
	// private String strReceivingApplicationUIDType = "";

	// private final String strSendingResponsibleOrganization = "NISTEHRFAC";
	// private final String strReceivingResponsibleOrganization = "NISTEHRFAC";
	private String strAdministeredAtLocationID = ""; // for inventory
	private String clinicCode = "";
	private String clinicID = "";
	private String registryCode = "";

	private final String UID_TYPE = "ISO";
	// private final String strEnteringOrganizationID = "NISTEHRFAC";
	// private final String strEnteringOrganizationName = "NISTEHRFacility";
	// private final String strEHRID = "Instant Healthcare";
	// private final String strEHRUID = "2.16.840.1.113883.3.72.5.40.5";
	// private final String strUIDType = "ISO";
	// private String strEHRAssigningAuthority = "";

	private String strSendingFacilityID = "";
	private String strSendingFacilityName = "";
	private String strSendingFacilityUID = "";
	// private String strSendingFacilityUIDType = "";
	// private String strSendingFacilityAssAuth = "";

	private String strRecOrgID = "";
	private String strReceivingFacility = "";
	private String strReceivingFacilityUID = "";
	// private String strReceivingFacilityUIDType = "";
	private String strOrgAssAuth = "";

	private String strPIDAssignAuth;

	private String strFilerPlacerAssignAuth;
	private String strFilerPlacerAssignAuthUID;

	private String strClinicStaffAssignAuth;
	// private String strClinicStaffAssignAuthUID;
	private final String phoneCountryCode = "1";
	private final String addressCountryCode = "USA";

	// private final DateFormat dtInput_formater_default = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// private final DateFormat dtInput_formaterMMDDYYYY = new
	// SimpleDateFormat("MM/dd/yyyy");
	// private final DateFormat dt_formater_yyyyMMdd = new
	// SimpleDateFormat("yyyyMMdd");
	// private final DateFormat dt_formater_TS_Z = new
	// SimpleDateFormat("yyyyMMddHHmmssZZZZ");

	Date date_current;
	String date_current_yyyy_MM_dd_HH_mm_ss_String;
	String date_current_yyyyMMddHHmmssZZZZ_String;

	ORMImmunizationRegistryMessagePatientInfoGet patientInfo;

	Long MessageID = (long) 500101; // Default ID if dot not saving.
	List<HL7FileGenerationResult> lstImmunizationFileGenerationResult = new ArrayList<>();
	int processing_id = 1;
	// HL7FileGenerationResult immunizationFileGenerationResult;
	List<ORMImmRegMessageImmunizationSave> lstImmRegMessageImmunizationSave;
	List<ORMImmunizationForRegistryMessageGet> lstImmunizationForRegistryMessage;

	// Constructor Variables
	Long practiceId;
	Long patientId;
	Long chartId;
	String chartImmunizationIDs;
	List<Long> lstChartImmunizationIds;
	String loginUser;
	String clientIP;
	Boolean isSubmitAfterGeneation;

	// Evaluated History and Forecast Group (Generate QBP Query by Parameter)
	private ORMImmRegQueryMessageDetailsSave objQueryMessageDetails;
	Long QueryMsgDetailID;
	String strMSH_QBP = "";
	String strQPD_QBP = "";// Input Parameter Specification
	String strRCP_QBP = "";

	public ImmunizationRegistryOperationsMU3(DBOperations dbOpt, PatientDAOImpl patientDAOImpl,
			EncounterDAOImpl encounterDAOImpl, ImmunizationDAOImpl immunizationDAOImpl) {
		this.db = dbOpt;
		this.patientDAOImpl = patientDAOImpl;
		this.encounterDAOImpl = encounterDAOImpl;
		this.immunizationDAOImpl = immunizationDAOImpl;
	}

	/*
	 * private void clearAllData() {
	 * 
	 * strMSH = ""; strPID = ""; strPD1 = ""; strNK1 = ""; strORC = ""; strRXA = "";
	 * strRXR = ""; strOBX = ""; strORCMain = "";
	 * 
	 * includeUIDs = false; strSendingApplicationName = ""; strSendingApplicationUID
	 * = "";
	 * 
	 * strReceivingApplicationName = ""; strReceivingApplicationUID = "";
	 * 
	 * strAdministeredAtLocationID = ""; // for inventory clinicCode = ""; clinicID
	 * = ""; registryCode = "";
	 * 
	 * strSendingFacilityID = ""; strSendingFacilityName = ""; strSendingFacilityUID
	 * = "";
	 * 
	 * strRecOrgID = ""; strReceivingFacility = ""; strReceivingFacilityUID = "";
	 * strOrgAssAuth = "";
	 * 
	 * strPIDAssignAuth = "";
	 * 
	 * strFilerPlacerAssignAuth = ""; strFilerPlacerAssignAuthUID = "";
	 * 
	 * strClinicStaffAssignAuth = "";
	 * 
	 * patientInfo = null; MessageID = (long) 500101; // Default ID if dot not
	 * saving. lstImmunizationFileGenerationResult = null; processing_id = 1;
	 * //immunizationFileGenerationResult = null; lstImmRegMessageImmunizationSave =
	 * null; lstImmunizationForRegistryMessage = null;
	 * 
	 * date_current = new Date(); date_current_yyyy_MM_dd_HH_mm_ss_String =
	 * DateTimeUtil.GetStringFromDate(date_current,
	 * DateFormatEnum.yyyy_MM_dd_HH_mm_ss); date_current_yyyyMMddHHmmssZZZZ_String =
	 * DateTimeUtil.GetStringFromDate(date_current,
	 * DateFormatEnum.yyyyMMddHHmmssZZZZ);
	 * 
	 * practiceId = null; patientId = null; chartId = null; chartImmunizationIDs =
	 * ""; lstChartImmunizationIds = null; loginUser = ""; clientIP = ""; clinicID =
	 * ""; isSubmitAfterGeneation = false;
	 * 
	 * QueryMsgDetailID = null; strMSH_QBP = ""; strQPD_QBP = ""; strRCP_QBP = "";
	 * 
	 * }
	 */

	/*
	 * Unsolicited vaccination record update
	 */
	public List<HL7FileGenerationResult> generateVXU_HL7(ImmRegVXUCriteria immRegQBPCriteria) {

		// String strHL7_Complete = "";

		try {

			// clearAllData();

			if (immRegQBPCriteria == null) {
				addFileGenerationResultToList((long) processing_id++, "", "ERROR",
						"File Generation Data is not provided.", "File Generation Data");

				return lstImmunizationFileGenerationResult;
			} else {
				practiceId = immRegQBPCriteria.getPractice_id();
				patientId = immRegQBPCriteria.getPatient_id();
				chartId = immRegQBPCriteria.getChart_id();
				loginUser = immRegQBPCriteria.getUser_name();
				clientIP = immRegQBPCriteria.getClient_ip();
				clinicID = immRegQBPCriteria.getClinic_id();
				isSubmitAfterGeneation = immRegQBPCriteria.isSubmit_after_geneation();
				lstChartImmunizationIds = immRegQBPCriteria.getLst_chart_immunization_ids();
			}

			if (immRegQBPCriteria == null || GeneralOperations.isNullorEmpty(practiceId)
					|| GeneralOperations.isNullorEmpty(patientId) || GeneralOperations.isNullorEmpty(chartId)
					|| GeneralOperations.isNullorEmpty(loginUser) || GeneralOperations.isNullorEmpty(clientIP)
					|| GeneralOperations.isNullorEmpty(clinicID)) {

				addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR",
						"One of the File Generation Data is not provided." + "\n Pracice Id" + ", Patient Id"
								+ ", Chart Id" + ", Loged In User Info" + ", Client IP" + ", Clinic Id",
						"File Generation Data");

				return lstImmunizationFileGenerationResult;
			}

			if (lstChartImmunizationIds == null || lstChartImmunizationIds.size() == 0) {
				addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR",
						"Chart Immunization ID(s) is/are not provided.", "File Generation Data");

				return lstImmunizationFileGenerationResult;
			}

			if (isSubmitAfterGeneation != null) {
				for (Long charImmId : lstChartImmunizationIds) {

					if (chartImmunizationIDs != "") {
						chartImmunizationIDs += ",";
					}
					chartImmunizationIDs += charImmId.toString();
				}
			}

			if (AssignRegistryInfo() == false) {

				addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR", "Registry Info Not Found.",
						"Registry Info");

				return lstImmunizationFileGenerationResult;
			}

			patientInfo = getPatientInfo(this.patientId);

			if (patientInfo == null) {
				addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR", "Patient Record Not Found.",
						"Patient Info");

				return lstImmunizationFileGenerationResult;
			}

			lstImmunizationForRegistryMessage = getChartImmunizationForRegistryMessage();
			if (lstImmunizationForRegistryMessage == null || lstImmunizationForRegistryMessage.isEmpty()) {

				// System.out.println("Immunization Record Not Found.");

				addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR", "Immunization Record Not Found.",
						"Immunization");

				return lstImmunizationFileGenerationResult;
			}

			if (isSubmitAfterGeneation) {
				MessageID = db.IDGenerator("immunization_registry_messages", practiceId);
			} else {
				MessageID = (long) 500101;// Default ID if dot not saving.
			}

			if (populateLists() == false) {

				addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR",
						"An Error Occured While populating Immunization Record.", "Immunization");

				return lstImmunizationFileGenerationResult;

			}

			if (lstImmunizationForRegistryMessage != null && lstImmunizationForRegistryMessage.size() > 0) {

				// patientInfo = getPatientInfo();
				// if (patientInfo == null) {
				// if (lstImmunizationFileGenerationResult == null ||
				// lstImmunizationFileGenerationResult.size() > 0) {
				//
				// addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR",
				// "Patient Record Not Found.",
				// "Patient Info");
				//
				// return lstImmunizationFileGenerationResult;
				// }
				// }

				if (Get_MSH(MessageID) == false) {

					addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR",
							"An Error Occuree while getting MSH Segment.", "MSH");

					return lstImmunizationFileGenerationResult;
				}

				if (!Get_PID()) {
					return lstImmunizationFileGenerationResult;
				}

				if (!Get_PD1()) {
					return lstImmunizationFileGenerationResult;
				}

				if (!Get_NK1()) {
					return lstImmunizationFileGenerationResult;
				}

				if (!validateVaccineData()) {
					return lstImmunizationFileGenerationResult;
				}

				strORCMain = "";
				int refRXA = 1;
				strORC = "";
				strRXA = "";
				strRXR = "";
				strOBX = "";

				for (ORMImmunizationForRegistryMessageGet chartImmunization : lstImmunizationForRegistryMessage) {

					if (Get_ORC(chartImmunization)) {
						strORCMain += strORC;
					} else {
						return lstImmunizationFileGenerationResult;
					}

					if (Get_RXA(chartImmunization)) {
						strORCMain += strRXA;
					} else {
						return lstImmunizationFileGenerationResult;
					}

					if (chartImmunization.getEntry_type().equalsIgnoreCase("administered")
							&& !chartImmunization.getCvx_code().equalsIgnoreCase("998")) {
						if (Get_RXR(chartImmunization)) {
							strORCMain += strRXR;
						} else {
							return lstImmunizationFileGenerationResult;
						}

						if (chartImmunization.getEntry_type().equalsIgnoreCase("administered")) {
							if (Get_OBX(chartImmunization, refRXA)) {
								strORCMain += strOBX;
							} else {
								return lstImmunizationFileGenerationResult;
							}
						}

					}
					refRXA++;
				}

				if (lstImmunizationFileGenerationResult == null || lstImmunizationFileGenerationResult.size() == 0) {

					String strHL7_Complete = strMSH + strPID + strPD1 + strNK1 + strORCMain;

					if (isSubmitAfterGeneation) {

						ORMImmunizationRegistryMessageSave objORM = new ORMImmunizationRegistryMessageSave();
						objORM.setMessage_id(MessageID);
						objORM.setPatient_id(patientId);
						objORM.setChart_id(chartId);
						objORM.setDate_created(date_current_yyyy_MM_dd_HH_mm_ss_String);
						objORM.setDate_modified(date_current_yyyy_MM_dd_HH_mm_ss_String);
						objORM.setSystem_ip(clientIP);
						objORM.setCreated_user(loginUser);
						objORM.setModified_user(loginUser);
						objORM.setRegistry_code(registryCode);
						objORM.setClinic_id(clinicID);
						objORM.setClinic_code(clinicCode);
						objORM.setMessage_status("AQ");
						objORM.setMessage_type("VXU"); // VXU ==> Unsolicited Vaccination Record Update
						objORM.setPractice_id(practiceId);
						objORM.setImmunization_message(strHL7_Complete);
						objORM.setMessage_sent_date(date_current_yyyy_MM_dd_HH_mm_ss_String);
						objORM.setMessage_sent_by(loginUser);

						String res = saveMU3RegistryMessage(objORM);

						if (res.split("~")[0].equalsIgnoreCase("SUCCESS")) {
							addFileGenerationResultToList(MessageID, "VXU", "SUCCESSFULL", strHL7_Complete,
									"Genereation File");
						} else {
							addFileGenerationResultToList(MessageID, "VXU", "ERROR",
									"An Error Occured While Saving Immunization Message", "File Genereation");
						}

					} else {

						addFileGenerationResultToList(MessageID, "VXU", "SUCCESSFULL", strHL7_Complete,
								"Genereation File");
					}
				}
			}

		} catch (Exception e) {

			addFileGenerationResultToList((long) processing_id++, "", "ERROR",
					"An Error Occured while generating File.", "File Genereation");

			e.printStackTrace();

			return lstImmunizationFileGenerationResult;
		}

		return lstImmunizationFileGenerationResult;

	}

	// MSH Segment
	private Boolean Get_MSH(Long generatedID) {

		try {

			// @formatter:off

			strMSH = // 1- MSH
					"MSH" + seperator + // 2- Encoding Characters
							encoding_characters + seperator;
			// 3- Sending Application (R)
			if (includeUIDs && GeneralOperations.isNotNullorEmpty(strSendingApplicationUID)) {
				strMSH += strSendingApplicationName + caret + strSendingApplicationUID + caret + UID_TYPE;
			} else {
				strMSH += strSendingApplicationName;
			}
			strMSH += seperator;
			// 4- Sending Facility (R)
			if (includeUIDs && GeneralOperations.isNotNullorEmpty(strSendingFacilityUID)) {
				strMSH += strSendingFacilityID + caret + strSendingFacilityUID + caret + UID_TYPE;
			} else {
				strMSH += strSendingFacilityID;
			}
			strMSH += seperator;
			// 5- Receiving Application (C)
			if (includeUIDs && GeneralOperations.isNotNullorEmpty(strReceivingApplicationUID)) {
				strMSH += strReceivingApplicationName + caret + strReceivingApplicationUID + caret + UID_TYPE;
			} else {
				strMSH += strReceivingApplicationName;
			}
			strMSH += seperator;
			// 6- Receiving Facility (C)
			if (includeUIDs && GeneralOperations.isNotNullorEmpty(strReceivingFacilityUID)) {
				strMSH += strReceivingFacility + caret + strReceivingFacilityUID + caret + UID_TYPE;
			} else {
				strMSH += strReceivingFacility;
			}
			strMSH += seperator + // 7- D/T of Message (O)
					DateTimeUtil.GetStringFromDate(date_current, DateFormatEnum.yyyyMMddHHmmssZZZZ) + seperator + // 8-
																													// Empty
					"" + seperator + // 9- Message Type (Type^Event) (R)
					"VXU" + caret + "V04" + caret + "VXU_V04" + seperator + // 10- Message Control ID (oerder ID) (R)
					generatedID.toString() + seperator + // 11- Processing ID (R)
					"P" + seperator + // 12- Version ID (R)
					"2.5.1" + seperator + // 13-
					"" + seperator + // 14-
					"" + seperator + // 15- Accept Acknowledgment Type
					"ER" + seperator
					// 16- Application Acknowledgment Type
					+ "AL" + seperator
					// 17- 18 - 19 -20
					+ seperator + seperator + seperator + seperator
					// 21- Message Profile Identifier
					+ "Z22^CDCPHINVS" + seperator;
			// 22- Sending Responsible Organization
			if (GeneralOperations.isNotNullorEmpty(strOrgAssAuth)) {
				// 22.1 - Organization Name
				// 22.6 - Assigning Authority
				// 22.7 - Identifier Type Code XX=Organization ID
				// 22.10 - Organization Identifier
				strMSH += strSendingFacilityID + caret + "" + caret + "" + caret + "" + caret + "" + caret
						+ strOrgAssAuth + "" + caret + "XX" + "" + caret + "" + caret + "" + caret + clinicID;
			} else {
				strMSH += strSendingFacilityID;
			}
			strMSH += seperator;
			// 23- Receiving Responsible Organization
			if (GeneralOperations.isNotNullorEmpty(strOrgAssAuth)) {
				strMSH += strReceivingFacility + caret + "" + caret + "" + caret + "" + caret + "" + caret
						+ strOrgAssAuth + "" + caret + "XX" + "" + caret + "" + caret + "" + caret + strRecOrgID;
			} else {
				strMSH += strReceivingFacility;
			}
			// 23.1 - Organization Name
			// 23.6 - Assigning Authority
			// 23.7 - Identifier Type Code XX=Organization ID
			// 23.10 - Organization Identifier

			// @formatter:on

			// System.err.println("MSH:" + strMSH);

		} catch (Exception e) {

			addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR", "Immunization Record Not Found.",
					"MSH");

			e.printStackTrace();

			return false;
		}
		return true;
	}

	// PID
	private Boolean Get_PID() {

		try {

			if (!validatePID()) {
				return false;
			}
			/*
			 * if (patientInfo == null) {
			 * 
			 * addFileGenerationResultToList((long) processing_id++, "", "ERROR",
			 * "Patient Record Not Found.", "Patient Info");
			 * 
			 * return false; }
			 */

			String ssn = patientInfo.getSsn();
			String GenderCode = patientInfo.getGender_code();
			if (GeneralOperations.isNullorEmpty(GenderCode)) {
				GenderCode = "U";
			}

			if (!(ssn == null)) {
				String[] a = ssn.split("-");

				ssn = "";
				for (String strSSNPart : a) {
					ssn = ssn + strSSNPart;
				}
			}

			List<ORMGetPatientRaceEthnicity> lstPatientRaceEthnicity = this.patientDAOImpl.getPatientRaceEthnicty("ALL",
					patientId);

			String raceSegment = "";
			String ethnicitySegment = "";

			for (ORMGetPatientRaceEthnicity objRaceEthnicity : (List<ORMGetPatientRaceEthnicity>) lstPatientRaceEthnicity) {

				if (objRaceEthnicity.getEntry_type().equalsIgnoreCase("RACE")) {

					if (GeneralOperations.isNotNullorEmpty(raceSegment)) {
						raceSegment += "~";
					}
					raceSegment += objRaceEthnicity.getOmb_code() + caret + objRaceEthnicity.getOmb_description()
							+ caret + "CDCREC";

				} else if (objRaceEthnicity.getEntry_type().equalsIgnoreCase("ETHNICITY")) {

					if (GeneralOperations.isNotNullorEmpty(ethnicitySegment)) {
						ethnicitySegment += "~";
					}
					ethnicitySegment += objRaceEthnicity.getOmb_code() + caret + objRaceEthnicity.getOmb_description()
							+ caret + "CDCREC";

				}
			}

			String Tel_Use_Code = "";
			String Tel_Equ_Type = "";
			String email_id = "";
			String area_code = "";
			String Local_number = "";
			String phone_number = "";

			// PRN Primary Residence Number
			// EMR Emergency Number
			// ORN Other Residence Number
			// WPN Work Phone Number
			// ASN Answering Service Number
			// VHN Vacation Home Number
			if (GeneralOperations.isNotNullorEmpty(patientInfo.getPrimary_phone())) {
				switch (patientInfo.getPrimary_phone().toUpperCase()) {

				case "HOME PHONE":
					if (GeneralOperations.isNotNullorEmpty(patientInfo.getHome_phone())) {

						phone_number = patientInfo.getHome_phone();
						Tel_Use_Code = "PRN"; // Primary Residence Number
						Tel_Equ_Type = "PH";
					}

					break;
				case "CELL PHONE":
					if (GeneralOperations.isNotNullorEmpty(patientInfo.getCell_phone())) {
						phone_number = patientInfo.getCell_phone();
						Tel_Use_Code = "PRN"; // Primary Residence Number
						Tel_Equ_Type = "CP";
					}
					break;
				case "WORK PHONE":
					if (GeneralOperations.isNotNullorEmpty(patientInfo.getWork_phone())) {
						phone_number = patientInfo.getWork_phone();
						Tel_Use_Code = "WPN"; // Work Place Number
						Tel_Equ_Type = "PH";
					}
					break;
				}
			}

			if (GeneralOperations.isNotNullorEmpty(patientInfo.getEmail())) {
				email_id = patientInfo.getEmail();
			}

			if (GeneralOperations.isNotNullorEmpty(phone_number)) {
				if (phone_number.length() > 3) {
					area_code = phone_number.substring(0, 3);
					Local_number = phone_number.substring(3, phone_number.length());
				}
			}

			// @formatter:off
			strPID = "\n" + "PID" + seperator + "1" + seperator + // 2- External Patient ID (Patient ID) (C)
					"" + seperator // empty
					+ // 3- Patient Identifier List Assigning Authority (Namespace ID^Universal
						// ID^Universal ID Type^ID Number Type) (C,r)
						// patient ID (MR -- > Medical Record Number)
					patientInfo.getPatient_id() + caret + "" + caret + "" + caret + strPIDAssignAuth + caret + "MR";

			// if SSN Exits also include SSN Identifier in Patient Identifier List (SS -->
			// SSN)
			if (GeneralOperations.isNotNullorEmpty(patientInfo.getSsn())) {
				strPID += "~" + patientInfo.getSsn() + caret + "" + caret + "" + caret + strPIDAssignAuth + caret
						+ "SS";
			}

			strPID += seperator// 4- Alternate Patient ID (C,r)
					+ "" + seperator
					// 5- Patient Name (Family^Given) (R)
					+ patientInfo.getLast_name() + caret + patientInfo.getFirst_name() + caret + patientInfo.getMname()
					+ caret + caret + caret + caret + "L" + seperator;
			// 6- Mother’s Maiden Name (O) -- XPN_M -- Extended Person Name – Maiden Name
			if (GeneralOperations.isNotNullorEmpty(patientInfo.getMother_maiden_first_name())
					|| GeneralOperations.isNotNullorEmpty(patientInfo.getMother_maiden_last_name())) {

				strPID += patientInfo.getMother_maiden_last_name() + caret + patientInfo.getMother_maiden_first_name()
						+ caret + caret + caret + caret + caret + "M";
			}
			strPID += seperator;

			// 7- Date of Birth (O)
			strPID += DateTimeUtil.GetStringFromDate(
					DateTimeUtil.GetDateFromString(patientInfo.getDob(), DateFormatEnum.MM_dd_yyyy),
					DateFormatEnum.yyyyMMdd) + seperator
			// dt_formater_yyyyMMdd.format((java.util.Date)
			// dtInput_formaterMMDDYYYY.parse(patientInfo.getDob())) + seperator
					+ // 8- Sex (O)
					GenderCode + seperator + // 9- Patient Alias (O)
					"" + seperator;

			// 10- Race (Identifier^Text^Name of Coding System)(O)
			if (GeneralOperations.isNotNullorEmpty(raceSegment)) {
				strPID += raceSegment;// patientInfo.getRace_code() + caret + patientInfo.getRace() + caret +
										// "CDCREC";
			}

			strPID += seperator + // 11- Patient Address (Address line 1^Address line 2^city^state^Zip code)(O)
					patientInfo.getAddress() + caret + patientInfo.getAddress2() + caret + patientInfo.getCity() + caret
					+ patientInfo.getState() + caret + patientInfo.getZip() + caret + addressCountryCode + caret + "L"
					+ seperator + // 12- Business Phone Number (O)
					"" + seperator + // 13.1- Home Phone Number (O)
					"" + caret + Tel_Use_Code + caret + Tel_Equ_Type + caret + caret + caret + area_code + caret
					+ Local_number;
			// 13.2- Email if exist (O)
			if (GeneralOperations.isNotNullorEmpty(email_id)) {
				strPID += "~" + caret + "NET" + caret + Tel_Equ_Type + caret + email_id + caret + caret + caret;
			}

			strPID += seperator
					// 14-
					+ "" + seperator + // 15-
					"" + seperator + // 16-
					"" + seperator + // 17-
					"" + seperator + // 18- (O)
					"" + seperator + // 19- (O)
					"" + seperator + // 20- (O)
					"" + seperator + // 21- (O)
					"" + seperator;

			// 22- Ethnic Group (Identifier^Text^Name of Coding System)
			if (GeneralOperations.isNotNullorEmpty(ethnicitySegment)) {
				strPID += ethnicitySegment;
			}
			strPID += seperator;

			// 23 -
			strPID += seperator;

			// 24 - Multiple Birth Indicator (ID) 00127
			if (GeneralOperations.isNotNullorEmpty(patientInfo.getMulti_birth())) {
				strPID += patientInfo.getMulti_birth();
			}
			strPID += seperator;

			// 25 - Patient birth order
			if (GeneralOperations.isNotNullorEmpty(patientInfo.getBirth_order())) {
				strPID += patientInfo.getBirth_order();
			}
			strPID += seperator;

			// 26,27,28,29 -
			strPID += seperator + seperator + seperator + seperator;

			// 30 - Patient Death Indicator
			strPID += "N";

			// @formatter:on

			// System.out.println("PID Segement: " + strPID);

		} catch (Exception e) {

			addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR",
					"Error Occured while getting PID Section", "PID");

			e.printStackTrace();

			return false;
		}
		return true;
	}

	// PD1
	private Boolean Get_PD1() {

		try {

			ORMGetPatImmRegInfoDisplay patientRegInfo = this.immunizationDAOImpl.getPatientImmRegInfo(patientId);

			if (patientRegInfo == null) {
				// System.out.println("Get_PD1: Patient Immunization Registry Info not Found.
				// ");
				return true;
			}

			if (GeneralOperations.isNullorEmpty(patientRegInfo.getPublicity_code())
					&& GeneralOperations.isNullorEmpty(patientRegInfo.getProtection_indicator())
					&& GeneralOperations.isNullorEmpty(patientRegInfo.getRegistry_status())) {
				strPD1 = "";
				return true;
			}

			String strProtectionEffectiveDate = "";
			String strStatusEffectiveDate = "";
			String strPublicityEffectiveDate = "";

			if (GeneralOperations.isNotNullorEmpty(patientRegInfo.getProtection_indicator_effective_date())
					&& !patientRegInfo.getProtection_indicator_effective_date().equalsIgnoreCase("01/01/1900")) {
				strProtectionEffectiveDate = DateTimeUtil.GetStringFromDate(
						DateTimeUtil.GetDateFromString(patientRegInfo.getProtection_indicator_effective_date(),
								DateFormatEnum.MM_dd_yyyy),
						DateFormatEnum.yyyyMMdd);
			}
			if (GeneralOperations.isNotNullorEmpty(patientRegInfo.getRegistry_status_effective_date())
					&& !patientRegInfo.getRegistry_status_effective_date().equalsIgnoreCase("01/01/1900")) {
				strStatusEffectiveDate = DateTimeUtil.GetStringFromDate(
						DateTimeUtil.GetDateFromString(patientRegInfo.getRegistry_status_effective_date(),
								DateFormatEnum.MM_dd_yyyy),
						DateFormatEnum.yyyyMMdd);
			}
			if (GeneralOperations.isNotNullorEmpty(patientRegInfo.getPublicity_code_effective_date())
					&& !patientRegInfo.getPublicity_code_effective_date().equalsIgnoreCase("01/01/1900")) {
				strPublicityEffectiveDate = DateTimeUtil.GetStringFromDate(
						DateTimeUtil.GetDateFromString(patientRegInfo.getPublicity_code_effective_date(),
								DateFormatEnum.MM_dd_yyyy),
						DateFormatEnum.yyyyMMdd);
			}

			// @formatter:off
			strPD1 = "\n" + "PD1" + seperator
			// 1- Set ID (C)
					+ "" + seperator + // 2-
					"" + seperator + // 3-
					"" + seperator + // 4-
					"" + seperator + // 5-
					"" + seperator + // 6-
					"" + seperator + // 7-
					"" + seperator + // 8-
					"" + seperator + // 9-
					"" + seperator + // 10-
					"" + seperator;

			// 11-Publicity Code
			if (GeneralOperations.isNotNullorEmpty(patientRegInfo.getPublicity_code())) {
				strPD1 += patientRegInfo.getPublicity_code() + caret + patientRegInfo.getPublicity_code_description()
						+ caret + "HL70215";
			}
			strPD1 += seperator;

			// 12- Protection Indicator
			strPD1 += patientRegInfo.getProtection_indicator() + seperator + // 13-
					strProtectionEffectiveDate + seperator + // 14-
					"" + seperator + // 15-
					"" + seperator + // 16- Immunization Registry Status
					patientRegInfo.getRegistry_status() + seperator + // 17- Immunization Registry Status Effective Date
					strStatusEffectiveDate + seperator + // 18- Publicity Code Effective Date
					strPublicityEffectiveDate;

			// @formatter:on

			// System.out.println("PD1: " + strPD1);
		} catch (Exception e) {

			addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR",
					"Error Occured while getting PD1 Section", "PD1");

			e.printStackTrace();

			return false;
		}
		return true;
	}

	// NK1
	private Boolean Get_NK1() {

		try {

			List<ORMGetPatientNextOfKin> lsNK = this.patientDAOImpl.getPatientNextOfKin(patientId);

			if (lsNK == null || lsNK.isEmpty()) {
				// System.out.println("Get_NK1: Patient Next of Kin Not Found. ");
				return true;
			}
			// ORMGetPatientNextOfKin objNK;
			int nk_no = 0;
			strNK1 = "";

			for (ORMGetPatientNextOfKin objNK : lsNK) {

				nk_no++;

				String area_code = "";
				String Local_number = "";
				String Tel_Equ_Type = "";

				if (GeneralOperations.isNotNullorEmpty(objNK.getCell_phone())) {
					if (objNK.getCell_phone().length() > 3) {
						area_code = objNK.getCell_phone().substring(0, 3);
					}
					Local_number = objNK.getCell_phone().substring(3, objNK.getCell_phone().length());

					Tel_Equ_Type = "CP";
				} else if (GeneralOperations.isNotNullorEmpty(objNK.getPhone())) {
					if (objNK.getPhone().length() > 3) {
						area_code = objNK.getPhone().substring(0, 3);
					}
					Local_number = objNK.getPhone().substring(3, objNK.getPhone().length());

					Tel_Equ_Type = "PH";

				}

				// @formatter:off
				strNK1 += "\n" + "NK1"
				// 1-
						+ "" + seperator + // 2- Set ID - NK1
						String.valueOf(nk_no) + seperator + // 3- Name(Family Name/Surname + Given Name + Second and
															// Further Given Names or Initials Thereof + Name Type Code)
						objNK.getLast_name() + caret + objNK.getFirst_name() + caret + objNK.getMname() + caret + caret
						+ caret + caret + "L" + seperator + // 4- Relationship (Identifier ^ Text ^ Name of the Coding
															// System)
						objNK.getRelationship_code() + caret + objNK.getRelationship_description() + caret + "HL70063"
						+ seperator;

				// 5- Address (Street or Mailing Address ^ Other Designation ^ City ^ State or
				// Province ^ Zip or Postal Code ^ Country ^ Address Type)
				if (GeneralOperations.isNotNullorEmpty(objNK.getAddress())
						&& GeneralOperations.isNotNullorEmpty(objNK.getCity())
						&& GeneralOperations.isNotNullorEmpty(objNK.getZip())
						&& GeneralOperations.isNotNullorEmpty(objNK.getCountry())) {
					strNK1 += objNK.getAddress() + caret + caret + objNK.getCity() + caret + objNK.getState() + caret
							+ objNK.getZip() + caret + objNK.getCountry() + caret + "L";
				}
				strNK1 += seperator;
				// 6- Phone Number ( ^ Telecommunication Use Code ^ Telecommunication Equipment
				// Type ^ Email Address ^ Area/City Code ^ Local Number)
				if (GeneralOperations.isNotNullorEmpty(area_code) && GeneralOperations.isNotNullorEmpty(Local_number)) {
					strNK1 += "" + caret + "PRN" + caret + Tel_Equ_Type + caret + caret + caret + area_code + caret
							+ Local_number;
				}

				// @formatter:on
			}

			// System.out.println("NK1: " + strNK1);
		} catch (Exception e) {

			addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR",
					"Error Occured while getting NK1 Section", "NK1");

			e.printStackTrace();

			return false;
		}
		return true;
	}

	// ORC
	private Boolean Get_ORC(ORMImmunizationForRegistryMessageGet chartImmunizaiton) {

		String entered_by_id = "";
		String entered_by_lname = "";
		String entered_by_fname = "";
		String entered_by_mname = "";
		String immDisplayName = GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getTrade_name())
				? chartImmunizaiton.getTrade_name()
				: chartImmunizaiton.getImmunization_name();

		try {

			// @formatter:off
			strORC = "\n" + "ORC" + seperator
			// 1- Order Control (R)
					+ "RE" + seperator;

			if (chartImmunizaiton.getCompletion_status_code().equalsIgnoreCase("RE")
					|| chartImmunizaiton.getCompletion_status_code().equalsIgnoreCase("NA")) {
				strORC += "" + seperator // 2- Placer Order number (Order ID) (C)
				// 3- Filler Order (C)
						+ "9999" + caret + strFilerPlacerAssignAuth;
				if (includeUIDs && GeneralOperations.isNotNullorEmpty(strFilerPlacerAssignAuth)
						&& GeneralOperations.isNotNullorEmpty(strFilerPlacerAssignAuthUID)) {
					strORC += caret + strFilerPlacerAssignAuthUID + caret + UID_TYPE;
				}
			} else {
				// 2- Placer Order number (Order ID) (C)
				strORC += chartImmunizaiton.getChart_immunization_id().toString() + caret + strFilerPlacerAssignAuth;
				if (includeUIDs && GeneralOperations.isNotNullorEmpty(strFilerPlacerAssignAuth)
						&& GeneralOperations.isNotNullorEmpty(strFilerPlacerAssignAuthUID)) {
					strORC += caret + strFilerPlacerAssignAuthUID + caret + UID_TYPE;
				}
				strORC += seperator
						// 3- Filler Order (C)
						+ chartImmunizaiton.getRegistry_msg_imm_id().toString() + caret + strFilerPlacerAssignAuth;
				if (includeUIDs && GeneralOperations.isNotNullorEmpty(strFilerPlacerAssignAuth)
						&& GeneralOperations.isNotNullorEmpty(strFilerPlacerAssignAuthUID)) {
					strORC += caret + strFilerPlacerAssignAuthUID + caret + UID_TYPE;
				}
			}
			strORC += seperator;

			// if (chartImmunizaiton.getAdministered_code().equalsIgnoreCase("00") &&
			// !chartImmunizaiton.getCvx_code().equalsIgnoreCase("998")) {
			if (!chartImmunizaiton.getCvx_code().equalsIgnoreCase("998")) {

				/*
				 * if
				 * (GeneralOperations.isNullorEmpty(chartImmunizaiton.getEntered_by_user_info())
				 * ) {
				 * 
				 * addFileGenerationResultToList((long) processing_id++,
				 * chartImmunizaiton.getTrade_name(), "ERROR",
				 * "First Name, Last Name of Entered User is required.", "ORC");
				 * 
				 * 
				 * } else
				 */
				{
					String[] enteredBy = chartImmunizaiton.getEntered_by_user_info().split("\\^");

					// System.out.println("Entered By Length : " + enteredBy.length + " Entered By:"
					// + chartImmunizaiton.getEntered_by_user_info());
					if (enteredBy.length >= 3) {
						entered_by_id = enteredBy[0];
						entered_by_lname = enteredBy[1];
						entered_by_fname = enteredBy[2];
						if (enteredBy.length > 3) {
							entered_by_mname = enteredBy[3];
						} else {
							entered_by_mname = "";
						}

					}
					// else {
					// addFileGenerationResultToList((long) processing_id++,
					// chartImmunizaiton.getTrade_name(), "ERROR", "Entered By User Details are
					// missing.", "ORC");
					// }
				}

				strORC += // 4-
						"" + seperator + // 5-
								"" + seperator + // 6-
								"" + seperator + // 7-
								"" + seperator + // 8-
								"" + seperator + // 9-
								"" + seperator + // 10- Entered By (ID Number ^ Family Name ^ Surname ^ Given Name ^
													// Second and Further Given Names or Initials Thereof ^ Assigning
													// Authority)
								entered_by_id + caret + entered_by_lname + caret + entered_by_fname + caret
								+ entered_by_mname + caret + "" + caret + "" + caret + "" + caret + "" + caret
								+ strClinicStaffAssignAuth + caret + "L" + caret + caret + caret + "PRN" + seperator + // 11-
								"" + seperator;

				// 12- Ordering Provider (ID Number ^ Family Name ^ Given Name ^ Second and
				// Further Given Names or Initials Thereof ^ Assigning Authority ^ Name Type
				// Code)
				if (chartImmunizaiton.getEntry_type().equalsIgnoreCase("administered")) {

					/*
					 * if (GeneralOperations.isNullorEmpty(chartImmunizaiton.getProvider_id()) ||
					 * GeneralOperations.isNullorEmpty(chartImmunizaiton.getProvider_last_name()) ||
					 * GeneralOperations.isNullorEmpty(chartImmunizaiton.getProvider_first_name()))
					 * {
					 * 
					 * 
					 * addFileGenerationResultToList((long) processing_id++,
					 * chartImmunizaiton.getTrade_name(), "ERROR",
					 * "Provider ID and/or Name is required.", "ORC");
					 * 
					 * lstImmunizationFileGenerationResult.add(immunizationFileGenerationResult); }
					 */

					if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getProvider_id())) {
						if (chartImmunizaiton.getProvider_id().toString().length() > 15) {
							strORC += chartImmunizaiton.getProvider_id().toString()
									.replace(chartImmunizaiton.getPractice_id().toString() + "101", "");
						} else {
							strORC += chartImmunizaiton.getProvider_id();
						}
						strORC += caret + chartImmunizaiton.getProvider_last_name() + caret
								+ chartImmunizaiton.getProvider_first_name() + caret
								+ chartImmunizaiton.getProvider_mname() + caret + "" + caret + "" + caret + "" + caret
								+ "" + caret + strClinicStaffAssignAuth + caret + "L" + caret + caret + caret + "MD";
					}
				}

				strORC += seperator;

				// 13,14,15,16
				strORC += seperator + seperator + seperator + seperator;

				// ORC- 17 -Entering Organization
				strORC += strSendingFacilityID + caret + strSendingFacilityName + caret + "HL70362";

				// @formatter:on
			}

		} catch (Exception e) {

			e.printStackTrace();

			addFileGenerationResultToList((long) processing_id++, immDisplayName, "ERROR",
					"Error Occured while getting ORC Section", "ORC");

			return false;
		}
		return true;
	}

	// RXA
	private Boolean Get_RXA(ORMImmunizationForRegistryMessageGet chartImmunizaiton) {

		String immDisplayName = GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getTrade_name())
				? chartImmunizaiton.getTrade_name()
				: chartImmunizaiton.getImmunization_name();
		try {

			String unit = "";
			String unitText = "";

			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getUnits())) {
				unit = chartImmunizaiton.getUnits();

				switch (unit.toLowerCase()) {
				case "ml":
					unitText = "MilliLiter";
					unit = "mL";
					break;
				case "mg":
					unitText = "MilliGram";
					break;
				case "cc":
					unitText = "CubicCentimeter";

					// addFileGenerationResultToList((long) processing_id++,
					// chartImmunizaiton.getTrade_name(), "ERROR",
					// "Invalid Unit Dosage Unit CC.", "RXA");

					break;
				case "oz":
					unitText = "Ounce";
					break;
				}
			}
			String expiration_date = "";
			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getExpiration_date())
					&& !chartImmunizaiton.getExpiration_date().startsWith("1900-01-01")) {
				expiration_date = DateTimeUtil
						.GetStringFromDate(DateTimeUtil.GetDateFromString(chartImmunizaiton.getExpiration_date(),
								DateFormatEnum.yyyy_MM_dd_HH_mm_ss_SSS), DateFormatEnum.yyyyMMdd);
			}

			String dateAdministered = "";
			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getDatetime_administered())
					&& !chartImmunizaiton.getDatetime_administered().startsWith("1900-01-01")) {
				dateAdministered = DateTimeUtil
						.GetStringFromDate(DateTimeUtil.GetDateFromString(chartImmunizaiton.getDatetime_administered(),
								DateFormatEnum.yyyy_MM_dd_HH_mm_ss_SSS), DateFormatEnum.yyyyMMdd);
			}

			// @formatter:off
			strRXA = "\n" + "RXA" + seperator
			// 1- Give Sub‐ID Counter
					+ "0" + seperator // 2- Administration Sub‐ID Counter
					+ "1" + seperator + dateAdministered // 3- Date/Time Start of Administration
					+ seperator + dateAdministered // 4- Date/Time End of Administration
					+ seperator;

			// 5- Administered Code Identifier^Text^Name of Coding System
			if (chartImmunizaiton.getEntry_type().equalsIgnoreCase("administered")) {

				if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getNdc_code())
						&& GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getTrade_name())) {
					strRXA += chartImmunizaiton.getNdc_code() + caret + chartImmunizaiton.getTrade_name() + caret
							+ "NDC";
				}
				/*
				 * else { if (GeneralOperations.isNullorEmpty(chartImmunizaiton.getNdc_code()))
				 * {
				 * 
				 * addFileGenerationResultToList((long) processing_id++,
				 * chartImmunizaiton.getTrade_name(), "ERROR", "NDC Code Missing.", "RXA");
				 * 
				 * } if (GeneralOperations.isNullorEmpty(chartImmunizaiton.getTrade_name())) {
				 * 
				 * addFileGenerationResultToList((long) processing_id++,
				 * chartImmunizaiton.getTrade_name(), "ERROR",
				 * "Trade Name or Coding System is missing.", "RXA");
				 * 
				 * } }
				 */
			} else {

				if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getCvx_code())) {
					strRXA += chartImmunizaiton.getCvx_code() + caret + chartImmunizaiton.getImmunization_name() + caret
							+ "CVX";
				}
				/*
				 * else {
				 * 
				 * addFileGenerationResultToList((long) processing_id++,
				 * chartImmunizaiton.getTrade_name(), "ERROR", "CVX Code Missing.", "RXA");
				 * 
				 * }
				 */
				if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getTrade_name())
						&& GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getTrade_coding_system())) {
					strRXA += caret + chartImmunizaiton.getTrade_name() + caret
							+ chartImmunizaiton.getTrade_description() + caret
							+ chartImmunizaiton.getTrade_coding_system();
				}

			}

			strRXA += seperator;

			// 6- Administered Amount
			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getDose())) {

				try {
					strRXA += Float.parseFloat(chartImmunizaiton.getDose());
				} catch (NumberFormatException e) {

					// addFileGenerationResultToList((long) processing_id++,
					// chartImmunizaiton.getTrade_name(), "ERROR", "Dose is invalid for CVX Code.",
					// "RXA");

				}

			} else {
				strRXA += "999";
			}
			strRXA += seperator;

			// 7- Administered Units (ml, etc) Identifier^Text^Name of Coding System
			if (chartImmunizaiton.getEntry_type().equalsIgnoreCase("administered")) {
				// if (chartImmunizaiton.getAdministered_code().equalsIgnoreCase("00")) {
				if (GeneralOperations.isNotNullorEmpty(unit)) {
					strRXA += unit + caret + unitText + caret + "UCUM";
				}
				// else if (chartImmunizaiton.getCvx_code().equalsIgnoreCase("998")) {
				// strRXA += seperator;
				// }
				// else {

				// addFileGenerationResultToList((long) processing_id++,
				// chartImmunizaiton.getTrade_name(), "ERROR", "Administered Unit is required.",
				// "RXA");

				// }
			}
			strRXA += seperator;

			strRXA += // 8- empty
					"" + seperator;
			// 9- Administration Notes

			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getAdministered_code())
					&& !chartImmunizaiton.getCvx_code().equalsIgnoreCase("998")
					&& !chartImmunizaiton.getCompletion_status_code().equalsIgnoreCase("RE")) {
				strRXA += chartImmunizaiton.getAdministered_code() + caret
						+ chartImmunizaiton.getAdministered_code_description() + caret + "NIP001";
			}
			strRXA += seperator;

			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getAdministering_user_info())
					&& !chartImmunizaiton.getCvx_code().equalsIgnoreCase("998")) {

				String Administered_id = "";
				String Administered_lname = "";
				String Administered_fname = "";
				String Administered_mname = "";

				String[] AdministeredBy = chartImmunizaiton.getAdministering_user_info().split("\\^");

				if (AdministeredBy.length >= 3) {
					Administered_id = AdministeredBy[0];
					Administered_lname = AdministeredBy[1];
					Administered_fname = AdministeredBy[2];

					if (AdministeredBy.length > 3) {
						Administered_mname = AdministeredBy[3];
					} else {
						Administered_mname = "";
					}

					strRXA += // 10- Administering Provider / user
							Administered_id + caret + Administered_lname + caret + Administered_fname + caret
									+ Administered_mname + caret + "" + caret + "" + caret + "" + caret + "" + caret
									+ strClinicStaffAssignAuth + caret + "L" + caret + "" + caret + "" + caret + "PRN"
									+ seperator
									// 11- Administered-at Location (Invetory Site ID)
									+ "" + caret + "" + caret + "" + caret + strAdministeredAtLocationID + seperator;
				} else {
					strRXA += seperator + seperator;
				}
			} else {
				strRXA += seperator + seperator;
			}

			// 12-
			strRXA += "" + seperator + // 13- empty
					"" + seperator + // 14- empty
					"" + seperator;
			// 15- Substance Lot Numbers
			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getLot_number())
					&& !chartImmunizaiton.getCvx_code().equalsIgnoreCase("998")) {
				strRXA += chartImmunizaiton.getLot_number();
			}
			strRXA += seperator;

			// 16- Substance Expiration Date
			if (GeneralOperations.isNotNullorEmpty(expiration_date)
					&& !chartImmunizaiton.getCvx_code().equalsIgnoreCase("998")) {
				strRXA += expiration_date;
			}
			strRXA += seperator;

			// 17- Substance Manufacturer Name Identifier^Text^Name of Coding System
			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getMvx_code())
					&& !chartImmunizaiton.getCvx_code().equalsIgnoreCase("998")) {
				strRXA += chartImmunizaiton.getMvx_code() + caret + chartImmunizaiton.getManufacturer() + caret + "MVX"
						+ seperator;
			} else {
				strRXA += seperator;
			}
			// 18- Substance/Treatment Refusal Reason
			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getEntry_type())
					&& chartImmunizaiton.getEntry_type().equalsIgnoreCase("Refused")
					&& GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getReason_code_cdc())
					&& !chartImmunizaiton.getCvx_code().equalsIgnoreCase("998")) {
				strRXA += chartImmunizaiton.getReason_code_cdc() + caret + chartImmunizaiton.getReason_description()
						+ caret + "NIP002";
			}
			strRXA += seperator;
			// 19- empty
			strRXA += "" + seperator;
			// 20- Completion Status // CP--> Copleted , PA--> Partially Administered , RE
			// --> Refused. , NA --> Not Administered
			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getCompletion_status_code())) {
				strRXA += chartImmunizaiton.getCompletion_status_code();
			}
			// System.out.println(chartImmunizaiton.getCompletion_status_code());
			strRXA += seperator;
			// 21- Action Code
			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getAction_code())) {
				strRXA += chartImmunizaiton.getAction_code();
			} else {
				strRXA += "A";
			}

			// @formatter:on

		} catch (Exception e) {

			e.printStackTrace();

			addFileGenerationResultToList((long) processing_id++, immDisplayName, "ERROR",
					"Error Occured while getting RXA Section.", "RXA");

			return false;
		}
		return true;
	}

	// RXA
	private Boolean Get_RXR(ORMImmunizationForRegistryMessageGet chartImmunizaiton) {

		String immDisplayName = GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getTrade_name())
				? chartImmunizaiton.getTrade_name()
				: chartImmunizaiton.getImmunization_name();
		try {

			if (chartImmunizaiton.getEntry_type().equalsIgnoreCase("Historical")
					|| chartImmunizaiton.getCvx_code().equalsIgnoreCase("998")) {
				strRXR = "";
				return true;
			}

			if (GeneralOperations.isNullorEmpty(chartImmunizaiton.getRoute_code())
					&& GeneralOperations.isNullorEmpty(chartImmunizaiton.getRoute_description())) {
				strRXR = "";
				return true;
			}

			/*
			 * if ((chartImmunizaiton.getCompletion_status_code().equalsIgnoreCase("CP") ||
			 * chartImmunizaiton.getCompletion_status_code().equalsIgnoreCase("PA")) &&
			 * (GeneralOperations.isNullorEmpty(chartImmunizaiton.getRoute_code()) ||
			 * GeneralOperations.isNullorEmpty(chartImmunizaiton.getRoute_description()))) {
			 * 
			 * addFileGenerationResultToList((long) processing_id++,
			 * chartImmunizaiton.getTrade_name(), "ERROR", "Route is missing.", "RXR");
			 * 
			 * return false;
			 * 
			 * }
			 */
			// @formatter:off
			strRXR = "\n" + "RXR" + seperator
			// 1- Route (Identifier ^ Text ^ Name of Coding System)
					+ chartImmunizaiton.getRoute_code() + caret + chartImmunizaiton.getRoute_description() + caret
					+ chartImmunizaiton.getRoute_code_system();// "NCIT";
			// 2- Administration Site (Identifier ^ Text ^ Name of Coding)

			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getSite_code())
					&& GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getSite_description())) {
				strRXR += seperator + chartImmunizaiton.getSite_code() + caret + chartImmunizaiton.getSite_description()
						+ caret + "HL70163";
			}

			// @formatter:on
		} catch (Exception e) {

			e.printStackTrace();

			addFileGenerationResultToList((long) processing_id++, immDisplayName, "ERROR",
					"Error Occured while getting RXR Section.", "RXR");

			return false;
		}
		return true;
	}

	// OBX
	private Boolean Get_OBX(ORMImmunizationForRegistryMessageGet chartImmunizaiton, int rxa_sgement_id) {

		String immDisplayName = GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getTrade_name())
				? chartImmunizaiton.getTrade_name()
				: chartImmunizaiton.getImmunization_name();

		try {

			strOBX = "";
			int obx_index = 1;
			int obx_group_id = 1;

			if (chartImmunizaiton.getAdministered_code().equalsIgnoreCase("00")
					&& !chartImmunizaiton.getCvx_code().equalsIgnoreCase("998")) {

				List<ORMChartImunizationVISGet> lstVIS = this.encounterDAOImpl
						.getChartImmunizationVIS(chartImmunizaiton.getChart_immunization_id());

				String vfc_code = "";
				String vfc_description = "";

				if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getVfc_code())) {
					vfc_code = chartImmunizaiton.getVfc_code();
					vfc_description = chartImmunizaiton.getVfc_description();
				}
				String vec_date_completed = "";
				if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getDatetime_administered())
						&& !chartImmunizaiton.getDatetime_administered().contains("1900-01-01")) {

					vec_date_completed = DateTimeUtil.GetStringFromDate(
							DateTimeUtil.GetDateFromString(chartImmunizaiton.getDatetime_administered(),
									DateFormatEnum.yyyy_MM_dd_HH_mm_ss_SSS),
							DateFormatEnum.yyyyMMdd);
				}

				// ADD PURCHASED WITH for Inventor (WIR) (Funding Program required)
				if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getFunding_code())
						&& GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getFunding_description())) {

					// @formatter:off
					strOBX += "\n" + "OBX" + seperator + // 1- Set ID - OBX
							obx_index + seperator + // 2- Value Type
							"CE" + seperator + // 3- Observation Identifier (Identifier^Text^Name of the Coding System)
							"30963-3" + caret + "Vaccine Funding Source" + caret + "LN" + seperator + // 4- Observation
																										// Sub-ID
							obx_group_id + seperator + // 5- Date/Time of the Observation
							chartImmunizaiton.getFunding_code() + caret + chartImmunizaiton.getFunding_description()
							+ caret + chartImmunizaiton.getFunding_coding_system() + seperator + // 6- Units
							"" + seperator + // 7-
							"" + seperator + // 8-
							"" + seperator + // 9-
							"" + seperator + // 10-
							"" + seperator + // 11- Observation Result Status
							"F" + seperator
							// 12,13
							+ seperator + seperator
							// 14 Date/Time of the Observation
							+ vec_date_completed;

					// @formatter:on

					obx_index++;
					obx_group_id++;
				}
				// else {

				// addFileGenerationResultToList((long) processing_id++,
				// chartImmunizaiton.getTrade_name(), "ERROR",
				// "Vaccine Funding Source is required.", "OBX");
				// }

				if (GeneralOperations.isNotNullorEmpty(vfc_code)) {

					String eligibility_captured_code = "VXC40";
					String eligibility_captured_text = "Eligibility captured at the Immunization level";

					// @formatter:off
					strOBX += "\n" + "OBX" + seperator + // 1- Set ID - OBX
							obx_index + seperator + // 2- Value Type
							"CE" + seperator + // 3- Observation Identifier (Identifier^Text^Name of the Coding System)
							"64994-7" + caret + "Vaccine Funding Program Eligibility" + caret + "LN" + seperator + // 4-
																													// Observation
																													// Sub-ID
							obx_group_id + seperator + // 5- Observation Value (Identifier^Text^Name of the Coding
														// System)
							vfc_code + caret + vfc_description + caret + "HL70064" + seperator + // 6- Units
							"" + seperator + // 7-
							"" + seperator + // 8-
							"" + seperator + // 9-
							"" + seperator + // 10-
							"" + seperator + // 11- Observation Result Status
							"F" + seperator + // 12-
							"" + seperator + // 13-
							"" + seperator + // 14- Date/Time of the Observation
							vec_date_completed + seperator + // 15-
							"" + seperator + // 16-
							"" + seperator + // 17- Observation Method (Identifier^Text^Name of the Coding System)
							eligibility_captured_code + caret + eligibility_captured_text + caret + "CDCPHINVS";
					// @formatter:on
					obx_index++;
					obx_group_id++;
				}

				if (lstVIS != null && lstVIS.size() > 0) {
					// String veccine_group_cvx;
					// String veccine_type;
					// String VIS_date_published;
					String VIS_date_presented;

					for (ORMChartImunizationVISGet vis : lstVIS) {

						VIS_date_presented = DateTimeUtil.GetStringFromDate(DateTimeUtil
								.GetDateFromString(vis.getVis_date_presented(), DateFormatEnum.yyyy_MM_dd_HH_mm_ss_SSS),
								DateFormatEnum.yyyyMMdd);

						// Document Type
						if (vis.getCoding_system().equalsIgnoreCase("cdcgs1vis")) {

							String vis_encoded_text = vis.getVis_encoded_text();
							String vis_name = vis.getVis_name();
							// @formatter:off
							strOBX += "\n" + "OBX" + seperator + // 1- Set ID - OBX
									obx_index + seperator + // 2- Value Type
									"CE" + seperator + // 3- Observation Identifier (Identifier^Text^Name of the Coding
														// System)
									"69764-9" + caret + "Document Type" + caret + "LN" + seperator + // 4- Observation
																										// Sub-ID
									obx_group_id + seperator + // 5- Observation Value (Identifier^Text^Name of the
																// Coding System)
									vis_encoded_text + caret + vis_name + caret + "cdcgs1vis" + seperator + // 6- Units
									"" + seperator + // 7-
									"" + seperator + // 8-
									"" + seperator + // 9-
									"" + seperator + // 10-
									"" + seperator
									// 11- Observation Result Status
									+ "F" + seperator
									// 12-
									+ seperator
									// 13-
									+ seperator
									// 14- Date/Time of the Observation
									+ vec_date_completed;
							// @formatter:on
							obx_index++;
						} else if (vis.getCoding_system().equalsIgnoreCase("CVX")) {

							/*
							 * veccine_group_cvx = vis.getImmunization_group_cvx_code() == null ? "" :
							 * vis.getImmunization_group_cvx_code(); veccine_type =
							 * vis.getImmunization_group_name() == null ? "" :
							 * vis.getImmunization_group_name();
							 * 
							 * if
							 * (GeneralOperations.isNotNullorEmpty(vis.getImmunization_group_description()))
							 * { veccine_type = veccine_type + " ( " +
							 * vis.getImmunization_group_description() + " ) "; }
							 * 
							 * if (GeneralOperations.isNotNullorEmpty(vis.getVis_date_published()) &&
							 * !vis.getVis_date_published().equalsIgnoreCase("01/01/1900")) {
							 * VIS_date_published = dt_formater_yyyyMMdd.format((java.util.Date)
							 * dtInput_formaterMMDDYYYY.parse(vis.getVis_date_published())); }
							 * 
							 * if (GeneralOperations.isNotNullorEmpty(vis.getVis_date_presented()) &&
							 * !vis.getVis_date_presented().equalsIgnoreCase("01/01/1900")) {
							 * VIS_date_presented = dt_formater_yyyyMMdd.format((java.util.Date)
							 * dtInput_formaterMMDDYYYY.parse(vis.getVis_date_presented())); }
							 * 
							 * //obx_group_id=obx_index;
							 * 
							 * // @formatter:off strOBX += "\n" + "OBX" + seperator +// 1- Set ID - OBX
							 * obx_index + seperator + // 2- Value Type "CE" + seperator + // 3- Observation
							 * Identifier (Identifier^Text^Name of the Coding System) "30956-7" + caret +
							 * "Vaccine Type" + caret + "LN" + seperator + // 4- Observation Sub-ID
							 * obx_group_id + seperator + // 5- Observation Value (Identifier^Text^Name of
							 * the Coding System) veccine_group_cvx + caret + veccine_type + caret + "CVX";
							 * 
							 * strOBX += seperator;
							 * 
							 * strOBX += // 6- Units "" + seperator + // 7- "" + seperator + // 8- "" +
							 * seperator + // 9- "" + seperator + // 10- "" + seperator + // 11- Observation
							 * Result Status "F";
							 * 
							 * obx_index++;
							 * 
							 * strOBX += "\n" + "OBX" + seperator +// 1- Set ID - OBX obx_index + seperator
							 * + // 2- Value Type "TS" + seperator + // 3- Observation Identifier
							 * (Identifier^Text^Name of the Coding System) "29768-9" + caret +
							 * "Date vaccine information statement published" + caret + "LN" + seperator +
							 * // 4- Observation Sub-ID obx_group_id + seperator + // 5- Date/Time of the
							 * Observation VIS_date_published + seperator + // 6- Units "" + seperator + //
							 * 7- "" + seperator + // 8- "" + seperator + // 9- "" + seperator + // 10- "" +
							 * seperator + // 11- Observation Result Status "F"; // @formatter:on
							 * obx_index++;
							 */
						}
						// Date VIS Presented.

						// @formatter:off

						strOBX += "\n" + "OBX" + seperator + // 1- Set ID - OBX
								obx_index + seperator + // 2- Value Type
								"DT" + seperator + // 3- Observation Identifier (Identifier^Text^Name of the Coding
													// System)
								"29769-7" + caret + "Date VIS Presented" + caret + "LN" + seperator + // 4- Observation
																										// Sub-ID
								obx_group_id + seperator + // 5- Date/Time of the Observation
								VIS_date_presented + seperator + // 6- Units
								"" + seperator + // 7-
								"" + seperator + // 8-
								"" + seperator + // 9-
								"" + seperator + // 10-
								"" + seperator
								// 11- Observation Result Status
								+ "F" + seperator
								// 12-
								+ seperator
								// 13-
								+ seperator
								// 14- Date/Time of the Observation
								+ vec_date_completed;

						// @formatter:on
						obx_index++;
						obx_group_id++;
					}
				}
			}

			// ADD Disease with presumed immunity, if entered.
			if (GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getReason_type())
					&& chartImmunizaiton.getReason_type().equalsIgnoreCase("IMMUNITY")
					&& GeneralOperations.isNotNullorEmpty(chartImmunizaiton.getReason_code_snomed())) {

				// @formatter:off
				strOBX += "\n" + "OBX" + seperator + // 1- Set ID - OBX
						obx_index + seperator + // 2- Value Type
						"CE" + seperator + // 3- Observation Identifier (Identifier^Text^Name of the Coding System)
						"59784-9" + caret + "Disease with presumed immunity" + caret + "LN" + seperator + // 4-
																											// Observation
																											// Sub-ID
						obx_group_id + seperator + // 5- Date/Time of the Observation
						chartImmunizaiton.getReason_code_snomed() + caret + chartImmunizaiton.getReason_description()
						+ caret + "SCT" + seperator // SCT --> Snomed CT
						+ // 6- Units
						"" + seperator + // 7-
						"" + seperator + // 8-
						"" + seperator + // 9-
						"" + seperator + // 10-
						"" + seperator + // 11- Observation Result Status
						"F";

				// @formatter:on
				obx_index++;
				obx_group_id++;
			}

		} catch (Exception e) {

			e.printStackTrace();

			addFileGenerationResultToList((long) processing_id++, immDisplayName, "ERROR",
					"Error Occured while getting OBX Section.", "OBX");
			return false;
		}
		return true;
	}

	private Boolean populateLists() {

		// if (isSubmitAfterGeneation) {
		// MessageID = db.IDGenerator("immunization_registry_messages", practiceId);
		// } else {
		// MessageID = (long) 500101;// Default ID if dot not saving.
		// }

		// lstImmunizationForRegistryMessage = getChartImmunizationForRegistryMessage();
		//
		// if (lstImmunizationForRegistryMessage == null ||
		// lstImmunizationForRegistryMessage.isEmpty()) {
		//
		// //System.out.println("Immunization Record Not Found.");
		//
		// immunizationFileGenerationResult = new ImmunizationFileGenerationResult();
		// immunizationFileGenerationResult.setId((long) processing_id++);
		// immunizationFileGenerationResult.setType("ERROR");
		// immunizationFileGenerationResult.setMessage("Immunization Record Not
		// Found.");
		// immunizationFileGenerationResult.setSegment("Immunization");
		//
		// if (lstImmunizationFileGenerationResult == null) {
		// lstImmunizationFileGenerationResult = new ArrayList<>();
		// }
		// lstImmunizationFileGenerationResult.add(immunizationFileGenerationResult);
		// return false;
		// }

		Long msgImmId = Long.parseLong("500101"); // default if not saving in db
		if (isSubmitAfterGeneation) {
			msgImmId = db.IDGenerator("imm_reg_message_immunizations", practiceId,
					lstImmunizationForRegistryMessage.size());
		}

		for (ORMImmunizationForRegistryMessageGet ormImm : (List<ORMImmunizationForRegistryMessageGet>) lstImmunizationForRegistryMessage) {

			ormImm.setRegistry_msg_imm_id(msgImmId);

			ORMImmRegMessageImmunizationSave ormMessageImmSave = new ORMImmRegMessageImmunizationSave();
			ormMessageImmSave.setRegistry_msg_imm_id(msgImmId);
			ormMessageImmSave.setMessage_id(MessageID);// MESSAGE ID

			ormMessageImmSave.setChart_imm_id(ormImm.getChart_immunization_id());
			ormMessageImmSave.setPatient_id(ormImm.getPatient_id());
			ormMessageImmSave.setPractice_id(ormImm.getPractice_id());
			ormMessageImmSave.setDate_administered(ormImm.getDatetime_administered());
			ormMessageImmSave.setEntry_type(ormImm.getEntry_type());
			ormMessageImmSave.setAdministered_code_description(ormImm.getAdministered_code_description());
			ormMessageImmSave.setRoute(ormImm.getRoute_description());
			ormMessageImmSave.setSite(ormImm.getSite_description());
			if (GeneralOperations.isNotNullorEmpty(ormImm.getNdc_code())) {
				ormMessageImmSave.setImm_code(ormImm.getNdc_code());
				ormMessageImmSave.setImm_code_type("NDC");
			} else {
				ormMessageImmSave.setImm_code(ormImm.getCvx_code());
				ormMessageImmSave.setImm_code_type("CVX");
			}

			ormMessageImmSave.setLot_no(ormImm.getLot_number());
			ormMessageImmSave.setExpirty_date(ormImm.getExpiration_date());
			ormMessageImmSave.setMvx_code(ormImm.getMvx_code());
			ormMessageImmSave.setManufacturer(ormImm.getManufacturer());
			if (GeneralOperations.isNotNullorEmpty(ormImm.getTrade_name())) {
				ormMessageImmSave.setImm_name(ormImm.getTrade_name());
			} else {
				ormMessageImmSave.setImm_name(ormImm.getImmunization_name());
			}

			ormMessageImmSave.setDose(ormImm.getDose());
			ormMessageImmSave.setUnits(ormImm.getUnits());

			ormMessageImmSave.setAction_code(ormImm.getAction_code());
			ormMessageImmSave.setReason_description(ormImm.getReason_description());
			ormMessageImmSave.setDate_created(date_current_yyyy_MM_dd_HH_mm_ss_String);
			ormMessageImmSave.setDate_modified(date_current_yyyy_MM_dd_HH_mm_ss_String);
			ormMessageImmSave.setCreated_user(loginUser);
			ormMessageImmSave.setModified_user(loginUser);

			if (lstImmRegMessageImmunizationSave == null) {
				lstImmRegMessageImmunizationSave = new ArrayList<>();
			}

			// add to list and save when complete message generates without any error alog
			// with the message.
			lstImmRegMessageImmunizationSave.add(ormMessageImmSave);

			msgImmId++;

		}
		return true;
	}

	private Boolean AssignRegistryInfo() {

		try {

			ORMImmunizationRegistryInfoGet registryInfo = getRegistryInfo();

			if (registryInfo != null) {

				includeUIDs = registryInfo.getInclude_uid();
				strRecOrgID = registryInfo.getRec_org_id();
				strReceivingFacility = registryInfo.getRec_facility_name();
				strReceivingApplicationName = registryInfo.getRec_app_name();

				strSendingFacilityID = registryInfo.getClinic_code();
				strSendingFacilityName = registryInfo.getClinic_name();
				clinicCode = registryInfo.getClinic_code();

				registryCode = registryInfo.getRegistry_code();

				strSendingFacilityUID = registryInfo.getSending_facility_uid();

				strReceivingFacilityUID = registryInfo.getRec_facility_uid();

				strReceivingApplicationName = registryInfo.getRec_app_name();
				strReceivingApplicationUID = registryInfo.getRec_app_uid();

				strSendingApplicationName = registryInfo.getSending_app();
				strSendingApplicationUID = registryInfo.getSending_app_uid();

				if (GeneralOperations.isNotNullorEmpty(registryInfo.getOrg_assign_authority())) {

					strOrgAssAuth = registryInfo.getOrg_assign_authority();
					if (includeUIDs && GeneralOperations.isNotNullorEmpty(registryInfo.getOrg_assign_authority_uid())) {
						strOrgAssAuth += ampersand + registryInfo.getOrg_assign_authority_uid() + ampersand + UID_TYPE;
					}
				}

				strAdministeredAtLocationID = registryInfo.getInventory_site_id();
				if (includeUIDs && GeneralOperations.isNotNullorEmpty(strAdministeredAtLocationID)) {
					if (GeneralOperations.isNotNullorEmpty(strSendingFacilityUID)) {
						strAdministeredAtLocationID += ampersand + strSendingFacilityUID + ampersand + UID_TYPE;
					}
				}

				if (GeneralOperations.isNotNullorEmpty(registryInfo.getPid_assign_auth())) {

					strPIDAssignAuth = registryInfo.getPid_assign_auth();
					if (includeUIDs && GeneralOperations.isNotNullorEmpty(registryInfo.getPid_assign_auth_uid())) {
						strPIDAssignAuth += ampersand + registryInfo.getPid_assign_auth_uid() + ampersand + UID_TYPE;
					}
				}

				if (GeneralOperations.isNotNullorEmpty(registryInfo.getFiler_placer_assign_auth())) {
					strFilerPlacerAssignAuth = registryInfo.getFiler_placer_assign_auth();

					if (includeUIDs
							&& GeneralOperations.isNotNullorEmpty(registryInfo.getFiler_placer_assign_auth_uid())) {
						strFilerPlacerAssignAuthUID = registryInfo.getFiler_placer_assign_auth_uid();
					}
				}

				if (GeneralOperations.isNotNullorEmpty(registryInfo.getClinic_staff_assign_auth())) {

					strClinicStaffAssignAuth = registryInfo.getClinic_staff_assign_auth();
					if (includeUIDs
							&& GeneralOperations.isNotNullorEmpty(registryInfo.getClinic_staff_assign_auth_uid())) {
						strClinicStaffAssignAuth += ampersand + registryInfo.getClinic_staff_assign_auth_uid()
								+ ampersand + UID_TYPE;
					}
				}

			} else {
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

		return true;

	}

	private List<ORMImmunizationForRegistryMessageGet> getChartImmunizationForRegistryMessage() {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_immunization_ids", chartImmunizationIDs.toString(), String.class,
				ParameterMode.IN));
		return db.getStoreProcedureData("spGetImmunizationForRegistryMessage",
				ORMImmunizationForRegistryMessageGet.class, lstParam);

	}

	private ORMImmunizationRegistryInfoGet getRegistryInfo() {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("clinic_id", clinicID.toString(), String.class, ParameterMode.IN));
		List<ORMImmunizationRegistryInfoGet> lst = db.getStoreProcedureData("spGetImmunizatioRegistryInfo",
				ORMImmunizationRegistryInfoGet.class, lstParam);

		if (lst != null && lst.size() > 0)
			return lst.get(0);

		else
			return null;

	}

	private ORMImmRegPathGet GetRegistryPath(Long pracId, String regCode) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", pracId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("registry_code", regCode.toString(), String.class, ParameterMode.IN));
		List<ORMImmRegPathGet> lst = db.getStoreProcedureData("spGetImmunizatioRegistryPath", ORMImmRegPathGet.class,
				lstParam);

		if (lst != null && lst.size() > 0)
			return lst.get(0);

		else
			return null;

	}

	private ORMImmunizationRegistryMessagePatientInfoGet getPatientInfo(Long patId) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patId.toString(), String.class, ParameterMode.IN));
		List<ORMImmunizationRegistryMessagePatientInfoGet> lst = db.getStoreProcedureData(
				"spGetImmunizationRegistryMessagePatientInfo", ORMImmunizationRegistryMessagePatientInfoGet.class,
				lstParam);

		if (lst != null && lst.size() > 0)
			return lst.get(0);

		else
			return null;

	}

	private void addFileGenerationResultToList(Long id, String recordDescription, String type, String description,
			String segment) {
		HL7FileGenerationResult immunizationFileGenerationResult = new HL7FileGenerationResult();
		immunizationFileGenerationResult.setId((long) id);
		immunizationFileGenerationResult.setRecord_description(recordDescription);
		immunizationFileGenerationResult.setType(type);
		immunizationFileGenerationResult.setMessage(description);
		immunizationFileGenerationResult.setSegment(segment);

		if (lstImmunizationFileGenerationResult == null) {
			lstImmunizationFileGenerationResult = new ArrayList<>();
		}
		lstImmunizationFileGenerationResult.add(immunizationFileGenerationResult);

	}

	private String saveMU3RegistryMessage(ORMImmunizationRegistryMessageSave objORM) {

		/*
		 * String strQuery =
		 * " select top 1 outgoing_path as scalar_value  from immunization_registries where registry_code='"
		 * + objORM.getRegistry_code() + "' and practice_id='" + objORM.getPractice_id()
		 * + "'  and isnull(deleted,0)<>1";
		 * 
		 * 
		 * String outgoing_path = this.db.getQuerySingleResult(strQuery);
		 */

		ORMImmRegPathGet objImmRegPathGet = GetRegistryPath(practiceId, registryCode);
		String outgoing_path = objImmRegPathGet.getOutgoing_path();

		String HL7FilePath = objORM.getMessage_id() + ".hl7";
		String path = outgoing_path + "\\" + HL7FilePath;

		try {

			FileUtil.UploadStringDataToFile(objORM.getImmunization_message(), path);

			if (db.SaveEntity(objORM, Operation.ADD) > 0) {

				if (objORM.getMessage_type().equalsIgnoreCase("VXU")) {
					if (lstImmRegMessageImmunizationSave != null) {

						for (ORMImmRegMessageImmunizationSave ormRegImm : lstImmRegMessageImmunizationSave) {
							db.SaveEntity(ormRegImm, Operation.ADD);
						}

						String strQuery = "update chart_immunization set registry_status='AQ',registry_date='"
								+ date_current_yyyy_MM_dd_HH_mm_ss_String + "' where chart_immunization_id in ("
								+ chartImmunizationIDs + ") ";
						db.ExecuteUpdateQuery(strQuery);

					}
				} else if (objORM.getMessage_type().equalsIgnoreCase("QBP")) {

					if (objQueryMessageDetails != null) {
						db.SaveEntity(objQueryMessageDetails, Operation.ADD);
					}
				}
			}

			return "SUCCESS~File has been generated.";

		} catch (IOException ex) {
			return "ERROR~" + ex.getMessage();
		}
	}

	// validate PID
	private boolean validatePID() {

		boolean isValid = true;

		if (patientInfo == null) {
			isValid = false;
			addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR", "Patient Record Not Found.",
					"Patient Info");
		}

		if (GeneralOperations.isNullorEmpty(patientInfo.getLast_name())) {
			isValid = false;
			addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR", "Patient Last Name is missing.",
					"Patient Info");

		}
		if (GeneralOperations.isNullorEmpty(patientInfo.getFirst_name())) {
			isValid = false;
			addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR", "Patient First Name is missing.",
					"Patient Info");

		} else if (GeneralOperations.isNotNullorEmpty(patientInfo.getFirst_name())
				&& patientInfo.getFirst_name().trim().length() <= 1) {
			isValid = false;
			addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR",
					"Patient First Name must be greater than one character in length.", "Patient Info");

		}

		if (GeneralOperations.isNullorEmpty(patientInfo.getDob())) {
			isValid = false;
			addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR", "Patient DOB is missing.",
					"Patient Info");

		}

		return isValid;

	}

	// Validate Vaccine
	private boolean validateVaccineData() {

		boolean isValid = true;

		for (ORMImmunizationForRegistryMessageGet chartImmunization : lstImmunizationForRegistryMessage) {

			String immDispalyName = GeneralOperations.isNotNullorEmpty(chartImmunization.getTrade_name())
					? chartImmunization.getTrade_name()
					: chartImmunization.getImmunization_name();

			if (GeneralOperations.isNullorEmpty(chartImmunization.getCompletion_status_code())) {

				isValid = false;
				addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
						"Completion Status Code is missing.", "Completion Status");
			}

			if (chartImmunization.getEntry_type().equalsIgnoreCase("administered")) {

				if (GeneralOperations.isNullorEmpty(chartImmunization.getProvider_id())
						|| GeneralOperations.isNullorEmpty(chartImmunization.getProvider_last_name())
						|| GeneralOperations.isNullorEmpty(chartImmunization.getProvider_first_name())) {

					isValid = false;
					addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
							"Ordering Provider ID and/or Name is required.", "ORC");
				}

				if (GeneralOperations.isNullorEmpty(chartImmunization.getNdc_code())) {

					isValid = false;
					addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR", "NDC Code Missing.",
							"RXA");

				}
				if (GeneralOperations.isNullorEmpty(chartImmunization.getTrade_name())) {

					isValid = false;
					addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
							"Trade Name or Coding System is missing.", "RXA");

				}

				if (GeneralOperations.isNotNullorEmpty(chartImmunization.getDose())) {

					try {
						Float.parseFloat(chartImmunization.getDose());
					} catch (NumberFormatException e) {
						isValid = false;
						addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
								"Dose is invalid for CVX Code.", "RXA");

					}

				}

				if (GeneralOperations.isNotNullorEmpty(chartImmunization.getUnits())) {

					if (chartImmunization.getUnits().equalsIgnoreCase("cc")) {
						isValid = false;
						addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
								"Invalid Unit Dosage Unit CC.", "RXA");
					}
				}

				if ((chartImmunization.getCompletion_status_code().equalsIgnoreCase("CP")
						|| chartImmunization.getCompletion_status_code().equalsIgnoreCase("PA"))) {

					if (GeneralOperations.isNullorEmpty(chartImmunization.getRoute_code())
							|| GeneralOperations.isNullorEmpty(chartImmunization.getRoute_description())) {

						isValid = false;
						addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
								"Route is missing.", "RXR");
					}
				}

				if (!chartImmunization.getCvx_code().equalsIgnoreCase("998")) {

					if (GeneralOperations.isNullorEmpty(chartImmunization.getUnits())) {
						isValid = false;
						addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
								"Administered Unit is required.", "RXA");
					} else if (chartImmunization.getUnits().equalsIgnoreCase("cc")) {
						isValid = false;
						addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
								"Invalid Unit Dosage Unit CC.", "RXA");
					}

					if (GeneralOperations.isNullorEmpty(chartImmunization.getFunding_code())
							|| GeneralOperations.isNullorEmpty(chartImmunization.getFunding_description())) {
						isValid = false;
						addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
								"Vaccine Funding Source is missing.", "OBX");
					}

				}

			} else if (chartImmunization.getEntry_type().equalsIgnoreCase("historical")) {

				if (GeneralOperations.isNullorEmpty(chartImmunization.getCvx_code())) {
					isValid = false;
					addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR", "CVX Code Missing.",
							"RXA");
				}

			} else if (chartImmunization.getEntry_type().equalsIgnoreCase("refused")) {

				if (GeneralOperations.isNullorEmpty(chartImmunization.getCvx_code())) {
					isValid = false;
					addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR", "CVX Code Missing.",
							"RXA");
				}

			} else {
				isValid = false;
				addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
						"Vaccine Entry Type is missing.", "Entry Type");
				isValid = false;
			}

			if (!chartImmunization.getCvx_code().equalsIgnoreCase("998")) {

				if (GeneralOperations.isNullorEmpty(chartImmunization.getEntered_by_user_info())) {
					isValid = false;
					addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
							"First Name, Last Name of Entered User is required.", "ORC");
				} else if (chartImmunization.getEntered_by_user_info().split("\\^").length < 3) {
					isValid = false;
					addFileGenerationResultToList((long) processing_id++, immDispalyName, "ERROR",
							"Entered By User Details are missing.", "ORC");
				}
			}
		}

		return isValid;
	}

	public WrapperImmRegEvaluationHistoryForecastMessageDetails getImmRegEvaluationHistoryForecastMessageDetails(
			Long messageId) {

		WrapperImmRegEvaluationHistoryForecastMessageDetails wrraper = new WrapperImmRegEvaluationHistoryForecastMessageDetails();

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("message_id", messageId.toString(), String.class, ParameterMode.IN));

		List<ORMImmRegQueryMessageDetailsGet> lst = db.getStoreProcedureData("spGetImmRegistryQueryMessageDetail",
				ORMImmRegQueryMessageDetailsGet.class, lstParam);

		if (lst == null || lst.size() == 0) {
			wrraper.setResponse_status("Error");
			wrraper.setError_message("Message Not Found.");
		} else {

			String msgStatus = lst.get(0).getMessage_status();
			Long patientId = lst.get(0).getPatient_id();
			String strResponseMsgString = lst.get(0).getResponse_message();

			if (msgStatus.equalsIgnoreCase("AQ")) {

				ORMImmunizationRegistryMessagePatientInfoGet patientInfo = getPatientInfo(patientId);

				wrraper.setMessage_id(messageId);
				wrraper.setPatient_info_notes("Patient Info is derived from EHR Patient Record.");
				wrraper.setPatient_info(patientInfo);

			} else if (GeneralOperations.isNullorEmpty(strResponseMsgString)) {
				wrraper.setResponse_status("Error");
				wrraper.setError_message("Response Message is empty.");

			} else {

				String queryRespStatus = "";

				queryRespStatus = lst.get(0).getResponse_status();
				wrraper.setReq_response(queryRespStatus);
				if (queryRespStatus.equalsIgnoreCase("NF")) {

					ORMImmunizationRegistryMessagePatientInfoGet patientInfo = getPatientInfo(patientId);
					wrraper.setMessage_id(messageId);
					wrraper.setPatient_info(patientInfo);
					// wrraper.setReq_response("NF");
					// wrraper.setReq_response_message("No matching records were found for the
					// person in the query.");
					wrraper.setPatient_info_notes(
							"Patient Info is derived from EHR Patient Record.\nReturn Acknowledgement with No Person Records Z33 message was received.");

				} else if (queryRespStatus.equalsIgnoreCase("TM")) {

					ORMImmunizationRegistryMessagePatientInfoGet patientInfo = getPatientInfo(patientId);
					wrraper.setMessage_id(messageId);
					wrraper.setPatient_info(patientInfo);

					// wrraper.setReq_response("TM");
					// ;("Too many matching records were found for the person in the query.");

					wrraper.setPatient_info_notes(
							"Patient Info is derived from EHR Patient Record.\nReturn Acknowledgement with No Person Records Z33 message was received.");
				} else if (queryRespStatus.equalsIgnoreCase("OK") || queryRespStatus.equalsIgnoreCase("AA_IW")) {
					String[] lines = strResponseMsgString.split(System.getProperty("line.separator"));

					// int lineIndex = 0;
					int history_vaccine_id = 0;
					int forecast_vaccine_id = 0;
					String query_response_status = "";
					String schedule_used = "";

					List<ImmRegEvaluatedHistory> objListImmHistory = new ArrayList<>();
					List<ImmRegForecast> objListImmForecast = new ArrayList<>();
					ORMImmunizationRegistryMessagePatientInfoGet patientInfo = new ORMImmunizationRegistryMessagePatientInfoGet();

					// lines[0] // ==> MSH
					// lines[1] // ==> MSA
					// lines[2] // ==> QAK
					// lines[3] // ==> QPD

					// lines[4] // ==> PID May or May Not be Present.
					try {
						for (int index = 0; index < lines.length; index++) {

							String[] lstLineSegments = lines[index].split("\\|");
							if (lstLineSegments == null || lstLineSegments.length == 0) {
								continue;
							}

							String segment = lstLineSegments[0];
							switch (segment) {

							case "QAK":
								// lstLineSegments[1] // tag_id
								if (queryRespStatus.equalsIgnoreCase("AA_IW")) {
									query_response_status = "AA_IW";
								} else {
									query_response_status = lstLineSegments[2]; // Query Response Status
								}
								// System.out.println("Tage ID:" + lstLineSegments[1] + " Req Status:" +
								// lstLineSegments[2]);

								// AE Application error
								// AR Application reject
								// NF No data found, no errors
								// OK Data found, no errors (this is the default)
								// TM Too many candidates found
								if (query_response_status.equalsIgnoreCase("NF")
										|| query_response_status.equalsIgnoreCase("TM"))// Not Found
								{
									break;
								}
								break;
							case "PID":
								String[] lstId = lstLineSegments[3].split("\\~");
								String patIdentifer = "";
								for (String s : lstId) {

									switch (s.split("\\^")[4]) {
									case "MA":
										patIdentifer = "Medicaid Number";
										break;
									case "MC":
										patIdentifer = "Medicare Number";
										break;
									case "MR":
										patIdentifer = "Medical Record Number";
										break;
									case "PI":
										patIdentifer = "Patient Internal Identifier";
										break;
									case "PN":
										patIdentifer = "Person Number";
										break;
									case "PRN":
										patIdentifer = "Provider Number";
										break;
									case "PRI":
										patIdentifer = "Regional Registry ID";
										break;
									case "SR":
										patIdentifer = "State Registry Identifier";
										break;
									case "SS":
										patIdentifer = "Social Security Number";
										break;

									default:
										break;
									}
									
									patIdentifer+=": "+s.split("\\^")[0].toString();
									patientInfo.setPatient_id(patIdentifer);
									break;
								}
								patientInfo.setLast_name(lstLineSegments[5].split("\\^")[0]);
								patientInfo.setFirst_name(lstLineSegments[5].split("\\^")[1]);
								patientInfo.setMname(lstLineSegments[5].split("\\^")[2]);

								Date dob = DateTimeUtil.GetDateFromString(lstLineSegments[7], DateFormatEnum.yyyyMMdd);
								patientInfo.setDob(DateTimeUtil.GetStringFromDate(dob, DateFormatEnum.MM_dd_yyyy));
								patientInfo.setGender_code(lstLineSegments[8]);
								switch (lstLineSegments[8]) {
								case "M":
									patientInfo.setGender_description("Male");
									break;
								case "F":
									patientInfo.setGender_description("Female");
									break;
								}

								break;
							case "ORC":
								// lstLineSegments[3] // ORC ID

								// System.out.println("ORC ID:" + lstLineSegments[3]);
								// check if next line is RXA
								if (index < lines.length) {
									lstLineSegments = lines[index + 1].split("\\|");
									segment = lstLineSegments[0];
									while (segment.equalsIgnoreCase("RXA")) {

										ImmRegEvaluatedHistory objEvaluatedImmHistory = new ImmRegEvaluatedHistory();
										ImmRegForecast objImmForecast = new ImmRegForecast();

										index++;// Move to Next Line

										// lstLineSegments[3] // Date Administered
										// lstLineSegments[5] // Vaccine
										// String[] vaccine=lstLineSegments[5].split("^");
										// vaccine[0] // Code
										// vaccine[1] // Name
										// vaccine[2] // Code Type
										// lstLineSegments[20] // completion_status
										// System.out.println("Date:" + lstLineSegments[3] + " Vaccine:" +
										// lstLineSegments[5].split("\\^")[1] + " Status:" + lstLineSegments[20]);
										String vaccine_code = lstLineSegments[5].split("\\^")[0];

										if (!vaccine_code.equalsIgnoreCase("998")) {
											history_vaccine_id++;
											objEvaluatedImmHistory.setS_no(history_vaccine_id);

											Date dateAdministered = DateTimeUtil.GetDateFromString(lstLineSegments[3],
													DateFormatEnum.yyyyMMdd);
											objEvaluatedImmHistory.setDate_administered(DateTimeUtil
													.GetStringFromDate(dateAdministered, DateFormatEnum.MM_dd_yyyy));

											objEvaluatedImmHistory.setCode(lstLineSegments[5].split("\\^")[0]);
											objEvaluatedImmHistory.setImm_name(lstLineSegments[5].split("\\^")[1]);
											objEvaluatedImmHistory.setCode_type(lstLineSegments[5].split("\\^")[2]);

											if (lstLineSegments[20].equalsIgnoreCase("CP")) {
												objEvaluatedImmHistory.setCompletion_status("Complete");
											} else if (lstLineSegments[20].equalsIgnoreCase("PA")) {
												objEvaluatedImmHistory.setCompletion_status("Partially Complete");
											}

										}

										if ((index + 1) < lines.length) {
											lstLineSegments = lines[index + 1].split("\\|");
											segment = lstLineSegments[0];
										} else {
											segment = "";
										}

										// SKIP All Segments inside RXA Other Than OBX;
										while (!segment.equalsIgnoreCase("OBX") && !segment.equalsIgnoreCase("RXA")
												&& !segment.equalsIgnoreCase("ORC")) {
											if ((index + 1) < lines.length) {
												index++;
												lstLineSegments = lines[index].split("\\|");
												segment = lstLineSegments[0];
											} else {
												segment = "";
												break;
											}
										}

										// int vaccine_forecast_no=0;
										while (segment.equalsIgnoreCase("OBX")) {
											index++;// Move to Next Line

											// lstLineSegments[3] // Observation Identifier
											// lstLineSegments[2] // Request Status
											if (vaccine_code.equalsIgnoreCase("998")) {

												if (forecast_vaccine_id != Integer.parseInt(lstLineSegments[4])) {

													forecast_vaccine_id = Integer.parseInt(lstLineSegments[4]);

													if (forecast_vaccine_id > 1) {
														objListImmForecast.add(objImmForecast);
													}

													objImmForecast = new ImmRegForecast();
													objImmForecast.setS_no(forecast_vaccine_id);
												}

												switch (lstLineSegments[3].split("\\^")[0]) {
												case "30956-7": // vaccine type
													objImmForecast.setImm_group(lstLineSegments[5].split("\\^")[1]);
													break;
												case "59779-9": // Immunization Schedule used
													if (GeneralOperations.isNullorEmpty(schedule_used)) {
														schedule_used = lstLineSegments[5].split("\\^")[1];
													}
													break;
												case "30980-7": // Date vaccination due

													Date dueDate = DateTimeUtil.GetDateFromString(lstLineSegments[5],
															DateFormatEnum.yyyyMMdd);
													objImmForecast.setDue_date(DateTimeUtil.GetStringFromDate(dueDate,
															DateFormatEnum.MM_dd_yyyy));

													break;
												case "30981-5": // Earliest Date to give

													Date earliestDate = DateTimeUtil.GetDateFromString(
															lstLineSegments[5], DateFormatEnum.yyyyMMdd);
													objImmForecast.setEaliest_date_to_give(
															DateTimeUtil.GetStringFromDate(earliestDate,
																	DateFormatEnum.MM_dd_yyyy));

													break;
												case "59777-3": // Latest date next dose may be given

													Date earlatestDate = DateTimeUtil.GetDateFromString(
															lstLineSegments[5], DateFormatEnum.yyyyMMdd);
													objImmForecast.setLatest_date_to_give(
															DateTimeUtil.GetStringFromDate(earlatestDate,
																	DateFormatEnum.MM_dd_yyyy));

													break;

												}

											} else {
												switch (lstLineSegments[3].split("\\^")[0]) {
												case "30956-7": // vaccine type
													objEvaluatedImmHistory
															.setImm_group(lstLineSegments[5].split("\\^")[1]);

													break;
												case "59779-9": // Immunization Schedule used

													if (GeneralOperations.isNullorEmpty(schedule_used)) {
														schedule_used = lstLineSegments[5].split("\\^")[1];
													}

													break;
												case "59781-5": // dose validity Y/N
													if (lstLineSegments[5].toString().equalsIgnoreCase("Y")) {
														objEvaluatedImmHistory.setDos_validity("YES");
													} else if (lstLineSegments[5].toString().equalsIgnoreCase("N")) {
														objEvaluatedImmHistory.setDos_validity("NO");
													}

													break;
												case "30982-3": // Reason applied
													objEvaluatedImmHistory.setReason_applied(lstLineSegments[5]);

													break;
												}
											}

											if ((index + 1) < lines.length) {
												lstLineSegments = lines[index + 1].split("\\|");
												segment = lstLineSegments[0];
											} else {
												segment = "";
											}

										}

										if (vaccine_code.equalsIgnoreCase("998") && forecast_vaccine_id > 1) {
											objListImmForecast.add(objImmForecast);
										} else if (history_vaccine_id > 0) {
											objListImmHistory.add(objEvaluatedImmHistory);
										}
									}
								}

								break;
							}
						}

						wrraper.setResponse_status(query_response_status);
						wrraper.setSchedule_used(schedule_used);
						wrraper.setPatient_info(patientInfo);
						wrraper.setLst_evaluated_history(objListImmHistory);
						wrraper.setLst_forecast(objListImmForecast);

						// return wrraper;

					} catch (Exception ex) {
						wrraper.setResponse_status("Error");
						wrraper.setError_message(ex.getMessage());
					}
				}

			}

		}

		return wrraper;
	}

	// Evaluated History and Forecast Group (Generate QBP Query by Parameter)
	/**
	 * Generate QBP Query by Parameter (Evaluated History and Forecast Group)
	 *
	 * @param practiceId
	 * @param clinic_id
	 * @param patientId
	 * @param loginUser
	 * @param clientIP
	 * @param isGenerateAndSubmit
	 * @return List
	 */
	public List<HL7FileGenerationResult> generateQBP_HL7(ImmRegQBPCriteria immRegQBPCriteria
	// String practiceId, String clinic_id, String patientId, String loginUser,
	// String clientIP,
	// Boolean isGenerateAndSubmit
	) {

		// clearAllData();

		if (immRegQBPCriteria == null) {
			addFileGenerationResultToList((long) processing_id++, "QBP", "ERROR",
					"File Generation Data is not provided.", "File Generation Data");

			return lstImmunizationFileGenerationResult;
		} else {
			practiceId = immRegQBPCriteria.getPractice_id();
			patientId = immRegQBPCriteria.getPatient_id();
			loginUser = immRegQBPCriteria.getUser_name();
			clientIP = immRegQBPCriteria.getClient_ip();
			clinicID = immRegQBPCriteria.getClinic_id();
			isSubmitAfterGeneation = immRegQBPCriteria.isSubmit_after_geneation();
		}

		if (GeneralOperations.isNullorEmpty(practiceId)
				|| GeneralOperations.isNullorEmpty(patientId) | GeneralOperations.isNullorEmpty(loginUser)
				|| GeneralOperations.isNullorEmpty(clientIP) || GeneralOperations.isNullorEmpty(clinicID)) {

			addFileGenerationResultToList(
					(long) processing_id++, "VXU", "ERROR", "One of the File Generation Data is not provided."
							+ "\n Pracice Id" + ", Patient Id" + ", Loged In User Info" + ", Client IP" + ", Clinic Id",
					"File Generation Data");

			return lstImmunizationFileGenerationResult;
		}

		if (AssignRegistryInfo() == false) {

			addFileGenerationResultToList((long) processing_id++, "QBP", "ERROR", "Registry Info Not Found.",
					"Registry Info");

			return lstImmunizationFileGenerationResult;
		}

		patientInfo = getPatientInfo(this.patientId);

		if (patientInfo == null) {
			addFileGenerationResultToList((long) processing_id++, "VXU", "ERROR", "Patient Record Not Found.",
					"Patient Info");

			return lstImmunizationFileGenerationResult;
		}

		if (isSubmitAfterGeneation) {
			MessageID = db.IDGenerator("immunization_registry_messages", practiceId);
			QueryMsgDetailID = db.IDGenerator("imm_registry_query_message_details", practiceId);
		} else {
			MessageID = (long) 500101;// Default ID if dot not saving.
			QueryMsgDetailID = (long) 500101;// Default ID if dot not saving.
		}

		objQueryMessageDetails = new ORMImmRegQueryMessageDetailsSave();
		objQueryMessageDetails.setId(QueryMsgDetailID);
		objQueryMessageDetails.setMessage_id(MessageID);
		objQueryMessageDetails.setPatient_id(Long.parseLong(patientInfo.getPatient_id()));
		objQueryMessageDetails.setPractice_id(practiceId);

		String strHL7_Complete = "";

		if (Get_MSH_QBP() == false) {

			addFileGenerationResultToList((long) processing_id++, "", "ERROR",
					"An Error Occuree while generating MSH Segment.", "MSH");
			/*
			 * System.out.println("An Error Occuree while generating MSH Segment.");
			 * 
			 * processing_id++; objProcessORM.setCol1(Integer.toString(processing_id));
			 * objProcessORM.setCol2("ERROR");
			 * objProcessORM.setCol3("An Error Occuree while generating MSH Segment.");
			 * objProcessORM.setCol4("MSH"); lstProceesStatus.add(objProcessORM); return
			 * lstProceesStatus;
			 */
		}
		if (Get_QPD_QBP(objQueryMessageDetails) == false) {

			addFileGenerationResultToList((long) processing_id++, "", "ERROR",
					"An Error Occuree while generating QPD Segment.", "QPD");
		}
		if (Get_RCP_QBP() == false) {

			addFileGenerationResultToList((long) processing_id++, "", "ERROR",
					"An Error Occuree while generating RCP Segment.", "RCP");

		}

		if (lstImmunizationFileGenerationResult == null || lstImmunizationFileGenerationResult.size() == 0) {

			strHL7_Complete = strMSH_QBP + strQPD_QBP + strRCP_QBP;

			if (isSubmitAfterGeneation) {

				ORMImmunizationRegistryMessageSave objORM = new ORMImmunizationRegistryMessageSave();
				objORM.setMessage_id(MessageID);
				objORM.setPatient_id(patientId);
				objORM.setDate_created(GeneralOperations.CurrentDateTime());
				objORM.setDate_modified(GeneralOperations.CurrentDateTime());
				objORM.setSystem_ip(clientIP);
				objORM.setCreated_user(loginUser);
				objORM.setModified_user(loginUser);
				objORM.setRegistry_code(registryCode);
				objORM.setClinic_id(clinicID);
				objORM.setClinic_code(clinicCode);
				objORM.setMessage_status("AQ");
				objORM.setMessage_type("QBP"); // QBP ==> Query by Parameter (Evaluated History and Forecast Group)
				objORM.setPractice_id(practiceId);
				objORM.setImmunization_message(strHL7_Complete);
				objORM.setMessage_sent_date(GeneralOperations.CurrentDateTime());
				objORM.setMessage_sent_by(loginUser);

				String res = saveMU3RegistryMessage(objORM);

				if (res.split("~")[0].equalsIgnoreCase("SUCCESS")) {
					addFileGenerationResultToList(MessageID, "QBP", "SUCCESSFULL", strHL7_Complete, "Genereation File");
				} else {
					addFileGenerationResultToList(MessageID, "QBP", "ERROR",
							"An Error Occured While Saving Immunization Message", "File Genereation");
				}

			} else {

				addFileGenerationResultToList(MessageID, "QBP", "SUCCESSFULL", strHL7_Complete, "Genereation File");
			}

		}

		return lstImmunizationFileGenerationResult;
	}

	/**
	 * MSH Segment
	 *
	 * @return Boolean
	 */
	public Boolean Get_MSH_QBP() {

		try {
			strMSH_QBP = // 1- MSH
					"MSH" + seperator + // 2- Encoding Characters
							encoding_characters + seperator;
			// 3- Sending Application (R)
			if (includeUIDs && GeneralOperations.isNotNullorEmpty(strSendingApplicationUID)) {
				strMSH_QBP += strSendingApplicationName + caret + strSendingApplicationUID + caret + UID_TYPE;
			} else {
				strMSH_QBP += strSendingApplicationName;
			}
			strMSH_QBP += seperator;
			// 4- Sending Facility (R)
			if (includeUIDs && GeneralOperations.isNotNullorEmpty(strSendingFacilityUID)) {
				strMSH_QBP += strSendingFacilityID + caret + strSendingFacilityUID + caret + UID_TYPE;
			} else {
				strMSH_QBP += strSendingFacilityID;
			}
			strMSH_QBP += seperator;
			// 5- Receiving Application (C)
			if (includeUIDs && GeneralOperations.isNotNullorEmpty(strReceivingApplicationUID)) {
				strMSH_QBP += strReceivingApplicationName + caret + strReceivingApplicationUID + caret + UID_TYPE;
			} else {
				strMSH_QBP += strReceivingApplicationName;
			}
			strMSH_QBP += seperator;
			// 6- Receiving Facility (C)
			if (includeUIDs && GeneralOperations.isNotNullorEmpty(strReceivingFacilityUID)) {
				strMSH_QBP += strReceivingFacility + caret + strReceivingFacilityUID + caret + UID_TYPE;
			} else {
				strMSH_QBP += strReceivingFacility;
			}
			strMSH_QBP += seperator + // 7- D/T of Message (O)
					date_current_yyyyMMddHHmmssZZZZ_String + seperator + // 8- Empty
					"" + seperator + // 9- Message Type (Type^Event) (R) Query by Parameter^
					"QBP" + caret + "Q11" + caret + "QBP_Q11" + seperator + // 10- Message Control ID (oerder ID) (R)
					MessageID + seperator + // 11- Processing ID (R)
					"P" + seperator + // 12- Version ID (R)
					"2.5.1" + seperator + // 13-
					"" + seperator + // 14-
					"" + seperator + // 15- Accept Acknowledgment Type
					"ER" + seperator
					// 16- Application Acknowledgment Type
					+ "AL" + seperator
					// 17- 18 - 19 -20
					+ seperator + seperator + seperator + seperator
					// 21- Message Profile Identifier
					+ "Z44^CDCPHINVS" + seperator; // Z44=>Request Evaluated History and Forecast
			// 22- Sending Responsible Organization
			if (GeneralOperations.isNotNullorEmpty(this)) {
				// 22.1 - Organization Name
				// 22.6 - Assigning Authority
				// 22.7 - Identifier Type Code XX=Organization ID
				// 22.10 - Organization Identifier
				strMSH_QBP += strSendingFacilityID + caret + "" + caret + "" + caret + "" + caret + "" + caret
						+ strOrgAssAuth + "" + caret + "XX" + "" + caret + "" + caret + "" + caret + clinicID;
			} else {
				strMSH_QBP += strSendingFacilityID;
			}
			strMSH_QBP += seperator;
			// 23- Receiving Responsible Organization
			if (GeneralOperations.isNotNullorEmpty(this)) {
				strMSH_QBP += strReceivingFacility + caret + "" + caret + "" + caret + "" + caret + "" + caret
						+ strOrgAssAuth + "" + caret + "XX" + "" + caret + "" + caret + "" + caret + strRecOrgID;
			} else {
				strMSH_QBP += strReceivingFacility;
			}
			// 23.1 - Organization Name
			// 23.6 - Assigning Authority
			// 23.7 - Identifier Type Code XX=Organization ID
			// 23.10 - Organization Identifier

			// System.err.println("MSH_QBP:" + strMSH_QBP);

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
		return true;
	}

	/**
	 * QPD=>Input Parameter Specification
	 *
	 * @param objQueryMessageDetails
	 * @param patientInfo
	 * @return Boolean
	 */
	public Boolean Get_QPD_QBP(ORMImmRegQueryMessageDetailsSave objQueryMessageDetails) {

		try {

			strQPD_QBP = "\n" + "QPD" + seperator;

			if (GeneralOperations.isNullorEmpty(patientInfo.getLast_name())) {

				addFileGenerationResultToList((long) processing_id++, "", "ERROR", "Patient Last Name is missing.",
						"QPD");
			}
			if (GeneralOperations.isNullorEmpty(patientInfo.getFirst_name())) {

				addFileGenerationResultToList((long) processing_id++, "", "ERROR", "Patient First Name is missing.",
						"QPD");

			}
			if (GeneralOperations.isNullorEmpty(patientInfo.getDob())) {
				addFileGenerationResultToList((long) processing_id++, "", "ERROR", "Patient DOB is missing.", "QPD");
			}

			String GenderCode = patientInfo.getGender_code();
			if (GeneralOperations.isNullorEmpty(GenderCode)) {
				GenderCode = "U";
			}

			String Tel_Use_Code = "";
			String Tel_Equ_Type = "";
			// String email_id = "";
			String area_code = "";
			String Local_number = "";
			String phone_number = "";

			// PRN Primary Residence Number
			// EMR Emergency Number
			// ORN Other Residence Number
			// WPN Work Phone Number
			// ASN Answering Service Number
			// VHN Vacation Home Number
			if (GeneralOperations.isNotNullorEmpty(patientInfo.getPrimary_phone())) {
				switch (patientInfo.getPrimary_phone().toUpperCase()) {

				case "HOME PHONE":
					if (GeneralOperations.isNotNullorEmpty(patientInfo.getHome_phone())) {

						phone_number = patientInfo.getHome_phone();
						Tel_Use_Code = "PRN"; // Primary Residence Number
						Tel_Equ_Type = "PH";
					}

					break;
				case "CELL PHONE":
					if (GeneralOperations.isNotNullorEmpty(patientInfo.getCell_phone())) {
						phone_number = patientInfo.getCell_phone();
						Tel_Use_Code = "PRN"; // Primary Residence Number
						Tel_Equ_Type = "CP";
					}
					break;
				case "WORK PHONE":
					if (GeneralOperations.isNotNullorEmpty(patientInfo.getWork_phone())) {
						phone_number = patientInfo.getWork_phone();
						Tel_Use_Code = "WPN"; // Work Place Number
						Tel_Equ_Type = "PH";
					}
					break;
				}
			}

			// if (GeneralOperations.isNotNullorEmpty(patientInfo.getEmail())) {
			// email_id = patientInfo.getEmail();
			// }
			if (GeneralOperations.isNotNullorEmpty(phone_number)) {
				if (phone_number.length() > 3) {
					area_code = phone_number.substring(0, 3);
					Local_number = phone_number.substring(3, phone_number.length());
				}
			}

			strQPD_QBP += // QPD-1: Message Query Name
					"Z44" + caret + "Request Evaluated History and Forecast" + caret + "CDCPHINVS" + seperator
					// QPD-2: Query Tag
							+ objQueryMessageDetails.getId().toString() + seperator
							// QPD-3 : PatientList
							+ objQueryMessageDetails.getPatient_id() + caret + "" + caret + "" + caret
							+ strPIDAssignAuth + caret + "MR" + seperator
							// QPD-4 : PatientName
							+ patientInfo.getLast_name() + caret + patientInfo.getFirst_name() + caret
							+ patientInfo.getMname() + caret + caret + caret + caret + "L" + seperator;
			// QPD-5 : PatientMotherMaidenName
			if (GeneralOperations.isNotNullorEmpty(patientInfo.getMother_maiden_first_name())
					|| GeneralOperations.isNotNullorEmpty(patientInfo.getMother_maiden_last_name())) {

				strQPD_QBP += patientInfo.getMother_maiden_last_name() + caret
						+ patientInfo.getMother_maiden_first_name() + caret + caret + caret + caret + caret + "M";
			}
			strQPD_QBP += seperator
					// QPD-6 DOB
					+ DateTimeUtil.GetStringFromDate(
							DateTimeUtil.GetDateFromString(patientInfo.getDob(), DateFormatEnum.MM_dd_yyyy),
							DateFormatEnum.yyyyMMdd)
					+ seperator
					// QPD-7 : PatientSex
					+ GenderCode + seperator
					// QPD-8 : PatientAddress
					+ patientInfo.getAddress() + caret + patientInfo.getAddress2() + caret + patientInfo.getCity()
					+ caret + patientInfo.getState() + caret + patientInfo.getZip() + caret + addressCountryCode + caret
					+ "P" + seperator;

			if (GeneralOperations.isNotNullorEmpty(area_code) && GeneralOperations.isNotNullorEmpty(Local_number)) {
				strQPD_QBP += "" + caret + Tel_Use_Code + caret + Tel_Equ_Type + caret + caret + phoneCountryCode
						+ caret + area_code + caret + Local_number;
			}
			strQPD_QBP += seperator;

			// QPD-10 Multiple birth indicator
			if (GeneralOperations.isNotNullorEmpty(patientInfo.getMulti_birth())) {
				strQPD_QBP += patientInfo.getMulti_birth() + seperator;
				// QPD-11 Birth order
				if (GeneralOperations.isNotNullorEmpty(patientInfo.getBirth_order())
						&& patientInfo.getMulti_birth().equalsIgnoreCase("Y")) {
					strQPD_QBP += patientInfo.getBirth_order() + seperator;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
		return true;
	}

	/**
	 * Query Priority
	 *
	 * @return Boolean
	 */
	public Boolean Get_RCP_QBP() {
		strRCP_QBP = "\n" + "RCP|I|1^RD&Records&HL70126";
		return true;
	}

	public ServiceResponse<ORMKeyValue> ProcessAcknowledgementMessageFromFileData(MultipartFile responseFileData,
			SearchCriteria searchCriteria) {

		// clearAllData();

		practiceId = searchCriteria.getPractice_id();
		String logedInUser = "";

		int total = 1;
		int success = 0;
		int errors = 0;
		int invalid = 0;
		int unmapped = 0;

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				case "registry_code":
					registryCode = pram.getValue();
					break;
				case "loged_in_user":
					logedInUser = pram.getValue();
					break;

				default:
					break;
				}
			}
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		try {

			ORMImmRegPathGet objImmRegPathGet = GetRegistryPath(practiceId, registryCode);
			String response_path = objImmRegPathGet.getResponse_path();

			if (GeneralOperations.isNullorEmpty(response_path)) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("Registry not setup");
			} else if (responseFileData != null && !responseFileData.isEmpty()) {

				byte[] bytes = responseFileData.getBytes();
				String response_message = new String(bytes);
				List<String> listOfLines = Arrays.asList(response_message.split(System.getProperty("line.separator")));
				String fileName = responseFileData.getOriginalFilename();

				if (listOfLines.size() > 0) {
					// MSH-9 Message Type
					String responseMsgType = listOfLines.get(0).split("\\|")[8].split("\\^")[0];
					ImmRegFileProcessingStatus fileProcessingStatus = null;

					switch (responseMsgType) {
					case "ACK":
						fileProcessingStatus = ProcessACK(listOfLines, response_message, practiceId, logedInUser, null,
								"STRING_DATA", response_path, fileName);

						break;
					case "RSP":
						fileProcessingStatus = ProcessRSP(listOfLines, response_message, practiceId, logedInUser, null,
								"STRING_DATA", response_path, fileName);
						break;
					default:
						// resp.setStatus(ServiceResponseStatus.ERROR);
						// resp.setResponse("Response File is not in proper format.");
						invalid++;
						break;
					}

					if (fileProcessingStatus.equals(ImmRegFileProcessingStatus.SUCCESS)) {
						success++;
					}
					if (fileProcessingStatus.equals(ImmRegFileProcessingStatus.ERROR)) {
						errors++;
					}
					if (fileProcessingStatus.equals(ImmRegFileProcessingStatus.INVALID_FORMAT)) {
						invalid++;
					}
					if (fileProcessingStatus.equals(ImmRegFileProcessingStatus.NOT_MAPPED)) {
						unmapped++;
					}

				}

			} else {
				// resp.setStatus(ServiceResponseStatus.ERROR);
				// resp.setResponse("Response File is emtpy.");
				invalid++;
			}

			String strMsg = "";
			if (success > 0) {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
			} else {
				resp.setStatus(ServiceResponseStatus.ERROR);
			}

			strMsg = "Total Files: " + total;
			if (success > 0) {
				strMsg += "<br>Processed Successfully: " + success;
			}
			if (errors > 0) {
				strMsg += "<br>Errors: " + errors;
			}
			if (invalid > 0) {
				strMsg += "<br>Invalid format: " + invalid;
			}
			if (unmapped > 0) {
				strMsg += "<br>Not Mapped: " + unmapped;
			}

			resp.setResponse(strMsg);

		} catch (Exception e) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse(e.getMessage());
			System.out.println(e.getMessage());
		}
		return resp;
	}

	public ServiceResponse<ORMKeyValue> ProcessAcknowledgementMessageFromDirectory(SearchCriteria searchCriteria) {

		// clearAllData();

		int total = 0;
		int success = 0;
		int errors = 0;
		int invalid = 0;
		int unmapped = 0;

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		try {
			practiceId = searchCriteria.getPractice_id();
			String logedInUser = "";

			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

					switch (pram.getName()) {
					case "registry_code":
						registryCode = pram.getValue();
						break;
					case "loged_in_user":
						logedInUser = pram.getValue();
						break;

					default:
						break;
					}
				}
			}

			String responseMsgType = "";
			String response_message = "";
			String response_path = "";

			ORMImmRegPathGet objImmRegPathGet = GetRegistryPath(practiceId, registryCode);
			response_path = objImmRegPathGet.getResponse_path();

			FileReader fr;
			BufferedReader br;
			Path sourcePath = null;
			// Path destinationPath = null;
			// File folder = new File(response_path);
			// File[] listOfFiles = folder.listFiles();

			// List<File> filesInFolder = Files.walk(Paths.get(response_path))
			// .filter(Files::isRegularFile)
			// .map(Path::toFile)
			// .collect(Collectors.toList());

			List<File> filesInFolder = Files.list(Paths.get(response_path))
					.filter(path -> path.toString().endsWith(".txt")).map(Path::toFile).collect(Collectors.toList());

			if (filesInFolder.size() > 0) {

				for (File file : filesInFolder) {
					// if (file.isFile()) {

					total++;
					try {

						fr = new FileReader(file.getPath());
						br = new BufferedReader(fr);

						// ArrayList<String> listOfLines = new ArrayList<>();
						List<String> listOfLines = new ArrayList<>();
						try {
							StringBuilder SBulder = new StringBuilder();
							String temp;

							while ((temp = br.readLine()) != null) {
								listOfLines.add(temp);
								if (SBulder.length() > 0) {
									SBulder.append(System.getProperty("line.separator"));
								}
								SBulder.append(temp);
							}

							response_message = SBulder.toString();
							br.close();
							br.close();

							sourcePath = Paths.get(file.getPath());
							// destinationPath = Paths.get(GeneralOperations.checkPath(response_path +
							// "\\Processed\\") + file.getName());

						} catch (Exception ex) {
							br.close();
							br.close();
						}

						if (listOfLines.size() > 0) {
							// MSH-9 Message Type
							responseMsgType = listOfLines.get(0).split("\\|")[8].split("\\^")[0];

							ImmRegFileProcessingStatus fileProcessingStatus = null;

							switch (responseMsgType) {
							case "ACK":
								fileProcessingStatus = ProcessACK(listOfLines, response_message, practiceId,
										logedInUser, sourcePath, "FILE", response_path, file.getName());
								break;
							case "RSP":
								// ProcessRSP(listOfLines, response_message, practice_id, User, sourcePath,
								// sponse_path, file.getName());

								fileProcessingStatus = ProcessRSP(listOfLines, response_message, practiceId,
										logedInUser, sourcePath, "FILE", response_path, file.getName());

								break;
							default:

								fileProcessingStatus = ImmRegFileProcessingStatus.INVALID_FORMAT;

								System.out.println("Response File is not in proper format.");
								Path destinationPath = Paths
										.get(FileUtil.CheckPath(response_path + "\\ERROR\\") + file.getName());
								Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

								break;
							}

							if (fileProcessingStatus.equals(ImmRegFileProcessingStatus.SUCCESS)) {
								success++;
							}
							if (fileProcessingStatus.equals(ImmRegFileProcessingStatus.ERROR)) {
								errors++;
							}
							if (fileProcessingStatus.equals(ImmRegFileProcessingStatus.INVALID_FORMAT)) {
								invalid++;
							}
							if (fileProcessingStatus.equals(ImmRegFileProcessingStatus.NOT_MAPPED)) {
								unmapped++;
							}
						}

					} catch (IOException ex) {
						ex.printStackTrace();
						// resp.setStatus(ServiceResponseStatus.ERROR);
						// resp.setResponse(ex.getMessage());

						errors++;
					}
					// }
				}

				String strMsg = "";
				if (success > 0) {
					resp.setStatus(ServiceResponseStatus.SUCCESS);
				} else {
					resp.setStatus(ServiceResponseStatus.ERROR);
				}

				strMsg = "Total Files: " + total;
				if (success > 0) {
					strMsg += "<br>Processed Successfully: " + success;
				}
				if (errors > 0) {
					strMsg += "<br>Errors: " + errors;
				}
				if (invalid > 0) {
					strMsg += "<br>Invalid format: " + invalid;
				}
				if (unmapped > 0) {
					strMsg += "<br>Not Mapped: " + unmapped;
				}

				resp.setResponse(strMsg);

			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("There is no file in the response directory.");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse(e.getMessage());
		}
		// System.out.println("Message Type:" + responseMsgType);
		return resp;

	}

	private ImmRegFileProcessingStatus ProcessACK(List<String> listOfLines, String response_message, Long practice_id,
			String User, Path sourcePath, String sourceFileType, String response_path, String fileName) {

		ImmRegFileProcessingStatus fileProcessingStatus;

		String message_status = "";
		String message_id = "";
		String error_severity_main = "";
		ORMImmRegMsgErrorsSave objError = null;
		Path destinationPath;
		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();
		try {
			for (String strLine : listOfLines) {

				System.out.println(strLine);

				String[] segments = strLine.split("\\|");
				if (segments[0].equalsIgnoreCase("MSA")) {
					message_status = segments[1];
					if (segments.length > 2) {
						message_id = segments[2];
					}
				} else if (segments[0].equalsIgnoreCase("ERR")) {

					objError = new ORMImmRegMsgErrorsSave();

					if (segments.length >= 2) {
						String eLocation = segments[2].replaceAll("\\^", ".");
						objError.setError_location(eLocation);
					}
					if (segments.length >= 3) {
						if (GeneralOperations.isNotNullorEmpty(segments[3])) {
							String eCode = segments[3].split("\\^")[0];
							String eDesc = segments[3].split("\\^")[1];
							objError.setError_code(eCode);
							objError.setError_description(eDesc);
						}
					}
					if (segments.length >= 4) {

						objError.setSeverity(segments[4]);

						// if E exists then ignore I and W for Main Msg.
						if (!error_severity_main.equalsIgnoreCase("E")) {
							error_severity_main = segments[4];
						}
					}

					if (segments.length >= 8) {

						if (GeneralOperations.isNotNullorEmpty(segments[8])) {
							objError.setUser_message(segments[8]);
						}

						objError.setMessage_id(message_id);
						objError.setDeleted(false);
						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objError));
					}
				}
			}

			String query = " select top 1 message_id from immunization_registry_messages where message_id='"
					+ message_id + "'";
			if (GeneralOperations.isNotNullorEmpty(db.getQuerySingleResult(query))) {

				String strQuery = " update immunization_registry_messages set message_status='" + message_status + "', "
						+ " response_message='" + response_message.replaceAll("'", "''") + "'," + " resend_enable=0,"
						+ " date_modified=getdate(),modified_user='" + User + "' where convert(varchar,message_id)='"
						+ message_id + "' ";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				strQuery = "update chart_immunization set registry_status='" + message_status
						+ "',registry_date=getdate() where chart_immunization_id in (select chart_imm_id from imm_reg_message_immunizations where message_id='"
						+ message_id + "') ";
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				destinationPath = Paths.get(FileUtil.CheckPath(response_path + "\\Processed\\") + fileName);

				if (sourceFileType.equalsIgnoreCase("FILE")) {
					Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
				} else if (sourceFileType.equalsIgnoreCase("STRING_DATA")) {
					// File destinationFile = new File(GeneralOperations.CheckPath(response_path),
					// fileName);
					FileUtil.UploadStringDataToFile(response_message, response_path + "\\Processed\\" + fileName);
				}

				int result = db.AddUpdateEntityWrapper(lstEntityWrapper);

				if (result == 0) {
					fileProcessingStatus = ImmRegFileProcessingStatus.ERROR;
					// resp.setStatus(ServiceResponseStatus.ERROR);
					// resp.setResponse("An Error Occured while saving record.");
				} else {
					fileProcessingStatus = ImmRegFileProcessingStatus.SUCCESS;
					// resp.setStatus(ServiceResponseStatus.SUCCESS);
					// resp.setResponse("Data has been saved successfully.");
					// resp.setResult(message_id.toString());
				}

			} else {
				fileProcessingStatus = ImmRegFileProcessingStatus.NOT_MAPPED;

				// System.out.println("Response File is not mapped with EHR Data.");
				destinationPath = Paths.get(FileUtil.CheckPath(response_path + "\\UnMapped\\") + fileName);
				Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

				// resp.setStatus(ServiceResponseStatus.ERROR);
				// resp.setResponse("Response File is not mapped with EHR Data.");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// resp.setStatus(ServiceResponseStatus.ERROR);
			// resp.setResponse(e.getMessage());
			fileProcessingStatus = ImmRegFileProcessingStatus.ERROR;
		}
		return fileProcessingStatus;

	}

	public ImmRegFileProcessingStatus ProcessRSP(List<String> listOfLines, String response_message, Long practice_id,
			String User, Path sourcePath, String sourceFileType, String response_path, String fileName)// Response to
																										// Query by
																										// parameter
	{

		ImmRegFileProcessingStatus fileProcessingStatus;
		String message_status = "";
		String message_id = "";

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		Path destinationPath;
		String query_response_status = "";
		String query_tag_id = "";

		Boolean MSAFetched = false;
		Boolean QAKFetched = false;
		for (String strLine : listOfLines) {

			System.out.println(strLine);

			String[] segments = strLine.split("\\|");
			if (segments[0].equalsIgnoreCase("MSA")) {
				MSAFetched = true;
				message_status = segments[1];
				if (segments.length > 2) {
					message_id = segments[2];
				}
			}
			if (segments[0].equalsIgnoreCase("QAK")) {
				QAKFetched = true;
				query_tag_id = segments[1];
				if (segments.length > 2) {
					query_response_status = segments[2];
				}
			}
			if (MSAFetched && QAKFetched) {
				break;
			}
		}

		// HibernateTransactionUtil hibernate = HibernateTransactionUtil.getInstance();
		// int res = hibernate.CustomUpdate(strQuery);

		try {

			String query = " select top 1 message_id from immunization_registry_messages where message_id='"
					+ message_id + "'";
			if (GeneralOperations.isNotNullorEmpty(db.getQuerySingleResult(query))) {

				String strQuery = " update immunization_registry_messages set message_status='" + message_status + "', "
						+ " response_message='" + response_message.replaceAll("'", "''") + "'," + " resend_enable=0,"
						+ " date_modified=getdate(),modified_user='" + User + "' where convert(varchar,message_id)='"
						+ message_id + "' ;";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				strQuery = " update imm_registry_query_message_details set response_status='" + query_response_status
						+ "' where convert(varchar,id)='" + query_tag_id + "' ;";
				// hibernate.CustomUpdate(strQuery);
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));

				destinationPath = Paths.get(FileUtil.CheckPath(response_path + "\\Processed\\") + fileName);

				if (sourceFileType.equalsIgnoreCase("FILE")) {
					Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
				} else if (sourceFileType.equalsIgnoreCase("STRING_DATA")) {
					FileUtil.UploadStringDataToFile(response_message, response_path + "\\Processed\\" + fileName);
				}

				int result = db.AddUpdateEntityWrapper(lstEntityWrapper);

				if (result == 0) {
					fileProcessingStatus = ImmRegFileProcessingStatus.ERROR;

				} else {
					fileProcessingStatus = ImmRegFileProcessingStatus.SUCCESS;

				}

			} else {
				fileProcessingStatus = ImmRegFileProcessingStatus.NOT_MAPPED;

				// System.out.println("Response File is not mapped with EHR Data.");
				destinationPath = Paths.get(FileUtil.CheckPath(response_path + "\\UnMapped\\") + fileName);
				Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			// result = "ERROR~" + ex.getMessage();
			// return result;
			fileProcessingStatus = ImmRegFileProcessingStatus.ERROR;
		}

		return fileProcessingStatus;

	}
}
