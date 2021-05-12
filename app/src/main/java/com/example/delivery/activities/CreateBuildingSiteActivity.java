package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.utils.SiteUtil;
import com.example.delivery.views.InputView;

public class CreateBuildingSiteActivity extends BaseActivity {

    private InputView siteAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_building_site);

        initView();
    }

    private void initView() {
        initNavBar(true, "Create Building Sites", false);

        siteAddress = fd(R.id.create_building_site);
    }

    public void onCreateBuildingSiteClick(View view) {
        String address = siteAddress.getInputString();

        if (!SiteUtil.createSite(this, address)) {
            return;
        }

        Intent intent = new Intent(this, ManagerActivity.class);
        startActivity(intent);
        finish();
    }
}