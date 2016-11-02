package com.alexsophia.b2cgoodsprice.features.list.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.app.MyApplication;

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
            view = LayoutInflater.from(mContext).inflate(R.layout.list_price_item, null);
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
        final Goods goods = mGoods.get(position);
        viewHolder.mBrand.setText(goods.getGoodsBrand().getGoodsBrandName());
        viewHolder.mName.setText(goods.getGoodsName());
        viewHolder.mStandard.setText(goods.getGoodsStandard());
        viewHolder.mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.mCheapestOnline.setText(String.valueOf(goods.getCheapestOnline()));
        viewHolder.mCheapestOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(mContext);
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(R.string.list_online);
                builder.setView(input);
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double price = Double.parseDouble(input.getText().toString());
                        if (price < goods.getCheapestOnline() || goods.getCheapestOnline() == -1) {
                            goods.setCheapestOnline(price);
                            MyApplication.getInstance().getDbMaster().addOrUpdateGoods(goods);
                            notifyDataSetChanged();
                        }
                    }
                });
                builder.show();
            }
        });
        viewHolder.mCheapestOffline.setText(String.valueOf(goods.getCheapestOffline()));
        viewHolder.mCheapestOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(mContext);
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(R.string.list_offline);
                builder.setView(input);
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double price = Double.parseDouble(input.getText().toString());
                        if (price < goods.getCheapestOffline() || goods.getCheapestOffline() == -1) {
                            goods.setCheapestOffline(price);
                            MyApplication.getInstance().getDbMaster().addOrUpdateGoods(goods);
                            notifyDataSetChanged();
                        }
                    }
                });
                builder.show();
            }
        });
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
