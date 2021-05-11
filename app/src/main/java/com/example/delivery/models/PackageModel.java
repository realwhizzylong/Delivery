package com.example.delivery.models;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class PackageModel {

    private String driver_id;

    @Required
    private String sites;

    @Required
    private String vendors;

    private String package_id;

    public String getDriver_id(){
        return driver_id;
    }

    public void setDriver_id(String driver_id){
        this.driver_id = driver_id;
    }

    public String getSites(){
        return sites;
    }

    public void setSites(String sites){
        this.sites = sites;
    }

    public String getVendors(){
        return vendors;
    }

    public void setVendors(String vendors){
        this.vendors = vendors;
    }



}
