package com.alexsophia.b2cgoodsprice.features.add.presenters;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.features.base.presenters.BasePresenter;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseView;

/**
 * AddPresenters
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public interface AddPresenters extends BasePresenter {
    interface View extends BaseView {
        /**
         * 获取当前Activity的Context
         *
         * @return 当前Activity的Context
         */
        Context getContext();

        String getType();

        String getBrand();

        String getName();

        String getStandard();

        boolean getOnlineOffline();

        double getPrice();
    }

    /**
     * 添加一个新记录
     */
    void addNew();
}
