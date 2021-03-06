package com.alexsophia.b2cgoodsprice.features.main.presenters;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.features.base.presenters.BasePresenter;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseView;

/**
 * MainPresenters
 * <p>
 * Created by Alexander on 2016/10/25.
 */
public interface MainPresenters extends BasePresenter {
    interface View extends BaseView {
        /**
         * 获取当前Activity的Context
         *
         * @return 当前Activity的Context
         */
        Context getContext();
    }
}
