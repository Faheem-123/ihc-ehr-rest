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

import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMGetChartReferral;
import com.ihc.ehr.model.ORMGetChartReferralDetail;
import com.ihc.ehr.model.ORMGetPatientReferral;
import com.ihc.ehr.model.ORMGetReferralEmailDetail;
import com.ihc.ehr.model.ORMGetReferralFaxDetail;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMPatientReferral;
import com.ihc.ehr.model.ORMReferralChartSummary;
import com.ihc.ehr.model.ORMReferralLabSummary;
import com.ihc.ehr.model.ORMReferralStaffNotes;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMgetLabOrderResultsSummary;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.Wrapper_ObjectSave;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.service.ReferralService;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@RestController
@RequestMapping("/referral")
public class ReferralController {

	@Autowired
	ReferralService referralService;
	@Autowired
	GeneralService generalService;
	
	@RequestMapping("/getPatientReferrals")
	public @ResponseBody ResponseEntity<List<ORMGetPatientReferral>> getPatientReferrals(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside searchCriteria: SearchCriteria="+searchCriteria.toString());
		
		List<ORMGetPatientReferral> lstSearchPatient=referralService.getPatientReferrals(searchCriteria);
		
		return new ResponseEntity<List<ORMGetPatientReferral>>(lstSearchPatient , HttpStatus.OK);
	}
	@RequestMapping("/getChartReferralsView/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetChartReferral>> getChartReferrals(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMGetChartReferral> lstHM = referralService.getChartReferrals(patient_id);

		return new ResponseEntity<List<ORMGetChartReferral>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getChartReferralsDetail/{referral_id}")
	public @ResponseBody ResponseEntity<ORMGetChartReferralDetail> getChartReferralsDetail(
			@PathVariable(value = "referral_id") String referral_id) {
		ORMGetChartReferralDetail lstHM = referralService.getChartReferralsDetail(referral_id);

		return new ResponseEntity<ORMGetChartReferralDetail>(lstHM, HttpStatus.OK);
	}
	
	@RequestMapping("/getPatientLabOrders/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMReferralLabSummary>> getPatientLabOrders(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMReferralLabSummary> lstHM = referralService.getPatientLabOrders(patient_id);

		return new ResponseEntity<List<ORMReferralLabSummary>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getReferralChartSummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMReferralChartSummary>> getReferralChartSummary(
			@PathVariable(value = "patient_id") String patient_id) {
		GeneralOperations.logMsg("Inside getSpeciality:  patient_id=" + patient_id);

		List<ORMReferralChartSummary> lstHM = referralService.getReferralChartSummary(patient_id);

		return new ResponseEntity<List<ORMReferralChartSummary>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getConsultType/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMThreeColum>> getConsultType(
			@PathVariable(value = "practice_id") String practice_id) {
		GeneralOperations.logMsg("Inside getSpeciality:  Practice_ID=" + practice_id);

		List<ORMThreeColum> lstHM = referralService.getConsultType(practice_id);

		return new ResponseEntity<List<ORMThreeColum>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getReferralsFaxDetails/{referral_id}")
	public @ResponseBody ResponseEntity<List<ORMGetReferralFaxDetail>> getReferralsFaxDetails(
			@PathVariable(value = "referral_id") String referral_id) {
		
		List<ORMGetReferralFaxDetail> lstHM = referralService.getReferralsFaxDetails(referral_id);

		return new ResponseEntity<List<ORMGetReferralFaxDetail>>(lstHM, HttpStatus.OK);
	}
	@RequestMapping("/getReferralsEmailDetails/{referral_id}")
	public @ResponseBody ResponseEntity<List<ORMGetReferralEmailDetail>> getReferralsEmailDetails(
			@PathVariable(value = "referral_id") String referral_id) {
		
		List<ORMGetReferralEmailDetail> lstHM = referralService.getReferralsEmailDetails(referral_id);

		return new ResponseEntity<List<ORMGetReferralEmailDetail>>(lstHM, HttpStatus.OK);
	}	
	
	@RequestMapping("/savePatientReferralRequest")
	public ResponseEntity<ORMPatientReferral> savePatientReferralRequest(
			@RequestBody ORMPatientReferral obj)
	{
		System.out.println("savePatientReferralRequest: "+obj.toString());
		referralService.savePatientReferralRequest(obj);
		
		return new ResponseEntity<ORMPatientReferral>(obj, HttpStatus.OK);
		
	}
	@RequestMapping("/savePatientReferral")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> savePatientReferral(
			@RequestBody Wrapper_ObjectSave<ORMPatientReferral> obj)
	{
		String path="";
		String dest_path="";
		ORMPatientReferral objRef=obj.getOrmSave();	
		for (ORMKeyValue item : obj.getLstKeyValue()) 
		{

			if (item.getKey().equalsIgnoreCase("upload_path")) 
			{
				path=item.getValue();
			}
			else if (item.getKey().equalsIgnoreCase("dest_path")) 
			{
				dest_path=item.getValue();
			}
			System.out.println(item.toString());
		}
		ServiceResponse<ORMKeyValue> rep=referralService.savePatientReferral(objRef,path,dest_path);			
		
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(rep, HttpStatus.OK);
	}
	@RequestMapping("/UpdateReferralRequestStatus")
	public ResponseEntity<ORMReferralStaffNotes> UpdateReferralRequestStatus(
			@RequestBody Wrapper_ObjectSave<ORMReferralStaffNotes> objAppSaveWrapper)
	{
		ORMReferralStaffNotes obj=objAppSaveWrapper.getOrmSave();	
		//System.out.println(objAppSaveWrapper.getKeyVlaueList().toString());
		//System.out.println("UpdateReferralRequestStatus: "+obj.toString());
		String ref_status="";
		for (ORMKeyValue item : objAppSaveWrapper.getLstKeyValue()) {

			if (item.getKey().equalsIgnoreCase("referal_status")) 
			{
				ref_status=item.getValue();
			}

			System.out.println(item.toString());
		}
		referralService.UpdateReferralRequestStatus(obj,ref_status);
		
		return new ResponseEntity<ORMReferralStaffNotes>(obj, HttpStatus.OK);
		
	}
	@RequestMapping("/deletePatientReferralRequest")
	public int  deletePatientReferralRequest(
			@RequestBody ORMDeleteRecord obj)
	{
		System.out.println("deletePatientReferralRequest");
		System.out.println(obj.toString());
		
		//ORMDeleteRecord ormDelete=new ORMDeleteRecord();
		obj.setTable_name("patient_referral");
		obj.setColumn_name("referral_id");		
//		ormDelete.setColumn_id(obj.getId());
//		ormDelete.setClient_date_time(obj.getClientDateTime());
//		ormDelete.setModified_user(obj.getLogedInuser());		
//		ormDelete.setClient_ip(obj.getClientIP());	
//		
		//obj.setTable_name("patient_referral");
		//obj.setColumn_name("referral_id");
		//return 0;
		 return generalService.deleteRecord(obj);
		 //return new ResponseEntity<ORMDeleteRecord>(obj, HttpStatus.OK);
	}
	
	
	@RequestMapping("/getPatientOrderResults")
	public @ResponseBody ResponseEntity<List<ORMgetLabOrderResultsSummary>> getPatientOrderResults(
			@RequestBody SearchCriteria searchCriteria)
	{	
		GeneralOperations.logMsg("Inside searchCriteria: SearchCriteria="+searchCriteria.toString());
		
		List<ORMgetLabOrderResultsSummary> lstSearchPatient=referralService.getPatientOrderResults(searchCriteria);
		
		return new ResponseEntity<List<ORMgetLabOrderResultsSummary>>(lstSearchPatient , HttpStatus.OK);
	}	
	@RequestMapping("/GenerateTempLetter")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> GenerateTempLetter(
			@RequestBody SearchCriteria searchCriteria)
	{	
		String lstSearchPatient=referralService.GenerateTempLetter(searchCriteria);
		GeneralOperations.logMsg("GenerateTempLetter "+lstSearchPatient);
		
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (lstSearchPatient !="") {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(lstSearchPatient.toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}	
	
	
}
