package com.smart.vlayoutdemo.sticky;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.extend.InnerRecycledViewPool;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.smart.vlayoutdemo.R;
import com.smart.vlayoutdemo.linear.CustomAdapter;

import java.util.ArrayList;

public class StickyLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    //DelegateAdapter方法加载数据
    private ArrayList<DelegateAdapter.Adapter> adapters;
    private DelegateAdapter delegateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_sticky_list);
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
//        delegateAdapter(50, true);
        delegateAdapter(100, true);
        addLinearDelegateAdapter();
        delegateAdapter(300, false);
        addLinearDelegateAdapter();
        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);

//        virtualAdapter();
    }

    //delegeteAdapter方式加载数据
    private void delegateAdapter(int offset, boolean stickyStart) {
//        StickyLayoutHelper layoutHelper = new StickyLayoutHelper(false);
        StickyLayoutHelper layoutHelper = new StickyLayoutHelper(stickyStart);
        layoutHelper.setOffset(offset);
        layoutHelper.setAspectRatio(4);
        adapters.add(new CustomAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.mTextTitle.setBackgroundColor(0xff00f0f0);
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
