package com.alexsophia.b2cgoodsprice.features.manage.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.ManageMainPresenters;

import greendao.GoodsType;
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
    public void initDatabase() {
        // Init goods type
        mDbMaster.clearGoodsType();
        mDbMaster.addGoodsType(new GoodsType(1L, "洗发护发"));
        mDbMaster.addGoodsType(new GoodsType(2L, "身体护理"));
        mDbMaster.addGoodsType(new GoodsType(3L, "口腔护理"));
        mDbMaster.addGoodsType(new GoodsType(4L, "纸品湿巾"));
        mDbMaster.addGoodsType(new GoodsType(5L, "洗涤用品"));
        mDbMaster.addGoodsType(new GoodsType(6L, "清洁用品"));
        mDbMaster.addGoodsType(new GoodsType(7L, "运动健身"));
        // Init goods brand
        mDbMaster.clearGoodsBrands();

        // Init goods
        mDbMaster.clearGoods();

        // Init price type
        mDbMaster.clearPriceType();
        mDbMaster.addPriceType(new PriceType(null, "网店"));
        mDbMaster.addPriceType(new PriceType(null, "实体店"));
        mView.onInitSuccess("初始化成功！");
    }
}
