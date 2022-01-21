package com.ihc.ehr.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import com.ihc.ehr.dao.PatientDAOImpl;
import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.HL7FileGenerationResult;
import com.ihc.ehr.model.ORMGetEncounterProblemDetail;
import com.ihc.ehr.model.ORMGetPatientRaceEthnicity;
import com.ihc.ehr.model.ORMPHSPatientInfoGet;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;

//Do Not use @AutoWired for this class.
//This class needs to be instantiated each time 
public class PHS_SyndromicSurveillanceOperationsMU3 {

	private DBOperations db;
	private PatientDAOImpl patientDAOImpl;

	private final String seperator = "|"; // Aurgument seperator
	private final String caret = "^"; // sub-Aurgument-seperator
	private final String encoding_characters = "^~\\&"; // encoding characters.
	private final String strApplicationName = "Instant Chart";

	Date date_current;
	String date_current_yyyy_MM_dd_HH_mm_ss_String;	

	private String strMessageTypeTags = "";
	private String messageTypeCode = "";

	private String strDischargeDisposition = "";
	private String practice_short_name = "";
	private String practice_npi = "";

	private String strMSH = "";
	private String strPID = "";
	private String strEVN = "";
	private String strPV1 = "";
	private String strPV2 = "";
	private String strOBX = "";
	private String strDG1 = "";

	private Integer age = 0;
	private String ageUnit = "";
	private String ageUnitDescription = "";

	private Integer height = 0;
	private String heightUnit = "";
	private String heightUnitDescription = "";

	private Integer weight = 0;
	private String weightUnit = "";
	private String weightUnitDescription = "";

	private String visitDateTime = "";
	private String dischargeDateTime = "";
	private String chiefComplaint = "";
	private String visit_reason = "";
	private String visit_id = "";
	private String dig_codes = "";

	private String smoking_status_code = "";
	private String smoking_status_description = "";

	private String visit_type = "";
	private String visit_type_code = "";

	private ORMPHSPatientInfoGet patientInfo;
	List<ORMGetPatientRaceEthnicity> lstRaceEthnicity;

	private String eventFacility;
	private String eventFacilityUID;
	private String eventFacilityUIDType;

	private String sendingFacility;
	private String sendingFacilityUID;
	private String sendingFacilityUIDType;

	private String countyCode;
	private int processing_id = 1;

	List<HL7FileGenerationResult> lstImmunizationFileGenerationResult = new ArrayList<>();	
	
	public PHS_SyndromicSurveillanceOperationsMU3(DBOperations db,PatientDAOImpl patientDAOImpl) {
		this.db=db;
		this.patientDAOImpl=patientDAOImpl;
	}

	private void clearAllData() {

		lstImmunizationFileGenerationResult = new ArrayList<>();
		
		date_current = new Date();
		date_current_yyyy_MM_dd_HH_mm_ss_String = DateTimeUtil.GetStringFromDate(date_current,
				DateFormatEnum.yyyy_MM_dd_HH_mm_ss);
		
		strMessageTypeTags = "";
		messageTypeCode = "";

		strDischargeDisposition = "";
		practice_short_name = "";
		practice_npi = "";

		strMSH = "";
		strPID = "";
		strEVN = "";
		strPV1 = "";
		strPV2 = "";
		strOBX = "";
		strDG1 = "";

		age = 0;
		ageUnit = "";
		ageUnitDescription = "";

		height = 0;
		heightUnit = "";
		heightUnitDescription = "";

		weight = 0;
		weightUnit = "";
		weightUnitDescription = "";

		visitDateTime = "";
		dischargeDateTime = "";
		chiefComplaint = "";
		visit_reason = "";
		visit_id = "";
		dig_codes = "";

		smoking_status_code = "";
		smoking_status_description = "";

		visit_type = "";
		visit_type_code = "";

		patientInfo=null;
		lstRaceEthnicity=null;

		eventFacility= "";
		eventFacilityUID= "";
		eventFacilityUIDType= "";

		sendingFacility= "";
		sendingFacilityUID= "";
		sendingFacilityUIDType= "";

		countyCode= "";
		processing_id = 1;

	}

