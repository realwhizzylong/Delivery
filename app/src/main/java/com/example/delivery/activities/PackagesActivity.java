package com.example.delivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.delivery.R;
import com.example.delivery.adapters.DriverAdapter;
import com.example.delivery.adapters.PackageAdapter;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.PackageModel;
import com.example.delivery.models.UserModel;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PackagesActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener, OnSpinnerItemSelectedListener {


    private RecyclerView drviverList;
    private DrawerLayout drawlayout;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private PackageAdapter packageAdapter;

    private NiceSpinner select_spinner;
    private String selectType;

    private LinearLayout create_start_layout;
    private LinearLayout create_end_layout;
    private TextView create_start_time;
    private TextView create_end_time;
    private TimePickerView createTimePicker;
    private TimePickerView createTimeEndPicker;
    private Date creatStartDate;
    private Date creatEndDate;

    private LinearLayout del_start_layout;
    private LinearLayout del_end_layout;
    private TextView del_start_time;
    private TextView del_end_time;
    private TimePickerView delTimePicker;
    private TimePickerView delTimeEndPicker;
    private Date delStartDate;
    private Date delEndDate;

    private TextView reset_txt;
    private TextView filter_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData(selectType);
    }


    private void initView() {
        initNavBar(true, "All Packages", false);

        reset_txt = fd(R.id.reset_txt);
        reset_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFilter();
            }
        });
        filter_txt = fd(R.id.filter_txt);
        filter_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter();
            }
        });

        create_start_layout = fd(R.id.create_start_layout);
        create_start_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTimePicker.show();
            }
        });
        create_end_layout = fd(R.id.create_end_layout);
        create_end_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTimeEndPicker.show();
            }
        });
        create_start_time = fd(R.id.create_start_time);
        create_end_time = fd(R.id.create_end_time);

        del_start_layout = fd(R.id.del_start_layout);
        del_start_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delTimePicker.show();
            }
        });
        del_end_layout = fd(R.id.del_end_layout);
        del_end_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delTimeEndPicker.show();
            }
        });
        del_start_time = fd(R.id.del_start_time);
        del_end_time = fd(R.id.del_end_time);


        drviverList = fd(R.id.package_list);
        drawlayout = fd(R.id.drawlayout);
        mSwipeRefreshLayout = fd(R.id.swipeLayout_package);

        packageAdapter = new PackageAdapter();
        drviverList.setLayoutManager(new LinearLayoutManager(this));
        drviverList.setAdapter(packageAdapter);
        packageAdapter.setOnItemClickListener(this);

        select_spinner = fd(R.id.select_spinner);
        select_spinner.setOnSpinnerItemSelectedListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        selectType = "All";

        List<String> type = new ArrayList<>();
        type.add("All");
        type.add("Pending");
        type.add("Delivered");
        select_spinner.attachDataSource(type);


        createTimePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                create_start_time.setText(TimeUtils.date2String(date));
                creatStartDate = date;
            }
        }).build();
        createTimeEndPicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                create_end_time.setText(TimeUtils.date2String(date));
                creatEndDate = date;
            }
        }).build();
        delTimePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                del_start_time.setText(TimeUtils.date2String(date));
                delStartDate = date;
            }
        }).build();
        delTimeEndPicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                del_end_time.setText(TimeUtils.date2String(date));
                delEndDate = date;
            }
        }).build();
    }

    private void filter() {
        drawlayout.closeDrawer(Gravity.RIGHT, true);
        refreshData(selectType);
    }

    private void resetFilter() {
        selectType = "All";
        drawlayout.closeDrawer(Gravity.RIGHT, true);
        refreshData(selectType);
        creatStartDate = null;
        creatEndDate = null;
        delStartDate = null;
        delEndDate = null;
    }

    public void onFilter(View view) {
        drawlayout.openDrawer(Gravity.RIGHT, true);
    }

    public void onCreatePackage(View view) {
        startActivity(new Intent(this, CreatePackageActivity.class));
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        refreshData(selectType);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent1 = new Intent(this, PackageInfoActivity.class);
        intent1.putExtra("packageName", packageAdapter.getData().get(position).getPackageId());
        intent1.putExtra("driver", packageAdapter.getData().get(position).getDriver().getUserName());
        intent1.putExtra("ventor", packageAdapter.getData().get(position).getVendor());
        intent1.putExtra("site", packageAdapter.getData().get(position).getSite());
        intent1.putExtra("isManager", true);
        startActivity(intent1);
    }

    @Override
    public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.select_spinner:
                String a = (String) parent.getItemAtPosition(position);
                if (StringUtils.equals(a, selectType)) {
                    return;
                }
                selectType = a;
                break;
            default:
                break;
        }
    }

    private void refreshData(String selectType) {


        RealmHelper realmHelper = new RealmHelper();
        List<PackageModel> list = realmHelper.getAllPackage();
        realmHelper.close();
        List<PackageModel> sortList = new ArrayList<>();
        if (StringUtils.equals("All", selectType)) {
            sortList.addAll(list);
        } else if (StringUtils.equals("Pending", selectType)) {
            for (PackageModel model : list) {
                if (!model.isDelivered()) {
                    sortList.add(model);
                }
            }
        } else {
            for (PackageModel model : list) {
                if (model.isDelivered()) {
                    sortList.add(model);
                }
            }
        }

        if (creatStartDate != null) {
            Iterator<PackageModel> iterator = sortList.iterator();
            while (iterator.hasNext()) {
                PackageModel model = iterator.next();
                if (model.getCreateTime() != null && model.getCreateTime().before(creatStartDate)) {
                    iterator.remove();
                }
            }
        }

        if (creatEndDate != null) {
            Iterator<PackageModel> iterator2 = sortList.iterator();
            while (iterator2.hasNext()) {
                PackageModel model = iterator2.next();
                if (model.getCreateTime() != null && model.getCreateTime().after(creatEndDate)) {
                    iterator2.remove();
                }
            }
        }

        if (delStartDate != null) {
            Iterator<PackageModel> iterator3 = sortList.iterator();
            while (iterator3.hasNext()) {
                PackageModel model = iterator3.next();
                if (model.getDelieveTime() != null && model.getDelieveTime().before(delStartDate)) {
                    iterator3.remove();
                }
            }
        }
        if (delEndDate != null) {
            Iterator<PackageModel> iterator4 = sortList.iterator();
            while (iterator4.hasNext()) {
                PackageModel model = iterator4.next();
                if (model.getDelieveTime() != null && model.getDelieveTime().after(delEndDate)) {
                    iterator4.remove();
                }
            }
        }

        packageAdapter.setNewData(sortList);
        mSwipeRefreshLayout.setRefreshing(false);

    }
}