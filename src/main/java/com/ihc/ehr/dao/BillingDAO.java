package com.ihc.ehr.dao;

import java.util.List;


import com.ihc.ehr.model.ERAOperationResult;
import com.ihc.ehr.model.ERAPaymentPostingResponse;
import org.springframework.web.multipart.MultipartFile;
import com.ihc.ehr.model.ORMBatchDetail;
import com.ihc.ehr.model.ORMClaim_Batch_Errors;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMDenialMessagesSave;
import com.ihc.ehr.model.ORMEOBSave;
import com.ihc.ehr.model.ORMEobAttachmentsSave;
import com.ihc.ehr.model.ORMEobDetailGet;
import com.ihc.ehr.model.ORMEraClaimDetailGet;
import com.ihc.ehr.model.ORMEraClaimListGet;
import com.ihc.ehr.model.ORMEraClaimServicesGet;
import com.ihc.ehr.model.ORMEraGet;
import com.ihc.ehr.model.ORMEraListGet;
import com.ihc.ehr.model.ORMEraProviderAdjustmentGet;
import com.ihc.ehr.model.ORMFourColumGeneric;
import com.ihc.ehr.model.ORMGetBatchClaimCount;
import com.ihc.ehr.model.ORMGetBillingSummaryClaims;
import com.ihc.ehr.model.ORMGetEOB;
import com.ihc.ehr.model.ORMEobAttachmentGet;
import com.ihc.ehr.model.ORMIdCodeDescriptionType;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMSaveBatch;
import com.ihc.ehr.model.ORMSaveClaimBatchDetail;
import com.ihc.ehr.model.ORMScalarValue;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORM_277ClaimStatusDetail;
import com.ihc.ehr.model.ORM_277_ResponseGet;
import com.ihc.ehr.model.SubmissionProccessedClaimInfo;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;

public interface BillingDAO {

	List<ORMGetBillingSummaryClaims> getClaimBillingSummary(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> addUpdateBatch(ORMSaveBatch obj);

	List<ORMFourColumGeneric> getUnlockBatch(String practice_id);

	List<ORMGetBatchClaimCount> getClaimBatchCount(SearchCriteria searchCriteria);

	List<ORMScalarValue> getBatchClaimList(String batch_id);

	ServiceResponse<ORMKeyValue> saveClaimBatchDetail(List<ORMSaveClaimBatchDetail> lstSave);

	List<ORMBatchDetail> getBatchDetail(String practice_id, String batch_id);

	int lockBatch(SearchCriteria searchCriteria);

	List<ORMClaim_Batch_Errors> generateBatch_5010_P(SearchCriteria searchCriteria);

	List<ORMTwoColumnGeneric> getBatchPath(String batch_id);

	int IgnoreError(String error_id, String user_id);

	int deleteClaimFromBatch(SearchCriteria searchCriteria);

	String uploadBatchToGatewayEDI(SearchCriteria searchCriteria);

	String downloadBatchResponse(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> saveDenialMessage(ORMDenialMessagesSave ormDenialMessagesSave);

	ServiceResponse<ORMKeyValue> resolveDenialMessage(SearchCriteria searchCriteria);

	List<ORMEraListGet> getEraList(SearchCriteria searchCriteria);

	List<ORMEraGet> getEraDetails(Long eraId);

	List<ORMEraClaimListGet> getEraClaimList(SearchCriteria searchCriteria);

	ORMEraClaimDetailGet getEraClaimDetail(Long practiceId,Long eraClaimId);

	List<ORMEraClaimServicesGet> getEraClaimServices(Long eraClaimId);

	List<ORMIdCodeDescriptionType> getAdjustCodesGlossary(SearchCriteria searchCriteria);

	List<ORMGetEOB> getEOB(SearchCriteria searchCriteria);

	List<ORMEobAttachmentGet> getEOBAttachment(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> uploadEOB(ORMEOBSave eob, ORMEobAttachmentsSave eobAtt, MultipartFile docFile);

	List<ORMEraProviderAdjustmentGet> getERAProviderAdjustment(Long eraId);

	ServiceResponse<ORMKeyValue> mapEraClaimServicesIDs(SearchCriteria searchCriteria);

	ERAOperationResult importERAFromGatewayEdi(SearchCriteria searchCriteria);
	
	ServiceResponse<ORMKeyValue> deleteERA(ORMDeleteRecord ormDeleteRecord);
	
	ServiceResponse<ORMKeyValue> mapEraClaimPaymentInsurance(SearchCriteria searchCriteria);
	
	ServiceResponse<ORMKeyValue> markAsPosted(SearchCriteria searchCriteria);
	
	ERAPaymentPostingResponse postERAPayment(SearchCriteria searchCriteria);

	ERAOperationResult importERAFromTextString(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> moveEraToOtherPractice(SearchCriteria searchCriteria);

	ServiceResponse<SubmissionProccessedClaimInfo> generateHCFA(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> updateProcessedClaims(List<SubmissionProccessedClaimInfo> objProcessedClaim);

	Boolean checkEOBPostedRecords(Long eob_id);

	ORMEobDetailGet getEobById(Long eobId);

	ServiceResponse<ORMKeyValue> downloadClaimResponse(SearchCriteria searchCriteria);

	List<ORM_277_ResponseGet> getEdiClaimStatus(SearchCriteria searchCriteria);

	List<ORM_277ClaimStatusDetail> getEdiClaimStatusDetailByClaimId(Long claimId);

	ServiceResponse<ORMKeyValue> markEdiClaimStatusAsWorked(SearchCriteria searchCriteria);

	ServiceResponse<ORMKeyValue> getEobIdByCriteria(SearchCriteria searchCriteria);

	
}
