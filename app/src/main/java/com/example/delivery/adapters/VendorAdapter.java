package com.example.delivery.adapters;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.delivery.R;
import com.example.delivery.models.UserModel;
import com.example.delivery.models.VendorModel;

import java.util.List;

public class VendorAdapter extends BaseQuickAdapter<VendorModel, BaseViewHolder> {
    public VendorAdapter() {
        super(R.layout.item_vendor);
    }

    @Override
    protected void convert(BaseViewHolder helper, VendorModel item) {

        helper.setText(R.id.item_vendor_name, item.getVendorName());
    }
}
