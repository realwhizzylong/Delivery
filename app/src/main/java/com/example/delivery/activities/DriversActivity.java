package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;

public class DriversActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        initView();
    }

    private void initView() {
        initNavBar(true, "Drivers", false);
    }

    public void onRegisterDriver(View view) {
        startActivity(new Intent(this, RegisterDriverActivity.class));
    }
}