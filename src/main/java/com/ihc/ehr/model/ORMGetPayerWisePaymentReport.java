package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ORMGetPayerWisePaymentReport {

	 @Id
	    private String payer_id;
	    private String name;
	    private String paid_amt;
	    private String allowed_amt;
	    private String adjusted_amount;
	    private Boolean is_aggregate;

	    public Boolean getIs_aggregate() {
	        return is_aggregate;
	    }

	    public void setIs_aggregate(Boolean is_aggregate) {
	        this.is_aggregate = is_aggregate;
	    }

	    public String getPayer_id() {
	        return payer_id;
	    }

	    public void setPayer_id(String payer_id) {
	        this.payer_id = payer_id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getPaid_amt() {
	        return paid_amt;
	    }

	    public void setPaid_amt(String paid_amt) {
	        this.paid_amt = paid_amt;
	    }

	    public String getAllowed_amt() {
	        return allowed_amt;
	    }

	    public void setAllowed_amt(String allowed_amt) {
	        this.allowed_amt = allowed_amt;
	    }

	    public String getAdjusted_amount() {
	        return adjusted_amount;
	    }

	    public void setAdjusted_amount(String adjusted_amount) {
	        this.adjusted_amount = adjusted_amount;
	    }
}
