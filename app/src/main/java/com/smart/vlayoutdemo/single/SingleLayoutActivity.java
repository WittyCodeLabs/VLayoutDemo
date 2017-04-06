package com.smart.vlayoutdemo.single;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.extend.InnerRecycledViewPool;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.smart.vlayoutdemo.R;
import com.smart.vlayoutdemo.linear.CustomAdapter;

import java.util.ArrayList;

public class SingleLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_single_list);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        layoutManager.setOrientation(VirtualLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        InnerRecycledViewPool recycledViewPool = new InnerRecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 20);
        mRecyclerView.setRecycledViewPool(recycledViewPool);

        //DelegateAdapter加载数据
        delegateAdapter(layoutManager);
    }

    //delegeteAdapter方式加载数据
    private void delegateAdapter(VirtualLayoutManager manager) {
        DelegateAdapter delegateAdapter = new DelegateAdapter(manager);
        ArrayList<DelegateAdapter.Adapter> adapters = new ArrayList<>();
        SingleLayoutHelper layoutHelper1 = new SingleLayoutHelper();
        layoutHelper1.setBgColor(Color.rgb(135, 225, 90));
        layoutHelper1.setAspectRatio(4);
        layoutHelper1.setMargin(10, 20, 10, 20);
        layoutHelper1.setPadding(20, 10, 20, 10);
        SingleLayoutHelper layoutHelper2 = new SingleLayoutHelper();
        layoutHelper2.setBgColor(0xFFF5A623);
        layoutHelper2.setAspectRatio(2);
        layoutHelper2.setMargin(10, 20, 10, 20);
        layoutHelper2.setPadding(20, 10, 20, 10);
//        adapters.add(new CustomAdapter(this, layoutHelper1, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));
        adapters.add(new CustomAdapter(this, layoutHelper1, 1));
        adapters.add(new CustomAdapter(this, layoutHelper2, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));

        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);
    }

    //自定义VirtualLayoutAdapter
    private void virtualAdapter(VirtualLayoutManager manager) {

    }
}
