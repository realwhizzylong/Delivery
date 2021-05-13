package com.example.delivery.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.delivery.R;
import com.example.delivery.models.PackageModel;

public class PackageAdapter extends BaseQuickAdapter<PackageModel, BaseViewHolder> {

    public PackageAdapter() {
        super(R.layout.package_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, PackageModel item) {
        helper.setText(R.id.item_package_name, item.getPackageId());

        if (item.isDelivered()) {
            helper.setImageResource(R.id.item_package_state, R.mipmap.finish);
        } else {
            helper.setImageResource(R.id.item_package_state, R.mipmap.pending);
        }

    }
}
