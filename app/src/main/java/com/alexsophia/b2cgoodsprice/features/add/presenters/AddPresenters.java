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
    /**
     * 获取types
     */
    String[] getGoodsTypes();

    /**
     * 获取brands
     * @param typeId
     */
    String[] getGoodsBrands(long typeId);

    /**
     * 选择Type
     *
     * @param position index
     */
    void selectType(int position);

    /**
     * 选择Brand
     *
     * @param position index
     */
    void selectBrand(int position);

    /**
     * 添加一个新记录
     */
    void addNew();

    interface View extends BaseView {
        /**
         * 获取当前Activity的Context
         *
         * @return 当前Activity的Context
         */
        Context getContext();

        String getName();

        String getStandard();

        boolean getOnlineOffline();

        double getPrice();

        void onTypeSelected(Long goodsTypeId);

        void onBrandSelected(Long goodsBrandId);

        void onAddGoodsSuccess(long id);
    }
}
