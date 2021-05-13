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
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.PackageModel;
import com.example.delivery.models.UserModel;

import java.util.List;

public class DriversActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener , BaseQuickAdapter.OnItemClickListener{

    private RecyclerView drviverList;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    DriverAdapter driverAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        initView();


    }

    @Override
    protected void onResume() {
        super.onResume();

        initData();
    }

    private void initData() {
        RealmHelper realmHelper = new RealmHelper();
        List<UserModel> list = realmHelper.getAllUsers();
        realmHelper.close();
        driverAdapter.setNewData(list);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    private void initView() {
        initNavBar(true, "Drivers", false);

        drviverList = fd(R.id.driver_list);
        mSwipeRefreshLayout = fd(R.id.swipeLayout_driver);


        driverAdapter = new DriverAdapter();
        drviverList.setLayoutManager(new LinearLayoutManager(this));
        drviverList.setAdapter(driverAdapter);
        driverAdapter.setOnItemClickListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    public void onRegisterDriver(View view) {
        startActivity(new Intent(this, RegisterDriverActivity.class));
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
        LogUtils.i("onItemClick----" + driverAdapter.getData().get(position));

        Intent intent1 = new Intent(this, DriverInfoActivity.class);
        intent1.putExtra("driver",  driverAdapter.getData().get(position).getUserName());
        intent1.putExtra("isManager", true);
        startActivity(intent1);


    }
}