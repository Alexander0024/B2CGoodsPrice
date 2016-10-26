package com.alexsophia.b2cgoodsprice.features.list.ui;

import android.widget.ListView;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.features.base.executor.impl.ThreadExecutor;
import com.alexsophia.b2cgoodsprice.features.base.threading.impl.MainThreadImpl;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseFragment;
import com.alexsophia.b2cgoodsprice.features.list.presenters.ListPresenters;
import com.alexsophia.b2cgoodsprice.features.list.presenters.impl.ListPresentersImpl;
import com.alexsophia.b2cgoodsprice.features.list.ui.adapter.PriceListAdapter;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ListMainFragment
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class ListMainFragment extends BaseFragment implements ListPresenters.View {
    private String TAG = "ListMainFragment";

    @Bind(R.id.lVi_list_prices)
    ListView mLVPrices;
    @Bind(R.id.tv_list_count)
    TextView mTvCount;

    private ListPresentersImpl mListPresenters;
    private PriceListAdapter mPriceListAdapter;

    public static ListMainFragment newInstance() {
        return new ListMainFragment();
    }

    @Override
    protected void stop() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.list_main_fragment;
    }

    @Override
    protected void createView() {

    }

    @Override
    protected void resume() {
        LogWrapper.e(TAG, "Refresh list");
        mPriceListAdapter.updateListView(MyApplication.getInstance().getDataPresenters().getGoods());
        mPriceListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void destroyView() {

    }

    @Override
    protected void initData() {
        mListPresenters = new ListPresentersImpl(ThreadExecutor.getInstance(), MainThreadImpl
                .getInstance(), this);
        mPriceListAdapter = new PriceListAdapter(getContext(), mListPresenters.getGoods());
        mLVPrices.setAdapter(mPriceListAdapter);
        mTvCount.setText(getString(R.string.list_item_count, mListPresenters.getGoods().size()));
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
