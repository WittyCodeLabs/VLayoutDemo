package com.smart.vlayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smart.vlayoutdemo.column.ColumnLayoutActivity;
import com.smart.vlayoutdemo.fix.FixLayoutActivity;
import com.smart.vlayoutdemo.floatl.FloatLayoutActivity;
import com.smart.vlayoutdemo.grid.GridLayoutActivity;
import com.smart.vlayoutdemo.linear.LinearLayoutActivity;
import com.smart.vlayoutdemo.oneplusn.OnePlusNLayoutActivity;
import com.smart.vlayoutdemo.scrollfix.ScrollFixLayoutActivity;
import com.smart.vlayoutdemo.single.SingleLayoutActivity;
import com.smart.vlayoutdemo.staggeredgrid.StaggeredGridLayoutActivity;
import com.smart.vlayoutdemo.sticky.StickyLayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mList;

    private String[] mTitles = new String[]{
            LinearLayoutActivity.class.getSimpleName(),
            GridLayoutActivity.class.getSimpleName(),
            FixLayoutActivity.class.getSimpleName(),
            ScrollFixLayoutActivity.class.getSimpleName(),
            FloatLayoutActivity.class.getSimpleName(),
            ColumnLayoutActivity.class.getSimpleName(),
            SingleLayoutActivity.class.getSimpleName(),
            OnePlusNLayoutActivity.class.getSimpleName(),
            StickyLayoutActivity.class.getSimpleName(),
            StaggeredGridLayoutActivity.class.getSimpleName()
    };
    private Class[] mActivities = new Class[]{
            LinearLayoutActivity.class,
            GridLayoutActivity.class,
            FixLayoutActivity.class,
            ScrollFixLayoutActivity.class,
            FloatLayoutActivity.class,
            ColumnLayoutActivity.class,
            SingleLayoutActivity.class,
            OnePlusNLayoutActivity.class,
            StickyLayoutActivity.class,
            StaggeredGridLayoutActivity.class

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_list);
        mList = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mList.add(mTitles[i]);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        MainListAdapter mainListAdapter = new MainListAdapter(mList);
        mRecyclerView.setAdapter(mainListAdapter);
        mainListAdapter.setOnItemClickListener(new OnItemClickListener<View>() {
            @Override
            public void onItemClick(View item, int position) {
                Intent intent = new Intent(MainActivity.this, mActivities[position]);
                startActivity(intent);
            }
        });
    }
}
