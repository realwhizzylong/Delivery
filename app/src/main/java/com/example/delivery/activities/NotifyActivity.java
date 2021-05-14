package com.example.delivery.activities;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.delivery.R;
import com.example.delivery.adapters.NotifyAdapter;
import com.example.delivery.adapters.PackageAdapter;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.NotifyModel;
import com.example.delivery.models.PackageModel;
import com.example.delivery.utils.PackageUtil;
import com.example.delivery.utils.SiteUtil;
import com.example.delivery.utils.UserInforSPUtils;
import com.example.delivery.utils.UserUtil;

import java.util.ArrayList;
import java.util.List;

public class NotifyActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    private RecyclerView mRvlist;


    private SwipeRefreshLayout mSwipeRefreshLayout;

    NotifyAdapter notifyAdapter;

    private boolean isAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        isAdmin = UserUtil.isAdmin(UserInforSPUtils.getEmail());

        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initdata();
    }

    private void initdata() {
        RealmHelper realmHelper = new RealmHelper();
        List<PackageModel> list = null;
        if (isAdmin) {
            list = realmHelper.getPackageBySite(SiteUtil.getFirstSite());
        } else {
            list = realmHelper.getPackageByUser(UserInforSPUtils.getName());
        }

        realmHelper.close();

        List<NotifyModel> notify = new ArrayList<>();

        for (PackageModel model : list) {
            if (isAdmin) {
                if (model.isDelivered() && !model.isDeliveredNotify()) {
                    NotifyModel notifyModel = new NotifyModel();
                    notifyModel.setNews(model.getPackageId() + " has been delivered to " + model.getSite() + " by " + model.getDriver().getUserName() + ".");
                    notifyModel.setPackageId(model.getPackageId());
                    notify.add(notifyModel);
                }
            } else {
                if (!model.isNotified()) {
                    NotifyModel notifyModel = new NotifyModel();
                    notifyModel.setNews("You have a new " + model.getPackageId() + " which need to deliver to " + model.getSite() + " and collect at " + model.getVendor() + ".");
                    notifyModel.setPackageId(model.getPackageId());
                    notify.add(notifyModel);
                }
            }
        }

        notifyAdapter.setNewData(notify);

        mSwipeRefreshLayout.setRefreshing(false);

    }

    private void initView() {
        initNavBar(true, "Notification", true);

        mRvlist = fd(R.id.rv_list);
        notifyAdapter = new NotifyAdapter();
        mRvlist.setLayoutManager(new LinearLayoutManager(this));
        mRvlist.setAdapter(notifyAdapter);
        notifyAdapter.setOnItemClickListener(this);

        mSwipeRefreshLayout = fd(R.id.swipeLayout_notify);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        initdata();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        String packageId = notifyAdapter.getData().get(position).getPackageId();
        if(isAdmin){
            if (!PackageUtil.clearDeliverNotify(this, packageId)) {
                return;
            }
        }else {
            if (!PackageUtil.clearNotify(this, packageId)) {
                return;
            }
        }


        notifyAdapter.remove(position);
    }


}
