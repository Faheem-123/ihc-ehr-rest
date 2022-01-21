package com.ihc.ehr.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.ihc.ehr.model.SendFaxAttachments;
import com.ihc.ehr.model.UploadFile;

public class FaxAge implements X509TrustManager {

	private String HOST = "api.faxage.com";
	private String FAXAGE_URL = "https://" + HOST + "/httpsfax.php";
	private String USERNAME = "";
	private String PASSWORD = "";

	// private String FAX_NAME = "t.pdf"; // MUST end with a case non-sensitive,
	// supported file extension (e.g. "pdf")
	// private File TEMPLATE_FILE = new File(FAX_NAME);
	// private File TEMPLATE_FILE1 = new File("test.pdf");
	private String COMPANY = "";
	// private String RECIPIENT_NAME = "test";
	// private String FAX_NUMBER = "4149310590";// "4144486848";
	private String OPERATION = "sendfax";// "listfax";

	private FaxAge() {
	}

	public FaxAge(String userName, String password, String companyId) {
		// GetFaxConfig(practice_id);
		this.USERNAME = userName;
		this.PASSWORD = password;
		this.COMPANY = companyId;
	}

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	public InputStream PreparePostCall(String Operation) {
		try {
			// Create an SSL connection
			URL url = new URL(FAXAGE_URL);

			SSLContext sslContext = SSLContext.getInstance("SSLv3");
			TrustManager[] trustManager = { new FaxAge() };
			sslContext.init(null, trustManager, null);
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			connection.connect();

			// Send the encoded message
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());

			// Encode the HOST
			out.writeBytes("host=");
			out.writeBytes(HOST);

			// Encode the USER_NAME
			out.writeBytes("&username=");
			out.writeBytes(USERNAME);

			// Encode the COMPANY
			out.writeBytes("&company=");
			out.writeBytes(COMPANY);

			// Encode the PASSWORD
			out.writeBytes("&password=");
			out.writeBytes(PASSWORD);

			// Encode the OPERATION to get
			out.writeBytes("&operation=");
			out.writeBytes(Operation);

			out.writeBytes("&unhandled=");
			out.writeBytes("1");

			out.flush();
			out.close();
			return connection.getInputStream();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public String SendFax(Long practice_id, String recipientFaxNo, String recipientName, String client_fax_no,
			String senderName, String senderCompanyName, String faxSubject, String faxNotes, String coverPagePath,
			List<SendFaxAttachments> lstAttachments) {
		String fax_response = "";

		// String SENDER_NAME = "Testing 123"; // Appears in the header of each fax page
		// String SENDER_PHONE = "1.303.555.1212"; // Appears in the header of each fax
		// page; 14 characters max
		try {
			// Create an SSL connection
			URL url = new URL(FAXAGE_URL);

			SSLContext sslContext = SSLContext.getInstance("SSLv3");
			TrustManager[] trustManager = { new FaxAge() };
			sslContext.init(null, trustManager, null);
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			connection.connect();

			// Send the encoded message
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());

			// Encode the HOST
			out.writeBytes("host=");
			out.writeBytes(HOST);

			// Encode the USER_NAME
			out.writeBytes("&username=");
			out.writeBytes(USERNAME);

			// Encode the COMPANY
			out.writeBytes("&company=");
			out.writeBytes(COMPANY);

			// Encode the PASSWORD
			out.writeBytes("&password=");
			out.writeBytes(PASSWORD);

			// Encode the FAX_NUMBER
			out.writeBytes("&faxno=");
			out.writeBytes(recipientFaxNo);

			// Encode the OPERATION
			out.writeBytes("&operation=");
			out.writeBytes(OPERATION);

			// Encode the RECIPIENT_NAME
			if (GeneralOperations.isNotNullorEmpty(recipientName)) {
				out.writeBytes("&recipname=");
				out.writeBytes(recipientName);
			}

			// for (int i = 0; i < 10; i++) {
			// out.writeBytes("&faxfilenames[]=");
			// out.writeBytes(FAX_NAME); 2d array name
			//
			// // Encode the FAX_DATA
			// out.writeBytes("&faxfiledata[]=");2d array data
			// out.writeBytes(FAX_B64_DATA); // base64-encoded contents of the file to be
			// faxed.
			// }

			String FAX_B64_DATA;
			int file_count = 0;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			List<String> fileLengths = new ArrayList<String>(lstAttachments.size());
			List<String> AllFileExt = new ArrayList<String>();
			String FileExt = "";
			try {
				if (GeneralOperations.isNotNullorEmpty(coverPagePath)) {

					try (InputStream templateIn = new FileInputStream(coverPagePath)) {
						System.out.println("coverPagePath:" + coverPagePath);
						int fileSize = templateIn.available();
						byte[] FAX_DATA = new byte[fileSize];
						templateIn.read(FAX_DATA);
						// sun.misc.BASE64Encoder coder = new sun.misc.BASE64Encoder();
						// FAX_B64_DATA = coder.encode(FAX_DATA);
						FAX_B64_DATA = Base64.getEncoder().encodeToString(FAX_DATA);
						templateIn.close();
					}

					// String cover_path = coverPagePath;
					// //System.out.println("File"+cover_path);
					// byte[] coverFileBytes = transformToBytes(cover_path);
					// os.write( coverFileBytes );
					// fileLengths.add(Integer.toString(coverFileBytes.length));
					// FileExt= cover_path.substring(cover_path.lastIndexOf(".") + 1,
					// cover_path.length());
					// AllFileExt.add(FileExt);

					// sun.misc.BASE64Encoder coder = new sun.misc.BASE64Encoder();
					// FAX_B64_DATA = coder.encode(coverFileBytes);
					// Encode the FILE_NAME
					out.writeBytes("&faxfilenames[" + file_count + "]=");
					out.writeBytes("CoverPage.pdf");

					// Encode the FAX_DATA
					out.writeBytes("&faxfiledata[" + file_count + "]=");
					out.writeBytes(FAX_B64_DATA); // base64-encoded contents of the file to be faxed.
					file_count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (SendFaxAttachments objAttach : lstAttachments) {
				FAX_B64_DATA = "";
				String file_path = objAttach.getDocument_path();

				byte[] currFileBytes = transformToBytes(file_path);

				os.write(currFileBytes);
				fileLengths.add(Integer.toString(currFileBytes.length));
				FileExt = file_path.substring(file_path.lastIndexOf(".") + 1, file_path.length());
				AllFileExt.add(FileExt);

				FAX_B64_DATA = Base64.getEncoder().encodeToString(currFileBytes);

				// sun.misc.BASE64Encoder coder = new sun.misc.BASE64Encoder();
				// FAX_B64_DATA = coder.encode(currFileBytes);

				// Encode the FILE_NAME
				out.writeBytes("&faxfilenames[" + file_count + "]=");
				out.writeBytes(Integer.toString(file_count) + "." + FileExt);
				// out.writeBytes(filename.getDocument_name());

				// Encode the FAX_DATA
				out.writeBytes("&faxfiledata[" + file_count + "]=");
				out.writeBytes(FAX_B64_DATA); // base64-encoded contents of the file to be faxed.
				file_count++;
			}

			// mulitple files loop
			// Encode the FILE_NAME
			// out.writeBytes("&faxfilenames[1]=");
			// out.writeBytes("test.pdf");
			//
			// // Encode the FAX_DATA
			// out.writeBytes("&faxfiledata[1]=");
			// out.writeBytes(FAX_B64_DATA1); // base64-encoded contents of the file to be
			// faxed.

			// Encode the SENDER_NAME
			out.writeBytes("&tagname=");
			out.writeBytes(senderCompanyName);
			// out.writeBytes(senderName);

			// Encode the SENDER_PHONE
			out.writeBytes("&tagnumber=");
			out.writeBytes(client_fax_no);

			out.flush();
			out.close();

			System.out.println("Response...");

			// Get the response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			String response = "";
			fax_response = "";
			while ((line = in.readLine()) != null) {
				response += line;
				System.out.println(response);
			}
			String[] responselst = response.split(":");
			if (responselst.length > 0) {
				if (responselst[0].toString().toLowerCase().equals("jobid")) {
					fax_response = "OK";
					fax_response += ":" + responselst[1].toString();
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			fax_response = "ERROR: error " + e.getMessage();
		}
		return fax_response;
	}

	public String ResendFax(String client_fax_no, String faxID) {

		String result = "";
		try {
			URL url = new URL(FAXAGE_URL);

			SSLContext sslContext = SSLContext.getInstance("SSLv3");
			TrustManager[] trustManager = { new FaxAge() };
			sslContext.init(null, trustManager, null);
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			connection.connect();

			// Send the encoded message
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());

			// Encode the HOST
			out.writeBytes("host=");
			out.writeBytes(HOST);

			// Encode the USER_NAME
			out.writeBytes("&username=");
			out.writeBytes(USERNAME);

			// Encode the COMPANY
			out.writeBytes("&company=");
			out.writeBytes(COMPANY);

			// Encode the PASSWORD
			out.writeBytes("&password=");
			out.writeBytes(PASSWORD);

			// Encode the OPERATION
			out.writeBytes("&operation=");
			out.writeBytes("resend");

			// Encode the jobid
			out.writeBytes("&jobid=");
			out.writeBytes(faxID);

			out.writeBytes("&tagname=");
			out.writeBytes("Taha Medical Center");

			// Encode the SENDER_PHONE
			out.writeBytes("&tagnumber=");
			out.writeBytes(client_fax_no);

			out.flush();
			out.close();

			System.out.println("Response...");

			// Get the response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			String response = "";
			result = "";
			while ((line = in.readLine()) != null) {
				response += line;
				System.out.println(response);
			}
			String[] responselst = response.split(":");
			if (responselst.length > 0) {
				if (responselst[0].toString().toLowerCase().equals("jobid")) {
					result = "OK";
					result += ":" + responselst[1].toString();
				} else if (responselst[0].toString().toLowerCase().equals("err40")) {
					result = "Error04";
					result += ":" + responselst[1].toString();
					// Send method

				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static byte[] transformToBytes(String aFilename) throws Exception {

		if (null == aFilename) {
			throw new NullPointerException("aFilename is null");
		}
		File theFile = new File(aFilename);
		if (!theFile.isFile()) {
			throw new IllegalArgumentException("Path doesn't represent a file: " + aFilename);
		}
		if (!theFile.exists()) {
			throw new IllegalArgumentException("File not found: " + aFilename);
		}
		ByteArrayOutputStream theRawData = new ByteArrayOutputStream();
		InputStream theIs = new BufferedInputStream(new FileInputStream(theFile));

		byte theBuffer[] = new byte[1024];
		int theBytesRead;

		try {
			while ((theBytesRead = theIs.read(theBuffer)) != -1) {
				if (theBytesRead < 1024) {
					byte theSlice[] = new byte[theBytesRead];
					System.arraycopy(theBuffer, 0, theSlice, 0, theBytesRead);
					theRawData.write(theSlice);
				} else {
					theRawData.write(theBuffer);
				}
			}
		} finally {
			theIs.close();
			theRawData.close();
		}

		return theRawData.toByteArray();
	}

	public String GetFaxstatus(String faxRefId) {
		String status = "";
		try {

			// Create an SSL connection
			URL url = new URL(FAXAGE_URL);

			SSLContext sslContext = SSLContext.getInstance("SSLv3");
			TrustManager[] trustManager = { new FaxAge() };
			sslContext.init(null, trustManager, null);
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			connection.connect();

			// Send the encoded message
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());

			// Encode the HOST
			out.writeBytes("host=");
			out.writeBytes(HOST);

			// Encode the USER_NAME
			out.writeBytes("&username=");
			out.writeBytes(USERNAME);

			// Encode the COMPANY
			out.writeBytes("&company=");
			out.writeBytes(COMPANY);

			// Encode the PASSWORD
			out.writeBytes("&password=");
			out.writeBytes(PASSWORD);

			// Encode the OPERATION
			out.writeBytes("&operation=");
			out.writeBytes("status");

			out.writeBytes("&jobid=");
			out.writeBytes(faxRefId);

			out.flush();
			out.close();

			System.out.println("Response...");

			// Get the response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			String response = "";
			while ((line = in.readLine()) != null) {
				response += line;
				System.out.println(response);
			}
			String[] responselst = response.split("\t");
			if (responselst.length > 4) {
				// if(responselst[4].toString().toLowerCase().equals("jobid"))
				{
					// status=responselst[4].toString().toLowerCase();
					status = responselst[4].toString().toLowerCase() + "~" + responselst[5].toString().toLowerCase();
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public List<FaxReceivedObject> getReceivedFaxList(String clientFaxNumber) {

		List<FaxReceivedObject> lstFax = new ArrayList<>();
		try {

			// Create an SSL connection
			URL url = new URL(FAXAGE_URL);

			SSLContext sslContext = SSLContext.getInstance("SSLv3");
			TrustManager[] trustManager = { new FaxAge() };
			sslContext.init(null, trustManager, null);
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			connection.connect();

			// Send the encoded message
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());

			// Encode the HOST
			out.writeBytes("host=");
			out.writeBytes(HOST);

			// Encode the USER_NAME
			out.writeBytes("&username=");
			out.writeBytes(USERNAME);

			// Encode the COMPANY
			out.writeBytes("&company=");
			out.writeBytes(COMPANY);

			// Encode the PASSWORD
			out.writeBytes("&password=");
			out.writeBytes(PASSWORD);

			// Encode the OPERATION to get
			out.writeBytes("&operation=");
			out.writeBytes("listfax");

			// get specific fax
			out.writeBytes("&didnumber=");
			out.writeBytes(clientFaxNumber);

			out.writeBytes("&unhandled=");
			out.writeBytes("1");

			out.flush();
			out.close();

			System.out.println("FaxAge Fax Receiving Response...");
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(PreparePostCall("listfax")));
			// Get the response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			String allRecResponses = "";
			while ((line = in.readLine()) != null) {
				allRecResponses += line + "\n";
				// System.out.println(response);
			}
			// System.out.println(allRecResponses);
			in.close();

			String[] responselst = allRecResponses.split("\n");

			if (responselst != null && responselst.length > 0) {

				System.out.println("Total Faxes:" + responselst.length);
				System.out.println(allRecResponses);

				for (String resp : responselst) {
					String[] responseSplitted = resp.split("\t");
					if (responseSplitted.length > 1) {

						FaxReceivedObject FaxReceivedObject = new FaxReceivedObject();

						FaxReceivedObject.setRecvid(responseSplitted[0].toString());
						FaxReceivedObject.setRecvdate(responseSplitted[1].toString());
						FaxReceivedObject.setCSID(responseSplitted[2].toString());

						lstFax.add(FaxReceivedObject);

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FaxAge Fax Receiving Error : " + e.getMessage());
		}

		return lstFax;
	}

	public UploadFile downLoadFax(String faxRefId, String practice_id, String Directory) {

		UploadFile uploadFile = null;
		try {

			String FaxfileURL = "";
			Double fileSizeKB = (double) 0;

			// Create an SSL connection
			URL url = new URL(FAXAGE_URL);

			SSLContext sslContext = SSLContext.getInstance("SSLv3");
			TrustManager[] trustManager = { new FaxAge() };
			sslContext.init(null, trustManager, null);
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			connection.connect();

			// Send the encoded message
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());

			// Encode the HOST
			out.writeBytes("host=");
			out.writeBytes(HOST);

			// Encode the USER_NAME
			out.writeBytes("&username=");
			out.writeBytes(USERNAME);

			// Encode the COMPANY
			out.writeBytes("&company=");
			out.writeBytes(COMPANY);

			// Encode the PASSWORD
			out.writeBytes("&password=");
			out.writeBytes(PASSWORD);

			// Encode the OPERATION to get
			out.writeBytes("&operation=");
			out.writeBytes("getfax");

			out.writeBytes("&faxid=");
			out.writeBytes(faxRefId);

			out.flush();
			out.close();

			BufferedInputStream inputFile = null;
			FileOutputStream fout = null;
			String filename = faxRefId + ".pdf";
			String path = FileUtil.GetYearMonthDayWisePath(Directory, practice_id, "Fax\\FaxRecived") + "\\" + filename;
			System.out.println("path = " + path);

			String[] splitedPath = path.split("\\\\");
			FaxfileURL = splitedPath[splitedPath.length - 4] + "\\" + splitedPath[splitedPath.length - 3] + "\\"
					+ splitedPath[splitedPath.length - 2] + "\\" + filename;
			try {

				inputFile = new BufferedInputStream(connection.getInputStream());
				fout = new FileOutputStream(path);

				final byte data[] = new byte[1024];
				int count;
				while ((count = inputFile.read(data, 0, 1024)) != -1) {
					fout.write(data, 0, count);
				}

			} finally {
				if (inputFile != null) {
					inputFile.close();
				}
				if (fout != null) {
					fout.close();
				}
			}

			File destinationFile = new File(path);
			if (destinationFile.exists()) {
				fileSizeKB = (double) (destinationFile.length() / 1024);
			}
			uploadFile = new UploadFile(filename, FaxfileURL, fileSizeKB);

			System.out.println("FaxAge Fax Downloaded Doc URL : " + FaxfileURL);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("FaxAge Fax Download Error : " + e.getMessage());
		}

		return uploadFile;
	}

	public void updateFaxStatusAtServer(String fax_ref_id) {

		try {
			// String FAX_B64_DATA;

			// Create an SSL connection
			URL url = new URL(FAXAGE_URL);

			SSLContext sslContext = SSLContext.getInstance("SSLv3");
			TrustManager[] trustManager = { new FaxAge() };
			sslContext.init(null, trustManager, null);
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			connection.connect();

			// Send the encoded message
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());

			// Encode the HOST
			out.writeBytes("host=");
			out.writeBytes(HOST);

			// Encode the USER_NAME
			out.writeBytes("&username=");
			out.writeBytes(USERNAME);

			// Encode the COMPANY
			out.writeBytes("&company=");
			out.writeBytes(COMPANY);

			// Encode the PASSWORD
			out.writeBytes("&password=");
			out.writeBytes(PASSWORD);

			// Encode the OPERATION
			out.writeBytes("&operation=");
			out.writeBytes("handled");

			out.writeBytes("&recvid=");
			out.writeBytes(fax_ref_id);

			out.writeBytes("&handled=");
			out.writeBytes("1");

			out.flush();
			out.close();

			System.out.println("Response...");

			// Get the response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;

			while ((line = in.readLine()) != null) {
				System.out.print(" ");
				System.out.println(line);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}