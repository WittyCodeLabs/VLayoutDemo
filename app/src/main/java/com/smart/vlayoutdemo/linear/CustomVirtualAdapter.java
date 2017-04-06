package com.smart.vlayoutdemo.linear;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.smart.vlayoutdemo.R;

/**
 * Created by fengjh on 17/3/14.
 */

public class CustomVirtualAdapter extends VirtualLayoutAdapter<CustomVirtualAdapter.VirtualViewHolder> {

    private Context mContext;
    private int mCount;
    private VirtualLayoutManager.LayoutParams mLayoutParams;

    private CustomVirtualAdapter(@NonNull VirtualLayoutManager layoutManager) {
        super(layoutManager);
    }

    public CustomVirtualAdapter(@NonNull VirtualLayoutManager layoutManager, @NonNull Context mContext, @NonNull int mCount) {
        this(layoutManager, mContext, mCount, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
    }

    public CustomVirtualAdapter(@NonNull VirtualLayoutManager layoutManager, @NonNull Context mContext, @NonNull int mCount, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        this(layoutManager);
        this.mContext = mContext;
        this.mCount = mCount;
        this.mLayoutParams = layoutParams;
    }

    @Override
    public CustomVirtualAdapter.VirtualViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new CustomVirtualAdapter.VirtualViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomVirtualAdapter.VirtualViewHolder holder, final int position) {
//            holder.itemView.setLayoutParams(mLayoutParams);
        // TODO: 17/3/14 填坑4 此时的layoutparams为VirtualLayoutManager.LayoutParams或者继承自viewgroup的LayoutParams
//            holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(mLayoutParams));
        holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(mLayoutParams));
        //此时的position和常规的位置相同
        holder.mTextTitle.setText(Integer.toString(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "pos = " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public class VirtualViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextTitle;

        public VirtualViewHolder(View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
        }
    }
}