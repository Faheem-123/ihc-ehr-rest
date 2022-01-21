package com.ihc.ehr.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ihc.ehr.model.ORMFile_Name;
import com.ihc.ehr.model.UploadFile;

public class FileUtil {

	public static void SaveFile(InputStream inputStream, File file) throws IOException {
		OutputStream outputstream = new FileOutputStream(file);

		IOUtils.copy(inputStream, outputstream);
	}

	public static String GetDatetimeFileName() {
		String DATE_FORMAT_NOW = "yyyyMMddHHmmssSSS";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		String fileName = sdf.format(cal.getTime());// .replaceAll(":", "").replaceAll("-", "").replaceAll(" ", "");
		return fileName;
	}
	public static String getUserFileName(String loginUser) {
		String DATE_FORMAT_NOW = "yyyyMMddHHmmssSSS";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		String fileName = loginUser+sdf.format(cal.getTime());// .replaceAll(":", "").replaceAll("-", "").replaceAll(" ", "");
		return fileName;
    }

	public static String GetYearMonthDayWisePath(String uploadPath, String practiceId, String docCategory) {
		String docPath = "";
		if (uploadPath.equals("") == false) {
			docPath = uploadPath + "\\" + practiceId + "\\" + docCategory + "\\" + DateTimeUtil.getCurrentYear() + "\\"
					+ DateTimeUtil.getCurrentMonth() + "\\" + DateTimeUtil.getCurrentDay();
			java.io.File chkFolder = new java.io.File(docPath);
			if (!chkFolder.exists()) {
				(new java.io.File(docPath)).mkdirs();
			}
		}
		return docPath;
	}

	public static String GetDirectoryPath(String uploadPath, String practiceId, String docCategory) {
		String docPath = "";
		if (uploadPath.equals("") == false) {
			docPath = uploadPath + "\\" + practiceId + "\\" + docCategory ;
			java.io.File chkFolder = new java.io.File(docPath);
			if (!chkFolder.exists()) {
				(new java.io.File(docPath)).mkdirs();
			}
		}
		return docPath;
	}
	
	/*
	 * public static String GetMonthYearDayWisePath(String directory) { String
	 * docPath = ""; if (directory.equals("") == false) { docPath =
	 * directory+"\\" + DateTimeUtil.getCurrentYear()+"
	 * \\"+ DateTimeUtil.getCurrentMonth()+"\\"+ DateTimeUtil.getCurrentDay();
	 * java.io.File chkFolder = new java.io.File(docPath); if (!chkFolder.exists())
	 * { (new java.io.File(docPath)).mkdirs(); } } return docPath; }
	 */

	public static String CheckPath(String path) {
		java.io.File chkFolder = new java.io.File(path);
		if (!chkFolder.exists()) {
			(new java.io.File(path)).mkdirs();
		}
		return path;
	}

	/**
	 * 
	 * @param multipartFile
	 * @param uploadPath
	 * @param practiceId
	 * @param docCategory
	 * @return UploadFile
	 */

