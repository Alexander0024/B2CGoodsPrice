package com.alexsophia.b2cgoodsprice.share.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * ListView，GridView的万能适配器
 * @author liuweiping
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
	private Context mContext;
	private List<T> mData;
	private int mItemLayoutId;

	protected CommonAdapter(Context context, int itemLayoutId, List<T> data) {
		this.mContext = context;
		this.mData = data;
		this.mItemLayoutId = itemLayoutId;
	}

	/**
	 * 使用数据集刷新adapter
	 */
	public void refreshAdapter(List<T> data) {
		if (data != null && !data.isEmpty()) {
			this.mData = data;
		} else {
			if (this.mData != null)
				this.mData.clear();
		}
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public T getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = ViewHolder.get(convertView, mContext,
				mItemLayoutId, parent, position);
		covertView(viewHolder, getItem(position));
		return viewHolder.getConvertView();
	}
	/**
	 * 暴露给外面实现对item的view进行操作的接口
	 * @param viewholder
	 * @param t
	 */
	public abstract void covertView(ViewHolder viewholder, T t);
}
