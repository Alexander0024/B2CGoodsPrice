package com.alexsophia.b2cgoodsprice.features.goods.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.goods.presenters.GoodsMainPresenters;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;

import java.util.ArrayList;
import java.util.List;

import greendao.Goods;
import greendao.GoodsType;

/**
 * GoodsMainPresentersImpl
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class GoodsMainPresentersImpl extends AbstractPresenter implements GoodsMainPresenters {
    private String TAG = "GoodsMainPresentersImpl";
    private final View mView;
    private final DbMaster mDbMaster;
    private GoodsType mSelectedType;

    public GoodsMainPresentersImpl(GoodsMainPresenters.View view) {
        super();
        this.mView = view;
        this.mDbMaster = MyApplication.getInstance().getDbMaster();
    }

    @Override
    public List<String> getGoodsTypes() {
        List<String> typeStr = new ArrayList<>();
        typeStr.add("所有分类");
        for (GoodsType goodsType : mDbMaster.getGoodsTypes()) {
            typeStr.add(goodsType.getGoodsTypeName());
        }
        return typeStr;
    }

    @Override
    public void selectType(int position) {
        LogWrapper.e(TAG, "selectType: select position = " + position);
        if (position != 0) {
            mSelectedType = mDbMaster.getGoodsTypes().get(position - 1);
        } else {
            mSelectedType = null;
        }
    }

    @Override
    public List<Goods> getGoods() {
        if (mSelectedType == null) {
            return mDbMaster.getGoodsList();
        } else {
            return mDbMaster.getGoodsListByTypeId(mSelectedType.getGoodsTypeId());
        }
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
