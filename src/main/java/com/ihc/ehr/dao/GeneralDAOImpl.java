package com.ihc.ehr.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ORMAppSetting;
import com.ihc.ehr.model.ORMAuditLog;
import com.ihc.ehr.model.ORMChartReportDetailsforPHR;
import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.ORMDocCategories;
import com.ihc.ehr.model.ORMDocCategoriesList;
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMFourColumGeneric;
import com.ihc.ehr.model.ORMGetBillingUsersList;
import com.ihc.ehr.model.ORMGetChartSummary;
import com.ihc.ehr.model.ORMGetCityState;
import com.ihc.ehr.model.ORMGetLocation;
import com.ihc.ehr.model.ORMGetPatientAllergiesSummary;
import com.ihc.ehr.model.ORMGetPatientHeaderInfo;
import com.ihc.ehr.model.ORMGetPatientInsuranceName;
import com.ihc.ehr.model.ORMGetPatientMedicationSummary;
import com.ihc.ehr.model.ORMGetPatientProblemsSummary;
import com.ihc.ehr.model.ORMGetProviderUsers;
import com.ihc.ehr.model.ORMGetReferringProvider;
import com.ihc.ehr.model.ORMGetUserChartModuleSetting;
import com.ihc.ehr.model.ORMGetUserRights;
import com.ihc.ehr.model.ORMHeaderVitals;
import com.ihc.ehr.model.ORMHeaderVitals_PHR;
import com.ihc.ehr.model.ORMIdCodeDescription;
import com.ihc.ehr.model.ORMIdCodeDescriptionDisplay;
import com.ihc.ehr.model.ORMIdName;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMLabelPrintDataGet;
import com.ihc.ehr.model.ORMLoginUserLog;
import com.ihc.ehr.model.ORMModules;
import com.ihc.ehr.model.ORMOneColumnGeneric;
import com.ihc.ehr.model.ORMPhysicianSearch;
import com.ihc.ehr.model.ORMPracticeInfo;
import com.ihc.ehr.model.ORMPracticeUsers;
import com.ihc.ehr.model.ORMPracticeUsersList;
import com.ihc.ehr.model.ORMProviderList;
import com.ihc.ehr.model.ORMSaveDocument;
import com.ihc.ehr.model.ORMScalarValue;
import com.ihc.ehr.model.ORMSignedResults;
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORMUserRoleList;
import com.ihc.ehr.model.ORMZipCityState;
import com.ihc.ehr.model.ScanDocumentData;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.UploadFile;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.FileUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.PDFOperations;

@Repository
public class GeneralDAOImpl implements GeneralDAO {

	@Autowired
	private DBOperations db;

	@Override
	public ORMPracticeInfo getPracticeInfo(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		List<ORMPracticeInfo> lst = db.getStoreProcedureData("spGetPracticeInfo", ORMPracticeInfo.class, lstParam);

		if (lst != null && lst.size() > 0)
			return lst.get(0);
		else {
			return null;
		}
	}

	@Override
	public List<ORMProviderList> getProvider(Long practice_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetAttendingProviderList", ORMProviderList.class, lstParam);
	}

