package com.owen.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.owen.recyclerview.adapter.SimpleAdater;

public class MainActivity extends Activity {
	
	private RecyclerView mRecyclerView = null;
	private List<String> mDatas = null;
	private SimpleAdater mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initDatas();
		initViews();
		
		mAdapter = new SimpleAdater(this, mDatas);
		mAdapter.setItemListener(new SimpleAdater.OnItemClickListener() {
			
			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(MainActivity.this, "click: " + position, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onItemLongClick(View view, int position) {
				Toast.makeText(MainActivity.this, "Long Click: " + position, Toast.LENGTH_SHORT).show();
			}
		});
		
		mRecyclerView.setAdapter(mAdapter);
		
		// 设置RecyclerView的布局方向为垂直方向
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		
		// 设置RecyclerView的item间的分割线
//		mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
		
		// 为RecyclerView的item添加动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		switch (id) {
		case R.id.action_add:
			mAdapter.addItem(1);
			break;
		case R.id.action_delete:
			mAdapter.deleteItem(1);
			break;
		case R.id.action_listview:
			mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
			break;
		case R.id.action_gridview:
			mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
			break;
		case R.id.action_horizontal_gridview:
			mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, 
					StaggeredGridLayoutManager.HORIZONTAL));
			break;
		case R.id.action_staggered:
			Intent intent = new Intent(MainActivity.this, StaggeredGridLayoutActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
