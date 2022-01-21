package com.ihc.ehr.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim_batch_error")
public class ORMClaim_Batch_Errors implements  Cloneable{
     
     
      public ORMClaim_Batch_Errors(ORMClaim_Batch_Errors obj) throws CloneNotSupportedException {
        ORMClaim_Batch_Errors obj1 = (ORMClaim_Batch_Errors) obj.clone();
        this.patient_name = obj1.patient_name;
        this.dos = obj1.dos;
        this.patient_id = obj1.patient_id;
        this.claim_id = obj1.claim_id;
        this.error_type = obj1.error_type;
        this.ignore_error = obj1.ignore_error;
        this.batch_id = obj1.batch_id;
    }

    public ORMClaim_Batch_Errors() {
    }
    
     @Id
      private String id;
      private String batch_id;
      private String patient_id;
      private String claim_id;
      private String dos;
      private String error;
      private String patient_name;
      private boolean deleted;
      private String date_created;
      private String created_user;
      private String error_type;
      private boolean ignore_error;

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }
      
      

    public String getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(String claim_id) {
        this.claim_id = claim_id;
    }

    public String getCreated_user() {
        return created_user;
    }

    public void setCreated_user(String created_user) {
        this.created_user = created_user;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getError_type() {
        return error_type;
    }

    public void setError_type(String error_type) {
        this.error_type = error_type;
    }

    public boolean getIgnore_error() {
        return ignore_error;
    }

    public void setIgnore_error(boolean ignore_error) {
        this.ignore_error = ignore_error;
    }
      
      
     
}
