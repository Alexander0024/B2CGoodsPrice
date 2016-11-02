package com.alexsophia.b2cgoodsprice.features.list.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.features.base.executor.Executor;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.base.threading.MainThread;
import com.alexsophia.b2cgoodsprice.features.list.presenters.ListPresenters;

import java.util.List;

import greendao.Goods;

/**
 * ListPresentersImpl
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class ListPresentersImpl extends AbstractPresenter implements ListPresenters {
    private final View mView;
    private final DbMaster mDbMaster;

    public ListPresentersImpl(Executor executor, MainThread mainThread, ListPresenters.View view) {
        super(executor, mainThread);
        this.mView = view;
        this.mDbMaster = MyApplication.getInstance().getDbMaster();
    }

    @Override
    public List<Goods> getGoods() {
        return mDbMaster.getGoodsList();
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
