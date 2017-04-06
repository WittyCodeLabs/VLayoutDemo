package com.smart.vlayoutdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fengjh on 17/3/14.
 */

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainViewHolder> {

    private List<String> mList;
    private OnItemClickListener<View> mOnItemClickListener;

    public MainListAdapter(List<String> mList) {
        this.mList = mList;
    }

    public void setOnItemClickListener(OnItemClickListener<View> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_list, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {
        holder.mTextTitle.setText(mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextTitle;

        public MainViewHolder(View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.tv_main_item_title);
        }
    }
}
