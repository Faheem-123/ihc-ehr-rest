package com.ihc.ehr.model;

public class SendFaxAttachmentsFromClient {

	private Long document_id;
    private String document_name;      
    private String document_link;      
    private Long patient_document_id;
    private String document_source;
    private Integer multipart_index;
    private String html_string;
    private Boolean read_only;
    
	public Long getDocument_id() {
		return document_id;
	}
	public void setDocument_id(Long document_id) {
		this.document_id = document_id;
	}
	public String getDocument_name() {
		return document_name;
	}
	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}
	public String getDocument_link() {
		return document_link;
	}
	public void setDocument_link(String document_link) {
		this.document_link = document_link;
	}
	public Long getPatient_document_id() {
		return patient_document_id;
	}
	public void setPatient_document_id(Long patient_document_id) {
		this.patient_document_id = patient_document_id;
	}
	public String getDocument_source() {
		return document_source;
	}
	public void setDocument_source(String document_source) {
		this.document_source = document_source;
	}
	public Integer getMultipart_index() {
		return multipart_index;
	}
	public void setMultipart_index(Integer multipart_index) {
		this.multipart_index = multipart_index;
	}
	public String getHtml_string() {
		return html_string;
	}
	public void setHtml_string(String html_string) {
		this.html_string = html_string;
	}
	public Boolean getRead_only() {
		return read_only;
	}
	public void setRead_only(Boolean read_only) {
		this.read_only = read_only;
	}	
}
