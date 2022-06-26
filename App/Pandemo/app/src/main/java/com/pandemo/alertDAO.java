package com.pandemo;

public class alertDAO {
    String alertsid,alerttype,alertinstrction,postdt,startdt,alertstatus,category_id,distict_id,category;

    public alertDAO(String alertsid, String alerttype, String alertinstrction, String postdt, String startdt, String alertstatus, String category_id, String distict_id, String category) {
        this.alertsid = alertsid;
        this.alerttype = alerttype;
        this.alertinstrction = alertinstrction;
        this.postdt = postdt;
        this.startdt = startdt;
        this.alertstatus = alertstatus;
        this.category_id = category_id;
        this.distict_id = distict_id;
        this.category = category;
    }

    public String getAlertsid() {
        return alertsid;
    }

    public void setAlertsid(String alertsid) {
        this.alertsid = alertsid;
    }

    public String getAlerttype() {
        return alerttype;
    }

    public void setAlerttype(String alerttype) {
        this.alerttype = alerttype;
    }

    public String getAlertinstrction() {
        return alertinstrction;
    }

    public void setAlertinstrction(String alertinstrction) {
        this.alertinstrction = alertinstrction;
    }

    public String getPostdt() {
        return postdt;
    }

    public void setPostdt(String postdt) {
        this.postdt = postdt;
    }

    public String getStartdt() {
        return startdt;
    }

    public void setStartdt(String startdt) {
        this.startdt = startdt;
    }

    public String getAlertstatus() {
        return alertstatus;
    }

    public void setAlertstatus(String alertstatus) {
        this.alertstatus = alertstatus;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getDistict_id() {
        return distict_id;
    }

    public void setDistict_id(String distict_id) {
        this.distict_id = distict_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
