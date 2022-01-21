package com.ihc.ehr.util;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.ihc.ehr.dao.GeneralDAOImpl;
import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.ExcelColumn;
import com.ihc.ehr.model.ORMDocumentPath;
import com.ihc.ehr.model.ORMGetEncounterReport;
import com.ihc.ehr.model.Wrapper_ExcelColumn;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
public class ExcelColumnsInfo {
	private DBOperations db;
	private GeneralDAOImpl generalDaoImpl;

	public ExcelColumnsInfo(DBOperations dbOpt, GeneralDAOImpl genImpl) {
		this.db = dbOpt;
		this.generalDaoImpl = genImpl;
	}
	
	public <T> String WriteExcel(List<? extends T> dataList,Wrapper_ExcelColumn excelOptions,String PracticeId) throws Exception  {

		
//		Class<?> collectionClass = list.getClass();
//		Field[] f =collectionClass.getDeclaredFields();
		
		List<String> fields = analyzeFilter(excelOptions.getLst_excel_columns());
		List<List<Object>> values = buildValueList(dataList, fields);
		return createBasicWorkbook(values,excelOptions.getLst_excel_columns(),PracticeId);
//		String excelFileName = "C:/Test.xlsx";//name of excel file
//
//		String sheetName = "Sheet1";//name of sheet
//
//		XSSFWorkbook wb = new XSSFWorkbook();
//		XSSFSheet sheet = wb.createSheet(sheetName) ;
//
//		//iterating r number of rows
//		for (int r=0;r < 5; r++ )
//		{
//			XSSFRow row = sheet.createRow(r);
//
//			//iterating c number of columns
//			for (int c=0;c < 5; c++ )
//			{
//				XSSFCell cell = row.createCell(c);
//	
//				cell.setCellValue("Cell "+r+" "+c);
//			}
//		}
//
//		FileOutputStream fileOut = new FileOutputStream(excelFileName);
//
//		//write this workbook to an Outputstream.
//		wb.write(fileOut);
//		fileOut.flush();
//		fileOut.close();
//	
//	      //Create blank workbook
//	      XSSFWorkbook workbook = new XSSFWorkbook();
////	      
////	      //Create a blank sheet
//	      XSSFSheet spreadsheet = workbook.createSheet( "Employee Info");
////
////	      //Create row object
//	      XSSFRow row;
////
//	     
////	      //This data needs to be written (Object[])
//	      Map < String, Object[] > empinfo = new TreeMap < String, Object[] >();
//	      empinfo.put( "1", new Object[] {
//	         "EMP ID", "EMP NAME", "DESIGNATION" });
//	      
//	      empinfo.put( "2", new Object[] {
//	         "tp01", "Gopal", "Technical Manager" });
//	      
//	      empinfo.put( "3", new Object[] {
//	         "tp02", "Manisha", "Proof Reader" });
//	      
//	      empinfo.put( "4", new Object[] {
//	         "tp03", "Masthan", "Technical Writer" });
//	      
//	      empinfo.put( "5", new Object[] {
//	         "tp04", "Satish", "Technical Writer" });
//	      
//	      empinfo.put( "6", new Object[] {
//	         "tp05", "Krishna", "Technical Writer" });
////
////	      //Iterate over data and write to sheet
//	      Set < String > keyid = empinfo.keySet();
//	      int rowid = 0;
//	      
//	      for (String key : keyid) {
//	         row = spreadsheet.createRow(rowid++);
//	         Object [] objectArr = empinfo.get(key);
//	         int cellid = 0;
//	         
//	         for (Object obj : objectArr){
//	            Cell cell = row.createCell(cellid++);
//	            cell.setCellValue((String)obj);
//	         }
//	      }
////	      //Write the workbook in file system
//	      FileOutputStream out = new FileOutputStream(
//	         new File("C:/Writesheet.xlsx"));
//	      
//	      workbook.write(out);
//	      out.close();
//	      System.out.println("Writesheet.xlsx written successfully");
	   }
	 private  String createBasicWorkbook(List<List<Object>> cellValues,List<ExcelColumn> title,String PracticeId) throws Exception{

		 String DocumentPath = "";
		 XSSFWorkbook workbook = new XSSFWorkbook();
//	      
//	      //Create a blank sheet
	      XSSFSheet sheet = workbook.createSheet( "rptUserReport");
//
//	      //Create row object
	      XSSFRow row;
	      
//		 XSSFWorkbook workbook = new XSSFWorkbook();
//	        Sheet sheet = workbook.createSheet("sheet");
//	        ExcelTemplate template;
	      	
	        
	        CellStyle style = workbook.createCellStyle();            
	        XSSFFont  font = workbook.createFont();//Create font
	        font.setBold(true);//Make font bold
	        style.setFont(font);//set it to bold
	        // generate excel title
	        row = sheet.createRow(0);
	        int a=0;
	        for (ExcelColumn obj:title) 
	        {
	        	Cell cell;	        	
	        	if(obj.getData_type().equals("amount"))
	        	{
	        		cell= row.createCell(a, XSSFCell.CELL_TYPE_NUMERIC);
	        	}
	        	else
	        		cell= row.createCell(a, XSSFCell.CELL_TYPE_STRING);
	        	
	            //template = templateList.get(i);
	            cell.setCellValue(obj.getCol_header());
	            cell.setCellStyle(style);
	            a++;
	        }
	        DataFormat dataFormat = workbook.createDataFormat();
	      	
	      	CellStyle styleTwoDecimalPlaces = workbook.createCellStyle();
	        styleTwoDecimalPlaces.setDataFormat(dataFormat.getFormat("#,##0.00"));
//
	       // DataFormat dataFormat = workbook.createDataFormat();
//
//	        CellStyle stylePercent = workbook.createCellStyle();
//	        stylePercent.setDataFormat(dataFormat.getFormat("0.00%"));
//
//	        CellStyle styleTwoDecimalPlaces = workbook.createCellStyle();
//	        styleTwoDecimalPlaces.setDataFormat(dataFormat.getFormat("#,##0.00"));
//
//	        CellStyle styleThousandPlace = workbook.createCellStyle();
//	        styleThousandPlace.setDataFormat(dataFormat.getFormat("#,##0"));

	        // generate excel date content
	        if (cellValues != null) {
	            for (List<Object> line : cellValues) {
	                row = sheet.createRow(sheet.getLastRowNum() + 1);
	                for (int i = 0; i < line.size(); i++) {
	                    Cell cell = row.createCell(i);
	                    //ExcelTemplate excelTemplate = templateList.get(i);
	                    
	                    Object valueObject = line.get(i);

	                    // Null gets blank, otherwise gets toString() value
	                    Integer cellType=0;
	                    String cellValue;
//	                    if (valueObject == null) {
//	                        //cellType = ExcelCellType.BLANK;
//	                        cellValue = "";
//	                    } else {
//	                       // cellType = excelTemplate.getCellType();
//	                        cellValue = valueObject.toString();
//	                    }
	                    ExcelColumn obj =title.get(i);
	                    
	                    if (valueObject == null) {
	                        cellValue = "";
	                    }
	                    else if(obj.getData_type().equals("amount"))
	                    {
	                    	cell.setCellValue(NumberUtils.toFloat(valueObject.toString()));
	                    	cell.setCellStyle(styleTwoDecimalPlaces);
	                    }
	                    else
	                    {
	                    	 cell.setCellType(cellType);
	                    	 cell.setCellValue(valueObject.toString());
	                    }
//	                    else if(valueObject instanceof Float)
//	                    {
//	                    	cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
//                        	cell.setCellValue(NumberUtils.toFloat(valueObject.toString()));
//                        	cell.setCellStyle(styleTwoDecimalPlaces);
//	                    }
//	                    else if(valueObject instanceof Integer)
//	                    {
//	                    	cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
//                        	cell.setCellValue(NumberUtils.toInt(valueObject.toString()));
//	                    }
//	                    else {
//	                    	 cell.setCellType(cellType);
//	                    	 cell.setCellValue(valueObject.toString());
//                        }
	                     
	                  
	                    // inject value as different type
//	                    if (cellType == ExcelCellType.PERCENTAGE) {
//	                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
//	                        cell.setCellValue(NumberUtils.toFloat(cellValue));
//	                        cell.setCellStyle(stylePercent);
//	                    } else if (cellType == ExcelCellType.TWO_DECIMAL_PLACES) {
//	                        cell.setCellValue(NumberUtils.toFloat(cellValue));
//	                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
//	                        cell.setCellStyle(styleTwoDecimalPlaces);
//	                    } else if (cellType == ExcelCellType.THOUSAND_PLACE) {
//	                        cell.setCellValue(NumberUtils.toFloat(cellValue));
//	                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
//	                        cell.setCellStyle(styleThousandPlace);
//	                    } else if (cellType == ExcelCellType.INT) {
//	                        cell.setCellValue(NumberUtils.toInt(cellValue));
//	                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
//	                        cell.setCellStyle(style);
//	                    } 
//	                    else if (cellType == ExcelCellType.DATE) {
//	                        cell.setCellValue(cellValue);
//	                    }
//	                    else if(cellType == ExcelCellType.BLANK){
//	                        cell.setCellValue(cellValue);
//	                    } 
//	                    else 
	                    {
	                        //cell.setCellStyle(style);
	                       
	                       
	                    }
	                }
	            }
	        }

	        for (int i = 0; i < title.size(); i++) {
	            sheet.autoSizeColumn(i);
	        }

	       // return workbook;
//	        FileOutputStream out = new FileOutputStream(
//	   	         new File("C:/Writesheet.xlsx"));
	   	      
//	   	      workbook.write(out);
//	   	      out.close();
	   	  
	   	      System.out.println("Writesheet.xlsx written successfully");
	   	   try {
				List<ORMDocumentPath> lstPath = generalDaoImpl.getDocPath(PracticeId, "DownloadExcel");
				for (ORMDocumentPath orm : (List<ORMDocumentPath>) lstPath) {
					DocumentPath = orm.getUpload_path() + "\\";
				}

				if (!DocumentPath.equals("")) {
					String Path = GeneralOperations.checkPathYearMonthWise(PracticeId, DocumentPath, "DownloadExcel");
					Path += "\\" + FileUtil.GetDatetimeFileName() + ".xlsx";
					
					FileOutputStream output = null;
					//File file = new File(DocumentPath + PracticeId + "\\DownloadExcel\\" + Path);
					
					output = new FileOutputStream( new File(DocumentPath + PracticeId + "\\DownloadExcel\\" + Path));
					workbook.write(output);
					output.close();
			   	   
					DocumentPath = DocumentPath + PracticeId + "\\DownloadExcel\\" + Path;

					String aa = DocumentPath.substring(DocumentPath.lastIndexOf("DownloadExcel") + 17,DocumentPath.length());
					// System.out.println(aa);
					//insertStatementLog(practiceID, StatementLog.replace("@Path", "'" + Path + "'"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				DocumentPath = "";
			}
	   	   
	   	  return DocumentPath;
	    }
	 
	 		 
	private  <T> List<List<Object>> buildValueList(List<? extends T> dataList, List<String> customFields) throws Exception {
        List<List<Object>> result = new ArrayList<List<Object>>();
        for (T data : dataList) {
            List<Object> values = new ArrayList<Object>();
            for (String customField : customFields) {
                try {
                    //use util to cache reflection
                    Object specificValue = PropertyUtils.getProperty(data, customField);
                    if (specificValue instanceof Date) {
                        Date date = (Date) specificValue;
                        String actualValue = DateFormatUtils.format(date, "yyyy/MM/dd");
                        values.add(actualValue);
                        continue;
                    }
//                    else if (specificValue instanceof Float) {
//                        values.add(NumberUtils.toFloat(specificValue.toString()));
//                        continue;
//                    }
//                    else if (specificValue instanceof Double) {
//                        values.add(NumberUtils.toDouble(specificValue.toString()));
//                        continue;
//                    }
//                    else if (specificValue instanceof Integer) {
//                        values.add(NumberUtils.toInt(specificValue.toString()));
//                        continue;
//                    }
                    values.add(specificValue);
                } catch (Exception e) {
                    //logger.error("could not get bean property - {}", customField);
                    throw e;
                }
            }
            result.add(values);
        }

        return result;
    }
	private  List<String> analyzeFilter(List<ExcelColumn> exColumns) {
//        if (stringutils .isEmpty()(filter)) {
//            throw new IllegalArgumentException("filter can not be null");
//        }

        //String[] fields = filter.split(",");
        List<String> fieldList = new ArrayList<String>();
        for (ExcelColumn field : exColumns) {
            //if (StringUtils.isNotBlank(field) && !fieldList.contains(field)) 
            {
            	if("undefined".equals(field)){
            		continue;
            	}
                fieldList.add(field.getCol_name());
            }
        }

        return fieldList;
    }
	
}
