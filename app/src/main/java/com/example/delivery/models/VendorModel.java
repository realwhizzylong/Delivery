package com.example.delivery.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VendorModel extends RealmObject {

    @PrimaryKey
    private String vendorName;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
