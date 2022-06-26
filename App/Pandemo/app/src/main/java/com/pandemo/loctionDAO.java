package com.pandemo;

public class loctionDAO {
    String location_id,location;

    public loctionDAO(String location_id, String location) {
        this.location_id = location_id;
        this.location = location;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
