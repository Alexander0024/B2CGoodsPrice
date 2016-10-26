package com.alexsophia.b2cgoodsprice.features.main.presenters;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.features.base.presenters.BasePresenter;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseView;
import com.alexsophia.b2cgoodsprice.features.main.entity.MovieEntity;

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
     * 获得价格
     * @param url 地址
     */
    void getPrice(String url);

    /**
     * 获取Top Movie
     */
    void getTopMovie250();
}
