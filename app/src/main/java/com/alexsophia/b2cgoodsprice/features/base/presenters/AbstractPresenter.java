package com.alexsophia.b2cgoodsprice.features.base.presenters;


import com.alexsophia.b2cgoodsprice.features.base.executor.Executor;
import com.alexsophia.b2cgoodsprice.features.base.executor.impl.ThreadExecutor;
import com.alexsophia.b2cgoodsprice.features.base.threading.MainThread;
import com.alexsophia.b2cgoodsprice.features.base.threading.impl.MainThreadImpl;

/**
 * This is a base class for all presenters which are communicating with interactors. This base
 * class will hold a reference to the Executor and MainThread objects that are needed for running
 * interactors in a background thread.
 */
public abstract class AbstractPresenter {
    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter() {
        mExecutor = ThreadExecutor.getInstance();
        mMainThread = MainThreadImpl.getInstance();
    }

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}