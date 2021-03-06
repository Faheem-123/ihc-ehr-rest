package com.ihc.ehr.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.AssignFaxToUserParmList;
import com.ihc.ehr.model.FaxAttachmentFileGenerationResponse;
import com.ihc.ehr.model.FaxResendModel;
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMFaxAttachmentSave;
import com.ihc.ehr.model.ORMFaxConfigGet;
import com.ihc.ehr.model.ORMFaxContactDetailGetSave;
import com.ihc.ehr.model.ORMFaxContactListGet;
import com.ihc.ehr.model.ORMFaxReceivedGet;
import com.ihc.ehr.model.ORMFaxReceivedSave;
import com.ihc.ehr.model.ORMFaxReceivedUserAssignedGet;
import com.ihc.ehr.model.ORMFaxSentAttachment;
import com.ihc.ehr.model.ORMFaxSentAttemptsGet;
import com.ihc.ehr.model.ORMFaxSentGet;
import com.ihc.ehr.model.ORMFaxSentSave;
import com.ihc.ehr.model.ORMFaxUsersSave;
import com.ihc.ehr.model.ORMGetAssignedToUsersList;
import com.ihc.ehr.model.ORMGetFaxDetailsForStatusUpdate;
import com.ihc.ehr.model.ORMIdDescription;
import com.ihc.ehr.model.ORMIdName;
import com.ihc.ehr.model.ORMKeyValue;
import com.ihc.ehr.model.ORMSaveDocument;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.SearchCriteriaParamList;
import com.ihc.ehr.model.SendFaxAttachments;
import com.ihc.ehr.model.SendFaxAttachmentsFromClient;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.UploadFile;
import com.ihc.ehr.model.WrapperListWithOneObjectSave;
import com.ihc.ehr.model.WrapperSendFax;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.model.Wrapper_ObjectSave;
import com.ihc.ehr.util.DateTimeUtil;
import com.ihc.ehr.util.EnumUtil.EntityType;
import com.ihc.ehr.util.EnumUtil.FaxServerEnum;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;
import com.ihc.ehr.util.FaxAge;
import com.ihc.ehr.util.FaxReceivedObject;
import com.ihc.ehr.util.FileUtil;
import com.ihc.ehr.util.GeneralOperations;
import com.ihc.ehr.util.MFax;
import com.ihc.ehr.util.PDFOperations;

@Repository
public class FaxDOAImpl implements FaxDOA {

	@Autowired
	DBOperations db;

	@Autowired
	private GeneralDAOImpl generalDAOImpl;

	@Override
	public List<ORMFaxContactListGet> getFaxContactList(Long practiceId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetFaxContactList", ORMFaxContactListGet.class, lstParam);
	}

	@Override
	public ORMFaxContactDetailGetSave getFaxContactDetailById(Long contactId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("contact_id", contactId.toString(), String.class, ParameterMode.IN));
		List<ORMFaxContactDetailGetSave> lst = db.getStoreProcedureData("spGetFaxContactDetailById",
				ORMFaxContactDetailGetSave.class, lstParam);

		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ServiceResponse<ORMKeyValue> saveFaxContact(ORMFaxContactDetailGetSave ormFaxContactDetailGetSave) {
		// TODO Auto-generated method stub
		int result = 0;
		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		ormFaxContactDetailGetSave.setDate_modified(DateTimeUtil.getCurrentDateTime());
		if (GeneralOperations.isNullorEmpty(ormFaxContactDetailGetSave.getContact_id())) {

			ormFaxContactDetailGetSave
					.setContact_id(db.IDGenerator("fax_contacts", ormFaxContactDetailGetSave.getPractice_id()));
			ormFaxContactDetailGetSave.setDate_created(DateTimeUtil.getCurrentDateTime());

			result = db.SaveEntity(ormFaxContactDetailGetSave, Operation.ADD);
		} else {
			result = db.SaveEntity(ormFaxContactDetailGetSave, Operation.EDIT);
		}

		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}

