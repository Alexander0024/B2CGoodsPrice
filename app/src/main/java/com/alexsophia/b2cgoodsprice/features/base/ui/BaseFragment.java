package com.alexsophia.b2cgoodsprice.features.base.ui;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.share.LoadingDialog;

import butterknife.ButterKnife;

/**
 * BaseFragment
 * <p>
 * Created by Alexander on 2016/10/24.
 */
public abstract class BaseFragment extends Fragment {
    protected Dialog mProgressDialog;

    @Override
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View contentView = inflater.inflate(getLayoutRes(),
                container, false);
        ButterKnife.bind(this, contentView);
        createView();
        initData();
        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        resume();
    }

    @Override
    public void onStop() {
        super.onStop();
        stop();
    }

    /**
     * onstop中
     */
    protected abstract void stop();

    /**
     * 布局文件id
     */
    protected abstract int getLayoutRes();

    /**
     * onCreateView中调用的方法
     */
    protected abstract void createView();

    /**
     * onResume时调用的方法
     */
    protected abstract void resume();

    /**
     * onDestoryView时调用的方法
     */
    protected abstract void destroyView();

    /**
     * 加载初始化数据
     */
    protected abstract void initData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void showLoadingProgress() {
        showLoadingProgress(R.string.menu_dialog_waiting);
    }

    public void showLoadingProgress(int resID) {
        if (null != mProgressDialog) {
            mProgressDialog.cancel();
        }
        mProgressDialog = new LoadingDialog.Builder(getActivity())
                .setMessage(getResources().getString(resID)).build();
        mProgressDialog.show();
    }

    public void hideLoadingProgress() {
        if (null != mProgressDialog) {
            mProgressDialog.hide();
        }
    }
}
