package com.alexsophia.b2cgoodsprice.features.goods.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseFragment;
import com.alexsophia.b2cgoodsprice.features.goods.presenters.ListPresenters;
import com.alexsophia.b2cgoodsprice.features.goods.presenters.impl.ListPresentersImpl;
import com.alexsophia.b2cgoodsprice.features.goods.ui.adapter.GoodsListAdapter;
import com.alexsophia.b2cgoodsprice.share.events.UpdateGoodsEvents;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import greendao.Goods;

/**
 * ListMainFragment
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class ListMainFragment extends BaseFragment implements ListPresenters.View {
    // Header 统计数字
    @Bind(R.id.tv_list_count)
    TextView mTvCount;
    // Header 添加新项目
    @Bind(R.id.btn_add_new)
    Button btnAddNew;
    // Header 下拉过滤栏
    @Bind(R.id.spinner_filter)
    Spinner mSpinnerFilter;
    // Content ListView
    @Bind(R.id.lVi_list_goods)
    ListView mLVGoods;
    private String TAG = "ListMainFragment";
    private ListPresentersImpl mPresenters;
    // ListView的Adapter
    private GoodsListAdapter mGoodsListAdapter;

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
        EventBus.getDefault().register(this);
    }

    @Override
    protected void resume() {
        LogWrapper.e(TAG, "Resume: Refresh list");
        refreshUI();
    }

    @Override
    protected void destroyView() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initData() {
        LogWrapper.e(TAG, "initData");
        mPresenters = new ListPresentersImpl(this);
        // Init spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getContext(), android.R.layout
                .simple_spinner_item, mPresenters.getGoodsTypes());
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerFilter.setAdapter(typeAdapter);
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
        mGoodsListAdapter = new GoodsListAdapter(getContext(), new ArrayList<Goods>());
        mLVGoods.setAdapter(mGoodsListAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @SuppressWarnings("unused")
    public void onMessageEvent(UpdateGoodsEvents event) {
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
        mTvCount.setText(getString(R.string.total_count, mPresenters.getGoods().size()));
        mGoodsListAdapter.updateListView(mPresenters.getGoods());
        mGoodsListAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_add_new)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_new:
                startActivity(getActivity(), AddNewGoodsActivity.class);
                break;
            default:
        }
    }
}