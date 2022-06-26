package com.pandemo;

public class categoryDAO {
    String category_id,categoryname,categorydesc,categorytype,categorystartdt,categorysymtoms,categoryprecotaions,categorystatus;

    public categoryDAO(String category_id, String categoryname, String categorydesc, String categorytype, String categorystartdt, String categorysymtoms, String categoryprecotaions, String categorystatus) {
        this.category_id = category_id;
        this.categoryname = categoryname;
        this.categorydesc = categorydesc;
        this.categorytype = categorytype;
        this.categorystartdt = categorystartdt;
        this.categorysymtoms = categorysymtoms;
        this.categoryprecotaions = categoryprecotaions;
        this.categorystatus = categorystatus;
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

    public String getCategorydesc() {
        return categorydesc;
    }

    public void setCategorydesc(String categorydesc) {
        this.categorydesc = categorydesc;
    }

    public String getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype;
    }

    public String getCategorystartdt() {
        return categorystartdt;
    }

    public void setCategorystartdt(String categorystartdt) {
        this.categorystartdt = categorystartdt;
    }

    public String getCategorysymtoms() {
        return categorysymtoms;
    }

    public void setCategorysymtoms(String categorysymtoms) {
        this.categorysymtoms = categorysymtoms;
    }

    public String getCategoryprecotaions() {
        return categoryprecotaions;
    }

    public void setCategoryprecotaions(String categoryprecotaions) {
        this.categoryprecotaions = categoryprecotaions;
    }

    public String getCategorystatus() {
        return categorystatus;
    }

    public void setCategorystatus(String categorystatus) {
        this.categorystatus = categorystatus;
    }
}
