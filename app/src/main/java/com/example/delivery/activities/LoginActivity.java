package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.utils.UserUtil;
import com.example.delivery.views.InputView;

public class LoginActivity extends BaseActivity {

    private InputView inputName, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        initNavBar(false, "Log In", false);

        inputName = fd(R.id.input_name);
        inputPassword = fd(R.id.input_password);
    }

    public void onLoginClick(View view) {
        String name = inputName.getInputString();
        String password = inputPassword.getInputString();

        if (!UserUtil.validateLogin(this, name, password)) {
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}