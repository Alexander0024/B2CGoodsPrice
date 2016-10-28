package com.alexsophia.b2cgoodsprice.features.manage.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.GoodsBrandManagePresenters;

import java.util.ArrayList;
import java.util.List;

import greendao.GoodsType;

/**
 * GoodsBrandManagePresentersImpl
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class GoodsBrandManagePresentersImpl extends AbstractPresenter implements
        GoodsBrandManagePresenters {
    private final View mView;
    private final DbMaster mDbMaster;
    private ArrayList<String> typeStrings;

    public GoodsBrandManagePresentersImpl(GoodsBrandManagePresenters.View view) {
        super();
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
    public String[] getGoodsTypes() {
        List<GoodsType> types = mDbMaster.getGoodsTypes();
         typeStrings = new ArrayList<>();
        for (GoodsType type : types) {
            typeStrings.add(type.getGoodsTypeName());
        }
        return typeStrings.toArray(new String[typeStrings.size()]);
    }

    @Override
    public void selectType(int position) {
//        mSelectedType =
    }
}
