package com.example.delivery.adapters;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.delivery.R;
import com.example.delivery.models.SiteModel;
import com.example.delivery.models.UserModel;

import java.util.List;

public class SiteAdapter extends BaseQuickAdapter<SiteModel, BaseViewHolder> {
    public SiteAdapter() {
        super(R.layout.item_site);
    }

    @Override
    protected void convert(BaseViewHolder helper, SiteModel item) {
        helper.setText(R.id.item_site_name, item.getAddress());
    }
}
