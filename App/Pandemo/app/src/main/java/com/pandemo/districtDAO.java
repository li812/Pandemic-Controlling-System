package com.pandemo;

public class districtDAO {
    String district_id,district,districtadminnm,districtadmindes,districtadmindcontact,districtadmindemail;

    public districtDAO(String district_id, String district, String districtadminnm, String districtadmindes, String districtadmindcontact, String districtadmindemail) {
        this.district_id = district_id;
        this.district = district;
        this.districtadminnm = districtadminnm;
        this.districtadmindes = districtadmindes;
        this.districtadmindcontact = districtadmindcontact;
        this.districtadmindemail = districtadmindemail;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictadminnm() {
        return districtadminnm;
    }

    public void setDistrictadminnm(String districtadminnm) {
        this.districtadminnm = districtadminnm;
    }

    public String getDistrictadmindes() {
        return districtadmindes;
    }

    public void setDistrictadmindes(String districtadmindes) {
        this.districtadmindes = districtadmindes;
    }

    public String getDistrictadmindcontact() {
        return districtadmindcontact;
    }

    public void setDistrictadmindcontact(String districtadmindcontact) {
        this.districtadmindcontact = districtadmindcontact;
    }

    public String getDistrictadmindemail() {
        return districtadmindemail;
    }

    public void setDistrictadmindemail(String districtadmindemail) {
        this.districtadmindemail = districtadmindemail;
    }
}
