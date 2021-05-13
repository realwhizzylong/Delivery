package com.example.delivery.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SiteModel extends RealmObject {

    @PrimaryKey
    private String sitId;

    private String address;

    public String getSitId() {
        return sitId;
    }

    public void setSitId(String sitId) {
        this.sitId = sitId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
