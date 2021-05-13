package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;

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
}
