package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.example.delivery.R;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.PackageModel;
import com.example.delivery.utils.PackageUtil;
import com.example.delivery.utils.UserUtil;
import com.example.delivery.views.InputView;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.List;

public class CreatePackageActivity extends BaseActivity implements OnSpinnerItemSelectedListener {

    private InputView deliverPackage;

    private NiceSpinner driver_spinner;
    private NiceSpinner vendor_spinner;
    private NiceSpinner site_spinner;

    private String driver;
    private String vemdor;
    private String site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_package);

        initView();

        initData();
    }


    private void initData() {
        RealmHelper realmHelper = new RealmHelper();

        List<String> driverList = realmHelper.getAllDriverNames();
        driver_spinner.attachDataSource(driverList);
        if (driverList != null && !driverList.isEmpty()) {
            driver = driverList.get(0);
            driver_spinner.setSelectedIndex(0);
        }


        List<String> siteList = realmHelper.getAllSiteNames();
        site_spinner.attachDataSource(siteList);
        if (siteList != null && !siteList.isEmpty()) {
            site = siteList.get(0);
            site_spinner.setSelectedIndex(0);
        }

        List<String> vendorList = realmHelper.getAllVendorNames();
        vendor_spinner.attachDataSource(vendorList);
        if (vendorList != null && !vendorList.isEmpty()) {
            vemdor = vendorList.get(0);
            vendor_spinner.setSelectedIndex(0);
        }

        realmHelper.close();

    }

    private void initView() {
        initNavBar(true, "Create New Package", false);
        deliverPackage = fd(R.id.create_package);
        driver_spinner = fd(R.id.driver_spinner);
        vendor_spinner = fd(R.id.vendor_spinner);
        site_spinner = fd(R.id.site_spinner);

        driver_spinner.setOnSpinnerItemSelectedListener(this);
        vendor_spinner.setOnSpinnerItemSelectedListener(this);
        site_spinner.setOnSpinnerItemSelectedListener(this);
    }

    public void onCreatePackageClick(View view) {
        String newPackage = deliverPackage.getInputString();

        if (!PackageUtil.createPackage(this, newPackage, driver, site, vemdor)) {
            return;
        }

        finish();
    }


    @Override
    public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.driver_spinner:
                LogUtils.i("-------driver_spinner----");
                driver = (String) parent.getItemAtPosition(position);
                break;
            case R.id.vendor_spinner:
                LogUtils.i("-------vendor_spinner----");
                vemdor = (String) parent.getItemAtPosition(position);
                break;
            case R.id.site_spinner:
                LogUtils.i("-------site_spinner----");
                site = (String) parent.getItemAtPosition(position);
                break;
            default:
                break;
        }
    }
}