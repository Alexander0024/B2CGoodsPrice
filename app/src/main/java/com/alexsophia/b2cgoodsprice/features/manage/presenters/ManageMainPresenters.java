package com.alexsophia.b2cgoodsprice.features.manage.presenters;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.features.base.presenters.BasePresenter;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseView;

/**
 * ManageMainPresenters
 * <p>
 * Created by Alexander on 2016/11/2.
 */
public interface ManageMainPresenters extends BasePresenter {

    /**
     * 初始化
     */
    void initDatabase();

    interface View extends BaseView {
        /**
         * 获取当前Activity的Context
         *
         * @return 当前Activity的Context
         */
        Context getContext();

        void onInitSuccess(String message);
    }
}
