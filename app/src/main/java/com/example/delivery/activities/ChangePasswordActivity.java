package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.utils.UserUtil;
import com.example.delivery.views.InputView;

public class ChangePasswordActivity extends BaseActivity {

    private InputView inputCurrentPassword, inputNewPassword, inputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initView();
    }

    private void initView() {
        initNavBar(true, "Reset Password", false);

        inputCurrentPassword = fd(R.id.current_password);
        inputNewPassword = fd(R.id.new_password);
        inputConfirmPassword = fd(R.id.confirm_password);
    }

    public void onConfirmChangePasswordClick(View view) {
        String currentPassword = inputCurrentPassword.getInputString();
        String newPassword = inputNewPassword.getInputString();
        String confirmPassword = inputConfirmPassword.getInputString();

        if (!UserUtil.validateChangePassword(this, currentPassword, newPassword, confirmPassword)) {
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}