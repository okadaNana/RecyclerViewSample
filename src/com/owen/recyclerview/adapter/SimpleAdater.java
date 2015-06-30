package com.owen.recyclerview.adapter;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.owen.recyclerview.R;

public class SimpleAdater extends RecyclerView.Adapter<SimpleAdater.MyViewHodler> {

	private Context context = null;
	private List<String> datas = null;
	private LayoutInflater layoutInflater = null;
	private SimpleAdater.OnItemClickListener itemClickListener = null;
	
	public SimpleAdater(Context context, List<String> datas) {
		this.context = context;
		this.datas = datas;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getItemCount() {
		return datas.size();
	}

	@Override
	public void onBindViewHolder(final SimpleAdater.MyViewHodler holder, final int position) {
		holder.tv.setText(datas.get(position));
		
		if (itemClickListener != null) {
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					int layoutPosition = holder.getLayoutPosition();
					itemClickListener.onItemClick(holder.itemView, layoutPosition);
				}
			});
			
			holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View v) {
					int layoutPosition = holder.getLayoutPosition();
					itemClickListener.onItemLongClick(holder.itemView, layoutPosition);
					
					return true;
				}
			});
		}
	}

	@Override
	public SimpleAdater.MyViewHodler onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		View itemView = layoutInflater.inflate(R.layout.item_single_text, viewGroup, false);
		SimpleAdater.MyViewHodler viewHodler = new SimpleAdater.MyViewHodler(itemView);
		
		return viewHodler;
	}
	
	public void addItem(int position) {
		datas.add(position, "Insert One");
		notifyItemInserted(position);
	}
	
	public void deleteItem(int position) {
		datas.remove(position);
		notifyItemRemoved(position);
	}

	public void setItemListener(OnItemClickListener listener) {
		this.itemClickListener = listener;
	}
	
	public interface OnItemClickListener {
		
		void onItemClick(View view, int position);
		void onItemLongClick(View view, int position);
		
	}
	
	public class MyViewHodler extends ViewHolder {
		
		TextView tv = null;

		public MyViewHodler(View itemView) {
			super(itemView);
			
			tv = (TextView) itemView.findViewById(R.id.tv);
		}
		
	}
	
}

