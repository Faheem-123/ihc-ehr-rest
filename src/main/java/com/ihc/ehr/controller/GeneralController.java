package com.ihc.ehr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihc.ehr.model.ORMAppSetting;
import com.ihc.ehr.model.ORMAuditLog;
import com.ihc.ehr.model.ORMChartReportDetailsforPHR;
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
import com.ihc.ehr.model.ORMThreeColum;
import com.ihc.ehr.model.ORMThreeColumGeneric;
import com.ihc.ehr.model.ORMTwoColumnGeneric;
import com.ihc.ehr.model.ORMUserRoleList;
import com.ihc.ehr.model.ORMZipCityState;
import com.ihc.ehr.model.ScanDocumentData;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.service.GeneralService;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.GeneralOperations;

@RestController
@RequestMapping("/general")
public class GeneralController {

	@Autowired
	GeneralService generalService;

	@RequestMapping("/getPracticeInfo/{practice_id}")
	public @ResponseBody ResponseEntity<ORMPracticeInfo> getPracticeInfo(
			@PathVariable(value = "practice_id") Long practice_id) {
		ORMPracticeInfo obj = generalService.getPracticeInfo(practice_id);
		return new ResponseEntity<ORMPracticeInfo>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getProviderList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMProviderList>> getProvider(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMProviderList> obj = generalService.getProvider(practice_id);
		return new ResponseEntity<List<ORMProviderList>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getOverrideSecuritySettings/{practice_id}/{role_id}")
	public @ResponseBody ResponseEntity<List<ORMThreeColumGeneric>> getOverrideSecuritySettings(
			@PathVariable(value = "practice_id") Long practice_id, @PathVariable(value = "role_id") String role_id) {
		List<ORMThreeColumGeneric> obj = generalService.getOverrideSecuritySettings(practice_id, role_id);

		return new ResponseEntity<List<ORMThreeColumGeneric>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getAllModules/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMModules>> getAllModules(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMModules> obj = generalService.getAllModules(practice_id);

		return new ResponseEntity<List<ORMModules>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getLabCategory/{practiceID}")
	public @ResponseBody ResponseEntity<List<ORMThreeColum>> getLabCategory(
			@PathVariable(value = "practiceID") Long practiceID) {
		List<ORMThreeColum> obj = generalService.getLabCategory(practiceID);
		return new ResponseEntity<List<ORMThreeColum>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getlocation/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetLocation>> getLocation(
			@PathVariable(value = "practice_id") Long practice_id) {

		List<ORMGetLocation> obj = generalService.getLocation(practice_id);

		return new ResponseEntity<List<ORMGetLocation>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getAppSetting/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMAppSetting>> getAppSetting(
			@PathVariable(value = "practice_id") Long practice_id) {

		List<ORMAppSetting> obj = generalService.getAppSetting(practice_id);

		return new ResponseEntity<List<ORMAppSetting>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getPracticeUserName/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMPracticeUsers>> getPracticeUserName(
			@PathVariable(value = "practice_id") Long practice_id) {

		List<ORMPracticeUsers> obj = generalService.getPracticeUserName(practice_id);

		return new ResponseEntity<List<ORMPracticeUsers>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getUserChartModuleSetting/{setting_id}")
	public @ResponseBody ResponseEntity<List<ORMGetUserChartModuleSetting>> getUserChartModuleSetting(
			@PathVariable(value = "setting_id") Long setting_id) {

		List<ORMGetUserChartModuleSetting> obj = generalService.getUserChartModuleSetting(setting_id);

		return new ResponseEntity<List<ORMGetUserChartModuleSetting>>(obj, HttpStatus.OK);

	}

	// @RequestMapping("/getPatientReferrals")
	// public @ResponseBody ResponseEntity<List<ORMGetPatientReferral>>
	// getPatientReferrals(
	// @RequestBody SearchCriteria searchCriteria)
	// {
	// GeneralOperations.logMsg("Inside searchCriteria:
	// SearchCriteria="+searchCriteria.toString());
	//
	// //List<ORMGetPatientReferral>
	// lstSearchPatient=referralService.getPatientReferrals(searchCriteria);
	//
	// return new ResponseEntity<List<ORMGetPatientReferral>>(lstSearchPatient ,
	// HttpStatus.OK);
	// }

	@RequestMapping("/getcitystate/{zip_code}")
	public @ResponseBody ResponseEntity<List<ORMGetCityState>> getCityState(
			@PathVariable(value = "zip_code") String zip_code) {

		List<ORMGetCityState> obj = generalService.getCityState(zip_code);

		return new ResponseEntity<List<ORMGetCityState>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/searchReferringProvider")
	public @ResponseBody ResponseEntity<List<ORMGetReferringProvider>> searchReferringProvider(
			@RequestBody SearchCriteria searchCriteria) {

		List<ORMGetReferringProvider> obj = generalService.searchReferringProvider(searchCriteria);

		return new ResponseEntity<List<ORMGetReferringProvider>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getDocCategories/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMDocCategories>> getDocCategories(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMDocCategories> lst = generalService.getDocCategories(practice_id);
		return new ResponseEntity<List<ORMDocCategories>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getDocCategoriesList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMDocCategoriesList>> getDocCategoriesList(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMDocCategoriesList> lst = generalService.getDocCategoriesList(practice_id);
		return new ResponseEntity<List<ORMDocCategoriesList>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getDocumentPaths/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMDocumentPath>> getDocumentPaths(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMDocumentPath> lst = generalService.getDocumentPaths(practice_id);
		return new ResponseEntity<List<ORMDocumentPath>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getDocumentPathByCategory/{practice_id}/{cateogry}")
	public @ResponseBody ResponseEntity<ORMDocumentPath> getDocumentPathByCategory(
			@PathVariable(value = "practice_id") Long practice_id, @PathVariable(value = "cateogry") String cateogry) {
		ORMDocumentPath objPath = generalService.getDocumentPathByCategory(practice_id, cateogry);
		return new ResponseEntity<ORMDocumentPath>(objPath, HttpStatus.OK);
	}

	@RequestMapping("/getUserRights/{user_id}")
	public @ResponseBody ResponseEntity<List<ORMGetUserRights>> getUserRights(
			@PathVariable(value = "user_id") String user_id) {
		List<ORMGetUserRights> lst = generalService.getUserRights(user_id);
		return new ResponseEntity<List<ORMGetUserRights>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/downloadFile/{file_name}")
	public void downloadFile(@PathVariable(value = "file_name") String file_name, HttpServletResponse response) {
		try {
			// get your file as InputStream
			File initialFile = new File(
					"D:\\ihcDocs\\Documents\\500\\PatientDocuments\\2018\\01\\22\\20180122111528315.pdf");
			InputStream targetStream = new FileInputStream(initialFile);
			// copy it to response's OutputStream
			org.apache.commons.io.IOUtils.copy(targetStream, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException ex) {
			// log.info("Error writing file to output stream. Filename was '{}'", fileName,
			// ex);
			throw new RuntimeException("IOError writing file to output stream");
		}

		// System.out.println(generalService.downloadFile("D:\\ihcDocs\\Documents\\500\\PatientDocuments\\2018\\01\\25\\"+file_name+".pdf").toString());
		// return
		// generalService.downloadFile("D:\\ihcDocs\\Documents\\500\\PatientDocuments\\2018\\01\\25\\"+file_name+".pdf");
	}

	@RequestMapping("/getImage")
	public @ResponseBody byte[] getImage() throws IOException {
		InputStream in = getClass().getResourceAsStream(
				"D:\\ihcDocs\\Documents\\500\\PatientDocuments\\2018\\01\\22\\20180122111528315.pdf");
		return org.apache.commons.io.IOUtils.toByteArray(in);
	}

	private static final String FILE_PATH = "D:\\ihcDocs\\Documents\\500\\PatientDocuments\\2018\\01\\22\\20180122114724435.jpg";
	private static final String APPLICATION_PDF = "application/pdf";

	@RequestMapping(value = "/downloadC", method = RequestMethod.GET, produces = APPLICATION_PDF)
	public @ResponseBody org.springframework.core.io.Resource downloadC(HttpServletResponse response)
			throws FileNotFoundException {
		File file = getFile();
		response.setContentType(APPLICATION_PDF);
		response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
		response.setHeader("Content-Length", String.valueOf(file.length()));
		return new FileSystemResource(file);
	}

	@RequestMapping(value = "/downloadB", method = RequestMethod.POST)
	public @ResponseBody byte[] downloadB(@RequestBody SearchCriteria searchCriteria) throws IOException {
		String file_name = searchCriteria.getCriteria();
		System.out.println("File Name:" + file_name);
		File file = new File(file_name);

		if (file.exists()) {
			System.out.println("File Exists:" + file_name);
			byte[] document = org.springframework.util.FileCopyUtils.copyToByteArray(file);

			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "pdf"));
			header.set("Content-Disposition", "inline; filename=" + file.getName());
			header.setContentLength(document.length);
			return document;
		} else
			return null;
	}

	private File getFile() throws FileNotFoundException {
		File file = new File(FILE_PATH);
		if (!file.exists()) {
			throw new FileNotFoundException("file with path: " + FILE_PATH + " was not found.");
		}
		return file;
	}

	@RequestMapping(value = "/SaveDocument", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse<ORMKeyValue>> SaveDocument(
			@RequestParam(required = false, value = "docFile") MultipartFile docFile,
			@RequestParam("docData") String docData, @RequestParam("docCategory") String docCategory)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		ORMSaveDocument patDoc = mapper.readValue(docData, ORMSaveDocument.class);

		// String doc_ca=(mapper.readValue(docCategory,String.class));
		// System.out.println("Document Category:"+doc_ca);
		System.out.println("Document Category:" + docCategory);
		docCategory = "PatientDocuments";

		Long result = generalService.SaveDocument(patDoc, docFile, docCategory);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (result > 0) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			// resp.setError_message("");
			resp.setResult(result.toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			// resp.setError_message("An Error Occured while uploading document.");
		}

		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	// @RequestMapping(value = "/GetDocument", method = RequestMethod.GET, produces
	// = "application/pdf")
	// public ResponseEntity<InputStreamResource> downloadPDFFile()
	// throws IOException {
	//
	//// ClassPathResource pdfFile = new
	// ClassPathResource("D:\\ihcDocs\\Documents\\500\\PatientDocuments\\2018\\01\\22\\20180122111528315.pdf");
	////
	//// return ResponseEntity
	//// .ok()
	//// .contentLength(pdfFile.contentLength())
	//// .contentType(
	//// MediaType.parseMediaType("application/octet-stream"))
	//// .body(new InputStreamResource(pdfFile.getInputStream()));
	//
	//
	//// File file = new
	// File("D:\\\\ihcDocs\\\\Documents\\\\500\\\\PatientDocuments\\\\2018\\\\01\\\\22\\\\20180122111528315.pdf");
	////
	//// Response response = Response.ok((Object) file);
	//// response.header("Content-Disposition",
	//// "attachment; filename=new-android-book.pdf");
	//// return response.build();
	////
	//// List<HttpMessageConverter<?>> messageConverters = new
	// ArrayList<HttpMessageConverter<?>>();
	//// messageConverters.add(new ByteArrayHttpMessageConverter());
	////
	//// RestTemplate restTemplate = new RestTemplate(messageConverters);
	////
	////
	//// File file = new File(path);
	////
	//// ResponseBuilder rb = Response.ok((Object) file);
	//// rb.header("Content-Disposition","attachment;
	// filename=java4sFileFromServer.txt");
	//// return rb.build();
	//
	// }
	@RequestMapping(value = "/GetDocument")
	public @ResponseBody InputStream run() throws IOException {

		return generalService.GetDocument();

		// String uri = "http://localhost:8080";
		// String filename="20180122111528315.pdf";
		// RestTemplate template = new RestTemplate();
		// MultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<String,
		// Object>();
		// multipartMap.add("filename", filename);
		// //logger.info("Created multipart request: " + multipartMap);
		// System.out.println("Created multipart request:"+multipartMap);
		// HttpHeaders headers = new HttpHeaders();
		// headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		//
		// template.getMessageConverters()
		// .add(new ByteArrayHttpMessageConverter());
		// HttpEntity<Object> request = new HttpEntity<Object>(multipartMap,
		// headers);
		// System.out.println("Posting request to:"+uri);
		// //logger.info(???Posting request to: ??? + uri);
		//
		// ResponseEntity<byte[]> httpResponse = template.exchange(uri,HttpMethod.POST,
		// request, byte[].class);
		//
		// if (!httpResponse.getStatusCode().equals(HttpStatus.OK)) {
		// //logger.error("Problems with the request. Http status: " +
		// httpResponse.getStatusCode());
		// System.out.println("Problems with the request. Http
		// status:"+httpResponse.getStatusCode());
		// } else {
		// //System.out.println("Got successfull message "+
		// httpResponse.getHeaders().CONTENT_DISPOSITION);
		// System.out.println("Got successfull message
		// :"+httpResponse.getHeaders().CONTENT_DISPOSITION);
		//
		// FileOutputStream fileOutputStream = new
		// FileOutputStream("D:\\ihcDocs\\Documents\\500\\PatientDocuments\\2018\\01\\22\\20180122111528315.pdf");
		//
		// org.apache.commons.io.IOUtils.write(httpResponse.getBody(),
		// fileOutputStream);
		// }
		// return httpResponse.toString();
	}
	// @GET
	// @Path("/get")
	// @Produces("application/pdf")
	// public Response getFile() {
	//
	// File file = new File(FILE_PATH);
	//
	// ResponseBuilder response = Response((Object) file);
	// response.header("Content-Disposition",
	// "attachment; filename=new-android-book.pdf");
	// return response.build();
	//
	// }

	@RequestMapping("/getdistinctlocaitonproviders/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMProviderList>> getDistinctLocationProviders(
			@PathVariable(value = "practice_id") Long practice_id) {

		List<ORMProviderList> obj = generalService.getDistinctLocationProviders(practice_id);

		return new ResponseEntity<List<ORMProviderList>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getProviderUser/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMGetProviderUsers>> getProviderUser(
			@PathVariable(value = "practice_id") Long practice_id) {

		List<ORMGetProviderUsers> obj = generalService.getProviderUser(practice_id);

		return new ResponseEntity<List<ORMGetProviderUsers>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/AuthenticateProviderUser")
	public @ResponseBody ResponseEntity<List<ORMIdName>> AuthenticateProviderUser(
			@RequestBody SearchCriteria searchCriteria) {
		List<ORMIdName> lst = generalService.AuthenticateProviderUser(searchCriteria);
		return new ResponseEntity<List<ORMIdName>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getauthorizationusers/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdName>> getAuthorizationUsers(
			@PathVariable(value = "practice_id") Long practice_id) {

		List<ORMIdName> obj = generalService.getAuthorizationUsers(practice_id);

		return new ResponseEntity<List<ORMIdName>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getpatientinsurancename")
	public @ResponseBody ResponseEntity<ORMGetPatientInsuranceName> getPatientInsuranceName(
			@RequestBody SearchCriteria searchCriteria) {
		GeneralOperations.logMsg("Inside getPatientInsuranceName: ");

		ORMGetPatientInsuranceName ormInfo = generalService.getPatientInsuranceName(searchCriteria);

		return new ResponseEntity<ORMGetPatientInsuranceName>(ormInfo, HttpStatus.OK);
	}

	@RequestMapping("/getMaritalStatusList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescriptionDisplay>> getMaritalStatusList() {
		GeneralOperations.logMsg("Inside getMaritalStatusList");

		List<ORMIdCodeDescriptionDisplay> lst = generalService.getMaritalStatusList();

		return new ResponseEntity<List<ORMIdCodeDescriptionDisplay>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getLanguageList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescriptionDisplay>> getLanguageList(
			@PathVariable(value = "practice_id") Long practice_id) {
		GeneralOperations.logMsg("Inside getLanguageList");

		List<ORMIdCodeDescriptionDisplay> lst = generalService.getLanguageList(practice_id);

		return new ResponseEntity<List<ORMIdCodeDescriptionDisplay>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getGenderIdentityList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getGenderIdentityList() {
		GeneralOperations.logMsg("Inside getLanguageList");

		List<ORMIdCodeDescription> lst = generalService.getGenderIdentityList();

		return new ResponseEntity<List<ORMIdCodeDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getSexualOrientationList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getSexualOrientationList() {
		GeneralOperations.logMsg("Inside getLanguageList");

		List<ORMIdCodeDescription> lst = generalService.getSexualOrientationList();

		return new ResponseEntity<List<ORMIdCodeDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getOMBRaceList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getOMBRaceList() {
		GeneralOperations.logMsg("Inside getOMBRaceList");
		List<ORMIdCodeDescription> lst = generalService.getOMBRaceList();
		return new ResponseEntity<List<ORMIdCodeDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getOMBEthnicityList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getOMBEthnicityList() {
		GeneralOperations.logMsg("Inside getOMBEthnicityList");
		List<ORMIdCodeDescription> lst = generalService.getOMBEthnicityList();
		return new ResponseEntity<List<ORMIdCodeDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getCountryParishCodeByState/{state}")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getCountryParishCodeByState(
			@PathVariable(value = "state") String state) {

		List<ORMIdCodeDescription> obj = generalService.getCountryParishCodeByState(state);

		return new ResponseEntity<List<ORMIdCodeDescription>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getCityStateByZip/{zip}")
	public @ResponseBody ResponseEntity<List<ORMZipCityState>> getCityStateByZip(
			@PathVariable(value = "zip") String zip) {

		List<ORMZipCityState> obj = generalService.getCityStateByZip(zip);

		return new ResponseEntity<List<ORMZipCityState>>(obj, HttpStatus.OK);

	}

	/*
	 * @RequestMapping("/getProvider_Eligibility/{practice_id}")
	 * public @ResponseBody ResponseEntity<List<ORMProvider_Eligibility>>
	 * getProvider_Eligibility(
	 * 
	 * @PathVariable(value = "practice_id") Long practice_id) {
	 * List<ORMProvider_Eligibility> obj =
	 * generalService.getProvider_Eligibility(practice_id); return new
	 * ResponseEntity<List<ORMProvider_Eligibility>>(obj, HttpStatus.OK); }
	 */
	@RequestMapping("/getRelationshipList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getRelationshipList() {

		List<ORMIdCodeDescription> obj = generalService.getRelationshipList();

		return new ResponseEntity<List<ORMIdCodeDescription>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getImmRegistryStatusList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getImmRegistryStatusList() {

		List<ORMIdCodeDescription> obj = generalService.getImmRegistryStatusList();

		return new ResponseEntity<List<ORMIdCodeDescription>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getImmRegistryPublicityCodeList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getImmRegistryPublicityCodeList() {

		List<ORMIdCodeDescription> obj = generalService.getImmRegistryPublicityCodeList();

		return new ResponseEntity<List<ORMIdCodeDescription>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getImmRegistryProcectionIndicatorList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getImmRegistryProcectionIndicatorList() {

		List<ORMIdCodeDescription> obj = generalService.getImmRegistryProcectionIndicatorList();

		return new ResponseEntity<List<ORMIdCodeDescription>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getProviderInfoById/{provider_id}")
	public @ResponseBody ResponseEntity<List<ORMPhysicianSearch>> getProviderInfoById(
			@PathVariable(value = "provider_id") Long providerId) {
		GeneralOperations.logMsg("Inside searchProvider");

		List<ORMPhysicianSearch> lst = generalService.getProviderInfoById(providerId);

		return new ResponseEntity<List<ORMPhysicianSearch>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getSnomedRelationshipList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getSnomedRelationshipList() {
		GeneralOperations.logMsg("Inside getSnomedRelationshipList");
		List<ORMIdCodeDescription> lst = generalService.getSnomedRelationshipList();
		return new ResponseEntity<List<ORMIdCodeDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping(value = "/uploadScan", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<?> uploadScan(@RequestParam(value = "RemoteFile") MultipartFile multipartFile,
			@RequestParam(value = "docData") String docData)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		ScanDocumentData scanDocument = mapper.readValue(docData.toString(), ScanDocumentData.class);

		GeneralOperations.logMsg("Inside upload ");

		ServiceResponse<ORMKeyValue> serviceResponse = generalService.uploadScan(multipartFile, scanDocument);

		if (serviceResponse.getStatus() == ServiceResponseStatus.SUCCESS) {
			// Must reutrn nothing in case of success becouse if the server returns anything
			// other than 0 bytes, Dynamsoft will force the post function to return false.
			// Ref: http://forums.dynamictwain.com/httpresponse-posting-to-server-t5593.html
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<ServiceResponse<ORMKeyValue>>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/getBillingProviderList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMProviderList>> getBillingProviderList(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMProviderList> obj = generalService.getBillingProviderList(practice_id);
		return new ResponseEntity<List<ORMProviderList>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getStatesList")
	public @ResponseBody ResponseEntity<List<ORMOneColumnGeneric>> getStatesList() {
		List<ORMOneColumnGeneric> obj = generalService.getStatesList();
		return new ResponseEntity<List<ORMOneColumnGeneric>>(obj, HttpStatus.OK);
	}

	@RequestMapping("getBillingUsersList/{billing_practice_id}/{practiceID}")
	public @ResponseBody ResponseEntity<List<ORMGetBillingUsersList>> getBillingUsersList(
			@PathVariable(value = "billing_practice_id") String billing_practice_id,
			@PathVariable(value = "practiceID") String practiceID) {

		List<ORMGetBillingUsersList> lst = generalService.getBillingUsersList(billing_practice_id, practiceID);
		return new ResponseEntity<List<ORMGetBillingUsersList>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getUserAllRoles/{PracticeId}")
	public @ResponseBody ResponseEntity<List<ORMUserRoleList>> getUserAllRoles(
			@PathVariable(value = "PracticeId") Long PracticeId) {
		List<ORMUserRoleList> obj = generalService.getUserAllRoles(PracticeId);
		return new ResponseEntity<List<ORMUserRoleList>>(obj, HttpStatus.OK);
	}

	@RequestMapping("/getPracticeUsersList/{practice_id}")
	public @ResponseBody ResponseEntity<List<ORMPracticeUsersList>> getPracticeUsersList(
			@PathVariable(value = "practice_id") Long practice_id) {
		List<ORMPracticeUsersList> lst = generalService.getPracticeUsersList(practice_id);
		return new ResponseEntity<List<ORMPracticeUsersList>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getSmokingStatusList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getSmokingStatusList() {
		List<ORMIdCodeDescription> lst = generalService.getSmokingStatusList();
		return new ResponseEntity<List<ORMIdCodeDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getDischargeDispositionList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getDischargeDispositionList() {
		List<ORMIdCodeDescription> lst = generalService.getDischargeDispositionList();
		return new ResponseEntity<List<ORMIdCodeDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getPatientHeader/{patient_id}")
	public @ResponseBody ResponseEntity<ORMGetPatientHeaderInfo> getPatientHeader(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientHeaderInfo for PHR:  patientId=" + patientId);
		ORMGetPatientHeaderInfo patient = generalService.getPatientHeader(patientId);
		return new ResponseEntity<ORMGetPatientHeaderInfo>(patient, HttpStatus.OK);
	}

	@RequestMapping("/getPatientVitals/{patient_id}")
	public @ResponseBody ResponseEntity<ORMHeaderVitals> getPatientVitals(
			@PathVariable(value = "patient_id") Long patientId) {
		GeneralOperations.logMsg("Inside getPatientHeaderVitals for PHR:  patientId=" + patientId);
		ORMHeaderVitals patVital = generalService.getPatientVitals(patientId);
		return new ResponseEntity<ORMHeaderVitals>(patVital, HttpStatus.OK);
	}

	@RequestMapping("/getPatientVitalsPHR/{patient_id}")
	public @ResponseBody ResponseEntity<ORMHeaderVitals_PHR> getPatientVitalsPHR(
			@PathVariable(value = "patient_id") Long patientId) {
		ORMHeaderVitals_PHR patVital = generalService.getPatientVitalsPHR(patientId);
		return new ResponseEntity<ORMHeaderVitals_PHR>(patVital, HttpStatus.OK);
	}

	@RequestMapping("/getPatientAllergies/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientAllergiesSummary>> getPatientAllergies(
			@PathVariable(value = "patient_id") Long patientId) {
		List<ORMGetPatientAllergiesSummary> lstAllergies = generalService.getPatientAllergies(patientId);
		return new ResponseEntity<List<ORMGetPatientAllergiesSummary>>(lstAllergies, HttpStatus.OK);
	}

	@RequestMapping("/getPatientMedicationSummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientMedicationSummary>> getPatientMedicationSummary(
			@PathVariable(value = "patient_id") Long patientId) {
		List<ORMGetPatientMedicationSummary> lstMed = generalService.getPatientMedicationSummary(patientId);
		return new ResponseEntity<List<ORMGetPatientMedicationSummary>>(lstMed, HttpStatus.OK);
	}

	@RequestMapping("/getPatientProblems/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetPatientProblemsSummary>> getPatientProblems(
			@PathVariable(value = "patient_id") Long patientId) {
		List<ORMGetPatientProblemsSummary> lstProblems = generalService.getPatientProblems(patientId);
		return new ResponseEntity<List<ORMGetPatientProblemsSummary>>(lstProblems, HttpStatus.OK);
	}

	@RequestMapping("/getPHREncounterSummary/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMGetChartSummary>> getPHREncounterSummary(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMGetChartSummary> obj = generalService.getPHREncounterSummary(patient_id);
		return new ResponseEntity<List<ORMGetChartSummary>>(obj, HttpStatus.OK);

	}

	@RequestMapping("getReportHeader/{chart_id}")
	public @ResponseBody ResponseEntity<List<ORMChartReportDetailsforPHR>> getReportHeader(
			@PathVariable(value = "chart_id") String chart_id) {
		List<ORMChartReportDetailsforPHR> lst = generalService.getReportHeader(chart_id);
		return new ResponseEntity<List<ORMChartReportDetailsforPHR>>(lst, HttpStatus.OK);
	}

	@RequestMapping("/getInsuranceDetails/{patient_id}")
	public @ResponseBody ResponseEntity<List<ORMFourColumGeneric>> getInsuranceDetails(
			@PathVariable(value = "patient_id") String patient_id) {
		List<ORMFourColumGeneric> obj = generalService.getInsuranceDetails(patient_id);
		return new ResponseEntity<List<ORMFourColumGeneric>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/loginUserLog")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> loginUserLog(
			@RequestBody ORMLoginUserLog ormSave) {

		ServiceResponse<ORMKeyValue> resp = generalService.loginUserLog(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/logoutUserLog")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> logoutUserLog(
			@RequestBody SearchCriteria searchCriteria) {

		ServiceResponse<ORMKeyValue> resp = generalService.logoutUserLog(searchCriteria);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);

	}

	@RequestMapping("/auditLog")
	public @ResponseBody ResponseEntity<ServiceResponse<ORMKeyValue>> auditLog(@RequestBody ORMAuditLog ormSave) {

		ServiceResponse<ORMKeyValue> resp = generalService.auditLog(ormSave);
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/ConvertHtmltoPDF")
	public ResponseEntity<ServiceResponse<ORMKeyValue>> ConvertHtmltoPDF(@RequestBody SearchCriteria searchCriteria) {
		String lstSearchPatient = generalService.ConvertHtmltoPDF(searchCriteria);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (lstSearchPatient != "") {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(lstSearchPatient.toString());
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
		}
		return new ResponseEntity<ServiceResponse<ORMKeyValue>>(resp, HttpStatus.OK);
	}

	@RequestMapping("/getBillingPractices/{user_id}")
	public @ResponseBody ResponseEntity<List<ORMTwoColumnGeneric>> getBillingPractices(
			@PathVariable(value = "user_id") String user_id) {
		List<ORMTwoColumnGeneric> obj = generalService.getBillingPractices(user_id);
		return new ResponseEntity<List<ORMTwoColumnGeneric>>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getLabelPrintData/{label_type}/{id}")
	public @ResponseBody ResponseEntity<ORMLabelPrintDataGet> getLabelPrintData(
			@PathVariable(value = "label_type") String label_type, @PathVariable(value = "id") String id) {
		ORMLabelPrintDataGet obj = generalService.getLabelPrintData(label_type, id);
		return new ResponseEntity<ORMLabelPrintDataGet>(obj, HttpStatus.OK);

	}

	@RequestMapping("/getPOSList")
	public @ResponseBody ResponseEntity<List<ORMIdCodeDescription>> getPOSList() {
		List<ORMIdCodeDescription> lst = generalService.getPOSList();
		return new ResponseEntity<List<ORMIdCodeDescription>>(lst, HttpStatus.OK);
	}

	@RequestMapping(value = "/getDocumentBytes", method = RequestMethod.POST)
	public @ResponseBody byte[] getDocumentBytes(@RequestBody SearchCriteria searchCriteria) throws IOException {

		return generalService.getDocumentBytes(searchCriteria);
		// return documentBytes; //new ResponseEntity<DocumentBytes>(documentBytes,
		// HttpStatus.OK);
	}

	@RequestMapping("/getUnreadMessageCount/{receiver_id}")
	public @ResponseBody int getUnreadMessageCount(
			@PathVariable(value = "receiver_id") String receiverId) {
		
		return generalService.getUnreadMessageCount(receiverId); 

	}
	
	@RequestMapping("/getHPPublicKey")
	public @ResponseBody ResponseEntity<String> getHPPublicKey(@RequestBody SearchCriteria searchCriteria) {
		String publicKey = generalService.getHPPublicKey(searchCriteria);	
		
		String str="{\"public_key\":\""+publicKey+"\"}";
		
		return new ResponseEntity<>(str, HttpStatus.OK);

	}

}