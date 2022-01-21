package com.ihc.ehr.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.LabOrderSaveWrapper;
import com.ihc.ehr.model.ORMCumulativeRpt;
import com.ihc.ehr.model.ORMGetPatientResultPhysicianNotes;
import com.ihc.ehr.model.ORMGetPatientTestStaffNotes;
import com.ihc.ehr.model.ORMGetPracticeLabs;
import com.ihc.ehr.model.ORMIdName;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMLabFollowUpPatientList;
import com.ihc.ehr.model.ORMLabOrder;
import com.ihc.ehr.model.ORMLabOrderAttachment;
import com.ihc.ehr.model.ORMLabOrderAttachmentComments;
import com.ihc.ehr.model.ORMLabOrderAttachmentSave;
import com.ihc.ehr.model.ORMLabOrderCategoryTest;
import com.ihc.ehr.model.ORMLabOrderComment;
import com.ihc.ehr.model.ORMLabOrderDetail;
import com.ihc.ehr.model.ORMLabOrderIcd;
import com.ihc.ehr.model.ORMLabOrderSearch;
import com.ihc.ehr.model.ORMLabOrderSpecimen;
import com.ihc.ehr.model.ORMLabOrderTest;
import com.ihc.ehr.model.ORMLabRequisition;
import com.ihc.ehr.model.ORMLabRequisitionInsurance;
import com.ihc.ehr.model.ORMLabRequisitionProc;
import com.ihc.ehr.model.ORMLabResult;
import com.ihc.ehr.model.ORMLabResultAbnormalFlags;
import com.ihc.ehr.model.ORMLabResultNotes;
import com.ihc.ehr.model.ORMLabResultSave;
import com.ihc.ehr.model.ORMLabResultStaffNotes;
import com.ihc.ehr.model.ORMLabResultStatus;
import com.ihc.ehr.model.ORMLabResultTest;
import com.ihc.ehr.model.ORMLabResultUnits;
import com.ihc.ehr.model.ORMLabSearchTest;
import com.ihc.ehr.model.ORMLabSpecimenSave;
import com.ihc.ehr.model.ORMPatientOrderSummaryView;
import com.ihc.ehr.model.ORMPendingAttachments;
import com.ihc.ehr.model.ORMPendingResults;
import com.ihc.ehr.model.ORMSignedAttachments;
import com.ihc.ehr.model.ORMSignedResults;
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.ORMgetPatientTestDetails;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.WrapperLabResultSave;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.model.Wrapper_ObjectSave;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.PostalMethod;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@Repository
public class LabDOAImpl implements LabDOA{

	@Autowired
	private DBOperations db;
	
