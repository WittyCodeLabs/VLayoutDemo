package com.smart.vlayoutdemo.floatl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.extend.InnerRecycledViewPool;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.smart.vlayoutdemo.R;
import com.smart.vlayoutdemo.linear.CustomAdapter;

import java.util.ArrayList;

public class FloatLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_float_list);
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
        FloatLayoutHelper layoutHelper = new FloatLayoutHelper();
        layoutHelper.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);
        layoutHelper.setDefaultLocation(100, 400);
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(150, 150);
        adapters.add(new CustomAdapter(this,layoutHelper,1,layoutParams));
        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);
    }

    //自定义VirtualLayoutAdapter
    private void virtualAdapter(VirtualLayoutManager manager) {

    }
}