	public List<HL7FileGenerationResult> GenerateSyndromicSurveillanceMessage(
			SearchCriteria searchCriteria)
			//Long practice_id, Long patient_id,
			//Long chart_id, String logniUser, String status, String probem_ids, String messageType) 
	{

		clearAllData();

		Long LogID;
		String strHL7_Complete;
		processing_id = 1;
		
		Long practiceId=searchCriteria.getPractice_id();
		Long patientId=null;
		Long chartId=null;
		String LogedInUser="";		
		String problemIds="";
		String Status="";
		
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {			
			
			for (SearchCriteriaParamList param : searchCriteria.getParam_list()) {
				
				switch (param.getName()) {
				case "patient_id":
					patientId=Long.parseLong(param.getValue());
					break;
				case "chart_id":
					chartId=Long.parseLong(param.getValue());
					break;
				case "loged_in_user":
					LogedInUser=param.getValue();
					break;
				case "message_type_code":
					messageTypeCode=param.getValue();
					break;
				case "problem_ids":
					problemIds=param.getValue();
					break;
				case "status":
					Status=param.getValue();
					break;
					

				default:
					break;
				}
			}

		}
		
		

		patientInfo = getPatientInfo(chartId);
		lstRaceEthnicity = patientDAOImpl.getPatientRaceEthnicty("ALL", patientId);
		
		practice_short_name = patientInfo.getPractice_short_name();
		practice_npi = patientInfo.getPractice_npi();

		if (GeneralOperations.isNotNullorEmpty(patientInfo.getCounty_code())) {
			countyCode = patientInfo.getCounty_code();
		} else {
			countyCode = "NA";
		}

		if (GeneralOperations.isNullorEmpty(practice_npi)) {

			addFileGenerationResultToList((long) processing_id++, "PHS", "ERROR", "Practice NPI is missing.",
					"Practice Info");

			return lstImmunizationFileGenerationResult;

		} else {
			eventFacility = practice_short_name;
			eventFacilityUID = practice_npi;
			eventFacilityUIDType = "NPI";

			sendingFacility = practice_short_name;
			sendingFacilityUID = practice_npi;
			sendingFacilityUIDType = "NPI";
		}

		strDischargeDisposition = "";
		//messageTypeCode = messageType;
		switch (messageTypeCode) {

		case "A04": // New Visit/Register patient
			strMessageTypeTags = "ADT^A04^ADT_A01";
			break;
		case "A03":// Discharge
			strMessageTypeTags = "ADT^A03^ADT_A03";
			if (GeneralOperations.isNotNullorEmpty(patientInfo.getDischarge_code())) {
				strDischargeDisposition = patientInfo.getDischarge_code();
			}
			break;
		case "A08": // Update
			break;
		default:
			strMessageTypeTags = "ADT^A01^ADT_A01";
			break;

		}

		// for inpatient (optional)
		if (patientInfo.getVisit_reason().equalsIgnoreCase("Urgent Care")) {

			// Facility / Visit Type (Syndromic Surveillance)
			// Code Code System Description
			// 1021-5 HSLOC Inpatient practice setting
			// 261QE0002X HCPTNUCC Emergency Care
			// 261QM2500X HCPTNUCC Medical Specialty
			// 261QP2300X HCPTNUCC Primary Care
			// 261QU0200X HCPTNUCC Urgent Care
			visit_type = "Urgent Care";
			visit_type_code = "261QU0200X";

		} else // for ambulatory standard. (Required)
		{
			visit_type = "Primary Care";
			visit_type_code = "261QP2300X";
		}

		
		visit_id = patientInfo.getChart_id().toString();
		visitDateTime = patientInfo.getVisit_date();
		dischargeDateTime = patientInfo.getDischarge_date();
		chiefComplaint = patientInfo.getHpi_detail();
		visit_reason = patientInfo.getReason_detail();
		
		if (patientInfo.getAge_months() > 0) {
			age = (patientInfo.getAge_years() * 12 + patientInfo.getAge_months());
			ageUnit = "mo";
			ageUnitDescription = "month";
		} else if (patientInfo.getAge_years() > 0) {
			age = patientInfo.getAge_years();
			ageUnit = "a";
			ageUnitDescription = "year";
		} else if (patientInfo.getAge_days() > 0) {
			age = patientInfo.getAge_days();
			ageUnit = "d";
			ageUnitDescription = "day";
		}

		if (GeneralOperations.isNotNullorEmpty(patientInfo.getHeight_feet())) {
			Integer ft = Integer.parseInt(patientInfo.getHeight_feet().split("\\.")[0]);
			Integer inc = Integer.parseInt(patientInfo.getHeight_feet().split("\\.")[1]);

			height = ft * 12 + inc;
			if (inc > 0) {

				heightUnit = "[in_us]";
				heightUnitDescription = "inch";
			} else if (ft > 0) {

				heightUnit = "[ft_us]";
				heightUnitDescription = "foot";
			}
		}

		if (GeneralOperations.isNotNullorEmpty(patientInfo.getWeight_lbs())) {

			Integer lb = Integer.parseInt(patientInfo.getWeight_lbs().split("\\.")[0]);
			// Integer onc=Integer.parseInt( patientInfo.getWeight_lbs().split("\\.")[1]);

			weight = lb;
			weightUnit = "[lb_av]";
			weightUnitDescription = "pound";
		}
		if (GeneralOperations.isNotNullorEmpty(patientInfo.getSmoking_snomed())) {

			smoking_status_code = patientInfo.getSmoking_snomed();
			smoking_status_description = patientInfo.getSmoking_status();
		}

		LogID = db.IDGenerator("PHS_Log", practiceId);

		Get_MSH(LogID);
		Get_EVN();
		Get_PID();
		getPHS_PV1();
		getPHS_PV2();
		getDG1(problemIds);
		getOBX();

		// for Urgent Care Case (Optional)
		if (patientInfo.getVisit_reason().equalsIgnoreCase("Urgent Care")) {
			if (messageTypeCode.equalsIgnoreCase("A04")) {
				strHL7_Complete = strMSH + strEVN + strPID + strPV1 + strPV2 + strOBX + strDG1;
			} else {
				strHL7_Complete = strMSH + strEVN + strPID + strPV1 + strPV2 + strDG1 + strOBX;
			}
		} else { // for Standard Ambulatory (Requried)
			strHL7_Complete = strMSH + strEVN + strPID + strPV1 + strPV2 + strOBX + strDG1;
		}

		String strQuery = " INSERT INTO PHS_LOG (MESSAGE_ID,PATIENT_id,CHART_ID,CLAIM_id,DIAG_CODE,MESSAGE_STRING,DATE_CREATED,CREATED_user,Is_send,DATE_MODIFIED,MODIFIED_user,status) "
				+ " VALUES (" + LogID + ",'" + patientId + "','" + chartId + "','','" + dig_codes + "','"
				+ strHL7_Complete + "',getdate(),'" + LogedInUser + "','true',getdate(),'" + LogedInUser + "','" + Status
				+ "')";

		db.ExecuteUpdateQuery(strQuery);

		addFileGenerationResultToList(LogID, "PHS", "SUCCESSFULL", strHL7_Complete, "Genereated File");

		return lstImmunizationFileGenerationResult;// LogID+"~"+strHL7_Complete;
	}

