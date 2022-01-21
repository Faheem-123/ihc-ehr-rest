package com.ihc.ehr.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ihc.ehr.model.SendFaxAttachments;
import com.ihc.ehr.model.UploadFile;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;

public class MFax {

	private final String mFaxEndPoint = "https://api.documo.com";
	private String accountId = "";// = "12776e8c-21ca-4ee2-8394-8de3877d92a6";
	private String apiKey = "";// =
								// "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJhYzE3YjhjOS0yMWE2LTRhNTAtYjdlZi01OWNkYjUxNWEwMGMiLCJpYXQiOjE1OTEwMTIzNjZ9.N1EbBsKNnwfwFNW-S4LTG3eUnw1wIRzTqNBHQRxvelo";

	public MFax(String accountId, String apiKey) {
		this.accountId = accountId;
		this.apiKey = apiKey;
	}

	/**
	 * 
	 * @param recipientFaxNo
	 * @param recipientName
	 * @param senderFaxNo
	 * @param senderName
	 * @param senderCompanyName
	 * @param faxSubject
	 * @param faxNotes
	 * @param converPage
	 * @param lstAttachments
	 * @return Response
	 */
	public String SendFax(String recipientFaxNo, String recipientName, String recipientCompanyName,
			String recipientPhone, String senderFaxNo, String senderName, String senderCompanyName, String senderPhone,
			String faxSubject, String faxNotes, String converPage, List<SendFaxAttachments> lstAttachments) {

		String responseRes = "";

		try {

			System.out.println("Sending mFax....");

			String endPoint = mFaxEndPoint + "/v1/faxes";

			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost uploadFile = new HttpPost(endPoint);

			uploadFile.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + this.apiKey);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();

			String recFaxNosOnly = recipientFaxNo.replaceAll("\\D+", "");
			String recFaxNo = recFaxNosOnly.length() == 10 ? ("1" + recFaxNosOnly) : recFaxNosOnly;

			builder.addTextBody("faxNumber", recFaxNo, ContentType.TEXT_PLAIN);
			builder.addTextBody("recipientName", recipientName, ContentType.TEXT_PLAIN);
			builder.addTextBody("recipientCompany", recipientCompanyName, ContentType.TEXT_PLAIN);
			builder.addTextBody("recipientPhone", recipientPhone, ContentType.TEXT_PLAIN);

			builder.addTextBody("callerId", senderFaxNo, ContentType.TEXT_PLAIN);
			builder.addTextBody("senderName", senderName, ContentType.TEXT_PLAIN);
			builder.addTextBody("senderCompany", senderCompanyName, ContentType.TEXT_PLAIN);
			builder.addTextBody("senderPhone", senderPhone, ContentType.TEXT_PLAIN);

			builder.addTextBody("subject", faxSubject, ContentType.TEXT_PLAIN);
			builder.addTextBody("notes", faxNotes, ContentType.TEXT_PLAIN);

			builder.addTextBody("coverPage", "true", ContentType.TEXT_PLAIN);

			// if UIID of cover page is set
			if (GeneralOperations.isNotNullorEmpty(converPage)) {
				builder.addTextBody("coverPageId", converPage, ContentType.TEXT_PLAIN);
			}

			if (lstAttachments != null) {
				for (SendFaxAttachments objAttach : lstAttachments) {

					String file_path = objAttach.getDocument_path();
					String fileExt = file_path.substring(file_path.lastIndexOf(".") + 1, file_path.length());

					File file = new File(file_path);

					switch (fileExt.toLowerCase()) {
					case "txt":
						builder.addBinaryBody("attachments", new FileInputStream(file), ContentType.TEXT_PLAIN,
								file.getName());

						break;
					case "pdf":
						builder.addBinaryBody("attachments", new FileInputStream(file),
								ContentType.create("application/pdf"), file.getName());

						break;
					case "jpg":
					case "png":
					case "gif":
						builder.addBinaryBody("attachments", new FileInputStream(file), ContentType.create("image/png"),
								file.getName());

						break;

					default:
						break;
					}

				}
			}

			HttpEntity multipart = builder.build();
			uploadFile.setEntity(multipart);
			CloseableHttpResponse response = httpClient.execute(uploadFile);
			int statusCode = response.getStatusLine().getStatusCode();

			System.out.println("POST Response Status: " + statusCode);

			HttpEntity responseEntity = response.getEntity();

			BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));

			String inputLine;
			StringBuffer res = new StringBuffer();

			while ((inputLine = reader.readLine()) != null) {
				res.append(inputLine);
			}
			reader.close();
			System.out.println(res.toString());
			// responseRes = res.toString();

			httpClient.close();

			JSONObject jsonPart = new JSONObject(res.toString());
			if (statusCode == 200) {
				responseRes = "OK:" + jsonPart.getString("messageId");
			} else {
				JSONObject errorObj = jsonPart.getJSONObject("error");
				responseRes = errorObj.getString("message");
			}
		} catch (ClientProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR: error " + e.getMessage();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR: error " + e.getMessage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR: error " + e.getMessage();
		}

		return responseRes;

	}

	public String ReSendFax(String faxRefId) {

		String responseRes = "";

		try {

			System.out.println("Re-Sending mFax....");

			String endPoint = mFaxEndPoint + "/v1/fax/resend";

			CloseableHttpClient httpClient = HttpClients.createDefault();

			final HttpUriRequest postRequest = RequestBuilder.post().setUri(new URI(endPoint))
					.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + this.apiKey).addParameter("messageId", faxRefId)
					.build();

			// CloseableHttpClient httpClient = HttpClients.createDefault();
			// HttpPost httpPost = new HttpPost(endPoint);

			// httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + this.apiKey);

			// MultipartEntityBuilder builder = MultipartEntityBuilder.create();

			// builder.addTextBody("recipientFax", recipientFaxNo, ContentType.TEXT_PLAIN);
			// builder.addTextBody("messageId", faxRefId, ContentType.TEXT_PLAIN);

			// List<NameValuePair> urlParameters = new ArrayList<>();

			// String recFaxNo=recipientFaxNo.length()==10 ? "1"+recipientFaxNo :
			// recipientFaxNo;
			// urlParameters.add(new BasicNameValuePair("recipientFax", recFaxNo));
			// urlParameters.add(new BasicNameValuePair("messageId", faxRefId));

			// httpPost.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));

			// HttpEntity multipart = builder.build();
			// uploadFile.setEntity(multipart);

			CloseableHttpResponse response = httpClient.execute(postRequest);
			int statusCode = response.getStatusLine().getStatusCode();

			System.out.println("POST Response Status: " + statusCode);

			HttpEntity responseEntity = response.getEntity();

			BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));

			String inputLine;
			StringBuffer res = new StringBuffer();

			while ((inputLine = reader.readLine()) != null) {
				res.append(inputLine);
			}
			reader.close();
			System.out.println(res.toString());
			// responseRes = res.toString();

			httpClient.close();

			JSONObject jsonPart = new JSONObject(res.toString());
			if (statusCode == 200) {

				JSONArray jsonArraySuccess = jsonPart.getJSONArray("success");
				JSONArray jsonArrayErrors = jsonPart.getJSONArray("errors");

				if (jsonArraySuccess.length() > 0) {
					// {"success":[{"messageId":"dfd-dfdf-dfd-df-df","pagesComplete":0,"isArchived":false,"isFilePurged":false,"status":"processing","faxNumber":"+45455454545","faxCsid":"455454545","faxCallerId":"45545454","direction":"outbound","accountId":"dfd-e4e9-dfd-99f8-dfd","channelType":"api","country":"US","pagesCount":2,"createdAt":"2020-06-26T07:50:55.568Z"}],"errors":[]}

					responseRes = "OK:" + jsonArraySuccess.getJSONObject(0).getString("messageId");
				}

				else if (jsonArrayErrors.length() > 0) {
					// {"success":[],"errors":[{"input":{"faxNumber":"+12122112211"},"error":"NotAllowedError:
					// International fax was disabled for account"}]}
					responseRes = jsonArrayErrors.getJSONObject(0).getString("error");

				}

			} else {
				JSONObject errorObj = jsonPart.getJSONObject("error");
				responseRes = errorObj.getString("message");
			}
		} catch (ClientProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR: error " + e.getMessage();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR: error " + e.getMessage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR: error " + e.getMessage();
		}

		return responseRes;

	}

	/**
	 * 
	 * @param faxRefId
	 * @return
	 */
	public String GetFaxstatus(String faxRefId) {

		String responseRes = "";

		try {

			System.out.println("Getting Fax Info ....");
			// String faxNo = "14144349667";
			String endPoint = mFaxEndPoint + "/v1/fax/" + faxRefId + "/info";

			CloseableHttpClient httpClient = HttpClients.createDefault();

			final HttpUriRequest getRequest = RequestBuilder.get().setUri(new URI(endPoint))
					.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + this.apiKey).build();

			// CloseableHttpClient httpClient = HttpClients.createDefault();
			// HttpGet httpGet = new HttpGet(endPoint);

			// httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + this.apiKey);

			CloseableHttpResponse response = httpClient.execute(getRequest);
			int statusCode = response.getStatusLine().getStatusCode();

			System.out.println("Getting Fax Info Response Status: " + statusCode);

			HttpEntity responseEntity = response.getEntity();

			BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));

			String inputLine;
			StringBuffer res = new StringBuffer();

			while ((inputLine = reader.readLine()) != null) {
				res.append(inputLine);
			}
			reader.close();
			System.out.println(res.toString());
			// responseRes = res.toString();

			httpClient.close();

			JSONObject jsonPart = new JSONObject(res.toString());
			if (statusCode == 200) {
				responseRes = jsonPart.getString("status") + "~"
						+ (jsonPart.getString("resultInfo") == "null" ? "" : jsonPart.getString("resultInfo"));
				// errorInfo
			} else {
				JSONObject errorObj = jsonPart.getJSONObject("error");
				responseRes = "ERROR~" + errorObj.getString("message");
			}
		} catch (ClientProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR~" + e.getMessage();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR~" + e.getMessage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR~" + e.getMessage();
		}

		return responseRes;
	}

	public List<FaxReceivedObject> GetInboudnFaxList() {

		List<FaxReceivedObject> lstFax = new ArrayList<>();

		try {

			System.out.println("Getting Fax History ....");
			// String faxNo = "14144349667";
			String endPoint = mFaxEndPoint + "/v1/fax/history";

			CloseableHttpClient httpClient = HttpClients.createDefault();
			final HttpUriRequest getRequest = RequestBuilder.get().setUri(new URI(endPoint))
					.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + this.apiKey)
					.addParameter("accountId", this.accountId).addParameter("direction", "inbound")
					.addParameter("order", "created_at ASC").build();
			/*
			 * 
			 * // settimg parameters ArrayList<NameValuePair> params = new
			 * ArrayList<NameValuePair>(); NameValuePair nvDirection = new
			 * BasicNameValuePair("direction", "inbound"); params.add(nvDirection);
			 * 
			 * NameValuePair nvAccountId = new BasicNameValuePair("accountId",
			 * this.accountId); params.add(nvAccountId);
			 * 
			 * NameValuePair nvOrderBy = new BasicNameValuePair("order", "created_at ASC");
			 * params.add(nvOrderBy);
			 * 
			 * 
			 * NameValuePair[] nvPairArray = new NameValuePair[params.size()];
			 * params.toArray(nvPairArray);
			 * 
			 * CloseableHttpClient httpClient = HttpClients.createDefault();
			 * 
			 * final HttpUriRequest getRequest = RequestBuilder.get().setUri(new
			 * URI(endPoint)) .addHeader(HttpHeaders.AUTHORIZATION, "Basic " +
			 * this.apiKey).addParameters(nvPairArray).build();
			 * 
			 */
			CloseableHttpResponse response = httpClient.execute(getRequest);
			int statusCode = response.getStatusLine().getStatusCode();

			System.out.println("Getting Fax Info Response Status: " + statusCode);

			HttpEntity responseEntity = response.getEntity();

			BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));

			String inputLine;
			StringBuffer res = new StringBuffer();

			while ((inputLine = reader.readLine()) != null) {
				res.append(inputLine);
			}
			reader.close();
			System.out.println(res.toString());
			// responseRes = res.toString();

			httpClient.close();

			JSONObject jsonPart = new JSONObject(res.toString());
			if (statusCode == 200) {

				JSONArray jsonArray = jsonPart.getJSONArray("rows");

				if (jsonArray.length() > 0) {

					for (int i = 0; i < jsonArray.length(); i++) {

						JSONObject objFax = jsonArray.getJSONObject(i);

						FaxReceivedObject faxAgeFileReceivedFax = new FaxReceivedObject();

						faxAgeFileReceivedFax.setRecvid(objFax.getString("messageId"));
						// faxAgeFileReceivedFax.setRecvdate(objFax.getString("createdAt"));
						if (objFax.getString("faxCsid") != "null") {
							faxAgeFileReceivedFax.setCSID(objFax.getString("faxCsid"));
						}

						String recDate = DateTimeUtil.formatDateString(objFax.getString("createdAt"),
								DateFormatEnum.yyyyMMddTHHmmssSSSZ, DateFormatEnum.yyyy_MM_dd_HH_mm_ss_SSS);
						faxAgeFileReceivedFax.setRecvdate(recDate);

						System.out.println(objFax.getString("createdAt") + "===" + faxAgeFileReceivedFax.getRecvdate());
						lstFax.add(faxAgeFileReceivedFax);

					}

				}

			} else {
				JSONObject errorObj = jsonPart.getJSONObject("error");
				// responseRes = "ERROR~" + errorObj.getString("message");
				System.out.println("Getting Fax History ERROR ... " + errorObj.getString("message"));
			}
		} catch (ClientProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();
			// responseRes = "ERROR~" + e.getMessage();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			// responseRes = "ERROR~" + e.getMessage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// responseRes = "ERROR~" + e.getMessage();
		}

		return lstFax;
	}

	public UploadFile downloadFax(String faxRefId, String practice_id, String Directory) {

		UploadFile uploadFile = null;
		String faxfileURL = "";
		Double fileSizeKB = (double) 0;
		try {
			// String faxMessageId = "5929472a-b6ec-4e9f-bf57-c8d45f178c8d";
			System.out.println("Downloading mFax... " + faxRefId);
			String endPoint = mFaxEndPoint + "/v1/fax/" + faxRefId + "/download";

			CloseableHttpClient httpClient = HttpClients.createDefault();

			final HttpUriRequest getRequest = RequestBuilder.get().setUri(new URI(endPoint))
					.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + this.apiKey).build();

			// int statusCode = response.getStatusLine().getStatusCode();

			// CloseableHttpClient client = HttpClients.createDefault();

			// HttpGet request = new HttpGet(mFaxEndPoint + "/v1/fax/" + faxRefId +
			// "/download");
			// request.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + this.apiKey);

			// HttpResponse response = client.execute(request);
			CloseableHttpResponse response = httpClient.execute(getRequest);
			HttpEntity entity = response.getEntity();
			int responseCode = response.getStatusLine().getStatusCode();

			if (responseCode == 200) {

				String filename = faxRefId + ".pdf";
				String filePath = FileUtil.GetYearMonthDayWisePath(Directory, practice_id, "Fax\\FaxRecived") + "\\"
						+ filename;
				System.out.println("path = " + filePath);

				String[] splitedPath = filePath.split("\\\\");
				faxfileURL = splitedPath[splitedPath.length - 4] + "\\" + splitedPath[splitedPath.length - 3] + "\\"
						+ splitedPath[splitedPath.length - 2] + "\\" + filename;

				// System.out.println("Request Url: " + request.getURI());
				// System.out.println("Response Code: " + responseCode);

				InputStream is = entity.getContent();

				// String directory = "E:\\FaxTestFiles\\downloaded";
				// FileUtil.CheckPath(directory);
				// String filePath = directory + "\\" + faxRefId + ".pdf";
				File outFile = new File(filePath);
				FileOutputStream fos = new FileOutputStream(outFile);

				int inByte;
				while ((inByte = is.read()) != -1) {
					fos.write(inByte);
				}

				is.close();
				fos.close();

				System.out.println("File Download Completed!!!");
				// responseRes = "File Download Completed!!!";

				// responseRes="OK~"+faxfileURL;

				File destinationFile = new File(filePath);
				if (destinationFile.exists()) {
					fileSizeKB = (double) (destinationFile.length() / 1024);
				}
				uploadFile = new UploadFile(filename, faxfileURL, fileSizeKB);

			}

			/*
			 * else { HttpEntity responseEntity = response.getEntity();
			 * 
			 * BufferedReader reader = new BufferedReader(new
			 * InputStreamReader(responseEntity.getContent()));
			 * 
			 * String inputLine; StringBuffer res = new StringBuffer();
			 * 
			 * while ((inputLine = reader.readLine()) != null) { res.append(inputLine); }
			 * reader.close(); //System.out.println(res.toString()); //responseRes =
			 * res.toString(); JSONObject jsonPart = new JSONObject(res.toString());
			 * JSONObject errorObj = jsonPart.getJSONObject("error"); //responseRes =
			 * "ERROR~" + errorObj.getString("message");
			 * 
			 * }
			 */
			httpClient.close();

		} catch (ClientProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();
			// responseRes = "ERROR~" + e.getMessage();

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			// responseRes = "ERROR~" + e.getMessage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// responseRes = "ERROR~" + e.getMessage();
		}

		return uploadFile;
	}

	public String MarkFaxAsArchived(String faxRefId) {

		String responseRes = "";

		try {

			System.out.println("Archiving mFax....");

			String endPoint = mFaxEndPoint + "/v1/fax/" + faxRefId;

			String json = "{\"isArchived\":true}";
			StringEntity entity = new StringEntity(json);

			CloseableHttpClient httpClient = HttpClients.createDefault();

			final HttpUriRequest patchRequest = RequestBuilder.patch().setUri(new URI(endPoint))
					.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + this.apiKey)
					.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()).setEntity(entity)
					.build();

			CloseableHttpResponse response = httpClient.execute(patchRequest);
			int statusCode = response.getStatusLine().getStatusCode();

			System.out.println("Patch Response Status: " + statusCode);

			HttpEntity responseEntity = response.getEntity();

			BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));

			String inputLine;
			StringBuffer res = new StringBuffer();

			while ((inputLine = reader.readLine()) != null) {
				res.append(inputLine);
			}
			reader.close();
			System.out.println(res.toString());
			// responseRes = res.toString();

			httpClient.close();

			JSONObject jsonPart = new JSONObject(res.toString());
			if (statusCode == 200) {

				if (jsonPart.getBoolean("isArchived")) {
					responseRes = "OK:" + jsonPart.getString("messageId");
				}

			} else {
				JSONObject errorObj = jsonPart.getJSONObject("error");
				responseRes = "ERROR:" + errorObj.getString("message");
			}
		} catch (ClientProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR: error " + e.getMessage();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR: error " + e.getMessage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseRes = "ERROR: error " + e.getMessage();
		}

		return responseRes;

	}

}
