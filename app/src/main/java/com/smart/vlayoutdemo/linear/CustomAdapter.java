package com.smart.vlayoutdemo.linear;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.smart.vlayoutdemo.R;

/**
 * Created by fengjh on 17/3/14.
 */

public class CustomAdapter extends DelegateAdapter.Adapter<CustomAdapter.LinearViewHolder> {

    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private int mCount;
    private VirtualLayoutManager.LayoutParams mLayoutParams;

    //仅仅适合垂直方向列表，如果是水平方向，自行修改LayoutParams
    public CustomAdapter(Context mContext, LayoutHelper mLayoutHelper, int count) {
        this(mContext, mLayoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
    }

    public CustomAdapter(Context mContext, LayoutHelper mLayoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        this.mContext = mContext;
        this.mLayoutHelper = mLayoutHelper;
        this.mCount = count;
        this.mLayoutParams = layoutParams;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public CustomAdapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new CustomAdapter.LinearViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomAdapter.LinearViewHolder holder, final int position) {
        // TODO: 17/3/14  填坑1
        //必须是new VirtualLayoutManager.LayoutParams(mLayoutParams)，而不能是mLayoutParams
        //此时的宽高可以通过setAspectRatio来控制或者手动设置setLayoutParams
        holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(mLayoutParams));
        //因为position和以前的不一样，所以在从数据集合里边去数据时，也不能使用position了
//            holder.mTextTitle.setText(Integer.toString(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 17/3/14 填坑2
                //position在当前的adapter里面，代表了各自LayoutHelper里面item的位置，通过holder.getLayoutPosition()才能获取到在列表的真正位置
                Toast.makeText(mContext, "pos=" + holder.getLayoutPosition(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(mContext, "pos=" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onBindViewHolderWithOffset(CustomAdapter.LinearViewHolder holder, int position, int offsetTotal) {
        //position在当前的adapter里面，代表了各自LayoutHelper里面item的位置，通过holder.getLayoutPosition()或者offsetTotal才能获取到在列表的真正位置
//            holder.mTextTitle.setText(Integer.toString(position));
        holder.mTextTitle.setText(Integer.toString(offsetTotal));
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public class LinearViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextTitle;

        public LinearViewHolder(View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
        }
    }
}
