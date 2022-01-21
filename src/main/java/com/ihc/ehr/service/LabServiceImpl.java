package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.LabDOA;
import com.ihc.ehr.model.LabOrderSaveWrapper;
import com.ihc.ehr.model.ORMCumulativeRpt;
import com.ihc.ehr.model.ORMGetPatientResultPhysicianNotes;
import com.ihc.ehr.model.ORMGetPatientTestStaffNotes;
import com.ihc.ehr.model.ORMGetPracticeLabs;
import com.ihc.ehr.model.ORMIdName;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMLabFollowUpPatientList;
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
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.ORMgetPatientTestDetails;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.WrapperLabResultSave;
import com.ihc.ehr.model.Wrapper_ObjectSave;

 
@Service
public class LabServiceImpl implements LabService{

	@Autowired
	private LabDOA labdoa;

	@Override
	public List<ORMLabOrderSearch> getSearchLabOrder(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return labdoa.getSearchLabOrder(searchCriteria);
	}
	

	@Override
	public List<ORMLabSearchTest> getSearchTest(String order_id) {
		// TODO Auto-generated method stub
		return labdoa.getSearchTest(order_id);
	}


	@Override
	public List<ORMPendingResults> getPendingResults(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return labdoa.getPendingResults(searchCriteria);
	}


	@Override
	public List<ORMPendingAttachments> spGetPendingAttachments(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return labdoa.spGetPendingAttachments(searchCriteria);
	}


	@Override
	public List<ORMSignedResults> getSignedResults(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return labdoa.getSignedResults(searchCriteria);
	}


	@Override
	public List<ORMSignedAttachments> spGetSignedAttachments(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return labdoa.spGetSignedAttachments(searchCriteria);
	}


	@Override
	public List<ORMCumulativeRpt> getCumulativeRpt(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return labdoa.getCumulativeRpt(searchCriteria);
	}


