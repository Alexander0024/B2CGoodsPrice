package com.alexsophia.b2cgoodsprice.features.add.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * AttrAdapter
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class AttrAdapter extends BaseAdapter {
    private String TAG = "AttrAdapter";
    private final Context mContext;
    private final ATTR_TYPE mAttrType;
    private List<String> mData;
    private String mExtra = "";

    public AttrAdapter(Context context, ATTR_TYPE attrType) {
        this.mContext = context;
        this.mAttrType = attrType;
        this.mData = getData();
    }

    public void setExtra(String extra) {
        this.mExtra = extra;
        this.mData = getData();
        notifyDataSetChanged();
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

    public enum ATTR_TYPE {
        TYPE, BRAND, NAME
    }

    private List<String> getData() {
        LogWrapper.e(TAG, "getData: " + mAttrType.toString());
        List<String> data;
        if (mAttrType.equals(ATTR_TYPE.TYPE)) {
            data = MyApplication.getInstance().getDataPresenters().getTypes();
            LogWrapper.e(TAG, "get type with " + data.size() + " items.");
        } else if (mAttrType.equals(ATTR_TYPE.BRAND)) {
            LogWrapper.e(TAG, "get brand which under type = " + mExtra);
            data = MyApplication.getInstance().getDataPresenters().getBrand(mExtra);
        } else if (mAttrType.equals(ATTR_TYPE.NAME)) {
            LogWrapper.e(TAG, "get name which under brand = " + mExtra);
            data = MyApplication.getInstance().getDataPresenters().getName(mExtra);
        } else {
            data = new ArrayList<>();
        }
        return data;
    }
}