	@Override
	public List<ORMThreeColum> getLabCategory(Long practiceID) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practiceID", practiceID.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetLabCategories", ORMThreeColum.class, lstParam);
	}

	@Override
	public List<ORMGetLocation> getLocation(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("proGetLocation", ORMGetLocation.class, lstParam);
	}

	@Override
	public List<ORMPracticeUsers> getPracticeUserName(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("PracticeId", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetALLUserName", ORMPracticeUsers.class, lstParam);
	}

	@Override
	public int deleteRecord(ORMDeleteRecord objDelete) {
		// TODO Auto-generated method stub
		// String query="update "+obj.getTable_name()+ " set deleted=1
		// ,modified_user='"+obj.getLogedInuser()+"', date_modified=getdate() where
		// "+obj.getColumn_name()+" ="+ obj.gettable_id();
		// System.out.println("updatequery: " + query);
		// return db.ExecuteUpdateQuery(query);
		// List<SpParameters> lstParam = new ArrayList<>();

		// lstParam.add(new SpParameters("table_name",
		// objDelete.getTable_name().toString(), String.class,ParameterMode.IN));
		// lstParam.add(new SpParameters("column_name",
		// objDelete.getColumn_name().toString(), String.class,ParameterMode.IN));
		// lstParam.add(new SpParameters("column_id",
		// objDelete.getColumn_id().toString(),String.class,ParameterMode.IN));
		// lstParam.add(new SpParameters("modified_user",
		// objDelete.getModified_user().toString(),String.class, ParameterMode.IN));
		// lstParam.add(new SpParameters("client_date_time",
		// objDelete.getClient_date_time().toString(),String.class, ParameterMode.IN));
		// lstParam.add(new
		// SpParameters("client_ip",objDelete.getClient_ip().toString(),String.class,
		// ParameterMode.IN));
		// lstParam.add(new SpParameters("criteria","",String.class, ParameterMode.IN));

		return db.deleteRecord(objDelete);

	}

	@Override
	public int updateQuery(String query) {
		return db.ExecuteUpdateQuery(query);

	}

	@Override
	public List<ORMGetCityState> getCityState(String zip_code) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("zipCode", zip_code, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetCityState", ORMGetCityState.class, lstParam);
	}

	@Override
	public List<ORMGetReferringProvider> searchReferringProvider(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String searchOption = "";
		String searchCriteriaString = "";
		String search = searchCriteria.getCriteria();
		System.out.println("searchCriteriaString:" + search);
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				search = pram.getValue();
				searchOption = pram.getOption();
				System.out.println(
						"param:" + pram.getName() + "    Value:" + pram.getValue() + "    Option:" + pram.getOption());
				break;
			}

		}
		// var Criteria:String = " and rp.practice_id='"+GeneralOptions.practiceID+"'";
		// Criteria = Criteria + " and ( ( rp.last_name like
		// '"+txtReferralSearch.text.replace("'","").split(" ")[0]+"%' and rp.first_name
		// like '"+(txtReferralSearch.text.replace("'","").split(" ")[1] != null ?
		// txtReferralSearch.text.replace("'","").split(" ")[1] : "" )+"%') ";
		// Criteria = Criteria + " OR replace( replace(replace(replace(rp.phone,'
		// ',''),'(',''),')',''),'-','')=
		// '"+GeneralOptions.ReplaceAll(GeneralOptions.ReplaceAll(GeneralOptions.ReplaceAll(GeneralOptions.ReplaceAll(txtReferralSearch.text,"
		// ",""),"(",""),")",""),"-","")+"' ";
		// Criteria = Criteria + " OR replace( replace(replace(replace(rp.fax,'
		// ',''),'(',''),')',''),'-','')=
		// '"+GeneralOptions.ReplaceAll(GeneralOptions.ReplaceAll(GeneralOptions.ReplaceAll(GeneralOptions.ReplaceAll(txtReferralSearch.text,"
		// ",""),"(",""),")",""),"-","")+"') ";
		//
		if (search.isEmpty())
			return null;
		searchCriteriaString = " and rp.practice_id='" + searchCriteria.getPractice_id().toString() + "'";
		if (searchOption.equals("Consult_criteria")) {
			searchCriteriaString += " and rp.consult_type_id='" + search + "'";
		} else {

			String[] strReferalName = search.replace("'", "").split(" ");
			searchCriteriaString += " and ( rp.last_name like '" + search.replace("'", "").split(" ")[0] + "%'";
			if (strReferalName.length > 1) {
				searchCriteriaString += " and rp.first_name like '" + search.replace("'", "").split(" ")[1] + "%'";
			}
			searchCriteriaString += " ) ";
		}
		// searchCriteriaString=searchCriteriaString+" and ( ( rp.last_name like
		// '"+search.replace("'","").split(" ")[0]+"%' and rp.first_name like
		// '"+(search.replace("'","").split(" ")[1] != null ?
		// search.replace("'","").split(" ")[1] : "" )+"%')) ";
		// searchCriteriaString=searchCriteriaString+ " OR replace(
		// replace(replace(replace(rp.phone,' ',''),'(',''),')',''),'-','')=
		// replace(replace(replace(replace("+search+"," ",""),"(",""),")",""),"-","")";

		System.out.println(searchCriteriaString);
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", searchCriteriaString, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetReferringProvider", ORMGetReferringProvider.class, lstParam);

	}

	@Override
	public List<ORMDocCategories> getDocCategories(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetDocCategories", ORMDocCategories.class, lstParam);
	}

	@Override
	public List<ORMDocCategoriesList> getDocCategoriesList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		// return db.getStoreProcedureData("spGetDocCategories", ORMDocCategories.class,
		// lstParam);
		return db.getStoreProcedureData("spGetDocCategorieslist", ORMDocCategoriesList.class, lstParam);
	}

	@Override
	public List<ORMDocumentPath> getDocPath(String practice_id, String doc_category) {
		// TODO Auto-generated method stub
		String query = " select top 1 id,category_name,upload_path,download_path from document_path where category_name='"
				+ doc_category + "' and practice_id=" + practice_id.toString() + "";

		return db.getQueryData(query, ORMDocumentPath.class);
	}

	@Override
	public Long SaveDocument(ORMSaveDocument patDoc, MultipartFile docFile, String docCategory) {
		// TODO Auto-generated method stub
		System.out.println("Data Received: " + patDoc.toString());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		Date date = new Date();
		String directory = "";
		String category_path = "";
		String originalFilename = "";
		Boolean isDocUploaded = false;

		Long generatedId;

		if (docFile != null && !docFile.isEmpty()) {
			try {

				List<ORMDocumentPath> lstPath = getDocPath(patDoc.getPractice_id().toString(), docCategory);
				System.out.println("Get doc" + lstPath.size());

				if (!lstPath.isEmpty()) {
					category_path = lstPath.get(0).getUpload_path();
					category_path = category_path + "\\" + patDoc.getPractice_id() + "\\" + docCategory;

					System.out.println("Directory" + category_path);
				}
				directory = GeneralOperations.checkPathYearMonthWise(patDoc.getPractice_id().toString(),
						lstPath.get(0).getUpload_path().toString(), docCategory);

				System.out.println("directory " + directory);
				originalFilename = docFile.getOriginalFilename();

			} catch (Exception e) {
				System.out.println("Picture Upload Error:" + e.getMessage());
			}
		}

		if (patDoc.getPatient_document_id() != null && patDoc.getPatient_document_id() != 0) {
			if (docFile != null && !docFile.isEmpty()) {
				try {

					patDoc.setDate_modified(dateFormat.format(date));
					// directory=directory+"\\"+patDoc.getEmp_id()+"\\";
					String fileName = GeneralOperations.GetDatetimeFileName() + "."
							+ FilenameUtils.getExtension(originalFilename);
					System.out.println("fileName" + fileName);
					File destinationFile = new File(GeneralOperations.CheckPath(category_path + "\\" + directory),
							fileName);
					docFile.transferTo(destinationFile);
					System.out.println("destinationFile" + directory);
					System.out.println("fileName" + fileName);

					patDoc.setLink(directory + "\\" + fileName);
					isDocUploaded = true;

				} catch (Exception e) {
					System.out.println("Document Upload Error:" + e.getMessage());
				}
			} else {
				isDocUploaded = true;
				//
			}

			if (isDocUploaded) {
				// System.out.println(""+e.getMessage());
				if (db.SaveEntity(patDoc, Operation.EDIT) > 0) {
					patDoc.setDate_modified(dateFormat.format(date));
					generatedId = patDoc.getPatient_document_id();
				} else {
					generatedId = (long) 0;
				}
			} else {
				generatedId = (long) 0;
			}
		} else {
			patDoc.setPatient_document_id(db.IDGenerator("patient_document", patDoc.getPractice_id()));
			patDoc.setDate_created(dateFormat.format(date));
			patDoc.setDate_modified(dateFormat.format(date));

			if (docFile != null && !docFile.isEmpty()) {
				try {
					// directory=directory+"\\"+empDoc.getEmp_id()+"\\";
					String fileName = GeneralOperations.GetDatetimeFileName() + "."
							+ FilenameUtils.getExtension(originalFilename);
					System.out.println("Final path" + category_path + "\\" + directory);
					File destinationFile = new File(GeneralOperations.CheckPath(category_path + "\\" + directory),
							fileName);
					System.out.println("destinationFile path" + destinationFile.toString());
					docFile.transferTo(destinationFile);

					patDoc.setLink(directory + "\\" + fileName);
					isDocUploaded = true;

				} catch (Exception e) {
					System.out.println("Document Upload Error:" + e.getMessage());
				}
			}

			if (isDocUploaded) {
				if (db.SaveEntity(patDoc, Operation.ADD) > 0) {
					generatedId = patDoc.getPatient_document_id();
				} else {
					generatedId = (long) 0;
				}
			} else {
				generatedId = (long) 0;
			}
		}
		System.out.println("Return " + Long.toString(generatedId));
		return generatedId;
	}

	@Override
	public List<ORMDocumentPath> getDocumentPaths(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetDocumentPaths", ORMDocumentPath.class, lstParam);
	}

	@Override
	public List<ORMGetUserChartModuleSetting> getUserChartModuleSetting(Long setting_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chartTemplate_id", setting_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spgetUserChartModuleSettings", ORMGetUserChartModuleSetting.class, lstParam);
	}

	@Override
	public InputStream GetDocument() throws IOException {

		// TODO Auto-generated method stub

		File initialFile = new File(
				"D:\\ihcDocs\\Documents\\500\\PatientDocuments\\2018\\01\\22\\20180122111528315.pdf");
		InputStream targetStream = new FileInputStream(initialFile);
		return targetStream;

	}

	@Override
	public byte[] downloadFile(String fileName) {
		String filePath = fileName;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream inputStream = new BufferedInputStream(fis);
			byte[] fileBytes = new byte[(int) file.length()];
			inputStream.read(fileBytes);
			inputStream.close();
			return fileBytes;
		} catch (IOException ex) {
			System.err.println(ex);
			return null;
		}
	}

	@Override
	public List<ORMGetUserRights> getUserRights(String user_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("user_id", user_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetUserRights", ORMGetUserRights.class, lstParam);
	}

	@Override
	public List<ORMProviderList> getDistinctLocationProviders(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("getDistinctLocationProviders", ORMProviderList.class, lstParam);
	}

	@Override
	public List<ORMGetProviderUsers> getProviderUser(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("getProviderUsers", ORMGetProviderUsers.class, lstParam);
	}

	@Override
	public List<ORMIdName> AuthenticateProviderUser(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				lstParam.add(new SpParameters(pram.getName(), pram.getValue(), String.class, ParameterMode.IN));
			}
		}
		return db.getStoreProcedureData("spAuthenticateProviderUser", ORMIdName.class, lstParam);

	}

	@Override
	public List<ORMIdName> getAuthorizationUsers(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetAuthorizationUsers", ORMIdName.class, lstParam);
	}

	@Override
	public ORMGetPatientInsuranceName getPatientInsuranceName(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String patientId = "0";
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", searchCriteria.getPractice_id().toString(), String.class,
				ParameterMode.IN));

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equalsIgnoreCase("patient_id")) {
					patientId = pram.getValue().toString();
				}

				lstParam.add(
						new SpParameters(pram.getName(), pram.getValue().toString(), String.class, ParameterMode.IN));
			}
		}

		List<ORMGetPatientInsuranceName> lst = db.getStoreProcedureData("spGetPatientInsuranceName",
				ORMGetPatientInsuranceName.class, lstParam);

		if (lst != null && lst.size() > 0)
			return lst.get(0);
		else {
			ORMGetPatientInsuranceName obj = new ORMGetPatientInsuranceName();
			obj.setPatient_id(Long.parseLong(patientId));
			return obj;
		}
	}

	@Override
	public List<ORMIdCodeDescriptionDisplay> getMaritalStatusList() {
		return db.getStoreProcedureData("spGetMaritalStatusList", ORMIdCodeDescriptionDisplay.class, null);
	}

	@Override
	public List<ORMIdCodeDescriptionDisplay> getLanguageList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class,
				ParameterMode.IN));
		
		return db.getStoreProcedureData("getLanguageList", ORMIdCodeDescriptionDisplay.class, lstParam);
	}

	@Override
	public List<ORMIdCodeDescription> getGenderIdentityList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetGenderIdentity", ORMIdCodeDescription.class, null);
	}

	@Override
	public List<ORMIdCodeDescription> getSexualOrientationList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetSexualOrientation", ORMIdCodeDescription.class, null);
	}

	@Override
	public List<ORMIdCodeDescription> getOMBRaceList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("getOMBRace", ORMIdCodeDescription.class, null);
	}

	@Override
	public List<ORMIdCodeDescription> getOMBEthnicityList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("getOMBEthnicity", ORMIdCodeDescription.class, null);
	}

	@Override
	public List<ORMIdCodeDescription> getCountryParishCodeByState(String state) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("state", state, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetCountryParishCodeByState", ORMIdCodeDescription.class, lstParam);
	}

	@Override
	public List<ORMZipCityState> getCityStateByZip(String zip) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("zipCode", zip, String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetCityStateByZip", ORMZipCityState.class, lstParam);
	}

	/*
	 * @Override public List<ORMProvider_Eligibility> getProvider_Eligibility(Long
	 * practice_id) { System.out.println(
	 * "*******************---------------------------------------------" +
	 * practice_id); List<SpParameters> lstParam = new ArrayList<>();
	 * lstParam.add(new SpParameters("practice_id", practice_id.toString(),
	 * String.class, ParameterMode.IN)); return
	 * db.getStoreProcedureData("spGetEligibilityDetail",
	 * ORMProvider_Eligibility.class, lstParam); }
	 */

	@Override
	public List<ORMIdCodeDescription> getRelationshipList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetRelationshipList", ORMIdCodeDescription.class, null);
	}

	@Override
	public List<ORMIdCodeDescription> getImmRegistryStatusList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetImmRegistryStatusList", ORMIdCodeDescription.class, null);
	}

	@Override
	public List<ORMIdCodeDescription> getImmRegistryPublicityCodeList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetImmRegistryPublicityCodeList", ORMIdCodeDescription.class, null);
	}

	@Override
	public List<ORMIdCodeDescription> getImmRegistryProcectionIndicatorList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetImmRegistryProtectionIndicatorList", ORMIdCodeDescription.class, null);
	}

	@Override
	public List<ORMPhysicianSearch> getProviderInfoById(Long providerId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("provider_id", providerId.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetProviderInfoById", ORMPhysicianSearch.class, lstParam);
	}

	@Override
	public ORMDocumentPath getDocumentPathByCategory(Long practice_id, String cateogry) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("category", cateogry, String.class, ParameterMode.IN));
		List<ORMDocumentPath> lst = db.getStoreProcedureData("spGetDocumentPathByCategory", ORMDocumentPath.class,
				lstParam);

		if (lst != null && lst.size() > 0)
			return lst.get(0);
		else {
			return null;
		}
	}

	@Override
	public List<ORMIdCodeDescription> getSnomedRelationshipList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetSnomedRelationshipList", ORMIdCodeDescription.class, null);
	}

	@Override
	public ServiceResponse<ORMKeyValue> uploadScan(MultipartFile multipartFile, ScanDocumentData scanDocument)
	// Long practiceId, Long patientId, String docName,
	// String docDate, String docType, Long categoryId, String clientDate, String
	// clientUser, String clientIp)
	{
		// TODO Auto-generated method stub

		int result = 0;
		String link = "";
		Double fileSizeKB = (double) 0;
		Long docId = db.IDGenerator("patient_document", scanDocument.getPractice_id());
		String uploadPath = "";
		String autoGeneratedFileName = "";
		String docCategory = "PatientDocuments";

		ORMDocumentPath objPath = getDocumentPathByCategory(scanDocument.getPractice_id(), "PatientDocuments");
		if (objPath != null) {
			uploadPath = objPath.getUpload_path();
		}

		autoGeneratedFileName = docId.toString() + FileUtil.GetDatetimeFileName() + ".pdf";

		UploadFile uploadFile = FileUtil.UploadDocumentYearMonthDayWise(multipartFile, uploadPath,
				scanDocument.getPractice_id().toString(), docCategory, autoGeneratedFileName);
		if (uploadFile != null) {
			link = uploadFile.getLink();
			fileSizeKB = uploadFile.getSize_KB();
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		ORMSaveDocument ormSaveDocument;

		String strUpdateQuery = "";

		if (!link.isEmpty()) {

			ormSaveDocument = new ORMSaveDocument();
			ormSaveDocument.setPatient_document_id(docId);
			ormSaveDocument.setPatient_id(scanDocument.getPatient_id());
			ormSaveDocument.setPractice_id(scanDocument.getPractice_id());
			ormSaveDocument.setLink(link);

			ormSaveDocument.setDoc_categories_id(scanDocument.getCategory_id());
			ormSaveDocument.setDocument_date(scanDocument.getDocument_date());
			ormSaveDocument.setName(scanDocument.getDocument_name());
			ormSaveDocument.setOriginal_file_name(scanDocument.getDocument_name());
			ormSaveDocument.setDate_created(DateTimeUtil.getCurrentDateTime());
			ormSaveDocument.setDate_modified(DateTimeUtil.getCurrentDateTime());
			ormSaveDocument.setClient_date_created(scanDocument.getClient_date());
			ormSaveDocument.setClient_date_modified(scanDocument.getClient_date());
			ormSaveDocument.setCreated_user(scanDocument.getClient_user());
			ormSaveDocument.setModified_user(scanDocument.getClient_user());
			ormSaveDocument.setSystem_ip(scanDocument.getClient_ip());
			ormSaveDocument.setDoc_size(Double.toString(fileSizeKB));

			switch (scanDocument.getDocument_type()) {
			case "insurance_card":
				ormSaveDocument.setDoc_type("Insurance Card");
				ormSaveDocument.setComment("Scanned Insurance Card");
				break;
			case "id_card":
				ormSaveDocument.setDoc_type("ID Card");
				ormSaveDocument.setComment("Scanned ID Card");
				break;
			case "driving_license":
				ormSaveDocument.setDoc_type("Driving License");
				ormSaveDocument.setComment("Scanned Driving License");
				break;
			case "patient_agreement":
				ormSaveDocument.setDoc_type("Patient Agreement");
				ormSaveDocument.setComment("Scanned Patient Agreement");
				break;
			case "patient_document":
				ormSaveDocument.setComment("Scanned Document");
				break;
			default:
				break;
			}

			result = db.SaveEntity(ormSaveDocument, Operation.ADD);

			if (scanDocument.getDocument_type().equalsIgnoreCase("insurance_card")) {

				if (result > 0) {
					strUpdateQuery = " update patient_insurance  set document_id='" + docId.toString() + "',"
							+ "date_modified=getdate(),client_date_modified='" + scanDocument.getClient_date()
							+ "',modified_user='" + scanDocument.getClient_user() + "',system_ip='"
							+ scanDocument.getClient_ip() + "'" + " where patientinsurance_id='"
							+ scanDocument.getPatientinsurance_id() + "'";

					result = db.ExecuteUpdateQuery(strUpdateQuery);
				}

			} else if (scanDocument.getDocument_type().equalsIgnoreCase("id_card")
					|| scanDocument.getDocument_type().equalsIgnoreCase("driving_license")
					|| scanDocument.getDocument_type().equalsIgnoreCase("patient_agreement")) {

				if (result > 0) {

					strUpdateQuery = " update patient  set " + scanDocument.getDocument_type() + " = '" + link + "',"
							+ "date_modified=getdate(),client_date_modified='" + scanDocument.getClient_date()
							+ "',modified_user='" + scanDocument.getClient_user() + "',system_ip='"
							+ scanDocument.getClient_ip() + "'" + " where patient_id='" + scanDocument.getPatient_id()
							+ "'";

					result = db.ExecuteUpdateQuery(strUpdateQuery);
				}

			}

			if (result == 0) {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An error occured while saving record.");
			} else {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse("File has been uploaded/saved successfully.");
				resp.setResult(link);
			}
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("Unable to upload scaned document.");
		}

		// }

		return resp;
	}

	@Override
	public List<ORMProviderList> getBillingProviderList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetBillingProviderList", ORMProviderList.class, lstParam);
	}

	@Override
	public List<ORMOneColumnGeneric> getStatesList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetStatesList", ORMOneColumnGeneric.class, null);
	}

	@Override
	public List<ORMGetBillingUsersList> getBillingUsersList(String billing_practice_id, String practiceID) {
		// TODO Auto-generated method stub
		List<SpParameters> objParm = new ArrayList<>();
		objParm.add(new SpParameters("billing_practice_id", billing_practice_id, String.class, ParameterMode.IN));
		objParm.add(new SpParameters("practice_id", practiceID, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetBillingUsersList", ORMGetBillingUsersList.class, objParm);
	}

	@Override
	public List<ORMAppSetting> getAppSetting(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetAppSetting", ORMAppSetting.class, lstParam);
	}

	@Override
	public List<ORMUserRoleList> getUserAllRoles(Long PracticeId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", PracticeId.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetRole", ORMUserRoleList.class, lstParam);
	}

	@Override
	public List<ORMPracticeUsersList> getPracticeUsersList(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("PracticeId", practice_id.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetAllUserList", ORMPracticeUsersList.class, lstParam);
	}

	@Override
	public List<ORMIdCodeDescription> getSmokingStatusList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetSmokingStatus", ORMIdCodeDescription.class, null);
	}

	@Override
	public List<ORMIdCodeDescription> getDischargeDispositionList() {
		// TODO Auto-generated method stub
		return db.getStoreProcedureData("spGetDischargeDisposition", ORMIdCodeDescription.class, null);
	}

	@Override
	public ORMGetPatientHeaderInfo getPatientHeader(Long patientId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		List<ORMGetPatientHeaderInfo> lstPatients = db.getStoreProcedureData("spGetPatientHeaderInfo",
				ORMGetPatientHeaderInfo.class, lstParam);
		if (lstPatients != null && lstPatients.size() > 0) {
			return (ORMGetPatientHeaderInfo) lstPatients.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ORMHeaderVitals getPatientVitals(Long patientId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		List<ORMHeaderVitals> lstPatients = db.getStoreProcedureData("spGetPatientHeaderVitals", ORMHeaderVitals.class,
				lstParam);
		if (lstPatients != null && lstPatients.size() > 0) {
			return (ORMHeaderVitals) lstPatients.get(0);
		} else {
			return null;
		}
	}

	/*
	 * @Override public ORMGetPracticeInfo getPracticeInfo(Long practiceId) {
	 * List<SpParameters> lstParam = new ArrayList<>(); lstParam.add(new
	 * SpParameters("practiceId", practiceId.toString(), String.class,
	 * ParameterMode.IN)); List<ORMGetPracticeInfo> lstPatients =
	 * db.getStoreProcedureData("spGetPracticeInfo_PHR", ORMGetPracticeInfo.class,
	 * lstParam); if (lstPatients != null && lstPatients.size() > 0) { return
	 * (ORMGetPracticeInfo) lstPatients.get(0); } else { return null; } }
	 */
	@Override
	public ORMHeaderVitals_PHR getPatientVitalsPHR(Long patientId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		List<ORMHeaderVitals_PHR> lstPatients = db.getStoreProcedureData("spGetPatientHeaderVitals_PHR",
				ORMHeaderVitals_PHR.class, lstParam);
		if (lstPatients != null && lstPatients.size() > 0) {
			return (ORMHeaderVitals_PHR) lstPatients.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ORMGetPatientAllergiesSummary> getPatientAllergies(Long patientId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		List<ORMGetPatientAllergiesSummary> lstAllergies = db.getStoreProcedureData("spGetPatientAllergiesSummary",
				ORMGetPatientAllergiesSummary.class, lstParam);

		return lstAllergies;
	}

	@Override
	public List<ORMGetPatientMedicationSummary> getPatientMedicationSummary(Long patientId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		List<ORMGetPatientMedicationSummary> lstMedication = db.getStoreProcedureData("spGetPatientPrescriptionSummary",
				ORMGetPatientMedicationSummary.class, lstParam);
		return lstMedication;
	}

	@Override
	public List<ORMGetPatientProblemsSummary> getPatientProblems(Long patientId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patientId.toString(), String.class, ParameterMode.IN));
		List<ORMGetPatientProblemsSummary> lstProblems = db.getStoreProcedureData("spGetPaientProblemSummary",
				ORMGetPatientProblemsSummary.class, lstParam);
		return lstProblems;
	}

	@Override
	public List<ORMGetChartSummary> getPHREncounterSummary(String patient_id) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_id", patient_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartSummary", ORMGetChartSummary.class, lstParam);
	}

	@Override
	public List<ORMChartReportDetailsforPHR> getReportHeader(String chart_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("chart_id", chart_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetChartReportHeaderforPHR", ORMChartReportDetailsforPHR.class, lstParam);
	}

	@Override
	public List<ORMFourColumGeneric> getInsuranceDetails(String patientId) {
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("patient_ID", patientId, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetPHRpatientInsurance", ORMFourColumGeneric.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> loginUserLog(ORMLoginUserLog ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setLogintime_server(DateTimeUtil.getCurrentDateTime());
		ormSave.setLogid(db.IDGenerator("user_login_log", Long.parseLong("1")).toString());

		result = db.SaveEntity(ormSave, Operation.ADD);

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult(ormSave.getLogid());
		}
		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> logoutUserLog(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		String id = "";
		String clientDate = "";
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().equals("loginId")) {
					id = pram.getValue();
				}
				if (pram.getName().equals("clientDate")) {
					clientDate = pram.getValue();
				}
			}
		}
		int result = db.ExecuteUpdateQuery("update user_login_log set logouttime ='" + clientDate
				+ "' ,logouttime_server='" + DateTimeUtil.getCurrentDateTime() + "' where logid = '" + id + "'");
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("1");
		}
		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> auditLog(ORMAuditLog ormSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormSave.setAccess_date(DateTimeUtil.getCurrentDateTime());
		result = db.SaveEntity(ormSave, Operation.ADD);

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("1");
		}
		return resp;
	}

	@Override
	public List<ORMThreeColumGeneric> getOverrideSecuritySettings(Long practice_id, String role_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practice_id.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("role_id", role_id, String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetOverrideSecurityRights", ORMThreeColumGeneric.class, lstParam);
	}

	@Override
	public List<ORMModules> getAllModules(Long practice_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("PracticeID", practice_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetModule", ORMModules.class, lstParam);
	}

	@Override
	public String ConvertHtmltoPDF(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String doc_url = "";
		try {

			PDFOperations objPDF = new PDFOperations();
			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
				String path = "";
				//String FooterText = "";
				String strHTMLString = "";
				String category = "";
				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
					if (pram.getName().equals("path")) {
						path = pram.getValue();
					} else if (pram.getName().equals("footer")) {
						//FooterText = pram.getValue();
					} else if (pram.getName().equals("html")) {
						strHTMLString = pram.getValue();
					} else if (pram.getName().equals("category")) {
						category = pram.getValue();
					}
				}

				String f_name = searchCriteria.getPractice_id() + GeneralOperations.GetDatetimeFileName() + ".pdf";
				String full_file_path = GeneralOperations
						.checkPathYearMonthWise(searchCriteria.getPractice_id().toString(), path, category);
				System.out.println("Full PDF Path:" + full_file_path);
				// String file_name=practice_id+GenericOperations.getDatetimeFileName()+".pdf";

				if (objPDF.ConvertHtmltoPDF(strHTMLString,
						path + searchCriteria.getPractice_id().toString() + "\\" + category + "\\" + full_file_path,
						f_name) != "") {
					System.out.println("PDF File Created:-" + full_file_path);
					String[] splitedPath = full_file_path.split("\\\\");
					doc_url = splitedPath[splitedPath.length - 3] + "\\" + splitedPath[splitedPath.length - 2] + "\\"
							+ splitedPath[splitedPath.length - 1] + "\\" + f_name;
					System.out.println("Retun Path:-" + doc_url);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc_url;
	}

	@Override
	public List<ORMTwoColumnGeneric> getBillingPractices(String user_id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("userid", user_id.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetBillingPractices", ORMTwoColumnGeneric.class, lstParam);
	}

	@Override
	public ORMLabelPrintDataGet getLabelPrintData(String label_type, String id) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("label_type", label_type.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("id", id.toString(), String.class, ParameterMode.IN));
		List<ORMLabelPrintDataGet> lst = db.getStoreProcedureData("spGetLabelPrintData", ORMLabelPrintDataGet.class,
				lstParam);

		if (lst != null && lst.size() > 0)
			return lst.get(0);
		else {
			return null;
		}
	}
	
	@Override
	public List<ORMIdCodeDescription> getPOSList() {
		// TODO Auto-generated method stub
		
		return db.getStoreProcedureData("spGetAllPOS", ORMIdCodeDescription.class, null);
	}

	@Override
	public byte[] getDocumentBytes(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		byte[] documentBytes=null;
		//DocumentBytes documentBytes=new DocumentBytes();

		try {

			String docCategory = "";
			String docLink = "";
			Long practiceId = searchCriteria.getPractice_id();

			if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {

				for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

					switch (pram.getName()) {
					case "category":
						docCategory = pram.getValue();
						break;
					case "link":
						docLink = pram.getValue();
						break;
					default:
						break;
					}
				}
				
				

				ORMDocumentPath objPath = getDocumentPathByCategory(practiceId, docCategory);

				if (objPath != null && GeneralOperations.isNotNullorEmpty(objPath.getUpload_path())) {

					String docFullPath = objPath.getUpload_path() + "\\" + practiceId + "\\" +docCategory+"\\"+ docLink;

					System.out.println("docFullPath:" + docFullPath);
					File file = new File(docFullPath);

					if (file.exists()) {

						documentBytes= FileCopyUtils.copyToByteArray(file);
						//byte[] document = FileCopyUtils.copyToByteArray(file);
						
						//documentBytes.setBytes(document);
						//documentBytes.setStatus(ServiceResponseStatus.SUCCESS);
						
						// HttpHeaders header = new HttpHeaders();
						// header.setContentType(new MediaType("application", "pdf"));
						// header.set("Content-Disposition", "inline; filename=" + file.getName());
						// header.setContentLength(document.length);

					} else {
						
						//documentBytes.setStatus(ServiceResponseStatus.ERROR);
						//documentBytes.setError_message("File Does not Exists");
						System.out.println("File Does not Exists:" + docFullPath);
					}
				}
			}

		} catch (Exception e) {
			//documentBytes.setStatus(ServiceResponseStatus.ERROR);
			//documentBytes.setError_message(e.getMessage());
			e.printStackTrace();
		}

		return documentBytes;
	}

	@Override
	public int getUnreadMessageCount(String receiverId) {
		// TODO Auto-generated method stub
		int count=0;
		
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("reciver_id", receiverId.toString(), String.class, ParameterMode.IN));
		List<ORMScalarValue> lst = db.getStoreProcedureData("spGetUnreadMessageCount", ORMScalarValue.class , lstParam);	
	
		if (lst != null && lst.size() > 0)
			count = Integer.parseInt(lst.get(0).getScalar_value());
		
		return count;
	}
	
	@Override
	public String getHPPublicKey(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String publicKey="";
		
		String practiceId=searchCriteria.getPractice_id().toString();
		String serviceName= "";
		
		
		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {
				if (pram.getName().toLowerCase().equals("service_name")) {
					serviceName = pram.getValue();
				}
			}
		}	
		
		
		String query="select top 1 isnull(public_key,'') as public_key "
				+ " from credit_card_configuration "
				+ " where isnull(deleted,0)<>1 "
				+ " and practice_id='"+practiceId+"' "
				+ " and isnull(service_name,'')='"+serviceName+"'";
		
		publicKey= db.getQuerySingleResult(query);

		
		return publicKey;
	}
	
}
