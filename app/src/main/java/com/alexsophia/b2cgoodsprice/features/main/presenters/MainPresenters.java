package com.alexsophia.b2cgoodsprice.features.main.presenters;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.features.base.presenters.BasePresenter;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseView;
import com.alexsophia.b2cgoodsprice.features.main.entity.MovieEntity;

import java.util.List;

import greendao.Goods;

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

        /**
         * 获得movie成功
         * @param movieEntity movie Entity
         */
        void onGetMovieTop250Success(MovieEntity movieEntity);
    }

    /**
     * 获得物品列表
     *
     * @return 物品信息列表
     */
    List<Goods> getGoods();

    /**
     * 获取Top Movie
     */
    void getTopMovie250();
}
