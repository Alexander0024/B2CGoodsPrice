package com.alexsophia.b2cgoodsprice.features.manage.presenters.impl;

import com.alexsophia.b2cgoodsprice.app.MyApplication;
import com.alexsophia.b2cgoodsprice.database.DbMaster;
import com.alexsophia.b2cgoodsprice.features.base.presenters.AbstractPresenter;
import com.alexsophia.b2cgoodsprice.features.manage.presenters.GoodsBrandManagePresenters;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;

import java.util.ArrayList;
import java.util.List;

import greendao.GoodsBrand;
import greendao.GoodsType;

/**
 * GoodsBrandManagePresentersImpl
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class GoodsBrandManagePresentersImpl extends AbstractPresenter implements
        GoodsBrandManagePresenters {
    private String TAG = "GoodsBrandManagePresentersImpl";
    private final View mView;
    private final DbMaster mDbMaster;
    private ArrayList<String> typeStrings;
    private GoodsType mSelectedType;

    public GoodsBrandManagePresentersImpl(View view) {
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
        mSelectedType = mDbMaster.getGoodsTypes().get(position);
        LogWrapper.e(TAG, "selectType: id = " + mSelectedType.getGoodsTypeId() + "; Name = " +
                mSelectedType.getGoodsTypeName());
    }

    @Override
    public void addNewBrand() {
        String brandName = mView.getBrandName();
        GoodsBrand goodsBrand = new GoodsBrand();
        goodsBrand.setGoodsBrandName(brandName);
        goodsBrand.setGoodsTypeId(mSelectedType.getGoodsTypeId());
        goodsBrand.setGoodsType(mSelectedType);
        long id = mDbMaster.addGoodsBrand(goodsBrand);
        if (id > 0) {
            mView.onAddNewBrandSuccess(id);
        } else {
            mView.showError("Add new brand failed");
        }
    }

    @Override
    public List<GoodsBrand> getGoodsBrands() {
        return mDbMaster.getGoodsBrands();
    }
}
