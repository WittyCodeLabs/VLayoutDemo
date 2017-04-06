package com.smart.vlayoutdemo.grid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.extend.InnerRecycledViewPool;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.smart.vlayoutdemo.R;
import com.smart.vlayoutdemo.linear.CustomAdapter;

import java.util.ArrayList;

public class GridLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_grid_list);
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
        GridLayoutHelper layoutHelper1 = new GridLayoutHelper(3);
        layoutHelper1.setItemCount(5);
        //设置行间距
        layoutHelper1.setVGap(10);
        //设置列间距
        layoutHelper1.setHGap(20);
        //设置行列间距
//        layoutHelper1.setGap(30);
        layoutHelper1.setMargin(10, 20, 10, 20);
        layoutHelper1.setBgColor(0xFFF5A623);
        GridLayoutHelper layoutHelper2 = new GridLayoutHelper(3);
        layoutHelper2.setItemCount(4);
        layoutHelper2.setVGap(20);
        layoutHelper2.setBgColor(0xFFF5A623);
        layoutHelper2.setPadding(20, 30, 20, 30);
        GridLayoutHelper layoutHelper3 = new GridLayoutHelper(3);
        layoutHelper3.setItemCount(3);
        layoutHelper3.setVGap(30);
        layoutHelper3.setBgColor(0xFFF5A623);
        layoutHelper3.setMargin(10, 20, 10, 20);
        layoutHelper3.setPadding(10, 20, 10, 20);
        adapters.add(new CustomAdapter(this,layoutHelper1,5));
        adapters.add(new CustomAdapter(this,layoutHelper2,4));
        adapters.add(new CustomAdapter(this,layoutHelper3,3));
        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);
    }

    //自定义VirtualLayoutAdapter
    private void virtualAdapter(VirtualLayoutManager manager) {

    }
}
