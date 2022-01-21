package com.ihc.ehr.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	public  static enum DateFormatEnum {

		dd("dd"),
		yyyy_MM_dd("yyyy-MM-dd"),
		MM("MM"),
		yyyy("yyyy"),
	    MM_dd_yyyy("MM/dd/yyyy"),	    
	    MM_dd_yy("MM/dd/yy"),
	    yyyy_MM_dd_HH_mm("yyyy-MM-dd HH:mm"),
	    yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
	    yyyy_MM_dd_HH_mm_ss_SSS("yyyy-MM-dd HH:mm:ss.SSS"),	    
		hh_mm_a("hh:mm a"),
		yyyyMMdd("yyyyMMdd"),
		yyMMdd("yyMMdd"),
		yyyyMMddHHmmssZZZZ("yyyyMMddHHmmssZZZZ"),
		yyyyMMddTHHmmssSSSZ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
		yyyyMMddHHmmZ("yyyyMMddHHmmZ"),// 201601061600+0000 use in qrda
		yyyyMMddHHmm("yyyyMMddHHmm"),
		EEEE("EEEE")// Full Day Name
		,HHmm("HHmm"); 
		

	    private String name;

	    private DateFormatEnum(String stringVal) {
	        name=stringVal;
	    }
	    public String toString(){
	        return name;
	    }

	    public static String getEnumByString(String code){
	        for(DateFormatEnum e : DateFormatEnum.values()){
	            if(code == e.name) return e.name();
	        }
	        return null;
	    }
	}
	
	public static String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat(DateFormatEnum.yyyy_MM_dd_HH_mm_ss.toString());
        //get current date time with Date()
        Date date = new Date();
        //System.out.println(dateFormat.format(date));
        return (String) dateFormat.format(date);
    }
	public static String getFormatedCurrentDate(DateFormatEnum dt) {
        DateFormat dateFormat = new SimpleDateFormat(dt.toString());
        //get current date time with Date()
        Date date = new Date();
       // System.out.println(dateFormat.format(date));
        return (String) dateFormat.format(date);
    }
	public static String getCurrentYear(){
        DateFormat dateFormat = new SimpleDateFormat(DateFormatEnum.yyyy.toString());
        //get current date time with Date()
        Date date = new Date();
       // System.out.println(dateFormat.format(date));
        return (String) dateFormat.format(date);
    }
    
    public static String getCurrentMonth(){
        DateFormat dateFormat = new SimpleDateFormat(DateFormatEnum.MM.toString());
        //get current date time with Date()
        Date date = new Date();
       // System.out.println(dateFormat.format(date));
        return (String) dateFormat.format(date);
    }
    
     public static String getCurrentDay(){
        DateFormat dateFormat = new SimpleDateFormat(DateFormatEnum.dd.toString());
        //get current date time with Date()
        Date date = new Date();
       // System.out.println(dateFormat.format(date));
        return (String) dateFormat.format(date);
    }
     
     public static boolean isDateValid(String dateToValidate, DateFormatEnum dfEnum){

 		if(dateToValidate == null){
 			return false;
 		}

 		SimpleDateFormat sdf = new SimpleDateFormat(dfEnum.toString());
 		sdf.setLenient(false);

 		try {

 			//if not valid, it will throw ParseException
 			Date date = sdf.parse(dateToValidate);
 			//System.out.println(date);

 		} catch (ParseException e) {

 			e.printStackTrace();
 			return false;
 		}

 		return true;
 	}
     
    public static Date GetDateFromString(String strDate,DateFormatEnum dfEnum)
     {
     	Date dt=null;
     	DateFormat df = new SimpleDateFormat(dfEnum.toString());  
     	try {
     		if(strDate!=null && !strDate.isEmpty())
     		{    			
     			dt=df.parse(strDate);
     		}    		
 		} catch (ParseException e) {
 			e.printStackTrace();
 		}
     	return dt;
     }
    
    public static String GetStringFromDate(Date dtDate,DateFormatEnum dfEnum)
    {
    	String strDate ="";
    	DateFormat df = new SimpleDateFormat(dfEnum.toString());  
    	try {
    		if(dtDate!=null)   			
    			strDate=df.format(dtDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return strDate;
    }
    
    public static String formatDateString(String strDate,DateFormatEnum sourceFormatEnum,DateFormatEnum requiredFormatEnum)
    {
    	return GetStringFromDate(GetDateFromString(strDate,sourceFormatEnum),requiredFormatEnum);    	
    }
     
}
