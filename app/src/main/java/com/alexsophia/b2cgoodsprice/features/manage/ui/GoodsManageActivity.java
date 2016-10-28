package com.alexsophia.b2cgoodsprice.features.manage.ui;

import android.app.Activity;
import android.view.View;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseActivity;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * GoodsManageActivity
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class GoodsManageActivity extends BaseActivity {
    @Bind(R.id.rl_manage_goods_type)
    AutoRelativeLayout mRlManageGoodsType;
    @Bind(R.id.rl_manage_goods_brand)
    AutoRelativeLayout mRlManageGoodsBrand;

    @Override
    protected void stop() {

    }

    @Override
    protected void destroy() {

    }

    @Override
    protected int getContentViewRes() {
        return R.layout.manage_goods_manage_activity;
    }

    @Override
    protected Activity getTarget() {
        return this;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void resumeData() {

    }

    @OnClick({R.id.rl_manage_goods_type, R.id.rl_manage_goods_brand})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_manage_goods_type:
                startActivity(this, GoodsTypeManageActivity.class);
                break;
            case R.id.rl_manage_goods_brand:
                break;
        }
    }
}
