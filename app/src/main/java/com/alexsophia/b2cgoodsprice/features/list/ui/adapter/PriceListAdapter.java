package com.alexsophia.b2cgoodsprice.features.list.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;

import java.util.List;

import greendao.Goods;

/**
 * PriceListAdapter
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class PriceListAdapter extends BaseAdapter {

    private final Context mContext;
    private List<Goods> mGoods;

    public PriceListAdapter(Context context, List<Goods> goods) {
        this.mContext = context;
        this.mGoods = goods;
    }

    public void updateListView(List<Goods> goods) {
        this.mGoods = goods;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mGoods.size();
    }

    @Override
    public Goods getItem(int position) {
        return mGoods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.list_price_item,
                    null);
            viewHolder.mBrand = (TextView) view.findViewById(R.id.tv_main_brand);
            viewHolder.mName = (TextView) view.findViewById(R.id.tv_main_name);
            viewHolder.mStandard = (TextView) view.findViewById(R.id.tv_main_standard);
            viewHolder.mCheapestOnline = (TextView) view.findViewById(R.id.tv_main_cheapest_online);
            viewHolder.mCheapestOffline = (TextView) view.findViewById(R.id
                    .tv_main_cheapest_offline);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        /**
         * display data
         */
        Goods goods = mGoods.get(position);
        viewHolder.mBrand.setText(goods.getBrand());
        viewHolder.mName.setText(goods.getName());
        viewHolder.mStandard.setText(goods.getStandard());
        viewHolder.mCheapestOnline.setText(String.valueOf(goods.getCheapest_online()));
        viewHolder.mCheapestOffline.setText(String.valueOf(goods.getCheapest_offline()));

        return view;
    }

    private class ViewHolder {
        TextView mBrand;
        TextView mName;
        TextView mStandard;
        TextView mCheapestOnline;
        TextView mCheapestOffline;
    }
}
