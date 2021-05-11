package com.example.delivery.activities;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.delivery.R;

public class ManagerActivity extends BaseActivity{

    private RecyclerView mRvlist;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        initView();
    }

    public void initView(){
        initNavBar(false,"Assign", true);


    }
}
