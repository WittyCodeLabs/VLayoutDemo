package com.smart.vlayoutdemo.column;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.extend.InnerRecycledViewPool;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.smart.vlayoutdemo.R;
import com.smart.vlayoutdemo.linear.CustomAdapter;

import java.util.ArrayList;

public class ColumnLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_column_list);
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
        ColumnLayoutHelper layoutHelper1 = new ColumnLayoutHelper();
        layoutHelper1.setWeights(new float[]{30, Float.NaN, 20, 10, 20});
        ColumnLayoutHelper layoutHelper2 = new ColumnLayoutHelper();
        layoutHelper2.setWeights(new float[]{10, Float.NaN});
        ColumnLayoutHelper layoutHelper3 = new ColumnLayoutHelper();
        layoutHelper3.setWeights(new float[]{Float.NaN, 30, 30});
        adapters.add(new CustomAdapter(this, layoutHelper1, 5) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position % 2 == 0)
                    holder.mTextTitle.setBackgroundColor(0xFFF5A623);
            }
        });
        adapters.add(new CustomAdapter(this, layoutHelper2, 5) {
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position % 2 == 0)
                    holder.mTextTitle.setBackgroundColor(0xff00f0f0);
            }
        });
        adapters.add(new CustomAdapter(this,layoutHelper3,5){
            @Override
            public void onBindViewHolder(LinearViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position % 2 == 0)
                    holder.mTextTitle.setBackgroundColor(0xFFF5A623);
            }
        });
        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);
    }

    //自定义VirtualLayoutAdapter
    private void virtualAdapter(VirtualLayoutManager manager) {

    }
}
