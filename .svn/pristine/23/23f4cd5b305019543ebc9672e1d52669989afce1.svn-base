package com.ihc.ehr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.MAPIDGenerator;
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
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.EnumUtil.Operation;

@Repository
public class LetterDAOImpl implements LetterDAO {

	@Autowired
	private DBOperations db;
	
	/*@Override
	public List<ORMGetPatientLetters> searchPatientLetters(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String search=searchCriteria.getCriteria();		
		System.out.println("searchCriteriaString:"+search);
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", search, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetPatientLetters", ORMGetPatientLetters.class, lstParam);
	}*/
	
	
	@Override
	public List<ORMGetPatientLetters> searchPatientLetters(SearchCriteria searchCriteria) {
		String criteria = " ";
		String dtFrom = "";
		String dtTo = "";
		String ddlDateType = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				switch (pram.getName()) {
				case "patient_id":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and pl.patient_id = '" + pram.getValue() + "'";
						break;
					}
				case "dateType":
					if (pram.getValue() != "" && pram.getValue() != null) {
						ddlDateType = pram.getValue().toLowerCase();
						break;
					}
				case "modified_user":
					if (pram.getValue() != "" && pram.getValue() != null) {
						criteria += " and pl.modified_user = '" + pram.getValue() + "'";
						break;
					}
				case "dateFrom":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtFrom = pram.getValue();
						break;
					}
				case "dateTo":
					if (pram.getValue() != "" && pram.getValue() != null) {
						dtTo = pram.getValue();
						break;
					}
				default:
					break;
				}
			}
		}
		
		if(ddlDateType.equals("dos")) {//DOS
			if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
				criteria += " and convert(datetime,convert(varchar,dos,101)) between  convert(datetime,convert(varchar,'"+ dtFrom +"',101)) and convert(datetime,convert(varchar,'"+ dtTo +"',101)) ";
			} else if (!dtFrom.isEmpty()) {
				criteria += " and convert(datetime,convert(varchar,dos,101)) >= convert(datetime,convert(varchar,'"+ dtFrom +"',101)) ";
			}else if (!dtTo.isEmpty()) {
				criteria += " and convert(datetime,convert(varchar,dos,101)) <= CONVERT(datetime,convert(varchar,'"+ dtTo +"',101)) ";
			}
		}else if(ddlDateType.equals("createddate")){//Created Date
			if (!dtFrom.isEmpty() && !dtTo.isEmpty()) {
				criteria += " and convert(datetime,convert(varchar,pl.date_created,101)) between  convert(datetime,convert(varchar,'"+ dtFrom +"',101)) and convert(datetime,convert(varchar,'"+ dtTo +"',101)) ";
			} else if (!dtFrom.isEmpty()) {
				criteria += " and convert(datetime,convert(varchar,pl.date_created,101)) >= convert(datetime,convert(varchar,'"+ dtFrom +"',101)) ";
			}else if (!dtTo.isEmpty()) {
				criteria += " and convert(datetime,convert(varchar,pl.date_created,101)) <= CONVERT(datetime,convert(varchar,'"+ dtTo +"',101)) ";
			}
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));
		
		List<ORMGetPatientLetters> lst = db.getStoreProcedureData("spGetPatientLetters",
				ORMGetPatientLetters.class, lstParam);

		return lst;
	}
	
	@Override
	public List<ORMLetterTemplate> GetLetterTemplates(Long practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLetterTemplate", ORMLetterTemplate.class, lstParam);
	}
	
	@Override
	public List<ORMLetterSections> getLetterSections(Long practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLetterSections", ORMLetterSections.class, lstParam);
	}
	@Override
	public List<ORMLetterSubSections> getLetterSubSections(Long practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLetterSubSections", ORMLetterSubSections.class, lstParam);
	}
	@Override
	public List<ORMLetterTemplateSection> getLetterTemplateSections(Long practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLetterTemplateSections", ORMLetterTemplateSection.class, lstParam);
	}
	@Override
	public List<ORMLetterTemplateSubSection> getLetterTemplateSubSections(Long practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLetterTemplateSubSections", ORMLetterTemplateSubSection.class, lstParam);
	}
	@Override
	public List<ORMLetterHeaders> getLetterHeaders(Long practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLetterHeaders", ORMLetterHeaders.class, lstParam);
	}
	@Override
	public List<ORMLetterSections> getSettingsLetterSections(Long practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLetterSections", ORMLetterSections.class, lstParam);
	}
	@Override
	public List<ORMLetterSubSections> getSettingsLetterSubSections(Long practiceId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLetterSubSections", ORMLetterSubSections.class, lstParam);
	}
	@Override
	public Long saveupdateLetterHeader(ORMLetterHeaders obj) {
		System.out.println(obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		obj.setDeleted("false");
		if (obj.getHeader_id() != null && obj.getHeader_id() != "") {
			System.out.println("EDIT...");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return (Long.parseLong(obj.getHeader_id()));
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setHeader_id(db.IDGenerator("letter_header", Long.parseLong(obj.getPractice_id())).toString());
			System.out.println("SAVE...");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return (Long.parseLong(obj.getHeader_id()));
			else
				return (long) 0;
		}
	}
	@Override
	public Long saveupdateLetterSection(ORMLetterSections obj) {
		System.out.println(obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		obj.setDeleted(false);
		if (obj.getSection_id() != null && obj.getSection_id() != "0") {
			System.out.println("EDIT...");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return (Long.parseLong(obj.getSection_id()));
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setSection_id(db.IDGenerator("letter_section", Long.parseLong(obj.getPractice_id())).toString());
			System.out.println("SAVE...");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return (Long.parseLong(obj.getSection_id()));
			else
				return (long) 0;
		}
	}
	@Override
	public Long saveupdateLetterSubSection(ORMLetterSubSections obj) {
		System.out.println(obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		obj.setDeleted(false);
		if (obj.getSub_section_id() != null && obj.getSub_section_id() != "0") {
			System.out.println("EDIT...");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return (Long.parseLong(obj.getSub_section_id()));
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setSub_section_id(db.IDGenerator("letter_sub_section", Long.parseLong(obj.getPractice_id())).toString());
			System.out.println("SAVE...");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return (Long.parseLong(obj.getSub_section_id()));
			else
				return (long) 0;
		}
	}
	@Override
	public int deleteSelectedHeader(ORMDeleteRecord objDelete) {
		return db.deleteRecord(objDelete);
	}
	@Override
	public int deleteSelectedSection(ORMDeleteRecord objDelete) {
		return db.deleteRecord(objDelete);
	}
	@Override
	public int deleteSeletedSubSection(ORMDeleteRecord objDelete) {
		return db.deleteRecord(objDelete);
	}
	@Override
	public int deleteSelectedTemplate(ORMDeleteRecord objDelete) {
		return db.deleteRecord(objDelete);
	}
	@Override
	public Long saveupdateLetterTemplate(ORMLetterTemplate obj) {
		System.out.println(obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		obj.setDeleted("false");
		if (obj.getTemplate_id() != null && obj.getTemplate_id() != "0") {
			System.out.println("EDIT...");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return (Long.parseLong(obj.getTemplate_id()));
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setTemplate_id(db.IDGenerator("letter_template", Long.parseLong(obj.getPractice_id())).toString());
			System.out.println("SAVE...");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return (Long.parseLong(obj.getTemplate_id()));
			else
				return (long) 0;
		}
	}
	@Override
	public Long SaveTemplateSections(List<ORMLetterTemplateSection> obj) {
			int result = 0;
			if (obj != null && obj.size() > 0) {
				for (ORMLetterTemplateSection orm : obj) {
					orm.setDate_modified(DateTimeUtil.getCurrentDateTime());
					if (orm.getTemplate_section_id() == null) // New
					{
						orm.setTemplate_section_id(db.IDGenerator("letter_template_section", Long.parseLong(orm.getPractice_id())));
						orm.setDate_created(DateTimeUtil.getCurrentDateTime());
						result += db.SaveEntity(orm, Operation.ADD);
					} else// modification
					{
						result += db.SaveEntity(orm, Operation.EDIT);
					}
				}
			}
			return (long) result;
	}
	@Override
	public Long SaveTemplateSubSections(List<ORMLetterTemplateSubSection> obj) {
			int result = 0;
			if (obj != null && obj.size() > 0) {
				for (ORMLetterTemplateSubSection orm : obj) {
					orm.setDate_modified(DateTimeUtil.getCurrentDateTime());
					if (orm.getTemplate_sub_section_id() == null) // New
					{
						orm.setTemplate_sub_section_id(db.IDGenerator("letter_template_sub_section", Long.parseLong(orm.getPractice_id())));
						orm.setDate_created(DateTimeUtil.getCurrentDateTime());
						result += db.SaveEntity(orm, Operation.ADD);
					} else// modification
					{
						result += db.SaveEntity(orm, Operation.EDIT);
					}
				}
			}
			return (long) result;
	}
	@Override
	public Long saveupdatePatientLetter(ORMPatientLetter obj) {
		System.out.println(obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		obj.setDeleted("false");
		if (obj.getPatient_letter_id() != null && obj.getPatient_letter_id() != "0") {
			System.out.println("EDIT...");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return (Long.parseLong(obj.getPatient_letter_id()));
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setPatient_letter_id(db.IDGenerator("patient_letter", Long.parseLong(obj.getPractice_id())).toString());
			System.out.println("SAVE...");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return (Long.parseLong(obj.getPatient_letter_id()));
			else
				return (long) 0;
		}
	}
	@Override
	public int deleteSelectedLetter(ORMDeleteRecord objDelete) {
		return db.deleteRecord(objDelete);
	}
	//long updateLetterStatus(SearchCriteria searchCriteria);
	@Override
	public long updateLetterStatus(SearchCriteria searchCriteria) {
		String User = "";
		String DOS = "";
		String patient_id = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("User")) {
					User = pram.getValue();
				} else if (pram.getName().equals("DOS")) {
					DOS = pram.getValue();
				} else if (pram.getName().equals("patient_id")) {
					patient_id = pram.getValue();
				}
			}

		}
		String Query = "update patient_order set followup_detail =  'By "+ User.toUpperCase() +" ("+ DateTimeUtil.getCurrentDateTime() +")', modified_user ='"+ User +"', date_modified = GETDATE(), follow_up_notes='Letter Sent', follow_up_action='' where  CONVERT(varchar,order_date,101) = CONVERT(varchar,'"+ DOS  +"',101) and  patient_id= '"+ patient_id +"'  and  follow_up_notes ='Follow Up Required' and isnull(deleted,0)<>1 ";
		return db.ExecuteUpdateQuery(Query);
	}
	@Override
	public String signLetter(List<ORMKeyValue> lstSignLetterData) {
		try {
			String patLetterId ="";
			String userName ="";
			String dateTime ="";
			String practiceId ="";
			String strQuery = "";
			String Query = "";
			String result = "";
			//List<SpParameters> lstParam = new ArrayList<>();
	
			if (lstSignLetterData != null && !lstSignLetterData.isEmpty()) {
				for (ORMKeyValue pram : lstSignLetterData) {
					if (pram.getKey().equals("patLetterId")) {
						patLetterId = pram.getValue();
					} else if (pram.getKey().equals("userName")) {
						userName = pram.getValue();
					} else if (pram.getKey().equals("dateTime")) {
						dateTime = pram.getValue();
					} else if (pram.getKey().equals("practiceId")) {
						practiceId = pram.getValue();
					}
				}
			}
			strQuery = "update patient_letter set signed = '1', signed_by = '"+userName+"', signed_date = '"+dateTime+"', modified_user = '"+userName+"', date_modified = GETDATE(), client_date_modified = '"+dateTime+"'  "
	                + "where patient_letter_id = '"+patLetterId+"'";
			if(db.ExecuteUpdateQuery(strQuery)>0) {
				//change scalar_value to id.
				Query = "select isnull(signature_path,'') as id  from users where user_name = '"+userName+"' and practice_id = '"+practiceId+"' and isnull(deleted,0)<>1";
				//int abc = db.ExecuteUpdateQuery(Query);
				//return String.valueOf(abc);
				
				 List<?> list = db.getQueryData(Query, MAPIDGenerator.class);
		            if (list.size() > 0) {
		                for (Object orm : list) {
		                	result = ((MAPIDGenerator) orm).getId();
		                }
		            }
		            return result;
				
				
			}else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	@Override
	public Long saveNotesonPrint(ORMPatientNote obj) {
		System.out.println(obj.toString());
		obj.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (obj.getPatient_note_id() != null && obj.getPatient_note_id() != "0") {
			System.out.println("IN EDIT...");
			if (db.SaveEntity(obj, Operation.EDIT) > 0)
				return (Long.parseLong(obj.getPatient_note_id()));
			else
				return (long) 0;
		} else {
			obj.setDate_created(DateTimeUtil.getCurrentDateTime());
			obj.setPatient_note_id(db.IDGenerator("patient_note", Long.parseLong(obj.getPractice_id())).toString());
			System.out.println("IN SAVE...");
			if (db.SaveEntity(obj, Operation.ADD) > 0)
				return (Long.parseLong(obj.getPatient_note_id()));
			else
				return (long) 0;
		}
	}
}