package com.alexsophia.b2cgoodsprice.features.add.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;

import java.util.ArrayList;
import java.util.List;

import greendao.Goods;

/**
 * GoodsNameAdapter
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class GoodsNameAdapter extends BaseAdapter {
    private String TAG = "GoodsNameAdapter";
    private final Context mContext;
    private DbMaster mDbMaster;
    private List<String> mData = new ArrayList<>();
    private long mTypeId;
    private long mBrandId;

    public GoodsNameAdapter(Context context) {
        this.mContext = context;
        mDbMaster = MyApplication.getInstance().getDbMaster();
        getData();
    }

    public void setType(long typeId) {
        this.mTypeId = typeId;
        getData();
    }

    public void setBrand(long brandId) {
        this.mBrandId = brandId;
        getData();
    }

    public void addData(String name) {
        if (!mData.contains(name)) {
            mData.add(name);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(mContext);
        tv.setText(mData.get(position));
        return tv;
    }

    private void getData() {
        LogWrapper.e(TAG, "getData: ");
        List<Goods> goods = mDbMaster.getGoodsList(mTypeId, mBrandId);
        mData.clear();
        for (Goods good : goods) {
            mData.add(good.getGoodsName());
        }
        notifyDataSetChanged();
    }
}