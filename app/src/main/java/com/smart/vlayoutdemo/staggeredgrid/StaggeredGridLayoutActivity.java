package com.smart.vlayoutdemo.staggeredgrid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.extend.InnerRecycledViewPool;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.smart.vlayoutdemo.R;
import com.smart.vlayoutdemo.linear.CustomAdapter;

import java.util.ArrayList;

public class StaggeredGridLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_staggered_grid_list);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        layoutManager.setOrientation(VirtualLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        InnerRecycledViewPool recycledViewPool = new InnerRecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 20);
        mRecyclerView.setRecycledViewPool(recycledViewPool);

        //加载数据
        delegateAdapter(layoutManager);
//        virtualAdapter(layoutManager);
    }

    //delegeteAdapter方式加载数据
    private void delegateAdapter(VirtualLayoutManager manager) {
        DelegateAdapter delegateAdapter = new DelegateAdapter(manager);
        ArrayList<DelegateAdapter.Adapter> adapters = new ArrayList<>();
        StaggeredGridLayoutHelper layoutHelper = new StaggeredGridLayoutHelper();
        //设置几列
        layoutHelper.setLane(3);
//        layoutHelper.setGap(10);
        layoutHelper.setVGap(20);
        layoutHelper.setHGap(30);
        layoutHelper.setMargin(20, 10, 10, 10);
        layoutHelper.setPadding(10, 10, 20, 10);
        layoutHelper.setBgColor(0xFF86345A);
        adapters.add(new CustomAdapter(this, layoutHelper, 27) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
                if (position % 2 == 0) {
                    //设置宽高比
                    layoutParams.mAspectRatio = 1.0f;
                    holder.mTextTitle.setBackgroundColor(0xff00f0f0);
                } else {
                    layoutParams.height = 340 + position % 7 * 20;
                    holder.mTextTitle.setBackgroundColor(0xFFF5A623);
                }
                holder.itemView.setLayoutParams(layoutParams);
            }
        });
        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);
    }

    //自定义VirtualLayoutAdapter
    private void virtualAdapter(VirtualLayoutManager manager) {

    }
}
