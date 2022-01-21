package com.ihc.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.dao.LetterDAO;
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

@Service
public class LetterServiceImpl implements LetterService {

	@Autowired
	private LetterDAO letterdDao;
	
	@Override
	public List<ORMGetPatientLetters> searchPatientLetters(SearchCriteria searchCriteria) {
		return letterdDao.searchPatientLetters(searchCriteria);
	}
	@Override
	public List<ORMLetterTemplate> GetLetterTemplates(Long practiceId) {
		return letterdDao.GetLetterTemplates(practiceId);
	}
	@Override
	public List<ORMLetterSections> getLetterSections(Long practiceId) {
		return letterdDao.getLetterSections(practiceId);
	}
	@Override
	public List<ORMLetterSubSections> getLetterSubSections(Long practiceId) {
		return letterdDao.getLetterSubSections(practiceId);
	}
	@Override
	public List<ORMLetterTemplateSection> getLetterTemplateSections(Long practiceId) {
		return letterdDao.getLetterTemplateSections(practiceId);
	}
	@Override
	public List<ORMLetterTemplateSubSection> getLetterTemplateSubSections(Long practiceId) {
		return letterdDao.getLetterTemplateSubSections(practiceId);
	}
	@Override
	public List<ORMLetterHeaders> getLetterHeaders(Long practiceId) {
		return letterdDao.getLetterHeaders(practiceId);
	}
	@Override
	public List<ORMLetterSections> getSettingsLetterSections(Long practiceId) {
		return letterdDao.getSettingsLetterSections(practiceId);
	}
	@Override
	public List<ORMLetterSubSections> getSettingsLetterSubSections(Long practiceId) {
		return letterdDao.getSettingsLetterSubSections(practiceId);
	}
	@Override
	public Long saveupdateLetterHeader(ORMLetterHeaders obj) {
		return letterdDao.saveupdateLetterHeader(obj);
	}
	@Override
	public Long saveupdateLetterSection(ORMLetterSections obj) {
		return letterdDao.saveupdateLetterSection(obj);
	}
	@Override
	public Long saveupdateLetterSubSection(ORMLetterSubSections obj) {
		return letterdDao.saveupdateLetterSubSection(obj);
	}
	@Override
	public int deleteSelectedHeader(ORMDeleteRecord obj) {
		return letterdDao.deleteSelectedHeader(obj);
	}
	@Override
	public int deleteSelectedSection(ORMDeleteRecord obj) {
		return letterdDao.deleteSelectedSection(obj);
	}
	@Override
	public int deleteSeletedSubSection(ORMDeleteRecord obj) {
		return letterdDao.deleteSeletedSubSection(obj);
	}
	@Override
	public int deleteSelectedTemplate(ORMDeleteRecord obj) {
		return letterdDao.deleteSelectedTemplate(obj);
	}
	@Override
	public Long saveupdateLetterTemplate(ORMLetterTemplate obj) {
		return letterdDao.saveupdateLetterTemplate(obj);
	}
	@Override
	public Long SaveTemplateSections(List<ORMLetterTemplateSection> obj) {
		return letterdDao.SaveTemplateSections(obj);
	}
	@Override
	public Long SaveTemplateSubSections(List<ORMLetterTemplateSubSection> obj) {
		return letterdDao.SaveTemplateSubSections(obj);
	}
	@Override
	public Long saveupdatePatientLetter(ORMPatientLetter obj) {
		// TODO Auto-generated method stub
		return letterdDao.saveupdatePatientLetter(obj);
	}
	@Override
	public int deleteSelectedLetter(ORMDeleteRecord obj) {
		return letterdDao.deleteSelectedLetter(obj);
	}
	@Override
	public long updateLetterStatus(SearchCriteria searchCriteria) {
		return letterdDao.updateLetterStatus(searchCriteria);
	}
	@Override
	public String signLetter(List<ORMKeyValue> lstSignLetterData) {
		return letterdDao.signLetter(lstSignLetterData);
	}
	@Override
	public Long saveNotesonPrint(ORMPatientNote obj) {
		// TODO Auto-generated method stub
		return letterdDao.saveNotesonPrint(obj);
	}
}
