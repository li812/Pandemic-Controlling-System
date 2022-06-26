package com.pandemo;

public class reqKitDAO {
    String kitid,kitstaus,requestdt,previousrequestdate,workstatus,allotedate,ashaworker_id,public_id,distict_id,category_id,categoryname,empname,empcontact;

    public reqKitDAO(String kitid, String kitstaus, String requestdt, String previousrequestdate, String workstatus, String allotedate, String ashaworker_id, String public_id, String distict_id, String category_id, String categoryname, String empname, String empcontact) {
        this.kitid = kitid;
        this.kitstaus = kitstaus;
        this.requestdt = requestdt;
        this.previousrequestdate = previousrequestdate;
        this.workstatus = workstatus;
        this.allotedate = allotedate;
        this.ashaworker_id = ashaworker_id;
        this.public_id = public_id;
        this.distict_id = distict_id;
        this.category_id = category_id;
        this.categoryname = categoryname;
        this.empname = empname;
        this.empcontact = empcontact;
    }

    public String getKitid() {
        return kitid;
    }

    public void setKitid(String kitid) {
        this.kitid = kitid;
    }

    public String getKitstaus() {
        return kitstaus;
    }

    public void setKitstaus(String kitstaus) {
        this.kitstaus = kitstaus;
    }

    public String getRequestdt() {
        return requestdt;
    }

    public void setRequestdt(String requestdt) {
        this.requestdt = requestdt;
    }

    public String getPreviousrequestdate() {
        return previousrequestdate;
    }

    public void setPreviousrequestdate(String previousrequestdate) {
        this.previousrequestdate = previousrequestdate;
    }

    public String getWorkstatus() {
        return workstatus;
    }

    public void setWorkstatus(String workstatus) {
        this.workstatus = workstatus;
    }

    public String getAllotedate() {
        return allotedate;
    }

    public void setAllotedate(String allotedate) {
        this.allotedate = allotedate;
    }

    public String getAshaworker_id() {
        return ashaworker_id;
    }

    public void setAshaworker_id(String ashaworker_id) {
        this.ashaworker_id = ashaworker_id;
    }

    public String getPublic_id() {
        return public_id;
    }

    public void setPublic_id(String public_id) {
        this.public_id = public_id;
    }

    public String getDistict_id() {
        return distict_id;
    }

    public void setDistict_id(String distict_id) {
        this.distict_id = distict_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
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
}
