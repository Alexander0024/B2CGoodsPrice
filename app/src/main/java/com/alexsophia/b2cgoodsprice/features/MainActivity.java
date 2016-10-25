package com.alexsophia.b2cgoodsprice.features;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseActivity;
import com.alexsophia.b2cgoodsprice.network.JsoupConnect;
import com.alexsophia.b2cgoodsprice.utils.UIJumpUtils;

import butterknife.Bind;
import butterknife.OnClick;

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
        JsoupConnect.getContent(mTvMain, url);
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
