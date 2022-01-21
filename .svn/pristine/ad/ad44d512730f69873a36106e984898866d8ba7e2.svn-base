package com.ihc.ehr.dao;

import java.util.List;

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
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.ORMgetPatientTestDetails;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.WrapperLabResultSave;
import com.ihc.ehr.model.Wrapper_ObjectSave;

public interface LabDOA {

	List<ORMLabOrderSearch> getSearchLabOrder(SearchCriteria searchCriteria);

	List<ORMLabSearchTest> getSearchTest(String order_id);

	List<ORMPendingResults> getPendingResults(SearchCriteria searchCriteria);

	List<ORMPendingAttachments> spGetPendingAttachments(SearchCriteria searchCriteria);

	List<ORMSignedResults> getSignedResults(SearchCriteria searchCriteria);

	List<ORMSignedAttachments> spGetSignedAttachments(SearchCriteria searchCriteria);

	List<ORMCumulativeRpt> getCumulativeRpt(SearchCriteria searchCriteria);

	List<ORMPatientOrderSummaryView> getpatientOrderSummaryView(String patientId);

	List<ORMGetPracticeLabs> GetPracticeLab(String practice_id);

	List<ORMLabOrderCategoryTest> getLabGroupTest(String practice_id);

	List<ORMIdName> getLabFacility(String practice_id);

	ServiceResponse<ORMKeyValue> saveLabOrder(LabOrderSaveWrapper wrapper_order);

	List<ORMLabOrderTest> getOrderTest(String orderId);

	List<ORMLabOrderIcd> getOrderICD(String orderId);

	List<ORMLabOrderComment> getOrderComments(String orderId);

	List<ORMLabOrderDetail> getPatientOrderDetail(String order_id);

	List<ORMLabOrderSpecimen> getSpecimenInfo(String test_id);

	Long saveSpecimen(ORMLabSpecimenSave obj);

	List<ORMLabResult> getTestResult(String test_id);

	Long saveResults(ORMLabResultSave obj);

	List<ORMLabResultUnits> getResultUnits();

	List<ORMLabResultStatus> getResultStatus();

	List<ORMLabResultAbnormalFlags> getResultAbnormalRange();

	List<ORMLabResultStaffNotes> getResultStafNotes(String order_id);

	List<ORMLabResultTest> getResultTest(String order_id);

	List<ORMLabOrderAttachment> getTestAttachments(String test_id,String order_id);

	Long saveLabAttachment(ORMLabOrderAttachmentSave obj);

	Long saveLabAttachment(ORMLabOrderAttachmentSave labAttachment,
			ORMLabOrderAttachmentComments labAttachmentcmnt);

	long signLabOrder(SearchCriteria searchCriteria);

	long updateCommentStatus(Wrapper_ObjectSave<ORMLabOrderComment> obj);

	Long saveResultData(WrapperLabResultSave wrapperLabResultSave);

	Long saveAttachmentComments(ORMLabOrderAttachmentComments obj);

	Long saveResultComments(ORMLabResultNotes obj);

	List<ORMLabRequisition> getReqReport(String order_id);

	List<ORMLabRequisitionInsurance> getReqReportIns(String patient_id);

	List<ORMLabRequisitionProc> getReqReportLabCode(String order_id);

	long updateOrderFollowup(SearchCriteria searchCriteria);

	List<ORMThreeColumGeneric> GetOrderFollowUpNotes(String order_id);

	List<ORMLabFollowUpPatientList> getLabFollowupPatients(SearchCriteria searchCriteria);

	List<ORMgetPatientTestDetails> getLabFollowupPatientTestDetail(SearchCriteria searchCriteria);

	List<ORMGetPatientTestStaffNotes> getPatientLabsStaffNotes(String order_id);

	List<ORMGetPatientResultPhysicianNotes> getPatientLabsPhysicianNotes(String order_id); 
	long sendDrugAbuse(SearchCriteria searchCriteria);
}
