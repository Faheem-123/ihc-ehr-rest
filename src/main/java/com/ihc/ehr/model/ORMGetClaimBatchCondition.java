package com.ihc.ehr.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Ihsan Ullah
 */
@Entity
public class ORMGetClaimBatchCondition {
    
     @Id
    private String condition_code_id;
    private String claim_id;
    private String code;

    public String getCondition_code_id() {
        return condition_code_id;
    }

    public void setCondition_code_id(String condition_code_id) {
        this.condition_code_id = condition_code_id;
    }

    public String getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(String claim_id) {
        this.claim_id = claim_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
}
