package com.alexsophia.b2cgoodsprice.features.main.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.executor.impl.ThreadExecutor;
import com.alexsophia.b2cgoodsprice.features.base.threading.impl.MainThreadImpl;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseActivity;
import com.alexsophia.b2cgoodsprice.features.main.entity.MovieEntity;
import com.alexsophia.b2cgoodsprice.features.main.presenters.MainPresenters;
import com.alexsophia.b2cgoodsprice.features.main.presenters.impl.MainPresentersImpl;
import com.alexsophia.b2cgoodsprice.utils.UIJumpUtils;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements MainPresenters.View {
    @Bind(R.id.tv_url)
    TextView mTvUrl;
    @Bind(R.id.tv_main)
    TextView mTvMain;

    private MainPresenters mMainPresenters;

    String url = "http://item.taobao.com/item.htm?id=534149604055";
//    String url = "https://detail.tmall.com/item.htm?spm=a230r.1.14.6.LRdWIF&id=42580172942&cm_id=140105335569ed55e27b&abbucket=14";

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
        mMainPresenters = new MainPresentersImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        mTvUrl.setText(url);
        mMainPresenters.getTopMovie250();
    }

    @Override
    protected void resumeData() {

    }

    @OnClick(R.id.tv_url)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_url:
                UIJumpUtils.openWebpageOnBrowser(this, url);
                break;
            default:
        }
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
        mTvMain.setText(stringBuilder.toString());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