		return resp;
	}

	@Override
	public List<ORMIdDescription> getFaxConfigFaxNumbersList(Long practiceId, String faxServer) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("fax_server", faxServer.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetFaxConfigFaxNumbersList", ORMIdDescription.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> sendFax(WrapperSendFax wrapperSendFax, MultipartFile[] attachmentFiles) {
		// TODO Auto-generated method stub

		List<ORMFaxConfigGet> lstConfig = getFaxConfig(wrapperSendFax.getPractice_id(), wrapperSendFax.getFax_server(),
				wrapperSendFax.getSenderFaxNo());

		ORMDocumentPath objPath = generalDAOImpl.getDocumentPathByCategory(wrapperSendFax.getPractice_id(),
				"PatientDocuments");

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (lstConfig != null && lstConfig.size() > 0 && objPath != null) {

			String patDocumentDirectory = objPath.getUpload_path();
			String fax_response = "";

			if (wrapperSendFax.getFax_server().equalsIgnoreCase(FaxServerEnum.FAXAGE.toString())) {
				fax_response = SendFaxAge(wrapperSendFax, attachmentFiles, patDocumentDirectory, lstConfig.get(0));
			} else if (wrapperSendFax.getFax_server().equalsIgnoreCase(FaxServerEnum.MFAX.toString())) {
				// DOCUMO
				fax_response = SendMFax(wrapperSendFax, attachmentFiles, patDocumentDirectory, lstConfig.get(0));
			}

			if (fax_response.split(":")[0].equals("OK")) {
				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse(fax_response);

			} else {
				System.out.println("Fax Send Error Response : " + fax_response);
				// return "Fax Send Failed.";
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse(fax_response);
			}
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("Fax Server is not configured.");
		}

		return resp;
	}

	public String SendFaxAge(WrapperSendFax wrapperSendFax, MultipartFile[] lstMultipartFile,
			String patDocumentDirectory, ORMFaxConfigGet faxConfig) {
		String fax_response = "";

		// Long newId = (long) 0;
		// Boolean isError = false;

		try {

			FaxAttachmentFileGenerationResponse faxAttachmentFileGenerationResponse = prepareFaxAttachements(
					wrapperSendFax, lstMultipartFile, patDocumentDirectory);

			/*
			 * List<SendFaxAttachments> lstAttachments = new ArrayList<>(); PDFOperations
			 * objPDF = new PDFOperations();
			 * 
			 * if (wrapperSendFax.getLstAttachments() != null &&
			 * wrapperSendFax.getLstAttachments().size() > 0) {
			 * 
			 * newId = db.IDGenerator("temp_fax_file_id", wrapperSendFax.getPractice_id(),
			 * wrapperSendFax.getLstAttachments().size());
			 * 
			 * for (SendFaxAttachmentsFromClient objAttach :
			 * wrapperSendFax.getLstAttachments()) {
			 * 
			 * String docPath = ""; String docLink = "";
			 * 
			 * if (objAttach.getDocument_source().equalsIgnoreCase("multi_part")) {
			 * 
			 * if (lstMultipartFile != null && lstMultipartFile.length > 0) {
			 * 
			 * MultipartFile file = lstMultipartFile[objAttach.getMultipart_index()];
			 * 
			 * // if (objAttach.getDocument_source().equalsIgnoreCase("bytefile")) { if
			 * (GeneralOperations.isNotNullorEmpty(file)) {
			 * 
			 * String FileName = wrapperSendFax.getPractice_id() + newId.toString() +
			 * objAttach.getDocument_name().substring(
			 * objAttach.getDocument_name().lastIndexOf("."),
			 * objAttach.getDocument_name().length());
			 * 
			 * UploadFile uploadedFile = FileUtil.UploadDocumentYearMonthDayWise(file,
			 * patDocumentDirectory, wrapperSendFax.getPractice_id().toString(),
			 * "PatientDocuments\\temp", FileName);
			 * 
			 * // objAttach.setDocument_link(uploadedFile.getLink()); docLink =
			 * uploadedFile.getLink(); // objAttach.setDocument_path(patDocumentDirectory +
			 * // wrapperSendFax.getPractice_id().toString() // +
			 * "\\PatientDocuments\\temp\\" + uploadedFile.getLink()); docPath =
			 * patDocumentDirectory + wrapperSendFax.getPractice_id().toString() +
			 * "\\PatientDocuments\\temp\\" + uploadedFile.getLink();
			 * 
			 * System.out.println("PDF File Created:" + docPath);
			 * 
			 * SendFaxAttachments sendFaxAttachments = new SendFaxAttachments();
			 * sendFaxAttachments.setDocument_name(objAttach.getDocument_name());
			 * sendFaxAttachments.setDocument_link(docLink);
			 * sendFaxAttachments.setDocument_path(docPath);
			 * sendFaxAttachments.setDocument_source("temp_file");
			 * 
			 * lstAttachments.add(sendFaxAttachments);
			 * 
			 * } // } // System.out.println("File Directory : " + patDocumentDirectory); //
			 * System.out.println("File Source : " + //
			 * sendFaxAttachments.getDocument_source()); //
			 * System.out.println("Temp File Path : " + //
			 * sendFaxAttachments.getDocument_path()); //
			 * System.out.println("Temp Document link : " + //
			 * sendFaxAttachments.getDocument_link()); } // SendFaxAttachments
			 * sendFaxAttachments=new SendFaxAttachments();
			 * 
			 * } else if (objAttach.getDocument_source().equalsIgnoreCase("html_string")) {
			 * 
			 * String FileName = wrapperSendFax.getPractice_id() + newId.toString() +
			 * ".pdf";
			 * 
			 * // docPath = //
			 * GeneralOperations.checkPathYearMonthWise(wrapperSendFax.getPractice_id().
			 * toString(), // patDocumentDirectory, "PatientDocuments\\temp");
			 * 
			 * docPath = FileUtil.GetYearMonthDayWisePath(patDocumentDirectory,
			 * wrapperSendFax.getPractice_id().toString(), "PatientDocuments\\temp");
			 * 
			 * //FileUtil.UploadStringDataToFile(objAttach.getHtml_string(), docPath +
			 * "123.html");
			 * 
			 * if (objPDF.create_pdf_fromHTMLStream(objAttach.getHtml_string(), docPath,
			 * FileName, "")) {
			 * 
			 * System.out.println("PDF File Created:-" + docPath); String[] splitedPath =
			 * docPath.split("\\\\"); docLink = splitedPath[splitedPath.length - 3] + "\\" +
			 * splitedPath[splitedPath.length - 2] +
			 * "\\" + splitedPath[splitedPath.length - 1] + "\\" + FileName;
			 * 
			 * System.out.println("Retun Path:-" + docLink);
			 * 
			 * String fullPDFPath = docPath + "\\" + FileName; SendFaxAttachments
			 * sendFaxAttachments = new SendFaxAttachments();
			 * sendFaxAttachments.setDocument_name(objAttach.getDocument_name());
			 * sendFaxAttachments.setDocument_link(docLink);
			 * sendFaxAttachments.setDocument_path(fullPDFPath);
			 * sendFaxAttachments.setDocument_source("temp_file");
			 * 
			 * lstAttachments.add(sendFaxAttachments); } else {
			 * System.out.println("PDF Generation from HTML String failed."); isError =
			 * true; }
			 * 
			 * } else if (objAttach.getDocument_source().equalsIgnoreCase("temp_file")) {
			 * 
			 * docPath = patDocumentDirectory + wrapperSendFax.getPractice_id().toString() +
			 * "\\PatientDocuments\\temp\\" + objAttach.getDocument_link();
			 * 
			 * // objAttach.setDocument_path(patDocumentDirectory + //
			 * wrapperSendFax.getPractice_id().toString() // + "\\PatientDocuments\\temp\\"
			 * + objAttach.getDocument_link());
			 * 
			 * // objAttach.setDocument_source("temp_file");
			 * 
			 * SendFaxAttachments sendFaxAttachments = new SendFaxAttachments();
			 * sendFaxAttachments.setDocument_name(objAttach.getDocument_name());
			 * sendFaxAttachments.setDocument_link(objAttach.getDocument_link());
			 * sendFaxAttachments.setDocument_path(docPath);
			 * sendFaxAttachments.setDocument_source("temp_file");
			 * 
			 * lstAttachments.add(sendFaxAttachments);
			 * 
			 * } else if
			 * (objAttach.getDocument_source().equalsIgnoreCase("referenced_document")) {
			 * 
			 * docPath = patDocumentDirectory + wrapperSendFax.getPractice_id().toString() +
			 * "\\PatientDocuments\\" + objAttach.getDocument_link();
			 * 
			 * // objAttach.setDocument_path(patDocumentDirectory + //
			 * wrapperSendFax.getPractice_id().toString() // + "\\PatientDocuments\\" +
			 * objAttach.getDocument_link());
			 * 
			 * //
			 * sendFaxAttachments.setPatient_document_id(objAttach.getPatient_document_id())
			 * ;
			 * 
			 * // objAttach.setDocument_source("referenced_document");
			 * 
			 * SendFaxAttachments sendFaxAttachments = new SendFaxAttachments();
			 * sendFaxAttachments.setPatient_document_id(objAttach.getPatient_document_id())
			 * ; sendFaxAttachments.setDocument_name(objAttach.getDocument_name());
			 * sendFaxAttachments.setDocument_link(objAttach.getDocument_link());
			 * sendFaxAttachments.setDocument_path(docPath);
			 * sendFaxAttachments.setDocument_source("referenced_document");
			 * 
			 * lstAttachments.add(sendFaxAttachments); } newId++; } }
			 */

			if (GeneralOperations.isNullorEmpty(faxAttachmentFileGenerationResponse.getError_message())) {
				// Fax Cover
				String generatedCoverPagePath = "";
				if (GeneralOperations.isNotNullorEmpty(faxConfig.getFax_cover_path())) {

					String faCoverPath = patDocumentDirectory + "/" + wrapperSendFax.getPractice_id()
							+ "/FaxCoverPageTemplate/" + faxConfig.getFax_cover_path();

					generatedCoverPagePath = getFaxCoverPage(faCoverPath, wrapperSendFax.getRecipientName(),
							wrapperSendFax.getRecipientFaxNo(), wrapperSendFax.getSenderName(),
							wrapperSendFax.getSenderFaxNo(), wrapperSendFax.getSenderPhoneNo(),
							wrapperSendFax.getFaxSubject(), wrapperSendFax.getFaxNotes(),
							wrapperSendFax.getPractice_id().toString(), patDocumentDirectory);

					System.out.println("Cover Page Generated: " + generatedCoverPagePath);
				}

				FaxAge objFax = new FaxAge(faxConfig.getUser_name(), faxConfig.getPassword(),
						faxConfig.getCompany_id());

				// fax_response = "Fax sending is committed for the time being.... ihsan";

				fax_response = objFax.SendFax(wrapperSendFax.getPractice_id(), wrapperSendFax.getRecipientFaxNo(),
						wrapperSendFax.getRecipientName(), wrapperSendFax.getSenderFaxNo(),
						wrapperSendFax.getSenderName(), wrapperSendFax.getSenderOrganization(),
						wrapperSendFax.getFaxSubject(), wrapperSendFax.getFaxNotes(), generatedCoverPagePath,
						faxAttachmentFileGenerationResponse.getLst_attachment());

				String fax_reference_id;
				if (fax_response.split(":")[0].equals("OK")) {
					fax_reference_id = fax_response.split(":")[1];

					wrapperSendFax.setSetFaxReferenceId(fax_reference_id);

					String fax_status = "Created";
					updateFaxInfo(wrapperSendFax, fax_status, fax_response, 1, patDocumentDirectory,
							faxAttachmentFileGenerationResponse.getLst_attachment());

					System.out.println("Fax Send OK Response : " + fax_response);
					// return "OK";

				} else {
					System.out.println("Fax Send Error Response : " + fax_response);
					// return "Fax Send Failed.";
				}

			} else {
				fax_response = "ERROR:" + faxAttachmentFileGenerationResponse.getError_message();
				System.out.println("Fax Send Error Response : " + fax_response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// return null;
			fax_response = "ERROR:" + e.getMessage();
		}

		return fax_response;
	}

	private FaxAttachmentFileGenerationResponse prepareFaxAttachements(WrapperSendFax wrapperSendFax,
			MultipartFile[] lstMultipartFile, String patDocumentDirectory) {

		FaxAttachmentFileGenerationResponse faxAttachmentFileGenerationResponse = new FaxAttachmentFileGenerationResponse();

		Long newId = (long) 0;
		Boolean isError = false;
		List<SendFaxAttachments> lstAttachments = new ArrayList<>();
		try {

			PDFOperations objPDF = new PDFOperations();

			if (wrapperSendFax.getLstAttachments() != null && wrapperSendFax.getLstAttachments().size() > 0) {

				newId = db.IDGenerator("temp_fax_file_id", wrapperSendFax.getPractice_id(),
						wrapperSendFax.getLstAttachments().size());

				for (SendFaxAttachmentsFromClient objAttach : wrapperSendFax.getLstAttachments()) {

					String docPath = "";
					String docLink = "";

					if (objAttach.getDocument_source().equalsIgnoreCase("multi_part")) {

						if (lstMultipartFile != null && lstMultipartFile.length > 0) {

							MultipartFile file = lstMultipartFile[objAttach.getMultipart_index()];

							if (GeneralOperations.isNotNullorEmpty(file)) {

								String FileName = wrapperSendFax.getPractice_id() + newId.toString()
										+ objAttach.getDocument_name().substring(
												objAttach.getDocument_name().lastIndexOf("."),
												objAttach.getDocument_name().length());

								UploadFile uploadedFile = FileUtil.UploadDocumentYearMonthDayWise(file,
										patDocumentDirectory, wrapperSendFax.getPractice_id().toString(),
										"PatientDocuments\\temp", FileName);

								docLink = uploadedFile.getLink();

								docPath = patDocumentDirectory + wrapperSendFax.getPractice_id().toString()
										+ "\\PatientDocuments\\temp\\" + uploadedFile.getLink();

								System.out.println("PDF File Created:" + docPath);

								SendFaxAttachments sendFaxAttachments = new SendFaxAttachments();
								sendFaxAttachments.setDocument_name(objAttach.getDocument_name());
								sendFaxAttachments.setDocument_link(docLink);
								sendFaxAttachments.setDocument_path(docPath);
								sendFaxAttachments.setDocument_source("temp_file");

								lstAttachments.add(sendFaxAttachments);

							}

						}

					} else if (objAttach.getDocument_source().equalsIgnoreCase("html_string")) {

						String FileName = wrapperSendFax.getPractice_id() + newId.toString() + ".pdf";

						docPath = FileUtil.GetYearMonthDayWisePath(patDocumentDirectory,
								wrapperSendFax.getPractice_id().toString(), "PatientDocuments\\temp");

						if (objPDF.create_pdf_fromHTMLStream(objAttach.getHtml_string(), docPath, FileName, "")) {

							System.out.println("PDF File Created:-" + docPath);
							String[] splitedPath = docPath.split("\\\\");
							docLink = splitedPath[splitedPath.length - 3] + "\\" + splitedPath[splitedPath.length - 2]
									+ "\\" + splitedPath[splitedPath.length - 1] + "\\" + FileName;

							System.out.println("Retun Path:-" + docLink);

							String fullPDFPath = docPath + "\\" + FileName;
							SendFaxAttachments sendFaxAttachments = new SendFaxAttachments();
							sendFaxAttachments.setDocument_name(objAttach.getDocument_name());
							sendFaxAttachments.setDocument_link(docLink);
							sendFaxAttachments.setDocument_path(fullPDFPath);
							sendFaxAttachments.setDocument_source("temp_file");

							lstAttachments.add(sendFaxAttachments);
						} else {
							System.out.println("PDF Generation from HTML String failed.");
							isError = true;
						}

					} else if (objAttach.getDocument_source().equalsIgnoreCase("temp_file")) {

						docPath = patDocumentDirectory + wrapperSendFax.getPractice_id().toString()
								+ "\\PatientDocuments\\temp\\" + objAttach.getDocument_link();

						SendFaxAttachments sendFaxAttachments = new SendFaxAttachments();
						sendFaxAttachments.setDocument_name(objAttach.getDocument_name());
						sendFaxAttachments.setDocument_link(objAttach.getDocument_link());
						sendFaxAttachments.setDocument_path(docPath);
						sendFaxAttachments.setDocument_source("temp_file");

						lstAttachments.add(sendFaxAttachments);

					} else if (objAttach.getDocument_source().equalsIgnoreCase("referenced_document")) {

						docPath = patDocumentDirectory + wrapperSendFax.getPractice_id().toString()
								+ "\\PatientDocuments\\" + objAttach.getDocument_link();

						SendFaxAttachments sendFaxAttachments = new SendFaxAttachments();
						sendFaxAttachments.setPatient_document_id(objAttach.getPatient_document_id());
						sendFaxAttachments.setDocument_name(objAttach.getDocument_name());
						sendFaxAttachments.setDocument_link(objAttach.getDocument_link());
						sendFaxAttachments.setDocument_path(docPath);
						sendFaxAttachments.setDocument_source("referenced_document");

						lstAttachments.add(sendFaxAttachments);
					}
					newId++;
				}
			}

			if (!isError) {
				faxAttachmentFileGenerationResponse.setLst_attachment(lstAttachments);
			} else {
				faxAttachmentFileGenerationResponse.setError_message("An Error Occured while generating PDF Files.");
				System.out.println("Fax Send Error Response : An Error Occured while generating PDF Files.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// return null;
			// fax_response = "ERROR:" + e.getMessage();
			faxAttachmentFileGenerationResponse.setError_message(e.getMessage());
		}

		return faxAttachmentFileGenerationResponse;
	}

	public int updateFaxInfo(WrapperSendFax wrapperSendFax, String faxStatus, String faxResponse,
			Integer resentAttempts, String fileDirectogry, List<SendFaxAttachments> lstSendFaxAttachments) {

		System.out.println("\nUpdating Fax Info");

		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();

		ORMFaxSentSave objFaxSent = new ORMFaxSentSave();

		objFaxSent.setFax_sent_id(db.IDGenerator("fax_sent", wrapperSendFax.getPractice_id()));
		objFaxSent.setFax_status(faxStatus);
		// objFaxSent.setResponse_url(fax_url);
		objFaxSent.setSubject(wrapperSendFax.getFaxSubject());
		objFaxSent.setFax_time(wrapperSendFax.getSentDate());
		// objFaxSent.setSender_email(FromEmail);

		objFaxSent.setReceiver_name(wrapperSendFax.getRecipientName());
		objFaxSent.setReceiver_no(wrapperSendFax.getRecipientFaxNo());
		objFaxSent.setReceiver_organization(wrapperSendFax.getRecipientOrganization());
		objFaxSent.setReceiver_phone(wrapperSendFax.getRecipientPhoneNo());

		objFaxSent.setSender_name(wrapperSendFax.getSenderName());
		objFaxSent.setSender_no(wrapperSendFax.getSenderFaxNo());
		objFaxSent.setSender_organization(wrapperSendFax.getSenderOrganization());
		objFaxSent.setSender_phone(wrapperSendFax.getSenderPhoneNo());

		objFaxSent.setClient_date_created(wrapperSendFax.getSentDate());
		objFaxSent.setCreated_user(wrapperSendFax.getUserName());
		objFaxSent.setDate_created(DateTimeUtil.getCurrentDateTime());
		objFaxSent.setFax_from(wrapperSendFax.getModuleName());
		objFaxSent.setFax_response(faxResponse);
		objFaxSent.setPatient_id(wrapperSendFax.getPatient_id());
		objFaxSent.setPractice_id(wrapperSendFax.getPractice_id());

		objFaxSent.setResend_attempts(resentAttempts);
		// objFaxSent.setInclude_cover_page(include_cover_page);

		objFaxSent.setCover_page_comments(wrapperSendFax.getFaxNotes());
		objFaxSent.setFax_type(wrapperSendFax.getFax_server());
		objFaxSent.setFax_reference_id(wrapperSendFax.getSetFaxReferenceId());
		objFaxSent.setModule_reference_id(wrapperSendFax.getModuleReferenceId());
		objFaxSent.setFax_sent_id_main(wrapperSendFax.getFaxSentIdMain());

		if (GeneralOperations.isNotNullorEmpty(wrapperSendFax.getFaxSentIdMain())) {
			objFaxSent.setFax_sent_id_main(wrapperSendFax.getFaxSentIdMain());
		}

		lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objFaxSent));

		if (wrapperSendFax.getFaxSentIdMain() == null || wrapperSendFax.getFaxSentIdMain() <= 0) {

			Long newId = (long) 0;
			// GenericOperations objGeneral = new GenericOperations();

			// int total_temp_files = 0;
			if (wrapperSendFax.getLstAttachments() != null && wrapperSendFax.getLstAttachments().size() > 0) {

				// for (ORMFaxAttachments objAttach : attachments) {
				// if (objAttach.getDocument_source().equalsIgnoreCase("tempfile")) {
				// total_temp_files++;
				// }
				// }

				newId = db.IDGenerator("fax_attachment", wrapperSendFax.getPractice_id(),
						wrapperSendFax.getLstAttachments().size());

				String source_file_path;
				String destination_path;
				String doc_link;

				for (SendFaxAttachments objAttach : lstSendFaxAttachments) {

					if (objAttach.getDocument_source().equalsIgnoreCase("temp_file")) {

						source_file_path = objAttach.getDocument_path();
						String ext = source_file_path.substring(source_file_path.lastIndexOf("."),
								source_file_path.length());

						doc_link = DateTimeUtil.getCurrentYear() + "\\" + DateTimeUtil.getCurrentMonth() + "\\"
								+ DateTimeUtil.getCurrentDay() + "\\" + newId.toString()
								+ FileUtil.GetDatetimeFileName() + ext;

						destination_path = fileDirectogry + wrapperSendFax.getPractice_id() + "\\PatientDocuments\\"
								+ doc_link;

						System.out.println("\n Source File path : " + source_file_path);
						System.out.println("\n Destination File path : " + destination_path);
						System.out.println("\n doc_link : " + doc_link);

						if (FileUtil.CopyFile(source_file_path, destination_path) == true) {
							objAttach.setDocument_link(doc_link);
						}
					}

					ORMFaxAttachmentSave objSaveAttach = new ORMFaxAttachmentSave();
					objSaveAttach.setFax_attachment_id(newId);
					objSaveAttach.setClient_date_created(wrapperSendFax.getSentDate());
					objSaveAttach.setCreated_user(wrapperSendFax.getUserName());
					objSaveAttach.setDoc_link(objAttach.getDocument_link());
					objSaveAttach.setFax_sent_id(objFaxSent.getFax_sent_id());
					objSaveAttach.setFax_no(wrapperSendFax.getRecipientFaxNo());
					objSaveAttach.setDate_created(DateTimeUtil.getCurrentDateTime());
					objSaveAttach.setPatient_document_id(objAttach.getPatient_document_id());
					objSaveAttach.setDoc_name(objAttach.getDocument_name());

					System.out.println("Fax Attachment ID : " + newId);
					newId++;

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, objSaveAttach));
				}
			}
		}

		if (wrapperSendFax.getModuleName().equalsIgnoreCase("referral") && wrapperSendFax.getModuleReferenceId() != null
				&& wrapperSendFax.getModuleReferenceId() > 0) {
			String strQuery = "update patient_referral set referral_status='Faxed' where referral_id='"
					+ wrapperSendFax.getModuleReferenceId() + "'";

			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, strQuery));
		}

		return db.AddUpdateEntityWrapper(lstEntityWrapper);
	}

	/**
	 * 
	 * @param sourceHTMLPath
	 * @param toName
	 * @param toFaxNo
	 * @param fromName
	 * @param fromFaxNo
	 * @param fromPhoneNo
	 * @param subject
	 * @param comments
	 * @param practiceId
	 * @param fileDirectory
	 * @return Fax Cover Path
	 */
	public String getFaxCoverPage(String sourceHTMLPath, String toName, String toFaxNo, String fromName,
			String fromFaxNo, String fromPhoneNo, String subject, String comments, String practiceId,
			String fileDirectory) {
		String generatedPath = "";
		try {

			String strhtml = new String(Files.readAllBytes(Paths.get(sourceHTMLPath)));

			strhtml = strhtml.replace("@ToName", toName == null ? "" : toName);
			strhtml = strhtml.replace("@ToFaxNo", toFaxNo == null ? "" : toFaxNo);
			// strhtml=strhtml.replace("@ToOrganization", toOrganization);

			strhtml = strhtml.replace("@FromName", fromName == null ? "" : fromName);
			strhtml = strhtml.replace("@FromFaxNo", fromFaxNo == null ? "" : fromFaxNo);
			strhtml = strhtml.replace("@FromPhoneNo", fromPhoneNo == null ? "" : fromPhoneNo);

			strhtml = strhtml.replace("@subject", subject == null ? "" : subject);
			strhtml = strhtml.replace("@comments", comments == null ? "" : comments);

			// System.out.println(strhtml);
			// PDFOperations obj=new PDFOperations();
			// obj.create_pdf_fromHTMLStream("500", strhtml,"c://s" ,"Fax87CoverSheet.pdf",
			// "");

			PDFOperations objPDF = new PDFOperations();

			String f_name = practiceId + FileUtil.GetDatetimeFileName() + ".pdf";
			String full_file_path = FileUtil.GetYearMonthDayWisePath(fileDirectory, practiceId,
					"PatientDocuments\\temp");

			// String file_name=practice_id+GenericOperations.getDatetimeFileName()+".pdf";
			if (objPDF.create_pdf_fromHTMLStream(strhtml, full_file_path, f_name, "")) {
				// String[] splitedPath = full_file_path.split("\\\\");
				// generatedPath = splitedPath[splitedPath.length - 3] +
				// "\\"+splitedPath[splitedPath.length - 2] + "\\"+
				// splitedPath[splitedPath.length - 1] + "\\" +f_name;
				generatedPath = full_file_path + "\\" + f_name;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return generatedPath;
	}

	public List<ORMFaxConfigGet> getFaxConfig(Long practiceId, String faxServer, String faxNo) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("fax_server", faxServer.toString(), String.class, ParameterMode.IN));
		lstParam.add(new SpParameters("fax_no", faxNo.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetFaxCofig", ORMFaxConfigGet.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> resendFax(FaxResendModel faxResendModel) {
		// TODO Auto-generated method stub
		String fax_response = "";
		Boolean isError = false;

		try {

			List<ORMFaxConfigGet> lstConfig = getFaxConfig(faxResendModel.getPracticeId(),
					faxResendModel.getClientFaxServer(), faxResendModel.getClientFaxNo());

			if (lstConfig != null && lstConfig.size() > 0) {

				fax_response = "";

				if (faxResendModel.getClientFaxServer().equalsIgnoreCase(FaxServerEnum.FAXAGE.toString())) {
					FaxAge objFax = new FaxAge(lstConfig.get(0).getUser_name(), lstConfig.get(0).getPassword(),
							lstConfig.get(0).getCompany_id());
					fax_response = objFax.ResendFax(faxResendModel.getClientFaxNo(),
							faxResendModel.getFaxReferenceId());

				} else if (faxResendModel.getClientFaxServer().equalsIgnoreCase(FaxServerEnum.MFAX.toString())) {

					MFax objFax = new MFax(lstConfig.get(0).getAccount_id(), lstConfig.get(0).getApi_key());
					fax_response = objFax.ReSendFax(faxResendModel.getFaxReferenceId());
				}

				if (fax_response.split(":")[0].equals("OK")) {

					System.out.println("Fax Re-Send OK Response : " + fax_response);
					String newFaxRefId = fax_response.split(":")[1];

					Long newFaxSentId = db.IDGenerator("fax_sent", faxResendModel.getPracticeId());

					String query = " insert into fax_sent (fax_sent_id	,sender_name	,sender_email,	fax_status	,fax_time	,fax_from	,fax_response,	practice_id	,patient_id	,created_user,	"
							+ " client_date_created	,date_created	,client_date_modified	,modified_user	,date_modified	,system_ip	,fax_status_detail	,"
							+ " subject	,resend_attempts	,receiver_no	,receiver_name	,receiver_organization	,receiver_phone	,sender_organization	,sender_phone	,sender_no	,"
							+ " include_cover_page	,cover_page_comments	,fax_type	,fax_reference_id,module_reference_id,fax_sent_id_main) "
							+ " select '" + newFaxSentId.toString()
							+ "' as fax_sent_id ,sender_name	,sender_email,'Pending'	fax_status	,getdate() fax_time	,fax_from,"
							+ " '' as fax_response,	practice_id	,patient_id	,'" + faxResendModel.getLogedInUser()
							+ "' as created_user,	" + "'" + faxResendModel.getClientDateTime()
							+ "' as client_date_created	,getdate() as date_created	,'"
							+ faxResendModel.getClientDateTime() + "'  as client_date_modified	,'"
							+ faxResendModel.getLogedInUser() + "' as modified_user	,getdate() as date_modified	,'"
							+ faxResendModel.getSystemIp() + "' as system_ip	,'' as fax_status_detail,"
							+ " 'RESEND:'+subject	,'1' AS resend_attempts	,receiver_no	,receiver_name	,receiver_organization	,receiver_phone	,sender_organization	,sender_phone	,sender_no	,"
							+ " include_cover_page	,cover_page_comments	,fax_type	,'" + newFaxRefId
							+ "' as fax_reference_id,module_reference_id,fax_sent_id "
							+ " from fax_sent where fax_sent_id='" + faxResendModel.getFaxSentIdMain() + "' ";

					if (db.ExecuteUpdateQuery(query) > 0) {
						isError = false;
						System.out.println("Fax Re-Sent Successfully. And Record has been saved.");
					} else {
						isError = true;
						System.out.println("Fax Re-Sent Successfully. Error while saving record.");
						fax_response = "Fax has been Re-Sent. But an error occured while saving record.";
					}

				} else {
					isError = true;
					System.out.println("Fax Re-Send Error Response : " + fax_response);
				}
			} else {
				isError = true;
				System.out.println("Fax Configuration not found.");
				fax_response = "Fax Configuration not found.";
			}

			// fax_response = "Fax sending is committed for the time being.... ihsan";

		} catch (Exception e) {
			e.printStackTrace();
			// return null;
			isError = true;
			fax_response = "ERROR:" + e.getMessage();
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (!isError) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse(fax_response);
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse(fax_response);
		}

		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> updateFaxSendingStatus(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		// String faxType = "";
		String clientDateTime = "";
		String logedInUser = "";
		String faxSentIds = "";
		String systemIp = "";
		Boolean isError = false;
		String msg = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {
				// case "faxType":
				// faxType = pram.getValue();
				// break;
				case "clientDateTime":
					clientDateTime = pram.getValue();
					break;
				case "logedInUser":
					logedInUser = pram.getValue();
					break;
				case "faxSentIds":
					faxSentIds = pram.getValue();
					break;
				case "systemIp":
					systemIp = pram.getValue();
					break;
				default:
					break;
				}

			}
		}

		List<ORMGetFaxDetailsForStatusUpdate> lstFaxDetails = this.GetFaxDetailsForStatusUpdate(faxSentIds);

		for (int i = 0; i < lstFaxDetails.size(); i++) {

			String faxStatus = "";
			String faxStatusDetails = "";

			switch (lstFaxDetails.get(i).getFax_type().toUpperCase()) {
			case "FAXAGE": {
				faxStatus = this.getFaxAgeFaxStatus(lstFaxDetails.get(i));
				faxStatusDetails = "";

				String FaxStatusSplitted[] = faxStatus.split("~");

				if (FaxStatusSplitted.length > 1) {
					faxStatus = FaxStatusSplitted[0];
					faxStatusDetails = FaxStatusSplitted[1];
				}

				if (!faxStatus.equalsIgnoreCase("ERROR")) {
					switch (faxStatus.toLowerCase()) {
					case "success":
						faxStatus = "Success";
						break;
					case "pending":
						faxStatus = "Pending";
						break;
					case "failure":
						faxStatus = "Fail";
						break;
					default:
						faxStatusDetails = faxStatus;
						faxStatus = "";
						break;
					}

					msg = faxStatus;
					this.updateFasStatus(lstFaxDetails.get(i).getFax_sent_id(), faxStatus, faxStatusDetails,
							logedInUser, clientDateTime, systemIp);

				} else {
					isError = true;
					msg = faxStatusDetails;
					// break;
				}
				break;
			}
			case "MFAX": {

				faxStatus = this.getMFaxStatus(lstFaxDetails.get(i));
				faxStatusDetails = "";

				String FaxStatusSplitted[] = faxStatus.split("~");

				if (FaxStatusSplitted.length == 1) {
					faxStatus = FaxStatusSplitted[0];
					faxStatusDetails = "";
				}
				if (FaxStatusSplitted.length > 1) {
					faxStatus = FaxStatusSplitted[0];
					faxStatusDetails = FaxStatusSplitted[1];
				}

				if (!faxStatus.equalsIgnoreCase("ERROR")) {
					switch (faxStatus.toLowerCase()) {
					case "success":
						faxStatus = "Success";
						break;
					case "transmitting":
						faxStatus = "Pending";
						break;
					case "failed":
						faxStatus = "Fail";
						break;
					default:
						faxStatusDetails = faxStatus;
						faxStatus = "";
						break;
					}

					msg = faxStatus;
					this.updateFasStatus(lstFaxDetails.get(i).getFax_sent_id(), faxStatus, faxStatusDetails,
							logedInUser, clientDateTime, systemIp);

				} else {
					isError = true;
					msg = faxStatusDetails;
					// break;
				}
				break;
			}
			default: {
				System.out.println("Fax Server type is not supported. :" + lstFaxDetails.get(i).getFax_type());
				break;
			}
			}

			if (isError) {
				break;
			}
			/*
			 * if (lstFaxDetails.get(i).getFax_type().equalsIgnoreCase("FAXAGE")) {
			 * 
			 * String faxStatus = this.updateFaxAgeFaxStatus(lstFaxDetails.get(i)); String
			 * faxStatusDetails = "";
			 * 
			 * String FaxStatusSplitted[] = faxStatus.split("~");
			 * 
			 * if (FaxStatusSplitted.length > 1) { faxStatus = FaxStatusSplitted[0];
			 * faxStatusDetails = FaxStatusSplitted[1]; }
			 * 
			 * if (!faxStatus.equalsIgnoreCase("error")) { switch (faxStatus.toLowerCase())
			 * { case "success": faxStatus = "Success"; break; case "pending": faxStatus =
			 * "Pending"; break; case "failure": faxStatus = "Fail"; break; default:
			 * faxStatusDetails = faxStatus; faxStatus = ""; break; }
			 * 
			 * msg = faxStatus; this.updateFasStatus(lstFaxDetails.get(i).getFax_sent_id(),
			 * faxStatus, faxStatusDetails, logedInUser, clientDateTime, systemIp);
			 * 
			 * } else { isError = true; msg = faxStatusDetails; break; } } else {
			 * System.out.println("Fax Server type is not supported. :" +
			 * lstFaxDetails.get(i).getFax_type()); }
			 */
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();

		if (!isError) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse(msg);
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse(msg);
		}

		return resp;
	}

	public String getFaxAgeFaxStatus(ORMGetFaxDetailsForStatusUpdate faxDetailsForStatusUpdate) {

		String faxStatus = "";

		try {

			// HibernateTransactionUtil hibernate = HibernateTransactionUtil.getInstance();
			// String FaxStatus = hibernate.getScalarValue("select fax_status as
			// scalar_value from fax_sent where fax_sent_id='" + fax_sent_id + "' ");
			if (faxDetailsForStatusUpdate.getFax_status().equalsIgnoreCase("success")
					|| faxDetailsForStatusUpdate.getFax_status().equalsIgnoreCase("fail")) {
				System.out.println("Fax Status: " + faxDetailsForStatusUpdate.getFax_status());
				return faxDetailsForStatusUpdate.getFax_status();
			}

			List<ORMFaxConfigGet> lstConfig = getFaxConfig(faxDetailsForStatusUpdate.getPractice_id(),
					faxDetailsForStatusUpdate.getFax_type(), faxDetailsForStatusUpdate.getSender_no());

			if (lstConfig != null && lstConfig.size() > 0) {

				FaxAge objFax = new FaxAge(lstConfig.get(0).getUser_name(), lstConfig.get(0).getPassword(),
						lstConfig.get(0).getCompany_id());
				faxStatus = objFax.GetFaxstatus(faxDetailsForStatusUpdate.getFax_reference_id());
			} else {
				faxStatus = "ERROR~Fax Server Configuration not found.";
			}

			// this.updateFasStatus(faxDetailsForStatusUpdate.getFax_sent_id(), faxStatus,
			// faxStatusDetails, ClientDateTime, systemIp)

		} catch (Exception e) {

			System.out.println("\nnError" + e.getMessage());
			e.printStackTrace();
			faxStatus = "ERROR~" + e.getMessage();
		}

		return faxStatus;
	}

	private List<ORMGetFaxDetailsForStatusUpdate> GetFaxDetailsForStatusUpdate(String faxSendIds) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("fax_sent_ids", faxSendIds.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetFaxDetailsForStatusUpdate", ORMGetFaxDetailsForStatusUpdate.class,
				lstParam);
	}

	private int updateFasStatus(Long faxSentId, String faxStatus, String faxStatusDetail, String logedInuser,
			String ClientDateTime, String systemIp) {

		String strQuery = "update fax_sent set fax_status='" + faxStatus + "',fax_status_detail='"
				+ faxStatusDetail.replaceAll("'", "") + "',modified_user='" + logedInuser + "',client_date_modified='"
				+ ClientDateTime + "',system_ip='" + systemIp + "' where fax_sent_id = " + faxSentId;

		return db.ExecuteUpdateQuery(strQuery);

	}

	@Override
	public List<ORMFaxSentGet> getSentFaxes(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String dateFrom = "";
		String dateTo = "";
		String patientId = "";
		String userName = "";
		String criteria = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {

				case "dateFrom":
					dateFrom = pram.getValue();
					break;
				case "dateTo":
					dateTo = pram.getValue();
					break;
				case "patientId":
					patientId = pram.getValue();
					break;
				case "userName":
					userName = pram.getValue();
					break;
				default:
					break;
				}

			}
		}

		criteria = " and f.practice_id ='" + searchCriteria.getPractice_id().toString() + "'";

		if (dateFrom != "" && dateTo != "") {
			criteria = criteria + " and convert(date,f.client_date_created) between convert(date,'" + dateFrom
					+ "') and convert(date,'" + dateTo + "')";
		} else if (dateFrom != "") {
			criteria = criteria + " and convert(date,f.client_date_created) >= convert(date,'" + dateFrom + "') ";
		} else if (dateTo != "") {
			criteria = criteria + " and convert(date,f.client_date_created) <= convert(date,'" + dateTo + "')";
		}

		if (GeneralOperations.isNotNullorEmpty(userName)) {
			criteria = criteria + " and f.created_user = '" + userName + "'";
		}
		if (GeneralOperations.isNotNullorEmpty(patientId)) {
			criteria = criteria + " and f.patient_id = '" + patientId + "'";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetFaxSent", ORMFaxSentGet.class, lstParam);

	}

	@Override
	public List<ORMFaxSentAttachment> getSentFaxAttachments(Long faxSentId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("fax_sent_id", faxSentId.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetFaxSentAttachment", ORMFaxSentAttachment.class, lstParam);
	}

	@Override
	public List<ORMFaxSentAttemptsGet> getFaxSendingAttempts(Long faxSentId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();

		lstParam.add(new SpParameters("fax_sent_id", faxSentId.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetFaxSentAttempts", ORMFaxSentAttemptsGet.class, lstParam);
	}

	@Override
	public List<ORMFaxReceivedGet> getReceivedFaxes(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String dateFrom = "";
		String dateTo = "";
		String locationId = "";
		String receiverFaxNo = "";
		String criteria = "";

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {

				case "dateFrom":
					dateFrom = pram.getValue();
					break;
				case "dateTo":
					dateTo = pram.getValue();
					break;
				case "locationId":
					locationId = pram.getValue();
					break;
				case "receiverFaxNo":
					receiverFaxNo = pram.getValue();
					break;
				default:
					break;
				}

			}
		}

		criteria = " fc.practice_id ='" + searchCriteria.getPractice_id().toString() + "'";

		if (dateFrom != "" && dateTo != "") {
			criteria = criteria + " and convert(date,fc.recived_date) between convert(date,'" + dateFrom
					+ "') and convert(date,'" + dateTo + "')";
		} else if (dateFrom != "") {
			criteria = criteria + " and convert(date,fc.recived_date) >= convert(date,'" + dateFrom + "') ";
		} else if (dateTo != "") {
			criteria = criteria + " and convert(date,fc.recived_date) <= convert(date,'" + dateTo + "')";
		}

		if (GeneralOperations.isNotNullorEmpty(locationId)) {
			criteria = criteria + " and fc.location_id = '" + locationId + "'";
		}
		if (GeneralOperations.isNotNullorEmpty(receiverFaxNo)) {
			criteria = criteria + " and fc.receiver = '" + receiverFaxNo + "'";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetAllFaxes", ORMFaxReceivedGet.class, lstParam);
	}

	@Override
	public List<ORMFaxReceivedUserAssignedGet> getUserAssignedReceivedFaxes(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		String dateFrom = "";
		String dateTo = "";
		String locationId = "";
		String receiverFaxNo = "";
		String faxAssignedToUserId = "";
		String criteria = "";
		Boolean myFaxes = false;

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {

				case "dateFrom":
					dateFrom = pram.getValue();
					break;
				case "dateTo":
					dateTo = pram.getValue();
					break;
				case "locationId":
					locationId = pram.getValue();
					break;
				case "receiverFaxNo":
					receiverFaxNo = pram.getValue();
					break;
				case "faxAssignedToUserId":
					faxAssignedToUserId = pram.getValue();
					break;
				case "myFaxes":
					myFaxes = Boolean.parseBoolean(pram.getValue()); // 'true|false'
					break;
				default:
					break;
				}

			}
		}

		criteria = " fc.practice_id ='" + searchCriteria.getPractice_id().toString() + "'";

		if (myFaxes) {
			if (dateFrom != "" && dateTo != "") {
				criteria = criteria + " and convert(date,fu.client_date_created) between convert(date,'" + dateFrom
						+ "') and convert(date,'" + dateTo + "')";
			} else if (dateFrom != "") {
				criteria = criteria + " and convert(date,fu.client_date_created) >= convert(date,'" + dateFrom + "') ";
			} else if (dateTo != "") {
				criteria = criteria + " and convert(date,fu.client_date_created) <= convert(date,'" + dateTo + "')";
			}

			if (GeneralOperations.isNotNullorEmpty(faxAssignedToUserId)) {
				criteria = criteria + " and fu.assigned_to_id='" + faxAssignedToUserId + "'";
			}

		} else {
			if (dateFrom != "" && dateTo != "") {
				criteria = criteria + " and convert(date,fc.recived_date) between convert(date,'" + dateFrom
						+ "') and convert(date,'" + dateTo + "')";
			} else if (dateFrom != "") {
				criteria = criteria + " and convert(date,fc.recived_date) >= convert(date,'" + dateFrom + "') ";
			} else if (dateTo != "") {
				criteria = criteria + " and convert(date,fc.recived_date) <= convert(date,'" + dateTo + "')";
			}

			if (GeneralOperations.isNotNullorEmpty(faxAssignedToUserId)) {
				criteria = criteria + " and isnull(fc.deleted,0)<>1 and fu.assigned_to_id='" + faxAssignedToUserId
						+ "'";
			}

		}

		if (GeneralOperations.isNotNullorEmpty(locationId)) {
			criteria = criteria + " and fc.location_id = '" + locationId + "'";
		}
		if (GeneralOperations.isNotNullorEmpty(receiverFaxNo)) {
			criteria = criteria + " and fc.receiver = '" + receiverFaxNo + "'";
		}

		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("criteria", criteria.toString(), String.class, ParameterMode.IN));

		return db.getStoreProcedureData("spGetFaxMine", ORMFaxReceivedUserAssignedGet.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> downloadFaxesFromServer(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		String clientFaxNo = "";
		String clientDateTime = "";
		String logedInuser = "";
		String systemIp = "";
		String faxServer = "";

		Boolean isError = false;
		String msg = "";
		List<ORMKeyValue> response_list = null;

		Long practiceId = searchCriteria.getPractice_id();

		if (searchCriteria.getParam_list() != null && !searchCriteria.getParam_list().isEmpty()) {
			for (SearchCriteriaParamList pram : searchCriteria.getParam_list()) {

				switch (pram.getName()) {

				case "clientFaxNo":
					clientFaxNo = pram.getValue();
					break;
				case "clientDateTime":
					clientDateTime = pram.getValue();
					break;
				case "logedInuser":
					logedInuser = pram.getValue();
					break;
				case "systemIp":
					systemIp = pram.getValue();
					break;
				case "faxServer":
					faxServer = pram.getValue();
					break;
				default:
					break;
				}

			}
		}

		List<ORMFaxConfigGet> lstConfig = getFaxConfig(practiceId, faxServer, clientFaxNo);
		ORMDocumentPath objPath = generalDAOImpl.getDocumentPathByCategory(practiceId, "Fax");

		if (lstConfig != null && lstConfig.size() > 0 && objPath != null) {

			String patDocumentDirectory = objPath.getUpload_path();

			// if(faxServer.equalsIgnoreCase("FAXAGE")) {
			String resp = downloadFaxes(lstConfig.get(0), faxServer, practiceId, patDocumentDirectory, clientDateTime,
					logedInuser, systemIp);
			String[] res = resp.split("~");
			if (Integer.parseInt(res[0]) > 0) {
				isError = false;
				msg = "Fax has been downloaded successfully. (" + res[0] + ")<br>Error Occured:(" + res[1] + ")";

				// String updateQuery="update "

			} else if (Integer.parseInt(res[1]) > 0) {
				isError = true;
				msg = "Error Occurd while getting Fax from server.";
			}

			response_list = new ArrayList<>();
			response_list.add(new ORMKeyValue("total", res[0]));
			response_list.add(new ORMKeyValue("success", res[1]));
			response_list.add(new ORMKeyValue("error", res[2]));
			// }

		} else {
			msg = "Fax Server Configuration not found.";
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		resp.setResponse(msg);
		resp.setResponse_list(response_list);

		if (!isError) {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
		} else {
			resp.setStatus(ServiceResponseStatus.ERROR);
		}
		return resp;
	}

	private String downloadFaxes(ORMFaxConfigGet faxConfigGet, String faxServer, Long practiceId, String directory,
			String clientDateTime, String loginUser, String systemIp) {

		int total = 0;
		int success = 0;
		int error = 0;
		List<FaxReceivedObject> lstFaxes = null;

		System.out.println("Getting Fax List");
		FaxAge objFaxAge = null;
		MFax objMFax = null;
		if (faxServer.equalsIgnoreCase(FaxServerEnum.FAXAGE.toString())) {
			objFaxAge = new FaxAge(faxConfigGet.getUser_name(), faxConfigGet.getPassword(),
					faxConfigGet.getCompany_id());
			lstFaxes = objFaxAge.getReceivedFaxList(faxConfigGet.getFax_no());
		} else if (faxServer.equalsIgnoreCase(FaxServerEnum.MFAX.toString())) {
			objMFax = new MFax(faxConfigGet.getAccount_id(), faxConfigGet.getApi_key());
			lstFaxes = objMFax.GetInboudnFaxList();
		}

		if (lstFaxes != null && lstFaxes.size() > 0) {

			total = lstFaxes.size();
			// FaxAgeFileReceivedFax fax=lstFaxes.get(0);
			for (FaxReceivedObject fax : lstFaxes) {

				System.out.println("Downloading Fax:" + fax.getRecvid());
				UploadFile downloadedFax = null;
				if (faxServer.equalsIgnoreCase(FaxServerEnum.FAXAGE.toString())) {
					downloadedFax = objFaxAge.downLoadFax(fax.getRecvid(), practiceId.toString(), directory);
				} else if (faxServer.equalsIgnoreCase(FaxServerEnum.MFAX.toString())) {
					downloadedFax = objMFax.downloadFax(fax.getRecvid(), practiceId.toString(), directory);
				}

				if (downloadedFax != null) {

					Long newId = db.IDGenerator("fax_recived", practiceId);
					ORMFaxReceivedSave objFaxIns = new ORMFaxReceivedSave();
					objFaxIns.setFax_recived_id(newId);
					objFaxIns.setClient_date_created(clientDateTime);
					objFaxIns.setClient_date_modified(clientDateTime);
					objFaxIns.setSystem_ip(systemIp);

					objFaxIns.setAssigned_to("");
					objFaxIns.setAssigned_to_id("");

					objFaxIns.setCreated_user(loginUser);
					objFaxIns.setDate_modified(clientDateTime);
					objFaxIns.setDate_created(clientDateTime);

					objFaxIns.setLocation_id((long) -1);
					objFaxIns.setModified_user(loginUser);
					objFaxIns.setPractice_id(practiceId);
					objFaxIns.setReceiver(faxConfigGet.getFax_no());

					objFaxIns.setFax_reference_id(fax.getRecvid());
					objFaxIns.setSender(fax.getCSID());

					objFaxIns.setRecived_date(fax.getRecvdate());

					objFaxIns.setLink(downloadedFax.getLink());
					objFaxIns.setFax_name(downloadedFax.getName());
					objFaxIns.setFax_type(faxServer);

					if (db.SaveEntity(objFaxIns, Operation.ADD) > 0) {

						if (faxServer.equalsIgnoreCase(FaxServerEnum.FAXAGE.toString())) {
							// Need to Mark as read on live server.
							objFaxAge.updateFaxStatusAtServer(fax.getRecvid());
						} else if (faxServer.equalsIgnoreCase(FaxServerEnum.MFAX.toString())) {
							// Need to Mark as read on live server.
							objMFax.MarkFaxAsArchived(fax.getRecvid());
						}
						success++;
					}
				} else {
					error++;
				}
			}
		}

		return total + "~" + success + "~" + error;
	}

	@Override
	public ServiceResponse<ORMKeyValue> addFaxReceivedToPatientDocument(
			Wrapper_ObjectSave<ORMSaveDocument> wrapperObjectSave) {
		// TODO Auto-generated method stub

		int result = 0;
		String faxLink = "";
		ORMSaveDocument ormSaveDocument = wrapperObjectSave.getOrmSave();
		List<ORMKeyValue> lstKV = wrapperObjectSave.getLstKeyValue();

		if (lstKV != null && lstKV.size() > 0) {
			for (ORMKeyValue kv : lstKV) {
				switch (kv.getKey()) {
				case "fax_link":
					faxLink = kv.getValue();
					break;
				default:
					break;
				}
			}
		}

		String serverDateTime = DateTimeUtil.getCurrentDateTime();
		Long patDocId = db.IDGenerator("patient_document", ormSaveDocument.getPractice_id());

		ORMDocumentPath objPathPatDocuments = generalDAOImpl.getDocumentPathByCategory(ormSaveDocument.getPractice_id(),
				"PatientDocuments");

		ORMDocumentPath objPathPatFax = generalDAOImpl.getDocumentPathByCategory(ormSaveDocument.getPractice_id(),
				"Fax");

		String patDocumentDirectory = objPathPatDocuments.getUpload_path();
		String faxDirectory = objPathPatFax.getUpload_path();

		String destFileName = patDocId.toString() + ormSaveDocument.getOriginal_file_name().toString().substring(
				ormSaveDocument.getOriginal_file_name().toString().lastIndexOf("."),
				ormSaveDocument.getOriginal_file_name().toString().length());

		String sourcePath = faxDirectory + "\\" + ormSaveDocument.getPractice_id() + "\\Fax\\FaxRecived\\" + faxLink;

		String destinationPath = FileUtil.GetYearMonthDayWisePath(patDocumentDirectory,
				ormSaveDocument.getPractice_id().toString(), "PatientDocuments") + "\\" + destFileName;

		String[] splitedPath = destinationPath.split("\\\\");

		String docLink = splitedPath[splitedPath.length - 4] + "\\" + splitedPath[splitedPath.length - 3] + "\\"
				+ splitedPath[splitedPath.length - 2] + "\\" + destFileName;

		System.out.println("\n Source Path :" + sourcePath + "\nDestination Path :" + destinationPath);

		if (FileUtil.CopyFile(sourcePath, destinationPath)) {

			System.out.println("\n fax moved successfully.");

			ormSaveDocument.setPatient_document_id(patDocId);
			ormSaveDocument.setDate_created(serverDateTime);
			ormSaveDocument.setDate_modified(serverDateTime);
			ormSaveDocument.setLink(docLink);

			result = db.SaveEntity(ormSaveDocument, Operation.ADD);
		}

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}
		return resp;
	}

	@Override
	public List<ORMGetAssignedToUsersList> getAssignedToUsersList(Long practiceId) {
		// TODO Auto-generated method stub
		List<SpParameters> lstParam = new ArrayList<>();
		lstParam.add(new SpParameters("practice_id", practiceId.toString(), String.class, ParameterMode.IN));
		return db.getStoreProcedureData("spGetAssignedToUsersList", ORMGetAssignedToUsersList.class, lstParam);
	}

	@Override
	public ServiceResponse<ORMKeyValue> assignFaxToUsers(
			WrapperListWithOneObjectSave<ORMIdName, AssignFaxToUserParmList> wrapperListWithOneObjectSave) {

		List<ORMIdName> lstFaxAssignedUsers = wrapperListWithOneObjectSave.getLstObjSave();
		AssignFaxToUserParmList assignFaxToUserParmList = wrapperListWithOneObjectSave.getObjSave();

		int result = 0;
		String serverDateTime = DateTimeUtil.getCurrentDateTime();
		String query = "";
		List<Wrapper_Entity> lstEntityWrapper = new ArrayList<>();
		String assignedToNames = "";
		String assignedToIds = "";

		if (lstFaxAssignedUsers != null && lstFaxAssignedUsers.size() > 0) {

			Long newID = db.IDGenerator("fax_users", assignFaxToUserParmList.getPracticeId(),
					lstFaxAssignedUsers.size());

			for (ORMIdName ormIdName : lstFaxAssignedUsers) {

				if (assignedToIds != "") {
					assignedToIds += ",";
				}
				assignedToIds += ormIdName.getId();

				if (assignedToNames != "") {
					assignedToNames += ",";
				}
				assignedToNames += ormIdName.getName();

				ORMFaxUsersSave ormFaxUsersSave = new ORMFaxUsersSave();

				ormFaxUsersSave.setAssigned_to_id(ormIdName.getId().toString());
				ormFaxUsersSave.setAssigned_to(ormIdName.getName());

				ormFaxUsersSave.setFax_users_id(newID);
				ormFaxUsersSave.setFax_recived_id(assignFaxToUserParmList.getFaxRecId());
				ormFaxUsersSave.setComments(assignFaxToUserParmList.getComments());
				ormFaxUsersSave.setFax_status("Unread");
				ormFaxUsersSave.setClient_date_created(assignFaxToUserParmList.getClientDateTime());
				ormFaxUsersSave.setClient_date_modified(assignFaxToUserParmList.getClientDateTime());
				ormFaxUsersSave.setDate_created(serverDateTime);
				ormFaxUsersSave.setDate_modified(serverDateTime);
				ormFaxUsersSave.setCreated_user(assignFaxToUserParmList.getLogedInUser());
				ormFaxUsersSave.setModified_user(assignFaxToUserParmList.getLogedInUser());
				ormFaxUsersSave.setSystem_ip(assignFaxToUserParmList.getSystemIp());
				ormFaxUsersSave.setImportant(assignFaxToUserParmList.getIsImportant());

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.ENTITY, Operation.ADD, ormFaxUsersSave));

				newID++;

			}

			if (assignFaxToUserParmList.getCallingFrom().equalsIgnoreCase("DASHBOARD")) {
				query = " update fax_recived set assigned_to = (case when isnull(assigned_to,'')='' then   '"
						+ assignedToNames + "' else  assigned_to+'," + assignedToNames
						+ "' end ) ,assigned_to_id= (case when isnull(assigned_to_id,'')='' then   '" + assignedToIds
						+ "' else  assigned_to_id+'," + assignedToIds + "' end ),client_date_modified='"
						+ assignFaxToUserParmList.getClientDateTime() + "',modified_user='"
						+ assignFaxToUserParmList.getLogedInUser() + "',date_modified = " + serverDateTime + "'";

				if (GeneralOperations.isNotNullorEmpty(assignFaxToUserParmList.getFaxName())) {
					query += ", fax_name='" + assignFaxToUserParmList.getFaxName() + "'";
				}
				query += " where fax_recived_id= '" + assignFaxToUserParmList.getFaxRecId() + "' ";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

			} else {

				String comments = "";
				if (GeneralOperations.isNotNullorEmpty(assignFaxToUserParmList.getComments())) {
					comments = assignFaxToUserParmList.getComments().replaceAll("'", "`");
				}

				query = " update fax_recived set comments = '" + comments
						+ "', assigned_to = (case when isnull(assigned_to,'')='' then   '" + assignedToNames
						+ "' else  assigned_to+'," + assignedToNames
						+ "' end ) ,assigned_to_id= (case when isnull(assigned_to_id,'')='' then   '" + assignedToIds
						+ "' else  assigned_to_id+'," + assignedToIds + "' end ),client_date_modified='"
						+ assignFaxToUserParmList.getClientDateTime() + "',modified_user='"
						+ assignFaxToUserParmList.getLogedInUser() + "',date_modified = '" + serverDateTime + "'";

				if (GeneralOperations.isNotNullorEmpty(assignFaxToUserParmList.getFaxName())) {
					query += ", fax_name='" + assignFaxToUserParmList.getFaxName() + "'";
				}
				query += " where fax_recived_id= '" + assignFaxToUserParmList.getFaxRecId() + "' ";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

				if (GeneralOperations.isNotNullorEmpty(assignFaxToUserParmList.getFaxUsersId())
						&& assignFaxToUserParmList.getFaxUsersId() > 0) {

					query = " update fax_users set comments='"
							+ comments + "',assigned_to =   '"
							+ assignedToNames + "'  ,client_date_modified='"
							+ assignFaxToUserParmList.getClientDateTime() + "',modified_user='"
							+ assignFaxToUserParmList.getLogedInUser() + "',date_modified = " + serverDateTime
							+ "' where fax_users_id ='" + assignFaxToUserParmList.getFaxUsersId() + "'";

					lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

				}

			}

		} else {

			if (GeneralOperations.isNotNullorEmpty(assignFaxToUserParmList.getFaxUsersId())
					&& assignFaxToUserParmList.getFaxUsersId() > 0) {

				query = " update fax_users set comments='" + assignFaxToUserParmList.getComments().replaceAll("'", "`")
						+ "', client_date_modified =  '" + assignFaxToUserParmList.getClientDateTime()
						+ "',modified_user =  '" + assignFaxToUserParmList.getLogedInUser() + "', date_modified = "
						+ serverDateTime + "' where fax_users_id ='" + assignFaxToUserParmList.getFaxUsersId() + "'";

				lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));

			}

			query = " update fax_recived set comments = '" + assignFaxToUserParmList.getComments().replaceAll("'", "`")
					+ "', client_date_modified='" + assignFaxToUserParmList.getClientDateTime() + "',modified_user='"
					+ assignFaxToUserParmList.getLogedInUser() + "',date_modified ='" + serverDateTime
					+ "' where fax_recived_id= '" + assignFaxToUserParmList.getFaxRecId() + "' ";
			lstEntityWrapper.add(new Wrapper_Entity(EntityType.QUERY, null, query));
		}

		result = db.AddUpdateEntityWrapper(lstEntityWrapper);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);

			List<ORMKeyValue> response_list = new ArrayList<>();
			response_list.add(new ORMKeyValue("assigned_to_users", assignedToNames));
			response_list.add(new ORMKeyValue("assigned_to_users_ids", assignedToIds));
			resp.setResponse_list(response_list);
			resp.setResponse("Data has been saved successfully.");
			resp.setResult("");
		}
		return resp;
	}

	@Override
	public ServiceResponse<ORMKeyValue> updateReceivedUserFaxStatus(List<ORMKeyValue> lstKV) {
		// TODO Auto-generated method stub

		String faxUserId = "";
		String clientDateTime = "";
		String logedInUser = "";
		String systemIp = "";
		String faxStatus = "";

		if (lstKV != null && lstKV.size() > 0) {
			for (ORMKeyValue kv : lstKV) {
				switch (kv.getKey()) {
				case "faxUserId":
					faxUserId = kv.getValue();
					break;
				case "clientDateTime":
					clientDateTime = kv.getValue();
					break;
				case "logedInUser":
					logedInUser = kv.getValue();
					break;
				case "systemIp":
					systemIp = kv.getValue();
					break;
				case "faxStatus":
					faxStatus = kv.getValue();
					break;
				default:
					break;
				}
			}
		}

		String query = " update fax_users set fax_status = '" + faxStatus + "',client_date_modified = '"
				+ clientDateTime + "',modified_user ='" + logedInUser + "',date_modified= GETDATE(),system_ip='"
				+ systemIp + "' where fax_users_id = '" + faxUserId + "' ";

		int result = db.ExecuteUpdateQuery(query);

		ServiceResponse<ORMKeyValue> resp = new ServiceResponse<ORMKeyValue>();
		if (result == 0) {
			resp.setStatus(ServiceResponseStatus.ERROR);
			resp.setResponse("An Error Occured while saving record.");
		} else {
			resp.setStatus(ServiceResponseStatus.SUCCESS);
			resp.setResult(faxUserId + "~" + faxStatus);
			resp.setResponse("Data has been saved successfully.");

		}
		return resp;

	}

	public String SendMFax(WrapperSendFax wrapperSendFax, MultipartFile[] lstMultipartFile, String patDocumentDirectory,
			ORMFaxConfigGet faxConfig) {
		String fax_response = "";

		try {

			FaxAttachmentFileGenerationResponse faxAttachmentFileGenerationResponse = prepareFaxAttachements(
					wrapperSendFax, lstMultipartFile, patDocumentDirectory);

			if (GeneralOperations.isNullorEmpty(faxAttachmentFileGenerationResponse.getError_message())) {
				// Fax Cover
				String faxCoverPage = "";
				if (GeneralOperations.isNotNullorEmpty(faxConfig.getFax_cover_path())) {

					// UIID of Fax Cover Page
					faxCoverPage = faxConfig.getFax_cover_path();

					System.out.println("Cover Page Generated: " + faxCoverPage);
				}

				MFax objFax = new MFax(faxConfig.getAccount_id(), faxConfig.getApi_key());

				// fax_response = "Fax sending is committed for the time being.... ihsan";

				fax_response = objFax.SendFax(wrapperSendFax.getRecipientFaxNo(), wrapperSendFax.getRecipientName(),
						wrapperSendFax.getRecipientOrganization(), wrapperSendFax.getRecipientPhoneNo(),
						wrapperSendFax.getSenderFaxNo(), wrapperSendFax.getSenderName(),
						wrapperSendFax.getSenderOrganization(), wrapperSendFax.getSenderPhoneNo(),
						wrapperSendFax.getFaxSubject(), wrapperSendFax.getFaxNotes(), faxCoverPage,
						faxAttachmentFileGenerationResponse.getLst_attachment());

				String fax_reference_id;
				if (fax_response.split(":")[0].equals("OK")) {
					fax_reference_id = fax_response.split(":")[1];

					wrapperSendFax.setSetFaxReferenceId(fax_reference_id);

					String fax_status = "Created";
					updateFaxInfo(wrapperSendFax, fax_status, fax_response, 1, patDocumentDirectory,
							faxAttachmentFileGenerationResponse.getLst_attachment());

					System.out.println("Fax Send OK Response : " + fax_response);
					// return "OK";

				} else {
					System.out.println("Fax Send Error Response : " + fax_response);
					// return "Fax Send Failed.";
				}

			} else {
				fax_response = "ERROR:" + faxAttachmentFileGenerationResponse.getError_message();
				System.out.println("Fax Send Error Response : " + fax_response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// return null;
			fax_response = "ERROR:" + e.getMessage();
		}

		return fax_response;
	}

	public String getMFaxStatus(ORMGetFaxDetailsForStatusUpdate faxDetailsForStatusUpdate) {

		String faxStatus = "";

		try {

			// HibernateTransactionUtil hibernate = HibernateTransactionUtil.getInstance();
			// String FaxStatus = hibernate.getScalarValue("select fax_status as
			// scalar_value from fax_sent where fax_sent_id='" + fax_sent_id + "' ");
			if (faxDetailsForStatusUpdate.getFax_status().equalsIgnoreCase("success")
					|| faxDetailsForStatusUpdate.getFax_status().equalsIgnoreCase("fail")) {
				System.out.println("Fax Status: " + faxDetailsForStatusUpdate.getFax_status());
				return faxDetailsForStatusUpdate.getFax_status();
			}

			List<ORMFaxConfigGet> lstConfig = getFaxConfig(faxDetailsForStatusUpdate.getPractice_id(),
					faxDetailsForStatusUpdate.getFax_type(), faxDetailsForStatusUpdate.getSender_no());

			if (lstConfig != null && lstConfig.size() > 0) {

				MFax objFax = new MFax(lstConfig.get(0).getAccount_id(), lstConfig.get(0).getApi_key());
				faxStatus = objFax.GetFaxstatus(faxDetailsForStatusUpdate.getFax_reference_id());
			} else {
				faxStatus = "ERROR~Fax Server Configuration not found.";
			}

			// this.updateFasStatus(faxDetailsForStatusUpdate.getFax_sent_id(), faxStatus,
			// faxStatusDetails, ClientDateTime, systemIp)

		} catch (Exception e) {

			System.out.println("\nnError" + e.getMessage());
			e.printStackTrace();
			faxStatus = "ERROR~" + e.getMessage();
		}

		return faxStatus;
	}
}
