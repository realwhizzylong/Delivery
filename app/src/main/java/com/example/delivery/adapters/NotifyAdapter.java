package com.example.delivery.adapters;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.delivery.R;
import com.example.delivery.models.NotifyModel;
import com.example.delivery.models.PackageModel;

import java.util.List;

public class NotifyAdapter extends BaseQuickAdapter<NotifyModel, BaseViewHolder> {
    public NotifyAdapter() {
        super(R.layout.item_notify);
    }

    @Override
    protected void convert(BaseViewHolder helper, NotifyModel item) {
        helper.setText(R.id.item_notify, item.getNews());
    }
}
