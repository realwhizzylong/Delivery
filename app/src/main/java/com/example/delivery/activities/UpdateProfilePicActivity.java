package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.delivery.R;

public class UpdateProfilePicActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_pic);

        initView();
    }

    private void initView() {
        initNavBar(true, "Update Profile Picture", false);
    }

    public void onConfirmUpdateProfilePicClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}