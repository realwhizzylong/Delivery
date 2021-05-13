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

public class VentorInfoActivity extends BaseActivity {

    String ventor;
    boolean isManager;

    EditText ventor_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventor_info);
        ventor = getIntent().getStringExtra("ventor");
        isManager = getIntent().getBooleanExtra("isManager", false);

        initView();

        initData();
    }

    private void initData() {
        ventor_name.setText(ventor);

    }

    private void initView() {
        initNavBar(true, "Ventor Info", false);
        ventor_name = fd(R.id.ventor_name);
    }

    public void onDeleteVentor(View view) {

        new TextDialog(this).showDialog("Notification", "Are you sure to delete this ventor", "Yes", "No", new DialogCallBack() {
            @Override
            public void doselectok() {
                super.doselectok();

                delete();
            }
        });

    }

    private void delete() {
        RealmHelper realmHelper = new RealmHelper();

        realmHelper.deleteventor(ventor);
        realmHelper.close();

        finish();
    }

    public void onUpdateVentor(View view) {

        new TextDialog(this).showDialog("Notification", "Are you sure to modify this vendor name", "Yes", "No", new DialogCallBack() {
            @Override
            public void doselectok() {
                super.doselectok();

                modify();
            }
        });

    }

    private void modify() {
        String name = ventor_name.getText().toString();
        if (StringUtils.isEmpty(name)) {
            Toast.makeText(this, "Vendor name isEmpty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtils.equals(ventor, name)) {
            Toast.makeText(this, "Driver name do not change", Toast.LENGTH_SHORT).show();
            return;
        }

        RealmHelper realmHelper = new RealmHelper();

        realmHelper.modifyVentor(ventor,name);
        realmHelper.close();

        finish();
    }
}
