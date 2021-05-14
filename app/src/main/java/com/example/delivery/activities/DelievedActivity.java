package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.delivery.R;
import com.example.delivery.utils.PackageUtil;
import com.example.delivery.widget.dialog.DialogCallBack;
import com.example.delivery.widget.dialog.TextDialog;

import org.angmarch.views.NiceSpinner;

public class DelievedActivity extends BaseActivity {

    String packageName;
    String driver;
    String ventor;
    String site;
    boolean isManager;
    boolean isAdmin;
    boolean delivered;

    private TextView package_name;
    private TextView driver_name;
    private TextView vendor_name;
    private TextView site_name;
    private Button mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deieved);

        packageName = getIntent().getStringExtra("packageName");
        driver = getIntent().getStringExtra("driver");
        ventor = getIntent().getStringExtra("ventor");
        site = getIntent().getStringExtra("site");
        isManager = getIntent().getBooleanExtra("isManager", false);
        isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        delivered = getIntent().getBooleanExtra("delivered", false);

        initView();

        initData();

    }

    private void initData() {
        if (delivered || isAdmin) {
            mark.setVisibility(View.GONE);
        } else {
            mark.setVisibility(View.VISIBLE);
        }

        package_name.setText(packageName);
        driver_name.setText(driver);
        vendor_name.setText(ventor);
        site_name.setText(site);
    }

    public void markDelieved(View view) {
        new TextDialog(this).showDialog("Notification", "Are you sure to mark as delivered", "Yes", "No", new DialogCallBack() {
            @Override
            public void doselectok() {
                super.doselectok();

                mark();
            }
        });
    }

    private void mark() {
        if (!PackageUtil.markDelieved(this, packageName)) {
            return;
        }

        finish();
    }

    private void initView() {
        initNavBar(true, "Package Info", false);

        mark = fd(R.id.mark);
        package_name = fd(R.id.package_name);
        driver_name = fd(R.id.driver_name);
        vendor_name = fd(R.id.vendor_name);
        site_name = fd(R.id.site_name);
    }


}
