package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.example.delivery.R;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.helpers.UserHelper;
import com.example.delivery.models.UserModel;
import com.example.delivery.utils.UserInforSPUtils;
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

        RealmHelper realmHelper2 = new RealmHelper();
        UserModel userModel = realmHelper2.getUserByEmail(email);
        UserInforSPUtils.saveEmail(userModel.getEmail());
        UserInforSPUtils.saveName(userModel.getUserName());
        UserInforSPUtils.savePhone(userModel.getPhone());
        UserInforSPUtils.savePic(userModel.getProfilePicture());
        realmHelper2.close();


        if (UserUtil.isManager(email)) {
            Intent intent1 = new Intent(this, ManagerActivity.class);
            startActivity(intent1);
        } else if(UserUtil.isAdmin(email)){
            Intent intent2 = new Intent(this, AdminActivity.class);
            startActivity(intent2);
        }else {
            Intent intent3 = new Intent(this, MainActivity.class);
            startActivity(intent3);
        }
        finish();

    }

    public void onRegisterManagerClick(View view) {
        startActivity(new Intent(this, RegisterManagerActivity.class));
    }
}