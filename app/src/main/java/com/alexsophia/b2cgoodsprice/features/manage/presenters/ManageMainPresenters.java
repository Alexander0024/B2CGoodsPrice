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
     * 初始化PriceType
     */
    void initPriceType();

    interface View extends BaseView {
        /**
         * 获取当前Activity的Context
         *
         * @return 当前Activity的Context
         */
        Context getContext();

        void onInitPriceTypeSuccess(String message);
    }
}
