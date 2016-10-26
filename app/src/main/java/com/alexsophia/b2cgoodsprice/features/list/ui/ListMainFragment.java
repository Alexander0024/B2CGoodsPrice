package com.alexsophia.b2cgoodsprice.features.list.ui;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
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
import com.alexsophia.b2cgoodsprice.share.CustomDialog;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import greendao.Goods;

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
        mLVPrices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Goods good = mPriceListAdapter.getItem(position);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ID = ").append(good.getId()).append("\n")
                        .append("Good ID = ").append(good.getGoodsId()).append("\n")
                        .append("Type = ").append(good.getType()).append("\n")
                        .append("Brand = ").append(good.getBrand()).append("\n")
                        .append("Name = ").append(good.getName()).append("\n")
                        .append("Standard = ").append(good.getStandard()).append("\n")
                        .append("Online price = ").append(good.getCheapest_online()).append("\n")
                        .append("Offline price = ").append(good.getCheapest_offline());
                new CustomDialog.Builder(getContext())
                        .setTitle("详情")
                        .setMessage(stringBuilder.toString())
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
        });
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
