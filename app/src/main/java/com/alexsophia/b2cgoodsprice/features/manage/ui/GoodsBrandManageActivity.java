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
import com.alexsophia.b2cgoodsprice.share.SimpleEditTextBox;
import com.alexsophia.b2cgoodsprice.share.adapters.CommonAdapter;
import com.alexsophia.b2cgoodsprice.share.adapters.ViewHolder;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import greendao.GoodsBrand;

/**
 * GoodsBrandManageActivity
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class GoodsBrandManageActivity extends BaseActivity implements GoodsBrandManagePresenters
        .View {
    @Bind(R.id.tv_manage_goods_brand_count)
    TextView mTvBrandCount; // Count
    @Bind(R.id.lv_manage_goods_brand)
    ListView mLvBrands; // ListView
    @Bind(R.id.spinner_manage_types)
    Spinner mSpinnerTypes; // 类别下拉选择
    @Bind(R.id.edtTxt_manage_new_brand_name)
    EditText mEdtTxtNewBrandName; // 新厂商名字
    @Bind(R.id.btn_manage_brand_add)
    Button mBtnBrandAdd; // 添加按键
    private GoodsBrandManagePresenters mPresenters;
    private CommonAdapter<GoodsBrand> mAdapter;

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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout
                .simple_spinner_item, mPresenters.getGoodsTypes());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerTypes.setAdapter(adapter);
        mSpinnerTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenters.selectType(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Set ListView Attributes
        mAdapter = new CommonAdapter<GoodsBrand>(this, R.layout.manage_goods_brand_item,
                mPresenters.getGoodsBrands()) {
            @Override
            public void covertView(ViewHolder viewholder, final GoodsBrand goodsBrand) {
                // 厂商ID
                viewholder.setText(R.id.tv_manage_goods_brand_id, String.valueOf(goodsBrand
                        .getGoodsBrandId()));
                // 厂商所属分类名称
                viewholder.setText(R.id.tv_manage_goods_brand_type_belows, goodsBrand
                        .getGoodsType().getGoodsTypeName());
                // 厂商名称
                viewholder.setText(R.id.tv_manage_goods_brand_name, goodsBrand.getGoodsBrandName());
                viewholder.setClickListener(R.id.tv_manage_goods_brand_name, new View
                        .OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new SimpleEditTextBox(getContext(), "更新厂商名称", "", goodsBrand
                                .getGoodsBrandName(), new SimpleEditTextBox.OnClickListener() {
                            @Override
                            public void onPositiveButtonClicked(String value) {
                                goodsBrand.setGoodsBrandName(value);
                                mPresenters.updateBrand(goodsBrand);
                            }
                        });
                    }
                });
                // 厂商下物品数目
                viewholder.setText(R.id.tv_manage_goods_brand_goods_count, String.valueOf
                        (goodsBrand.getGoodsList().size()));
            }
        };
        mLvBrands.setAdapter(mAdapter);
    }

    @Override
    protected void resumeData() {
        refreshUI();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getBrandName() {
        return mEdtTxtNewBrandName.getText().toString().trim();
    }

    @Override
    public void onAddNewBrandSuccess(long id) {
        ToastUtil.showLong(this, "添加厂商成功 ID = " + id);
        refreshUI();
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

    @Override
    public void refreshUI() {
        mEdtTxtNewBrandName.setText("");
        mTvBrandCount.setText(getString(R.string.total_count, mPresenters.getGoodsBrands().size()));
        mAdapter.refreshAdapter(mPresenters.getGoodsBrands());
    }

    @OnClick({R.id.btn_manage_brand_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_manage_brand_add:
                mPresenters.addNewBrand();
                break;
        }
    }
}
