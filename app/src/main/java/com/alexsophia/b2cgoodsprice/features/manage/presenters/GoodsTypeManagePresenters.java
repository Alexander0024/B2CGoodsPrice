package com.alexsophia.b2cgoodsprice.features.manage.presenters;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.features.base.presenters.BasePresenter;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseView;

import java.util.List;

import greendao.GoodsType;

/**
 * GoodsTypeManagePresenters
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public interface GoodsTypeManagePresenters extends BasePresenter {
    /**
     * 获取物品分类信息
     *
     * @return 物品分类信息
     */
    List<GoodsType> getGoodsType();

    /**
     * 添加一个新的分类
     */
    void addType();

    /**
     * 更新分类信息
     */
    void updateType(GoodsType goodsType);

    interface View extends BaseView {
        /**
         * 获取当前Activity的Context
         *
         * @return 当前Activity的Context
         */
        Context getContext();

        /**
         * 获取新的分类名称
         *
         * @return 新分类的名称
         */
        String getNewTypeName();

        /**
         * 添加物品分类成功
         */
        void onAddNewTypeSuccess(long id);
    }
}
