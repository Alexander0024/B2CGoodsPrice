package com.alexsophia.b2cgoodsprice.features.list.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.executor.impl.ThreadExecutor;
import com.alexsophia.b2cgoodsprice.features.base.threading.impl.MainThreadImpl;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseFragment;
import com.alexsophia.b2cgoodsprice.features.list.presenters.ListPresenters;
import com.alexsophia.b2cgoodsprice.features.list.presenters.impl.ListPresentersImpl;
import com.alexsophia.b2cgoodsprice.features.list.ui.adapter.PriceListAdapter;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.Bind;
import greendao.Goods;

/**
 * ListMainFragment
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class ListMainFragment extends BaseFragment implements ListPresenters.View {
    @Bind(R.id.spinner_filter)
    Spinner mSpinnerFilter;
    @Bind(R.id.lVi_list_prices)
    ListView mLVPrices;
    @Bind(R.id.tv_list_count)
    TextView mTvCount;
    private String TAG = "ListMainFragment";
    private ListPresentersImpl mPresenters;
    // Type Spinner的Adapter
    private ArrayAdapter<String> mTypeAdapter;
    // ListView的Adapter
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
        LogWrapper.e(TAG, "Resume: Refresh list");
        refreshUI();
    }

    @Override
    protected void destroyView() {

    }

    @Override
    protected void initData() {
        LogWrapper.e(TAG, "initData");
        mPresenters = new ListPresentersImpl(ThreadExecutor.getInstance(), MainThreadImpl
                .getInstance(), this);
        // Init spinner
        mTypeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,
                mPresenters.getGoodsTypes());
        mTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerFilter.setAdapter(mTypeAdapter);
        mSpinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenters.selectType(position);
                refreshUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Init list view
        mPriceListAdapter = new PriceListAdapter(getContext(), new ArrayList<Goods>());
        mLVPrices.setAdapter(mPriceListAdapter);
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
        mTvCount.setText(getString(R.string.total_count, mPresenters.getGoods().size()));
        mPriceListAdapter.updateListView(mPresenters.getGoods());
        mPriceListAdapter.notifyDataSetChanged();
    }
}
