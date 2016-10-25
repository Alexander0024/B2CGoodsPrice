package com.alexsophia.b2cgoodsprice.features.main.interactors.impl;

import com.alexsophia.b2cgoodsprice.features.base.executor.Executor;
import com.alexsophia.b2cgoodsprice.features.base.interactors.AbstractInteractor;
import com.alexsophia.b2cgoodsprice.features.base.threading.MainThread;
import com.alexsophia.b2cgoodsprice.features.main.entity.MovieEntity;
import com.alexsophia.b2cgoodsprice.features.main.interactors.MovieTop250Interactors;
import com.alexsophia.b2cgoodsprice.network.ApiManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * MovieTop250InteractorsImpl
 * <p>
 * Created by Alexander on 2016/10/25.
 */
public class MovieTop250InteractorsImpl extends AbstractInteractor implements MovieTop250Interactors {
    private final Callback mCallback;

    public MovieTop250InteractorsImpl(Executor threadExecutor, MainThread mainThread, Callback callback) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
    }

    @Override
    public void run() {
        ApiManager.getInstance().getTopMovie(mCallback.getStart(), mCallback.getCount())
                .subscribeOn(Schedulers.immediate())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieEntity>() {
                    @Override
                    public void call(MovieEntity movieEntity) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }
}
