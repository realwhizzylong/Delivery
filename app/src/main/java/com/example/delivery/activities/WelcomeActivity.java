package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.delivery.R;
import com.example.delivery.models.UserModel;
import com.example.delivery.utils.UserInforSPUtils;
import com.example.delivery.utils.UserUtil;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    private Timer welcomeTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();
    }

    private void init() {
        welcomeTimer = new Timer();
        welcomeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                toLogin();
            }
        }, 2 * 1000);
    }

    private void toLogin() {
        if (!StringUtils.isEmpty(UserInforSPUtils.getEmail())) {


            LogUtils.i(UserInforSPUtils.getEmail());

            if (UserUtil.isManager(UserInforSPUtils.getEmail())) {
                Intent intent1 = new Intent(this, ManagerActivity.class);
                startActivity(intent1);
            } else if(UserUtil.isAdmin(UserInforSPUtils.getEmail())){
                Intent intent2 = new Intent(this, AdminActivity.class);
                startActivity(intent2);
            }else {
                Intent intent3 = new Intent(this, MainActivity.class);
                startActivity(intent3);
            }
            finish();

            return;
        }

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}