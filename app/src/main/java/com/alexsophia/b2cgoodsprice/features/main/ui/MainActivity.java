package com.alexsophia.b2cgoodsprice.features.main.ui;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseActivity;
import com.alexsophia.b2cgoodsprice.features.main.entity.MovieEntity;
import com.alexsophia.b2cgoodsprice.network.ApiManager;
import com.alexsophia.b2cgoodsprice.utils.UIJumpUtils;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscriber;

public class MainActivity extends BaseActivity {
    @Bind(R.id.tv_url)
    TextView mTvUrl;
    @Bind(R.id.tv_main)
    TextView mTvMain;

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
        mTvUrl.setText(url);
        getMovie();
    }

    private void getMovie() {
        ApiManager.getInstance().getTopMovie(0, 10, new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                mTvMain.setText(e.getMessage());
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                StringBuilder stringBuilder = new StringBuilder();
                for (MovieEntity.SubjectsBean subject : movieEntity.getSubjects()) {
                    stringBuilder.append(subject.getTitle())
                            .append(" ")
                            .append(subject.getYear())
                            .append("\n");
                }
                mTvMain.setText(stringBuilder.toString());
            }
        });
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
}
