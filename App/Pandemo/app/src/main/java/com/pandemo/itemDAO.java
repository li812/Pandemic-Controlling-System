package com.pandemo;

public class itemDAO {
    String orderid,date,status,itemid,qty,stock_id,medicinename,medtype,medprice,medqty;

    public itemDAO(String orderid, String date, String status, String itemid, String qty, String stock_id, String medicinename, String medtype, String medprice, String medqty) {
        this.orderid = orderid;
        this.date = date;
        this.status = status;
        this.itemid = itemid;
        this.qty = qty;
        this.stock_id = stock_id;
        this.medicinename = medicinename;
        this.medtype = medtype;
        this.medprice = medprice;
        this.medqty = medqty;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public String getMedtype() {
        return medtype;
    }

    public void setMedtype(String medtype) {
        this.medtype = medtype;
    }

    public String getMedprice() {
        return medprice;
    }

    public void setMedprice(String medprice) {
        this.medprice = medprice;
    }

    public String getMedqty() {
        return medqty;
    }

    public void setMedqty(String medqty) {
        this.medqty = medqty;
    }
}
