package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.delivery.R;
import com.example.delivery.adapters.DriverAdapter;
import com.example.delivery.adapters.VendorAdapter;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.UserModel;
import com.example.delivery.models.VendorModel;

import java.util.List;

public class VendorsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    private RecyclerView drviverList;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private VendorAdapter vendorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        initNavBar(true, "Vendors", false);

        drviverList = fd(R.id.vendor_list);
        mSwipeRefreshLayout = fd(R.id.swipeLayout_vendor);


        vendorAdapter = new VendorAdapter();
        drviverList.setLayoutManager(new LinearLayoutManager(this));
        drviverList.setAdapter(vendorAdapter);
        vendorAdapter.setOnItemClickListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    public void onCreateVendor(View view) {
        startActivity(new Intent(this, CreateVendorActivity.class));
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        initData();
    }

    private void initData() {
        RealmHelper realmHelper = new RealmHelper();
        List<VendorModel> list = realmHelper.getAllVendors();
        realmHelper.close();
        vendorAdapter.setNewData(list);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent1 = new Intent(this, VentorInfoActivity.class);
        intent1.putExtra("ventor",  vendorAdapter.getData().get(position).getVendorName());
        intent1.putExtra("isManager", true);
        startActivity(intent1);
    }
}