package com.owen.recyclerview;

import java.util.ArrayList;
import java.util.List;

import com.owen.recyclerview.adapter.SimpleAdater;
import com.owen.recyclerview.adapter.StaggeredAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public class StaggeredGridLayoutActivity extends Activity {

	private RecyclerView mRecyclerView = null;
	private List<String> mDatas = null;
	private StaggeredAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initDatas();
		initViews();
		
		mAdapter = new StaggeredAdapter(this, mDatas);
		mRecyclerView.setAdapter(mAdapter);
		
		// 设置RecyclerView的布局方向为垂直方向
		mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
		
		// 设置RecyclerView的item间的分割线
//		mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
	}

	private void initDatas() {
		mDatas = new ArrayList<String>();
		
		for (int i = 'A'; i <= 'z'; i++) {
			mDatas.add("" + ((char) i) );
		}
	}

	private void initViews() {
		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
	}
	
}
