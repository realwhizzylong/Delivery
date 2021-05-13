package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.utils.VendorUtil;
import com.example.delivery.views.InputView;

public class CreateVendorActivity extends BaseActivity {

    private InputView vendorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vendor);

        initView();
    }

    private void initView() {
        initNavBar(true, "Create Vendor", false);

        vendorName = fd(R.id.create_vendor);
    }

    public void onCreateVendorClick(View view) {
        String vendor = vendorName.getInputString();

        if (!VendorUtil.createVendor(this, vendor)) {
            return;
        }

        finish();
    }
}