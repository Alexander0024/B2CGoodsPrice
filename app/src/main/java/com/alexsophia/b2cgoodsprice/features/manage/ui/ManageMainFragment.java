package com.alexsophia.b2cgoodsprice.features.manage.ui;

import android.view.View;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseFragment;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ManageMainFragment
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class ManageMainFragment extends BaseFragment {
    @Bind(R.id.rl_manage_goods)
    AutoRelativeLayout mRlManageGoods;

    public static ManageMainFragment newInstance() {
        return new ManageMainFragment();
    }

    @Override
    protected void stop() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.manage_main_fragment;
    }

    @Override
    protected void createView() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void destroyView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.rl_manage_goods)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_manage_goods:
                startActivity(getActivity(), GoodsManageActivity.class);
                break;
        }
    }
}