	@Override
	public List<ORMLabOrderSearch> getSearchLabOrder(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		String criteria="";
		criteria+=" and po.practice_id="+searchCriteria.getPractice_id();
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			
			String criteria2="0";
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()) 
			{ 
				System.out.println("param:"+pram.getName()+"    Value:"+pram.getValue()+"    Option:"+pram.getOption());
				
				if(pram.getName().toLowerCase().equals("patient_id"))
				{
					criteria+=" and po.patient_id="+pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("assigned_to"))
				{
					criteria+=" and  po.assigned_to = "+pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("lab_id"))
				{
					criteria+=" and po.lab_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("location_id"))
				{
					criteria+=" and po.location_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("provider_id"))
				{
					criteria+=" and po.provider_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("status"))
				{
					criteria+=" and po.status='"+pram.getValue()+"'"; 
				}
				else if(pram.getName().toLowerCase().equals("lab_category_id"))
				{
					criteria+=" and pot.lab_category_id="+pram.getValue(); 
					criteria2="1";
				}
				else if(pram.getName().toLowerCase().equals("cpt_code"))
				{
					String[] arrCpt=pram.getValue().toString().split(",");
					String test_code="";
					for(int i=0;i<arrCpt.length;i++)
					{
						if(test_code.equals(""))
							test_code= "'"+arrCpt[i]+"'";
						else
							test_code+= ",'"+arrCpt[i]+"'";
					}
					criteria+=" and (pot.Proc_code in ("+test_code+") or pot.lab_assigned_cpt in ("+test_code+"))"; 
					criteria2="1";
				}
				else if(pram.getName().toLowerCase().equals("order_from_date"))
				{
					criteria+=" and convert(date,po.order_date)>= convert(date,'"+pram.getValue()+"')"; 
				}
				else if(pram.getName().toLowerCase().equals("order_to_date"))
				{
					criteria+=" and convert(date,po.order_date)<= convert(date,'"+pram.getValue()+"')"; 
				}
				else if(pram.getName().toLowerCase().equals("status_from_date"))
				{
					criteria+=" and convert(date,po.status_detail)>= convert(date,'"+pram.getValue()+"')"; 
				}
				else if(pram.getName().toLowerCase().equals("status_to_date"))
				{
					criteria+=" and convert(date,po.status_detail)<= convert(date,'"+pram.getValue()+"')"; 
				}
			}
			System.out.println("criteria1"+criteria);	
			lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("category", criteria2, String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData("spGetPatientOrderSearch", ORMLabOrderSearch.class, lstParam);
	}

	@Override
	public List<ORMLabSearchTest> getSearchTest(String order_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("order_id", order_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetPatientOrderSearchTest", ORMLabSearchTest.class, lstParam);
	}

	@Override
	public List<ORMPendingResults> getPendingResults(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		String criteria="";
		criteria+=" and po.practice_id="+searchCriteria.getPractice_id();
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()) 
			{ 
				System.out.println("param:"+pram.getName()+"    Value:"+pram.getValue()+"    Option:"+pram.getOption());
				
				if(pram.getName().toLowerCase().equals("patient_id"))
				{
					criteria+=" and po.patient_id="+pram.getValue();
				}
				 
				else if(pram.getName().toLowerCase().equals("location_id"))
				{
					criteria+=" and po.location_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("provider_id"))
				{
					criteria+=" and po.provider_id="+pram.getValue(); 
				}
				 
				else if(pram.getName().toLowerCase().equals("lab_category_id"))
				{
					criteria+=" and pot.lab_category_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("result_from_date"))
				{
					criteria+=" and convert(date,por.observation_datetime)>= convert(date,'"+pram.getValue()+"')"; 
				}
				else if(pram.getName().toLowerCase().equals("result_to_date"))
				{
					criteria+=" and convert(date,por.observation_datetime)<= convert(date,'"+pram.getValue()+"')"; 
				}	 
			}
			System.out.println("criteria1"+criteria);	
			lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData("spGetPendingResults", ORMPendingResults.class, lstParam);
	}
	@Override
	public List<ORMPendingAttachments> spGetPendingAttachments(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		String criteria="";
		criteria+=" and po.practice_id="+searchCriteria.getPractice_id();
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()) 
			{ 
				System.out.println("param:"+pram.getName()+"    Value:"+pram.getValue()+"    Option:"+pram.getOption());
				
				if(pram.getName().toLowerCase().equals("patient_id"))
				{
					criteria+=" and po.patient_id="+pram.getValue();
				}
				 
				else if(pram.getName().toLowerCase().equals("location_id"))
				{
					criteria+=" and po.location_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("provider_id"))
				{
					criteria+=" and po.provider_id="+pram.getValue(); 
				}
				 
				else if(pram.getName().toLowerCase().equals("lab_category_id"))
				{
					criteria+=" and pot.lab_category_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("result_from_date"))
				{
					criteria+=" and convert(date,pd.document_date)>= convert(date,'"+pram.getValue()+"')"; 
				}
				else if(pram.getName().toLowerCase().equals("result_to_date"))
				{
					criteria+=" and convert(date,pd.document_date)<= convert(date,'"+pram.getValue()+"')"; 
				}	 
			}
			System.out.println("criteria1"+criteria);	
			lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData("spGetPendingAttachments", ORMPendingAttachments.class, lstParam);
	}

	@Override
	public List<ORMSignedResults> getSignedResults(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		String criteria="";
		criteria+=" and po.practice_id="+searchCriteria.getPractice_id();
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()) 
			{ 
				System.out.println("param:"+pram.getName()+"    Value:"+pram.getValue()+"    Option:"+pram.getOption());
				
				if(pram.getName().toLowerCase().equals("patient_id"))
				{
					criteria+=" and po.patient_id="+pram.getValue();
				}
				 
				else if(pram.getName().toLowerCase().equals("location_id"))
				{
					criteria+=" and po.location_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("provider_id"))
				{
					criteria+=" and po.provider_id="+pram.getValue(); 
				}
				 
				else if(pram.getName().toLowerCase().equals("lab_category_id"))
				{
					criteria+=" and pot.lab_category_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("result_from_date"))
				{
					criteria+=" and convert(date,por.observation_datetime)>= convert(date,'"+pram.getValue()+"')"; 
				}
				else if(pram.getName().toLowerCase().equals("result_to_date"))
				{
					criteria+=" and convert(date,por.observation_datetime)<= convert(date,'"+pram.getValue()+"')"; 
				}	 
			}
			System.out.println("criteria1"+criteria);	
			lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData("spGetSignedResults", ORMSignedResults.class, lstParam);
	}

	@Override
	public List<ORMSignedAttachments> spGetSignedAttachments(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		String criteria="";
		criteria+=" and po.practice_id="+searchCriteria.getPractice_id();
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()) 
			{ 
				System.out.println("param:"+pram.getName()+"    Value:"+pram.getValue()+"    Option:"+pram.getOption());
				
				if(pram.getName().toLowerCase().equals("patient_id"))
				{
					criteria+=" and po.patient_id="+pram.getValue();
				}
				 
				else if(pram.getName().toLowerCase().equals("location_id"))
				{
					criteria+=" and po.location_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("provider_id"))
				{
					criteria+=" and po.provider_id="+pram.getValue(); 
				}
				 
				else if(pram.getName().toLowerCase().equals("lab_category_id"))
				{
					criteria+=" and pot.lab_category_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("result_from_date"))
				{
					criteria+=" and convert(date,pd.document_date)>= convert(date,'"+pram.getValue()+"')"; 
				}
				else if(pram.getName().toLowerCase().equals("result_to_date"))
				{
					criteria+=" and convert(date,pd.document_date)<= convert(date,'"+pram.getValue()+"')"; 
				}	 
			}
			System.out.println("criteria1"+criteria);	
			lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData("spGetSignedAttachments", ORMSignedAttachments.class, lstParam);
	}

	@Override
	public List<ORMCumulativeRpt> getCumulativeRpt(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		String criteria="";
		criteria+=" and po.practice_id="+searchCriteria.getPractice_id();
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()) 
			{ 
				if(pram.getName().toLowerCase().equals("patient_id"))
				{
					criteria+=" and por.patient_id="+pram.getValue();
				}
				if(pram.getName().toLowerCase().equals("order_to_date"))
				{
					criteria+=" and convert(date,po.order_date)<= convert(date,'"+pram.getValue()+"')"; 
				}
				if(pram.getName().toLowerCase().equals("order_from_date"))
				{
					criteria+=" and convert(date,po.order_date)>= convert(date,'"+pram.getValue()+"')"; 
				}
				
			}
			lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData("spGetPatientCumulativeRPT", ORMCumulativeRpt.class, lstParam);
	}

	@Override
	public List<ORMPatientOrderSummaryView> getpatientOrderSummaryView(String patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("Patient_ID", patientId, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetPatientOrderSummary_View", ORMPatientOrderSummaryView.class, lstParam);
	}

	@Override
	public List<ORMGetPracticeLabs> GetPracticeLab(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetPracticeLab", ORMGetPracticeLabs.class, lstParam);
	}

	@Override
	public List<ORMLabOrderCategoryTest> getLabGroupTest(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("PracticeId", practice_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetLabCategoryTest", ORMLabOrderCategoryTest.class, lstParam);
	}

	@Override
	public List<ORMIdName> getLabFacility(String practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetLabFacility", ORMIdName.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveLabOrder(LabOrderSaveWrapper wrapper_order) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		ORMLabOrder objOrder = wrapper_order.getOrder();
		ORMLabOrderComment objComment = wrapper_order.getOrder_comment();
		
		List<ORMLabOrderTest> lstOrderTest = wrapper_order.getLst_order_test();
		List<ORMLabOrderIcd> lstOrderDiag = wrapper_order.getLst_order_diag();
		
		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();
		
		if (objOrder != null) 
		{
			objOrder.setDate_modified(DateTimeUtil.getCurrentDateTime());
			
			if (objOrder.getOrder_id() == null || objOrder.getOrder_id()=="") 
			{
				objOrder.setOrder_id(db.IDGenerator("patient_order", Long.parseLong(objOrder.getPractice_id())).toString());
				objOrder.setDate_created(DateTimeUtil.getCurrentDateTime());

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objOrder));
			} 
			else 
			{
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, objOrder));
			}
			
			if (lstOrderTest != null && lstOrderTest.size() > 0) 
			{
				for (ORMLabOrderTest objOrderTest : lstOrderTest) 
				{
					objOrderTest.setDate_modified(DateTimeUtil.getCurrentDateTime());
					objOrderTest.setOrder_id(objOrder.getOrder_id());
					if (objOrderTest.getTest_id() == null || objOrderTest.getTest_id()=="")  
					{

						objOrderTest
								.setTest_id(db.IDGenerator("patient_order_test", Long.parseLong(objOrder.getPractice_id())).toString());
						objOrderTest.setDate_created(DateTimeUtil.getCurrentDateTime());

						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objOrderTest));
					} else {
						lstEntityWrapper
								.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, objOrderTest));
					}
				}
			}
			if (lstOrderDiag != null && lstOrderDiag.size() > 0) 
			{
				for (ORMLabOrderIcd objOrderDiag : lstOrderDiag) 
				{
					objOrderDiag.setDate_modified(DateTimeUtil.getCurrentDateTime());
					objOrderDiag.setOrder_id(objOrder.getOrder_id());
					if (objOrderDiag.getDiagnosise_id() == null || objOrderDiag.getDiagnosise_id() == "") 
					{

						objOrderDiag
								.setDiagnosise_id(db.IDGenerator("patient_order_diagnosis", Long.parseLong(objOrder.getPractice_id())).toString());
						objOrderDiag.setDate_created(DateTimeUtil.getCurrentDateTime());

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objOrderDiag));
					}
					else 
					{
						lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, objOrderDiag));
					}
				}
			}
			if(objComment!=null)
			{
				objComment.setDate_created(DateTimeUtil.getCurrentDateTime());
				objComment.setComment_id(db.IDGenerator("patient_order_comment", Long.parseLong(objOrder.getPractice_id())).toString());
				objComment.setOrder_id(objOrder.getOrder_id());
				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objComment));
			}
			//@
			String query = "";
			if (wrapper_order.getLst_deleted_ids()!=null) 
			{
				for (ORMKeyValue ormKV : wrapper_order.getLst_deleted_ids()) 
				{
					switch (ormKV.getKey()) 
					{
					case "patient_order_test":
						query = " update patient_order_test set deleted=1,date_modified=getdate(),modified_user='"
								+ wrapper_order.getLoged_in_user() + "',client_date_modified='"
								+ wrapper_order.getClient_date() + "',system_ip='" + wrapper_order.getSystem_ip()
								+ "' where test_id in (" + ormKV.getValue() + ") ";

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));
						break;
						
					case "patient_order_diagnosis":
						query = " update patient_order_diagnosis set deleted=1,date_modified=getdate(),modified_user='"
								+ wrapper_order.getLoged_in_user() + "',client_date_modified='"
								+ wrapper_order.getClient_date() + "',system_ip='" + wrapper_order.getSystem_ip()
								+ "' where diagnosise_id in (" + ormKV.getValue() + ") ";

						lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));
						break;
					}
				}
			}
			
			result = db.AddUpdateEntityWrapper(lstEntityWrapper);

			if (result == 0) 
			{
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while saving record.");
			} 
			else 
			{
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("Data has been saved successfully.");
				resp.setResult(objOrder.getOrder_id());
			}
		}
		return resp;
		
	}

	@Override
	public List<ORMLabOrderTest> getOrderTest(String orderId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("OrderId", orderId, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetLabOrderTest", ORMLabOrderTest.class, lstParam);
	}

	@Override
	public List<ORMLabOrderIcd> getOrderICD(String orderId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("OrderId", orderId, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetLabOrderICD", ORMLabOrderIcd.class, lstParam);
	}

	@Override
	public List<ORMLabOrderComment> getOrderComments(String orderId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("OrderId", orderId, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetLabOrderComment", ORMLabOrderComment.class, lstParam);
	}

	@Override
	public List<ORMLabOrderDetail> getPatientOrderDetail(String order_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("order_id", order_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetPatientOrderDetail", ORMLabOrderDetail.class, lstParam);
	}

	@Override
	public List<ORMLabOrderSpecimen> getSpecimenInfo(String test_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("test_id", test_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetOrderTestSpecimen", ORMLabOrderSpecimen.class, lstParam);
	}

	@Override
	public Long saveSpecimen(ORMLabSpecimenSave obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if(obj.getPatient_specimen_id()==null || obj.getPatient_specimen_id()=="")
		{
			obj.setPatient_specimen_id(db.IDGenerator("patient_order_specimen",Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(obj, Operation.ADD);
		}
		else
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		
		return (long) result;
	}

	@Override
	public List<ORMLabResult> getTestResult(String test_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("test_id", test_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetLabTestResult", ORMLabResult.class, lstParam);
		
	}

	@Override
	public Long saveResults(ORMLabResultSave obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if(obj.getResult_id()==null || obj.getResult_id()=="")
		{
			obj.setResult_id(db.IDGenerator("patient_order_results",Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(obj, Operation.ADD);
		}
		else
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		db.ExecuteUpdateQuery("update patient_order_test set physician_comments='"+obj.getPhysician_comments()+"' where order_id="+obj.getOrder_id()+" and test_id="+obj.getTest_id()+"");
		return (long) result;
	}

	@Override
	public List<ORMLabResultUnits> getResultUnits() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetResultUnits", ORMLabResultUnits.class, null);
	}
	@Override
	public List<ORMLabResultStatus> getResultStatus() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetResultStatus", ORMLabResultStatus.class, null);
	}

	@Override
	public List<ORMLabResultAbnormalFlags> getResultAbnormalRange() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetResultAbnormalFalgs", ORMLabResultAbnormalFlags.class, null);
	}

	@Override
	public List<ORMLabResultStaffNotes> getResultStafNotes(String order_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("order_id", order_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetPatientOrderResultStaffNote", ORMLabResultStaffNotes.class, lstParam);
		
	}

	@Override
	public List<ORMLabResultTest> getResultTest(String order_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("OrderID", order_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetPatientOrderResultTest", ORMLabResultTest.class, lstParam);
	}

	@Override
	public List<ORMLabOrderAttachment> getTestAttachments(String test_id,String order_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("test_id", test_id, String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("order_id", order_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetTestAttachment", ORMLabOrderAttachment.class, lstParam);
	}

	@Override
	public Long saveLabAttachment(ORMLabOrderAttachmentSave obj) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if(obj.getPatient_order_attachment_id()==null || obj.getPatient_order_attachment_id()=="")
		{
			obj.setPatient_order_attachment_id(db.IDGenerator("patient_order_attachment",Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(obj, Operation.ADD);
		}
		else
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		//db.ExecuteUpdateQuery("update patient_order_test set physician_comments='"+obj.getPhysician_comments()+"' where order_id="+obj.getOrder_id()+" and test_id="+obj.getTest_id()+"");
		return (long) result;
	}

	@Override
	public Long saveLabAttachment(ORMLabOrderAttachmentSave obj,
			ORMLabOrderAttachmentComments labAttachmentcmnt) {
		// TODO Auto-generated method stub
		int result = 0;
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if(obj.getPatient_order_attachment_id()==null || obj.getPatient_order_attachment_id()=="")
		{
			obj.setPatient_order_attachment_id(db.IDGenerator("patient_order_attachment",Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(obj, Operation.ADD);
		}
		else
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		if(labAttachmentcmnt!=null)
		{
			labAttachmentcmnt.setComment_id(db.IDGenerator("patient_order_attachment_comment",Long.parseLong(obj.getPractice_id())).toString());
			labAttachmentcmnt.setPatient_order_attachment_id(obj.getPatient_order_attachment_id());
			labAttachmentcmnt.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(labAttachmentcmnt, Operation.ADD);
		}
		return (long) result; 
		
	}

	@Override
	public long signLabOrder(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) 
		{
			String user="";
			String order_id="";
			
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) 
			{
				 
				if (pram.getName().equals("user")) 
				{
					user = pram.getValue();
				} 
				else if (pram.getName().equals("order_id")) 
				{
					order_id = pram.getValue();
				} 
			}
			String qry= "update patient_order set signed_by='"+user+"',signed_date=GETDATE(),modified_user='"+user+"',date_modified=GETDATE()  where order_id='"+order_id+"'";
			return db.ExecuteUpdateQuery(qry);
		}
		return 0;
	}

	@Override
	public long updateCommentStatus(Wrapper_ObjectSave<ORMLabOrderComment> obj) {
		// TODO Auto-generated method stub
		ORMLabOrderComment objComment = obj.getOrmSave();
		
		
		objComment.setDate_created(DateTimeUtil.getCurrentDateTime());
		objComment.setComment_id(db.IDGenerator("patient_order_comment", Long.parseLong(objComment.getPractice_id())).toString());
		
		db.SaveEntity(objComment, Operation.ADD);
		
		List<ORMKeyValue> objOtherData = obj.getLstKeyValue();
		String status="";
		String status_Date="";
		for (ORMKeyValue item : objOtherData) {
			if (item.getKey().equalsIgnoreCase("status")) {
				status = item.getValue();
			}
			if (item.getKey().equalsIgnoreCase("status_date")) {
				status_Date = item.getValue();
			}
		}
		String qry="update patient_order set status = '"+status+"',modified_user ='"+objComment.getCreated_user()+"',client_date_modified ='"+objComment.getClient_date_created()+"',date_modified = GETDATE(),"+  
				  " status_detail = '"+objComment.getClient_date_created()+"',status_by = '"+objComment.getCreated_user()+"'   "+
				  " where order_id = "+objComment.getOrder_id()  ;
		return db.ExecuteUpdateQuery(qry);
	}

	@Override
	public Long saveResultData(WrapperLabResultSave wrapperLabResultSave) {
		// TODO Auto-generated method stub
		int result = 0;

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		wrapperLabResultSave.getObjLabResultSave().setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (wrapperLabResultSave.getObjLabResultSave().getResult_id() == null
				|| wrapperLabResultSave.getObjLabResultSave().getResult_id() == "") {
			wrapperLabResultSave.getObjLabResultSave()
					.setResult_id(db
							.IDGenerator("patient_order_results",
									Long.parseLong(wrapperLabResultSave.getObjLabResultSave().getPractice_id()))
							.toString());
			wrapperLabResultSave.getObjLabResultSave().setDate_created(DateTimeUtil.getCurrentDateTime());

			lstEntityWrapper.add(
					new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, wrapperLabResultSave.getObjLabResultSave()));

			// result = db.SaveEntity(obj, Operation.ADD);
		} else {

			lstEntityWrapper.add(
					new Wrapper_Entity(EntityType.ENTITY, Operation.EDIT, wrapperLabResultSave.getObjLabResultSave()));
			// result = db.SaveEntity(obj, Operation.EDIT);
		}

		String queryPhyComments = "update patient_order_test set physician_comments='"
				+ wrapperLabResultSave.getObjLabResultSave().getPhysician_comments() + "' where order_id="
				+ wrapperLabResultSave.getObjLabResultSave().getOrder_id() + " and test_id="
				+ wrapperLabResultSave.getObjLabResultSave().getTest_id() + "";
		lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, queryPhyComments));

		String strcol = "";
		String fu_detail = "";
		if (GeneralOperations.isNotNullorEmpty(wrapperLabResultSave.getObjLabResultSave().getFollowup_notes())) {
			strcol = "follow_up_notes='" + wrapperLabResultSave.getObjLabResultSave().getFollowup_notes() + "' , ";

			if (GeneralOperations.isNotNullorEmpty(wrapperLabResultSave.getObjLabResultSave().getFollow_up_action()))
				strcol += " follow_up_action='" + wrapperLabResultSave.getObjLabResultSave().getFollow_up_action()
						+ "' ,";
		} else {
			strcol = "follow_up_notes='',follow_up_action='', ";
		}

		if (GeneralOperations.isNotNullorEmpty(wrapperLabResultSave.getObjLabResultSave().getAssigned_to())) {
			strcol += " assigned_to='" + wrapperLabResultSave.getObjLabResultSave().getAssigned_to() + "' ,";
		}

		if (GeneralOperations.isNotNullorEmpty(wrapperLabResultSave.getFollowUpDetails()))
			fu_detail = "By " + wrapperLabResultSave.getFollowUpDetails();
		else
			fu_detail = "";

		strcol += " followup_detail='" + fu_detail + "' ,";

		String followUpQuery = "update patient_order set " + strcol + " change_med='"
				+ wrapperLabResultSave.getObjLabResultSave().isChange_med() + "',modified_user='"
				+ wrapperLabResultSave.getObjLabResultSave().getModified_user()
				+ "',date_modified=GETDATE() where order_id='"
				+ wrapperLabResultSave.getObjLabResultSave().getOrder_id() + "'";
		lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, followUpQuery));

		if (wrapperLabResultSave.getLabNotes() != null) {
			wrapperLabResultSave.getLabNotes()
					.setNotes_id(db
							.IDGenerator("patient_order_result_staff_note",
									Long.parseLong(wrapperLabResultSave.getObjLabResultSave().getPractice_id()))
							.toString());
			wrapperLabResultSave.getLabNotes().setResult_id(wrapperLabResultSave.getObjLabResultSave().getResult_id());
			wrapperLabResultSave.getLabNotes().setDate_created(DateTimeUtil.getCurrentDateTime());
			// result = db.SaveEntity(wrapperLabResultSave.getLabNotes(), Operation.ADD);

			lstEntityWrapper.add(
					new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, wrapperLabResultSave.getObjLabResultSave()));
		}

		result = db.AddUpdateEntityWrapper(lstEntityWrapper);

		return (long) result;
	}


	@Override
	public Long saveAttachmentComments(ORMLabOrderAttachmentComments obj) {
		// TODO Auto-generated method stub
		int result = 0;
		
		if(obj.getComment_id()==null || obj.getComment_id()=="")
		{
			obj.setComment_id(db.IDGenerator("patient_order_attachment_comment",Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(obj, Operation.ADD);
		}
		else
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		return (long) result;
	}

	@Override
	public Long saveResultComments(ORMLabResultNotes obj) {
		// TODO Auto-generated method stub
		int result = 0;
		
		if(obj.getNotes_id()==null || obj.getNotes_id()=="")
		{
			obj.setNotes_id(db.IDGenerator("patient_order_result_staff_note",Long.parseLong(obj.getPractice_id())).toString());
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			result = db.SaveEntity(obj, Operation.ADD);
		}
		else
		{
			result = db.SaveEntity(obj, Operation.EDIT);
		}
		
		return (long) result;
	}

	@Override
	public List<ORMLabRequisition> getReqReport(String order_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("Order_ID", order_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetRequisitionRpt", ORMLabRequisition.class, lstParam);
	}

	@Override
	public List<ORMLabRequisitionInsurance> getReqReportIns(String patient_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_ID", patient_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetRequisitionRptInsurance", ORMLabRequisitionInsurance.class, lstParam);
	}

	@Override
	public List<ORMLabRequisitionProc> getReqReportLabCode(String order_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("order_ID", order_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetTestCode", ORMLabRequisitionProc.class, lstParam);
	}

	@Override
	public long updateOrderFollowup(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String qry="";
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			String follow_up_notes="";
			String follow_up_action="";
			String user="";
			String order_id="";
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()) 
			{ 
				System.out.println("param:"+pram.getName()+"    Value:"+pram.getValue()+"    Option:"+pram.getOption());
				
				if(pram.getName().toLowerCase().equals("follow_up_notes"))
				{
					follow_up_notes=pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("followup_action"))
				{
					follow_up_action= pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("user"))
				{
					user= pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("order_id"))
				{
					order_id= pram.getValue();
				}
			}
			qry="update patient_order set follow_up_notes='"+follow_up_notes+"',follow_up_action='"+follow_up_action+"' , modified_user='"+user+"' ,date_modified=getdate() where order_id='"+order_id+"'";
			
		}
		return db.ExecuteUpdateQuery(qry);
	}

	@Override
	public List<ORMThreeColumGeneric> GetOrderFollowUpNotes(String order_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("order_id", order_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetOrderFollowUpNotes", ORMThreeColumGeneric.class, lstParam);
	}

	@Override
	public List<ORMLabFollowUpPatientList> getLabFollowupPatients(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		String criteria="";
		criteria+=" and po.practice_id="+searchCriteria.getPractice_id();
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			String criteria2="";
			String notes_exists_criteria="";
			String strcriteriaDate="";
			String date_from="";
			String date_to="";
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()) 
			{ 
				System.out.println("param:"+pram.getName()+"    Value:"+pram.getValue()+"    Option:"+pram.getOption());
				
				if(pram.getName().toLowerCase().equals("patient_id"))
				{
					criteria+=" and po.patient_id="+pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("provider_id"))
				{
					criteria+=" and  po.provider_id = "+pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("location_id"))
				{
					criteria+=" and po.location_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("lab_category_id"))
				{
					criteria+=" and pot.lab_category_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("signed"))
				{
					if(pram.getValue().equals("true"))
						criteria+=" and isnull(po.signed_by,'')<>''";
//					else
//						criteria+=" and isnull(po.signed_by,'')=''";
				}
				else if(pram.getName().toLowerCase().equals("follow_up_notes"))
				{
					criteria+=" and isnull(po.follow_up_notes,'')='"+pram.getValue()+"'"; 
				}
				else if(pram.getName().toLowerCase().equals("follow_up_action"))
				{
					criteria+=" and isnull(po.follow_up_action,'')='"+pram.getValue()+"'"; 
				}
				else if(pram.getName().toLowerCase().equals("from_date"))
				{
					date_from=pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("to_date"))
				{
					date_to=pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("notes_exist"))
				{
					if(pram.getValue().equals("true"))
						notes_exists_criteria="1";
					else
						notes_exists_criteria="0";
				}
			}
			strcriteriaDate="convert(datetime,'"+date_from+"'  ) and convert(datetime,'"+date_to+"'  )";
					
			System.out.println("criteria1"+criteria);	
			lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("date_range", strcriteriaDate, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("notes_not_exist", notes_exists_criteria, String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData("spGetFollowUpPatients", ORMLabFollowUpPatientList.class, lstParam);
	}

	@Override
	public List<ORMgetPatientTestDetails> getLabFollowupPatientTestDetail(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		String criteria="";
		criteria+=" and po.practice_id="+searchCriteria.getPractice_id();
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			String criteria2="";
			String notes_exists_criteria="";
			String strcriteriaDate="";
			String date_from="";
			String date_to="";
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()) 
			{ 
				System.out.println("param:"+pram.getName()+"    Value:"+pram.getValue()+"    Option:"+pram.getOption());
				
				if(pram.getName().toLowerCase().equals("patient_id"))
				{
					criteria+=" and po.patient_id="+pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("provider_id"))
				{
					criteria+=" and  po.provider_id = "+pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("location_id"))
				{
					criteria+=" and po.location_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("lab_category_id"))
				{
					criteria+=" and pot.lab_category_id="+pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("signed"))
				{
					if(pram.getValue().equals("true"))
						criteria+=" and isnull(po.signed_by,'')<>''";
//					else
//						criteria+=" and isnull(po.signed_by,'')=''";
				}
				else if(pram.getName().toLowerCase().equals("follow_up_notes"))
				{
					criteria+=" and isnull(po.follow_up_notes,'')='"+pram.getValue()+"'"; 
				}
				else if(pram.getName().toLowerCase().equals("follow_up_action"))
				{
					criteria+=" and isnull(po.follow_up_action,'')='"+pram.getValue()+"'"; 
				}
				else if(pram.getName().toLowerCase().equals("from_date"))
				{
					date_from=pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("to_date"))
				{
					date_to=pram.getValue(); 
				}
				else if(pram.getName().toLowerCase().equals("notes_exist"))
				{
					if(pram.getValue().equals("true"))
						notes_exists_criteria="1";
					else
						notes_exists_criteria="0";
				}
			}
			strcriteriaDate="convert(datetime,'"+date_from+"'  ) and convert(datetime,'"+date_to+"' )";
					
			System.out.println("criteria1"+criteria);	
			lstParam.add(new SpParameters("criteria", criteria, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("date_range", strcriteriaDate, String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("notes_not_exist", notes_exists_criteria, String.class, ParameterMode.IN));
		}
		return db.getStoreProcedureData("spGetPatientTestsFollowUpDetails", ORMgetPatientTestDetails.class, lstParam);
	}

	@Override
	public List<ORMGetPatientTestStaffNotes> getPatientLabsStaffNotes(String order_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("order_id", order_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetPatientLabsStaffNotes", ORMGetPatientTestStaffNotes.class, lstParam);
	}

	@Override
	public List<ORMGetPatientResultPhysicianNotes> getPatientLabsPhysicianNotes(String order_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("order_id", order_id, String.class, ParameterMode.IN));
		
		return db.getStoreProcedureData("spGetPatientLabsPhysicianNotes", ORMGetPatientResultPhysicianNotes.class, lstParam);
	}
	
	@Override
	public long sendDrugAbuse(SearchCriteria searchCriteria){
		String strUpdateQuery = "";
		if(searchCriteria.getParam_list()!=null && !searchCriteria.getParam_list().isEmpty())
		{
			String healthcheckId="";
			String drugfilePath="";
			for(SearchCriteriaParamList pram :  searchCriteria.getParam_list()){
				if(pram.getName().toLowerCase().equals("drugfilepath"))
				{
					drugfilePath=pram.getValue();
				}
				else if(pram.getName().toLowerCase().equals("healthcheckid"))
				{
					healthcheckId= pram.getValue();
				}
			}
			File file = new File(drugfilePath);
			PostalMethod postmethod = new PostalMethod();
			
			String result = postmethod.SendLetter("", file.toString());
			
			
			
			strUpdateQuery = "update drug_abuse_score set reference_id = '" + result + "', send_date = getdate() "
	                + "where health_check_id = '"+ healthcheckId +"'";
			
		}
		return db.ExecuteUpdateQuery(strUpdateQuery);
	}	 
}