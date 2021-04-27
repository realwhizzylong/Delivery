package com.example.delivery.activities;

import android.os.Bundle;

import com.example.delivery.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        initNavBar(false, "Delivery", true);
    }
}