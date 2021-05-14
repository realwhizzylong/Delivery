package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.utils.PackageUtil;

import java.util.List;
import java.util.Random;

public class ManagerActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        initView();
    }

    public void initView() {
        initNavBar(false, "Manager", true);
    }

    public void onDrivers(View view) {
        startActivity(new Intent(this, DriversActivity.class));
    }

    public void onVendors(View view) {
        startActivity(new Intent(this, VendorsActivity.class));
    }

    public void onBuildingSites(View view) {
        startActivity(new Intent(this, BuildingSitesActivity.class));
    }

    public void onPackages(View view) {
        startActivity(new Intent(this, PackagesActivity.class));
    }

    public void addPak(View view) {
        RealmHelper realmHelper = new RealmHelper();

        List<String> driverList = realmHelper.getAllDriverNames();
        List<String> siteList = realmHelper.getAllSiteNames();
        List<String> vendorList = realmHelper.getAllVendorNames();
        realmHelper.close();

        Random r = new Random();
        for (int i = 1; i < 51; i++) {
            PackageUtil.createPackage(this, "Package" + i, driverList.get(r.nextInt(driverList.size())), siteList.get(r.nextInt(siteList.size())), vendorList.get(r.nextInt(vendorList.size())));
        }

    }
}
