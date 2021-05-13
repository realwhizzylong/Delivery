package com.example.delivery.adapters;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.delivery.R;
import com.example.delivery.models.PackageModel;
import com.example.delivery.models.UserModel;

import java.util.List;

public class DriverAdapter extends BaseQuickAdapter<UserModel, BaseViewHolder> {
    public DriverAdapter() {
        super(R.layout.item_driver);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserModel item) {
        helper.setText(R.id.item_driver_name, item.getUserName());
    }
}
