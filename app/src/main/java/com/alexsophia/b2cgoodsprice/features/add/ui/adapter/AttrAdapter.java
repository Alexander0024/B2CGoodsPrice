package com.alexsophia.b2cgoodsprice.features.add.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * AttrAdapter
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class AttrAdapter extends BaseAdapter {
    private final Context mContext;
    private List<String> mData;

    public AttrAdapter(Context context, ATTR_TYPE attr_type) {
        this.mContext = context;
        this.mData = getData(attr_type);
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
        BRAND, NAME;
    }

    private List<String> getData(ATTR_TYPE attr_type) {
        List<String> mList = new ArrayList<String>() {
            {
                add("常用选项 one");
                add("常用选项 two");
                add("常用选项 three");
                add("常用选项 four");
                add("常用选项 five");
                add("常用选项 six");
                add("常用选项 7怎么拼来着？");
            }
        };
        return mList;
    }
}