	// MSH Segment (Total 21 Segments)
	public String Get_MSH(Long LogId) {

		strMSH = "";
		try {

			// @formatter:off
			strMSH = // 1- MSH
					"MSH" + seperator
					+ // 2- Encoding Characters
					encoding_characters + seperator
					+ // 3- Sending Application (O)
					strApplicationName + seperator
					+ // 4- Sending Facility (R)
					sendingFacility+caret+sendingFacilityUID+caret+sendingFacilityUIDType + seperator
					+ // 5- Receiving Application 
					"" + seperator
					+ // 6- Receiving Facility 
					"" + seperator
					+ // 7- D/T of Message (O)
					date_current_yyyy_MM_dd_HH_mm_ss_String + seperator                    
					+ // 8- Empty
					"" + seperator
					+ // 9- Message Type (R)
					strMessageTypeTags + seperator
					+ // 10- Message Control ID (order ID) (R)
					LogId.toString() + seperator
					+ // 11- Processing ID (R)
					"P" + seperator
					+ // 12- Version ID (R)
					"2.5.1" + seperator
					+ // 13-
					"" + seperator
					+ // 14- 
					"" + seperator
					+ // MSH-15	Accept Acknowledgment Type 
					"AL" + seperator
					+ // MSH-16	Application Acknowledgment Type
					"AL" + seperator
					+ // 17- 
					"" + seperator
					+ // 18- 
					"" + seperator
					+ // 19- 
					"" + seperator
					+ // 20- 
					"" + seperator
					+ // 21- Message Profile Identifier
					"PH_SS-Ack" + caret + "SS Sender" + caret + "2.16.840.1.114222.4.10.3" + caret + "ISO";
			// @formatter:on

		} catch (Exception e) {

			e.printStackTrace();
		}
		return strMSH;
	}

