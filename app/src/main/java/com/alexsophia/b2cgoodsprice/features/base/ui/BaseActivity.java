package com.alexsophia.b2cgoodsprice.features.base.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.share.LoadingDialog;

import butterknife.ButterKnife;

/**
 * BaseActivity
 * <p>
 * Created by Alexander on 2016/10/24.
 */
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 标示当前activity是否仍然活着
     */
    private boolean mIsStillAlive;
    protected Dialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewRes());
        ButterKnife.bind(getTarget());//绑定对象

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        /**
         * 通用页面调用软键盘事件，防止软键盘覆盖UI输入框
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        loadData();
    }

    @Override
    protected void onResume() {
        mIsStillAlive = true;
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
        mIsStillAlive = false;
        super.onDestroy();
        destroy();
        ButterKnife.unbind(getTarget());
    }

    /**
     * activity onStop时的业务逻辑
     */
    protected abstract void stop();

    /**
     * 销毁该Activity时的业务逻辑
     */
    protected abstract void destroy();

    /**
     * 布局文件id
     */
    protected abstract int getContentViewRes();

    /**
     * 获取需要绑定的Activity对象
     */
    protected abstract Activity getTarget();

    /**
     * 获取数据
     */
    protected abstract void loadData();

    /**
     * 重新展示数据
     */
    protected abstract void resumeData();

    /**
     * 标示当前Activity是否仍然存活着
     *
     * @return 是否没被销毁：true：仍然运行中；false：已经被销毁
     */
    protected boolean isActivityStillAlive() {
        if (Build.VERSION.SDK_INT >= 17) {
            return !isDestroyed();
        } else {
            return mIsStillAlive;
        }
    }

    /**
     * 页面跳转
     *
     * @param mActivity 当前Activity
     * @param mClass    需要跳转的Activity的class
     */
    protected void startActivity(Activity mActivity, Class mClass) {
        Intent intent = new Intent();
        intent.setClass(mActivity, mClass);
        startActivity(intent);
    }

    /**
     * 调用默认的“加载中...”的等待
     */
    protected void showLoadingProgress() {
        showLoadingProgress(R.string.menu_dialog_waiting, null);
    }

    /**
     * 调用自定义展示名字的等待
     *
     * @param resID Resource ID
     */
    protected void showLoadingProgress(int resID) {
        showLoadingProgress(resID, null);
    }

    protected void showLoadingProgress(DialogInterface.OnCancelListener onCancelListener) {
        showLoadingProgress(R.string.menu_dialog_waiting, onCancelListener);
    }

    protected void showLoadingProgress(int resID, DialogInterface.OnCancelListener
            onCancelListener) {
        if (null != mProgressDialog) {
            mProgressDialog.cancel();
        }
        LoadingDialog.Builder builder = new LoadingDialog.Builder(this);
        builder.setMessage(getResources().getString(resID));
        if (null != onCancelListener) {
            builder.setOnCancelListener(onCancelListener);
        }
        mProgressDialog = builder.build();
        mProgressDialog.show();
    }

    /**
     * 隐藏等待
     */
    protected void hideLoadingProgress() {
        if (null != mProgressDialog) {
            mProgressDialog.hide();
        }
    }
}
