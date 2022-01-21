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
import com.ihc.ehr.model.ORMGetPatientLetters;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMLetterHeaders;
import com.ihc.ehr.model.ORMLetterSections;
import com.ihc.ehr.model.ORMLetterSubSections;
import com.ihc.ehr.model.ORMLetterTemplate;
import com.ihc.ehr.model.ORMLetterTemplateSection;
import com.ihc.ehr.model.ORMLetterTemplateSubSection;
import com.ihc.ehr.model.ORMPatientLetter;
import com.ihc.ehr.model.ORMPatientNote;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.service.LetterService;

@RestController
@RequestMapping("/letter")
public class LetterController {

	@Autowired
	LetterService letterService;
	
	@RequestMapping("/searchPatientLetters")
	public @ResponseBody ResponseEntity<List<ORMGetPatientLetters>> searchPatientLetters(
			@RequestBody SearchCriteria searchCriteria)
	{
		List<ORMGetPatientLetters> obj=letterService.searchPatientLetters(searchCriteria);		
		return new ResponseEntity<List<ORMGetPatientLetters>>(obj , HttpStatus.OK);
	}
	@RequestMapping("/GetLetterTemplates/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMLetterTemplate>> GetLetterTemplates(
			@PathVariable(value = "practiceId") Long practiceId) {
		List<ORMLetterTemplate> obj = letterService.GetLetterTemplates(practiceId);
		return new ResponseEntity<List<ORMLetterTemplate>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/getLetterSections/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMLetterSections>> getLetterSections(
			@PathVariable(value = "practiceId") Long practiceId) {
		List<ORMLetterSections> obj = letterService.getLetterSections(practiceId);
		return new ResponseEntity<List<ORMLetterSections>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/getLetterSubSections/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMLetterSubSections>> getLetterSubSections(
			@PathVariable(value = "practiceId") Long practiceId) {
		List<ORMLetterSubSections> obj = letterService.getLetterSubSections(practiceId);
		return new ResponseEntity<List<ORMLetterSubSections>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/getLetterTemplateSections/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMLetterTemplateSection>> getLetterTemplateSections(
			@PathVariable(value = "practiceId") Long practiceId) {
		List<ORMLetterTemplateSection> obj = letterService.getLetterTemplateSections(practiceId);
		return new ResponseEntity<List<ORMLetterTemplateSection>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/getLetterTemplateSubSections/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMLetterTemplateSubSection>> getLetterTemplateSubSections(
			@PathVariable(value = "practiceId") Long practiceId) {
		List<ORMLetterTemplateSubSection> obj = letterService.getLetterTemplateSubSections(practiceId);
		return new ResponseEntity<List<ORMLetterTemplateSubSection>>(obj, HttpStatus.OK);
	}
	////
	@RequestMapping("/getLetterHeaders/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMLetterHeaders>> getLetterHeaders(
			@PathVariable(value = "practiceId") Long practiceId) {
		List<ORMLetterHeaders> obj = letterService.getLetterHeaders(practiceId);
		return new ResponseEntity<List<ORMLetterHeaders>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/getSettingsLetterSections/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMLetterSections>> getSettingsLetterSections(
			@PathVariable(value = "practiceId") Long practiceId) {
		List<ORMLetterSections> obj = letterService.getSettingsLetterSections(practiceId);
		return new ResponseEntity<List<ORMLetterSections>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/getSettingsLetterSubSections/{practiceId}")
	public @ResponseBody ResponseEntity<List<ORMLetterSubSections>> getSettingsLetterSubSections(
			@PathVariable(value = "practiceId") Long practiceId) {
		List<ORMLetterSubSections> obj = letterService.getSettingsLetterSubSections(practiceId);
		return new ResponseEntity<List<ORMLetterSubSections>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/saveupdateLetterHeader")
	public ResponseEntity<ORMLetterHeaders> saveupdatePaymentCategory(
			@RequestBody ORMLetterHeaders obj) {
		letterService.saveupdateLetterHeader(obj);
		return new ResponseEntity<ORMLetterHeaders>(obj, HttpStatus.OK);
	}
	@RequestMapping("/saveupdateLetterSection")
	public ResponseEntity<ORMLetterSections> saveupdateLetterSection(
			@RequestBody ORMLetterSections obj) {
		letterService.saveupdateLetterSection(obj);
		return new ResponseEntity<ORMLetterSections>(obj, HttpStatus.OK);
	}
	@RequestMapping("/saveupdateLetterSubSection")
	public ResponseEntity<ORMLetterSubSections> saveupdateLetterSubSection(
			@RequestBody ORMLetterSubSections obj) {
		letterService.saveupdateLetterSubSection(obj);
		return new ResponseEntity<ORMLetterSubSections>(obj, HttpStatus.OK);
	}
	@RequestMapping("/deleteSelectedHeader")
	public int deleteSelectedHeader(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("Delete letter settings header ID: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("letter_header");
		ormDeleteRecord.setColumn_name("header_id");
		return letterService.deleteSelectedHeader(ormDeleteRecord);
	}
	@RequestMapping("/deleteSelectedSection")
	public int deleteSelectedSection(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("Delete letter settings section ID: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("letter_section");
		ormDeleteRecord.setColumn_name("section_id");
		return letterService.deleteSelectedSection(ormDeleteRecord);
	}
	@RequestMapping("/deleteSeletedSubSection")
	public int deleteSeletedSubSection(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("Delete letter settings sub section ID: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("letter_sub_section");
		ormDeleteRecord.setColumn_name("sub_section_id");
		return letterService.deleteSeletedSubSection(ormDeleteRecord);
	}
	@RequestMapping("/deleteSelectedTemplate")
	public int deleteSelectedTemplate(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("Delete letter Template ID: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("letter_template");
		ormDeleteRecord.setColumn_name("template_id");
		return letterService.deleteSelectedTemplate(ormDeleteRecord);
	}
	@RequestMapping("/saveupdateLetterTemplate")
	public ResponseEntity<ORMLetterTemplate> saveupdateLetterTemplate(
			@RequestBody ORMLetterTemplate obj) {
		letterService.saveupdateLetterTemplate(obj);
		return new ResponseEntity<ORMLetterTemplate>(obj, HttpStatus.OK);
	}
	@RequestMapping("/SaveTemplateSections")
	public ResponseEntity<List<ORMLetterTemplateSection>> SaveTemplateSections(
			@RequestBody List<ORMLetterTemplateSection> obj) {
		letterService.SaveTemplateSections(obj);
		return new ResponseEntity<List<ORMLetterTemplateSection>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/SaveTemplateSubSections")
	public ResponseEntity<List<ORMLetterTemplateSubSection>> SaveTemplateSubSections(
			@RequestBody List<ORMLetterTemplateSubSection> obj) {
		letterService.SaveTemplateSubSections(obj);
		return new ResponseEntity<List<ORMLetterTemplateSubSection>>(obj, HttpStatus.OK);
	}
	@RequestMapping("/saveupdatePatientLetter")
	public ResponseEntity<ORMPatientLetter> saveupdatePatientLetter(
			@RequestBody ORMPatientLetter obj) {
		letterService.saveupdatePatientLetter(obj);
		return new ResponseEntity<ORMPatientLetter>(obj, HttpStatus.OK);
	}
	@RequestMapping("/deleteSelectedLetter")
	public int deleteSelectedLetter(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		System.out.println("Delete letter Template ID: " + ormDeleteRecord.getColumn_id());
		ormDeleteRecord.setTable_name("patient_letter");
		ormDeleteRecord.setColumn_name("patient_letter_id");
		return letterService.deleteSelectedLetter(ormDeleteRecord);
	}
	@RequestMapping("/updateLetterStatus")
	public long updateLetterStatus(@RequestBody SearchCriteria searchCriteria) {
		return letterService.updateLetterStatus(searchCriteria);

	}
	//@RequestMapping("/signLetter")
	//public String signLetter(@RequestBody List<ORMKeyValue> lstSignLetterData)
	//{
	//	return letterService.signLetter(lstSignLetterData);
	//}
	@RequestMapping("/signLetter")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> signLetter(
			@RequestBody List<ORMKeyValue> lstSignLetterData) {
		
		String result=letterService.signLetter(lstSignLetterData);
		
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();;
		
		if (result == "") {
			resp.setResult("");
		} else {
			resp.setResult(result);
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp , HttpStatus.OK);
		

	}
	@RequestMapping("/saveNotesonPrint")
	public ResponseEntity<ORMPatientNote> saveNotesonPrint(
			@RequestBody ORMPatientNote obj) {
		letterService.saveNotesonPrint(obj);
		return new ResponseEntity<ORMPatientNote>(obj, HttpStatus.OK);
	}
}
