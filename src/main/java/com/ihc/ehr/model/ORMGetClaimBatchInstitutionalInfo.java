package com.ihc.ehr.model;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Ihsan Ullah
 */
@Entity
public class ORMGetClaimBatchInstitutionalInfo {
    
    @Id
    private String detail_id;
    private String claim_id;
    private String statement_date_from_ccyymmdd;
    private String statement_date_to_ccyymmdd;
    private String admission_date_ccyymmdd;
    private String admission_hour;
    private String admission_type;
    private String admission_source;
    private String discharge_hour;
    private String discharge_status;
    private Boolean accept_assignment;
    private Boolean release_info;
    private Boolean benifits_assignment;
    private Boolean auto_accident;
    private Boolean employment_accident;
    private Boolean other_accident;
    private String accident_state;
    private String pps;
    private String delay_reason_code;
    private Boolean epsd_no_referral_given;
    private Boolean epsd_referral_refused;
    private Boolean epsd_referred_to_other;
    private Boolean epsd_under_treatment;

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getStatement_date_from_ccyymmdd() {
        return statement_date_from_ccyymmdd;
    }

    public void setStatement_date_from_ccyymmdd(String statement_date_from_ccyymmdd) {
        this.statement_date_from_ccyymmdd = statement_date_from_ccyymmdd;
    }

    public String getStatement_date_to_ccyymmdd() {
        return statement_date_to_ccyymmdd;
    }

    public void setStatement_date_to_ccyymmdd(String statement_date_to_ccyymmdd) {
        this.statement_date_to_ccyymmdd = statement_date_to_ccyymmdd;
    }

    public String getAdmission_date_ccyymmdd() {
        return admission_date_ccyymmdd;
    }

    public void setAdmission_date_ccyymmdd(String admission_date_ccyymmdd) {
        this.admission_date_ccyymmdd = admission_date_ccyymmdd;
    }

   

   

    public String getAdmission_hour() {
        return admission_hour;
    }

    public void setAdmission_hour(String admission_hour) {
        this.admission_hour = admission_hour;
    }

    public String getAdmission_type() {
        return admission_type;
    }

    public void setAdmission_type(String admission_type) {
        this.admission_type = admission_type;
    }

    public String getAdmission_source() {
        return admission_source;
    }

    public void setAdmission_source(String admission_source) {
        this.admission_source = admission_source;
    }

    public String getDischarge_hour() {
        return discharge_hour;
    }

    public void setDischarge_hour(String discharge_hour) {
        this.discharge_hour = discharge_hour;
    }

    public String getDischarge_status() {
        return discharge_status;
    }

    public void setDischarge_status(String discharge_status) {
        this.discharge_status = discharge_status;
    }

    public Boolean getAccept_assignment() {
        return accept_assignment;
    }

    public void setAccept_assignment(Boolean accept_assignment) {
        this.accept_assignment = accept_assignment;
    }

    public Boolean getRelease_info() {
        return release_info;
    }

    public void setRelease_info(Boolean release_info) {
        this.release_info = release_info;
    }

    public Boolean getBenifits_assignment() {
        return benifits_assignment;
    }

    public void setBenifits_assignment(Boolean benifits_assignment) {
        this.benifits_assignment = benifits_assignment;
    }

    public Boolean getAuto_accident() {
        return auto_accident;
    }

    public void setAuto_accident(Boolean auto_accident) {
        this.auto_accident = auto_accident;
    }

    public Boolean getEmployment_accident() {
        return employment_accident;
    }

    public void setEmployment_accident(Boolean employment_accident) {
        this.employment_accident = employment_accident;
    }

    public Boolean getOther_accident() {
        return other_accident;
    }

    public void setOther_accident(Boolean other_accident) {
        this.other_accident = other_accident;
    }

    public String getAccident_state() {
        return accident_state;
    }

    public void setAccident_state(String accident_state) {
        this.accident_state = accident_state;
    }

    public String getPps() {
        return pps;
    }

    public void setPps(String pps) {
        this.pps = pps;
    }

    public String getDelay_reason_code() {
        return delay_reason_code;
    }

    public void setDelay_reason_code(String delay_reason_code) {
        this.delay_reason_code = delay_reason_code;
    }

    public Boolean getEpsd_no_referral_given() {
        return epsd_no_referral_given;
    }

    public void setEpsd_no_referral_given(Boolean epsd_no_referral_given) {
        this.epsd_no_referral_given = epsd_no_referral_given;
    }

    public Boolean getEpsd_referral_refused() {
        return epsd_referral_refused;
    }

    public void setEpsd_referral_refused(Boolean epsd_referral_refused) {
        this.epsd_referral_refused = epsd_referral_refused;
    }

    public Boolean getEpsd_referred_to_other() {
        return epsd_referred_to_other;
    }

    public void setEpsd_referred_to_other(Boolean epsd_referred_to_other) {
        this.epsd_referred_to_other = epsd_referred_to_other;
    }

    public Boolean getEpsd_under_treatment() {
        return epsd_under_treatment;
    }

    public void setEpsd_under_treatment(Boolean epsd_under_treatment) {
        this.epsd_under_treatment = epsd_under_treatment;
    }

    public String getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(String claim_id) {
        this.claim_id = claim_id;
    }
    
    
}
