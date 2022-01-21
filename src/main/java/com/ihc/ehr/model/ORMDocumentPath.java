package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMDocumentPath {
	 @Id
	    private int id;
	    private String category_name;
	    private String upload_path;
	    private String download_path;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCategory_name() {
			return category_name;
		}
		public void setCategory_name(String category_name) {
			this.category_name = category_name;
		}
		public String getUpload_path() {
			return upload_path;
		}
		public void setUpload_path(String upload_path) {
			this.upload_path = upload_path;
		}
		public String getDownload_path() {
			return download_path;
		}
		public void setDownload_path(String download_path) {
			this.download_path = download_path;
		}
	    
	    
}