	public static UploadFile UploadDocumentYearMonthDayWise(MultipartFile multipartFile, String uploadPath,
			String practiceId, String docCategory, String fileName) {
		UploadFile uploadFile = null;
		String link = "";
		String directory = "";

		directory = GetYearMonthDayWisePath(uploadPath, practiceId, docCategory);
		File destinationFile = new File(GeneralOperations.CheckPath(directory), fileName);

		double fileSizeKB = 0;
		try {
			multipartFile.transferTo(destinationFile);

			if (destinationFile.exists()) {
				fileSizeKB = destinationFile.length() / 1024;
			}

			String[] splitedPath = directory.split("\\\\");
			link = splitedPath[splitedPath.length - 3] + "\\" + splitedPath[splitedPath.length - 2] + "\\"
					+ splitedPath[splitedPath.length - 1] + "\\" + fileName;

			uploadFile = new UploadFile(fileName,link, fileSizeKB);

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uploadFile;

	}

	/**
	 * @param fileString
	 * @param uploadPath
	 * @throws IOException
	 */

	public static UploadFile UploadStringDataToFile(String stringData, String completeUploadPath) throws IOException {

		UploadFile uploadFile = null;
		String link = "";
		double fileSizeKB = 0;

		File file = new File(completeUploadPath);
		String directory = file.getAbsoluteFile().getParent();
		CheckPath(directory);

		BufferedWriter writer = new BufferedWriter(new FileWriter(completeUploadPath, true));
		writer.write(stringData);
		writer.close();

		if (file.exists()) {
			fileSizeKB = file.length() / 1024;
		}

		uploadFile = new UploadFile(file.getName(), link, fileSizeKB);

		return uploadFile;
	}

	/**
	 * @param source_path
	 * @param destination_path
	 * @return
	 */
	public static Boolean CopyFile(String source_path,String destination_path)
    {
        Boolean result=false;
        try
        {
            
            Path source = Paths.get(source_path);
            Path destination = Paths.get(destination_path);
            CheckPath(destination_path);
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            result=true;
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
        
        return result;
    }
    public static String CreateZipWithoutDelete(List<ORMFile_Name> objlist, String ZipPath, String ZipName) {
      int filecount = 0;
      for (ORMFile_Name obj : objlist) {
          int dupfile = 0;

          for (ORMFile_Name obj1 : objlist) {
              filecount++;
              if (obj.getName().equals(obj1.getName())) {
                  dupfile++;
              }
              if (obj.getName().equals(obj1.getName()) && dupfile > 1) {
                  obj.setName("dup_" + filecount + "_" + obj.getName());
              }
          }
      }
      //String strpath = ZipPath + ZipName;
      try {
          byte[] buffer = new byte[1024];
          FileOutputStream fos = new FileOutputStream(ZipPath + ZipName);
          ZipOutputStream zos = new ZipOutputStream(fos);

          for (ORMFile_Name obj : objlist) {
              ZipEntry ze = new ZipEntry(obj.getName());

              zos.putNextEntry(ze);

              FileInputStream in = new FileInputStream(obj.getPath());
              int len;
              while ((len = in.read(buffer)) > 0) {
                  zos.write(buffer, 0, len);
              }

              in.close();
          }
          zos.closeEntry();
          zos.close();
          System.out.println("Done" + ZipName);
      } catch (Exception e) {
          e.printStackTrace();
          return "";

      }
      return ZipName;
  }
  //for  multiple document download once
  public static String CreateZip(ORMFile_Name[] objlist, String ZipPath, String ZipName) {
      try {
          File fin = new File(ZipPath);
          for (File file : fin.listFiles()) {
              file.delete();
          }
          System.out.println("Delete Files");
      } catch (Exception e) {
          System.out.println("Deletion Error");
      }
      int filecount = 0;
      for (ORMFile_Name obj : objlist) {
          int dupfile = 0;

          for (ORMFile_Name obj1 : objlist) {
              filecount++;
              if (obj.getName().equals(obj1.getName())) {
                  dupfile++;
              }
              if (obj.getName().equals(obj1.getName()) && dupfile > 1) {
                  obj.setName("dup_" + filecount + "_" + obj.getName());
              }
          }
      }
      //String strpath = ZipPath + ZipName;
      try {
          byte[] buffer = new byte[1024];
          FileOutputStream fos = new FileOutputStream(ZipPath + ZipName);
          ZipOutputStream zos = new ZipOutputStream(fos);

          for (ORMFile_Name obj : objlist) {
              ZipEntry ze = new ZipEntry(obj.getName());
              zos.putNextEntry(ze);
              FileInputStream in = new FileInputStream(obj.getPath());
              int len;
              while ((len = in.read(buffer)) > 0) {
                  zos.write(buffer, 0, len);
              }
              in.close();
          }
          zos.closeEntry();
          zos.close();
          System.out.println("Done" + ZipName);
      } catch (Exception e) {
          e.printStackTrace();
          return "";

      }
      return ZipName;
  }

}
