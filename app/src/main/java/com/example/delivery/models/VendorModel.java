package com.example.delivery.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VendorModel extends RealmObject {

    @PrimaryKey
    private String ventorId;

    private String vendorName;

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVentorId() {
        return ventorId;
    }

    public void setVentorId(String ventorId) {
        this.ventorId = ventorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
