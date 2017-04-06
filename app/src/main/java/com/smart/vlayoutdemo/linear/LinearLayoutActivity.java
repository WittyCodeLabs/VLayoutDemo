package com.smart.vlayoutdemo.linear;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.extend.InnerRecycledViewPool;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.smart.vlayoutdemo.R;

import java.util.ArrayList;

public class LinearLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_linear_list);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        layoutManager.setOrientation(VirtualLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

//        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        //设置回收复用池大小,（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        //第一个参数：需要回收复用的视图类型，第二个参数：回收复用池大小
//        recycledViewPool.setMaxRecycledViews(0, 20);
//        recyclerView.setRecycledViewPool(recycledViewPool);
        InnerRecycledViewPool recycledViewPool = new InnerRecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 20);
        mRecyclerView.setRecycledViewPool(recycledViewPool);
        //加载数据
//        delegateAdapter(layoutManager);
        virtualAdapter(layoutManager);
    }

    //delegeteAdapter方式加载数据
    private void delegateAdapter(VirtualLayoutManager manager) {
        DelegateAdapter delegateAdapter = new DelegateAdapter(manager);
//        DelegateAdapter delegateAdapter = new DelegateAdapter(manager, true);
        //多个adapter先设置recyclerView的adapter
//        recyclerView.setAdapter(delegateAdapter);
        ArrayList<DelegateAdapter.Adapter> adapters = new ArrayList<>();
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
        delegateAdapter.setAdapters(adapters);
        // TODO: 17/3/14 填坑3 如何是设置单个adapter，可以采用DelegateAdapter的addAdapter方法，
        // 但是recyclerView.setAdapter方法必须在delegateAdapter.addAdapter方法之后
        //如果采用DelegateAdapter的setAdapters方法，可以设置1个或多个adapter,此时recyclerView.setAdapter方法可以放在delegateAdapter.addAdapter之前
//        delegateAdapter.addAdapter(new CustomAdapter(this, layoutHelper1, 6));
        //单个或多个adapter后设置recyclerView的adapter
        mRecyclerView.setAdapter(delegateAdapter);
    }

    //自定义VirtualLayoutAdapter
    private void virtualAdapter(VirtualLayoutManager manager) {
        //构造 layoutHelper 列表
        ArrayList<LayoutHelper> layoutHelpers = new ArrayList<>();
        LinearLayoutHelper layoutHelper1 = new LinearLayoutHelper();
        layoutHelper1.setItemCount(2);
        //设置宽高比
        layoutHelper1.setAspectRatio(2.0f);
        //设置分割线高度
        layoutHelper1.setDividerHeight(20);
        //设置背景颜色
        layoutHelper1.setBgColor(0xFFF5A623);
        LinearLayoutHelper layoutHelper2 = new LinearLayoutHelper();
        layoutHelper2.setItemCount(3);
        layoutHelper2.setAspectRatio(3.0f);
        layoutHelper2.setDividerHeight(10);
        layoutHelper2.setBgColor(0xFFF5A623);
        layoutHelper2.setMargin(10, 20, 10, 20);
        LinearLayoutHelper layoutHelper3 = new LinearLayoutHelper();
        layoutHelper3.setItemCount(4);
        layoutHelper3.setAspectRatio(4.0f);
        layoutHelper3.setDividerHeight(5);
        layoutHelper3.setBgColor(0xFFF5A623);
        layoutHelper3.setPadding(10, 20, 10, 20);
        layoutHelpers.add(layoutHelper1);
        layoutHelpers.add(layoutHelper2);
        layoutHelpers.add(layoutHelper3);
        //将 layoutHelper 列表传递给 adapter，第三个参数是item的总个数，为layouthelper里面各个layouthelper的item总和 9 = 2 + 3 + 4
        CustomVirtualAdapter customVirtualAdapter = new CustomVirtualAdapter(manager, this, 9);
        customVirtualAdapter.setLayoutHelpers(layoutHelpers);
        //将 adapter 设置给 recyclerView
        mRecyclerView.setAdapter(customVirtualAdapter);
    }
}
