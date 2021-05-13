package com.example.delivery.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.example.delivery.R;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.widget.dialog.DialogCallBack;
import com.example.delivery.widget.dialog.TextDialog;

public class SiteInfoActivity extends BaseActivity{



    String site;
    boolean isManager;

    EditText site_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_info);
        site = getIntent().getStringExtra("site");
        isManager = getIntent().getBooleanExtra("isManager", false);

        initView();

        initData();
    }

    private void initData() {
        site_name.setText(site);

    }

    private void initView() {
        initNavBar(true, "Site Info", false);
        site_name = fd(R.id.site_name);
    }

    public void onDeleteSite(View view) {

        new TextDialog(this).showDialog("Notification", "Are you sure to delete this site", "Yse", "No", new DialogCallBack() {
            @Override
            public void doselectok() {
                super.doselectok();

                delete();
            }
        });

    }

    private void delete() {
        RealmHelper realmHelper = new RealmHelper();

        realmHelper.deleteSite(site);
        realmHelper.close();

        finish();
    }

    public void onUpdateSite(View view) {

        new TextDialog(this).showDialog("Notification", "Are you sure to modify this site name", "Yse", "No", new DialogCallBack() {
            @Override
            public void doselectok() {
                super.doselectok();

                modify();
            }
        });

    }

    private void modify() {
        String name = site_name.getText().toString();
        if (StringUtils.isEmpty(name)) {
            Toast.makeText(this, "Site name isEmpty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtils.equals(site, name)) {
            Toast.makeText(this, "Site name do not change", Toast.LENGTH_SHORT).show();
            return;
        }

        RealmHelper realmHelper = new RealmHelper();

        realmHelper.modifySite(site,name);
        realmHelper.close();

        finish();
    }
}
