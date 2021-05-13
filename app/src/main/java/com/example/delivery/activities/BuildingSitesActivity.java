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
import com.example.delivery.adapters.SiteAdapter;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.SiteModel;
import com.example.delivery.models.UserModel;

import java.util.List;

public class BuildingSitesActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    private RecyclerView drviverList;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private SiteAdapter siteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_sites);

        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        RealmHelper realmHelper = new RealmHelper();
        List<SiteModel> list = realmHelper.getAllSites();
        realmHelper.close();
        siteAdapter.setNewData(list);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void initView() {
        initNavBar(true, "Building Sites", false);

        drviverList = fd(R.id.site_list);
        mSwipeRefreshLayout = fd(R.id.swipeLayout_site);

        siteAdapter = new SiteAdapter();
        drviverList.setLayoutManager(new LinearLayoutManager(this));
        drviverList.setAdapter(siteAdapter);
        siteAdapter.setOnItemClickListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    public void onCreateBuildingSite(View view) {
        startActivity(new Intent(this, CreateBuildingSiteActivity.class));
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

        Intent intent1 = new Intent(this, SiteInfoActivity.class);
        intent1.putExtra("site",  siteAdapter.getData().get(position).getAddress());
        intent1.putExtra("isManager", true);
        startActivity(intent1);
    }
}