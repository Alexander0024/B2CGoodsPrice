package com.alexsophia.b2cgoodsprice.features.main.presenters.impl;

import android.content.Context;

import com.alexsophia.b2cgoodsprice.features.base.executor.Executor;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.base.threading.MainThread;
import com.alexsophia.b2cgoodsprice.features.main.entity.MovieEntity;
import com.alexsophia.b2cgoodsprice.features.main.interactors.MovieTop250Interactors;
import com.alexsophia.b2cgoodsprice.features.main.interactors.impl.MovieTop250InteractorsImpl;
import com.alexsophia.b2cgoodsprice.features.main.presenters.MainPresenters;

/**
 * MainPresentersImpl
 * <p>
 * Created by Alexander on 2016/10/25.
 */
public class MainPresentersImpl extends AbstractPresenter implements MainPresenters,
        MovieTop250Interactors.Callback {
    private MainPresenters.View mView;

    public MainPresentersImpl(Executor executor, MainThread mainThread, MainPresenters.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override
    public void getPrice(String url) {

    }

    @Override
    public void getTopMovie250() {
        MovieTop250Interactors interactors = new MovieTop250InteractorsImpl(mExecutor,
                mMainThread, this);
        interactors.execute();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public Context getContext() {
        return mView.getContext();
    }

    @Override
    public int getStart() {
        return 0;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public void onGetMovieTop250Success(MovieEntity movieEntity) {
        mView.onGetMovieTop250Success(movieEntity);
    }

    @Override
    public void onGetMovieTop250Failed(String errorMessage) {
        mView.showError(errorMessage);
    }
}
