package com.example.delivery.activities;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delivery.R;
import com.example.delivery.adapters.PackageListAdapter;

public class MainActivity extends BaseActivity {

    private RecyclerView mRvlist;
    private PackageListAdapter packageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        initNavBar(false, "Delivery", true);

        mRvlist = fd(R.id.rv_list);
        mRvlist.setLayoutManager(new LinearLayoutManager(this));
        mRvlist.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvlist.setNestedScrollingEnabled(false);
        packageListAdapter = new PackageListAdapter(this, mRvlist);
        mRvlist.setAdapter(packageListAdapter);
    }
}