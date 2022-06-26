package com.pandemo;

public class docTestCasesDAO {
    String caseid,casestatus,infectionhistory,infectiontype,distict,public_id,pubname,pubcontact,pubemail,kitrequest,categoryname,casedate;

    public docTestCasesDAO(String caseid, String casestatus, String infectionhistory, String infectiontype, String distict, String public_id, String pubname, String pubcontact, String pubemail, String kitrequest, String categoryname, String casedate) {
        this.caseid = caseid;
        this.casestatus = casestatus;
        this.infectionhistory = infectionhistory;
        this.infectiontype = infectiontype;
        this.distict = distict;
        this.public_id = public_id;
        this.pubname = pubname;
        this.pubcontact = pubcontact;
        this.pubemail = pubemail;
        this.kitrequest = kitrequest;
        this.categoryname = categoryname;
        this.casedate = casedate;
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public String getCasestatus() {
        return casestatus;
    }

    public void setCasestatus(String casestatus) {
        this.casestatus = casestatus;
    }

    public String getInfectionhistory() {
        return infectionhistory;
    }

    public void setInfectionhistory(String infectionhistory) {
        this.infectionhistory = infectionhistory;
    }

    public String getInfectiontype() {
        return infectiontype;
    }

    public void setInfectiontype(String infectiontype) {
        this.infectiontype = infectiontype;
    }

    public String getDistict() {
        return distict;
    }

    public void setDistict(String distict) {
        this.distict = distict;
    }

    public String getPublic_id() {
        return public_id;
    }

    public void setPublic_id(String public_id) {
        this.public_id = public_id;
    }

    public String getPubname() {
        return pubname;
    }

    public void setPubname(String pubname) {
        this.pubname = pubname;
    }

    public String getPubcontact() {
        return pubcontact;
    }

    public void setPubcontact(String pubcontact) {
        this.pubcontact = pubcontact;
    }

    public String getPubemail() {
        return pubemail;
    }

    public void setPubemail(String pubemail) {
        this.pubemail = pubemail;
    }

    public String getKitrequest() {
        return kitrequest;
    }

    public void setKitrequest(String kitrequest) {
        this.kitrequest = kitrequest;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCasedate() {
        return casedate;
    }

    public void setCasedate(String casedate) {
        this.casedate = casedate;
    }
}
