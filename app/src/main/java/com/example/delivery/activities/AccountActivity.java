package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.utils.UserUtil;

public class AccountActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initView();
    }

    private void initView() {
        initNavBar(true, "User Account", false);
    }

    public void onAddPhoneNumClick(View view) {
        startActivity(new Intent(this, AddPhoneNumActivity.class));
    }

    public void onUpdateProfilePicClick(View view) {
        startActivity(new Intent(this, UpdateProfilePicActivity.class));
    }


    public void onChangePasswordClick(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }


    public void onLogoutClick(View view) {
        UserUtil.logout(this);
    }
}