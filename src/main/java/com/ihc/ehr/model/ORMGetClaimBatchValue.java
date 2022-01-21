package com.ihc.ehr.model;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Ihsan Ullah
 */
@Entity
public class ORMGetClaimBatchValue {
    
    @Id
    private String value_code_id;
    private String claim_id;
    private String code;
    private BigDecimal amount;

    public String getValue_code_id() {
        return value_code_id;
    }

    public void setValue_code_id(String value_code_id) {
        this.value_code_id = value_code_id;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }    
}
