package com.smart.vlayoutdemo.oneplusn;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.extend.InnerRecycledViewPool;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.smart.vlayoutdemo.R;
import com.smart.vlayoutdemo.linear.CustomAdapter;

import java.util.ArrayList;

public class OnePlusNLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_plus_nlayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_one_plusn_list);
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
        OnePlusNLayoutHelper layoutHelper1 = new OnePlusNLayoutHelper();
        layoutHelper1.setBgColor(0xff876384);
        layoutHelper1.setAspectRatio(4.0f);
        layoutHelper1.setColWeights(new float[]{40f, 50f});
        layoutHelper1.setMargin(10, 20, 10, 20);
        layoutHelper1.setPadding(10, 10, 10, 10);
        OnePlusNLayoutHelper layoutHelper2 = new OnePlusNLayoutHelper();
        layoutHelper2.setBgColor(0xffef8ba3);
        layoutHelper2.setAspectRatio(2.0f);
//        layoutHelper2.setColWeights(new float[]{40f});
//        layoutHelper2.setColWeights(new float[]{40f,20f});
//        layoutHelper2.setColWeights(new float[]{40f,20f,5f});
//        layoutHelper2.setColWeights(new float[]{40f,Float.NaN,5f});
//        layoutHelper2.setColWeights(new float[]{40f,Float.NaN,5f,20f});
//        layoutHelper2.setColWeights(new float[]{40f,Float.NaN,Float.NaN,20f});
        layoutHelper2.setColWeights(new float[]{40f, Float.NaN, 20f, Float.NaN});
//        layoutHelper2.setColWeights(new float[]{40f,Float.NaN,Float.NaN,Float.NaN});
//        layoutHelper2.setColWeights(new float[]{40f,Float.NaN});
        //当前列表控制item＝3，pos＝1
        layoutHelper2.setRowWeight(20f);
        layoutHelper2.setMargin(10, 20, 10, 20);
        layoutHelper2.setPadding(10, 10, 10, 10);
        adapters.add(new CustomAdapter(this, layoutHelper1, 2) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position % 2 == 0)
                    holder.mTextTitle.setBackgroundColor(0xFFF5A623);
            }
        });
        adapters.add(new CustomAdapter(this, layoutHelper2, 4) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                // TODO: 17/3/17 注意position和实际展示的数字是不一致的
                if (position == 0)
                    holder.mTextTitle.setBackgroundColor(0xff00f0f0);
                else if (position == 1)
                    holder.mTextTitle.setBackgroundColor(0xFFF5A623);
                else if (position == 2)
                    holder.mTextTitle.setBackgroundColor(Color.rgb(135, 225, 90));
                else if (position == 3)
                    holder.mTextTitle.setBackgroundColor(0xff00f0f0);
            }
        });
        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);
    }

    //自定义VirtualLayoutAdapter
    private void virtualAdapter(VirtualLayoutManager manager) {

    }
}
