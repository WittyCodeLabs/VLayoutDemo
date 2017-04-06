package com.smart.vlayoutdemo.scrollfix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.extend.InnerRecycledViewPool;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.smart.vlayoutdemo.R;
import com.smart.vlayoutdemo.linear.CustomAdapter;

import java.util.ArrayList;

public class ScrollFixLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    //DelegateAdapter方法加载数据
    private ArrayList<DelegateAdapter.Adapter> adapters;
    private DelegateAdapter delegateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_fix_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_scroll_fix_list);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        layoutManager.setOrientation(VirtualLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        InnerRecycledViewPool recycledViewPool = new InnerRecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 20);
        mRecyclerView.setRecycledViewPool(recycledViewPool);


        //DelegateAdapter加载数据
        delegateAdapter = new DelegateAdapter(layoutManager);
        adapters = new ArrayList<>();
        addLinearDelegateAdapter();
        delegateAdapter();
        addLinearDelegateAdapter();
        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);

//        virtualAdapter();
    }

    //delegeteAdapter方式加载数据
    private void delegateAdapter() {
        ScrollFixLayoutHelper layoutHelper1 = new ScrollFixLayoutHelper(20, 20);
        ScrollFixLayoutHelper layoutHelper2 = new ScrollFixLayoutHelper(230, 230);
        layoutHelper2.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);
        ScrollFixLayoutHelper layoutHelper3 = new ScrollFixLayoutHelper(430, 430);
        layoutHelper3.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);
        ScrollFixLayoutHelper layoutHelper4 = new ScrollFixLayoutHelper(630, 630);
        adapters.add(new CustomAdapter(this, layoutHelper1, 1) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(200, 200);
                holder.mTextTitle.setBackgroundColor(0xFFF5A623);
                holder.itemView.setLayoutParams(layoutParams);
            }
        });
        adapters.add(new CustomAdapter(this, layoutHelper2, 1) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(200, 200);
                holder.mTextTitle.setBackgroundColor(0xFFF5A623);
                holder.itemView.setLayoutParams(layoutParams);
            }
        });
        adapters.add(new CustomAdapter(this, layoutHelper3, 1) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(200, 200);
                holder.mTextTitle.setBackgroundColor(0xFFF5A623);
                holder.itemView.setLayoutParams(layoutParams);
            }
        });
        adapters.add(new CustomAdapter(this, layoutHelper4, 1) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(200, 200);
                holder.mTextTitle.setBackgroundColor(0xFFF5A623);
                holder.itemView.setLayoutParams(layoutParams);
            }
        });
    }

    //自定义VirtualLayoutAdapter
    private void virtualAdapter() {

    }

    private void addLinearDelegateAdapter() {
        LinearLayoutHelper layoutHelper1 = new LinearLayoutHelper();
        //设置宽高比
        layoutHelper1.setAspectRatio(2.0f);
        //设置分割线高度
        layoutHelper1.setDividerHeight(20);
        //设置背景颜色
        layoutHelper1.setBgColor(0xFFF5A623);
        LinearLayoutHelper layoutHelper2 = new LinearLayoutHelper();
        layoutHelper2.setAspectRatio(3.0f);
        layoutHelper2.setDividerHeight(10);
        layoutHelper2.setBgColor(0xFFF5A623);
        layoutHelper2.setMargin(10, 20, 10, 20);
        LinearLayoutHelper layoutHelper3 = new LinearLayoutHelper();
        layoutHelper3.setAspectRatio(4.0f);
        layoutHelper3.setDividerHeight(5);
        layoutHelper3.setBgColor(0xFFF5A623);
        layoutHelper3.setPadding(10, 20, 10, 20);
        adapters.add(new CustomAdapter(this, layoutHelper1, 3));
        adapters.add(new CustomAdapter(this, layoutHelper2, 3));
        adapters.add(new CustomAdapter(this, layoutHelper3, 3));
    }
}
