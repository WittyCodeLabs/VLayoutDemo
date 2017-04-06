package com.smart.vlayoutdemo.fix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.extend.InnerRecycledViewPool;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.smart.vlayoutdemo.R;
import com.smart.vlayoutdemo.linear.CustomAdapter;

import java.util.ArrayList;

public class FixLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_fix_list);
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
        FixLayoutHelper layoutHelper1 = new FixLayoutHelper(30, 30);
//        layoutHelper1.setMargin(10, 20, 10, 20);
//        layoutHelper1.setBgColor(0xFFF5A623);
//        layoutHelper1.setItemCount(3);
        FixLayoutHelper layoutHelper2 = new FixLayoutHelper(260, 260);
        FixLayoutHelper layoutHelper3 = new FixLayoutHelper(FixLayoutHelper.BOTTOM_RIGHT, 260, 260);
        FixLayoutHelper layoutHelper4 = new FixLayoutHelper(FixLayoutHelper.BOTTOM_LEFT, 260, 260);
        FixLayoutHelper layoutHelper5 = new FixLayoutHelper(FixLayoutHelper.TOP_RIGHT, 260, 260);
//        adapters.add(new CustomAdapter(this, layoutHelper1, 1));
        adapters.add(new CustomAdapter(this, layoutHelper1, 1) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(200, 200);
                holder.itemView.setLayoutParams(layoutParams);
            }
        });
        adapters.add(new CustomAdapter(this, layoutHelper2, 1) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(200, 200);
                holder.itemView.setLayoutParams(layoutParams);
            }
        });
        adapters.add(new CustomAdapter(this, layoutHelper3, 1) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(200, 200);
                holder.itemView.setLayoutParams(layoutParams);
            }
        });
        adapters.add(new CustomAdapter(this, layoutHelper4, 1) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(200, 200);
                holder.itemView.setLayoutParams(layoutParams);
            }
        });
        adapters.add(new CustomAdapter(this, layoutHelper5, 1) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(200, 200);
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
