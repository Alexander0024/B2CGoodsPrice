package com.alexsophia.b2cgoodsprice.features.manage.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.ManageMainPresenters;

import greendao.GoodsBrand;
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
        mDbMaster.addGoodsType(new GoodsType(1L, "身体护理"));
        mDbMaster.addGoodsType(new GoodsType(2L, "清洁用品"));
        mDbMaster.addGoodsType(new GoodsType(3L, "运动健身"));
        // Init goods brand
        mDbMaster.clearGoodsBrands();
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "海飞丝", 1L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "潘婷", 1L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "清扬", 1L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "舒肤佳", 1L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "多芬", 1L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "B&B", 1L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "高露洁", 1L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "云南白药", 1L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "碧浪", 2L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "蓝月亮", 2L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "奥妙", 2L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "汰渍", 2L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "威露士", 2L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "心相印", 2L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "维达", 2L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "清风", 2L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "泉林本色", 2L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "舒洁", 2L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "红双喜", 3L));
        mDbMaster.addGoodsBrand(new GoodsBrand(null, "双鱼", 3L));

        // Init goods
        mDbMaster.clearGoods();

        // Init price type
        mDbMaster.clearPriceType();
        mDbMaster.addPriceType(new PriceType(null, "网店"));
        mDbMaster.addPriceType(new PriceType(null, "实体店"));
        mView.onInitSuccess("初始化成功！");
    }
}
