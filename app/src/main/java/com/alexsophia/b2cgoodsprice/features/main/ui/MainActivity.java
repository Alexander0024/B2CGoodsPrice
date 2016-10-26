package com.alexsophia.b2cgoodsprice.features.main.ui;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.executor.impl.ThreadExecutor;
import com.alexsophia.b2cgoodsprice.features.base.threading.impl.MainThreadImpl;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseActivity;
import com.alexsophia.b2cgoodsprice.features.main.entity.MovieEntity;
import com.alexsophia.b2cgoodsprice.features.main.presenters.MainPresenters;
import com.alexsophia.b2cgoodsprice.features.main.presenters.impl.MainPresentersImpl;
import com.alexsophia.b2cgoodsprice.features.main.ui.adapter.PriceListAdapter;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements MainPresenters.View {
    @Bind(R.id.lVi_prices)
    ListView mLVPrices;

    private MainPresenters mMainPresenters;
    private PriceListAdapter mPriceListAdapter;

    @Override
    protected void stop() {

    }

    @Override
    protected void destroy() {

    }

    @Override
    protected int getContentViewRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Activity getTarget() {
        return this;
    }

    @Override
    protected void loadData() {
        mMainPresenters = new MainPresentersImpl(ThreadExecutor.getInstance(), MainThreadImpl
                .getInstance(), this);
        mPriceListAdapter = new PriceListAdapter(this, mMainPresenters.getGoods());
        mLVPrices.setAdapter(mPriceListAdapter);
    }

    @Override
    protected void resumeData() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onGetMovieTop250Success(MovieEntity movieEntity) {
        final StringBuilder stringBuilder = new StringBuilder();
        Observable.from(movieEntity.getSubjects())
                .subscribe(new Action1<MovieEntity.SubjectsBean>() {
                    @Override
                    public void call(MovieEntity.SubjectsBean subjectsBean) {
                        stringBuilder.append(subjectsBean.getTitle())
                                .append(" ")
                                .append(subjectsBean.getYear())
                                .append("\n");
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
        ToastUtil.showLong(this, message);
    }
}
