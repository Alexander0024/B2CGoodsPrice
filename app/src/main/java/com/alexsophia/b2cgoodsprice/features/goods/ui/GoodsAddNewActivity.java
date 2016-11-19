package com.alexsophia.b2cgoodsprice.features.goods.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.goods.presenters.GoodsAddNewPresenters;
import com.alexsophia.b2cgoodsprice.features.goods.presenters.impl.GoodsAddNewPresentersImpl;
import com.alexsophia.b2cgoodsprice.features.goods.ui.adapter.GoodsNameAdapter;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseActivity;
import com.alexsophia.b2cgoodsprice.share.DropEditText;
import com.alexsophia.b2cgoodsprice.share.events.UpdateGoodsEvents;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Add new goods page
 * <p>
 * Created by Alexander on 2016/11/19.
 */
public class GoodsAddNewActivity extends BaseActivity implements GoodsAddNewPresenters.View {
    @Bind(R.id.spinner_add_type)
    Spinner mSpinnerType; // 类别的下拉列表
    @Bind(R.id.spinner_add_brand)
    Spinner mSpinnerBrand; // 厂家的下拉列表
    @Bind(R.id.dropEdtTxt_add_name)
    DropEditText mDropEdtTxtName; // 名称的下拉列表
    @Bind(R.id.edtTxt_add_standard)
    EditText mEdtTxtStandard; // 规格信息
    @Bind(R.id.rg_add_type_select)
    RadioGroup mRgAddTypeSelect; // 价格选择框体
    @Bind(R.id.rb_add_online)
    RadioButton mRbAddOnline; // Online Price
    @Bind(R.id.rb_add_offline)
    RadioButton mRbAddOffline; // Offline Price
    @Bind(R.id.edtTxt_add_price)
    EditText mEdtTxtPrice; // 价格输入框
    @Bind(R.id.btn_add_submit)
    Button mBtnSubmit; // 提交按钮
    @Bind(R.id.btn_add_reset)
    Button mBtnReset; // 重置按钮
    private static final String TAG = "GoodsAddNewActivity";
    private GoodsAddNewPresenters mPresenters;
    private GoodsNameAdapter mNameAdapter;

    @Override
    protected void stop() {

    }

    @Override
    protected void destroy() {

    }

    @Override
    protected int getContentViewRes() {
        return R.layout.goods_add_new_activity;
    }

    @Override
    protected Activity getTarget() {
        return this;
    }

    @Override
    protected void loadData() {
        LogWrapper.e(TAG, "loadData: ");
        mPresenters = new GoodsAddNewPresentersImpl(this);
        // Type
        setSpinnerType();
        mSpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenters.selectType(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Brand
        setSpinnerBrand(1);
        mSpinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenters.selectBrand(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Name
        mNameAdapter = new GoodsNameAdapter(getContext());
        mDropEdtTxtName.setAdapter(mNameAdapter);
    }

    @OnClick({R.id.btn_add_submit, R.id.btn_add_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_submit:
                LogWrapper.e(TAG, "Submit click!");
                mNameAdapter.addData(getName());
                mPresenters.addNew();
                refreshUI();
                break;
            case R.id.btn_add_reset:
                LogWrapper.e(TAG, "Reset click!");
                refreshUI();
                break;
        }
    }

    @Override
    protected void resumeData() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getName() {
        return mDropEdtTxtName.getText();
    }

    @Override
    public String getStandard() {
        return mEdtTxtStandard.getText().toString();
    }

    @Override
    public boolean getOnlineOffline() {
        return mRbAddOnline.isChecked();
    }

    @Override
    public double getPrice() {
        return Double.parseDouble(mEdtTxtPrice.getText().toString());
    }

    @Override
    public void onTypeSelected(Long goodsTypeId) {
        mNameAdapter.setType(goodsTypeId);
        mDropEdtTxtName.setAdapter(mNameAdapter);
        // 选择类别后刷新厂家
        setSpinnerBrand(goodsTypeId);
    }

    @Override
    public void onBrandSelected(Long goodsBrandId) {
        mNameAdapter.setBrand(goodsBrandId);
        mDropEdtTxtName.setAdapter(mNameAdapter);
    }

    @Override
    public void onAddGoodsSuccess(long id) {
        ToastUtil.showLong(getContext(), "添加成功！id = " + id);
        EventBus.getDefault().post(new UpdateGoodsEvents());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void refreshUI() {
        mEdtTxtStandard.setText("");
        mRbAddOnline.setSelected(true);
        mEdtTxtPrice.setText("");
    }

    private void setSpinnerType() {
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getContext(), android.R.layout
                .simple_spinner_item, mPresenters.getGoodsTypes());
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerType.setAdapter(typeAdapter);
    }

    private void setSpinnerBrand(long goodsTypeId) {
        ArrayAdapter<String> mBrandAdapter = new ArrayAdapter<>(getContext(), android.R.layout
                .simple_spinner_item, mPresenters.getGoodsBrands(goodsTypeId));
        mBrandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerBrand.setAdapter(mBrandAdapter);
    }
}
