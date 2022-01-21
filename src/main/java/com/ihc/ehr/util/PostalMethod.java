package com.ihc.ehr.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
public class PostalMethod {

	/*private String USERNAME = "omee.khan06";
	private String PASSWORD = "omer@123";
	private String MyDescription="";
	private String MyFileExtension="html";
	private String MyWorkMode="Development";*/
	private String USERNAME = "krgenesis";
	private String PASSWORD = "nintendo2";
	private String MyDescription="";
	private String MyFileExtension="html";
	private String MyWorkMode="Production";
	private String MyPathToFile = "";
	
	public String SendLetter(String desc,String filePath) {
		
		MyDescription=desc;
		MyPathToFile=filePath;
		String FAX_B64_DATA;
		String sentId="";
		try {
			try (InputStream templateIn = new FileInputStream(MyPathToFile)) {
				System.out.println("coverPagePath:" + MyPathToFile);
				int fileSize = templateIn.available();
				byte[] FAX_DATA = new byte[fileSize];
				templateIn.read(FAX_DATA);
				FAX_B64_DATA = Base64.getEncoder().encodeToString(FAX_DATA);
				templateIn.close();
			}
			
			HttpResponse<String> responseuni = Unirest.post("https://api.postalmethods.com/2009-02-26/PostalWS.asmx")
			  .header("Content-Type", "text/xml")
			  .body("<?xml version=\"1.0\" encoding=\"utf-8\"?>"
			  		+ "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" + 
			  		"  <soap12:Body>" + 
			  		"    <SendLetter xmlns=\"PostalMethods\">" + 
			  		"      <Username>"+USERNAME+"</Username>" + 
			  		"      <Password>"+PASSWORD+"</Password>" + 
			  		"      <MyDescription>"+MyDescription+"</MyDescription>" + 
			  		"      <FileExtension>"+MyFileExtension+"</FileExtension>" + 
			  		"      <FileBinaryData>"+FAX_B64_DATA+"</FileBinaryData>" + 
			  		"      <WorkMode>"+MyWorkMode+"</WorkMode>" + 
			  		"    </SendLetter>" + 
			  		"  </soap12:Body>" + 
			  		"</soap12:Envelope>")
			  .asString();
			 DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
			 DocumentBuilder db = dbf.newDocumentBuilder();
			 InputSource is = new InputSource(new StringReader(responseuni.getBody()));
			    
			 	Document xmlDoc =  db.parse(is);
			    NodeList nodeList = xmlDoc.getElementsByTagName("SendLetterResult");
			    List<String> ids = new ArrayList<String>(nodeList.getLength());
			    for(int i=0;i<nodeList.getLength(); i++) {
			        Node x = nodeList.item(i);
			        ids.add(x.getFirstChild().getNodeValue());             
			        System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
			        sentId=nodeList.item(i).getFirstChild().getNodeValue();
			    }
		} catch (Exception e) {
			e.printStackTrace();
			sentId ="";
			}
		return sentId;
	}
	
}
