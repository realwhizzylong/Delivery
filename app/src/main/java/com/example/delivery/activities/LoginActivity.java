package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.utils.UserUtil;
import com.example.delivery.views.InputView;

public class LoginActivity extends BaseActivity {

    private InputView inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        initNavBar(false, "Log In", false);

        inputEmail = fd(R.id.login_email);
        inputPassword = fd(R.id.login_password);
    }

    public void onLoginClick(View view) {
        String email = inputEmail.getInputString();
        String password = inputPassword.getInputString();

        if (!UserUtil.login(this, email, password)) {
            return;
        }
        if (UserUtil.isManager(email)) {
            Intent intent1 = new Intent(this, ManagerActivity.class);
            startActivity(intent1);
            finish();
        } else {
            Intent intent2 = new Intent(this, MainActivity.class);
            startActivity(intent2);
            finish();
        }

    }

    public void onRegisterManagerClick(View view) {
        startActivity(new Intent(this, RegisterManagerActivity.class));
    }
}