	@Override
	public List<ORMThreeColum> getLabCategory(String practiceID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ORMPatientOrderSummaryView> getpatientOrderSummaryView(String patientId) {
		// TODO Auto-generated method stub
		return labdoa.getpatientOrderSummaryView(patientId);
	}


	@Override
	public List<ORMGetPracticeLabs> GetPracticeLab(String practice_id) {
		// TODO Auto-generated method stub
		return labdoa.GetPracticeLab(practice_id);
	}


	@Override
	public List<ORMLabOrderCategoryTest> getLabGroupTest(String practice_id) {
		// TODO Auto-generated method stub
		return labdoa.getLabGroupTest(practice_id);
	}


	@Override
	public List<ORMIdName> getLabFacility(String practice_id) {
		// TODO Auto-generated method stub
		return labdoa.getLabFacility(practice_id);
	}


	@Override
	public ServiceResponse<ORMKeyValue> saveLabOrder(LabOrderSaveWrapper wrapper_order) {
		// TODO Auto-generated method stub
		return labdoa.saveLabOrder(wrapper_order);
	}


	@Override
	public List<ORMLabOrderTest> getOrderTest(String orderId) {
		// TODO Auto-generated method stub
		return labdoa.getOrderTest(orderId);
	}


	@Override
	public List<ORMLabOrderIcd> getOrderICD(String orderId) {
		// TODO Auto-generated method stub
		return labdoa.getOrderICD(orderId);
	}


	@Override
	public List<ORMLabOrderComment> getOrderComments(String orderId) {
		// TODO Auto-generated method stub
		return labdoa.getOrderComments(orderId);
	}


	@Override
	public List<ORMLabOrderDetail> getPatientOrderDetail(String order_id) {
		// TODO Auto-generated method stub
		return labdoa.getPatientOrderDetail(order_id);
	}


	@Override
	public List<ORMLabOrderSpecimen> getSpecimenInfo(String test_id) {
		// TODO Auto-generated method stub
		return labdoa.getSpecimenInfo(test_id);
	}


	@Override
	public Long saveSpecimen(ORMLabSpecimenSave obj) {
		// TODO Auto-generated method stub
		return labdoa.saveSpecimen(obj);
	}


	@Override
	public List<ORMLabResult> getTestResult(String test_id) {
		// TODO Auto-generated method stub
		return labdoa.getTestResult(test_id);
	}


	@Override
	public Long saveResults(ORMLabResultSave obj) {
		// TODO Auto-generated method stub
		return labdoa.saveResults(obj);
	}


	@Override
	public List<ORMLabResultUnits> getResultUnits() {
		// TODO Auto-generated method stub
		return labdoa.getResultUnits();
	}


	@Override
	public List<ORMLabResultStatus> getResultStatus() {
		// TODO Auto-generated method stub
		return labdoa.getResultStatus();
	}


	@Override
	public List<ORMLabResultAbnormalFlags> getResultAbnormalRange() {
		// TODO Auto-generated method stub
		return labdoa.getResultAbnormalRange();
	}


	@Override
	public List<ORMLabResultStaffNotes> getResultStafNotes(String order_id) {
		// TODO Auto-generated method stub
		return labdoa.getResultStafNotes(order_id);
	}


	@Override
	public List<ORMLabResultTest> getResultTest(String order_id) {
		// TODO Auto-generated method stub
		return labdoa.getResultTest(order_id);
	}


	@Override
	public List<ORMLabOrderAttachment> getTestAttachments(String test_id,String order_id) {
		// TODO Auto-generated method stub
		return labdoa.getTestAttachments(test_id,order_id);
	}


	@Override
	public Long saveLabAttachment(ORMLabOrderAttachmentSave obj) {
		// TODO Auto-generated method stub
		return labdoa.saveLabAttachment(obj);
	}
	@Override
	public Long saveLabAttachment(ORMLabOrderAttachmentSave labAttachment,ORMLabOrderAttachmentComments labAttachmentcmnt) {
		// TODO Auto-generated method stub
		return labdoa.saveLabAttachment(labAttachment,labAttachmentcmnt);
	}


	@Override
	public long signLabOrder(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return labdoa.signLabOrder(searchCriteria);
	}


	@Override
	public long updateCommentStatus(Wrapper_ObjectSave<ORMLabOrderComment> obj) {
		// TODO Auto-generated method stub
		return labdoa.updateCommentStatus(obj);
	}


	@Override
	public Long saveResultData(WrapperLabResultSave wrapperLabResultSave) {
		// TODO Auto-generated method stub
		return labdoa.saveResultData(wrapperLabResultSave);
	}


	@Override
	public Long saveAttachmentComments(ORMLabOrderAttachmentComments obj) {
		// TODO Auto-generated method stub
		return labdoa.saveAttachmentComments(obj);
	}


	@Override
	public Long saveResultComments(ORMLabResultNotes obj) {
		// TODO Auto-generated method stub
		return labdoa.saveResultComments(obj);
	}


	@Override
	public List<ORMLabRequisition> getReqReport(String order_id) {
		// TODO Auto-generated method stub
		return labdoa.getReqReport(order_id);
	}


	@Override
	public List<ORMLabRequisitionInsurance> getReqReportIns(String patient_id) {
		// TODO Auto-generated method stub
		return labdoa.getReqReportIns(patient_id);
	}


	@Override
	public List<ORMLabRequisitionProc> getReqReportLabCode(String order_id) {
		// TODO Auto-generated method stub
		return labdoa.getReqReportLabCode(order_id);
	}


	@Override
	public long updateOrderFollowup(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return labdoa.updateOrderFollowup(searchCriteria);
	}


	@Override
	public List<ORMThreeColumGeneric> GetOrderFollowUpNotes(String order_id) {
		// TODO Auto-generated method stub
		return labdoa.GetOrderFollowUpNotes(order_id);
	}


	@Override
	public List<ORMLabFollowUpPatientList> getLabFollowupPatients(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return labdoa.getLabFollowupPatients(searchCriteria);
	}


	@Override
	public List<ORMgetPatientTestDetails> getLabFollowupPatientTestDetail(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return labdoa.getLabFollowupPatientTestDetail(searchCriteria);
	}


	@Override
	public List<ORMGetPatientTestStaffNotes> getPatientLabsStaffNotes(String order_id) {
		// TODO Auto-generated method stub
		return labdoa.getPatientLabsStaffNotes(order_id);
	}


	@Override
	public List<ORMGetPatientResultPhysicianNotes> getPatientLabsPhysicianNotes(String order_id) {
		// TODO Auto-generated method stub
		return labdoa.getPatientLabsPhysicianNotes(order_id);
	}
	
	@Override
	public long sendDrugAbuse(SearchCriteria searchCriteria) {
		return labdoa.sendDrugAbuse(searchCriteria);
	}
}
