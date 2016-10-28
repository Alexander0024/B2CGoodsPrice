package com.alexsophia.b2cgoodsprice.features.add.presenters.impl;

import com.alexsophia.b2cgoodsprice.features.add.presenters.AddPresenters;
import com.alexsophia.b2cgoodsprice.features.base.executor.Executor;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.base.threading.MainThread;

/**
 * AddPresentersImpl
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class AddPresentersImpl extends AbstractPresenter implements AddPresenters {
    private final View mView;

    public AddPresentersImpl(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override
    public void addNew() {
//        Goods newGood = new Goods();
//        newGood.setGoodsType(mView.getType());
//        newGood.setBrand(mView.getBrand());
//        newGood.setName(mView.getName());
//        newGood.setStandard(mView.getStandard());
//        if (mView.getOnlineOffline()) {
//            newGood.setCheapest_online(mView.getPrice());
//            newGood.setCheapest_offline(-1d);
//        } else {
//            newGood.setCheapest_online(-1d);
//            newGood.setCheapest_offline(mView.getPrice());
//        }
//        MyApplication.getInstance().getDataPresenters().addGoods(newGood, new DataPresentersImpl
//                .OnOperatorListener() {
//            @Override
//            public void onSuccess(long id) {
//                ToastUtil.showLong(mView.getContext(), "Add Success, item id = " + id);
//            }
//
//            @Override
//            public void onFailed(String message) {
//                ToastUtil.showLong(mView.getContext(), "Add Failed, " + message);
//            }
//        });
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
