package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.utils.UserUtil;
import com.example.delivery.views.InputView;

public class RegisterDriverActivity extends BaseActivity {

    private InputView inputName;
    private InputView inputEmail;
    private InputView inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
        initView();
    }

    private void initView() {
        initNavBar(true, "Register New Driver", false);

        inputName = fd(R.id.register_name);
        inputEmail = fd(R.id.register_email);
        inputPassword = fd(R.id.register_password);
    }

    public void onRegisterDriverClick(View view) {
        String name = inputName.getInputString();
        String email = inputEmail.getInputString();
        String password = inputPassword.getInputString();

        if (!UserUtil.register(this, name, email, password)) {
            return;
        }

        finish();
    }
}