	// EVN Segment (Total 7 Segments)
	public Boolean Get_EVN() {

		strEVN = "";
		try {

			// @formatter:off
			strEVN = "\n"
					+ "EVN" + seperator
					// 1
					+ "" + seperator
					// EVN-2	Recorded Date/Time
					+ date_current_yyyy_MM_dd_HH_mm_ss_String + seperator
					// 3- Sending Application (O)
					+ seperator
					// 4- Sending Facility (R)
					+ seperator
					// 5- Receiving Application 
					+ seperator
					// 6- Receiving Facility 
					+ seperator
					+ // EVN-7	Event Facility
					eventFacility+caret+eventFacilityUID+caret+eventFacilityUIDType ;
			// @formatter:on

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

	// PID
	public Boolean Get_PID() {

		strPID = "";
		try {

			String GenderCode = patientInfo.getGender_code();
			if (GeneralOperations.isNullorEmpty(GenderCode)) {
				GenderCode = "U";
			}

			String raceSegment = "";
			String ethnicitySegment = "";

			for (ORMGetPatientRaceEthnicity objRacEthnicty : lstRaceEthnicity) {

				switch (objRacEthnicty.getEntry_type()) {
				case "RACE":
					if (GeneralOperations.isNotNullorEmpty(raceSegment)) {
						raceSegment += "~";
					}
					raceSegment += objRacEthnicty.getOmb_code() + caret + objRacEthnicty.getOmb_description() + caret
							+ "CDCREC";
					break;
				case "ETHNICITY":
					if (GeneralOperations.isNotNullorEmpty(ethnicitySegment)) {
						ethnicitySegment += "~";
					}
					ethnicitySegment += objRacEthnicty.getOmb_code() + caret + objRacEthnicty.getOmb_description()
					+ caret + "CDCREC";
					break;

				default:
					break;
				}

			}
			
			String dobyyyyMMdd=DateTimeUtil.GetStringFromDate(DateTimeUtil.GetDateFromString(patientInfo.getDob(),DateFormatEnum.MM_dd_yyyy),DateFormatEnum.yyyyMMdd);
			if(GeneralOperations.isNullorEmpty(dobyyyyMMdd)) {
				addFileGenerationResultToList((long) processing_id++, "PHS", "ERROR", "Patient DOB is Missing.",
						"Patient Info");

			}

			// @formatter:off
			strPID = "\n"
					+ "PID" + seperator
					+ "1" + seperator
					+ // 2- External Patient ID (Patient ID) (C)
					"" + seperator // empty
					+ // PID-3	Patient Identifier List
					patientInfo.getPatient_id() + caret + "" + caret + "" + caret + eventFacility+"&"+eventFacilityUID+"&"+eventFacilityUIDType + caret + "MR" + seperator
					+//4- Alternate Patient ID (C,r)
					"" + seperator
					// PID-5[1] Patient Name
					+// PID-5[2] Patient Name
					"~" + caret + caret + caret + caret + caret + caret + "S" + seperator //When the name of the patient is known, but not desired to be sent, HL7 recommends the following: |~^^^^^^S|. The "S" for the name type code (PID.5.7) in the second name field indicates that it is a pseudonym.
					+ // 6- Mother’s Maiden Name (O)
					"" + seperator
					+ // 7- Date of Birth (O)
					dobyyyyMMdd + seperator
					+ // PID-8	Administrative Sex                   
					GenderCode + seperator
					+ // 9- Patient Alias (O)
					"" + seperator
					+// PID-10[1] Race
					raceSegment+seperator                    
					//patientInfo.getRace_code() + caret + patientInfo.getRace() + caret + "CDCREC" + seperator
					//PID-10[2]	Race                    
					+// 11- Patient Address
					patientInfo.getAdress() + caret + "" + caret + patientInfo.getCity() + caret + patientInfo.getState()+ caret + patientInfo.getZip() + caret + "USA" + caret + "" + caret + "" + caret + countyCode + seperator
					+ // 12- Business Phone Number (O)
					"" + seperator
					+ // 13- Home Phone Number (O)
					"" + seperator
					// 14- 
					+ "" + seperator
					+ // 15- 
					"" + seperator
					+ // 16- 
					"" + seperator
					+ // 17- 
					"" + seperator
					+ // 18-  
					"" + seperator
					+ // 19-  
					"" + seperator
					+ // 20-  
					"" + seperator
					+ // 21-  
					"" + seperator
					+// 22- Ethnic Group (Identifier^Text^Name of Coding System)
					ethnicitySegment;
			//patientInfo.getEthnicity_code() + caret + patientInfo.getEthnicity() + caret + "CDCREC";
			// @formatter:on

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
		return true;
	}

	// PV1
	public Boolean getPHS_PV1() {

		// B HL70004 Obstetrics
		// D HL70004 Direct admit
		// E HL70004 Emergency
		// I HL70004 Inpatient
		// O HL70004 Outpatient
		// P HL70004 Preadmit
		// R HL70004 Recurring patient
		// V HL70004 Observation patient

		try {
			// @formatter:off
			strPV1 = "\n"
					+ "PV1" + seperator
					// 1
					+ "1" + seperator
					+//PV1-2	Patient Class
					"O"+ seperator // O==> OutPatient
					// 3,4,5,6,7,8,9,10,11 12 13 14 15 16 17 18 (Empty)
					+ seperator + seperator + seperator + seperator + seperator + seperator + seperator + seperator
					+ seperator + seperator + seperator + seperator + seperator + seperator + seperator + seperator
					// PV1-19	Visit Number
					+ visit_id + caret + caret + caret +eventFacility+"&"+eventFacilityUID+"&"+eventFacilityUIDType+ caret + "VN" + seperator
					//20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 (Empty)
					+ seperator + seperator + seperator + seperator + seperator + seperator + seperator + seperator + seperator
					+ seperator + seperator + seperator + seperator + seperator + seperator + seperator
					// 36 Discharge Disposition
					+ strDischargeDisposition + seperator // 01 = Discharged to home or self care (routine discharge)
					// 37 38 39 40 41 42 43 (Empty)
					+ seperator + seperator + seperator + seperator + seperator + seperator + seperator
					// PV1-44	Admit Date/Time
					+ visitDateTime;

			// @formatter:on
			if (messageTypeCode.equalsIgnoreCase("A03") && GeneralOperations.isNotNullorEmpty(dischargeDateTime)) {
				strPV1 += seperator + dischargeDateTime;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return true;
	}

	// additional visit information.
	public Boolean getPHS_PV2() {

		try {
			// PV2|||^Fever, cough, earache
			strPV2 = "";
			if (GeneralOperations.isNotNullorEmpty(visit_reason)) {
				// @formatter:off
				strPV2= "\n"
						+ "PV2" + seperator
						// 1,2
						+ seperator + seperator                     
						//PV2-3	Admit Reason
						+caret+visit_reason;
				// @formatter:on
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return true;
	}

	// get Diagnosis
	public Boolean getDG1(String problem_ids) {

		try {
			strDG1 = "";
			dig_codes = "";
			int dg1_seq_no = 1;

			String CodingSystem = "";
			String strDiagnoseType = "";

			List<ORMGetEncounterProblemDetail> lstProblems = getProblemDetail(problem_ids);

			if (lstProblems == null || lstProblems.isEmpty()) {
				System.out.println("Get_DG1: Problems not found. ");
				return true;
			}

			for (ORMGetEncounterProblemDetail objProblem : lstProblems) {

				// if (objProblem.getStatus().equalsIgnoreCase("active"))
				{

					if (dig_codes != "") {
						dig_codes += ", ";
					}

					if (objProblem.getCode_type().equalsIgnoreCase("SnomedCT")) {
						CodingSystem = "SCT";
					} else if (objProblem.getCode_type().equalsIgnoreCase("ICD-9")) {
						CodingSystem = "I9CDX";
					} else { // ICD-10
						CodingSystem = "I10C";
					}

					if (GeneralOperations.isNotNullorEmpty(objProblem.getDiag_type())) {
						strDiagnoseType = objProblem.getDiag_type();
					} else {
						strDiagnoseType = "F";
					}

					dig_codes += objProblem.getDiag_code();
					// @formatter:off
					strDG1 += "\n"
							+ "DG1" + seperator
							// 1
							+ dg1_seq_no++ + seperator
							//2 
							+ "" + seperator
							// DG1-3	Diagnosis Code - DG1
							+ objProblem.getDiag_code() + caret + objProblem.getDiag_description() + caret + CodingSystem + seperator
							// 4 empty
							+ "" + seperator
							//5 
							+ "" + seperator
							//DG1-6	Diagnosis Type
							+ strDiagnoseType;
					// @formatter:on
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return true;
	}

	// OBX
	public Boolean getOBX() {

		try {
			strOBX = "";

			int segment_no = 1;

			// OBX-1
			// OBX|1|CWE|SS003^Facility/Visit Type^PHINQUESTION||261QU0200X^Urgent
			// Care^HCPTNUCC^^^^^^Urgent Care Center||||||F
			// @formatter:off
			strOBX += "\n"
					+ "OBX" + seperator
					// 1
					+ segment_no++ + seperator
					//2 Value Type
					+ "CWE" + seperator
					// OBX-3	Observation Identifier
					+ "SS003" + caret + "Facility/Visit Type" + caret+"PHINQUESTION"+seperator
					// 4 empty
					+ seperator
					//OBX-5	Observation Value
					+ visit_type_code + caret + visit_type + caret + "HCPTNUCC" +caret+caret+caret+caret+caret+caret+eventFacility+ seperator
					//6
					+ seperator
					//7
					+ seperator
					//8
					+ seperator
					//9
					+ seperator
					//10
					+ seperator
					//OBX-11	Observation Result Status
					+ "F";

			// OBX-2
			//OBX|2|NM|21612-7^Age at Time Patient Reported^LN||6|mo^month^UCUM|||||F            
			// @formatter:on
			if (age > 0) {
				// @formatter:off
				strOBX += "\n"
						+ "OBX" + seperator
						// 1
						+ segment_no++ + seperator
						//2 Value Type
						+ "NM" + seperator
						// OBX-3	Observation Identifier
						+ "21612-7" + caret + "Age at Time Patient Reported" +caret+"LN"+ seperator
						// 4 empty
						+ seperator
						// OBX-5	Observation Value
						+ age +seperator
						+// OBX-6	Units 
						ageUnit + caret+ ageUnitDescription +caret+ "UCUM" + seperator                    
						//7
						+ seperator
						//8
						+ seperator
						//9
						+ seperator
						//10
						+ seperator
						//OBX-11	Observation Result Status
						+ "F";
				// @formatter:on
			}
			// OBX-3
			// OBX|3|TX|8661-1^Chief complaint:Find:Pt:Patient:Nom:Reported^LN||Mother
			// states that patient has fever, cough, and earache||||||F
			if (GeneralOperations.isNotNullorEmpty(chiefComplaint)) {
				// @formatter:off
				strOBX += "\n"
						+ "OBX" + seperator
						// 1
						+ segment_no++ + seperator
						//2 Value Type
						+ "TX" + seperator
						// 3 Observation Identifier
						+ "8661-1" + caret + "Chief complaint:Find:Pt:Patient:Nom:Reported" + caret + "LN" + seperator
						// 4 empty
						+ seperator
						//OBX-5	Observation Value                        
						+ chiefComplaint + seperator
						// OBX-6	Units 
						+ seperator
						//7
						+ seperator
						//8
						+ seperator
						//9
						+ seperator
						//10
						+ seperator
						//OBX-11	Observation Result Status
						+ "F";
				// @formatter:on

				// OBX-4
				// OBX|4|NM|8302-2^Height^LN||27|[in_us]^inch^UCUM|||||F
				if (height > 0) {
					// @formatter:off
					strOBX += "\n"
							+ "OBX" + seperator
							// 1
							+ segment_no++ + seperator
							//2 Value Type
							+ "NM" + seperator
							// 3 Observation Identifier
							+ "8302-2" + caret + "Height" + caret + "LN" + seperator
							// 4 empty
							+ seperator
							//OBX-5	Observation Value                        
							+ height + seperator
							+// OBX-6	Units 
							heightUnit + caret+ heightUnitDescription +caret+ "UCUM" + seperator
							//7
							+ seperator
							//8
							+ seperator
							//9
							+ seperator
							//10
							+ seperator
							//OBX-11	Observation Result Status
							+ "F";
					// @formatter:on
				}

				// OBX-5
				// OBX|5|NM|3141-9^Weight^LN||17|[lb_av]^pound^UCUM|||||F
				if (weight > 0) {
					// @formatter:off
					strOBX += "\n"
							+ "OBX" + seperator
							// 1
							+ segment_no++ + seperator
							//2 Value Type
							+ "NM" + seperator
							// 3 Observation Identifier
							+ "3141-9" + caret + "Weight" + caret + "LN" + seperator
							// 4 empty
							+ seperator
							//OBX-5	Observation Value                        
							+ weight + seperator
							+// OBX-6	Units 
							weightUnit + caret+ weightUnitDescription + caret+"UCUM" + seperator
							//7
							+ seperator
							//8
							+ seperator
							//9
							+ seperator
							//10
							+ seperator
							//OBX-11	Observation Result Status
							+ "F";
					// @formatter:on
				}

				// OBX-6
				// OBX|6|CWE|72166-2^Tobacco Smoking Status^LN||266919005^Never
				// smoker^SCT||||||F
				if (GeneralOperations.isNotNullorEmpty(smoking_status_code)) {
					// @formatter:off
					strOBX += "\n"
							+ "OBX" + seperator
							// 1
							+ segment_no++ + seperator
							//2 Value Type
							+ "CWE" + seperator
							// 3 Observation Identifier
							+ "72166-2" + caret + "Tobacco Smoking Status" + caret + "LN" + seperator
							// 4 empty
							+ seperator
							//OBX-5	Observation Value                        
							+ smoking_status_code + caret + smoking_status_description +caret+ "SCT" + seperator
							// OBX-6	Units 
							+ seperator
							//7
							+ seperator
							//8
							+ seperator
							//9
							+ seperator
							//10
							+ seperator
							//OBX-11	Observation Result Status
							+ "F";
					// @formatter:on
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return true;
	}

	private List<ORMGetEncounterProblemDetail> getProblemDetail(String problemIds) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("problem_ids", problemIds.toString(), String.class, ParameterMode.IN));		
		List<ORMGetEncounterProblemDetail> lst = db.getStoreProcedureData("spGetPatientProblemsForPHSMessage",
				ORMGetEncounterProblemDetail.class, lstParam);


		return lst;
	}
	private ORMPHSPatientInfoGet getPatientInfo(Long chartId) {

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chartId.toString(), String.class, ParameterMode.IN));
		List<ORMPHSPatientInfoGet> lst = db.getStoreProcedureData("spgetPHS_PatientInfo", ORMPHSPatientInfoGet.class,
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

}
