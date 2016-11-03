package com.alexsophia.b2cgoodsprice.features.manage.ui;

import android.view.View;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseFragment;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.ManageMainPresenters;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.impl.ManageMainPresentersImpl;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ManageMainFragment 管理主页
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class ManageMainFragment extends BaseFragment implements ManageMainPresenters.View {
    @Bind(R.id.rl_manage_goods_type)
    AutoRelativeLayout mRlManageGoodsType; // 管理分类
    @Bind(R.id.rl_manage_goods_brand)
    AutoRelativeLayout mRlManageGoodsBrand; // 管理厂商
    @Bind(R.id.rl_manage_init_database)
    AutoRelativeLayout mRlInitDatabase; // 初始化数据库
    private ManageMainPresenters mPresenters;

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
        mPresenters = new ManageMainPresentersImpl(this);
    }

    @OnClick({R.id.rl_manage_goods_type, R.id.rl_manage_goods_brand, R.id
            .rl_manage_init_database})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_manage_goods_type:
                startActivity(getActivity(), GoodsTypeManageActivity.class);
                break;
            case R.id.rl_manage_goods_brand:
                startActivity(getActivity(), GoodsBrandManageActivity.class);
                break;
            case R.id.rl_manage_init_database:
                mPresenters.initDatabase();
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        ToastUtil.showLong(getContext(), message);
    }

    @Override
    public void refreshUI() {

    }

    @Override
    public void onInitSuccess(String message) {
        ToastUtil.showLong(getContext(), message);
    }
}
