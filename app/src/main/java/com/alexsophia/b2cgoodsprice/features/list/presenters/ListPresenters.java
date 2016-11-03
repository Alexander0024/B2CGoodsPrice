package com.alexsophia.b2cgoodsprice.features.list.presenters;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.features.base.presenters.BasePresenter;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseView;

import java.util.List;

import greendao.Goods;

/**
 * ListPresenters
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public interface ListPresenters extends BasePresenter {
    /**
     * 获得GoodsType 列表
     *
     * @return GoodsTypes
     */
    List<String> getGoodsTypes();

    /**
     * 设置选中的type，注意0为不分类
     *
     * @param position 选中的index
     */
    void selectType(int position);

    /**
     * 获得物品列表
     *
     * @return 物品信息列表
     */
    List<Goods> getGoods();

    interface View extends BaseView {
        /**
         * 获取当前Activity的Context
         *
         * @return 当前Activity的Context
         */
        Context getContext();
    }
}
