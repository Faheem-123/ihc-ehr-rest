package com.ihc.ehr.model;



import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORM_restricted_codes {
    @Id
    private String id;
    private String payer_type;
    private String payer_name;
    private String code;
    private String code_type;
    private String payer_id;
    private String err_message;
    private String Payer_ins_type;

    public String getPayer_ins_type() {
        return Payer_ins_type;
    }

    public void setPayer_ins_type(String Payer_ins_type) {
        this.Payer_ins_type = Payer_ins_type;
    }
    
    public String getErr_message() {
        return err_message;
    }

    public void setErr_message(String err_message) {
        this.err_message = err_message;
    }
    

    public String getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(String payer_id) {
        this.payer_id = payer_id;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayer_type() {
        return payer_type;
    }

    public void setPayer_type(String payer_type) {
        this.payer_type = payer_type;
    }

    public String getPayer_name() {
        return payer_name;
    }

    public void setPayer_name(String payer_name) {
        this.payer_name = payer_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode_type() {
        return code_type;
    }

    public void setCode_type(String code_type) {
        this.code_type = code_type;
    }
    
    
}
