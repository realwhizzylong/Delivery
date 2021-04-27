package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;
import com.example.delivery.utils.UserUtil;
import com.example.delivery.views.InputView;

public class AddPhoneNumActivity extends BaseActivity {

    private InputView inputPhoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_num);

        initView();
    }

    private void initView() {
        initNavBar(true, "Add Phone Number", false);

        inputPhoneNum = fd(R.id.add_phone);
    }

    public void onConfirmAddPhoneNumClick(View view) {
        String phoneNum = inputPhoneNum.getInputString();

        if (!UserUtil.validatePhoneNum(this, phoneNum)) {
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}