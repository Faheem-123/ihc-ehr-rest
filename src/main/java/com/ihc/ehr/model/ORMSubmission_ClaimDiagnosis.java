package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMSubmission_ClaimDiagnosis  {

    @Id
    private String claim_diagnosis_id;
    private String claim_id;
    private String diag_code;
    private String description;
    private Integer diag_sequence;
    private String poa_indicator;
    private String diagnose_type;

    public String getClaim_diagnosis_id() {
        return claim_diagnosis_id;
    }

    public void setClaim_diagnosis_id(String claim_diagnosis_id) {
        this.claim_diagnosis_id = claim_diagnosis_id;
    }

    public String getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(String claim_id) {
        this.claim_id = claim_id;
    }

    public String getDiag_code() {
        return diag_code;
    }

    public void setDiag_code(String diag_code) {
        this.diag_code = diag_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiag_sequence() {
        return diag_sequence;
    }

    public void setDiag_sequence(Integer diag_sequence) {
        this.diag_sequence = diag_sequence;
    }

    public String getPoa_indicator() {
        return poa_indicator;
    }

    public void setPoa_indicator(String poa_indicator) {
        this.poa_indicator = poa_indicator;
    }

    public String getDiagnose_type() {
        return diagnose_type;
    }

    public void setDiagnose_type(String diagnose_type) {
        this.diagnose_type = diagnose_type;
    }
    
    

}
