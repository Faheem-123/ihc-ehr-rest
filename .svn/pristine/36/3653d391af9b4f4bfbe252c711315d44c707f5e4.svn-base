package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.dao.BillingDAO;
import com.ihc.ehr.model.ERAOperationResult;
import com.ihc.ehr.model.ERAPaymentPostingResponse;
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



@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	BillingDAO billingDOA;

	@Override
	public List<ORMGetBillingSummaryClaims> getClaimBillingSummary(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.getClaimBillingSummary(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> addUpdateBatch(ORMSaveBatch obj) {
		// TODO Auto-generated method stub
		return billingDOA.addUpdateBatch(obj);
	}

	@Override
	public List<ORMFourColumGeneric> getUnlockBatch(String practice_id) {
		// TODO Auto-generated method stub
		return billingDOA.getUnlockBatch(practice_id);
	}

	@Override
	public List<ORMGetBatchClaimCount> getClaimBatchCount(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.getClaimBatchCount(searchCriteria);
	}

	@Override
	public List<ORMScalarValue> getBatchClaimList(String batch_id) {
		// TODO Auto-generated method stub
		return billingDOA.getBatchClaimList(batch_id);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveClaimBatchDetail(List<ORMSaveClaimBatchDetail> lstSave) {
		// TODO Auto-generated method stub
		return billingDOA.saveClaimBatchDetail(lstSave);
	}

	@Override
	public List<ORMBatchDetail> getBatchDetail(String practice_id, String batch_id) {
		// TODO Auto-generated method stub
		return billingDOA.getBatchDetail(practice_id,batch_id);
	}

	@Override
	public int lockBatch(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.lockBatch(searchCriteria);
	}

	@Override
	public List<ORMClaim_Batch_Errors> generateBatch_5010_P(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.generateBatch_5010_P(searchCriteria);
	}

	@Override
	public List<ORMTwoColumnGeneric> getBatchPath(String batch_id) {
		// TODO Auto-generated method stub
		return billingDOA.getBatchPath(batch_id);
	}

	@Override
	public int IgnoreError(String error_id, String user_id) {
		// TODO Auto-generated method stub
		return billingDOA.IgnoreError(error_id,user_id);
	}

	@Override
	public int deleteClaimFromBatch(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.deleteClaimFromBatch(searchCriteria);
	}

	@Override
	public String uploadBatchToGatewayEDI(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.uploadBatchToGatewayEDI(searchCriteria);
	}

	@Override
	public String downloadBatchResponse(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.downloadBatchResponse(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveDenialMessage(ORMDenialMessagesSave ormDenialMessagesSave) {
		// TODO Auto-generated method stub
		return billingDOA.saveDenialMessage(ormDenialMessagesSave);
	}

	@Override
	public ServiceResponse<ORMKeyValue> resolveDenialMessage(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.resolveDenialMessage(searchCriteria);
	}

	@Override
	public List<ORMEraListGet> getEraList(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.getEraList(searchCriteria);
	}

	@Override
	public List<ORMEraGet> getEraDetails(Long eraId) {
		// TODO Auto-generated method stub
		return billingDOA.getEraDetails(eraId);
	}

	@Override
	public List<ORMEraClaimListGet> getEraClaimList(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.getEraClaimList(searchCriteria);
	}

	@Override
	public ORMEraClaimDetailGet getEraClaimDetail(Long practiceId,Long eraClaimId) {
		// TODO Auto-generated method stub
		return billingDOA.getEraClaimDetail(practiceId,eraClaimId);
	}

	@Override
	public List<ORMEraClaimServicesGet> getEraClaimServices(Long eraClaimId) {
		// TODO Auto-generated method stub
		return billingDOA.getEraClaimServices(eraClaimId);
	}

	@Override
	public List<ORMIdCodeDescriptionType> getAdjustCodesGlossary(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.getAdjustCodesGlossary(searchCriteria);
	}

	@Override
	public List<ORMGetEOB> getEOB(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.getEOB(searchCriteria);
	}

	@Override
	public List<ORMEobAttachmentGet> getEOBAttachment(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.getEOBAttachment(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> uploadEOB(ORMEOBSave eob, ORMEobAttachmentsSave eobAtt, MultipartFile docFile) {
		// TODO Auto-generated method stub
		return billingDOA.uploadEOB(eob,eobAtt,docFile);
	}

	@Override
	public List<ORMEraProviderAdjustmentGet> getERAProviderAdjustment(Long eraId) {
		// TODO Auto-generated method stub
		return billingDOA.getERAProviderAdjustment(eraId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> mapEraClaimServicesIDs(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.mapEraClaimServicesIDs(searchCriteria);
	}

	@Override
	public ERAOperationResult importERAFromGatewayEdi(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.importERAFromGatewayEdi(searchCriteria);
	}
	
	@Override
	public ServiceResponse<ORMKeyValue> deleteERA(ORMDeleteRecord ormDeleteRecord) {
		// TODO Auto-generated method stub
		return billingDOA.deleteERA(ormDeleteRecord);
	}
	
	
	@Override
	public ServiceResponse<ORMKeyValue> mapEraClaimPaymentInsurance(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.mapEraClaimPaymentInsurance(searchCriteria);
	}	
	
	@Override
	public ServiceResponse<ORMKeyValue> markAsPosted(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.markAsPosted(searchCriteria);
	}

	@Override
	public ERAPaymentPostingResponse postERAPayment(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.postERAPayment(searchCriteria);
	}

	@Override
	public ERAOperationResult importERAFromTextString(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.importERAFromTextString(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> moveEraToOtherPractice(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.moveEraToOtherPractice(searchCriteria);
	}

	@Override
	public ServiceResponse<SubmissionProccessedClaimInfo> generateHCFA(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.generateHCFA(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> updateProcessedClaims(List<SubmissionProccessedClaimInfo> objProcessedClaim) {
		// TODO Auto-generated method stub
		return billingDOA.updateProcessedClaims(objProcessedClaim);
	}

	@Override
	public Boolean checkEOBPostedRecords(Long eob_id) {
		// TODO Auto-generated method stub
		return billingDOA.checkEOBPostedRecords(eob_id);
	}

	@Override
	public ORMEobDetailGet getEobById(Long eobId) {
		// TODO Auto-generated method stub
		return billingDOA.getEobById(eobId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> downloadClaimResponse(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.downloadClaimResponse(searchCriteria);
	}

	@Override
	public List<ORM_277_ResponseGet> getEdiClaimStatus(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.getEdiClaimStatus(searchCriteria);
	}

	@Override
	public List<ORM_277ClaimStatusDetail> getEdiClaimStatusDetailByClaimId(Long claimId) {
		// TODO Auto-generated method stub
		return billingDOA.getEdiClaimStatusDetailByClaimId(claimId);
	}

	@Override
	public ServiceResponse<ORMKeyValue> markEdiClaimStatusAsWorked(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.markEdiClaimStatusAsWorked(searchCriteria);
	}

	@Override
	public ServiceResponse<ORMKeyValue> getEobIdByCriteria(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return billingDOA.getEobIdByCriteria(searchCriteria);
	}

	
	
}
