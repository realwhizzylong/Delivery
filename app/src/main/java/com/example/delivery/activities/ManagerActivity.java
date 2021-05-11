package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.helpers.UserHelper;
import com.example.delivery.utils.UserUtil;

public class ManagerActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        initView();
    }

    public void initView() {
        initNavBar(false, "Assign", true);
    }

    public void onRegisterDriver(View view) {
        String email = UserHelper.getInstance().getEmail();
        if (UserUtil.isRegisterAuthority(this, email)) {
            startActivity(new Intent(this, RegisterDriverActivity.class));
        }
    }
}
