package com.alexsophia.b2cgoodsprice.features.manage.presenters;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.features.base.presenters.BasePresenter;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseView;

import java.util.List;

import greendao.GoodsBrand;

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
     *
     * @param position 数组ID
     */
    void selectType(int position);

    /**
     * 添加一个新的厂商信息
     */
    void addNewBrand();

    /**
     * 更新厂商信息
     * @param goodsBrand 新信息
     */
    void updateBrand(GoodsBrand goodsBrand);

    /**
     * 获得厂商信息列表
     *
     * @return 厂商信息列表
     */
    List<GoodsBrand> getGoodsBrands();

    interface View extends BaseView {
        /**
         * 获取当前Activity的Context
         *
         * @return 当前Activity的Context
         */
        Context getContext();

        /**
         * 获得输入的厂商信息
         *
         * @return 厂商信息
         */
        String getBrandName();

        /**
         * 添加厂商信息成功
         *
         * @param id 厂商ID
         */
        void onAddNewBrandSuccess(long id);
    }
}
