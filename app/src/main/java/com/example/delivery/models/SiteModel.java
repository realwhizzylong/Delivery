package com.example.delivery.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SiteModel extends RealmObject {

    @PrimaryKey
    private String siteName;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
