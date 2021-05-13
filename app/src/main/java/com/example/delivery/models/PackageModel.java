package com.example.delivery.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class PackageModel extends RealmObject {

    @PrimaryKey
    private String packageId;

    @Required
    private String site;

    @Required
    private String vendor;

    private UserModel driver;

    private Date createTime;

    private Date delieveTime;

    // is need notify driver
    private boolean notified;

    private boolean delivered;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public UserModel getDriver() {
        return driver;
    }

    public void setDriver(UserModel driver) {
        this.driver = driver;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDelieveTime() {
        return delieveTime;
    }

    public void setDelieveTime(Date delieveTime) {
        this.delieveTime = delieveTime;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
