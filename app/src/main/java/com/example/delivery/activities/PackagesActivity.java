package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.delivery.R;
import com.example.delivery.adapters.DriverAdapter;
import com.example.delivery.adapters.PackageAdapter;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.PackageModel;
import com.example.delivery.models.UserModel;

import java.io.Serializable;
import java.util.List;

public class PackagesActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {


    private RecyclerView drviverList;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private PackageAdapter packageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        RealmHelper realmHelper = new RealmHelper();
        List<PackageModel> list = realmHelper.getAllPackage();
        realmHelper.close();
        packageAdapter.setNewData(list);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void initView() {
        initNavBar(true, "All Packages", false);

        drviverList = fd(R.id.package_list);
        mSwipeRefreshLayout = fd(R.id.swipeLayout_package);

        packageAdapter = new PackageAdapter();
        drviverList.setLayoutManager(new LinearLayoutManager(this));
        drviverList.setAdapter(packageAdapter);
        packageAdapter.setOnItemClickListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    public void onCreatePackage(View view) {
        startActivity(new Intent(this, CreatePackageActivity.class));
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        initData();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent1 = new Intent(this, PackageInfoActivity.class);
        intent1.putExtra("packageName",  packageAdapter.getData().get(position).getPackageId());
        intent1.putExtra("driver",  packageAdapter.getData().get(position).getDriver().getUserName());
        intent1.putExtra("ventor",  packageAdapter.getData().get(position).getVendor());
        intent1.putExtra("site", packageAdapter.getData().get(position).getSite());
        intent1.putExtra("isManager", true);
        startActivity(intent1);
    }
}