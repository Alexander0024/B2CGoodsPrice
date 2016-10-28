package com.alexsophia.b2cgoodsprice.features.manage.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.executor.impl.ThreadExecutor;
import com.alexsophia.b2cgoodsprice.features.base.threading.impl.MainThreadImpl;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseActivity;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.GoodsTypeManagePresenters;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.impl.GoodsTypeManagePresentersImpl;
import com.alexsophia.b2cgoodsprice.share.adapters.CommonAdapter;
import com.alexsophia.b2cgoodsprice.share.adapters.ViewHolder;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import greendao.GoodsType;

/**
 * GoodsTypeManageActivity
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class GoodsTypeManageActivity extends BaseActivity implements GoodsTypeManagePresenters
        .View {
    @Bind(R.id.tv_manage_goods_type_count)
    TextView mTvManageGoodsTypeCount;
    @Bind(R.id.lv_manage_goods_type)
    ListView mLvManageGoodsType;
    @Bind(R.id.edtTxt_manage_new_type_name)
    EditText mEdtTxtManageNewTypeName;
    @Bind(R.id.btn_manage_add)
    Button mBtnManageAdd;

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
        mPresenters = new GoodsTypeManagePresentersImpl(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(), this);
        mAdapter = new CommonAdapter<GoodsType>(this, R.layout
                .manage_goods_type_item, mPresenters.getGoodsType()) {
            @Override
            public void covertView(ViewHolder viewholder, GoodsType goodsType) {
                viewholder.setText(R.id.tv_manage_goods_type_id, String.valueOf(goodsType
                        .getGoodsTypeId()));
                viewholder.setText(R.id.tv_manage_goods_type_name, goodsType.getGoodsTypeName());
                viewholder.setText(R.id.tv_manage_goods_type_brand_count, String.valueOf
                        (goodsType.getBrandList().size()));
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
                mPresenters.addNewType();
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
        ToastUtil.showLong(this, "添加分类成功，id = " + id);
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

    private void refreshUI() {
        mEdtTxtManageNewTypeName.setText("");
        mTvManageGoodsTypeCount.setText(String.valueOf(mPresenters.getGoodsType().size()));
        mAdapter.reflushAdapter(mPresenters.getGoodsType());
    }
}
