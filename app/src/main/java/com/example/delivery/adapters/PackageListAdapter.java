package com.example.delivery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delivery.R;

public class PackageListAdapter extends RecyclerView.Adapter<PackageListAdapter.ViewHolder> {

    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalculated;

    public PackageListAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRv = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.package_list, viewGroup, false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setRecyclerViewHeight();
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    private void setRecyclerViewHeight() {
        if (isCalculated || mRv == null) {
            return;
        }
        RecyclerView.LayoutParams itemViewLp = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        LinearLayout.LayoutParams recyclerViewLp = (LinearLayout.LayoutParams) mRv.getLayoutParams();
        recyclerViewLp.height = itemViewLp.height * getItemCount();
        mRv.setLayoutParams(recyclerViewLp);
        isCalculated = true;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
