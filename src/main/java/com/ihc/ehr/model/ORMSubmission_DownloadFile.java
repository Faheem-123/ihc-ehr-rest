package com.ihc.ehr.model;


public class ORMSubmission_DownloadFile {

    private byte[] fileData;
    private Boolean noErrors;
    private String FileName;

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Boolean getNoErrors() {
        return noErrors;
    }

    public void setNoErrors(Boolean noErrors) {
        this.noErrors = noErrors;
    }

}
