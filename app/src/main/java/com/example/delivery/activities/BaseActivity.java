package com.example.delivery.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;

import com.example.delivery.R;

public class BaseActivity extends Activity {
    private ImageView ivBack, ivAccount;
    private TextView tvTitle;

    protected <T extends View> T fd(@IdRes int id) {
        return findViewById(id);
    }

    protected void initNavBar(boolean isShowBack, String title, boolean isShowAccount) {
        ivBack = fd(R.id.back);
        tvTitle = fd(R.id.title);
        ivAccount = fd(R.id.account);

        ivBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        ivAccount.setVisibility(isShowAccount ? View.VISIBLE : View.GONE);
        tvTitle.setText(title);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ivAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseActivity.this, AccountActivity.class));
            }
        });
    }
}