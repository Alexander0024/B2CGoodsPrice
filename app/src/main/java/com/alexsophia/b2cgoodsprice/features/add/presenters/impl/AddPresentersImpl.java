package com.alexsophia.b2cgoodsprice.features.add.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.features.add.presenters.AddPresenters;
import com.alexsophia.b2cgoodsprice.features.base.executor.Executor;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.base.threading.MainThread;

import java.util.ArrayList;
import java.util.List;

import greendao.Goods;
import greendao.GoodsBrand;
import greendao.GoodsType;

/**
 * AddPresentersImpl
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class AddPresentersImpl extends AbstractPresenter implements AddPresenters {
    private final View mView;
    private final DbMaster mDbMaster;
    private GoodsType mSelectedGoodsType;
    private GoodsBrand mSelectedBrand;

    public AddPresentersImpl(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);
        this.mView = view;
        this.mDbMaster = MyApplication.getInstance().getDbMaster();
    }

    @Override
    public String[] getGoodsTypes() {
        List<String> typeStr = new ArrayList<>();
        for (GoodsType goodsType : mDbMaster.getGoodsTypes()) {
            typeStr.add(goodsType.getGoodsTypeName());
        }
        return typeStr.toArray(new String[typeStr.size()]);
    }

    @Override
    public String[] getGoodsBrands(long typeId) {
        List<String> brandStr = new ArrayList<>();
        for (GoodsBrand goodsBrand : mDbMaster.getGoodsBrands()) {
            if (typeId == goodsBrand.getGoodsTypeId()) {
                brandStr.add(goodsBrand.getGoodsBrandName());
            }
        }
        return brandStr.toArray(new String[brandStr.size()]);
    }

    @Override
    public void selectType(int position) {
        mSelectedGoodsType = mDbMaster.getGoodsTypes().get(position);
        mView.onTypeSelected(mSelectedGoodsType.getGoodsTypeId());
    }

    @Override
    public void selectBrand(int position) {
        List<GoodsBrand> brands = new ArrayList<>();
        for (GoodsBrand brand : mDbMaster.getGoodsBrands()) {
            if (brand.getGoodsType().getGoodsTypeId().equals(mSelectedGoodsType.getGoodsTypeId())) {
                brands.add(brand);
            }
        }
        mSelectedBrand = brands.get(position);
        mView.onBrandSelected(mSelectedBrand.getGoodsBrandId());
    }

    @Override
    public void addNew() {
        Goods newGood = new Goods();
//        newGood.setGoodsTypeId(mSelectedGoodsType.getGoodsTypeId());
        newGood.setGoodsType(mSelectedGoodsType);
//        newGood.setGoodsBrandId(mSelectedBrand.getGoodsBrandId());
        newGood.setGoodsBrand(mSelectedBrand);
        newGood.setGoodsName(mView.getName());
        newGood.setGoodsStandard(mView.getStandard());
        if (mView.getOnlineOffline()) {
            newGood.setCheapestOnline(mView.getPrice());
            newGood.setCheapestOffline(-1d);
        } else {
            newGood.setCheapestOnline(-1d);
            newGood.setCheapestOffline(mView.getPrice());
        }
        long id = mDbMaster.addOrUpdateGoods(newGood);
        if (id > 0) {
            mView.onAddGoodsSuccess(id);
        } else {
            mView.showError("Add failed");
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
