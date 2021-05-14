package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.utils.UserUtil;
import com.example.delivery.views.InputView;

/**
 *
 */
public class RegisterManagerActivity extends BaseActivity {

    private InputView inputName;
    private InputView inputEmail;
    private InputView inputPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_manager);
        initView();
    }

    private void initView() {
        initNavBar(true, "Register New Manager", false);
        inputName = fd(R.id.register_manager_name);
        inputEmail = fd(R.id.register_manager_email);
        inputPassword = fd(R.id.register_manager_password);
    }

    public void onRegisterManagerSubmitClick(View view) {
        String name = inputName.getInputString();
        String email = inputEmail.getInputString();
        String password = inputPassword.getInputString();

        if (!UserUtil.register(this, name, email, password)) {
            return;
        }

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
