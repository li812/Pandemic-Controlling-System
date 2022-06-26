package com.pandemo;

public class shopDAO {
    String medshop_id, shopname, contactno, email, workinghrs;

    public shopDAO(String medshop_id, String shopname, String contactno, String email, String workinghrs) {
        this.medshop_id = medshop_id;
        this.shopname = shopname;
        this.contactno = contactno;
        this.email = email;
        this.workinghrs = workinghrs;
    }

    public String getMedshop_id() {
        return medshop_id;
    }

    public void setMedshop_id(String medshop_id) {
        this.medshop_id = medshop_id;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkinghrs() {
        return workinghrs;
    }

    public void setWorkinghrs(String workinghrs) {
        this.workinghrs = workinghrs;
    }
}
