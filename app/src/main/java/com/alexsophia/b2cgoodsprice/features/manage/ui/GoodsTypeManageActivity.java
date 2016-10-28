package com.alexsophia.b2cgoodsprice.features.manage.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseActivity;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.GoodsTypeManagePresenters;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.impl.GoodsTypeManagePresentersImpl;
import com.alexsophia.b2cgoodsprice.share.SimpleEditTextBox;
import com.alexsophia.b2cgoodsprice.share.adapters.CommonAdapter;
import com.alexsophia.b2cgoodsprice.share.adapters.ViewHolder;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import greendao.GoodsType;

/**
 * GoodsTypeManageActivity 管理 - 物品类别管理
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class GoodsTypeManageActivity extends BaseActivity implements GoodsTypeManagePresenters
        .View {
    @Bind(R.id.tv_manage_goods_type_count)
    TextView mTvManageGoodsTypeCount; // 物品分类总计
    @Bind(R.id.lv_manage_goods_type)
    ListView mLvManageGoodsType; // 物品分类列表
    @Bind(R.id.edtTxt_manage_new_type_name)
    EditText mEdtTxtManageNewTypeName; // 新分类输入框
    @Bind(R.id.btn_manage_add)
    Button mBtnManageAdd; // 添加按钮

    private String TAG = "GoodsTypeManageActivity";
    private GoodsTypeManagePresenters mPresenters;
    private CommonAdapter<GoodsType> mAdapter;

    @Override
    protected void stop() {

    }

    @Override
    protected void destroy() {

    }

    @Override
    protected int getContentViewRes() {
        return R.layout.manage_goods_type_activity;
    }

    @Override
    protected Activity getTarget() {
        return this;
    }

    @Override
    protected void loadData() {
        LogWrapper.e(TAG, "loadData: ");
        mPresenters = new GoodsTypeManagePresentersImpl(this);
        /**
         * 设置分类列表的Adapter
         */
        mAdapter = new CommonAdapter<GoodsType>(this, R.layout
                .manage_goods_type_item, mPresenters.getGoodsType()) {
            @Override
            public void covertView(ViewHolder viewholder, final GoodsType goodsType) {
                // 分类ID
                viewholder.setText(R.id.tv_manage_goods_type_id, String.valueOf(goodsType
                        .getGoodsTypeId()));
                // 分类名称
                viewholder.setText(R.id.tv_manage_goods_type_name, goodsType.getGoodsTypeName());
                viewholder.setClickListener(R.id.tv_manage_goods_type_name, new View
                        .OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new SimpleEditTextBox(getContext(), "更新分类名称", "", goodsType
                                .getGoodsTypeName(), new SimpleEditTextBox.OnClickListener() {
                            @Override
                            public void onPositiveButtonClicked(String value) {
                                goodsType.setGoodsTypeName(value);
                                mPresenters.updateType(goodsType);
                            }
                        });
                    }
                });
                // 分类下厂商信息
                viewholder.setText(R.id.tv_manage_goods_type_brand_count, String.valueOf
                        (goodsType.getBrandList().size()));
                // 分类下物品信息
                viewholder.setText(R.id.tv_manage_goods_type_goods_count, String.valueOf
                        (goodsType.getGoodsList().size()));
            }
        };
        mLvManageGoodsType.setAdapter(mAdapter);
    }

    @Override
    protected void resumeData() {
        LogWrapper.e(TAG, "resumeData: ");
        refreshUI();
    }

    @OnClick(R.id.btn_manage_add)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_manage_add:
                mPresenters.addType();
                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getNewTypeName() {
        return mEdtTxtManageNewTypeName.getText().toString().trim();
    }

    @Override
    public void onAddNewTypeSuccess(long id) {
        LogWrapper.e(TAG, "onAddNewTypeSuccess: " + id);
        ToastUtil.showLong(this, "分类编辑成功，id = " + id);
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
        ToastUtil.showLong(getContext(), message);
    }

    @Override
    public void refreshUI() {
        mEdtTxtManageNewTypeName.setText("");
        mTvManageGoodsTypeCount.setText(getString(R.string.total_count, mPresenters.getGoodsType
                ().size()));
        mAdapter.reflushAdapter(mPresenters.getGoodsType());
    }
}
