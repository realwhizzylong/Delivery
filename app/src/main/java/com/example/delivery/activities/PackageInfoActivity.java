package com.example.delivery.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.delivery.R;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.PackageModel;
import com.example.delivery.utils.PackageUtil;
import com.example.delivery.views.InputView;
import com.example.delivery.widget.dialog.DialogCallBack;
import com.example.delivery.widget.dialog.TextDialog;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.List;

public class PackageInfoActivity extends BaseActivity implements OnSpinnerItemSelectedListener {

    String packageName;
    String driver;
    String ventor;
    String site;
    boolean isManager;

    private TextView package_name;
    private NiceSpinner driver_spinner;
    private NiceSpinner vendor_spinner;
    private NiceSpinner site_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_info);

        packageName = getIntent().getStringExtra("packageName");
        driver = getIntent().getStringExtra("driver");
        ventor = getIntent().getStringExtra("ventor");
        site = getIntent().getStringExtra("site");
        isManager = getIntent().getBooleanExtra("isManager", false);

        initView();

        initData();

    }

    private void initData() {
        package_name.setText(packageName);

        loadSelectData();
    }

    private void loadSelectData() {
        RealmHelper realmHelper = new RealmHelper();

        List<String> driverList = realmHelper.getAllDriverNames();
        driver_spinner.attachDataSource(driverList);
        driver_spinner.setSelectedIndex(driverList.indexOf(driver));

        List<String> siteList = realmHelper.getAllSiteNames();
        site_spinner.attachDataSource(siteList);
        site_spinner.setSelectedIndex(siteList.indexOf(site));

        List<String> vendorList = realmHelper.getAllVendorNames();
        vendor_spinner.attachDataSource(vendorList);
        vendor_spinner.setSelectedIndex(vendorList.indexOf(ventor));

        realmHelper.close();
    }

    private void initView() {
        initNavBar(true, "Package Info", false);

        package_name = fd(R.id.package_name);
        driver_spinner = fd(R.id.driver_spinner);
        vendor_spinner = fd(R.id.vendor_spinner);
        site_spinner = fd(R.id.site_spinner);

        driver_spinner.setOnSpinnerItemSelectedListener(this);
        vendor_spinner.setOnSpinnerItemSelectedListener(this);
        site_spinner.setOnSpinnerItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.driver_spinner:
                driver = (String) parent.getItemAtPosition(position);
                break;
            case R.id.vendor_spinner:
                ventor = (String) parent.getItemAtPosition(position);
                break;
            case R.id.site_spinner:
                site = (String) parent.getItemAtPosition(position);
                break;
            default:
                break;
        }
    }

    public void deletePackage(View view) {

        new TextDialog(this).showDialog("Notification", "Are you sure to delete this package", "Yes", "No", new DialogCallBack() {
            @Override
            public void doselectok() {
                super.doselectok();

                delete();
            }
        });

    }

    private void delete() {

        if (!PackageUtil.deletePackage(this, packageName)) {
            return;
        }

        finish();
    }

    public void updatePackage(View view) {

        new TextDialog(this).showDialog("Notification", "Are you sure to modify this package", "Yes", "No", new DialogCallBack() {
            @Override
            public void doselectok() {
                super.doselectok();

                modify();
            }
        });

    }

    private void modify() {

        if (!PackageUtil.updatePackage(this, packageName,driver,ventor,site)) {
            return;
        }

        finish();
    }
}
