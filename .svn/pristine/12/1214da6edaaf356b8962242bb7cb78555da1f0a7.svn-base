package com.ihc.ehr.util;

import com.itextpdf.text.*;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.*;

public class PDFOperations {

	public  String ConvertHtmltoPDF(String HTML, String Path, String File_Name) throws Exception {
        String result = Path + "\\" + File_Name;

        try {
            try {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);

                PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(result));
                document.open();
                document.addAuthor("Author of the Doc");
                document.addCreator("Creator of the Doc");
                document.addSubject("Subject of the Doc");
                document.addCreationDate();
                document.addTitle("");
                HTMLWorker htmlWorker = new HTMLWorker(document);

               
                String MainStr = HTML;


                htmlWorker.parse(new StringReader(MainStr));
              
                document.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
        return result;
    }
	  public Boolean create_pdf_fromHTMLStream(String strHTMLString,String path,String file_name,String FooterText) {
	        
	        String pdf_full_path = "";
	        //String file_path = "";
	        Boolean fileGenerated=false;
	         
	        try {
	            
//	        	FileUtil.UploadStringDataToFile(strHTMLString, path+"123.html");
//	           System.out.println("HTML To PDF:\n"+strHTMLString);
	           System.out.println("HTML To PDF:\n");
		           
	        	
	           pdf_full_path = GeneralOperations.CheckPath(path) + "\\" + file_name;
	           
	            // step 1
	            Document document = new Document(PageSize.A4);
	            
	            document.addAuthor("Instant Healthcare");
	            document.addCreator("Instant Chart");
	            document.addSubject("Referral");
	            document.addCreationDate();
	            document.addTitle("Patient Referral");
	            
	            // step 2
	            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_full_path));
	            
	            // setting footer...
	            TableHeader event = new TableHeader();
	            if(!FooterText.equals(""))
	                writer.setPageEvent(event);
	            event.footer=FooterText;
	            
	            
	            
	            // step 3
	            document.open();
	            // step 4
	            InputStream is = new ByteArrayInputStream(strHTMLString.getBytes());
	            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
	            
	            //step 5
	            document.close();
	              //Page 
//	            PdfReader reader = new PdfReader(is);
//	            // Create a stamper
//	            PdfStamper stamper
//	                = new PdfStamper(reader, new FileOutputStream(pdf_full_path));
//	            // Loop over the pages and add a header to each page
//	            int n = reader.getNumberOfPages();
//	            for (int i = 1; i <= n; i++) {
//	                getHeaderTable(i, n).writeSelectedRows(
//	                        0, -1, 34, 803, stamper.getOverContent(i));
//	            }
//	            // Close the stamper
//	            stamper.close();
	        
	            //String[] splitedPath = pdf_full_path.split("\\\\");
	            //file_path= splitedPath[splitedPath.length - 3] + "\\" +splitedPath[splitedPath.length - 2] + "\\" + file_name;
	            //file_path=  file_name;
	            System.out.println("Fulle Path :"+pdf_full_path);
	            
	            fileGenerated=true;

	        } catch (Exception e) {
	            e.printStackTrace();
	            //file_path="";
	            pdf_full_path="";
	            fileGenerated=false;
	        }
	        return fileGenerated;// file_path;
	    }
	     public static PdfPTable getHeaderTable(int x, int y) {
	        PdfPTable table = new PdfPTable(2);
	        table.setTotalWidth(527);
	        table.setLockedWidth(true);
	        table.getDefaultCell().setFixedHeight(20);
	        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
	        table.addCell("FOOBAR FILMFESTIVAL");
	        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	        table.addCell(String.format("Page %d of %d", x, y));
	        return table;
	    }
	//public void printPageNumber(PdfWriter writer,Document document,String pageNumber){
	//    
	//  PdfContentByte directContent=writer.getDirectContent();
	//  PdfTemplate template=directContent.createTemplate(document.getPageSize().getWidth(),document.getPageSize().getHeight());
	//  template.moveTo(document.left(),document.bottom() - 11);
	//  template.lineTo(document.right(),document.bottom() - 11);
	//  template.stroke();
	//  directContent.addTemplate(template,0,0);
	//  ColumnText.showTextAligned(directContent,Element.ALIGN_CENTER,new Phrase(pageNumber,reportUtils.createDefaultFont(8,Font.NORMAL)),document.getPageSize().getWidth() / 2,document.bottom() - 20,0);
	//}
	    // create pdf from html file
	    public String create_pdf_fromHMTLFile(String htmlPath, String output_directory, String pdf_file_name) {
	        String pdf_path = "";



	        return pdf_path;
	    }
	    
	    static class TableHeader extends PdfPageEventHelper {

	        PdfTemplate total;
	        String header;
	        String footer;

	        public void setFooter(String footer) {
	            this.footer = footer;
	        }
	        public void setHeader(String header) {
	            this.header = header;
	        }
	        
	        @Override
	        public void onOpenDocument(PdfWriter writer, Document document) {
	            total = writer.getDirectContent().createTemplate(30, 16);
	        }

	        @Override
	        public void onEndPage(PdfWriter writer, Document document) {
	            PdfPTable table = new PdfPTable(3);
	            //Rectangle rect = writer.getBoxSize("art");
	            try {
	                
	                // three columns in footer
	                // col1 width 120 characters  -- patient name 
	                // col2 width 24 characters   -- Page %d of
	                // col3 width 2 characters    -- tota no of pages.
	                table.setWidths(new int[]{120, 24, 2});
	                table.setTotalWidth(527);
	                table.setLockedWidth(true);
	                table.getDefaultCell().setFixedHeight(20);
	                table.getDefaultCell().setBorder(Rectangle.TOP);
	                table.addCell(footer);
	                table.getDefaultCell().setHorizontalAlignment(
	                        Element.ALIGN_RIGHT);
	                table.addCell(String.format("Page %d of", writer.getPageNumber()));
	                
	                //System.out.println(String.format("Page %d of", writer.getPageNumber()));
	                
	                PdfPCell cell = new PdfPCell(Image.getInstance(total));
	                cell.setBorder(Rectangle.TOP);
	                table.addCell(cell);
	                 table.writeSelectedRows(0, -1,
	                        34, 30, writer.getDirectContent());
	                 
	            } catch (DocumentException de) {
	                throw new ExceptionConverter(de);
	            }
	        }

	        @Override
	        public void onCloseDocument(PdfWriter writer, Document document) {
//	            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
//	                    new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2, 0);
	            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
	                    new Phrase(String.valueOf(writer.getPageNumber())), 2, 2, 0);
	        }
	    }
}
