package com.example.delivery.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.StringUtils;
import com.example.delivery.R;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.widget.dialog.DialogCallBack;
import com.example.delivery.widget.dialog.TextDialog;

public class DriverInfoActivity extends BaseActivity {

    String driver;
    boolean isManager;

    EditText driver_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        driver = getIntent().getStringExtra("driver");
        isManager = getIntent().getBooleanExtra("isManager", false);

        initView();

        initData();
    }

    private void initData() {
        driver_name.setText(driver);
    }

    private void initView() {
        initNavBar(true, "Driver Info", false);

        driver_name = fd(R.id.driver_name);
    }


    public void onDelete(View view) {

        new TextDialog(this).showDialog("Notification", "Are you sure to delete this driver", "Yse", "No", new DialogCallBack() {
            @Override
            public void doselectok() {
                super.doselectok();

                delete();
            }
        });

    }

    public void onUpdateDriver(View view) {

        new TextDialog(this).showDialog("Notification", "Are you sure to modify this driver name", "Yse", "No", new DialogCallBack() {
            @Override
            public void doselectok() {
                super.doselectok();

                modify();
            }
        });

    }

    private void modify() {
        String name = driver_name.getText().toString();
        if (StringUtils.isEmpty(name)) {
            Toast.makeText(this, "Driver name isEmpty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtils.equals(driver, name)) {
            Toast.makeText(this, "Driver name do not change", Toast.LENGTH_SHORT).show();
            return;
        }

        RealmHelper realmHelper = new RealmHelper();

        realmHelper.modifyDriver(driver,name);
        realmHelper.close();

        finish();
    }

    private void delete() {
        RealmHelper realmHelper = new RealmHelper();

        realmHelper.deleteDriver(driver);
        realmHelper.close();

        finish();
    }


}
