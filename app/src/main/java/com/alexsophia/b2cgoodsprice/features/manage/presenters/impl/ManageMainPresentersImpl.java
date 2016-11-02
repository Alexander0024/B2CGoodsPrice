package com.alexsophia.b2cgoodsprice.features.manage.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.ManageMainPresenters;

import greendao.PriceType;

/**
 * ManageMainPresentersImpl
 * <p>
 * Created by Alexander on 2016/11/2.
 */
public class ManageMainPresentersImpl extends AbstractPresenter implements ManageMainPresenters {
    private final DbMaster mDbMaster;
    private String TAG = "ManageMainPresentersImpl";
    private ManageMainPresenters.View mView;

    public ManageMainPresentersImpl(ManageMainPresenters.View view) {
        this.mView = view;
        mDbMaster = MyApplication.getInstance().getDbMaster();
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
    public void initPriceType() {
        if (mDbMaster.getPriceTypes().size() == 0) {
            mDbMaster.addPriceType(new PriceType(null, "网店"));
            mDbMaster.addPriceType(new PriceType(null, "实体店"));
            mView.onInitPriceTypeSuccess("初始化价格类型成功！");
        } else {
            mView.showError("已经存在数据，无法初始化！");
        }
    }
}
