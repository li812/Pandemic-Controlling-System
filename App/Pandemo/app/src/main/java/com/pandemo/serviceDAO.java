package com.pandemo;

public class serviceDAO {
    String serviceid,isolationreason,requirement,regdate,reqdate,requeststatus,district_id,public_id,worker_id,pubname,pubcontact,pubaddress,empname,empcontact,empemail;

    public serviceDAO(String serviceid, String isolationreason, String requirement, String regdate, String reqdate, String requeststatus, String district_id, String public_id, String worker_id, String pubname, String pubcontact, String pubaddress, String empname, String empcontact, String empemail) {
        this.serviceid = serviceid;
        this.isolationreason = isolationreason;
        this.requirement = requirement;
        this.regdate = regdate;
        this.reqdate = reqdate;
        this.requeststatus = requeststatus;
        this.district_id = district_id;
        this.public_id = public_id;
        this.worker_id = worker_id;
        this.pubname = pubname;
        this.pubcontact = pubcontact;
        this.pubaddress = pubaddress;
        this.empname = empname;
        this.empcontact = empcontact;
        this.empemail = empemail;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getIsolationreason() {
        return isolationreason;
    }

    public void setIsolationreason(String isolationreason) {
        this.isolationreason = isolationreason;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getReqdate() {
        return reqdate;
    }

    public void setReqdate(String reqdate) {
        this.reqdate = reqdate;
    }

    public String getRequeststatus() {
        return requeststatus;
    }

    public void setRequeststatus(String requeststatus) {
        this.requeststatus = requeststatus;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getPublic_id() {
        return public_id;
    }

    public void setPublic_id(String public_id) {
        this.public_id = public_id;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
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

    public String getPubaddress() {
        return pubaddress;
    }

    public void setPubaddress(String pubaddress) {
        this.pubaddress = pubaddress;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmpcontact() {
        return empcontact;
    }

    public void setEmpcontact(String empcontact) {
        this.empcontact = empcontact;
    }

    public String getEmpemail() {
        return empemail;
    }

    public void setEmpemail(String empemail) {
        this.empemail = empemail;
    }
}
