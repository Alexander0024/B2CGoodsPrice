package com.alexsophia.b2cgoodsprice.features.main.interactors;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.features.base.interactors.Interactor;
import com.alexsophia.b2cgoodsprice.features.main.entity.MovieEntity;

/**
 * MovieTop250Interactors
 * <p>
 * Created by Alexander on 2016/10/25.
 */
public interface MovieTop250Interactors extends Interactor {
    /**
     * 接口调用的回调接口
     */
    interface Callback {
        /**
         * 获取当前Activity的Context
         *
         * @return 当前Activity的Context
         */
        Context getContext();

        /**
         * 开始坐标
         */
        int getStart();

        /**
         * 计数
         */
        int getCount();

        /**
         * 获得movie成功
         * @param movieEntity movie Entity
         */
        void onGetMovieTop250Success(MovieEntity movieEntity);

        /**
         * 获取movice失败
         * @param errorMessage 错误信息
         */
        void onGetMovieTop250Failed(String errorMessage);
    }
}
