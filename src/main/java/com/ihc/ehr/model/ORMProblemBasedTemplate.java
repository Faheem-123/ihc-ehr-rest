package com.ihc.ehr.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chart_template")
public class ORMProblemBasedTemplate {

	 @Id
	    private String template_id;
	    private String name;
	    private String date_created; 
	    private String created_user;
	    private Boolean deleted;
	    private String practice_id;
	    private String modified_user;
	    private String date_modified;
	    private String rfv_txt;
	    private String hpi_txt;
	    private String pmh_txt;
	    private String ros_txt;
	    private String pe_txt;
	    private String notes_txt;

	    public String getTemplate_id() {
	        return template_id;
	    }

	    public void setTemplate_id(String template_id) {
	        this.template_id = template_id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getDate_created() {
	        return date_created;
	    }

	    public void setDate_created(String date_created) {
	        this.date_created = date_created;
	    }

	    public String getCreated_user() {
	        return created_user;
	    }

	    public void setCreated_user(String created_user) {
	        this.created_user = created_user;
	    }

	    public Boolean getDeleted() {
	        return deleted;
	    }

	    public void setDeleted(Boolean deleted) {
	        this.deleted = deleted;
	    }

	    public String getPractice_id() {
	        return practice_id;
	    }

	    public void setPractice_id(String practice_id) {
	        this.practice_id = practice_id;
	    }

	    public String getModified_user() {
	        return modified_user;
	    }

	    public void setModified_user(String modified_user) {
	        this.modified_user = modified_user;
	    }

	    public String getDate_modified() {
	        return date_modified;
	    }

	    public void setDate_modified(String date_modified) {
	        this.date_modified = date_modified;
	    }

	    public String getRfv_txt() {
	        return rfv_txt;
	    }

	    public void setRfv_txt(String rfv_txt) {
	        this.rfv_txt = rfv_txt;
	    }

	    public String getHpi_txt() {
	        return hpi_txt;
	    }

	    public void setHpi_txt(String hpi_txt) {
	        this.hpi_txt = hpi_txt;
	    }

	    public String getPmh_txt() {
	        return pmh_txt;
	    }

	    public void setPmh_txt(String pmh_txt) {
	        this.pmh_txt = pmh_txt;
	    }

	    public String getRos_txt() {
	        return ros_txt;
	    }

	    public void setRos_txt(String ros_txt) {
	        this.ros_txt = ros_txt;
	    }

	    public String getPe_txt() {
	        return pe_txt;
	    }

	    public void setPe_txt(String pe_txt) {
	        this.pe_txt = pe_txt;
	    }

	    public String getNotes_txt() {
	        return notes_txt;
	    }

	    public void setNotes_txt(String notes_txt) {
	        this.notes_txt = notes_txt;
	    }
	    
}
