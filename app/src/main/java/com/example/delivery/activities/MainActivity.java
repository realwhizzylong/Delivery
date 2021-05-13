package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.delivery.R;
import com.example.delivery.adapters.PackageAdapter;
import com.example.delivery.adapters.PackageListAdapter;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.PackageModel;
import com.example.delivery.utils.UserInforSPUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    private RecyclerView mRvlist;


    private SwipeRefreshLayout mSwipeRefreshLayout;

    PackageAdapter packageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        RealmHelper realmHelper = new RealmHelper();
        List<PackageModel> list = realmHelper.getPackageByUser(UserInforSPUtils.getName());
        realmHelper.close();
        packageAdapter.setNewData(list);

        checkNotify(list);

        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void checkNotify(List<PackageModel> list) {
        boolean showNotify=false;
        for (PackageModel model : list) {
            if (!model.isNotified()) {
                showNotify=true;
                break;
            }
        }

        setNotify(showNotify);
    }

    private void initView() {
        initNavBar(false, "Delivery", true);

        mRvlist = fd(R.id.rv_list);
        packageAdapter = new PackageAdapter();
        mRvlist.setLayoutManager(new LinearLayoutManager(this));
        mRvlist.setAdapter(packageAdapter);
        packageAdapter.setOnItemClickListener(this);

        mSwipeRefreshLayout = fd(R.id.swipeLayout_main);
        mSwipeRefreshLayout.setOnRefreshListener(this);
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
        Intent intent1 = new Intent(this, DelievedActivity.class);
        intent1.putExtra("packageName", packageAdapter.getData().get(position).getPackageId());
        intent1.putExtra("driver", packageAdapter.getData().get(position).getDriver().getUserName());
        intent1.putExtra("ventor", packageAdapter.getData().get(position).getVendor());
        intent1.putExtra("site", packageAdapter.getData().get(position).getSite());
        intent1.putExtra("isManager", false);
        intent1.putExtra("delivered", packageAdapter.getData().get(position).isDelivered());
        startActivity(intent1);
    }
}