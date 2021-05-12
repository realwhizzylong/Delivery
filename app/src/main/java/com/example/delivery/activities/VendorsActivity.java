package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;

public class VendorsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors);

        initView();
    }

    private void initView() {
        initNavBar(true, "Vendors", false);
    }

    public void onCreateVendor(View view) {
        startActivity(new Intent(this, CreateVendorActivity.class));
    }
}