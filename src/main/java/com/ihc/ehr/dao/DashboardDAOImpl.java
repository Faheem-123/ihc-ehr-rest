package com.ihc.ehr.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ORMClaimReminderGet;
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
import com.ihc.ehr.model.ORMProviderList;
import com.ihc.ehr.model.ORMSevenColumnGeneric;
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.ORMgetDashBoardModule;
import com.ihc.ehr.model.ORMgetPrescriptionAllergies;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.newCrop.newCropPreproduction;
import com.ihc.newCrop.source.RenewalListDetailResultV4;



@Repository
public class DashboardDAOImpl implements DashboardDAO {

	@Autowired
	private DBOperations db;
	@Autowired
	private GeneralDAOImpl objGeneral;
	 
	private final DateFormat dtInput_formaterMMDDYYYY = new SimpleDateFormat("MM/dd/yyyy");
	private final DateFormat dt_formater_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private final DateFormat dt_formater_mdyyampm = new SimpleDateFormat("M/d/yy h:mm:ss a");
	
	@Override
	public List<ORMDashBoardCheckInPatient> getCheckInPatient(SearchCriteria searchcriteria) {
		// TODO Auto-generated method stub		
		String search=searchcriteria.getCriteria();
		
		//searchCriteriaString += " and p.practice_id='" + practice_id.toString() + "' ";
		
		System.out.println("searchCriteriaString:"+search);

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("PracticeID", searchcriteria.getPractice_id().toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", search, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetCheckinPatient", ORMDashBoardCheckInPatient.class, lstParam);
		
	}
	//
	@Override
	public List<ORMDashBoardLab> getLabPendingResults(Long ProviderID, Long locationID, String type, Long LoginUserID) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam=new ArrayList<>();
		lstParam.add(new SpParameters("ProviderID",ProviderID.toString().equals("-1")?"":ProviderID.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("locationID",locationID.toString().equals("-1")?"":locationID.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("type",type.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("LoginUserID",LoginUserID.toString(), String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetPendingResult", ORMDashBoardLab.class, lstParam);
	}
	//
	@Override
	public List<ORMDashboardPendingEncounter> getPendingEncounter(Long provider_id, Long location_id, SearchCriteria  searchCriteria){
		String location_id1 = "";
		
		if(location_id==-1) {
			location_id1 = "";
		}else {
			location_id1 = location_id.toString();
		}
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("ProviderID",provider_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("locationID",location_id1.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("Criteria", searchCriteria.getCriteria().toString() , String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPendingEncounters", ORMDashboardPendingEncounter.class, lstParam);
	}
	//
	@Override
	public List<ORMGetCashRegisterDayWisePayment> getCashRegisterLastWeekDayWisePayment(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam=new ArrayList<>();
		
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}

		return db.getStoreProcedureData("spGetCashRegisterLastWeekDayWisePayment", ORMGetCashRegisterDayWisePayment.class, lstParam);
	}
	
	//List<ORMDashBoarCashReg> getCashPaymentDetails(String practice_id, String date, String provider_id, String location_id);
	//dashboard cash register details
	@Override
	public List<ORMDashBoarCashReg> getCashPaymentDetails(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam=new ArrayList<>();		
		
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}
		
		return db.getStoreProcedureData("proGetCashRegisterChartDetail", ORMDashBoarCashReg.class, lstParam);
	}
	
	//List<ORMDashBoardMessages> getUnReadMessages(Long ReciverID);
	@Override
	public List<ORMDashBoardMessages> getUnReadMessages(Long ReciverID) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam=new ArrayList<>();
		lstParam.add(new SpParameters("ReciverID",ReciverID.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetUnreadMessage", ORMDashBoardMessages.class, lstParam);
	}
	@Override
	public int markAsRead(String id,String loginuser,String datetime,String ip){
		//List<SpParameters> lstParam=new ArrayList<>();
		String query = " update message_detail set readed = '1', modified_user='"+ loginuser +"', client_date_modified = '"+ datetime + "' where message_detail_id = '"+ id +"' ";
		return db.ExecuteUpdateQuery(query);
		//return db.getStoreProcedureData("spGetUnreadMessage", UpdateRecord.class, lstParam);
	}
	
	
	@Override
	public List<ORMFaxMyUnRead> getUnReadFaxes(String userID,String practice_id){
		List<SpParameters> lstParam=new ArrayList<>();
		lstParam.add(new SpParameters("userID",userID.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id",practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetMyFaxes", ORMFaxMyUnRead.class, lstParam);
	}
	
	@Override
	public List<ORMGetGynTodayEdd> getgynEDD(String practice_id){
		List<SpParameters> lstParam=new ArrayList<>();
		lstParam.add(new SpParameters("practice_id",practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetToDay_EDD", ORMGetGynTodayEdd.class, lstParam);
	}
	
	@Override
	public List<ORMDashBoardMissingClaims> getMissingClaims(SearchCriteria searchCriteria){
		String sp = "";
		List<SpParameters> lstParam=new ArrayList<>();
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()) {
				if(searchCriteria.getOption().toString().equals("missing")) {
					if(pram.getName().toString().equals("provider_id"))
						lstParam.add(new SpParameters("provider_id", pram.getValue().toString() , String.class, ParameterMode.IN));
					else if (pram.getName().toString().equals("location_id"))
						lstParam.add(new SpParameters("location_id", pram.getValue().toString() , String.class, ParameterMode.IN));
					
					sp = "spGetMissingClaims_dashboard";
				}else if (searchCriteria.getOption().toString().equals("draft")) {
					if(pram.getName().toString().equals("attending_physician"))
						lstParam.add(new SpParameters("attending_physician", pram.getValue().toString() , String.class, ParameterMode.IN));
					else if (pram.getName().toString().equals("location_id"))
						lstParam.add(new SpParameters("location_id", pram.getValue().toString() , String.class, ParameterMode.IN));
					
					sp = "spGetPendingClaims_dashboard";
				}
			}
			lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString() , String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData(sp, ORMDashBoardMissingClaims.class, lstParam);
	}
	@Override
	public List<ORMPrescriptionRefills> getRefills(ORMgetPrescriptionAllergies getRefills) {
		// TODO Auto-generated method stub
		newCropPreproduction objNewCrop = new newCropPreproduction();
		
		if(getRefills.getOptions().toLowerCase().equals("refills"))
		{	
			List<ORMProviderList> lstProvider = objGeneral.getProvider(Long.parseLong(getRefills.getPracticeid()));
			RenewalListDetailResultV4 objResult = objNewCrop.getAccountRefills(getRefills.isRxproduction(), getRefills.getRxusername(),getRefills.getRxpartnername(), getRefills.getRxuserpassword(), 
	        		getRefills.getPracticeid().equals("504")?"500":getRefills.getPracticeid(),getRefills.getRxsiteid());			
			System.out.println("Populating Refill List");
			return GetRefillDetails(objResult, lstProvider);//lstProvider);			
		}
		else if(getRefills.getOptions().toLowerCase().equals("staff pending"))
		{
			NodeList nList = objNewCrop.getAccountStatusDetails(getRefills.isRxproduction(), "StaffProcessing", getRefills.getRxusername(),getRefills.getRxpartnername(), getRefills.getRxuserpassword(), 
					getRefills.getPracticeid().equals("504")?"500":getRefills.getPracticeid(),getRefills.getRxsiteid(), "", "");
			return getOtherPrescrionDetails("StaffProcessing", nList, "");
	        
		}
		else if(getRefills.getOptions().toLowerCase().equals("for physician review"))
		{
			NodeList nList = objNewCrop.getAccountStatusDetails(getRefills.isRxproduction(), "AllDoctorReview", getRefills.getRxusername(),getRefills.getRxpartnername(), getRefills.getRxuserpassword(), 
					getRefills.getPracticeid().equals("504")?"500":getRefills.getPracticeid(),getRefills.getRxsiteid(), "", "");
			return getOtherPrescrionDetails("AllDoctorReview", nList, "");   
		}
		else if(getRefills.getOptions().toLowerCase().equals("physician pending"))
		{
			NodeList nList = objNewCrop.getAccountStatusDetails(getRefills.isRxproduction(), "DrReview", getRefills.getRxusername(),getRefills.getRxpartnername(), getRefills.getRxuserpassword(), 
					getRefills.getPracticeid().equals("504")?"500":getRefills.getPracticeid(),getRefills.getRxsiteid(), "", getRefills.getProviderid().toString());
			System.out.println("Populating Pysician Pending Prescription List..");
			return getOtherPrescrionDetails("DrReview", nList, "");			
		}
		else if(getRefills.getOptions().toLowerCase().equals("failed"))
		{
			NodeList nList = objNewCrop.getAccountStatusDetails(getRefills.isRxproduction(), "FailedElectronicRx", getRefills.getRxusername(),getRefills.getRxpartnername(), getRefills.getRxuserpassword(), 
					getRefills.getPracticeid().equals("504")?"500":getRefills.getPracticeid(),getRefills.getRxsiteid(), "", "");
			System.out.println("Populating Pysician Pending Prescription List..");
			return getOtherPrescrionDetails("FailedFax", nList, "");
		}
		return null;
	}
	
private List<ORMPrescriptionRefills> getOtherPrescrionDetails(String SectionType, NodeList nList,String providerID) {
        
        List<ORMPrescriptionRefills> lst = new ArrayList<ORMPrescriptionRefills>();
        System.out.print("in getOtherPrescrionDetails method");
        try {
            if (nList != null && nList.getLength() > 0) {
                ORMPrescriptionRefills objPrescRefills;
                for (int row = 0; row < nList.getLength(); row++) {
                    Node nNode = nList.item(row);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        objPrescRefills = new ORMPrescriptionRefills();
                        objPrescRefills.setPrescription_refills_id(Integer.toString(row));
                        System.out.print("\n ExternalPatientId"+eElement.getElementsByTagName("ExternalPatientId").item(0).getTextContent());
                        
                        objPrescRefills.setExt_patient_id(eElement.getElementsByTagName("ExternalPatientId").item(0).getTextContent());
                        objPrescRefills.setPat_firstname(eElement.getElementsByTagName("PatientFirstName").item(0).getTextContent());
                        objPrescRefills.setPat_mname(eElement.getElementsByTagName("PatientMiddleName").item(0).getTextContent());
                        objPrescRefills.setPat_lastname(eElement.getElementsByTagName("PatientLastName").item(0).getTextContent());
                        objPrescRefills.setPat_dob(eElement.getElementsByTagName("PatientDOB").item(0).getTextContent());
                        objPrescRefills.setExt_userid(eElement.getElementsByTagName("ExternalUserId").item(0).getTextContent());
                        objPrescRefills.setUser_firstname(eElement.getElementsByTagName("UserFirstName").item(0).getTextContent());
                        objPrescRefills.setUser_mname(eElement.getElementsByTagName("UserMiddleName").item(0).getTextContent());
                        objPrescRefills.setUser_lastname(eElement.getElementsByTagName("UserLastName").item(0).getTextContent());
                        objPrescRefills.setExt_providerid(eElement.getElementsByTagName("ExternalDoctorId").item(0).getTextContent());
                        objPrescRefills.setProv_firstname(eElement.getElementsByTagName("DoctorFirstName").item(0).getTextContent());
                        objPrescRefills.setProv_mname(eElement.getElementsByTagName("DoctorMiddleName").item(0).getTextContent());
                        objPrescRefills.setProv_lastname(eElement.getElementsByTagName("DoctorLastName").item(0).getTextContent());
                        objPrescRefills.setDrug_info(eElement.getElementsByTagName("DrugInfo").item(0).getTextContent());
                        
                        if(GeneralOperations.isNotNullorEmpty(eElement.getElementsByTagName("PrescriptionDate").item(0).getTextContent()))
                        {
//                        	need to update
                            objPrescRefills.setPrescription_date( dtInput_formaterMMDDYYYY.format((java.util.Date) dt_formater_mdyyampm.parse(eElement.getElementsByTagName("PrescriptionDate").item(0).getTextContent())));
                        	 System.out.print("\n PrescriptionDate"+eElement.getElementsByTagName("PrescriptionDate").item(0).getTextContent());
                        	 System.out.print("\n PrescriptionDate"+objPrescRefills.getPrescription_date());                        	 
                        }
                        else
                        {
                            objPrescRefills.setPrescription_date("");
                        }
                        
                        objPrescRefills.setPrescription_status(eElement.getElementsByTagName("PrescriptionStatus").item(0).getTextContent());
                        objPrescRefills.setPrescription_sub_status(eElement.getElementsByTagName("PrescriptionSubStatus").item(0).getTextContent());
                        objPrescRefills.setPrescription_archive_status(eElement.getElementsByTagName("PrescriptionArchiveStatus").item(0).getTextContent());
                        objPrescRefills.setExt_location_id(eElement.getElementsByTagName("ExternalLocationId").item(0).getTextContent());
                        objPrescRefills.setLocation_name(eElement.getElementsByTagName("LocationName").item(0).getTextContent());
                        objPrescRefills.setExt_prescription_id(eElement.getElementsByTagName("ExternalPrescriptionId").item(0).getTextContent());
                        objPrescRefills.setPrescription_guid(eElement.getElementsByTagName("PrescriptionGuid").item(0).getTextContent());
                        //if ("AllDoctorReview".equals(SectionType) && (eElement.getElementsByTagName("ExternalDoctorId").item(0).getTextContent() == null ? providerID == null : eElement.getElementsByTagName("ExternalDoctorId").item(0).getTextContent().equals(providerID))) {
                        //    objPrescRefills.setStatus_section("DrReview");
                       // } else {
                            objPrescRefills.setStatus_section(SectionType);
                        //}
                        lst.add(objPrescRefills);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

	private List<ORMPrescriptionRefills> GetRefillDetails(RenewalListDetailResultV4 objResult,List<ORMProviderList> lstProvider) {
	              
        ArrayList<ORMPrescriptionRefills> lst = new ArrayList<ORMPrescriptionRefills>();
        try {
            if (objResult != null && objResult.getResult().getRowCount() > 0) {
                
                //String generatedID = GenericOperations.IDGenerator("prescription_refills", practiceID, objResult.getResult().getRowCount());
//                HibernateTransactionUtil hibernate = HibernateTransactionUtil.getInstance();
                ORMPrescriptionRefills objPrescRefills;
                for (int row = 0; row < objResult.getResult().getRowCount(); row++) {
                    String providerName = getProviderName(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getExternalDoctorId(),lstProvider);
                    objPrescRefills = new ORMPrescriptionRefills();
                    objPrescRefills.setPrescription_refills_id(Integer.toString(row));
                    objPrescRefills.setExt_patient_id(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getExternalPatientId());
                    objPrescRefills.setPat_firstname(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getPatientFirstName());
                    objPrescRefills.setPat_mname(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getPatientMiddleName());
                    objPrescRefills.setPat_lastname(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getPatientLastName());
                    objPrescRefills.setPat_dob( dtInput_formaterMMDDYYYY.format((java.util.Date) dt_formater_yyyyMMdd.parse(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getPatientDOB())));
                      
                    objPrescRefills.setExt_providerid(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getExternalDoctorId());
                    objPrescRefills.setProv_firstname(providerName.split(",")[0]);
                    objPrescRefills.setProv_lastname(providerName.split(",")[1]);
                    objPrescRefills.setDrug_info(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getDrugInfo());
                    objPrescRefills.setPrescription_date( dtInput_formaterMMDDYYYY.format((java.util.Date) dt_formater_yyyyMMdd.parse(org.apache.commons.lang.StringUtils.substring(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getReceivedTimestamp(),0,10).replaceAll("-",""))));
                    
                    objPrescRefills.setExt_location_id(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getExternalLocationId());
                    objPrescRefills.setLocation_name(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getLocationName());
                    objPrescRefills.setExt_prescription_id(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getExternalPrescriptionId());
                    objPrescRefills.setPrescription_guid(objResult.getRenewalListDetailArray().getRenewalDetailV4().get(row).getRenewalRequestGuid());
                    objPrescRefills.setStatus_section("Refill");
                    lst.add(objPrescRefills);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

	private String getProviderName(String providerid, List<ORMProviderList> prvList) {
	    for (ORMProviderList obj : prvList) {
	        if (obj.getId().equals(Long.parseLong(providerid))) {
	            return obj.getName();
	        }
	    }
	    return "";
	}
	@Override
	public List<ORMgetDashBoardModule> getDashBoardModule(Long userId, Long practiceId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam=new ArrayList<>();
		lstParam.add(new SpParameters("UserId",userId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id",practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetDashBoarModules", ORMgetDashBoardModule.class, lstParam);
	}
	@Override
	public List<ORMOneColumnGeneric> getLabPendingResults_Widget(Long providerID, Long locationID, String type,
			Long loginUserID) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam=new ArrayList<>();
		lstParam.add(new SpParameters("ProviderID",providerID.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("locationID",locationID.toString().equals("0")?"":locationID.toString(), String.class, ParameterMode.IN)); 
		lstParam.add(new SpParameters("type",type.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("LoginUserID",loginUserID.toString(), String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetPendingResult_Widget", ORMOneColumnGeneric.class, lstParam);

	}
	@Override
	public List<ORMOneColumnGeneric> getCheckInPatient_Widget(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("PracticeID", searchCriteria.getPractice_id().toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", searchCriteria.getCriteria(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetCheckinPatient_Widget", ORMOneColumnGeneric.class, lstParam);
	}
	@Override
	public List<ORMOneColumnGeneric> getPendingClaims_Widget(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam=new ArrayList<>();
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()) 
			{
				if(pram.getName().toString().equals("attending_physician"))
					lstParam.add(new SpParameters("attending_physician", pram.getValue().toString() , String.class, ParameterMode.IN));
				else if (pram.getName().toString().equals("location_id"))
					lstParam.add(new SpParameters("location_id", pram.getValue().toString() , String.class, ParameterMode.IN));
			}
			lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString() , String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData("spGetPendingClaims_dashboard_Widget", ORMOneColumnGeneric.class, lstParam);
	}
	@Override
	public List<ORMOneColumnGeneric> getUnReadFaxes_Widget(String userID, String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam=new ArrayList<>();
		lstParam.add(new SpParameters("userID",userID.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("practice_id",practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetMyFaxes_Widget", ORMOneColumnGeneric.class, lstParam);
	}
	@Override
	public List<ORMOneColumnGeneric> getCashRegister_Widget(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam=new ArrayList<>();
		
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) 
		{
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}
		return db.getStoreProcedureData("spGetCashRegister_widget", ORMOneColumnGeneric.class, lstParam);
	}
	@Override
	public ServiceResponse<ORMKeyValue> updateOrderAssignedTo(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		String assigned_to="";
		String user="";
		String order_id="";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().toString().equals("assigned_to"))
					assigned_to=pram.getValue().toString();
				else if (pram.getName().toString().equals("user"))
					user=pram.getValue().toString();
				else if (pram.getName().toString().equals("order_id"))
					order_id=pram.getValue().toString();
			}
			String qry="update patient_order set assigned_to='"+ assigned_to +"', modified_user='"+user+"', date_modified=GETDATE() where order_id='"+ order_id +"'";
			int res=db.ExecuteUpdateQuery(qry);
			
			if (res == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
			}
		}
		return resp;
	}
	@Override
	public ServiceResponse<ORMKeyValue> faxMarkasRead(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		String client_date="";
		String user="";
		String fax_users_id="";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) 
		{
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) 
			{
				if (pram.getName().toString().equals("client_date"))
					client_date=pram.getValue().toString();
				else if (pram.getName().toString().equals("user"))
					user=pram.getValue().toString();
				else if (pram.getName().toString().equals("fax_users_id"))
					fax_users_id=pram.getValue().toString();
			}
			String qry="update fax_users set fax_status = 'Read',client_date_modified =  '"+client_date+"',modified_user =  '"+user+"', date_modified = GETDATE() where fax_users_id ='"+fax_users_id+"'";
			int res=db.ExecuteUpdateQuery(qry);
			
			if (res == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
			}
		}
		return resp;
	}
	@Override
	public List<ORMSevenColumnGeneric> getPaymentSummary(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam=new ArrayList<>();
		lstParam.add(new SpParameters("practice_id",practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("includeACA","false", String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetDashboardPaymentSummary", ORMSevenColumnGeneric.class, lstParam);
	}
	@Override
	public List<ORMThreeColumGeneric> getDashBoardDenial(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam=new ArrayList<>();
		lstParam.add(new SpParameters("practice_id",practice_id.toString(), String.class, ParameterMode.IN));		
		return db.getStoreProcedureData("spGetDashBoardDenial", ORMThreeColumGeneric.class, lstParam);
	}
//	@Override
//	public List<ORMThreeColumGeneric> getDashBoardClaimAging(SearchCriteria searchCriteria) {
//		// TODO Auto-generated method stub
//		List<SpParameters> lstParam=new ArrayList<>();
//		
//		lstParam.add(new SpParameters("criteria"," and c.practice_id= '"+practice_id+"'", String.class, ParameterMode.IN));		
//		return db.getStoreProcedureData("spGetDashboardAging", ORMThreeColumGeneric.class, lstParam);
//	}
	@Override
	public List<ORMThreeColumGeneric> getDashBoardClaimAging(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String criteria="";
		
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) 
		{
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) 
			{
				if (pram.getName().toString().equals("type"))
				{
					if(pram.getValue().equals("all"))
						criteria=" and c.practice_id= '"+searchCriteria.getPractice_id()+"'";
					else if(pram.getValue().equals("insurance"))
						criteria=" and c.practice_id= '"+searchCriteria.getPractice_id()+"' and (ISNULL(c.pat_status,'')<>'B' AND isnull(c.self_pay,0)<>1) ";
					else if(pram.getValue().equals("patient"))
						criteria=" and c.practice_id= '"+searchCriteria.getPractice_id()+"' and (ISNULL(c.pat_status,'')='B' OR isnull(c.self_pay,0)=1) ";
				}
			}
		}
		
		List<SpParameters> lstParam=new ArrayList<>();		
		lstParam.add(new SpParameters("criteria",criteria, String.class, ParameterMode.IN));		
		return db.getStoreProcedureData("spGetDashboardAging", ORMThreeColumGeneric.class, lstParam);	
		
	}
	@Override
	public List<ORMThreeColumGeneric> getDashBoardClaimCount(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String attending_physician="";
		String location_id="";
		String date_from="";
		String date_to="";
		
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) 
		{
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) 
			{
				if (pram.getName().toString().equals("attending_physician"))
					attending_physician=pram.getValue().toString();
				else if (pram.getName().toString().equals("location_id"))
					location_id=pram.getValue().toString();
				else if (pram.getName().toString().equals("date_from"))
					date_from=pram.getValue().toString();
				else if (pram.getName().toString().equals("date_to"))
					date_to=pram.getValue().toString();
			}
		}
		String criteria="";
		criteria+=" and cl.practice_id='"+searchCriteria.getPractice_id()+"' ";
		if(!attending_physician.toLowerCase().equals("all"))
		{
			criteria+="AND cl.attending_physician ='"+attending_physician+"'";	
		}
		if(!location_id.toLowerCase().equals("all"))
		{
			criteria+="AND cl.location_id ='"+location_id+"'";	
		}
		criteria+=" and convert(date,convert(varchar,cl.date_created,101)) between convert(date,'"+date_from+"') and convert(date,'"+date_to+"') ";
		
		List<SpParameters> lstParam=new ArrayList<>();			
		lstParam.add(new SpParameters("criteria",criteria, String.class, ParameterMode.IN));		
		return db.getStoreProcedureData("spGetDashboardClaimCount", ORMThreeColumGeneric.class, lstParam);
	}
	@Override
	public List<ORMDashboardClaimReminder> getDashboardClaimReminders(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String practiceId="";
		String reminderOption="";
		String logedInUser="";
		practiceId=searchCriteria.getPractice_id().toString();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {				
				case "reminder_option":
					reminderOption = pram.getValue();
					break;
				case "user_name":
					logedInUser = pram.getValue();
					break;
				default:
					break;
				}
			}
		}
		
		
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("reminder_option", reminderOption.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("user_name", logedInUser.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetDashBoardClaimsReminders", ORMDashboardClaimReminder.class, lstParam);
		
	}
}
