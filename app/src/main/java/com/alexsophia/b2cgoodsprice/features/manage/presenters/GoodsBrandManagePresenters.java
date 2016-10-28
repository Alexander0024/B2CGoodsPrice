package com.alexsophia.b2cgoodsprice.features.manage.presenters;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.features.base.presenters.BasePresenter;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseView;

/**
 * GoodsBrandManagePresenters
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public interface GoodsBrandManagePresenters extends BasePresenter {
    /**
     * 获得所有的物品类别
     *
     * @return 物品类别
     */
    String[] getGoodsTypes();

    /**
     * 添加新厂商时选择的分类
     * @param position 数组ID
     */
    void selectType(int position);

    interface View extends BaseView {
        /**
         * 获取当前Activity的Context
         *
         * @return 当前Activity的Context
         */
        Context getContext();
    }
}
