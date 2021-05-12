package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;

public class BuildingSitesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_sites);

        initView();
    }

    private void initView() {
        initNavBar(true, "Building Sites", false);
    }

    public void onCreateBuildingSite(View view) {
        startActivity(new Intent(this, CreateBuildingSiteActivity.class));
    }
}