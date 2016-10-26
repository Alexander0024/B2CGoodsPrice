package com.alexsophia.b2cgoodsprice.features.main.presenters.impl;

import com.alexsophia.b2cgoodsprice.features.base.executor.Executor;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.base.threading.MainThread;
import com.alexsophia.b2cgoodsprice.features.main.presenters.MainPresenters;

/**
 * MainPresentersImpl
 * <p>
 * Created by Alexander on 2016/10/25.
 */
public class MainPresentersImpl extends AbstractPresenter implements MainPresenters {
    private MainPresenters.View mView;

    public MainPresentersImpl(Executor executor, MainThread mainThread, MainPresenters.View view) {
        super(executor, mainThread);
        this.mView = view;
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
}
