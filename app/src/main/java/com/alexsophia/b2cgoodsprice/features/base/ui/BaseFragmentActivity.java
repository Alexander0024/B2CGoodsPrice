package com.alexsophia.b2cgoodsprice.features.base.ui;

import android.app.Activity;
import android.os.Bundle;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * BaseFragmentActivity
 * <p>
 * Created by Alexander on 2016/10/24.
 */
public abstract class BaseFragmentActivity extends AutoLayoutActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewRes());
        ButterKnife.bind(getTarget());//绑定对象
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        ButterKnife.unbind(getTarget());
    }

    //fragment onStop时的业务逻辑
    protected abstract void stop();

    //销毁该fragment时的业务逻辑
    protected abstract void destroy();

    /**
     * 布局文件id
     */
    protected abstract int getContentViewRes();

    //获取需要绑定的fragment对象
    protected abstract Activity getTarget();

    //获取数据
    protected abstract void loadData();

    //重新展示数据
    protected abstract void resumeData();
}
