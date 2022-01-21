package com.ihc.ehr.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class GeneralOperations {

	private static Boolean showLogMsg = true;

	public static void logMsg(String msg) {

		if (showLogMsg)
			System.out.println(msg);
	}

	public static String getAlphaNumericFromMaskedString(String value) {

		String regex = "[^A-Za-z0-9]";

		String res = null;

		if (value != null && value != "") {
			res = value.replaceAll(regex, "");
		}
		return res;
	}

	public static String getNumericFromMaskedString(String value) {

		String regex = "[^0-9]";

		String res = null;

		if (value != null && value != "") {
			res = value.replaceAll(regex, "");
		}
		return res;
	}

	public static boolean isWholeNumber(String s) {

		return StringUtils.isNumeric(s);
	}

	public static void SaveFile(InputStream inputStream, File file) throws IOException {
		OutputStream outputstream = new FileOutputStream(file);

		IOUtils.copy(inputStream, outputstream);
	}

	public static String GetDatetimeFileName() {
		String DATE_FORMAT_NOW = "yyyyMMddHHmmssSSS";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		String fileName = sdf.format(cal.getTime());
		return fileName;
	}

	public static String DateFormatYYYYMMDD(String strDate) {
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dateRec;
		try {
			dateRec = df.parse(strDate);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return "";
		}
		String FormatedDate = sdf.format(dateRec);
		return FormatedDate;
	}

	public static String GetMonthYearDayWisePath(String directory) {
		String docPath = "";
		if (directory.equals("") == false) {
			docPath = directory + "\\" + getCurrentYear() + "\\" + getCurrentMonth() + "\\" + getCurrentDay();
			java.io.File chkFolder = new java.io.File(docPath);
			if (!chkFolder.exists()) {
				(new java.io.File(docPath)).mkdirs();
			}
		}
		return docPath;
	}

	public static String CheckPath(String path) {
		java.io.File chkFolder = new java.io.File(path);
		if (!chkFolder.exists()) {
			(new java.io.File(path)).mkdirs();
		}
		return path;
	}

	public static String getCurrentYear() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		// get current date time with Date()
		Date date = new Date();
		// System.out.println(dateFormat.format(date));
		return (String) dateFormat.format(date);
	}

	public static String getCurrentMonth() {
		DateFormat dateFormat = new SimpleDateFormat("MM");
		// get current date time with Date()
		Date date = new Date();
		// System.out.println(dateFormat.format(date));
		return (String) dateFormat.format(date);
	}

	public static String getCurrentDay() {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		// get current date time with Date()
		Date date = new Date();
		// System.out.println(dateFormat.format(date));
		return (String) dateFormat.format(date);
	}

	public static String checkPathYearMonthWise(String PracticeID, String Path, String docCategory) {
		String docPath = "";
		if (Path.equals("") == false) {
			docPath = Path + PracticeID + "\\" + docCategory + "\\" + getCurrentYear() + "\\" + getCurrentMonth() + "\\"
					+ getCurrentDay();
			java.io.File chkFolder = new java.io.File(docPath);
			if (!chkFolder.exists()) {
				(new java.io.File(docPath)).mkdirs();
			}
		}

		String[] splitedPath = docPath.split("\\\\");
		System.out.println("splitedPath length " + splitedPath.length);
		System.out.println("splitedPath " + splitedPath);
		// directory=splitedPath[splitedPath.length - 4] + "\\"
		// +splitedPath[splitedPath.length - 3] + "\\" +splitedPath[splitedPath.length -
		// 2] + "\\" +splitedPath[splitedPath.length - 1];
		return splitedPath[splitedPath.length - 3] + "\\" + splitedPath[splitedPath.length - 2] + "\\"
				+ splitedPath[splitedPath.length - 1];
		// System.out.println("directory End:-"+directory);
		// return docPath;
	}

	public static String encodeBas64(String str) {

		byte[] bytesEncoded = Base64.getEncoder().encode(str.getBytes());
		System.out.println("encoded value is " + new String(bytesEncoded));
		return new String(bytesEncoded);
	}

	public static String dencodeBas64(String encodedString) {

		byte[] valueDecoded = Base64.getDecoder().decode(encodedString.getBytes());
		System.out.println("Decoded value is " + new String(valueDecoded));
		return new String(valueDecoded);
	}

	public static Boolean isNotNullorEmpty(Object obj) {
		if (obj != null && !obj.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean isNullorEmpty(Object obj) {
		if (obj == null || obj.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static String CurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// get current date time with Date()
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return (String) dateFormat.format(date);
	}

	public static boolean isAlphaNumericWithSpace(String s) {
		String pattern = "^[a-zA-Z0-9\\s]*$";
		return s.matches(pattern);
	}

	public static String getNumericOnly(String strAlphaNumeric) {
		return strAlphaNumeric.replaceAll("[^0-9]", "");
	}

	public static String getNumbersOnlyFromString(String strSource) {
		String res = "";
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(strSource);
		while (m.find()) {
			res += m.group();
		}
		return res;
	}

	// http://hl7.org/fhir/ValueSet-administrative-gender.html
	public static boolean isValidGenderCode(String gender) {
		// male,female,other,unknown

		Boolean isValidGender = false;

		switch (gender) {
		case "male":
		case "female":
		case "other":
		case "unknown":
			isValidGender = true;
			break;

		default:
			break;
		}

		return isValidGender;

	}
	
	/**
	 * Add Float to Two Decimal Points
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static float addFloatNumbers(float f1,float f2) {
		
		float y1 = Float.sum(f1, f2);		
		DecimalFormat df = new DecimalFormat("#.00");  
		return  Float.valueOf(df.format(y1));
	}
}
