package com.owen.recyclerview.adapter;

import java.util.ArrayList;
import java.util.List;

import com.owen.recyclerview.R;

import android.R.integer;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHodler> {

	private Context context = null;
	private List<String> datas = null;
	private LayoutInflater layoutInflater = null;
	private List<Integer> mHeights = null;
	
	public StaggeredAdapter(Context context, List<String> datas) {
		this.context = context;
		this.datas = datas;
		layoutInflater = LayoutInflater.from(context);
		
		mHeights = new ArrayList<Integer>();
		for (int i = 0; i < datas.size(); i++) {
			mHeights.add((int) (100 + Math.random()*300));
		}
	}

	@Override
	public int getItemCount() {
		return datas.size();
	}

	@Override
	public void onBindViewHolder(MyViewHodler holder, int position) {
		LayoutParams layoutParams = holder.itemView.getLayoutParams();
		layoutParams.height = mHeights.get(position);
		holder.itemView.setLayoutParams(layoutParams);
		
		holder.tv.setText(datas.get(position));
	}

	@Override
	public MyViewHodler onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		View itemView = layoutInflater.inflate(R.layout.item_single_text, viewGroup, false);
		MyViewHodler viewHodler = new MyViewHodler(itemView);
		
		return viewHodler;
	}
	
	class MyViewHodler extends ViewHolder {

		TextView tv = null;
		
		public MyViewHodler(View itemView) {
			super(itemView);
			
			tv = (TextView) itemView.findViewById(R.id.tv);
		}
		
	}
	
}
