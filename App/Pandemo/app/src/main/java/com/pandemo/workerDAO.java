package com.pandemo;

public class workerDAO {

    String workerid,empcode,location,distict,empname,empphoto,empcontact,empemail,empstatus,empaddress,emptype;

    public workerDAO(String workerid, String empcode, String location, String distict, String empname, String empphoto, String empcontact, String empemail, String empstatus, String empaddress, String emptype) {
        this.workerid = workerid;
        this.empcode = empcode;
        this.location = location;
        this.distict = distict;
        this.empname = empname;
        this.empphoto = empphoto;
        this.empcontact = empcontact;
        this.empemail = empemail;
        this.empstatus = empstatus;
        this.empaddress = empaddress;
        this.emptype = emptype;
    }

    public String getWorkerid() {
        return workerid;
    }

    public void setWorkerid(String workerid) {
        this.workerid = workerid;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistict() {
        return distict;
    }

    public void setDistict(String distict) {
        this.distict = distict;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmpphoto() {
        return empphoto;
    }

    public void setEmpphoto(String empphoto) {
        this.empphoto = empphoto;
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

    public String getEmpstatus() {
        return empstatus;
    }

    public void setEmpstatus(String empstatus) {
        this.empstatus = empstatus;
    }

    public String getEmpaddress() {
        return empaddress;
    }

    public void setEmpaddress(String empaddress) {
        this.empaddress = empaddress;
    }

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }
}
