package com.alexsophia.b2cgoodsprice.features.manage.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseActivity;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.GoodsBrandManagePresenters;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.impl.GoodsBrandManagePresentersImpl;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * GoodsBrandManageActivity
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class GoodsBrandManageActivity extends BaseActivity implements GoodsBrandManagePresenters
        .View {
    @Bind(R.id.tv_manage_goods_brand_count)
    TextView mTvBrandCount;
    @Bind(R.id.lv_manage_goods_brand)
    ListView mLvBrands;
    @Bind(R.id.spinner_manage_types)
    Spinner mSpinnerTypes;
    @Bind(R.id.edtTxt_manage_new_brand_name)
    EditText mEdtTxtNewBrandName;
    @Bind(R.id.btn_manage_brand_add)
    Button mBtnBrandAdd;
    private GoodsBrandManagePresenters mPresenters;

    @Override
    protected void stop() {

    }

    @Override
    protected void destroy() {

    }

    @Override
    protected int getContentViewRes() {
        return R.layout.manage_goods_brand_activity;
    }

    @Override
    protected Activity getTarget() {
        return this;
    }

    @Override
    protected void loadData() {
        mPresenters = new GoodsBrandManagePresentersImpl(this);
        // Set Spinner Attributes
        String[] types = mPresenters.getGoodsTypes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout
                .simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerTypes.setAdapter(adapter);
        mSpinnerTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showLong(getContext(), "Select item = " + position + "; id = " + id);
                mPresenters.selectType(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void resumeData() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showProgress() {
        showLoadingProgress();
    }

    @Override
    public void hideProgress() {
        hideLoadingProgress();
    }

    @Override
    public void showError(String message) {
        ToastUtil.showLong(this, message);
    }

    @OnClick({R.id.btn_manage_brand_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_manage_brand_add:
                break;
        }
    }
}
