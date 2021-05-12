package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.utils.PackageUtil;
import com.example.delivery.utils.UserUtil;
import com.example.delivery.views.InputView;

public class CreatePackageActivity extends BaseActivity {

    private InputView deliverPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_package);

        initView();
    }

    private void initView() {
        initNavBar(true, "Create New Package", false);
        deliverPackage = fd(R.id.create_package);
    }

    public void onCreatePackageClick(View view) {
        String newPackage = deliverPackage.getInputString();

        if(!PackageUtil.createPackage(this, newPackage)){
            return;
        }

        startActivity(new Intent(this, ManagerActivity.class));
        finish();
    }
}