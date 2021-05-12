package com.example.delivery.activities;

import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;

public class CreatePackageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_package);

        initView();
    }

    private void initView() {
        initNavBar(true, "Create New Package", false);
    }

    public void onCreateBuildingSiteClick(View view) {
    }
}