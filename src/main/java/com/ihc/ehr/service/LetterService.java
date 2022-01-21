package com.ihc.ehr.service;

import java.util.List;

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

public interface LetterService {
	List<ORMGetPatientLetters> searchPatientLetters(SearchCriteria searchCriteria);
	List<ORMLetterTemplate> GetLetterTemplates(Long practiceId);
	List<ORMLetterSections> getLetterSections(Long practiceId);
	List<ORMLetterSubSections> getLetterSubSections(Long practiceId);
	List<ORMLetterTemplateSection> getLetterTemplateSections(Long practiceId);
	List<ORMLetterTemplateSubSection> getLetterTemplateSubSections(Long practiceId);
	List<ORMLetterHeaders> getLetterHeaders(Long practiceId);
	List<ORMLetterSections> getSettingsLetterSections(Long practiceId);
	List<ORMLetterSubSections> getSettingsLetterSubSections(Long practiceId);
	public Long saveupdateLetterHeader(ORMLetterHeaders obj);
	public Long saveupdateLetterSection(ORMLetterSections obj);
	public Long saveupdateLetterSubSection(ORMLetterSubSections obj);
	int deleteSelectedHeader(ORMDeleteRecord obj);
	int deleteSelectedSection(ORMDeleteRecord obj);
	int deleteSeletedSubSection(ORMDeleteRecord obj);
	int deleteSelectedTemplate(ORMDeleteRecord obj);
	int deleteSelectedLetter(ORMDeleteRecord obj);
	public Long saveupdateLetterTemplate(ORMLetterTemplate obj);
	public Long SaveTemplateSections(List<ORMLetterTemplateSection> obj);
	public Long SaveTemplateSubSections(List<ORMLetterTemplateSubSection> obj);
	public Long saveupdatePatientLetter(ORMPatientLetter obj);
	long updateLetterStatus(SearchCriteria searchCriteria);
	String signLetter(List<ORMKeyValue> lstSignLetterData);
	public Long saveNotesonPrint(ORMPatientNote obj);
}
