package com.pandemo;

public class medicineDAO {
    String stock_id,medicinename,medtype,medprice,medqty,meddec,medshop_id;

    public medicineDAO(String stock_id, String medicinename, String medtype, String medprice, String medqty, String meddec, String medshop_id) {
        this.stock_id = stock_id;
        this.medicinename = medicinename;
        this.medtype = medtype;
        this.medprice = medprice;
        this.medqty = medqty;
        this.meddec = meddec;
        this.medshop_id = medshop_id;
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

    public String getMeddec() {
        return meddec;
    }

    public void setMeddec(String meddec) {
        this.meddec = meddec;
    }

    public String getMedshop_id() {
        return medshop_id;
    }

    public void setMedshop_id(String medshop_id) {
        this.medshop_id = medshop_id;
    }
}
